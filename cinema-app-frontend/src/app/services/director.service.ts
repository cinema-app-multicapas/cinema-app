import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Director } from '../models/Director';

@Injectable({
  providedIn: 'root'
})
export class DirectorService {
  private apiUrl = '/api/directors'; 

  constructor(private http: HttpClient) {}

  getDirectors(): Observable<Director[]> {
    return this.http.get<Director[]>(this.apiUrl);
  }

  getDirector(id: number): Observable<Director> {
    return this.http.get<Director>(`${this.apiUrl}/${id}`);
  }

  addDirector(director: Director): Observable<Director> {
    return this.http.post<Director>(this.apiUrl, director);
  }

  updateDirector(id: number, director: Director): Observable<Director> {
    return this.http.put<Director>(`${this.apiUrl}/${id}`, director);
  }

  deleteDirector(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Algo salió mal, por favor intenta nuevamente.';
    if (error.status === 400) {
      errorMessage = `Error: ${error.error}`;
    } else if (error.status === 404) {
      errorMessage = 'No se encontró el director.';
    } else if (error.status === 500) {
      errorMessage = 'Hubo un problema con el servidor.';
    }
    return throwError(() => new Error(errorMessage)); // Lanza el error para que se pueda manejar en el componente
  }
}