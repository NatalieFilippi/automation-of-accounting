import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadingReport {
    public static String pathYearlyReport = "y.2021";
    public static String pathMonthlyReport = "m.2021";

    public static HashMap<Integer, ArrayList<OneMonthlyReport>> readingMonthlyReport(String directory) {

        String path = pathMonthlyReport; //путь к файлами
        String content = "";  //сюда прочитаю файл
        String filePath = ""; //переменная для полного пути к файл
        HashMap<Integer, ArrayList<OneMonthlyReport>> monthlyReportHash = new HashMap<>(); //создаём хэш-таблицу, где ключ-месяц, значение - список объектов

        for (int i=1; i<4; i++) {

            filePath = getPath(i, path);                            //получить название файла с месяцем
            content = readFileContentsOrNull(filePath, directory);  //получить содержимое файла
            if (content != null) {                                  //если нам вернули содержимое файла, работаем дальше
                String[] lines = content.split("\\n");        //разобрать на строки
                ArrayList<OneMonthlyReport> monthlyReportList = new ArrayList<>(); //создать список трат/приходов для текущего месяца

                for (int k = 1; k < lines.length; k++) {
                    String[] lineContents = lines[k].split(",");     //разделила строку по запятым
                    monthlyReportList.add(getLineMonthlyReport(lineContents, i)); //добавить в список новую запись
                }
                monthlyReportHash.put(i, monthlyReportList);        //добавить в мэп новый отчет за новый месяц
            }

        }
        return monthlyReportHash; //readFileContentsOrNull(filePath);
    }

    private static OneMonthlyReport getLineMonthlyReport(String[] line, int numberMonth){
        String itemName = line[0];                          //запомнила имя
        boolean isExpense = Boolean.parseBoolean(line[1]);  //флажок расхода/прихода
        int quantity = Integer.parseInt(line[2]) ;          //количество товара
        double sum = Double.parseDouble(line[3]);           //суммма за единицу
        return new OneMonthlyReport(numberMonth,itemName, isExpense, quantity, sum); //вернём готовенький объект
      }

    public static HashMap<Integer, ArrayList<YearlyReport>> readingYearlyReport(String directory) {
        String path = pathYearlyReport;
        String content = "";  //сюда прочитаю файл
        HashMap<Integer, ArrayList<YearlyReport>> yearlyReportHash = new HashMap<>(); //создаём хэш-таблицу, где ключ-месяц, значение - список объектов

        content = readFileContentsOrNull(path,directory);             //получили содержимое файла
        String[] lines = content.split("\\n");        //разобрали на строки

        for (int k = 1; k < lines.length; k++) {
            String[] lineContents = lines[k].split(",");  //разделила строку по запятым
            addLineYearlyReport(lineContents, yearlyReportHash);
        }

        return yearlyReportHash;
    }

    private static void addLineYearlyReport(String[] line, HashMap<Integer, ArrayList<YearlyReport>> yearlyReportHash) {

        int month = Integer.parseInt(line[0]) ;         //получим месяц

        ArrayList<YearlyReport> yearlyReportList = new ArrayList<>();
        if (yearlyReportHash.containsKey(month)) {          //если хэш-таблица содержит данные по месяцу
            yearlyReportList = yearlyReportHash.get(month); //получаем список, добавляем в него новую запись
            yearlyReportList.add(getLineYearlyReport(line));//в списке создала новую запись
        } else {                                            //если данных о месяце еще нет
            yearlyReportList.add(getLineYearlyReport(line));//в списке создала новую запись
            yearlyReportHash.put(month, yearlyReportList);
        }
    }

    private static YearlyReport getLineYearlyReport(String[] line){
        int amount = Integer.parseInt(line[1]) ;        //количество товара
        boolean isExpense = Boolean.parseBoolean(line[2]);  //флажок расхода/прихода
        return new YearlyReport(isExpense, amount);

    }

    private static String readFileContentsOrNull(String path, String directory) {
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
