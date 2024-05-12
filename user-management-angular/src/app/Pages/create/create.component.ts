import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './create.component.html',
  styleUrl: './create.component.css'
})
export class CreateComponent {
  form: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl('')
  });
  submitted = false;
  usernameValid: boolean = true;
  
  constructor(private formBuilder: FormBuilder, private userservice: UserService) {}
  
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(36)]],
      lastName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(36)]],
      username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9]*'), Validators.maxLength(36)]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$'), Validators.minLength(8)]],
    });

  }


  get formCon(): { [key: string]: AbstractControl} {
    return this.form.controls;
  }


 saveUser(){
  this.submitted = true;

  if(this.form.invalid) {
    return;
  }

this.userservice.saveNewUser(this.form.value).subscribe({  
 next:() => {alert('User Created Successfully');
 this.submitted = false;
 this.form.reset();
 },
 error: () => {alert('Username already exists, try anither one')}
});
 }



 validateUsername() {
  const username = this.form.value['username'];
    return this.userservice.isUsernameAvaliable(username).subscribe(available => {
      this.usernameValid = available;
    });
    
  }

}