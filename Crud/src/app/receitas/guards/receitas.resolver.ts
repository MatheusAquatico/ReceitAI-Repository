import { Injectable } from '@angular/core';
import {
  Router,
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot,
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Receita } from '../model/receita';
import { ReceitasService } from '../services/receitas.service';

@Injectable({
  providedIn: 'root',
})
export class EstoqueResolver implements Resolve<Receita> {
  constructor(private service: ReceitasService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Receita> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of({ id: 0, titulo: '', data: '' });
  }
}
