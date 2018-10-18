
class Credit extends Account{
		private final double credit_line = 500.00;
		private double balance = 0;
		
		public Credit(){
			balance = credit_line;
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