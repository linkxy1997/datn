import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

export class AuthenticationInterceptor implements HttpInterceptor{
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        let currentUser = JSON.parse(localStorage.getItem('AuthenticationToken'));
        if (currentUser && currentUser.authenticationToken) {
            request = request.clone({
                setHeaders: { 
                    Authorization: `${currentUser.authenticationToken}`
                }
            });
        }

        return next.handle(request);
    }
}
