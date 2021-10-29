package luv2code.ecommerce.dao;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;

import luv2code.ecommerce.entity.ProductCategory;



public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	
	
	

}
