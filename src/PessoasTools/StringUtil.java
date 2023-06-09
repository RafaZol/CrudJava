package PessoasTools;

/**
 *
 * @author Rafael
 */
public class StringUtil {

    public static String Cripto(String senha) {

        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = senha.length();
        senha = senha.toUpperCase();
        contador = 0;

        while (contador < tamanho) {
            codigoASCII = senha.charAt(contador) + 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }

        return senhaCriptografada;
    }

    public static String Decripto(String senha) {
        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = senha.length();
        senha = senha.toUpperCase();
        contador = 0;

        while (contador < tamanho) {
            codigoASCII = senha.charAt(contador) - 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }

        return senhaCriptografada;
    }
}
