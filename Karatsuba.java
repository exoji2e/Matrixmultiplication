
public class Karatsuba
{
  public long karMult(long x, long y){
      int size1 = getSize(x);
      int size2 = getSize(y);

      int N = Math.max(size1, size2);

      if (N < 10) {
        return x * y;
      }

      N = (N / 2) + (N % 2);
      long m = (long)Math.pow(10, N);

      // Task3
      long b = x / m;
      long a = x - (b * m);
      long d = y / m;
      long c = y - (d * N);

      long z0 = karMult(a, c);
      long z1 = karMult(a + b, c + d);
      long z2 = karMult(b, d);


      // Task4
      return z0 + ((z1 - z0 - z2) * m) + (z2 * (long)(Math.pow(10, 2 * N)));
  }

  public int getSize(long num)
  {
      int ctr = 0;
      while (num != 0)
      {
          ctr++;
          num /= 10;
      }
      return ctr;
  }
}
