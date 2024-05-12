import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './update.component.html',
  styleUrl: './update.component.css'
})
export class UpdateComponent {

form: FormGroup = new FormGroup({
  firstName: new FormControl(''),
  lastName: new FormControl(''),
  username: new FormControl(''),
  password: new FormControl(''),
  id: new FormControl('')
});
submitted = false;
id!: number;
usernameValid: boolean = true;
oldUsername!: string;

constructor(private formBuilder: FormBuilder, private userservice: UserService, private route: ActivatedRoute) {}

ngOnInit(): void {
  this.form = this.formBuilder.group({
    firstName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(36)]],
    lastName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(36)]],
    username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9]*'), Validators.maxLength(36)]],
    password: ['', [Validators.required, Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$'), Validators.minLength(8)]],
  });

   this.route.params.subscribe( params =>
    this.id = params['id'] )

   this.userservice.getUserById(this.id).subscribe( user => {
    this.form.patchValue ({
        firstName: user.firstName,
        lastName: user.lastName,
        username: user.username,
        password: user.password
    });
    this.oldUsername=user.username;
   });

}

get formCon(): { [key: string]: AbstractControl} {
  return this.form.controls;
}

updateUser(): void {
  this.submitted = true;



  if(this.form.invalid) {
    return;
  }

  this.userservice.updateUser(this.id ,this.form.value).subscribe({  
    next:() => {alert('User updated Successfully');
    this.submitted = false;
    this.form.reset();
    },
    error: () => {alert('Username already exists, try anither one')}
  

  
  });

}

validateUsername() {
  const username = this.form.value['username'];
  if(this.oldUsername !== username){
    return this.userservice.isUsernameAvaliable(username).subscribe(available => {
      this.usernameValid = available;
    });
    
  }
  return;
}
}




