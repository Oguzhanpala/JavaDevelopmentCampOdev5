package Kodlama.io.Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguageResponse;

@RestController
@RequestMapping("api/languages")
public class LanguagesController {

	private LanguageService languageService;

	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@GetMapping("/getall")
	List<GetAllLanguageResponse> getAll() {

		return languageService.getAll();
	}

	@PostMapping("/add")
	void add(@RequestBody CreateLanguageRequest createLanguageRequest) throws Exception {

		languageService.add(createLanguageRequest);

	}
	@PutMapping("/{id}")
	void update(@RequestBody UpdateLanguageRequest updateLanguageRequest, @PathVariable int id) {
		
		languageService.update(updateLanguageRequest, id);
		
	}
	@DeleteMapping("/{id}")
	void delete(@PathVariable int id) throws Exception {
			
		languageService.delete(id);
	}
}
