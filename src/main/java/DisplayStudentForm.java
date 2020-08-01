import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DisplayStudentForm {

    VBox mainVbox;

    public DisplayStudentForm(Student student, School school) {
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

        Label instructions = new Label("Please review the information below.  If anything is incorrect, please " +
                "click \"Edit\" and then click \"Submit\"");

        hbox1.getChildren().add(instructions);
        hbox1.setAlignment(Pos.TOP_CENTER);

        TextField statusLabel = new TextField(student.getStatus());

        Label firstName = new Label("First Name: ");
        TextField firstNameField = new TextField(student.getFirstName());
        firstNameField.setEditable(false);

        Label middleName = new Label("Middle Name ");
        TextField middleNameField = new TextField(student.getMiddleName());
        middleNameField.setEditable(false);

        Label lastName = new Label("Last Name: ");
        TextField lastNameField = new TextField(student.getLastName());
        lastNameField.setEditable(false);

        Label address = new Label("Address: ");
        TextField addressField = new TextField(student.getAddress());
        addressField.setEditable(false);

        Label city = new Label("City: ");
        TextField cityField = new TextField(student.getCity());
        cityField.setEditable(false);

        Label state = new Label("State: ");
        TextField stateField = new TextField(student.getState());
        stateField.setEditable(false);
        stateField.setMaxWidth(40);

        Label zipCode = new Label("Zip Code: ");
        TextField zipCodeField = new TextField(Integer.toString(student.getZipCode()));
        zipCodeField.setMaxWidth(80);
        zipCodeField.setEditable(false);

        Label emailAddress = new Label("Email Address: ");
        TextField emailField = new TextField(student.getEmail());
        emailField.setMaxWidth(150);
        emailField.setEditable(false);

        Label studentIdPrefix = new Label("Your student ID is: ");
        Label studentId = new Label(student.reformatStudentNum());
        studentId.setMaxWidth(80);

        Button submitButton = new Button("Submit");
        submitButton.setDisable(true);
        submitButton.setOnAction(e -> {
            onSubmitButtonClick(school, student, firstNameField, middleNameField, lastNameField, addressField, cityField,
                    stateField, zipCodeField, emailField);
        });

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> onEditButtonClick(firstNameField, middleNameField, lastNameField,
                addressField, cityField, stateField, zipCodeField, emailField, submitButton));

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> onResetButtonClick(student, firstNameField, middleNameField, lastNameField,
                addressField, cityField, stateField, zipCodeField, emailField));

        hbox2.getChildren().addAll(firstName, firstNameField, middleName, middleNameField,
                lastName,lastNameField);
        hbox2.setAlignment(Pos.CENTER);

        hbox3.getChildren().addAll(address, addressField, city, cityField, state, stateField,
                zipCode, zipCodeField);
        hbox3.setAlignment(Pos.CENTER);

        hbox4.getChildren().addAll(emailAddress, emailField, studentIdPrefix, studentId);
        hbox4.setAlignment(Pos.CENTER);

        hbox5.getChildren().addAll(submitButton, editButton, resetButton);
        hbox5.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(hbox1,hbox2,hbox3, hbox4, hbox5);

        mainVbox = vbox1;
        mainVbox.setId("mainbox");
    }

    private void onSubmitButtonClick(School school, Student student, TextField fn, TextField mn,
                                     TextField ln, TextField add, TextField city,
                                     TextField state, TextField zip, TextField email) {
        if (fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty() || add.getText().isEmpty() ||
                city.getText().isEmpty() || state.getText().isEmpty() || zip.getText().isEmpty() ||
                email.getText().isEmpty()) {

            new MessageBox("Please make sure you fill in all fields.", "ERROR: Empty Fields");
        }  else if (state.getText().length() > 2) {
            new MessageBox("Use the abbreviation for your state (e.g. VA, MD, etc.)"
                    , "ERROR: Too many characters");
        } else {
            student.updateStudent(fn.getText(), mn.getText(), ln.getText(), add.getText(),
                    city.getText(), state.getText(), Integer.parseInt(zip.getText()), email.getText());
            school.transmitStudent(student);
        }
    }

    private void onEditButtonClick(TextField fn, TextField mn, TextField ln, TextField add, TextField city,
                                   TextField state, TextField zip, TextField email, Button sb) {
        fn.setEditable(true);
        mn.setEditable(true);
        ln.setEditable(true);
        add.setEditable(true);
        city.setEditable(true);
        state.setEditable(true);
        zip.setEditable(true);
        email.setEditable(true);

        String fnString = fn.getText();

        while (true) {
            if (fn.getText() != fnString) {
                sb.setDisable(false);
                break;
            }
        }
    }

    private void onResetButtonClick(Student student, TextField fn, TextField mn, TextField ln,
                                    TextField add, TextField city, TextField state,
                                    TextField zip, TextField email) {
        fn.setText(student.getFirstName());
        mn.setText(student.getMiddleName());
        ln.setText(student.getLastName());
        add.setText(student.getAddress());
        city.setText(student.getCity());
        state.setText(student.getState());
        zip.setText(Integer.toString(student.getZipCode()));
        email.setText(student.getEmail());

        fn.setEditable(false);
        mn.setEditable(false);
        ln.setEditable(false);
        add.setEditable(false);
        city.setEditable(false);
        state.setEditable(false);
        zip.setEditable(false);
        email.setEditable(false);
    }

    public VBox getVbox() {
        return mainVbox;
    }
}
