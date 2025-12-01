package com.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class StoreClient {

    public static void main(String[] args) {
        String endpointUrl = "http://localhost:8080/ode/processes/StoreService.StorePort";
        String soapAction = "http://supplychain.org/store/startRestock";

        String soapXml = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
            "xmlns:data=\"http://supplychain.org/data\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "      <data:OrderDetails>" +
            "         <data:orderID>ORDER-12345</data:orderID>" +
            "         <data:product>Laptop</data:product>" +
            "         <data:quantity>10</data:quantity>" +
            "      </data:OrderDetails>" +
            "   </soapenv:Body>" +
            "</soapenv:Envelope>";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpointUrl))
                    .header("Content-Type", "text/xml; charset=utf-8")
                    .header("SOAPAction", soapAction)
                    .POST(BodyPublishers.ofString(soapXml))
                    .build();

            System.out.println("Sending SOAP Request to: " + endpointUrl);
            System.out.println(soapXml);

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            System.out.println("\nResponse Code: " + response.statusCode());
            System.out.println("Response Body:");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
