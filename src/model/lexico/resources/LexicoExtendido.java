/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.lexico.resources;

import model.erro.LexicalError;

/**
 *
 * @author thomh
 */
public class LexicoExtendido extends Lexico {
    
    public LexicoExtendido() {
        super();
    }
    
    public LexicoExtendido(String input) {
        super(input);
    }
    
    
    @Override
    public Token nextToken() throws LexicalError {
        Token token = super.nextToken();
        if (token == null){
            return null;
        }
        
        if (verificarPalavraReservadaInvalida(token)) {
            throw new LexicalError("palavra reservada inválida", token.getPosition());
        }
        
        return token;
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
    
    private boolean verificarPalavraReservadaInvalida(Token token) {
        return (getClasse(token.getId()) == null);
    }
}
