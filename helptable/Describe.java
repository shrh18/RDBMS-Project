package helptable;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Describe {
	public void DESCRIBE(String tablename) {
		boolean flag = false;
		File f = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
		
		if(f.exists())
			flag = true;
		
		if(flag) {
			File schema = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
			try {
				Scanner read = new Scanner(schema);
				String str = "";
				
				while(read.hasNextLine()) {
					str = read.nextLine();
					if(str.indexOf(tablename)==0) {
						String[] op = str.split("#");
						for(int i=1;i<op.length;i+=2) {
							System.out.println(op[i]+" -- "+op[i+1]);
						}
					}
				}
				read.close();
			}catch(IOException e) {
				System.out.println("error");
			}
			
		}
		else {
			System.out.println("table does not exist.");
		}
	}

}
