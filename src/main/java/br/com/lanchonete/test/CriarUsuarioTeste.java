package br.com.lanchonete.test;

import br.com.lanchonete.controller.UsuarioController;

public class CriarUsuarioTeste {

    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();

        controller.cadastrarUsuario("Administrador", "admin", "123");

        System.out.println("Usuário criado com sucesso!");
    }
}