package vibes.task.type;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    private final static String TASK_TYPE = "T";
    public static final String PRINT_FORMAT = "[T]%s";

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, super.toString());
    }
}
