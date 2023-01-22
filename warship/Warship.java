/**
 * 
 */
package project.warship;

import java.util.ArrayList;

import project.activeParts.Sector;
import project.crewman.Crewman;
import project.crewman.General;
import project.crewman.Officer;
import project.enums.Intrinsic;
import project.enums.StateCrewman;
import project.enums.StateWarship;
import project.interfaces.IWarship;

/**
 * @author pc
 *
 */
public abstract class Warship implements IWarship {
	/*
	 * Necessary fields
	 */

	protected int id;
	protected String name;
	protected Sector currentSector;
	protected int coordinate;
	protected int armamentPower;
	protected int shieldPower;
	protected int crewCapacity;
	protected ArrayList<Crewman> crew;
	protected StateWarship state;
	protected Warship destroyer;

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
	public Warship(int id, String name, Sector currentSector, int coordinate, ArrayList<Crewman> crew) {
		this.id = id;
		this.name = name;
		this.currentSector = currentSector;
		this.coordinate = coordinate;
		this.crew = crew;
		this.state = StateWarship.INTACT;
	}

	/**
	 * removes crewman from this warship and set him free
	 */
	public void removeCrewman(Crewman crewman) {
		if (this.crew.contains(crewman)) {
			this.crew.remove(crewman);
			crewman.setState(StateCrewman.FREE);
			crewman.setCurrentWarshipId(-1);
		}

	}

	/**
	 * this warship will move to given sector and coordinate.
	 */
	@Override
	public void jumpToSector(Sector sector, int coordinate) {
//		System.out.println("jumped to "+ sector.getId() +" "+coordinate );
		this.currentSector = sector;
		this.coordinate = coordinate;

	}

	@Override
	public int getPowerOutput() {

		int power = this.sectorBuff()
				* (this.armamentPower + this.shieldPower + this.generalContribution() + this.officerContribution());

		return power;
	}

	/**
	 * 
	 * @return If a warship and a sector are affiliated to the same organization,
	 *         Sector Buff is 3. If they aren’t, Sector Buff is 2.
	 */
	protected abstract int sectorBuff();

	/**
	 * store crewman of type Officer
	 */

	protected ArrayList<Officer> officers = new ArrayList<Officer>();

	/**
	 * 
	 * @return generalContribution
	 */
	private int generalContribution() {
		int generalContribution = 0;
		for (Crewman c : this.crew) {
			if (c instanceof Officer) {
				this.officers.add((Officer) c);
			} else if (c instanceof General) {
				generalContribution += ((General) c).getCombatPower();
			}
		}
		return generalContribution;
	}

	/**
	 * 
	 * @return OfficerContribution
	 */
	private int officerContribution() {
		int tactical = 0, piloting = 0, gunnery = 0, engineering = 0, command = 0;
		for (Officer f : officers) {
			if (f.getIntrinsic().equals(Intrinsic.TACTICAL)) {
				if (f.getIntrinsicLevel() > tactical) {
					tactical = f.getIntrinsicLevel();
				}
			}
			if (f.getIntrinsic().equals(Intrinsic.PILOTING)) {
				if (f.getIntrinsicLevel() > piloting) {
					piloting = f.getIntrinsicLevel();
				}
			}
			if (f.getIntrinsic().equals(Intrinsic.GUNNERY)) {
				if (f.getIntrinsicLevel() > gunnery) {
					gunnery = f.getIntrinsicLevel();
				}
			}
			if (f.getIntrinsic().equals(Intrinsic.ENGINEERING)) {
				if (f.getIntrinsicLevel() > engineering) {
					engineering = f.getIntrinsicLevel();
				}
			}
			if (f.getIntrinsic().equals(Intrinsic.COMMAND)) {
				if (f.getIntrinsicLevel() > command) {
					command = f.getIntrinsicLevel();
				}
			}
		}
		int OfficerContribution = (tactical + 1) * (piloting + 1) * (gunnery + 1) * (engineering + 1) * (command + 1);
		return OfficerContribution;
	}

	/**
	 * getters and setters
	 */
	@Override
	public void upgradeArmament(int amount) {
		this.armamentPower += amount;

	}

	@Override
	public void upgradeShield(int amount) {
		this.shieldPower += amount;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sector getCurrentSector() {
		return currentSector;
	}

	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
	}

	public int getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(int coordinate) {
		this.coordinate = coordinate;
	}

	public int getArmamentPower() {
		return armamentPower;
	}

	public void setArmamentPower(int armamentPower) {
		this.armamentPower = armamentPower;
	}

	public int getShieldPower() {
		return shieldPower;
	}

	public void setShieldPower(int shieldPower) {
		this.shieldPower = shieldPower;
	}

	public int getCrewCapacity() {
		return crewCapacity;
	}

	public void setCrewCapacity(int crewCapacity) {
		this.crewCapacity = crewCapacity;
	}

	public ArrayList<Crewman> getCrew() {
		return crew;
	}

	public void setCrew(ArrayList<Crewman> crew) {
		this.crew = crew;
	}

	public ArrayList<Officer> getOfficers() {
		return officers;
	}

	public void setOfficers(ArrayList<Officer> officers) {
		this.officers = officers;
	}

	public project.enums.StateWarship getState() {
		return state;
	}

	public void setState(project.enums.StateWarship state) {
		this.state = state;
	}

	public Warship getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(Warship destroyer) {
		this.destroyer = destroyer;
	}
}
