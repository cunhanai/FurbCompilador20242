/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model.semantico.resources;

/**
 *
 * @author thomh
 */
public enum OperadoresRelacionais {

    IGUAL("=="),
    DIFERENTE("!="),
    MENOR("<"),
    MAIOR(">");

    public final String operador;

    private OperadoresRelacionais(String operador) {
        this.operador = operador;
    }
}
