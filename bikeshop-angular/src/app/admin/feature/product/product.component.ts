// import { Component, OnInit } from '@angular/core';
// import { ProductService } from '../../service/product.service';
// import { FullProduct, ProductDto } from '../../../dto/FullProduct';
// import { ProductDetailService } from '../../service/product-detail.service';
// import { JwtPayloadDto } from '../../../dto/JwtPayloadDto';
// import { AuthService } from '../../../auth/service/auth.service';
// import { ToastrService } from 'ngx-toastr';

// @Component({
//   selector: 'app-product',
//   templateUrl: './product.component.html',
//   styleUrl: './product.component.scss'
// })
// export class ProductComponent implements OnInit {
//   userInfo: JwtPayloadDto | undefined;
//   selectedCategoryId: number | null = null;
//   selectedSubCategoryId: number | null = null;
//   selectedProductId: number | null = null;
//   data: FullProduct[] = [];
//   isSubmitting = false;

//   constructor(
//     private productService: ProductService, 
//     private productDetailService: ProductDetailService, 
//     private authService: AuthService, 
//     private toastrService: ToastrService
//   ) {}

//   ngOnInit(): void {
//     this.getAllProduct();
//     this.getUserInfo();
//   }

//   getAllProduct() {
//     this.productService.getAllProduct().subscribe({
//       next: (res) => {
//         this.data = res.result;
//       }
//     });
//   }

//   getUserInfo() {
//     this.userInfo = this.authService.getUserInfo();
//   }

//   checkScope(roles: string[]): boolean {
//     return roles.some(role => this.userInfo?.scope.includes(role));
//   }

//   deleteProductCategoryById(id: number) {
//     this.isSubmitting = true;
//     this.productService.deleteProductCategoryById(id).subscribe({
//       next: () => {
//         this.isSubmitting = false;
//         this.toastrService.success('Product category deleted successfully', 'Product category Notification');
//         this.getAllProduct();
//       },
//       error: () => {
//         this.isSubmitting = false;
//         this.toastrService.error('Can\'t delete, please check again', 'Product category Notification');
//       }
//     });
//   }

//   deleteProductSubCategoryById(id: number) {
//     this.isSubmitting = true;
//     this.productService.deleteProductSubCategoryById(id).subscribe({
//       next: () => {
//         this.isSubmitting = false;
//         this.toastrService.success('Product sub-category deleted successfully', 'Product sub-category Notification');
//         this.getAllProduct();
//       },
//       error: () => {
//         this.isSubmitting = false;
//         this.toastrService.error('Can\'t delete, please check again', 'Product sub-category Notification');
//       }
//     });
//   }

//   hasHandlebarStyle(product: ProductDto): boolean {
//     return product.productDetailDtoList.some(detail => detail.productHandlebarDto?.style);
//   }

//   hasMaterial(product: ProductDto): boolean {
//     return product.productDetailDtoList.some(detail => detail.productMaterialDto?.material);
//   }

//   toggleCategory(categoryId: number) {
//     this.selectedCategoryId = this.selectedCategoryId === categoryId ? null : categoryId;
//     this.selectedSubCategoryId = null;
//     this.selectedProductId = null;
//   }

//   toggleSubCategory(subCategoryId: number) {
//     this.selectedSubCategoryId = this.selectedSubCategoryId === subCategoryId ? null : subCategoryId;
//     this.selectedProductId = null;
//   }

//   toggleProduct(productId: number) {
//     this.selectedProductId = this.selectedProductId === productId ? null : productId;
//   }

//   deleteProduct(id: number) {
//     this.isSubmitting = true;
//     this.productService.deleteProductById(id).subscribe({
//       next: () => {
//         this.isSubmitting = false;
//         this.toastrService.success('Product deleted successfully', 'Product Notification');
//         this.getAllProduct();
//       },
//       error: (err) => {
//         this.isSubmitting = false;
//         this.toastrService.error('Can\'t delete product, check again', 'Product Notification');
//       }
//     });
//   }

//   deleteProductDetail(id: number) {
//     this.isSubmitting = true;
//     this.productDetailService.deleteProductDetailById(id).subscribe({
//       next: () => {
//         this.isSubmitting = false;
//         this.toastrService.success('Product detail deleted successfully', 'Product detail Notification');
//         this.getAllProduct();
//       },
//       error: (err) => {
//         this.isSubmitting = false;
//         this.toastrService.error('Can\'t delete product detail, check again', 'Product detail Notification');
//       }
//     });
//   }
// }

import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { ProductDetailService } from '../../service/product-detail.service';
import { JwtPayloadDto } from '../../../dto/JwtPayloadDto';
import { AuthService } from '../../../auth/service/auth.service';
import { ToastrService } from 'ngx-toastr';
import { ProductCategories } from '../../../dto/ProductCategories';
import { ProductSubCategories } from '../../../dto/ProductSubCategories';
import { Product } from '../../../dto/Product';
import { ProductDetail } from '../../../dto/ProductDetail';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent implements OnInit {
  userInfo: JwtPayloadDto | undefined;
  selectedCategoryId: number | null = null;
  selectedSubCategoryId: number | null = null;
  selectedProductId: number | null = null;
  
  // Categories
  categories: ProductCategories[] = [];
  categoryPage: number = 0;
  categorySize: number = 3;
  categoryTotalPages: number = 0;
  categoryTotalElements: number = 0;
  
  // SubCategories
  subCategories: ProductSubCategories[] = [];
  subCategoryPage: number = 0;
  subCategorySize: number = 3;
  subCategoryTotalPages: number = 0;
  subCategoryTotalElements: number = 0;
  
  // Products
  products: Product[] = [];
  productPage: number = 0;
  productSize: number = 3;
  productTotalPages: number = 0;
  productTotalElements: number = 0;
  
  // Product Details
  productDetails: ProductDetail[] = [];
  productDetailPage: number = 0;
  productDetailSize: number = 3;
  productDetailTotalPages: number = 0;
  productDetailTotalElements: number = 0;
  
  isSubmitting = false;

  constructor(
    private productService: ProductService, 
    private productDetailService: ProductDetailService, 
    private authService: AuthService, 
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllCategories();
    this.getUserInfo();
  }

  getAllCategories() {
    this.productService.getAllCategories(this.categoryPage, this.categorySize).subscribe({
      next: (res) => {
        this.categories = res.result.content;
        this.categoryTotalPages = res.result.totalPages;
        this.categoryTotalElements = res.result.totalElements;
      }
    });
  }

  getUserInfo() {
    this.userInfo = this.authService.getUserInfo();
  }

  checkScope(roles: string[]): boolean {
    return roles.some(role => this.userInfo?.scope.includes(role));
  }

  deleteProductCategoryById(id: number) {
    this.isSubmitting = true;
    this.productService.deleteProductCategoryById(id).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.toastrService.success('Product category deleted successfully', 'Product category Notification');
        if (this.selectedCategoryId === id) {
          this.selectedCategoryId = null;
          this.selectedSubCategoryId = null;
          this.selectedProductId = null;
          this.subCategories = [];
          this.products = [];
          this.productDetails = [];
        }
        this.getAllCategories();
      },
      error: () => {
        this.isSubmitting = false;
        this.toastrService.error('Can\'t delete, please check again', 'Product category Notification');
      }
    });
  }

  deleteProductSubCategoryById(id: number) {
    this.isSubmitting = true;
    this.productService.deleteProductSubCategoryById(id).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.toastrService.success('Product sub-category deleted successfully', 'Product sub-category Notification');
        if (this.selectedSubCategoryId === id) {
          this.selectedSubCategoryId = null;
          this.selectedProductId = null;
          this.products = [];
          this.productDetails = [];
        }
        if (this.selectedCategoryId) {
          this.getAllSubCategoriesByCategoryId(this.selectedCategoryId);
        }
      },
      error: () => {
        this.isSubmitting = false;
        this.toastrService.error('Can\'t delete, please check again', 'Product sub-category Notification');
      }
    });
  }

  hasHandlebarStyle(product: Product): boolean {
    return this.productDetails.some(detail => detail.productHandlebarDto?.style);
  }

  hasMaterial(product: Product): boolean {
    return this.productDetails.some(detail => detail.productMaterialDto?.material);
  }

  toggleCategory(categoryId: number) {
    if (this.selectedCategoryId === categoryId) {
      this.selectedCategoryId = null;
      this.selectedSubCategoryId = null;
      this.selectedProductId = null;
      this.subCategories = [];
      this.products = [];
      this.productDetails = [];
    } else {
      this.selectedCategoryId = categoryId;
      this.selectedSubCategoryId = null;
      this.selectedProductId = null;
      this.subCategoryPage = 0;
      this.productPage = 0;
      this.productDetailPage = 0;
      this.getAllSubCategoriesByCategoryId(categoryId);
      this.products = [];
      this.productDetails = [];
    }
  }

  toggleSubCategory(subCategoryId: number) {
    if (this.selectedSubCategoryId === subCategoryId) {
      this.selectedSubCategoryId = null;
      this.selectedProductId = null;
      this.products = [];
      this.productDetails = [];
    } else {
      this.selectedSubCategoryId = subCategoryId;
      this.selectedProductId = null;
      this.productPage = 0;
      this.productDetailPage = 0;
      this.getProductsBySubCategoryId(subCategoryId);
      this.productDetails = [];
    }
  }

  toggleProduct(productId: number) {
    if (this.selectedProductId === productId) {
      this.selectedProductId = null;
      this.productDetails = [];
    } else {
      this.selectedProductId = productId;
      this.productDetailPage = 0;
      this.getProductDetailsByProductId(productId);
    }
  }

  getAllSubCategoriesByCategoryId(categoryId: number) {
    this.productService.getAllSubCategoriesByCategoryId(categoryId, this.subCategoryPage, this.subCategorySize).subscribe({
      next: (res) => {
        this.subCategories = res.result.content;
        this.subCategoryTotalPages = res.result.totalPages;
        this.subCategoryTotalElements = res.result.totalElements;
      }
    });
  }

  getProductsBySubCategoryId(subCategoryId: number) {
    this.productService.getProductsBySubCategoryId(subCategoryId, this.productPage, this.productSize).subscribe({
      next: (res) => {
        this.products = res.result.content;
        this.productTotalPages = res.result.totalPages;
        this.productTotalElements = res.result.totalElements;
      }
    });
  }

  getProductDetailsByProductId(productId: number) {
    this.productDetailService.getProductDetailsByProductId(productId, this.productDetailPage, this.productDetailSize).subscribe({
      next: (res) => {
        this.productDetails = res.result.content;
        this.productDetailTotalPages = res.result.totalPages;
        this.productDetailTotalElements = res.result.totalElements;
      }
    });
  }

  deleteProduct(id: number) {
    this.isSubmitting = true;
    this.productService.deleteProductById(id).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.toastrService.success('Product deleted successfully', 'Product Notification');
        if (this.selectedProductId === id) {
          this.selectedProductId = null;
          this.productDetails = [];
        }
        if (this.selectedSubCategoryId) {
          this.getProductsBySubCategoryId(this.selectedSubCategoryId);
        }
      },
      error: (err) => {
        this.isSubmitting = false;
        this.toastrService.error('Can\'t delete product, check again', 'Product Notification');
      }
    });
  }

  deleteProductDetail(id: number) {
    this.isSubmitting = true;
    this.productDetailService.deleteProductDetailById(id).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.toastrService.success('Product detail deleted successfully', 'Product detail Notification');
        if (this.selectedProductId) {
          this.getProductDetailsByProductId(this.selectedProductId);
        }
      },
      error: (err) => {
        this.isSubmitting = false;
        this.toastrService.error('Can\'t delete product detail, check again', 'Product detail Notification');
      }
    });
  }

  // Pagination methods for Categories
  categoryPreviousPage() {
    if (this.categoryPage > 0) {
      this.categoryPage--;
      this.getAllCategories();
    }
  }

  categoryNextPage() {
    if (this.categoryPage < this.categoryTotalPages - 1) {
      this.categoryPage++;
      this.getAllCategories();
    }
  }

  // Pagination methods for SubCategories
  subCategoryPreviousPage() {
    if (this.subCategoryPage > 0) {
      this.subCategoryPage--;
      if (this.selectedCategoryId) {
        this.getAllSubCategoriesByCategoryId(this.selectedCategoryId);
      }
    }
  }

  subCategoryNextPage() {
    if (this.subCategoryPage < this.subCategoryTotalPages - 1) {
      this.subCategoryPage++;
      if (this.selectedCategoryId) {
        this.getAllSubCategoriesByCategoryId(this.selectedCategoryId);
      }
    }
  }

  // Pagination methods for Products
  productPreviousPage() {
    if (this.productPage > 0) {
      this.productPage--;
      if (this.selectedSubCategoryId) {
        this.getProductsBySubCategoryId(this.selectedSubCategoryId);
      }
    }
  }

  productNextPage() {
    if (this.productPage < this.productTotalPages - 1) {
      this.productPage++;
      if (this.selectedSubCategoryId) {
        this.getProductsBySubCategoryId(this.selectedSubCategoryId);
      }
    }
  }

  // Pagination methods for Product Details
  productDetailPreviousPage() {
    if (this.productDetailPage > 0) {
      this.productDetailPage--;
      if (this.selectedProductId) {
        this.getProductDetailsByProductId(this.selectedProductId);
      }
    }
  }

  productDetailNextPage() {
    if (this.productDetailPage < this.productDetailTotalPages - 1) {
      this.productDetailPage++;
      if (this.selectedProductId) {
        this.getProductDetailsByProductId(this.selectedProductId);
      }
    }
  }
}
