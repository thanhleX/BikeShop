import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from '../../service/users.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {

  userId!: number;
  changePasswordForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private usersService: UsersService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));

    this.changePasswordForm = this.fb.group({
      oldPassword: ['', Validators.required],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      rePassword: ['', Validators.required]
    });
  }

  get passwordNotMatch() {
    return (
      this.changePasswordForm.get('newPassword')?.value !==
      this.changePasswordForm.get('rePassword')?.value
    );
  }

  onSubmit() {
    if (this.passwordNotMatch) {
      this.toastr.error("New passwords do not match");
      return;
    }

    const dto = {
      oldPassword: this.changePasswordForm.value.oldPassword,
      newPassword: this.changePasswordForm.value.newPassword,
      rePassword: this.changePasswordForm.value.rePassword
    };

    this.usersService.changePassword(this.userId, dto).subscribe({
      next: () => {
        this.toastr.success("Password changed successfully");
        this.router.navigate(['/admin/dashboard']);
      },
      error: () => this.toastr.error("Current password incorrect")
    });
  }
}
