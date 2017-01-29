package ie.cit.Taxes.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.Taxes.domain.Rate;

public class RateRowMapper implements RowMapper<Rate> {

	@Override
	public Rate mapRow(ResultSet rs, int index) throws SQLException {
	
		
		Rate rate = new Rate();
		rate.setId(rs.getInt("id"));
		rate.setBand(rs.getFloat("band"));
		rate.setRate(rs.getFloat("rateField"));
		return rate;
	}
	
	

}
