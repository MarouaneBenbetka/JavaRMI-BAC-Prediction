package app.bacgradesprediction;
import app.bacgradesprediction.models.StudentRecord;
import app.bacgradesprediction.utils.TabularDataReader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class TrainingPage extends VBox {
    private TextField schoolNameInput;
    private Button submitButton, chooseFileButton, clearFileButton;
    private Label fileNameLabel, warningLabel;
    private FileChooser fileChooser;
    private File selectedFile;
    private TabularDataReader tabularDataReader = new TabularDataReader();
    public List<StudentRecord> studentRecords ;

    public TrainingPage() {
        initUI();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void initUI() {
        schoolNameInput = new TextField();
        schoolNameInput.setPromptText("Enter School Name");
        schoolNameInput.getStyleClass().add("text-field");

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv" , "*.txt"),
                new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx")
        );

        chooseFileButton = new Button("Choose File");
        chooseFileButton.getStyleClass().add("button");
        clearFileButton = new Button("Clear");
        clearFileButton.getStyleClass().add("button");
        clearFileButton.setDisable(true);

        fileNameLabel = new Label("No file selected");
        fileNameLabel.getStyleClass().add("file-name-label");
        fileNameLabel.setVisible(false);  // Initially not visible

        chooseFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                fileNameLabel.setText(selectedFile.getName());
                fileNameLabel.setVisible(true);
                clearFileButton.setDisable(false);
            }
        });

        clearFileButton.setOnAction(e -> {
            selectedFile = null;
            fileNameLabel.setVisible(false);
            clearFileButton.setDisable(true);
        });

        warningLabel = new Label();
        warningLabel.getStyleClass().add("warning-label");
        warningLabel.setVisible(false);  // Initially hidden

        submitButton = new Button("Submit");
        submitButton.getStyleClass().add("button");
        submitButton.setOnAction(e -> {
            if (selectedFile == null) {
                warningLabel.setText("No file selected");
                warningLabel.setVisible(true);
            } else {
                System.out.println("TEST BEFORE CRASH");
                System.out.println(selectedFile.getPath());


                try {
                    studentRecords = tabularDataReader.readTabularData(selectedFile.getPath()) ;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println(studentRecords);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Training Complete");
                alert.setHeaderText(null);
                alert.setContentText("We have finished the training!");
                alert.showAndWait();
                warningLabel.setVisible(false);
            }
        });

        HBox fileSelectionBox = new HBox(10);
        fileSelectionBox.getChildren().addAll(chooseFileButton, clearFileButton);

        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(schoolNameInput, fileSelectionBox, submitButton, fileNameLabel, warningLabel);
    }
}
