import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  error=null;
  categories;

  file;
  constructor(private service: ProductService) { }

  ngOnInit() {
    this.categories=this.service.allCategories().subscribe(
      res=>{
        this.categories=res;
      }
    );
  }

  addNewProduct(form:HTMLInputElement){
   this.service.addNewProduct(form,this.file['0']).subscribe();
  }
  
  getFiles(event) {
    console.log(event.target.files);
    this.file=event.target.files;
  }

}
