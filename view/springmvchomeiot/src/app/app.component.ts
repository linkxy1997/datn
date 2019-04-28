import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { LedService } from './services/led.service';
import { HttpClient } from '@angular/common/http';

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
  constructor(private apiService: ApiService, private ledService: LedService) {

  }
  ngOnInit() {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getStates();
    // setInterval(() => {
    //   this.getStates(); 
    //   }, 2000);
  }

  private getStates() {
    // this.apiService.getData().subscribe(data=>{
    //   var states = new States;
    //   // states.ledStt=data.ledStt;
    //   // states.humidity=data.humidity;
    //   // states.lightDependent=data.lightDependent;
    //   // states.raintStatus=data.raintStatus;
    //   // states.temperature=data.temperature;
    //   states = <States> data;
    //   this.states=states;
    //   console.log(this.states);
    // })
    this.apiService.getStatesResponse().subscribe(resp => {
      this.states = { ...resp.body }
    })
  }
  
}
