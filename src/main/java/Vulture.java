import java.awt.*;

public class Vulture extends Bird {

	private boolean hungry;

	@Override
	public boolean eat() {
		if (hungry) {
			hungry = false;
			return true;
		}
		return false;
	}

	@Override
	public Attack fight(String opponent) {
		hungry = true;
		return super.fight(opponent);
	}

	@Override
	public Color getColor() {
		return Color.BLACK;
	}

}
