
import { ProductService } from './../../services/product.service';
import { Component } from '@angular/core';


@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent  {

  isCollapsed = true;
  category='';


  constructor(private service:ProductService){}

  addNewCategory(){

    console.log(this.category);
    this.service.addNewCategory(this.category).subscribe(
      res=>{
        this.isCollapsed = true;
      }
    );
  }

}
