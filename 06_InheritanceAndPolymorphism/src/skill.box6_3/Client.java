package skill.box6_3;

abstract class Client {
    private final int COUNT;
    private double countBalance;

    public Client(int count) {
        this.COUNT = count;
    }
    public void depositMoney(double ammount) {
        if (ammount <= 0) {
            System.out.println("Некорректная сумма для добавления");
        } else {
            countBalance += ammount;
            System.out.printf("Вы внесли %.2f рублей%n", ammount);
        }
    }

    public double getMoneyFromCount (double sum) {
        if (sum > countBalance) {
            System.out.println("Недостаточно средств на счете");
        } else {
            countBalance -= sum;
            System.out.printf("Сумма снятия составила %.2f рублей%n", sum);
            System.out.printf("Остаток на счете %.2f рублей%n", countBalance);
        }
        return countBalance;
    }

    public int getCount() {
        return COUNT;
    }
}
