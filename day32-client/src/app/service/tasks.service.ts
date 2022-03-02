import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { last, lastValueFrom } from 'rxjs';

import { Task } from '../task-model';

@Injectable()
export class TasksService {

  constructor(private http: HttpClient) { }

  getAllTasks(): Promise<Task[]> {
    return lastValueFrom(
      this.http.get<Task[]>("/api/tasks")
    )
  }

  addTask(task: Task): Promise<string> {
    return lastValueFrom(
      this.http.post<string>("/api/tasks/add", task)
    )
  }

}
