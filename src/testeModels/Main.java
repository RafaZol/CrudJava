package testeModels;

import testeConect.Conexao;
import testeTelas.LogPessoa;
import java.sql.Connection;
import javax.swing.WindowConstants;

/**
 *
 * @author Rafael
 */
public class Main {
    public static void main(String[] args) {
        
        Connection conn = Conexao.getConnPublic();
        
        if (conn != null){
            System.out.println("sucesso");
        }
        
        LogPessoa log = new LogPessoa();
      
        log.pack();
        log.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        log.setLocationRelativeTo(null);
        log.setVisible(true);

    }
    
}
