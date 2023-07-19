package car.parking.model;

//import java.time.Date;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "parkinglot")
public class ParkingLotEntity {
	@Id
	@Column(name = "slotId")
	private Long slotId;
	@Column(name = "carName")
	private String CarName;
	@Column(name = "carNumber")
	private String carNumber;
	@Column(name = "carOwnername")
	private String CarOwnerName;
	@Column(name = "entryTime")
	private Date entryTime;
	@Column(name = "exitTime")
	private Date exitTime;
	@Column(name = "comments")
	private String comments;
	@Column(name = "fare")
	private Double fare;
	@JsonIgnore
	@Column(name = "status")
	private String status;
	@Column(name = "categoryType")
	private String categoryType;
//	@JsonIgnore
//	@Column(name = "bookTime")
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	private Date bookTime;
	@JsonIgnore
	@Column(name = "bookTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookTime;

	public ParkingLotEntity(Long slotId, String carName, String carNumber, String carOwnerName, Date entryTime,
			Date exitTime, String comments, Double fare, String status, String categoryType, Date bookTime) {
		super();
		this.slotId = slotId;
		CarName = carName;
		this.carNumber = carNumber;
		CarOwnerName = carOwnerName;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.comments = comments;
		this.fare = fare;
		this.status = status;
		this.categoryType = categoryType;
		this.bookTime = bookTime;
	}

	public ParkingLotEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingLotEntity(String status, Long slotId, Date bookTime) {
		super();
		this.status = status;
		this.slotId = slotId;
		this.bookTime = bookTime;

	}

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public String getCarName() {
		return CarName;
	}

	public void setCarName(String carName) {
		CarName = carName;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarOwnerName() {
		return CarOwnerName;
	}

	public void setCarOwnerName(String carOwnerName) {
		CarOwnerName = carOwnerName;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	@Override
	public String toString() {
		return "ParkingLotEntity [slotId=" + slotId + ", CarName=" + CarName + ", carNumber=" + carNumber
				+ ", CarOwnerName=" + CarOwnerName + ", entryTime=" + entryTime + ", exitTime=" + exitTime
				+ ", comments=" + comments + ", fare=" + fare + ", status=" + status + ", categoryType=" + categoryType
				+ ", bookTime=" + bookTime + "]";
	}

}
