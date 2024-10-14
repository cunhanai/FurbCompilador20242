/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.lexico.LexicoFactory;

/**
 *
 * @author anaj2
 */
public class AppUI extends javax.swing.JFrame {

    private String filePath = "";

    /**
     * Creates new form AppUI
     */
    public AppUI() {
        initComponents();

        defineAtalho(buttonNovo, "Novo", KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        defineAtalho(buttonAbrir, "Abrir", KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        defineAtalho(buttonSalvar, "Salvar", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        defineAtalho(buttonCompilar, "Compilar", KeyEvent.VK_F7, 0);
        defineAtalho(buttonEquipe, "Equipe", KeyEvent.VK_F1, 0);
    }

    private void limparAmbiente() {
        limparAreaMensagens();
        editor.setText("");
        barraStatus.setText("");
        filePath = "";
    }

    private void testAreamensagens() {
        if (!areaMensagens.getText().equals("")) {
            areaMensagens.append("\n");
        }
    }

    private void mostrarEquipe() {
        testAreamensagens();

        areaMensagens.append("Equipe: Ana Julia da Cunha, Gabriel Ribas "
                + "Cestari e Thomas Augusto Haskel - 2024");
    }

    private void abrirArquivo() {
        File file = selecionarArquivo();
        if (file != null) {
            String texto = lerArquivo(file);
            limparAmbiente();
            editor.setText(texto);
            atualizarBarraStatus(file);
        }
    }

    private void limparAreaMensagens() {
        areaMensagens.setText("");
    }

    private void atualizarBarraStatus(File file) {
        filePath = file.getAbsolutePath();
        barraStatus.setText(filePath);
    }

    private void salvarArquivo() {
        if (filePath.equals("")) {
            salvarNovoArquivo();
        } else {
            salvarArquivoExistente();
        }

        limparAreaMensagens();
    }

    private void salvarNovoArquivo() {
        File fileSelecionado = selecionarArquivo();

        if (fileSelecionado != null) {
            File file = null;
            if (fileSelecionado.getPath().endsWith(".txt")) {
                file = new File(fileSelecionado.getPath());
            } else {
                file = new File(fileSelecionado.getPath() + ".txt");
            }
            escreverArquivo(file);
            atualizarBarraStatus(file);
        }
    }

    private void salvarArquivoExistente() {
        File file = new File(filePath);
        escreverArquivo(file);
    }

    private File selecionarArquivo() {
        JFileChooser janela = new JFileChooser();

        if (!filePath.equals("")) {
            janela.setCurrentDirectory(new File(filePath));
        }

        janela.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        int result = janela.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return janela.getSelectedFile();
        } else {
            return null;
        }
    }

    private String lerArquivo(File file) {
        String texto = "";
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                texto += scanner.nextLine() + "\r\n";
            }
        } catch (FileNotFoundException e) {
            areaMensagens.setText("Não foi possível abrir o arquivo: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return texto;
    }

    private void escreverArquivo(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(editor.getText());
            writer.close();
        } catch (IOException e) {
            areaMensagens.setText("Não foi possível salvar o arquivo: " + e.getMessage());
        }
    }

    private void copiarAreaTransferencia() {
        String selecionado = editor.getSelectedText();
        copiarAreaTransferencia(selecionado);
    }

    private void copiarAreaTransferencia(String texto) {
        StringSelection selection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    private void colarAreaTransferencia() {
        editor.paste();
    }

    private void recortarAreaTransferencia() {
        String selecionado = editor.getSelectedText();
        copiarAreaTransferencia(selecionado);
        editor.setText(editor.getText().replace(selecionado, ""));
    }

    private void defineAtalho(JButton botao, String nomeAcao, int tecla, int modificadores) {
        // Define o InputMap para o botão
        InputMap inputMap = botao.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(tecla, modificadores), nomeAcao);

        // Define a Action para o botão
        Action acao = new AbstractAction(nomeAcao) {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Chama o método do botão correspondente
                if (botao == buttonNovo) {
                    buttonNovoActionPerformed(e);
                } else if (botao == buttonAbrir) {
                    buttonAbrirActionPerformed(e);
                } else if (botao == buttonSalvar) {
                    buttonSalvarActionPerformed(e);
                } else if (botao == buttonCompilar) {
                    buttonCompilarActionPerformed(e);
                } else if (botao == buttonEquipe) {
                    buttonEquipeActionPerformed(e);
                }
            }
        };

        // Adiciona a Action ao botão
        botao.getActionMap().put(nomeAcao, acao);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is alwaysZ
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        barraStatus = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        editor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaMensagens = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        buttonNovo = new javax.swing.JButton();
        buttonAbrir = new javax.swing.JButton();
        buttonSalvar = new javax.swing.JButton();
        buttonCopiar = new javax.swing.JButton();
        buttonColar = new javax.swing.JButton();
        buttonRecortar = new javax.swing.JButton();
        buttonCompilar = new javax.swing.JButton();
        buttonEquipe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(910, 600));

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 25));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportView(editor);

        editor.setColumns(20);
        editor.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        editor.setRows(5);
        editor.setBorder(new NumberedBorder());
        jScrollPane1.setViewportView(editor);

        jSplitPane1.setTopComponent(jScrollPane1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setWheelScrollingEnabled(false);

        areaMensagens.setEditable(false);
        areaMensagens.setColumns(20);
        areaMensagens.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        areaMensagens.setRows(5);
        areaMensagens.setDragEnabled(true);
        jScrollPane2.setViewportView(areaMensagens);

        jSplitPane1.setBottomComponent(jScrollPane2);

        jPanel2.setMinimumSize(new java.awt.Dimension(900, 70));
        jPanel2.setPreferredSize(new java.awt.Dimension(900, 70));

        buttonNovo.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/add-document.png"))); // NOI18N
        buttonNovo.setText("Novo [ctrl+n]");
        buttonNovo.setToolTipText("");
        buttonNovo.setActionCommand("buttonNovo");
        buttonNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNovo.setMaximumSize(new java.awt.Dimension(95, 49));
        buttonNovo.setPreferredSize(new java.awt.Dimension(95, 49));
        buttonNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovoActionPerformed(evt);
            }
        });

        buttonAbrir.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/folder.png"))); // NOI18N
        buttonAbrir.setText("Abrir [ctrl+o]");
        buttonAbrir.setActionCommand("buttonAbrir");
        buttonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbrirActionPerformed(evt);
            }
        });

        buttonSalvar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/archive.png"))); // NOI18N
        buttonSalvar.setText("Salvar [ctrl+s]");
        buttonSalvar.setActionCommand("butttonSalvar");
        buttonSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        buttonCopiar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/pages.png"))); // NOI18N
        buttonCopiar.setText("Copiar [ctrl+c]");
        buttonCopiar.setToolTipText("");
        buttonCopiar.setActionCommand("buttonCopiar");
        buttonCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCopiarActionPerformed(evt);
            }
        });

        buttonColar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonColar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/paste-as-text.png"))); // NOI18N
        buttonColar.setText("Colar [ctrl+v]");
        buttonColar.setToolTipText("");
        buttonColar.setActionCommand("buttonColar");
        buttonColar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonColar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonColar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColarActionPerformed(evt);
            }
        });

        buttonRecortar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonRecortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/scissor.png"))); // NOI18N
        buttonRecortar.setText("Recortar [ctrl+x]");
        buttonRecortar.setActionCommand("buttonRecortar");
        buttonRecortar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonRecortar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonRecortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecortarActionPerformed(evt);
            }
        });

        buttonCompilar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/play.png"))); // NOI18N
        buttonCompilar.setText("Compilar [F7]");
        buttonCompilar.setActionCommand("buttonCompilar");
        buttonCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCompilarActionPerformed(evt);
            }
        });

        buttonEquipe.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        buttonEquipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/team.png"))); // NOI18N
        buttonEquipe.setText("Equipe [F1]");
        buttonEquipe.setActionCommand("buttonEquipe");
        buttonEquipe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEquipe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonEquipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEquipeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonCopiar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonColar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonRecortar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(buttonEquipe, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRecortar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonColar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonCopiar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonCompilar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonEquipe, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(buttonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEquipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEquipeActionPerformed
        mostrarEquipe();
    }//GEN-LAST:event_buttonEquipeActionPerformed

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        limparAmbiente();
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompilarActionPerformed
        testAreamensagens();

        LexicoFactory lexico = new LexicoFactory();
        String tokens = lexico.RealizarAnaliseLexica(editor.getText());
        areaMensagens.setText(tokens);
        
        	/*
                Lexico lexico = new Lexico();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		lexico.setInput(  [entrada] );
		try
		{
			sintatico.parse(lexico, semantico);    // tradução dirigida pela sintaxe
		}
		// mensagem: programa compilado com sucesso - área reservada para mensagens
		
		catch ( LexicalError e )
		{
			//Trata erros léxicos, conforme especificação da parte 2 - do compilador
		}
		catch ( SyntaticError e )
		{
		       System.out.println(e.getPosition() + " símbolo encontrado: na entrada " + e.getMessage()); 
			 
			//Trata erros sintáticos
			//linha 			      sugestão: converter getPosition em linha
			//símbolo encontrado    sugestão: implementar um método getToken no sintatico
			//símbolos esperados,   alterar ParserConstants.java, String[] PARSER_ERROR
                        // consultar os símbolos esperados no GALS (em Documentação > Tabela de Análise Sintática): 		
		}
		catch ( SemanticError e )
		{
			//Trata erros semânticos
		}*/
    }//GEN-LAST:event_buttonCompilarActionPerformed

    private void buttonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAbrirActionPerformed
        abrirArquivo();
    }//GEN-LAST:event_buttonAbrirActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        salvarArquivo();
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCopiarActionPerformed
        copiarAreaTransferencia();
    }//GEN-LAST:event_buttonCopiarActionPerformed

    private void buttonColarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColarActionPerformed
        colarAreaTransferencia();
    }//GEN-LAST:event_buttonColarActionPerformed

    private void buttonRecortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecortarActionPerformed
        recortarAreaTransferencia();
    }//GEN-LAST:event_buttonRecortarActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaMensagens;
    private javax.swing.JLabel barraStatus;
    private javax.swing.JButton buttonAbrir;
    private javax.swing.JButton buttonColar;
    private javax.swing.JButton buttonCompilar;
    private javax.swing.JButton buttonCopiar;
    private javax.swing.JButton buttonEquipe;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonRecortar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JTextArea editor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
