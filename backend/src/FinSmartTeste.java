import java.math.BigDecimal;

public class FinSmartTeste {

    public static void main(String[] args) {
        Empresa empresa = new Empresa(1, "Tech Solucoes", "12.345.678/0001-90",
                "Tech Solucoes LTDA", "Tecnologia", 10);
        empresa.cadastrar();
        empresa.inativar();

        Conta conta = new Conta(100, "12345-6", empresa.getIdEmpresa(), "corrente");
        conta.abrirConta();
        conta.depositar(new BigDecimal("1500.00"));
        conta.sacar(new BigDecimal("250.00"));

        System.out.println("Status da empresa: " + empresa.getStatus());
        System.out.println("Saldo final da conta: R$ " + conta.consultarSaldo());

        Transacao transacao = new Transacao(1, 100, 200, new BigDecimal("300.00"),
                "pix", "Pagamento de fornecedor");
        transacao.realizarTransacao();
        transacao.confirmarTransacao();
        System.out.println("Status da transacao: " + transacao.consultarStatus());
    }
}
