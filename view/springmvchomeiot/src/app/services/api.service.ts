import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse } from '@angular/common/http';

import {States} from "../entities/states";
import { Constant } from '../entities/constant';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL: string = Constant.apiUrl + '/states';
  constructor(private httpClient: HttpClient) { }
  private getHeaders(){
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    return headers;
  }
  getData(){
    return this.httpClient.get<States>(`${this.apiURL}/getStates`)
  }
  getStatesResponse(): Observable<HttpResponse<States>> {
    return this.httpClient.get<States>(
      `${this.apiURL}/getStates`, { observe: 'response' });
  }
}
