package maze.common.utils;


public class AssertUtil {
	public static void assertNotNull(Object o) {
		if (o == null)
			throw new IllegalStateException("Object must not be null.");
	}
	
	public static void assertNotNull(Object o, String errorMessage) {
		if (o == null)
			throw new IllegalStateException(errorMessage);
	}

	public static void assertNotEmptyString(String s) {
		if (s == null || s.trim().isEmpty())
			throw new IllegalStateException("String must not be empty.");
	}
}
