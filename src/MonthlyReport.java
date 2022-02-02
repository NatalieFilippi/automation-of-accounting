import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    int numberMonth;
    String itemName;
    boolean isExpense;
    int quantity;
    double sum_of_one;

    public MonthlyReport(int month, String item_Name, boolean is_Expense, int count, double sum) {
        numberMonth = month;
        itemName = item_Name;
        isExpense = is_Expense;
        quantity = count;
        sum_of_one = sum;
    }


    public static void PrintReport(HashMap<Integer, ArrayList<MonthlyReport>> fileContents){
        String month = "";

        ArrayList<MonthlyReport> itemReport = new ArrayList<>();

        System.out.println("~~~ИНФОРМАЦИЯ ПО МЕСЯЦАМ~~~");

        for(int numberMonth: fileContents.keySet()) {
            month = Main.getMonth(numberMonth);
            double maxSaleSum = 0;
            String maxSaleProduct = "";
            double expenseSum = 0;
            String maxExpense = "";
            itemReport = fileContents.get(numberMonth);              //получаем список расходов/прибыли за месяц
            for (MonthlyReport item : itemReport) {
                if (item.isExpense) {                                    //если это расход
                    if (item.quantity*item.sum_of_one > expenseSum) {    //сравним с текущей максимальной тратой
                        expenseSum = item.quantity*item.sum_of_one;      //запомним, если превышает
                        maxExpense = item.itemName;
                    }
                } else {                                                 //если это прибыль
                    if (item.quantity*item.sum_of_one > maxSaleSum) {    //сравним с текущим максимально прибыльным товаром
                        maxSaleSum = item.quantity*item.sum_of_one;      //запомним, если превышает
                        maxSaleProduct = item.itemName;
                    }
                }
            }

            System.out.println(month + ":");
            System.out.println("Самый прибыльный товар: " + maxSaleProduct + ", сумма " + maxSaleSum);
            System.out.println("Самая большая трата: " + maxExpense + ", сумма " + expenseSum);
        }


    }

    public static double getTotalIncome(int month, HashMap<Integer, ArrayList<MonthlyReport>> fileContents){
        double TotalIncome = 0;
        ArrayList<MonthlyReport>  monthlyReportsList = new ArrayList<>();
        monthlyReportsList = fileContents.get(month);
        for( MonthlyReport monthlyReports: monthlyReportsList){
            if (!monthlyReports.isExpense) {
                TotalIncome += monthlyReports.quantity * monthlyReports.sum_of_one;
            }
        }
        return TotalIncome;
    }

    public static double getTotalExpense(int month, HashMap<Integer, ArrayList<MonthlyReport>> fileContents){
        double TotalExpense = 0;
        ArrayList<MonthlyReport>  monthlyReportsList = new ArrayList<>();
        monthlyReportsList = fileContents.get(month);
        for( MonthlyReport monthlyReports: monthlyReportsList){
            if (monthlyReports.isExpense) {
                TotalExpense += monthlyReports.quantity * monthlyReports.sum_of_one;
            }
        }
        return TotalExpense;
    }
}
