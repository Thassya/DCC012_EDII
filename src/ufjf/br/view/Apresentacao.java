/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.view;

import javax.swing.JOptionPane;
import ufjf.br.dao.ProdutoDAO;
import ufjf.br.dao.iProdutoDAO;
import ufjf.br.modelos.ProdutoTableModel;

/**
 *
 * @author Aline Sotte
 */
public class Apresentacao extends javax.swing.JFrame {

    private static ProdutoTableModel produtoModel;
    private static iProdutoDAO dao;
    private Inserir insere;
    private Relatorio relatorio;
    private Buscar buscar;

    /**
     * Creates new form apresentacao
     */
    public Apresentacao() {
        try {
            produtoModel = new ProdutoTableModel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível criar o produtoModel!", "Erro no TableModel!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            dao = new ProdutoDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível criar o DAO!", "Erro no DAO!", JOptionPane.ERROR_MESSAGE);
        }

        insere = new Inserir(produtoModel,dao);
        buscar = new Buscar(produtoModel,dao);
        relatorio = new Relatorio(produtoModel,dao);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inserirprodutoapr = new javax.swing.JButton();
        pesprodutoapre = new javax.swing.JButton();
        relatorioapre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Bem Vindo ao Comércio Eletrônico ");

        inserirprodutoapr.setText("Inserir Produto");
        inserirprodutoapr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirprodutoaprActionPerformed(evt);
            }
        });

        pesprodutoapre.setText("Pesquisar Produto");
        pesprodutoapre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesprodutoapreActionPerformed(evt);
            }
        });

        relatorioapre.setText("Relatório");
        relatorioapre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioapreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(106, 106, 106))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pesprodutoapre)
                    .addComponent(inserirprodutoapr, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relatorioapre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(249, 249, 249))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inserirprodutoapr, pesprodutoapre, relatorioapre});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(inserirprodutoapr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(pesprodutoapre)
                .addGap(43, 43, 43)
                .addComponent(relatorioapre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inserirprodutoapr, pesprodutoapre, relatorioapre});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesprodutoapreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesprodutoapreActionPerformed
        buscar.setVisible(true);

    }//GEN-LAST:event_pesprodutoapreActionPerformed

    private void inserirprodutoaprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirprodutoaprActionPerformed
        insere.setVisible(true);
    }//GEN-LAST:event_inserirprodutoaprActionPerformed

    private void relatorioapreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioapreActionPerformed
        relatorio.setVisible(true);
    }//GEN-LAST:event_relatorioapreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apresentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton inserirprodutoapr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pesprodutoapre;
    private javax.swing.JButton relatorioapre;
    // End of variables declaration//GEN-END:variables
}
