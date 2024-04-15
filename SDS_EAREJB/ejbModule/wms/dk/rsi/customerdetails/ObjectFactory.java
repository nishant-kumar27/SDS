
package wms.dk.rsi.customerdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wms.dk.rsi.customerdetails package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CustomerOrderException_QNAME = new QName("http://customerDetails.rsi.dk.wms", "CustomerOrderException");
    private final static QName _CreateCustomerOrderResponse_QNAME = new QName("http://customerDetails.rsi.dk.wms", "createCustomerOrderResponse");
    private final static QName _ReturnCustomerOrderResponse_QNAME = new QName("http://customerDetails.rsi.dk.wms", "returnCustomerOrderResponse");
    private final static QName _ReturnCustomerOrder_QNAME = new QName("http://customerDetails.rsi.dk.wms", "returnCustomerOrder");
    private final static QName _CreateCustomerOrder_QNAME = new QName("http://customerDetails.rsi.dk.wms", "createCustomerOrder");
    private final static QName _CancelCustomerOrder_QNAME = new QName("http://customerDetails.rsi.dk.wms", "cancelCustomerOrder");
    private final static QName _CancelCustomerOrderResponse_QNAME = new QName("http://customerDetails.rsi.dk.wms", "cancelCustomerOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wms.dk.rsi.customerdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelCustomerOrderResponse }
     * 
     */
    public CancelCustomerOrderResponse createCancelCustomerOrderResponse() {
        return new CancelCustomerOrderResponse();
    }

    /**
     * Create an instance of {@link CancelCustomerOrder }
     * 
     */
    public CancelCustomerOrder createCancelCustomerOrder() {
        return new CancelCustomerOrder();
    }

    /**
     * Create an instance of {@link CreateCustomerOrder }
     * 
     */
    public CreateCustomerOrder createCreateCustomerOrder() {
        return new CreateCustomerOrder();
    }

    /**
     * Create an instance of {@link ReturnCustomerOrder }
     * 
     */
    public ReturnCustomerOrder createReturnCustomerOrder() {
        return new ReturnCustomerOrder();
    }

    /**
     * Create an instance of {@link ReturnCustomerOrderResponse }
     * 
     */
    public ReturnCustomerOrderResponse createReturnCustomerOrderResponse() {
        return new ReturnCustomerOrderResponse();
    }

    /**
     * Create an instance of {@link CustomerOrderException }
     * 
     */
    public CustomerOrderException createCustomerOrderException() {
        return new CustomerOrderException();
    }

    /**
     * Create an instance of {@link CreateCustomerOrderResponse }
     * 
     */
    public CreateCustomerOrderResponse createCreateCustomerOrderResponse() {
        return new CreateCustomerOrderResponse();
    }

    /**
     * Create an instance of {@link CustomerOrderReturnDetails }
     * 
     */
    public CustomerOrderReturnDetails createCustomerOrderReturnDetails() {
        return new CustomerOrderReturnDetails();
    }

    /**
     * Create an instance of {@link CustomerOrderCreationDetails }
     * 
     */
    public CustomerOrderCreationDetails createCustomerOrderCreationDetails() {
        return new CustomerOrderCreationDetails();
    }

    /**
     * Create an instance of {@link ReturnHeaderDetails }
     * 
     */
    public ReturnHeaderDetails createReturnHeaderDetails() {
        return new ReturnHeaderDetails();
    }

    /**
     * Create an instance of {@link CustomerOrderReturnStatus }
     * 
     */
    public CustomerOrderReturnStatus createCustomerOrderReturnStatus() {
        return new CustomerOrderReturnStatus();
    }

    /**
     * Create an instance of {@link CustomerOrderCancelDetails }
     * 
     */
    public CustomerOrderCancelDetails createCustomerOrderCancelDetails() {
        return new CustomerOrderCancelDetails();
    }

    /**
     * Create an instance of {@link CreateHeaderDetails }
     * 
     */
    public CreateHeaderDetails createCreateHeaderDetails() {
        return new CreateHeaderDetails();
    }

    /**
     * Create an instance of {@link CancelLineItemDetails }
     * 
     */
    public CancelLineItemDetails createCancelLineItemDetails() {
        return new CancelLineItemDetails();
    }

    /**
     * Create an instance of {@link CustomerOrderCancelStatus }
     * 
     */
    public CustomerOrderCancelStatus createCustomerOrderCancelStatus() {
        return new CustomerOrderCancelStatus();
    }

    /**
     * Create an instance of {@link CreateLineDetails }
     * 
     */
    public CreateLineDetails createCreateLineDetails() {
        return new CreateLineDetails();
    }

    /**
     * Create an instance of {@link CustomerOrderCreationStatus }
     * 
     */
    public CustomerOrderCreationStatus createCustomerOrderCreationStatus() {
        return new CustomerOrderCreationStatus();
    }

    /**
     * Create an instance of {@link ReturnLineDetails }
     * 
     */
    public ReturnLineDetails createReturnLineDetails() {
        return new ReturnLineDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerOrderException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "CustomerOrderException")
    public JAXBElement<CustomerOrderException> createCustomerOrderException(CustomerOrderException value) {
        return new JAXBElement<CustomerOrderException>(_CustomerOrderException_QNAME, CustomerOrderException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCustomerOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "createCustomerOrderResponse")
    public JAXBElement<CreateCustomerOrderResponse> createCreateCustomerOrderResponse(CreateCustomerOrderResponse value) {
        return new JAXBElement<CreateCustomerOrderResponse>(_CreateCustomerOrderResponse_QNAME, CreateCustomerOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCustomerOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "returnCustomerOrderResponse")
    public JAXBElement<ReturnCustomerOrderResponse> createReturnCustomerOrderResponse(ReturnCustomerOrderResponse value) {
        return new JAXBElement<ReturnCustomerOrderResponse>(_ReturnCustomerOrderResponse_QNAME, ReturnCustomerOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCustomerOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "returnCustomerOrder")
    public JAXBElement<ReturnCustomerOrder> createReturnCustomerOrder(ReturnCustomerOrder value) {
        return new JAXBElement<ReturnCustomerOrder>(_ReturnCustomerOrder_QNAME, ReturnCustomerOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCustomerOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "createCustomerOrder")
    public JAXBElement<CreateCustomerOrder> createCreateCustomerOrder(CreateCustomerOrder value) {
        return new JAXBElement<CreateCustomerOrder>(_CreateCustomerOrder_QNAME, CreateCustomerOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelCustomerOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "cancelCustomerOrder")
    public JAXBElement<CancelCustomerOrder> createCancelCustomerOrder(CancelCustomerOrder value) {
        return new JAXBElement<CancelCustomerOrder>(_CancelCustomerOrder_QNAME, CancelCustomerOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelCustomerOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://customerDetails.rsi.dk.wms", name = "cancelCustomerOrderResponse")
    public JAXBElement<CancelCustomerOrderResponse> createCancelCustomerOrderResponse(CancelCustomerOrderResponse value) {
        return new JAXBElement<CancelCustomerOrderResponse>(_CancelCustomerOrderResponse_QNAME, CancelCustomerOrderResponse.class, null, value);
    }

}
