import java.util.Random;
public class TestMMA {
	public static void main(String args[]) {
		int size = 256;
		int deg = 1;
		Random r = new Random(37);
		Polynomial A[][] = new Polynomial[size][size];
		Polynomial B[][] = new Polynomial[size][size];
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				double[] koef = new double[deg];
				for(int k = 0; k<deg; k++) {
					koef[k] = (int)(r.nextDouble()*10);
				}
				A[i][j] = new Polynomial(koef);
				B[i][j] = new Polynomial(koef);
			}
		}
		/*System.out.println("\n A:");
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				System.out.print(A[i][j].toString() + "\t");
			}
			System.out.println();
		}*/
		
		long t = System.currentTimeMillis();
		Polynomial C1[][] = MMAs.ordinary(A,B,size);
		System.out.println(System.currentTimeMillis() - t);
		t = System.currentTimeMillis();
		Polynomial C2[][] = MMAs.strassen(A,B,size);
		System.out.println(System.currentTimeMillis() - t);
		/*System.out.println("\n C1:");
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				System.out.print(C1[i][j].toString() + "\t");
			}
			System.out.println();
		}
		System.out.println("\n C2:");
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				System.out.print(C2[i][j].toString() + "\t");
			}
			System.out.println();
		}*/
	}
}