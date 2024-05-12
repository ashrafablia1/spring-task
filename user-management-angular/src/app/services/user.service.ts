import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Iuser {
    "id": number;
    "firstName": string,
    "lastName": string,
    "username": string,
    "password": string
}


@Injectable({
  providedIn: 'root'
})
export class UserService {
  
 
  
  private url = 'http://localhost:8080/api'

  constructor(private httpClient: HttpClient) {}

  getAllUsersService(): Observable<Iuser[]> {
    return this.httpClient.get<Iuser[]>(this.url + '/all/users');
  }


  saveNewUser(form: any) {
        return this.httpClient.post(this.url +'/create/user', form);
  }

  getUserById(id: any){
    return this.httpClient.get<Iuser>(this.url + '/user/' + id);
  }

  updateUser(id: number,form: any) {
    
    return this.httpClient.put(this.url + '/update/user/'+id, form)
  }


  isUsernameAvaliable(username: string): Observable<boolean> {
    return this.httpClient.get<boolean>(this.url+'/usernameAvailable/'+username)
  }


  deleteUser(id: number) {
   return this.httpClient.delete(this.url+'/delete/user/'+id, { responseType: 'text' });
  }
}
