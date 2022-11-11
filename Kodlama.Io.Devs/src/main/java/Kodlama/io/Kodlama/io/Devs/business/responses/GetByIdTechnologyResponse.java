package Kodlama.io.Kodlama.io.Devs.business.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdTechnologyResponse {
	private int language_id;
	private String languageName;
	private String name;

}
