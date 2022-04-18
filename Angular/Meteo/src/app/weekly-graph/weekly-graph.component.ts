import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';

@Component({
  selector: 'app-weekly-graph',
  templateUrl: './weekly-graph.component.html',
  styleUrls: ['./weekly-graph.component.css']
})
export class WeeklyGraphComponent implements OnInit {

  private data:any = []; //data shown in the graph
  private metric : boolean = true; //metric/imperial system
  private savedDay : string = ""; //day selected by the user
  private value = ""; //value selected by the user
  private shownValue = ""; //value shown in graph

  //component constructor
  constructor(private http : HttpClient) {
    Chart.register(...registerables);
  }//end constructor

  //function for identifying displayed value
  identifyValue(){
    let pomu : string = "";

    switch(this.value){
      case "tempc":
        if(this.metric){
          this.shownValue = "Týdení Teplota (°C)";
          pomu = "tempc";
        }//end if
        if(!this.metric){
          this.shownValue = "Týdení Teplota (°F)";
          pomu = "tempf";
        }//end if
        break;
      case "baromcm":
        if(this.metric){
          this.shownValue = "Týdení Barometrický tlak (cm)";
          pomu = "baromcm";
        }//end if
        if(!this.metric){
          this.shownValue = "Týdení Barometrický tlak (in)";
          pomu = "baromin";
        }//end if
        break;
      case "windspeedkph":
        if(this.metric){
          this.shownValue = "Týdení Rychlost větru (km/h)";
          pomu = "windspeedkph";
        }//end if
        if(!this.metric){
          this.shownValue = "Týdení Rychlost větru (mi/h)";
          pomu = "windspeedmph";
        }//end if
        break;
      case "raincm":
        if(this.metric){
          this.shownValue = "Týdení Výška srážek (cm)";
          pomu = "raincm";
        }//end if
        if(!this.metric){
          this.shownValue = "Týdení Výška srážek (in)";
          pomu = "rainin";
        }//end if
        break;
      case "indoortempc":
        if(this.metric){
          this.shownValue = "Týdení Vnitřní teplota (°C)";
          pomu = "indoortempc";
        }//end if
        if(!this.metric){
          this.shownValue = "Týdení Vnitřní teplota (°F)";
          pomu = "indoortempf";
        }//end if
        break;
      case "humidity":
        this.shownValue = "Týdení Vlhkost";
        pomu = "humidity";
        break;
      case "solarradiation":
        this.shownValue = "Týdení Sluneční záření";
        pomu = "solarradiation";
        break;
      case "indoorhumidity":
        this.shownValue = "Týdení Vnitřní vlhkost";
        pomu = "indoorhumidity";
        break;
      case "UV":
        this.shownValue = "Týdení Ultrafialové záření";
        pomu = "UV";
        break;
    }//end switch
    return pomu;
  }//end function

  //function changing units from metric to imperial and backwards
  switchUnits(){
    this.metric = !this.metric;
    let pomu = this.identifyValue();
    console.log(this.metric);
    console.log(pomu);
    this.displayGraph(pomu, this.savedDay);
  }//end function
  
  displayGraphInit(value : string, day : string){
    this.value = value;
    this.displayGraph(value, day);
  }//end function

  //method for displatying graph
  displayGraph(value : string, day : string){
    this.identifyValue();
    this.savedDay = day;
    this.data.length = 0;
    const url = "http://localhost:8080/api/weekly/" + value + "/" + day;
    console.log(url);
    this.http.get(url).subscribe((res) => {
      this.data = res;
      console.log(this.data);

      const labels=[ //graph labels
        "1. den",
        "2. den",
        "3. den",
        "4. den",
        "5. den",
        "6. den",
        "7. den"
      ];
  
      const lineChartType : ChartType = "line";
      //const interpolation : CubicInterpolationMode = "monotone";
      const chartData = { //graph data config
        labels: labels,
        datasets: [{
          label: this.shownValue,
          backgroundColor: 'rgb(255,255,255)',
          borderColor: 'rgb(0, 0, 100)',
          data: this.data
        }]
      };
      
      const config = { //graph config
        type: lineChartType,
        data: chartData,
        options: {
          plugins: {
            title:{ //graph title
              display: true,
              text: "Týden od " + this.savedDay
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
  }
  ngOnInit(): void {
  }//end function

}//end component class
