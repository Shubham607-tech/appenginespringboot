package online.vegetable.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.vegetable.sales.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
