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
//import testeRelatorios.RelPessoas;

/**
 *
 * @author Rafael
 */
public class CadCategorias extends javax.swing.JDialog {

    private final CategoriaProdControl categoriaProdControl = new CategoriaProdControl();
    private DefaultTableModel tmConsulta = new DefaultTableModel(null, new String[]{"id", "Descrição"});
    private ListSelectionModel lsmConsulta;
    private List<CategoriaProdBean> categorias;
    private Boolean inicializando = true;

    public CadCategorias() {
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
        tbConsultas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbConsultas.getColumnModel().getColumn(0).setCellRenderer(direita);
        tbConsultas.setAutoCreateRowSorter(true);

        listar();
    }

    private void salvar() {
        if (verificaCampo()) {
            try {
                CategoriaProdBean categoriaProdBean = new CategoriaProdBean();

                categoriaProdBean.setDescricao(tfDescricao.getText());

                tfId.setEnabled(false);

                if (tfId.getText().isEmpty()) {
                    categoriaProdBean.setId(categoriaProdControl.buscaUltimoCodigo());
                    categoriaProdControl.cadastrar(categoriaProdBean);

                } else {

                    categoriaProdBean.setId(Integer.parseInt(tfId.getText()));
                    categoriaProdControl.alterar(categoriaProdBean);
                }

                listar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campo Vazio");
        }
    }

    private void mostrar(List<CategoriaProdBean> list) {
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
                tmConsulta.setValueAt(list.get(i).getDescricao(), i, 1);

            }
        }
    }

    private void listar() {
        try {
            categorias = categoriaProdControl.listar();
            mostrar(categorias);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Pesquisa : " + e.getLocalizedMessage());
        }
    }

    protected void tbConsultaLinhaSelecionada(JTable tb) {
        try {
            if (tb.getSelectedRow() != -1 && !inicializando) {
                int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
                tfId.setText(categorias.get(ls).getId() + "");
                tfDescricao.setText(categorias.get(ls).getDescricao());

            } else {
                tfId.setText("");
                tfDescricao.setText("");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void excluir() {
        if (tbConsultas.getSelectedRow() != -1) {
            int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
            categoriaProdControl.excluir(categorias.get(ls).getId());
        }

        listar();
    }

    private void habilitarCampos() {
        tfDescricao.setEditable(true);
        btSalvar.setEnabled(true);
        btExcluir.setEnabled(true);
    }

    protected void desabilitarCampos() {
        tfDescricao.setEditable(false);
        btSalvar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(false);
        btExcluir.setEnabled(false);
        tbConsultas.requestFocus();

    }

    private boolean verificaCampo() {
        if (tfDescricao.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbConsultas = new javax.swing.JTable();
        tfId = new javax.swing.JTextField();
        tfDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        btRelCadProd = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btNovo = new javax.swing.JButton();
        btDesab = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Categorias");

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

        tfId.setEditable(false);

        jLabel1.setText("Id :");

        jLabel2.setText("Descrição :");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btRelCadProd.setText("Vizualizar Relatório");
        btRelCadProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelCadProdActionPerformed(evt);
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testeTelas/comercio1.png"))); // NOI18N

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
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btSalvar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btExcluir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btAtualizar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btRelCadProd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSair))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfDescricao))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)
                        .addComponent(btDesab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNovo)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btNovo)
                        .addComponent(btDesab)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalvar)
                        .addComponent(btExcluir)
                        .addComponent(btAtualizar)
                        .addComponent(btRelCadProd))
                    .addComponent(btSair))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btRelCadProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelCadProdActionPerformed
        HashMap<String, Object> parameters = new HashMap<>();
        String sql_lista = "SELECT * FROM tb_prod_categoria";

//        new RelPessoas().abrePdf("categoria.jasper", parameters, sql_lista, Conexao.getConnPublic(), "categoria");
    }//GEN-LAST:event_btRelCadProdActionPerformed

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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadCategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btDesab;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btRelCadProd;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbConsultas;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables
}
