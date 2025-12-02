package com.client;

import javax.xml.ws.BindingProvider;
import org.supplychain.store.generated.ObjectFactory;
import org.supplychain.store.generated.OrderDetails;
import org.supplychain.store.generated.StorePT;
import org.supplychain.store.generated.StoreService;

public class StoreClient {

    private static final String ENDPOINT = "http://localhost:8080/ode/processes/StoreService.StorePort";

    public static void main(String[] args) {
        OrderDetails orderDetails = buildOrderRequest();
        invokeStoreProcess(orderDetails);
    }

    private static OrderDetails buildOrderRequest() {
        ObjectFactory factory = new ObjectFactory();
        OrderDetails details = factory.createOrderDetails();
        details.setOrderID("ORDER-12345");
        details.setProduct("Laptop");
        details.setQuantity(10);
        return details;
    }

    private static void invokeStoreProcess(OrderDetails orderDetails) {
        StoreService service = new StoreService();
        StorePT port = service.getStorePort();

        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT);

        System.out.println("Invoking StoreService.startRestock via generated JAX-WS client...");
        System.out.println("Endpoint: " + ENDPOINT);
        System.out.println("Payload: orderId=" + orderDetails.getOrderID() +
                ", product=" + orderDetails.getProduct() +
                ", quantity=" + orderDetails.getQuantity());

        try {
            String response = port.startRestock(orderDetails);
            System.out.println("\nResponse from Store process:");
            System.out.println(response);
        } catch (Exception ex) {
            System.err.println("Store process invocation failed");
            ex.printStackTrace();
        }
    }
}
