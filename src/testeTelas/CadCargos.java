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
import testeModels.CargoPessoaBean;
import testeModels.CargoPessoaControl;
//import testeRelatorios.RelPessoas;

/**
 *
 * @author Rafael
 */
public class CadCargos extends javax.swing.JDialog {

    private final CargoPessoaControl cargoPessoaControl = new CargoPessoaControl();
    private DefaultTableModel tmConsultas = new DefaultTableModel(null, new String[]{"id", "Descrição"});
    private ListSelectionModel lsmConsulta;
    private List<CargoPessoaBean> cargos;
    private Boolean inicializando = true;

    public CadCargos() {
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

        tbConsultas.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbConsultas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbConsultas.getColumnModel().getColumn(0).setCellRenderer(direita);
        tbConsultas.setAutoCreateRowSorter(true);

        listar();
    }

    private void salvar() {
        try {
        verificaCampos();
            CargoPessoaBean cargoPessoaBean = new CargoPessoaBean();

            cargoPessoaBean.setDescricao(tfDescricao.getText());

            tfId.setEnabled(false);

            if (tfId.getText().isEmpty()) {
                cargoPessoaBean.setId(cargoPessoaControl.buscaUltimoCodigo());
                cargoPessoaControl.cadastrar(cargoPessoaBean);

            } else {

                cargoPessoaBean.setId(Integer.parseInt(tfId.getText()));
                cargoPessoaControl.alterar(cargoPessoaBean);
            }

            listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado");
        }
    }

    private void mostrar(List<CargoPessoaBean> list) {
        inicializando = true;
        while (tmConsultas.getRowCount() > 0) {
            tmConsultas.removeRow(0);
        }
        inicializando = false;

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum registro encontrado!");
        } else {
            String[] campos = new String[]{null, null, null, null};
            for (int i = 0; i < list.size(); i++) {
                tmConsultas.addRow(campos);
                tmConsultas.setValueAt(list.get(i).getId(), i, 0);
                tmConsultas.setValueAt(list.get(i).getDescricao(), i, 1);

            }
        }
    }

    private void listar() {
        try {
            cargos = cargoPessoaControl.listar();
            mostrar(cargos);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Pesquisa : " + e.getLocalizedMessage());
        }
    }

    protected void tbConsultaLinhaSelecionada(JTable tb) {
        try {
            if (tb.getSelectedRow() != -1 && !inicializando) {
                int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
                tfId.setText(cargos.get(ls).getId() + "");
                tfDescricao.setText(cargos.get(ls).getDescricao());

            } else {
                tfId.setText("");
                tfDescricao.setText("");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void excluir() {
        verificaCampos();
        if (tbConsultas.getSelectedRow() != -1) {
            int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
            cargoPessoaControl.excluir(cargos.get(ls).getId());
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
        tbConsultas.requestFocus();
    }

    private void verificaCampos() {
        if (tfDescricao.getText().isEmpty() || tfDescricao.getText() == null) {
            JOptionPane.showMessageDialog(null, "Campo Vazio");
            System.exit(0);// melhorar implementação !
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbConsultas = new javax.swing.JTable();
        tfId = new javax.swing.JTextField();
        tfDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        btRelCargos = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btNovo = new javax.swing.JButton();
        btDesab = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Cargos");

        tbConsultas.setModel(tmConsultas);
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

        jLabel2.setText("Cargo :");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btRelCargos.setText("Vizualizar Relatório");
        btRelCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelCargosActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btSalvar)
                                    .addGap(5, 5, 5)
                                    .addComponent(btExcluir)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btAtualizar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btRelCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btSair)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btDesab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btNovo))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btNovo)
                            .addComponent(btDesab))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btRelCargos)
                        .addComponent(btSalvar)
                        .addComponent(btExcluir)
                        .addComponent(btAtualizar))
                    .addComponent(btSair))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btRelCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelCargosActionPerformed
        HashMap<String, Object> parameters = new HashMap<>();
        String sql_lista = "SELECT * FROM tb_cargo";

//        new RelPessoas().abrePdf("cargo.jasper", parameters, sql_lista, Conexao.getConnPublic(), "cargos");
    }//GEN-LAST:event_btRelCargosActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        listar();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btExcluirActionPerformed

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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadCargos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btDesab;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btRelCargos;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbConsultas;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables
}
