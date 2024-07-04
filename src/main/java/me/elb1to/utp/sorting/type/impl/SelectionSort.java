package me.elb1to.utp.sorting.type.impl;

import me.elb1to.utp.sorting.type.SortingType;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class SelectionSort extends SortingType {

	public SelectionSort() {
		super(SelectionSort.class);
	}

	@Override
	public int[] sort(int[] array) {
		int n = array.length;
		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (array[j] < array[min_idx]) {
					min_idx = j;
				}
			}

			int temp = array[min_idx];
			array[min_idx] = array[i];
			array[i] = temp;
		}

		return array;
	}
}
