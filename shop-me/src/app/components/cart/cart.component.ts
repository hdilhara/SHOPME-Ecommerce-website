import { CartService } from './../../services/cart-service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products;
  total;
  isEmpty=false;
  constructor(private service: CartService) { }

  ngOnInit() {
    this.products=this.service.addedProducts;
    this.total=this.service.total;
  }

  clearCart(){
    this.service.clearCart();
    this.products=null;
    this.isEmpty=true;
    this.total=0;
  }

}
