package assignment4;

public class Critter1 extends Critter {

	@Override
	public String toString() { return "1"; }
	
	@Override
	public void doTimeStep() {
		Critter1 offspring1 = new Critter1();
		Critter1 offspring2 = new Critter1();
		reproduce (offspring1, Critter.getRandomInt(8));
		reproduce (offspring2, Critter.getRandomInt(8));
		
	}

	@Override
	public boolean fight(String opponent) {
		return true;
	}

}
