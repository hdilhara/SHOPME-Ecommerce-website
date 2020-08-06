import { OrderService } from './../../services/order-service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-orders',
  templateUrl: './manage-orders.component.html',
  styleUrls: ['./manage-orders.component.css']
})
export class ManageOrdersComponent implements OnInit {

  orders;
  constructor(private service:OrderService) { }

  ngOnInit() {
    this.service.getAllOrders().subscribe(
      res=>{
        this.orders = res;
        console.log(res)
      }
    );
  }


}
