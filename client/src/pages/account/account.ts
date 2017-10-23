import { Component } from '@angular/core';
import { User } from '../../app/model/user';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-account',
  templateUrl: 'account.html'
})
export class AccountPage {
  user: User;
  constructor(public navCtrl: NavController) {

  }

}
