package car.parking.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import car.parking.model.ParkingLotEntity;
import car.parking.repository.ParkingLotRepository;
import car.parking.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	@Autowired
	ParkingLotRepository parkingLotRepsitory;

	@Transactional
	@Override
	public ParkingLotEntity addParkingSlot(ParkingLotEntity Pse) {
		ParkingLotEntity savedPse = parkingLotRepsitory.save(Pse);
		return savedPse;
	}

	@Transactional
	@Override
	public void bookParkingSlot(String status, Long slotId, Date bookTime) {

		parkingLotRepsitory.updateParkingSlot(status, slotId, bookTime);

	}

	@Override
	@Transactional
	public List<Object> getAllParkingSlot() {
		return parkingLotRepsitory.allParkingSlot();
	}

	@Transactional
	@Override
	public List<Object> getoccupiedParkingSlot() {
		return parkingLotRepsitory.occupiedParkingSlot();
	}

	@Transactional
	@Override
	public List<Object> gettotalUser() {

		return parkingLotRepsitory.occupiedParkingSlot();

	}

	@Transactional
	@Override
	public long getAvailableParkingSlot() {
		List<Object> TotalParkingLot = parkingLotRepsitory.allParkingSlot();
		long Total = (long) TotalParkingLot.get(0);
		List<Object> occupiedparkingSlot = parkingLotRepsitory.occupiedParkingSlot();
		long Occ = (long) occupiedparkingSlot.get(0);
		long AvailableParkingSlot = Total - Occ;
		return AvailableParkingSlot;
	}
	
	
	@Transactional
	@Override
	public long getAllReservedSlot() {
		List<Object> TotalReservedLot = parkingLotRepsitory.allReservedParkingSlot();
		long TotalR = (long) TotalReservedLot.get(0);
		List<Object> occupiedResrvedSlot = parkingLotRepsitory.occupiedReservedSlot();
		long res = (long) occupiedResrvedSlot.get(0);
		long AvailableReservedParkingSlot = TotalR - res;
		return AvailableReservedParkingSlot;
	}
	
	@Transactional
	@Override
	public long getAllGeneralSlot() {
		List<Object> TotalGeneralLot = parkingLotRepsitory.allGeneralParkingSlot();
		long TotalG = (long) TotalGeneralLot.get(0);
		List<Object> occupiedGeneralSlot = parkingLotRepsitory.occupiedGeneralSlot();
		long gen = (long) occupiedGeneralSlot.get(0);
		long AvailableReservedParkingSlot = TotalG - gen;
		return AvailableReservedParkingSlot;
	}


}
