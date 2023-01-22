/**
 * 
 */
package project.comparators;

import java.util.Comparator;

/**
 * orders warships according to their xCoordinate from low to high
 * 
 
 *
 */
public class WarshipsCoordinateComparator<Warship> implements Comparator<Warship> {

	@Override
	public int compare(Warship o1, Warship o2) {
		if (((project.warship.Warship) o1).getCoordinate() > ((project.warship.Warship) o2).getCoordinate()) {
			return 1;
		}
		return -1;
	}

}
