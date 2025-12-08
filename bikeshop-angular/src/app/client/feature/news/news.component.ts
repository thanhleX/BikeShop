import { Component } from '@angular/core';
import { NewsService } from '../../service/news.service';
import { Blogs } from '../../../dto/Blog';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrl: './news.component.scss'
})
export class NewsComponent {
  constructor(private newService: NewsService, private toastrService:ToastrService) { }
  targetPage: number | undefined | null;
  news: Blogs[] = [];
  totalPages!: number;
  currentPage: number = 1;
  pageSize: number = 2;
  pageAmount: any[] = [];

  ngOnInit(): void {
    this.getAllNews()
  }

  getAllNews() {
    this.newService.getAllBlogCategories().subscribe((res)=>{
      const newsId = res.result.find(blog => blog.name === "News")?.id;
      this.newService.getAllNews(Number(newsId),this.currentPage - 1, this.pageSize).subscribe(
        (res) => {
          this.news = res.result.content
          this.totalPages = res.result.totalPages;
          // this.pageAmount = [];
          this.pageAmount = this.getPageAmount();
  
        })
    })
   
  }

  getPageAmount(): number[] {
    const pages: number[] = [];
    for (let i = 1; i <= this.totalPages; i++) {
      pages.push(i);
    }
    return pages;
  }

  getVisiblePages(): (number | string)[] {
    const visiblePages: (number | string)[] = [];
    const maxVisiblePages = 5;
    
    if (this.totalPages <= maxVisiblePages) {
      // Show all pages if total pages is less than max visible
      return this.pageAmount;
    }
    
    // Always show first page
    visiblePages.push(1);
    
    // Calculate start and end of visible pages
    let start = Math.max(2, this.currentPage - 1);
    let end = Math.min(this.totalPages - 1, this.currentPage + 1);
    
    // Add ellipsis if needed
    if (start > 2) {
      visiblePages.push('...');
    }
    
    // Add middle pages
    for (let i = start; i <= end; i++) {
      visiblePages.push(i);
    }
    
    // Add ellipsis if needed
    if (end < this.totalPages - 1) {
      visiblePages.push('...');
    }
    
    // Always show last page
    if (this.totalPages > 1) {
      visiblePages.push(this.totalPages);
    }
    
    return visiblePages;
  }

  changePage(page: any) {
    this.currentPage = page;
    this.getAllNews();
  

  }

  goToPage() {
    if (this.targetPage && this.targetPage >= 1 && this.targetPage <= this.totalPages) {
      this.currentPage = this.targetPage;
      this.changePage(this.targetPage);
    } else {
      this.toastrService.error(`can't navigate to page ${this.targetPage}`, `Navigate Notification`)
    }
  }
}
