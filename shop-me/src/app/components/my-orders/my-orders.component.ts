import { UserService } from './../../services/user-service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

  orders;

  constructor(private service: UserService) { }

  ngOnInit() {
    this.service.getUserOrders().subscribe(
      res=>{
        console.log(res);
        this.orders=res;;
      }
    );
  }



}
