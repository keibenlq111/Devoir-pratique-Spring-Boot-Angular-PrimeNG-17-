import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Experimentation } from '../models/experimentation.model';

@Injectable({ providedIn: 'root' })
export class ExperimentationService {
  private readonly baseUrl = 'http://localhost:8080/api/experimentations';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Experimentation[]> {
    return this.http.get<Experimentation[]>(this.baseUrl);
  }

  getById(id: number): Observable<Experimentation> {
    return this.http.get<Experimentation>(`${this.baseUrl}/${id}`);
  }

  create(experimentation: Partial<Experimentation>): Observable<Experimentation> {
    return this.http.post<Experimentation>(this.baseUrl, experimentation);
  }

  update(id: number, experimentation: Partial<Experimentation>): Observable<Experimentation> {
    return this.http.put<Experimentation>(`${this.baseUrl}/${id}`, experimentation);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
