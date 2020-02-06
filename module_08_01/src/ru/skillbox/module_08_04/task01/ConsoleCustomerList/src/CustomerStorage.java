package ru.skillbox.module_08_04.task01.ConsoleCustomerList.src;

import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");

        if (components.length != 4) {
            throw new IllegalArgumentException("add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        if (!isEmailValid(components[2])) {
            throw new IllegalArgumentException("Wrong email format. \n Correct format: vasya.ivanov@gmail.com");
        }

        if (!isNumberValid(components[3])) {
            throw new IllegalArgumentException("Wrong phone number format. \n Correct format: +79030856765");
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
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    public boolean isEmailValid(String email){
        return email.matches("^[\\w-_.]*[\\w-_.]@[\\w-]+[\\w]+[\\w]*((\\.\\p{Alpha}{2,})+)$");
    }

    public static boolean isNumberValid(String phone) {
        return phone.matches("[\\\\+]{1}\\d+");
    }
}