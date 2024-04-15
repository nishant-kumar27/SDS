package rispl.ds.order;

import java.util.ArrayList;
import com.rispl.sds.cancel.order.service.CancelOrderServiceIfc;
import javax.naming.NamingException;
import java.util.Iterator;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import rispl.dkart.services.ejb.transaction.OrderTransactionsIfc;
import rispl.ds.DSAction;
import java.text.SimpleDateFormat;
import rispl.dkart.services.entities.transaction.DkartReasonCodes;
import rispl.dkservices.common.OrderTransactionSearchCriteria;
import rispl.ds.context.DKartContext;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import java.math.BigDecimal;
import org.apache.struts2.dispatcher.SessionMap;
import java.util.Map;
import com.rispl.cancel.order.dao.RisplDkCancelOrderSearchV;
import rispl.dkart.services.entities.customer.CustomerSiteAddress;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.order.lookup.dao.OrderDAOBeanRemote;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import com.test.entities.OrderDetailsWithInvoice;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import java.util.List;
import org.apache.logging.log4j.Logger;

public class OrderSearchSummaryAction extends PartialShippingAction
{
    private static final long serialVersionUID = 1L;
    private static final Logger LOG;
    private String empId;
    private List<OrderTranLineItem> order_items;
    private OrderDetailsWithInvoice order;
    private OrderTranSum ord_tran_sum;
    private OrderDAOBeanRemote dao;
    private CustomerHeader customer;
    private String cust_seg;
    private EmployeeIfc employee;
    private CustomerSiteAddress ship_address;
    private RisplDkCancelOrderSearchV cancelOrder;
    private Map<String, String> globalReasonCode;
    private Map<String, String> getAllDiscountReasnCode;
    private SessionMap<String, Object> sessionMap;
    private String delvOrderkey;
    private BigDecimal deliveredQuantity;
    private boolean showDeliveredQuantity;
    private boolean orderCancelAvailable;
    private String ord_timestamp;
    
    static {
        LOG = LogManager.getLogger((Class)OrderSearchSummaryAction.class);
    }
    
    public OrderSearchSummaryAction() {
        this.globalReasonCode = new HashMap<String, String>();
        this.getAllDiscountReasnCode = new HashMap<String, String>();
        this.showDeliveredQuantity = true;
    }
    
    public Map<String, String> getGetAllDiscountReasnCode() {
        return this.getAllDiscountReasnCode;
    }
    
    public void setGetAllDiscountReasnCode(final Map<String, String> getAllDiscountReasnCode) {
        this.getAllDiscountReasnCode = getAllDiscountReasnCode;
    }
    
    public String execute() {
        this.employee = super.getEmployee();
        try {
            final OrderTransactionsIfc trans = DKartContext.getLookupOrder();
            this.globalReasonCode = trans.getAllReasonCodes().get("Returns");
            final OrderTransactionSearchCriteriaIfc search = (OrderTransactionSearchCriteriaIfc)new OrderTransactionSearchCriteria();
            search.setMaximumResults(99);
            search.setSearchByinvoiceNumberOrOrderNumber(true);
            search.setInvoiceNumberOrOrderNumber(this.getOrderId().trim());
            final OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
            this.setOrd_tran_header(orders1[0]);
            this.order_items = (List<OrderTranLineItem>)this.getOrd_tran_header().getOrdTranLineItems();
            this.customer = this.getOrd_tran_header().getCustomer().getCustomerHeader();
            this.cust_seg = this.getOrd_tran_header().getCustomer().getCustomerSegmentID();
            this.ord_tran_sum = this.getOrd_tran_header().getOrdTranSum();
            this.dao = DKartContext.getOrderDAOBean();
            final List<DkartReasonCodes> getDisResnCodesList = (List<DkartReasonCodes>)trans.getAllDisRsnCode();
            for (final DkartReasonCodes getDisResnCodes : getDisResnCodesList) {
                final String resnCodeDes = getDisResnCodes.getRsnDesc();
                final long longrsncode = getDisResnCodes.getRsnCode();
                final String resnCode = Long.toString(longrsncode);
                this.getAllDiscountReasnCode.put(resnCode, resnCodeDes);
            }
            if (this.getOrd_tran_header().getScOrd().compareTo(new BigDecimal(5)) == 0 || this.getOrd_tran_header().getScOrd().compareTo(new BigDecimal(7)) == 0 || this.getOrd_tran_header().getScOrd().compareTo(new BigDecimal(3)) == 0) {
                final String OrderID = this.getOrd_tran_header().getOrdTranSum().getIdOrd();
                final Map<String, Long> quantityMap = new HashMap<String, Long>();
                final OrderTransactionsIfc tran = DKartContext.getLookupOrder();
                final List<OrderTranLineItem> shipOrders = (List<OrderTranLineItem>)tran.getDeliveredQty(OrderID);
                for (final OrderTranLineItem line : shipOrders) {
                    quantityMap.get(line.getLineQnt());
                }
            }
            else {
                this.setShowDeliveredQuantity(false);
            }
            this.order = this.dao.getOrderByOrderId(this.employee, this.getOrderId()).get(0);
            if (this.getOrd_tran_header().getCustomer().getCustomerSiteAddress() != null) {
                this.ship_address = this.getOrd_tran_header().getCustomer().getCustomerSiteAddress().get(0);
            }
            //kunal : order approval time
            this.setOrd_timestamp(this.dao.getTimestampByOrderId(this.getOrderId()));
            
            this.getShippingDetails();
            this.checkForPartialShipping();
            this.checkForOrderCancellation();
            this.putInSession(DSAction.SESSION.ORDER_TRANHEADER, (Object)this.getOrd_tran_header());
        }
        catch (Exception e) {
            OrderSearchSummaryAction.LOG.error(e.getMessage(), (Throwable)e);
            this.addActionError("Error looking up order details for Order ID: " + this.getOrderId());
            return "error";
        }
        return "success";
    }
    
    protected void checkForOrderCancellation() throws Exception {
        String orderId = null;
        if (this.getOrd_tran_header() != null) {
            orderId = this.getOrd_tran_header().getOrdTranSum().getIdOrd();
            final String orderType = this.getOrd_tran_header().getOrdTy();
            if (orderType != null && orderType.equalsIgnoreCase("23")) {
                this.setOrderCancelAvailable(true);
            }
            if (this.getItemsShipped() != null && this.getItemsShipped().size() > 0) {
                final Map<String, List<String>> itemsShipped = (Map<String, List<String>>)this.getItemsShipped();
                OrderSearchSummaryAction.LOG.debug("For Order Id: {}, items shipped are: \n\t{}", (Object)orderId, (Object)itemsShipped);
                final boolean itemShipped = itemsShipped.entrySet().stream().filter(i -> new BigDecimal(i.getValue().get(2)).compareTo(BigDecimal.ZERO) > 0).findFirst().isPresent();
                OrderSearchSummaryAction.LOG.info("For Order Id: {}, is atleast one item shipped: {}", (Object)orderId, (Object)itemShipped);
                this.setOrderCancelAvailable(!itemShipped);
            }
        }
        OrderSearchSummaryAction.LOG.info("For Order Id: {}, order cancellation available: {}", (Object)orderId, (Object)this.isOrderCancelAvailable());
    }
    
    public String displayCancelOrderDetails() {
        this.employee = super.getEmployee();
        try {
            final CancelOrderServiceIfc service = DKartContext.getCancelOrderBean();
            final OrderTransactionsIfc trans = DKartContext.getLookupOrder();
            if (this.employee.getEmployeeId() != null && this.getOrderId() != null) {
                final List<RisplDkCancelOrderSearchV> cancelOrderList = (List<RisplDkCancelOrderSearchV>)service.getCancelOrderByOrderIdTab(this.getOrderId().trim(), "", "", "", "", "", "", this.employee);
                if (cancelOrderList.size() == 1) {
                    this.cancelOrder = cancelOrderList.get(0);
                }
                final OrderTransactionSearchCriteriaIfc search = (OrderTransactionSearchCriteriaIfc)new OrderTransactionSearchCriteria();
                search.setMaximumResults(99);
                search.setSearchByinvoiceNumberOrOrderNumber(true);
                search.setInvoiceNumberOrOrderNumber(this.cancelOrder.getOrderId().trim());
                final OrderTranHeader[] orders1 = trans.getTransactionsInvoices(search);
                this.setOrd_tran_header(orders1[0]);
                this.order_items = (List<OrderTranLineItem>)this.getOrd_tran_header().getOrdTranLineItems();
                this.customer = this.getOrd_tran_header().getCustomer().getCustomerHeader();
                this.cust_seg = this.getOrd_tran_header().getCustomer().getCustomerSegmentID();
                this.ord_tran_sum = this.getOrd_tran_header().getOrdTranSum();
                if (this.getOrd_tran_header().getCustomer().getCustomerSiteAddress() != null) {
                    this.ship_address = this.getOrd_tran_header().getCustomer().getCustomerSiteAddress().get(0);
                }
            }
            final List<DkartReasonCodes> getDisResnCodesList = (List<DkartReasonCodes>)trans.getAllDisRsnCode();
            for (final DkartReasonCodes getDisResnCodes : getDisResnCodesList) {
                final String resnCodeDes = getDisResnCodes.getRsnDesc();
                final long longrsncode = getDisResnCodes.getRsnCode();
                final String resnCode = Long.toString(longrsncode);
                this.getAllDiscountReasnCode.put(resnCode, resnCodeDes);
            }
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e2) {
            e2.printStackTrace();
            System.out.println("Null pointer exception...");
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        return "success";
    }
    
    public Map<String, String> getGlobalReasonCode() {
        return this.globalReasonCode;
    }
    
    public void setGlobalReasonCode(final Map<String, String> globalReasonCode) {
        this.globalReasonCode = globalReasonCode;
    }
    
    public RisplDkCancelOrderSearchV getCancelOrder() {
        return this.cancelOrder;
    }
    
    public void setCancelOrder(final RisplDkCancelOrderSearchV cancelOrder) {
        this.cancelOrder = cancelOrder;
    }
    
    public String getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(final String empId) {
        this.empId = empId;
    }
    
    public EmployeeIfc getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(final EmployeeIfc employee) {
        this.employee = employee;
    }
    
    public OrderDetailsWithInvoice getOrder() {
        return this.order;
    }
    
    public void setOrder(final OrderDetailsWithInvoice order) {
        this.order = order;
    }
    
    public List<OrderTranLineItem> getOrder_items() {
        return this.order_items;
    }
    
    public void setOrder_items(final List<OrderTranLineItem> order_items) {
        this.order_items = order_items;
    }
    
    public CustomerSiteAddress getShip_address() {
        return this.ship_address;
    }
    
    public void setShip_address(final CustomerSiteAddress ship_address) {
        this.ship_address = ship_address;
    }
    
    public OrderTranSum getOrd_tran_sum() {
        return this.ord_tran_sum;
    }
    
    public void setOrd_tran_sum(final OrderTranSum ord_tran_sum) {
        this.ord_tran_sum = ord_tran_sum;
    }
    
    public CustomerHeader getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(final CustomerHeader customer) {
        this.customer = customer;
    }
    
    public String getCust_seg() {
        return this.cust_seg;
    }
    
    public void setCust_seg(final String cust_seg) {
        this.cust_seg = cust_seg;
    }
    
    public SessionMap<String, Object> getSessionMap() {
        return this.sessionMap;
    }
    
    public void setSessionMap(final SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
    
    public String getDelvOrderkey() {
        return this.delvOrderkey;
    }
    
    public void setDelvOrderkey(final String delvOrderkey) {
        this.delvOrderkey = delvOrderkey;
    }
    
    public BigDecimal getDeliveredQuantity() {
        return this.deliveredQuantity;
    }
    
    public void setDeliveredQuantity(final BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }
    
    public boolean isShowDeliveredQuantity() {
        return this.showDeliveredQuantity;
    }
    
    public void setShowDeliveredQuantity(final boolean showDeliveredQuantity) {
        this.showDeliveredQuantity = showDeliveredQuantity;
    }
    
    public String setDeliveredOrderKey() {
        this.employee = super.getEmployee();
        List delvOrderSearchDetails = new ArrayList();
        try {
            final OrderTransactionsIfc trans = DKartContext.getLookupOrder();
            final OrderDAOBeanRemote orderDao = DKartContext.getOrderDAOBean();
            delvOrderSearchDetails = orderDao.setDeliveredOrderDetails(this.delvOrderkey);
            if (delvOrderSearchDetails != null) {
                if (delvOrderSearchDetails.get(0) != null) {
                    this.setOrder((OrderDetailsWithInvoice) delvOrderSearchDetails.get(0));
                }
                if (delvOrderSearchDetails.get(1) != null) {
                    this.setOrd_tran_header((OrderTranHeader)delvOrderSearchDetails.get(1));
                    this.setOrder_items(this.getOrd_tran_header().getOrdTranLineItems());
                    this.setOrd_tran_sum(this.getOrd_tran_header().getOrdTranSum());
                }
                if (delvOrderSearchDetails.get(2) != null) {
                    this.customer = (CustomerHeader) delvOrderSearchDetails.get(2);
                }
                System.out.println(delvOrderSearchDetails.size());
            }
            final List<DkartReasonCodes> getDisResnCodesList = (List<DkartReasonCodes>)trans.getAllDisRsnCode();
            for (final DkartReasonCodes getDisResnCodes : getDisResnCodesList) {
                final String resnCodeDes = getDisResnCodes.getRsnDesc();
                final long longrsncode = getDisResnCodes.getRsnCode();
                final String resnCode = Long.toString(longrsncode);
                this.getAllDiscountReasnCode.put(resnCode, resnCodeDes);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    public boolean isOrderCancelAvailable() {
        return this.orderCancelAvailable;
    }
    
    public void setOrderCancelAvailable(final boolean orderCancelAvailable) {
        this.orderCancelAvailable = orderCancelAvailable;
    }
    
    public String getOrd_timestamp() {
        return this.ord_timestamp;
    }
    
    public void setOrd_timestamp(final String ord_timestamp) {
        this.ord_timestamp = ord_timestamp;
    }
}