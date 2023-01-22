package project.comparators;

import java.util.Comparator;

/**
 * order officers according to their IntrinsicLevel from high to low. if
 * IntrinsicLevel is equal: order according id from low to high
 * 

 */
public class OfficersIntrinsicLevelComparator<Officer> implements Comparator<Officer> {
	@Override
	public int compare(Officer o1, Officer o2) {
		if (((project.crewman.Officer) o1).getIntrinsicLevel() < ((project.crewman.Officer) o2).getIntrinsicLevel()) {
			return 1;
		} else if (((project.crewman.Officer) o1).getIntrinsicLevel() == ((project.crewman.Officer) o2)
				.getIntrinsicLevel()) {
			if (((project.crewman.Officer) o1).getId() > ((project.crewman.Officer) o2).getId()) {
				return 1;
			}
			return -1;
		}
		return -1;
	}
}
