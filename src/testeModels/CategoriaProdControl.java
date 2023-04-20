package testeModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import testeConect.Conexao;

/**
 *
 * @author Rafael
 */
public class CategoriaProdControl {
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private String sql_cadastra = "INSERT INTO tb_prod_categoria (\n"
        + "cat_id ,\n"
        + "cat_descricao"      
        + ")\n"
        + " VALUES (?, ?)\n";
    
    private String sql_altera = "UPDATE tb_prod_categoria SET \n"
        + " cat_descricao = ? " //1
        + " WHERE cat_id = ?"; //2
    
    private String sql_delete = "DELETE FROM tb_prod_categoria WHERE cat_id = ?";

    private String sql_busca_id = "SELECT * FROM tb_prod_categoria cat_id = ?";

    private String sql_lista = "SELECT * FROM tb_prod_categoria";
    
    public Boolean cadastrar(CategoriaProdBean bean) {
        return cadastrar(bean, Conexao.getConnPublic(), true);
    }
    
     public Boolean cadastrar(CategoriaProdBean bean, Connection conn, Boolean commit) {
         int regs = 0;
        try {
            pstm = conn.prepareStatement(sql_cadastra);

            pstm.setInt(1, bean.getId());
            pstm.setString(2, bean.getDescricao());
 
           regs = pstm.executeUpdate();;
            if (commit) {
                conn.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return regs > 0;
    }
     
     public boolean alterar(CategoriaProdBean bean) {
        return alterar(bean, Conexao.getConnPublic(), true);
   }

    public boolean alterar(CategoriaProdBean bean, Connection conn, Boolean commit) {
        int regs = 0;
        try {
            pstm = conn.prepareStatement(sql_altera);
           
          
            pstm.setString(1, bean.getDescricao());
            pstm.setInt(2, bean.getId());
            
            regs = pstm.executeUpdate();
            if (commit) {
                conn.commit();
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return regs > 0;
    }
    
    public boolean excluir(long id) {
        return excluir(id, Conexao.getConnPublic(), true);
    }

    public boolean excluir(long id, Connection conn, Boolean commit) {
        int regs = 0;
        try {
            pstm = conn.prepareStatement(sql_delete);
            pstm.setLong(1, id);

            regs = pstm.executeUpdate();
            if (commit) {
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regs > 0;
    }
    
    public CategoriaProdBean buscaPorId(Long idContrato, Connection conn) {
        CategoriaProdBean bean = null;

        try {
            pstm = conn.prepareStatement(sql_busca_id);
            pstm.setLong(1, idContrato);
            rs = pstm.executeQuery();
            if (rs.next()) {
                bean = preencheBean(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bean;
    }
    
    public List<CategoriaProdBean> listar() {
         return listar(Conexao.getConnPublic());
    }

    public List<CategoriaProdBean> listar(Connection conn) {
        List<CategoriaProdBean> lista = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(sql_lista);
            rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(preencheBean(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    public List<CategoriaProdBean> listarSQL(String sql) {
        return listarSQL(sql, Conexao.getConnPublic());
    }

    public List<CategoriaProdBean> listarSQL(String sql, Connection conn) {
        List<CategoriaProdBean> lista = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(preencheBean(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    private CategoriaProdBean preencheBean(ResultSet rs) {
        CategoriaProdBean bean = new CategoriaProdBean();
        try {
            bean.setId(rs.getInt("cat_id"));
            bean.setDescricao(rs.getString("cat_descricao"));
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bean;
    }
    
    public Integer buscaUltimoCodigo(){
        return buscaUltimoCodigo(Conexao.getConnPublic());
    }
    
    
    public Integer buscaUltimoCodigo(Connection conn){
        ResultSet rs;
        String sql = "SELECT max(cat_id) as codigo FROM tb_prod_categoria";
        try{
        pstm = conn.prepareStatement(sql);
        rs = pstm.executeQuery();
        if(rs.next()){
            return rs.getInt("codigo") + 1;
        }
        } catch(Exception e) {
        }
        return 1;
    }
    
}
