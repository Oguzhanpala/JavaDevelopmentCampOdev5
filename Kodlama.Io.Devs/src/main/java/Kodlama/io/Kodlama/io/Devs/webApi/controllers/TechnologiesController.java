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

import Kodlama.io.Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologyResponse;
import Kodlama.io.Kodlama.io.Devs.business.responses.GetByIdTechnologyResponse;

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {

	private TechnologyService technologyService;

	public TechnologiesController(TechnologyService technologyService) {

		this.technologyService = technologyService;
	}

	@GetMapping("/getall")
	List<GetAllTechnologyResponse> getAll() {
		return technologyService.getAll();
	}

	@GetMapping("/{id}")
	public GetByIdTechnologyResponse getById(int id) throws Exception {
		return technologyService.getById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateTechnologyRequest createTechnologyRequest) throws Exception {
		technologyService.add(createTechnologyRequest);
	}

	@PutMapping("/{id}")

	public void update(@RequestBody UpdateTechnologyRequest updateTechnologyRequest, @PathVariable int id) throws Exception {
		technologyService.update(updateTechnologyRequest, id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws Exception {
		technologyService.delete(id);
	}

}
