package org.supplychain.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.xml.ws.BindingProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.supplychain.store.generated.ObjectFactory;
import org.supplychain.store.generated.OrderDetails;
import org.supplychain.store.generated.StorePT;
import org.supplychain.store.generated.StoreService;

/**
 * Minimal desktop UI that submits restock orders via the generated SOAP stubs.
 */
public class StoreGuiApp extends Application {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final ObservableList<String> history = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        TextField endpointField = new TextField("http://localhost:8080/ode/processes/StoreService.StorePort");
        TextField orderField = new TextField("ORDER-1");
        TextField productField = new TextField("Laptop");
        Spinner<Integer> quantitySpinner = new Spinner<>();
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 10));

        Button submitButton = new Button("Submit Order");
        submitButton.setDefaultButton(true);

        ListView<String> logView = new ListView<>(history);

        submitButton.setOnAction(event -> {
            submitButton.setDisable(true);
            runOrder(endpointField.getText(), orderField.getText(), productField.getText(), quantitySpinner.getValue(), () -> submitButton.setDisable(false));
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));
        form.addRow(0, new Label("Endpoint"), endpointField);
        form.addRow(1, new Label("Order ID"), orderField);
        form.addRow(2, new Label("Product"), productField);
        form.addRow(3, new Label("Quantity"), quantitySpinner);

        HBox buttonRow = new HBox(submitButton);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);
        buttonRow.setPadding(new Insets(0, 20, 20, 20));

        BorderPane root = new BorderPane();
        root.setTop(form);
        root.setCenter(logView);
        root.setBottom(buttonRow);

        stage.setTitle("Store Process Desktop Client");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    private void runOrder(String endpoint, String orderId, String product, int quantity, Runnable onFinished) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    StoreService service = new StoreService();
                    StorePT port = service.getStorePort();
                    BindingProvider bindingProvider = (BindingProvider) port;
                    bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

                    ObjectFactory factory = new ObjectFactory();
                    OrderDetails details = factory.createOrderDetails();
                    details.setOrderID(orderId);
                    details.setProduct(product);
                    details.setQuantity(quantity);

                    log("Dispatching order %s (%s x%d)", orderId, product, quantity);
                    String response = port.startRestock(details);
                    log("Response [%s]: %s", orderId, response);
                } catch (Exception ex) {
                    log("Order %s failed: %s", orderId, ex.getMessage());
                    showError("SOAP call failed", ex.getMessage());
                }
                return null;
            }

            @Override
            protected void succeeded() {
                onFinished.run();
            }

            @Override
            protected void failed() {
                onFinished.run();
            }
        };
        Thread thread = new Thread(task, "store-gui-call");
        thread.setDaemon(true);
        thread.start();
    }

    private void log(String template, Object... args) {
        String line = String.format("%s - %s", TIME_FORMATTER.format(LocalDateTime.now()), String.format(template, args));
        Platform.runLater(() -> history.add(0, line));
    }

    private void showError(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
