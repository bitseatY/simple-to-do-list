import java.util.Stack;

public class TaskCareTaker {
     Stack<Task.Memento> mementos=new Stack<>();      //mementos for the main tasks listed in the to do list
     public void saveMemento(Task task){
        mementos.add(task.saveState());
     }
   public void  restore(Task task){
          task.restore(mementos.pop());
   }

}
