import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../service/notification.service';
import { AuthService } from '../../../auth/service/auth.service';
import { JwtPayloadDto } from '../../../dto/JwtPayloadDto';
import { Notification } from '../../../dto/Notification';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss'] // Changed to styleUrls (plural)
})
export class NotificationComponent implements OnInit {
  currentPage: number = 1;
  pageSize: number = 50;
  allNotification: Notification[] | undefined;
  pageAmount: number[] = [];
  totalPages!: number;
  targetPage: number | undefined;
  userInfo: JwtPayloadDto | undefined;

  constructor(
    private notificationService: NotificationService,
    private authService: AuthService,
    private router: Router,
    private toastrService: ToastrService
  ) { }

  ngOnInit(): void {
    this.getAllNotification();
  }

  getAllNotification() {
    this.userInfo = this.authService.getUserInfo();
    if (this.userInfo) {
      this.notificationService.getAllNotification(this.currentPage - 1, this.pageSize, Number(this.userInfo.sub)).subscribe({
        next: (res) => {
          this.allNotification = res.result.content;
          this.totalPages = res.result.totalPages;
          this.pageAmount = this.getPageAmount();
        },
        error: (err) => {
          this.toastrService.error('Failed to load notifications. Please try again later.', 'Error');
        }
      });
    }
  }

  setRead(noti:Notification){
    if(noti.notificationStatus === 'NEW'){
      this.notificationService.setNotificationStatusToRead(noti.id).subscribe({
        next: (res) => {
          
        },
        error: (err) => {
          this.toastrService.error('Failed to mark notification as read. Please try again.', 'Error');
        }
      });
    }
    
  }

  navigateToOrder(id: number) {
    this.notificationService.setNotificationStatusToRead(id).subscribe({
      next: (res) => {
        this.router.navigate(['/admin/order']);
      },
      error: (err) => {
        this.toastrService.error('Failed to mark notification as read. Please try again.', 'Error');
      }
    });
  }

  getPageAmount(): any[] {
    const pageAmount: number[] = [];
    if (this.totalPages) {
      for (let i = 1; i <= this.totalPages; i++) {
        pageAmount.push(i);
      }
    }
    return pageAmount;
  }

  changePage(page: number) {
    this.currentPage = page;
    this.getAllNotification();
  }

  goToPage() {
    if (this.totalPages) {
      if (this.targetPage && this.targetPage >= 1 && this.targetPage <= this.totalPages) {
        this.changePage(this.targetPage);
        this.targetPage = undefined;
      } else {
        this.toastrService.error("Can't navigate, please check the page number", 'Navigate Notification');
      }
    }
  }
}
