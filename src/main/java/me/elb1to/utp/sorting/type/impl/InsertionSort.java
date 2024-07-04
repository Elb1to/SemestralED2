package me.elb1to.utp.sorting.type.impl;

import me.elb1to.utp.sorting.type.SortingType;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class InsertionSort extends SortingType {

	public InsertionSort() {
		super(InsertionSort.class);
	}

	@Override
	public int[] sort(int[] array) {
		int n = array.length;
		for (int i = 1; i < n; ++i) {
			int key = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j = j - 1;
			}

			array[j + 1] = key;
		}

		return array;
	}
}
