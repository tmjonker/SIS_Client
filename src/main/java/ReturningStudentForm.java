import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReturningStudentForm {

    public ReturningStudentForm(School school) {
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox(20);
        HBox hbox3 = new HBox(20);

        Label instructions = new Label("Enter your student ID to login:");
        hbox1.getChildren().add(instructions);
        hbox1.setPadding(new Insets(20,0,0,0));

        Label studentIdLabel = new Label("Student ID: ");
        TextField studentIdField = new TextField();
        hbox2.getChildren().addAll(studentIdLabel, studentIdField);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> onSubmitButtonClick(studentIdField, stage, school));

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> onResetButtonClick(studentIdField));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onBackButtonClick(stage));

        hbox3.getChildren().addAll(submitButton, resetButton, backButton);

        VBox vbox1 = new VBox(20);
        vbox1.getChildren().addAll(hbox1,hbox2, hbox3);
        hbox1.setAlignment(Pos.TOP_CENTER);
        hbox2.setAlignment(Pos.TOP_CENTER);
        hbox3.setAlignment(Pos.TOP_CENTER);

        pane.setCenter(vbox1);
        pane.setId("mainbox");

        Scene scene = new Scene(pane, 300, 150);
        scene.getStylesheets().add("client_css.css");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("SIS v1.0");
        stage.show();
    }

    private void onResetButtonClick(TextField s) {
        s.clear();
    }

    /*
    Checks data entered to verify that it is valid and then transmits data from those fields to the School
    to pull their records.
     */
    private void onSubmitButtonClick(TextField s, Stage stage, School school) {
        if (s.getText().matches("^[a-zA-Z ]")) {
            s.clear();
            new MessageBox("Your student ID should only contain numbers.", "Error");
        } else if (s.getText().isEmpty()) {
            s.clear();
            new MessageBox("Please fill in one of the fields.", "Error");
        } else if (!school.checkOutOfRange(Integer.parseInt(s.getText()))) {
            s.clear();
                new MessageBox("Your entry is invalid.  Please try again.", "Error");
        } else {
            school.requestStudentData(Integer.parseInt(s.getText()));
            new StudentDashboardForm(stage, school.receiveStudent(), school);
        }
    }

    private void onBackButtonClick(Stage stage) {
        stage.close();
    }
}
