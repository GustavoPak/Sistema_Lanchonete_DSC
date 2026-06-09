package br.com.lanchonete.view;

import br.com.lanchonete.controller.CategoriaController;
import br.com.lanchonete.controller.ProdutoController;
import br.com.lanchonete.model.Categoria;
import br.com.lanchonete.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdutoView extends JFrame {

    private final ProdutoController produtoController;
    private final CategoriaController categoriaController;

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private JCheckBox chkAtivo;
    private JComboBox<Categoria> cbCategorias;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private Integer idSelecionado;

    public ProdutoView() {

        produtoController = new ProdutoController();
        categoriaController = new CategoriaController();

        inicializarComponentes();
        carregarCategorias();
        carregarTabela();

        setTitle("Cadastro de Produtos");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtNome = new JTextField();
        txtDescricao = new JTextField();
        txtPreco = new JTextField();
        chkAtivo = new JCheckBox("Produto ativo");
        cbCategorias = new JComboBox<>();

        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Descrição:"));
        painelFormulario.add(txtDescricao);

        painelFormulario.add(new JLabel("Preço:"));
        painelFormulario.add(txtPreco);

        painelFormulario.add(new JLabel("Ativo:"));
        painelFormulario.add(chkAtivo);

        painelFormulario.add(new JLabel("Categoria:"));
        painelFormulario.add(cbCategorias);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Descrição", "Preço", "Ativo", "Categoria"}, 0
        );

        tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);

        JPanel painelBotoes = new JPanel();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        painelPrincipal.add(painelFormulario, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);

        btnSalvar.addActionListener(e -> salvar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limparCampos());

        tabela.getSelectionModel().addListSelectionListener(
                e -> selecionarProduto()
        );
    }

    private void carregarCategorias() {

        cbCategorias.removeAllItems();

        List<Categoria> categorias = categoriaController.listar();

        for (Categoria categoria : categorias) {
            cbCategorias.addItem(categoria);
        }

        cbCategorias.setSelectedIndex(-1);
    }

    private void salvar() {

        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto.");
            return;
        }

        Categoria categoria = (Categoria) cbCategorias.getSelectedItem();

        if (categoria == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria.");
            return;
        }

        Double preco;

        try {
            preco = Double.parseDouble(txtPreco.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um preço válido.");
            return;
        }

        Boolean ativo = chkAtivo.isSelected();

        produtoController.salvar(
                nome,
                preco,
                descricao,
                ativo,
                categoria.getId()
        );

        JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

        limparCampos();
        carregarTabela();
    }

    private void atualizar() {

        if (idSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }

        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto.");
            return;
        }

        Categoria categoria = (Categoria) cbCategorias.getSelectedItem();

        if (categoria == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria.");
            return;
        }

        Double preco;

        try {
            preco = Double.parseDouble(txtPreco.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um preço válido.");
            return;
        }

        Boolean ativo = chkAtivo.isSelected();

        produtoController.atualizar(
                idSelecionado,
                nome,
                preco,
                descricao,
                ativo,
                categoria.getId()
        );

        JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");

        limparCampos();
        carregarTabela();
    }

    private void excluir() {

        if (idSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir este produto?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {

            produtoController.excluir(idSelecionado);

            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");

            limparCampos();
            carregarTabela();
        }
    }

    private void carregarTabela() {

        modeloTabela.setRowCount(0);

        List<Produto> produtos = produtoController.listar();

        for (Produto produto : produtos) {

            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getAtivo() ? "Sim" : "Não",
                produto.getCategoria().getNome()
            });
        }
    }

    private void selecionarProduto() {

        int linha = tabela.getSelectedRow();

        if (linha >= 0) {

            idSelecionado = (Integer) modeloTabela.getValueAt(linha, 0);

            Produto produto = produtoController.buscarPorId(idSelecionado);

            if (produto != null) {

                txtNome.setText(produto.getNome());
                txtDescricao.setText(produto.getDescricao());
                txtPreco.setText(String.valueOf(produto.getPreco()));
                chkAtivo.setSelected(produto.getAtivo());

                selecionarCategoriaNoCombo(produto.getCategoria().getId());
            }
        }
    }

    private void selecionarCategoriaNoCombo(Integer categoriaId) {

        for (int i = 0; i < cbCategorias.getItemCount(); i++) {

            Categoria categoria = cbCategorias.getItemAt(i);

            if (categoria.getId().equals(categoriaId)) {
                cbCategorias.setSelectedIndex(i);
                return;
            }
        }
    }

    private void limparCampos() {

        idSelecionado = null;

        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        chkAtivo.setSelected(false);
        cbCategorias.setSelectedIndex(-1);

        tabela.clearSelection();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ProdutoView().setVisible(true);
        });
    }
}