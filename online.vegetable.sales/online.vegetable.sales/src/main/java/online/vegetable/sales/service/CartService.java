package online.vegetable.sales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.vegetable.sales.dto.CartDto;
import online.vegetable.sales.dto.CartItemDto;
import online.vegetable.sales.exception.ProductAlreadyExitException;
import online.vegetable.sales.exception.ProductNotFoundException;
import online.vegetable.sales.model.Advertise;
import online.vegetable.sales.model.Cart;
import online.vegetable.sales.repository.AdvertiseRepository;
import online.vegetable.sales.repository.CartRepository;

@Service
public class CartService {

	private static final Logger LOG = LoggerFactory.getLogger(AdvertiseService.class);

	@Autowired
	private CartRepository repository;
	
//	add to cart
	@Transactional
	public Cart addProductToCart(Cart cart) {
		try {
			LOG.info("addProduct");
			if (repository.existsById(cart.getCartid())) {
	            throw new ProductAlreadyExitException();
	        }
			Cart savedAdv=repository.save(cart);
			return savedAdv;
		} catch (ProductAlreadyExitException e) {
			LOG.error("ProductAlreadyExit",e.getMessage());
			throw new ProductAlreadyExitException();
		}
	}
	
//	update cart
	@Transactional
	public void updateCart(Cart cart) {
		LOG.info("addProduct");
		repository.save(cart);
		
	}
	
	
	
//	 view item in cart
	@Transactional
	public CartDto getAllProducts() {
		try {
			LOG.info("ViewAllProductInCart");
			List<Cart> cartlist=new ArrayList<>();
//			List<Advertise> adverise=new ArrayList<>();
//			repository.findAll().forEach(cartlist::add);
//			double totalCost = 0;
//	        for (Cart cart :cartlist){
//	        	double cost=cart.getAdvertise().getPrice();
//	            totalCost += cost;
////	            cart.setTotalCost(totalCost);
//	        }
//	        System.out.println(totalCost);
//	        return cartlist;
	        
	        List<Cart> cartList = repository.findAll();
	        List<CartItemDto> cartItems = new ArrayList<>();
	        for (Cart cart:cartList){
	            CartItemDto cartItemDto = getDtoFromCart(cart);
	            cartItems.add(cartItemDto);
	        }
	        double totalCost = 0;
	        for (CartItemDto cartItemDto :cartItems){
	            totalCost += (cartItemDto.getAdvertise().getPrice());
	        }
	        return new CartDto(cartItems,totalCost);
	        
	        
	        
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
		
	}
	
	public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }


//	 delete all item in cart
	@Transactional
	public void deleteAllItem() {
		LOG.info("DeleteAllInCart");
		repository.deleteAll();;
	}

//	 delete item in cart by id
	@Transactional
	public void deleteById(int cartid) {
		LOG.info("DeleteByIdCart");
		 repository.deleteById(cartid);
		
	}



}
