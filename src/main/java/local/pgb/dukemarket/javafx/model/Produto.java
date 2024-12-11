/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.pgb.dukemarket.javafx.model;

/**
 *
 * @author qualifica
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Produto {
    // Atributos
    private int results;
    private int id;
    private String codBarras;
    private String descricao;
    private double qtd;
    private double valorCompra;
    private double valorVenda;
    private Calendar dataCadastro; // Corrigido o nome da variável
    private String level; // Renomeado para um tipo mais específico

    // Construtor
    public Produto(Calendar dataCadastro, int id, String codBarras, String descricao, double qtd, double valorCompra, double valorVenda) {
        this.dataCadastro = dataCadastro;
        this.id = id;
        this.codBarras = codBarras;
        this.descricao = descricao;
        this.qtd = qtd;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
    }

    public Produto() {
    }

    // Método para obter a data de cadastro formatada
    public String getDataCadastro() {
        if (this.dataCadastro != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return sdf.format(this.dataCadastro.getTime());
        } else {
            return "";
        }
    }

    // Método para definir a data de cadastro a partir de uma string
    public void setDataCadastro(String strData) {
        try{
        this.dataCadastro = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.dataCadastro.setTime(sdf.parse(strData));
      } catch (ParseException ex) {
        Logger.getLogger(Produto.class.getName()).log(Level.SEVERE,null,ex);
     }
    }
    // Getters e Setters

    public int getResults(){
        return results;
    }
    public void setResults(int results){
        this.results = results;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        return String.format("%3d | %15s | %-30s | %10.2f | %10.2f | %10.2f | %10s",
                this.id, this.codBarras, this.descricao, this.qtd, this.valorCompra, this.valorVenda, this.getDataCadastro());
    }
}

