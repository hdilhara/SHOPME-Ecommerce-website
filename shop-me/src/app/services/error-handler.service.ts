import { HttpHeaders } from '@angular/common/http';
import { ErrorHandler } from '@angular/core';


export class ErrorHandlerService implements ErrorHandler{
    handleError(res: any){
        if(res['status']===0)
        alert('You are not connected! or not authorized');
        else if(res['status']===404)
        alert('Page not found!');
        else if(res['status']===403)
        alert('Access denied!');
        else if(res['status']===401)
        alert('Please login first to see resources');
    }
}