package vibes;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.task.TaskList;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Vibes {
    private static final String CHATBOT_NAME = "Vibes";
    private static final String DASHED_LINE = "\t---------------------------------------------------------------------------------------";

    private static void executeCommand(String commandToExecute, TaskList taskList, String userInput) throws CommandNotFoundException {
        int taskNumber;

        switch (commandToExecute) {
        case "list":
            taskList.listTasks();
            break;
        case "mark":
            taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
            taskList.setAsDone(taskNumber);
            break;
        case "unmark":
            taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            taskList.setAsNotDone(taskNumber);
            break;
        case "todo":
            try {
                taskList.addTask(userInput);
            } catch (InvalidArgumentException e) {
                System.out.println("\t Argument not found! The description of a todo cannot be empty.");
            }
            break;
        case "event":
            try {
                taskList.addTask(userInput);
            } catch (InvalidArgumentException e) {
                System.out.println("\t Argument not found! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                taskList.addTask(userInput);
            } catch (InvalidArgumentException e) {
                System.out.println("\t Argument not found! The description of a todo cannot be empty.");
            }
            break;
        case "delete":
            taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            taskList.deleteTask(taskNumber);
            break;
        default:
            throw new CommandNotFoundException();
        }

        try {
            taskList.writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String userInput;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        try {
            taskList.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }

        System.out.println(DASHED_LINE);
        System.out.println("\t Hello! I'm " + CHATBOT_NAME + "\n\t What can I do for you?");
        System.out.println(DASHED_LINE);


        while(!isExit){
            userInput = in.nextLine().trim();
            String commandToExecute = userInput.split(" ")[0].toLowerCase();

            System.out.println(DASHED_LINE);
            if (commandToExecute.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                isExit = true;
            } else {
                try {
                    executeCommand(commandToExecute, taskList, userInput);
                } catch (CommandNotFoundException e) {
                    System.out.println("\t Invalid Command. Please choose between: todo, deadline, event, mark, unmark, " +
                            "and bye");
                }
            }
            System.out.println(DASHED_LINE);
        }
    }
}