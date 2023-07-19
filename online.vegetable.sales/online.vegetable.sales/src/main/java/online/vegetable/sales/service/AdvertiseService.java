package online.vegetable.sales.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import online.vegetable.sales.exception.ProductAlreadyExitException;
import online.vegetable.sales.exception.ProductNotFoundException;
import online.vegetable.sales.model.Advertise;
import online.vegetable.sales.repository.AdvertiseRepository;

@Service
public class AdvertiseService {
	private static final Logger LOG = LoggerFactory.getLogger(AdvertiseService.class);

	@Autowired
	private AdvertiseRepository repository;


//	Post New Advertise(Selling)
	@Transactional
	public Advertise addAdvertise(Advertise adv) {
		try {
			LOG.info("addProduct");
			if (repository.existsById(adv.getAdvid())) {
	            throw new ProductAlreadyExitException();
	        }
			Advertise savedAdv=repository.save(adv);
			return savedAdv;
		} catch (ProductAlreadyExitException e) {
			LOG.error("ProductAlreadyExit",e.getMessage());
			throw new ProductAlreadyExitException();
		}
	}
	
// Edit Advertise		
	@Transactional
	public void updateAdvertise(Advertise adv) {
		try {
			LOG.info("updateProduct");
			if (repository.existsById(adv.getAdvid())) {
				repository.save(adv); 
	        }			
									
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
					
	}
	
	
//	 view advertise all Advertise
	@Transactional
	public List<Advertise> getAllAdvertises() {
//		List<Advertise> response = null;
		try {
			LOG.info("ViewAllAdvertises");
			return repository.findAll();
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
		
	}
	
	
//	Search Advertise by text entered in textbox(seller)
	@Transactional
	public List<Advertise> getAdvertiseByName(String advertisetitle) {
		try {
			LOG.info("findAdvertiseByName");
			return repository.findByadvertisetitle(advertisetitle);
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
	
	}


//	Read the specific advertise by id(seller)
	@Transactional
	public Advertise findAdvertiseByAdvId(int advid) {
		try {
			LOG.info("findAdvertiseById");
			return repository.findByadvid(advid);
				
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
		
	}
	
//	 user can also search for advertises by advertisetitle	
	@Transactional
	public List<Advertise> findAdvertiseByAdvertiseTitle(String advertisetitle) {
		List<Advertise> response = null;
		try {
			LOG.info("findAdvertiseByadvertisetitle");
			return repository.findByadvertisetitle(advertisetitle);
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException();
		}
		
	}
	
//	Delete product by id
	@Transactional
	public void deleteAdvertise(int advid) {
		try {
			LOG.info("deleteProduct-service");
			if(repository.existsById(advid)) {
					repository.deleteById(advid);
			}
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound",e.getMessage());
			throw new ProductNotFoundException(e.getMessage());
		}
	}
	

//	Admin will update status of advertise
	@Transactional
	public void updateAdvStatus(int advid, String status) {
		LOG.info("UpdateStatus");
		repository.updateStatusAdv(status, advid);
	}

	



}



