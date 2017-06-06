import java.util.ArrayList;

public class TransactionSummary extends Receipt{

	@Override
	public String printReceipt(String name, int actNum, ArrayList<String> types, ArrayList<Float> amounts, float total, int day, int month, int year){
		String complete;
		DateUtility.setCalendar(day, month, year);
		complete = "+--------------------------------------+\n"
				+ "|      Java Bank ATM Receipt           |\n"
				+ "|      " + DateUtility.getDayOfWeek() + ", " + DateUtility.getMonth(month) + " " + String.valueOf(day) + ", " + String.valueOf(year) + "\n"
				+ "|      ATM Location # 123              |\n"
				+ "|                                      |\n"
				+ "|                                      |\n"
				+ "|      Account Number:      " + String.valueOf(actNum) + "    |\n"
				+ "|      Customer:     " + name + "    |\n";
		
		for(int i = 0; i < types.size();i++){
			complete += "|      Transaction Type:    " + types.get(i) + "    |\n"
				+ "|      Transaction Amount:  " + NumberUtility.toCurrency((float) amounts.get(i)) + "    |\n";
		}		
				
				
		complete += "|      Account Balance:   " + NumberUtility.toCurrency(total) + "    |\n"
				+ "|                                      |\n"
				+ "|      Thank you for banking with us   |\n"
				+ "|            Have a coffee day         |\n"
				+ "|                                      |\n"
				+ "+--------------------------------------+\n";	
				
	
		return complete;		
	}

}
