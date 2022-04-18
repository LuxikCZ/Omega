import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';

@Component({
  selector: 'app-daily-graph',
  templateUrl: './daily-graph.component.html',
  styleUrls: ['./daily-graph.component.css']
})

export class DailyGraphComponent implements OnInit {

  private data:any = []; //data shown in the graph
  private metric : boolean = true; //metric/imperial system
  private savedDay : string = ""; //day selected by the user
  private value = ""; //value selected by the user
  private shownValue = ""; //value that is shown in the graph

  //component constructor
  constructor(private http : HttpClient) {
    Chart.register(...registerables);
  }

  //function for identifying displayed value
  identifyValue(){
    let pomu : string = "";

    switch(this.value){
      case "tempc":
        if(this.metric){
          this.shownValue = "Denní Teplota (°C)";
          pomu = "tempc";
        }//end if
        if(!this.metric){
          this.shownValue = "Denní Teplota (°F)";
          pomu = "tempf";
        }//end if
        break;
      case "baromcm":
        if(this.metric){
          this.shownValue = "Denní Barometrický tlak (cm)";
          pomu = "baromcm";
        }//end if
        if(!this.metric){
          this.shownValue = "Denní Barometrický tlak (in)";
          pomu = "baromin";
        }//end if
        break;
      case "windspeedkph":
        if(this.metric){
          this.shownValue = "Denní Rychlost větru (km/h)";
          pomu = "windspeedkph";
        }//end if
        if(!this.metric){
          this.shownValue = "Denní Rychlost větru (mi/h)";
          pomu = "windspeedmph";
        }//end if
        break;
      case "raincm":
        if(this.metric){
          this.shownValue = "Denní Výška srážek (cm)";
          pomu = "raincm";
        }//end if
        if(!this.metric){
          this.shownValue = "Denní Výška srážek (in)";
          pomu = "rainin";
        }//end if
        break;
      case "indoortempc":
        if(this.metric){
          this.shownValue = "Denní Vnitřní teplota (°C)";
          pomu = "indoortempc";
        }//end if
        if(!this.metric){
          this.shownValue = "Denní Vnitřní teplota (°F)";
          pomu = "indoortempf";
        }//end if
        break;
      case "humidity":
        this.shownValue = "Denní Vlhkost";
        pomu = "humidity";
        break;
      case "solarradiation":
        this.shownValue = "Denní Sluneční záření";
        pomu = "solarradiation";
        break;
      case "indoorhumidity":
        this.shownValue = "Denní Vnitřní vlhkost";
        pomu = "indoorhumidity";
        break;
      case "UV":
        this.shownValue = "Denní Ultrafialové záření";
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
  
  displayGraphInit(value : string, day : string){ //graph display initialization
    this.value = value;
    this.displayGraph(value, day);
  }//end function

  //method for displatying graph
  displayGraph(value : string, day : string){
    this.identifyValue();
    this.savedDay = day;
    this.data.length = 0;
    const url = "http://localhost:8080/api/inhour/" + value + "/" + day;
    console.log(url);
    this.http.get(url).subscribe((res) => {
      this.data = res;
      console.log(this.data);

      const labels=[ //graph labels
        "0:00",
        "1:00",
        "2:00",
        "3:00",
        "4:00",
        "5:00",
        "6:00",
        "7:00",
        "8:00",
        "9:00",
        "10:00",
        "11:00",
        "12:00",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00",
        "18:00",
        "19:00",
        "20:00",
        "21:00",
        "22:00",
        "23:00"
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
            title:{ //graph title
              display: true,
              text: "Den " + this.savedDay,
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

  //function run on page's init
  ngOnInit(): void {
  }//end function

}//end component
