package ie.cit.Taxes.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.cit.Taxes.domain.Rate;
import ie.cit.Taxes.rowMapper.RateRowMapper;

@Repository
public class JdbcRateRepository implements RateRepository {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcRateRepository(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void changeRate(List<Rate> ratesNew) throws SQLException {
		
		int id;
		float band;
		float rateField;
		
		String sql = "TRUNCATE TABLE rate;";
		jdbcTemplate.update(sql);
		
		for (Rate r: ratesNew){
			id = r.getId();
			band = r.getBand();
			rateField = r.getRate();

			sql = "INSERT INTO rate (id, band, ratefield) VALUES (?,?,?)";
			jdbcTemplate.update(sql,id, band, rateField);
			
		}
	}

	@Override
	public List<Rate> listCurrentRate() throws SQLException {
		
		String sql = "SELECT * FROM rate;";

		List<Rate> listCitizens = jdbcTemplate.query(sql, new Object[] {}, new RateRowMapper());

		return listCitizens;
	}
	
	

}
