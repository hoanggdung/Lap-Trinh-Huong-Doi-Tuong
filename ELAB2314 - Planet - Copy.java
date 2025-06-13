import java.util.*;  //ELAB2314 - Planet

public class Main {
    static class HanhTinh {
        double m, r;
        static final double G = 6.673e-11;
        HanhTinh(double m, double r) { this.m = m; this.r = r; }
        double g() { return G * m / (r * r); }
        double w(double w0) {
            double g0 = G * 5.976e24 / (6.37814e6 * 6.37814e6);
            return w0 * g() / g0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String t = sc.nextLine();
        double m = Double.parseDouble(sc.nextLine());
        double r = Double.parseDouble(sc.nextLine());
        double w0 = Double.parseDouble(sc.nextLine());
        System.out.printf("Your weight on %s is %.2f", t, new HanhTinh(m, r).w(w0));
    }
}
