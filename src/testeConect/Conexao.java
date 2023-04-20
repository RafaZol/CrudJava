package testeConect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author Rafael
 */
public class Conexao {
    private static Connection connPublic;

    public static Connection getConnPublic() {
        try {

            connPublic = Conexao.conectar("postgresql", "localhost", "postgres", "postgres", "new_db", "5432", "");
            connPublic.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connPublic;
    }
    

    public static void setConnPublic(Connection connPublic) {
        Conexao.connPublic = connPublic;
    }

    public static Connection conectar(String tipoConexao, String host, String usuario, String senha, String nomeBD, String porta,
            String auxString) throws SQLException, Exception {
        registrar(tipoConexao);
        String url = "jdbc:" + tipoConexao + "://" + host + ":" + porta + "/" + nomeBD + "?ApplicationName=APILite"
                + ((auxString.isEmpty() ? "" : "&Parameter1=" + auxString));
        Properties props = new Properties();
        props.setProperty("user", usuario);
        props.setProperty("password", senha);
        return DriverManager.getConnection(url, props);
    }

    public static void registrar(String tp) throws ClassNotFoundException {
        if (tp.equals("postgresql")) {
            tp = "org.postgresql.Driver";
        }
        if (tp.equals("mysql")) {
            tp = "com.mysql.jdbc.Driver";
        }
        Class.forName(tp);
    }


}
