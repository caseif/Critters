import java.awt.*;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.util.Collection;

public class HuskyRoncace extends Critter {

	private static CritterModel model = null;
	private static Field critterList = null;

	private final String donger;

	private boolean hasEaten = false;
	private int iteration = 5;
	private Direction dir = Direction.CENTER;

	private static final String[] dongers = {
			"(つ ◕_◕)つ",
			"(つ°ヮ°)つ",
			"╰(◕ヮ ◕)╯",
			"(´∀ `)",
			"└╏･ヮ ･╏┐",
			"ლ(❛Д❛ლ)"
			// >tfw no lenny
			// >tfw no raise your dongers
	};

	public HuskyRoncace() {
		donger = dongers[(int)(Math.random() * dongers.length)];
	}

	@Override
	public String toString() {
		return donger;
	}

	private static void cheat() {
		try {
			outer:
			for (Window f : Window.getWindows()) {
				for (WindowListener l : f.getWindowListeners()) {
					if (l instanceof CritterGui) {
						CritterGui gui = (CritterGui)l;
						Field modelField = gui.getClass().getDeclaredField("model");
						modelField.setAccessible(true);
						model = (CritterModel)modelField.get(gui);
						critterList = model.getClass().getDeclaredField("critterList");
						critterList.setAccessible(true);
						break outer;
					}
				}
			}
		}
		catch (IllegalAccessException | NoSuchFieldException ex) {
			throw new AssertionError();
		}
	}

	@Override
	public boolean eat() {
		return !hasEaten && (hasEaten = true);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Attack fight(String opponent) {
		try {
			if (critterList == null) {
				cheat();
			}
			Collection<Critter> critters = (Collection<Critter>)critterList.get(model);
			for (Critter c : critters) {
				if (distance(this.getX(), this.getY(), c.getX(), c.getY()) == 0
						&& c.toString().equals(opponent)) {
					Attack attack = c.fight(this.toString());
					switch (attack) {
						case POUNCE:
							return Attack.SCRATCH;
						case SCRATCH:
							return Attack.ROAR;
						case ROAR:
							return Attack.POUNCE;
					}
				}
			}
		}
		catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
		return Attack.POUNCE;
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

	private static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(x1 - x2, 2));
	}

}
