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
public class ProdutosControl {

    private PreparedStatement pstm;
    private ResultSet rs;

    private String sql_cadastra = "INSERT INTO tb_produtos (\n"
            + "prod_id ,\n"
            + "prod_nome ,\n"
            + "prod_cod_barra ,\n"
            + "prod_unidade ,\n"
            + "prod_validade,\n"
            + "prod_id_categoria"
            + ")\n"
            + " VALUES (?, ?, ?, ?, ?, ?)\n";

    private String sql_altera = "UPDATE tb_produtos SET \n"
            + "  prod_nome = ?,\n"
            + "  prod_cod_barra = ?,\n"
            + "  prod_unidade = ?,\n"
            + "  prod_validade = ?,\n"
            + "  prod_id_categoria = ?"
            + " WHERE prod_id = ?";

    private String sql_delete = "DELETE FROM tb_produtos WHERE prod_id = ?";

    private String sql_busca_id = "SELECT * FROM tb_produtos prod_id = ?";

    private String sql_lista = "SELECT * FROM tb_produtos";

    public Boolean cadastrar(ProdutosBean bean) {
        return cadastrar(bean, Conexao.getConnPublic(), true);
    }

    public Boolean cadastrar(ProdutosBean bean, Connection conn, Boolean commit) {
        int regs = 0;
        try {
            pstm = conn.prepareStatement(sql_cadastra);

            pstm.setInt(1, bean.getId());
            pstm.setString(2, bean.getNome());
            pstm.setLong(3, bean.getCodBarras());
            pstm.setString(4, bean.getUnidade());
            pstm.setString(5, bean.getValidade());
            pstm.setInt(6, bean.getPro_categoria());

            regs = pstm.executeUpdate();;
            if (commit) {
                conn.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return regs > 0;
    }

    public boolean alterar(ProdutosBean bean) {
        return alterar(bean, Conexao.getConnPublic(), true);
    }

    public boolean alterar(ProdutosBean bean, Connection conn, Boolean commit) {
        int regs = 0;
        try {
            pstm = conn.prepareStatement(sql_altera);

            pstm.setString(1, bean.getNome());
            pstm.setLong(2, bean.getCodBarras());
            pstm.setString(3, bean.getUnidade());
            pstm.setString(4, bean.getValidade());
            pstm.setInt(5, bean.getPro_categoria());
            pstm.setInt(6, bean.getId());

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

    public ProdutosBean buscaPorId(Long idContrato, Connection conn) {
        ProdutosBean bean = null;

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

    public List<ProdutosBean> listar() {
        return listar(Conexao.getConnPublic());
    }

    public List<ProdutosBean> listar(Connection conn) {
        List<ProdutosBean> lista = new ArrayList<>();
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

    public List<ProdutosBean> listarSQL(String sql) {
        return listarSQL(sql, Conexao.getConnPublic());
    }

    public List<ProdutosBean> listarSQL(String sql, Connection conn) {
        List<ProdutosBean> lista = new ArrayList<>();
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

    private ProdutosBean preencheBean(ResultSet rs) {
        ProdutosBean bean = new ProdutosBean();
        try {
            bean.setId(rs.getInt("prod_id"));
            bean.setNome(rs.getString("prod_nome"));
            bean.setCodBarras(rs.getLong("prod_cod_barra"));
            bean.setUnidade(rs.getString("prod_unidade"));
            bean.setDia_de_cadastro(rs.getDate("prod_dia_cadastro"));
            bean.setValidade(rs.getString("prod_validade"));
            bean.setPro_categoria(rs.getInt("prod_id_categoria"));
            try {
                bean.setNome_categoria(rs.getString("cat_descricao"));
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bean;
    }

    public Integer buscaUltimoCodigo() {
        return buscaUltimoCodigo(Conexao.getConnPublic());
    }

    public Integer buscaUltimoCodigo(Connection conn) {
        ResultSet rs;
        String sql = "SELECT max(prod_id) as codigo FROM tb_produtos ";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo") + 1;
            }
        } catch (Exception e) {
        }
        return 1;
    }
}
