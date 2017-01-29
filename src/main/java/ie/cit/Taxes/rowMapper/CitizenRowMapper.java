package ie.cit.Taxes.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.Taxes.domain.Citizen;

public class CitizenRowMapper implements RowMapper<Citizen> {

	@Override
	public Citizen mapRow(ResultSet rs, int index) throws SQLException {
		
		Citizen citizen = new Citizen();
		
		citizen.setId(rs.getInt("id"));
		citizen.setName(rs.getString("name"));
		citizen.setSalary(rs.getFloat("salary"));
		
		citizen.setSurname(rs.getString("surname"));
		
		citizen.setTakehome(rs.getFloat("takehome"));
		
		return citizen;
	}

}
