package skill.box6_3;

public class LegalPersonClient extends Client {

    private final double COMISSION = 1.01;

    public LegalPersonClient(int count) {
        super(count);
    }

    @Override
    public double getMoneyFromCount(double sum) {
        System.out.printf("Комиссия при внесении составит %.2f рублей%n", sum * COMISSION - sum);
        return super.getMoneyFromCount(sum * COMISSION);

    }
}
