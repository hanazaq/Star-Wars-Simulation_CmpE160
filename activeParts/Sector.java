/**
 * 
 */
package project.activeParts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import project.comparators.WarshipsCoordinateComparator;
import project.enums.Affiliation;
import project.warship.RepublicCruiser;
import project.warship.SeparatistDestroyer;
import project.warship.Warship;

/**
 * @author Hanaa Zaqout
 *
 */
public class Sector {
	/*
	 * Necessary fields
	 */
	private int id;
	private String name;
	private Affiliation affiliation;

	/*
	 * Necessary methods
	 */
	public Sector(int id, String name, Affiliation affiliation) {
		this.id = id;
		this.name = name;
		this.affiliation = affiliation;
	}
	/**
	 * places attacks that satisfies specific conditions
	 * logic followed: order warships in this sector according to their xCoordinate,
	 * create ArrayList willingToAttack warships for each destroyer warship,
	 * place attackes between destroyer and last warship in willingToAttack
	 */
	public void assault() {

		ArrayList<Warship> all = CreateObjects.getWarships();
		ArrayList<Warship> wHere = new ArrayList<Warship>();
		for (int i = 1; i < all.size(); i++) {
			if (all.get(i).getCurrentSector().equals(this)) {
				wHere.add(all.get(i));
			}
		}
		// now order the warships according to there Xcoordinate
		Comparator<Warship> compare = new WarshipsCoordinateComparator<Warship>();
		Collections.sort(wHere, compare);

		// now will have to parallel structure
		ArrayList<Warship> destroyer = new ArrayList<Warship>();
		ArrayList<ArrayList<Warship>> willingToAttack = new ArrayList<ArrayList<Warship>>();
		for (Warship wi : wHere) {
			if (wi instanceof SeparatistDestroyer) {
				destroyer.add(wi);
				willingToAttack.add(new ArrayList<Warship>());
			}
		}
		// iterate with interest about Republic list
		
			for(int y=0; y<wHere.size();y++) {
			if (wHere.get(y) instanceof RepublicCruiser) {
				int target = 0;
				for (int z = y+1; z < wHere.size(); z++) {
					if (wHere.get(z) instanceof SeparatistDestroyer) {
						if (wHere.get(y).getCoordinate() < wHere.get(z).getCoordinate()) {
							if (wHere.get(y).getPowerOutput() > wHere.get(z).getPowerOutput()) {
								target = destroyer.indexOf(wHere.get(z));
								willingToAttack.get(target).add(wHere.get(y));
								break;
							}
							target++;
						}
					}
				}
			}

		}

		// do the attacks
		for (int i = 0; i < destroyer.size(); i++) {
			if (willingToAttack.get(i).size() > 0) {
				int att = willingToAttack.get(i).size() - 1;
//				System.out.println("attacker " + willingToAttack.get(i).get(att).getName() + " defender "
//						+ destroyer.get(i).getName());
				Event.attack(willingToAttack.get(i).get(att).getId(), destroyer.get(i).getId());
			}
		}
	}


/**
 * getters and setters
 * @return
 */
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

	public Affiliation getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

}
