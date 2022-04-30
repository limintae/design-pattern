package strategy;

public class KRWPayment implements PaymentStrategy {

    @Override
    public void payment() {
        System.out.println("KRW payment");
    }

}
