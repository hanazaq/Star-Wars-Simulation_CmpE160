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
public class SeparatistBattleship extends SeparatistDestroyer {
	/*
	 * Necessary methods
	 */
	/**
	 * constructor
	 * @param id
	 * @param name
	 * @param currentSector
	 * @param coordinate
	 * @param crew
	 */
	public SeparatistBattleship(int id, String name, Sector currentSector, int coordinate, ArrayList<Crewman> crew) {
		super(id, name, currentSector, coordinate, crew);
		this.armamentPower=400;
		this.shieldPower=200;
		this.crewCapacity=20;
		this.escapePods=3;
	}

}
