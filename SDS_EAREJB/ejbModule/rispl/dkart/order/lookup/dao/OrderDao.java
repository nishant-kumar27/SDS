package rispl.dkart.order.lookup.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.test.entities.OrderDetail;
import com.test.entities.OrderDetailsWithInvoice;
import com.test.entities.OrderTimestamp;
import com.test.entities.SdsOrdersDashboard;
import com.test.entities.SdsOrdersDashboardPK;

import rispl.db.model.customer.RisplDkCustSeg;
import rispl.db.model.item.RisplDkItemMstr;
import rispl.dk.Employee.EmpMerchAssociationIfc;
import rispl.dk.Employee.EmployeeIfc;
import rispl.dkart.services.entities.customer.CustomerHeader;
import rispl.dkart.services.entities.transaction.ExciseTaxItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkservices.common.OrderTransactionSearchCriteriaIfc;
import weblogic.management.configuration.ServerTemplateMBean;

public class OrderDao {

	private static final Logger LOGGER = LogManager.getLogger(OrderDao.class);
	public static SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yy");

	List<OrderDetail> orders;
	List<SdsOrdersDashboard> orders_count;
	List<OrderDetailsWithInvoice> orders_with_invoice;
	EntityManager entitymanager;
	EntityManagerFactory entitymanagerfact;
	RisplDkCustSeg rispl_cust_seg;

	public OrderDao() {

	}

	public OrderDao(EntityManager em) {
		this.entitymanager = em;
	}
	public OrderDao(EntityManagerFactory em) {
		this.entitymanagerfact = em;
	}

	// Pulling out order data
		public List<OrderDetail> getOrdersByEmpId(String empId) {

			Query query = entitymanagerfact.createEntityManager()
					.createQuery("SELECT ord FROM OrderDetail ord WHERE ord.empId=:empid ORDER BY ord.id.orderDate DESC");
			query.setParameter("empid", empId);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders = query.getResultList();
			for (OrderDetail temp : orders) {
				if (temp.getStatus().equals("Unknown")) {
					orders.remove(temp);
				}
			}
			return orders;
		}

		public List<OrderDetail> getOrdersByDivision(int division) {

			Query query = entitymanagerfact.createEntityManager()
					.createQuery("SELECT ord FROM OrderDetail ord WHERE ord.divisionId=:id ORDER BY ord.id.orderDate DESC");
			query.setParameter("id", division);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders = query.getResultList();
			for (OrderDetail temp : orders) {
				if (temp.getStatus().equals("Unknown")) {
					orders.remove(temp);
				}
			}
			return orders;
		}

		public List<OrderDetailsWithInvoice> getOrdersByOrderTotals(EmployeeIfc employee, String from, String to) {
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			if (from.equals("") && to.equals(""))
				return null;
			else if (to.equals("")) {
				BigDecimal parsed_from = new BigDecimal(from);
				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_ORDTOT1");
					query.setParameter("divIds", divIds);
					query.setParameter("from", parsed_from);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDTOT1");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("from", parsed_from);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
						query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDTOT1");
						query.setParameter("empid", "%");
						query.setParameter("from", parsed_from);
						query.setHint(QueryHints.REFRESH, HintValues.TRUE);
					}

			} else {
				BigDecimal parsed_to = new BigDecimal(to);
				BigDecimal parsed_from = BigDecimal.ZERO;
				if (!from.equals(""))
					parsed_from = new BigDecimal(from);

				// Build Employee Role based Query
			/*	if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_ORDTOT2");
					query.setParameter("divIds", divIds);
					query.setParameter("from", parsed_from);
					query.setParameter("to", parsed_to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDTOT2");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("from", parsed_from);
					query.setParameter("to", parsed_to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
						query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDTOT2");
						query.setParameter("empid", "%");
						query.setParameter("from", parsed_from);
						query.setParameter("to", parsed_to);
						query.setHint(QueryHints.REFRESH, HintValues.TRUE);
					}
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public RisplDkCustSeg getRisplDkCustSegByGroupId(BigDecimal grpId) {
			String querystr = "SELECT seg FROM RisplDkCustSeg seg WHERE seg.id.prcngGrpId=:grpid";
			Query query = entitymanagerfact.createEntityManager().createQuery(querystr);
			query.setParameter("grpid", grpId);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			rispl_cust_seg = (RisplDkCustSeg) query.getSingleResult();
			System.out.println("Cust seg:" + rispl_cust_seg);
			return rispl_cust_seg;
		}

		public List<OrderDetailsWithInvoice> getOrdersByInvoiceTotals(EmployeeIfc employee, String from, String to) {
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			if (from.equals("") && to.equals(""))
				return null;
			else if (to.equals("")) {
				BigDecimal parsed_from = new BigDecimal(from);

				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_INVTOT1");
					query.setParameter("divIds", divIds);
					query.setParameter("from", parsed_from);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVTOT1");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("from", parsed_from);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
		query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVTOT1");
		query.setParameter("empid", "%");
		query.setParameter("from", parsed_from);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				}

			} else {
				BigDecimal parsed_to = new BigDecimal(to);
				BigDecimal parsed_from = BigDecimal.ZERO;
				if (!from.equals(""))
					parsed_from = new BigDecimal(from);

				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_INVTOT2");
					query.setParameter("divIds", divIds);
					query.setParameter("from", parsed_from);
					query.setParameter("to", parsed_to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVTOT2");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("from", parsed_from);
					query.setParameter("to", parsed_to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
					 query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVTOT2");
						query.setParameter("empid","%");
						query.setParameter("from", parsed_from);
						query.setParameter("to", parsed_to);
						query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				}
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetailsWithInvoice> getOrdersByOrderDate(EmployeeIfc employee, Date from, Date to) {
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			if (from == null && to == null)
				return null;
			else if (from == null) {
				to = parsingDate(to);

				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_ORDDT1");
					query.setParameter("divIds", divIds);
					query.setParameter("to", to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDDT1");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("to", to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
		query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDDT1");
		query.setParameter("empid", "%");
		query.setParameter("to", to);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				}

			} else {
				if (to == null)
					to = new Date();
				from = parsingDate(from);
				to = parsingDate(to);

				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_ORDDT2");
					query.setParameter("divIds", divIds);
					query.setParameter("from", from);
					query.setParameter("to", to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDDT2");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("from", from);
					query.setParameter("to", to);
					query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
				query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDDT2");
				query.setParameter("empid", "%");
				query.setParameter("from", from);
				query.setParameter("to", to);
				query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				}

			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetailsWithInvoice> getOrdersByInvoiceDate(EmployeeIfc employee, Date from, Date to) {
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			if (from == null && to == null)
				return null;
			else if (from == null) {
				to = parsingDate(to);

				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_INVDT1");
					query.setParameter("divIds", divIds);
					query.setParameter("to", to);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVDT1");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("to", to);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				  else if (employee.getRoleAccess().equalsIgnoreCase("All")){
		query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVDT1");
		query.setParameter("empid", "%");
		query.setParameter("to", to);
				}
			} else {
				if (to == null)
					to = new Date();
				from = parsingDate(from);
				to = parsingDate(to);

				// Build Employee Role based Query
				/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
				if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_INVDT2");
					query.setParameter("divIds", divIds);
					query.setParameter("to", to);
					query.setParameter("from", from);
				} /*else if (roleNameIds.get("SalesAgent") != null
						&& roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
				 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVDT2");
					query.setParameter("empid", employee.getEmployeeId());
					query.setParameter("to", to);
					query.setParameter("from", from);
				} /*else if (roleNameIds.get("DivisionHead") != null
						&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
				  else if (employee.getRoleAccess().equalsIgnoreCase("All")){
				query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVDT2");
				query.setParameter("empid", "%");
				query.setParameter("to", to);
				query.setParameter("from", from);
				}
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetailsWithInvoice> getOrdersByOrderId(EmployeeIfc employee, String orderId) {
			if (orderId.equals(""))
				return null;
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			// Build Employee Role based Query
			/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_ORDID");
				query.setParameter("orderid", orderId);
				query.setParameter("divIds", divIds);
				query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			} /*else if (roleNameIds.get("SalesAgent") != null && roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
			 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDID");
				query.setParameter("orderid", orderId);
				query.setParameter("empid", employee.getEmployeeId());
				query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			} /*else if (roleNameIds.get("DivisionHead") != null
					&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
			 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ORDID");
query.setParameter("orderid", orderId);
query.setParameter("empid", "%");
query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			}
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetailsWithInvoice> getOrdersByInvoiceId(EmployeeIfc employee, String invoiceId) {
			if (invoiceId.equals(""))
				return null;
			Long parsed_id = Long.parseLong(invoiceId);
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			// Build Employee Role based Query
		/*	if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_INVID");
				query.setParameter("invoiceid", invoiceId);
				query.setParameter("divIds", divIds);
			} /*else if (roleNameIds.get("SalesAgent") != null && roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
			else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVID");
				query.setParameter("invoiceid", invoiceId);
				query.setParameter("empid", employee.getEmployeeId());
			} /*else if (roleNameIds.get("DivisionHead") != null
					&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
			 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_INVID");
query.setParameter("invoiceid", invoiceId);
query.setParameter("empid", "%");
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetailsWithInvoice> getOrdersByCustomer(EmployeeIfc employee, String customerData) {
			if (customerData.equals(""))
				return null;
			customerData = customerData.trim();
			customerData = customerData.toUpperCase();
			String[] splitData = customerData.split(" ");
			String parsed_data = "%";
			for (String str : splitData) {
				parsed_data += str + "%";
			}
			Query query = null;

			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			// Build Employee Role based Query
		/*	if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_CUST");
				query.setParameter("customer", parsed_data);
				query.setParameter("divIds", divIds);
			} /*else if (roleNameIds.get("SalesAgent") != null && roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
			  else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_CUST");
				query.setParameter("customer", parsed_data);
				query.setParameter("empid", employee.getEmployeeId());
			} /*else if (roleNameIds.get("DivisionHead") != null
					&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
			  else if (employee.getRoleAccess().equalsIgnoreCase("All")){
query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_CUST");
query.setParameter("customer", parsed_data);
query.setParameter("empid", "%");
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetailsWithInvoice> getOrdersByItem(EmployeeIfc employee, String itemData) {
			if (itemData.equals(""))
				return null;
			itemData = itemData.trim();
			itemData = itemData.toUpperCase();
			String[] splitData = itemData.split("---");
			int lastElement = splitData.length - 1;
			String parsed_data = "%";
			parsed_data = splitData[lastElement];
			Query query = null;
			List<Integer> divIds = getEmpMrchAssc(employee);
			String empRoleId = String.valueOf(employee.getRoleId());
			
			String itmid=getItemId(parsed_data); // calling a method to get item id
			if(itmid!=null){
				parsed_data=itmid;
				parsed_data=parsed_data.trim();
			}
			
			// Build Employee Role based Query
			/*if (roleNameIds.get("DataEntry") != null && roleNameIds.get("DataEntry").equalsIgnoreCase(empRoleId)) {*/
			if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("DIVHD_ORD_SRCH_ITMINFO");
				query.setParameter("itemid", parsed_data);
				query.setParameter("divIds", divIds);
			} /*else if (roleNameIds.get("SalesAgent") != null && roleNameIds.get("SalesAgent").equalsIgnoreCase(empRoleId)) {*/
			 else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
				query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ITMINFO");
				query.setParameter("itemid", parsed_data);
				query.setParameter("empid", employee.getEmployeeId());
			} /*else if (roleNameIds.get("DivisionHead") != null
					&& roleNameIds.get("DivisionHead").equalsIgnoreCase(empRoleId)) {*/
			 else if (employee.getRoleAccess().equalsIgnoreCase("All")){
				 query = entitymanagerfact.createEntityManager().createNamedQuery("SLSAGNT_ORD_SRCH_ITMINFO");
					query.setParameter("itemid", parsed_data);
					query.setParameter("empid", "%");
			}
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders_with_invoice = query.getResultList();
			return orders_with_invoice;
		}

		public List<OrderDetail> getOrdersByEmpId(String empId, String range, String status) {
			Date current_date = new Date();
			Date from_date = getDateFromRange(range);
			String querystr = "SELECT ord FROM OrderDetail ord WHERE ord.empId=:id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			Query query = entitymanagerfact.createEntityManager().createQuery(querystr);

			switch (status) {
			case OrderStatus.NEW: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.empId=:id AND ord.scOrd=0  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.OPEN: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.empId=:id AND ord.scOrd =1  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.IN_PROGRESS: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE (ord.ordTy=23 OR ord.ordTy=26) AND ord.empId=:id AND (ord.scOrd BETWEEN 2 AND 3)  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.COMPELETED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.empId=:id AND (ord.scOrd BETWEEN 4 AND 5)  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.DELIVERED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.empId=:id  AND (ord.scOrd BETWEEN 6 AND 7) AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.QUOTE: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.empId=:id AND ord.scOrd=0  AND ord.scTran=4 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.CANCELLED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.empId=:id AND ord.ordTy=25 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.RETURNED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE  ord.empId=:id AND ord.ordTy=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			default:
				break;
			}
			query.setParameter("from", from_date);
			query.setParameter("to", current_date);
			query.setParameter("id", empId);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders = query.getResultList();
			return orders;
		}

		/*
		 * public List<OrderDetail> getOrdersByDivision(int division, String range,
		 * String status) {
		 */
		public List<OrderDetail> getOrdersByDivision(List<Integer> division, String range, String status) {
			Date current_date = new Date();
			Date from_date = getDateFromRange(range);
			/*
			 * String querystr =
			 * "SELECT ord FROM OrderDetail ord WHERE ord.divisionId=:id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC"
			 * ;
			 */
			String querystr = "SELECT ord FROM OrderDetail ord WHERE ord.divisionId IN :id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			Query query = entitymanagerfact.createEntityManager().createQuery(querystr);

			switch (status) {
			case OrderStatus.NEW: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.divisionId IN :id AND ord.scOrd=0  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.OPEN: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.divisionId IN :id AND ord.scOrd =1  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.IN_PROGRESS: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE (ord.ordTy=23 OR ord.ordTy=26) AND ord.divisionId IN :id AND (ord.scOrd BETWEEN 2 AND 3)  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.COMPELETED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.divisionId IN :id AND (ord.scOrd BETWEEN 4 AND 5)  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.DELIVERED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.divisionId IN :id  AND (ord.scOrd BETWEEN 6 AND 7) AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.QUOTE: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.divisionId IN :id AND ord.scOrd=0  AND ord.scTran=4 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.CANCELLED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE ord.divisionId IN :id AND ord.ordTy=25 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			case OrderStatus.RETURNED: // $NON-NLS-1$
				querystr = "SELECT ord FROM OrderDetail ord WHERE  ord.divisionId IN :id AND ord.ordTy=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
				query = entitymanagerfact.createEntityManager().createQuery(querystr);
				break;
			default:
				break;
			}
			query.setParameter("from", from_date);
			query.setParameter("to", current_date);
			query.setParameter("id", division);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders = query.getResultList();
			return orders;
		}

		public List<OrderDetail> getOrdersByEmpId(String empId, String range) {
			Date current_date = new Date();
			Date from_date = getDateFromRange(range);
			String querystr = "SELECT ord FROM OrderDetail ord WHERE ord.empId=:id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			Query query = entitymanagerfact.createEntityManager().createQuery(querystr, OrderDetail.class);
			query.setParameter("from", from_date);
			query.setParameter("to", current_date);
			query.setParameter("id", empId);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders = query.getResultList();
			return orders;
		}
		// Added by jagadish to Get Item Id using Bar code
		public String getItemId(String parsed_data)
		{
			String result=null;
				try {
					String invitemid=parsed_data.toUpperCase().trim();
					String getItemId = "SELECT rdim FROM RisplDkItemMstr rdim where rdim.id.idItmPos=:invitemid";
					Query query = entitymanagerfact.createEntityManager().createQuery(getItemId, RisplDkItemMstr.class).setParameter("invitemid", invitemid);
					List<RisplDkItemMstr> RisplDkItemMstrList=query.getResultList();
						for(RisplDkItemMstr RisplDkItemMstr : RisplDkItemMstrList){
							result=RisplDkItemMstr.getId().getItmId();
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
			return result;
		}
		
		/*
		 * public List<OrderDetail> getOrdersByDivision(int division, String range)
		 * {
		 */
		public List<OrderDetail> getOrdersByDivision(List<Integer> division, String range) {
			Date current_date = new Date();
			Date from_date = getDateFromRange(range);
			/*
			 * String querystr =
			 * "SELECT ord FROM OrderDetail ord WHERE ord.divisionId=:id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC"
			 * ;
			 */
			String querystr = "SELECT ord FROM OrderDetail ord WHERE ord.divisionId IN :id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			Query query = entitymanagerfact.createEntityManager().createQuery(querystr, OrderDetail.class);
			query.setParameter("from", from_date);
			query.setParameter("to", current_date);
			query.setParameter("id", division);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			orders = query.getResultList();
			return orders;
		}

		// Order Count for pie chart in dashboard-Employee Level
		public List<SdsOrdersDashboard> getOrdersCountByEmpId(String empId, String range) {
			Date current_date = new Date();
			Date from_date = getDateFromRange(range);
			try{
				String querystr = "SELECT sum(ord.deliveredOrders),sum(ord.inProgress),sum(ord.cancelledOrders),sum(ord.newOrders),sum(ord.openOrders),sum(ord.pendingOrders),sum(ord.returnedOrders),sum(ord.completed) FROM SdsOrdersDashboard ord WHERE ord.id.empId=:id AND ord.id.ordDate BETWEEN :from AND :to ORDER BY ord.id.ordDate DESC";
				Query query = entitymanagerfact.createEntityManager().createQuery(querystr,Vector.class);
				query.setParameter("from", from_date,TemporalType.DATE);
				query.setParameter("to", current_date,TemporalType.DATE);
				query.setParameter("id", empId);
				query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				
				Vector result = (Vector) query.getResultList();
				SdsOrdersDashboard aggregate = new SdsOrdersDashboard();
				SdsOrdersDashboardPK pk = new SdsOrdersDashboardPK();
				Object innrResult[] = (Object[])result.get(0);
				BigDecimal delivered = (BigDecimal) innrResult[0];
				BigDecimal Inprogress=(BigDecimal) innrResult[1];
				BigDecimal Cancelled=(BigDecimal) innrResult[2];
				BigDecimal Neworders=(BigDecimal) innrResult[3];
				BigDecimal OpenOrders=(BigDecimal) innrResult[4];
				BigDecimal PendingOrders=(BigDecimal) innrResult[5];
				BigDecimal ReturnOrders=(BigDecimal) innrResult[6];
				BigDecimal Completed=(BigDecimal) innrResult[7];
				aggregate.setDeliveredOrders(delivered);
				aggregate.setInProgress(Inprogress);
				aggregate.setCancelledOrders(Cancelled);
				aggregate.setNewOrders(Neworders);
				aggregate.setOpenOrders(OpenOrders);
				aggregate.setPendingOrders(PendingOrders);
				aggregate.setReturnedOrders(ReturnOrders);
				aggregate.setCompleted(Completed);	
				pk.setEmpId(empId);
				aggregate.setId(pk);
				orders_count=result;
				orders_count.clear();
				orders_count.add(aggregate);
				}catch(Exception e){
					e.printStackTrace();
				}
				return orders_count;
			}

		private String convertListValuesToString(List<Integer> divList) {
			String value = "";
			if (divList != null && !divList.isEmpty()) {
				for (Integer wrap : divList) {
					value = value + wrap + ",";
				}
				if (value != null) {
					value = value.substring(0, value.length() - 1);
				}
			}

			return value;
		}

		// Order Count for pie chart in dashboard-Division Level
		/*
		 * 
		 * changed by mudassir
		 */
		/*
		 * public List<SdsOrdersDashboard> getOrdersCountByDivision(int division,
		 * String range) {
		 */
		public List<SdsOrdersDashboard> getOrdersCountByDivision(List<Integer> division, String range) {

			Date current_date = new Date();
			Date from_date = getDateFromRange(range);
			String div = division.toString(); 
			char a=div.charAt(1);
			String finaldiv=String.valueOf(a);
			BigDecimal divisionid=new BigDecimal(finaldiv);
			try{
				String querystr = "SELECT sum(ord.deliveredOrders),sum(ord.inProgress),sum(ord.cancelledOrders),sum(ord.newOrders),sum(ord.openOrders),sum(ord.pendingOrders),sum(ord.returnedOrders),sum(ord.completed) FROM SdsOrdersDashboard ord WHERE ord.divisionId=:id AND ord.id.ordDate BETWEEN :from AND :to ORDER BY ord.id.ordDate DESC ";
				Query query = entitymanagerfact.createEntityManager().createQuery(querystr,Vector.class);
				query.setParameter("from", from_date,TemporalType.DATE);
				query.setParameter("to", current_date,TemporalType.DATE);
				query.setParameter("id", divisionid);
				query.setHint(QueryHints.REFRESH, HintValues.TRUE);
				
				Vector result = (Vector) query.getResultList();
				SdsOrdersDashboard aggregate = new SdsOrdersDashboard();
				Object innrResult[] = (Object[])result.get(0);
				BigDecimal delivered = (BigDecimal) innrResult[0];
				BigDecimal Inprogress=(BigDecimal) innrResult[1];
				BigDecimal Cancelled=(BigDecimal) innrResult[2];
				BigDecimal Neworders=(BigDecimal) innrResult[3];
				BigDecimal OpenOrders=(BigDecimal) innrResult[4];
				BigDecimal PendingOrders=(BigDecimal) innrResult[5];
				BigDecimal ReturnOrders=(BigDecimal) innrResult[6];
				BigDecimal Completed=(BigDecimal) innrResult[7];
				aggregate.setDeliveredOrders(delivered);
				aggregate.setInProgress(Inprogress);
				aggregate.setCancelledOrders(Cancelled);
				aggregate.setNewOrders(Neworders);
				aggregate.setOpenOrders(OpenOrders);
				aggregate.setPendingOrders(PendingOrders);
				aggregate.setReturnedOrders(ReturnOrders);
				aggregate.setCompleted(Completed);
				aggregate.setDivisionId(divisionid);
				orders_count=result;
				orders_count.clear();
				orders_count.add(aggregate);
				}catch(Exception e){
					e.printStackTrace();
				}
				return orders_count;
		}

		public List<SdsOrdersDashboard> getOrdersCountAggregate(List<SdsOrdersDashboard> orders_count) {
			SdsOrdersDashboard aggregate = new SdsOrdersDashboard();
			aggregate.setCancelledOrders(new BigDecimal(0));
			aggregate.setDeliveredOrders(new BigDecimal(0));
			aggregate.setNewOrders(new BigDecimal(0));
			aggregate.setOpenOrders(new BigDecimal(0));
			aggregate.setPendingOrders(new BigDecimal(0));
			aggregate.setReturnedOrders(new BigDecimal(0));
			aggregate.setInProgress(new BigDecimal(0));
			aggregate.setCompleted(new BigDecimal(0));

			for (SdsOrdersDashboard temp : orders_count) {
				aggregate.setCancelledOrders(aggregate.getCancelledOrders().add(temp.getCancelledOrders()));
				aggregate.setDeliveredOrders(aggregate.getDeliveredOrders().add(temp.getDeliveredOrders()));
				aggregate.setNewOrders(aggregate.getNewOrders().add(temp.getNewOrders()));
				aggregate.setOpenOrders(aggregate.getOpenOrders().add(temp.getOpenOrders()));
				aggregate.setPendingOrders(aggregate.getPendingOrders().add(temp.getPendingOrders()));
				aggregate.setReturnedOrders(aggregate.getReturnedOrders().add(temp.getReturnedOrders()));
				aggregate.setInProgress(aggregate.getInProgress().add(temp.getInProgress()));
				aggregate.setCompleted(aggregate.getCompleted().add(temp.getCompleted()));
			}
			orders_count.clear();
			orders_count.add(aggregate);
			return orders_count;
		}

		public Date getDateFromRange(String range) {
			Date from_date = new Date();
			Calendar c = Calendar.getInstance();
	        c.set(Calendar.HOUR_OF_DAY, 0);  
	        c.set(Calendar.MINUTE, 0);  
	        c.set(Calendar.SECOND, 0);  
	        c.set(Calendar.MILLISECOND, 0);  
			switch (range) {
			case "week":
				c.set(Calendar.DAY_OF_WEEK, 1);
				from_date = c.getTime();
				break;
			case "month":
				c.set(Calendar.DAY_OF_MONTH, 1);
				from_date = c.getTime();
				break;
			case "quarter":
				//c.add(Calendar.MONTH, -2);
				//c.set(Calendar.DAY_OF_MONTH, 1);
				from_date = getFirstDayOfQuarter(from_date);
				break;
			case "year":
				c.set(Calendar.DAY_OF_YEAR, 1);
				from_date = c.getTime();
				break;
			case "NONE":
				from_date = FromDate(range);
				break;
			default:
				break;
			}
			return from_date;
		}

		
		public Date FromDate(String range){
		Date from_date = null;
		entitymanager=entitymanagerfact.createEntityManager();
		if(range.equals("NONE"))
		{
			try{
				Date fromDateDBString = (Date) entitymanager.createQuery("SELECT MIN(SOD.id.ordDate) FROM SdsOrdersDashboard SOD").getSingleResult();
				from_date = fromDateDBString;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return from_date;
		}
		
		
		private static Date getFirstDayOfQuarter(Date date) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.set(Calendar.HOUR_OF_DAY, 0);  
	        cal.set(Calendar.MINUTE, 0);  
	        cal.set(Calendar.SECOND, 0);  
	        cal.set(Calendar.MILLISECOND, 0);  
		    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
		    cal.set(Calendar.DAY_OF_MONTH, 1);
		    return cal.getTime();
		}
		
		public static Date parsingDate(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			date = cal.getTime();
			return date;
		}

		public List<Integer> getEmpMrchAssc(EmployeeIfc employee) {
			List<EmpMerchAssociationIfc> empMrchAss = employee.getMerchAssoc();
			List<Integer> divIds = new ArrayList<Integer>();
			if (empMrchAss != null) {
				for (EmpMerchAssociationIfc empMrchAsc : empMrchAss) {
					if (empMrchAsc.getMerchId().startsWith("1:")) {
						int merchId = Integer.parseInt(empMrchAsc.getMerchId().split("1:")[1]);
						divIds.add(merchId);
					}
				}
			}
			return divIds;
		}

		/*
		
		 * delivered order search 
		
		 */

		public DelvOrderSearchVO deliveredOrderSearchByTab1(OrderTransactionSearchCriteriaIfc searchCreteria, EmployeeIfc employee) throws Exception {
			
			DelvOrderSearchVO searchResult=null;
			List<Integer> divisions = new ArrayList<Integer>();
			try {
				if (employee != null) {
					if (employee.getRoleAccess().equalsIgnoreCase("Within Division")) {
						divisions = getEmpMrchAssc(employee);
						if (searchCreteria.isSearchByinvoiceNumberOrOrderNumber()) {
							searchResult=	deliveredOrderSearForOrdersTab(searchCreteria, divisions, null);

						}

						else if (searchCreteria.deliveredOrderSearchWithItem() || searchCreteria.isSearchByCustomerInfo()) {
							searchResult=deliveredOrderSearForCustomerAndItemTab(searchCreteria, divisions, null);
						}
					} 
					else if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
						if (searchCreteria.isSearchByinvoiceNumberOrOrderNumber()) {
							searchResult=deliveredOrderSearForOrdersTab(searchCreteria, null, employee.getEmployeeId());
						} else if (searchCreteria.deliveredOrderSearchWithItem()
								|| searchCreteria.isSearchByCustomerInfo()) {
							searchResult=	deliveredOrderSearForCustomerAndItemTab(searchCreteria, null, employee.getEmployeeId());
						}
					}
					else if (employee.getRoleAccess().equalsIgnoreCase("All")){

						if (searchCreteria.isSearchByinvoiceNumberOrOrderNumber()) {
							searchResult=deliveredOrderSearForOrdersTab(searchCreteria, null, null);
						} else if (searchCreteria.deliveredOrderSearchWithItem()
								|| searchCreteria.isSearchByCustomerInfo()) {
							searchResult=	deliveredOrderSearForCustomerAndItemTab(searchCreteria, null, null);
						}
					
					}
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return searchResult;
		}

		private Date stringToDate(String date) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			Date returnDate = null;
			try {
				return returnDate = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnDate;
		}
		
		private Date stringDBToDate(String date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date returnDate = null;
			try {
				return returnDate = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnDate;
		}
		
		private String stringFromToStringDB(String date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date datefrom = null;
			String StringDBDate = null;
			try {
				datefrom = new SimpleDateFormat("dd-MMM-yyyy").parse(date);
				return StringDBDate=format.format(datefrom);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return StringDBDate;
		}
		
		// for tab2 customer and item_id details
		private DelvOrderSearchVO deliveredOrderSearForCustomerAndItemTab(OrderTransactionSearchCriteriaIfc searchCreteria,
				List<Integer> divisionList, String emp_id) {
			List<OrderDetailsWithInvoice> delOrdSearchForItem = null;
			List<OrderDetailsWithInvoice> delOrdSearchForCust = null;
			Map<Integer, List<OrderDetailsWithInvoice>> combinedList = new HashMap<>();
			DelvOrderSearchVO searhResultList = new DelvOrderSearchVO();
			try {
				
				if(searchCreteria.getItemIdOrDescription()!= null && !searchCreteria.getItemIdOrDescription().equalsIgnoreCase("")){
					String parsed_data=searchCreteria.getItemIdOrDescription();
					String itmid=getItemId(parsed_data); // calling a method to get item id using bar code Added by jagadish
						if(itmid!=null){
							itmid=itmid.trim();
							searchCreteria.setItemIdOrDescription(itmid);
						}
					}
				// for dataentry and division head
				if (divisionList != null) {
					if (searchCreteria.deliveredOrderSearchWithItem() && searchCreteria.getItemIdOrDescription() != null) {

						Query itemSearchForDeliveredOrder = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_ITEM_DIV_HEAD",
								OrderDetailsWithInvoice.class);

						itemSearchForDeliveredOrder.setParameter("ord_type", "24");
						//itemSearchForDeliveredOrder.setParameter("ord_type1", "26");
						itemSearchForDeliveredOrder.setParameter("sc_ord", 5);
						//itemSearchForDeliveredOrder.setParameter("sc_ord1", 4);
						itemSearchForDeliveredOrder.setParameter("sc_tran", 2);
						//itemSearchForDeliveredOrder.setParameter("sc_tran1", 2);
						itemSearchForDeliveredOrder.setParameter("itemid", searchCreteria.getItemIdOrDescription());
						if (searchCreteria.getItemIdOrDescription() != null
								&& searchCreteria.getItemIdOrDescription().equalsIgnoreCase("%")) {
							itemSearchForDeliveredOrder.setParameter("itemiddes", "%%");
						} else {
							itemSearchForDeliveredOrder.setParameter("itemiddes",
									"%" + searchCreteria.getItemIdOrDescription() + "%");
						}
						itemSearchForDeliveredOrder.setParameter("division_list", divisionList);
						//itemSearchForDeliveredOrder.setParameter("division_list1", divisionList);
						delOrdSearchForItem = itemSearchForDeliveredOrder.getResultList();

						if (delOrdSearchForItem != null) {
							combinedList.put(1, delOrdSearchForItem);
						}

					}
					if (delOrdSearchForItem == null) {
						combinedList.put(1, null);
					}
					if (searchCreteria.isSearchByCustomerInfo() && searchCreteria.getDelvCustIdorDesc() != null) {

						Query custIDSearchForDeliveredOrder = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_CUST_DIV_HEAD",
								OrderDetailsWithInvoice.class);

						custIDSearchForDeliveredOrder.setParameter("ord_type", "24");
						//custIDSearchForDeliveredOrder.setParameter("ord_type1", "26");
						custIDSearchForDeliveredOrder.setParameter("sc_ord", 5);
						//custIDSearchForDeliveredOrder.setParameter("sc_ord1", 4);
						custIDSearchForDeliveredOrder.setParameter("sc_tran", 2);
					    //custIDSearchForDeliveredOrder.setParameter("sc_tran1", 2);
						if (searchCreteria.getDelvCustIdorDesc() != null
								&& searchCreteria.getDelvCustIdorDesc().equalsIgnoreCase("%")) {
							custIDSearchForDeliveredOrder.setParameter("customerdes", "%%");

						} else {
							custIDSearchForDeliveredOrder.setParameter("customerdes",
									"%" + searchCreteria.getDelvCustIdorDesc() + "%");
						}
						custIDSearchForDeliveredOrder.setParameter("customer", searchCreteria.getDelvCustIdorDesc());

						custIDSearchForDeliveredOrder.setParameter("division_list", divisionList);
						//custIDSearchForDeliveredOrder.setParameter("division_list1", divisionList);
						delOrdSearchForCust = custIDSearchForDeliveredOrder.getResultList();

						if (delOrdSearchForCust != null) {
							combinedList.put(2, delOrdSearchForCust);
						}

					}
					if (delOrdSearchForCust == null) {
						combinedList.put(2, null);
					}
					searhResultList.setOrderList(formatListFortab2(combinedList));
					searhResultList.setSearCreteria(searchCreteria);
				}
				// for others
				else {

					if (searchCreteria.deliveredOrderSearchWithItem() && searchCreteria.getItemIdOrDescription() != null) {

						Query itemSearchForDeliveredOrder = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_ITEM_EMP",
								OrderDetailsWithInvoice.class);

						itemSearchForDeliveredOrder.setParameter("ord_type", "24");
						//itemSearchForDeliveredOrder.setParameter("ord_type1", "26");
						itemSearchForDeliveredOrder.setParameter("sc_ord", 5);
						//itemSearchForDeliveredOrder.setParameter("sc_ord1", 4);
						itemSearchForDeliveredOrder.setParameter("sc_tran", 2);
						//itemSearchForDeliveredOrder.setParameter("sc_tran1", 2);
						itemSearchForDeliveredOrder.setParameter("itemid", searchCreteria.getItemIdOrDescription());
						if (searchCreteria.getItemIdOrDescription() != null
								&& searchCreteria.getItemIdOrDescription().equalsIgnoreCase("%")) {
							itemSearchForDeliveredOrder.setParameter("itemiddes", "%%");
						} else {
							itemSearchForDeliveredOrder.setParameter("itemiddes",
									"%" + searchCreteria.getItemIdOrDescription() + "%");
						}
						if(emp_id!=null &&  !emp_id.isEmpty()){
						itemSearchForDeliveredOrder.setParameter("employee_num", emp_id);
						//itemSearchForDeliveredOrder.setParameter("employee_num1", emp_id);
						}
						else{
							itemSearchForDeliveredOrder.setParameter("employee_num", "%");
							//itemSearchForDeliveredOrder.setParameter("employee_num1", "%");
						}
						delOrdSearchForItem = itemSearchForDeliveredOrder.getResultList();

						if (delOrdSearchForItem != null) {
							combinedList.put(1, delOrdSearchForItem);
						}

					}
					if (delOrdSearchForItem == null) {
						combinedList.put(1, null);
					}
					if (searchCreteria.isSearchByCustomerInfo() && searchCreteria.getDelvCustIdorDesc() != null) {

						Query custIDSearchForDeliveredOrder = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_CUST_EMP",
								OrderDetailsWithInvoice.class);

						custIDSearchForDeliveredOrder.setParameter("ord_type", "24");
						//custIDSearchForDeliveredOrder.setParameter("ord_type1", "26");
						custIDSearchForDeliveredOrder.setParameter("sc_ord", 5);
						//custIDSearchForDeliveredOrder.setParameter("sc_ord1", 4);
						custIDSearchForDeliveredOrder.setParameter("sc_tran", 2);
						//custIDSearchForDeliveredOrder.setParameter("sc_tran1", 2);
						if (searchCreteria.getDelvCustIdorDesc() != null
								&& searchCreteria.getDelvCustIdorDesc().equalsIgnoreCase("%")) {
							custIDSearchForDeliveredOrder.setParameter("customerdes", "%%");

						} else {
							custIDSearchForDeliveredOrder.setParameter("customerdes",
									"%" + searchCreteria.getDelvCustIdorDesc() + "%");
						}
						custIDSearchForDeliveredOrder.setParameter("customer", searchCreteria.getDelvCustIdorDesc());
						if(emp_id!=null &&  !emp_id.isEmpty()){
						custIDSearchForDeliveredOrder.setParameter("employee_num", emp_id);
						//custIDSearchForDeliveredOrder.setParameter("employee_num1", emp_id);
						}
						else{
							custIDSearchForDeliveredOrder.setParameter("employee_num", "%");
							//custIDSearchForDeliveredOrder.setParameter("employee_num1", "%");
						}
						delOrdSearchForCust = custIDSearchForDeliveredOrder.getResultList();

						if (delOrdSearchForCust != null) {
							combinedList.put(2, delOrdSearchForCust);
						}

					}
					if (delOrdSearchForCust == null) {
						combinedList.put(2, null);
					}
					searhResultList.setOrderList(formatListFortab2(combinedList));
					searhResultList.setSearCreteria(searchCreteria);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return searhResultList;
		}

		// for tab1 complete orders details
		private DelvOrderSearchVO deliveredOrderSearForOrdersTab(OrderTransactionSearchCriteriaIfc searchCreteria, List<Integer> divisionList,
				String emp_id) {
			List<OrderDetailsWithInvoice> orderListOfFromAndToDates = null;
			List<OrderDetailsWithInvoice> orderListOfFromDates = null;
			List<OrderDetailsWithInvoice> orderListOfToDates = null;
			List<OrderDetailsWithInvoice> orderListOfFromAndToAmounts = null;
			List<OrderDetailsWithInvoice> orderListOfFromNetTotals = null;
			List<OrderDetailsWithInvoice> orderListOfToNetTotals = null;
			List<OrderDetailsWithInvoice> orderListOfOrderIDSearch = null;
			Map<Integer, List<OrderDetailsWithInvoice>> combinedList = new HashMap<>();
			DelvOrderSearchVO searchResultList = new DelvOrderSearchVO();
			try {

				if (divisionList != null) {
					if (searchCreteria.getInvoiceNumberOrOrderNumber() != null
							&& !searchCreteria.getInvoiceNumberOrOrderNumber().isEmpty()) {

						// query for division head with order id
						Query orderSearchWithOrderId = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_ORD_ID_DIV_HEAD",
								OrderDetailsWithInvoice.class);

						orderSearchWithOrderId.setParameter("ord_type", "24");
						//orderSearchWithOrderId.setParameter("ord_type1", "26");
						orderSearchWithOrderId.setParameter("sc_ord", 5);
						//orderSearchWithOrderId.setParameter("sc_ord1", 4);
						orderSearchWithOrderId.setParameter("sc_tran", 2);
						//orderSearchWithOrderId.setParameter("sc_tran1", 2);
						orderSearchWithOrderId.setParameter("orderid", searchCreteria.getInvoiceNumberOrOrderNumber());
						//orderSearchWithOrderId.setParameter("orderid1", searchCreteria.getInvoiceNumberOrOrderNumber());
						orderSearchWithOrderId.setParameter("division_list", divisionList);
						//orderSearchWithOrderId.setParameter("division_list1", divisionList);
						orderListOfOrderIDSearch = orderSearchWithOrderId.getResultList();

						if (orderListOfOrderIDSearch != null) {
							combinedList.put(1, orderListOfOrderIDSearch);
						}
					}
					if (orderListOfOrderIDSearch == null) {
						combinedList.put(1, null);
					}

					if (searchCreteria.getOrderDateRangeTo() != null && searchCreteria.getOrderDateRangeFrom() != null
							&& !searchCreteria.getOrderDateRangeFrom().isEmpty()
							&& !searchCreteria.getOrderDateRangeTo().isEmpty()) {
						//// query for division head with from date and to date
						Query orderSearchWithBoth = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FROM_TO_DATE_DIV_HEAD",
								OrderDetailsWithInvoice.class);
						orderSearchWithBoth.setParameter("ord_type", "24");
						//orderSearchWithBoth.setParameter("ord_type1", "26");
						orderSearchWithBoth.setParameter("sc_ord", 5);
						//orderSearchWithBoth.setParameter("sc_ord1", 4);
						orderSearchWithBoth.setParameter("sc_tran", 2);
						//orderSearchWithBoth.setParameter("sc_tran1", 2);
						orderSearchWithBoth.setParameter("from", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						//orderSearchWithBoth.setParameter("from1", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						orderSearchWithBoth.setParameter("to", stringToDate(searchCreteria.getOrderDateRangeTo()));
						//orderSearchWithBoth.setParameter("to1", stringToDate(searchCreteria.getOrderDateRangeTo()));
						orderSearchWithBoth.setParameter("division_list", divisionList);
						//orderSearchWithBoth.setParameter("division_list1", divisionList);
						orderListOfFromAndToDates = orderSearchWithBoth.getResultList();

						if (orderListOfFromAndToDates != null) {
							combinedList.put(2, orderListOfFromAndToDates);
						}

					} else if (searchCreteria.getOrderDateRangeFrom() != null
							&& !searchCreteria.getOrderDateRangeFrom().isEmpty()) {
						// query for division head with from date
						Query orderSearchWithFrom = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FROM_DATE_DIV_HEAD",
								OrderDetailsWithInvoice.class);
						orderSearchWithFrom.setParameter("ord_type", "24");
						//orderSearchWithFrom.setParameter("ord_type1", "26");
						orderSearchWithFrom.setParameter("sc_ord", 5);
						//orderSearchWithFrom.setParameter("sc_ord1", 4);
						orderSearchWithFrom.setParameter("sc_tran", 2);
						//orderSearchWithFrom.setParameter("sc_tran1", 2);
						orderSearchWithFrom.setParameter("from", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						//orderSearchWithFrom.setParameter("from1", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						orderSearchWithFrom.setParameter("division_list", divisionList);
						//orderSearchWithFrom.setParameter("division_list1", divisionList);
						orderListOfFromDates = orderSearchWithFrom.getResultList();

						if (orderListOfFromDates != null) {
							combinedList.put(2, orderListOfFromDates);
						}
					} else if (searchCreteria.getOrderDateRangeTo() != null
							&& !searchCreteria.getOrderDateRangeTo().isEmpty()) {
						// query for division head with to date
						Query orderSearchWithTo = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_TO_DATE_DIV_HEAD",
								OrderDetailsWithInvoice.class);

						orderSearchWithTo.setParameter("ord_type", "24");
						//orderSearchWithTo.setParameter("ord_type1", "26");
						orderSearchWithTo.setParameter("sc_ord", 5);
						//orderSearchWithTo.setParameter("sc_ord1", 4);
						orderSearchWithTo.setParameter("sc_tran", 2);
						//orderSearchWithTo.setParameter("sc_tran1", 2);
						orderSearchWithTo.setParameter("to", stringToDate(searchCreteria.getOrderDateRangeTo()));
						//orderSearchWithTo.setParameter("to1", stringToDate(searchCreteria.getOrderDateRangeTo()));
						orderSearchWithTo.setParameter("division_list", divisionList);
						//orderSearchWithTo.setParameter("division_list1", divisionList);
						orderListOfToDates = orderSearchWithTo.getResultList();

						if (orderListOfToDates != null) {
							combinedList.put(2, orderListOfToDates);
						}

					}
					if (orderListOfToDates == null && orderListOfFromDates == null && orderListOfFromAndToDates == null) {
						combinedList.put(2, null);
					}
					if (searchCreteria.getOrderTotalFrom() != null && searchCreteria.getOrderTotalTo() != null
							&& !searchCreteria.getOrderTotalFrom().isEmpty()
							&& !searchCreteria.getOrderTotalTo().isEmpty()) {
						// query for division head with order from total and to
						// total
						Query orderSearchWithBothTotals = this.entitymanagerfact.createEntityManager()
								.createNamedQuery("DELV_ORD_FROM_TO_TOTAL_DIV_HEAD", OrderDetailsWithInvoice.class);
						orderSearchWithBothTotals.setParameter("ord_type", "24");
						//orderSearchWithBothTotals.setParameter("ord_type1", "26");
						orderSearchWithBothTotals.setParameter("sc_ord", 5);
						//orderSearchWithBothTotals.setParameter("sc_ord1", 4);
						orderSearchWithBothTotals.setParameter("sc_tran", 2);
						//orderSearchWithBothTotals.setParameter("sc_tran1", 2);
						orderSearchWithBothTotals.setParameter("from", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						//orderSearchWithBothTotals.setParameter("from1", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						orderSearchWithBothTotals.setParameter("to", new BigDecimal(searchCreteria.getOrderTotalTo()));
						//orderSearchWithBothTotals.setParameter("to1", new BigDecimal(searchCreteria.getOrderTotalTo()));
						orderSearchWithBothTotals.setParameter("division_list", divisionList);
						//orderSearchWithBothTotals.setParameter("division_list1", divisionList);
						orderListOfFromAndToAmounts = orderSearchWithBothTotals.getResultList();

						if (orderListOfFromAndToAmounts != null) {
							combinedList.put(3, orderListOfFromAndToAmounts);
						}

					} else if (searchCreteria.getOrderTotalFrom() != null
							&& !searchCreteria.getOrderTotalFrom().isEmpty()) {
						// query for division head with order from total
						Query orderSearchWithFromTotal = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FROM_TOTAL_DIV_HEAD",
								OrderDetailsWithInvoice.class);
						orderSearchWithFromTotal.setParameter("ord_type", "24");
						//orderSearchWithFromTotal.setParameter("ord_type1", "26");
						orderSearchWithFromTotal.setParameter("sc_ord", 5);
						//orderSearchWithFromTotal.setParameter("sc_ord1", 4);
						orderSearchWithFromTotal.setParameter("sc_tran", 2);
						//orderSearchWithFromTotal.setParameter("sc_tran1", 2);
						orderSearchWithFromTotal.setParameter("from", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						//orderSearchWithFromTotal.setParameter("from1", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						orderSearchWithFromTotal.setParameter("division_list", divisionList);
						//orderSearchWithFromTotal.setParameter("division_list1", divisionList);
						orderListOfFromNetTotals = orderSearchWithFromTotal.getResultList();

						if (orderListOfFromNetTotals != null) {
							combinedList.put(3, orderListOfFromNetTotals);
						}

					} else if (searchCreteria.getOrderTotalTo() != null && !searchCreteria.getOrderTotalTo().isEmpty()) {
						// query for division head with order with to total
						Query orderSearchWithToNetTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_TO_TOTAL_DIV_HEAD",
								OrderDetailsWithInvoice.class);

						orderSearchWithToNetTotals.setParameter("ord_type", "24");
						//orderSearchWithToNetTotals.setParameter("ord_type1", "26");
						orderSearchWithToNetTotals.setParameter("sc_ord", 5);
						//orderSearchWithToNetTotals.setParameter("sc_ord1", 4);
						orderSearchWithToNetTotals.setParameter("sc_tran", 2);
						//orderSearchWithToNetTotals.setParameter("sc_tran1", 2);
						orderSearchWithToNetTotals.setParameter("to", new BigDecimal(searchCreteria.getOrderTotalTo()));
						//orderSearchWithToNetTotals.setParameter("to1", new BigDecimal(searchCreteria.getOrderTotalTo()));
						orderSearchWithToNetTotals.setParameter("division_list", divisionList);
						//orderSearchWithToNetTotals.setParameter("division_list1", divisionList);
						orderListOfToNetTotals = orderSearchWithToNetTotals.getResultList();

						if (orderListOfToNetTotals != null) {
							combinedList.put(3, orderListOfToNetTotals);
						}

					}
					if (orderListOfToNetTotals == null && orderListOfFromNetTotals == null
							&& orderListOfFromAndToAmounts == null) {
						combinedList.put(3, null);
					}
					searchResultList.setOrderList(formatListFortab1(combinedList));
					searchResultList.setSearCreteria(searchCreteria);
				}
				// logic for roles other than division head and data entry operator
				else {

					if (searchCreteria.getInvoiceNumberOrOrderNumber() != null
							&& !searchCreteria.getInvoiceNumberOrOrderNumber().isEmpty()) {

						Query orderSearchWithOrderId = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_ORD_ID_EMP",
								OrderDetailsWithInvoice.class);

						orderSearchWithOrderId.setParameter("ord_type", "24");
						//orderSearchWithOrderId.setParameter("ord_type1", "26");
						orderSearchWithOrderId.setParameter("sc_ord", 5);
						//orderSearchWithOrderId.setParameter("sc_ord1", 4);
						orderSearchWithOrderId.setParameter("sc_tran", 2);
						//orderSearchWithOrderId.setParameter("sc_tran1", 2);
						orderSearchWithOrderId.setParameter("orderid", searchCreteria.getInvoiceNumberOrOrderNumber());
						//orderSearchWithOrderId.setParameter("orderid1", searchCreteria.getInvoiceNumberOrOrderNumber());
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithOrderId.setParameter("employee_num", emp_id);
						//orderSearchWithOrderId.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithOrderId.setParameter("employee_num", "%");
							//orderSearchWithOrderId.setParameter("employee_num1", "%");
						}
						orderListOfOrderIDSearch = orderSearchWithOrderId.getResultList();

						if (orderListOfOrderIDSearch != null) {
							combinedList.put(1, orderListOfOrderIDSearch);
						}
					}
					if (orderListOfOrderIDSearch == null) {
						combinedList.put(1, null);
					}

					if (searchCreteria.getOrderDateRangeTo() != null && searchCreteria.getOrderDateRangeFrom() != null
							&& !searchCreteria.getOrderDateRangeFrom().isEmpty()
							&& !searchCreteria.getOrderDateRangeTo().isEmpty()) {

						Query orderSearchWithBoth = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FROM_TO_DATES_EMP",
								OrderDetailsWithInvoice.class);
						orderSearchWithBoth.setParameter("ord_type", "24");
						//orderSearchWithBoth.setParameter("ord_type1", "26");
						orderSearchWithBoth.setParameter("sc_ord", 5);
						//orderSearchWithBoth.setParameter("sc_ord1", 4);
						orderSearchWithBoth.setParameter("sc_tran", 2);
						//orderSearchWithBoth.setParameter("sc_tran1", 2);
						orderSearchWithBoth.setParameter("from", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						//orderSearchWithBoth.setParameter("from1", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						orderSearchWithBoth.setParameter("to", stringToDate(searchCreteria.getOrderDateRangeTo()));
						//orderSearchWithBoth.setParameter("to1", stringToDate(searchCreteria.getOrderDateRangeTo()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithBoth.setParameter("employee_num", emp_id);
						//orderSearchWithBoth.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithBoth.setParameter("employee_num", "%");
							//orderSearchWithBoth.setParameter("employee_num1", "%");
						}
						orderListOfFromAndToDates = orderSearchWithBoth.getResultList();

						if (orderListOfFromAndToDates != null) {
							combinedList.put(2, orderListOfFromAndToDates);
						}

					} else if (searchCreteria.getOrderDateRangeFrom() != null
							&& !searchCreteria.getOrderDateRangeFrom().isEmpty()) {

						Query orderSearchWithFrom = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FROM_DATE_EMP",
								OrderDetailsWithInvoice.class);
						orderSearchWithFrom.setParameter("ord_type", "24");
						//orderSearchWithFrom.setParameter("ord_type1", "26");
						orderSearchWithFrom.setParameter("sc_ord", 5);
						//orderSearchWithFrom.setParameter("sc_ord1", 4);
						orderSearchWithFrom.setParameter("sc_tran", 2);
						//orderSearchWithFrom.setParameter("sc_tran1", 2);
						orderSearchWithFrom.setParameter("from", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						//orderSearchWithFrom.setParameter("from1", stringToDate(searchCreteria.getOrderDateRangeFrom()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithFrom.setParameter("employee_num", emp_id);
						//orderSearchWithFrom.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithFrom.setParameter("employee_num", "%");
							//orderSearchWithFrom.setParameter("employee_num1", "%");
						}
						orderListOfFromDates = orderSearchWithFrom.getResultList();

						if (orderListOfFromDates != null) {
							combinedList.put(2, orderListOfFromDates);
						}
					} else if (searchCreteria.getOrderDateRangeTo() != null
							&& !searchCreteria.getOrderDateRangeTo().isEmpty()) {

						Query orderSearchWithTo = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_TO_DATE_EMP",
								OrderDetailsWithInvoice.class);

						orderSearchWithTo.setParameter("ord_type", "24");
						//orderSearchWithTo.setParameter("ord_type1", "26");
						orderSearchWithTo.setParameter("sc_ord", 5);
						//orderSearchWithTo.setParameter("sc_ord1", 4);
						orderSearchWithTo.setParameter("sc_tran", 2);
						//orderSearchWithTo.setParameter("sc_tran1", 2);
						orderSearchWithTo.setParameter("to", stringToDate(searchCreteria.getOrderDateRangeTo()));
						//orderSearchWithTo.setParameter("to1", stringToDate(searchCreteria.getOrderDateRangeTo()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithTo.setParameter("employee_num", emp_id);
						//orderSearchWithTo.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithTo.setParameter("employee_num", "%");
						//	orderSearchWithTo.setParameter("employee_num1", "%");
						}
						orderListOfToDates = orderSearchWithTo.getResultList();

						if (orderListOfToDates != null) {
							combinedList.put(2, orderListOfToDates);
						}

					}
					if (orderListOfToDates == null && orderListOfFromDates == null && orderListOfFromAndToDates == null) {
						combinedList.put(2, null);
					}
					if (searchCreteria.getOrderTotalFrom() != null && searchCreteria.getOrderTotalTo() != null
							&& !searchCreteria.getOrderTotalFrom().isEmpty()
							&& !searchCreteria.getOrderTotalTo().isEmpty()) {

						Query orderSearchWithBothTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FOM_TO_TOTALS_EMP",
								OrderDetailsWithInvoice.class);
						orderSearchWithBothTotals.setParameter("ord_type", "24");
						//orderSearchWithBothTotals.setParameter("ord_type1", "26");
						orderSearchWithBothTotals.setParameter("sc_ord", 5);
						//orderSearchWithBothTotals.setParameter("sc_ord1", 4);
						orderSearchWithBothTotals.setParameter("sc_tran", 2);
						//orderSearchWithBothTotals.setParameter("sc_tran1", 2);
						orderSearchWithBothTotals.setParameter("from", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						//orderSearchWithBothTotals.setParameter("from1", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						orderSearchWithBothTotals.setParameter("to", new BigDecimal(searchCreteria.getOrderTotalTo()));
						//orderSearchWithBothTotals.setParameter("to1", new BigDecimal(searchCreteria.getOrderTotalTo()));
						if(emp_id!=null &&  !emp_id.isEmpty()){	
						orderSearchWithBothTotals.setParameter("employee_num", emp_id);
						//orderSearchWithBothTotals.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithBothTotals.setParameter("employee_num", "%");
							//orderSearchWithBothTotals.setParameter("employee_num1", "%");	
						}
						orderListOfFromAndToAmounts = orderSearchWithBothTotals.getResultList();

						if (orderListOfFromAndToAmounts != null) {
							combinedList.put(3, orderListOfFromAndToAmounts);
						}

					} else if (searchCreteria.getOrderTotalFrom() != null
							&& !searchCreteria.getOrderTotalFrom().isEmpty()) {

						Query orderSearchWithFromTotal = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_FOM_TOTALS_EMP",
								OrderDetailsWithInvoice.class);
						orderSearchWithFromTotal.setParameter("ord_type", "24");
						// orderSearchWithFromTotal.setParameter("ord_type1", "26");
						orderSearchWithFromTotal.setParameter("sc_ord", 5);
						//orderSearchWithFromTotal.setParameter("sc_ord1", 4);
						orderSearchWithFromTotal.setParameter("sc_tran", 2);
						//orderSearchWithFromTotal.setParameter("sc_tran1", 2);
						orderSearchWithFromTotal.setParameter("from", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						//orderSearchWithFromTotal.setParameter("from1", new BigDecimal(searchCreteria.getOrderTotalFrom()));
						if(emp_id!=null &&  !emp_id.isEmpty()){	
						orderSearchWithFromTotal.setParameter("employee_num", emp_id);
						//orderSearchWithFromTotal.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithFromTotal.setParameter("employee_num", "%");
							//orderSearchWithFromTotal.setParameter("employee_num1", "%");
						}
						orderListOfFromNetTotals = orderSearchWithFromTotal.getResultList();

						if (orderListOfFromNetTotals != null) {
							combinedList.put(3, orderListOfFromNetTotals);
						}

					} else if (searchCreteria.getOrderTotalTo() != null && !searchCreteria.getOrderTotalTo().isEmpty()) {

						Query orderSearchWithToNetTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("DELV_ORD_TO_TOTALS_EMP",
								OrderDetailsWithInvoice.class);

						orderSearchWithToNetTotals.setParameter("ord_type", "24");
						//orderSearchWithToNetTotals.setParameter("ord_type1", "26");
						orderSearchWithToNetTotals.setParameter("sc_ord", 5);
						//orderSearchWithToNetTotals.setParameter("sc_ord1", 4);
						orderSearchWithToNetTotals.setParameter("sc_tran", 2);
						//orderSearchWithToNetTotals.setParameter("sc_tran1", 2);
						orderSearchWithToNetTotals.setParameter("to", new BigDecimal(searchCreteria.getOrderTotalTo()));
						//orderSearchWithToNetTotals.setParameter("to1", new BigDecimal(searchCreteria.getOrderTotalTo()));
						if(emp_id!=null &&  !emp_id.isEmpty()){	
						orderSearchWithToNetTotals.setParameter("employee_num", emp_id);
						//orderSearchWithToNetTotals.setParameter("employee_num1", emp_id);
						}
						else{
							orderSearchWithToNetTotals.setParameter("employee_num", "%");
							//orderSearchWithToNetTotals.setParameter("employee_num1", "%");	
						}
						orderListOfToNetTotals = orderSearchWithToNetTotals.getResultList();

						if (orderListOfToNetTotals != null) {
							combinedList.put(3, orderListOfToNetTotals);
						}

					}
					if (orderListOfToNetTotals == null && orderListOfFromNetTotals == null
							&& orderListOfFromAndToAmounts == null) {
						combinedList.put(3, null);
					}
					searchResultList.setOrderList(formatListFortab1(combinedList));
					searchResultList.setSearCreteria(searchCreteria);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return searchResultList;
		}

		private List<OrderDetailsWithInvoice> formatListFortab1(Map<Integer, List<OrderDetailsWithInvoice>> combined) {
			List<OrderDetailsWithInvoice> tab1List = null;
			if (combined.size() > 0) {
			if(combined.get(1)!=null && combined.get(2)!=null && combined.get(3)!=null && !combined.get(1).isEmpty() && !combined.get(2).isEmpty() && !combined.get(3).isEmpty()){
			combined.get(2).retainAll(combined.get(3));
			combined.get(1).retainAll(combined.get(2));
			tab1List=combined.get(1);
			}
			else if(combined.get(1)!=null && combined.get(2)!=null && !combined.get(1).isEmpty() && !combined.get(2).isEmpty()){
				combined.get(1).retainAll(combined.get(2));
				tab1List=combined.get(1);
			}
			else if (combined.get(2)!=null && combined.get(3)!=null && !combined.get(2).isEmpty() && !combined.get(3).isEmpty()){
				combined.get(2).retainAll(combined.get(3));
				tab1List=combined.get(2);
			}
			else if (combined.get(1)!=null && combined.get(3)!=null && !combined.get(1).isEmpty() && !combined.get(3).isEmpty()){
				combined.get(1).retainAll(combined.get(3));
				tab1List=combined.get(1);
			}
			else{
				if(combined.get(1)!=null && !combined.get(1).isEmpty()){
					if(combined.get(2)!=null && combined.get(2).isEmpty() && combined.get(3)!=null && combined.get(3).isEmpty()){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if (combined.get(2)!=null && combined.get(2).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if(combined.get(3)!=null && combined.get(3).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
						tab1List=combined.get(1);
					}
					
				}
				if(combined.get(2)!=null && !combined.get(2).isEmpty()){
					if(combined.get(3)!=null && combined.get(3).isEmpty() && combined.get(1)!=null && combined.get(1).isEmpty()){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if (combined.get(3)!=null && combined.get(3).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if(combined.get(1)!=null && combined.get(1).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
						tab1List=combined.get(2);
					}
					
				}
				if(combined.get(3)!=null && !combined.get(3).isEmpty()){
					if(combined.get(2)!=null && combined.get(2).isEmpty() && combined.get(1)!=null && combined.get(1).isEmpty()){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if (combined.get(2)!=null && combined.get(2).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else if(combined.get(1)!=null && combined.get(1).isEmpty() ){
						tab1List=new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
						tab1List=combined.get(3);
					}
					
				}
			}
			
			} else {
				return new ArrayList<OrderDetailsWithInvoice>();
			}
			return tab1List;
		}

		private List<OrderDetailsWithInvoice> formatListFortab2(Map<Integer, List<OrderDetailsWithInvoice>> combined) {
			List<OrderDetailsWithInvoice> tab1List = null;
			if (combined.size() > 0) {
			if(combined.get(1)!=null && combined.get(2)!=null && !combined.get(1).isEmpty() && !combined.get(2).isEmpty()){
				combined.get(1).retainAll(combined.get(2));
				tab1List=combined.get(1);
			}
			else {
				if(combined.get(1)!=null && !combined.get(1).isEmpty()){
					if(combined.get(2)!=null && combined.get(1).isEmpty()){
						return new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
					tab1List=combined.get(1);
					}
				}
				if(combined.get(2)!=null && !combined.get(2).isEmpty()){
					if(combined.get(1)!=null && combined.get(1).isEmpty()){
						return new ArrayList<OrderDetailsWithInvoice>();
					}
					else{
					tab1List=combined.get(2);
					}
				}
			}
			
			} else {
				return new ArrayList<OrderDetailsWithInvoice>();
			}
			return tab1List;
		}

		public List getTranHeadPkList(String pkey){
			List delvInvSearchList=new ArrayList<>();
			OrderDetailsWithInvoice pkList=null;
			OrderTranHeader head=null;
			CustomerHeader custHead=null;
			EntityManager em=this.entitymanagerfact.createEntityManager();
			if(pkey!=null){
			
					String[] data=pkey.split("@");
					pkList =(OrderDetailsWithInvoice)	em.createQuery("select o from OrderDetailsWithInvoice o where o.id.rtStrId=:p1 "
	+" and o.id.ordWs=:p2"
	+" and o.id.trnSeq=:p3"
	+" and o.id.dcDyOrd=:p4").setParameter("p1", data[2]).setParameter("p2", data[3]).setParameter("p3", Long.parseLong(data[1])).setParameter("p4", data[4]).getSingleResult();
					
				/*	pkList =	em.find(OrderDetailsWithInvoice.class, getHeadId(Long.parseLong(data[1]),data[2],data[3],data[4],data[0]));*/
					System.out.println("we got it");
					delvInvSearchList.add(pkList);
					head=(OrderTranHeader)em.createQuery("select o from OrderTranHeader o where o.id.rtStrId=:p1 "
	+" and o.id.ordWs=:p2"
	+" and o.id.trnSeq=:p3"
	+" and o.id.dcDyOrd=:p4").setParameter("p1", data[2]).setParameter("p2", data[3]).setParameter("p3", Long.parseLong(data[1])).setParameter("p4", data[4]).getSingleResult();
					delvInvSearchList.add(head);
					
					if(pkList!=null){
						custHead=(CustomerHeader) em.createNamedQuery("CUSTOMER_LOOKUP_CUSTOMER_BY_ID", CustomerHeader.class).setParameter("customerId", pkList.getCustId()).getSingleResult();
					}
					delvInvSearchList.add(custHead);

				
					}
			
			return delvInvSearchList;
		}
		
	public List<OrderDetailsWithInvoice> openOrderSearchWithBothTabs(OpenOrderSearchVo search,EmployeeIfc emp){
		
		List<OrderDetailsWithInvoice> searchResult=null;
		List<Integer> divisions = new ArrayList<Integer>();
		try {
			if (emp != null) {
				if (emp.getRoleAccess().equalsIgnoreCase("Within Division")) {
					divisions = getEmpMrchAssc(emp);
					if (search.isSearchByOrderTab()) {
						searchResult=	openOrderSearForOrdersTab(search, divisions, null);

					}

					else if (search.isSearchByCustomerTab()) {
						searchResult=openOrderSearForCustomerAndItemTab(search, divisions, null);
					}
				} 
					  
					  else if(emp.getRoleAccess().equalsIgnoreCase("Linked Agent")) {
					if (search.isSearchByOrderTab()) {
						searchResult=openOrderSearForOrdersTab(search, null, emp.getEmployeeId());
					} else if (search.isSearchByCustomerTab() || search.isSearchByItemTab()) {
						searchResult=	openOrderSearForCustomerAndItemTab(search, null, emp.getEmployeeId());
					}
				}
					  else if (emp.getRoleAccess().equalsIgnoreCase("All")){

							if (search.isSearchByOrderTab()) {
								searchResult=openOrderSearForOrdersTab(search, null, null);
							} else if (search.isSearchByCustomerTab() || search.isSearchByItemTab()) {
								searchResult=	openOrderSearForCustomerAndItemTab(search, null, null);
							}
						
					  }
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return searchResult;
	}

	private List<OrderDetailsWithInvoice> openOrderSearForCustomerAndItemTab(OpenOrderSearchVo search,
			List<Integer> divisions, String emp_id) {
		List<OrderDetailsWithInvoice> openOrdSearchForItem = null;
		List<OrderDetailsWithInvoice> openOrdSearchForCust = null;
		Map<Integer, List<OrderDetailsWithInvoice>> combinedList = new HashMap<>();
		List<OrderDetailsWithInvoice> searhResultList =null;
		try {
			if(search.getInvItemId() != null && !search.getInvItemId().equalsIgnoreCase("")){
				String parsed_data=search.getInvItemId();
				String itmid=getItemId(parsed_data); // calling a method to get item id using bar code
					if(itmid!=null){
						itmid=itmid.trim();
						search.setInvItemId(itmid);
					}
				}
			// for dataentry and division head
			if (divisions != null) {
				if (search.isSearchByItemTab() && search.getInvItemId()!=null) 
				{
					Query itemSearchForOPenOrder=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					itemSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ITEM_DIV_HEAD",OrderDetailsWithInvoice.class);
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")
								|| search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							itemSearchForOPenOrder.setParameter("ord_type", Arrays.asList("23"));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							itemSearchForOPenOrder.setParameter("ord_type", Arrays.asList("24"));
						}
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
							itemSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(1));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							itemSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(0));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							itemSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(5));
						}
					itemSearchForOPenOrder.setParameter("sc_tran", 2);
					itemSearchForOPenOrder.setParameter("itemid", search.getInvItemId());
					itemSearchForOPenOrder.setParameter("itemiddes", search.getInvItemId());
					itemSearchForOPenOrder.setParameter("division_list", divisions);
					}
					//in progress
					else{
						itemSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ITEM_DIV_HEAD_IN_PROGRESS",OrderDetailsWithInvoice.class);
						itemSearchForOPenOrder.setParameter("itemid", search.getInvItemId());
						itemSearchForOPenOrder.setParameter("itemiddes", search.getInvItemId());
						itemSearchForOPenOrder.setParameter("division_list", divisions);
					}
					openOrdSearchForItem = itemSearchForOPenOrder.getResultList();

					if (openOrdSearchForItem != null) {
						combinedList.put(1, openOrdSearchForItem);
					}

				}
				if (openOrdSearchForItem == null) {
					combinedList.put(1, null);
				}
				if (search.isSearchByCustomerTab() && search.getCustomerInfo() != null) {

					Query customerSearchForOPenOrder=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					customerSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_CUST_DIV_HEAD",OrderDetailsWithInvoice.class);
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")
								|| search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							customerSearchForOPenOrder.setParameter("ord_type", Arrays.asList("23"));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							customerSearchForOPenOrder.setParameter("ord_type", Arrays.asList("24"));
						}
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
							customerSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(1));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							customerSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(0));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							customerSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(5));
						}
					
					customerSearchForOPenOrder.setParameter("customer", search.getCustomerInfo());
					customerSearchForOPenOrder.setParameter("customerdes", search.getCustomerInfo());
					customerSearchForOPenOrder.setParameter("division_list", divisions);
					}
					//in progress
					else{
						customerSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_CUST_DIV_HEAD_IN_PROGRESS",OrderDetailsWithInvoice.class);
						customerSearchForOPenOrder.setParameter("customer", search.getCustomerInfo());
						customerSearchForOPenOrder.setParameter("customerdes", search.getCustomerInfo());
						customerSearchForOPenOrder.setParameter("division_list", divisions);
					}
					openOrdSearchForCust = customerSearchForOPenOrder.getResultList();

					if (openOrdSearchForCust != null) {
						combinedList.put(2, openOrdSearchForCust);
					}

				
					
				}
				if (openOrdSearchForCust == null) {
					combinedList.put(2, null);
				}
				searhResultList=formatListFortab2(combinedList);
				
			}
			// for others
			else {
				
					if (search.isSearchByItemTab() && search.getInvItemId() != null) {
					
					Query itemSearchForOPenOrder=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					itemSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ITEM_EMP",OrderDetailsWithInvoice.class);
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")
								|| search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							itemSearchForOPenOrder.setParameter("ord_type", Arrays.asList("23"));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							itemSearchForOPenOrder.setParameter("ord_type", Arrays.asList("24"));
						}
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
							itemSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(1));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							itemSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(0));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							itemSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(5));
						}
					itemSearchForOPenOrder.setParameter("sc_tran", 2);
					itemSearchForOPenOrder.setParameter("itemid", search.getInvItemId());
					itemSearchForOPenOrder.setParameter("itemiddes", search.getInvItemId());
					if(emp_id!=null &&  !emp_id.isEmpty()){	
						
					itemSearchForOPenOrder.setParameter("employee_num", emp_id);
					}
					else{
						itemSearchForOPenOrder.setParameter("employee_num", "%");	
					}
					}
					//in progress
					else{
						itemSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ITEM_EMP_IN_PROGRESS",OrderDetailsWithInvoice.class);
						itemSearchForOPenOrder.setParameter("itemid", search.getInvItemId());
						itemSearchForOPenOrder.setParameter("itemiddes", search.getInvItemId());
						if(emp_id!=null &&  !emp_id.isEmpty()){	
							
							itemSearchForOPenOrder.setParameter("employee_num", emp_id);
							}
							else{
								itemSearchForOPenOrder.setParameter("employee_num", "%");	
							}
					}
					openOrdSearchForItem = itemSearchForOPenOrder.getResultList();

					if (openOrdSearchForItem != null) {
						combinedList.put(1, openOrdSearchForItem);
					}

				}
				if (openOrdSearchForItem == null) {
					combinedList.put(1, null);
				}
				if (search.isSearchByCustomerTab() && search.getCustomerInfo() != null) {


					Query customerSearchForOPenOrder=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					customerSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_CUST_EMP",OrderDetailsWithInvoice.class);
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")
								|| search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							customerSearchForOPenOrder.setParameter("ord_type", Arrays.asList("23"));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							customerSearchForOPenOrder.setParameter("ord_type", Arrays.asList("24"));
						}
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
							customerSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(1));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							customerSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(0));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							customerSearchForOPenOrder.setParameter("sc_ord", Arrays.asList(5));
						}
					
					customerSearchForOPenOrder.setParameter("customer", search.getCustomerInfo());
					customerSearchForOPenOrder.setParameter("customerdes", search.getCustomerInfo());
					if(emp_id!=null &&  !emp_id.isEmpty()){	
						
					customerSearchForOPenOrder.setParameter("employee_num", emp_id);
					}
					else{
						customerSearchForOPenOrder.setParameter("employee_num", "%");
					}
					}
					//in progress
					else{
						customerSearchForOPenOrder= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_CUST_EMP_IN_PROGRESS",OrderDetailsWithInvoice.class);
						customerSearchForOPenOrder.setParameter("customer", search.getCustomerInfo());
						customerSearchForOPenOrder.setParameter("customerdes", search.getCustomerInfo());
						if(emp_id!=null &&  !emp_id.isEmpty()){	
							
							customerSearchForOPenOrder.setParameter("employee_num", emp_id);
							}
							else{
								customerSearchForOPenOrder.setParameter("employee_num", "%");
							}
					}
					openOrdSearchForCust = customerSearchForOPenOrder.getResultList();

					if (openOrdSearchForCust != null) {
						combinedList.put(2, openOrdSearchForCust);
					}

				
					
				
				}
				if (openOrdSearchForCust == null) {
					combinedList.put(2, null);
				}
				searhResultList=formatListFortab2(combinedList);
		

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searhResultList;
	}

	private List<OrderDetailsWithInvoice> openOrderSearForOrdersTab(OpenOrderSearchVo search, List<Integer> divisions,
			String emp_id) {
		List<OrderDetailsWithInvoice> orderListOfFromAndToDates = null;
		List<OrderDetailsWithInvoice> orderListOfFromDates = null;
		List<OrderDetailsWithInvoice> orderListOfToDates = null;
		List<OrderDetailsWithInvoice> orderListOfFromAndToAmounts = null;
		List<OrderDetailsWithInvoice> orderListOfFromNetTotals = null;
		List<OrderDetailsWithInvoice> orderListOfToNetTotals = null;
		List<OrderDetailsWithInvoice> orderListOfOrderIDSearch = null;
		Map<Integer, List<OrderDetailsWithInvoice>> combinedList = new HashMap<>();
		List<OrderDetailsWithInvoice> searchResultList = null;
		try {

			if (divisions != null) {
				if (search.getOrderID()!= null && !search.getOrderID().isEmpty()) {
					Query orderSearchWithOrderId = null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						// query for division head with order id
						orderSearchWithOrderId = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ORD_ID_DIV_HEAD", OrderDetailsWithInvoice.class);
						if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							orderSearchWithOrderId.setParameter("ord_type", Arrays.asList("23"));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							orderSearchWithOrderId.setParameter("ord_type", Arrays.asList("24","26"));
						}
						if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
							orderSearchWithOrderId.setParameter("sc_ord", Arrays.asList(1));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
							orderSearchWithOrderId.setParameter("sc_ord", Arrays.asList(0));
						} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
							orderSearchWithOrderId.setParameter("sc_ord", Arrays.asList(5,4));
						}
						orderSearchWithOrderId.setParameter("sc_tran", 2);

						orderSearchWithOrderId.setParameter("orderid", search.getOrderID());

						orderSearchWithOrderId.setParameter("division_list", divisions);
					}
					// for in progress orders
					else {
				// query for division head with order id
						orderSearchWithOrderId = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ID_DIV_HEAD_FOR_IN_PROGRESS", OrderDetailsWithInvoice.class);
						orderSearchWithOrderId.setParameter("orderid", search.getOrderID());
						orderSearchWithOrderId.setParameter("division_list", divisions);
			}
					orderListOfOrderIDSearch = orderSearchWithOrderId.getResultList();

					if (orderListOfOrderIDSearch != null) {
						combinedList.put(1, orderListOfOrderIDSearch);
					}
				}
				if (orderListOfOrderIDSearch == null) {
					combinedList.put(1, null);
				}

				if (search.getOrdertoDate() != null && search.getOrderFromDate() != null && !search.getOrderFromDate().isEmpty() && !search.getOrdertoDate().isEmpty()) {
					//// query for division head with from date and to date
					Query orderSearchWithBoth=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					orderSearchWithBoth	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TO_DATE_DIV_HEAD",
							OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBoth.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBoth.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithBoth.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBoth.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBoth.setParameter("sc_ord", Arrays.asList(5,4));
					}
					
					orderSearchWithBoth.setParameter("sc_tran", 2);
					
					orderSearchWithBoth.setParameter("from", stringToDate(search.getOrderFromDate()));
					
					orderSearchWithBoth.setParameter("to", stringToDate(search.getOrdertoDate()));
					
					orderSearchWithBoth.setParameter("division_list", divisions);
					}
					//in progress
					else{
						orderSearchWithBoth	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TO_DATE_DIV_HEAD_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
						orderSearchWithBoth.setParameter("from", stringToDate(search.getOrderFromDate()));
						
						orderSearchWithBoth.setParameter("to", stringToDate(search.getOrdertoDate()));
						
						orderSearchWithBoth.setParameter("division_list", divisions);
					}
					orderListOfFromAndToDates = orderSearchWithBoth.getResultList();

					if (orderListOfFromAndToDates != null) {
						combinedList.put(2, orderListOfFromAndToDates);
					}

				} else if (search.getOrderFromDate() != null && !search.getOrderFromDate().isEmpty()) {
					// query for division head with from date
					Query orderSearchWithFrom=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
					orderSearchWithFrom = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_DATE_DIV_HEAD",
							OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFrom.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFrom.setParameter("ord_type", Arrays.asList("24",26));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithFrom.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFrom.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFrom.setParameter("sc_ord", Arrays.asList(5,4));
					}
					
					orderSearchWithFrom.setParameter("sc_tran", 2);
					
					orderSearchWithFrom.setParameter("from", stringToDate(search.getOrderFromDate()));
					
					orderSearchWithFrom.setParameter("division_list", divisions);
					}
					//in progress
					else{
						orderSearchWithFrom = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_DATE_DIV_HEAD_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
					
						orderSearchWithFrom.setParameter("from", stringToDate(search.getOrderFromDate()));
						
						orderSearchWithFrom.setParameter("division_list", divisions);
					}
					orderListOfFromDates = orderSearchWithFrom.getResultList();

					if (orderListOfFromDates != null) {
						combinedList.put(2, orderListOfFromDates);
					}
				} else if (search.getOrdertoDate() != null && !search.getOrdertoDate().isEmpty()) {
					// query for division head with to date
					Query orderSearchWithTo =null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
					orderSearchWithTo	= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_DATE_DIV_HEAD",
							OrderDetailsWithInvoice.class);

					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithTo.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithTo.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithTo.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithTo.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithTo.setParameter("sc_ord", Arrays.asList(5,4));
					}
					orderSearchWithTo.setParameter("sc_tran", 2);
					
					orderSearchWithTo.setParameter("to", stringToDate(search.getOrdertoDate()));
				
					orderSearchWithTo.setParameter("division_list", divisions);
					}
					//in progress
					else{
						orderSearchWithTo	= this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_DATE_DIV_HEAD_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
						orderSearchWithTo.setParameter("to", stringToDate(search.getOrdertoDate()));
						
						orderSearchWithTo.setParameter("division_list", divisions);
					}
				
					orderListOfToDates = orderSearchWithTo.getResultList();

					if (orderListOfToDates != null) {
						combinedList.put(2, orderListOfToDates);
					}

				}
				if (orderListOfToDates == null && orderListOfFromDates == null && orderListOfFromAndToDates == null) {
					combinedList.put(2, null);
				}
				if (search.getOrderTotalFrom() != null && search.getOrderTotalTo() != null && !search.getOrderTotalFrom().isEmpty() && !search.getOrderTotalTo().isEmpty()) {
					// query for division head with order from total and to
					// total
					Query orderSearchWithBothTotals=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
					orderSearchWithBothTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TO_TOTAL_DIV_HEAD", OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBothTotals.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBothTotals.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithBothTotals.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBothTotals.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBothTotals.setParameter("sc_ord", Arrays.asList(5,4));
					}
					
					orderSearchWithBothTotals.setParameter("sc_tran", 2);
				
					orderSearchWithBothTotals.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
			
					orderSearchWithBothTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
					
					orderSearchWithBothTotals.setParameter("division_list", divisions);
					}
					//in progress 
					else{
						orderSearchWithBothTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TO_TOTAL_DIV_HEAD_IN_PROGRESS", OrderDetailsWithInvoice.class);
						
						orderSearchWithBothTotals.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
						
						orderSearchWithBothTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
						
						orderSearchWithBothTotals.setParameter("division_list", divisions);
					}
				
					orderListOfFromAndToAmounts = orderSearchWithBothTotals.getResultList();

					if (orderListOfFromAndToAmounts != null) {
						combinedList.put(3, orderListOfFromAndToAmounts);
					}

				} else if (search.getOrderTotalFrom() != null && !search.getOrderTotalFrom().isEmpty()) {
					// query for division head with order from total
					Query orderSearchWithFromTotal=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
					orderSearchWithFromTotal = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TOTAL_DIV_HEAD",OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFromTotal.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFromTotal.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithFromTotal.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFromTotal.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFromTotal.setParameter("sc_ord", Arrays.asList(5,4));
					}
				
					orderSearchWithFromTotal.setParameter("sc_tran", 2);
			
					orderSearchWithFromTotal.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
			
					orderSearchWithFromTotal.setParameter("division_list", divisions);
					}
					//in progresss
					else{
						orderSearchWithFromTotal = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TOTAL_DIV_HEAD_IN_PROGRESS",OrderDetailsWithInvoice.class);
						
						orderSearchWithFromTotal.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
						
						orderSearchWithFromTotal.setParameter("division_list", divisions);
					}
				
					orderListOfFromNetTotals = orderSearchWithFromTotal.getResultList();

					if (orderListOfFromNetTotals != null) {
						combinedList.put(3, orderListOfFromNetTotals);
					}

				} else if (search.getOrderTotalTo() != null && !search.getOrderTotalTo().isEmpty()) {
					// query for division head with order with to total
					Query orderSearchWithToNetTotals=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
			orderSearchWithToNetTotals	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_TOTAL_DIV_HEAD",OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithToNetTotals.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithToNetTotals.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithToNetTotals.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithToNetTotals.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithToNetTotals.setParameter("sc_ord", Arrays.asList(5,4));
					}
					
					orderSearchWithToNetTotals.setParameter("sc_tran", 2);
					
					orderSearchWithToNetTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
				
					orderSearchWithToNetTotals.setParameter("division_list", divisions);
				
					}
					//in progress
					else{
						orderSearchWithToNetTotals	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_TOTAL_DIV_HEAD_IN_PROGRESS",OrderDetailsWithInvoice.class);
						orderSearchWithToNetTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
						
						orderSearchWithToNetTotals.setParameter("division_list", divisions);
					}
					orderListOfToNetTotals = orderSearchWithToNetTotals.getResultList();

					if (orderListOfToNetTotals != null) {
						combinedList.put(3, orderListOfToNetTotals);
					}

				}
				if (orderListOfToNetTotals == null && orderListOfFromNetTotals == null
						&& orderListOfFromAndToAmounts == null) {
					combinedList.put(3, null);
				}
				searchResultList=formatListFortab1(combinedList);
				
			}
			// logic for roles other than division head and data entry operator
			else {
               if (search.getOrderID() != null&& !search.getOrderID().isEmpty()) {
					Query orderSearchWithOrderId=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
					orderSearchWithOrderId = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ORD_ID_EMP",OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithOrderId.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithOrderId.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithOrderId.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithOrderId.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithOrderId.setParameter("sc_ord", Arrays.asList(5,4));
					}
				
					orderSearchWithOrderId.setParameter("sc_tran", 2);
				
					orderSearchWithOrderId.setParameter("orderid", search.getOrderID());
				
					if(emp_id!=null &&  !emp_id.isEmpty()){	
						orderSearchWithOrderId.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithOrderId.setParameter("employee_num", "%");
						}
					}
					//in progress
					else{
						orderSearchWithOrderId = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_ORD_ID_EMP_IN_PROGRESS",OrderDetailsWithInvoice.class);
						orderSearchWithOrderId.setParameter("orderid", search.getOrderID());
						if(emp_id!=null &&  !emp_id.isEmpty()){	
						orderSearchWithOrderId.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithOrderId.setParameter("employee_num", "%");
						}
					}
					orderListOfOrderIDSearch = orderSearchWithOrderId.getResultList();

					if (orderListOfOrderIDSearch != null) {
						combinedList.put(1, orderListOfOrderIDSearch);
					}
				}
				if (orderListOfOrderIDSearch == null) {
					combinedList.put(1, null);
				}

				if (search.getOrdertoDate() != null && search.getOrderFromDate() != null && !search.getOrderFromDate().isEmpty() && !search.getOrdertoDate().isEmpty()) {
					Query orderSearchWithBoth=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
					orderSearchWithBoth	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TO_DATES_EMP",OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBoth.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBoth.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithBoth.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBoth.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBoth.setParameter("sc_ord", Arrays.asList(5,4));
					}
					orderSearchWithBoth.setParameter("sc_tran", 2);
		
					orderSearchWithBoth.setParameter("from", stringToDate(search.getOrderFromDate()));
				
					orderSearchWithBoth.setParameter("to", stringToDate(search.getOrdertoDate()));
				
					if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithBoth.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithBoth.setParameter("employee_num", "%");
						}
				
					}
					//in progreess
					else{
						orderSearchWithBoth	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_TO_DATES_EMP_IN_PROGRESS",OrderDetailsWithInvoice.class);
						orderSearchWithBoth.setParameter("from", stringToDate(search.getOrderFromDate()));
						orderSearchWithBoth.setParameter("to", stringToDate(search.getOrdertoDate()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithBoth.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithBoth.setParameter("employee_num", "%");
						}
					}
					orderListOfFromAndToDates = orderSearchWithBoth.getResultList();
					
					if (orderListOfFromAndToDates != null) {
						combinedList.put(2, orderListOfFromAndToDates);
					}

				} else if (search.getOrderFromDate() != null && !search.getOrderFromDate().isEmpty()) {
					Query orderSearchWithFrom=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					orderSearchWithFrom = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_DATE_EMP",
							OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFrom.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFrom.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithFrom.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFrom.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFrom.setParameter("sc_ord", Arrays.asList(5));
					}
					orderSearchWithFrom.setParameter("sc_tran", 2);
				
					orderSearchWithFrom.setParameter("from", stringToDate(search.getOrderFromDate()));
					
					if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithFrom.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithFrom.setParameter("employee_num", "%");
						}
					}
					//in progress
					else{
						orderSearchWithFrom = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FROM_DATE_EMP_IN_PROGRESS",
								OrderDetailsWithInvoice.class);

						orderSearchWithFrom.setParameter("from", stringToDate(search.getOrderFromDate()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithFrom.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithFrom.setParameter("employee_num", "%");
						}
					}
					
					orderListOfFromDates = orderSearchWithFrom.getResultList();

					if (orderListOfFromDates != null) {
						combinedList.put(2, orderListOfFromDates);
					}
				} else if (search.getOrdertoDate() != null && !search.getOrdertoDate().isEmpty()) {
					Query orderSearchWithTo=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					orderSearchWithTo = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_DATE_EMP",
							OrderDetailsWithInvoice.class);

					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithTo.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithTo.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithTo.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithTo.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithTo.setParameter("sc_ord", Arrays.asList(5,4));
					}
			
					orderSearchWithTo.setParameter("sc_tran", 2);
				
					orderSearchWithTo.setParameter("to", stringToDate(search.getOrdertoDate()));
				
					if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithTo.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithTo.setParameter("employee_num", "%");
						}
			
					}
					else{
						orderSearchWithTo = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_DATE_EMP_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
						orderSearchWithTo.setParameter("to", stringToDate(search.getOrdertoDate()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithTo.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithTo.setParameter("employee_num", "%");
						}
						}
					orderListOfToDates = orderSearchWithTo.getResultList();

					if (orderListOfToDates != null) {
						combinedList.put(2, orderListOfToDates);
					}

				}
				if (orderListOfToDates == null && orderListOfFromDates == null && orderListOfFromAndToDates == null) {
					combinedList.put(2, null);
				}
				if (search.getOrderTotalFrom() != null && search.getOrderTotalTo() != null && !search.getOrderTotalFrom().isEmpty() && !search.getOrderTotalTo().isEmpty()) {
					Query orderSearchWithBothTotals=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					orderSearchWithBothTotals	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FOM_TO_TOTALS_EMP",
							OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBothTotals.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBothTotals.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithBothTotals.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithBothTotals.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithBothTotals.setParameter("sc_ord", Arrays.asList(5,4));
					}
					orderSearchWithBothTotals.setParameter("sc_tran", 2);
					
					orderSearchWithBothTotals.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
					
					orderSearchWithBothTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
				
					if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithBothTotals.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithBothTotals.setParameter("employee_num", "%");
						}
					}
					else{
						orderSearchWithBothTotals	 = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FOM_TO_TOTALS_EMP_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
						orderSearchWithBothTotals.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
						orderSearchWithBothTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithBothTotals.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithBothTotals.setParameter("employee_num", "%");
						}
					}
				
					orderListOfFromAndToAmounts = orderSearchWithBothTotals.getResultList();

					if (orderListOfFromAndToAmounts != null) {
						combinedList.put(3, orderListOfFromAndToAmounts);
					}

				} else if (search.getOrderTotalFrom() != null && !search.getOrderTotalFrom().isEmpty()) {
					Query orderSearchWithFromTotal=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
						orderSearchWithFromTotal = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FOM_TOTALS_EMP",
							OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFromTotal.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFromTotal.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithFromTotal.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithFromTotal.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithFromTotal.setParameter("sc_ord", Arrays.asList(5,4));
					}
					
					orderSearchWithFromTotal.setParameter("sc_tran", 2);
		
					orderSearchWithFromTotal.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
				
if(emp_id!=null &&  !emp_id.isEmpty()){
						
						orderSearchWithFromTotal.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithFromTotal.setParameter("employee_num", "%");
						}
					}
					//in progress
					else{
						orderSearchWithFromTotal = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_FOM_TOTALS_EMP_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
						orderSearchWithFromTotal.setParameter("from", new BigDecimal(search.getOrderTotalFrom()));
						if(emp_id!=null &&  !emp_id.isEmpty()){
						
						orderSearchWithFromTotal.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithFromTotal.setParameter("employee_num", "%");
						}
					}
				
					orderListOfFromNetTotals = orderSearchWithFromTotal.getResultList();

					if (orderListOfFromNetTotals != null) {
						combinedList.put(3, orderListOfFromNetTotals);
					}

				} else if (search.getOrderTotalTo() != null && !search.getOrderTotalTo().isEmpty()) {
					Query orderSearchWithToNetTotals=null;
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new") || search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						
					orderSearchWithToNetTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_TOTALS_EMP",
							OrderDetailsWithInvoice.class);
					if (search.getOpenOrderStatus().equalsIgnoreCase("open") || search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithToNetTotals.setParameter("ord_type", Arrays.asList("23"));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithToNetTotals.setParameter("ord_type", Arrays.asList("24","26"));
					}
					if (search.getOpenOrderStatus().equalsIgnoreCase("open")) {
						orderSearchWithToNetTotals.setParameter("sc_ord", Arrays.asList(1));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("new")) {
						orderSearchWithToNetTotals.setParameter("sc_ord", Arrays.asList(0));
					} else if (search.getOpenOrderStatus().equalsIgnoreCase("awaiting")) {
						orderSearchWithToNetTotals.setParameter("sc_ord", Arrays.asList(5,4));
					}
			
					orderSearchWithToNetTotals.setParameter("sc_tran", 2);
			
					orderSearchWithToNetTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
					if(emp_id!=null &&  !emp_id.isEmpty()){
					orderSearchWithToNetTotals.setParameter("employee_num", emp_id);
					}
					else{
						orderSearchWithToNetTotals.setParameter("employee_num", "%");
					}
					}
					//in progress
					else{
						orderSearchWithToNetTotals = this.entitymanagerfact.createEntityManager().createNamedQuery("OPEN_ORD_TO_TOTALS_EMP_IN_PROGRESS",
								OrderDetailsWithInvoice.class);
						orderSearchWithToNetTotals.setParameter("to", new BigDecimal(search.getOrderTotalTo()));
						
						if(emp_id!=null &&  !emp_id.isEmpty()){
						orderSearchWithToNetTotals.setParameter("employee_num", emp_id);
						}
						else{
							orderSearchWithToNetTotals.setParameter("employee_num", "%");
						}
					}
					orderListOfToNetTotals = orderSearchWithToNetTotals.getResultList();

					if (orderListOfToNetTotals != null) {
						combinedList.put(3, orderListOfToNetTotals);
					}

				}
				if (orderListOfToNetTotals == null && orderListOfFromNetTotals == null
						&& orderListOfFromAndToAmounts == null) {
					combinedList.put(3, null);
				}
				searchResultList=formatListFortab1(combinedList);
			

			
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return searchResultList;
	}
	
	
	public List<ReturnedOrdersVO> getReturnedOrderByOrderAndCustomer(String orderId,String OrderDateFrom,String orderDateTo,String OrderTotalFrom,String orderTotalTo,String customer,String rtnitem,EmployeeIfc employee)
	{
		Query returnedOrderQuery = null;
		EntityManager entitymanager = null;
		List<ReturnedOrdersVO> returnedOrdersVOlist = new ArrayList<ReturnedOrdersVO>();
		boolean flag = false;
		try{
			entitymanager =  entitymanagerfact.createEntityManager();
			flag = true;
		}
		catch(Exception e)
		{
			flag  = false;
		}
		if(flag)
		{
		String fromDateDB = null;
		String toDateDB = null;
		BigDecimal fromTotalDB = null;
		BigDecimal toTotalDB = null;
		String parsedCustomer = "";
		String parsedItem = "";
		if(orderId.equals(""))
		{
			orderId = "%";
		}
		else{
			orderId= orderId.trim();
		}
		if(OrderDateFrom.equals(""))
		{
			try{
				String fromDateDBString = (String) entitymanager.createQuery("SELECT MIN(OTS.id.dcDyOrd) FROM OrderTranSum OTS").getSingleResult();
				fromDateDB = fromDateDBString;
			}
			catch(Exception e)
			{
				try {
					fromDateDB = "2001-01-01";
				} catch (Exception e1) {
				}
			}
		}
		else{
			OrderTotalFrom = OrderTotalFrom.trim();
			fromDateDB = stringFromToStringDB(OrderDateFrom);
		}
		if(orderDateTo.equals(""))
		{
			toDateDB = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
		}
		else{
			orderDateTo = orderDateTo.trim();
			toDateDB = stringFromToStringDB(orderDateTo);
		}
		if(OrderTotalFrom.equals(""))
		{
			fromTotalDB  = new BigDecimal(0);
		}
		else{
			OrderTotalFrom = OrderTotalFrom.trim();
			fromTotalDB = new BigDecimal(OrderTotalFrom);
		}
		if(orderTotalTo.equals(""))
		{
			try{
				toTotalDB = (BigDecimal) entitymanager.createQuery("SELECT MAX(OTS.dkartNetTot) FROM OrderTranSum OTS").getSingleResult();
			}
			catch(NoResultException e)
			{
				try {
					toTotalDB = new BigDecimal("99999999999999999999");
				} catch (Exception e1) {
				}
			}
		}
		else{
			orderTotalTo = orderTotalTo.trim();
			toTotalDB = new BigDecimal(orderTotalTo);
		}
		if(customer.equals(""))
		{
			parsedCustomer = "%";
		}
		else{
			customer = customer.trim();
			customer = customer.toUpperCase();
			String[] splitData = customer.split(" ");
			for (String str : splitData) {
				parsedCustomer += str + "%";
			}
		}
		if(rtnitem.equals(""))
		{
			parsedItem = "%";
		}
		else{
			rtnitem = rtnitem.trim();
			rtnitem = rtnitem.toUpperCase();
			String[] splitData = rtnitem.split("---");
			int lastElement = splitData.length - 1;
			parsedItem = "%";
			parsedItem = splitData[lastElement];
			
			String itmid=getItemId(parsedItem); // calling a method to get item id using bar code added by jagadish
			if(itmid!=null){
				parsedItem=itmid;
				parsedItem=parsedItem.trim();
			}
		}
			try{
				String searchQuery = "";
				if(employee.getRoleAccess().equalsIgnoreCase("Linked Agent"))
				{
					returnedOrderQuery = entitymanager.createNamedQuery("RETURN_ORD_SEARCH_FOR_SalesAgent");
					returnedOrderQuery.setParameter("fromDate", fromDateDB);
					returnedOrderQuery.setParameter("toDate", toDateDB);
					returnedOrderQuery.setParameter("orderId",orderId);
					returnedOrderQuery.setParameter("fromTotal", fromTotalDB);
					returnedOrderQuery.setParameter("toTotal", toTotalDB);
					returnedOrderQuery.setParameter("salesAgent", employee.getEmployeeId());
					returnedOrderQuery.setParameter("itemid", parsedItem);
					returnedOrderQuery.setParameter("customer", parsedCustomer);
					
				}
				if(employee.getRoleAccess().equalsIgnoreCase("Within Division"))
				{
					List<EmpMerchAssociationIfc> merchandiseList = employee.getMerchAssoc();
					List<String> merchGroupList = new ArrayList<String>();
					for(EmpMerchAssociationIfc merchHieararchy : merchandiseList)
					{
						if(merchHieararchy.getMerchId().startsWith("1:"))
						{
						merchGroupList.add(merchHieararchy.getMerchId().trim());
						}
					}
					returnedOrderQuery = entitymanager.createNamedQuery("RETURN_ORD_SEARCH_FOR_WITH_IN_DIVISION");
					returnedOrderQuery.setParameter("fromDate", fromDateDB);
					returnedOrderQuery.setParameter("toDate", toDateDB);
					returnedOrderQuery.setParameter("orderId",orderId);
					returnedOrderQuery.setParameter("fromTotal", fromTotalDB);
					returnedOrderQuery.setParameter("toTotal", toTotalDB);
					returnedOrderQuery.setParameter("divIds", merchGroupList);
					returnedOrderQuery.setParameter("itemid", parsedItem);
					returnedOrderQuery.setParameter("customer", parsedCustomer);
				}
				if(employee.getRoleAccess().equalsIgnoreCase("All"))
				{
					returnedOrderQuery = entitymanager.createNamedQuery("RETURN_ORD_SEARCH_FOR_ALL");
					returnedOrderQuery.setParameter("fromDate", fromDateDB);
					returnedOrderQuery.setParameter("toDate", toDateDB);
					returnedOrderQuery.setParameter("orderId",orderId);
					returnedOrderQuery.setParameter("fromTotal", fromTotalDB);
					returnedOrderQuery.setParameter("toTotal", toTotalDB);
					returnedOrderQuery.setParameter("itemid", parsedItem);
					returnedOrderQuery.setParameter("customer", parsedCustomer);
					
				}
				List<Object[]> returnedOrderList = returnedOrderQuery.getResultList();
				for(Object[] returnOrders :returnedOrderList)
				{
					ReturnedOrdersVO returnOrder = new ReturnedOrdersVO();
					returnOrder.setOrderId(returnOrders[0].toString());
					returnOrder.setOrderDate(stringDBToDate((String)returnOrders[1]));
					returnOrder.setCustomerId((String)returnOrders[2]);
					returnOrder.setOrderTotal((BigDecimal)returnOrders[3]);
					returnOrder.setCustomerName((String)returnOrders[4]);
					returnOrder.setSalesAgent((String)returnOrders[5]);
					returnOrder.setReasonCode((String)returnOrders[6]);
					returnOrder.setOriginalOrderId(returnOrders[8].toString());
					returnOrder.setAcceptedClaimId(returnOrders[9].toString());
					returnedOrdersVOlist.add(returnOrder);
				}
				}
		
		catch(Exception e)
		{
			LOGGER.error("Error Occured While Finding Returned Order Detais From Database=> "+e);
		}
		}
		
		return returnedOrdersVOlist;
	}
	
	/* sharanya Start */
	public List<SdsOrdersDashboard> getOrdersCountByAllDiv(String range) {
		Date current_date = new Date();
		Date from_date = getDateFromRange(range);
		try{
		String querystr = "SELECT sum(ord.deliveredOrders),sum(ord.inProgress),sum(ord.cancelledOrders),sum(ord.newOrders),sum(ord.openOrders),sum(ord.pendingOrders),sum(ord.returnedOrders),sum(ord.completed) FROM SdsOrdersDashboard ord WHERE ord.id.ordDate BETWEEN :from AND :to ORDER BY ord.id.ordDate DESC";
		Query query = entitymanagerfact.createEntityManager().createQuery(querystr,Vector.class);
		query.setParameter("from", from_date,TemporalType.DATE);
		query.setParameter("to", current_date,TemporalType.DATE);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		
		Vector result = (Vector) query.getResultList();
		SdsOrdersDashboard aggregate = new SdsOrdersDashboard();
		Object innrResult[] = (Object[])result.get(0);
		BigDecimal delivered = (BigDecimal) innrResult[0];
		BigDecimal Inprogress=(BigDecimal) innrResult[1];
		BigDecimal Cancelled=(BigDecimal) innrResult[2];
		BigDecimal Neworders=(BigDecimal) innrResult[3];
		BigDecimal OpenOrders=(BigDecimal) innrResult[4];
		BigDecimal PendingOrders=(BigDecimal) innrResult[5];
		BigDecimal ReturnOrders=(BigDecimal) innrResult[6];
		BigDecimal Completed=(BigDecimal) innrResult[7];
		aggregate.setDeliveredOrders(delivered);
		aggregate.setInProgress(Inprogress);
		aggregate.setCancelledOrders(Cancelled);
		aggregate.setNewOrders(Neworders);
		aggregate.setOpenOrders(OpenOrders);
		aggregate.setPendingOrders(PendingOrders);
		aggregate.setReturnedOrders(ReturnOrders);
		aggregate.setCompleted(Completed);	
		orders_count=result;
		orders_count.clear();
		orders_count.add(aggregate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return orders_count;
	}

	public List<OrderDetail> getOrdersByAllDiv(String range) {
		Date current_date = new Date();
		Date from_date = getDateFromRange(range);
		String querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
		Query query = entitymanagerfact.createEntityManager().createQuery(querystr, OrderDetail.class);
		query.setParameter("from", from_date);
		query.setParameter("to", current_date);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		orders= query.getResultList();
		return orders;
	}

	public List<OrderDetail> getOrdersByAllDiv(String range, String status) {
		Date current_date = new Date();
		Date from_date = getDateFromRange(range);
		/*
		 * String querystr =
		 * "SELECT ord FROM OrderDetail ord WHERE ord.divisionId=:id AND ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC"
		 * ;
		 */
		String querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy!=18 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
		Query query = entitymanagerfact.createEntityManager().createQuery(querystr);

		switch (status) {
		case OrderStatus.NEW: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.scOrd=0  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.OPEN: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.scOrd =1  AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.IN_PROGRESS: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE (ord.ordTy=23 OR ord.ordTy=26) AND ord.scOrd BETWEEN 2 AND 3 AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.COMPELETED: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.scOrd BETWEEN 4 AND 5 AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.DELIVERED: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.scOrd BETWEEN 6 AND 7 AND ord.scTran=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.QUOTE: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=23 AND ord.scOrd=0  AND ord.scTran=4 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.CANCELLED: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=25 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		case OrderStatus.RETURNED: // $NON-NLS-1$
			querystr = "SELECT ord FROM OrderDetail ord WHERE ord.ordTy=2 AND ord.id.orderDate BETWEEN :from AND :to ORDER BY ord.id.orderDate DESC";
			query = entitymanagerfact.createEntityManager().createQuery(querystr);
			break;
		default:
			break;
		}
		query.setParameter("from", from_date);
		query.setParameter("to", current_date);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		orders = query.getResultList();
		return orders;
	}
	/* sharanya END */
	
	
	private EntityManager getEntityManager() {
        return entitymanagerfact.createEntityManager();	
	}
	/*order approval time : Kunal*/ 
	public String getTimestampByOrderId (String orderId){
		
		String timestamp = null;
		try{
			
			EntityManager em = getEntityManager();
			String sqlQry = "SELECT T.TS_ORD_TIME FROM RISPL_DK_ORD_APRV_TIME T WHERE T.ORDER_ID=" + orderId;
			Query qe = em.createNativeQuery(sqlQry);
			Date result = (Date) qe.getSingleResult();
			timestamp = new SimpleDateFormat("MMM d, yyyy h:mm:ss a").format(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timestamp;
	}
}