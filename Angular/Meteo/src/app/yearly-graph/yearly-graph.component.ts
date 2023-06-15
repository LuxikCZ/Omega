import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';
import { LabelGeneratorModule } from '../label-generator/label-generator.module';

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
    const url = "https://www.titera.eu/Meteo/api/years";
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
    this.shownValue = LabelGeneratorModule.identifyValue(this.value, this.metric, "Roční");
    this.savedYear = year;
    this.data.length = 0;
    const url = "https://www.titera.eu/Meteo/api/yearly/" + value + "/" + year;
    this.http.get(url).subscribe((res) => {
      this.data = res;
      console.log(this.data);

      const labels : any = LabelGeneratorModule.yearlyGraphLabels();
  
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
    let pomu = LabelGeneratorModule.switchUnitsIdentifyValue(this.value, this.metric);
    this.displayGraph(pomu, this.savedYear);
  }//end function

}//end component class
