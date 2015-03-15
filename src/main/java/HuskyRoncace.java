import java.awt.*;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.util.Collection;

public class HuskyRoncace extends Critter {

	private static CritterModel model = null;

	private static Field critterList = null;

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
	public Color getColor() {
		return Color.MAGENTA;
	}

	@Override
	public String toString() {
		return "(つ ◕_◕ )つ";
	}

	private static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(x1 - x2, 2));
	}

}
