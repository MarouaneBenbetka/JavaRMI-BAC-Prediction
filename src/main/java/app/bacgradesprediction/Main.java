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

        Tab trainingTab = new Tab("Training");
        trainingTab.setContent(new TrainingPage());
        trainingTab.setClosable(false);

        Tab predictingTab = new Tab("Predicting");
        predictingTab.setContent(new PredictPage());
        predictingTab.setClosable(false);

        tabPane.getTabs().addAll(trainingTab, predictingTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("JavaFX Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
