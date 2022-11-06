package Kodlama.io.Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologyResponse;

public interface TechnologyService {
	
	
	List<GetAllTechnologyResponse> getAll();
	void add(CreateTechnologyRequest createTechnologyRequest);
	void update(UpdateTechnologyRequest updateTechnologyRequest,int id);
	void delete(int id) throws Exception;

}
