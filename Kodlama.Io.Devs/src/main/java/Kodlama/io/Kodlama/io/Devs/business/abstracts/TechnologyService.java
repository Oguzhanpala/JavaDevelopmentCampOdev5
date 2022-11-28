package Kodlama.io.Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologyResponse;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetByIdTechnologyResponse;

public interface TechnologyService {
	
	List<GetAllTechnologyResponse> getAll();
	GetByIdTechnologyResponse getById(int id) throws Exception;
	
	void add(CreateTechnologyRequest createTechnologyRequest) throws Exception;
	void update(UpdateTechnologyRequest updateTechnologyRequest,int id) throws Exception;
	void delete(int id) throws Exception;

}
