import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentDashboardForm {

    public StudentDashboardForm(Stage stage, Student student, School school) {
        BorderPane borderPane = new BorderPane();

        Hyperlink updateInfoLink = new Hyperlink("Update Information");
        updateInfoLink.setOnAction(e -> onUpdateInfoLinkClick(borderPane, student, school));
        updateInfoLink.setVisited(true);

        Hyperlink addClassLink = new Hyperlink("Add Class");
        addClassLink.setOnAction(e -> onAddClassLinkClick(borderPane, student, school));
        addClassLink.setVisited(true);

        Hyperlink dropClassLink = new Hyperlink("Drop Class");
        dropClassLink.setOnAction(e -> onDropClassLinkClick(borderPane, student, school));
        dropClassLink.setVisited(true);

        Hyperlink scheduleLink = new Hyperlink("Class Schedule");
        scheduleLink.setVisited(true);

        Hyperlink classHistoryLink = new Hyperlink("Class History");
        classHistoryLink.setVisited(true);

        Hyperlink accountSummaryLink = new Hyperlink("Account Summary");
        accountSummaryLink.setVisited(true);

        VBox vbox1 = new VBox(10);
        vbox1.setPadding(new Insets(23,10,0,10));
        vbox1.getChildren().addAll(updateInfoLink, addClassLink, dropClassLink, scheduleLink, classHistoryLink,
                accountSummaryLink);
        vbox1.setId("vbox1");

        VBox vbox2 = new VBox();
        vbox2.setId("mainbox");

        borderPane.setLeft(vbox1);
        borderPane.setCenter(vbox2);

        Scene scene = new Scene(borderPane,1000,300);
        scene.getStylesheets().add("client_css.css");

        stage.setMaxWidth(1000);
        stage.setMaxHeight(300);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    private void onUpdateInfoLinkClick(BorderPane borderPane, Student student, School school) {
        borderPane.setCenter(new DisplayStudentForm(student, school).getVbox());
    }

    private void onAddClassLinkClick(BorderPane borderPane, Student student, School school) {
        borderPane.setCenter(new DisplayClassesForm(student, school).getVbox());
    }

    private void onDropClassLinkClick(BorderPane borderPane, Student student, School school) {
        borderPane.setCenter(new StudentClassesForm(student, school).getVbox());
    }
}
