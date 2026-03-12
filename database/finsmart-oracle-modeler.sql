-- ============================================================================
-- FinSmart: DDL Code for Oracle Data Modeler (Logical Model)
-- Platform: Oracle Data Modeler
-- Database: Oracle 21c / 19c / 12c
-- Created: November 2025
-- Purpose: Import this file into Oracle Data Modeler to generate the logical model
-- ============================================================================

-- ============================================================================
-- 1. CREATE TABLES (Logical Model)
-- ============================================================================

-- Table: USUARIOS
CREATE TABLE usuarios (
    id_usuario NUMBER PRIMARY KEY,
    nome VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    telefone VARCHAR2(20),
    cpf VARCHAR2(14) NOT NULL,
    tipo_usuario VARCHAR2(20) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR2(20) DEFAULT 'ativo'
);

-- Table: EMPRESAS
CREATE TABLE empresas (
    id_empresa NUMBER PRIMARY KEY,
    nome_empresa VARCHAR2(255) NOT NULL,
    cnpj VARCHAR2(18) NOT NULL,
    razao_social VARCHAR2(255),
    setor VARCHAR2(100),
    data_fundacao DATE,
    id_usuario NUMBER NOT NULL,
    status VARCHAR2(20) DEFAULT 'ativa',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: CONTAS
CREATE TABLE contas (
    id_conta NUMBER PRIMARY KEY,
    numero_conta VARCHAR2(20) NOT NULL,
    id_empresa NUMBER NOT NULL,
    tipo_conta VARCHAR2(20) DEFAULT 'corrente',
    saldo NUMBER(15,2) DEFAULT 0,
    data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR2(20) DEFAULT 'ativa'
);

-- Table: TRANSACOES
CREATE TABLE transacoes (
    id_transacao NUMBER PRIMARY KEY,
    id_conta_origem NUMBER NOT NULL,
    id_conta_destino NUMBER NOT NULL,
    valor NUMBER(15,2) NOT NULL,
    tipo VARCHAR2(20) NOT NULL,
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR2(500),
    status VARCHAR2(20) DEFAULT 'concluida'
);

-- Table: SOLICITACOES_CREDITO
CREATE TABLE solicitacoes_credito (
    id_solicitacao NUMBER PRIMARY KEY,
    id_empresa NUMBER NOT NULL,
    valor_solicitado NUMBER(15,2) NOT NULL,
    taxa_juros NUMBER(5,2) DEFAULT 0,
    prazo_meses NUMBER(3) DEFAULT 12,
    status_aprovacao VARCHAR2(20) DEFAULT 'pendente',
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_analise TIMESTAMP,
    score_credito NUMBER(4)
);

-- Table: DOCUMENTOS
CREATE TABLE documentos (
    id_documento NUMBER PRIMARY KEY,
    id_empresa NUMBER NOT NULL,
    tipo_documento VARCHAR2(100) NOT NULL,
    caminho_arquivo VARCHAR2(500) NOT NULL,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_validacao VARCHAR2(20) DEFAULT 'pendente',
    observacoes VARCHAR2(1000)
);

-- Table: PRODUTOS_FINANCEIROS
CREATE TABLE produtos_financeiros (
    id_produto NUMBER PRIMARY KEY,
    nome_produto VARCHAR2(255) NOT NULL,
    tipo VARCHAR2(20) NOT NULL,
    taxa_base NUMBER(5,2) DEFAULT 0,
    descricao VARCHAR2(1000),
    ativo CHAR(1) DEFAULT 'Y'
);

-- Table: ANALISES_CREDITO
CREATE TABLE analises_credito (
    id_analise NUMBER PRIMARY KEY,
    id_solicitacao NUMBER NOT NULL,
    resultado_ia VARCHAR2(500),
    dados_alternativos CLOB,
    risco_score NUMBER(5,2),
    data_analise TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario_analista NUMBER
);

-- Table: CONTRATOS
CREATE TABLE contratos (
    id_contrato NUMBER PRIMARY KEY,
    id_empresa NUMBER NOT NULL,
    id_produto NUMBER NOT NULL,
    valor_contratado NUMBER(15,2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    status VARCHAR2(20) DEFAULT 'ativo'
);

-- Table: MOVIMENTACOES_FINANCEIRAS
CREATE TABLE movimentacoes_financeiras (
    id_movimentacao NUMBER PRIMARY KEY,
    id_conta NUMBER NOT NULL,
    tipo_movimentacao VARCHAR2(20) NOT NULL,
    valor NUMBER(15,2) NOT NULL,
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR2(500)
);

-- ============================================================================
-- 2. ADD PRIMARY KEY CONSTRAINTS (Explicit)
-- ============================================================================

ALTER TABLE usuarios ADD CONSTRAINT pk_usuarios PRIMARY KEY (id_usuario);
ALTER TABLE empresas ADD CONSTRAINT pk_empresas PRIMARY KEY (id_empresa);
ALTER TABLE contas ADD CONSTRAINT pk_contas PRIMARY KEY (id_conta);
ALTER TABLE transacoes ADD CONSTRAINT pk_transacoes PRIMARY KEY (id_transacao);
ALTER TABLE solicitacoes_credito ADD CONSTRAINT pk_solicitacoes_credito PRIMARY KEY (id_solicitacao);
ALTER TABLE documentos ADD CONSTRAINT pk_documentos PRIMARY KEY (id_documento);
ALTER TABLE produtos_financeiros ADD CONSTRAINT pk_produtos_financeiros PRIMARY KEY (id_produto);
ALTER TABLE analises_credito ADD CONSTRAINT pk_analises_credito PRIMARY KEY (id_analise);
ALTER TABLE contratos ADD CONSTRAINT pk_contratos PRIMARY KEY (id_contrato);
ALTER TABLE movimentacoes_financeiras ADD CONSTRAINT pk_movimentacoes_financeiras PRIMARY KEY (id_movimentacao);

-- ============================================================================
-- 3. ADD UNIQUE CONSTRAINTS
-- ============================================================================

ALTER TABLE usuarios ADD CONSTRAINT uk_usuarios_email UNIQUE (email);
ALTER TABLE usuarios ADD CONSTRAINT uk_usuarios_cpf UNIQUE (cpf);
ALTER TABLE empresas ADD CONSTRAINT uk_empresas_cnpj UNIQUE (cnpj);
ALTER TABLE contas ADD CONSTRAINT uk_contas_numero UNIQUE (numero_conta);
ALTER TABLE analises_credito ADD CONSTRAINT uk_analises_solicitacao UNIQUE (id_solicitacao);

-- ============================================================================
-- 4. ADD FOREIGN KEY RELATIONSHIPS (Relationships)
-- ============================================================================

-- Relationship: Usuarios 1:N Empresas
ALTER TABLE empresas ADD CONSTRAINT fk_empresas_usuarios 
FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);

-- Relationship: Empresas 1:N Contas
ALTER TABLE contas ADD CONSTRAINT fk_contas_empresas 
FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa);

-- Relationship: Contas 1:N Transacoes (Origem)
ALTER TABLE transacoes ADD CONSTRAINT fk_transacoes_contas_origem 
FOREIGN KEY (id_conta_origem) REFERENCES contas(id_conta);

-- Relationship: Contas 1:N Transacoes (Destino)
ALTER TABLE transacoes ADD CONSTRAINT fk_transacoes_contas_destino 
FOREIGN KEY (id_conta_destino) REFERENCES contas(id_conta);

-- Relationship: Empresas 1:N Solicitacoes_Credito
ALTER TABLE solicitacoes_credito ADD CONSTRAINT fk_solicitacoes_empresas 
FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa);

-- Relationship: Empresas 1:N Documentos
ALTER TABLE documentos ADD CONSTRAINT fk_documentos_empresas 
FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa);

-- Relationship: Solicitacoes_Credito 1:1 Analises_Credito
ALTER TABLE analises_credito ADD CONSTRAINT fk_analises_solicitacoes 
FOREIGN KEY (id_solicitacao) REFERENCES solicitacoes_credito(id_solicitacao);

-- Relationship: Usuarios 1:N Analises_Credito (Analyst)
ALTER TABLE analises_credito ADD CONSTRAINT fk_analises_usuarios 
FOREIGN KEY (id_usuario_analista) REFERENCES usuarios(id_usuario);

-- Relationship: Empresas N:N Produtos_Financeiros (through Contratos)
ALTER TABLE contratos ADD CONSTRAINT fk_contratos_empresas 
FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa);

ALTER TABLE contratos ADD CONSTRAINT fk_contratos_produtos 
FOREIGN KEY (id_produto) REFERENCES produtos_financeiros(id_produto);

-- Relationship: Contas 1:N Movimentacoes_Financeiras
ALTER TABLE movimentacoes_financeiras ADD CONSTRAINT fk_movimentacoes_contas 
FOREIGN KEY (id_conta) REFERENCES contas(id_conta);

-- ============================================================================
-- 5. ADD CHECK CONSTRAINTS (Data Validation)
-- ============================================================================

ALTER TABLE transacoes ADD CONSTRAINT ck_transacoes_valor 
CHECK (valor > 0);

ALTER TABLE solicitacoes_credito ADD CONSTRAINT ck_solicitacoes_valor 
CHECK (valor_solicitado > 0);

ALTER TABLE solicitacoes_credito ADD CONSTRAINT ck_solicitacoes_prazo 
CHECK (prazo_meses > 0);

ALTER TABLE solicitacoes_credito ADD CONSTRAINT ck_solicitacoes_score 
CHECK (score_credito >= 0 AND score_credito <= 1000);

ALTER TABLE contratos ADD CONSTRAINT ck_contratos_valor 
CHECK (valor_contratado > 0);

ALTER TABLE movimentacoes_financeiras ADD CONSTRAINT ck_movimentacoes_valor 
CHECK (valor > 0);

ALTER TABLE analises_credito ADD CONSTRAINT ck_analises_risco 
CHECK (risco_score >= 0 AND risco_score <= 100);

-- ============================================================================
-- 6. CREATE SEQUENCES (For Oracle Identity Generation)
-- ============================================================================

CREATE SEQUENCE seq_usuarios START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_empresas START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_contas START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_transacoes START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_solicitacoes_credito START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_documentos START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_produtos_financeiros START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_analises_credito START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_contratos START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_movimentacoes_financeiras START WITH 1 INCREMENT BY 1;

-- ============================================================================
-- 7. CREATE INDEXES (Performance Optimization)
-- ============================================================================

-- Indexes for Usuarios
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_cpf ON usuarios(cpf);
CREATE INDEX idx_usuarios_tipo ON usuarios(tipo_usuario);

-- Indexes for Empresas
CREATE INDEX idx_empresas_cnpj ON empresas(cnpj);
CREATE INDEX idx_empresas_id_usuario ON empresas(id_usuario);
CREATE INDEX idx_empresas_status ON empresas(status);

-- Indexes for Contas
CREATE INDEX idx_contas_numero ON contas(numero_conta);
CREATE INDEX idx_contas_id_empresa ON contas(id_empresa);
CREATE INDEX idx_contas_status ON contas(status);

-- Indexes for Transacoes
CREATE INDEX idx_transacoes_data ON transacoes(data_transacao);
CREATE INDEX idx_transacoes_conta_origem ON transacoes(id_conta_origem);
CREATE INDEX idx_transacoes_conta_destino ON transacoes(id_conta_destino);
CREATE INDEX idx_transacoes_status ON transacoes(status);

-- Indexes for Solicitacoes_Credito
CREATE INDEX idx_solicitacoes_status ON solicitacoes_credito(status_aprovacao);
CREATE INDEX idx_solicitacoes_id_empresa ON solicitacoes_credito(id_empresa);
CREATE INDEX idx_solicitacoes_data ON solicitacoes_credito(data_solicitacao);

-- Indexes for Documentos
CREATE INDEX idx_documentos_id_empresa ON documentos(id_empresa);
CREATE INDEX idx_documentos_status ON documentos(status_validacao);

-- Indexes for Analises_Credito
CREATE INDEX idx_analises_id_solicitacao ON analises_credito(id_solicitacao);
CREATE INDEX idx_analises_risco ON analises_credito(risco_score);

-- Indexes for Produtos_Financeiros
CREATE INDEX idx_produtos_tipo ON produtos_financeiros(tipo);
CREATE INDEX idx_produtos_ativo ON produtos_financeiros(ativo);

-- Indexes for Contratos
CREATE INDEX idx_contratos_status ON contratos(status);
CREATE INDEX idx_contratos_id_empresa ON contratos(id_empresa);
CREATE INDEX idx_contratos_id_produto ON contratos(id_produto);

-- Indexes for Movimentacoes_Financeiras
CREATE INDEX idx_movimentacoes_data ON movimentacoes_financeiras(data_movimentacao);
CREATE INDEX idx_movimentacoes_id_conta ON movimentacoes_financeiras(id_conta);
CREATE INDEX idx_movimentacoes_tipo ON movimentacoes_financeiras(tipo_movimentacao);

-- ============================================================================
-- 8. COMMENTS FOR DOCUMENTATION (Logical Model Annotations)
-- ============================================================================

COMMENT ON TABLE usuarios IS 'Stores system users: company owners, credit analysts, and administrators';
COMMENT ON COLUMN usuarios.id_usuario IS 'Unique user identifier (Primary Key)';
COMMENT ON COLUMN usuarios.nome IS 'Full name of the user';
COMMENT ON COLUMN usuarios.email IS 'Email address for login (Unique)';
COMMENT ON COLUMN usuarios.cpf IS 'Brazilian CPF registration number (Unique)';
COMMENT ON COLUMN usuarios.tipo_usuario IS 'User type: proprietario, analista, admin';
COMMENT ON COLUMN usuarios.status IS 'User status: ativo or inativo';

COMMENT ON TABLE empresas IS 'Stores companies registered on the platform';
COMMENT ON COLUMN empresas.id_empresa IS 'Unique company identifier (Primary Key)';
COMMENT ON COLUMN empresas.cnpj IS 'Brazilian CNPJ registration number (Unique)';
COMMENT ON COLUMN empresas.razao_social IS 'Legal company name';
COMMENT ON COLUMN empresas.setor IS 'Business sector';
COMMENT ON COLUMN empresas.id_usuario IS 'Reference to company owner (Foreign Key to usuarios)';

COMMENT ON TABLE contas IS 'Digital bank accounts for companies';
COMMENT ON COLUMN contas.id_conta IS 'Unique account identifier (Primary Key)';
COMMENT ON COLUMN contas.numero_conta IS 'Account number (Unique)';
COMMENT ON COLUMN contas.id_empresa IS 'Reference to company (Foreign Key to empresas)';
COMMENT ON COLUMN contas.tipo_conta IS 'Account type: corrente or poupanca';
COMMENT ON COLUMN contas.saldo IS 'Current account balance';

COMMENT ON TABLE transacoes IS 'Financial transactions between accounts';
COMMENT ON COLUMN transacoes.id_transacao IS 'Unique transaction identifier (Primary Key)';
COMMENT ON COLUMN transacoes.id_conta_origem IS 'Source account (Foreign Key to contas)';
COMMENT ON COLUMN transacoes.id_conta_destino IS 'Destination account (Foreign Key to contas)';
COMMENT ON COLUMN transacoes.tipo IS 'Transaction type: transferencia, deposito, saque, pix';
COMMENT ON COLUMN transacoes.status IS 'Transaction status: concluida, pendente, rejeitada';

COMMENT ON TABLE solicitacoes_credito IS 'Credit loan requests from companies';
COMMENT ON COLUMN solicitacoes_credito.id_solicitacao IS 'Unique request identifier (Primary Key)';
COMMENT ON COLUMN solicitacoes_credito.id_empresa IS 'Reference to company (Foreign Key to empresas)';
COMMENT ON COLUMN solicitacoes_credito.valor_solicitado IS 'Requested credit amount';
COMMENT ON COLUMN solicitacoes_credito.taxa_juros IS 'Interest rate applied';
COMMENT ON COLUMN solicitacoes_credito.score_credito IS 'Credit score (0-1000)';
COMMENT ON COLUMN solicitacoes_credito.status_aprovacao IS 'Approval status: pendente, aprovada, rejeitada, cancelada';

COMMENT ON TABLE documentos IS 'Documents submitted by companies for credit analysis';
COMMENT ON COLUMN documentos.id_documento IS 'Unique document identifier (Primary Key)';
COMMENT ON COLUMN documentos.id_empresa IS 'Reference to company (Foreign Key to empresas)';
COMMENT ON COLUMN documentos.tipo_documento IS 'Document type classification';
COMMENT ON COLUMN documentos.caminho_arquivo IS 'File storage location';
COMMENT ON COLUMN documentos.status_validacao IS 'Validation status: pendente, validado, rejeitado';

COMMENT ON TABLE produtos_financeiros IS 'Catalog of financial products offered by the platform';
COMMENT ON COLUMN produtos_financeiros.id_produto IS 'Unique product identifier (Primary Key)';
COMMENT ON COLUMN produtos_financeiros.nome_produto IS 'Product name';
COMMENT ON COLUMN produtos_financeiros.tipo IS 'Product type: credito, investimento, conta';
COMMENT ON COLUMN produtos_financeiros.taxa_base IS 'Base interest rate';

COMMENT ON TABLE analises_credito IS 'AI-powered credit analysis results';
COMMENT ON COLUMN analises_credito.id_analise IS 'Unique analysis identifier (Primary Key)';
COMMENT ON COLUMN analises_credito.id_solicitacao IS 'Reference to credit request (Foreign Key to solicitacoes_credito)';
COMMENT ON COLUMN analises_credito.resultado_ia IS 'AI processing result';
COMMENT ON COLUMN analises_credito.risco_score IS 'Risk score (0-100)';
COMMENT ON COLUMN analises_credito.id_usuario_analista IS 'Reference to analyst (Foreign Key to usuarios)';

COMMENT ON TABLE contratos IS 'Contracts linking companies to financial products (N:N relationship)';
COMMENT ON COLUMN contratos.id_contrato IS 'Unique contract identifier (Primary Key)';
COMMENT ON COLUMN contratos.id_empresa IS 'Reference to company (Foreign Key to empresas)';
COMMENT ON COLUMN contratos.id_produto IS 'Reference to product (Foreign Key to produtos_financeiros)';
COMMENT ON COLUMN contratos.valor_contratado IS 'Total contracted value';
COMMENT ON COLUMN contratos.status IS 'Contract status: ativo, encerrado, suspenso';

COMMENT ON TABLE movimentacoes_financeiras IS 'Detailed record of all account movements';
COMMENT ON COLUMN movimentacoes_financeiras.id_movimentacao IS 'Unique movement identifier (Primary Key)';
COMMENT ON COLUMN movimentacoes_financeiras.id_conta IS 'Reference to account (Foreign Key to contas)';
COMMENT ON COLUMN movimentacoes_financeiras.tipo_movimentacao IS 'Movement type: entrada, saida, juros, tarifa';

-- ============================================================================
-- 9. LOGICAL MODEL RELATIONSHIPS DOCUMENTATION
-- ============================================================================

/*
LOGICAL MODEL RELATIONSHIPS (For Oracle Data Modeler):

1. USUARIOS -> EMPRESAS (1:N)
   - One user can be the owner of multiple companies
   - Cardinality: 1 to Many
   - Relationship Type: Identifying

2. EMPRESAS -> CONTAS (1:N)
   - One company can have multiple digital accounts
   - Cardinality: 1 to Many
   - Relationship Type: Identifying

3. CONTAS -> TRANSACOES (1:N - Origin)
   - One account can be the origin of multiple transactions
   - Cardinality: 1 to Many
   - Relationship Type: Non-Identifying

4. CONTAS -> TRANSACOES (1:N - Destination)
   - One account can be the destination of multiple transactions
   - Cardinality: 1 to Many
   - Relationship Type: Non-Identifying

5. EMPRESAS -> SOLICITACOES_CREDITO (1:N)
   - One company can have multiple credit requests
   - Cardinality: 1 to Many
   - Relationship Type: Identifying

6. EMPRESAS -> DOCUMENTOS (1:N)
   - One company can submit multiple documents
   - Cardinality: 1 to Many
   - Relationship Type: Identifying

7. SOLICITACOES_CREDITO -> ANALISES_CREDITO (1:1)
   - Each credit request has exactly one analysis
   - Cardinality: 1 to 1
   - Relationship Type: Identifying

8. USUARIOS -> ANALISES_CREDITO (1:N)
   - One analyst can perform multiple analyses
   - Cardinality: 1 to Many
   - Relationship Type: Non-Identifying

9. EMPRESAS -> CONTRATOS (1:N)
   - One company can have multiple contracts
   - Cardinality: 1 to Many
   - Relationship Type: Identifying

10. PRODUTOS_FINANCEIROS -> CONTRATOS (1:N)
    - One product can be part of multiple contracts
    - Cardinality: 1 to Many
    - Relationship Type: Non-Identifying

11. CONTAS -> MOVIMENTACOES_FINANCEIRAS (1:N)
    - One account can have multiple financial movements
    - Cardinality: 1 to Many
    - Relationship Type: Identifying

ENTITIES SUMMARY:
- USUARIOS: 1 entity (with multiple types)
- EMPRESAS: 1 entity
- CONTAS: 1 entity
- TRANSACOES: 1 entity (bridge for account transfers)
- SOLICITACOES_CREDITO: 1 entity
- DOCUMENTOS: 1 entity
- PRODUTOS_FINANCEIROS: 1 entity
- ANALISES_CREDITO: 1 entity
- CONTRATOS: 1 entity (junction table for N:N)
- MOVIMENTACOES_FINANCEIRAS: 1 entity

TOTAL ENTITIES: 10
TOTAL RELATIONSHIPS: 11 (including 1:1, 1:N, and N:N through junction table)
*/

-- ============================================================================
-- 10. IMPORT INSTRUCTIONS FOR ORACLE DATA MODELER
-- ============================================================================

/*
STEPS TO IMPORT INTO ORACLE DATA MODELER:

1. Open Oracle Data Modeler
2. Create a new Logical Model (File > New > Logical Model)
3. Go to File > Import > SQL file
4. Select this SQL file (finsmart-ddl-oracle-modeler.sql)
5. Oracle Data Modeler will automatically:
   - Recognize all CREATE TABLE statements
   - Create entities for each table
   - Detect PRIMARY KEY constraints as entity identifiers
   - Detect FOREIGN KEY constraints as relationships
   - Detect UNIQUE constraints
   - Detect CHECK constraints
   - Apply comments as entity/attribute descriptions

6. The logical model will be generated with:
   - All 10 entities properly defined
   - All relationships with correct cardinality
   - All attributes with their data types
   - All constraints properly annotated

7. You can then:
   - Review the logical model diagram
   - Modify relationships if needed
   - Generate physical models for different databases
   - Export to documentation formats

NOTES:
- All table and constraint names follow Oracle naming conventions
- All relationships are properly defined with constraints
- Comments provide documentation for the logical model
- Sequences are included for Oracle-style ID generation
- Indexes are defined for performance optimization
*/

-- ============================================================================
-- END OF LOGICAL MODEL DDL FOR ORACLE DATA MODELER
-- ============================================================================
