package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Ho-chang Chang
 * hc23882
 * 16220
 * Slip days used: <0>
 * Spring 2017
 */


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static List<Boolean> hasMoved = new ArrayList<Boolean>();
	private static List<ArrayList<Critter>> map = new ArrayList<ArrayList<Critter>>();


	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	// initializes map
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
		// may not be allowed
		for (int y = 0; y < Params.world_height; y++) {
			for (int x = 0; x < Params.world_width; x++) {
				map.add(new ArrayList<Critter>());
			}
		}
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		StackTraceElement current = Thread.currentThread().getStackTrace()[2];
		int index = population.indexOf(this);
		if (current.getMethodName().equals("fight") && !hasMoved.get(index)) {
			int x, y;
			if (direction == 0 || direction == 1 || direction == 7) {
				x = x_coord + 1;
			}
			else if (direction == 3 || direction == 4 || direction == 5) {
				x = x_coord - 1;
			}
			else {
				x = x_coord;
			}
			if (direction == 1 || direction == 2 || direction == 3) {
				y = y_coord + 1;
			}
			else if (direction == 5 || direction == 6 || direction == 7) {
				y = y_coord - 1;
			}
			else {
				y= y_coord;
			}
			if (x >= Params.world_width) {
				x -= Params.world_width;
			}
			if (x < 0) {
				x += Params.world_width;
			}
			if (y >= Params.world_height) {
				y -= Params.world_height;
			}
			if (y < 0) {
				y += Params.world_height;
			}
			if (map.get(y * Params.world_width + x).isEmpty()) {
				x_coord = x;
				y_coord = y;
				hasMoved.set(index, true);
			}

		}
		else if (!hasMoved.get(index)) {
			if (direction == 0 || direction == 1 || direction == 7) {
				x_coord++;
			}
			else if (direction == 3 || direction == 4 || direction == 5) {
				x_coord--;
			}
			if (direction == 1 || direction == 2 || direction == 3) {
				y_coord++;
			}
			else if (direction == 5 || direction == 6 || direction == 7) {
				y_coord--;
			}
			if (x_coord >= Params.world_width) {
				x_coord -= Params.world_width;
			}
			if (x_coord < 0) {
				x_coord += Params.world_width;
			}
			if (y_coord >= Params.world_height) {
				y_coord -= Params.world_height;
			}
			if (y_coord < 0) {
				y_coord += Params.world_height;
			}
			hasMoved.set(index, true);
		}
		energy -= Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
		StackTraceElement current = Thread.currentThread().getStackTrace()[2];
		int index = population.indexOf(this);
		if (current.getMethodName().equals("fight") && !hasMoved.get(index)) {
			int x, y;
			if (direction == 0 || direction == 1 || direction == 7) {
				x = x_coord + 2;
			}
			else if (direction == 3 || direction == 4 || direction == 5) {
				x = x_coord - 2;
			}
			else {
				x = x_coord;
			}
			if (direction == 1 || direction == 2 || direction == 3) {
				y = y_coord + 2;
			}
			else if (direction == 5 || direction == 6 || direction == 7) {
				y = y_coord - 2;
			}
			else {
				y= y_coord;
			}
			if (x >= Params.world_width) {
				x -= Params.world_width;
			}
			if (x < 0) {
				x += Params.world_width;
			}
			if (y >= Params.world_height) {
				y -= Params.world_height;
			}
			if (y < 0) {
				y += Params.world_height;
			}
			if (map.get(y * Params.world_width + x).isEmpty()) {
				x_coord = x;
				y_coord = y;
				hasMoved.set(index, true);
			}
		}
		else if (!hasMoved.get(index)) {
			if (direction == 0 || direction == 1 || direction == 7) {
				x_coord += 2;
			}
			else if (direction == 3 || direction == 4 || direction == 5) {
				x_coord -= 2;
			}
			if (direction == 1 || direction == 2 || direction == 3) {
				y_coord += 2;
			}
			else if (direction == 5 || direction == 6 || direction == 7) {
				y_coord -= 2;
			}
			if (x_coord >= Params.world_width) {
				x_coord -= Params.world_width;
			}
			if (x_coord < 0) {
				x_coord += Params.world_width;
			}
			if (y_coord >= Params.world_height) {
				y_coord -= Params.world_height;
			}
			if (y_coord < 0) {
				y_coord += Params.world_height;
			}
			hasMoved.set(index, true);
		}	
		energy -= Params.run_energy_cost;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if (energy >= Params.min_reproduce_energy) {
			offspring.energy = energy / 2;
			energy -= offspring.energy;
			offspring.x_coord = x_coord;
			offspring.y_coord = y_coord;
			if (direction == 0 || direction == 1 || direction == 7) {
				offspring.x_coord++;
			}
			else if (direction == 3 || direction == 4 || direction == 5) {
				offspring.x_coord--;
			}
			if (direction == 1 || direction == 2 || direction == 3) {
				offspring.y_coord++;
			}
			else if (direction == 5 || direction == 6 || direction == 7) {
				offspring.y_coord--;
			}
			if (offspring.x_coord >= Params.world_width) {
				offspring.x_coord -= Params.world_width;
			}
			if (offspring.x_coord < 0) {
				offspring.x_coord += Params.world_width;
			}
			if (offspring.y_coord >= Params.world_height) {
				offspring.y_coord -= Params.world_height;
			}
			if (offspring.y_coord < 0) {
				offspring.y_coord += Params.world_height;
			}
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String opponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try{
			Class<?> passCritter = Class.forName(critter_class_name);
			Critter newCritter = (Critter)passCritter.getConstructor().newInstance();
			newCritter.x_coord = Critter.getRandomInt(Params.world_width);
			newCritter.y_coord = Critter.getRandomInt(Params.world_height);
			newCritter.energy = Params.start_energy;
			population.add(newCritter);
			hasMoved.add(false);
		}
		catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		try {			
			List<Critter> result = new java.util.ArrayList<Critter>();
			Class<?> critterClass = Class.forName(critter_class_name);
			for (Critter current : population) {			
				if (critterClass.isInstance(current)) {
					result.add(current);
				}
			}
			return result;
		}
		catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}		
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		while (population.size() > 0) {
			population.remove(0);
			hasMoved.remove(0);
		}
	}
	
	public static void worldTimeStep() {
		
		// simulate individual time steps
		for (int i = 0; i < population.size(); i++) {
			Critter current = population.get(i);
			current.doTimeStep();
			if (current.energy <= 0) {
				population.remove(i);
				hasMoved.remove(i);
			}
			else {
				map.get(current.y_coord * Params.world_width + current.x_coord).add(current);
				i++;
			}
		}
		
		// resolve encounters

		for (int i = 0; i < map.size(); i++) {
			ArrayList<Critter> current = map.get(i);
			while (current.size() > 1) {
				Critter first = current.get(0);
				Critter second = current.get(1);
				int firstChance, secondChance;
				int firstIndex = population.indexOf(first);
				int secondIndex = population.indexOf(second);		
				// account for critters that move while fighting
				int first_x = first.x_coord;
				int first_y = first.y_coord;
				int second_x = second.x_coord;
				int second_y = second.y_coord;
				if (first.fight(second.toString())) {
					firstChance = Critter.getRandomInt(first.energy + 1);
				}
				else {
					firstChance = 0;
				}
				if (second.fight(first.toString())) {
					secondChance = Critter.getRandomInt(second.energy + 1);
				}
				else {
					secondChance = 0;
				}
				// if critters die from fighting
				if (first.energy <= 0 || second.energy <= 0) {
					if (first.energy <= 0) {
						current.remove(first);
						population.remove(firstIndex);
						hasMoved.remove(firstIndex);
					}
					if (second.energy <= 0) {
						current.remove(second);
						population.remove(secondIndex);
						hasMoved.remove(secondIndex);
					}
				}
				// if critters flee from fighting
				else if (first_x != first.x_coord || first_y != first.y_coord || second_x != second.x_coord || second_y != second.y_coord) {
					if (first_x != first.x_coord || first_y != first.y_coord) {
						current.remove(first);
					}
					if (second_x != second.x_coord || second_y != second.y_coord) {
						current.remove(second);
					}
				}
				// fight
				else if (firstChance > secondChance) {
					first.energy += (second.energy + 1)/2;
					current.remove(second);
					population.remove(secondIndex);
					hasMoved.remove(secondIndex);
					
				}
				else {
					second.energy += (first.energy + 1)/2;
					current.remove(first);
					population.remove(firstIndex);
					hasMoved.remove(firstIndex);			
				}
			}
		}
		
		
		// update rest energy, remove resulting dead critters
		int j = 0;
		while (j < population.size()) {
			Critter current = population.get(j);
			current.energy -= Params.rest_energy_cost;
			if (current.energy <= 0) {
				population.remove(j);
				hasMoved.remove(j);
			}
			else {
				j++;
			}
		}
		
		// generate algae 
		for (int i = 0; i < Params.refresh_algae_count; i++) {      				
			Algae newAlgae = new Algae();		
			newAlgae.setX_coord(Critter.getRandomInt(Params.world_width));
			newAlgae.setY_coord(Critter.getRandomInt(Params.world_height));
			newAlgae.setEnergy(Params.start_energy);
			population.add(newAlgae);
			hasMoved.add(false);
		}
		// resolve algae encounters
		
		
		
		// reset movement flags
		for (int i = 0; i < hasMoved.size(); i++) {
			hasMoved.set(i, false);
		}
		
		// add babies
		population.addAll(babies);
		for (int i = 0; i < babies.size(); i++) {
			hasMoved.add(false);
		}
		babies.clear();
	}
	
	public static void displayWorld() {
		Critter [] grid = new Critter[Params.world_height*Params.world_width];
		for (Critter current : population) {
			if (grid[current.y_coord * Params.world_width + current.x_coord] == null) {
				grid[current.y_coord * Params.world_width + current.x_coord] = current;
			}
			else {
				System.out.println("Error: conflicts were not correctly resolved");
			}
		}
		
		System.out.print("+");
		for (int x = 0; x < Params.world_width; x++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		for (int y = 0; y < Params.world_height; y++) {
			System.out.print("|");
			for (int x = 0; x < Params.world_width; x++) {
				Critter current = grid[y * Params.world_width + x];
				if (current != null) {
					System.out.print(current);
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}
		
		System.out.print("+");
		for (int x = 0; x < Params.world_width; x++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
}
