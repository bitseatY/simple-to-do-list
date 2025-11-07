import java.time.LocalDateTime;
import java.util.*;

public class NormalToDoList  implements  Mode{
    private  final  ArrayList<Task> toDoList=new ArrayList<>();

    public   Iterator<Task> getTask(){
        return toDoList.iterator();

    }
  public  void addTask(Task task){
         toDoList.add(task);
         Mode.sort(toDoList);
         Mode.setTimer(task);

  }
  public void checkOffTask(Task task) {    //even if we're not forcing sequence , subtasks must be completed before checking Off task
      if (task.hasSubtasks()) {
          List<Task> subTasks=task.getSubTasks();
          for(Task sub: subTasks){
              if (sub.getState() != Task.State.COMPLETED) {
                  System.out.println("sub tasks must be completed first to check off task.");
                  return;
              }
          }
      }
      task.setState(Task.State.COMPLETED);
  }

  public   void postPoneTask(Task task,LocalDateTime newDueTime) {
      task.setDueTime(newDueTime);
      Mode.sort(toDoList);
  }

  public   void viewTasks() {       //show only undone&& half completed tasks
      System.out.printf("%-3s%-15s%25s%n", "", "Task", "Due Time");
      List<Task> undoneTasks = toDoList.stream().filter(task -> task.getState() == Task.State.PENDING || task.getState() == Task.State.inPROGRESS).toList();

      for (int i = 0; i < undoneTasks.size(); i++) {
          System.out.printf("%-3d%-20s%25s%n", (i + 1), undoneTasks.get(i).getTaskContent(),
                  undoneTasks.get(i).getDueTime().format(formatter));
      }

  }

  public static void viewSubTasks(Task task) {

  }














}
