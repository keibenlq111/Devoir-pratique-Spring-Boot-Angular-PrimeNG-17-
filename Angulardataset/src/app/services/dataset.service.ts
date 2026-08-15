import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dataset } from '../models/dataset.model';

@Injectable({ providedIn: 'root' })
export class DatasetService {
  private readonly baseUrl = 'http://localhost:8080/api/datasets';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Dataset[]> {
    return this.http.get<Dataset[]>(this.baseUrl);
  }

  getById(id: number): Observable<Dataset> {
    return this.http.get<Dataset>(`${this.baseUrl}/${id}`);
  }

  create(dataset: Partial<Dataset>): Observable<Dataset> {
    return this.http.post<Dataset>(this.baseUrl, dataset);
  }

  update(id: number, dataset: Partial<Dataset>): Observable<Dataset> {
    return this.http.put<Dataset>(`${this.baseUrl}/${id}`, dataset);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
