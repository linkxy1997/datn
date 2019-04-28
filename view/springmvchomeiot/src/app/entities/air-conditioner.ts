export class AirConditioner {
    private airStt:number;
    private id:number;
    constructor(airConditionerId:number,airConditionerStt:number){
        this.id=airConditionerId;
        this.airStt=airConditionerStt;
    }
    public getAirConditionerStt():number{
        return this.airStt;
    }
    public getAirConditionerId():number{
        return this.id;
    }
    public setAirConditionerId(airConditionerId:number){
        this.id=airConditionerId;
    }
    public setAirConditionerStt(airConditionerStt:number){
        this.airStt=airConditionerStt;
    }
}
