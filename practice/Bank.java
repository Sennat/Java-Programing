import java.util.Scanner;

public Bank{
		int digit = 0000;
		
		public static void main(String[] args){
				System.out.println("\t\tWelcome to Development Bank!");
				Scanner ask = new Scanner(System.in);
				
				Syetem.out.print("Enter four digit pin(****): ");
				int in = ask.nextInt();
				
				try{	// Try block to handle code that may cause exception
						digit = Integer.parseInt(in)
							
				}catch (NumberFormatException e) {
						System.out.println("Not an integer number !");
				}
				
		}
}