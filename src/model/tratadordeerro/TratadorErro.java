/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tratadordeerro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.utils.LeitorDeLinha;

/**
 *
 * @author thomh
 */
public abstract class TratadorErro<T> {

    private String editorText;
    LeitorDeLinha leitorDeLinha;

    public abstract String gerarMensagemDeErro(T e);
    
    protected void setEditorText(String editorText){
        this.editorText = editorText;
    }

    protected String gerarLinhaErro(int posicaoToken) {
        leitorDeLinha = new LeitorDeLinha(editorText);
        String linhaErro = "Erro na linha " + leitorDeLinha.getLinhaDoTokenAtual(posicaoToken) + " - ";
        return linhaErro;
    }

    protected String getLexemaErro(int posicaoToken) {
        String lexemaDoErro = editorText.substring(posicaoToken);
        Pattern fimDeLinha = Pattern.compile("\r\n|\n|\r| ");
        Matcher buscadorFimDelinha = fimDeLinha.matcher(lexemaDoErro);

        if (buscadorFimDelinha.find()) {
            lexemaDoErro = lexemaDoErro.substring(0, buscadorFimDelinha.start());
        }

        return lexemaDoErro;
    }
}
