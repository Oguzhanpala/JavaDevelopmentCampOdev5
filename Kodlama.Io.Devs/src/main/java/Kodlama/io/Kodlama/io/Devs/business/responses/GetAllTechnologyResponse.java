package Kodlama.io.Kodlama.io.Devs.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTechnologyResponse {
	private int id;
	private int language_id;
	private String name;
	private String languageName;
	}
