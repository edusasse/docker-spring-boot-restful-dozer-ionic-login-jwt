import { Component } from '@angular/core';
import { AuthService } from "../../providers/auth-service";
import { JwtHelper, AuthHttp } from "angular2-jwt";
import { SERVER_URL } from "../../config";
import { AnalyticsPage } from "../analytics/analytics";
import { ChatPage } from "../chat/chat";
import { FinancialPage } from "../financial/financial";

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {
  tab1Root: any = AnalyticsPage;
  tab2Root: any = ChatPage;
  tab3Root: any = FinancialPage;

  user: string;
  message: string;

  constructor(private readonly authService: AuthService,
              private readonly jwtHelper: JwtHelper,
              private readonly  authHttp: AuthHttp) {
    this.authService.authUser.subscribe(jwt => {
      if (jwt) {
        const decoded = this.jwtHelper.decodeToken(jwt);
        this.user = decoded.sub
      }
      else {
        this.user = null;
      }
    });

  }

  ionViewWillEnter() {
    this.authHttp.get(`${SERVER_URL}/secret`).subscribe(
      data => this.message = data.text(),
      err => console.log(err)
    );
  }

}
