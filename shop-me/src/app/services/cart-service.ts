import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
    providedIn: "root"
})
export class CartService  {

    count=0;
    total=0;
    addedProducts=[];
    subject = new Subject();



    constructor(){
        if(localStorage.getItem('cartDetails')){
            let cartDetails=JSON.parse(localStorage.getItem('cartDetails'));
            this.count = cartDetails.count;
            this.addedProducts = cartDetails.addedProducts;
            this.total = cartDetails.total;
        }
    }

    addToCart(product){
        this.count++;
        this.total+=product.price;
        this.subject.next(this.count);
        if(this.addedProducts.length==0){
                this.addedProducts.push({id: product.productId,count:1,product:product});
                console.log('begin')
        }
        else{
            for(let i=0; i<this.addedProducts.length ; i++){
                if(this.addedProducts[i].id==product.productId){
                    this.addedProducts[i].count+=1;
                    break;
                }
                else if(i == this.addedProducts.length-1){
                    this.addedProducts.push({id:product.productId,count:1,product:product});
                    break;
                }
            }
        }
        
        //serialize addedProducts and save it in localstorage
        localStorage.setItem('cartDetails',JSON.stringify({addedProducts:this.addedProducts,
                                                            count:this.count,
                                                            total:this.total}));
    }

    clearCart(){
        localStorage.removeItem('cartDetails');
        this.count=0;
        this.total=0;
        this.addedProducts=[];
        this.subject.next(this.count);
    }

    notifyCartChange(){
       return this.subject.asObservable();
    }

    getProductsInCart(){
        return this.addedProducts;
    }
}