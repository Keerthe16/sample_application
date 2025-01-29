import { Routes } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { ClientApproveComponent } from './admindashboard/client-approve/client-approve.component';
import { ProductPageComponent } from './admindashboard/product-page/product-page.component';
import { AboutContactsComponent } from './about-contacts/about-contacts.component';


export const routes: Routes = [
    { path: 'navbar', component: NavbarComponent },
    { path: '', redirectTo: '/navbar', pathMatch: 'full' },
    {path:'login',component:LoginComponent},
    {path:'register-page',component:RegisterComponent},
    {path:'admin',component:AdmindashboardComponent},
    {path:'customer',component:CustomerdashboardComponent},
    {path:'client-approve',component:ClientApproveComponent},
    {path:'product',component:ProductPageComponent},
    {path:'about-contacts',component:AboutContactsComponent},
    
    
    
];


