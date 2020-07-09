import { LoginService } from './../../services/login.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit,OnDestroy {

  subscription: Subscription;

  isCollapsed = true;
  isLogged=false;
  user;

  constructor(private service: LoginService) { }

  ngOnInit() {

    this.subscription=this.service.notifyWhenLogged().subscribe(
      tokenInfo=>{
        this.isLogged=true;
        this.user=tokenInfo['sub'];
      }
    );

  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }


}
