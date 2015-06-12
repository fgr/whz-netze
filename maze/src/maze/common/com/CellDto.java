package maze.common.com;

import java.util.Collections;
import java.util.Map;

import maze.common.model.CellConnectionDirection;
import maze.common.utils.AssertUtil;

public final class CellDto {
	public CellDto(String cellID, String name, boolean isExit, Map<CellConnectionDirection, String> neighbors) {
		AssertUtil.assertNotNull(cellID);
		AssertUtil.assertNotNull(neighbors);
		this.id = cellID;
		this.name = name;
		this.isExit = isExit;
		this.neighbors = Collections.unmodifiableMap(neighbors);
	}

	public final String id;

	public final String name;

	public final boolean isExit;

	/**
	 * Contains the <em>ID</em> of each neighboring cell. Each neighbor is
	 * mapped according to its {@link CellConnectionDirection direction}.
	 */
	public final Map<CellConnectionDirection, String> neighbors;
}
