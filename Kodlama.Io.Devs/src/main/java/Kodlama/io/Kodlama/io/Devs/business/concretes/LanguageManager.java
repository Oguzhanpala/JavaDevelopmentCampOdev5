package Kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguageResponse;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetByIdLanguageResponse;
import Kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Kodlama.io.Devs.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {

	private LanguageRepository languageRepository;

	@Autowired
	public LanguageManager(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}

	@Override
	public List<GetAllLanguageResponse> getAll() {

		List<Language> languages = languageRepository.findAll();
		List<GetAllLanguageResponse> languageResponses = new ArrayList<GetAllLanguageResponse>();

		for (Language language : languages) {
			GetAllLanguageResponse responseItem = new GetAllLanguageResponse();
			responseItem.setId(language.getId());
			responseItem.setName(language.getName());
			languageResponses.add(responseItem);
		}
		return languageResponses;
	}
	
	@Override
	public GetByIdLanguageResponse getById(int id) throws Exception {
		
		if(!isIdExist(id)) {throw new Exception("Geçersiz id");	}
		
		Language language = languageRepository.getReferenceById(id);
		GetByIdLanguageResponse getbyIdLanguageResponse = new GetByIdLanguageResponse();
		getbyIdLanguageResponse.setName(language.getName());
		return getbyIdLanguageResponse;
	}

	@Override
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception {

		if (isNameExist(createLanguageRequest.getName())) {throw new Exception("Girilen isim kayıtlı");}
		if (isNameEmpty(createLanguageRequest.getName())) {throw new Exception("İsim boş olamaz tekrar deneyin");}
		
		Language language = new Language();
		language.setName(createLanguageRequest.getName());
		languageRepository.save(language);
	}

	@Override
	public void update(UpdateLanguageRequest updateLanguageRequest, int id) throws Exception {

		if (!isIdExist(id)) 							  {throw new Exception("Geçersiz id");}
		if (isNameEmpty(updateLanguageRequest.getName())) {	throw new Exception("İsim boş olamaz tekrar deneyin");}
		if (isNameExist(updateLanguageRequest.getName())) {	throw new Exception("Girilen isim kayıtlı");}

		Optional<Language> language = languageRepository.findById(id);
		GetAllLanguageResponse allLanguageResponse = new GetAllLanguageResponse();
		allLanguageResponse.setName(updateLanguageRequest.getName());
		language.get().setName(updateLanguageRequest.getName());
		languageRepository.save(language.get());
	}

	@Override
	public void delete(int id) throws Exception {

		if (!isIdExist(id)) { throw new Exception("Geçersiz id"); }

		languageRepository.deleteById(id);
	}

	
	
	private boolean isNameExist(String name) {
		for (Language language : languageRepository.findAll()) {
			if (language.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	private boolean isNameEmpty(String name) {
		if (name.isBlank()) {
			return true;
		}
		return false;
	}

	private boolean isIdExist(int id) {
		if (languageRepository.existsById(id)) {
			return true;
		}
		return false;
	}

	public Language findById(int id) {
		return languageRepository.getReferenceById(id);
	}
	
}
