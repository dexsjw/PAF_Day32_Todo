import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TasksService } from '../service/tasks.service';

import { Task } from '../task-model';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  taskForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private tSvc: TasksService) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.taskForm = this.fb.group({
      username: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      taskName: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      priority: this.fb.control('med', [Validators.required]),
      dueDate: this.fb.control('', [Validators.required])
    })
  }

  addTask() {
    const task: Task = this.taskForm.value as Task;
    console.info(task);
    this.tSvc.addTask(task)
      .then(v => console.info(v))
      .catch(v => console.info(v));
    this.back();
  }

  back() {
    this.taskForm.reset();
    this.router.navigate(['/'])
  }

}
