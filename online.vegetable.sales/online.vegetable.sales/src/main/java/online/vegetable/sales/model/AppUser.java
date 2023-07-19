package online.vegetable.sales.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "appuser")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int userId;
	@Column(nullable = false, unique = true, length = 10)
	private String userName;
	private String password;
	@Column( nullable = false, unique = true, length = 30)
	private String email;
	@Column
	private Role role;

}
