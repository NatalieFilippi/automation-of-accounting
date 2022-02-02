import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<MonthlyReport>> fileContentsMonth = new HashMap<>();
        HashMap<Integer, ArrayList<YearlyReport>> fileContentsYear = new HashMap<>();


        while (true) {
            switch (PrintMenu()) {
                case 1: //1 - Считать все месячные отчёты
                    fileContentsMonth = ReadingReport.ReadingMonthlyReport();
                    if (!fileContentsMonth.isEmpty()) {
                        System.out.println("Отчёты успешно загружены.");
                    }
                    break;
                case 2: //2 - Считать годовой отчёт
                    fileContentsYear = ReadingReport.ReadingYearlyReport();
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
                        MonthlyReport.PrintReport(fileContentsMonth);
                    } else {
                        System.out.println("Загрузите отчёты.");
                    }
                    break;
                case 5: //5 - Вывести информацию о годовом отчёте
                    if (!fileContentsYear.isEmpty()) {
                        YearlyReport.PrintReport(fileContentsYear);
                    } else {
                        System.out.println("Загрузите отчёты.");
                    }
                    break;
                case 0: //выход
                    return;
                default: //все несовпадения
                    break;
            }

        }
    }

    public static int PrintMenu() {
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

    public static String getMonth(int number){
        switch (number) {
            case 1:
                return "Январь";
            case 2:
                return "Февраль";
            case 3:
                return "Март";
            case 4:
                return "Апрель";
            case 5:
                return "Май";
            case 6:
                return "Июнь";
            case 7:
                return "Июль";
            case 8:
                return "Август";
            case 9:
                return "Сентябрь";
            case 10:
                return "Октябрь";
            case 11:
                return "Ноябрь";
            case 12:
                return "Декабрь";
            default:
                return "Неизвестный месяц";
        }
    }
}

