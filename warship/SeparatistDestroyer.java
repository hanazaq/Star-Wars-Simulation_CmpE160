/**
 * 
 */
package project.warship;

import java.util.ArrayList;

import project.activeParts.Sector;
import project.crewman.Crewman;
import project.crewman.General;
import project.crewman.Officer;
import project.crewman.Sith;
import project.enums.Affiliation;
import project.enums.StateCrewman;

/**
 * @author pc
 *
 */
public class SeparatistDestroyer extends Warship {
	/*
	 * Necessary fields
	 */
	/**
	 * used for Sith to escape
	 */
	protected int escapePods;

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
	public SeparatistDestroyer(int id, String name, Sector currentSector, int coordinate, ArrayList<Crewman> crew) {
		super(id, name, currentSector, coordinate, crew);
		this.armamentPower = 80;
		this.shieldPower = 60;
		this.crewCapacity = 7;
		this.escapePods = 1;
	}

	/**
	 * adds Sith or Officer to this warship if they add to this warship's
	 * outputpower makes sure there is space on the warship+ crewman is free
	 * 
	 */
	@Override
	public void addCrewman(Crewman crewman) {

		if (crewman.getState().equals(StateCrewman.FREE)) {
			if (this.crew.size() < this.crewCapacity) {
				if (crewman instanceof Sith || crewman instanceof Officer) {

					int prepower = this.getPowerOutput();
					this.crew.add(crewman);
					int postpower = this.getPowerOutput();
					if (postpower <= prepower) {
						this.crew.remove(crewman);
					} else {
						crewman.setState(StateCrewman.ONBOARD);
						crewman.setCurrentWarshipId(this.id);
					}

				}
			}
		}

	}

	/**
	 * @return commander of max combat power and lower id
	 */
	@Override
	public General getCommander() {
		Sith s = null;
		int maxCombatPower = -1;
		for (Crewman c : crew) {
			if (c.getState().equals(StateCrewman.ONBOARD)) {
				if (c instanceof Sith) {
					if (((Sith) c).getCombatPower() > maxCombatPower) {
						s = (Sith) c;
						maxCombatPower = ((Sith) c).getCombatPower();
					} else if (((Sith) c).getCombatPower() == maxCombatPower) {
						if (s.getId() > c.getId())
							s = (Sith) c;
					}
				}
			}
		}
		return s;

	}

	/**
	 * calculates sector buff
	 */
	protected int sectorBuff() {
		if (this.currentSector.getAffiliation().equals(Affiliation.SEPARATISTS)) {
			return 3;
		}
		return 2;
	}

	public int getEscapePods() {
		return escapePods;
	}

	public void setEscapePods(int escapePods) {
		this.escapePods = escapePods;
	}
}
