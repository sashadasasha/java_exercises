package skill.box61;

import java.time.LocalDate;

public class DepositCount extends BankAccount {
    private LocalDate dateOfAddingMoney;

    @Override
    public double takeMoney(double sumForTaking) {
        LocalDate today = LocalDate.now();
        if (today.minusMonths(1).isBefore(getDateOfAddingMoney())) {
            System.out.println("Не прошло месяца после последнего пополнения!");
            return getAccountMoney();
        } else {
            return super.takeMoney(sumForTaking);
        }
    }

    @Override
    public void putMoney(double sumForAdding) {
        this.setDateOfAddingMoney(LocalDate.now());
        super.putMoney(sumForAdding);
        System.out.println("Дата пополнения " + getDateOfAddingMoney());
    }

    public LocalDate getDateOfAddingMoney() {
        return dateOfAddingMoney;
    }

    public void setDateOfAddingMoney(LocalDate dateOfAddingMoney) {
        this.dateOfAddingMoney = dateOfAddingMoney;
    }
}
