package br.com.finsmart.model;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoFinanceiro {

    private int idProduto;
    private String nomeProduto;
    private String tipo; // "credito", "investimento", "conta"
    private BigDecimal taxaBase;
    private String descricao;
    private boolean ativo;

    // Construtor padrão
    public ProdutoFinanceiro() {
    }

    // Construtor com parâmetros
    public ProdutoFinanceiro(int idProduto, String nomeProduto, String tipo,
                              BigDecimal taxaBase, String descricao) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.tipo = tipo;
        this.taxaBase = taxaBase;
        this.descricao = descricao;
        this.ativo = true;
    }

    // Cadastra um novo produto financeiro no catálogo
    public void cadastrarProduto() {
        System.out.println("Cadastrando produto financeiro: " + nomeProduto);
    }

    // Ativa o produto para contratação pelas empresas
    public void ativarProduto() {
        System.out.println("Ativando produto financeiro: " + nomeProduto);
    }

    // Desativa o produto impedindo novas contratações
    public void desativarProduto() {
        System.out.println("Desativando produto financeiro: " + nomeProduto);
    }

    // Atualiza a taxa base do produto
    public void atualizarTaxaBase(BigDecimal novaTaxa) {
        System.out.println("Atualizando taxa base do produto " + nomeProduto
                + " para: " + novaTaxa + "%");
    }

    // Lista todos os produtos disponíveis por tipo
    public static List<ProdutoFinanceiro> listarPorTipo(String tipo) {
        System.out.println("Listando produtos financeiros do tipo: " + tipo);
        return null;
    }

    // Getters e Setters
    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getTaxaBase() { return taxaBase; }
    public void setTaxaBase(BigDecimal taxaBase) { this.taxaBase = taxaBase; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
