/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.lexico;

import java.util.ArrayList;

/**
 *
 * @author anaj2
 */
public class TokensOutputFactory {
    
    private String[] titulos = { "linha",  "classe", "lexema"};
    private ArrayList<String[]> tokens = new ArrayList<String[]>();
    
    private int lenTabLinha = titulos[0].length();
    private int lenTabClasse = titulos[1].length();
    private int margemTab = 10;
    
    public TokensOutputFactory() {
    }
    
    public String build() {
        String output = formatarLinhaOutput(titulos);
        
        for (String[] token : tokens)
            output += "\n" + formatarLinhaOutput(token);
        
        output += "\n\n" + " ".repeat(lenTabLinha + margemTab + 1)  +"Programa compilado com sucesso";
        
        return output;
    }
    
    private String formatarLinhaOutput(String[] tokens) {
        return formatarTabulacao(lenTabLinha, tokens[0])
                + formatarTabulacao(lenTabClasse, tokens[1]) 
                + tokens[2];
    }
    
    private String formatarTabulacao(int tamTabulacao, String expressao) {
        int repeticao = tamTabulacao - expressao.length() + margemTab;
        return " " + expressao + (" ".repeat(repeticao));
    }
    
    public void adicionarToken(int linha, String classe, String lexema) {
        String[] token = { "" + linha, classe, lexema };
        tokens.add(token);
        
        definirTabulacao(getLenLinha(linha), classe.length());
    }
    
    private int getLenLinha(int linhaValue) {
        String linhaText = "" + linhaValue;
        return linhaText.length();
    }
    
    private void definirTabulacao(int lenLinha, int lenClasse) {
        if (lenLinha > lenTabLinha)
            lenTabLinha = lenLinha;
        
        if (lenClasse > lenTabClasse)
            lenTabClasse = lenClasse;
    }
}
