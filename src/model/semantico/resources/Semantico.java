package model.semantico.resources;

import model.lexico.resources.Constants;
import model.lexico.resources.Token;
import model.erro.SemanticError;

public class Semantico implements Constants
{
    public void executeAction(int action, Token token) throws SemanticError
    {
        System.out.println("Acao #"+action+", Token: "+token);
    }	
}
