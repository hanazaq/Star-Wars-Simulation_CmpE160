/**
 * 
 */
package project.crewman;

import project.interfaces.IForceUser;

/**
 *
 */
public abstract class General extends Crewman implements IForceUser {
	/*
	 * Necessary fields
	 */
	protected int experience;
	protected int midichlorian;

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
	 */
	public General(int id, String name, int experience, int midichlorian) {
		super(id, name);
		this.experience = experience;
		this.midichlorian = midichlorian;
	}

	/**
	 * @return forcePower
	 */
	@Override
	public int getForcePower() {
		int forcePower = 0;
		if (this instanceof Jedi) {
			forcePower = 3 * this.midichlorian;
		} else if (this instanceof Sith) {
			forcePower = 4 * this.midichlorian;
		}
		return forcePower;
	}

	/**
	 * will be implemented in children
	 */
	public abstract int getCombatPower();

	/**
	 * getters and setters
	 * 
	 * @return
	 */
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMidichlorian() {
		return midichlorian;
	}

	public void setMidichlorian(int midichlorian) {
		this.midichlorian = midichlorian;
	}
}
