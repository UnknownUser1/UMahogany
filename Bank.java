package UnknownUser.Scripts.Pkhonorwoodcutter;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {

	@Override
	public boolean activate() {

		return Inventory.isFull();

	}

	@Override
	public void execute() {
		if (Skill.WOODCUTTING.getRealLevel() >= 80) {
			SceneObject[] Bank = SceneObjects.getNearest(9398);
			if (Bank != null && Bank.length > 0) {
				Bank[0].interact(1);
				Time.sleep(5000);
				if (Loader.getClient().getOpenInterfaceId() == 23350) {
					Menu.sendAction(646, 2894, 27, 23412);
					Main.bank += 1;
					Time.sleep(500);
					Menu.sendAction(200, 556, 6, 5384);
					Time.sleep(500);

				}
			}

		}
		if (Skill.WOODCUTTING.getRealLevel() <= 80) {
			Keyboard.getInstance().sendKeys("::empty");
			Main.emptied += 1;
			Time.sleep(2000);
		}

	}
}