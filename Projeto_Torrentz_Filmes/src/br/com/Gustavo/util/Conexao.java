package br.com.Gustavo.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    private static Connection conexao = null;
    
    public static Connection getConexao() throws Exception {
        if(conexao == null){
        
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://192.168.0.105:5432/Miniaturas";
            String user = "postgres";
            String password = "comandos46";
                  
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);  
        }
            return conexao;
        
        }
    }
            

