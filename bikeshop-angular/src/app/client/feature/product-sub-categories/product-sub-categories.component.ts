import { Component, OnInit } from '@angular/core';
import { ProductSubCategoriesService } from '../../service/product-sub-categories.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductSubCategories } from '../../../dto/ProductSubCategories';

@Component({
  selector: 'app-product-sub-categories',
  templateUrl: './product-sub-categories.component.html',
  styleUrl: './product-sub-categories.component.scss'
})
export class ProductSubCategoriesComponent implements OnInit {
  productCategoryName!: string;
  productSubCategories: ProductSubCategories[] = [];

  page = 0;
  size = 6;
  totalPages = 0;
  totalElements = 0;

  constructor(private router: Router, private route: ActivatedRoute, private productSubCategoriesService: ProductSubCategoriesService) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.productCategoryName = param['productCategoryName'];
      this.getAllProductSubCategories();
    })
  }

  getAllProductSubCategories() {
    this.productSubCategoriesService.getProductSubCategoryByName(this.productCategoryName, this.page, this.size).subscribe({
      next: (res) => {
        this.productSubCategories = res.result.content;
        this.totalPages = res.result.totalPages;
        this.totalElements = res.result.totalElements;
        const foundSubCategory = this.productSubCategories.find(subCategoy => subCategoy.productCategoryDto.name == this.productCategoryName);
        if (!foundSubCategory) {
          this.router.navigate(['not-found'], { replaceUrl: true });
        }
      },
      error: (err) => {
      }
    })
  }

  nextPage() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.getAllProductSubCategories();
    }
  }

  previousPage() {
    if (this.page > 0) {
      this.page--;
      this.getAllProductSubCategories();
    }
  }

  goToPage(pageNumber: number) {
    this.page = pageNumber;
    this.getAllProductSubCategories();
  }
}
