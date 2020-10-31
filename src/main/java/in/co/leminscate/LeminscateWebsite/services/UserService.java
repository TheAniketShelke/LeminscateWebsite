package in.co.leminscate.LeminscateWebsite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.leminscate.LeminscateWebsite.model.User;
import in.co.leminscate.LeminscateWebsite.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired 
	private UserRepository repository;

	@Override
	public User authenticate(String name,String password) {
		return repository.getUserByName(name,password);
	}

	@Override
	public boolean addUser(User user) {
		return repository.save(user) != null;
	}

	@Override
	public List<User> getAll() {
		
		return (List<User>) repository.findAll();
	}

	

}
