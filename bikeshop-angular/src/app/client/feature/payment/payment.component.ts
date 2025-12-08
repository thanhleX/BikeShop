import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from '../../service/payment.service';
import { CartService } from '../../service/cart.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.scss'
})
export class PaymentComponent implements OnInit {
  isSubmitting = false;
  isSuccess!: boolean;
  orderId: number = 0;
  constructor(private activatedRoute: ActivatedRoute, private paymentService: PaymentService, private cartService:CartService,
    private router: Router
  ) { }
  ngOnInit(): void {
    this.isSubmitting = true;
    const orderId = this.activatedRoute.snapshot.paramMap.get('id');
    const token = this.activatedRoute.snapshot.queryParams['token'];
    if (orderId && token) {
      this.paymentService.setPendingById(Number(orderId), token).subscribe({
        next: (res) => {
          this.cartService.clearCart();
          this.isSubmitting = false;
          this.isSuccess = true;
          this.orderId = res.result.id;
        }, error: (err) => {
          console.log(err)
          this.isSuccess = false;
          this.isSubmitting = false;
        }
      })
    }
    if (orderId && !token) {
      this.cartService.clearCart();
      this.isSubmitting = false;
      this.isSuccess = true;
      this.orderId = Number(orderId);
    }

  }

  home() {
    this.router.navigate(['/']);
  }

}
