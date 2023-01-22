/**
 * 
 */
package project.activeParts;

import java.util.ArrayList;
import java.util.Scanner;

import project.crewman.Crewman;
import project.crewman.Jedi;
import project.crewman.Officer;
import project.crewman.Sith;
import project.enums.Affiliation;
import project.enums.Intrinsic;
import project.enums.StateCrewman;
import project.enums.StateWarship;
import project.warship.RepublicCruiser;
import project.warship.SeparatistBattleship;
import project.warship.SeparatistDestroyer;
import project.warship.SeparatistFrigate;
import project.warship.Warship;

/**
 * creates objects out of the given input
 * 
 * @author Hanaa Zaqout
 *
 */
public class CreateObjects {
	private Scanner in;

	/**
	 * constructor of CreateObjects Class
	 * 
	 
	 */
	public CreateObjects(Scanner in) {
		this.in = in;
	}

	/**
	 * stores #Sector objects in ArrayList Note: the first object in this ArrayList
	 * is null (to keep the id=index)
	 */
	private static ArrayList<Sector> sectors = new ArrayList<Sector>();
	/**
	 * stores #Crewman objects in ArrayList Note: the first object in this ArrayList
	 * is null (to keep the id=index)
	 */
	private static ArrayList<Crewman> crewmen = new ArrayList<Crewman>();
	/**
	 * stores #Warship objects in ArrayList Note: the first object in this ArrayList
	 * is null (to keep the id=index)
	 */
	private static ArrayList<Warship> warships = new ArrayList<Warship>();

	/**
	 * Creates Sector objects and stores the object in {@link #sectors}
	 */
	private void createSector() {
		int S = Integer.valueOf(in.nextLine().split(" ")[0]);
		// the first object in {@link #sectors} ArrayList is null (to keep the id=index)
		sectors.add(null);
		for (int i = 1; i < S + 1; i++) {
			if (in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				String sectorName = line[0];
				String affiliation = line[1];

				if (affiliation.equals("REPUBLIC")) {
					// create object
					Sector s = new Sector(i, sectorName, Affiliation.REPUBLIC);
					sectors.add(s);
				} else if (affiliation.equals("SEPARATISTS")) {
					// create object
					Sector s = new Sector(i, sectorName, Affiliation.SEPARATISTS);
					sectors.add(s);
				}

			}
		}
	}

	/**
	 * Creates Crewman objects and stores the object in {@link #crewmen}
	 */
	private void createCrewman() {
		int C = Integer.valueOf(in.nextLine().split(" ")[0]);
		// the first object in {@link #crewmen} ArrayList is null (to keep the id=index)
		crewmen.add(null);
		for (int i = 1; i < C + 1; i++) {

			if (in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				String type = line[0];
				// Officer type
				if (type.equals("Officer")) {
					String name = line[1];
					String intrinsic = line[2];
					int intrinsicLevel = Integer.valueOf(line[3]);
					Officer o = null;
					// create objects
					if (intrinsic.equals("PILOTING")) {
						o = new Officer(i, name, Intrinsic.PILOTING, intrinsicLevel);

					} else if (intrinsic.equals("COMMAND")) {
						o = new Officer(i, name, Intrinsic.COMMAND, intrinsicLevel);

					} else if (intrinsic.equals("ENGINEERING")) {
						o = new Officer(i, name, Intrinsic.ENGINEERING, intrinsicLevel);

					} else if (intrinsic.equals("TACTICAL")) {
						o = new Officer(i, name, Intrinsic.TACTICAL, intrinsicLevel);

					} else if (intrinsic.equals("GUNNERY")) {
						o = new Officer(i, name, Intrinsic.GUNNERY, intrinsicLevel);

					}
					crewmen.add(o);

				}
				// Jedi type
				else if (type.equals("Jedi")) {
					String name = line[1];
					int experience = Integer.valueOf(line[2]);
					int midichlorian = Integer.valueOf(line[3]);
					int intelligence = Integer.valueOf(line[4]);
					// create object
					Jedi j = new Jedi(i, name, experience, midichlorian, intelligence);
					crewmen.add(j);

				}
				// Sith type
				else if (type.equals("Sith")) {
					String name = line[1];
					int experience = Integer.valueOf(line[2]);
					int midichlorian = Integer.valueOf(line[3]);
					int persuasion = Integer.valueOf(line[4]);
					Sith si = new Sith(i, name, experience, midichlorian, persuasion);
					crewmen.add(si);

				}
			}
		}
	}

	/**
	 * Creates Warship objects and stores the object in {@link #warships}
	 */
	private void createWarship() {
		int W = Integer.valueOf(in.nextLine().split(" ")[0]);
		// the first object in {@link #warships} ArrayList is null (to keep the
		// id=index)
		warships.add(null);
		String className = null;
		String name = null;
		int currentSectorId = 0;
		int coordinate = 0;

		for (int i = 1; i < W + 1; i++) {

			if (in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				className = line[0];
				name = line[1];
				currentSectorId = Integer.valueOf(line[2]);
				coordinate = Integer.valueOf(line[3]);
			}
			// ArrayList of Crewman to store crew memebers of this warship
			ArrayList<Crewman> crew = new ArrayList<Crewman>();
			if (in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				int crewSize = Integer.valueOf(line[0]);
				for (int cr = 0; cr < crewSize; cr++) {
					int crewmanId = Integer.valueOf(line[cr + 1]);
					crew.add(crewmen.get(crewmanId));
					// changes status of crewman to ONBOARD
					crewmen.get(crewmanId).setState(StateCrewman.ONBOARD);
					crewmen.get(crewmanId).setCurrentWarshipId(i);
				}
			}
			// creates objects
			if (className.equals("RepublicCruiser")) {
				RepublicCruiser r = new RepublicCruiser(i, name, sectors.get(currentSectorId), coordinate, crew);
				warships.add(r);
			} else if (className.equals("SeparatistBattleship")) {
				SeparatistBattleship sb = new SeparatistBattleship(i, name, sectors.get(currentSectorId), coordinate,
						crew);
				warships.add(sb);
			} else if (className.equals("SeparatistFrigate")) {
				SeparatistFrigate sf = new SeparatistFrigate(i, name, sectors.get(currentSectorId), coordinate, crew);
				warships.add(sf);
			} else if (className.equals("SeparatistDestroyer")) {
				SeparatistDestroyer sd = new SeparatistDestroyer(i, name, sectors.get(currentSectorId), coordinate,
						crew);
				warships.add(sd);
			}
		}
	}

	/**
	 * calls methods suitable for the given operation number in input
	 */
	private void createEvents() {
		int E = Integer.valueOf(in.nextLine().split(" ")[0]);
		for (int i = 1; i < E + 1; i++) {

			if (in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				int operation = Integer.valueOf(line[0]);
				if (operation == 10) {
					int attackerWarshipId = Integer.valueOf(line[1]);
					int defenderWarshipId = Integer.valueOf(line[2]);
					// makes sure the warships are INTACT before the attack mathod is called
					if (warships.get(defenderWarshipId).getState().equals(StateWarship.INTACT))
						if (warships.get(attackerWarshipId).getState().equals(StateWarship.INTACT))
							Event.attack(attackerWarshipId, defenderWarshipId);
				} else if (operation == 11) {
					int sectorId = Integer.valueOf(line[1]);
					sectors.get(sectorId).assault();
				} else if (operation == 20) {
					int warshipId = Integer.valueOf(line[1]);
					int sectorId = Integer.valueOf(line[2]);
					int coordinate = Integer.valueOf(line[3]);
					// makes sure the given sector id exist
					if (sectors.size() > sectorId) {
						Sector s = sectors.get(sectorId);
						Warship w = warships.get(warshipId);
						w.jumpToSector(s, coordinate);
					}
				} else if (operation == 30) {
					int cruiserId = Integer.valueOf(line[1]);
					// the only warships that can call visitCoruscant are INTACT RepublicCruiser
					if (warships.get(cruiserId) instanceof RepublicCruiser) {
						RepublicCruiser rc = (RepublicCruiser) warships.get(cruiserId);
						if (rc.getState().equals(StateWarship.INTACT))
							rc.visitCoruscant();
					}

				} else if (operation == 40) {
					int crewmanId = Integer.valueOf(line[1]);
					int warshipId = Integer.valueOf(line[2]);
					// add FREE crewman to an INTACT warship
					if (warships.get(warshipId).getState().equals(StateWarship.INTACT))
						if (crewmen.get(crewmanId).getState().equals(StateCrewman.FREE))
//					System.out.println("########################");		
					warships.get(warshipId).addCrewman(crewmen.get(crewmanId));
				} else if (operation == 41) {
					int crewmanId = Integer.valueOf(line[1]);
					int warshipId = Integer.valueOf(line[2]);
					// remove crewman from an INTACT warship
					if (warships.get(warshipId).getState().equals(StateWarship.INTACT))
						if(warships.get(warshipId).getCrew().size()>1)
						warships.get(warshipId).removeCrewman(crewmen.get(crewmanId));
				} else if (operation == 50) {
					int officerId = Integer.valueOf(line[1]);
					if (crewmen.get(officerId) instanceof Officer) {
						Officer f = (Officer) crewmen.get(officerId);
						// train alive Officer
						if (f.getState().equals(StateCrewman.KILLED) == false)
							f.train();
					}
				} else if (operation == 51) {
					int warshipId = Integer.valueOf(line[1]);
					String type = line[2];
					int amount = Integer.valueOf(line[3]);
					// upgrade INTACT warship
					if (warships.get(warshipId).getState().equals(StateWarship.INTACT))
						Event.upgradeWarship(warshipId, type, amount);
				}
			}
		}
	}

	/**
	 * calls
	 * {@link #createSector()}
	 * ,{@link #createCrewman()}
	 * ,{@link #createWarship()}
	 * ,{@link #createEvents}
	 */
	public void createObjectsHere() {
		this.createSector();
		this.createCrewman();
		this.createWarship();
		this.createEvents();

	}

	/**
	 * getter
	 * 
	 * @return {@link #sectors}
	 */
	public static ArrayList<Sector> getSectors() {
		return sectors;
	}

	/**
	 * setter method
	 * 
	
	 */
	public static void setSectors(ArrayList<Sector> sectors) {
		CreateObjects.sectors = sectors;
	}

	/**
	 * getter
	 * 
	 * @return {@link #crewmen}
	 */
	public static ArrayList<Crewman> getCrewmen() {
		return crewmen;
	}

	/**
	 * setter
	 * 
	 * @param crewmen
	 */
	public static void setCrewmen(ArrayList<Crewman> crewmen) {
		CreateObjects.crewmen = crewmen;
	}

	/**
	 * getter
	 * 
	 * @return {@link #warships}
	 */
	public static ArrayList<Warship> getWarships() {
		return warships;
	}

	/**
	 * setter
	 * 

	 */
	public static void setWarships(ArrayList<Warship> warships) {
		CreateObjects.warships = warships;
	}
}
