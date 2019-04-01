package com.saahas.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saahas.demo.api.v1.model.VendorDTO;
import com.saahas.demo.api.vi.mapper.VendorMapper;
import com.saahas.demo.controllers.VendorController;
import com.saahas.demo.domain.Vendor;
import com.saahas.demo.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	private VendorRepository vendorRepo;
	private VendorMapper mapper = VendorMapper.INSTANCE;
	
	public VendorServiceImpl( VendorMapper mapper, VendorRepository vendorRepo) {
		super();
		this.vendorRepo = vendorRepo;
		this.mapper = mapper;
	}

	@Override
	public VendorDTO getVendorById(Long id) {
		return mapper.vendorTOVendorDTO(vendorRepo.getById(id));
	}

	@Override
	public List<VendorDTO> getAllVendors() {
		return vendorRepo.findAll().stream().map(v -> {
			VendorDTO vendorDTO = mapper.vendorTOVendorDTO(v);
			vendorDTO.setVendorUrl(getVendorUrl(v.getId()));
			return vendorDTO;
		}
		).collect(Collectors.toList());
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
		Vendor vendor = mapper.vendorDTOToVendor(vendorDTO);
		
		return saveAndReturnDTO(vendor);
	}


	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
		Vendor vendor = mapper.vendorDTOToVendor(vendorDTO);
		vendor.setId(id);
		
		return saveAndReturnDTO(vendor);
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		return vendorRepo.findById(id)
                .map(vendor -> {
                    //todo if more properties, add more if statements

                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }

                    return saveAndReturnDTO(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteVendorById(Long id) {
		

	}
	
	private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
	
	private VendorDTO saveAndReturnDTO(Vendor vendor) {
		Vendor savedVendor = vendorRepo.save(vendor);
		
		VendorDTO savedVendorDTO = mapper.vendorTOVendorDTO(savedVendor);
		savedVendorDTO.setVendorUrl("api/v1/vendors/" + savedVendorDTO.getId());
		
		return savedVendorDTO;
	}

}
