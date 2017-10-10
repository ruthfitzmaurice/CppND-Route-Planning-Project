package People;

import ie.ucd.items.*;
import ie.ucd.people.Person;

public class NotDrinker extends Person {
	private String firstName;
	private String lastName;
	private int weight;

	public NotDrinker(String firstName, String lastName, int weight) {
		this.firstName = firstName;
		this.lastName = lastName;

	}

	// A NotDrinker can only drink non alcoholic drinks. Should be an error message
	// if they try to drink alcohol
	public boolean drink(Drink arg0) {
		if (arg0 instanceof AlcoholicDrink) {
			System.out.println(firstName + ", you cant drink alcohol...");
			return false;
		} else if (arg0 instanceof NotAlcoholicDrink) {
			System.out.println(firstName + ", well done dont drink alcohol");
			return true;
		} else {
			return true;
		}
	}

	@Override
	public boolean eat(Food arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
