package UnknownUser.Scripts.Pkhonorwoodcutter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;


@ScriptManifest(
author = "Unknown User", 
category = Category.WOODCUTTING, 
description = "Cuts mahogany  empties or drops depending on level", 
name = "UMahogony", 
servers = { "Pkhonor" }, 
version = 1.0)

public class Main extends Script implements Paintable {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	public long startTime;
	public static int bank = 0;
	public static int emptied = 0;

	public boolean onExecute() {

		startTime = System.currentTimeMillis();

		strategies.add(new Anti());
		strategies.add(new Cut());
		strategies.add(new Bank());
		provide(strategies);

		return true;
	}

	public int getHourlyRate(int variable) {
		return (int) (((double) (variable - 0) * 3600000D) / (double) (System
				.currentTimeMillis() - startTime));
	}

	private final Color color1 = new Color(70, 152, 239, 63);
	private final Color color2 = new Color(255, 255, 255);
	private final Color color3 = new Color(0, 0, 0);

	private final Font font1 = new Font("Arial", 1, 15);
	private final Font font2 = new Font("Arial", 1, 11);
	private final Font font3 = new Font("Arial", 1, 14);

	public void paint(Graphics C) {

		Graphics2D g = (Graphics2D) C;
		C.setColor(color1);
		C.fillRect(3, 4, 272, 95);
		C.setFont(font1);
		C.setColor(color2);
		C.drawString("UMahogany", 6, 20);
		C.drawString("Bank Trips - ", 5, 77);
		C.drawString("Times Emptied  - ", 4, 58);
		C.setFont(font2);
		C.setColor(color3);
		C.drawString("By - Unknown User", 6, 93);
		C.setFont(font3);
		C.setColor(color2);
		C.drawString("Run Time -", 4, 37);

		C.setColor(Color.yellow);
		C.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

		C.drawString(runTime(startTime), 90, 37);
		C.drawString("" + Main.emptied, 130, 58);
		C.drawString("" + Main.bank, 100, 77);

	}

	public static String runTime(long i) {

		DecimalFormat nf = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - i;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;

		return nf.format(hours) + ":" + nf.format(minutes) + ":"
				+ nf.format(seconds);
	}
}