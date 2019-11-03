import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './components/roles/user/user.component';
import { PmComponent } from './components/roles/pm/pm.component';
import { AdminComponent } from './components/roles/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from 'src/views/home/home.component';
import { AltHomeComponent } from './components/alt-home/alt-home.component';
import { AboutComponent } from 'src/views/about/about.component';
import { EventsComponent } from 'src/views/events/events.component';
import { EventComponent } from 'src/views/event/event.component';
import { InstructionsComponent } from 'src/views/event/instructions/instructions.component';
import { CodeComponent } from 'src/views/event/code/code.component';
import { ResourcesComponent } from 'src/views/event/resources/resources.component';
import { SolutionsComponent } from 'src/views/event/solutions/solutions.component';

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'althome',
        component: AltHomeComponent
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
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },
    {
        path: 'about',
        component: AboutComponent
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
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
