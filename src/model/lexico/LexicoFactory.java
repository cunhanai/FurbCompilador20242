/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.lexico;

import model.utils.LeitorDeLinha;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.erro.LexicalError;
import model.lexico.resources.Lexico;
import model.lexico.resources.Token;

/**
 *
 * @author anaj2
 */
public class LexicoFactory {

    private Lexico lexico;
    private String editorText;
    LeitorDeLinha leitorDeLinha;

    public LexicoFactory() {
        lexico = new Lexico();
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
            verificarPalavraReservadaInvalida(token);
        }
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

    private void verificarPalavraReservadaInvalida(Token token) throws LexicalError {
        if (getClasse(token.getId()) == null) {
            throw new LexicalError("palavra reservada inválida", token.getPosition());
        }
    }

    private String gerarMensagemDeErro(LexicalError e) {

        leitorDeLinha.getLinhaDoTokenAtual(e.getPosition());
        String linhaErro = "linha " + leitorDeLinha.getLinhaAtual() + ": ";

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
