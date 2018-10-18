
class Checking extends Account{
		private final double initial_bal = 100.00;
		private double balance = 0;

		public Checking(){
			balance = initial_bal;
		}
		
		public deduct(int deduct){
				balance = balance - deduct;
				return balance;
		}
		
		public int add(int add){
				balance = balance + add;
				return balance;
		}
		
}