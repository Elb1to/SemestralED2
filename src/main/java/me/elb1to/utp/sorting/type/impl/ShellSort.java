package me.elb1to.utp.sorting.type.impl;

import me.elb1to.utp.sorting.type.SortingType;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class ShellSort extends SortingType {

	public ShellSort() {
		super(ShellSort.class);
	}

	@Override
	public int[] sort(int[] array) {
		int n = array.length;
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i += 1) {
				int temp = array[i];
				int j;
				for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
					array[j] = array[j - gap];
				}

				array[j] = temp;
			}
		}

		return array;
	}
}
