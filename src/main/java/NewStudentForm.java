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

public class NewStudentForm {

    public NewStudentForm(Stage stage, School school) {

        BorderPane pane = new BorderPane();

        VBox vbox1 = new VBox();
        HBox hbox1 = new HBox(20);
        HBox hbox2 = new HBox(20);
        HBox hbox3 = new HBox(20);
        HBox hbox4 = new HBox(20);
        HBox hbox5 = new HBox(20);

        hbox1.setPadding(new Insets(20, 20, 20, 20));
        hbox2.setPadding(new Insets(20, 20, 20, 20));
        hbox4.setPadding(new Insets(20, 0, 0, 20));
        hbox3.setPadding(new Insets(20, 0, 0, 20));
        hbox5.setPadding(new Insets(30, 0, 0, 0));

        Label instructions = new Label("Please fill out the fields below and then click SUBMIT to be added to the student " +
                "database.");

        hbox4.getChildren().add(instructions);
        hbox4.setAlignment(Pos.TOP_CENTER);

        Label firstName = new Label("First Name: ");
        TextField firstNameField = new TextField();

        Label middleName = new Label("Middle Name ");
        TextField middleNameField = new TextField();

        Label lastName = new Label("Last Name: ");
        TextField lastNameField = new TextField();

        Label address = new Label("Address: ");
        TextField addressField = new TextField();

        Label city = new Label("City: ");
        TextField cityField = new TextField();

        Label state = new Label("State: ");
        TextField stateField = new TextField();
        stateField.setMaxWidth(40);

        Label zipCode = new Label("Zip Code: ");
        TextField zipCodeField = new TextField();
        zipCodeField.setMaxWidth(80);

        Label emailAddress = new Label("Email Address: ");
        TextField emailField = new TextField();
        emailField.setMaxWidth(150);

        Label studentIdPrefix = new Label("Your student ID is: ");

        Label studentId = new Label("     ");
        studentId.setMaxWidth(80);

        Button generateButton = new Button("Generate");
        generateButton.setOnAction(e -> onGenerateButtonClick(studentId, school));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> onSubmitButtonClick(firstNameField, middleNameField, lastNameField, addressField, cityField,
                    stateField, zipCodeField, emailField, school));

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> onResetButtonClick(firstNameField, middleNameField, lastNameField, addressField, cityField,
                    stateField, zipCodeField, emailField, studentId));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onBackButtonClick(stage, school));

        hbox3.getChildren().addAll(emailAddress, emailField, studentIdPrefix, studentId, generateButton);
        hbox3.setAlignment(Pos.CENTER);

        hbox1.getChildren().addAll(firstName, firstNameField, middleName, middleNameField,
                lastName,lastNameField);
        hbox1.setAlignment(Pos.CENTER);

        hbox2.getChildren().addAll(address, addressField, city, cityField, state, stateField,
                zipCode, zipCodeField);
        hbox2.setAlignment(Pos.CENTER);

        hbox5.getChildren().addAll(submitButton, resetButton, backButton);
        hbox5.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(hbox4,hbox1,hbox2, hbox3, hbox5);

        pane.setCenter(vbox1);
        pane.setId("mainbox");

        Scene scene = new Scene(pane, 800, 300);
        scene.getStylesheets().add("client_css.css");

        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void onGenerateButtonClick(Label a, School school) {
        a.setText(school.getPrelimStudentNum());
    }

    private void onResetButtonClick(TextField fn, TextField mn, TextField ln, TextField add, TextField city,
                                           TextField st, TextField zip, TextField em, Label si) {
        fn.clear();
        mn.clear();
        ln.clear();
        add.clear();
        city.clear();
        st.clear();
        zip.clear();
        em.clear();
        si.setText("     ");
    }

    private void onSubmitButtonClick(TextField fn, TextField mn, TextField ln, TextField add, TextField city,
                                            TextField st, TextField zip, TextField em, School school) {
        if (fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty() || add.getText().isEmpty() ||
                city.getText().isEmpty() || st.getText().isEmpty() || zip.getText().isEmpty() ||
                em.getText().isEmpty()) {

            new MessageBox().show("Please make sure you fill in all fields.", "ERROR: Empty Fields");
        }  else if (st.getText().length() > 2) {
            new MessageBox().show("Use the abbreviation for your state (e.g. VA, MD, etc.)"
                    , "ERROR: Too many characters");
        } else {
            Student s = new Student(fn.getText(), mn.getText(), ln.getText(), add.getText(), city.getText(),
                    st.getText(), Integer.parseInt(zip.getText()), em.getText(), school.incrementStudentNum());
            school.transmitStudent(s);

            onResetButtonClick(fn, mn, ln, add, city, st, zip, em, new Label());
        }
    }

    private void onBackButtonClick(Stage stage, School school) {
        new QueryForm(stage, school);
    }
}
