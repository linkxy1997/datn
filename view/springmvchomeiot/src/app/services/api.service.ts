import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';

import { States } from "../entities/states";
import { Constant } from '../entities/constant';
import { AuthenticationToken } from '../entities/authentication-token';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL: string = Constant.API_URL + '/states';
  constructor(private httpClient: HttpClient) { }
  public httpOptions(): HttpHeaders {
    let token = localStorage.getItem('AuthenticationToken');
    const au=token.split('"');
    console.log(token)
    let headers = new HttpHeaders().set('Content-Type', 'application/json')
      .set('Authorization', token);
    return headers;
  }
  getData() {
    console.log(this.httpOptions())
    return this.httpClient.get<States>(`${this.apiURL}/getStates`, { headers: this.httpOptions() })
  }
  getStatesResponse(): Observable<HttpResponse<States>> {
    return this.httpClient.get<States>(
      `${this.apiURL}/getStates`,
      {
        headers: this.httpOptions(),
        observe: 'response'
      }
    );
  }
}
