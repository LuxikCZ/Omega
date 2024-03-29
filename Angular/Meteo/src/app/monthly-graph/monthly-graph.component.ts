import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';
import { LabelGeneratorModule } from '../label-generator/label-generator.module';

@Component({
  selector: 'app-monthly-graph',
  templateUrl: './monthly-graph.component.html',
  styleUrls: ['./monthly-graph.component.css']
})
export class MonthlyGraphComponent implements OnInit {

  private data:any = []; //data shown in the graph
  private metric : boolean = true; //metric/imperial system
  private savedMonth : string = ""; //month selected by the user
  private savedYear : string = ""; //year selected by the user
  private value = ""; //value selected by the user
  private shownValue = ""; //value shown in the graph
  public years:any = []; //array of available years
  public months:any = []; //dictionary of available months
  public monthNums:any = []; //numbers of available months

  constructor(private http : HttpClient) {
    Chart.register(...registerables);
  }//end constructor

  ngOnInit(): void {
    const url = "https://www.titera.eu/Meteo/api/years";
    this.http.get(url).subscribe((res)=>{ //get all available years
      this.years = res;
    })//end get
    const url2 = "https://www.titera.eu/Meteo/api/months";
    this.http.get(url2).subscribe((res)=>{ //get all available months
      this.months = res;
      for(let key in this.months){ //get numbers of all available months
        this.monthNums.push(key);
      }//end for
    })//end get
  }//end onInit

  displayGraphInit(value : string, month : string, year : string){ //graph display initialization
    this.value = value;
    this.displayGraph(value, month, year);
  }//end function

  displayGraph(value : string, month : string, year : string){ //function for getting data from API and displaying it in the graph
    this.shownValue = LabelGeneratorModule.identifyValue(this.value, this.metric, "Měsíční")
    this.savedMonth = month;
    this.savedYear = year;
    this.data.length = 0;
    const url = "https://www.titera.eu/Meteo/api/monthly/" + value + "/" + month + "/" + year;
    this.http.get(url).subscribe((res) => {
      this.data = res;

      const labels = LabelGeneratorModule.monthlyGraphLabels(this.data.length);
  
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
            title: { //graph title
              display: true,
              text: "Měsíc " + this.months[month] + " roku " + year,
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

  //function for switching between imperial/metric units
  switchUnits(){
    this.metric = !this.metric;
    let pomu = LabelGeneratorModule.switchUnitsIdentifyValue(this.value, this.metric);
    this.displayGraph(pomu, this.savedMonth, this.savedYear);
  }//end function

}//end component class
