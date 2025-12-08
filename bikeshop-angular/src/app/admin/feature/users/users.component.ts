import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../service/users.service';
import { Router } from '@angular/router';
import { User } from '../../../dto/User';
import { ToastrService } from 'ngx-toastr';
import { JwtPayloadDto } from '../../../dto/JwtPayloadDto';
import { AuthService } from '../../../auth/service/auth.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  userInfo: JwtPayloadDto | undefined;

  constructor(
    private usersService: UsersService,
    private router: Router,
    private toastrService: ToastrService, private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.getAllUsers();
    this.getUserInfo();
  }

  private getAllUsers(): void {
    this.usersService.getAllUser().subscribe({
      next: (res) => {
        this.users = res.result;
        console.log(this.users)
      }
    });
  }

  deleteUser(id: number): void {
    this.usersService.deleteUserById(id).subscribe({
      next: () => {
        this.toastrService.success('User deleted successfully', 'User Notification');
        this.getAllUsers();
      },
      error: () => {
        this.toastrService.error("Can't delete user, please try again", 'User Notification');
      }
    });
  }

  getUserInfo() {
    this.userInfo = this.authService.getUserInfo();
    this.userInfo?.scope
  }

  onResetPassword(id: number) {
    if (!confirm("Are you sure to reset password?")) return;

    this.usersService.resetPassword(id).subscribe({
      next: (res) => {
        this.toastrService.success('Reset password successfully!', 'User Notification');
      },
      error: (err) => {
        console.log(err);
        this.toastrService.error("Cannot reset password! Please try again.", 'User Notification');
      }
    });
  }

}
