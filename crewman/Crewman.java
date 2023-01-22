/**
 * 
 */
package project.crewman;

import project.enums.StateCrewman;

/**
 * abstract class has fields, constructor, getters and setters.
 *
 */
public abstract class Crewman {
	/*
	 * Necessary fields
	 */
	protected int id;
	protected String name;
	protected StateCrewman state;
	protected Crewman killer;
	protected int currentWarshipId;

	/*
	 * Necessary methods
	 */
	public Crewman(int id, String name) {
		this.id=id;
		this.name=name;
		this.state=StateCrewman.FREE;
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

	public project.enums.StateCrewman getState() {
		return state;
	}
	public void setState(project.enums.StateCrewman state) {
		this.state = state;
	}
	public Crewman getKiller() {
		return killer;
	}
	public void setKiller(Crewman killer) {
		this.killer = killer;
	}

	public int getCurrentWarshipId() {
		return currentWarshipId;
	}
	public void setCurrentWarshipId(int currentWarshipId) {
		this.currentWarshipId = currentWarshipId;
	}
}
