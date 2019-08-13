package SampleTests.Practice;

public class SearchAnArrayForAnElement {

	public static void main(String[] args) {
		int a[] = { 3, 6, 1, 8, 22, 1, 0, 6 };
		int key = 22;
		searchForKey(a, key);
	}

	private static void searchForKey(int[] a, int key) {
		int flag = 0;
		int i;
		for (i = 0; i < a.length; i++) {
			if (key == a[i]) {
				flag = flag + 1;
				break;
			}
		}
		if (flag == 1)
			System.out.println("Key is found at " + i + " th place");
	}

}
