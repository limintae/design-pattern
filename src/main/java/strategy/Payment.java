package strategy;

public class Payment {

    private final PaymentStrategy paymentStrategy;

    Payment(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payment() {
        paymentStrategy.payment();
    }

}
