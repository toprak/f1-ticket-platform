import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TicketRequest {
  firstName: string;
  lastName: string;
  email: string;
  grandstand: string;
}

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  // Docker ağında veya localhost'ta backend API adresi
  private apiUrl = 'http://localhost:8080/api/tickets/purchase';

  constructor(private http: HttpClient) {}

  purchaseTicket(request: TicketRequest): Observable<Blob> {
    return this.http.post(this.apiUrl, request, {
      responseType: 'blob' // PDF'i binary (Blob) olarak yakalamak için kritik ayar!
    });
  }
}
