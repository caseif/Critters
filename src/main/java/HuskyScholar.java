import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuskyScholar extends Critter {

	private String currentOpponent;
	private Attack currentAttack;
	private boolean hasEaten = false;
	private int iteration = 5;
	private Direction dir = Direction.CENTER;

	private static final HashMap<String, Integer> pounceVictories = new HashMap<>();
	private static final HashMap<String, Integer> roarVictories = new HashMap<>();
	private static final HashMap<String, Integer> scratchVictories = new HashMap<>();
	private static final HashMap<String, Integer> pounceLosses = new HashMap<>();
	private static final HashMap<String, Integer> roarLosses = new HashMap<>();
	private static final HashMap<String, Integer> scratchLosses = new HashMap<>();

	private static boolean isSpeciescideComplete = false;

	private static final String[] disguises = {
			"%",
			"V",
			"S",
			"0"
	};

	@Override
	public String toString() {
		return disguises[(int)(Math.random() * disguises.length)];
	}

	@Override
	public boolean eat() {
		return isSpeciescideComplete || (!hasEaten && (hasEaten = true));
	}

	@Override
	public Attack fight(String opponent) {
		currentOpponent = opponent;
		int vPounce = pounceVictories.containsKey(opponent) ? pounceVictories.get(opponent) : 0;
		int vRoar = roarVictories.containsKey(opponent) ? roarVictories.get(opponent) : 0;
		int vScratch = scratchVictories.containsKey(opponent) ? scratchVictories.get(opponent) : 0;
		int lPounce = pounceLosses.containsKey(opponent) ? pounceLosses.get(opponent) : 0;
		int lRoar = roarLosses.containsKey(opponent) ? roarLosses.get(opponent) : 0;
		int lScratch = scratchLosses.containsKey(opponent) ? scratchLosses.get(opponent) : 0;
		List<Attack> candidates = new ArrayList<>();
		if (lPounce == 0) {
			candidates.add(Attack.POUNCE);
		}
		if (lRoar == 0) {
			candidates.add(Attack.ROAR);
		}
		if (lScratch == 0) {
			candidates.add(Attack.SCRATCH);
		}
		if (candidates.size() > 0) {
			return currentAttack = candidates.get((int)(Math.random() * candidates.size()));
		}
		/*System.out.println((float)vRoar / (float)lRoar + ", " +
				(float)vScratch / (float)lScratch + ", " +
				(float)vPounce / (float)lPounce + " - " +
				vRoar + "/" + lRoar + ", " +
				vScratch + "/" + lScratch + ", " +
				vPounce + "/" + lPounce + " - " +
				candidates.get(0) +
				(candidates.size() > 1 ? ", " + candidates.get(1) +
						(candidates.size() > 2 ? ", " + candidates.get(2)
								: "") : ""));*/
		if (vPounce / (float)lPounce > vRoar / (float)lRoar) {
			if (lPounce / (float)lPounce > vScratch / (float)lScratch) {
				return currentAttack = Attack.POUNCE;
			}
			else {
				return currentAttack = Attack.SCRATCH;
			}
		}
		else {
			if (vRoar / (float)vRoar > vScratch / (float)lScratch) {
				return currentAttack = Attack.ROAR;
			}
			else {
				return currentAttack = Attack.SCRATCH;
			}
		}
		//return currentAttack = candidates.get((int)(Math.random() * candidates.size()));
	}

	@Override
	public void win() {
		HashMap<String, Integer> map;
		switch (currentAttack) {
			case POUNCE:
				map = pounceVictories;
				break;
			case SCRATCH:
				map = scratchVictories;
				break;
			default:
				map = roarVictories;
		}
		map.put(currentOpponent, map.containsKey(currentOpponent) ? map.get(currentOpponent) + 1 : 1);
	}

	@Override
	public void lose() {
		HashMap<String, Integer> map;
		switch (currentAttack) {
			case POUNCE:
				map = pounceLosses;
				break;
			case SCRATCH:
				map = scratchLosses;
				break;
			default:
				map = roarLosses;
				break;
		}
		map.put(currentOpponent, map.containsKey(currentOpponent) ? map.get(currentOpponent) + 1 : 1);
	}

	@Override
	public Direction getMove() {
		// I mean, hey, it works for the hippos
		if (iteration == 5) {
			iteration = 0;
			int r = (int)(Math.random() * 4);
			switch (r) {
				case 0:
					dir = Direction.NORTH;
					break;
				case 1:
					dir = Direction.SOUTH;
					break;
				case 2:
					dir = Direction.EAST;
					break;
				case 3:
					dir = Direction.WEST;
					break;
			}
		}
		++iteration;
		return dir;
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}
}
