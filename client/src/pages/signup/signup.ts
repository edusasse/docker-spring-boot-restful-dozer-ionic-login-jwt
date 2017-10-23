import {Component, ViewChild} from '@angular/core';
import {LoadingController, ToastController} from 'ionic-angular';
import {AuthService} from "../../providers/auth-service";
import {FormGroup, FormBuilder, FormControl, Validators} from "@angular/forms";
import {UsuarioDTO} from "../interfaces.ts";

@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html'
})
export class SignupPage {
  myForm: FormGroup;
  usuarioDTO: IUsuarioDTO;

  constructor(private readonly authService: AuthService,
              private readonly loadingCtrl: LoadingController,
              private readonly toastCtrl: ToastController,
              private readonly formBuilder: FormBuilder) {
                this.usuarioDTO = { pessoa: { identity: {} }} as IUsuarioDTO;
  }

  ngOnInit(): any {

    this.myForm = this.formBuilder.group({
        'primeiroNome': ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(15)])],
        'segundoNome': ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(15)])],
        'apelido': ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(15)])],
        'senha': ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.maxLength(255)])]
    });       
  }

  signup() {
    let loading = this.loadingCtrl.create({
      spinner: 'bubbles',
      content: 'Signing up ...'
    });

    loading.present();

    this.authService
      .signup(this.usuarioDTO)
      .finally(() => loading.dismiss())
      .subscribe(
        (jwt) => this.showSuccesToast(jwt),
        err => this.handleError(err));
  }

  private showSuccesToast(jwt) {
    if (jwt !== 'EXISTS') {
      const toast = this.toastCtrl.create({
        message: 'Sign up successful',
        duration: 3000,
        position: 'bottom'
      });

      toast.present();
    }
    else {
      const toast = this.toastCtrl.create({
        message: 'Username already registered',
        duration: 3000,
        position: 'bottom'
      });

      toast.present();
    }
  }

  isValid(field: string) {
    let formField = this.myForm.get(field);
    return formField.valid || formField.pristine;
  }

  handleError(error: any) {
    let message = `Unexpected error occurred`;

    const toast = this.toastCtrl.create({
      message,
      duration: 5000,
      position: 'bottom'
    });

    toast.present();
  }

}
