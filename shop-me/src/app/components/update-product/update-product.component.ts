import { ProductService } from './../../services/product.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit,OnDestroy {

  error=null;
  product;
  id;
  hostedCategory;
  categories;
  val=false;
  subscription:Subscription;

  constructor( private service: ProductService,private activeRoute:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    //load categories
    this.loadCategories();

    this.activeRoute.paramMap.subscribe(
      paramMap=>{
        this.id=paramMap.get('product-id');
      }
    );
    
    this.service.getProductById(this.id).subscribe(
      res=>{
        this.product=res;
        this.hostedCategory=this.product.category.category;
      },
      error=>{
        console.log(error); 
      }
    );     

    //notify when add new category
    this.subscription = this.service.notifyService().subscribe(
      res=>{
        this.loadCategories();
      },
      error=>{
        this.error='Can\'t add category';  
      }
    );
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  updateProduct(form: HTMLInputElement){
    this.service.updateProduct(form.form.value,this.id).subscribe(
      res=>{
        this.error=null;
        this.router.navigate(['/system/manage/products']);
      },
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
      }
    )
  }

  loadCategories(){
    this.categories=this.service.allCategories().subscribe(
      res=>{
        this.categories=res;
      }
    );
  }


}
