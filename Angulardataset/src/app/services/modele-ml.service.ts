import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ModeleML } from '../models/modele-ml.model';

@Injectable({ providedIn: 'root' })
export class ModeleMLService {
  private readonly baseUrl = 'http://localhost:8080/api/modeles';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ModeleML[]> {
    return this.http.get<ModeleML[]>(this.baseUrl);
  }

  getById(id: number): Observable<ModeleML> {
    return this.http.get<ModeleML>(`${this.baseUrl}/${id}`);
  }

  create(modele: Partial<ModeleML>): Observable<ModeleML> {
    return this.http.post<ModeleML>(this.baseUrl, modele);
  }

  update(id: number, modele: Partial<ModeleML>): Observable<ModeleML> {
    return this.http.put<ModeleML>(`${this.baseUrl}/${id}`, modele);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
