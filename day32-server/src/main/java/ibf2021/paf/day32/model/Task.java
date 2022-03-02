package ibf2021.paf.day32.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Task {
    private int tid;
    private String username;
    private String taskName;
    private String priority;
    private Date dueDate;

    public static Task create(String payload) throws IOException {
        InputStream is = new ByteArrayInputStream(payload.getBytes());
        JsonReader jr = Json.createReader(is);
        JsonObject task = jr.readObject();
        final Task newTask = new Task();
        newTask.setUsername(task.getString("username"));
        newTask.setTaskName(task.getString("taskName"));
        newTask.setPriority(task.getString("priority"));
        newTask.setDueDate(Date.valueOf(task.getString("dueDate")));
        return newTask;
    }

    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
            .add("tid", tid)
            .add("username", username)
            .add("taskName", taskName)
            .add("priority", priority)
            .add("dueDate", dueDate.toString())
            .build();
    }

    public static Task populate(SqlRowSet rs) {
        final Task task = new Task();
        task.setTid(rs.getInt("tid"));
        task.setUsername(rs.getString("username"));
        task.setTaskName(rs.getString("task_name"));
        task.setPriority(rs.getString("priority"));
        task.setDueDate(rs.getDate("due_date"));
        return task;
    }

    public int getTid() { return tid; }
    public void setTid(int tid) { this.tid = tid; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    
}
