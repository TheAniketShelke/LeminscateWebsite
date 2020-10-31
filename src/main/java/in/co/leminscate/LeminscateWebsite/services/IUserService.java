package in.co.leminscate.LeminscateWebsite.services;

import java.util.List;

import in.co.leminscate.LeminscateWebsite.model.User;


public interface IUserService {

	public User authenticate(String name,String password);
	public boolean addUser(User user);	
	public List<User> getAll();
}
	