package br.com.lanchonete.controller;

import br.com.lanchonete.dao.UsuarioDAO;
import br.com.lanchonete.model.Usuario;
import br.com.lanchonete.util.BCryptUtil;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrarUsuario(String nome, String login, String senha) {
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(BCryptUtil.gerarHash(senha));
        usuario.setAtivo(true);

        usuarioDAO.salvar(usuario);
    }

    public Usuario autenticar(String login, String senhaDigitada) {
        Usuario usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario == null) {
            return null;
        }

        boolean senhaCorreta = BCryptUtil.verificarSenha(
            senhaDigitada,
            usuario.getSenha()
        );

        if (senhaCorreta) {
            return usuario;
        }

        return null;
    }
    
    public Usuario buscarPorLogin(String login) {
    return usuarioDAO.buscarPorLogin(login);
}
}