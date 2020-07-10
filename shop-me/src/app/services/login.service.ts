import { environment } from './../../environments/environment';
import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from "jwt-decode";

import { map } from "rxjs/operators";
import { Subject } from "rxjs";
import { Router } from '@angular/router';

@Injectable({
    providedIn:"root"
})
export class LoginService{
    private subjectLog = new Subject<any>();

    loggingAlert(){
        return this.subjectLog.asObservable();
    }

    constructor(private _injector: Injector ){}

    private get _router(){
        return this._injector.get(Router);
    }

    private get _http(){
        return this._injector.get(HttpClient);
    }

    createNewUser(email,password){
        return this._http.post(
            environment.addNewUser,
            {
                username:email,
                password: password
            }
        )
        .pipe(
            map(res=>{//if succed
                this._router.navigate(['/']);
                this.subjectLog.next(this.loggedinDetails());
            })
        );
        
    }
    login(email,password){
        return this._http.post(
            environment.authenticate,
            {
                username:email,
                password: password
            }
        )
        .pipe(
            map(res=>{//if succed
                localStorage.setItem('token',res['token']);
                this._router.navigate(['/']);
                this.subjectLog.next(this.loggedinDetails());
            })
        );
        
    }

    //use jwt_decode lightweight library to decode
     getDecodedAccessToken(token: string): any {
        try{
            return jwt_decode(token);
        }
        catch(Error){
            return null;
        }
    }

    isLogged(){
        let tokenInfo = this.getDecodedAccessToken(localStorage.getItem('token')); // decode token
        if(tokenInfo == null)
        return false;
        let expireDate = tokenInfo.exp; // get token expiration dateTime
        // console.log(expireDate*1000);
        // console.log(Date.now()); 
        if(expireDate*1000 > Date.now())
        return true;
    }

    loggedinDetails(){
        let tokenInfo = this.getDecodedAccessToken(localStorage.getItem('token')); // decode token
        if(tokenInfo == null)
        return false;
        let expireDate = tokenInfo.exp; // get token expiration dateTime
        // console.log(expireDate*1000);
        // console.log(Date.now()); 
        if(expireDate*1000 > Date.now())
        return tokenInfo;
    }

    logout(){
        localStorage.removeItem('token');
    }



}