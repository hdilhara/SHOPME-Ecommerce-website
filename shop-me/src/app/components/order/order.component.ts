import { Router } from '@angular/router';
import { OrderDetailsModel } from './../../models/order-details-model';
import { CartService } from './../../services/cart-service';
import { UserService } from './../../services/user-service';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { ProductQuantityModel } from 'src/app/models/product-quantity-model';

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
  orderCreated=false;
  

  constructor(private service: LoginService, private userService:UserService, private cartService:CartService,private router:Router) { 
    
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

  createOrder(){

    let checkoutProducts=this.cartService.getProductsInCart();
    let orderDetails=new OrderDetailsModel(this.deliveryDetails.name,
      this.deliveryDetails.streetAddress1+' '+this.deliveryDetails.streetAddress2+' '+this.deliveryDetails.city,
      this.totalPayment);
    for(var i=0; i<checkoutProducts.length; i++){
      let product=checkoutProducts[i];
      let productQuantity = new ProductQuantityModel(product.id,product.count);
      orderDetails.addproductQuantity(productQuantity);
    } 
    this.userService.createOrder(orderDetails)
    .subscribe(
      res=>{
        this.orderCreated=true;
        setTimeout(()=>{this.router.navigate(['/']);}, 3000);
        this.cartService.clearCart();
      }
    );
  }

  navigateToHomePage(){
    console.log('timeout');
    
  }

}
