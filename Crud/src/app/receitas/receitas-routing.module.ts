import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReceitasComponent } from './containers/receitas/receitas.component';
import { ReceitasListComponent } from './components/receitas-list/receitas-list.component';

const routes: Routes = [
  { path: '', component: ReceitasComponent },
  { path: 'receitas-salvas', component: ReceitasListComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReceitasRoutingModule {}
