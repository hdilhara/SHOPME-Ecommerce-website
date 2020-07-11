import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: "root"
})
export class LoginInterceptor implements HttpInterceptor{

    intercept(req: HttpRequest<any>, next: HttpHandler){

        
        let token=localStorage.getItem('token');

        req= req.clone({
            setHeaders:{
                Authorization: 'Bearer '+token
            }
        })
        return next.handle(req);
    }
}