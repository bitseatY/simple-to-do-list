import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public interface Mode {
     DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm a");
     ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(2);

    static  void setTimer(Task task){
        try {
            long delay= Duration.between(LocalDateTime.now(),task.getDueTime().minusMinutes(30)).toMillis();  //30 min before due time
            scheduler.schedule(()->{              //background thread that waits delay amount of time to execute command
                if(task.getState()== Task.State.PENDING) {
                    System.out.printf("task: %s  due time is within 30 minutes", task.getTaskContent());
                    task.setState(Task.State.OVERDUE);
                }
            },delay, TimeUnit.SECONDS);

            scheduler.schedule(Mode::closeTimer,delay+60,TimeUnit.SECONDS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static  void closeTimer(){
        scheduler.shutdown();
    }
    static void   sort(List<Task> tasks){
       tasks.sort(Comparator.comparing(Task::getDueTime));
    }

    default void addSubTask(Task task,Task subTask){
        task.addSubTask(subTask);
        Mode.setTimer(subTask);
    }
    default   void    removeSubTask(Task task,Task subTask){
        task.removeSubTask(subTask);
    }



    void addTask(Task task);

    void checkOffTask(Task task);
    void removeTask(Task task);
    void postPoneTask(Task task,LocalDateTime newDueTime);


}
