import { Injectable } from '@angular/core';
import { JwtHelper, AuthHttp } from "angular2-jwt";
import { AuthService } from "../../providers/auth-service";
import { SERVER_URL } from "../../config";
import { User } from "../../app/model/user";
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
  data: User;
  user: string;

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
    this.getAuthenticatedUserInfo();
  }

  getLogin() {
     return this.user;
  }

  getAuthenticatedUserInfo() {
    this.authHttp.get(`${SERVER_URL}/users/` + this.user)
          .flatMap((data) => data.json())
          .subscribe((data) => {
            console.log(data)
          });
        }

}
