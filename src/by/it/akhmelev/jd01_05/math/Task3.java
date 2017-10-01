package by.it.akhmelev.jd01_05.math;

import static java.lang.Math.tan;
import static java.lang.Math.pow;

public class Task3 {
    public static void printTable(){
        double x = 3.567;
        System.out.println("┃ param ┃        value       ┃");
        for (double a = 3.5; a <= 25.5; a += 3.75){
            double y = (1.0 / tan(pow(x, 3))) + 2.24 * a * x;
            System.out.printf("%-2s%-6s%-2s%-19s%-20s%n", "┃", a, "┃", y, "┃");
        }
    }
}
