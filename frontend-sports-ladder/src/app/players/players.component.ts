import {Component, OnInit, Directive, ElementRef, Input} from '@angular/core';

@Component({
  selector: 'app-players',
  templateUrl: 'players.component.html',
  styleUrls: ['players.component.css']
})

export class PlayersComponent implements OnInit {
  newPlayerPending:boolean = true;
  newPlayerName:string = "";
  newPlayerRank:number = null;
  players = [["Mr Milk", 1], ["Mr Shake", 2]];

  errors = [];

  constructor(private elRef: ElementRef) { }
  ngOnInit() {
  }

  onAddPlayer() {
    this.newPlayerPending = false;
  }

  onSavePlayer() {
    this.errors = [];
    this.validateNewPlayer(this.newPlayerName, this.newPlayerRank);
    if (this.errors.length == 0) {
      this.players.push([this.newPlayerName, this.newPlayerRank]);
      this.clearPendingPlayer();
    }
  }

  /**
   * I don't have internet and can't search how to type the return of functions :|
   * Sorry for the non pure functions.
   * @param name
   * @param rank
   */
  validateNewPlayer(name:string, rank:number) {
    if (name == "") {
      this.errors.push("Name cannot be empty");
    }
    // Should we allow unranked people on the ladder or should everyone have a rank?
    if (rank == null) {
      this.errors.push("Rank cannot be empty");
    }
  }

  onCancelAdd() {
    this.clearPendingPlayer();
  }

  clearPendingPlayer() {
    this.newPlayerPending = true;
    this.newPlayerRank=null;
    this.newPlayerName = "";
    this.errors = [];
  }

  onUpdatePlayerName(event:Event) {
    console.log(this.newPlayerName);
    this.newPlayerName = (<HTMLInputElement> event.target).value;
    console.log(this.newPlayerName);
  }

  onUpdatePlayerRank(event:Event) {
    this.newPlayerRank = Number.parseFloat((<HTMLInputElement> event.target).value);
    console.log(this.newPlayerRank);
    console.log((<HTMLInputElement> event.target).value);
  }

}
