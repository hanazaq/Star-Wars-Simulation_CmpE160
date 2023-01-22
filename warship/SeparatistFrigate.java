/**
 * 
 */
package project.warship;

import java.util.ArrayList;

import project.activeParts.Sector;
import project.crewman.Crewman;

/**
 * @author pc
 *
 */
public class SeparatistFrigate extends SeparatistDestroyer {
	/*
	 * Necessary methods
	 */
	/**
	 * construcotr
	 * @param id
	 * @param name
	 * @param currentSector
	 * @param coordinate
	 * @param crew
	 */
	public SeparatistFrigate(int id, String name, Sector currentSector, int coordinate, ArrayList<Crewman> crew) {
		super(id, name, currentSector, coordinate, crew);
		this.armamentPower=120;
		this.shieldPower=100;
		this.crewCapacity=12;
		this.escapePods=2;
	}

}
