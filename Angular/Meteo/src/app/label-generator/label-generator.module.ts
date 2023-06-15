import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class LabelGeneratorModule {

  //function that identifies value
  public static identifyValue(value : string, metric : boolean, prefix : string){
    let shownValue : string = "";

    switch(value){
      case "tempc":
        if(metric){
          shownValue = prefix + " Teplota (°C)";
        }//end if
        if(!metric){
          shownValue = prefix + " Teplota (°F)";
        }//end if
        break;
      case "baromcm":
        if(metric){
          shownValue = prefix + " Barometrický tlak (cm)";
        }//end if
        if(!metric){
          shownValue = prefix + " Barometrický tlak (in)";
        }//end if
        break;
      case "windspeedkph":
        if(metric){
          shownValue = prefix + " Rychlost větru (km/h)";
        }//end if
        if(!metric){
          shownValue = prefix + " Rychlost větru (mi/h)";
        }//end if
        break;
      case "raincm":
        if(metric){
          shownValue = prefix + " Výška srážek (cm)";
        }//end if
        if(!metric){
          shownValue = prefix + " Výška srážek (in)";
        }//end if
        break;
      case "indoortempc":
        if(metric){
          shownValue = prefix + " Vnitřní teplota (°C)";
        }//end if
        if(!metric){
          shownValue = prefix + " Vnitřní teplota (°F)";
        }//end if
        break;
      case "humidity":
        shownValue = prefix + " Vlhkost";
        break;
      case "solarradiation":
        shownValue = prefix + " Sluneční záření";
        break;
      case "indoorhumidity":
        shownValue = prefix + " Vnitřní vlhkost";
        break;
      case "UV":
        shownValue = prefix + " Ultrafialové záření";
        break;
    }//end switch
    return shownValue;
  }//end function

  //function for identifying value, to use in HTTP get
  public static switchUnitsIdentifyValue(value : string, metric : boolean){
    let pomu = "";

    switch(value){
      case "tempc":
        if(metric){
          pomu = "tempc";
        }//end if
        if(!metric){
          pomu = "tempf";
        }//end if
        break;
      case "baromcm":
        if(metric){
          pomu = "baromcm";
        }//end if
        if(!metric){
          pomu = "baromin";
        }//end if
        break;
      case "windspeedkph":
        if(metric){
          pomu = "windspeedkph";
        }//end if
        if(!metric){
          pomu = "windspeedmph";
        }//end if
        break;
      case "raincm":
        if(metric){
          pomu = "raincm";
        }//end if
        if(!metric){
          pomu = "rainin";
        }//end if
        break;
      case "indoortempc":
        if(metric){
          pomu = "indoortempc";
        }//end if
        if(!metric){
          pomu = "indoortempf";
        }//end if
        break;
      case "humidity":
        pomu = "humidity";
        break;
      case "solarradiation":
        pomu = "solarradiation";
        break;
      case "indoorhumidity":
        pomu = "indoorhumidity";
        break;
      case "UV":
        pomu = "UV";
        break;
    }//end switch
    return pomu;
  }//end function

  public static dailyGraphLabels(){
    const labels = [ //graph labels
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

  return labels;
  }//end function

  public static monthlyGraphLabels(length : number){
    const labels : any =  [];
    for(let i = 0; i < length; i++){
      labels.push(i+1 + ". den");
    }//end for
    return labels;
  }//end function

  public static weeklyGraphLabels(){
    const labels = [ //graph labels
        "1. den",
        "2. den",
        "3. den",
        "4. den",
        "5. den",
        "6. den",
        "7. den"
      ];
    return labels;
  }//end function

  public static yearlyGraphLabels(){
    const labels = [
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
    return labels;
  }//end function

 }
