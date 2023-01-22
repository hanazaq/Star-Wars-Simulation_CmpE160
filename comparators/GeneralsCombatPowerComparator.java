/**
 * 
 */
package project.comparators;

import java.util.Comparator;

/**
 * 
 * order generals according to their combat power from high to low. if power is
 * equal: order according id from low to high
 * 

 */
public class GeneralsCombatPowerComparator<General> implements Comparator<General> {

	@Override
	public int compare(General o1, General o2) {
		if (((project.crewman.General) o1).getCombatPower() < ((project.crewman.General) o2).getCombatPower()) {
			return 1;
		} else if (((project.crewman.General) o1).getCombatPower() == ((project.crewman.General) o2).getCombatPower()) {
			if (((project.crewman.General) o1).getId() > ((project.crewman.General) o2).getId()) {
				return 1;
			}
			return -1;
		}
		return -1;
	}
}
