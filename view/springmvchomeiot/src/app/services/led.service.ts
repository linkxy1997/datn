import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Led } from '../entities/led';
import { Constant } from '../entities/constant';
@Injectable({
  providedIn: 'root'
})
export class LedService {
  apiURL: string = Constant.apiUrl + '/led';
  constructor(private httpClient: HttpClient) { }
  private httpOptions() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8'
    })
    return headers;
  }
  addLed(led: Led): Observable<HttpResponse<Led>> {
    let data = new HttpParams()
      .append('ledStt', led.ledStt.toString());

    return this.httpClient.post<Led>(`${this.apiURL}/save`, led,
      {
        headers: this.httpOptions(),
        observe: 'response'
      });
  }
}
