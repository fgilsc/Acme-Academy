package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.SocialIdentity;
import forms.SocialIdentityForm;

import repositories.SocialIdentityRepository;


@Service
@Transactional
public class SocialIdentityService {
	
	//Managed repository -----------------------------------------
		@Autowired
		private SocialIdentityRepository socialIdRepo;
		
	//Supporting Service-----------------------------------------
		@Autowired	
		private ActorService actorService;
	//Simple CRUD methods -------------------------------------------------	
		public SocialIdentity create(){
			Actor a = actorService.findByPrincipal();
			SocialIdentity res = new SocialIdentity();
			res.setActor(a);
			return res;
		}
		
		public Collection<SocialIdentity> findAll(){
			Collection<SocialIdentity>res = socialIdRepo.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public SocialIdentity findOne(int id){
			SocialIdentity res = socialIdRepo.findOne(id);
			Assert.notNull(res);
			return res;
		}
		
		public void save(SocialIdentity si){
			Assert.notNull(si);
			socialIdRepo.saveAndFlush(si);
		}
		
		public void update(SocialIdentity si){
			Assert.notNull(si);
			socialIdRepo.save(si);
		}
		
		public void delete(SocialIdentity si){
			Assert.notNull(si);
			socialIdRepo.delete(si);
		}
	//Other Bussines Methods
		public Double getAverageSocialIdentitiesPerActor(){
			return socialIdRepo.getAverageSocialIdentitiesPerActor();
		}
		public Collection<SocialIdentity>findByActor(){
			//UserAccount loginNow = LoginService.getPrincipal();
			Actor a = actorService.findByPrincipal();
			Collection<SocialIdentity> res = socialIdRepo.findByActor(a.getId());
			Assert.notNull(res);
			return res;
		}
		
		public SocialIdentity recontruct(SocialIdentityForm sif){
			SocialIdentity res = create();
			res.setSocialPlatformName(sif.getSocialPlatformName());
			res.setNick(sif.getNick());
			res.setHomepage(sif.getHomepage());
			res.setEmail(sif.getEmail());
			return res;
		}
		
		public SocialIdentity recontructEdit(SocialIdentityForm sif,Integer socialIdentityId){
			SocialIdentity res = findOne(socialIdentityId);
			res.setSocialPlatformName(sif.getSocialPlatformName());
			res.setNick(sif.getNick());
			res.setHomepage(sif.getHomepage());
			res.setEmail(sif.getEmail());
			return res;
		}
		
		public SocialIdentityForm constructForm(SocialIdentity si){
			Assert.notNull(si);
			SocialIdentityForm resForm = new SocialIdentityForm();
			resForm.setSocialPlatformName(si.getSocialPlatformName());
			resForm.setNick(si.getNick());
			resForm.setHomepage(si.getHomepage());
			resForm.setEmail(si.getEmail());
			return resForm;
		}

}
