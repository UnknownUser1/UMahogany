package UnknownUser.Scripts.Pkhonorwoodcutter;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Cut implements Strategy {
	// To add different trees
	int TREE = (9034);

	@Override
	public boolean activate() {

		return !Inventory.isFull();
	}

	@Override
	public void execute() {

		SceneObject[] SELECTED_TREE = SceneObjects.getNearest(TREE);
		if (SELECTED_TREE != null && SELECTED_TREE.length > 0) {
			SELECTED_TREE[0].interact(0);
			Time.sleep(5000);

		} else {
			if (Loader.getClient().getBackDialogId() == 4272) {
				Menu.sendAction(679, 148045824, 55, 4275);
				Time.sleep(500);
			}
		}

	}

}
