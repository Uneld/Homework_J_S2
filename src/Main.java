//1) Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
//Если значение null, то параметр не должен попадать в запрос.
//Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
//2)* Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //T1
        String jsonString = "\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"";
        System.out.println(Task1(jsonString));
        //T2
        int[] array = {6, 8, 3, 123, 5, 4, 1, 2, 0, 9, 7};
        Task2(array);
    }

    private static String Task1(String str) {
        String[] strings = str.trim().replace("\"", "").split(",");
        System.out.println(Arrays.toString(strings));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHERE");
        for (String curStr : strings) {
            if (!curStr.contains("null")) {
                String[] newStrings = curStr.split(":");
                stringBuilder.append(" AND ");
                stringBuilder.append(newStrings[0]).append('=').append('\'').append(newStrings[1]).append('\'');
            }
        }
        return stringBuilder.toString().replaceFirst("AND ", "");
    }

    private static void Task2(int[] array) {
        String pathLog = "log.txt";

        Logger logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(pathLog);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.log(Level.INFO, "Start sort");
        logger.log(Level.INFO, "Array before: " + Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            // внутренний цикл прохода
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
                logger.log(Level.INFO, "Array: " + Arrays.toString(array) + " Iterators: " + "i= " + i + "j= " + j);
            }
        }
        logger.log(Level.INFO, "Array after: " + Arrays.toString(array));
        logger.log(Level.INFO, "End sort");

        fileHandler.close();
        logger.getHandlers()[0].close();
    }
}