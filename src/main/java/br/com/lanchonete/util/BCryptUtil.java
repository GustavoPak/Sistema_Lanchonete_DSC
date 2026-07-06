package br.com.lanchonete.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {

    public static String gerarHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senhaDigitada, String hashSalvo) {
        return BCrypt.checkpw(senhaDigitada, hashSalvo);
    }
}