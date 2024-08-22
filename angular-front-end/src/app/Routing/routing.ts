import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from '../Pages/Page-not-found/page.not.found.component'; 
import { ProductListComponent } from '../Pages/Product-List/product-list.component';
import { CartComponent } from '../Pages/Cart/cart.component';
import { OrderProductComponent } from '../Pages/Order/order.component';

const routes: Routes = [
  { path: 'list', component: ProductListComponent },
  { path: 'cart', component: CartComponent},
  { path: 'history', component: OrderProductComponent },

  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRouting { }
