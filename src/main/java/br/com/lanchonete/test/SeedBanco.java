package br.com.lanchonete.test;

import br.com.lanchonete.controller.CategoriaController;
import br.com.lanchonete.controller.ClienteController;
import br.com.lanchonete.controller.PedidoController;
import br.com.lanchonete.controller.ProdutoController;
import br.com.lanchonete.controller.UsuarioController;
import br.com.lanchonete.model.Categoria;
import br.com.lanchonete.model.Cliente;

public class SeedBanco {

    public static void main(String[] args) {

        UsuarioController usuarioController = new UsuarioController();
        CategoriaController categoriaController = new CategoriaController();
        ProdutoController produtoController = new ProdutoController();
        ClienteController clienteController = new ClienteController();
        PedidoController pedidoController = new PedidoController();

        criarUsuarioSeNaoExistir(usuarioController, "Administrador", "admin", "123");

        criarCategoriaSeNaoExistir(categoriaController, "Lanches");
        criarCategoriaSeNaoExistir(categoriaController, "Bebidas");
        criarCategoriaSeNaoExistir(categoriaController, "Sobremesas");

        Categoria lanches = categoriaController.buscarPorNome("Lanches");
        Categoria bebidas = categoriaController.buscarPorNome("Bebidas");
        Categoria sobremesas = categoriaController.buscarPorNome("Sobremesas");

        criarProdutoSeNaoExistir(produtoController, "X-Burger", 18.00, "Pão, hambúrguer, queijo e molho", true, lanches.getId());
        criarProdutoSeNaoExistir(produtoController, "X-Salada", 22.00, "Pão, hambúrguer, queijo, alface e tomate", true, lanches.getId());
        criarProdutoSeNaoExistir(produtoController, "Coca-Cola", 7.00, "Refrigerante lata 350ml", true, bebidas.getId());
        criarProdutoSeNaoExistir(produtoController, "Suco de Laranja", 9.00, "Suco natural 300ml", true, bebidas.getId());
        criarProdutoSeNaoExistir(produtoController, "Pudim", 8.50, "Fatia de pudim", true, sobremesas.getId());

        criarClienteSeNaoExistir(clienteController, "João Silva");
        criarClienteSeNaoExistir(clienteController, "Maria Oliveira");
        criarClienteSeNaoExistir(clienteController, "Carlos Souza");

        Cliente joao = clienteController.buscarPorNome("João Silva");
        Cliente maria = clienteController.buscarPorNome("Maria Oliveira");
        Cliente carlos = clienteController.buscarPorNome("Carlos Souza");

        criarPedidoSeNaoExistir(pedidoController, joao.getId(), "ABERTO", 25.00);
        criarPedidoSeNaoExistir(pedidoController, maria.getId(), "FINALIZADO", 31.00);
        criarPedidoSeNaoExistir(pedidoController, carlos.getId(), "EM PREPARO", 18.00);

        System.out.println("Banco semeado com sucesso!");
    }

    private static void criarUsuarioSeNaoExistir(
            UsuarioController controller,
            String nome,
            String login,
            String senha
    ) {
        if (controller.buscarPorLogin(login) == null) {
            controller.cadastrarUsuario(nome, login, senha);
            System.out.println("Usuário criado: " + login);
        }
    }

    private static void criarCategoriaSeNaoExistir(
            CategoriaController controller,
            String nome
    ) {
        if (controller.buscarPorNome(nome) == null) {
            controller.salvar(nome);
            System.out.println("Categoria criada: " + nome);
        }
    }

    private static void criarProdutoSeNaoExistir(
            ProdutoController controller,
            String nome,
            Double preco,
            String descricao,
            Boolean ativo,
            Integer categoriaId
    ) {
        if (controller.buscarPorNomeExato(nome) == null) {
            controller.salvar(nome, preco, descricao, ativo, categoriaId);
            System.out.println("Produto criado: " + nome);
        }
    }

    private static void criarClienteSeNaoExistir(
            ClienteController controller,
            String nome
    ) {
        if (controller.buscarPorNome(nome) == null) {
            controller.salvar(nome);
            System.out.println("Cliente criado: " + nome);
        }
    }

    private static void criarPedidoSeNaoExistir(
            PedidoController controller,
            Integer clienteId,
            String status,
            Double valorTotal
    ) {
        if (controller.buscarPorClienteEStatus(clienteId, status) == null) {
            controller.salvar(clienteId, status, valorTotal);
            System.out.println("Pedido criado: " + status);
        }
    }
}