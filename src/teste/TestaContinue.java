package teste;

public class TestaContinue {
	public static void main(String[] args) {
		int[] num = {1, 2, 3, 4, 5, 6};
		int x;
		for (x = 0; x < num.length; x++) {
			if (x == 3) continue;
			System.out.print(x + " ");
		}
		System.out.println(x);
	}
}
