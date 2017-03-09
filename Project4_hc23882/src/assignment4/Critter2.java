package assignment4;

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
