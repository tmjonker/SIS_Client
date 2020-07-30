import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ConnectForm {

    public ConnectForm(Stage stage) {
        Button connectButton = new Button("Connect");
        connectButton.setOnAction(e -> onConnectButtonClick(stage));
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        HBox hbox1 = new HBox(10);
        hbox1.getChildren().addAll(connectButton, exitButton);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setId("mainbox");

        Scene scene = new Scene(hbox1, 250, 50);
        scene.getStylesheets().add("client_css.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("SIS v1.0");
        stage.show();
    }

    private void onConnectButtonClick(Stage stage) {
        Connector connector = new Connector();
        if (connector.establishConnection()) {
            School school = new School(connector.getNetSocket());
            new QueryForm(stage, school);
        } else {
            new MessageBox().show("Connection Unsuccessful",
                    "Connection Problem");
        }
    }
}
