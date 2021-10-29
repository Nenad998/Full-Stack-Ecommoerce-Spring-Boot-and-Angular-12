package luv2code.ecommerce.rest;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import luv2code.ecommerce.entity.Product;
import luv2code.ecommerce.entity.ProductCategory;
import luv2code.ecommerce.service.ProductCategoryService;
import luv2code.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductRestController {
	
	private ProductService productService;
	
	private ProductCategoryService productCategoryService;
	
	
	@Autowired
	public ProductRestController(ProductService theProductService, ProductCategoryService theproductCategoryService) {
		productService = theProductService;
		productCategoryService = theproductCategoryService;
		
	}
	
	@GetMapping(value="/products/search/findByCategoryId")
	public Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable){
		return productService.findByCategoryId(id, pageable);
	}
	
	
	@GetMapping(value="/products")
	public List<Product> findAll(){
		
		return productService.findAll();
	}  

	
	// add mapping for GET /employees/{employeeId}
	@GetMapping(value="/products/{productId}")
	public Product getProduct(@PathVariable int productId) {
		
		Product theProduct = productService.findById(productId);
		
		if(theProduct == null) {
			throw new RuntimeException("Product is not found - " + productId);
		}
		
		
		return theProduct;
	}
	
	// add mapping for POST /employees - add new employee
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product theProduct) {
		
		// also just in case they pass id in JSON...set id to 0
		// this is to force a save of new item...instead of update
		theProduct.setId((long) 0);
		
		productService.save(theProduct);
		
		return theProduct;
	}
	
	// add mapping PUT /employees - update existing employee
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product theProduct) {
		
		productService.save(theProduct);
		
		return theProduct;
		
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		
		Product tempProduct = productService.findById(productId);
		
		if(tempProduct == null) {
			throw new RuntimeException("Product is not found - " + productId);
		}
		
		productService.deleteById(productId);
		
		return "Deleted employee id - " + productId;
		
	}
	
	
	@GetMapping(value="/product-category")
	public List<ProductCategory> findAllProductCategory() {
		
		return productCategoryService.findAll();
	} 

	
	@GetMapping(value="/products/search/findByNameContaining")
	public Page<Product> findAllProductByName(@RequestParam("name") String name, Pageable pageable){
		return productService.findByNameContaining(name, pageable);
	}

	
	
	@GetMapping("/getProducts")
	public Page<Product> getPaginate(@RequestParam Integer page, @RequestParam Integer size){
		
		return productService.fetchProducts(page, size);
	}
	
	@GetMapping(value="/getRandom")
	public List<Product> getRandom(){
		
		return productService.getRandom();
	}
	
}
