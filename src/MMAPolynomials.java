public class MMAPolynomials {
	public static Polynomial[][] ordinary(Polynomial[][] A, Polynomial[][] B, int n) {
		Polynomial[][] ret = new Polynomial[n][n];
		for(int i = 0 ; i<n; i++) {
			for(int j = 0; j<n; j++) {
				ret[i][j] = new Polynomial();
				for(int k = 0; k<n; k++) {
					ret[i][j] = ret[i][j].add(A[i][k].multiply(B[k][j]));
				}
			}
		}
		return ret;
	}
	public static Polynomial[][] addMatrix(Polynomial[][] A, Polynomial[][] B, int n) {
		Polynomial[][] C = new Polynomial[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				C[i][j] = A[i][j].add(B[i][j]);
			}
		}
		return C;
	}
	public static Polynomial[][] subtractMatrix(Polynomial[][] A, Polynomial[][] B, int n) {
		Polynomial[][] C = new Polynomial[n][n];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				C[i][j] = A[i][j].subtract(B[i][j]);
			}
		}
		return C;
	}
	public static Polynomial[][] concatMatrix(Polynomial[][] C11, Polynomial[][] C12, Polynomial[][] C21, Polynomial[][] C22, int n) {
		Polynomial C [][] = new Polynomial[2*n][2*n];
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
	public static Polynomial[][] strassen(Polynomial[][] A, Polynomial[][] B, int n) {
		if(n == 1) {
			Polynomial[][] a = new Polynomial[1][1];
			a[0][0] = A[0][0].multiply(B[0][0]);
			return a;
		}
		int n2 = n/2;
		Polynomial[][] A11 = new Polynomial[n2][n2];
		Polynomial[][] A12 = new Polynomial[n2][n2];
		Polynomial[][] A21 = new Polynomial[n2][n2];
		Polynomial[][] A22 = new Polynomial[n2][n2];
		Polynomial[][] B11 = new Polynomial[n2][n2];
		Polynomial[][] B12 = new Polynomial[n2][n2];
		Polynomial[][] B21 = new Polynomial[n2][n2];
		Polynomial[][] B22 = new Polynomial[n2][n2];
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
		Polynomial[][] M1 = strassen(addMatrix(A11,A22,n2),addMatrix(B11,B22,n2),n2);
		Polynomial[][] M2 = strassen(addMatrix(A21,A22,n2),B11,n2);
		Polynomial[][] M3 = strassen(A11,subtractMatrix(B12,B22,n2),n2);
		Polynomial[][] M4 = strassen(A22,subtractMatrix(B21,B11,n2),n2);
		Polynomial[][] M5 = strassen(addMatrix(A11,A12,n2),B22,n2);		
		Polynomial[][] M6 = strassen(subtractMatrix(A21,A11,n2),addMatrix(B11,B12,n2),n2);
		Polynomial[][] M7 = strassen(subtractMatrix(A12,A22,n2),addMatrix(B21,B22,n2),n2);
		
		Polynomial[][] C11 = addMatrix(addMatrix(M1,M4,n2),subtractMatrix(M7,M5,n2),n2);
		Polynomial[][] C12 = addMatrix(M3,M5,n2);
		Polynomial[][] C21 = addMatrix(M2,M4,n2);
		Polynomial[][] C22 = addMatrix(subtractMatrix(M1,M2,n2),addMatrix(M3,M6,n2),n2);
		
		return concatMatrix(C11,C12,C21,C22,n2);
	}
}























