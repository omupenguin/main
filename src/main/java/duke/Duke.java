package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;


/**
 * <h1>duke.Duke</h1>
 * duke.Duke is a chat-bot styled todo_list manager.
 *
 * @author  Aik Peng
 * @version 1.0
 * @since   2019-07-26
 */
public class Duke extends Application {

    private TaskList tasks;

    // Javafx variables for Ui
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates an instance of duke.Duke using a task list loaded from /data/duke.txt
     */
    public Duke() {
        tasks = new TaskList(Storage.load());
    }

    /**
     * Runs the main program of duke.Duke
     * @throws Exception when exceptional condition happens
     */
    public void run() throws Exception {
        Reminder reminderObject = new Reminder(); //reminder pop up
        reminderObject.execute(tasks);
        boolean isExit = false;
        Scanner input = new Scanner(System.in); // TODO: Add to duke.Ui instead?
        while (isExit == false) {
            if (input.hasNextLine()) {
                String inputLine = input.nextLine();
                if (inputLine.equals("bye")) {
                    isExit = true;
                    Parser.exit();
                } else {
                    Command c = Parser.handleInput(inputLine, tasks);
                    c.execute(tasks);
                    Storage.save(tasks.get());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception { // Exception needs to be handled?
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2. Styling
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
    }
}
