package userInterfaceTestbed;

public class sample {
	public static String measuredValueErrorMessage = "";	
	public static String measuredValueInput = "";		
	public static int measuredValueIndexofError = -1;	
	private static int state = 0;				
	private static int nextState = 0;			
	private static boolean finalState = false;		
	private static String inputLine = "";			
	private static char currentChar;			
	private static int currentCharNdx;			
	private static boolean running;				

	private static String displayInput(String input, int currentCharNdx) {
		String result = input + "\n";
		for (int ndx=0; ndx < currentCharNdx; ndx++) result += " ";
		return result + "\u21EB";
	}

	private static void displayDebuggingInfo() {
		if (currentCharNdx >= inputLine.length())
			System.out.println(((state < 10) ? "  " : " ") + state + ((finalState) ? "       F   " : "           ") + "None");
		else
			System.out.println(((state < 10) ? "  " : " ") + state + ((finalState) ? "       F   " : "           ") + "  " + currentChar + " " + ((nextState < 10) && (nextState != -1) ? "    " : "   ") + nextState );
	}

	private static void moveToNextCharacter() {
		currentCharNdx++;
		if (currentCharNdx < inputLine.length())
			currentChar = inputLine.charAt(currentCharNdx);
		else {
			currentChar = ' ';
			running = false;
		}
	}

	public static String checkMeasuredValue(String input) {
		if(input.length() <= 0) return "";
		state = 0;						
		inputLine = input;					
		currentCharNdx = 0;					
		currentChar = input.charAt(0);		
		measuredValueInput = input;			
		running = true;						
		System.out.println("\nCurrent Final Input  Next\nState   State Char  State");
		while (running) {
			switch (state) {
			case 0: 
				finalState = false;
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 1;
					break;
				}
				else if (currentChar == '.') {
					nextState = 3;
					break;					
				}
				else 
					running = false;
				
				break;
			
			case 1: 
				finalState = true;
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 1;
					break;
				}
				else if (currentChar == '.') {
					nextState = 2;
					break;
				}
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}
				else
					running = false;
				break;			
				
			case 2: 
				finalState = true;
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 2;
					break;
				}
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}
				else 
					running = false;
				break;
	
			case 3: 
				finalState = true;
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 3;
					break;
				}
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 4;
					break;
				}
				else 
					running = false;
				break;

			case 4: 
				break;
}
			
			if (running) {
				displayDebuggingInfo();
				moveToNextCharacter();
				state = nextState;

			}
		}

		System.out.println("The loop has ended.");
		measuredValueIndexofError = currentCharNdx;
		switch (state) {
		case 0:
			measuredValueIndexofError = currentCharNdx;		
			measuredValueErrorMessage = "The first character must be a digit or a decimal point.";
			return "The first character must be a digit or a decimal point.";

		case 1:
			if (currentCharNdx<input.length()) {
				measuredValueErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "+ "a \".\", or it must be the end of the input.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				measuredValueErrorMessage = "";
				return measuredValueErrorMessage;
			}

		case 2:
		case 4:
			if (currentCharNdx<input.length()) {
				measuredValueErrorMessage = "This character may only be an \"E\", an \"e\", or it must"+ " be the end of the input.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				measuredValueErrorMessage = "";
				return measuredValueErrorMessage;
			}
		case 3:
		case 6:
			measuredValueErrorMessage = "This character may only be a digit.\n";
			return measuredValueErrorMessage + displayInput(input, currentCharNdx);

		case 7:
			if (currentCharNdx<input.length()) {
				measuredValueErrorMessage = "This character may only be a digit.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				measuredValueErrorMessage = "";
				return measuredValueErrorMessage;
			}
		case 5:
			measuredValueErrorMessage = "This character may only be a digit, a plus, or minus "+ "character.\n";
			return measuredValueErrorMessage + displayInput(input, currentCharNdx);
		default:
			return "";
		}
	}
}

