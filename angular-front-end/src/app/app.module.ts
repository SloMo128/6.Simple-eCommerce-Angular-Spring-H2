import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ErrorCodeService } from './Service/http.error.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { GlobalHttpInterceptorService } from './Service/global-http-Interceptor.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppRouting } from './Routing/routing';

import { HeaderComponent } from './Header/header.component';

import { ProductListComponent } from './Pages/Product-List/product-list.component';
import { PageNotFoundComponent } from './Pages/Page-not-found/page.not.found.component';
import { ProductService } from './Service/product.service';
import { CartComponent } from './Pages/Cart/cart.component';
import { OrderProductComponent } from './Pages/Order/order.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PageNotFoundComponent,
    ProductListComponent,
    CartComponent,
    OrderProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxPaginationModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRouting,
    FormsModule
  ],
  providers: [
    ProductService,
    ErrorCodeService,
    { provide: HTTP_INTERCEPTORS, useClass: GlobalHttpInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
