package skill.box61;

import java.time.LocalDate;

public class DepositCount extends BankAccount {
    LocalDate dateOfAddingMoney;

    @Override
    public void takeMoney(double sumForTaking) {
        LocalDate today = LocalDate.now();
        if (today.minusMonths(1).isBefore(dateOfAddingMoney)) {
            System.out.println("Не прошло месяца после последнего пополнения!");
        } else {
            super.takeMoney(sumForTaking);
        }
    }

    //Как это правильно оформить? Такой override не проходит, когда еще один аргумент в метод добавляю
    public void putMoney(double sumForAdding, LocalDate date) {
        this.dateOfAddingMoney = date;
        super.putMoney(sumForAdding);
        System.out.println("Дата пополнения " + dateOfAddingMoney);
    }
}
