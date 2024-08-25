import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final int fields_num = 6;

    public static void main(String[] args) {
        System.out.println("Введите данные формате (в одну строку, через пробел): Фамилия Имя Отчество датарождения(dd.MM.yyyy) номертелефона(без пробелов и спец. знаков) пол(f|m)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String[] fields = input.split(" ");
        if (fields.length != fields_num) {
            System.err.println("Неверное количество полей, вы ввели " + fields.length + ", а нужно 6!");
        }
        String lastName = fields[0];
        String firstName = fields[1];
        String middleName = fields[2];

        LocalDate dateOfBirth;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            dateOfBirth = LocalDate.parse(fields[3], formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Неверный формат даты!");
            return;
        }

        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(fields[4]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат номера телефона!");
            return;
        }

        String gender = fields[5];
        if (!gender.equals("f") && !gender.equals("m")) {
            System.err.println("Неверный формат пола, ввведите f или m!");
        }

        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lastName+" "+firstName+" "+middleName+" "+dateOfBirth+"   "+phoneNumber+" "+gender+"\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи!" + e.getMessage());
        }
    }
}