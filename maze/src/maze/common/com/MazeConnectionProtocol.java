package maze.common.com;

public interface MazeConnectionProtocol {
	enum MazeConnectionRequests {
		ENTER_MAZE, GO_TO_CELL
	}
	
	CellDto enterMaze();

	CellDto goToCell(String cellID);
}
