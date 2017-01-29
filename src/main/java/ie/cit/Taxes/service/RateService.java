package ie.cit.Taxes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ie.cit.Taxes.domain.Rate;

@Service
public interface RateService {
	
	public List<Rate> getRateBands();

	public boolean changeRateBands(List<Rate> ratesNew);

	float calculateTakeHome(float salary);

}
