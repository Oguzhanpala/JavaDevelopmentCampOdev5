package Kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguageResponse;
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
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception {

		Language language = new Language();
		language.setName(createLanguageRequest.getName());

		if (isNameExist(language)) {
			throw new Exception("Girilen isim kayıtlı yada değer girilmemiş");
		}

		languageRepository.save(language);
	}

	@Override
	public void update(UpdateLanguageRequest updateLanguageRequest, int id){

		for (Language language : languageRepository.findAll()) {
			if (language.getId() == id) {
				language.setName(updateLanguageRequest.getName());
				languageRepository.save(language);
			}
		}

	}

	@Override
	public void delete(int id) throws Exception {

		if (!languageRepository.existsById(id)) {

			throw new Exception("Geçersiz id");
		}

		languageRepository.deleteById(id);
	}

	public boolean isNameExist(Language language) {
		for (Language lang : languageRepository.findAll()) {
			if (language.getName().equals(lang.getName()) || language.getName().isBlank()) {
				return true;
			}
		}
		return false;
	}

}
