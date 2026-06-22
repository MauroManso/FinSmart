# Checklist - Classes do Sistema FinSmart

## 1. Classes Definidas para o Projeto Fintech

| # | Classe | Pacote | Descrição |
|---|--------|--------|-----------|
| 1 | `Login` | `br.com.finsmart.model` | Gerencia autenticação de usuários na plataforma |
| 2 | `Usuario` | `br.com.finsmart.model` | Representa os usuários do sistema (proprietários, analistas, admins) |
| 3 | `Empresa` | `br.com.finsmart.model` | Representa as empresas cadastradas que utilizam a plataforma |
| 4 | `Conta` | `br.com.finsmart.model` | Representa contas bancárias digitais das empresas |
| 5 | `Transacao` | `br.com.finsmart.model` | Registra transações financeiras (transferência, depósito, saque, PIX) |
| 6 | `SolicitacaoCredito` | `br.com.finsmart.model` | Gerencia solicitações de crédito das empresas |
| 7 | `Documento` | `br.com.finsmart.model` | Armazena referências a documentos enviados pelas empresas |
| 8 | `ProdutoFinanceiro` | `br.com.finsmart.model` | Catálogo de produtos financeiros oferecidos pela plataforma |
| 9 | `AnaliseCredito` | `br.com.finsmart.model` | Detalhes técnicos da análise de crédito com IA |
| 10 | `Contrato` | `br.com.finsmart.model` | Relacionamento entre empresas e produtos financeiros contratados |
| 11 | `MovimentacaoFinanceira` | `br.com.finsmart.model` | Registro detalhado de todas as movimentações em contas |

**Total de classes (excluindo Login/Autenticação): 10** ✅ *(requisito mínimo: 4)*

---

## 2. Atributos Codificados e Tipos de Dados

### Login.java
| Atributo | Tipo Java | Descrição |
|----------|-----------|-----------|
| `username` | `String` | Nome de usuário para login |
| `password` | `String` | Senha do usuário |

### Usuario.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idUsuario` | `int` | NUMBER / INTEGER | Identificador único do usuário (PK) |
| `nome` | `String` | VARCHAR2(255) | Nome completo do usuário |
| `email` | `String` | VARCHAR2(255) | E-mail único para login |
| `telefone` | `String` | VARCHAR2(20) | Contato telefônico |
| `cpf` | `String` | VARCHAR2(14) | CPF único do usuário |
| `tipoUsuario` | `String` | VARCHAR2(20) | Tipo de acesso: "proprietario", "analista", "admin" |
| `dataCriacao` | `LocalDateTime` | TIMESTAMP | Data e hora do registro |
| `status` | `String` | VARCHAR2(20) | Estado: "ativo" ou "inativo" |

### Empresa.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idEmpresa` | `int` | NUMBER / INTEGER | Identificador único da empresa (PK) |
| `nomeEmpresa` | `String` | VARCHAR2(255) | Nome fantasia da empresa |
| `cnpj` | `String` | VARCHAR2(18) | CNPJ único da empresa |
| `razaoSocial` | `String` | VARCHAR2(255) | Razão social formal |
| `setor` | `String` | VARCHAR2(100) | Segmento de negócio |
| `dataFundacao` | `LocalDate` | DATE | Data de constituição da empresa |
| `idUsuario` | `int` | NUMBER / INTEGER | FK - referência ao proprietário |
| `status` | `String` | VARCHAR2(20) | Estado: "ativa" ou "inativa" |
| `dataCriacao` | `LocalDateTime` | TIMESTAMP | Data e hora do cadastro |

### Conta.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idConta` | `int` | NUMBER / INTEGER | Identificador único da conta (PK) |
| `numeroConta` | `String` | VARCHAR2(20) | Número único da conta |
| `idEmpresa` | `int` | NUMBER / INTEGER | FK - empresa proprietária da conta |
| `tipoConta` | `String` | VARCHAR2(20) | Tipo: "corrente" ou "poupanca" |
| `saldo` | `BigDecimal` | NUMBER(15,2) | Saldo atual da conta |
| `dataAbertura` | `LocalDateTime` | TIMESTAMP | Data e hora da abertura |
| `status` | `String` | VARCHAR2(20) | Estado: "ativa", "bloqueada" ou "encerrada" |

### Transacao.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idTransacao` | `int` | NUMBER / INTEGER | Identificador único da transação (PK) |
| `idContaOrigem` | `int` | NUMBER / INTEGER | FK - conta que enviou o recurso |
| `idContaDestino` | `int` | NUMBER / INTEGER | FK - conta que recebeu o recurso |
| `valor` | `BigDecimal` | NUMBER(15,2) | Valor da transação |
| `tipo` | `String` | VARCHAR2(20) | Tipo: "transferencia", "deposito", "saque", "pix" |
| `dataTransacao` | `LocalDateTime` | TIMESTAMP | Data e hora da transação |
| `descricao` | `String` | VARCHAR2(500) | Descrição adicional da transação |
| `status` | `String` | VARCHAR2(20) | Estado: "concluida", "pendente" ou "rejeitada" |

### SolicitacaoCredito.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idSolicitacao` | `int` | NUMBER / INTEGER | Identificador único da solicitação (PK) |
| `idEmpresa` | `int` | NUMBER / INTEGER | FK - empresa solicitante |
| `valorSolicitado` | `BigDecimal` | NUMBER(15,2) | Valor do crédito solicitado |
| `taxaJuros` | `BigDecimal` | NUMBER(5,2) | Taxa de juros aplicada |
| `prazoMeses` | `int` | NUMBER(3) | Prazo em meses para pagamento |
| `statusAprovacao` | `String` | VARCHAR2(20) | Estado: "pendente", "aprovada", "rejeitada", "cancelada" |
| `dataSolicitacao` | `LocalDateTime` | TIMESTAMP | Data e hora da solicitação |
| `dataAnalise` | `LocalDateTime` | TIMESTAMP | Data e hora da análise |
| `scoreCredito` | `int` | NUMBER(4) | Pontuação de crédito calculada |

### Documento.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idDocumento` | `int` | NUMBER / INTEGER | Identificador único do documento (PK) |
| `idEmpresa` | `int` | NUMBER / INTEGER | FK - empresa proprietária do documento |
| `tipoDocumento` | `String` | VARCHAR2(100) | Classificação do documento |
| `caminhoArquivo` | `String` | VARCHAR2(500) | Localização do arquivo armazenado |
| `dataEnvio` | `LocalDateTime` | TIMESTAMP | Data e hora do envio |
| `statusValidacao` | `String` | VARCHAR2(20) | Estado: "pendente", "validado" ou "rejeitado" |
| `observacoes` | `String` | VARCHAR2(1000) | Notas sobre validação |

### ProdutoFinanceiro.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idProduto` | `int` | NUMBER / INTEGER | Identificador único do produto (PK) |
| `nomeProduto` | `String` | VARCHAR2(255) | Nome do produto financeiro |
| `tipo` | `String` | VARCHAR2(20) | Tipo: "credito", "investimento", "conta" |
| `taxaBase` | `BigDecimal` | NUMBER(5,2) | Taxa base do produto |
| `descricao` | `String` | VARCHAR2(1000) | Descrição detalhada do produto |
| `ativo` | `boolean` | CHAR(1) / BOOLEAN | Disponibilidade: true=ativo, false=inativo |

### AnaliseCredito.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idAnalise` | `int` | NUMBER / INTEGER | Identificador único da análise (PK) |
| `idSolicitacao` | `int` | NUMBER / INTEGER | FK - solicitação de crédito (1:1) |
| `resultadoIa` | `String` | VARCHAR2(500) | Resultado do processamento pela IA |
| `dadosAlternativos` | `String` | CLOB / JSON | Dados alternativos em formato JSON |
| `riscoScore` | `BigDecimal` | NUMBER(5,2) | Pontuação de risco (0 a 100) |
| `dataAnalise` | `LocalDateTime` | TIMESTAMP | Data e hora da análise |
| `idUsuarioAnalista` | `int` | NUMBER / INTEGER | FK - analista responsável |

### Contrato.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idContrato` | `int` | NUMBER / INTEGER | Identificador único do contrato (PK) |
| `idEmpresa` | `int` | NUMBER / INTEGER | FK - empresa contratante |
| `idProduto` | `int` | NUMBER / INTEGER | FK - produto financeiro contratado |
| `valorContratado` | `BigDecimal` | NUMBER(15,2) | Valor total contratado |
| `dataInicio` | `LocalDate` | DATE | Data de início do contrato |
| `dataFim` | `LocalDate` | DATE | Data de término do contrato |
| `status` | `String` | VARCHAR2(20) | Estado: "ativo", "encerrado" ou "suspenso" |

### MovimentacaoFinanceira.java
| Atributo | Tipo Java | Tipo SQL Equivalente | Descrição |
|----------|-----------|----------------------|-----------|
| `idMovimentacao` | `int` | NUMBER / INTEGER | Identificador único da movimentação (PK) |
| `idConta` | `int` | NUMBER / INTEGER | FK - conta em que ocorreu a movimentação |
| `tipoMovimentacao` | `String` | VARCHAR2(20) | Tipo: "entrada", "saida", "juros", "tarifa" |
| `valor` | `BigDecimal` | NUMBER(15,2) | Valor da movimentação |
| `dataMovimentacao` | `LocalDateTime` | TIMESTAMP | Data e hora da movimentação |
| `descricao` | `String` | VARCHAR2(500) | Descrição da movimentação |

---

## 3. Métodos Codificados e Seus Objetivos

### Login.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `doLogin()` | `void` | Realiza o login do usuário na plataforma |
| `doLogout()` | `void` | Realiza o logout do usuário da plataforma |
| `validarCredenciais()` | `boolean` | Valida as credenciais informadas pelo usuário |
| `recuperarSenha(String email)` | `void` | Solicita redefinição de senha via e-mail |

### Usuario.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `cadastrar()` | `void` | Cadastra um novo usuário no sistema |
| `atualizar()` | `void` | Atualiza os dados pessoais do usuário |
| `inativar()` | `void` | Inativa o usuário impedindo acesso ao sistema |
| `buscarPorEmail(String email)` | `Usuario` | Localiza um usuário pelo endereço de e-mail |
| `buscarPorCpf(String cpf)` | `Usuario` | Localiza um usuário pelo número de CPF |

### Empresa.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `cadastrar()` | `void` | Cadastra uma nova empresa na plataforma |
| `atualizar()` | `void` | Atualiza os dados cadastrais da empresa |
| `inativar()` | `void` | Inativa a empresa desabilitando o acesso à plataforma |
| `buscarPorCnpj(String cnpj)` *(static)* | `Empresa` | Localiza uma empresa pelo CNPJ |
| `listarContas()` | `void` | Lista todas as contas vinculadas à empresa |

### Conta.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `abrirConta()` | `void` | Abre uma nova conta digital para a empresa |
| `bloquearConta()` | `void` | Bloqueia a conta impedindo movimentações financeiras |
| `encerrarConta()` | `void` | Encerra definitivamente a conta bancária |
| `depositar(BigDecimal valor)` | `void` | Registra um depósito na conta |
| `sacar(BigDecimal valor)` | `void` | Registra um saque na conta |
| `consultarSaldo()` | `BigDecimal` | Retorna o saldo atual da conta |

### Transacao.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `realizarTransacao()` | `void` | Executa a transação financeira entre contas |
| `cancelarTransacao()` | `void` | Cancela/rejeita a transação antes de ser concluída |
| `confirmarTransacao()` | `void` | Confirma e conclui a transação financeira |
| `consultarStatus()` | `String` | Retorna o status atual da transação |

### SolicitacaoCredito.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `registrarSolicitacao()` | `void` | Registra a solicitação de crédito da empresa |
| `aprovar()` | `void` | Aprova a solicitação de crédito |
| `rejeitar()` | `void` | Rejeita a solicitação de crédito |
| `cancelar()` | `void` | Cancela a solicitação de crédito |
| `calcularScore()` | `int` | Calcula o score de crédito da empresa |

### Documento.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `enviarDocumento()` | `void` | Realiza o envio e registro do documento no sistema |
| `validarDocumento()` | `void` | Valida o documento enviado pela empresa |
| `rejeitarDocumento(String motivo)` | `void` | Rejeita o documento e registra o motivo |
| `consultarStatus()` | `String` | Retorna o status de validação do documento |

### ProdutoFinanceiro.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `cadastrarProduto()` | `void` | Cadastra um novo produto financeiro no catálogo |
| `ativarProduto()` | `void` | Ativa o produto para contratação pelas empresas |
| `desativarProduto()` | `void` | Desativa o produto impedindo novas contratações |
| `atualizarTaxaBase(BigDecimal novaTaxa)` | `void` | Atualiza a taxa base do produto financeiro |
| `listarPorTipo(String tipo)` *(static)* | `List<ProdutoFinanceiro>` | Lista todos os produtos disponíveis por tipo |

### AnaliseCredito.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `executarAnalise()` | `void` | Executa a análise de crédito usando IA |
| `calcularRiscoScore()` | `BigDecimal` | Calcula o score de risco com base nos dados alternativos |
| `registrarResultadoIa(String resultado)` | `void` | Registra o resultado da análise feita pela IA |
| `consultarResultado()` | `String` | Retorna o resultado da análise de crédito |

### Contrato.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `assinarContrato()` | `void` | Formaliza a contratação do produto financeiro pela empresa |
| `suspenderContrato()` | `void` | Suspende temporariamente o contrato |
| `encerrarContrato()` | `void` | Encerra definitivamente o contrato |
| `renovarContrato(LocalDate novaDataFim)` | `void` | Renova o contrato com nova data de fim |

### MovimentacaoFinanceira.java
| Método | Retorno | Objetivo |
|--------|---------|----------|
| `registrarMovimentacao()` | `void` | Registra uma nova movimentação financeira na conta |
| `consultarExtrato(int idConta)` *(static)* | `List<MovimentacaoFinanceira>` | Lista o extrato de movimentações de uma conta |
| `filtrarMovimentacoes(int idConta, LocalDateTime, LocalDateTime, String)` *(static)* | `List<MovimentacaoFinanceira>` | Filtra movimentações por período e tipo |

---

## Resumo de Conformidade

- [x] Mínimo de 4 classes codificadas (excluindo Login/Autenticação) — **10 classes implementadas**
- [x] Atributos definidos com tipos de dados corretos para cada classe
- [x] Métodos codificados com `System.out.println` indicando qual método está sendo executado
- [x] Construtores padrão e com parâmetros em todas as classes
- [x] Getters e Setters para todos os atributos
- [x] Classes baseadas no modelo de dados do projeto FinSmart (SQL + modelo lógico)
