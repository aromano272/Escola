/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.Serializable;

/**
 *
 * @author aRomano
 */
public class Nota implements Serializable {
    
    private final Cadeira cadeira;
    private final float valor;
    
    public float getValor() {
        return this.valor;
    }
    
    public Cadeira getCadeira() {
        return this.cadeira;
    }
    
    Nota(Cadeira cadeira, float valor) {
        this.cadeira = cadeira;
        this.valor = valor;
    }
}
