import {Component, OnInit, Directive, ElementRef, Input} from '@angular/core';

@Component({
  selector: 'app-players',
  templateUrl: 'players.component.html',
  styleUrls: ['players.component.css']
})


@Directive({
  selector: '[contenteditableModel]',
  host: {
    '(blur)': 'onBlur()'
  }
})

export class PlayersComponent implements OnInit {
  @Input('contenteditableModel') model: any;
  newPlayerPending:boolean = true;
  newPlayerName:string = "Player Name";
  newPlayerRank:number = null;

  constructor(private elRef: ElementRef) { }
  players = [["Mr Milk", 1], ["Mr Shake", 2]];

  ngOnInit() {
  }

  onAddPlayer() {
    this.newPlayerPending = false;
  }

  onSavePlayer() {
    this.newPlayerPending = true;
    this.players.push([this.newPlayerName, this.newPlayerRank ]);
  }

  onCancelAdd() {
    this.newPlayerPending = true;
  }

  onBlur() {
    var value = this.elRef.nativeElement.innerText
    this.newPlayerName = value
  }
}
