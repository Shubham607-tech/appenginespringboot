package online.vegetable.sales.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advertise_adv")
public class Advertise {

	@Id
	@Column(name = "advid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advid_generator")
	@SequenceGenerator(name = "advid_generator", initialValue = 201, allocationSize = 1, sequenceName = "advId_seq")
	private int advid;
//	@OneToOne(mappedBy = "advertise")
//	private Message message;
	
	
    

	@Column(name = "advertisetitle", length = 50)
	private String advertisetitle;

	@Column(name = "price", length = 10)
	private double price;

	@Column(name = "description", length = 40)
	private String description;

	@Column(name = "status", length = 10)
	private String status;

	@Column(name = "advownername", length = 20)
	private String advownername;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	@OneToMany( fetch = FetchType.LAZY,mappedBy = "advertise",orphanRemoval = true)
	@JsonIgnore
    private List<Cart> carts;
	
	public Advertise(String advertisetitle, double price, String description, String advownername, String imageUrl
			) {
		super();
		this.advertisetitle = advertisetitle;
		this.price = price;
		this.description = description;
		this.advownername = advownername;
		this.imageUrl = imageUrl;
		
	}



	public Advertise(int advid, String advertisetitle, double price, String description, String advownername,
			String imageUrl) {
		super();
		this.advid = advid;
		this.advertisetitle = advertisetitle;
		this.price = price;
		this.description = description;
		this.advownername = advownername;
		this.imageUrl = imageUrl;

	}


	public Advertise(int advid, String status) {
		super();
		this.advid = advid;
		this.status = status;
	}
	

}
