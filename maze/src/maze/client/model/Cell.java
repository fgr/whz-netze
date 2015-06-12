package maze.client.model;

import java.util.Map;

import maze.common.model.CellConnectionDirection;

public interface Cell {
	String id();

	String name();

	boolean isExit();

	/**
	 * Contains the <em>ID</em> of each neighboring cell. Each neighbor is
	 * mapped according to its {@link CellConnectionDirection direction}.
	 */
	Map<CellConnectionDirection, String> neighbors();
}
