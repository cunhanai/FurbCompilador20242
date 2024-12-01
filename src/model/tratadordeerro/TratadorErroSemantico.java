/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tratadordeerro;

import model.erro.SemanticError;
import model.lexico.resources.Token;

/**
 *
 * @author thomh
 */
public class TratadorErroSemantico extends TratadorErro<SemanticError>{
    
     public TratadorErroSemantico(String editorText) {
        super.setEditorText(editorText);
    }
    
    @Override
    public String gerarMensagemDeErro(SemanticError e) {
        String linhaErro = super.gerarLinhaErro(e.getPosition());
        String mensagemErro = e.getMessage();
        
        return linhaErro + mensagemErro;
    }
    
}
