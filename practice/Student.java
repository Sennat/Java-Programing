class Student{
		//student member
		private String studentname;
		
		//constructor - initializes the student member
		public Student(){
				studentname = "Unknown";
		}
		
		//methods for the name studant name
		public String getName(){
				return studentname;
		}
		
		//assign the name of studant name
		public void setName(String name){
				studentname = name;
		}

}