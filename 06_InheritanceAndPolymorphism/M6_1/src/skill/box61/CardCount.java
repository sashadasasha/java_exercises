package skill.box61;

public class CardCount extends BankAccount {

    private final double COMMISSION = 1.01;

    @Override
    public double takeMoney(double sumForTaking) {
        System.out.printf("Сумма снятия %.2f рублей%n", sumForTaking);
        System.out.printf("Комиссия составит %.2f рублей%n", sumForTaking * COMMISSION - sumForTaking);
        return super.takeMoney(sumForTaking * COMMISSION);
    }
}
