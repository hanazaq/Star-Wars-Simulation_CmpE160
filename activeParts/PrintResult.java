/**
 * 
 */
package project.activeParts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import project.comparators.GeneralsCombatPowerComparator;
import project.comparators.OfficersIntrinsicLevelComparator;
import project.comparators.WarshipsIdComparator;
import project.comparators.WarshipsPowerOutputComparator;
import project.crewman.Crewman;
import project.crewman.General;
import project.crewman.Jedi;
import project.crewman.Officer;
import project.crewman.Sith;
import project.enums.StateWarship;
import project.warship.Warship;

/**
 * Writes results ordered with help of comparators
 * 
 * @author Hanaa Zaqout
 *
 */
public class PrintResult {
	static private ArrayList<Warship> intact = new ArrayList<Warship>();
	static private ArrayList<Warship> destroyed = new ArrayList<Warship>();
	static private ArrayList<General> generals = new ArrayList<General>();
	static private ArrayList<Officer> officers = new ArrayList<Officer>();

	/**
	 * writes Intact warships ordered by their powerOutput from higher to lower
	 * {@link #WarshipsPowerOutputComparator}
	 */
	private void writeIntactWarships() {

		Comparator<Warship> compare = new WarshipsPowerOutputComparator<Warship>();
		Collections.sort(intact, compare);

		for (Warship w : intact) {
			System.out.println("Warship " + w.getName() + " in (" + w.getCurrentSector().getName() + ", "
					+ w.getCoordinate() + ")");
			System.out.println(w.getCommander().getName() + " " + w.getPowerOutput());
		}

	}

	/**
	 * writes destroyed warships ordered by their id {@link #WarshipsIdComparator}
	 */
	private void writeDestroyedWarships() {
		Comparator<Warship> compare = new WarshipsIdComparator<Warship>();
		Collections.sort(destroyed, compare);
		for (Warship w : destroyed) {
			System.out.println("Warship " + w.getName() + " is destroyed by " + w.getDestroyer().getName() + " in ("
					+ w.getCurrentSector().getName() + "," + w.getCoordinate() + ")");
		}
	}

	/**
	 * filters warships to intact and destroyed
	 */
	private void writeWarships() {
		ArrayList<Warship> warships = CreateObjects.getWarships();

		for (int i = 1; i < warships.size(); i++) {
			if (warships.get(i).getState().equals(StateWarship.INTACT)) {
				intact.add(warships.get(i));
			} else if (warships.get(i).getState().equals(StateWarship.DESTROYED)) {
				destroyed.add(warships.get(i));
			}
		}
		writeIntactWarships();
		writeDestroyedWarships();
	}

	/**
	 * 
	 * @param c
	 * @return type of the Crewman
	 */
	private static String typeCrewman(Crewman c) {
		if (c instanceof Jedi) {
			return "Jedi";
		} else if (c instanceof Sith) {
			return "Sith";
		}

		return "Officer";

	}

	private static void killed(Crewman c) {
		System.out.println(typeCrewman(c) + " " + c.getName() + " is killed by " + c.getKiller().getName());
	}

	private static void free(Crewman c) {
		System.out.println(typeCrewman(c) + " " + c.getName() + " is free");
	}

	private static void onboard(Crewman c) {
		System.out.println(typeCrewman(c) + " " + c.getName() + " is in "
				+ CreateObjects.getWarships().get(c.getCurrentWarshipId()).getName());
	}

	private static void imprisoned(Crewman c) {
		System.out.println(typeCrewman(c) + " " + c.getName() + " is imprisoned");
	}

	/**
	 * checks the state of the crewman
	 * 
	 * @param c
	 */
	private static void checkState(Crewman c) {
		if (c.getState().equals(project.enums.StateCrewman.FREE)) {
			free(c);
		} else if (c.getState().equals(project.enums.StateCrewman.IMPRISONED)) {
			imprisoned(c);
		} else if (c.getState().equals(project.enums.StateCrewman.KILLED)) {
			killed(c);
		} else if (c.getState().equals(project.enums.StateCrewman.ONBOARD)) {
			onboard(c);
		}
	}

	/**
	 * writes generals ordered by their combarPower from higher to lower
	 * {@link #GeneralsCombatPowerComparator}
	 */
	private void writeGenerals() {
		Comparator<General> compare = new GeneralsCombatPowerComparator<General>();
		Collections.sort(generals, compare);
		for (General g : generals) {
			checkState(g);
			System.out.println(g.getCombatPower());
		}
	}

	/**
	 * writes officers ordered by their intrinsicLevel
	 * {@link #OfficersIntrinsicLevelComparator}
	 */
	private void writeOfficers() {
		Comparator<Officer> compare = new OfficersIntrinsicLevelComparator<Officer>();
		Collections.sort(officers, compare);
		for (Officer o : officers) {
			checkState(o);
			System.out.println(o.getIntrinsic() + " " + o.getIntrinsicLevel());
		}
	}

	// you should print generals then officers
	private void writeCrewmen() {
		ArrayList<Crewman> crewmen = CreateObjects.getCrewmen();

		for (int i = 1; i < crewmen.size(); i++) {
			if (crewmen.get(i) instanceof General) {
				generals.add((General) crewmen.get(i));
			} else if (crewmen.get(i) instanceof Officer) {
				officers.add((Officer) crewmen.get(i));
			}
		}
		writeGenerals();
		writeOfficers();

	}

	public void printFinalResult() {
//		System.out.println("############################################");
		this.writeWarships();
		this.writeCrewmen();
	}
}
