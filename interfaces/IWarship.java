package project.interfaces;

import project.activeParts.Sector;
import project.crewman.Crewman;
import project.crewman.General;

public interface IWarship {
	/**
	 * Adds a crewman to ship if crewman is accepted by the warship.
	 * 
	 * @param crewman
	 */
	void addCrewman(Crewman crewman);

	/**
	 * Removes a crewman if it exist in the warship.
	 * 
	 * @param crewman
	 */

	void removeCrewman(Crewman crewman);

	/**
	 * Relocate the warship’s active sector and coordinate.
	 * 
	 * @param sector
	 * @param coordinate
	 */
	void jumpToSector(Sector sector, int coordinate);

	/**
	 * power= Sector Buff * Armament Power + Shield Power + General’s Contribution +
	 * Officer’s Contribution
	 * 
	 * @return the power output of the warship.
	 */
	int getPowerOutput();

	/**
	 * 
	 * In a Republic Cruiser, the commander is the one with the highest experience
	 * (in case of equality the one with the lower id will become commander). In a
	 * Separatist ship, the commander is the one who has the highest combat power
	 * (in case of equality the one with the lower id will become the commander).
	 * 
	 * @return the general of the warship.
	 */

	General getCommander();

	/**
	 * Increment warship armament power by amount
	 * 
	 * @param amount
	 */

	void upgradeArmament(int amount);

	/**
	 * Increment warship shield power by amount
	 * 
	 * @param amount
	 */
	void upgradeShield(int amount);
}
