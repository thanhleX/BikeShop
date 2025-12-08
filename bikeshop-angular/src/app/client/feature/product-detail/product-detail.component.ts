import { Component } from '@angular/core';
import { ProductDetailService } from '../../service/product-detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../../service/cart.service';
import { CartItem } from '../../../dto/CartItem';
import { Product, ProductColorDto, ProductHandlebarDto, ProductMaterialDto } from '../../../dto/Product';
import { ProductDetail, ProductImageResponse } from '../../../dto/ProductDetail';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent {
  selectedHandlebar: string | undefined;
  selectedColor: string | undefined;
  selectedMaterial: string | undefined;
  counter: number = 1;
  selectedImg: string | undefined;
  categoryName: string | undefined;
  subCategoryName: string | undefined;
  currentProductDetail: ProductDetail | undefined;
  productName: string | undefined;
  product: Product | undefined
  trustedHtml: any;
  productDetail: ProductDetail[] = [];
  productColors: ProductColorDto[] = [];
  productHandlebars: ProductHandlebarDto[] = [];
  productMaterials: ProductMaterialDto[] = [];
  filteredImages: ProductImageResponse[] = [];

  // New properties for available options
  availableHandlebars: ProductHandlebarDto[] = [];
  availableMaterials: ProductMaterialDto[] = [];

  constructor(
    private productDetailService: ProductDetailService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router,
    private sanitizer: DomSanitizer,
    private toastrService: ToastrService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.categoryName = param['productCategoryName'];
      this.subCategoryName = param['productSubCategoryName'];
      this.productName = param['productName'];
      this.getData();
    });
  }

  getData() {
    if (this.productName) {
      this.productDetailService.getProductDetailByProductName(this.productName).subscribe({
        next: (res) => {
          this.productDetail = res.result;
          this.extractProductAttributes();
          this.setInitialSelections();
          this.updateAvailableOptions();
        }
      });

      this.productDetailService.getProductByProductName(this.productName).subscribe({
        next: (res) => {
          this.product = res.result;
          this.trustedHtml = this.sanitizer.bypassSecurityTrustHtml(this.product.description);
        }
      });
    }
  }

  extractProductAttributes() {
    // Extract unique colors, handlebars, and materials
    this.productColors = [];
    this.productHandlebars = [];
    this.productMaterials = [];

    this.productDetail.forEach(detail => {
      // Colors
      if (this.productColors.every(color => color.id !== detail.productColorDto.id)) {
        this.productColors.push(detail.productColorDto);
      }

      // Handlebars
      if (detail.productHandlebarDto &&
        this.productHandlebars.every(handlebar => handlebar.id !== detail.productHandlebarDto?.id)) {
        this.productHandlebars.push(detail.productHandlebarDto);
      }

      // Materials
      if (detail.productMaterialDto &&
        this.productMaterials.every(material => material.id !== detail.productMaterialDto?.id)) {
        this.productMaterials.push(detail.productMaterialDto);
      }
    });
  }

  setInitialSelections() {
    // Set initial selections from first available product detail
    const firstDetail = this.productDetail[0];

    this.selectedHandlebar = firstDetail?.productHandlebarDto?.style;
    this.selectedMaterial = firstDetail?.productMaterialDto?.material;
    this.selectedColor = firstDetail?.productColorDto.color;

    this.updateCurrentProductDetail();
    this.updateImages();
  }

  selectColor(color: string) {
    this.selectedColor = color;

    // Find available handlebars and materials for selected color
    const availableDetails = this.productDetail.filter(
      detail => detail.productColorDto.color === color
    );

    // Auto-select first available handlebar and material
    const firstAvailableDetail = availableDetails[0];
    this.selectedHandlebar = firstAvailableDetail?.productHandlebarDto?.style;
    this.selectedMaterial = firstAvailableDetail?.productMaterialDto?.material;

    this.updateAvailableOptions();
    this.updateCurrentProductDetail();
    this.updateImages();
    this.resetCounter();
  }

  selectHandlebar(handlebar: string) {
    this.selectedHandlebar = handlebar;
    this.updateCurrentProductDetail();
    this.updateImages();
    this.resetCounter();
  }

  selectMaterial(material: string) {
    this.selectedMaterial = material;
    this.updateCurrentProductDetail();
    this.updateImagesForMaterial();
    this.resetCounter();
  }

  updateAvailableOptions() {
    // Update available handlebars and materials based on selected color
    if (this.selectedColor) {
      const availableDetails = this.productDetail.filter(
        detail => detail.productColorDto.color === this.selectedColor
      );

      this.availableHandlebars = this.productHandlebars.filter(handlebar =>
        availableDetails.some(detail => detail.productHandlebarDto?.style === handlebar.style)
      );

      this.availableMaterials = this.productMaterials.filter(material =>
        availableDetails.some(detail => detail.productMaterialDto?.material === material.material)
      );
    } else {
      this.availableHandlebars = this.productHandlebars;
      this.availableMaterials = this.productMaterials;
    }
  }

  updateCurrentProductDetail() {
    this.currentProductDetail = this.productDetail.find(detail =>
      detail.productHandlebarDto?.style === this.selectedHandlebar &&
      detail.productColorDto.color === this.selectedColor &&
      detail.productMaterialDto?.material === this.selectedMaterial
    );
  }

  updateImagesForMaterial() {
    if (this.selectedColor && this.selectedHandlebar && this.selectedMaterial) {
      const matchingProduct = this.productDetail.find(detail =>
        detail.productHandlebarDto?.style === this.selectedHandlebar &&
        detail.productColorDto.color === this.selectedColor &&
        detail.productMaterialDto?.material === this.selectedMaterial
      );

      if (matchingProduct && matchingProduct.productImageResponseList.length > 0) {
        this.selectedImg = matchingProduct.productImageResponseList[0].url;
        this.filteredImages = matchingProduct.productImageResponseList;
      }
    }
  }

  updateImages() {
    if (this.selectedColor && this.selectedHandlebar) {
      // Khi chọn handlebar, hiển thị tất cả ảnh của tất cả materials cho combination này
      this.filteredImages = this.productDetail.filter(detail =>
        detail.productHandlebarDto?.style === this.selectedHandlebar &&
        detail.productColorDto.color === this.selectedColor
      ).flatMap(product => product.productImageResponseList || []);

      // Chọn ảnh đầu tiên làm ảnh chính
      if (this.filteredImages.length > 0) {
        this.selectedImg = this.filteredImages[0].url;
      }
    }
  }

  getFilteredImages() {
    this.filteredImages = this.productDetail.filter(detail =>
      detail.productHandlebarDto?.style === this.selectedHandlebar &&
      detail.productColorDto.color === this.selectedColor
    ).flatMap(product => product.productImageResponseList || []);
  }

  // Availability check methods
  isColorAvailable(color: string): boolean {
    return this.productDetail.some(detail => detail.productColorDto.color === color);
  }

  isHandlebarAvailable(handlebar: string): boolean {
    if (!this.selectedColor) return true;
    return this.productDetail.some(detail =>
      detail.productColorDto.color === this.selectedColor &&
      detail.productHandlebarDto?.style === handlebar
    );
  }

  isMaterialAvailable(material: string): boolean {
    if (!this.selectedColor || !this.selectedHandlebar) return true;
    return this.productDetail.some(detail =>
      detail.productColorDto.color === this.selectedColor &&
      detail.productHandlebarDto?.style === this.selectedHandlebar &&
      detail.productMaterialDto?.material === material
    );
  }

  getColorTooltip(color: string): string {
    const isAvailable = this.isColorAvailable(color);
    return isAvailable ? color : `${color} - Not available`;
  }

  selectImg(url: string) {
    this.selectedImg = url;
  }

  increase() {
    if (this.currentProductDetail && this.currentProductDetail.stock > this.counter) {
      this.counter++;
    }
  }

  decrease() {
    if (this.counter > 0) {
      this.counter--;
    }
  }

  resetCounter() {
    this.counter = 1;
  }

  addToCart(): void {
    if (!this.currentProductDetail) {
      this.toastrService.error('Invalid product');
      return;
    }

    // Không cho thêm nếu hết hàng
    if (this.currentProductDetail.stock <= 0) {
      this.toastrService.error('This product is out of stock', 'Stock Error');
      return;
    }

    const cartItem: CartItem = {
      productDetail: this.currentProductDetail,
      amount: this.counter
    };

    const success = this.cartService.addToCart(cartItem);

    if (success) {
      this.toastrService.success('Added to cart successfully', 'Cart Notification');
    } else {
      this.toastrService.error('This product is out of stock', 'Stock Error');
    }
  }
}