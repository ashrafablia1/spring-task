import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Iuser, UserService } from '../../services/user.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit{


  constructor(private userService: UserService){}

  users!: Iuser[];

  ngOnInit(){
    this.getAllUsers();
  }

  getAllUsers(){
    this.userService.getAllUsersService().subscribe((i: Iuser[]) => {
      this.users = i;
    });
  }


  deleteUser(id: number) {
 this.userService.deleteUser(id).subscribe((res) =>{
  this.getAllUsers();
 }
 );
}

}
