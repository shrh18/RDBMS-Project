package helptable;
import java.util.*;
import java.io.File;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.util.Scanner;

public class Help_table {
	File file = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
	public boolean empty = false;
	public ArrayList<String> HELP_TABLE(){
		String tables;
		ArrayList<String> tbs = new ArrayList<String>();
		try {
			file.createNewFile();
			Scanner myReader = new Scanner(file);
			if(file.length() == 0) {
				empty = true;
			}
			while(myReader.hasNextLine()) {
				tables = myReader.nextLine();
				//System.out.println(tables);
				int loc = tables.indexOf("#");
				tbs.add(tables.substring(0, loc));
				}
			myReader.close();
		}catch(IOException e){
			System.out.println("An error occured");
		}
		return tbs;
	}

}
