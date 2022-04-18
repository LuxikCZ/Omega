import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';

@Component({
  selector: 'app-yearly-graph',
  templateUrl: './yearly-graph.component.html',
  styleUrls: ['./yearly-graph.component.css']
})
export class YearlyGraphComponent implements OnInit {

  private data:any = []; //data to display
  public years:any = []; //array of available years
  private metric : boolean = true; //use metric or imperial system
  private shownValue = ""; //value to show as graph label
  private value = ""; //selected value by the user
  private savedYear : string = ""; //year selected by the user

  constructor(private http : HttpClient) {
    Chart.register(...registerables);
  }//end constructor

  ngOnInit(): void {
    const url = "http://localhost:8080/api/years";
    this.http.get(url).subscribe((res)=>{ //get all available years
      this.years = res;
      console.log(this.years);//debug log
    })//end get
  }//end oninti function

  displayGraphInit(value : string, year : string){
    this.value = value;
    this.displayGraph(value, year);
  }//end function

  displayGraph(value : string, year : string){
    this.identifyValue();
    this.savedYear = year;
    this.data.length = 0;
    const url = "http://localhost:8080/api/yearly/" + value + "/" + year;
    console.log(url);
    this.http.get(url).subscribe((res) => {
      this.data = res;
      console.log(this.data);

      const labels : any = [
        "Leden",
        "Únor",
        "Březen",
        "Duben",
        "Květen",
        "Červen",
        "Červenec",
        "Srpen",
        "Září",
        "Listopad",
        "Prosinec"
      ];
  
      const lineChartType : ChartType = "line";
      //const interpolation : CubicInterpolationMode = "monotone";
      const chartData = { //graph data config
        labels: labels,
        datasets: [{
          label: this.shownValue,
          backgroundColor: 'rgb(255,255,255)',
          borderColor: 'rgb(0, 0, 100)',
          data: this.data,
        // cubicInterpolationMode: interpolation,
        }]
      };
      
      const config = { //graph config
        type: lineChartType,
        data: chartData,
        options: {
          plugins:{
            title: {
              display: true,
              text: "Rok " + this.savedYear,
            },
          },
        }
      };
  
      //checks if graph already exists, if it does, it is destroyed
      let chartStatus = Chart.getChart("graph");
      if (chartStatus != undefined) {
        chartStatus.destroy();
      }//end if   

      const graph = new Chart( //graph creation
        "graph",
        config
      );
    });
  }//end function

  switchUnits(){
    this.metric = !this.metric;
    let pomu = this.identifyValue();
    console.log(this.metric);
    console.log(pomu);
    this.displayGraph(pomu, this.savedYear);
  }//end function

  identifyValue(){
    let pomu : string = "";

    switch(this.value){
      case "tempc":
        if(this.metric){
          this.shownValue = "Roční Teplota (°C)";
          pomu = "tempc";
        }//end if
        if(!this.metric){
          this.shownValue = "Roční Teplota (°F)";
          pomu = "tempf";
        }//end if
        break;
      case "baromcm":
        if(this.metric){
          this.shownValue = "Roční Barometrický tlak (cm)";
          pomu = "baromcm";
        }//end if
        if(!this.metric){
          this.shownValue = "Roční Barometrický tlak (in)";
          pomu = "baromin";
        }//end if
        break;
      case "windspeedkph":
        if(this.metric){
          this.shownValue = "Roční Rychlost větru (km/h)";
          pomu = "windspeedkph";
        }//end if
        if(!this.metric){
          this.shownValue = "Roční Rychlost větru (mi/h)";
          pomu = "windspeedmph";
        }//end if
        break;
      case "raincm":
        if(this.metric){
          this.shownValue = "Roční Výška srážek (cm)";
          pomu = "raincm";
        }//end if
        if(!this.metric){
          this.shownValue = "Roční Výška srážek (in)";
          pomu = "rainin";
        }//end if
        break;
      case "indoortempc":
        if(this.metric){
          this.shownValue = "Roční Vnitřní teplota (°C)";
          pomu = "indoortempc";
        }//end if
        if(!this.metric){
          this.shownValue = "Roční Vnitřní teplota (°F)";
          pomu = "indoortempf";
        }//end if
        break;
      case "humidity":
        this.shownValue = "Roční Vlhkost";
        pomu = "humidity";
        break;
      case "solarradiation":
        this.shownValue = "Roční Sluneční záření";
        pomu = "solarradiation";
        break;
      case "indoorhumidity":
        this.shownValue = "Roční Vnitřní vlhkost";
        pomu = "indoorhumidity";
        break;
      case "UV":
        this.shownValue = "Roční Ultrafialové záření";
        pomu = "UV";
        break;
    }//end switch
    return pomu;
  }//end function

}//end component class
