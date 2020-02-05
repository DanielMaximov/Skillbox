package ru.skillbox.module_07_01.task01.LambdaExpressions.src;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    private static String staffFile = "/Users/administrator/Desktop/Java/Skillbox-git/module_07_01/src/ru/skillbox/module_07_01/task01/LambdaExpressions/data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();
        Collections.sort(staff, ((o1, o2) -> {
            int result = o1.getSalary().compareTo(o2.getSalary());
            if (result != 0) {
                return result;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }));

        for (Employee employee : staff) {
            System.out.println(employee);
        }
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}