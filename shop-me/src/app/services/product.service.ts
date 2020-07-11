import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment.prod';
import { Injectable, Injector } from '@angular/core';

@Injectable({
    providedIn: "root"
})
export class ProductService{
    //urls
    getAllProductsUrl=environment.server_rootUrl+'product/all';
    getAllCategoriesUrl=environment.server_rootUrl+'product/categories';
    addNewProductUrl=environment.server_rootUrl+'system/product/add';

    constructor(private injector:Injector){}

    private get _http(){
        return this.injector.get(HttpClient);
    }

    getAllProducts(){
        return this._http.get(this.getAllProductsUrl);
    }

    allCategories(){
        return this._http.get(this.getAllCategoriesUrl);
    }

    addNewProduct(form,file){
        let formData = new FormData();
        formData.append('title', form.value.title);
        formData.append('price', form.value.price);
        formData.append('image', file);
        formData.append('categoryId',form.value.category );

        return this._http.post(this.addNewProductUrl,formData);
    }

}