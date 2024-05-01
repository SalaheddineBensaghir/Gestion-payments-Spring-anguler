import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Payment} from "../model/payment.module";

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {
  public  paymentsState:any = {
    payment : [],
    };
private host : string = "http://localhost:8081";
  constructor(private  http : HttpClient) { }

  public Payment(page:number=1,size:number=4){
    return this.http.get(`${this.host}/payments?_page=${page}&_limit=${size}`,{observe:'response'})
  }
}
