/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tratadordeerro;

import model.erro.LexicalError;

/**
 *
 * @author thomh
 */
public class TratadorErroLexico extends TratadorErro<LexicalError>{
    
    public TratadorErroLexico(String editorText) {
        super.setEditorText(editorText);
    }

    @Override
    public String gerarMensagemDeErro(LexicalError e) {
        String linhaErro = super.gerarLinhaErro(e.getPosition());

        if (mensagemDeErroRequerLexema(e.getMessage())) {
            String lexemaErro = super.getLexemaErro(e.getPosition());
            return linhaErro + lexemaErro + " " + e.getMessage();
        } else {
            return linhaErro + e.getMessage();
        }
    }

    private boolean mensagemDeErroRequerLexema(String mensagemErro) {
        return !(mensagemErro.contains("constante_string") || mensagemErro.contains("comentário de bloco"));
    }
    
    private String getClasseLexema(int id) {

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
