package com.saahas.demo.services;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.saahas.demo.api.v1.model.VendorDTO;
import com.saahas.demo.api.vi.mapper.VendorMapper;
import com.saahas.demo.domain.Vendor;
import com.saahas.demo.repositories.VendorRepository;


public class VendorServiceImplTest {

	@Mock
	private VendorRepository vendorRepo;
	private VendorMapper mapper;
	private VendorService vendorService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mapper = VendorMapper.INSTANCE;
		vendorService = new VendorServiceImpl(mapper, vendorRepo);
	}
	
	@Test
	public void testGetVendorById() {
		Vendor vendor = new Vendor();
		vendor.setId(1L);
		vendor.setName("Vendor1");
		
		Mockito.when(vendorRepo.getById(Mockito.anyLong())).thenReturn(vendor);
		
		VendorDTO vendorDTO = vendorService.getVendorById(1L);
		
		Assert.assertNotNull(vendorDTO);
		Assert.assertEquals("Vendor1", vendorDTO.getName());
	}
	
	@Test
	public void testGetAllVendors() {
		Vendor vendor1 = new Vendor();
		vendor1.setId(1L);
		vendor1.setName("Vendor1");
		
		Vendor vendor2 = new Vendor();
		vendor2.setId(2L);
		vendor2.setName("Vendor2");
		
		List<Vendor> vendors = new ArrayList<>();
		vendors.add(vendor1);
		vendors.add(vendor2);
		
		Mockito.when(vendorRepo.findAll()).thenReturn(vendors);
		
		List<VendorDTO> allVendors = vendorService.getAllVendors();
		
		Assert.assertNotNull(allVendors);
		Assert.assertTrue(allVendors.size() > 0);
		Assert.assertEquals("Vendor1", allVendors.get(0).getName());
		Assert.assertEquals("Vendor2", allVendors.get(1).getName());
	}
	
	@Test
	public void testCreateNewVendor() {
		Vendor vendor = new Vendor();
		vendor.setId(1L);
		vendor.setName("Vendor1");
		Mockito.when(vendorRepo.save(Mockito.any(Vendor.class))).thenReturn(vendor);

		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("Vendor1");
		
		VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);
		
		Assert.assertNotNull(savedVendorDTO);
		Assert.assertEquals("api/v1/vendors/1", savedVendorDTO.getVendorUrl());
		Assert.assertEquals("Vendor1", savedVendorDTO.getName());
	}
	
	@Test
	public void testPatchVendor() {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName("VENDOR_NAME");
		vendorDTO.setId(1L);
		
		Vendor vendor = new Vendor();
		vendor.setName("Vendor1");
        vendor.setId(1L);
        
		Mockito.when(vendorRepo.findById(1L)).thenReturn(Optional.of(vendor));
		Mockito.when(vendorRepo.save(Mockito.any(Vendor.class))).thenReturn(vendor);
		
		VendorDTO savedVendorDTO = vendorService.patchVendor(vendorDTO.getId(), vendorDTO);
		
		Mockito.verify(vendorRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(vendorRepo, Mockito.times(1)).save(vendor);
		Assert.assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
	}
	
	

}
