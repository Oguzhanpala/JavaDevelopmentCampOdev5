package Kodlama.io.Kodlama.io.Devs.business.requests;

import Kodlama.io.Kodlama.io.Devs.entities.concretes.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTechnologyRequest {
	private String name;
	private Language language;
}
