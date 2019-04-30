import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { AutomationState } from '../entities/automation-state';
import { Constant } from '../entities/constant';

@Injectable({
  providedIn: 'root'
})
export class AutomationStateService {
  url: string = Constant.API_URL + '/automation';
  constructor(private httpClient: HttpClient) { }
  private httpOptions() {
    let token = localStorage.getItem('AuthenticationToken');
    const au=token.split('"');
    let headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization':token
    })
    return headers;
  }
  updateAutomationState(automationState: AutomationState): Observable<HttpResponse<AutomationState>> {
    return this.httpClient.post<AutomationState>(`${this.url}/setState`, automationState,
      {
        headers: this.httpOptions(),
        observe: 'response'
      });
  }
  getAutomationState(): Observable<HttpResponse<AutomationState>> {
    return this.httpClient.get<AutomationState>(
      `${this.url}/getState`, 
      { 
        observe: 'response',
        headers:this.httpOptions()
      }
      );
  }
}
