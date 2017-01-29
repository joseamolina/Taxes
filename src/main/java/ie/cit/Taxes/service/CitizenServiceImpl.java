package ie.cit.Taxes.service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.Taxes.domain.Citizen;
import ie.cit.Taxes.repository.CitizenRepository;

@Service
public class CitizenServiceImpl implements CitizenService {
	
	CitizenRepository citizenRepository;
	
	RateService rateService;
	
	@Autowired
	public CitizenServiceImpl(CitizenRepository citizenRepository, RateService rateService ) {
		this.rateService = rateService;
		this.citizenRepository = citizenRepository;
	}

	
	//---
	@Override
	public List<Citizen> listCitizens() {
		List<Citizen> listCitizens;
		
		try{
			listCitizens = citizenRepository.listAll();
			
		}catch(SQLException e){
			
			listCitizens = new ArrayList<Citizen>();
		}
		
		return listCitizens;
	}

	
	//-----
	@Override
	public boolean introduceCitizen(int id, String name, String surname, float salary) {
		
		boolean correct = true;
		
		
		try{
			
			float takehome = rateService.calculateTakeHome(salary);
			Citizen citizen = new Citizen(id, name, surname, salary, takehome);
			citizenRepository.put(citizen);
			
		}catch(SQLException e){
			correct = false;
		}
		
		return correct;
	}

	//------
	@Override
	public boolean changeSalary(int id, float newSalary) {
		
		boolean correct = true;
		
		try{
			
			float takehome = rateService.calculateTakeHome(newSalary);
			
			citizenRepository.updateCitizen(id, newSalary, takehome);
			
		} catch(SQLException e) {
			correct = false;
		}
		
		return correct;
		
	}


}
