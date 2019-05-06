import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { Constant } from '../entities/constant';
import { UserAccount } from '../entities/user-account'

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  apiUrl: string = Constant.API_URL + '/login'
  constructor(private httpClient: HttpClient) { }

  login(userAccount: UserAccount): Observable<HttpResponse<UserAccount>> {
    let data = new HttpParams()
      .append('ledStt', userAccount.username);

    return this.httpClient.post<UserAccount>(`${this.apiUrl}`, userAccount,
      {
        headers: this.httpOptions(),
        observe: 'response'
      }).pipe(tap(resp => {
        //sessionStorage.setItem('AuthenticationToken', resp.body.authenticationToken);
        localStorage.setItem('AuthenticationToken', resp.body.authenticationToken)
      }));
  }
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('AuthenticationToken');
  }
  private httpOptions() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8'
    })
    return headers;
  }
}