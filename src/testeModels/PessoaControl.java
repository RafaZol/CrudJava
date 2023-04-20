package testeModels;

import testeConect.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Rafael
 */

public class PessoaControl {

    private PreparedStatement pstm;
    private ResultSet rs;

    private String sql_cadastra = "INSERT INTO tb_usuarios (\n"
            + "usu_nome,\n"
            + "usu_email,\n"
            + "usu_celular,\n"
            + "usu_documento,\n"
            + "usu_idade,\n"
            + "usu_pass,\n"
            + "usu_cargo"
            + ")\n"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)\n"
            + " RETURNING usu_id";

    private String sql_altera = "UPDATE tb_usuarios SET \n"
            + "  usu_nome = ?,\n"
            + "  usu_email = ?,\n"
            + "  usu_celular = ?,\n"           
            + "  usu_documento = ?,\n"
            + "  usu_idade = ?,\n"
            + "  usu_pass  = ?,\n"
            + "  usu_cargo = ?"
            + " WHERE usu_id = ?";

    private String sql_delete = "DELETE FROM tb_usuarios WHERE usu_id = ?";

    private String sql_busca_id = "SELECT * FROM tb_usuarios usu_id = ?";

    private String sql_lista = "SELECT * FROM tb_usuarios";

    public long cadastrar(PessoaBean bean) {
        return cadastrar(bean, Conexao.getConnPublic(), true);
    }

    public long cadastrar(PessoaBean bean, Connection conn, Boolean commit) {
        try {
            pstm = conn.prepareStatement(sql_cadastra);

            pstm.setString(1, bean.getNome());
            pstm.setString(2, bean.getEmail());
            pstm.setString(3, bean.getCelular());
            pstm.setString(4, bean.getDocumento());
            pstm.setInt(5, bean.getIdade());
            pstm.setString(6, bean.getPass());
            pstm.setInt(7, bean.getCargo());

            

            ResultSet rs = pstm.executeQuery();
            if (commit) {
                conn.commit();
            }
            if (rs.next()) {
                return rs.getLong("usu_id");
            }
        } catch (Exception e) {
            System.out.println("Nenhum Registro Selecionado" +e );
        }
        return 0;
    }

    public boolean alterar(PessoaBean bean) {
        return alterar(bean, Conexao.getConnPublic(), true);
   }

    public boolean alterar(PessoaBean bean, Connection conn, Boolean commit) {
        int regs = 0;
        try {
            pstm = conn.prepareStatement(sql_altera);
           
          
            pstm.setString(1, bean.getNome());
            pstm.setString(2, bean.getEmail());
            pstm.setString(3, bean.getCelular());
            pstm.setString(4, bean.getDocumento());
            pstm.setInt(5, bean.getIdade());
            pstm.setString(6, bean.getPass());
            pstm.setInt(7, bean.getCargo());
            pstm.setLong(8, bean.getId());


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
            JOptionPane.showMessageDialog(null, "Nenhum Registro selecionado" + e);
        }
        return regs > 0;
    }

    public PessoaBean buscaPorId(Long idContrato, Connection conn) {
        PessoaBean bean = null;

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

    public List<PessoaBean> listar() {
         return listar(Conexao.getConnPublic());
    }

    public List<PessoaBean> listar(Connection conn) {
        List<PessoaBean> lista = new ArrayList<>();
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

    public List<PessoaBean> listarSQL(String sql) {
        return listarSQL(sql, Conexao.getConnPublic());
    }

    public List<PessoaBean> listarSQL(String sql, Connection conn) {
        List<PessoaBean> lista = new ArrayList<>();
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
    
    public boolean verificarCredenciais(String idUsuario, String senha) throws SQLException {
        boolean resultado = false;
        String sql = "SELECT * FROM tb_usuarios WHERE usu_nome = ? AND usu_pass = ?";    
        pstm = Conexao.getConnPublic().prepareStatement(sql);
        pstm.setString(1, idUsuario);
        pstm.setString(2, senha);
        rs = pstm.executeQuery();
        if (rs.next()) {
            System.out.println("sucesso");
            resultado = true;
        }
        rs.close();
        pstm.close();
        return resultado;
    }

    private PessoaBean preencheBean(ResultSet rs) {
        PessoaBean bean = new PessoaBean();
        try {
            bean.setId(rs.getLong("usu_id"));
            bean.setNome(rs.getString("usu_nome"));
            bean.setEmail(rs.getString("usu_email"));
            bean.setCelular(rs.getString("usu_celular"));
            bean.setDiaCadastro(rs.getDate("usu_dia_cadastro"));
            bean.setDocumento(rs.getString("usu_documento"));
            bean.setIdade(rs.getInt("usu_idade"));
            bean.setPass(rs.getString("usu_pass"));
            bean.setCargo(rs.getInt("usu_cargo"));
            try {
                bean.setNome_cargo(rs.getString("car_descricao"));
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bean;
    }

}
