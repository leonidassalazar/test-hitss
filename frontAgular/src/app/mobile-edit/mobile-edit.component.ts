import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { MobileService } from '../shared/mobile/mobile.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-mobile-edit',
  templateUrl: './mobile-edit.component.html',
  styleUrls: ['./mobile-edit.component.css']
})
export class MobileEditComponent implements OnInit {

  mobile: any = {};
  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private mobileService: MobileService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.mobileService.get(id).subscribe((mobile: any) => {
          if (mobile) {
            this.mobile = mobile;
            this.mobile.href = mobile._links.self.href;
          } else {
            console.log(`Mobile with code '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/mobile-list']);
  }

  save(form: NgForm) {
    this.mobileService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(id) {
    this.mobileService.remove(id).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}
