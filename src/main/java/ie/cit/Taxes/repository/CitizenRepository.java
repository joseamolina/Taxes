package ie.cit.Taxes.repository;

import java.sql.SQLException;
import java.util.List;

import ie.cit.Taxes.domain.Citizen;

public interface CitizenRepository {
	
	public void put(Citizen citizen) throws SQLException;
	
	public List<Citizen> listAll() throws SQLException;
	
	public void updateCitizen(int id, float salary_chang, float takehome) throws SQLException;

}
