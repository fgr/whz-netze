package maze.server.model.impl;

import maze.common.utils.AssertUtil;
import maze.server.model.Cell;
import maze.server.model.Maze;

public class MazeImpl implements Maze {
	private Cell currentCell;
	private Cell exitCell;

	public MazeImpl(Cell entryCell, Cell exitCell) {
		AssertUtil.assertNotNull(entryCell);
		AssertUtil.assertNotNull(exitCell);
		this.currentCell = entryCell;
		this.exitCell = exitCell;
	}

	@Override
	public Cell currentCell() {
		return currentCell;
	}

	public void currentCell(Cell c) {
		currentCell = c;
	}

	@Override
	public Cell exitCell() {
		return exitCell;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MazeImpl {")//
				.append(" currentCell=").append(currentCell)//
				.append(", finishCell=").append(exitCell)//
				.append("}");
		return sb.toString();
	}
}
