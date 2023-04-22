package testeTelas;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import testeConect.Conexao;
import testeModels.CategoriaProdBean;
import testeModels.CategoriaProdControl;
import testeModels.ProdutosBean;
import testeModels.ProdutosControl;
//import testeRelatorios.RelPessoas;

/**
 *
 * @author Rafael
 */
public class CadProdutos extends javax.swing.JDialog {

    private final ProdutosControl produtosControl = new ProdutosControl();
    private final CategoriaProdControl categoriaprodControl = new CategoriaProdControl();
    private DefaultTableModel tmConsulta = new DefaultTableModel(null, new String[]{"id", "Produto", "Validade", "Código de Barras", "Categoria"});
    private ListSelectionModel lsmConsulta;
    private List<ProdutosBean> produtos;
    private Boolean inicializando = true;
    private final List<CategoriaProdBean> listaCategorias;

    public CadProdutos() {
        initComponents();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage("mercado.png");
        this.setIconImage(img);
        desabilitarCampos();

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        tbConsultas.getColumnModel().getColumn(0).setPreferredWidth(20);
        tbConsultas.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbConsultas.getColumnModel().getColumn(2).setPreferredWidth(20);
        tbConsultas.getColumnModel().getColumn(3).setPreferredWidth(200);
        tbConsultas.getColumnModel().getColumn(4).setPreferredWidth(100);

        tbConsultas.getColumnModel().getColumn(0).setCellRenderer(direita);
        tbConsultas.setAutoCreateRowSorter(true);

        listaCategorias = categoriaprodControl.listar();
        cbCategoria.removeAllItems();

        for (int i = 0; i < listaCategorias.size(); i++) {
            cbCategoria.addItem(listaCategorias.get(i).getId() + "-" + listaCategorias.get(i).getDescricao());
        }

        listar();
    }

    private void salvar() {
        if (verificaCampo()) {
            try {
                ProdutosBean produtosBean = new ProdutosBean();

                produtosBean.setValidade(tfValidade.getText());
                produtosBean.setNome(tfNome.getText());
                produtosBean.setCodBarras(Long.parseLong(tfCodBarras.getText()));
                produtosBean.setUnidade(tfUnidade.getText());
                produtosBean.setPro_categoria(listaCategorias.get(cbCategoria.getSelectedIndex()).getId());

                tfId.setEnabled(false);

                if (tfId.getText().isEmpty()) {
                    produtosBean.setId(produtosControl.buscaUltimoCodigo());
                    produtosControl.cadastrar(produtosBean);

                } else {

                    produtosBean.setId(Integer.parseInt(tfId.getText()));
                    produtosControl.alterar(produtosBean);
                }

                listar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Verifique se todos campos foram preenchidos");
        }
    }

    private void mostrar(List<ProdutosBean> list) {
        inicializando = true;
        while (tmConsulta.getRowCount() > 0) {
            tmConsulta.removeRow(0);
        }
        inicializando = false;

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum registro encontrado!");
        } else {
            String[] campos = new String[]{null, null, null, null};
            for (int i = 0; i < list.size(); i++) {
                tmConsulta.addRow(campos);
                tmConsulta.setValueAt(list.get(i).getId(), i, 0);
                tmConsulta.setValueAt(list.get(i).getNome(), i, 1);
                tmConsulta.setValueAt(list.get(i).getValidade(), i, 2);
                tmConsulta.setValueAt(list.get(i).getCodBarras(), i, 3);
                tmConsulta.setValueAt(list.get(i).getNome_categoria(), i, 4);

            }
        }
    }

    private void listar() {
        try {
            produtos = produtosControl.listarSQL("SELECT *\n"
                    + "FROM\n"
                    + "  public.tb_produtos\n"
                    + "  INNER JOIN public.tb_prod_categoria ON (public.tb_produtos.prod_id_categoria = public.tb_prod_categoria.cat_id)");
            mostrar(produtos);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Pesquisa : " + e.getLocalizedMessage());
        }
    }

    protected void tbConsultaLinhaSelecionada(JTable tb) {
        try {
            if (tb.getSelectedRow() != -1 && !inicializando) {
                int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
                tfId.setText(produtos.get(ls).getId() + "");
                tfNome.setText(produtos.get(ls).getNome());
                tfValidade.setText(produtos.get(ls).getValidade());
                tfCodBarras.setText(produtos.get(ls).getCodBarras() + "");
                tfCadastro.setText(produtos.get(ls).getDia_de_cadastro() + "");
                tfUnidade.setText(produtos.get(ls).getUnidade() + "");
                for (int i = 0; i < listaCategorias.size(); i++) {
                    CategoriaProdBean get = listaCategorias.get(i);
                    if (get.getId() == produtos.get(ls).getPro_categoria()) {
                        cbCategoria.setSelectedIndex(i);
                        break;
                    }
                }

            } else {
                tfId.setText("");
                tfNome.setText("");
                tfValidade.setText("");
                tfCodBarras.setText("");
                tfCadastro.setText("");
                tfUnidade.setText("");
                cbCategoria.setSelectedIndex(0);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void excluir() {
        if (tbConsultas.getSelectedRow() != -1) {
            int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
            produtosControl.excluir(produtos.get(ls).getId());
        }

        listar();
    }

    private void habilitarCampos() {
        tfNome.setEditable(true);
        tfCadastro.setEditable(true);
        tfCodBarras.setEditable(true);
        tfUnidade.setEditable(true);
        tfValidade.setEditable(true);
        cbCategoria.setEnabled(true);
        btEnviar.setEnabled(true);
        btExcluir.setEnabled(true);
    }

    protected void desabilitarCampos() {
        tfNome.setEditable(false);
        tfCadastro.setEditable(false);
        tfCodBarras.setEditable(false);
        tfUnidade.setEditable(false);
        tfValidade.setEditable(false);
        btEnviar.setEnabled(false);
        btExcluir.setEnabled(false);
        cbCategoria.setEnabled(false);
        tbConsultas.requestFocus();
    }

    private boolean verificaCampo() {
        if (tfNome.getText().trim().isEmpty() || tfValidade.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfNome = new javax.swing.JTextField();
        btEnviar = new javax.swing.JButton();
        jtNome = new javax.swing.JLabel();
        tfCodBarras = new javax.swing.JTextField();
        btRelProdutos = new javax.swing.JButton();
        jtCodBarras = new javax.swing.JLabel();
        tfCadastro = new javax.swing.JTextField();
        jtUnidade = new javax.swing.JLabel();
        tfValidade = new javax.swing.JTextField();
        jtValidade = new javax.swing.JLabel();
        jtId = new javax.swing.JLabel();
        jtCategoria = new javax.swing.JLabel();
        tfUnidade = new javax.swing.JTextField();
        jtCadastro = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbConsultas = new javax.swing.JTable();
        cbCategoria = new javax.swing.JComboBox<>();
        btExcluir = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btNovo = new javax.swing.JButton();
        btDesab = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");

        btEnviar.setText("Cadastrar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        jtNome.setText("Nome :");

        tfCodBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodBarrasActionPerformed(evt);
            }
        });

        btRelProdutos.setText("Verificar Relatório");
        btRelProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelProdutosActionPerformed(evt);
            }
        });

        jtCodBarras.setText("Código de Barras :");

        tfCadastro.setEditable(false);

        jtUnidade.setText("Unidade :");

        jtValidade.setText("Dia de Validade :");

        jtId.setText("Id :");

        jtCategoria.setText("Categoria :");

        jtCadastro.setText("Dia de Cadastro :");

        tfId.setEditable(false);

        tbConsultas.setModel(tmConsulta);
        tbConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmConsulta = tbConsultas.getSelectionModel();
        lsmConsulta.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (! e.getValueIsAdjusting()){
                    tbConsultaLinhaSelecionada(tbConsultas);
                }
            }
        });
        jScrollPane1.setViewportView(tbConsultas);

        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testeTelas/comercio1.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        btNovo.setText("+");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btDesab.setText("-");
        btDesab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesabActionPerformed(evt);
            }
        });

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtCategoria)
                                .addGap(18, 18, 18)
                                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btEnviar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btExcluir)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btAtualizar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btRelProdutos)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btSair))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btDesab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btNovo))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jtCadastro)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jtValidade)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jtUnidade)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(tfUnidade))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jtId)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jtNome)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jtCodBarras)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfCodBarras))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovo)
                    .addComponent(btDesab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtId)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtCodBarras)
                    .addComponent(tfCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtCadastro)
                    .addComponent(tfCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtValidade)
                    .addComponent(tfValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtUnidade)
                    .addComponent(tfUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCategoria)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEnviar)
                    .addComponent(btRelProdutos)
                    .addComponent(btExcluir)
                    .addComponent(btAtualizar)
                    .addComponent(btSair))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        salvar();
    }//GEN-LAST:event_btEnviarActionPerformed

    private void tfCodBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodBarrasActionPerformed

    private void btRelProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelProdutosActionPerformed
        HashMap<String, Object> parameters = new HashMap<>();
        String sql_lista = "SELECT *\n"
                + "FROM\n"
                + "  public.tb_produtos\n"
                + "  INNER JOIN public.tb_prod_categoria ON (public.tb_produtos.prod_id_categoria = public.tb_prod_categoria.cat_id)";

//        new RelPessoas().abrePdf("produtos.jasper", parameters, sql_lista, Conexao.getConnPublic(), "Produtos");
    }//GEN-LAST:event_btRelProdutosActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        listar();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        habilitarCampos();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btDesabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesabActionPerformed
        desabilitarCampos();
    }//GEN-LAST:event_btDesabActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btDesab;
    private javax.swing.JButton btEnviar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btRelProdutos;
    private javax.swing.JButton btSair;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jtCadastro;
    private javax.swing.JLabel jtCategoria;
    private javax.swing.JLabel jtCodBarras;
    private javax.swing.JLabel jtId;
    private javax.swing.JLabel jtNome;
    private javax.swing.JLabel jtUnidade;
    private javax.swing.JLabel jtValidade;
    private javax.swing.JTable tbConsultas;
    private javax.swing.JTextField tfCadastro;
    private javax.swing.JTextField tfCodBarras;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfUnidade;
    private javax.swing.JTextField tfValidade;
    // End of variables declaration//GEN-END:variables
}
