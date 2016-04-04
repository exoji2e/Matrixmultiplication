import java.util.Random;
public class perfKaratsuba {
	public static void main(String args[]) {

for (int n = 0 ; n < 10 ; n++){
    int deg1 = (int)Math.pow(2, n);
    int deg2 = (int)Math.pow(2, n);
    Random r = new Random();
    double[] koef1 = new double[deg1];
    double[] koef2 = new double[deg2];
    for(int k = 0; k<deg1; k++) {
      koef1[k] = (int)(r.nextDouble()*10);
    }
    for(int k = 0; k<deg2; k++) {
      koef2[k] = (int)(r.nextDouble()*10);
    }
    Polynomial p1 = new Polynomial(koef1);
    Polynomial p2 = new Polynomial(koef2);


    //System.out.println(p1.toString());
		//System.out.println(p2.toString());

		//System.out.println("Karatsuba:" + p2.karatsuba(p1));
		//System.out.println("Normal:" + p2.multiply(p1));




		long t1 = System.nanoTime();
		Polynomial pkar=p2.karatsuba(p1);
		t1 = System.nanoTime() - t1;
		//System.out.println("Karatsuba: " + (t1));


		//long t2 = System.currentTimeMillis();
		long t2 = System.nanoTime();
		Polynomial pmult = p2.multiply(p1);
		t2 = System.nanoTime() - t2;
		//System.out.println("multiply : " + (t2));
		System.out.println("Degree: 10^" + n + ", working = " +pkar.equals(pmult));
		double ratio = ((double) t2)/((double) t1);
		System.out.println("multiply/karatsuba = " + ratio);
		System.out.println("---------");

}




  }
}
