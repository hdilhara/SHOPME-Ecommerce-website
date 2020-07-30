import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';

export const InterceptorSkipHeader = 'X-Skip-Interceptor';

@Injectable({
    providedIn: "root"
})
export class LoginInterceptor implements HttpInterceptor{

    intercept(req: HttpRequest<any>, next: HttpHandler){

        let url=req.url;
        if(req.headers.has(InterceptorSkipHeader)){
            const headers = req.headers.delete(InterceptorSkipHeader);
            return next.handle(req);
        }
        if(url.includes('system') || url.includes('user')){
        let token=localStorage.getItem('token');

        req= req.clone({
            setHeaders:{
                Authorization: 'Bearer '+token
            }
        })
        }
        return next.handle(req);
    }
}