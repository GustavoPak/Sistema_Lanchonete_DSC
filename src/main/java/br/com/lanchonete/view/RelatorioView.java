package br.com.lanchonete.view;

import br.com.lanchonete.controller.PedidoController;
import br.com.lanchonete.controller.ProdutoController;
import br.com.lanchonete.model.Pedido;
import br.com.lanchonete.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RelatorioView extends JFrame {

    private final ProdutoController produtoController;
    private final PedidoController pedidoController;

    private JComboBox<String> cbRelatorio;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public RelatorioView() {

        produtoController = new ProdutoController();
        pedidoController = new PedidoController();

        inicializarComponentes();

        setTitle("Relatórios");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelTopo = new JPanel(new GridLayout(2, 2, 10, 10));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        cbRelatorio = new JComboBox<>(new String[]{
            "Produtos cadastrados",
            "Pedidos cadastrados"
        });

        JButton btnGerar = new JButton("Gerar Relatório");

        painelTopo.add(new JLabel("Tipo de relatório:"));
        painelTopo.add(cbRelatorio);
        painelTopo.add(new JLabel(""));
        painelTopo.add(btnGerar);

        modeloTabela = new DefaultTableModel();
        tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(painelPrincipal);

        btnGerar.addActionListener(e -> gerarRelatorio());
    }

    private void gerarRelatorio() {

        String tipo = cbRelatorio.getSelectedItem().toString();

        if (tipo.equals("Produtos cadastrados")) {
            gerarRelatorioProdutos();
        } else {
            gerarRelatorioPedidos();
        }
    }

    private void gerarRelatorioProdutos() {

        modeloTabela.setColumnIdentifiers(new Object[]{
            "ID", "Nome", "Categoria", "Preço", "Ativo"
        });

        modeloTabela.setRowCount(0);

        List<Produto> produtos = produtoController.listar();

        for (Produto produto : produtos) {

            String categoria = "";

            if (produto.getCategoria() != null) {
                categoria = produto.getCategoria().getNome();
            }

            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                categoria,
                produto.getPreco(),
                produto.getAtivo() ? "Sim" : "Não"
            });
        }
    }

    private void gerarRelatorioPedidos() {

        modeloTabela.setColumnIdentifiers(new Object[]{
            "ID", "Cliente", "Status", "Valor Total", "Data/Hora"
        });

        modeloTabela.setRowCount(0);

        List<Pedido> pedidos = pedidoController.listar();

        for (Pedido pedido : pedidos) {

            String cliente = "";

            if (pedido.getCliente() != null) {
                cliente = pedido.getCliente().getNome();
            }

            modeloTabela.addRow(new Object[]{
                pedido.getId(),
                cliente,
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getDataHora()
            });
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RelatorioView().setVisible(true);
        });
    }
}