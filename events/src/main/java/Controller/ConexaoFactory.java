package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
private static final String USUARIO = "evento";
private static final String SENHA = "$5cz9zK64";
private static final String URL = "jdbc:postgresql://51.222.51.65:5432/events";

public static Connection conectar() throws SQLException{
	
	//Referencia ao Driver para versões antigas do java
	DriverManager.registerDriver(new org.postgresql.Driver());
	
	Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
	return conexao;
}

public static void main(String[] args) {
	try{
	Connection conexao = ConexaoFactory.conectar();
	System.out.println("Conectado com sucesso!!");
	}
	
	catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Conexão falhou!!");
	}
}

}
