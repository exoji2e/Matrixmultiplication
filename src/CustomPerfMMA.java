import java.util.Random;
public class CustomPerfMMA {
	public static void main(String args[]) {
		Random r = new Random(37);
		System.out.println("deg\tsize\tn\totime\tstime ");
		int deg = 4;
		int maxsize = 8192;
		for(int size = 2 ;size<=maxsize; size*=2) {
			int n = (size>=128) ? 1 : 10;
			long ts = 0;
			long tm = 0;
			for(int m = 0; m<n; m++) {
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
				long t = System.currentTimeMillis();
				Polynomial C1[][] = MMAPolynomials.ordinary(A,B,size);
				tm += (System.currentTimeMillis() - t);
				t = System.currentTimeMillis();
				Polynomial C2[][] = MMAPolynomials.strassen(A,B,size);
				ts += (System.currentTimeMillis() - t);
			}
			if(n!= 0) {
				System.out.println(deg + "\t" + size + "\t" + n + "\t" + tm/n + "\t" + ts/n);
			}
		}	
	}
}