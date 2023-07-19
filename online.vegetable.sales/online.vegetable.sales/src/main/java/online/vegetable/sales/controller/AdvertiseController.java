package online.vegetable.sales.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import online.vegetable.sales.exception.ProductAlreadyExitException;
import online.vegetable.sales.exception.ProductNotFoundException;
import online.vegetable.sales.model.Advertise;
import online.vegetable.sales.service.AdvertiseService;
import online.vegetable.sales.service.LoginService;

	@RestController
	@CrossOrigin(origins = "*")
	public class AdvertiseController {

		private static final Logger LOG = LoggerFactory.getLogger(AdvertiseController.class);

		@Autowired
		private AdvertiseService service;

		@Autowired
		LoginService loginService;

//		To post New advertise
		@PostMapping("/AddNewAdv")
		public ResponseEntity addNewProduct(@RequestBody Advertise adv) {
			try {
				LOG.info("addproduct");
//				if (loginService.loginStatus().getRole().toString().equals("USER"))
				Advertise savedAdv=service.addAdvertise(adv);
			        return new ResponseEntity<>(savedAdv, HttpStatus.CREATED);

//			}
			} catch (ProductAlreadyExitException e) {
				LOG.error("ProductAlreadyExit",e.getMessage());
				 return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
			}			
	
		}

//		 edit or update his advertise details 
		@PutMapping("/editadv")
		public void updateProduct( @RequestBody Advertise adv  ) {
			try {
				LOG.info("updateproduct");
//				if (loginService.loginStatus().getRole().toString().equals("USER"))
					 service.updateAdvertise(adv);	
//			}
			} catch (ProductNotFoundException e) {
				LOG.error("ProductNotFound",e.getMessage());
				throw new ProductNotFoundException();
			}
			
		}		
		
//	view all the advertises  
		@GetMapping("getalladv")
		private List<Advertise> getAllAdvA() {
			try {
				LOG.info("ViewAdvertises");
//				if (loginService.loginStatus().getRole().toString().equals("ADMIN"))
					return service.getAllAdvertises();
//			}
			} catch (ProductNotFoundException e) {
				LOG.error("ProductNotFound",e.getMessage());
			}
			return null;
		}
		
//		 search for advertises by advertisetitle
		@GetMapping("/getadvertise/{advertisetitle}")
		public List<Advertise> getAdvertiseByadvertisetitle(String advertisetitle) {
			try {
				LOG.info("getadvbytitle");
//				if (loginService.loginStatus().getRole().toString().equals("USER")) {
					return service.findAdvertiseByAdvertiseTitle(advertisetitle);
//				}
			} catch (ProductNotFoundException e) {
				LOG.error("ProductNotFound",e.getMessage());
			}
			return null;
		}
		
		
//		 view the specific advertise  by id posted by seller
		@GetMapping("/getadv/{advid}")
		public Advertise getAdvertiseByadvid(@PathVariable("advid") int advid) {			
			try {
				LOG.info("getadvby");
//				if (loginService.loginStatus().getRole().toString().equals("USER")) {
					return service.findAdvertiseByAdvId(advid);
//				}
			} catch (ProductNotFoundException e) {
				LOG.error("ProductNotFound",e.getMessage());
			}
			return null;
		}
		
		
		//   delete advertise
		@DeleteMapping("/deleteadv/{advid}")
		public ResponseEntity deleteAdv(@PathVariable int advid) {
			try {
				LOG.info("deleteadvertise");
//				if (loginService.loginStatus().getRole().toString().equals("USER"))
				service.deleteAdvertise(advid);
				return new ResponseEntity(advid, HttpStatus.OK);
//			}
				
			} catch (ProductNotFoundException e) {
				LOG.error("ProductNotFound",e.getMessage());
				 return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
			}
		}
		
		
////		Admin can update the status posted by the seller
//		@PutMapping("/admin/updateStatus")
//		public void updateStatus( int advid, String status) {
////		public void updateStatus(@PathVariable("advid") int advid, String status) {
////			LOG.info("updateStatus");
//			try {
//				if (loginService.loginStatus().getRole().toString().equals("ADMIN"))
//					service.updateAdvStatus(advid, status);
//			} catch (Exception e) {
//			}
//			
//		}


}
