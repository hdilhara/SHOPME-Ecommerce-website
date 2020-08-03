import { logging, error } from 'protractor';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  haveAccount=true;

  error=null;

  constructor(private service: LoginService) { }

  ngOnInit() {
  }
  haveAccountToggler(){
    this.haveAccount=!this.haveAccount;
  }

  userSignup(form: HTMLInputElement){
    this.service.createNewUser(form.value['email'],form.value['password']).subscribe(
      res=>{
        this.error=null;
        this.userLogin(form);
      },
      error=>{
        this.error=error;
      },
      ()=>{
        
      }
    );
    
  }
  userLogin(form: HTMLInputElement){
    this.service.login(form.value['email'],form.value['password']).subscribe(
      res=>{
        this.error=null;
      },
      error=>{
        this.error=error;
      }
    );
  }
}
