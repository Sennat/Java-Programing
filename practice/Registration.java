import java.util.Scanner;

class Registration{
		public static void main(String[] args){
				Student student1, student2, student3;
				String name1, name2, name3;
				
				//request etudent's name
				Scanner keyboard = new Scanner(System.in);
				System.out.print("What is your name?  ");
				String askname = keyboard.next();
				
				student1 = new Student();
				student1.setName(askname);
				name1 = student1.getName();
				
				//print out the information
				System.out.print(name1 + " is a student.");
				
		}

}