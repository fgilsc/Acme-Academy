package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {
	
	//	Managed repository -----------------------------------------
	@Autowired
	private AdministratorRepository administratorRepository;
	
	
		

		//Constructor -------------------------------------------------
		public AdministratorService(){
			super();
		}
		
		//Simple CRUD methods -------------------------------------------------
		public Administrator create(){
			isAdmin(LoginService.getPrincipal());
			Administrator res = new Administrator();
			UserAccount userAccount=new UserAccount();
			List<Authority> authorities=new ArrayList<Authority>();
			Authority a=new Authority();
			a.setAuthority(Authority.ADMIN);
			authorities.add(a);
			userAccount.setAuthorities(authorities);
			return res;
		}
		
		public Collection<Administrator> findAll(){
			Collection<Administrator> res = administratorRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public Administrator findOne(int administratorId){
			Administrator res = administratorRepository.findOne(administratorId);
			Assert.notNull(res);
			return res;
		}
		
		public void save(Administrator admin){
			isAdmin(LoginService.getPrincipal());
			administratorRepository.saveAndFlush(admin);
		}
		
		public void delete(Administrator admin){
			administratorRepository.delete(admin);
		}
		
		// Other business methods -------------------------------------------------
		public void isAdmin(UserAccount account) {
			Collection<Authority> authorities= account.getAuthorities();
			Boolean res=false;
			for(Authority a:authorities){
				if(a.getAuthority().equals("ADMIN")) res=true;
			}
			Assert.isTrue(res);
		}
		
		public Administrator findByUserAccountID(int id){
			return administratorRepository.findByUserAccountID(id);
		}
}
