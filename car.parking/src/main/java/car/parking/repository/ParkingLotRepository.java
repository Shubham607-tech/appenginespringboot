package car.parking.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import car.parking.model.ParkingLotEntity;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, Long> {

	@Modifying
	@Query("UPDATE ParkingLotEntity a SET a.status = :status, a.bookTime = :bookTime  WHERE a.slotId = :slotId")
	public void updateParkingSlot(@Param(value = "status") String status, @Param(value = "slotId") Long slotId, @Param(value = "bookTime") Date bookTime);

	@Query("SELECT COUNT(u) FROM ParkingLotEntity u WHERE u.status='book'")
	List<Object> occupiedParkingSlot();

	@Query("SELECT COUNT(u) FROM ParkingLotEntity u WHERE u.slotId=slotId")
	public List<Object> allParkingSlot();
	
	@Query("SELECT COUNT(u) FROM ParkingLotEntity u WHERE u.categoryType='reserved'")
	public List<Object> allReservedParkingSlot();
	
	@Query("SELECT COUNT(u) FROM ParkingLotEntity u WHERE u.categoryType='reserved' AND u.status='book'")
	public List<Object>  occupiedReservedSlot();
	
	@Query("SELECT COUNT(u) FROM ParkingLotEntity u WHERE u.categoryType='general'")
	public List<Object> allGeneralParkingSlot();
	
	@Query("SELECT COUNT(u) FROM ParkingLotEntity u WHERE u.categoryType='general' AND u.status='book'")
	public List<Object>  occupiedGeneralSlot();

}
