package me.elb1to.utp.sorting.type;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class SortingType {

	private final Class<?> sortingType;

	public SortingType(Class<?> sortingType) {
		this.sortingType = sortingType;
	}

	public int[] sort(int[] array) {
		return array;
	}

	/*public int[] sort(int[] array) {
		try {
			this.sortingType.getMethod("sort", int[].class).invoke(null, array);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return array;
	}*/

	public Class<?> getSortingType() {
		return sortingType;
	}
}
