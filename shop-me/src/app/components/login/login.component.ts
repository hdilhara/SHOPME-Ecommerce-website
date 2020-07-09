import { logging } from 'protractor';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  haveAccount=true;
  constructor(private service: LoginService) { }

  ngOnInit() {
  }
  haveAccountToggler(){
    this.haveAccount=!this.haveAccount;
  }

  userSignup(form: HTMLInputElement){
    this.service.createNewUser(form.value['email'],form.value['password']);
  }
  userLogin(form: HTMLInputElement){
    this.service.login(form.value['email'],form.value['password']);
  }
}
