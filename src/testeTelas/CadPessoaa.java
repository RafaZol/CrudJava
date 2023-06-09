/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeTelas;

//import java.util.ArrayList;
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
import testeModels.PessoaBean;
import testeModels.PessoaControl;
import PessoasTools.StringUtil;
import java.awt.Image;
import java.awt.Toolkit;
import testeModels.CargoPessoaBean;
import testeModels.CargoPessoaControl;
//import testeRelatorios.RelPessoas;

/**
 *
 * @author Rafael
 */
public class CadPessoaa extends javax.swing.JDialog {

    private final PessoaControl pessoaControl = new PessoaControl();
    private final CargoPessoaControl cargoPessoaControl = new CargoPessoaControl();
    private DefaultTableModel tmConsulta = new DefaultTableModel(null, new String[]{"id", "Usuário", "E-mail", "Celular", "Documento", "Cargo"});
    private ListSelectionModel lsmConsulta;
    private List<PessoaBean> usuarios;
    private Boolean inicializando = true;
    private final List<CargoPessoaBean> listaCategorias;

    public CadPessoaa() {
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
        tbConsultas.getColumnModel().getColumn(1).setPreferredWidth(10);
        tbConsultas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbConsultas.getColumnModel().getColumn(3).setPreferredWidth(20);
        tbConsultas.getColumnModel().getColumn(4).setPreferredWidth(20);
        tbConsultas.getColumnModel().getColumn(5).setPreferredWidth(100);

        tbConsultas.getColumnModel().getColumn(0).setCellRenderer(direita);
        tbConsultas.setAutoCreateRowSorter(true);

        listaCategorias = cargoPessoaControl.listar();
        cbCategoria.removeAllItems();

        for (int i = 0; i < listaCategorias.size(); i++) {
            cbCategoria.addItem(listaCategorias.get(i).getId() + "-" + listaCategorias.get(i).getDescricao());
        }

        listar();
    }

    private void habilitarCampos() {
        tfNome.setEditable(true);
        tfCelular.setEditable(true);
        tfDocumento.setEditable(true);
        tfEmail.setEditable(true);
        tfIdade.setEditable(true);
        tfPass.setEditable(true);
        tfPassC.setEditable(true);
        cbCategoria.setEnabled(true);
        btSalvar.setEnabled(true);
        btExcluir.setEnabled(true);
    }

    protected void desabilitarCampos() {
        tfNome.setEditable(false);
        tfCelular.setEditable(false);
        tfEmail.setEditable(false);
        tfDocumento.setEditable(false);
        tfIdade.setEditable(false);
        tfPass.setEditable(false);
        tfPassC.setEditable(false);
        btSalvar.setEnabled(false);
        btExcluir.setEnabled(false);
        cbCategoria.setEnabled(false);
        tbConsultas.requestFocus();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        tfCelular = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        tfDocumento = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        tfIdade = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfPass = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        tfPassC = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbConsultas = new javax.swing.JTable();
        btPdf = new javax.swing.JButton();
        tfId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btExcluir = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btDesab = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de usuario");

        jLabel4.setText("Idade : ");

        tfEmail.setEditable(false);
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });

        lbNome.setText("Nome :");

        tfCelular.setEditable(false);

        tfNome.setEditable(false);
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        tfDocumento.setEditable(false);
        tfDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDocumentoActionPerformed(evt);
            }
        });

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        tfIdade.setEditable(false);

        jLabel1.setText("Email :");

        jLabel2.setText("Celular :");

        jLabel3.setText("CPF/CNPJ :");

        jLabel5.setText("Senha :");

        jLabel6.setText("Senha : ");

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

        btPdf.setText("Vizualizar Relatório");
        btPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPdfActionPerformed(evt);
            }
        });

        tfId.setEditable(false);

        jLabel7.setText("Id :");

        cbCategoria.setEnabled(false);
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        jLabel8.setText("Cargo :");

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

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testeTelas/comercio1.png"))); // NOI18N

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
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btDesab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNovo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEmail))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btSalvar)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(tfPass, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tfPassC, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btPdf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btSair)))))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btDesab)
                            .addComponent(btNovo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPassC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(tfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar)
                    .addComponent(btPdf)
                    .addComponent(btExcluir)
                    .addComponent(btAtualizar)
                    .addComponent(btSair))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDocumentoActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPdfActionPerformed
        HashMap<String, Object> parameters = new HashMap<>();
        String sql_lista = "SELECT *\n"
                + "FROM\n"
                + "  public.tb_usuarios\n"
                + "  LEFT JOIN public.tb_cargo ON (public.tb_usuarios.usu_cargo = public.tb_cargo.car_id)";

//        new RelPessoas().abrePdf("usuarios.jasper", parameters, sql_lista, Conexao.getConnPublic(), "Usuarios");
    }//GEN-LAST:event_btPdfActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

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

    private void salvar() {
        if (verificaCampos()) {
            try {
                PessoaBean pessoaBean = new PessoaBean();
                pessoaBean.setNome(tfNome.getText());
                pessoaBean.setEmail(tfEmail.getText());
                pessoaBean.setCelular(tfCelular.getText());
                pessoaBean.setDocumento(tfDocumento.getText());
                pessoaBean.setIdade(Integer.parseInt(tfIdade.getText()));
                pessoaBean.setCargo(listaCategorias.get(cbCategoria.getSelectedIndex()).getId());
                pessoaBean.setPass(tfPass.getText());
                tfId.setEnabled(false);

                if (tfPass.getText().equals(tfPassC.getText())) {
                    String criptografar;
                    criptografar = StringUtil.Cripto(tfPass.getText());
                    pessoaBean.setPass(criptografar);
                } else {
                    JOptionPane.showMessageDialog(null, "As senha são diferentes");
                }
                if (tfId.getText().isEmpty()) {
                    pessoaControl.cadastrar(pessoaBean);
                } else {

                    pessoaBean.setId(Long.parseLong(tfId.getText()));
                    pessoaControl.alterar(pessoaBean);
                }

                listar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nenhum Registro Selecionado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Verifique se todos campos foram preenchidos");
        }
    }

    private void mostrar(List<PessoaBean> list) {
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
                tmConsulta.setValueAt(list.get(i).getEmail(), i, 2);
                tmConsulta.setValueAt(list.get(i).getCelular(), i, 3);
                tmConsulta.setValueAt(list.get(i).getDocumento(), i, 4);
                tmConsulta.setValueAt(list.get(i).getNome_cargo(), i, 5);

            }
        }
    }

    private void listar() {
        try {
            usuarios = pessoaControl.listarSQL("SELECT *\n"
                    + "FROM\n"
                    + "  public.tb_usuarios\n"
                    + "  LEFT JOIN public.tb_cargo ON (public.tb_usuarios.usu_cargo = public.tb_cargo.car_id)");
            mostrar(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Pesquisa : " + e.getLocalizedMessage());
        }
    }

    protected void tbConsultaLinhaSelecionada(JTable tb) {
        try {
            if (tb.getSelectedRow() != -1 && !inicializando) {
                int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
                tfNome.setText(usuarios.get(ls).getNome());
                tfCelular.setText(usuarios.get(ls).getCelular());
                tfEmail.setText(usuarios.get(ls).getEmail());
                tfDocumento.setText(usuarios.get(ls).getDocumento());
                tfIdade.setText(usuarios.get(ls).getIdade() + "");
                tfId.setText(usuarios.get(ls).getId() + "");
                desabilitarCampos();
                for (int i = 0; i < listaCategorias.size(); i++) {
                    CargoPessoaBean get = listaCategorias.get(i);
                    if (get.getId() == usuarios.get(ls).getCargo()) {
                        cbCategoria.setSelectedIndex(i);
                        break;
                    }
                }

            } else {
                tfNome.setText("");
                tfCelular.setText("");
                tfEmail.setText("");
                tfDocumento.setText("");
                tfIdade.setText("");
                tfId.setText("");
                tfPass.setText("");
                cbCategoria.setSelectedIndex(0);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            //     JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void excluir() {
        if (tbConsultas.getSelectedRow() != -1) {
            int ls = tbConsultas.convertRowIndexToModel(tbConsultas.getSelectedRow());
            pessoaControl.excluir(usuarios.get(ls).getId());
        }

        listar();
    }

    private boolean verificaCampos() {
        if (tfNome.getText().trim().isEmpty() || tfDocumento.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

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
            java.util.logging.Logger.getLogger(CadPessoaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadPessoaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadPessoaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadPessoaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadPessoaa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btDesab;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPdf;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNome;
    private javax.swing.JTable tbConsultas;
    private javax.swing.JTextField tfCelular;
    private javax.swing.JTextField tfDocumento;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfIdade;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfPass;
    private javax.swing.JPasswordField tfPassC;
    // End of variables declaration//GEN-END:variables
}
