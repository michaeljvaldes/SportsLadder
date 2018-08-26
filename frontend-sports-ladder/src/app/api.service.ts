import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';

import {Http, Response} from '@angular/http';
import { HttpClient } from '@angular/common/http'
import {Player} from './player';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {map} from "rxjs/internal/operators";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) {
  }


  public getAllPlayers(): Observable<Player[]> {

    return this.http
      .get<Player[]>(API_URL + '/leaderboard')
      }

  // return this.http.get<Hero[]>(this.heroesUrl)


  // getAll() {
  //   return this.http
  //     .get<any[]>(this.url)
  //     .pipe(map(data => data));
  // }
}
