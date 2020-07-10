import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css']
})
export class ManageProductComponent implements OnInit {

  
  products;

  constructor(private service:ProductService) {
    this.getAllProducts();
   }

  ngOnInit() {
  }

  getAllProducts(){
    this.service.getAllProducts().subscribe(
      res=>{
        this.products=res;
      }
    );
  }


}