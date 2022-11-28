package Kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Kodlama.io.Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologyResponse;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetByIdTechnologyResponse;
import Kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Kodlama.io.Devs.entities.concretes.Language;
import Kodlama.io.Kodlama.io.Devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {

	private TechnologyRepository technologyRepository;
	
	private LanguageService languageService;
	
	
	
	public TechnologyManager(TechnologyRepository technologyRepository, LanguageService languageService) {
		this.technologyRepository = technologyRepository;
		this.languageService = languageService;
	}

	@Override
	public List<GetAllTechnologyResponse> getAll() {
		List<Technology> technologies = technologyRepository.findAll();
		List<GetAllTechnologyResponse> technologyResponses = new ArrayList<GetAllTechnologyResponse>();

		for (Technology technology : technologies) {
			GetAllTechnologyResponse responseItem = new GetAllTechnologyResponse();
			responseItem.setId(technology.getId());
			responseItem.setLanguage_id(technology.getLanguage().getId());
			responseItem.setName(technology.getName());
			responseItem.setLanguageName(technology.getLanguage().getName());
			technologyResponses.add(responseItem);
		}
		return technologyResponses;
	}

	@Override
	public GetByIdTechnologyResponse getById(int id) throws Exception {
		
		if(!isIdExist(id)) { throw new Exception("Geçersiz id"); }
		
		Technology technology = technologyRepository.getReferenceById(id);
		GetByIdTechnologyResponse byIdTechnologyResponse = new GetByIdTechnologyResponse();
		byIdTechnologyResponse.setLanguage_id(technology.getLanguage().getId());
		byIdTechnologyResponse.setLanguageName(technology.getLanguage().getName());
		byIdTechnologyResponse.setName(technology.getName());
		return byIdTechnologyResponse;
	}
	
	@Override
	public void add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
		
		if(isNameEmpty(createTechnologyRequest.getName())) { throw new Exception("İsim boş olamaz tekrar deneyin"); }
		if(isNameExist(createTechnologyRequest.getName())) { throw new Exception("Girilen isim kayıtlı"); } 
		
		Technology technology = new Technology();
		Language language = languageService.findById(createTechnologyRequest.getLanguageId());
		technology.setName(createTechnologyRequest.getName());
		technology.setLanguage(language);
		technologyRepository.save(technology);
	}

	@Override
	public void update(UpdateTechnologyRequest updateTechnologyRequest, int id) throws Exception {
		
		if(!isIdExist(id)) {
			throw new Exception("Geçersiz id");
		}
		if(isNameEmpty(updateTechnologyRequest.getName())) {
			throw new Exception("İsim boş olamaz tekrar deneyin");
		}
		if(isNameExist(updateTechnologyRequest.getName())) {
			throw new Exception("Girilen isim kayıtlı");
		}
		
		
		
		Language language = languageService.findById(updateTechnologyRequest.getLanguageId());
		for (Technology technology : technologyRepository.findAll()) {
		if (technology.getId() == id) {
				technology.setName(updateTechnologyRequest.getName());
				technology.setLanguage(language);
				technologyRepository.save(technology);
			}
			
		}
	}

	@Override
	public void delete(int id) throws Exception {
		if (!isIdExist(id)) {
			throw new Exception("Geçersiz id");
		}
		technologyRepository.deleteById(id);
	
	}

	private boolean isNameExist(String name) {
		for (Technology technology : technologyRepository.findAll()) {
			if (technology.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isNameEmpty(String name) {
		if(name.isBlank())
			return true;
		return false;
	}
	
	private boolean isIdExist(int id) {
		if (technologyRepository.existsById(id)) {
			return true;
		}
		return false;
	}

}
