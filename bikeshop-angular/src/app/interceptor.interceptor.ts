import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, switchMap, throwError } from 'rxjs';
import { AuthService } from './auth/service/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ApiResponse } from './dto/ApiResponse';

@Injectable()
export class Interceptor implements HttpInterceptor {
  excludedUrls: string[] = [
    '/auth/refresh',
    '/auth/introspect'
  ];
  constructor(private authService: AuthService, private toastrService: ToastrService, private router: Router) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isExcluded = this.excludedUrls.some((url) => req.url.includes(url));
    if (isExcluded) {
      return next.handle(req); // Bỏ qua token cho các URL này
    }
    const token = localStorage.getItem('token');
    let authReq = req;

    if (token) {
      authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
    return next.handle(authReq)
    .pipe(
      catchError((error) => {
        if (error.status === 401 && token) {
          return this.authService.refreshToken(token).pipe(
            switchMap((res: ApiResponse<{ token: string }>) => {
              const newToken = res.result.token;

              localStorage.setItem('token', newToken);

              const newReq = req.clone({
                setHeaders: { Authorization: `Bearer ${newToken}` }
              });

              return next.handle(newReq);
            }),

            // Nếu refresh token cũng thất bại -> logout
            catchError(() => {
              this.logout();
              this.toastrService.error("Session expired", "Authentication");
              return throwError(() => error);
            })
          );
        }

        /**
         * ---------- KHÔNG LOGOUT CHO NHỮNG LỖI NÀY ----------
         * 400 = bad request
         * 409 = conflict (tên Category trùng)
         * 403 = forbidden (không có quyền)
         * 404 = not found
         * 500 = server error
         * → Trả error về component -> hiển thị Toast
         */
        return throwError(() => error);
      })
    );
  }

  private logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/auth/login']);
  }
}

