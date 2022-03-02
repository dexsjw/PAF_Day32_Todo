package ibf2021.paf.day32.repository;

public class SQL {

    public static final String SQL_CHECK_USERNAME_FROM_USER = "select count(*) as count from user where username = ?";
    public static final String SQL_GET_ALL_TASKS_FROM_TASKS = "select * from tasks order by username";
    public static final String SQL_INSERT_INTO_TASKS = 
        "insert into tasks (username, task_name, priority, due_date) values (?, ?, ?, ?)";
    
}
