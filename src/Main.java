import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*Здавствуйте! 2 дня пыталась изменить код согласно вашей рекомендации,
но мне не хватает знаний. Пока не могу понять, как всё красиво оформить.
 То, что я сделала, является шагом к правильной реализации?*/

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<OneMonthlyReport>> fileContentsMonth = new HashMap<>();
        HashMap<Integer, ArrayList<YearlyReport>> fileContentsYear = new HashMap<>();


        while (true) {
            switch (PrintMenu()) {
                case 1: //1 - Считать все месячные отчёты
                    fileContentsMonth = ReadingReport.readingMonthlyReport("resources");
                    if (!fileContentsMonth.isEmpty()) {
                        System.out.println("Отчёты успешно загружены.");
                    }
                    break;
                case 2: //2 - Считать годовой отчёт
                    fileContentsYear = ReadingReport.readingYearlyReport("resources");
                    if (!fileContentsYear.isEmpty()) {
                        System.out.println("Отчёты успешно загружены.");
                    }
                    break;
                case 3: //3 - Сверить отчёты
                    if (!fileContentsMonth.isEmpty() && !fileContentsYear.isEmpty()) {
                        CompareReports.CheckingReports(fileContentsYear, fileContentsMonth);
                    } else {
                        System.out.println("Загрузите отчёты.");
                    }
                    break;
                case 4: //4 - Вывести информацию о всех месячных отчётах
                    if (!fileContentsMonth.isEmpty()) {
                        MonthlyReport.printReport(fileContentsMonth);
                    } else {
                        System.out.println("Загрузите отчёты.");
                    }
                    break;
                case 5: //5 - Вывести информацию о годовом отчёте
                    if (!fileContentsYear.isEmpty()) {
                        YearlyReport.printReport(fileContentsYear);
                    } else {
                        System.out.println("Загрузите отчёты.");
                    }
                    break;
                case 0: //выход
                    return;
                default: //все несовпадения
                    System.out.println("Неверная команда.");
                    break;
            }

        }
    }

    private static int PrintMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите действие:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");

        int action = scanner.nextInt(); // Считываем ввод пользователя
        //scanner.close();
        return action;
    }


}

