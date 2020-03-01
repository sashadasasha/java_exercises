package skill.box61;

public class CardCount extends BankAccount {

    @Override
    public void takeMoney(double sumForTaking) {
        accountMoney = accountMoney - sumForTaking*1.01;

        System.out.printf("Вы сняли со счета %.2f рублей\n" +
                "Комиссия составила %.2f рублей\n" +
                "Остаток на счете %.2f рублей\n", sumForTaking, sumForTaking*0.01, accountMoney);
    }
}
