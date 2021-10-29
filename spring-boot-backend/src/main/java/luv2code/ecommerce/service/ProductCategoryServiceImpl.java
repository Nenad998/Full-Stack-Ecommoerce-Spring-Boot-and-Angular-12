package luv2code.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luv2code.ecommerce.dao.ProductCategoryRepository;
import luv2code.ecommerce.entity.ProductCategory;


@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	private ProductCategoryRepository productCategoryRepository;
	
	
	@Autowired
	public ProductCategoryServiceImpl(ProductCategoryRepository theProductCategoryRepository) {
		productCategoryRepository = theProductCategoryRepository;
	}
	
	

	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	@Override
	public ProductCategory findById(int theId) {
		Optional<ProductCategory> result = productCategoryRepository.findById((long) theId);

		ProductCategory theProductCategory = null;
		

		if(result.isPresent()) {
			theProductCategory = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find product_category_id - " + theId);
		}
		
		return theProductCategory;
	}

	@Override
	public void save(ProductCategory theProductCategory) {
		productCategoryRepository.save(theProductCategory);
		
	}

	@Override
	public void deleteById(int theId) {
		productCategoryRepository.deleteById((long) theId);
		
	}


}
