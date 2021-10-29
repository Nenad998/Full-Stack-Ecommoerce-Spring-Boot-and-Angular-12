import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: any[] = [];
  currentCategoryId: number = 1;
  previousCategoryId: number = 1;
  searchMode: boolean = false;

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  previousKeyword: string = null;
  

  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit() { 
    this.route.paramMap.subscribe(() => { 
       this.listProducts();
      });
   
  }

  listProducts(){  

    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if(this.searchMode){
      this.handleSearchProducts();
    } else{
      this.handleListProducts();
    }

  }

  handleSearchProducts(){

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');

    if(this.previousKeyword != theKeyword){
      this.thePageNumber = 1;
    }

    this.previousKeyword = theKeyword;

    this.productService.searchProductsPaginate(this.thePageNumber - 1,
                                               this.thePageSize,
                                               theKeyword)
                                               .subscribe(data => {
                                                this.products = data.content;
                                                this.thePageNumber = data.number + 1;
                                                this.thePageSize = data.size;
                                                this.theTotalElements = data.totalElements;
                                                console.log(data);
                                               });

    /*
    this.productService.searchProducts(theKeyword)
         .subscribe(data => {
           this.products = data.content
           //console.log(data)
         });  */


  }


  handleListProducts(){
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if(hasCategoryId){
      // get the "id" param string. convert string to a number using the "+" symbol
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
    } else{
      // not category id available...default to category id 1
      this.currentCategoryId = 1;
    }  

    // paginacija
    if(this.previousCategoryId != this.currentCategoryId){
      this.thePageNumber = 1;
    }
    this.previousCategoryId = this.currentCategoryId;
    //console.log(`currentCategoryId=${this.currentCategoryId}, thePageNumber=${this.thePageNumber}`);
 
    
    this.productService.getProductListPaginate(this.thePageNumber - 1,
                                               this.thePageSize,
                                               this.currentCategoryId)
                                               .subscribe(data => {
                                                this.products = data.content;
                                                this.thePageNumber = data.number + 1;
                                                this.thePageSize = data.size;
                                                this.theTotalElements = data.totalElements;
                                                //console.log(data);
                                               })
                                               
    
  }

  updatePageSize(pageSize: number){
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listProducts();
  }


}
