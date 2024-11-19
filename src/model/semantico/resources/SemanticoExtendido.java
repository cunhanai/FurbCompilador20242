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
    private String codigoObjeto;
    private Stack<String> pilhaTipos;
    private Stack<String> pilhaRotulos;
    private List<String> listaIdentificadores;
    private List<String> tabelaSimbolos;

    public SemanticoExtendido() {
        super();
    }

    @Override
    public void executeAction(int action, Token token) throws SemanticError {
        switch (action) {
            case 100:
                break;
            case 101:
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
            case 105:
                break;
            case 106:
                break;
            case 107:
                break;
            case 108:
                break;
            case 109:
                break;
            case 110:
                break;
            case 111:
                break;
            case 112:
                break;
            case 113:
                break;
            case 114:
                break;
            case 115:
                break;
            case 116:
                break;
            case 117:
                break;
            case 118:
                break;
            case 119:
                break;
            case 120:
                break;
            case 121:
                break;
            case 122:
                break;
            case 123:
                break;
            case 124:
                break;
            case 125:
                break;
            case 126:
                break;
            case 127:
                break;
            case 128:
                break;
            case 129:
                break;
            case 130:
                break;
            case 131:
                break;
            case 132:
                break;

        }
    }

}
