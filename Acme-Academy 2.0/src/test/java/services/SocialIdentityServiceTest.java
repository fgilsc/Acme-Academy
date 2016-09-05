package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.SocialIdentity;

import forms.SocialIdentityForm;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SocialIdentityServiceTest extends AbstractTest {
	
	//Services based test
	@Autowired
	private SocialIdentityService siService;
	
	/*
	 A user who is authenticated must be able to:
	1. Manage his or her social identities, which includes listing, creating, modifying, or de-leting them.
	 */
	
	@Test
	public void create(){
		authenticate("student1");
		SocialIdentityForm sif = new SocialIdentityForm();
		sif.setEmail("test@test.com");
		sif.setHomepage("https://test.com");
		sif.setNick("test");
		sif.setSocialPlatformName("Test");
		SocialIdentity si = siService.recontruct(sif);
		
		siService.save(si);
		for(SocialIdentity sid : siService.findAll()){
			System.out.println(sid.getSocialPlatformName());
		}
	}
	
	@Test
	public void edit(){
		authenticate("lecturer1");
		SocialIdentity si = siService.findOne(37);
		SocialIdentityForm edit = siService.constructForm(si);
		edit.setSocialPlatformName("Test");
		si = siService.recontructEdit(edit, si.getId());
		siService.update(si);
		for(SocialIdentity sid : siService.findAll()){
			System.out.println(sid.getSocialPlatformName());
		}
	}
	@Test
	public void delete(){
		SocialIdentity si = siService.findOne(37);
		siService.delete(si);
		for(SocialIdentity sid : siService.findAll()){
			System.out.println(sid.getSocialPlatformName());
		}
	}
	/*
	 * An actor who is authenticated as an administrator must be able to:
			1. Display a dashboard with the following information:
	 			The average number of social identities per actor.
	 */
	@Test
	public void getAverageSocialIdentitiesPerActor(){
		System.out.println(siService.getAverageSocialIdentitiesPerActor());
	}

}
