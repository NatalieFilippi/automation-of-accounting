public class OneMonthlyReport {
    private int numberMonth;
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private double sum_of_one;

    public OneMonthlyReport(int numberMonth, String itemName, boolean isExpense, int quantity, double sum_of_one) {
        this.numberMonth = numberMonth;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }

    public boolean getIsExpense(){
        return isExpense;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getSum_of_one(){
        return sum_of_one;
    }

    public String getItemName(){
        return itemName;
    }


}
