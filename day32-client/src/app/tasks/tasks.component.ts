import { Component, OnInit } from '@angular/core';

import { TasksService } from '../service/tasks.service';
import { Task } from '../task-model';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private tSvc: TasksService) { }

  ngOnInit(): void {
    this.getAllTasks();
  }

  getAllTasks() {
    this.tSvc.getAllTasks()
      .then(t => this.tasks = t);
  }

}
