import { CartService } from './../../services/cart-service';
import { ProductService } from './../../services/product.service';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  products;
  categories;
  category_type;
  constructor(private service:ProductService,private cartService:CartService) {
   }

  ngOnInit() {
    this.getAllProducts();
    this.getAllCategories();
  }

  addProduct(product){
    this.cartService.addToCart(product);
  }

  getProductsByCategory(id,category){
    this.service.getProductsByCategory(id)
    .subscribe(
      res=>{
        this.products =res;
        this.category_type=category;
      }
    );
  }

  getAllCategories(){
    this.service.allCategories().subscribe(
      res=>{
        this.categories=res;
      }
    );
  }
  getAllProducts(){
    this.category_type='All';
    this.service.getAllProducts().subscribe(
      res=>{
        this.products=res;
      }
    );
  }
}
