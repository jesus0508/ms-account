package pe.com.project1.ms.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerType {
	PERSONAL("PERSONAL"), 
	ENTERPRISE("ENTERPRISE");
	private String value;

}
