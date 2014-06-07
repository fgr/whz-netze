package maze.server.model.impl;

import static org.junit.Assert.assertEquals;
import maze.server.model.Maze;

import org.junit.Test;

public class MazeBuilderTest {
	@Test
	public void testMazeBuilder1() {
		Maze m = MazeBuilder.INSTANCE//
				.firstRow()//
				.cell("A").cell("B")//
				//
				.nextRow()//
				.cell("C").cell("D")//
				//
				.nextRow()//
				.cell("E").cell("F")//
				//
				.connectCells()//
				.connect("A", "B")//
				.connect("A", "C")//
				.connect("C", "E")//
				.connect("B", "D")//
				.connect("D", "C")//
				.connect("E", "F")//
				//
				.entry("A")//
				.exit("F")//
				//
				.buildMaze();

		assertEquals("A", m.currentCell().id());
		assertEquals("F", m.exitCell().id());

		assertEquals("B", m.currentCell().eastwardNeighbor().id());
		assertEquals("C", m.currentCell().southwardNeighbor().id());
	}
}
