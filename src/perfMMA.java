import java.util.Random;
public class perfMMA {
	public static void main(String args[]) {
		int size = 2;
		System.out.println("size\tordinarytime\tstrassentime");
		for(;size<=256; size*=2) {
		int deg = 64;
		Random r = new Random(37);
		Polynomial A[][] = new Polynomial[size][size];
		Polynomial B[][] = new Polynomial[size][size];
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				double[] koef = new double[deg];
				for(int k = 0; k<deg; k++) {
					koef[k] = r.nextDouble();
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
		Polynomial C1[][] = MMAPolynomials.ordinary(A,B,size);
		System.out.print(size + "\t" + (System.currentTimeMillis() - t));
		t = System.currentTimeMillis();
		Polynomial C2[][] = MMAPolynomials.strassen(A,B,size);
		System.out.println("\t" + (System.currentTimeMillis() - t));
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
}