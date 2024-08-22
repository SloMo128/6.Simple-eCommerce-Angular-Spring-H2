import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/Service/product.service';
import { FeedBack } from 'src/app/Model/feedback';

@Component({
  selector: 'orderProductComponent',
  templateUrl: './order.component.html',

})
export class OrderProductComponent implements OnInit {

  orderProduct = [];
  cart = [];
  feedback = new FeedBack("", "");

  constructor(private productService: ProductService) { }

  ngOnInit() {

    //this.orderProduct= JSON.parse(localStorage.getItem('order'));

    this.orderHistory();

    

  }

  orderHistory() {
    
    this.cart = [];
    this.productService.getAllOrdersProducts().subscribe({
      next: (data: any) => {
        console.log(data);
        if (data.length !== 0) {
          this.cart = data.content.map(order => ({    
            orderId: order.id.orderId,
            productName: order.product.name,
            quantity: order.quantity,
            dateCreated: order.order.dataCreated
          }));
        };
        //window.location.reload();

      },
      error: (err: any) => {
        console.log(err);
        this.feedback = {
          feedbackType: err.feedbackType,
          feedbackmsg: err.feedbackmsg,
        };
      },
      complete: () => {},
    });
  }
}