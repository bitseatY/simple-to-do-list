
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


public class Task  {
    private  String taskContent;
    private   LocalDateTime dueTime;
    public  enum State{PENDING, inPROGRESS,COMPLETED,OVERDUE,DELETED}
    private State state;
    private    List<Task> subTasks;


    public static class Memento{
        private final String taskContent;
        private final   LocalDateTime dueTime;
        private  final   State state;
        private final    List<Memento> subTaskMementos;
        //private Task lastSubTask;
        public Memento(String taskContent,LocalDateTime dueTime,State state,List<Memento> subTaskMementos){
            this.dueTime=dueTime;
            this.taskContent=taskContent;
            this.state=state;
            this.subTaskMementos=subTaskMementos;

        }

    }

   public Memento saveState() {
       List<Memento> subTaskMementos = new ArrayList<>();
       if (this.subTasks != null) {
           for (Task sub : this.subTasks) {                 //capture  task and all its subtasks
               subTaskMementos.add(sub.saveState());
           }
       }
       return new Memento(taskContent, dueTime, state, subTaskMementos);
   }

   public void  restore(Memento memento){
        this.setTaskContent(memento.taskContent);                //restore state from top to bottom
        this.setDueTime(memento.dueTime);
        this.setState(memento.state);
        this.subTasks.clear();
        for(Memento m: memento.subTaskMementos){
             Task subTask=new Task(m.taskContent,m.dueTime);
             subTask.restore(m);
             subTasks.add(subTask);
        }




   }







    public Task(String taskContent,LocalDateTime dueTime){
        this.dueTime =dueTime;
        this.taskContent=taskContent;
        state=State.PENDING;

    }
    public  boolean hasSubtasks(){
        return subTasks!=null;
    }



    public List<Task> getSubTasks(){

        return  subTasks;
    }


    public void addSubTask(Task task){
        if(subTasks==null){
             subTasks=new ArrayList<>();
        }

        subTasks.add(task);
        Mode.sort(subTasks);
    }

    public void  removeSubTask(Task subTask){
            subTasks.remove(subTask);
    }


    public void setState(State state) {
        this.state = state;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }



    public String getTaskContent() {
        return taskContent;
    }

    public State getState() {
        return state;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }


    @Override
    public String toString() {
        return taskContent;
    }
}
