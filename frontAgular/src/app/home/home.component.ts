import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';

export interface User
{
  login: string;
  password: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  user : User;
  isAuthenticated: boolean;

  constructor(private oktaAuth: OktaAuthService) { }

  async ngOnInit() {

    this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    // Subscribe to authentication state changes
    this.oktaAuth.$authenticationState.subscribe(
      (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
    );
    console.log(this.isAuthenticated);
  }

  async save(form: NgForm) {
    this.mobileService.login(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
