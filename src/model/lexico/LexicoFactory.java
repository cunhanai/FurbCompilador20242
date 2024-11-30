/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.lexico;

import model.utils.LeitorDeLinha;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.erro.LexicalError;
import model.lexico.resources.LexicoExtendido;
import model.lexico.resources.Token;

/**
 *
 * @author anaj2
 */
public class LexicoFactory {

    private LexicoExtendido lexico;
    private String editorText;
    LeitorDeLinha leitorDeLinha;

    public LexicoFactory() {
        lexico = new LexicoExtendido();
    }

    public String realizarAnaliseLexica(String editorText) {
        this.editorText = editorText;

        try {
            gerarListaDeTokens();
        } catch (LexicalError e) {
            return gerarMensagemDeErro(e);
        }

        return "programa compilado com sucesso";
    }

    private void gerarListaDeTokens() throws LexicalError {
        Token token = null;
        leitorDeLinha = new LeitorDeLinha(editorText);
        lexico.setInput(editorText);

        while ((token = lexico.nextToken()) != null) {
            token = token;
        }
    }
    
    private String gerarMensagemDeErro(LexicalError e) {

        String linhaErro = "linha " + leitorDeLinha.getLinhaDoTokenAtual(e.getPosition()) + ": ";

        if (mensagemDeErroRequerLexema(e.getMessage())) {
            String lexemaErro = getLexemaErro(e);
            return linhaErro + lexemaErro + " " + e.getMessage();
        } else {
            return linhaErro + e.getMessage();
        }
    }

    private boolean mensagemDeErroRequerLexema(String mensagemErro) {
        return !(mensagemErro.contains("constante_string") || mensagemErro.contains("comentário de bloco"));
    }

    private String getLexemaErro(LexicalError e) {
        String lexemaDoErro = editorText.substring(e.getPosition());
        Pattern fimDeLinha = Pattern.compile("\r\n|\n|\r| ");
        Matcher buscadorFimDelinha = fimDeLinha.matcher(lexemaDoErro);

        if (buscadorFimDelinha.find()) {
            lexemaDoErro = lexemaDoErro.substring(0, buscadorFimDelinha.start());
        }

        return lexemaDoErro;
    }
}
