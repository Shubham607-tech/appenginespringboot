package car.parking.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import car.parking.model.ParkingLotEntity;
import car.parking.service.ParkingLotService;
import java.util.Date;

@RestController
public class ParkingLotController {

	@Autowired
	ParkingLotService parkingLotService;

	@PostMapping("/addParkingSlot")
	public ResponseEntity addParkingSlot(@RequestBody ParkingLotEntity Pse) {
		ParkingLotEntity savedPse = parkingLotService.addParkingSlot(Pse);
		return new ResponseEntity<>(savedPse, HttpStatus.CREATED);
	}

	@PostMapping("/bookParkingSlot")
	public void bookParkingSlot(@RequestParam(value = "status") String status, @RequestParam(value = "slotId") Long slotId, @RequestParam(value = "bookTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date bookTime) {
		parkingLotService.bookParkingSlot(status, slotId, bookTime);
	}
	
	

	@GetMapping(value = "/getAllParkingSlot")
	public List<Object> getAllarkingSlot() {

		List<Object> parkingLotEntity = parkingLotService.getAllParkingSlot();

		return parkingLotEntity;

	}

	@GetMapping(value = "/getOcupiedParkingSlot")
	public List<Object> getoccupiedparkingSlot() {

		List<Object> parkingLotEntity = parkingLotService.getoccupiedParkingSlot();

		return parkingLotEntity;

	}

	@GetMapping(value = "/getAvailableParkingSlot")
	public long getAvailableParkingSlot() {

		long parkingLotEntity = parkingLotService.getAvailableParkingSlot();

		return parkingLotEntity;

	}
	
	@GetMapping(value = "/getAvailableReservedParkingSlot")
	public long getAvailableResrvedParkingSlot() {

		long reserved = parkingLotService.getAllReservedSlot();

		return reserved;

	}
	
	@GetMapping(value = "/getAvailableGeneralParkingSlot")
	public long getAvailableGeneralParkingSlot() {

		long general = parkingLotService.getAllGeneralSlot();

		return general;

	}

	@GetMapping(value = "/gettotalUser")
	public List<Object> gettotalUser() {

		List<Object> parkingLotEntity = parkingLotService.gettotalUser();

		return parkingLotEntity;

	}

}
