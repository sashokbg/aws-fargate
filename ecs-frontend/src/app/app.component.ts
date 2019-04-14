import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  cars = [];

  constructor(
    private httpClient: HttpClient
  ) {

  }

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8080/cars')
      .subscribe((cars: any) => {
        this.cars = cars;
      });
  }
}
