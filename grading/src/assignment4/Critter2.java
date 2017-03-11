package assignment4;

/**
 * Critter2 - this critter moves extremely quickly because it chooses to run every time step.
 *During a fight, this critter will reproduce once, then fight only if the opponent is algae, otherwise it will not try.
 */
public class Critter2 extends Critter {
	
	@Override
	public String toString() { return "2"; }

	@Override
	public void doTimeStep() {
		run(Critter.getRandomInt(8));
	}

	@Override
	public boolean fight(String opponent) {
		Critter2 offspring = new Critter2();
		reproduce (offspring, Critter.getRandomInt(8));
		if (opponent.equals("@")) {
			return true;
		}
		else {
			return false;
		}
	}

}
