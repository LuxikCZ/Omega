import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { LabelGeneratorModule } from '../label-generator/label-generator.module';

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
  private tempSymbol = "°C";
  private rainUnit = "cm";
  private windUnit = "km/h";
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
    this.getActualTemp(); //gets tempIn and tempOut
    this.getMaxRecords(); //get maximal records
    this.getMinRecords(); //get minimal records
    //update both temperatures every 15 seconds
    setInterval(() => this.getActualTemp(), 15000);
    //update records every 60 seconds
    setInterval(() => this.getMaxRecords(), 60000);
    setInterval(() => this.getMinRecords(), 60000);
  }//end function

  switchUnits(){ //switch units from celsius to fahrenheit and the other way
    if(this.metric){
      this.metric = !this.metric; //switch units
      this.unitSystem = "Imperiální";
      this.tempSymbol = "°F";
      this.rainUnit = "in";
      this.windUnit = "mi/h";
    }//end function
    else if(!this.metric){
      this.metric = !this.metric; //switch units
      this.unitSystem = "Metrický";
      this.tempSymbol = "°C";
      this.rainUnit = "cm";
      this.windUnit = "km/h";
    }//end else if
    
    //update data
    this.getActualTemp();
    this.getMaxRecords();
    this.getMinRecords();
  }//end function

  getActualTemp(){
    this.http.get("https://www.titera.eu/Meteo/api/newest/" + LabelGeneratorModule.switchUnitsIdentifyValue("tempc", this.metric)).subscribe((res)=>{ //outside temp
      this.tempOut = res + this.tempSymbol;
    });//end http get

    this.http.get("https://www.titera.eu/Meteo/api/newest/" + LabelGeneratorModule.switchUnitsIdentifyValue("indoortempc", this.metric)).subscribe((res)=>{ //inside temp
      this.tempIn = res + this.tempSymbol;
    });//end http get
  }//end function

  getMaxRecords(){
    this.http.get("https://www.titera.eu/Meteo/api/max/" + LabelGeneratorModule.switchUnitsIdentifyValue("tempc", this.metric)).subscribe((res)=>{ //outside temp
      let temp : any = [];
      temp = res;
      for(let key in temp){
        this.maxTempOut = key + this.tempSymbol;
        this.maxTempOutDate = temp[key];
      }//end for
    });//end http get

    this.http.get("https://www.titera.eu/Meteo/api/max/" + LabelGeneratorModule.switchUnitsIdentifyValue("indoortempc", this.metric)).subscribe((res)=>{ //inside temp
      let temp : any = [];
      temp = res;
      for(let key in temp){
        this.maxTempIn = key + this.tempSymbol;
        this.maxTempInDate = temp[key];
      }//end for
    });//end http get
    
      this.http.get("https://www.titera.eu/Meteo/api/max/" + LabelGeneratorModule.switchUnitsIdentifyValue("raincm", this.metric)).subscribe((res)=>{ //rain height
        let rain : any = [];
        rain = res;
        for(let key in rain){
          this.maxRain= key + this.rainUnit;
          this.maxRainDate = rain[key];
        }//end for
      });//end http get

      this.http.get("https://www.titera.eu/Meteo/api/max/" + LabelGeneratorModule.switchUnitsIdentifyValue("windspeedkph", this.metric)).subscribe((res)=>{ //wind speed
        let wind : any = [];
        wind = res;
        for(let key in wind){
          this.maxWind= key + this.windUnit;
          this.maxWindDate = wind[key];
        }//end for
      });//end http get
  }//end function

  getMinRecords(){
      this.http.get("https://www.titera.eu/Meteo/api/min/" + LabelGeneratorModule.switchUnitsIdentifyValue("indoortempc", this.metric)).subscribe((res)=>{ //inside temp
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.minTempIn= key + this.tempSymbol;
          this.minTempInDate = temp[key];
        }//end for
      });//end http get

      this.http.get("https://www.titera.eu/Meteo/api/min/" + LabelGeneratorModule.switchUnitsIdentifyValue("tempc", this.metric)).subscribe((res)=>{ //outside temp
        let temp : any = [];
        temp = res;
        for(let key in temp){
          this.minTempOut= key + this.tempSymbol;
          this.minTempOutDate = temp[key];
        }//end for
      });//end http get
  }//end function

}//end component class
