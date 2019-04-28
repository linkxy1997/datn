import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { ApiService } from '../services/api.service';
import { LedService } from '../services/led.service';
import { AirConditionerService } from '../services/air-conditioner.service';
import { AutomationStateService } from '../services/automation-state.service';

import { Led } from '../entities/led';
import { States } from "../entities/states";
import { AirConditioner } from '../entities/air-conditioner';
import { AutomationState } from '../entities/automation-state'
declare var jquery: any;
declare var $: any;

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css', '../app.component.css']
})
export class ContactsComponent implements OnInit {
  automationState: AutomationState;
  states: States;
  static ledServices: LedService;
  constructor(private apiService: ApiService
    , private ledService: LedService
    , private airConditionerService: AirConditionerService
    , private automationStateService: AutomationStateService) {
  }

  ngOnInit() {
    this.setAutomationButton();
    this.setLedButton();
    this.setAirButton();
    setInterval(() => {
      this.getStates();
    }, 2000);
    $(document).ready(() => {
      $('#toggle-event').change(() => {
        if(this.automationState.automationState == 1){
          this.controllLed();
        }
        else{
          $('#modal-body').html('You are actived Automation State');
          $('#exampleModal').modal('show')
        }
      })
      $('#toggle-air').change(() => {
        if(this.automationState.automationState == 1){
          this.controllAirConditioner();
        }
        else{
          $('#modal-body').html('You are actived Automation State');
          $('#exampleModal').modal('show')
        }
      })
      $('#toggle-auto').change(() => {
        this.controllAutomationState();
      })

    })
  }
  setLedButton() {
    this.apiService.getStatesResponse().subscribe(resp => {
      this.states = { ...resp.body }
      if (this.states.ledStt == 1) {
        $('#toggle-event').bootstrapToggle('on');
      }
    })
  }
  setAirButton() {
    this.apiService.getStatesResponse().subscribe(resp => {
      this.states = { ...resp.body }
      if (this.states.airStt == 1) {
        $('#toggle-air').bootstrapToggle('on');
      }
    })
  }
  setAutomationButton() {
    this.automationStateService.getAutomationState().subscribe(resp => {
      this.automationState = resp.body 
      if (this.automationState.automationState == 1) {
        $('#toggle-auto').bootstrapToggle('on');
      }
    })
  }
  getStates() {
    this.apiService.getStatesResponse().subscribe(resp => {
      this.states = { ...resp.body }

    })
  }
  public controllLed() {
    let stt = $('#toggle-event').prop('checked');
    console.log(stt == true);
    let ledStt = 0;
    if (stt == true) {
      ledStt = 1;
    }
    let led = new Led(ledStt, 1);
    console.log(led.ledStt.toString());
    this.ledService.addLed(led).subscribe(res => {
      let led: Led = res.body;
      console.log(led);
      console.log(res.headers.keys());
    }, (err: HttpErrorResponse) => {
      if (err.error instanceof Error) {
        //A client-side or network error occurred.				 
        console.log('An error occurred:', err.error.message);
      } else {
        //Backend returns unsuccessful response codes such as 404, 500 etc.				 
        console.log('Backend returned status code: ', err.status);
        console.log('Response body:', err.error);
      }
    }
    );
  }
  public controllAirConditioner() {
    let stt = $('#toggle-air').prop('checked');
    let airStt = 0;
    if (stt == true) {
      airStt = 1;
    }
    let airConditioner = new AirConditioner(1, airStt);
    console.log(airConditioner);
    this.airConditionerService.controllAirConditioner(airConditioner).subscribe(res => {
      let airConditioner: AirConditioner = res.body;
      console.log(airConditioner);
      console.log(res.headers.get('Content-Type'));
    },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          //A client-side or network error occurred.				 
          console.log('An error occurred:', err.error.message);
        } else {
          //Backend returns unsuccessful response codes such as 404, 500 etc.				 
          console.log('Backend returned status code: ', err.status);
          console.log('Response body:', err.error);
        }
      }
    );
  }
  public controllAutomationState() {
    let stt = $('#toggle-auto').prop('checked');
    let automationStt = 0;
    if (stt == true) {
      automationStt = 1;
    }
    let automationState = new AutomationState(1, automationStt);
    console.log(automationState);
    this.automationStateService.updateAutomationState(automationState).subscribe(res => {
      this.automationState = res.body;
      console.log(this.automationState);
      console.log(res.headers.get('Content-Type'));
    },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          //A client-side or network error occurred.				 
          console.log('An error occurred:', err.error.message);
        } else {
          //Backend returns unsuccessful response codes such as 404, 500 etc.				 
          console.log('Backend returned status code: ', err.status);
          console.log('Response body:', err.error);
        }
      }
    );
  }
}
