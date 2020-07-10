import { ErrorHandler } from '@angular/core';


export class ErrorHandlerService implements ErrorHandler{
    handleError(error: any){
        if(error['status']===0)
        alert('You are not connected!(Server is down!)');
        else if(error['status']===404)
        alert('Page not found!');
        else if(error['status']===403)
        alert('Access denied!');
        else if(error['status']===401)
        alert('Please login first to see resources');
    }
}