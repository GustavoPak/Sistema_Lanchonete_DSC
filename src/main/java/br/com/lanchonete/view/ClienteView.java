package br.com.lanchonete.view;

import br.com.lanchonete.controller.ClienteController;
import br.com.lanchonete.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteView extends JFrame {

    private final ClienteController controller;

    private JTextField txtNome;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private Integer idSelecionado;

    public ClienteView() {

        controller = new ClienteController();

        inicializarComponentes();
        carregarTabela();

        setTitle("Cadastro de Clientes");
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
                e -> selecionarCliente()
        );
    }

    private void salvar() {

        String nome = txtNome.getText().trim();

        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o nome do cliente."
            );

            return;
        }

        controller.salvar(nome);

        JOptionPane.showMessageDialog(
                this,
                "Cliente cadastrado com sucesso!"
        );

        limparCampos();
        carregarTabela();
    }

    private void atualizar() {

        if (idSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um cliente."
            );

            return;
        }

        String nome = txtNome.getText().trim();

        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o nome do cliente."
            );

            return;
        }

        try {

            controller.atualizar(idSelecionado, nome);

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente atualizado com sucesso!"
            );

            limparCampos();
            carregarTabela();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void excluir() {

        if (idSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um cliente."
            );

            return;
        }

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir este cliente?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {

            controller.excluir(idSelecionado);

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente excluído com sucesso!"
            );

            limparCampos();
            carregarTabela();
        }
    }

    private void carregarTabela() {

        modeloTabela.setRowCount(0);

        List<Cliente> clientes = controller.listar();

        for (Cliente cliente : clientes) {

            modeloTabela.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome()
            });
        }
    }

    private void selecionarCliente() {

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
            new ClienteView().setVisible(true);
        });
    }
}