package ie.cit.Taxes.domain;

public class Citizen implements Component {
	
	int id;
	String name, surname;
	float salary, takehome;
	
	
	
	public Citizen() {
		super();
	}

	public Citizen(int id, String name, String surname, float salary, float takehome) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.takehome = takehome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getTakehome() {
		return takehome;
	}

	public void setTakehome(float takehome) {
		this.takehome = takehome;
	}
	
	@Override
	public String toString(){
		
		return "Id: " + this.id + ", name: " + this.name + ", surname: " + this.surname + ", salary: €" + this.salary
				+ ", take-home money: €" + this.takehome + "."; 
	}
	
}
