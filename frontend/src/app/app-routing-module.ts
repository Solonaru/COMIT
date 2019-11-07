import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './views/home/home.component';
import { AltHomeComponent } from './views/alt-home/alt-home.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { UserComponent } from './components/roles/user/user.component';
import { PmComponent } from './components/roles/pm/pm.component';
import { AdminComponent } from './components/roles/admin/admin.component';
import { AboutComponent } from './views/about/about.component';
import { ContactComponent } from './views/contact/contact.component';
import { EventsComponent } from './views/events/events.component';
import { EventComponent } from './views/event/event.component';
import { InstructionsComponent } from './views/event/instructions/instructions.component';
import { CodeComponent } from './views/event/code/code.component';
import { ResourcesComponent } from './views/event/resources/resources.component';
import { SolutionsComponent } from './views/event/solutions/solutions.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { AccountManagerComponent } from './views/account-manager/account-manager.component';
import { EventManagerComponent } from './views/event-manager/event-manager.component';
import { AuthGuard } from './components/auth/auth.guard';

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'althome',
        component: AltHomeComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },
    {
        path: 'user',
        component: UserComponent
    },
    {
        path: 'pm',
        component: PmComponent
    },
    {
        path: 'admin',
        component: AdminComponent
    },
    {
        path: 'about',
        component: AboutComponent
    },
    {
        path: 'contact',
        component: ContactComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
        /* canActivate: AdminGuard */
    },
    {
        path: 'account-manager',
        component: AccountManagerComponent,
    },
    {
        path: 'event-manager',
        component: EventManagerComponent
    },
    {
        path: 'events',
        component: EventsComponent
    },
    {
        path: 'event/:id',
        component: EventComponent,
        children: [
            {
                path: 'instructions',
                component: InstructionsComponent
            },
            {
                path: 'code',
                component: CodeComponent
            },
            {
                path: 'resources',
                component: ResourcesComponent
            },
            {
                path: 'solutions',
                component: SolutionsComponent
            },
            {
                path: '',
                redirectTo: 'instructions',
                pathMatch: 'full'
            }
        ]
    },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
