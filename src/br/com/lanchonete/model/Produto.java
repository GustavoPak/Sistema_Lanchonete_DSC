/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpoo;

/**
 *
 * @author joao
 */
public class Produto {
    
    private int id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private boolean ativo;
    
    public void Produto(int id, String nome, String descricao, Double preco, String categoria){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.ativo = true;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public Double getpreco(){
        return preco;
    }
    public void setPreco(Double preco){
        this.preco = preco;
    }
    
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    public boolean getAtivo(){
        return ativo;
    }
    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }
    
    public void ativar(){
        this.ativo = true;
    }
    public void desativar(){
        this.ativo = false;
    }
    
    public void status(){
        System.out.println("Produto: " + this.nome);
        System.out.println("Id: " + this.id);
        System.out.println("preco: " + this.preco);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Descrição: " + this.descricao);
        System.out.println("Status: " + this.ativo);
    }
    
}
