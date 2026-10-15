
public class Complex {
    double re;
    double im;
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public Complex add(Complex b) {
        return new Complex(re + b.re, im + b.im);
    }
    public Complex subtract(Complex b) {
        return new Complex(re - b.re, im - b.im);
    }
    public Complex multiply(Complex b) {
        double a = re * b.re - im * b.im;
        double c = re * b.im + im * b.re;
        return new Complex(a, c);
    }
    public Complex divide(Complex b) {
        double d = b.re * b.re + b.im * b.im;
        if (d == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double a = (re * b.re + im * b.im) / d;
        double c = (im * b.re - re * b.im) / d;
        return new Complex(a, c);
    }
    public Complex negate() {
        return new Complex(-re, -im);
    }
    public boolean isZero() {
        return Math.abs(re) < 1e-10 && Math.abs(im) < 1e-10;
    }
    public String toString() {
        if (Math.abs(im) < 1e-10) {
            return String.format("%.2f", re);
        }
        if (Math.abs(re) < 1e-10) {
            return String.format("%.2fi", im);
        }
        if (im < 0) {
            return String.format("%.2f - %.2fi", re, -im);
        }
        return String.format("%.2f + %.2fi", re, im);
    }
}