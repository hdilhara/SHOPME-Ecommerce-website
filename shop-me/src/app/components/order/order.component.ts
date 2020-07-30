import { CartService } from './../../services/cart-service';
import { UserService } from './../../services/user-service';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  isLogedIn;
  user;
  totalPayment;
  have=false;
  deliveryDetails;
  

  constructor(private service: LoginService, private userService:UserService, private cartService:CartService) { 
    
  }

  ngOnInit() {
    this.isLogedIn = this.service.isLogged();
    if(this.isLogedIn){
      this.user = this.service.loggedinDetails().sub;
      this.getDeliverDetails(this.user);
      this.totalPayment=this.cartService.total;
    }
  }

  addDeliverDetails(form){
    this.userService.addDeliverDetails(form.value)
    .subscribe(
      res=>{
        this.have=true;
      },
      error=>{
        
      }
    );
  }

  getDeliverDetails(userName){
    this.userService.getDeliverDetails(userName)
    .subscribe(
      res=>{
        this.deliveryDetails=res;
        this.have=true;
      },
      error=>{
        this.deliveryDetails={userName:'',
        name:'',
        streetAddress1:'',
        streetAddress2:'',
        city:'',
        contactNumber:null
      };
    
      }
    );
  }

}
