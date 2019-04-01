package com.saahas.demo.api.vi.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.saahas.demo.api.v1.model.VendorDTO;
import com.saahas.demo.domain.Vendor;


public class VendorMapperTest {

	private VendorMapper mapper = VendorMapper.INSTANCE;
	
	@Test
	public void testVendorToVendorDTO() {
		Vendor vendor = new Vendor();
		vendor.setId(1L);
		vendor.setName("FruitVendor");
		
		VendorDTO vendorDTO = mapper.vendorTOVendorDTO(vendor);
		
		Assert.assertNotNull(vendorDTO);
		Assert.assertEquals("FruitVendor", vendorDTO.getName());
	}
	
	@Test
	public void testVendorDTOToVendor() {
		VendorDTO dto = new VendorDTO();
		dto.setId(1L);
		dto.setName("ITVendor");
		
		Vendor vendor = mapper.vendorDTOToVendor(dto);
		
		Assert.assertNotNull(vendor);
		Assert.assertEquals("ITVendor", vendor.getName());
	}

}
