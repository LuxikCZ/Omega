import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DailyGraphComponent } from './daily-graph/daily-graph.component';
import { MainComponent } from './main/main.component';
import { MonthlyGraphComponent } from './monthly-graph/monthly-graph.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { WeeklyGraphComponent } from './weekly-graph/weekly-graph.component';
import { YearlyGraphComponent } from './yearly-graph/yearly-graph.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'main', component: MainComponent},
  {path: 'dailyGraph', component: DailyGraphComponent},
  {path: 'weeklyGraph', component: WeeklyGraphComponent},
  {path: 'monthlyGraph', component: MonthlyGraphComponent},
  {path: 'yearlyGraph', component: YearlyGraphComponent},
  {path: '**', component: NotFoundComponent} //this must always be last
];

@NgModule({
  imports: [RouterModule.forRoot(
    routes
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
