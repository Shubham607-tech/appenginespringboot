package online.vegetable.sales.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartid;

//    @Column(name = "user_id")
//    private  Integer userId;

//    @Column(name = "product_id")
//    private  Long productId;

//    @Column(name = "created_date")
//    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "products" ,referencedColumnName = "advid")
    private Advertise advertise;

    
}