package testeModels;

import java.sql.Date;

/**
 *
 * @author Rafael
 */
public class ProdutosBean {
    private int id;
    private String nome;
    private Long codBarras;
    private String unidade;
    private Date dia_de_cadastro;
    private String validade;
    private int pro_categoria;
    private String nome_categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(Long codBarras) {
        this.codBarras = codBarras;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Date getDia_de_cadastro() {
        return dia_de_cadastro;
    }

    public void setDia_de_cadastro(Date dia_de_cadastro) {
        this.dia_de_cadastro = dia_de_cadastro;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public int getPro_categoria() {
        return pro_categoria;
    }

    public void setPro_categoria(int pro_categoria) {
        this.pro_categoria = pro_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
    
    
}
