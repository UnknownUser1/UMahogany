package UnknownUser.Scripts.Pkhonorwoodcutter;


import java.awt.Polygon;

import org.rev317.min.api.wrappers.Tile;

public class Area {
	private Polygon p;

	/**
	 * Initializes a PolygonArea with the tiles given
	 * 
	 * @param tiles
	 *            tiles to use in the area
	 */
	public Area(Tile... tiles) {
		this.p = new Polygon();
		for (int i = 0; i < tiles.length; i++) {
			p.addPoint(tiles[i].getX(), tiles[i].getY());
		}
	}

	/**
	 * Checks if a tile is in the area
	 * 
	 * @param tile
	 *            The tile to check
	 * @return <b>true</b> if area does contain the tile, otherwise <b>false</b>
	 */
	public boolean contains(Tile tile) {
		return this.contains(tile.getX(), tile.getY());
	}

	public boolean contains(int x, int y) {
		int i;
		int j;
		boolean result = false;
		for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
			if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1)
					&& (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i])
							/ (p.ypoints[j] - p.ypoints[i]) + p.xpoints[i])) {
				result = !result;
			}
		}
		return result;
	}
}