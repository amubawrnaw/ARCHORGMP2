import java.util.Scanner;

public class Driver {
	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		String input;
		
		System.out.println("Input format:  n.nnnnn X 10 ^ e");
		System.out.println("            :  n.n\n");
		
		Converter c = new Converter();
		input = sc.nextLine();
		
		c.split(input);
		c.convert();
		System.out.println(c.getAnswer());
	}
}
