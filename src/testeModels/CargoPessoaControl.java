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
public class CargoPessoaControl {
    private PreparedStatement pstm;
    private ResultSet rs;
    
    private String sql_cadastra = "INSERT INTO tb_cargo (\n"
        + "car_id ,\n"
        + "car_descricao"      
        + ")\n"
        + " VALUES (?, ?)\n";
    
    private String sql_altera = "UPDATE tb_cargo SET \n"
        + " car_descricao = ? "
        + " WHERE car_id = ?";
    
    private String sql_delete = "DELETE FROM tb_cargo WHERE car_id = ?";

    private String sql_busca_id = "SELECT * FROM tb_cargo car_id = ?";

    private String sql_lista = "SELECT * FROM tb_cargo";
    
    public Boolean cadastrar(CargoPessoaBean bean) {
        return cadastrar(bean, Conexao.getConnPublic(), true);
    }
    
     public Boolean cadastrar(CargoPessoaBean bean, Connection conn, Boolean commit) {
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
     
     public boolean alterar(CargoPessoaBean bean) {
        return alterar(bean, Conexao.getConnPublic(), true);
   }

    public boolean alterar(CargoPessoaBean bean, Connection conn, Boolean commit) {
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
    
    public CargoPessoaBean buscaPorId(Long idContrato, Connection conn) {
        CargoPessoaBean bean = null;

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
    
    public List<CargoPessoaBean> listar() {
         return listar(Conexao.getConnPublic());
    }

    public List<CargoPessoaBean> listar(Connection conn) {
        List<CargoPessoaBean> lista = new ArrayList<>();
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
    public List<CargoPessoaBean> listarSQL(String sql) {
        return listarSQL(sql, Conexao.getConnPublic());
    }

    public List<CargoPessoaBean> listarSQL(String sql, Connection conn) {
        List<CargoPessoaBean> lista = new ArrayList<>();
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
    private CargoPessoaBean preencheBean(ResultSet rs) {
        CargoPessoaBean bean = new CargoPessoaBean();
        try {
            bean.setId(rs.getInt("car_id"));
            bean.setDescricao(rs.getString("car_descricao"));
    
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
        String sql = "SELECT max(car_id) as codigo FROM tb_cargo";
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
