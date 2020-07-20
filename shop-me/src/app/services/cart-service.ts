import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
    providedIn: "root"
})
export class CartService{

    count=0;
    addedProducts=[];
    subject = new Subject();

    addToCart(product){
        this.count++;
        this.subject.next(this.count);
        if(this.addedProducts.length==0){
                this.addedProducts.push({id: product.productId,count:1});
                console.log('begin')
        }
        else{
            for(let i=0; i<this.addedProducts.length ; i++){
                if(this.addedProducts[i].id==product.productId){
                    this.addedProducts[i].count+=1;
                    break;
                }
                else if(i == this.addedProducts.length-1){
                    this.addedProducts.push({id:product.productId,count:1});
                    break;
                }
            }
        }
        
        console.log(this.addedProducts)
    }

    notifyCartChange(){
       return this.subject.asObservable();
    }
}