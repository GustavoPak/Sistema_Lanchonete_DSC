package br.com.lanchonete.view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame {

    public MenuPrincipalView() {

        setTitle("Sistema de Lanchonete");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criarTela();
    }

    private void criarTela() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel(
                "SISTEMA DE LANCHONETE",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(
                2,
                2,
                20,
                20
        ));

        painelBotoes.setBorder(
                BorderFactory.createEmptyBorder(
                        50,
                        100,
                        50,
                        100
                )
        );

        JButton btnCategorias = new JButton("Categorias");
        JButton btnProdutos = new JButton("Produtos");
        JButton btnClientes = new JButton("Clientes");
        JButton btnPedidos = new JButton("Pedidos");

        Font fonteBotao = new Font("Arial", Font.BOLD, 18);

        btnCategorias.setFont(fonteBotao);
        btnProdutos.setFont(fonteBotao);
        btnClientes.setFont(fonteBotao);
        btnPedidos.setFont(fonteBotao);

        painelBotoes.add(btnCategorias);
        painelBotoes.add(btnProdutos);
        painelBotoes.add(btnClientes);
        painelBotoes.add(btnPedidos);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        JButton btnSair = new JButton("Sair");
        btnSair.setFont(fonteBotao);

        JPanel painelRodape = new JPanel();
        painelRodape.add(btnSair);

        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);

        btnCategorias.addActionListener(e ->
                new CategoriaView().setVisible(true));

        btnProdutos.addActionListener(e ->
                new ProdutoView().setVisible(true));

        btnClientes.addActionListener(e ->
                new ClienteView().setVisible(true));

        btnPedidos.addActionListener(e ->
                new PedidoView().setVisible(true));

        btnSair.addActionListener(e ->
                System.exit(0));
    }
    
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new MenuPrincipalView().setVisible(true);
    });
    }
}



