package app.bacgradesprediction;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PredictPage extends VBox {
    private TextField grade1Input, grade2Input, grade3Input;
    private Button submitButton;
    private Label resultLabel;

    public PredictPage() {
        initUI();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void initUI() {
        grade1Input = new TextField();
        grade2Input = new TextField();
        grade3Input = new TextField();

        grade1Input.setPromptText("Enter grade 1 (0-20)");
        grade2Input.setPromptText("Enter grade 2 (0-20)");
        grade3Input.setPromptText("Enter grade 3 (0-20)");
        grade1Input.getStyleClass().add("text-field");
        grade2Input.getStyleClass().add("text-field");
        grade3Input.getStyleClass().add("text-field");

        submitButton = new Button("Submit");
        submitButton.getStyleClass().add("button");
        submitButton.setOnAction(e -> submitGrades());

        resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        resultLabel.setVisible(false);

        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(new Label("Enter Grades:"), grade1Input, grade2Input, grade3Input, submitButton, resultLabel);
    }

    private void submitGrades() {
        // Assuming API call or validation logic goes here
        resultLabel.setText("Predicted BAC mention: Excellent"); // Placeholder text
        resultLabel.setVisible(true);
    }
}
