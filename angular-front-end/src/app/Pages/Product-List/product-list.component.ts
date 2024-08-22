import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeedBack } from 'src/app/Model/feedback';
import { Product } from 'src/app/Model/product.model';
import { ProductService } from 'src/app/Service/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
})
export class ProductListComponent implements OnInit {

  product: Product[] = [];
  feedback = new FeedBack("", "");
  isLoading: boolean = true;
  isLoadingPage: boolean = false;
  cart: { name: string, price: number, quantity: number }[] = [];
  total: number = 0;
  constructor(
    private productService: ProductService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.getProductList();
    this.feedback = { feedbackType: '', feedbackmsg: '' };
  }

  getProductList(): void {
    this.product = [];
    //let params = new HttpParams();

    // If you need pagination or sorting
    /*
    params = params.append('page', "" + this.pagination);
    params = params.append('size', "" + this.customerPage);
    params = params.append('sort', this.sortField);
    params = params.append('order', this.sortOrder);
    */

    this.productService.getListProduct().subscribe({
      next: (data: any) => {
        if (data.length !== 0) {
          console.log(data)
          this.product = data.products;
          this.isLoading = false;
          this.isLoadingPage = true;
        }
      },
      error: (err: any) => {
        console.log(err);
        this.isLoading = false;
        this.feedback = {
          feedbackType: err.feedbackType,
          feedbackmsg: err.feedbackmsg,
        };
        console.log(JSON.stringify(this.feedback));
        throw new Error();
      },
      complete: () => {
        this.feedback = { feedbackType: 'success', feedbackmsg: 'loaded' };
      },
    });
  }

  addCart(product: any) {
    if (product.quantity > 0) {
      const cartItem = {
        id: product.id,
        name: product.name,
        price: product.price,
        quantity: product.quantity
      };
      this.cart.push(cartItem);
      this.total += product.price * product.quantity
      product.quantity = 0; // Reset quantity after adding to cart
    } else {
      // Handle the case where quantity is 0 or invalid
      console.error('Invalid quantity');
    }
  }

  saveDataAndNavigate() {
    localStorage.setItem('cartList', JSON.stringify(this.cart));
    localStorage.setItem('totalPrice', this.total.toString())
    this.router.navigate(['/cart']);
  }

  
}
