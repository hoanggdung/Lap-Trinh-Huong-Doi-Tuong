class Money {
    private int euros;
    private int cents;
    public Money(int euros, int cents) {
        this.euros = euros + cents / 100;
        this.cents = cents % 100;
    }
    public String toString() {
        return String.format("%d.%02de", euros, cents);
    }
    public Money plus(Money added) {
        return new Money(this.euros + added.euros, this.cents + added.cents);
    }
    public boolean lessThan(Money compared) {
        return this.euros < compared.euros || (this.euros == compared.euros && this.cents < compared.cents);
    }
    public Money minus(Money minus) {
        int total1 = this.euros * 100 + this.cents;
        int total2 = minus.euros * 100 + minus.cents;
        int diff = total1 - total2;
        if (diff < 0) return new Money(0, 0);
        return new Money(0, diff);
    }
}
public class Main {
    public static void main(String[] args) {
        // (1)
        Money a = new Money(10, 8);
        Money b = new Money(5, 5);
        Money c = a.plus(b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        // (2)
        Money u = new Money(10, 0);
        Money y = new Money(3, 0);
        Money w = new Money(5, 0);
        System.out.println(u.lessThan(y));
        System.out.println(y.lessThan(w));
        // (3)
        Money m = new Money(10, 0);
        Money n = new Money(3, 50);
        Money o = m.minus(n);
        System.out.println(m);
        System.out.println(n);
        System.out.println(o);
        o = o.minus(m);
        System.out.println(o);
    }
}
