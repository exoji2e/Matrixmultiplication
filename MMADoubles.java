public class MMADoubles {
	public static double[][] ordinary(double[][] A, double[][] B, int n) {
		double[][] ret = new double[n][n];
		for(int i = 0 ; i<n; i++) {
			for(int j = 0; j<n; j++) {
				ret[i][j] = 0;
				for(int k = 0; k<n; k++) {
					ret[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		return ret;
	}
	public static double[][] addMatrix(double[][] A, double[][] B, int n) {
		double[][] C = new double[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				C[i][j] = A[i][j]+B[i][j];
			}
		}
		return C;
	}
	public static double[][] subtractMatrix(double[][] A, double[][] B, int n) {
		double[][] C = new double[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				C[i][j] = A[i][j]-B[i][j];
			}
		}
		return C;
	}
	public static double[][] concatMatrix(double[][] C11, double[][] C12, double[][] C21, double[][] C22, int n) {
		double C [][] = new double[2*n][2*n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				C[i][j] = C11[i][j];
				C[i+n][j] = C21[i][j];
				C[i][j+n] = C12[i][j];
				C[i+n][j+n] = C22[i][j];
			}
		}
		return C;
	}
	public static double[][] strassen(double[][] A, double[][] B, int n) {
		if(n == 1) {
			double[][] a = new double[1][1];
			a[0][0] = A[0][0]*B[0][0];
			return a;
		}
		int n2 = n/2;
		double[][] A11 = new double[n2][n2];
		double[][] A12 = new double[n2][n2];
		double[][] A21 = new double[n2][n2];
		double[][] A22 = new double[n2][n2];
		double[][] B11 = new double[n2][n2];
		double[][] B12 = new double[n2][n2];
		double[][] B21 = new double[n2][n2];
		double[][] B22 = new double[n2][n2];
		for(int i = 0; i<n2; i++) {
			for(int j = 0; j<n2; j++) {
				A11[i][j] = A[i][j];
				B11[i][j] = B[i][j];
				A12[i][j] = A[i][j+n2];
				B12[i][j] = B[i][j+n2];
				A21[i][j] = A[i+n2][j];
				B21[i][j] = B[i+n2][j];
				A22[i][j] = A[i+n2][j+n2];
				B22[i][j] = B[i+n2][j+n2];
			}
		}
		double[][] M1 = strassen(addMatrix(A11,A22,n2),addMatrix(B11,B22,n2),n2);
		double[][] M2 = strassen(addMatrix(A21,A22,n2),B11,n2);
		double[][] M3 = strassen(A11,subtractMatrix(B12,B22,n2),n2);
		double[][] M4 = strassen(A22,subtractMatrix(B21,B11,n2),n2);
		double[][] M5 = strassen(addMatrix(A11,A12,n2),B22,n2);		
		double[][] M6 = strassen(subtractMatrix(A21,A11,n2),addMatrix(B11,B12,n2),n2);
		double[][] M7 = strassen(subtractMatrix(A12,A22,n2),addMatrix(B21,B22,n2),n2);
		
		double[][] C11 = addMatrix(addMatrix(M1,M4,n2),subtractMatrix(M7,M5,n2),n2);
		double[][] C12 = addMatrix(M3,M5,n2);
		double[][] C21 = addMatrix(M2,M4,n2);
		double[][] C22 = addMatrix(subtractMatrix(M1,M2,n2),addMatrix(M3,M6,n2),n2);
		
		return concatMatrix(C11,C12,C21,C22,n2);
	}
}























