
import { ErrorHandlerService } from './services/error-handler.service';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './components/login/login.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModule.forRoot(),
    RouterModule.forRoot([
      {path:'',component:HomePageComponent},
      // {path: '/system/manage/products', component:ManageProductsComponent},
      {path:'login',component:LoginComponent}
    ])
  ],
  providers: [
    {provide: ErrorHandler , useClass:ErrorHandlerService}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
