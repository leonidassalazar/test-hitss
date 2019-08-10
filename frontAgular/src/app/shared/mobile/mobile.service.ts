import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MobileService {

  public baseUrl = '//localhost:8080/claro';
  public mobilesUrl = this.baseUrl + '/mobile';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.mobilesUrl);
  }

  get(id: number) {
    return this.http.get(this.mobilesUrl + '/' + id);
  }

  save(mobile: any): Observable<any> {
    let result: Observable<Object>;

    result = this.http.post(this.mobilesUrl, mobile);

    return result;
  }

  remove(id: number) {
    return this.http.delete(this.mobilesUrl + '/' + id);
  }

}
