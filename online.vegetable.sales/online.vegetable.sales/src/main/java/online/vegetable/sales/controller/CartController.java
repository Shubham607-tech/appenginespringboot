package online.vegetable.sales.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import online.vegetable.sales.dto.CartDto;
import online.vegetable.sales.exception.ProductAlreadyExitException;
import online.vegetable.sales.exception.ProductNotFoundException;
import online.vegetable.sales.model.Advertise;
import online.vegetable.sales.model.Cart;
import online.vegetable.sales.service.AdvertiseService;
import online.vegetable.sales.service.CartService;
import online.vegetable.sales.service.LoginService;

@RestController
@CrossOrigin(origins = "*")
public class CartController {
	private static final Logger LOG = LoggerFactory.getLogger(AdvertiseController.class);

	@Autowired
	private CartService service;

	@Autowired
	LoginService loginService;

//	Add Prouct to cart advertise
	@PostMapping("/addProduct")
	public ResponseEntity addNewProduct(@RequestBody Cart cart) {
		try {
			LOG.info("addproduc");
//			if (loginService.loginStatus().getRole().toString().equals("USER"))
			Cart savedAdv=service.addProductToCart(cart);
		        return new ResponseEntity<>(savedAdv, HttpStatus.CREATED);
//		}
		} catch (ProductAlreadyExitException e) {
			LOG.error("ProductAlreadyExit",e.getMessage());
			 return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}			

	}
	
	
//	 edit or update his advertise details 
//	@PutMapping("/editcart")
//	public void updateProduct( @RequestBody Cart cart  ) {
//		try {
//			LOG.info("updatecart");
////			if (loginService.loginStatus().getRole().toString().equals("USER"))
//				 service.updateCart(cart);	
////		}
//		} catch (ProductNotFoundException e) {
//			LOG.error("ProductNotFound",e.getMessage());
//			throw new ProductNotFoundException();
//		}
//		
//	}

	
//view  Product In cart  
	@GetMapping("/getAllProducts")
	private CartDto getAllProducts() {
		try {
			LOG.info("ViewProduct");
//			if (loginService.loginStatus().getRole().toString().equals("ADMIN"))
			CartDto cart= service.getAllProducts();
			return cart;
//		}
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
	}
	
	
	
//  delete Product in cart by Id
	@DeleteMapping("/deletecart")
	public void deleteAllItem() {
			LOG.info("deleteadvertise");
//			if (loginService.loginStatus().getRole().toString().equals("USER"))
			service.deleteAllItem();
				
	}
	
	
	
	//   delete Product in cart by Id
	@DeleteMapping("/deletecart/{cartid}")
	public ResponseEntity deleteById(@PathVariable int cartid) {
		try {
			LOG.info("deleteCartItemById");
//			if (loginService.loginStatus().getRole().toString().equals("USER"))
			service.deleteById(cartid);
			return new ResponseEntity(cartid, HttpStatus.OK);
//		}
			
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			 return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	}

	


