package luv2code.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.stereotype.Service;

import luv2code.ecommerce.dao.ProductRepository;
import luv2code.ecommerce.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRespoitory;
	
	
	
	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		productRespoitory = theProductRepository;
	}
	
	
	

	@Override
	public List<Product> findAll() {
		return productRespoitory.findAll();
	}

	@Override
	public Product findById(int theId) {
		Optional<Product> result = productRespoitory.findById((long) theId);

		Product theProduct = null;
		

		if(result.isPresent()) {
			theProduct = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find product id - " + theId);
		}
		
		return theProduct;
	}

	@Override
	public void save(Product theProduct) {
		productRespoitory.save(theProduct);
	}

	@Override
	public void deleteById(int theId) {
		productRespoitory.deleteById((long) theId);
	}


	@Override
	public Page<Product> findByCategoryId(Long id, Pageable pageable) {
		return productRespoitory.findByCategoryId(id, pageable);
	}




	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productRespoitory.findByNameContaining(name, pageable);
	}



	
     /*
	@Override
	public List<Product> findPaginated(int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Product> pagedResult = productRespoitory.findAll(paging);
      

        return pagedResult.getContent();
        // moze i pagedResult.toList();
	}  */

	
	public Page<Product> fetchProducts(Integer page, Integer pageSize){
		
		
		Pageable pageable = PageRequest.of(page, pageSize);
		
		return productRespoitory.findAll(pageable);
	}




	@Override
	public List<Product> getRandom() {
		
		return productRespoitory.getRandom();
	}

}
