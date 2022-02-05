import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {


    public static void printReport(HashMap<Integer, ArrayList<OneMonthlyReport>> fileContents){
        String month = "";

        ArrayList<OneMonthlyReport> itemReport = new ArrayList<>();

        System.out.println("~~~ИНФОРМАЦИЯ ПО МЕСЯЦАМ~~~");

        for(int numberMonth: fileContents.keySet()) {
            month = Month.getMonth(numberMonth);
            double maxSaleSum = 0;
            String maxSaleProduct = "";
            double expenseSum = 0;
            String maxExpense = "";
            itemReport = fileContents.get(numberMonth);              //получаем список расходов/прибыли за месяц
            for (OneMonthlyReport item : itemReport) {
                if (item.getIsExpense()) {                                    //если это расход
                    if (item.getQuantity()*item.getSum_of_one() > expenseSum) {    //сравним с текущей максимальной тратой
                        expenseSum = item.getQuantity()*item.getSum_of_one();      //запомним, если превышает
                        maxExpense = item.getItemName();
                    }
                } else {                                                 //если это прибыль
                    if (item.getQuantity()*item.getSum_of_one() > maxSaleSum) {    //сравним с текущим максимально прибыльным товаром
                        maxSaleSum = item.getQuantity()*item.getSum_of_one();      //запомним, если превышает
                        maxSaleProduct = item.getItemName();
                    }
                }
            }

            System.out.println(month + ":");
            System.out.println("Самый прибыльный товар: " + maxSaleProduct + ", сумма " + maxSaleSum);
            System.out.println("Самая большая трата: " + maxExpense + ", сумма " + expenseSum);
        }


    }

    public static double getTotalIncome(int month, HashMap<Integer, ArrayList<OneMonthlyReport>> fileContents){
        double TotalIncome = 0;
        ArrayList<OneMonthlyReport>  monthlyReportsList = new ArrayList<>();
        monthlyReportsList = fileContents.get(month);
        for( OneMonthlyReport monthlyReports: monthlyReportsList){
            if (!monthlyReports.getIsExpense()) {
                TotalIncome += monthlyReports.getQuantity() * monthlyReports.getSum_of_one();
            }
        }
        return TotalIncome;
    }

    public static double getTotalExpense(int month, HashMap<Integer, ArrayList<OneMonthlyReport>> fileContents){
        double TotalExpense = 0;
        ArrayList<OneMonthlyReport>  monthlyReportsList = new ArrayList<>();
        monthlyReportsList = fileContents.get(month);
        for( OneMonthlyReport monthlyReports: monthlyReportsList){
            if (monthlyReports.getIsExpense()) {
                TotalExpense += monthlyReports.getQuantity() * monthlyReports.getSum_of_one();
            }
        }
        return TotalExpense;
    }
}
