package ie.cit.Taxes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ie.cit.Taxes.domain.Citizen;

@Service
public interface CitizenService {
	
	public List<Citizen> listCitizens();
	
	public boolean introduceCitizen(int id , String name, String surname, float salary);
	
	public boolean changeSalary(int id, float newSalary);


}
