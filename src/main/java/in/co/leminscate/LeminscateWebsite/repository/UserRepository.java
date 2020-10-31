package in.co.leminscate.LeminscateWebsite.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import in.co.leminscate.LeminscateWebsite.model.User;

@RepositoryRestController
public interface UserRepository extends CrudRepository<User, Long>{

	@Query("Select u from User u where u.name=?1 and u.password=?2") 
	public User getUserByName(String name,String password);
}
