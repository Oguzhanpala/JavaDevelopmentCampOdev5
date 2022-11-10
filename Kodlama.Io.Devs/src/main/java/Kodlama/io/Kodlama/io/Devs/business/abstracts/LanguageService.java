package Kodlama.io.Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguageResponse;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetByIdLanguageResponse;

public interface LanguageService {
	
	List<GetAllLanguageResponse> getAll();
	GetByIdLanguageResponse getById(int id) throws Exception;
	
	void add(CreateLanguageRequest createLanguageRequest) throws Exception;
	void update(UpdateLanguageRequest updateLanguageRequest,int id) throws Exception;
	void delete(int id) throws Exception;

}
