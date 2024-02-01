public class List {
    Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String description) {
        tasks[tasksCount] = new Task(description);
        tasksCount++;
        System.out.println("\t added: " + description);
    }

    public void listTasks() {
        if (tasksCount == 0) {
            System.out.println("\t You have no tasks added to the list.");
            return;
        }
        for (int i = 0; i < tasksCount; i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks[i].getDescription());
        }
    }
}
