public class Polynomial extends MatrixElement {
	private double[] kf;
	public Polynomial(double [] arr) {
		kf = new double[arr.length];
		for(int i = 0; i<arr.length; i++) {
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
	public String toString() {
		String s = "P: ";
		for(int i = 0; i<kf.length; i++) {
			s+=("" + i + ": "+kf[i] + " ");
		}
		return s;
	}
}