package kairos.poc.web.kairoswebpoc.pojo;

public class Points {
    private int points;
    private String tasks;
    private String time;
    public Points(int points,String tasks,String timestamp){
        this.points = points;
        this.tasks= tasks;
        this.time = timestamp;
    }
    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
