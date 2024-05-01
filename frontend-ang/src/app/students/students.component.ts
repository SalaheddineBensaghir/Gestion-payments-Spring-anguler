import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent  implements OnInit{

  public students !: any;
  public dataSource: any;
  public displayColumns = ['id','firstName','code','programId'];
  @ViewChild(MatPaginator) paginator ! : MatPaginator;
  @ViewChild(MatSort) sort ! : MatSort;
  constructor(private http : HttpClient) {
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8081/students").subscribe(value => {
      this.students = value;
      this.dataSource = new MatTableDataSource(this.students);
      this.dataSource.paginator =this.paginator;
      this.dataSource.sort = this.sort;
    },
      error => {
      console.log(error);
      }
    )
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
