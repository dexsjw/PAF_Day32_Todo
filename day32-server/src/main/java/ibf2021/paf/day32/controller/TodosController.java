package ibf2021.paf.day32.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.paf.day32.Day32Application;
import ibf2021.paf.day32.model.Task;
import ibf2021.paf.day32.repository.TodosRepo;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api/tasks", produces=MediaType.APPLICATION_JSON_VALUE)
public class TodosController {

    Logger logger = Logger.getLogger(Day32Application.class.toString());

    @Autowired
    private TodosRepo tdr;

    @GetMapping
    public ResponseEntity<String> getAllTasks() {
        logger.info(">>>>>>> getting tasks...");
        List<Task> result = tdr.getAllTasks();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        result.stream().forEach(t -> arrBuilder.add(t.toJsonObj()));
        return ResponseEntity.ok(arrBuilder.build().toString());
    }
    
    @PostMapping(path="/add")
    public ResponseEntity<String> addTask(@RequestBody String payload) {
        logger.info(payload);
        JsonObject respMsg;
        Task newTask;
        try {
            newTask = Task.create(payload);
        } catch (IOException ioe) {
            respMsg = Json.createObjectBuilder()
                .add("error", ioe.getMessage())
                .build();
            return ResponseEntity.ok(respMsg.toString());
        }
        if (tdr.checkUser(newTask.getUsername())) {
            int row = tdr.insertTask(newTask);
            respMsg = Json.createObjectBuilder()
                .add("msg", "%d new task has been successfully added!".formatted(row))
                .build();
            return ResponseEntity.ok(respMsg.toString());
        } else {
            respMsg = Json.createObjectBuilder()
                .add("msg", "Error 401: Unauthorized Access")
                .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respMsg.toString());
        }
    }
    
}
