package maze.client.model;

import java.util.Map;

import maze.common.model.CellConnectionDirection;

public interface Cell {

	String id();

	String name();

	boolean isExit();

	Map<CellConnectionDirection, Cell> neighbors();
}
