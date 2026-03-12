# Modelo de Dados Completo - FinSmart

## 1. Visão Geral

O modelo de dados da FinSmart foi desenvolvido para suportar uma plataforma digital integrada de serviços financeiros para pequenas e médias empresas brasileiras. O modelo foi estruturado em duas perspectivas:

- **Modelo Lógico**: Define as entidades, atributos e relacionamentos de forma independente da tecnologia
- **Modelo Físico**: Define a implementação específica em banco de dados relacional (PostgreSQL)

## 2. Modelo Lógico (ER Diagram)

### 2.1 Entidades Principais

#### **Usuarios**
Representa os usuários do sistema. Pode ser proprietário de empresa, analista de crédito ou administrador.

- `id_usuario`: Identificador único (PK)
- `nome`: Nome completo do usuário
- `email`: Email único para login
- `telefone`: Contato telefônico
- `cpf`: CPF único do usuário
- `tipo_usuario`: Tipo de acesso (proprietario, analista, admin)
- `data_criacao`: Data de registro
- `status`: Ativo ou inativo

#### **Empresas**
Representa as empresas cadastradas que utilizam a plataforma.

- `id_empresa`: Identificador único (PK)
- `nome_empresa`: Nome da empresa
- `cnpj`: CNPJ único e identificador legal
- `razao_social`: Razão social formal
- `setor`: Segmento de negócio
- `data_fundacao`: Data de constituição
- `id_usuario`: Referência ao proprietário (FK)
- `status`: Ativa ou inativa
- `data_criacao`: Data de cadastro

#### **Contas**
Representa contas bancárias digitais das empresas.

- `id_conta`: Identificador único (PK)
- `numero_conta`: Número único da conta
- `id_empresa`: Referência à empresa (FK)
- `tipo_conta`: Tipo de conta (corrente, poupança)
- `saldo`: Saldo atual da conta
- `data_abertura`: Data da abertura
- `status`: Ativa, bloqueada ou encerrada

#### **Transacoes**
Registra todas as transações financeiras do sistema.

- `id_transacao`: Identificador único (PK)
- `id_conta_origem`: Conta que enviou o recurso (FK)
- `id_conta_destino`: Conta que recebeu o recurso (FK)
- `valor`: Valor da transação
- `tipo`: Tipo de transação (transferência, depósito, saque, PIX)
- `data_transacao`: Data/hora da transação
- `descricao`: Descrição adicional
- `status`: Concluída, pendente ou rejeitada

#### **Solicitacoes_Credito**
Gerencia solicitações de crédito das empresas.

- `id_solicitacao`: Identificador único (PK)
- `id_empresa`: Referência à empresa (FK)
- `valor_solicitado`: Valor do crédito solicitado
- `taxa_juros`: Taxa de juros aplicada
- `prazo_meses`: Prazo em meses para pagamento
- `status_aprovacao`: Pendente, aprovada, rejeitada ou cancelada
- `data_solicitacao`: Data da solicitação
- `data_analise`: Data da análise
- `score_credito`: Pontuação de crédito calculada

#### **Documentos**
Armazena referências a documentos enviados pelas empresas.

- `id_documento`: Identificador único (PK)
- `id_empresa`: Referência à empresa (FK)
- `tipo_documento`: Classificação do documento
- `caminho_arquivo`: Localização do arquivo armazenado
- `data_envio`: Data do envio
- `status_validacao`: Pendente, validado ou rejeitado
- `observacoes`: Notas sobre validação

#### **Analises_Credito**
Detalhes técnicos da análise de crédito usando IA.

- `id_analise`: Identificador único (PK)
- `id_solicitacao`: Referência à solicitação (FK, 1:1)
- `resultado_ia`: Resultado do processamento IA
- `dados_alternativos`: Dados alternativos em formato JSON
- `risco_score`: Pontuação de risco (0-100)
- `data_analise`: Data da análise
- `id_usuario_analista`: Referência ao analista (FK)

#### **Produtos_Financeiros**
Catálogo de produtos financeiros oferecidos.

- `id_produto`: Identificador único (PK)
- `nome_produto`: Nome do produto
- `tipo`: Tipo de produto (crédito, investimento, conta)
- `taxa_base`: Taxa base do produto
- `descricao`: Descrição detalhada
- `ativo`: Disponibilidade do produto

#### **Contratos**
Relacionamento N:N entre empresas e produtos financeiros.

- `id_contrato`: Identificador único (PK)
- `id_empresa`: Referência à empresa (FK)
- `id_produto`: Referência ao produto (FK)
- `valor_contratado`: Valor total contratado
- `data_inicio`: Início do contrato
- `data_fim`: Término do contrato
- `status`: Ativo, encerrado ou suspenso

#### **Movimentacoes_Financeiras**
Registro detalhado de todas as movimentações em contas.

- `id_movimentacao`: Identificador único (PK)
- `id_conta`: Referência à conta (FK)
- `tipo_movimentacao`: Entrada, saída, juros, tarifa
- `valor`: Valor da movimentação
- `data_movimentacao`: Data/hora da movimentação
- `descricao`: Descrição da movimentação

### 2.2 Relacionamentos

| Origem | Destino | Cardinalidade | Descrição |
|--------|---------|---------------|-----------|
| Usuarios | Empresas | 1:N | Um usuário pode ser proprietário de várias empresas |
| Empresas | Contas | 1:N | Uma empresa pode ter múltiplas contas |
| Contas | Transacoes | 1:N | Uma conta pode ter múltiplas transações como origem ou destino |
| Empresas | Solicitacoes_Credito | 1:N | Uma empresa pode solicitar múltiplos créditos |
| Empresas | Documentos | 1:N | Uma empresa pode enviar vários documentos |
| Solicitacoes_Credito | Analises_Credito | 1:1 | Cada solicitação tem uma análise única |
| Empresas | Produtos_Financeiros | N:N | Empresas contratam múltiplos produtos (através de Contratos) |
| Contas | Movimentacoes_Financeiras | 1:N | Uma conta tem múltiplas movimentações |
| Usuarios | Analises_Credito | 1:N | Um analista pode realizar múltiplas análises |

## 3. Modelo Físico (Schema Relacional)

### 3.1 Definição de Tabelas com DDL SQL

```sql
-- Tabela de Usuários
CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    tipo_usuario ENUM('proprietario', 'analista', 'admin') NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ativo', 'inativo') DEFAULT 'ativo',
    INDEX idx_usuarios_email (email),
    INDEX idx_usuarios_cpf (cpf)
);

-- Tabela de Empresas
CREATE TABLE empresas (
    id_empresa SERIAL PRIMARY KEY,
    nome_empresa VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    razao_social VARCHAR(255),
    setor VARCHAR(100),
    data_fundacao DATE,
    id_usuario INT NOT NULL,
    status ENUM('ativa', 'inativa') DEFAULT 'ativa',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    INDEX idx_empresas_cnpj (cnpj),
    INDEX idx_empresas_id_usuario (id_usuario)
);

-- Tabela de Contas
CREATE TABLE contas (
    id_conta SERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    id_empresa INT NOT NULL,
    tipo_conta ENUM('corrente', 'poupanca') DEFAULT 'corrente',
    saldo DECIMAL(15, 2) DEFAULT 0,
    data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ativa', 'bloqueada', 'encerrada') DEFAULT 'ativa',
    FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa),
    INDEX idx_contas_numero (numero_conta),
    INDEX idx_contas_id_empresa (id_empresa)
);

-- Tabela de Transações
CREATE TABLE transacoes (
    id_transacao SERIAL PRIMARY KEY,
    id_conta_origem INT NOT NULL,
    id_conta_destino INT NOT NULL,
    valor DECIMAL(15, 2) NOT NULL CHECK (valor > 0),
    tipo ENUM('transferencia', 'deposito', 'saque', 'pix') NOT NULL,
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao TEXT,
    status ENUM('concluida', 'pendente', 'rejeitada') DEFAULT 'concluida',
    FOREIGN KEY (id_conta_origem) REFERENCES contas(id_conta),
    FOREIGN KEY (id_conta_destino) REFERENCES contas(id_conta),
    INDEX idx_transacoes_data (data_transacao),
    INDEX idx_transacoes_conta_origem (id_conta_origem),
    INDEX idx_transacoes_conta_destino (id_conta_destino)
);

-- Tabela de Solicitações de Crédito
CREATE TABLE solicitacoes_credito (
    id_solicitacao SERIAL PRIMARY KEY,
    id_empresa INT NOT NULL,
    valor_solicitado DECIMAL(15, 2) NOT NULL,
    taxa_juros DECIMAL(5, 2),
    prazo_meses INT,
    status_aprovacao ENUM('pendente', 'aprovada', 'rejeitada', 'cancelada') DEFAULT 'pendente',
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_analise TIMESTAMP,
    score_credito INT,
    FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa),
    INDEX idx_solicitacoes_status (status_aprovacao),
    INDEX idx_solicitacoes_id_empresa (id_empresa)
);

-- Tabela de Documentos
CREATE TABLE documentos (
    id_documento SERIAL PRIMARY KEY,
    id_empresa INT NOT NULL,
    tipo_documento VARCHAR(100) NOT NULL,
    caminho_arquivo VARCHAR(500) NOT NULL,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_validacao ENUM('pendente', 'validado', 'rejeitado') DEFAULT 'pendente',
    observacoes TEXT,
    FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa),
    INDEX idx_documentos_id_empresa (id_empresa)
);

-- Tabela de Análises de Crédito
CREATE TABLE analises_credito (
    id_analise SERIAL PRIMARY KEY,
    id_solicitacao INT NOT NULL UNIQUE,
    resultado_ia VARCHAR(500),
    dados_alternativos JSON,
    risco_score DECIMAL(5, 2),
    data_analise TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario_analista INT,
    FOREIGN KEY (id_solicitacao) REFERENCES solicitacoes_credito(id_solicitacao),
    FOREIGN KEY (id_usuario_analista) REFERENCES usuarios(id_usuario),
    INDEX idx_analises_id_solicitacao (id_solicitacao)
);

-- Tabela de Produtos Financeiros
CREATE TABLE produtos_financeiros (
    id_produto SERIAL PRIMARY KEY,
    nome_produto VARCHAR(255) NOT NULL,
    tipo ENUM('credito', 'investimento', 'conta') NOT NULL,
    taxa_base DECIMAL(5, 2),
    descricao TEXT,
    ativo BOOLEAN DEFAULT true
);

-- Tabela de Contratos
CREATE TABLE contratos (
    id_contrato SERIAL PRIMARY KEY,
    id_empresa INT NOT NULL,
    id_produto INT NOT NULL,
    valor_contratado DECIMAL(15, 2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    status ENUM('ativo', 'encerrado', 'suspenso') DEFAULT 'ativo',
    FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa),
    FOREIGN KEY (id_produto) REFERENCES produtos_financeiros(id_produto),
    INDEX idx_contratos_status (status),
    INDEX idx_contratos_id_empresa (id_empresa)
);

-- Tabela de Movimentações Financeiras
CREATE TABLE movimentacoes_financeiras (
    id_movimentacao SERIAL PRIMARY KEY,
    id_conta INT NOT NULL,
    tipo_movimentacao ENUM('entrada', 'saida', 'juros', 'tarifa') NOT NULL,
    valor DECIMAL(15, 2) NOT NULL CHECK (valor > 0),
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR(500),
    FOREIGN KEY (id_conta) REFERENCES contas(id_conta),
    INDEX idx_movimentacoes_data (data_movimentacao),
    INDEX idx_movimentacoes_id_conta (id_conta)
);
```

### 3.2 Tipos de Dados Utilizados

| Tipo | Descrição | Uso |
|------|-----------|-----|
| SERIAL | Inteiro auto-incrementado | Chaves primárias |
| VARCHAR(n) | Texto variável | Nomes, emails, descrições |
| DECIMAL(15,2) | Número decimal preciso | Valores monetários |
| DATE | Data sem hora | Datas de contrato, fundação |
| TIMESTAMP | Data e hora | Auditoria, transações |
| ENUM | Enumeração de valores | Status, tipos de operação |
| JSON | Dados estruturados | Dados alternativos de análise |
| BOOLEAN | Verdadeiro/Falso | Flags de ativação |

### 3.3 Constraints Aplicados

| Constraint | Propósito |
|-----------|----------|
| PRIMARY KEY | Garante unicidade de registro |
| FOREIGN KEY | Mantém integridade referencial |
| UNIQUE | Garante valores únicos (email, CPF, CNPJ) |
| NOT NULL | Campo obrigatório |
| CHECK | Validação de domínio (valores > 0) |
| DEFAULT | Valor padrão automático |

### 3.4 Índices para Performance

| Tabela | Índice | Propósito |
|--------|--------|----------|
| usuarios | email, cpf | Busca rápida por login e identificação |
| empresas | cnpj, id_usuario | Consultas por CNPJ e proprietário |
| contas | numero_conta, id_empresa | Acesso à conta e contas da empresa |
| transacoes | data_transacao, id_conta_origem, id_conta_destino | Filtros de transação por período |
| solicitacoes_credito | status_aprovacao, id_empresa | Consultas de status de crédito |
| contratos | status, id_empresa | Filtros de contratos ativos |
| movimentacoes_financeiras | data_movimentacao, id_conta | Histório de movimentações |

## 4. Fluxos de Dados Principais

### 4.1 Fluxo de Abertura de Conta
```
Usuario (Proprietário) 
  → Registra na plataforma
  → Cadastra Empresa
  → Sistema cria Conta digital
  → Usuário ativa Conta (status='ativa')
```

### 4.2 Fluxo de Solicitação de Crédito
```
Empresa solicita Crédito
  → Cria Solicitacao_Credito (status='pendente')
  → Sistema coleta Documentos
  → IA executa Analise_Credito (com dados_alternativos)
  → Calcula score_credito e risco_score
  → Atualiza status_aprovacao
  → Se aprovado → Cria Contrato com Produto_Financeiro
  → Se aprovado → Valor creditado em Conta
```

### 4.3 Fluxo de Transação Financeira
```
Empresa origem → Transacao (transferência, PIX, etc.)
  → Contas reduz saldo
  → Cria Movimentacoes_Financeiras (saida)
  → Empresa destino
  → Contas aumenta saldo
  → Cria Movimentacoes_Financeiras (entrada)
```

## 5. Considerações de Segurança e Conformidade

### 5.1 Princípios Implementados

- **Integridade de Dados**: Foreign keys garantem referências válidas
- **Atomicidade**: Transações ACID protegem operações financeiras
- **Auditoria**: Timestamps em todas as operações
- **Isolamento de Dados**: Dados de empresas separados por id_empresa
- **Conformidade Regulatória**: Campos para CNPJ, CPF, documentação

### 5.2 Recomendações Adicionais

1. **Criptografia**: Armazenar CPF e dados sensíveis criptografados
2. **Backup**: Implementar estratégia de backup automático
3. **Versionamento**: Auditar alterações em dados críticos
4. **Rate Limiting**: Proteger APIs contra abuso
5. **Compliance**: Adequação a LGPD (Lei Geral de Proteção de Dados)

## 6. Escalabilidade e Performance

### 6.1 Estratégias Recomendadas

- **Particionamento de Tabelas**: Particionar `transacoes` por data
- **Replicação**: Read replicas para consultas analíticas
- **Cache**: Redis para saldos e status frequentemente consultados
- **Normalização**: Estrutura normalizada em 3FN
- **Denormalização Estratégica**: Saldos em cache para performance

### 6.2 Crescimento Esperado

Modelo preparado para:
- Milhões de transações diárias
- Milhares de empresas cadastradas
- Centenas de milhares de usuários
- Análises de crédito paralelas com IA

## 7. Migração de Dados Legados

Para empresas migrando de bancos tradicionais:

1. Extração de dados do sistema legado
2. Transformação para formato FinSmart
3. Carregamento em ambiente de staging
4. Validação e testes
5. Migração cutover com sincronização
6. Verificação pós-migração

---

**Versão**: 1.0  
**Data**: Novembro 2025  
**Status**: Modelo Proposto
