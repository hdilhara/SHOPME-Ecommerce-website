import { environment } from './../../environments/environment';
import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { from } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService{

    getDeliveryDetailsUrl=environment.server_rootUrl+'user/get/delivery-details/';
    addDeliveryDetailsUrl=environment.server_rootUrl+'user/add/delivery-details';
    createOrderUrl=environment.server_rootUrl+'user/place/order';
    getUserOrderUrl=environment.server_rootUrl+'user/get/orders';

    constructor(private _injector: Injector){}

    private get _http(){
        return this._injector.get(HttpClient);
    }

    getDeliverDetails(userName){
        return this._http.get(this.getDeliveryDetailsUrl+userName);
    }

    addDeliverDetails(form){
        form.userName="admin";
        return this._http.post(this.addDeliveryDetailsUrl,
            form);
    }

    createOrder(checkoutProducts){
        return this._http.post(this.createOrderUrl,checkoutProducts);
    }

    getUserOrders(){
        return this._http.get(this.getUserOrderUrl);
    }
}