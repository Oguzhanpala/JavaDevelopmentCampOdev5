package Kodlama.io.Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguageResponse;

public interface LanguageService {
	
	List<GetAllLanguageResponse> getAll();
	void add(CreateLanguageRequest createLanguageRequest) throws Exception;
	void update(UpdateLanguageRequest updateLanguageRequest,int id);
	void delete(int id) throws Exception;

}
