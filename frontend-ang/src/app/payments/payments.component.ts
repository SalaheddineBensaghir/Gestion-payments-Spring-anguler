import {Component, OnInit, ViewChild} from '@angular/core';
import {PaymentsService} from "../services/payments.service";
import {Router} from "@angular/router";
import {Payment} from "../model/payment.module";
import {MatTableDataSource} from "@angular/material/table";
import {HttpClient} from "@angular/common/http";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort, MatSortModule} from "@angular/material/sort";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements OnInit{

  public currentPage !: number;
  public pageSize !: number;
  public  payments :any;
  public dataSource : any;
  public  displayedColumns = ['id','date','amount','type','status','firstName'];
 @ViewChild(MatPaginator) paginator ! : MatPaginator;
  @ViewChild(MatSort) sort ! : MatSort;
  constructor( public paymentsService : PaymentsService,private router:Router,private http : HttpClient) {
 }

  ngOnInit(): void {
  this.http.get("http://localhost:8081/payments").subscribe(
    {
      next : data => {
this.payments = data;
this.dataSource= new  MatTableDataSource(this.payments);

this.dataSource.paginator= this.paginator;
this.dataSource.sort = this.sort;
},
      error : err => {
        console.log(err)
      }
    }
  )
  }

}
