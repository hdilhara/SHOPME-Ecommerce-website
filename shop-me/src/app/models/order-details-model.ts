import { ProductQuantityModel } from './product-quantity-model';

export class OrderDetailsModel{
    productQuantity=[];//:any[];

    constructor( public orderPerson: string,
        public address: string,
        public orderPrice: number){}

    addproductQuantity(val){
        this.productQuantity.push(val);
    }
}