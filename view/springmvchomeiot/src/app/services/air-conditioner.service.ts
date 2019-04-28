import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { AirConditioner } from '../entities/air-conditioner';
import { Constant } from '../entities/constant';
@Injectable({
  providedIn: 'root'
})
export class AirConditionerService {

  constructor(private httpClient: HttpClient) { }

  private airUrl = Constant.apiUrl + '/air/postAirStatus';

  public httpOptions():HttpHeaders {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8'
    })
    return headers;
  }
  public controllAirConditioner(airConditioner:AirConditioner):Observable<HttpResponse<AirConditioner>> {
    return this.httpClient.post<AirConditioner>(`${this.airUrl}`, airConditioner,
      {
        headers: this.httpOptions(),
        observe: 'response'
      });
  }
}