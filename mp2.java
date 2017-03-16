//Bornales, Amiel James
//Tan, Julianne Agatha
//Yuvallos, Jonn Ralge
//ARCHORg mp 

import java.util.*;
public class mp2{
	private String finalAnswer;
	public mp2(){
		finalAnswer = "";
	}
	public String getFinalAnswer(){
		return finalAnswer;
	}
	public void appendToAnswer(String s){
		finalAnswer = finalAnswer + s;
	}

	public void baseToDecimal(String[] str){
		System.out.println("baseToDecimal recieved: " + str[0] + " " + str[1]);
		String splitDec[] = str[0].split(".");
		//check if the decimal part of the input is only .0
		boolean isZero = true;
		//traverse the decimal part, if it is not 0, isZero set to false
		for(int i = 0 ; i < splitDec[1].length() ; i++){
			if(splitDec[1].charAt(i) != '0')
				isZero = false;
		}
		if(isZero){

		}
		else{

		}



	}
	public void decToBinary(Double d){
		System.out.println("decToBinary received: " + d);
		StringBuilder sb = new StringBuilder();


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
			case "1101": return 'E';
			case "1110": return 'D';
			case "1111": return 'F';
		}
	}
	//recieves a 32 bit 
	public void binaryToHex(String s){
		System.out.println("binaryToHex received: " + s);
		
		for(int i = 0 ; i < s.length() ; i ++){

		}
		String s;
		//traverse the entire binary string and create a hex string based on it
		for(int i = 0 ; i < 32 ; i+=4){
			 
		}


	}
	//recieves string with length of 12 to be converted to 10
	public String toDenselyPacked(String s){
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

		if(a == '1'){
			if(e == '1'){
				if(i == '1'){//111
					arr = new char[]{'0','0',d,'1','1',h,'1','1','1',m};
				}
				else{//110
					arr = new char[]{j,k,d,'0','0',h,'1','1','1',m};
				}
			}
			else{
				if(i == '1'){//101
					arr = new char[]{f,g,d,'0','1',h,'1','1','1',m};
				}
				else{//100
					arr = new char[]{j,k,d,f,g,h,'1','1','0',m};
				}
			}
		}
		else{
			if(e == '1'){
				if(i == '1'){//011
					arr = new char[]{b,c,d,'1','0',h,'1','1','1',m};
				}
				else{//010
					arr = new char[]{b,c,d,j,k,h,'1','0','1',m};
				}
			}
			else{
				if(i == '1'){//001
					arr = new char[]{b,c,d,f,g,h,'1','0','0',m};
				}
				else{//000
					arr = new char[]{b,c,d,f,g,h,'0',j,k,m};
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int l = 0 ; l < 10 ; l++){
			sb.append(arr[l]);
		}

		return sb.toString();
	}


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Input format:  n.nnnnn X 10 ^ e");
		System.out.println("            :  n.n\n");

		String input;
		mp2 m = new mp2();
		
		input = sc.nextLine();

		String s = m.toDenselyPacked(input);
		System.out.println(input + "\n" + s);

		//if input is a negative, append 1 as the sign bit
		if(input.charAt(0) == '-'){
			m.appendToAnswer("1");
			input.replaceAll("-", "");
		}
		else{
			m.appendToAnswer("0");
		}
		
		//splits the input to 2 parts, if split was not successful, user's input is only decimal 
		String[] split = input.split("X");
		String[] split2 = input.split("x");
		if(split.length == 2 || split2.length == 2)
			m.baseToDecimal(split);
		else 
			m.decToBinary(Double.parseDouble(input));
	}
}