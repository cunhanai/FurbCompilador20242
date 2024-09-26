/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.lexico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.lexico.resources.LexicalError;
import model.lexico.resources.Lexico;
import model.lexico.resources.Token;

/**
 *
 * @author anaj2
 */
public class LexicoFactory {

    private Lexico lexico;

    public LexicoFactory() {
        lexico = new Lexico();
    }

    public String RealizarAnaliseLexica(String editorText) {
        TokensOutputFactory tokenFactory = new TokensOutputFactory();

        lexico.setInput(editorText); // texto do editor de textos

        int linha = 1;
        int posJaLidas = 0;
        String[] linhas = editorText.split("\r\n|\n|\r");

        Token token = null;
        try {

            while ((token = lexico.nextToken()) != null) {

                while (token.getPosition() > linhas[linha - 1].length() + posJaLidas) {
                    posJaLidas += linhas[linha - 1].length() + 1;
                    linha++;
                }

                String classe = getClasse(token.getId());
                if (classe == null){
                    throw new LexicalError("palavra reservada inválida", token.getPosition());
                }
                tokenFactory.adicionarToken(linha, classe, token.getLexeme());

                //System.out.println(t.getLexeme());
                // só escreve o lexema, necessário escrever t.getId, t.getPosition()
                // t.getId () - retorna o identificador da classe. Olhar Constants.java e adaptar, pois 
                // deve ser apresentada a classe por extenso
                // t.getPosition () - retorna a posição inicial do lexema no editor, necessário adaptar 
                // para mostrar a linha	
                // esse código apresenta os tokens enquanto não ocorrer erro
                // no entanto, os tokens devem ser apresentados SÓ se não ocorrer erro, necessário adaptar 
                // para atender o que foi solicitado		
            }

        } catch (LexicalError e) {  // tratamento de erros

            String linhaErro = "linha " + linha + ": ";
                       
            String lexemaErro = editorText.substring(e.getPosition());
            Pattern fimDeLexema = Pattern.compile("\r\n|\n|\r");
            Matcher procura = fimDeLexema.matcher(lexemaErro);
            
            if (procura.find()) {
                lexemaErro = lexemaErro.substring(0, procura.start());
            }
                  
            return linhaErro + lexemaErro + " " + e.getMessage();

            // e.getMessage() - retorna a mensagem de erro de SCANNER_ERRO (olhar ScannerConstants.java 
            // e adaptar conforme o enunciado da parte 2)
            // e.getPosition() - retorna a posição inicial do erro, tem que adaptar para mostrar a 
            // linha  
        }

        return tokenFactory.build();
    }

    private String getClasse(int id) {

        if (id == 16) {
            return "identificador";
        } else if (id == 17) {
            return "constante_int";
        } else if (id == 18) {
            return "constante_float";
        } else if (id == 19) {
            return "constante_string";
        } else if (id >= 3 && id <= 15) {
            return "palavra reservada";
        } else if (id >= 20 && id <= 35) {
            return "símbolo especial";
        }

        return null;
    }
}
