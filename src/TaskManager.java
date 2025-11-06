import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskManager  {
     private static final  ArrayList<Task> toDoList=new ArrayList<>();
     public static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm a");
    private static final ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(2);



  public  static  Iterator<Task> getTask(){
      return toDoList.iterator();

  }
    public static void addTask(String taskContent, LocalDateTime dueTime){
        Task task=new Task(taskContent,dueTime);
        toDoList.add(task);
        toDoList.sort(Comparator.comparing(Task::getDueTime));
        setTimer(task);
    }

    public static  void addSubTask(Task task,String taskContent,LocalDateTime dueTime){
          Task subTask=new Task(taskContent,dueTime);
          task.addSubTask(subTask);
           setTimer(subTask);
    }





    public static  void setTimer(Task task){
        try {
            long delay= Duration.between(LocalDateTime.now(),task.getDueTime().minusMinutes(30)).toMillis();  //30 min before due time
            scheduler.schedule(()->{              //background thread that waits delay amount of time to execute command
                if(task.getState()== Task.State.PENDING) {
                    System.out.printf("task: %s  due time is within 30 minutes", task.getTaskContent());
                   task.setState(Task.State.OVERDUE);
                }
            },delay, TimeUnit.SECONDS);

            scheduler.schedule(TaskManager::closeTimer,delay+60,TimeUnit.SECONDS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static  void closeTimer(){
        scheduler.shutdown();
    }

    public static void checkOffTask(Task task){
          task.setState(Task.State.COMPLETED);       //accessing is o(1)
    }

    public static void removeTask(Task task){              //
         task.setState(Task.State.ARCHIVED);
         toDoList.remove(task);
                                                                //o(n) in the worst case
    }
    public  static  void removeSubTask(Task task,Task subTask){
           task.removeSubTask(subTask);
    }

    public static  void postPoneTask(Task task,LocalDateTime newDueTime){
           task.setDueTime(newDueTime);
          toDoList.sort(Comparator.comparing(Task::getDueTime));
    }



    public static  void viewTasks(){       //show only undone&& half completed tasks
        System.out.printf("%-3s%-15s%25s%n","","Task","Due Time");
        List<Task> undoneTasks=toDoList.stream().filter(task->task.getState()== Task.State.PENDING||task.getState()== Task.State.inPROGRESS).toList();

        for(int i=0;i< undoneTasks.size();i++){
           System.out.printf("%-3d%-20s%25s%n",(i+1),undoneTasks.get(i).getTaskContent(),
                                                         undoneTasks.get(i) .getDueTime().format(formatter));
        }

    }

    public static void viewSubTasks(Task task){
       while (task.getSubTasks().hasNext()){

           System.out.println(task.getSubTasks().next().getTaskContent());
       }

    }














}
