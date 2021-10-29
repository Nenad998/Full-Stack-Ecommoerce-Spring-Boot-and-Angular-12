package luv2code.ecommerce.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import luv2code.ecommerce.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	@Query("SELECT p FROM Product p ORDER BY RAND()")
	public List<Product> getRandom();
	
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	
	
            					
	
	

	
	

}
