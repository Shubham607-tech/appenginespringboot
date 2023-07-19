package car.parking.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import car.parking.model.ParkingLotEntity;

public interface ParkingLotService {

	public ParkingLotEntity addParkingSlot(ParkingLotEntity Pse);

	public void bookParkingSlot(String status, Long slotId, Date bookTime);

	public List<Object> getoccupiedParkingSlot();

	public List<Object> getAllParkingSlot();

	public long getAvailableParkingSlot();

	public List<Object> gettotalUser();

	public long getAllReservedSlot();
	
	public long getAllGeneralSlot();

	

}
