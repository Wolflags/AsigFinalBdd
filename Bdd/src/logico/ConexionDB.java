package logico;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class ConexionDB {
	
	
    // Declaramos las variables
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=AsignacionFinal;trustServerCertificate=true";
    
    private static String user = "sa";
    private static String password = "lachencha";
    private static Connection con = null;
    
    private static InetAddress inetAddress;
	static {
	try {
		inetAddress = InetAddress.getLocalHost();
		String pc = inetAddress.getHostName().toString();
		if(!pc.equalsIgnoreCase("DESKTOP-18452EU")) {
			password="05122028";
		}
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return con;
    }
}
