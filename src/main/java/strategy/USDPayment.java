package strategy;

public class USDPayment implements PaymentStrategy {

    @Override
    public void payment() {
        System.out.println("USDPayment");
    }

}
