import java.awt.*;

public class Ant extends Critter {

	private boolean walkSouth;

	private boolean diagToggle = false;

	public Ant(boolean walkSouth) {
		this.walkSouth = walkSouth;
	}

	@Override
	public boolean eat() {
		return true;
	}

	@Override
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public Direction getMove() {
		diagToggle = !diagToggle;
		return diagToggle ? Direction.EAST : walkSouth ? Direction.SOUTH : Direction.NORTH;
	}

	@Override
	public String toString() {
		return "%";
	}

}
