import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/api";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {LoginModel} from "../../../../core/models/login.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  message: Message[] = [];
  hide = true;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.loginForm = this.createFormGroup();
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      username: ['', [Validators.required, Validators.email]],
      password: [null, Validators.required]
    });
  }

  revertFormGroup() {
    this.loginForm = this.createFormGroup();
  }

  login() {
    const loginModel: LoginModel = Object.assign({},
      this.loginForm.value);
    this.authenticationService.authenticate(loginModel);
  }
}
