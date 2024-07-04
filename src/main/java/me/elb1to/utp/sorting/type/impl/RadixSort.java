package me.elb1to.utp.sorting.type.impl;

import me.elb1to.utp.sorting.type.SortingType;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class RadixSort extends SortingType {

	public RadixSort() {
		super(RadixSort.class);
	}

	@Override
	public int[] sort(int[] array) {
		int m = getMax(array, array.length);
		for (int exp = 1; m / exp > 0; exp *= 10) {
			countSort(array, array.length, exp);
		}

		return array;
	}

	private void countSort(int[] array, int n, int exp) {
		int[] output = new int[n];
		int[] count = new int[10];
		for (int i = 0; i < n; i++) count[(array[i] / exp) % 10]++;
		for (int i = 1; i < 10; i++) count[i] += count[i - 1];

		for (int i = n - 1; i >= 0; i--) {
			output[count[(array[i] / exp) % 10] - 1] = array[i];
			count[(array[i] / exp) % 10]--;
		}

		for (int i = 0; i < n; i++) {
			array[i] = output[i];
		}
	}

	private int getMax(int[] array, int n) {
		int mx = array[0];
		for (int i = 1; i < n; i++) {
			if (array[i] > mx) {
				mx = array[i];
			}
		}

		return mx;
	}
}
