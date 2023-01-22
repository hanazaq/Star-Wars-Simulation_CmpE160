/**
 * 
 */
package project.comparators;

import java.util.Comparator;

/**
 * orders warships according their id from low to high
 *
 */
public class WarshipsIdComparator <Warship> implements Comparator<Warship> {
	

	@Override
	public int compare(Warship o1, Warship o2) {
		if (((project.warship.Warship) o1).getId() > ((project.warship.Warship) o2).getId()) {
			return 1;
		}
		return -1;
	}
	
}
