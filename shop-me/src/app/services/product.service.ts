import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment.prod';
import { Injectable, Injector } from '@angular/core';

@Injectable({
    providedIn: "root"
})
export class ProductService{
    //urls
    getAllProductsUrl=environment.server_rootUrl+'product/all';

    constructor(private injector:Injector){}

    private get _http(){
        return this.injector.get(HttpClient);
    }

    getAllProducts(){
        return this._http.get(this.getAllProductsUrl);
    }

}