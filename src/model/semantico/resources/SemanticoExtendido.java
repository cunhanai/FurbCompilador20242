/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.semantico.resources;

import java.util.List;
import java.util.Stack;
import model.erro.SemanticError;
import model.lexico.resources.Token;

/**
 *
 * @author tahaskel
 */
public class SemanticoExtendido extends Semantico {

    private String operadorRelacional;
    private List<String> codigoObjeto;
    private Stack<String> pilhaTipos;
    private Stack<String> pilhaRotulos;
    private List<String> listaIdentificadores;
    private List<String> tabelaSimbolos;

    public SemanticoExtendido() {
        super();
    }

    @Override
    public void executeAction(int action, Token token) throws SemanticError {
        super.executeAction(action, token);
        switch (action) {
            case 100:
                System.out.println("uou codigo gerado com cabeçalho!!1!!");
                break;
            default:
                System.out.println("mensagem ação ainda não implementada");
        }
    }
    
    private 

}
