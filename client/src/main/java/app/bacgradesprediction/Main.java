package app.bacgradesprediction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab dataUploadTab = new Tab("Data Upload");
        dataUploadTab.setContent(new DataUploadPage());
        dataUploadTab.setClosable(false);

        Tab predictingTab = new Tab("Predicting");
        predictingTab.setContent(new PredictPage());
        predictingTab.setClosable(false);

        Tab trainingTab = new Tab("Training");
        trainingTab.setContent(new TrainingPage());
        trainingTab.setClosable(false);

        tabPane.getTabs().addAll(dataUploadTab,trainingTab, predictingTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("BAC PREDICTIONS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
