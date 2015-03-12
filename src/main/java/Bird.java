import java.awt.*;
import java.util.HashMap;

public class Bird extends Critter {

	private int iteration = 0;
	private Direction dir = Direction.CENTER;

	protected static HashMap<Direction, String> symbols = new HashMap<Direction, String>();

	static {
		symbols.put(Direction.NORTH, "^");
		symbols.put(Direction.SOUTH, "V");
		symbols.put(Direction.WEST, "<");
		symbols.put(Direction.EAST, ">");
		symbols.put(Direction.CENTER, "^");

	}

	@Override
	public boolean eat() {
		return false;
	}

	@Override
	public Attack fight(String opponent) {
		return opponent.equals("%") ? Attack.ROAR : Attack.POUNCE;
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public Direction getMove() {
		if (iteration == 3) {
			iteration = 0;
			switch (dir) {
				case NORTH:
					dir = Direction.EAST;
					break;
				case EAST:
					dir = Direction.SOUTH;
					break;
				case SOUTH:
					dir = Direction.WEST;
					break;
				case WEST:
					dir = Direction.NORTH;
					break;
				default:
					dir = Direction.NORTH;
					break;
			}
		}
		++iteration;
		return dir;
	}

	@Override
	public String toString() {
		return symbols.get(dir);
	}

}
