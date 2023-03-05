import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

t[1], t[2], ..., t[k]

right cyclic = last digit will be moved to the first
t[k], t[1], t[2], ..., t[k-1]

left cyclic = first digit will be moved to the last
t[2], t[3], ..., t[k], t[1]

therefore, a good string is
t[k] = t[2], t[1] = t[3], t[2] = t[4], ..., t[k-2] = t[k], t[k-1] = t[1]
-> basically, an alternating sequence of two digits (e.g., 121212...)

correct approach: brute-force + greedy (for subsequence)

 */

// It's all about creating test cases
public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			char[] s = fs.next().toCharArray();
			int max = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					max = Math.max(max, countLength(s, i, j));
				}
			}
			System.out.println(s.length - max);
		}
		out.close();
	}
	
	static int countLength(char[] s, int x, int y) {
		int res = 0;
		for (char ch : s) {
			if (ch - '0' == x) {
				res++;
				// cool trick of checking the subsequence (swapping)
				int temp = x;
				x = y;
				y = temp;
			}
		}
		if (x != y && res % 2 == 1) {
			res--;
		}
		return res;
	}
	
	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
