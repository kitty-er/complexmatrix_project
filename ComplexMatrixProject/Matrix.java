
public class Matrix {
    int rows;
    int cols;
    Complex[][] data;
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }
    public void set(int i, int j, Complex value) {
        data[i][j] = value;
    }
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public Matrix add(Matrix b) {
        if (rows != b.rows || cols != b.cols) {
            throw new IllegalArgumentException(
                "Matrix sizes must be equal"
            );
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].add(b.data[i][j]);
            }
        }
        return result;
    }
    public Matrix multiply(Matrix b) {
        if (cols != b.rows) {
            throw new IllegalArgumentException(
                "Number of columns of A must equal rows of B"
            );
        }
        Matrix result = new Matrix(rows, b.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                Complex sum = new Complex(0, 0);

                for (int k = 0; k < cols; k++) {
                    sum = sum.add(data[i][k].multiply(b.data[k][j]));
                }

                result.data[i][j] = sum;
            }
        }
        return result;
    }
    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = data[i][j];
            }
        }
        return result;
    }
    private Matrix minor(int row, int col) {
        Matrix result = new Matrix(rows - 1, cols - 1);
        int r = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) {
                continue;
            }
            int c = 0;
            for (int j = 0; j < cols; j++) {
                if (j == col) {
                    continue;
                }
                result.data[r][c] = data[i][j];
                c++;
            }
            r++;
        }
        return result;
    }
    public Complex determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                "Determinant exists only for square matrices"
            );
        }
        if (rows == 1) {
            return data[0][0];
        }
        if (rows == 2) {
            return data[0][0].multiply(data[1][1])
                .subtract(data[0][1].multiply(data[1][0]));
        }
        Complex result = new Complex(0, 0);
        for (int j = 0; j < cols; j++) {
            Complex term = data[0][j].multiply(
                minor(0, j).determinant()
            );
            if (j % 2 == 1) {
                term = term.negate();
            }
            result = result.add(term);
        }
        return result;
    }
    public Matrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                "Inverse exists only for square matrices"
            );
        }
        Complex det = determinant();
        if (det.isZero()) {
            throw new IllegalArgumentException(
                "Inverse does not exist: determinant is zero"
            );
        }
        Matrix cofactors = new Matrix(rows, cols);
        if (rows == 1) {
            cofactors.data[0][0] = new Complex(1, 0);
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Complex value = minor(i, j).determinant();
                    if ((i + j) % 2 == 1) {
                        value = value.negate();
                    }
                    cofactors.data[i][j] = value;
                }
            }
        }
        Matrix result = cofactors.transpose();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = result.data[i][j].divide(det);
            }
        }
        return result;
    }
    public Matrix divide(Matrix b) {
        return multiply(b.inverse());
    }
}