package Project01;

public class MatrixMultiplication
{
    private static MatrixMultiplication instance = null;

    private MatrixMultiplication()
    {
    }

    public static MatrixMultiplication getInstance()
    {
        if(instance == null)
            instance = new MatrixMultiplication();
        return instance;
    }

    /**
     * Classical Matrix Multiplication algorithm for square matrices.
     * @param n - The size of the matrix.
     * @param a - The matrix A.
     * @param b - The matrix B.
     * @return - Returns the calculated matrix C.
     */
    public int[][] classicalMatrixMultiplication(int n, int[][] a, int[][] b)
    {
        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                c[i][j] = 0;
                for (int k = 0; k < n; k++)
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
            }
        }

        return c;
    }

    public void strassenMatrixMultiplication()
    {



    }
}
