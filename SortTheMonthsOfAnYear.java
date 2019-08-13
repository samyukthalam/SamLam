package SampleTests.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTheMonthsOfAnYear {

	public static void main(String[] args) {
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		usingArrays(months);
		usingCollections(months);
		usingComparison(months);
	}

	private static void usingComparison(String[] months) {
		String temp = null;
		for (int i = 0; i < months.length; i++) {
			for (int j = i + 1; j < months.length; j++) {
				if (months[i].compareTo(months[j]) > 0) {
					temp = months[i];
					months[i] = months[j];
					months[j] = temp;
				}
			}
		}
		for (int i = 0; i < months.length; i++)
			System.out.println(months[i]);
	}

	private static void usingCollections(String[] months) {
		List<String> lsmonths = new ArrayList<String>();
		for (int i = 0; i < months.length; i++)
			lsmonths.add(months[i]);
		Collections.sort(lsmonths);
		for (int i = 0; i < months.length; i++)
			System.out.println(months[i]);
	}

	private static void usingArrays(String[] months) {
		Arrays.sort(months);
		for (int i = 0; i < months.length; i++)
			System.out.println(months[i]);
	}

}
