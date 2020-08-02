import javafx.application.Application;
import javafx.stage.Stage;

public class Bridge extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ConnectForm(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
