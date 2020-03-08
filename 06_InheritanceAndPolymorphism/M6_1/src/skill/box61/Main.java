package skill.box61;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//       DepositCount depCount =  new DepositCount();
//       depCount.putMoney(5000.0);
//       System.out.println(depCount.getAccountMoney());
//       System.out.println(depCount.takeMoney(1000));

        CardCount cardCount = new CardCount();
        cardCount.putMoney(10000.0);
        System.out.printf("Cумма на вашем счете %.2f рублей%n", cardCount.getAccountMoney());
        System.out.printf("На вашем счете осталось %.2f рублей%n", cardCount.takeMoney(9999.0));
    }
}
