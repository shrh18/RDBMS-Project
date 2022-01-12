package helptable;
import java.util.*;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
public class Create {
	//ArrayList<Pair> attributes = new ArrayList<Pair>();
	public void CREATE(String tablename, ArrayList<Pair> attributes) {
		File table_file = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
		try {
			//FileWriter fwr = new FileWriter("C:\\Users\\jayma\\eclipse-workspace\\dbms_CP\\schema.txt");
			table_file.createNewFile();
			//fwr.write(tablename+" # ");
			//fwr.close();
			
			FileWriter fwt = new FileWriter("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt", true);
			fwt.write(tablename+"#");
			for(int i=0;i<attributes.size();i++) {
				fwt.write(attributes.get(i).First()+"#"+attributes.get(i).Second()+"#");
			}
			fwt.append('\n');
			fwt.close();
		}catch(IOException e) {
			System.out.println("error has occured");
		}
	}

}
