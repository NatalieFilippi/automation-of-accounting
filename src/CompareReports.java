import java.util.ArrayList;
import java.util.HashMap;

public class CompareReports {
    public static void CheckingReports(HashMap<Integer, ArrayList<YearlyReport>> yearlyReport, HashMap<Integer, ArrayList<MonthlyReport>> monthlyReport){

        for (int i: yearlyReport.keySet()){
            if ((MonthlyReport.getTotalIncome(i, monthlyReport) == YearlyReport.getTotalIncome(i, yearlyReport)) && (MonthlyReport.getTotalExpense(i, monthlyReport) == YearlyReport.getTotalExpense(i, yearlyReport))){
                System.out.println(i + " ок");
            } else {
                System.out.println("Обнаружено несоответствие в следующем месяце: " + Main.getMonth(i));
            }
        }
    }
}
