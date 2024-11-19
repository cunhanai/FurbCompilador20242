/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.utils;

import java.util.List;
import model.lexico.resources.Token;

/**
 *
 * @author tahaskel
 */
public class GeradorCodigoObjeto {
    
    private List<String> comandos;
 
    public List<String> gerarCarregarValorConstante(Token token){
        comandos.add("ldc.i8 " + token.getLexeme());
        return comandos;
    }
    
}
