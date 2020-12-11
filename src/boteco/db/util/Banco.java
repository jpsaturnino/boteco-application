package boteco.db.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco { // classe Singleton 
    static private Conexao con = new Conexao();
    
    static public boolean conectar() {
        //SERVIDOR DO ELEPHANT CASO A RESTAURAÇÃO FALHE
        /*return con.conectar("jdbc:postgresql://tuffi.db.elephantsql.com/", 
                "ifsjgdzu", "ifsjgdzu", "P-QdnNBdsaWG2HaxZy75stxcIvreMgEO"); 
        */
        return con.conectar("jdbc:postgresql://localhost/", 
                "botecodb", "postgres", "postgres123");
    }
    
    static public Conexao getCon() {
        return con;
    }

    private Banco() {
    
    }

    public static boolean criarBD(String BD){ 
        try{
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url,"postgres","postgres123");

            Statement statement = con.createStatement();
            statement.execute("CREATE DATABASE "+BD+" WITH OWNER = postgres ENCODING = 'UTF8'  "
                                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252'  "
                                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
          }catch(Exception e)
          {  System.out.println(e.getMessage()); return false;}
             return true;               
    }

    public static boolean backup(String arquivo) throws Exception
    {     
       final ArrayList<String> comandos = new ArrayList();     
       comandos.add("bdutil/pg_dump.exe"); 
       comandos.add("--host"); comandos.add("localhost");
       comandos.add("--port"); comandos.add("5432"); comandos.add("--username");     
       comandos.add("postgres");comandos.add("--format");comandos.add("custom");     
       comandos.add("--blobs");comandos.add("--verbose");comandos.add("--file");
       comandos.add(arquivo);  
       comandos.add("botecodb");     
       ProcessBuilder pb = new ProcessBuilder(comandos);     
       pb.environment().put("PGPASSWORD", "postgres123");
       try {     
          final Process process = pb.start();     
          final BufferedReader r = new BufferedReader(     
                          new InputStreamReader(process.getErrorStream()));     
          String line = r.readLine();     
          while (line != null) {     
                System.err.println(line); line = r.readLine();     
          }     
          r.close();     
          process.waitFor();   
          process.destroy();            
          JOptionPane.showMessageDialog(null,"Backup realizado com sucesso!"); 
          return true;
       } catch (Exception e) {     
          JOptionPane.showMessageDialog(null,"Erro na realização do backup.");   
          return false;
       }
    }

    public static boolean restaurar(String arquivo) throws Exception
    {     
       final ArrayList<String> comandos = new ArrayList();     
       comandos.add("bdutil/pg_restore.exe"); comandos.add("-c");     
       comandos.add("--host"); comandos.add("localhost");
       comandos.add("--port"); comandos.add("5432");     
       comandos.add("--username"); comandos.add("postgres");     
       comandos.add("--dbname"); comandos.add("botecodb");    
       comandos.add("--verbose");  
       comandos.add(arquivo);  
       ProcessBuilder pb = new ProcessBuilder(comandos);     
       pb.environment().put("PGPASSWORD", "postgres123");           
       try {     
                final Process process = pb.start();     
                final BufferedReader r = new BufferedReader(     
                    new InputStreamReader(process.getErrorStream()));     
                String line = r.readLine();     
                while (line != null) {     
                   System.err.println(line);     
                   line = r.readLine();     
                }     
                r.close();     
                process.waitFor();   
                process.destroy();
                JOptionPane.showMessageDialog(null,"Restauração com sucesso!"); 
                return true;
       } catch (Exception e) {     
                JOptionPane.showMessageDialog(null,"Erro na restauração.");   
                return false;
        }
    }
    
}