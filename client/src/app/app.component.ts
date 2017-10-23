import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { TabsPage } from '../pages/tabs/tabs';
import { AccountPage } from '../pages/account/account';
import { AboutPage } from '../pages/about/about';
import { LoginPage } from "../pages/login/login";
import { AuthService } from "../providers/auth-service";
import { UserService } from "../providers/user/user-service";

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  rootPage: any = null;
  pages: Array<{title: string, component: any, icon: string}>;

  constructor(public platform: Platform,
              statusBar: StatusBar, splashScreen: SplashScreen,
              private readonly authService: AuthService,
              private readonly userService: UserService) {
    platform.ready().then(() => {
      statusBar.styleDefault();
      splashScreen.hide();
    });
    
    this.pages = [
      { title: 'Start', component: TabsPage, icon: 'home' },
      { title: 'User', component: AccountPage, icon: 'contact'},
      { title: 'Blog', component: AccountPage, icon: 'paper'},
      { title: 'Invite friends', component: AccountPage, icon: 'share'}
    ];

    this.authService.authUser.subscribe(jwt => {
      if (jwt) {
        userService.getAuthenticatedUserInfo();
        this.rootPage = TabsPage;
      }
      else {
        this.rootPage = LoginPage;
      }
    });

    this.authService.checkLogin();
  }

  openPage(page) {
    this.nav.setRoot(page.component);
  }

  logout() {
    this.authService.logout();
  }

  getLogin(){
    return this.userService.getLogin();
  }

}
