  import { Component, HostListener, OnInit } from '@angular/core';
  import { HomeService } from '../../service/home.service';
  import { BlogCategories } from '../../../dto/BlogCategories';
  import { Blogs } from '../../../dto/Blog';
  import { ProductCategories } from '../../../dto/ProductCategories';
  import { ProductSubCategories } from '../../../dto/ProductSubCategories';
  import { Router } from '@angular/router';
  @Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
  })
  export class HomeComponent implements OnInit {
    blogCategories: BlogCategories[] = [];
    news: Blogs[] = [];
    carousels: Blogs[] = [];
    bikeCategories: ProductSubCategories[] = [];
    partCategories: ProductSubCategories[] = [];
    blogCurrentPage: number = 0;
    blogPageSize: number = 5;
    carouselCurrentPage: number = 0;
    carrouselPageSize: number = 3;
    bikeCurrentPage: number = 0;
    bikePageSize: number = 99;

    constructor(private router: Router, private homeService: HomeService) { }

    ngOnInit(): void {
      this.getData();
    }



    getData() {
      this.homeService.getAllBlogCategories().subscribe((res) => {
        //get News
        this.blogCategories = res.result;
        const newsId = this.blogCategories.find(item => item.name === "News")?.id;
        console.log(newsId)
        if (newsId) {
          this.homeService.getAllBlogs(newsId, this.blogCurrentPage, this.blogPageSize).subscribe({
            next:(res) => {
              console.log(res)
              this.news = res.result.content.filter(item => item.blogCategoryDto.name == "News").slice(0, 3);
            },
            error:(err)=>{
              console.log(err)
            }
          })
        }
      })
      //get Carousel
      this.homeService.getAllCarousels(this.carouselCurrentPage, this.carrouselPageSize).subscribe((res) => {
        this.carousels = res.result.content;
      })


      //get all categories
      this.homeService.getAllProductCategories().subscribe((res) => {
        const productCategories = res.result.content;
        const partsId = productCategories.find(item =>item.name === "Parts")?.id;
      //get all parts
        if(partsId){
          this.homeService.getAllProductSubCategories(partsId).subscribe((res) =>{
            this.partCategories = res.result.content;

          })
        }
        //get all bikes
        const bikeId = productCategories.find(item =>item.name === "Bikes")?.id;
        if(bikeId){
          this.homeService.getAllProductSubCategories(bikeId).subscribe((res) =>{
            this.bikeCategories = res.result.content;
          })
        }
        
      })

    }

    ngAfterViewInit(): void {
      this.initializeCarousel();
    }
    initializeCarousel(): void {
      const items = document.querySelectorAll<HTMLElement>('#spareCarousel .carousel-item');

      items.forEach((el: HTMLElement): void => {
        const minPerSlide = 4;
        let next: HTMLElement | null = el.nextElementSibling as HTMLElement;

        for (let i = 1; i < minPerSlide; i++) {
          if (!next) {
            next = items[0] as HTMLElement;
          }
          const cloneChild = next.cloneNode(true) as HTMLElement;
          el.appendChild(cloneChild.children[0] as HTMLElement);
          next = next.nextElementSibling as HTMLElement;
        }
      });
    }



    navigateToBlog(blogCategoryName: string, blogId: number) {
      this.router.navigate(['blog', blogCategoryName, blogId]);
    }

  }
