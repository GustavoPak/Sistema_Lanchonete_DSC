package br.com.lanchonete.view;

import br.com.lanchonete.controller.CategoriaController;
import br.com.lanchonete.model.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CategoriaView extends JFrame {

    private final CategoriaController controller;

    private JTextField txtNome;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private Integer idSelecionado;

    public CategoriaView() {

        controller = new CategoriaController();

        inicializarComponentes();
        carregarTabela();

        setTitle("Cadastro de Categorias");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelFormulario = new JPanel(new FlowLayout());

        JLabel lblNome = new JLabel("Nome:");

        txtNome = new JTextField(20);

        painelFormulario.add(lblNome);
        painelFormulario.add(txtNome);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome"}, 0
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
                e -> selecionarCategoria()
        );
    }

    private void salvar() {

        String nome = txtNome.getText().trim();

        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o nome da categoria."
            );

            return;
        }

        controller.salvar(nome);

        JOptionPane.showMessageDialog(
                this,
                "Categoria cadastrada com sucesso!"
        );

        limparCampos();
        carregarTabela();
    }

    private void atualizar() {

        if (idSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma categoria."
            );

            return;
        }

        String nome = txtNome.getText().trim();

        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o nome da categoria."
            );

            return;
        }

        controller.atualizar(idSelecionado, nome);

        JOptionPane.showMessageDialog(
                this,
                "Categoria atualizada com sucesso!"
        );

        limparCampos();
        carregarTabela();
    }

    private void excluir() {

        if (idSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma categoria."
            );

            return;
        }

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {

            controller.excluir(idSelecionado);

            JOptionPane.showMessageDialog(
                    this,
                    "Categoria removida com sucesso!"
            );

            limparCampos();
            carregarTabela();
        }
    }

    private void carregarTabela() {

        modeloTabela.setRowCount(0);

        List<Categoria> categorias = controller.listar();

        for (Categoria categoria : categorias) {

            modeloTabela.addRow(new Object[]{
                categoria.getId(),
                categoria.getNome()
            });
        }
    }

    private void selecionarCategoria() {

        int linha = tabela.getSelectedRow();

        if (linha >= 0) {

            idSelecionado =
                    (Integer) modeloTabela.getValueAt(linha, 0);

            txtNome.setText(
                    modeloTabela.getValueAt(linha, 1).toString()
            );
        }
    }

    private void limparCampos() {

        txtNome.setText("");

        idSelecionado = null;

        tabela.clearSelection();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new CategoriaView().setVisible(true);
        });
    }
}