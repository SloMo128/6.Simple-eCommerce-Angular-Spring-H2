import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forEach } from '@angular/router/src/utils/collection';
import { FeedBack } from 'src/app/Model/feedback';
import { ProductService } from 'src/app/Service/product.service';

@Component({
  selector: 'app-component',
  templateUrl: './cart.component.html',
})
export class CartComponent implements OnInit {
  cart: any[] = [];
  totalPrice: number = 0;
  feedback = new FeedBack("", "");
  productId: number[];
  quantity: number[];
  paymentInProgress = false;

  constructor(
    private router: Router,
    private productService: ProductService,
  ) { }

  ngOnInit() {
    this.cart = JSON.parse(localStorage.getItem("cartList"));
    if (!this.cart) {
      alert("Cart non trovato!");
      this.router.navigate(['']);
      return;
    }
    this.totalPrice = +localStorage.getItem("totalPrice");
    if (!this.totalPrice) {
      alert("Price non trovato!");
      this.router.navigate(['']);
      return;
    }
  }

  pay() {
    //const date = new Date().toISOString().split('T')[0]; // YYYY-MM-DD format
    let orderStatus = {
      status: "PAID"
    }

    this.productService.addOrder(orderStatus).subscribe({
      next: (data) => {
        this.paymentInProgress = true;
        this.orderproduct(data.id);
        this.feedback = {
          feedbackType: "success",
          feedbackmsg: "Payment successful!",
        };
      },
      error: (err: any) => {
        console.log(err);
        this.feedback = {
          feedbackType: err.feedbackType,
          feedbackmsg: err.feedbackmsg,
        };
        throw new Error();
      },
      complete: () => {
      },
    });

  }

  orderproduct(orderId) {
    type CartItem = {
      id: number;
      quantity: number;
    };

    const cartListString: string = localStorage.getItem("cartList");

    if (cartListString) {
      const cartItems: CartItem[] = JSON.parse(cartListString);

      cartItems.forEach((item) => {
        const quantity = item.quantity;

        let params = new HttpParams().set('quantity', quantity.toString());

        // Sending individual requests for each item
        this.productService.addOrderProduct(orderId, item.id, params).subscribe({
          next: (data) => {
            console.log('Order product added successfully:', data);
          },
          error: (err: any) => {
            console.log('Error occurred:', err);
            this.feedback = {
              feedbackType: err.feedbackType,
              feedbackmsg: err.feedbackmsg,
            };
            throw new Error('Error adding order product');
          },
          complete: () => {
            console.log('Request completed');
          },
        });

        console.log('Request details:', orderId, item.id, quantity); // Print the ID and quantity for debugging
      });
    }
  }
}
