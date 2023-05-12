import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { ReceitasRoutingModule } from './receitas-routing.module';
import { ReceitasComponent } from './containers/receitas/receitas.component';
import { ReceitasListComponent } from './components/receitas-list/receitas-list.component';
import { ReceitasDisplayComponent } from './components/receitas-display/receitas-display.component';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    ReceitasComponent,
    ReceitasListComponent,
    ReceitasDisplayComponent,
  ],
  imports: [
    CommonModule,
    ReceitasRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule,
    MatDialogModule,
    SharedModule,
  ],
})
export class ReceitasModule {}
