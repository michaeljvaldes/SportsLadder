export class Player {
  id: number;
  name: string;
  rank: number;


  constructor(values: Object = {}) {
    Object.assign(this,values);
  }

}
