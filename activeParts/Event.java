/**
 * 
 */
package project.activeParts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import project.comparators.GeneralsCombatPowerComparator;
import project.crewman.Crewman;
import project.crewman.General;
import project.crewman.Jedi;
import project.crewman.Officer;
import project.crewman.Sith;
import project.enums.StateCrewman;
import project.enums.StateWarship;
import project.warship.RepublicCruiser;
import project.warship.SeparatistDestroyer;
import project.warship.Warship;

/**
 * some events are implemented here
 * 
 * @author Hanaa Zaqout
 *
 */

public class Event {
	/**
	 * used in {@link #attack(int, int)} to transfer power whenever a crewman is
	 * killed
	 * 
	 * @param killer
	 * @param victim
	 */
	private static void powerTransfer(Crewman killer, Crewman victim) {
		if (killer instanceof General) {
			General killer2 = (General) killer;
			// transfer from general to general
			if (victim instanceof General) {
				General victim2 = (General) victim;
				killer2.setExperience(killer2.getExperience() + victim2.getExperience());
				victim2.setExperience(0);
			}
			// transfer from officer to general
			else if (victim instanceof Officer) {
				Officer victim2 = (Officer) victim;
				killer2.setExperience(killer2.getExperience() + victim2.getIntrinsicLevel());
				victim2.setIntrinsicLevel(0);
			}
		}
		

	}

	/**
	 * updates state of the killed crewman and specify his killer + transfer power
	 * 
	 * @param killer
	 * @param victim
	 */
	private static void kill(Crewman killer, Crewman victim) {
		victim.setState(StateCrewman.KILLED);
		victim.setKiller(killer);
		powerTransfer(killer, victim);
	}

	/**
	 * destroyes warship
	 * 
	 * @param killer
	 * @param victim
	 */
	private static void destroy(Warship killer, Warship victim) {
		victim.setState(StateWarship.DESTROYED);
		victim.setDestroyer(killer);
	}

	/**
	 * sets crewman state to free
	 * 
	 * @param c
	 */
	private static void setFree(Crewman c) {
		c.setState(StateCrewman.FREE);
		c.setCurrentWarshipId(-1);
	}

	/**
	 * republic warship wins
	 * 
	 * @param attacker
	 * @param defender
	 * @param attackerWarshipId
	 * @param jCommander
	 * @param sCommander
	 */
	private static void republicWin(Warship attacker, Warship defender, int attackerWarshipId, Jedi jCommander,
			Sith sCommander) {

		RepublicCruiser attacker2 = (RepublicCruiser) attacker;
		SeparatistDestroyer defender2 = (SeparatistDestroyer) defender;
		ArrayList<Sith> sithAimEscape = new ArrayList<Sith>();
		ArrayList<Crewman> cap = attacker2.getCaptives();
		for (Crewman c : defender.getCrew()) {
			// Officers are arrested
			if (c instanceof Officer) {
				c.setCurrentWarshipId(attackerWarshipId);
				// take to captives
				cap.add(c);
			}
			// Sith with lower or equal combatPower will be killed
			else if (c instanceof Sith) {
				Sith c2 = (Sith) c;
				// kill sith with low combatPower
				if (jCommander.getCombatPower() >= c2.getCombatPower()) {
					kill(jCommander, c2);
				} else {
					// they may escape if there are enough escape pods
					sithAimEscape.add(c2);
				}

			}
		}
		attacker2.setCaptives(cap);
		if (sithAimEscape.size() > defender2.getEscapePods()) {
			Comparator<General> compare = new GeneralsCombatPowerComparator<General>();
			Collections.sort(sithAimEscape, compare);

			// free enough for escapepods
			for (int i = 0; i < defender2.getEscapePods(); i++) {
				setFree(sithAimEscape.get(i));
			}
			// others are killed
			for (int i = defender2.getEscapePods(); i < sithAimEscape.size(); i++) {
				kill(jCommander, sithAimEscape.get(i));
			}

		} else {
			// all of them will be free
			for (int i = 0; i < sithAimEscape.size(); i++) {
				setFree(sithAimEscape.get(i));
			}
		}
		destroy(attacker2, defender2);

	}

	/**
	 * separatis warship wins
	 * 
	 * @param attacker
	 * @param defender
	 * @param jCommander
	 * @param sCommander
	 */
	private static void separatistWin(Warship attacker, Warship defender, Jedi jCommander, Sith sCommander) {
		SeparatistDestroyer attacker2 = (SeparatistDestroyer) attacker;
		RepublicCruiser defender2 = (RepublicCruiser) defender;
		for (Crewman c : defender2.getCrew()) {
			kill(sCommander, c);

		}
		for (Crewman c : defender2.getCaptives()) {
			kill(sCommander, c);

		}
		destroy(attacker2, defender2);
	}

	/**
	 * betrayal case
	 * 
	 * @param jCommander
	 * @param sCommander
	 * @param r
	 * @param s
	 */
	private static void betrayal(Jedi jCommander, Sith sCommander, RepublicCruiser r, SeparatistDestroyer s) {
//		System.out.println("say hiiii " + r.getName());
		// jCommander kills all crew members
		for (Crewman c : r.getCrew()) {

			// jCommander does not kill himself
			if (c.equals(jCommander) == false) {
				kill(jCommander, c);
			}
		}
		// jCommander kills all captives
		for (Crewman c : r.getCaptives()) {
			kill(jCommander, c);

		}
		// warship is destroyer
		destroy(s, r);
		// jCommander killed by sCommander
		kill(sCommander, jCommander);
	}

	/**
	 * does attack considering many scenarios
	 * <li>betrayal of Jedi</li>
	 * <li>attacker wins: A_ it can be a RepublicCruiser B_ SeparatistDestroyer</li>
	 * <li>defender wins: A_ it can be a RepublicCruiser B_ SeparatistDestroyer</li>
	 * 
	 * @param attackerWarshipId
	 * @param defenderWarshipId
	 */
	public static void attack(int attackerWarshipId, int defenderWarshipId) {
		// initiallizing variables
		Warship attacker = CreateObjects.getWarships().get(attackerWarshipId);
		Warship defender = CreateObjects.getWarships().get(defenderWarshipId);
		RepublicCruiser r = null;
		SeparatistDestroyer s = null;
		// checks who is jedi and who is Sith
		if (attacker instanceof RepublicCruiser) {
			r = (RepublicCruiser) attacker;
			s = (SeparatistDestroyer) defender;
			// attacker jumps to defender's location
//			r.jumpToSector(s.getCurrentSector(), s.getCoordinate());
		} else if (attacker instanceof SeparatistDestroyer) {
			s = (SeparatistDestroyer) attacker;
			r = (RepublicCruiser) defender;
			// attacker jumps to defender's location
//			s.jumpToSector(r.getCurrentSector(), r.getCoordinate());
		}
		// find commanders of each warship
		Sith sCommander = ((Sith) s.getCommander());
		Jedi jCommander = ((Jedi) r.getCommander());
		if(jCommander!=null || sCommander!=null)
		{	attacker.jumpToSector(defender.getCurrentSector(), defender.getCoordinate());}
		if(jCommander!=null && sCommander!=null) 
		{	
//			attacker.jumpToSector(defender.getCurrentSector(), defender.getCoordinate());
			int changeInSanity = sCommander.getPersuasion() - jCommander.getIntelligence();
//		System.out.println("fireeee between "+ r.getName() + " " +jCommander.getState()+" and "+s.getName()+" "+sCommander.getState() );
		////////////////////////////////////
		// After persuasion
		if (changeInSanity > 0) {
			// make sure not go to negative values
			if (jCommander.getSanity() - changeInSanity <= 0) {
				// sets jCommander's sanity to 0
				jCommander.setSanity(0);
			} else {
				// declines in jCommander's sanity
				jCommander.setSanity(jCommander.getSanity() - changeInSanity);
			}
		}
		// betrayal of Jedi
		if (jCommander.getSanity() == 0) {
		
			betrayal(jCommander, sCommander, r, s);

		}
		// consider other cases
		else {
		
			if (attacker.getPowerOutput() > defender.getPowerOutput()) {
				// case 1
				// attacker RepublicCruiser wins
				if (attacker instanceof RepublicCruiser) {

					republicWin(attacker, defender, attackerWarshipId, jCommander, sCommander);
				}
				// case 2
				else if (attacker instanceof SeparatistDestroyer) {
					separatistWin(attacker, defender, jCommander, sCommander);
				}
			} else {
				// same code just set attacker to defender and defender to attacker
				// defender will win
				// case 1

				if (defender instanceof RepublicCruiser) {
					republicWin(defender, attacker, attackerWarshipId, jCommander, sCommander);
				}
				// case 2
				else if (defender instanceof SeparatistDestroyer) {
					separatistWin(defender, attacker, jCommander, sCommander);
				}

			}
		}}
		else if(jCommander==null && sCommander!=null) {
//			for (Crewman c : r.getCrew()) {
//				kill(sCommander, c);
//
//			}
//			for (Crewman c : r.getCaptives()) {
//				kill(sCommander, c);
//
//			}
			destroy(s, r);
		}
		else if(sCommander==null && jCommander!=null) {
	
//			ArrayList<Crewman> cap = r.getCaptives();
//			for (Crewman c : s.getCrew()) {
//				// Officers are arrested
//				if (c instanceof Officer) {
//					c.setCurrentWarshipId(attackerWarshipId);
//					// take to captives
//					cap.add(c);
//				}
//			
//
//				}
//			}
	
			destroy(r, s);
		}
		
	}

	/**
	 * Increment the proper property of that warship according to the given amount.
	 * 
	 * @param warshipId
	 * @param type
	 * @param amount
	 */
	public static void upgradeWarship(int warshipId, String type, int amount) {
		if (type.equals("Armament")) {
			int newAmout = CreateObjects.getWarships().get(warshipId).getArmamentPower() + amount;
			CreateObjects.getWarships().get(warshipId).setArmamentPower(newAmout);
		}
		if (type.equals("Shield")) {
			int newAmout = CreateObjects.getWarships().get(warshipId).getShieldPower() + amount;
			CreateObjects.getWarships().get(warshipId).setShieldPower(newAmout);
		}

	}

}
