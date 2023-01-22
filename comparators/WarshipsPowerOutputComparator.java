/**
 * 
 */
package project.comparators;

import java.util.Comparator;

/**
 * order warships according to their output power from high to low. if output
 * power is equal: order according id from low to high

 *
 */
public class WarshipsPowerOutputComparator<Warship> implements Comparator<Warship> {
	@Override
	public int compare(Warship o1, Warship o2) {
		if (((project.warship.Warship) o1).getPowerOutput() < ((project.warship.Warship) o2).getPowerOutput()) {
			return 1;
		} else if (((project.warship.Warship) o1).getPowerOutput() == ((project.warship.Warship) o2).getPowerOutput()) {
			if (((project.warship.Warship) o1).getId() > ((project.warship.Warship) o2).getId()) {
				return 1;
			}
			return -1;
		}
		return -1;
	}

}
