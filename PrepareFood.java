import java.io.*;
import java.util.*;

public class PrepareFood{
	
	public static void checkForDishes(int inputNumbers[], String timeOfDay, HashMap<Integer, String> dishesMap){
		int inputNumbersCount = 0, validNumbers = 0, allowedMultipleDish = 0, sameDishesCount = 1, i = 0;
		TreeMap<Integer, String> resultDishes = new TreeMap<Integer, String>();
		boolean errorPresent = false;
		
		inputNumbersCount = inputNumbers.length;
		if(timeOfDay.equals("morning")){
			validNumbers = 4;
			allowedMultipleDish = 3;
		} else if(timeOfDay.equals("night")){
			validNumbers = 5;
			allowedMultipleDish = 2;
		} else{
			
		}
		
		for(i=1;i<inputNumbersCount;i++){
			if(inputNumbers[i] >= validNumbers){
				resultDishes.put(inputNumbers[i], "error");
				i = inputNumbersCount;
			} 
			else if(inputNumbers[i] == allowedMultipleDish && i!=inputNumbersCount-1 && inputNumbers[i] == inputNumbers[i+1]){
				sameDishesCount++;
			}
			else if(i!=inputNumbersCount-1 && inputNumbers[i] == inputNumbers[i+1]){
				errorPresent = true;
				if(resultDishes.containsKey(inputNumbers[i])){
					
				} else
					resultDishes.put(inputNumbers[i], dishesMap.get(inputNumbers[i]));		
			}
			else{
				if(sameDishesCount > 1) {
					if(!errorPresent){
						resultDishes.put(inputNumbers[i], dishesMap.get(inputNumbers[i])+ "(x"+ sameDishesCount+")");
					}
					sameDishesCount = 1;
				}
				else{
					if(resultDishes.containsKey(inputNumbers[i])){
						errorPresent = true;
					}
					else
						resultDishes.put(inputNumbers[i], dishesMap.get(inputNumbers[i]));	

					if(errorPresent){
						resultDishes.put(validNumbers, "error");
						i = inputNumbersCount;
					}	
				}
			}
		}	

		System.out.println("Output:");	
		for (Map.Entry<Integer, String> entry : resultDishes.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue().toString();
			System.out.print(value+(value == "error"?" ":(key==inputNumbersCount-1?" ":", ")));
		}
	}
	
	public static void main(String args[]){
		int i, j, dishTypesCount, inputCount;
		boolean breakInputLoop = false, inputError = true;
		String timeOfDay, input, inputAgain = " ";
		String inputSplit[];
		Scanner scan;
		HashMap<Integer, String> morningDishes = new LinkedHashMap<Integer, String>();
		HashMap<Integer, String> nightDishes = new LinkedHashMap<Integer, String>();
		
		dishTypesCount = 1;
		i = 1;
		
		morningDishes.put(i, "eggs");
		nightDishes.put(i, "steak");

		morningDishes.put(i+1, "toast");
		nightDishes.put(i+1, "potato");

		morningDishes.put(i+2, "coffee");
		nightDishes.put(i+2, "wine");
		
		morningDishes.put(i+3, "not applicable");
		nightDishes.put(i+3, "cake");
		
		do{
			System.out.println("Enter input here. Format: morning, 1, 2, 3 ");
			scan = new Scanner(System.in);
			input = scan.nextLine();

			inputSplit = input.split(", ");
			
			inputCount = inputSplit.length;
			timeOfDay = inputSplit[0].toLowerCase();
			
			if(input.length() == 0){
				System.out.println("[Input Error] Please enter time of day and the types of dishes you want to eat here. Format: morning, 1, 2, 3 ");
			} 
			else if(!(timeOfDay.equals("morning")|| timeOfDay.equals("night"))){
				System.out.println("[Input Error] Invalid time of day and please enter either morning or night. Or you might have forgot to separate the list of dish types with comma");
			}
			else if(inputCount == 1){
				System.out.println("[Input Error] No types of dishes are entered and please enter them. Format: morning, 1, 2, 3 ");
			}
			else{
				inputError = false;
			}
			
			if(inputError){
				System.out.println("Do you want to try entering input again? [Yes/No]");
				inputAgain = scan.nextLine().toLowerCase();
				inputError = (inputAgain.equals("yes")?true:false);
			}
		} while(inputError);		
		
		if(inputAgain.equals("no")){
			return;
		}
		
		int inputNumbers[] = new int[inputCount];
		for (i = 1; i < inputCount; i++) {
			try{
				inputNumbers[i] = Integer.parseInt(inputSplit[i]);
			} catch(Exception e){
				System.out.println("[Input Error] You might have forgot to separate the list of dish types with comma. Or invalid input");
				return;
			}
		}

		switch(timeOfDay){
			case "morning":
				checkForDishes(inputNumbers, timeOfDay, morningDishes);
			break;
			case "night":
				checkForDishes(inputNumbers, timeOfDay, nightDishes);	
			break;
			default:
			break;
		}
	}
}