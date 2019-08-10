package com.test.hitss.claro.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.hitss.claro.DAO.MobileRepository;
import com.test.hitss.claro.model.Mobile;

@RestController
public class MobileController {

    private final MobileRepository repository;

    public MobileController(MobileRepository repository) {
        this.repository = repository;
    }
    
	@GetMapping("/claro/mobile")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Mobile> getAllMobile() {
		Collection<Mobile> mobiles = repository.findAll();
		System.out.println("getAllMobile - mobiles: " + mobiles.size());
		return mobiles;
	}
	
	@PostMapping("/claro/mobile")
	@CrossOrigin(origins = "http://localhost:4200")
	public Mobile saveMobile(@RequestBody Mobile mobile) {
		System.out.println("saveMobile - mobile: " + mobile);
		return repository.save(mobile);
	}
	
	@GetMapping("/claro/mobile/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Mobile getMobile(@PathVariable Long id) {
		System.out.println("getMobile 1- mobile: " + id);
		if(repository.existsById(id)) {
			Mobile mobile = repository.findById(id).get();

			System.out.println("getMobile- mobile: " + mobile);
			
			return mobile;
		}
		return new Mobile();
	}
	  
	@PutMapping("/claro/mobile/{code}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Mobile updateMobie(@RequestBody Mobile newMobile, @PathVariable Long id) {
		return repository.findById(id)
				.map(mobile -> {
					mobile.setCode(newMobile.getCode());
					mobile.setModel(newMobile.getModel());
					mobile.setPrice(newMobile.getPrice());
					mobile.setBrand(newMobile.getBrand());
					mobile.setDate(newMobile.getDate());
					mobile.setPhoto(newMobile.getPhoto());
					return repository.save(mobile);
				})
				.orElseGet(() -> {
					return repository.save(newMobile);
				});
	}
	
	@DeleteMapping("/claro/mobile/{id}")
	public void deleteMobie(@PathVariable Long id) {

		System.out.println("deleteMobie - id: " + id);
		repository.deleteById(id);
	}
}
