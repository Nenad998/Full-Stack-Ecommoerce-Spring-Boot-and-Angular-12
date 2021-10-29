package luv2code.ecommerce.service;

import java.util.List;


import luv2code.ecommerce.entity.ProductCategory;

public interface ProductCategoryService {
	

	public List<ProductCategory> findAll();
	
	public ProductCategory findById(int theId); 
	
	public void save(ProductCategory theProduct); 
	
	public void deleteById(int theId);
	

	
	
	
}
