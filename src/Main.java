import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
         TaskManager.addTask("playing",LocalDateTime.now().plusHours(3));
         TaskManager.addTask("writing poem",LocalDateTime.now().plusHours(2));
         TaskManager.viewTasks();

    }
}