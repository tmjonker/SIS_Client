import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QueryForm {

    Stage primaryStage;

    public QueryForm(Stage stage, School school) {
        primaryStage = stage;
        Button yesButton = new Button();
        yesButton.setText("Yes");
        yesButton.setOnAction(e -> new NewStudentForm(stage, school));

        Button noButton = new Button("No");
        noButton.setOnAction(e -> new ReturningStudentForm(school));

        VBox vbox = new VBox(20);
        HBox hbox = new HBox(20);

        Label questionLabel = new Label("Are you a new student?");
        hbox.getChildren().addAll(yesButton, noButton);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(questionLabel, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setId("mainbox");

        Scene scene = new Scene(vbox, 300, 100);
        scene.getStylesheets().add("client_css.css");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("SIS v1.0");
        stage.show();
        stage.centerOnScreen();
    }
}
