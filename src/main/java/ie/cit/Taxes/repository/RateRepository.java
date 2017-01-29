package ie.cit.Taxes.repository;

import java.sql.SQLException;
import java.util.List;

import ie.cit.Taxes.domain.Rate;

public interface RateRepository {
	
	public void changeRate(List<Rate> ratesNew) throws SQLException;
	
	public List<Rate> listCurrentRate() throws SQLException;

}
