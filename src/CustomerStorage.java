import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private static final Pattern ADD_MAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?");
    private static boolean isMAIL(String email) {
        return ADD_MAIL.matcher(email).matches();
    }
    private static boolean isPhone(String string) {
        return PHONE_PATTERN.matcher(string).matches();
    }
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new ArrayIndexOutOfBoundsException("Wrong format! Correct format:\n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        } else if (!isMAIL(components[2])){
            throw new IllegalArgumentException("Wrong E-mail format! Correct format:\n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        } else if (!isPhone(components[3])){
            throw new IllegalArgumentException("Wrong phone format! Correct format:\n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        String[] components = name.split("\\s+");
        if (components.length != 2){
            throw new ArrayIndexOutOfBoundsException("Wrong name format! Correct format: \nremove Василий Петров");
        }
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}