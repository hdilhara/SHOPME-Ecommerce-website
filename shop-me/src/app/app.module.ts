import { LoginInterceptor } from './services/LoginInterceptor.service';

import { ErrorHandlerService } from './services/error-handler.service';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './components/login/login.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ManageProductComponent } from './components/manage-product/manage-product.component';
import { ProductComponent } from './components/product/product.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { TestComponent } from './components/test/test.component';
import { AddCategoryComponent } from './components/add-category/add-category.component';
import { CartComponent } from './components/cart/cart.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    NavbarComponent,
    ManageProductComponent,
    ProductComponent,
    UpdateProductComponent,
    TestComponent,
    AddCategoryComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModule.forRoot(),
    RouterModule.forRoot([
      {path:'',component:HomePageComponent},
      {path:'cart', component: CartComponent},
      {path: 'system/update/product/:product-id', component: UpdateProductComponent },
      {path: 'system/manage/products', component:ManageProductComponent },
      {path: 'system/add/product', component: ProductComponent },
      {path:'login',component:LoginComponent}
    ])
  ],
  providers: [
    {provide: ErrorHandler , useClass:ErrorHandlerService},
    {provide: HTTP_INTERCEPTORS, useClass: LoginInterceptor, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
