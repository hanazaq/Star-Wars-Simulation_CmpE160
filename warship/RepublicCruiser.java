/**
 * 
 */
package project.warship;

import java.util.ArrayList;

import project.activeParts.Sector;
import project.crewman.Crewman;
import project.crewman.General;
import project.crewman.Jedi;
import project.crewman.Officer;
import project.enums.Affiliation;
import project.enums.StateCrewman;

/**
 *
 */
public class RepublicCruiser extends Warship {
	/*
	 * Necessary fields
	 */

	ArrayList<Crewman> captives;

	/*
	 * Necessary methods
	 */
	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param currentSector
	 * @param coordinate
	 * @param crew
	 */
	public RepublicCruiser(int id, String name, Sector currentSector, int coordinate, ArrayList<Crewman> crew) {
		super(id, name, currentSector, coordinate, crew);
		this.armamentPower = 100;
		this.shieldPower = 100;
		this.crewCapacity = 10;
		this.captives = new ArrayList<Crewman>();
	}

	/**
	 * sets sanity to 100 captives will be put in Corousant-Prison so change their
	 * state to imprisoned.
	 */
	public void visitCoruscant() {
//		System.out.println("visitCoruscant "+this.id);
		for (Crewman j : this.crew) {
			if (j instanceof Jedi) {
				Jedi jMember = (Jedi) j;
				jMember.setSanity(100);
			}
		}
		if (this.captives.size() > 0)

			for (Crewman cap : this.captives) {
				cap.setState(StateCrewman.IMPRISONED);
				cap.setCurrentWarshipId(-1);
			}
		this.captives.clear();
	}

	/**
	 * adds Crewman to the crew if the Crewman is not Sith, and free. must be
	 * enought space for it in the warship
	 */
	@Override
	public void addCrewman(Crewman crewman) {

		if (crewman.getState().equals(StateCrewman.FREE)) {
			if (crewman instanceof Jedi || crewman instanceof Officer) {
				if (this.crew.size() < this.crewCapacity) {
//					System.out.println("#######################################################");
					this.crew.add(crewman);
					crewman.setState(StateCrewman.ONBOARD);
					crewman.setCurrentWarshipId(this.id);
				}
			}
		}

	}

	/**
	 * @return commander that has max Experience and lower id
	 */
	@Override
	public General getCommander() {
		Jedi j = null;
		int maxExperience = -1;
		for (Crewman c : crew) {
			if (c.getState().equals(StateCrewman.ONBOARD)) {
				if (c instanceof Jedi) {
					if (((Jedi) c).getExperience() > maxExperience) {
						j = (Jedi) c;
						maxExperience = ((Jedi) c).getExperience();
					} else if (((Jedi) c).getExperience() == maxExperience) {
						if (c.getId() < j.getId())
							j = (Jedi) c;
					}
				}
			}
		}
		return j;
	}

	/**
	 * calculates sector buff
	 */
	protected int sectorBuff() {
		if (this.currentSector.getAffiliation().equals(Affiliation.REPUBLIC)) {
			return 3;
		}
		return 2;
	}

	/**
	 * getters and setters
	 * 
	 * @return
	 */
	public ArrayList<Crewman> getCaptives() {
		return captives;
	}

	public void setCaptives(ArrayList<Crewman> captives) {
		this.captives = captives;
	}

}
