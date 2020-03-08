package skill.box6_3;

public class SoleTraderClient extends Client {

    private final double COMISSION_FOR_DEPOSIT_UNDER_1000 = 0.01;
    private final double COMISSION_FOR_DEPOSIT_OVER_1000 = 0.005;
    public SoleTraderClient(int count) {
        super(count);
    }

    @Override
    public void depositMoney(double ammount) {
        if (ammount >= 1000.0) {
            System.out.printf("Комиссия составит %.2f рублей%n", ammount*COMISSION_FOR_DEPOSIT_OVER_1000);
            super.depositMoney(ammount - ammount*COMISSION_FOR_DEPOSIT_OVER_1000);
        } else {
            System.out.printf("Комиссия составит %.2f рублей%n", ammount*COMISSION_FOR_DEPOSIT_UNDER_1000);
            super.depositMoney(ammount - ammount*COMISSION_FOR_DEPOSIT_UNDER_1000);
        }
    }
}
