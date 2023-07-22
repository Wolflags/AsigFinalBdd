package logico;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;

public class Principal {

	public static void main(String[] args) {
		Connection con = ConexionDB.getConnection();
		if (con != null) {
		    System.out.println("Conexión exitosa");
		} else {
		    System.out.println("Error en la conexión");
		}
		
		
        

	}

}
