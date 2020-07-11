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
    getProductUrl=environment.server_rootUrl+'product/';//append id 
    updateProductDetailsUrl=environment.server_rootUrl+'system/product/update/details';
    deleteProductUrl=environment.server_rootUrl+"system/product/delete/";//append id

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

    getProductById(id){
        return this._http.get(this.getProductUrl+id);
    }

    updateProduct(form,id){ //provide title price category
        return this._http.post(this.updateProductDetailsUrl,{
            "title":form.title,
            "price":form.price,
            "productId":id,
            "categoryId":form.category
        });
    }

    addNewProduct(form,file){
        let formData = new FormData();
        formData.append('title', form.value.title);
        formData.append('price', form.value.price);
        formData.append('image', file);
        formData.append('categoryId',form.value.category );

        return this._http.post(this.addNewProductUrl,formData);
    }

    deleteProduct(id: number){
        console.log(id)
        return this._http.delete(this.deleteProductUrl+id);
    }

}