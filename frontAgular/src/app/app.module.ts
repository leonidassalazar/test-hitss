import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { DigitOnlyModule } from '@uiowa/digit-only';
import { OktaCallbackComponent, OktaAuthModule } from '@okta/okta-angular';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './shared/okta/auth.interceptor';

import { AppComponent } from './app.component';
import { MobileService } from './shared/mobile/mobile.service';
import { MobileListComponent } from './mobile-list/mobile-list.component';
import { MobileEditComponent } from './mobile-edit/mobile-edit.component';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'mobile-list',
    component: MobileListComponent
  },
  {
    path: 'mobile-add',
    component: MobileEditComponent
  },
  {
    path: 'mobile-edit/:id',
    component: MobileEditComponent
  },
  {
    path: 'implicit/callback',
    component: OktaCallbackComponent
  }
];

const config = {
  issuer: 'https://dev-671057.okta.com/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oa13dkn0c1AeoQCm357'
};

@NgModule({
  declarations: [
    AppComponent,
    MobileListComponent,
    MobileEditComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatTableModule,
    MatToolbarModule,
    FormsModule,
    DigitOnlyModule,
    RouterModule.forRoot(appRoutes),
    OktaAuthModule.initAuth(config)
  ],
  providers: [MobileService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
