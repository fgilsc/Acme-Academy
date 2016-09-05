package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {
	
	//	Managed repository -----------------------------------------
	@Autowired
	private ActorRepository actorRepository;

	//	Constructor ------------------------------------------------
	
	//	Simple CRUD methods ----------------------------------------
	public Collection<Actor> findAll(){
		Collection<Actor> res = actorRepository.findAll();
		return res;
	}
	
	public Actor findOne(int id){
		Actor res = actorRepository.findOne(id);
		return res;
	}
	
	// Other bussiness methods ----------------------------
	public Actor findByUserAccountID(int UserAccountID){
		Actor res = actorRepository.findByUserAccountID(UserAccountID);
		return res;
	}
	
	public Actor findByPrincipal(){
		UserAccount user = LoginService.getPrincipal();
		int userID = user.getId();
		Actor res = findByUserAccountID(userID);
		return res;
	}
	
	public Actor findByPrincipalNullable(){
		try{
		UserAccount user = LoginService.getPrincipal();
		int userID = user.getId();
		Actor res = findByUserAccountID(userID);
		return res;
		}catch(Exception oops){
			return null;
		}
	}
	
	public UserAccount createUserAccount(String authorityString){
		checkAuthority(authorityString);
		Authority authority;
		UserAccount res = new UserAccount();
		authority = new Authority();
		authority.setAuthority(authorityString);
		res.setAuthorities(new HashSet<Authority>());
		res.getAuthorities().add(authority);
		return res;
	}
	
	public void checkAuthority(String authorityString){
		Authority auth = new Authority();
		auth.setAuthority(authorityString);
		Collection<Authority> posibleAuths;
		posibleAuths = Authority.listAuthorities();
		Assert.isTrue(posibleAuths.contains(auth));
	}
	

	
	// Utilities 
	
	public String encodePassword(String password){
		String res;
		if(password == null || "".equals(password)){
			res = null;
		}else{
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			res = encoder.encodePassword(password, null);
		}
		return res;
	}
}
