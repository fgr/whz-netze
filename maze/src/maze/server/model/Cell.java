package maze.server.model;

import java.util.Map;

import maze.common.model.CellConnectionDirection;

public interface Cell {
	String id();

	String name();

	Cell eastwardNeighbor();

	void eastwardNeighbor(Cell c);

	Cell westwardNeighbor();

	void westwardNeighbor(Cell c);

	Cell northwardNeighbor();

	void northwardNeighbor(Cell c);

	Cell southwardNeighbor();

	void southwardNeighbor(Cell c);
	
	Map<CellConnectionDirection, Cell> neighbors();
}
