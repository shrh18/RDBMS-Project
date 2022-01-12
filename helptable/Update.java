package helptable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Update {
	public void UPDATE(ArrayList<Pair> attr_upd, ArrayList<String> attr_ch, String tablename) {
		//System.out.println("inside UPDATE");
		boolean flag = false;
		File f = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
		if(f.exists())
			flag = true;
		
		if(flag) {
			int ctr = 0;
			String[] op;
			ArrayList<String> xx = new ArrayList<>();
			File temp = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/temp.txt");
			File schema = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
			
			try {
				temp.createNewFile();
				Scanner read_table = new Scanner(f);
				Scanner read_sch = new Scanner(schema);
				
				while(read_sch.hasNextLine()) {
					String sch_line = read_sch.nextLine();
					op = sch_line.split("#");
					List<String> a = Arrays.asList(op);
					if(sch_line.indexOf(tablename)==0) {
						for(int i=0;i<a.size();i++) {
							if(a.get(i).compareTo("student")!=0 && a.get(i).compareTo("INT")!=0 && a.get(i).compareTo("VARCHAR")!=0 ) {
								xx.add(a.get(i));
							}
						}
					}
				}
				//System.out.println(attr_ch);
				FileWriter write_temp = new FileWriter(temp, true);
				while(read_table.hasNextLine()) {
					String str = read_table.nextLine();
					op = str.split("#");
					//System.out.println(op);

					//updating records
					if(!attr_ch.isEmpty()) {
						//System.out.println("reading line....");
						
						//------------- CONTAINS "=" -------------
						if(attr_ch.contains("=")) {
							
							//----------- < -----------------
							if(attr_ch.contains("<")) {
								int x = xx.indexOf(attr_ch.get(0));
								int l = attr_ch.size();
								boolean original_f = true;
								if(check_numeric(op[x]) && check_numeric(attr_ch.get(l-1))) {
									if(Integer.parseInt(op[x]) <= Integer.parseInt(attr_ch.get(l-1))) {
										for(int i=0;i<op.length;i++) {
											for(int j=0;j<attr_upd.size();j++) {
												int y = xx.indexOf(attr_upd.get(j).First());
												if(i == y) {
													write_temp.write(attr_upd.get(j).Second()+"#");	
													original_f = false;
												}	
											}
											if(original_f) {
												write_temp.write(op[i]);
											}
											original_f = true;
										}
										write_temp.append('\n');
										ctr++;
										str = "";
									}
								}
								else {
									System.out.println("error");
								}
							}
							
							//--------------- > ------------------
							else if(attr_ch.contains(">")) {
								boolean original_f = true;
								int x = xx.indexOf(attr_ch.get(0));
								int l = attr_ch.size();
								if(check_numeric(op[x]) && check_numeric(attr_ch.get(l-1))) {
									if(Integer.parseInt(op[x]) >= Integer.parseInt(attr_ch.get(l-1))) {
										for(int i=0;i<op.length;i++) {
											for(int j=0;j<attr_upd.size();j++) {
												int y = xx.indexOf(attr_upd.get(j).First());
												if(i == y) {
													write_temp.write(attr_upd.get(j).Second()+"#");	
													original_f = false;
												}	
											}
											if(original_f) {
												write_temp.write(op[i]);
											}
											original_f = true;
										}
										write_temp.append('\n');
										ctr++;
										str = "";
									}
								}
								else {
									System.out.println("error");
								}
								
							}
							
							//------------ CONTAINS ONLY "=" -----------
							else {
								//System.out.println("for only =");
								int x = xx.indexOf(attr_ch.get(0));
								int l = attr_ch.size();
								boolean original_f = true;
								//System.out.println(op[x]+" "+attr_ch.get(l-1));
								if(op[x].compareTo(attr_ch.get(l-1))==0) {
									//System.out.println(str);
									//updating this particular line
									for(int i=0;i<op.length;i++) {
										for(int j=0;j<attr_upd.size();j++) {
											int y = xx.indexOf(attr_upd.get(j).First());
											if(i == y) {
												write_temp.write(attr_upd.get(j).Second()+"#");	
												original_f = false;
											}
										}
										if(original_f) {
											write_temp.write(op[i]+"#");
										}
										original_f = true;
									}
									write_temp.append('\n');
									ctr++;
									str = "";
								}
							}
						}
						
						//------------- DOES NOT CONTAIN "=" -------------
						else {
							
							if(attr_ch.contains("<")) {
								boolean original_f = true;
								int x = xx.indexOf(attr_ch.get(0));
								int l = attr_ch.size();
								if(check_numeric(op[x]) && check_numeric(attr_ch.get(l-1))) {
									if(Integer.parseInt(op[x]) < Integer.parseInt(attr_ch.get(l-1))) {
										for(int i=0;i<op.length;i++) {
											for(int j=0;j<attr_upd.size();j++) {
												int y = xx.indexOf(attr_upd.get(j).First());
												if(i == y) {
													write_temp.write(attr_upd.get(j).Second()+"#");	
													original_f = false;
												}	
											}
											if(original_f) {
												write_temp.write(op[i]+"#");
											}
											original_f = true;
										}
										write_temp.append('\n');
										ctr++;
										str = "";
									}
								}
								else {
									System.out.println("error");
								}
							}
							
							else if(attr_ch.contains(">")) {
								boolean original_f = true;
								int x = xx.indexOf(attr_ch.get(0));
								int l = attr_ch.size();
								if(check_numeric(op[x]) && check_numeric(attr_ch.get(l-1))) {
									if(Integer.parseInt(op[x]) > Integer.parseInt(attr_ch.get(l-1))) {
										for(int i=0;i<op.length;i++) {
											for(int j=0;j<attr_upd.size();j++) {
												int y = xx.indexOf(attr_upd.get(j).First());
												if(i == y) {
													write_temp.write(attr_upd.get(j).Second()+"#");	
													original_f = false;
												}	
											}
											if(original_f) {
												write_temp.write(op[i]);
											}
											original_f = true;
										}
										write_temp.append('\n');
										ctr++;
										str = "";
									}
								}
								else {
									System.out.println("error");
								}
								
							}
						}
						
						//write the rest of the records unchanged
						if(!str.isEmpty()) {
							write_temp.write(str);
							write_temp.write('\n');
							}
					}
				}
				read_table.close();
				read_sch.close();
				write_temp.close();
				f.delete();
				temp.renameTo(f);
			}catch(IOException e) {
				System.out.println("Error.");
			}
			//System.out.println(xx);
			if(ctr>0)
				System.out.println("Updated records successfully.");
			else
				System.out.println("record not found.");
		}
		else {
			System.out.println("table not found.");
		}
	}
	
	public boolean check_numeric(String str) {
		try {
			double d = Double.parseDouble(str);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}

}
