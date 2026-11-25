## Download do Projeto

1. Clone o projeto para a pasta **"eclipse-workspace"**:

`git clone https://github.com/LuisOPKI/LojaOO2`

- Alternativa: Faça download e descompacte o projeto na pasta **"eclipse-workspace"**.

## Configure a Base de Dados

1. Crie uma base de dados com o nome de **"loja"** no MySQL.

2. Na raiz do projeto Java, há uma pasta com o nome de **"database"**, nela há um arquivo chamado **"loja.sql"**. Este arquivo deve ser importado para a base de dados **"loja"**, criada no passo 1.

**Obs.:** Esta base contém uma tabela chamada **"produto"**, nela há 3 produtos previamente cadastrados: _"Fone de Ouvido Bluetooth JBL Tune 510BT"_, _"Camiseta Básica Algodão Masculina"_ e _"Café Torrado e Moído 500g – Melitta Tradicional"_.

## Configure o Projeto no Eclipse IDE

1. Importe o projeto para o eclipse: _File_ > _Import_ > _General_ > _Existing Projects into Workspace_

2. Execute o arquivo **"BancoDadosTeste.java"** disponível na pasta **"src/test/java"**
