package skill.box61;

public class BankAccount {

    private double accountMoney;

    public double takeMoney(double sumForTaking) {
        if (sumForTaking > getAccountMoney()) {
            System.out.println("Недостаточно средств на счете");
            return accountMoney;
        } else {
            setAccountMoney(getAccountMoney() - sumForTaking);
            return accountMoney;
        }
    }

    public void putMoney(double sumForAdding) {

        if (sumForAdding <= 0) {
            System.out.println("Некорректная сумма для добавления");
        } else {
            setAccountMoney(getAccountMoney() + sumForAdding);
        }
    }

    public double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(double accountMoney) {
        this.accountMoney = accountMoney;
    }
}