package strategy;

public class StrategyApplication {

    public static void main(String[] args) {
        Payment KRWPayment = new Payment(new KRWPayment());
        KRWPayment.payment();

        Payment USDPayment = new Payment(new USDPayment());
        USDPayment.payment();;
    }

}
