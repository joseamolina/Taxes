package ie.cit.Taxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import ie.cit.Taxes.domain.Citizen;
import ie.cit.Taxes.domain.Rate;
import ie.cit.Taxes.repository.CitizenRepository;
import ie.cit.Taxes.repository.RateRepository;
import ie.cit.Taxes.service.CitizenService;
import ie.cit.Taxes.service.RateService;

/* 
 * @Author: Jose Angel Molina
 * 
 * November 2016
 * 
 * Cork Institute of technology
 * 
 * */

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {
	
	/**
	 *All autowiring classes 
	 */
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CitizenRepository citizenRepository;
	
	@Autowired
	RateRepository rateRepository;
	
	@Autowired
	RateService rateService;
	
	@Autowired
	CitizenService citizenService;
	
	Scanner sc;

	public static void main(String[] args) {
		
		SpringApplication.run(TaxesApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		initial();

	}

	//TODO: review rates
	private void changeRate() {

		System.out.println("Introduce the details of band rates, when you finish, type E: ");
		System.out.println("After this; you can either finish or add a solidarity rate.");
		
		int index = 1;
		
		String intro = "";

		List<Rate> listRates = new ArrayList<Rate>();

		float band = (float) 0.0;
		float rate = (float) 0.0;
		
		Rate r;
		
		while (!intro.equals("E")){
			System.out.println("Introduce the details of band: " + Character.toString((char) (65+index - 1)) + ": ");
			System.out.println("Introduce band(float): ");
			band = Float.parseFloat(sc.next());
			System.out.println("Introduce rate %(float): ");
			rate = Float.parseFloat(sc.next());
			
			System.out.println("Type E to finish or, any other key to continue ");
			intro = sc.next();
			
			if (intro.equals("E")){
				
				r = new Rate(index, (float) -1.0 , rate);
				
				
			}else{
				r = new Rate(index, band , rate);
			}
			
			listRates.add(index-1, r);
			index++;
		}
		
		System.out.println("Do you want to introduce a solidarity tax: ");
		System.out.println("					Y   			yes");
		System.out.println("					any other key   no");
		
		intro = sc.next();
		
		if (intro.equals("Y")){
			
			System.out.println("Introduce the solidarity tax: ");
			rate = Float.parseFloat(sc.next());
			r = new Rate(index,(float) 0.0 , rate);
			listRates.add(index-1, r);
		}
		
		System.out.println("Thank you!");
		
		System.out.println(listRates);
		
		rateService.changeRateBands(listRates);
		
		System.out.println("finish");

	}
	
	
	/*
	 * The beggining method
	 * 
	 * 
	 * */
	private void initial(){
		System.out.println("Hello to the taxation application of Kegdonia!");
		String option = "";
		sc = new Scanner(System.in);

		do {

			System.out.println("\nSelect an option:");
			System.out.println("	List all citizens in the database:				A");
			System.out.println("	Introduce a citizen in the database:			B");
			System.out.println("	Change the salary of a citizen:					C");
			System.out.println("	Change the taxation bands:						D");
			System.out.println("	Display the current taxation bands:				F");
			System.out.println("	Exit the application:							E");
			
			System.out.println("---------------------------------------------------------");
			option = sc.next();

			switch (option) {
			case "A":
				listCitizens();
				break;

			case "B":
				introduceCitizen();
				break;

			case "C":
				changeSalary();
				break;

			case "D":
				changeRate();
				break;
				
			case "F":
				displayRates();
				break;

			case "E":
				System.out.println("GoodBye!");
				break;

			default:
				System.out.println("Sorry, introduce an appropiate value!");
				

			}

		} while (!option.equals("E"));

		sc.close();
	}
	
	private void displayRates(){
		System.out.println("Here are the rates: \n");
		
		for (Rate r: rateService.getRateBands()){
			System.out.println(r);
			
		}
		System.out.println("------------------------------");
	}

	
	private void listCitizens() {
		List<Citizen> citizens = citizenService.listCitizens();

		if (citizens.isEmpty()) {

			System.out.println("There aren't any citizens in the database.");

		} else {
			System.out.println("The list of citizens: ");

			for (Citizen c : citizens) {
				System.out.println(c.toString());
				System.out.println("-----------------------------------------------");
			}

		}

	}

	
	private void introduceCitizen() {

		System.out.println("Introduce secquentially the details of the new citizen: ");

		int id;
		String name = "", surname = "";
		float salary;

		try{
			System.out.println("Introduce his/her id (integer): ");
			id = sc.nextInt();
	
			System.out.println("Introduce his/her name(string):");
	
			name = sc.next().trim();
	
			System.out.println("Introduce his/her surname(string):");
	
			surname = sc.next().trim();
	
			System.out.println("Introduce his/her salary(float):");
	
			salary = Float.parseFloat(sc.next());
	
			if (citizenService.introduceCitizen(id, name, surname, salary))
				System.out.println("A new citizen has been introduced!");
	
			else
				System.out.println("It was unable to introduce the salary, sorry!");
		
		}catch(Exception e){
			System.out.println("Some parameter was not introduced properly. Try again!");
		}

	}

	private void changeSalary() {

		try{
			System.out.println("You are gonna change the salary details of a citizen: ");
	
			int id;
			float salary;
	
			System.out.println("Introduce his/her id (integer): ");
	
			id = sc.nextInt();
	
			System.out.println("Introduce his/her salary(float):");
	
			salary = Float.parseFloat(sc.next());
	
			if (citizenService.changeSalary(id, salary))
				System.out.println("The new salary has been adapted now!");
	
			else
				System.out.println("It was unable to change the salary, sorry!");
		
		}catch(Exception e){
			System.out.println("Some parameter was not introduced properly. Try again!");
		}

	}
	
	
	
	

}
