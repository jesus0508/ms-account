package pe.com.project1.ms.domain.customer;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {
	@Id
	private String id;
	private String names;
	private String firstSurname;
	private String lastSurname;
	private String dni;
	private String email;
	private LocalDate birthdate;
	private CustomerType customerType;

	public Customer(Customer customer) {

	}

	public Customer update(Customer customer) {
		return new Customer(id, customer.getNames(), customer.getFirstSurname(), customer.getLastSurname(),
				customer.getDni(), customer.getEmail(), customer.getBirthdate(), customer.getCustomerType());
	}
}
