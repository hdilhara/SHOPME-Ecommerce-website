import { environment } from './../../environments/environment';
import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: "root"
})
export class OrderService{

    getAllOrdersUrl = environment.server_rootUrl+'system/orders';

    constructor(private injector: Injector){
    }

    private get _http(){
        return this.injector.get(HttpClient);
    }
    getAllOrders(){
        return this._http.get(this.getAllOrdersUrl);
    }
}