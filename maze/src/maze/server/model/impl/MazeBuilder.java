package maze.server.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maze.common.utils.AssertUtil;
import maze.server.model.Cell;
import maze.server.model.Maze;

public enum MazeBuilder {
	INSTANCE;

	private RowBuilderImpl firstRow;

	public RowBuilderImpl firstRow() {
		if (firstRow == null)
			firstRow = new RowBuilderImpl();
		return firstRow;
	}

	public static final class RowBuilderImpl {
		private List<RowBuilderImpl> rows;
		private List<CellData> cellsInRow = new ArrayList<>();

		private RowBuilderImpl() {
			this(new ArrayList<RowBuilderImpl>());
		}

		private RowBuilderImpl(List<RowBuilderImpl> rowsAbove) {
			rows = new ArrayList<>(rowsAbove);
			rows.add(this);
		}

		public RowBuilderImpl cell(String id, String name) {
			cellsInRow.add(new CellData(id, name));
			return this;
		}

		public RowBuilderImpl cell(String id) {
			cell(id, null);
			return this;
		}

		public RowBuilderImpl nextRow() {
			return new RowBuilderImpl(rows);
		}

		public CellConnectionBuilderImpl connectCells() {
			return new CellConnectionBuilderImpl(layoutCells());
		}

		private List<List<CellData>> layoutCells() {
			List<List<CellData>> maze = new ArrayList<>();
			for (RowBuilderImpl rowAbove : rows)
				maze.add(rowAbove.cellsInRow);

			int currentRow = 0;
			int numOfCellsPerRow = -1;
			for (List<CellData> row : maze) {
				int currentColumn = 0;

				// assign each cell's row and column
				for (CellData cell : row) {
					cell.row = currentRow;
					cell.column = currentColumn;

					++currentColumn;
				}

				{
					// ensure that all rows have the same number of cells
					int currentNumOfCells = row.size();
					if (numOfCellsPerRow == -1)
						numOfCellsPerRow = currentNumOfCells;
					else if (numOfCellsPerRow != currentNumOfCells)
						throw new IllegalStateException("The number of cells differs between rows!");
				}

				++currentRow;
			}

			return maze;
		}
	}

	public static final class CellConnectionBuilderImpl {
		private final Map<String, CellData> cells = new HashMap<>();
		private CellData entryCell;
		private CellData exitCell;

		private CellConnectionBuilderImpl(List<List<CellData>> rows) {
			for (List<CellData> row : rows)
				for (CellData c : row)
					cells.put(c.id, c);
		}

		public CellConnectionBuilderImpl connect(String cellId1, String cellId2) {
			CellData cell1 = getCell(cellId1);
			CellData cell2 = getCell(cellId2);

			if (cell1.row == cell2.row) { // cells belong to same row
				if (cell1.column == cell2.column - 1) {
					// cell1[east] -- [west]cell2
					cell1.east = cell2;
					cell2.west = cell1;
				} else if (cell1.column == cell2.column + 1) {
					// cell2[east] -- [west]cell1
					cell2.east = cell1;
					cell1.west = cell2;
				} else { // cells are not assigned to adjacent columns
					throw new IllegalStateException("Cells are not assigned to adjacent columns: " + cell1 + " and " + cell2);
				}
			} else if (cell1.row == cell2.row + 1) {
				// cell1 belongs to a row one row above cell2's row;
				// cell1[north] -- [south]cell2

				// ensure cells belong to same column
				if (cell1.column != cell2.column)
					throw new IllegalStateException("Cells on adjacent rows must not be assigned to differnt columns: " + cell1 + " and " + cell2);

				cell1.north = cell2;
				cell2.south = cell1;
			} else if (cell1.row == cell2.row - 1) {
				// cell1 belongs to a row one row below cell2's row;
				// cell1[south] -- [north]cell2

				// ensure cells belong to same column
				if (cell1.column != cell2.column)
					throw new IllegalStateException("Cells on adjacent rows must not be assigned to differnt columns: " + cell1 + " and " + cell2);

				cell1.south = cell2;
				cell2.north = cell1;
			} else
				throw new IllegalStateException("Cells are not assigned to adjacent rows: " + cell1 + " and " + cell2);

			return this;
		}

		public CellConnectionBuilderImpl entry(String cellId) {
			entryCell = getCell(cellId);
			return this;
		}

		public CellConnectionBuilderImpl exit(String cellId) {
			exitCell = getCell(cellId);
			return this;
		}

		public Maze buildMaze() {
			AssertUtil.assertNotNull(entryCell, "Start cell was not defined.");
			AssertUtil.assertNotNull(exitCell, "Finish cell was not defined.");

			// create the real Cells from CellData
			Map<String, Cell> theCells = new HashMap<>();
			for (CellData cell : cells.values())
				theCells.put(cell.id, new CellImpl(cell.id, cell.name));

			// connect the real Cells
			for (CellData cell : cells.values()) {
				Cell theCell = theCells.get(cell.id);

				if (cell.north != null)
					theCell.northwardNeighbor(theCells.get(cell.north.id));

				if (cell.south != null)
					theCell.southwardNeighbor(theCells.get(cell.south.id));

				if (cell.west != null)
					theCell.westwardNeighbor(theCells.get(cell.west.id));

				if (cell.east != null)
					theCell.eastwardNeighbor(theCells.get(cell.east.id));
			}

			// create the maze
			Cell theEntryCell = theCells.get(entryCell.id);
			Cell theExitCell = theCells.get(exitCell.id);
			MazeImpl maze = new MazeImpl(theEntryCell, theExitCell);
			return maze;
		}

		private CellData getCell(String cellId) {
			CellData cell = cells.get(cellId);
			if (cell == null)
				throw new IllegalStateException("Could not find cell with ID " + cellId);
			return cell;
		}
	}

	private static final class CellData {
		final String id;
		final String name;
		int row;
		int column;
		CellData east;
		CellData west;
		CellData north;
		CellData south;

		private CellData(String id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("CellData {")//
					.append(" id=").append(id)//
					.append(", name=").append(name)//
					.append(", row=").append(row)//
					.append(", column=").append(column)//
					.append(", east=").append(east)//
					.append(", west=").append(west)//
					.append(", north=").append(north)//
					.append("}");
			return sb.toString();
		}
	}
}
