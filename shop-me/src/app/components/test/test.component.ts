import { ActivatedRoute } from '@angular/router';
import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  product={
    title:'tidssd',
    price: 141,
  }

  categories=[{categoryId:1,category: 'backery'},{categoryId:1,category: 'backery'}];

  constructor( private service: ProductService,private activeRoute:ActivatedRoute) { }

  ngOnInit() {
  }

  updateProduct(form){
    console.log(form.form.value);
  }
}
