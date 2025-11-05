import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task {
    private  String taskContent;

    private   LocalDateTime dueTime;
    public  enum State{DONE,UNDONE};
    private State state;

    public Task(String taskContent,LocalDateTime dueTime){
        this.dueTime =dueTime;
        this.taskContent=taskContent;
        state=State.UNDONE;




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
