import java.io.File;
import java.io.IOException;

import Check.FileComponent;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			FileComponent read = new FileComponent(new File("C:\\Users\\zhang\\Desktop\\code\\gradle\\src\\main\\java\\Bloaters\\TestClass.java"));
			read.type();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
