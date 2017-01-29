package ie.cit.Taxes.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.cit.Taxes.domain.Citizen;
import ie.cit.Taxes.rowMapper.CitizenRowMapper;

@Repository
public class JdbcCitizenRepository implements CitizenRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcCitizenRepository(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void put(Citizen citizen) throws SQLException {
		
		System.out.println(citizen.getTakehome());
		
		String sql = "INSERT INTO citizen (id, name, surname, salary, takehome) VALUES (?,?,?,?,?)";
		
		 System.out.println("->"+jdbcTemplate.update(sql,citizen.getId(), citizen.getName(), citizen.getSurname(),
					citizen.getSalary(), citizen.getTakehome())+"<-");
	}

	@Override
	public List<Citizen> listAll() throws SQLException {
		
		String sql = "SELECT * FROM citizen";
		List<Citizen> citizens = jdbcTemplate.query(sql, new Object[] {}, new CitizenRowMapper());
		
		return citizens;
	}

	@Override
	public void updateCitizen(int id, float salary_change, float takehome) throws SQLException {
		
		String sql = "UPDATE citizen SET salary = ?, takehome = ? WHERE id = ?;";
		jdbcTemplate.update(sql,salary_change, takehome, id);
		
	}
	

}
