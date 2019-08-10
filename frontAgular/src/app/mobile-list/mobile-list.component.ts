import { Component, OnInit } from '@angular/core';
import { MobileService } from '../shared/mobile/mobile.service';

@Component({
  selector: 'app-mobile-list',
  templateUrl: './mobile-list.component.html',
  styleUrls: ['./mobile-list.component.css']
})

export class MobileListComponent implements OnInit {
  mobiles: Array<any>;
  displayedColumns :Array<string> = ['photo', 'model', 'price', 'brand', 'date', 'code'];

  constructor(private mobileService: MobileService) { }

  ngOnInit() {
    this.mobileService.getAll().subscribe(data => {
      this.mobiles = data;
    });
  }

}
