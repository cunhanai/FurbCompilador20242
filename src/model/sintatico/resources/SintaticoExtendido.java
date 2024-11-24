/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sintatico.resources;

import model.erro.LexicalError;
import model.erro.SemanticError;
import model.erro.SyntaticError;
import model.lexico.resources.LexicoExtendido;
import model.semantico.resources.Semantico;

/**
 *
 * @author thomh
 */
public class SintaticoExtendido extends Sintatico {
    
    public void parse(LexicoExtendido scanner, Semantico semanticAnalyser) throws LexicalError, SyntaticError, SemanticError {
        super.parse(scanner, semanticAnalyser);
    }
}
