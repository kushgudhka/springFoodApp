package spring.kush.springCore.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class ResponseDTO {

	private int status;

	private String message;

	public ResponseDTO(HttpStatus status, String message) {
		this.status = status.value();
		this.message = message;
	}

}
