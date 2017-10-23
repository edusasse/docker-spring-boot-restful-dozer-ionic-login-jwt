import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { TabsPage } from '../pages/tabs/tabs';
import { AnalyticsPage } from '../pages/analytics/analytics';
import { ChatPage } from '../pages/chat/chat';
import { FinancialPage } from '../pages/financial/financial';
import { AccountPage } from '../pages/account/account';
import { AboutPage } from '../pages/about/about';
import { HeaderComponent } from '../pages/tabs/header';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { LoginPage } from "../pages/login/login";
import { SignupPage } from "../pages/signup/signup";
import { CustomFormsModule } from 'ng2-validation'
import { Storage, IonicStorageModule } from "@ionic/storage";
import { AuthService } from "../providers/auth-service";
import { UserService } from "../providers/user/user-service";
import { JwtHelper, AuthConfig, AuthHttp } from "angular2-jwt";
import { Http, RequestOptions } from "@angular/http";

export function authHttpServiceFactory(http: Http, options: RequestOptions, storage: Storage) {
  storage.get('jwt').then((value) => {
  console.log("Local Storage value:", JSON.stringify(value))
});

  const authConfig = new AuthConfig({
    tokenGetter: (() => storage.get('jwt')),
  });
  return new AuthHttp(authConfig, http, options);
}

@NgModule({
  declarations: [
    MyApp,
    TabsPage,
    AnalyticsPage,
    ChatPage,
    FinancialPage,
    AccountPage,
    AboutPage,
    LoginPage,
    SignupPage,
    HeaderComponent
  ],
  imports: [
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot({
      name: 'myapp',
      driverOrder: ['sqlite', 'indexeddb', 'websql']
    }),
    CustomFormsModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    TabsPage,
    AnalyticsPage,
    ChatPage,
    FinancialPage,
    AccountPage,
    AboutPage,
    LoginPage,
    SignupPage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler},
    StatusBar,
    SplashScreen,
    AuthService,
    UserService,
    JwtHelper, {
      provide: AuthHttp,
      useFactory: authHttpServiceFactory,
      deps: [Http, RequestOptions, Storage]
    }]
})
export class AppModule {
}
