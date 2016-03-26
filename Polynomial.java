import java.util.Arrays;
public class Polynomial {
	private double[] kf;
	int deg;
	Karatsuba kar = new Karatsuba();
	public Polynomial(double [] arr) {

		kf = new double[arr.length];
		deg = arr.length;
		for(int i = 0; i<deg; i++) {
			kf[i] = arr[i];
		}
	}

	public Polynomial() {
		kf = new double[1];
		kf[0] = 0;
	}

	public Polynomial add(Polynomial p) {
		if(kf.length>p.kf.length) {
			double[] nkf = new double[kf.length];
			for(int i = 0; i<p.kf.length; i++) {
				nkf[i] = p.kf[i] + kf[i];
			}
			for(int i = p.kf.length; i<kf.length; i++) {
				nkf[i] = kf[i];
			}
			return new Polynomial(nkf);
		} else {
			double[] nkf = new double[p.kf.length];
			for(int i = 0; i<kf.length; i++) {
				nkf[i] = p.kf[i] + kf[i];
			}
			for(int i = kf.length; i<p.kf.length; i++) {
				nkf[i] = p.kf[i];
			}
			return new Polynomial(nkf);
		}
	}


	public Polynomial subtract(Polynomial p) {
		if(kf.length>p.kf.length) {
			double[] nkf = new double[kf.length];
			for(int i = 0; i<p.kf.length; i++) {
				nkf[i] =  kf[i] - p.kf[i];
			}
			for(int i = p.kf.length; i<kf.length; i++) {
				nkf[i] = kf[i];
			}
			return new Polynomial(nkf);
		} else {
			double[] nkf = new double[p.kf.length];
			for(int i = 0; i<kf.length; i++) {
				nkf[i] = kf[i] - p.kf[i];
			}
			for(int i = kf.length; i<p.kf.length; i++) {
				nkf[i] = -p.kf[i];
			}
			return new Polynomial(nkf);
		}
	}


	public Polynomial multiply(Polynomial p) {
		double[] nkf = new double[kf.length+p.kf.length - 1];
		for(int i = 0; i<p.kf.length; i++) {
			for(int j = 0; j<kf.length; j++) {
				nkf[i+j] += p.kf[i]*kf[j];
			}
		}
		return new Polynomial(nkf);
	}




	public Polynomial shift(int number) {

		double array[] = new double[number + deg];
		for( int i = 0; i < number; i++){
			array[i] = 0;
		}
		for (int i = number; i <array.length;i++){
			array[i] = kf[i-number];
		}
		return new Polynomial(array);
	}


	//returna första halvan av polynomet
	private double[] partHigh(double[] array, int size) {
		double[] part1 = new double[size];
		System.arraycopy(array, 0, part1, 0, part1.length);
		return part1;
}


// returna andra halvan av polynomet, size är fortfarande storleken på första halvan... De borde väl iofs vara lika.
private double[] partLow(double[] array, int size) {
	double[] part2 = new double[array.length-size];
	System.arraycopy(array, size, part2, 0, part2.length);
	return part2;
}


	public Polynomial karatsuba(Polynomial g){
		Polynomial f = new Polynomial(kf);

		// Task1
		if (f.deg < 2 && g.deg < 2){
			double r[] = {(double)kar.karMult((long) f.kf[0], (long) g.kf[0])};
			return new Polynomial(r);
			//return f.multiply(g);
		}

		// Task2
		int n = Math.max(f.deg,g.deg);
		int n2 = n / 2;

		//Task3
		Polynomial F1 = new Polynomial(partHigh(f.kf,n2));
		Polynomial F0 = new Polynomial(partLow(f.kf,f.deg-n2));
		Polynomial G1 = new Polynomial(partHigh(g.kf,n2));
		Polynomial G0 = new Polynomial(partLow(g.kf,g.deg-n2));


		Polynomial F0G0 = new Polynomial(F0.karatsuba(G0).kf);
		Polynomial F1G1 = new Polynomial(F1.karatsuba(G1).kf);
		Polynomial W = new Polynomial((F0.add(F1)).karatsuba(G0.add(G1)).kf);

		//Task4

		// Istället för att höja upp F1G1 med 10^n så "shiftar" jag ner F0G0 med 10^n istället, typ.
		return F1G1.add((W.subtract(F0G0).subtract(F1G1)).shift(n2)).add(F0G0.shift(n));


	}

	public String toString() {
		String s = "P=";
		for(int i = 0; i<kf.length; i++) {
			s+=((int)kf[i] + "x" + (kf.length - i - 1) + " + "); //with x-es
		}
		return s;
	}
	public boolean equals(Polynomial p) {
		if (p.deg != deg) return false;
		for (int i = 0 ; i < deg ; i++){
			if (p.kf[i] != kf[i]) return false;
		}
		return true;

	}
}
