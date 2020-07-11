import { ProductService } from './../../services/product.service';
import { error } from 'protractor';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  error=null;
  product;
  id;
  categories;
  constructor( private service: ProductService,private activeRoute:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    //load categories
    this.categories=this.service.allCategories().subscribe(
      res=>{
        this.categories=res;
      }
    );

    this.activeRoute.paramMap.subscribe(
      paramMap=>{
        this.id=paramMap.get('product-id');
      }
    );
    
    this.product=this.service.getProductById(this.id).subscribe(
      res=>{

        this.product=res;
        console.log(res);
      }
    );
  }

  updateProduct(form: HTMLInputElement){
    console.log(form.form.value);
    this.service.updateProduct(form.form.value,this.id).subscribe(
      res=>{},
      error=>{
        console.log(error)
      }
    );
  }

  deleteProduct(){
    this.service.deleteProduct(this.id).subscribe(
      res=>{
        this.error=null;
           this.router.navigate(['/system/manage/products']);
      },
      error=>{
        this.error='Can\'t Delete this product!';
        this.router.navigate(['/system/manage/products']);
      }
    )
  }

}
