package lk.sadeep.balckmarket.repository;

import lk.sadeep.balckmarket.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{

}
