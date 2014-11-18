package UnknownUser.Scripts.Pkhonorwoodcutter;



import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;

import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Sexy anti random by Minimal
 * http://www.parabot.org/community/user/10775-minimal/
 */
public class Anti implements Strategy {

	// AntiRandom
	private final int[] RANDOMS = { 410, 1091, 3117, 3022, 3351, 409 };
	private final Area BOBS_ISLAND = new Area(new Tile(2511, 4765), new Tile(
			2511, 4790), new Tile(2542, 4790), new Tile(2542, 4765));

	@Override
	public boolean activate() {
		for (Npc n : Npcs.getNearest(RANDOMS)) {
			if (n.getLocation().distanceTo() < 3)
				return true;
		}
		return false;
	}

	
	public void execute() {
		Time.sleep(750);
		Npc[] n = Npcs.getNearest(RANDOMS);
		System.out.println("There is a random nearby!");
		Time.sleep(750);
		if (n[0].getDef().getId() == 1091) {
			SceneObject[] portal = SceneObjects.getNearest(8987);

			for (int i = 0; i < portal.length; i++) {
				if (BOBS_ISLAND.contains(Players.getMyPlayer().getLocation())) {
					final SceneObject portal2 = portal[i];
					portal2.interact(0);
					Time.sleep(new SleepCondition() {
						
						public boolean isValid() {
							return portal2.getLocation().distanceTo() < 2;
						}
					}, 7500);
					portal2.interact(0);
					Time.sleep(1000);
				} else
					break;
			}
			System.out.println("Bob's Island has been completed");
		} else if (n[0].getDef().getId() == 3022
				|| n[0].getDef().getId() == 3351 || n[0].getDef().getId() == 40) {
			System.exit(0);
			System.out.println("A mod called a Genie random onto you.\n"
					+ "The client was closed to protect your account.");
		} else {
			n[0].interact(0);
			Time.sleep(1500);
			System.out
					.println("Sandwich lady/Old man random has been completed");
		}
	}

}