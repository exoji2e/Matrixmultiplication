import java.util.Random;
public class TestMMAD {
	public static void main(String args[]) {
		int size = 512;
		Random r = new Random(37);
		double A[][] = new double[size][size];
		double B[][] = new double[size][size];
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				A[i][j] = r.nextDouble();
				B[i][j] = r.nextDouble();
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
		double C1[][] = MMADoubles.ordinary(A,B,size);
		System.out.println(System.currentTimeMillis() - t);
		t = System.currentTimeMillis();
		double C2[][] = MMADoubles.strassen(A,B,size);
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