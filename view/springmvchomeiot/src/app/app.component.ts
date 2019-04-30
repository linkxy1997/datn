import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { LedService } from './services/led.service';
import { AuthenticationService } from './services/authentication.service';

import { catchError, retry } from 'rxjs/operators';
import { States } from "./entities/states";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'springmvchomeiot';
  states: States;
  constructor(private authenticationService:AuthenticationService) {

  }
  ngOnInit() {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    
    //this.authenticationService.logout();
  }
  logOut(){
    this.authenticationService.logout();
  }
}
