package hh.palvelinohjelmointi.Movierate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.palvelinohjelmointi.Movierate.domain.User;
import hh.palvelinohjelmointi.Movierate.domain.UserRepository;

// This class is used by spring security to authenticate and authorize user

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserRepository uRepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.uRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User curruser = uRepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
	}
	
}
