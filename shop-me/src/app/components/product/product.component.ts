import { CartService } from './../../services/cart-service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { ProductService } from './../../services/product.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { error } from 'protractor';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit, OnDestroy {

  error=null;
  success = null;
  categories;
  file;
  subscription:Subscription;

  constructor(private service: ProductService) { }

  ngOnInit() {
    this.loadCategories();
    this.subscription=this.service.notifyService().subscribe(
      res=>{
        this.loadCategories();
      }
      
    );
    
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  

  addNewProduct(form:NgForm){
     this.service.addNewProduct(form,this.file['0']).subscribe(
        res=>{
          this.success=true;
          this.error=false;
          form.resetForm();
        },
        error=>{
          console.log(error)
          this.error=true;
        }
     );
  }
  
  getFiles(event) {
    console.log(event.target.files);
    this.file=event.target.files;
  }

  loadCategories(){
    this.categories=this.service.allCategories().subscribe(
      res=>{
        this.categories=res;
      }
    );
  }

}
