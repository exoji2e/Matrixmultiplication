import java.util.Random;
public class perfKaratsuba {
	public static void main(String args[]) {
		for (int n = 0; n <= 17; n++){
			int deg1 = (int)Math.pow(2, n);
			int deg2 = (int)Math.pow(2, n);
			Random r = new Random();
			double[] koef1 = new double[deg1];
			double[] koef2 = new double[deg2];
			for(int k = 0; k<deg1; k++) {
				koef1[k] = r.nextDouble();
			}
			for(int k = 0; k<deg2; k++) {
				koef2[k] = r.nextDouble();
			}
			Polynomial p1 = new Polynomial(koef1);
			Polynomial p2 = new Polynomial(koef2);
			long t1 = System.nanoTime();
			Polynomial pkar=p2.karatsuba(p1);
			t1 = System.nanoTime() - t1;
			long t2 = System.nanoTime();
			Polynomial pmult = p2.multiply(p1);
			t2 = System.nanoTime() - t2;
			double ratio = ((double) t2)/((double) t1);
			System.out.println(n + " " + ratio + " " + t1 + " " + t2);
			/*System.out.println("Degree: 2^" + n + " pkar.equals(pmul):" + pkar.equals(pmult));
			System.out.println("multiply/karatsuba = " + ratio);
			System.out.println("karatsuba = " + t1);
			System.out.println("multiply = " + t2);
			
			System.out.println("---------");*/
		}
	}
}
