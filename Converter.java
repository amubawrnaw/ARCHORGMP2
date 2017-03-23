import java.util.ArrayList;
import java.util.Arrays;

public class Converter {
	private int exponent;
	private int ePrime;
	private int sign;
	private String ePrimeBinary;
	private String decimal;
	private String finalAnswer;
	private String mantissa;
	private String firstDigit;
	private String SBit;
	private String CBit;
	private String ECBit;
	private String MCBit;
	
	public Converter() {
		finalAnswer = "";
		decimal = "";
		exponent = 0;
		ePrime = 101;
		sign = 0;
		mantissa = "";
		ePrimeBinary = "";
	}
	
	public String getAnswer() { return finalAnswer; }
	public String getMantissa() { return mantissa; }
	public String getEPrimeBinary() { return ePrimeBinary; }
	public int getExponent() { return exponent; }
	
	private void assembleAnswer() {
		finalAnswer += SBit;
		finalAnswer += " ";
		finalAnswer += CBit;
		finalAnswer += " ";
		finalAnswer += ECBit;
		finalAnswer += " ";
		finalAnswer += MCBit;
		finalAnswer += " ";
	}
	
	private void Normalize(Double number) {
		//moves decimal point to the right if it has decimal portion
		while (number % 1 != 0) {
			exponent--;
			number *= 10;
		}
		
		int num = number.intValue();
		mantissa = Integer.toString(num);
		
		while(mantissa.length() < 7) {
			mantissa = "0" + mantissa;
		}
		
		//need to check if sobra sa 7 D:
	}
	
	private boolean isNegative(char sign) {
		if (sign == '-') {
			sign = 1;
			return true;
		}
		return false;			
	}
	
	private boolean isNotEmpty(String[] a, String[] b) {
		if (a.length == 2 || b.length == 2) {
			return true;
		}
		return false;
	}
	
	private void parseExponent(String expression) {
		exponent = Integer.parseInt(expression.substring(3));
	}
	
	private String[] returnArray(String[] a, String[] b) {
		if (a.length == 2)
			return a;
		return b;
	}
	
	public void split(String expression) {		
		if (isNegative(expression.charAt(0))) {
			expression = expression.substring(1);
		}
		
		String[] inputx = expression.split("x");
		String[] inputX = expression.split("X");
		String[] input;
		
		if(isNotEmpty(inputx, inputX)) {
			input = returnArray(inputx, inputX);
			parseExponent(input[1]);
		} else {
			input = new String[1]; 
			input[0] = expression;
		}
		
		Normalize(Double.parseDouble(input[0]));
	}
	
	private void computeEPrime() {
		ePrime += exponent;
	}
	
	private void computeCBit() {
		if (mantissa.charAt(0) == '8' || mantissa.charAt(0) == '9') {
			// format = 11e'e'm
		} else {
			// format = e'e'mmm
		}
	}
	
	private void ePrimeToBinary() {
		ePrimeBinary = Integer.toBinaryString(ePrime);
		if (ePrimeBinary.length() < 8) {
			ePrimeBinary = "0" + ePrimeBinary;
		}
		//need to check if sobra sa 8 D:
		//special cases pls
		//Lord have mercy
	}
	
	public void checkSign() {
		SBit = Integer.toString(sign);
	}
	
	public void checkECBit() {
		ECBit = ePrimeBinary.substring(2);
	}
	
	public void checkCBit() {
		if (firstDigit.length() == 3) {
			CBit = ePrimeBinary.substring(0, 2) + firstDigit;
		} else {
			CBit = "11" + ePrimeBinary.substring(0, 2) + firstDigit;
		}
	}
	
	public void convert() {
		checkSign();
		computeEPrime();
		ePrimeToBinary();
		checkECBit();
		checkFirstDigit();
		ArrayList<String> BCD = convertMantissaBCD();
		MCBit = convertMantissa(BCD);
		checkCBit();
		/*
		mantissa = toDenselyPacked(mantissa);
		computeCBit();
		//computeECBit
		//computeMCBit
		
		*/
		assembleAnswer();
	}
	
	public String convertMantissa(ArrayList<String> BCD) {
		ArrayList<String> packedBCD = new ArrayList<String>();
		for (int i = 0; i < BCD.size(); i++) {
			packedBCD.add(toDenselyPacked(BCD.get(i)));
		}
		return packedBCD.get(0) + packedBCD.get(1);
	}
	
	public void checkFirstDigit() {
		char first = cutFirst();
		if (isSmall(first)) {
			firstDigit = smallFirstDigit(first);
		} else {
			firstDigit = bigFirstDigit(first);
		}
	}
	
	public boolean isSmall(char first) {
		if (first == 0 || first == 1 || first == 2 || first == 3 || first == 4 || first == 5 || first == 6 || first == 7)
			return true;
		return false;
	}
	
	public ArrayList<String> convertMantissaBCD() {
		String a = mantissa.substring(0, 3), b = mantissa.substring(3);
		String aBCD = "", bBCD = "";
		ArrayList<String> BCD = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			aBCD = aBCD + toBCD(a.charAt(i));
			bBCD = bBCD + toBCD(b.charAt(i));
		}
		BCD.add(aBCD);
		BCD.add(bBCD);
		return BCD;
	}
	
	public String toBCD(char a) {
		switch(a) {
		case '0': return "0000";
		case '1': return "0001";
		case '2': return "0010";
		case '3': return "0011";
		case '4': return "0100";
		case '5': return "0101";
		case '6': return "0110";
		case '7': return "0111";
		case '8': return "1000";
		default: return "1001";
		}
	}
	
	public char cutFirst() {
		char c = mantissa.charAt(0);
		mantissa = mantissa.substring(1);
		return c;
	}
	
	public String bigFirstDigit(char first) {
		switch(first) {
		case '8': return "0";
		default: return "1";
		}
	}
	
	public String smallFirstDigit(char first) {
		switch(first) {
		case '0': return "000";
		case '1': return "001";
		case '2': return "010";
		case '3': return "011";
		case '4': return "100";
		case '5': return "101";
		case '6': return "110";
		default: return "111";
		}
	}
	
	//recieves string with length of 12 to be converted to 10
	public String toDenselyPacked(String s) {
		char[] arr = new char[10];

		//convert string to array of int
		char a = s.charAt(0);
		char b = s.charAt(1);
		char c = s.charAt(2);
		char d = s.charAt(3);
		char e = s.charAt(4);
		char f = s.charAt(5);
		char g = s.charAt(6);
		char h = s.charAt(7);
		char i = s.charAt(8);
		char j = s.charAt(9);
		char k = s.charAt(10);
		char m = s.charAt(11);

		if(a == '1') {
			if(e == '1') {
				if(i == '1') //111
					arr = new char[]{'0','0',d,'1','1',h,'1','1','1',m};
				else //110
					arr = new char[]{j,k,d,'0','0',h,'1','1','1',m};
			} else {
				if(i == '1') //101
					arr = new char[]{f,g,d,'0','1',h,'1','1','1',m};
				else//100
					arr = new char[]{j,k,d,f,g,h,'1','1','0',m};
			}
		} else {
			if(e == '1') {
				if(i == '1') //011
					arr = new char[]{b,c,d,'1','0',h,'1','1','1',m};
				else//010
					arr = new char[]{b,c,d,j,k,h,'1','0','1',m};
			} else {
				if(i == '1') //001
					arr = new char[]{b,c,d,f,g,h,'1','0','0',m};
				else //000
						arr = new char[]{b,c,d,f,g,h,'0',j,k,m};
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int l = 0 ; l < 10 ; l++) {
			sb.append(arr[l]);
		}

		return sb.toString();
	}
		
	private char quadToHex(String s){
		switch(s){
			case "0000": return '0';
			case "0001": return '1';
			case "0010": return '2';
			case "0011": return '3';
			case "0100": return '4';
			case "0101": return '5';
			case "0110": return '6';
			case "0111": return '7';
			case "1000": return '8';
			case "1001": return '9';
			case "1010": return 'A';
			case "1011": return 'B';
			case "1100": return 'C';
			case "1101": return 'D';
			case "1110": return 'E';
			default: return 'F';
			//case "1111": return 'F';
		}
	}
}
