import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';

import {Http, Response} from '@angular/http';
import { HttpClient } from '@angular/common/http'
import {Player} from './player';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) {
  }


  public getAllPlayers(): Observable<Player[]> {
    return this.http
      .get(API_URL + '/leaderboard')
      .map(response => {
        const players = response;
        return players.map((player) => new
        Player(player));

      })
  }
}
