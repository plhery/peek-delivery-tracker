---
title: Rastreamento Memphis Courier — códigos, alfândega e ANVISA
description: Rastreie encomendas da Memphis Courier dos EUA para o Brasil. Veja códigos M-MIA, status em Guarulhos, Receita Federal, ANVISA, CPF e contatos oficiais.
slug: rastreio-memphis-courier
published: 2026-11-23
updated: 2026-11-23
---

A Memphis Courier é uma empresa 100% brasileira com mais de 25 anos de atuação em logística internacional de remessas expressas (courier) e importação formal entre o exterior — especialmente os Estados Unidos — e o Brasil. Operando com terminal courier no Aeroporto Internacional de Guarulhos (GRU) e escritório em São Paulo, a empresa realiza o transporte aéreo, o desembaraço aduaneiro junto à Receita Federal e a órgãos reguladores como a ANVISA (incluindo importações de cannabis medicinal pela RDC 660 e RDC 327) e a entrega em todo o Brasil.

Como boa parte das remessas embarca em Miami rumo a Guarulhos antes de passar pela alfândega e pela fiscalização sanitária, o rastreamento acompanha de perto a chegada no aeroporto, o desembaraço na Receita Federal e a liberação da ANVISA.

## Formato do código de rastreamento da Memphis Courier

O código usado pela Memphis Courier segue um padrão próprio de 11 caracteres alfanuméricos:

- **`M` + 7 dígitos + `MIA`** (11 caracteres no total, como `M4829173MIA`) — código de remessa courier emitido para encomendas expedidas via hub de Miami com destino ao Brasil.

Espaços e letras minúsculas são normalizados automaticamente na consulta. O portal de rastreamento (`clients.memphiscourier.com.br`) consulta esse código diretamente, sem exigir login de conta corporativa. Pelo formato `M` + 7 dígitos + `MIA`, o Peek reconhece a Memphis Courier como candidata automaticamente e confirma assim que o serviço da transportadora retorna os dados da remessa. Para conferir padrões usados por outras transportadoras em compras internacionais, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## Significado dos códigos e status da Memphis Courier

O sistema da Memphis Courier publica códigos de três letras acompanhados de descrições em português e da indicação de localidade (país ou estado). A barra de etapas do site destaca quatro marcos — Criado (`FLT`), desembaraço (`BRP`), Liberado (`CLA`) e Entregue (`DLV`) — enquanto o histórico detalha cada movimentação entre origem, aeroporto e inspeção.

| Código | Texto na Memphis Courier | O que significa |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | A remessa foi registrada no sistema da Memphis Courier antes do embarque aéreo. |
| `CHK` | Entrada registrada no Centro de Distribuição | A encomenda deu entrada em um centro de distribuição da operação. |
| `ENO` | Encomenda em trânsito | Em deslocamento dentro da malha logística. |
| `TRA` | Encomenda em transferência | Em transferência entre unidades operacionais. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Embarcou na origem em voo com destino ao Aeroporto Internacional de Guarulhos (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | A carga pousou em Guarulhos (SP). |
| `AAZ` | Pedido recebido pela GRU Airport | A encomenda foi recepcionada no terminal de cargas do aeroporto de Guarulhos antes do início do desembaraço. |
| `BRP` | Processo de desembaraço iniciado | O processo de desembaraço aduaneiro no Brasil foi aberto. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | A remessa está em conferência pela Receita Federal. |
| `ANV` | Em inspeção pela ANVISA | A encomenda passa por inspeção da ANVISA, etapa habitual em medicamentos e produtos de cannabis medicinal importados. |
| `CLA` | Liberado | A alfândega e os órgãos anuentes liberaram a encomenda para seguir para a entrega nacional. |
| `DLV` | Entregue | A remessa foi entregue ao destinatário no Brasil. |

## Alfândega, validação de CPF e inspeção da ANVISA

A Receita Federal exige o CPF do importador pessoa física para liberar qualquer remessa expressa internacional no Brasil. Conforme explicado na página de [Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/), muitos sites estrangeiros não têm campo para CPF no fechamento da compra; nesses casos, a equipe de Customer Services da Memphis Courier entra em contato com o destinatário para validar o CPF e evitar travas no desembaraço.

Em remessas de medicamentos e CBD medicinal procedentes dos Estados Unidos, Europa, Colômbia ou Uruguai, o pacote passa tanto pela Receita Federal (`HOC`) quanto pela ANVISA (`ANV`) em Guarulhos antes de mudar para `CLA` (Liberado). O prazo nessa fase varia conforme a fila dos órgãos reguladores e a conferência de receitas, autorizações de importação e dados cadastrais. Se o rastreamento permanecer em `BRP`, `HOC` ou `ANV` por vários dias úteis, verifique seu e-mail e mensagens de WhatsApp da Memphis Courier para checar se há alguma pendência de CPF ou documento. Saiba mais no [guia de alfândega](guide:customs) e em [quando o rastreamento para de atualizar](guide:tracking-not-updating).

Após a liberação (`CLA`), a distribuição cobre todo o território brasileiro. Se você também acompanha entregas de outros operadores no Brasil, consulte as páginas de [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) e [J&T Express Brazil](carrier:j-and-t-br).

## Como acompanhar a Memphis Courier no Peek

Cole o código `M...MIA` no Peek para identificar a Memphis Courier automaticamente assim que o sistema confirmar a remessa. Caso precise escolher ou alterar a transportadora manualmente, use **Trocar** ao adicionar a encomenda ou **Trocar transportadora** na tela de detalhes.

O Peek consulta o serviço público de rastreamento da Memphis Courier diretamente por meio do [adaptador Memphis Courier no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Ele lê todos os códigos de evento, as descrições em português, a localidade informada e o peso total em quilogramas quando disponível, preservando o horário local exato retornado pela transportadora (ou o fuso explícito quando presente). Campos adicionais do portal, como observações internas, contagem de volumes, CEPs e documentos aduaneiros anexados, permanecem disponíveis apenas no portal da Memphis Courier.

O Peek organiza os 12 códigos da Memphis Courier em etapas claras: **Etiqueta criada** para `FLT`, **Em trânsito** de `CHK` até `AAZ` e novamente após a liberação em `CLA` (os códigos da Memphis Courier passam direto da criação do pedido para o trânsito no centro de distribuição, sem um código separado de **Recebida pela transportadora**), **Na alfândega** para `BRP`, `HOC` e `ANV`, e **Entregue** quando `DLV` é registrado.

Em voos longos ou durante a espera na alfândega e na ANVISA, o Peek informa: „É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Memphis Courier.“ Se passarem mais de três semanas sem novidades, o aviso muda para: „Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Memphis Courier.“ Sempre que precisar falar diretamente com a transportadora, toque em **Pedir ajuda a Memphis Courier** na tela do pacote.

Nas configurações de alerta do Peek, escolha entre **Cada movimentação**, **Etapas importantes** ou **Só a entrega**.

## Canais oficiais de contato da Memphis Courier

A Memphis Courier publica em seu site um aviso explícito informando que seus canais oficiais de atendimento são exclusivamente os divulgados no próprio site, e que não reconhece o Reclame Aqui como canal oficial de protocolo, notificação ou atendimento.

- **Formulário oficial:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- **Telefone de atendimento:** +55 11 94264-7550 (informado tanto para o Terminal Courier quanto para o escritório em São Paulo)
- **E-mail:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` e `info@memphiscourier.com.br`
- **Terminal Courier (Guarulhos):** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo – Brasil
- **Escritório São Paulo:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo – Brasil ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Tenha em mãos o código `M...MIA` e o CPF do destinatário ao acionar o atendimento para agilizar a localização do processo aduaneiro.

## Perguntas frequentes

### O que significa `ANV` („Em inspeção pela ANVISA“) no rastreamento?

O código `ANV` indica que a Agência Nacional de Vigilância Sanitária (ANVISA) está analisando a remessa no Aeroporto de Guarulhos durante o desembaraço. É um passo padrão em importações de medicamentos e cannabis medicinal (RDC 660 / RDC 327) antes da liberação (`CLA`).

### Por que a Memphis Courier pediu para validar meu CPF?

A Receita Federal exige o CPF do destinatário pessoa física para liberar remessas expressas internacionais. Quando a loja no exterior não coleta o CPF na compra, a equipe da Memphis Courier entra em contato para confirmar o dado e destravar o desembaraço.

### Por que o rastreamento fica sem atualizações entre `DEP` e `ARR`?

`DEP` marca a saída da origem rumo a Guarulhos e `ARR` registra o pouso em São Paulo. Enquanto a carga está no voo ou aguardando conferência no terminal de cargas (`AAZ`), não há novos eventos intermediários.

### A Memphis Courier atende solicitações feitas pelo Reclame Aqui?

Não. O aviso oficial publicado em [memphiscourier.com.br](https://memphiscourier.com.br/) estabelece que reclamações, dúvidas e pedidos de esclarecimento devem ser enviados apenas pelos canais oficiais do site (telefone, e-mail e formulário de contato) para garantir rastreabilidade e confidencialidade.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [Adaptador Memphis Courier no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
