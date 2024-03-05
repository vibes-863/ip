package vibes.task;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.parser.Parser;
import vibes.storage.Storage;
import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public void executeCommand(String commandToExecute, String userInput) throws CommandNotFoundException, InvalidArgumentException {
        switch (commandToExecute) {
        case "list":
            listTasks();
            break;
        case "mark":
            setAsDone(Parser.parseTaskNumber(userInput));
            break;
        case "unmark":
            setAsNotDone(Parser.parseTaskNumber(userInput));
            break;
        case "todo":
            addTodo(Parser.parseTodo(userInput));
            showTaskAddedMessage();
            break;
        case "event":
            addEvent(Parser.parseEvent(userInput));
            showTaskAddedMessage();
            break;
        case "deadline":
            addDeadline(Parser.parseDeadline(userInput));
            showTaskAddedMessage();
            break;
        case "delete":
            deleteTask(Parser.parseTaskNumber(userInput));
            break;
        default:
            throw new CommandNotFoundException();
        }

        try {
            Storage.writeToFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showTaskAddedMessage() {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + tasks.get(tasks.size() - 1));
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addEvent(String[] parsedInput) {
        tasks.add(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
    }

    public void addDeadline(String[] parsedInput) {
        tasks.add(new Deadline(parsedInput[0], parsedInput[1]));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
    }

    public void setAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void setAsNotDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void deleteTask(int taskNumber) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + tasks.get(taskNumber));
        tasks.remove(taskNumber);
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

}
