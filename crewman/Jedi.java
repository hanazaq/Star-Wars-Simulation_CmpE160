/**
 * 
 */
package project.crewman;

/**

 *
 */
public class Jedi extends General {
	/*
	 * Necessary fields
	 */
	private int sanity;
	private int intelligence;

	/*
	 * Necessary methods
	 */
	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param experience
	 * @param midichlorian
	 * @param intelligence
	 */
	public Jedi(int id, String name, int experience, int midichlorian, int intelligence) {
		super(id, name, experience, midichlorian);
		this.intelligence = intelligence;
		this.sanity = 100;

	}

	/**
	 * A Jedi’s combat power is calculated as follows: Force Power + experience +
	 * sanity - 100 + intelligence
	 * 
	 * @return combatPower
	 */
	public int getCombatPower() {
		int combatPower = this.getForcePower() + this.experience + (this.sanity - 100) + this.intelligence;
		return combatPower;
	}

	/**
	 * getters and setters
	 * 
	 * @return
	 */
	public int getSanity() {
		return sanity;
	}

	public void setSanity(int sanity) {
		this.sanity = sanity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

}
