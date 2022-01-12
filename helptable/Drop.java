package helptable;
import java.io.*;
import java.util.*;

public class Drop {
	public String DROP(String tablename) {
		//System.out.println("Inside DROP()");
		boolean flag = false;
		File f = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
		try {
			Scanner fr = new Scanner(f);
			String line="";
			while(fr.hasNextLine()) {
				line = fr.nextLine();
				if(line.indexOf(tablename)==0) {
					flag = true;
				}
			}
			fr.close();
		}catch(IOException e){
			System.out.println("error.");
		}
		
		if(flag) {
			//System.out.println("table found");
			File d = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
			//proceed to delete table
			File temp = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/temp.txt");
			try {
				temp.createNewFile();
				Scanner sr = new Scanner(f);
				FileWriter nsw = new FileWriter(temp, true);
				String line = "";
				while(sr.hasNextLine()) {
					line = sr.nextLine();
					if(line.indexOf(tablename)!=0) {
						nsw.write(line);
					}
				}
				sr.close();
				nsw.close();
				f.delete();
				d.delete();
				temp.renameTo(f);
			}catch(IOException e) {
				System.out.println("error.");
			}
			return "table deleted.";
		}
		else {
			return "table does not exist";
		}
	}

}
