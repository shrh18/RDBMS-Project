//package one;
import java.util.*;
import helptable.Help_table;
import helptable.Create;
import helptable.Pair;
import helptable.Insert;
import helptable.Drop;
import helptable.Select;
import helptable.Update;
import helptable.Delete;
import helptable.Describe;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class Main {
	static String p = "password123";
	static ArrayList<Pair> attributes = new ArrayList<Pair>();

	public static void main(String[] args) {
		System.out.println("--------- RDBMS BY SHRH18 --------- ");
		System.out.println("Enter password: ");
		Scanner sc = new Scanner(System.in);
		String passwd;
		passwd = sc.nextLine();
		if(passwd.compareTo(p) == 0) {
			//start mysql
			while(true) {
				String query;
				ArrayList<String> command = new ArrayList<String>();
				//ArrayList<pair<String,String>> attributes = new ArrayList<pair<>>();
				//Pair pair = new Pair();
				//ArrayList<Pair> attributes = new ArrayList<Pair>();
				ArrayList<String> values = new ArrayList<String>();
				System.out.print("SQL>");
				query = sc.nextLine();
				
				//------------CREATE TABLE------------
				if(query.indexOf("create table") ==  0) {
					String str = "";
					//char[] q = query.toCharArray();
					for(int i=0;i<query.length();i++) {
						if(query.charAt(i) == ' ') {
							if(str.length() > 0)
								command.add(str);
							str = "";
						}
						else if(query.charAt(i) == '(' || query.charAt(i) == ')' || query.charAt(i) == ';' || query.charAt(i) == ',') {
							if(str.length() > 0)
								command.add(str);
							str = "";
						}
						else {
							str += query.charAt(i);
						}
					}
					/*for(int j=0;j<command.size();j++) {
						System.out.print(" "+command.get(j));
					}*/
					Help_table help = new Help_table();
					ArrayList<String> tbs = help.HELP_TABLE();
					//System.out.println(tbs.size());
					//System.out.println(tbs.get(0));
					for(int j=3;j<command.size();j+=2) {
						//System.out.println(command.get(j));
						Pair p = new Pair(command.get(j),command.get(j+1));
						attributes.add(p);
					}
					//System.out.println(attributes.size());
					boolean flag = false;
					String tb;
					int i = 0;
					if(!help.empty) {
						for(tb=tbs.get(i);i<tbs.size();i++) {
							if(tb.compareTo(command.get(2))== 0) {
								flag = true;
							}
						}
					}
					if(flag)
						System.out.println("table already exists!");
					else {
						Create create = new Create();
						create.CREATE(command.get(2),attributes);
						System.out.println("table created.");
					}
					
					/*for(int j=3;j<command.size();j+=2) {
						//System.out.println(command.get(j));
						Pair p = new Pair(command.get(j),command.get(j+1));
						attributes.add(p);
					}*/
					
					attributes.removeAll(attributes);
				}
				
				//------------INSERT VALUES--------------
				if(query.indexOf("insert into") ==  0) {
					
					
					ArrayList<String> records = new ArrayList<>();
					String str = "";
					for(int i=0;i<query.length();i++) {
						if(query.charAt(i)==' ') {
							if(str.length()>0) 
								values.add(str);
							str = "";
						}
						else if(query.charAt(i) == '(' || query.charAt(i) == ')' || query.charAt(i) == ';' || query.charAt(i) == ',') {
							if(str.length() > 0)
								values.add(str);
							str = "";
						}
						else {
							str += query.charAt(i);
						}
					}
					/*for(int k=0;k<values.size();k++) {
						System.out.print(values.get(k)+" ");
					}*/
					for(int r=4;r<values.size();r++) {
						records.add(values.get(r));
					}
					
					/*for(int k=0;k<records.size();k++) {
					System.out.print(records.get(k)+" ");
					}
					System.out.println();*/
					
					Insert insert = new Insert();
					//System.out.println(values.get(2));
					//System.out.println(attributes.size());
					insert.INSERT(values.get(2), records, attributes);
					attributes.removeAll(attributes);
					
				}
				
				//--------DROP TABLE--------
				if(query.indexOf("drop")==0) {
					String comm="";
					for(int i=0;i<query.length();i++) {
						if(query.charAt(i)==' ' || query.charAt(i)==';') {
							command.add(comm);
							comm = "";
							continue;
						}
						comm += query.charAt(i);
					}
					Drop dro = new Drop();
					//System.out.println("drop command given");
					//System.out.println(command.get(2));
					System.out.println(dro.DROP(command.get(2)));
				}
				
				//---------SELECT VALUES--------
				if(query.indexOf("select")==0) {
					String str = "";
					for(int i=0;i<query.length();i++) {
						if(query.charAt(i)==' ') {
							if(str.length()>0)
								command.add(str);
							str = "";	
						}
						else if(query.charAt(i) == '(' || query.charAt(i) == ')' || query.charAt(i) == ';' || query.charAt(i) == ',') {
							if(str.length() > 0)
								command.add(str);
							str = "";
						}
						else {
							str += query.charAt(i);
						}
					}
					/*for(int k=0;k<command.size();k++) {
					System.out.print(command.get(k)+" ");
					}*/
					//System.out.println(command);
					ArrayList<String> attr_p = new ArrayList<>();
					ArrayList<String> attr_ch = new ArrayList<>();
					
					//adding attributes to respective lists
					int x = command.indexOf("from");
					int y = 1;
					//System.out.println(x);
					while(y < x) {
						attr_p.add(command.get(y));
						y++;
					}
					
					if(query.contains("where")) {
						x = command.indexOf("where");
						x++;
						//System.out.println(x);
						//assuming one condition for now
						attr_ch.add(command.get(x));
						x += 2;
						attr_ch.add(command.get(x));
						
					}
					/*while(x<command.size()) {
						attr_ch.add(command.get(x));
						x++;
					}*/
					
					//System.out.println(attr_p);
					//System.out.println(attr_ch);
					
					int tn = command.indexOf("from");
					//System.out.println(tn);
					tn++;
					String tablename = command.get(tn);
					Select sel = new Select();
					sel.SELECT(attr_p, attr_ch, tablename);
				}
				
				//--------DELETE RECORD---------
				else if(query.indexOf("delete")==0) {
					String str = "";
					for(int i=0;i<query.length();i++) {
						if(query.charAt(i)==' ') {
							if(str.length()>0)
								command.add(str);
							str = "";	
						}
						else if(query.charAt(i) == '(' || query.charAt(i) == ')' || query.charAt(i) == ';' || query.charAt(i) == ',') {
							if(str.length() > 0)
								command.add(str);
							str = "";
						}
						else {
							str += query.charAt(i);
						}
					}
					ArrayList<String> attr_ch = new ArrayList<>();
					int x;
					if(query.contains("where")) {
						x = command.indexOf("where");
						x++;
						//System.out.println(x);
						//assuming one condition for now
						attr_ch.add(command.get(x));
						x += 2;
						attr_ch.add(command.get(x));
					}
					//System.out.println(attr_ch);
					
					Delete del = new Delete();
					del.DELETE(command.get(2), attr_ch);
				}
				//------------ UPDATE RECORD -------------
				else if(query.indexOf("update")==0) {
					String str = "";
					for(int i=0;i<query.length();i++) {
						if(query.charAt(i)==' ') {
							if(str.length()>0)
								command.add(str);
							str = "";	
						}
						else if(query.charAt(i) == '(' || query.charAt(i) == ')' || query.charAt(i) == ';' || query.charAt(i) == ',') {
							if(str.length() > 0)
								command.add(str);
							str = "";
						}
						else {
							str += query.charAt(i);
						}
					}
					
					//System.out.println(command);
					ArrayList<Pair> attr_upd = new ArrayList<>();
					ArrayList<String> attr_ch = new ArrayList<>();
					ArrayList<String> temp = new ArrayList<>();
					temp.add("=");
					
					for(int i=command.indexOf("where")+1;i<command.size();i++) {
						attr_ch.add(command.get(i));
					}
					
					//System.out.println(attr_ch);
					
					command.removeAll(temp);
					//System.out.println(command);
					
					int a;
					for(int i=command.indexOf("set");i<command.indexOf("where");i+=2) {
						a = i+1;
						if(command.get(a).compareTo("where")!=0) {
							Pair pair = new Pair(command.get(a),command.get(a+1));
							attr_upd.add(pair);
						}
					}
					//System.out.println(attr_upd);
					/*for(int i=0;i<attr_upd.size();i++) {
						System.out.println(attr_upd.get(i).First()+" "+attr_upd.get(i).Second());
					}*/
					Update upd = new Update();
					upd.UPDATE(attr_upd, attr_ch, command.get(1));
				}
				
				//----------- DESCRIBE -----------
				else if(query.indexOf("describe")==0) {
					List<String> commands = Arrays.asList(query.split(" |;"));
					//System.out.println(commands);
					
					//System.out.println(commands.get(1));
					Describe desc = new Describe();
					desc.DESCRIBE(commands.get(1));
					
				}
				
				//-----------HELP TABLE----------
				else if(query.compareTo("help tables;")==0){
					File schema = new File("C:/Users/lenovo/Desktop/Academics/TY/Database Managament Systems/PPT GD CP HA/CP 1/schema.txt");
					try{
						Scanner reader = new Scanner(schema);
						String str = "";
						String op[];
						while(reader.hasNextLine()) {
							str = reader.nextLine();
							op = str.split("#");
							System.out.println("-- "+op[0]+" --");
						}
						reader.close();
					}catch(IOException e) {
						System.out.println("error");
					}
				}
				
				//---------HELP CMD-----------
				else if(query.indexOf("help cmd")==0) {
					List<String> commands = Arrays.asList(query.split(" |;"));
					//System.out.println(commands);
					
					switch(commands.get(2)) {
					case "create": 
						System.out.println("--  creates a new table");
						break;
						
					case "drop":
						System.out.println("-- deletes a table");
						break;
						
					case "select":
						System.out.println("-- extracts data from a database");
						break;
						
					case "insert":
						System.out.println("-- inserts new data into a database");
						break;
						
					case "delete":
						System.out.println("-- deletes data from a database");
						break;
						
					case "update":
						System.out.println("--  updates data in a database");
						break;
						
					default:
						System.out.println("invalid input");
					}
				}
				//----------- QUIT -------------
				else if(query.compareTo("quit;")==0) {
					sc.close();
					break;
				}
				//break;
			}//-------END OF LOOOP--------
		}
		else {
			System.out.println("Incorrect Password");
		}
		

	}
}
