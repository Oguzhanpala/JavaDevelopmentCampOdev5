package Kodlama.io.Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologyResponse;
import Kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Kodlama.io.Devs.entities.concretes.Language;
import Kodlama.io.Kodlama.io.Devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {

	private TechnologyRepository technologyRepository;
	private LanguageRepository languageRepository;

	@Autowired
	public TechnologyManager(TechnologyRepository technologyRepository, LanguageRepository languageRepository) {
		this.technologyRepository = technologyRepository;
		this.languageRepository = languageRepository;
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
	public void add(CreateTechnologyRequest createTechnologyRequest) {
		Technology technology = new Technology();
		Language language = languageRepository.findById(createTechnologyRequest.getLanguageId()).get();
		technology.setName(createTechnologyRequest.getName());
		technology.setLanguage(language);
		technologyRepository.save(technology);
	}

	@Override
	public void update(UpdateTechnologyRequest updateTechnologyRequest, int id) {
		Language language = languageRepository.findById(updateTechnologyRequest.getLanguageId()).get();
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
		if (!technologyRepository.existsById(id)) {
			throw new Exception("İd bulunamadı");
		}
		technologyRepository.deleteById(id);
	
	}

}
