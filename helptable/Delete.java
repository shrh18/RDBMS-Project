package helptable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Delete {
	public void DELETE(String tablename, ArrayList<String> attr_ch) {
		boolean flag = false;
		File f = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
		if(f.exists())
			flag = true;
		if(flag) {
			String op[];
			String sch[];
			ArrayList<String> aa = new ArrayList<>();
			boolean attribute_flag = false;
			try {
				Scanner reader = new Scanner(f);
				String line = "";
				line = reader.nextLine();
				op = line.split("#");
				File schema = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
				Scanner read = new Scanner(schema);
				while(read.hasNextLine()) {
					String tempLine = "";
					tempLine = read.nextLine();
					if(tempLine.indexOf(tablename) == 0) {
						sch = tempLine.split("#");
						List<String> a = Arrays.asList(sch);
						for(int i=0;i<a.size();i++) {
							if(a.get(i).compareTo("student")!=0 && a.get(i).compareTo("INT")!=0 && a.get(i).compareTo("VARCHAR")!=0 ) {
								aa.add(a.get(i));
							}
						}
					}
				}
				int x = aa.indexOf(attr_ch.get(0));
				//reader.close();
				read.close();
				reader.close();
				
				//deleting a line from tablename file
				File temp = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/temp.txt");
				
				try {
					temp.createNewFile();
					Scanner imprt = new Scanner(f);
					FileWriter nsw = new FileWriter(temp, true);
					
					String read_line="";
					
					while(imprt.hasNextLine()) {
						read_line = imprt.nextLine();
						op = read_line.split("#");
						//System.out.println(read_line);
						if(op[x].compareTo(attr_ch.get(1))!=0) {
							//System.out.println(op[x]+" "+attr_ch.get(1));
							nsw.write(read_line);
							nsw.append('\n');	
							attribute_flag = false;
						}
						else {
							attribute_flag = true;
						}
					}
					//add condition to check whether attribute exists or not
					nsw.close();
					imprt.close();
					f.delete();
					temp.renameTo(f);
				}catch(IOException e) {
					System.out.println("error");
				}

			}catch(IOException e) {
				System.out.println("error");
			}
			
			//System.out.println(attribute_flag);
			if(attribute_flag)
				System.out.println("Record deleted.");	
			else
				System.out.println("Record not found.");		
		}
		else {
			System.out.println("table not found.");
		}
	}
}
