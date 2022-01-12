package helptable;
import java.util.*;
import java.io.*;

public class Select {
	public void SELECT(ArrayList<String> attr_p, ArrayList<String> attr_ch, String tablename) {
		boolean flag = false;
		File f = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/"+tablename+".txt");
		if(f.exists())
			flag = true;
		
		if(flag) {
			try {
				String[] op;
				if(attr_p.get(0).compareTo("*")==0) {
					if(attr_ch.size()==0) {
						Scanner reader = new Scanner(f);
						while(reader.hasNextLine()) {
							String line = reader.nextLine();
							op = line.split("#");
							for(int i=0;i<op.length;i++) {
								System.out.print(op[i]+" ");
							}
							System.out.println();
						}
						reader.close();
					}
					else {
						File sch = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
						Scanner s = new Scanner(sch);
						Scanner reader = new Scanner(f);
						//check if input is valid
						//System.out.println(s.nextLine());
						//System.out.println(reader.nextLine());
						List<String> xx = Arrays.asList(s.nextLine().split("#"));
						int x = xx.indexOf(attr_ch.get(0));
						List<String> yy = new ArrayList<>();
						
						while(reader.hasNextLine()) {
							String attribute = reader.nextLine();
							if(attribute.contains(attr_ch.get(1))) {
								yy = Arrays.asList(attribute.split("#"));
							}
						}
						
						int y = yy.indexOf(attr_ch.get(1));
						reader.close();
						
						//int x = s.nextLine().indexOf(attr_ch.get(0));
						//int y = reader.nextLine().indexOf(attr_ch.get(1));
						//System.out.println(x+" "+y);
						if(x == (y*2 + 1)){
							Scanner read = new Scanner(f);
							//System.out.println(read.nextLine());
							//read.reset();
							//System.out.println("valid input");
							while(read.hasNextLine()) {
								String line = read.nextLine();
								//System.out.println(line);
								if(line.contains(attr_ch.get(1))){
									op = line.split("#");
									for(int i=0;i<op.length;i++) {
										System.out.print(op[i]+" ");
									}
									System.out.println();
								}
							}
							read.close();
						}
						else
							System.out.println("Error!");
						s.close();
						//reader.close();
					}
					//reader.close();
				}
				else if(attr_p.size()>0) {
					ArrayList<Integer> set = new ArrayList<>();
					File sch = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
					Scanner sc = new Scanner(sch);
					String line = sc.nextLine();
					String[] sch_line = line.split("#");
					List<String> a = Arrays.asList(sch_line);
					ArrayList<String> aa = new ArrayList<>();
					for(int i=0;i<a.size();i++) {
						if(a.get(i).compareTo("student")!=0 && a.get(i).compareTo("INT")!=0 && a.get(i).compareTo("VARCHAR")!=0) 
							aa.add(a.get(i));
					}
					for(int j=0;j<attr_p.size();j++) {
						set.add(aa.indexOf(attr_p.get(j)));
					}
					sc.close();
					
					if(attr_ch.size()==0) {
						Scanner read = new Scanner(f);
						String rline = "";
						//System.out.println(set);
						while(read.hasNext()) {
							rline = read.nextLine();
							op = rline.split("#");
							for(int i=0;i<op.length;i++) {
								if(set.contains(i)) 
									System.out.print(op[i]+" ");
							}
							System.out.println();
						}
						read.close();
					}
					else {
						Scanner s = new Scanner(sch);
						Scanner reader = new Scanner(f);
						
						List<String> xx = Arrays.asList(s.nextLine().split("#"));
						int x = xx.indexOf(attr_ch.get(0));
						List<String> yy = new ArrayList<>();
						
						while(reader.hasNextLine()) {
							String attribute = reader.nextLine();
							if(attribute.contains(attr_ch.get(1))) {
								yy = Arrays.asList(attribute.split("#"));
							}
						}
						
						int y = yy.indexOf(attr_ch.get(1));
						reader.close();
						if(x == (y*2 + 1)){
							Scanner read = new Scanner(f);
							//System.out.println(read.nextLine());
							//read.reset();
							//System.out.println("valid input");
							while(read.hasNextLine()) {
								String zline = read.nextLine();
								//System.out.println(line);
								if(zline.contains(attr_ch.get(1))){
									op = zline.split("#");
									for(int i=0;i<op.length;i++) {
										if(set.contains(i)) 
											System.out.print(op[i]+" ");
									}
									System.out.println();
								}
							}
							read.close();
						}
						else
							System.out.println("Error!");
						s.close();
					}
				}
				
			}catch(IOException e) {
				System.out.println("error");
			}
			
		}
		else {
			System.out.println("table not found.");
		}
	}
}
