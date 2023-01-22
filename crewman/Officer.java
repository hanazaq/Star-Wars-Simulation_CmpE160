/**
 * 
 */
package project.crewman;

import project.enums.Intrinsic;

/**
 *
 */
public class Officer extends Crewman {
	/*
	 * Necessary fields
	 */
	private int intrinsicLevel;
	private Intrinsic intrinsic;

	/*
	 * Necessary methods
	 */
	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param intrinsic
	 * @param intrinsicLevel
	 */
	public Officer(int id, String name, Intrinsic intrinsic, int intrinsicLevel) {
		super(id, name);
		this.intrinsic = intrinsic;
		this.intrinsicLevel = intrinsicLevel;
	}

	/**
	 * An officer id will be given Training increments his intrinsic level by 1.
	 * Maximum value for intrinsic level is 10. 
	 */
	public void train() {
		if (this.intrinsicLevel < 10) {
			this.intrinsicLevel++;
		}
	}

	/**
	 * getters and setters
	 * 
	 * @return
	 */
	public int getIntrinsicLevel() {
		return intrinsicLevel;
	}

	public void setIntrinsicLevel(int intrinsicLevel) {
		this.intrinsicLevel = intrinsicLevel;
	}

	public Intrinsic getIntrinsic() {
		return intrinsic;
	}

	public void setIntrinsic(Intrinsic intrinsic) {
		this.intrinsic = intrinsic;
	}

}
