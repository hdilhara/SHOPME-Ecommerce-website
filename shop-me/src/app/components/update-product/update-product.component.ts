import { error } from 'protractor';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  error=null;

  constructor() { }

  ngOnInit() {
  }

  updateProduct(form: HTMLInputElement){

  }

}
