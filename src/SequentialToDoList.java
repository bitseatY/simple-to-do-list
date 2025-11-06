import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SequentialToDoList implements  Mode {
   private  final List<Task> toDoList=new ArrayList<>();

    public  void addTask(Task task){
          toDoList.add(task);
          Mode.sort(toDoList);
          Mode.setTimer(task);
   }

   public void checkOffTask(Task task) {
          int index=toDoList.indexOf(task);
          if(index>0){
             if(toDoList.get(index-1).getState()!= Task.State.COMPLETED){
                 System.out.println("previous task must be completed first.");
                 return;
             }
          }
          if(task.hasSubtasks()){
              if(task.getLastSubTask().getState()!= Task.State.COMPLETED){
                  System.out.println("last subtask must be completed to cross off task.");
                  return;
              }
          }
          task.setState(Task.State.COMPLETED);

    }

    @Override
    public void postPoneTask(Task task, LocalDateTime newDueTime) {
        task.setDueTime(newDueTime);
        Mode.sort(toDoList);
    }

    @Override
    public void removeTask(Task task) {
         toDoList.remove(task);
    }
}
