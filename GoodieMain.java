
import java.io.*;
import java.util.*;

public class GoodieMain {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		String[] itemList = new String[10];
		int [] priceList = new int[10];
		
		String line;
		int i=0;
		int totalNumberOfItems = 0;
		int lowerBound = 0;
		int upperBound = 0;
		int minimumDifference = 99999999;
		
		File file = new File("sample_input.txt");
		FileWriter writer = new FileWriter("sample_output.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		br.readLine();				// SKIP First Line
		
		
		
		while((line = br.readLine()) != null) {			// Read Input
			totalNumberOfItems++;
			String[] temp = line.split(":", 0);
			itemList[i] = temp[0];
			priceList[i++] = convertStringToInteger(temp[1]);
		}
		
		br.close();
		
		sortItems(priceList, itemList);
		
		System.out.println("Number of the employees: ");
		int numberOfEmployees = sc.nextInt();
		
		
		for(i = 0; i + numberOfEmployees - 1 < totalNumberOfItems; i++) {			// Find the bounds
			if( minimumDifference > (priceList[i + numberOfEmployees - 1] - priceList[i])) {
				minimumDifference = (priceList[i + numberOfEmployees - 1] - priceList[i]);
				lowerBound = i;
				upperBound = i + numberOfEmployees - 1;
			}
		}
		
		writer.write("Here the goodies that are selected for distribution are:\n");			//Write to output file
		
		for(i = lowerBound; i <= upperBound; i++) {
			writer.write(itemList[i]+": "+ String.valueOf(priceList[i])+"\n");
		}
		writer.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is "+String.valueOf(priceList[upperBound] - priceList[lowerBound]));
		writer.close();
	}
	
	public static void sortItems(int[] priceList, String[] itemList) {
		
		for(int i = 0; i < priceList.length; i++) {
			for(int j = 1; j<priceList.length - i; j++) {
				if(priceList[j-1] > priceList[j]) {
					int tmp1 = priceList[j-1];
					priceList[j-1] = priceList[j];
					priceList[j] = tmp1;
					String tmp2 = itemList[j-1];
					itemList[j-1] = itemList[j];
					itemList[j] = tmp2;
				}
			}
		}
		
		
		
	}
	public static int convertStringToInteger(String stringValue) {
	    int integerValue = 0, place = 1;
	    for (int i = stringValue.length()-1; i > 0; i--) {
	    	integerValue += (stringValue.charAt(i) - '0') * place;
	    	place *= 10;
	    }
	    return integerValue;
	}

}