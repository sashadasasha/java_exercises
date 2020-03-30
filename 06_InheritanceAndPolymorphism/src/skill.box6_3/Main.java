package skill.box6_3;

public class Main {
    public static void main(String[] args) {
        PrivatePersonClient client = new PrivatePersonClient(1345678);
        client.depositMoney(10500.0);
        System.out.println("Номер счета клиента - " + client.getCount());
        client.getMoneyFromCount(3200.50);

        System.out.println("------------");

        LegalPersonClient legalClient = new LegalPersonClient(3254656);
        System.out.println("Номер счета клиента - " + legalClient.getCount());
        legalClient.depositMoney(80000.0);
        legalClient.getMoneyFromCount(24000.0);

        System.out.println("------------");

        SoleTraderClient soleClient = new SoleTraderClient(7894556);
        System.out.println("Номер счета клиента - " + soleClient.getCount());
        soleClient.depositMoney(100000.0);


    }


}
