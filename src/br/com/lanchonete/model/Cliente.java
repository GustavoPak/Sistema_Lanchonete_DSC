/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpoo;

/**
 *
 * @author joao
 */
public class Cliente {
    
    private int id;
    private String nome;
    private String telefone;
    
    public void Cliente(int id, String nome, String telefone){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }
    
    public int getId(){
        return id;
    }
    
    public int setId(int id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public void Setnome(String nome){
        this.nome = nome;
    }
    
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public void dadosCliente(){
        System.out.println("Cliente: " + this.nome);
        System.out.println("Id: "+ this.id);
        System.out.println("Telefone: " + this.telefone);
    }
}
