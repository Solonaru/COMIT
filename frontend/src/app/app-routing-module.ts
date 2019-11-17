import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './components/auth/auth.guard';

import { HomeComponent } from './views/user-side/presentation-feature/home/home.component';
import { AltHomeComponent } from './views/user-side/presentation-feature/alt-home/alt-home.component';
import { LoginComponent } from './views/user-side/auth-feature/login/login.component';
import { RegisterComponent } from './views/user-side/auth-feature/register/register.component';
import { UserComponent } from './components/roles/user/user.component';
import { PmComponent } from './components/roles/pm/pm.component';
import { AdminComponent } from './components/roles/admin/admin.component';
import { AboutComponent } from './views/user-side/presentation-feature/about/about.component';
import { ContactComponent } from './views/user-side/presentation-feature/contact/contact.component';
import { DashboardComponent } from './views/admin-side/dashboard/dashboard.component';
import { AccountManagerComponent } from './views/admin-side/account-manager/account-manager.component';
import { ChallengesComponent } from './views/user-side/event-feature/challenges/challenges.component';
import { ChallengeComponent } from './views/user-side/event-feature/challenges/challenge-list/challenge-item/challenge/challenge.component';
import { InstructionsComponent } from './views/user-side/event-feature/challenges/challenge-list/challenge-item/challenge/instructions/instructions.component';
import { CodeComponent } from './views/user-side/event-feature/challenges/challenge-list/challenge-item/challenge/code/code.component';
import { ResourcesComponent } from './views/user-side/event-feature/challenges/challenge-list/challenge-item/challenge/resources/resources.component';
import { SolutionsComponent } from './views/user-side/event-feature/challenges/challenge-list/challenge-item/challenge/solutions/solutions.component';
import { EventsComponent } from './views/user-side/event-feature/events.component';
import { GeneralManagerComponent } from './views/admin-side/general-manager/general-manager.component';
import { EventManagerComponent } from './views/admin-side/general-manager/event-manager/event-manager.component';
import { ChallengeManagerComponent } from './views/admin-side/general-manager/challenge-manager/challenge-manager.component';
import { SolutionManagerComponent } from './views/admin-side/general-manager/solution-manager/solution-manager.component';

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
        path: 'events',
        component: EventsComponent
    },
    {
        path: 'challenges/:id',
        component: ChallengesComponent
    },
    {
        path: 'challenge/:id',
        component: ChallengeComponent,
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
        path: 'general-manager',
        component: GeneralManagerComponent,
        children: [
            {
                path: 'event-manager',
                component: EventManagerComponent
            },
            {
                path: 'challenge-manager',
                component: ChallengeManagerComponent
            },
            {
                path: 'solution-manager',
                component: SolutionManagerComponent
            },
            {
                path: '',
                redirectTo: 'event-manager',
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
