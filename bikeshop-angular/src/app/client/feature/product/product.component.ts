import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product, ProductDetailDto } from '../../../dto/Product';
import { ProductDetail } from '../../../dto/ProductDetail';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent implements OnInit {
  productSubCategoryName!: string;
  productCategoryName!: string;
  products: Product[] = [];
  productPage: number = 0;
  productSize: number = 10;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe((param) => {
      this.productCategoryName = param['productCategoryName'];
      this.productSubCategoryName = param['productSubCategoryName'];
      this.getData();

    })
  }

  uniqueMaterials(details: ProductDetailDto[]): ProductDetailDto[] {
    const uniqueDetailSet = new Set<string>();
    return details.filter(detail => {
      const material = detail.productMaterialDto?.material;
      if (material && !uniqueDetailSet.has(material)) {
        uniqueDetailSet.add(material);
        return true;
      }
      return false;
    })
  }

  uniqueColor(details: ProductDetailDto[]): ProductDetailDto[] {
    const uniqueDetailSet = new Set<string>();
    return details.filter(detail => {
      const color = detail.productColorDto.color;
      if (color && !uniqueDetailSet.has(color)) {
        uniqueDetailSet.add(color)
        return true;
      }
      return false;
    })
  }





  getData() {
    this.productService.findAllProductBySubCategoryName(this.productSubCategoryName, this.productPage, this.productSize).subscribe({
      next: (res) => {
        this.products = res.result.content;
        this.totalPages = res.result.totalPages;
        this.totalElements = res.result.totalElements;
      }
    })
  }

  previousPage() {
    if (this.productPage > 0) {
      this.productPage--;
      this.getData();
    }
  }

  nextPage() {
    if (this.productPage < this.totalPages - 1) {
      this.productPage++;
      this.getData();
    }
  }

  goToPage(page: number) {
    this.productPage = page;
    this.getData();
  }

  getPageNumbers(): number[] {
    const pages: number[] = [];
    const maxVisiblePages = 5;
    let startPage = Math.max(0, this.productPage - Math.floor(maxVisiblePages / 2));
    let endPage = Math.min(this.totalPages - 1, startPage + maxVisiblePages - 1);

    // Adjust start page if we're at the end
    if (endPage - startPage + 1 < maxVisiblePages) {
      startPage = Math.max(0, endPage - maxVisiblePages + 1);
    }

    for (let i = startPage; i <= endPage; i++) {
      pages.push(i + 1);
    }

    return pages;
  }
}




