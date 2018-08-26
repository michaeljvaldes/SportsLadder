import {Injectable} from '@angular/core';
import {Player} from './player'
import {PLAYERS} from './mock-players'
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class PlayerService {

  constructor() {
  }

  getPlayers(): Observable<Player[]> {
    return of(PLAYERS);
  }
}
