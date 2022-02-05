import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    private boolean isExpense;
    private double amount;


    public YearlyReport(boolean isExpense, double amount) {
        this.isExpense = isExpense;
        this.amount = amount;
    }

    public static void printReport(HashMap<Integer, ArrayList<YearlyReport>> fileContents){
        String month = "";

        ArrayList<YearlyReport> itemReport = new ArrayList<>();

        System.out.println("~~~ИНФОРМАЦИЯ ПО ГОДОВОМУ ОТЧЁТУ~~~");

        double averageExpense = 0;  //средний расход
        double averageIncome = 0;   //средний доход

        for(int numberMonth: fileContents.keySet()) {
            month = Month.getMonth(numberMonth);

            double profit = 0;          //прибыль


            itemReport = fileContents.get(numberMonth);    //получаем список расходов/прибыли за месяц
            for (YearlyReport item : itemReport) {
                if (item.isExpense) {                      //если это расход
                    profit -= item.amount;
                    averageExpense += item.amount;
                } else {                                   //если это прибыль
                    profit += item.amount;
                    averageIncome += item.amount;
                }
            }

            System.out.println(month + ", прибыль: " + profit);

        }
        String result = String.format("%.2f",averageIncome/fileContents.size());
        System.out.println("Средний доход за " + fileContents.size() + " месяца: " + result);

        result = String.format("%.2f",averageExpense/fileContents.size());
        System.out.println("Средний расход за " + fileContents.size() + " месяца: " + result);

    }

    public static double getTotalIncome(int month, HashMap<Integer, ArrayList<YearlyReport>> fileContents){
        ArrayList<YearlyReport>  yearlyReportsList = new ArrayList<>();
        yearlyReportsList = fileContents.get(month);
        double amount = 0;
        for( YearlyReport yearlyReports: yearlyReportsList){
            if (!yearlyReports.isExpense) {
                amount = yearlyReports.amount;
                break;
            }
        }
        return amount;
    }

    public static double getTotalExpense(int month, HashMap<Integer, ArrayList<YearlyReport>> fileContents){
        ArrayList<YearlyReport>  yearlyReportsList = new ArrayList<>();
        yearlyReportsList = fileContents.get(month);
        double amount = 0;
        for( YearlyReport yearlyReports: yearlyReportsList) {
            if (yearlyReports.isExpense) {
                amount =  yearlyReports.amount;
                break;
            }
        }
        return amount;
    }
}
