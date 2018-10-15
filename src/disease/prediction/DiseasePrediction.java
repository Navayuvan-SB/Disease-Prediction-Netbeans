package disease.prediction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import java.util.StringTokenizer;

public class DiseasePrediction {

  private static Connection conn;
	
	private static void getConnection() throws   ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","sonayuvan");
		System.out.println("Connected to MySql server");
		
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int[] v = new int[50];
		int i= 0;
		Scanner s = new Scanner(System.in);
		DiseasePrediction.getConnection();	
		System.out.println("1	sweating\r\n" + 
				"2	chills\r\n" + 
				"3	headache\r\n" + 
				"4	muscle aches\r\n" + 
				"5	loss of appetite\r\n" + 
				"6	irritablity\r\n" + 
				"7	weakness\r\n" + 
				"8	cold\r\n" + 
				"9	running nose\r\n" + 
				"10	sneezing\r\n" + 
				"11	fatigue\r\n" + 
				"12	cough\r\n" + 
				"13	fever\r\n" + 
				"14	nausea\r\n" + 
				"15	vomitting\r\n" + 
				"16	diarrhea\r\n" + 
				"17	anemia\r\n" + 
				"18	chest pain\r\n" + 
				"19	swollen lymph nodes\r\n" + 
				"20	eye pain\r\n" + 
				"21	skin rash\r\n" + 
				"22	bleeding gums\r\n" + 
				"23	weight loss\r\n" + 
				"24	oral ulcer\r\n" + 
				"25	night sweats\r\n" + 
				"26	enlarged lymph nodes\r\n" + 
				"27	enlarged armpits\r\n" + 
				"28	rapid heart rate\r\n" + 
				"29	loss of skin elasticiy\r\n" + 
				"30	dry mouth\r\n" + 
				"31	dry throat\r\n" + 
				"32	dry nose\r\n" + 
				"33	dry eyelids\r\n" + 
				"34	low bp\r\n" + 
				"35	thirst\r\n" + 
				"36	red eyes\r\n" + 
				"37	light sensitivity\r\n" + 
				"38	white spots inside the mouth\r\n" + 
				"39	abdominal pain\r\n" + 
				"40	constipation\r\n" + 
				"41	pale stools \r\n" + 
				"42	dark urine\r\n" + 
				"");
		System.out.println("Enter the symptoms number.\nEnter 0 when you finished.");
		do {
			System.out.print("Enter the symptom number:");
			i += 1;
			v[i] =  new Scanner(System.in).nextInt();
		}while(v[i] != 0);
		DiseasePrediction.predict(v,i);
	}
		
	public static void predict(int[] v, int n) throws SQLException {
		
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from main");
		ResultSet rs = ps.executeQuery();
		int[] a = new int [100];
		int h = 0,t = 0;
		int[] r = new int[100];
		while(rs.next()) {
			r[t] = 0;
			StringTokenizer st2 = new StringTokenizer(rs.getString("sym"), ", ");
			while (st2.hasMoreElements()) {
				a[h]=Integer.parseInt(st2.nextToken());
				h += 1;
			}
			for(int i = 1; i < n; i++) {
				for(int j = 0; j <= h; j++) {
					if(v[i] == a[j]) {
						r[t] += 1;
					}
				}
			}
			t += 1;
			h = 0;
	}
	int max = r[0],m = 0;
	for(int i = 0;i < 10;i ++) {
		if(r[i] > max) {
			max = r[i];
			m = i;
		}
	}
	m = m + 1;
	PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("select * from dname");
	ResultSet rs1 = ps1.executeQuery();
	while(rs1.next()) {
		if(rs1.getInt("id") == m) {
			System.out.println(rs1.getString("dname"));
		}
	}
 }
	
}


