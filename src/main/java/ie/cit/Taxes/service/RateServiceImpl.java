package ie.cit.Taxes.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.Taxes.domain.Rate;
import ie.cit.Taxes.repository.RateRepository;

@Service
public class RateServiceImpl implements RateService {
	
	private RateRepository rateRepository;

	@Autowired
	public RateServiceImpl(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
		
	}

	@Override
	public boolean changeRateBands(List<Rate> ratesNew) {
		boolean received = true;
		
		try {
			rateRepository.changeRate(ratesNew);
			
		} catch (SQLException e) {
			received = false;
		}
		return received;
		
	}
	
	@Override
	public float calculateTakeHome(float salary) {
		
		List<Rate> rates = getRateBands();
		
		Rate solTax = rates.get(rates.size()-1);
		
		float substract = salary;
		
		float totalTaxes = (float) 0.0;
		
		float rate = (float) 0.0;
		float band = (float) 0.0;
		
		if (solTax.getBand() == 0.0){
			solTax = rates.remove(rates.size()-1);
			rate = solTax.getRate();
			totalTaxes += (float) salary * ((float)rate/100);
		}

		for (int i = 0; i < rates.size(); i++){
			
			rate = rates.get(i).getRate();
			
			band = rates.get(i).getBand();
			
			if (i == rates.size()-1){
				totalTaxes += ((float) substract * ( (float) rate / 100));
				
			}else{
				
				if (substract - band <= 0.0){
					
					
					totalTaxes += ((float) substract  * ( (float) rate / 100));
					break;
					
				}else{
					
					substract -= band;
					totalTaxes += ((float) band * ( (float) rate / 100));
				}
				
	
			}
			
		}
		
		
		
		return salary - totalTaxes;
		
		
	}

	
	@Override
	public List<Rate> getRateBands() {
		List<Rate> listRates;
		
		try {
			listRates = rateRepository.listCurrentRate();
		} catch (SQLException e) {
			
			listRates = new ArrayList<Rate>();
		}
		
		return listRates;
	}
	
}