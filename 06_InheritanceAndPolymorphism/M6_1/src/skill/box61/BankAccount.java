package skill.box61;

public class BankAccount {

    public double accountMoney;

//    public BankAccount(double money){
//        this.accountMoney = money;
//    }
    public void getMoneyInfo() {
        System.out.printf("Cумма на вашем счете составляет %.2f рублей\n", accountMoney);
    }

    public void takeMoney(double sumForTaking) {
        accountMoney -= sumForTaking;
        System.out.printf("Со счета снято %.2f рублей.\n" +
                "Cумма на вашем счете составляет %.2f\n", sumForTaking, accountMoney);
    }

    public void putMoney(double sumForAdding) {
        accountMoney += sumForAdding;
        System.out.printf("Счет успешно пополнен на %.2f рублей.\n" +
                "Cумма на вашем счете составляет %.2f\n", sumForAdding, accountMoney);
    }
}