package People;

import ie.ucd.items.*;
import ie.ucd.people.Person;

public class Drinker extends Person {

	private int numberOfDrinks = 0;
	private String firstName;
	private String lastName;

	public Drinker(String firstName, String lastName, int weight) {
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	// An alcoholic drinker can drink alcoholic drinks and non alcoholic drinks
	public boolean drink(Drink arg0) {

		if (arg0 instanceof AlcoholicDrink) { // if the person is drinking alcohol, incrememnt their alcoholic drink
												// count

			numberOfDrinks += 1;
			System.out.println(firstName + " you have had a total of " + numberOfDrinks + " drinks"); // Alert them how
																										// many drinks
																										// they have had
		} else if (arg0 instanceof NotAlcoholicDrink) { // They can also have regular drinks, the counter should not be
														// incremented
			System.out.println("Well done for not drinking alcohol, " + firstName);

		}

		return true;
	}

	@Override
	public boolean eat(Food arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	// Method to check is the person is drunk
	public boolean isDrunk() {
		double weight = getWeight(); // First get the persons weight
		double drunkMeasure = weight / 10; // Used to check if the person has passed their limit
		if (numberOfDrinks > drunkMeasure) { // If they have passed their limit, alert the person
			System.out.println(firstName + " ,you have drank too much ");
			return true;

		} else {
			return false;
		}

	}

}
