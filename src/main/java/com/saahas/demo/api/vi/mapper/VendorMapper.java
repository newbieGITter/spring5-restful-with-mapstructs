package com.saahas.demo.api.vi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.saahas.demo.api.v1.model.VendorDTO;
import com.saahas.demo.domain.Vendor;

@Mapper
public interface VendorMapper {

	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

	VendorDTO vendorTOVendorDTO(Vendor vendor);
	
	Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
