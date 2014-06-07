package maze.server.model.impl;

import java.util.HashMap;
import java.util.Map;

import maze.common.model.CellConnectionDirection;
import maze.common.utils.AssertUtil;
import maze.server.model.Cell;

public class CellImpl implements Cell {
	private String id;
	private String name;
	private Cell eastwardCell;
	private Cell southwardCell;
	private Cell westwardCell;
	private Cell northwardCell;

	public CellImpl(String id) {
		this(id, null);
	}

	public CellImpl(String id, String name) {
		AssertUtil.assertNotEmptyString(id);
		this.id = id;
		this.name = name;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Cell eastwardNeighbor() {
		return eastwardCell;
	}

	@Override
	public void eastwardNeighbor(Cell c) {
		eastwardCell = c;
	}

	@Override
	public Cell westwardNeighbor() {
		return westwardCell;
	}

	@Override
	public void westwardNeighbor(Cell c) {
		this.westwardCell = c;
	}

	@Override
	public Cell northwardNeighbor() {
		return northwardCell;
	}

	@Override
	public void northwardNeighbor(Cell c) {
		northwardCell = c;
	}

	@Override
	public Cell southwardNeighbor() {
		return southwardCell;
	}

	@Override
	public void southwardNeighbor(Cell c) {
		southwardCell = c;
	}

	@Override
	public Map<CellConnectionDirection, Cell> neighbors() {
		Map<CellConnectionDirection, Cell> connections = new HashMap<>();

		if (northwardCell != null)
			connections.put(CellConnectionDirection.NORTH, northwardCell);
		if (southwardCell != null)
			connections.put(CellConnectionDirection.SOUTH, southwardCell);
		if (eastwardCell != null)
			connections.put(CellConnectionDirection.EAST, eastwardCell);
		if (westwardCell != null)
			connections.put(CellConnectionDirection.WEST, westwardCell);

		return connections;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CellImpl {")//
				.append(" id=").append(id)//
				.append(", name=").append(name)//
				.append(", nothwardCell=").append(northwardCell)//
				.append(", southwardCell=").append(southwardCell)//
				.append(", nothwardCell=").append(northwardCell)//
				.append(", eastwardCell=").append(eastwardCell)//
				.append(", westwardCell=").append(westwardCell)//
				.append("}");
		return sb.toString();
	}
}
