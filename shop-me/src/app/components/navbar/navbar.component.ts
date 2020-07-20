import { Subscription } from 'rxjs';
import { CartService } from './../../services/cart-service';
import { LoginService } from './../../services/login.service';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit,OnDestroy {

  isLogged;
  user='';
  isCollapsed = true;
  cartCount;
  subscription: Subscription;

  constructor(private service:LoginService,private cartService:CartService) {

    if(service.isLogged()){
      console.log('logged!');
      this.isLogged=true;
      this.user=service.loggedinDetails()['sub'];
    }
    else{
      console.log('not logged!');
      this.isLogged=false;
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
    this.cartCount=this.cartService.count;
    this.subscription= this.cartService.notifyCartChange().subscribe(
      res=>{
        this.cartCount=res;
      }
    )
  }

  logout(){
    this.service.logout();
    this.isLogged=false;
  }

ngOnDestroy(){this.subscription.unsubscribe();}  

}
