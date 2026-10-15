
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Matrix readMatrix() {
        System.out.print("Количество строк: ");
        int rows = sc.nextInt();
        System.out.print("Количество столбцов: ");
        int cols = sc.nextInt();
        Matrix m = new Matrix(rows, cols);
        System.out.println("Введите действительную и мнимую части каждого элемента.");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + (i + 1) + "][" + (j + 1) + "]: ");
                double re = sc.nextDouble();
                double im = sc.nextDouble();
                m.set(i, j, new Complex(re, im));
            }
        }
        return m;
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== МАТРИЦЫ КОМПЛЕКСНЫХ ЧИСЕЛ ===");
            System.out.println("1. Сложение");
            System.out.println("2. Умножение");
            System.out.println("3. Деление");
            System.out.println("4. Транспонирование");
            System.out.println("5. Определитель");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");
            int choice = sc.nextInt();
            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;
            }
            try {
                Matrix a;
                Matrix b;
                Matrix result;
                switch (choice) {
                    case 1:
                        System.out.println("Первая матрица:");
                        a = readMatrix();
                        System.out.println("Вторая матрица:");
                        b = readMatrix();
                        result = a.add(b);
                        System.out.println("Результат:");
                        result.print();
                        break;
                    case 2:
                        System.out.println("Первая матрица:");
                        a = readMatrix();
                        System.out.println("Вторая матрица:");
                        b = readMatrix();
                        result = a.multiply(b);
                        System.out.println("Результат:");
                        result.print();
                        break;
                    case 3:
                        System.out.println("Первая матрица:");
                        a = readMatrix();
                        System.out.println("Вторая матрица:");
                        b = readMatrix();
                        result = a.divide(b);
                        System.out.println("Результат:");
                        result.print();
                        break;
                    case 4:
                        System.out.println("Матрица:");
                        a = readMatrix();
                        result = a.transpose();
                        System.out.println("Транспонированная матрица:");
                        result.print();
                        break;
                    case 5:
                        System.out.println("Матрица:");
                        a = readMatrix();
                        System.out.println("Определитель: " + a.determinant());
                        break;
                    default:
                        System.out.println("Нет такой операции.");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        sc.close();
    }
}