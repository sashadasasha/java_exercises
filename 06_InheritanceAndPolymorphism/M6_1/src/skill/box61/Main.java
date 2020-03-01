package skill.box61;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//       DepositCount depCount =  new DepositCount();
//       LocalDate dateOfAddingMoney = LocalDate.of(2020, 02, 25);
//       depCount.putMoney(5000.0, dateOfAddingMoney);
//       depCount.getMoneyInfo();
//       depCount.takeMoney(1000);

        CardCount cardCount = new CardCount();
        cardCount.putMoney(10000.0);
        cardCount.getMoneyInfo();
        cardCount.takeMoney(3000.0);
    }
}
