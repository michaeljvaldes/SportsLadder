import { Component, OnInit } from '@angular/core';
import { Player } from '../player';
import { PlayerService } from '../player.service';
import { ApiService } from '../api.service'

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {

  players: Player[];

//   getPlayers(): void {
//     this.playerService.getPlayers()
//       .subscribe(players => this.players = players);
// }

getPlayers(): void {
    this.apiService.getAllPlayers()
      .subscribe(players => this.players = players);
}

    selectedPlayer: Player;

  // constructor(private playerService: PlayerService) { }
  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getPlayers();
  }

  onSelect(player: Player): void {
    this.selectedPlayer = player;
  }

}
