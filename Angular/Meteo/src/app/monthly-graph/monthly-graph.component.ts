import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Chart, registerables } from 'chart.js';
import { ChartType } from 'chart.js';

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
    const url = "http://localhost:8080/api/years";
    this.http.get(url).subscribe((res)=>{ //get all available years
      this.years = res;
      console.log(this.years);//debul log
    })//end get
    const url2 = "http://localhost:8080/api/months";
    this.http.get(url2).subscribe((res)=>{ //get all available months
      this.months = res;
      console.log(this.months);//debug log
      for(let key in this.months){ //get numbers of all available months
        this.monthNums.push(key);
        console.log(this.monthNums);//debug log
      }//end for
    })//end get
  }//end onInit

  displayGraphInit(value : string, month : string, year : string){ //graph display initialization
    this.value = value;
    this.displayGraph(value, month, year);
  }//end function

  displayGraph(value : string, month : string, year : string){ //function for getting data from API and displaying it in the graph
    this.identifyValue();
    this.savedMonth = month;
    this.savedYear = year;
    this.data.length = 0;
    const url = "http://localhost:8080/api/monthly/" + value + "/" + month + "/" + year;
    console.log(url);
    this.http.get(url).subscribe((res) => {
      this.data = res;
      console.log(this.data);

      const labels = this.generateLabels();
  
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
              text: "M??s??c " + this.months[month] + " roku " + year,
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

  //function for generating graph labels according to selected month and year
  generateLabels(){
    const labels : any =  [];
    for(let i = 0; i < this.data.length; i++){
      labels.push(i+1 + ". den");
    }//end for
    return labels;
  }//end function

  //function for switching between imperial/metric units
  switchUnits(){
    this.metric = !this.metric;
    let pomu = this.identifyValue();
    console.log(this.metric);
    console.log(pomu);
    this.displayGraph(pomu, this.savedMonth, this.savedYear);
  }//end function

  identifyValue(){
    let pomu : string = "";

    switch(this.value){
      case "tempc":
        if(this.metric){
          this.shownValue = "M??s????n?? Teplota (??C)";
          pomu = "tempc";
        }//end if
        if(!this.metric){
          this.shownValue = "M??s????n?? Teplota (??F)";
          pomu = "tempf";
        }//end if
        break;
      case "baromcm":
        if(this.metric){
          this.shownValue = "M??s????n?? Barometrick?? tlak (cm)";
          pomu = "baromcm";
        }//end if
        if(!this.metric){
          this.shownValue = "M??s????n?? Barometrick?? tlak (in)";
          pomu = "baromin";
        }//end if
        break;
      case "windspeedkph":
        if(this.metric){
          this.shownValue = "M??s????n?? Rychlost v??tru (km/h)";
          pomu = "windspeedkph";
        }//end if
        if(!this.metric){
          this.shownValue = "M??s????n?? Rychlost v??tru (mi/h)";
          pomu = "windspeedmph";
        }//end if
        break;
      case "raincm":
        if(this.metric){
          this.shownValue = "M??s????n?? V????ka sr????ek (cm)";
          pomu = "raincm";
        }//end if
        if(!this.metric){
          this.shownValue = "M??s????n?? V????ka sr????ek (in)";
          pomu = "rainin";
        }//end if
        break;
      case "indoortempc":
        if(this.metric){
          this.shownValue = "M??s????n?? Vnit??n?? teplota (??C)";
          pomu = "indoortempc";
        }//end if
        if(!this.metric){
          this.shownValue = "M??s????n?? Vnit??n?? teplota (??F)";
          pomu = "indoortempf";
        }//end if
        break;
      case "humidity":
        this.shownValue = "M??s????n?? Vlhkost";
        pomu = "humidity";
        break;
      case "solarradiation":
        this.shownValue = "M??s????n?? Slune??n?? z????en??";
        pomu = "solarradiation";
        break;
      case "indoorhumidity":
        this.shownValue = "M??s????n?? Vnit??n?? vlhkost";
        pomu = "indoorhumidity";
        break;
      case "UV":
        this.shownValue = "M??s????n?? Ultrafialov?? z????en??";
        pomu = "UV";
        break;
    }//end switch
    return pomu;
  }//end function

}//end component class
