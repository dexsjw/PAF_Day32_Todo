package ibf2021.paf.day32.repository;

import static ibf2021.paf.day32.repository.SQL.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.paf.day32.model.Task;

@Repository
public class TodosRepo {
    
    @Autowired
    private JdbcTemplate template;

    public boolean checkUser(String username) {
        final SqlRowSet rs = template.queryForRowSet(SQL_CHECK_USERNAME_FROM_USER, username);
        if (rs.next()) {
            int count = rs.getInt("count");
            if (count == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public List<Task> getAllTasks() {
        final List<Task> tasks = new ArrayList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_TASKS_FROM_TASKS);
        while (rs.next()) {
            final Task task = Task.populate(rs);
            tasks.add(task);
        }
        return tasks;
    }

    public int insertTask(Task task) {
        final String username = task.getUsername();
        final String taskName = task.getTaskName();
        final String priority = task.getPriority();
        final Date dueDate = task.getDueDate();
        final int row = template.update(SQL_INSERT_INTO_TASKS, username, taskName, priority, dueDate);
        return row;
    }

}
