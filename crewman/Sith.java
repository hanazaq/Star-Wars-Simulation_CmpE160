/**
 * 
 */
package project.crewman;

/**
 *
 */
public class Sith extends General {
	/*
	 * Necessary fields
	 */
	private int persuasion;

	/*
	 * Necessary methods
	 */
	/**
	 * constructor
	 * @param id
	 * @param name
	 * @param experience
	 * @param midichlorian
	 * @param persuasion
	 */
	public Sith(int id, String name, int experience, int midichlorian, int persuasion) {
		super(id, name, experience, midichlorian);
		this.persuasion = persuasion;
	}
	/**
	 * A Sith’s combat power is calculated as follows: Force Power + experience +
	 * persuasion
	 * @return combatpower
	 */
	public int getCombatPower() {
		int combatPower= this.getForcePower()+this.experience+this.persuasion;
		return combatPower;
	}
	/**
	 * getters and setters
	 * @return
	 */
	public int getPersuasion() {
		return persuasion;
	}
	public void setPersuasion(int persuasion) {
		this.persuasion = persuasion;
	}
}
