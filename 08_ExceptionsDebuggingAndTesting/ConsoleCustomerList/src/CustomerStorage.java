import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws EmailException {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format! Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Pattern patternPhone = Pattern.compile("^\\+7[0-9]{10}$");
        Matcher matcher;
        matcher = patternEmail.matcher(components[2]);
        if (!matcher.matches()) {
            throw new EmailException("Wrong format of email! Correct format: \n" +
                    "vasily.petrov@gmail.com");
        }
        matcher = patternPhone.matcher(components[3]);
        if (!matcher.matches()) {
            throw new PhoneException("Wrong format of phone number! Correct format: \n" +
                    "+79215637722");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}