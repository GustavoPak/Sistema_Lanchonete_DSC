package br.com.lanchonete.view;

import br.com.lanchonete.controller.ProdutoController;
import br.com.lanchonete.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BuscaView extends JFrame {

    private final ProdutoController produtoController;

    private JComboBox<String> cbTipoBusca;
    private JTextField txtPesquisa;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public BuscaView() {

        produtoController = new ProdutoController();

        inicializarComponentes();
        carregarTodosProdutos();

        setTitle("Busca de Produtos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelBusca = new JPanel(new GridLayout(3, 2, 10, 10));
        painelBusca.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        cbTipoBusca = new JComboBox<>(new String[]{
            "Nome do Produto",
            "Categoria"
        });

        txtPesquisa = new JTextField();

        painelBusca.add(new JLabel("Buscar por:"));
        painelBusca.add(cbTipoBusca);

        painelBusca.add(new JLabel("Pesquisar:"));
        painelBusca.add(txtPesquisa);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpar = new JButton("Limpar");

        painelBusca.add(btnBuscar);
        painelBusca.add(btnLimpar);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Categoria", "Preço", "Ativo"}, 0
        );

        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        painelPrincipal.add(painelBusca, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(painelPrincipal);

        btnBuscar.addActionListener(e -> buscar());
        btnLimpar.addActionListener(e -> limparBusca());
    }

    private void buscar() {

        String texto = txtPesquisa.getText().trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite algo para pesquisar.");
            return;
        }

        String tipoBusca = cbTipoBusca.getSelectedItem().toString();

        List<Produto> produtos;

        if (tipoBusca.equals("Nome do Produto")) {
            produtos = produtoController.buscarPorNome(texto);
        } else {
            produtos = produtoController.buscarPorCategoria(texto);
        }

        preencherTabela(produtos);
    }

    private void carregarTodosProdutos() {
        List<Produto> produtos = produtoController.listar();
        preencherTabela(produtos);
    }

    private void preencherTabela(List<Produto> produtos) {

        modeloTabela.setRowCount(0);

        for (Produto produto : produtos) {

            String nomeCategoria = "";

            if (produto.getCategoria() != null) {
                nomeCategoria = produto.getCategoria().getNome();
            }

            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                nomeCategoria,
                produto.getPreco(),
                produto.getAtivo() ? "Sim" : "Não"
            });
        }
    }

    private void limparBusca() {
        txtPesquisa.setText("");
        cbTipoBusca.setSelectedIndex(0);
        carregarTodosProdutos();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new BuscaView().setVisible(true);
        });
    }
}