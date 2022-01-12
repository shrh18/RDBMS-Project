package helptable;
import java.util.*;
import java.io.*;


public class Insert {
	public void INSERT(String tablename, ArrayList<String> records, ArrayList<Pair> attributes) {
		//System.out.println("Inside INSERT()");
		File f = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
		boolean s_flag = false;
		if(f.exists()){
			if(attributes.isEmpty()) {
				//get attributes from file.
				File sch = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
				try {
					Scanner reader = new Scanner(sch);
					String line = "";
					while(line.indexOf(tablename)!=0) {
						line = reader.nextLine();
					}//now string line has the correct line of schema file
					//finding attributes
					String[] attr = line.split("#");
					for(int r=1;r<attr.length;r+=2) {
						Pair p = new Pair(attr[r],attr[r+1]);
						attributes.add(p);
					}
					//System.out.println(attributes.size());
					reader.close();
				}catch(IOException e) {
					System.out.println("error");
				}
			}
			//System.out.println("file found.");
			
			//checking if record type matches with schema
			//System.out.println(records.size());
			//System.out.println(attributes.size());
			//System.out.println(attributes.get(0).Second());
			for(int i=0;i<records.size();i++){
				//System.out.println(attributes.get(i).Second()+" "+type(records.get(i)));
				if(attributes.get(i).Second().compareTo(type(records.get(i))) == 0) {
					s_flag = true;
				}
				else {
					s_flag = false;
					break;
				}
			}
			if(s_flag){
				//inserting record
				try {
					FileWriter fwr = new FileWriter(f, true);
					for(int i=0;i<records.size();i++) {
						fwr.write(records.get(i)+"#");
					}
					fwr.append('\n');
					fwr.close();
					System.out.println("values inserted.");
				}catch(IOException e){
					System.out.println("table not found");
				}
			}
			else {
				System.out.println("Error.");
			}
			
		}
		else {
			System.out.println("table not found.");
		}
		
	}
	public String type(String str){
		try {
			double d = Double.parseDouble(str);
		}catch(NumberFormatException nfe){
			return "VARCHAR";
		}
		return "INT";
	}

}
