import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DisplayClassesForm {

    VBox mainBox;

    public DisplayClassesForm(Student student, School school) {
        Label tableLabel = new Label("Current class available:");
        tableLabel.setPadding(new Insets(10, 0,10, 10));

        ObservableList<Course> courseList = school.getCourses();

        TableView<Course> table = new TableView<>();
        table.setItems(courseList);

        TableColumn<Course, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(250);
        colName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        TableColumn<Course, Integer> colId = new TableColumn<>("ID");
        colId.setMinWidth(100);
        colId.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));

        TableColumn<Course, String> colDays = new TableColumn<>("Days");
        colDays.setMinWidth(250);
        colDays.setCellValueFactory(new PropertyValueFactory<Course, String>("days"));

        TableColumn<Course, String> colStartTime = new TableColumn<>("Start time");
        colStartTime.setMinWidth(100);
        colStartTime.setCellValueFactory(new PropertyValueFactory<Course, String>("startTime"));

        TableColumn<Course, String> colEndTime = new TableColumn<>("End time");
        colEndTime.setMinWidth(100);
        colEndTime.setCellValueFactory(new PropertyValueFactory<Course, String>("endTime"));

        table.getColumns().addAll(colName, colId, colDays, colStartTime, colEndTime);

        Button addButton = new Button("Add Class");
        addButton.setOnAction(e -> onAddButtonClick(table, student, school));

        HBox hbox1 = new HBox(10);
        hbox1.getChildren().add(addButton);
        hbox1.setPadding(new Insets(10,0,10,0));
        hbox1.setAlignment(Pos.CENTER);

        mainBox = new VBox();
        mainBox.getChildren().addAll(tableLabel, table, hbox1);
        mainBox.setId("mainbox");
    }

    public VBox getVbox() {
        return mainBox;
    }

    private void onAddButtonClick(TableView<Course> table, Student student, School school) {
        school.addCourse((Course) table.getSelectionModel().getSelectedItem(),
                student.getStudentNumber());
    }
}
