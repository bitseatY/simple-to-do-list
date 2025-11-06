
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Task {
    private  String taskContent;
    private   LocalDateTime dueTime;
    public  enum State{PENDING, inPROGRESS,COMPLETED,OVERDUE}
    private State state;
    private    List<Task> subTasks;
    private Task lastSubTask;


    public Task(String taskContent,LocalDateTime dueTime){
        this.dueTime =dueTime;
        this.taskContent=taskContent;
        state=State.PENDING;

    }
    public  boolean hasSubtasks(){
        return subTasks!=null;
    }

    public Task getLastSubTask() {
        return lastSubTask;
    }

    public Iterator<Task> getSubTasks(){
        if(subTasks==null||subTasks.isEmpty()){
            return null;
        }
        return  subTasks.iterator();
    }


    public void addSubTask(Task task){
        if(subTasks==null){
             subTasks=new ArrayList<>();
        }
        lastSubTask=task;
        subTasks.add(lastSubTask);
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
}
