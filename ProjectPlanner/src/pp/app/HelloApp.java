package pp.app;

public class HelloApp {
	public static void main(String[] args) {		
		System.out.println(again());
		
	}
	
	public static String again() {
		System.out.print("Hello ");
	int i = 0; 
	while(i<50) {
	System.out.print("again ");	
	i++;
	}
	return "again";
	}
}


