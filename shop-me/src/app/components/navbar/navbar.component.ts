import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLogged;
  user='';
  isCollapsed = true;

  constructor(private service:LoginService) {
    if(service.isLogged){
      this.isLogged=true;
      this.user=service.loggedinDetails()['sub'];
    }
    service.loggingAlert().subscribe(
      res=>{
        if(res){
          this.isLogged=true;
          this.user=res['sub'];
          console.log(res['sub'])
        }
      }
    );
   }

  ngOnInit() {
  }

  logout(){
    this.service.logout();
    this.isLogged=false;
  }


}
