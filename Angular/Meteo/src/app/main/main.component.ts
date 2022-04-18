import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private http : HttpClient) { 
  }//end constructor

  //unit system
  private metric : boolean = true;
  public unitSystem : any = "Metrický";
  //active temps
  public tempOut : any = "Načítám data prosím počkejte...";
  public tempIn : any = "Načítám data prosím počkejte...";
  //records
  public maxTempOut : any = "Načítám data prosím počkejte..."; //temp outside max record
  public maxTempOutDate : any = "Načítám data prosím počkejte..."; //date for temp outside max record
  public maxTempIn : any = "Načítám data prosím počkejte..."; //temp inside max record
  public maxTempInDate : any = "Načítám data prosím počkejte..."; //date for temp inside max record
  public maxRain : any = "Načítám data prosím počkejte..."; //max rain height
  public maxRainDate : any = "Načítám data prosím počkejte..."; //max rain heing date
  public maxWind : any = "Načítám data prosím počkejte..."; //max wind speed
  public maxWindDate : any = "Načítám data prosím počkejte..."; //max wind speed date
  public minTempOut : any = "Načítám data prosím počkejte..."; //min temp outside record
  public minTempOutDate : any = "Načítám data prosím počkejte..."; //min temp outside record date
  public minTempIn : any = "Načítám data prosím počkejte..."; //min temp inside record
  public minTempInDate : any =  "Načítám data prosím počkejte..."; //min temp inside record date

  ngOnInit(): void { //executes on initialization
    this.getTempIn(); //get temp inside
    this.getTempOut(); //get temp outside
    this.getMaxRecordTempOut(); //get record temp outside
    this.getMaxRecordTempIn(); //get record temp inside
    this.getMaxRecordRain(); //get record rain height
    this.getMaxRecordWind(); //get record wind speed
    this.getMinRecordTempIn(); //get min record temp inside
    this.getMinRecordTempOut(); //get min record temp outside
    //update both temperatures every 15 seconds
    setInterval(() => this.getTempIn(), 15000);
    setInterval(() => this.getTempOut(), 15000);
    //update records every 60 seconds
    setInterval(() => this.getMaxRecordTempOut(), 60000);
    setInterval(() => this.getMaxRecordTempIn(), 60000);
    setInterval(() => this.getMaxRecordRain(), 60000);
    setInterval(() => this.getMaxRecordWind(), 60000);
    setInterval(() => this.getMinRecordTempIn(), 60000);
    setInterval(() => this.getMaxRecordTempOut(), 60000);
  }//end function

  switchUnits(){ //switch units from celsius to fahrenheit and the other way
    if(this.metric){
      this.metric = !this.metric; //switch units
      this.unitSystem = "Imperiální";
      //update data
      this.getTempIn();
      this.getTempOut();
      this.getMaxRecordTempIn();
      this.getMaxRecordTempOut();
      this.getMaxRecordRain();
      this.getMaxRecordWind();
      this.getMinRecordTempIn();
      this.getMinRecordTempOut();
    }//end function
    else if(!this.metric){
      this.metric = !this.metric; //switch units
      this.unitSystem = "Metrický";
      //update data
      this.getTempIn();
      this.getTempOut();
      this.getMaxRecordTempIn();
      this.getMaxRecordTempOut();
      this.getMaxRecordRain();
      this.getMaxRecordWind();
      this.getMinRecordTempIn();
      this.getMinRecordTempOut();
    }//end else if
  }//end function

  getTempIn(){ //get newest temperature inside
    if(this.metric){ //get in celsius
      const url = "http://localhost:8080/api/newest/indoortempc";
      this.http.get(url).subscribe((res)=>{
        this.tempIn = res + "°C";
      });//end http get
    }//end if
    else{ //get in fahrenheit
      const url = "http://localhost:8080/api/newest/indoortempf";
      this.http.get(url).subscribe((res)=>{
        this.tempIn = res + "°F";
      });//end http get
    }//end else
    console.log(this.tempIn); //debug log
  }//end function

  getTempOut(){ //get newest temperature outside
    if(this.metric){ //get in celsius
      const url = "http://localhost:8080/api/newest/tempc";
      this.http.get(url).subscribe((res)=>{
        this.tempOut = res + "°C";
      });//end http get
    }//end if
    else{ //get in fahrenheit
      const url = "http://localhost:8080/api/newest/tempf";
      this.http.get(url).subscribe((res)=>{
        this.tempOut = res + "°F";
      });//end http get
    }//end else
    console.log(this.tempOut); //debug log
  }//end funtction

  getMaxRecordTempOut(){
    if(this.metric){//get in celsius
      const url = "http://localhost:8080/api/max/tempc"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.maxTempOut= key + "°C";
          this.maxTempOutDate = temp[key];
        }//end for
      });//end http get
    }//end if
    else{ //get in fahrenheit
      const url = "http://localhost:8080/api/max/tempf"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.maxTempOut= key + "°F";
          this.maxTempOutDate = temp[key];
        }//end for
      });//end http get
    }//end else
  }//end function

  getMaxRecordTempIn(){
    if(this.metric){//get in celsius
      const url = "http://localhost:8080/api/max/indoortempc"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.maxTempIn= key + "°C";
          this.maxTempInDate = temp[key];
        }//end for
      });//end http get
    }//end if
    else{ //get in fahrenheit
      const url = "http://localhost:8080/api/max/indoortempf"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.maxTempIn= key + "°F";
          this.maxTempInDate = temp[key];
        }//end for
      });//end http get
    }//end else
  }//end function

  getMaxRecordRain(){
    if(this.metric){//get in centimetres
      const url = "http://localhost:8080/api/max/raincm"
      this.http.get(url).subscribe((res)=>{
        let rain : any = [];
        rain = res;
        for(let key in rain){
          this.maxRain= key + "cm";
          this.maxRainDate = rain[key];
        }//end for
      });//end http get
    }//end if
    else{//get in inches
      const url = "http://localhost:8080/api/max/rainin"
      this.http.get(url).subscribe((res)=>{
        let rain : any = [];
        rain = res;
        for(let key in rain){
          this.maxRain= key + "in";
          this.maxRainDate = rain[key];
        }//end for
      });//end http get
    }//end else
  }//end function

  getMaxRecordWind(){
    if(this.metric){//get in centimetres
      const url = "http://localhost:8080/api/max/windspeedkph"
      this.http.get(url).subscribe((res)=>{
        let wind : any = [];
        wind = res;
        for(let key in wind){
          this.maxWind= key + "km/h";
          this.maxWindDate = wind[key];
        }//end for
      });//end http get
    }//end if
    else{//get in inches
      const url = "http://localhost:8080/api/max/windspeedmph"
      this.http.get(url).subscribe((res)=>{
        let wind : any = [];
        wind = res;
        for(let key in wind){
          this.maxWind= key + "mi/h";
          this.maxWindDate = wind[key];
        }//end for
      });//end http get
    }//end else
  }//end function

  getMinRecordTempIn(){
    if(this.metric){//get in celsius
      const url = "http://localhost:8080/api/min/indoortempc"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.minTempIn= key + "°C";
          this.minTempInDate = temp[key];
        }//end for
      });//end http get
    }//end if
    else{ //get in fahrenheit
      const url = "http://localhost:8080/api/min/indoortempf"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.minTempIn= key + "°F";
          this.minTempInDate = temp[key];
        }//end for
      });//end http get
    }//end else
  }//end function

  getMinRecordTempOut(){
    if(this.metric){//get in celsius
      const url = "http://localhost:8080/api/min/tempc"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.minTempOut= key + "°C";
          this.minTempOutDate = temp[key];
        }//end for
      });//end http get
    }//end if
    else{ //get in fahrenheit
      const url = "http://localhost:8080/api/min/tempf"
      this.http.get(url).subscribe((res)=>{
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.minTempOut= key + "°F";
          this.minTempOutDate = temp[key];
        }//end for
      });//end http get
    }//end else
  }//end function

}//end component class
