package luv2code.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import luv2code.ecommerce.dao.ProductRepository;
import luv2code.ecommerce.entity.Product;


public interface ProductService {
	
	public List<Product> findAll();
	
	public Product findById(int theId); 
	
	public void save(Product theProduct); 
	
	public void deleteById(int theId);
	
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	
	
	public List<Product> getRandom();
								
	
	public Page<Product> fetchProducts(Integer page, Integer pageSize);



	
}
