package online.vegetable.sales.dto;

import online.vegetable.sales.model.Advertise;
import online.vegetable.sales.model.Cart;

public class CartItemDto {
    private Integer id;
//    private  Integer quantity;
    private  Advertise advertise;
    
    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getCartid());
//        this.setQuantity(cart.getQuantity());
        this.setAdvertise(cart.getAdvertise());
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Advertise getAdvertise() {
		return advertise;
	}

	public void setAdvertise(Advertise advertise) {
		this.advertise = advertise;
	}

	@Override
	public String toString() {
		return "CartItemDto [id=" + id + ", advertise=" + advertise + "]";
	}
    
    
}