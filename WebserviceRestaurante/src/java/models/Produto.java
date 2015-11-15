/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author alexandre
 */
class Produto implements Serializable{
    
    private long id;
    private String categoria;
    private String nome;
    private float valor;
    private String foto;
}
