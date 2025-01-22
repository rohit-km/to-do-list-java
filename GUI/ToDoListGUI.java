
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return title + (isCompleted ? " (Completed)" : "");
    }
}

public class ToDoListGUI extends Application {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List Application");

        // Task list view
        ListView<Task> taskListView = new ListView<>(tasks);

        // Input field and buttons
        TextField taskInputField = new TextField();
        taskInputField.setPromptText("Enter task title");
        Button addButton = new Button("Add Task");
        Button markCompleteButton = new Button("Mark as Completed");
        Button deleteButton = new Button("Delete Task");

        // Event handlers
        addButton.setOnAction(e -> {
            String title = taskInputField.getText().trim();
            if (!title.isEmpty()) {
                tasks.add(new Task(title));
                taskInputField.clear();
            } else {
                showAlert("Error", "Task title cannot be empty.");
            }
        });

        markCompleteButton.setOnAction(e -> {
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                selectedTask.markAsCompleted();
                taskListView.refresh();
            } else {
                showAlert("Error", "Please select a task to mark as completed.");
            }
        });

        deleteButton.setOnAction(e -> {
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                tasks.remove(selectedTask);
            } else {
                showAlert("Error", "Please select a task to delete.");
            }
        });

        // Layout
        HBox inputArea = new HBox(10, taskInputField, addButton);
        inputArea.setPadding(new Insets(10));

        HBox buttonArea = new HBox(10, markCompleteButton, deleteButton);
        buttonArea.setPadding(new Insets(10));

        VBox layout = new VBox(10, taskListView, inputArea, buttonArea);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
