import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';
import { LabelGeneratorModule } from '../label-generator/label-generator.module';

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

  //function changing units from metric to imperial and backwards
  switchUnits(){
    this.metric = !this.metric;
    let pomu = LabelGeneratorModule.switchUnitsIdentifyValue(this.value, this.metric);
    this.displayGraph(pomu, this.savedDay);
  }//end function
  
  displayGraphInit(value : string, day : string){ //graph display initialization
    this.value = value;
    this.displayGraph(value, day);
  }//end function

  //method for displatying graph
  displayGraph(value : string, day : string){
    this.shownValue = LabelGeneratorModule.identifyValue(this.value, this.metric, "Denní");
    this.savedDay = day;
    this.data.length = 0;
    const url = "https://www.titera.eu/Meteo/api/inhour/" + value + "/" + day;
    this.http.get(url).subscribe((res) => {
      this.data = res;

      const labels=LabelGeneratorModule.dailyGraphLabels();
  
      const lineChartType : ChartType = "line";
      //const interpolation : CubicInterpolationMode = "monotone";
      const chartData = { //graph data config
        labels: labels,
        datasets: [{
          label: this.shownValue,
          backgroundColor: 'rgb(255,255,255)',
          borderColor: 'rgb(0, 0, 100)',
          data: this.data,
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
