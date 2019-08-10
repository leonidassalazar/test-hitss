package com.test.hitss.claro.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.hitss.claro.DAO.MobileRepository;
import com.test.hitss.claro.model.Mobile;

@SpringBootTest
public class MobileControllerTests {
	
	@InjectMocks
	MobileController controller;
	     
    @Mock
    MobileRepository dao;
    
    private List<Mobile> mobileList;
    private Mobile mobile;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
        mobile = new Mobile(UUID.randomUUID(), "Moto G5 Plus", 1990, "Motorola",
        		"http://www3.claro.com.br/sites/default/files/claro-net-simples_5654b06692637-224x170_565c9453ee1bf.png​", 
        		LocalDate.of(2015, 11, 26));
        mobile.setId(1l);
        Mobile mobile2 = new Mobile(UUID.randomUUID(), "IPhone 7 Plus", 3990, "Apple",
        		"http://www3.claro.com.br/sites/default/files/claro-net-simples_5654b06692637-224x170_565c9453ee1bf.png​", 
        		LocalDate.of(2015, 12, 25));
        mobile2.setId(2l);
        Mobile mobile4 = new Mobile(UUID.randomUUID(), "J7", 699, "Samsung",
        		"http://www3.claro.com.br/sites/default/files/claro-net-simples_5654b06692637-224x170_565c9453ee1bf.png​", 
        		LocalDate.of(2015, 11, 2));
        mobile4.setId(4l);
        
        mobileList = new ArrayList<Mobile>();
        mobileList.add(mobile);
        mobileList.add(mobile2);
        mobileList.add(mobile4);
    }
     
    
	@Test
	public void getAllMobile() {
		when(dao.findAll()).thenReturn( mobileList);
        
        Collection<Mobile> mobList = controller.getAllMobile();
         
        assertEquals(3, mobList.size());
        verify(dao, times(1)).findAll();
	}
	
	@Test
	public void getMobileTest()
	{
		when(dao.existsById(1l)).thenReturn(true);
		when(dao.findById(1l)).thenReturn((Optional.ofNullable(mobile)));

		Mobile mob = controller.getMobile(1l);
		
		assertEquals("Motorola", mob.getBrand());
		assertEquals("Moto G5 Plus", mob.getModel());
		assertEquals(1990, mob.getPrice());
	}
	
    @Test
    public void saveMobileTest()
    {
        Mobile mob = new Mobile(UUID.randomUUID(), "J5", 600, "Samsung",
        		"http://www3.claro.com.br/sites/default/files/claro-net-simples_5654b06692637-224x170_565c9453ee1bf.png​", 
        		LocalDate.of(2015, 11, 26));
         
        controller.saveMobile(mob);
         
        verify(dao, times(1)).save(mob);
    }

    @Test
    public void updateMobieTest()
    {
		when(dao.findById(1l)).thenReturn((Optional.ofNullable(mobile)));
		
        Mobile newMob = new Mobile(UUID.randomUUID(), "J5", 600, "Samsung",
        		"http://www3.claro.com.br/sites/default/files/claro-net-simples_5654b06692637-224x170_565c9453ee1bf.png​", 
        		LocalDate.of(2015, 11, 26));
        
        controller.updateMobie(newMob, 1l);

        verify(dao, times(1)).findById(1l);
        verify(dao, times(1)).save(newMob);
    }
    
	@Test
	public void deleteMobieTest() {
		when(dao.findAll()).thenReturn(mobileList.subList(0, 2));
        
        Collection<Mobile> mobList = controller.getAllMobile();

        controller.deleteMobie(4l);
        
        verify(dao, times(1)).deleteById(4l);
        assertEquals(2, mobList.size());
	}
	
}
