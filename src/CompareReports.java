import java.util.ArrayList;
import java.util.HashMap;

public class CompareReports {
    public static void CheckingReports(HashMap<Integer, ArrayList<YearlyReport>> yearlyReport, HashMap<Integer, ArrayList<OneMonthlyReport>> monthlyReport){

        for (int i: yearlyReport.keySet()){
            if ((MonthlyReport.getTotalIncome(i, monthlyReport) == YearlyReport.getTotalIncome(i, yearlyReport)) && (MonthlyReport.getTotalExpense(i, monthlyReport) == YearlyReport.getTotalExpense(i, yearlyReport))){
                System.out.println(i + " ок");
            } else {
                System.out.println("Обнаружено несоответствие в следующем месяце: " + Month.getMonth(i));
            }
        }
    }
}
