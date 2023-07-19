package online.vegetable.sales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.vegetable.sales.model.AppUser;

@Repository
public interface LoginRepository extends JpaRepository<AppUser, Integer> {
	
//	@Query("SELECT p FROM AppUser p WHERE CONCAT(p.username , ' ' ) LIKE %?1%")
	public abstract List<AppUser> findByEmail(String email);
	
	public abstract List<AppUser> findByUserName(String username);

//
//	AppUser findByPassword(String password);
	
	AppUser findByEmailAndPassword(String email,String password);

}
