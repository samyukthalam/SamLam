package SampleTests.Practice;

public class MaxAndMinOfAnArray {

	public static void main(String[] args) {
		int a[] = { 3, 2, 5, 1, 0, 35 };
		System.out.println("Maximum is : " + max(a));
		System.out.println("Minimum is : " + min(a));
	}

	private static int max(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max)
				max = a[i];
		}
		return max;
	}

	private static int min(int[] a) {
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] < min)
				min = a[i];
		}
		return min;
	}

}
