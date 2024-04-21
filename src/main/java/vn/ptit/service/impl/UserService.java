package vn.ptit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.ptit.dao.impl.UserCustomDAO;
import vn.ptit.model.UserCustom;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserCustomDAO userCustomDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCustom user = userCustomDAO.getUserByUsername(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

		return user;
	}
		
	public boolean isExistUser(String username) {
		try {
			UserCustom user = userCustomDAO.getUserByUsername(username);
			
			if (user != null)
				return true;
			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UserCustom getUserByUsername(String username) {
		UserCustom user = userCustomDAO.getUserByUsername(username);
		
		return user;
	}
	
	public int insertUserCustom(UserCustom user) {
		int userId = userCustomDAO.insert(user);
		
		return userId;
	}
}