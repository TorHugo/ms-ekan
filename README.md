# Ekan Rest

## Descrição:
RestAPI, para cadastro de novos beneficiarios de um plano de saúde. Para a empresa @ekan. 

## Versao:
- v0.0.9

## Tecnologias:
- Java 17
- H2
- SpringBoot v3.1.5 (web/ validation/ devtools/ jdbc/ lombok/)
- Docker

## Instruções para Executar o Projeto:

Certifique-se de ter o Java 17 instalado em sua máquina antes de prosseguir.

1. Clone o repositório para a sua máquina local:
    ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
2. Navegue até o diretório do projeto:
    ```bash
    cd seu-repositorio
3. Execute o build do projeto usando o Maven:
    ```bash
    mvnw.cmd clean install
4. Após o build ser concluído com sucesso, você pode executar a aplicação SpringBoot:
      ```bash 
   mvnw.cmd spring-boot:run
   Isso iniciará a aplicação e você poderá acessar os endpoints mencionados na seção "ENDPOINTS" via http://localhost:8080.
   
5. Para criar uma imagem Docker da aplicação, use o seguinte comando:
    ```bash
    docker build -t nome-da-imagem .

6. Execute o contêiner Docker:
    ```bash
    docker run -p 8080:8080 nome-da-imagem
    Agora você pode acessar a aplicação SpringBoot no Docker via http://localhost:8080.   

## ENDPOINTS:
### Beneficiary

-  <b>Endpoint responsável por:</b> Cadastrar um novo <b>Benefíciario</b> no sistema.<br/>
```http
  POST /api/v1/beneficiary/create
```
| Parâmetro                 | Tipo     | Descrição                                | 
|:--------------------------|:---------|:-----------------------------------------| 
| `name`                    | `string` | **Obrigatório**. Nome.                   | 
| `phone_number`            | `string` | **Obrigatório**. Telefone.               | 
| `birth_date`              | `string` | **Obrigatório**. Data de Nascimento.     | 
| `documents.type_document` | `string` | **Obrigatório**. Tipo do documento.      | 
| `documents.description`   | `string` | **Obrigatório**. Descrição do documento. | 

-  <b>Endpoint responsável por:</b> Adicionar um <b>documento</b> em um beneficiary existente no sistema.<br/>

```http
  POST /api/v1/beneficiary/add-document/{beneficiaryId}
```
| Parâmetro       | Tipo                 | Descrição                                                            | 
|:----------------|:---------------------|:---------------------------------------------------------------------| 
| `beneficiaryId` | `@PathVariable long` | **Obrigatório**. Id do beneficiario que terá o documento adicionado. | 
| `typeDocument`  | `string`             | **Obrigatório**. Tipo do documento.                                  | 
| `description`   | `string`             | **Obrigatório**. Descrição do documento.                             | 

-  <b>Endpoint responsável por:</b> Atualizar um usuário existente.<br/>
```http 
  PUT /api/v1/beneficiary/update-beneficiary/{beneficiaryId}
``` 
| Parâmetro                | Tipo                 | Descrição                            | 
|:-------------------------|:---------------------|:-------------------------------------| 
| `beneficiaryId`          | `@PathVariable long` | **Obrigatório**. Id do beneficiario. | 
| `name`                   | `string`             | **Obrigatório**. Nome                | 
| `phone_number`           | `string`             | **Obrigatório**. Telefone.           | 
| `birth_date.`            | `string`             | **Obrigatório**. Data de Nascimento. | 

-  <b>Endpoint responsável por:</b> Realizar uma exclusão de um beneficiário.<br/>
- **Obs**.: Este endpoint não realiza de fato um delete na base de dados. Ele faz uma exclusão lógica do beneficiário, para que assim o mesmo possa reativar a sua conta sem muitos problemas futuramente.
```http 
  POST /api/v1/beneficiary/delete-beneficiary/{beneficiaryId}
``` 
| Parâmetro       | Tipo                 | Descrição                            | 
|:----------------|:---------------------|:-------------------------------------| 
| `beneficiaryId` | `@PathVariable long` | **Obrigatório**. Id do beneficiario. | 

-  <b>Endpoint responsável por:</b> Reativar um beneficiario.<br/>
```http 
  POST /api/v1/beneficiary/reactivate
``` 
| Parâmetro | Tipo                   | Descrição                                                 | 
|:----------|:-----------------------|:----------------------------------------------------------| 
| `name`    | `@RequestParam string` | **Obrigatório**. Nome do beneficiario que será reativado. | 


-  <b>Endpoint responsável por:</b> Recuperar todos os beneficiarios ativos do sistema.<br/>
```http 
  GET /api/v1/beneficiary/retrieve/all
``` 

-  <b>Endpoint responsável por:</b> Recuperar UM único beneficiarios ativos do sistema.<br/>
```http 
  GET /api/v1/beneficiary/retrieve/{beneficiaryId}
``` 
| Parâmetro       | Tipo                 | Descrição                                       | 
|:----------------|:---------------------|:------------------------------------------------| 
| `beneficiaryId` | `@RequestParam long` | **Obrigatório**. Id do beneficiario recuperado. | 

-  <b>Endpoint responsável por:</b> Recuperar todos os documentos de UM único beneficiarios ativo do sistema.<br/>
```http 
  GET /api/v1/beneficiary/retrieve/documents/{beneficiaryId}
``` 
| Parâmetro       | Tipo                 | Descrição                                       | 
|:----------------|:---------------------|:------------------------------------------------| 
| `beneficiaryId` | `@RequestParam long` | **Obrigatório**. Id do beneficiario recuperado. | 

### INFO
Desenvolvedor: Victor Hugo Arruda
Contato: (https://beacons.ai/tor_hugo)
