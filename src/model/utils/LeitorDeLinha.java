/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.utils;

/**
 *
 * @author thomh
 */
public class LeitorDeLinha {
    private int linhaAtual;
    private int ponteiroPosicaoNoArquivo;
    private String[] listaLinhasDoEditor;
    
    public LeitorDeLinha(String textoASerLido){
        linhaAtual = 1;
        ponteiroPosicaoNoArquivo = 0;
        listaLinhasDoEditor = textoASerLido.split("\n");
    }
    
    public int getLinhaDoTokenAtual(int posicaoToken) {
        while (posicaoToken > listaLinhasDoEditor[linhaAtual - 1].length() + ponteiroPosicaoNoArquivo) {
            ponteiroPosicaoNoArquivo += listaLinhasDoEditor[linhaAtual - 1].length() + 1;
            linhaAtual++;
        }

        return linhaAtual;
    }
}
