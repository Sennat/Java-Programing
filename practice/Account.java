
class Account{
		private String cust_name;
		private final double new_balance = 0;
		private int pin = 0000;
		int total = 0;
		
		
		public Account(){
				cust_name = "Unassigned";
				new_balance = 500.00
		}
		
		public String getName(){
				return cust_name
		}
		
		public String setName(String cust_name){
				this.cust_name = cust_name;
		}
		
		public double getBalance(){
				return new_balance
		}
		
		public double setBalance(int new_balance){
				this.new_balance = new_balance;
		}
		
		public deduct(int deduct){
				total = total - deduct;
				return total;
		}
		
		public int add(int add){
				total = total + add;
				return total;
		}
		
		public int menu(){
				char checking = "C";
				char saving = "S";
				char credit = "Cecking";
				
				
		}
}
