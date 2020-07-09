import { logging } from 'protractor';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from "jwt-decode";

import { map } from "rxjs/operators";
import { Subject } from "rxjs";
import { Router } from '@angular/router';

@Injectable({
    providedIn:"root"
})
export class LoginService{

    private subject = new Subject<any>();

    constructor(private http: HttpClient, private router: Router){}

    createNewUser(email,password){
        console.log(email,password);
        this.http.post(
            environment.addNewUser,
            {
                username:email,
                password: password
            }
        )
        .pipe(
            map(res=>{//if succed
                this.router.navigate(['/']);
                this.subject.next(this.loggedinDetails);
            })
        )
        .subscribe();
    }
    login(email,password){
        console.log(email,password);
        this.http.post(
            environment.authenticate,
            {
                username:email,
                password: password
            }
        )
        .pipe(
            map(res=>{//if succed
                localStorage.setItem('token',res['token']);
                this.router.navigate(['/']);
                this.subject.next(this.loggedinDetails);
            })
        )
        .subscribe();
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
        console.log(tokenInfo);
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
        console.log(tokenInfo);
        // console.log(expireDate*1000);
        // console.log(Date.now()); 
        if(expireDate*1000 > Date.now())
        return tokenInfo;
    }

    logout(){
        localStorage.removeItem('token');
    }

    notifyWhenLogged(){
        return this.subject.asObservable();
    }

}