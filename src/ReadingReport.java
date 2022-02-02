import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadingReport {
    public static String pathYearlyReport = "y.2021";
    public static String directory = "resources";
    public static String pathMonthlyReport = "m.2021";

    public static HashMap<Integer, ArrayList<MonthlyReport>> ReadingMonthlyReport() {

        String path = pathMonthlyReport; //путь к файлами
        String content = "";  //сюда прочитаю файл
        String filePath = ""; //переменная для полного пути к файл
        HashMap<Integer, ArrayList<MonthlyReport>> monthlyReportHash = new HashMap<>(); //создаём хэш-таблицу, где ключ-месяц, значение - список объектов
        for (int i=1; i<4; i++) {

            filePath = getPath(i, path);

            content = readFileContentsOrNull(filePath);         //получили содержимое файла
            String[] lines = content.split("\\n");        //разобрали на строки

            int numberMonth = 0; //инициализация
            boolean isExpense;
            int quantity =0;
            double sum = 0;
            ArrayList<MonthlyReport> monthlyReportList = new ArrayList<>();

            for (int k = 1; k < lines.length; k++) {
                String[] lineContents = lines[k].split(",");  //разделила строку по запятым
                String itemName = lineContents[0];                  //запомнила имя
                numberMonth = i;                                    //месяц
                isExpense = Boolean.parseBoolean(lineContents[1]);  //флажок расхода/прихода
                quantity = Integer.parseInt(lineContents[2]) ;      //количество товара
                sum = Double.parseDouble(lineContents[3]);          //суммма за единицу
                monthlyReportList.add(new MonthlyReport(numberMonth,itemName, isExpense, quantity, sum)); //в списке создала новую запись
            }
            monthlyReportHash.put(i, monthlyReportList);

        }
        return monthlyReportHash; //readFileContentsOrNull(filePath);
    }

    public static HashMap<Integer, ArrayList<YearlyReport>> ReadingYearlyReport() {
        String path = pathYearlyReport;
        String content = "";  //сюда прочитаю файл
        HashMap<Integer, ArrayList<YearlyReport>> yearlyReportHash = new HashMap<>(); //создаём хэш-таблицу, где ключ-месяц, значение - список объектов

        content = readFileContentsOrNull(path);             //получили содержимое файла
        String[] lines = content.split("\\n");        //разобрали на строки

        int month = 0; //инициализация
        boolean isExpense;
        double amount = 0;

        for (int k = 1; k < lines.length; k++) {
            String[] lineContents = lines[k].split(",");  //разделила строку по запятым
            month = Integer.parseInt(lineContents[0]) ;         //месяц
            amount = Integer.parseInt(lineContents[1]) ;        //количество товара
            isExpense = Boolean.parseBoolean(lineContents[2]);  //флажок расхода/прихода
            ArrayList<YearlyReport> yearlyReportList = new ArrayList<>();
            if (yearlyReportHash.containsKey(month)) {          //если хэш-таблица содержит данные по месяцу
                yearlyReportList = yearlyReportHash.get(month); //получаем список, добавляем в него новую запись
                yearlyReportList.add(new YearlyReport(isExpense, amount)); //в списке создала новую запись
            } else {                                            //если данных о месяце еще нет
                yearlyReportList.add(new YearlyReport(isExpense, amount)); //в списке создала новую запись
                yearlyReportHash.put(month, yearlyReportList);
            }

        }

        return yearlyReportHash;
    }

    private static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(directory, path + ".csv"));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private static String getPath(int number, String path)
    {
        String month = ""; //подставим месяц
        if (number<10) {
            month = "0" + number;
        } else {
            month = String.valueOf(number);
        }
        return path + month; //получили полный путь файла
    }
}
