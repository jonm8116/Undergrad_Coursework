import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class RegisterService {

    constructor(private http:HttpClient) {}

    addUser(user) {
        let body = JSON.stringify(user);
        return this.http.post('/api/users/register', body, httpOptions);
    }
}
