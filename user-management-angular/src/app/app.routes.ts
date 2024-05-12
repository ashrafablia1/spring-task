import { Routes} from '@angular/router';
import { HomePageComponent } from './Pages/home-page/home-page.component';
import { CreateComponent } from './Pages/create/create.component';
import { UpdateComponent } from './Pages/update/update.component';

export const routes: Routes = [
    { path: '', component: HomePageComponent, title: "Home"},
    { path: 'create', component: CreateComponent, title: "Create User"},
    { path: 'update/:id', component: UpdateComponent, title: "Update User"}

];


