---
title: Rastreamento Memphis Courier — códigos, alfândega e ANVISA
description: Rastreie encomendas da Memphis Courier dos EUA para o Brasil. Veja códigos M-MIA, status em Guarulhos, Receita Federal, ANVISA, CPF e contatos oficiais.
slug: rastreio-memphis-courier
published: 2026-11-23
updated: 2026-11-23
---

A Memphis Courier é uma empresa 100% brasileira com mais de 25 anos de história, que oferece soluções logísticas para encomendas expressas e formais, tanto para exportação como para importação. Com terminal courier no Aeroporto Internacional de Guarulhos (GRU) e escritório em São Paulo, faz o transporte aéreo, o desembaraço junto à Receita Federal e à ANVISA (incluindo importações de cannabis medicinal pela RDC 660 e RDC 327, de medicamentos produzidos nos Estados Unidos, Europa, Colômbia e Uruguai) e a entrega em todo o Brasil.

O rastreamento acompanha a encomenda até o aeroporto de Guarulhos, o desembaraço na Receita Federal, a inspeção da ANVISA e a entrega.

## Formato do código de rastreamento da Memphis Courier

O código usado pela Memphis Courier segue um padrão próprio de 11 caracteres alfanuméricos:

- **`M` + 7 dígitos + `MIA`** (11 caracteres no total, como `M4829173MIA`) — o formato que o Peek reconhece como da Memphis Courier.

O Peek aceita códigos da Memphis Courier de 6 a 30 letras e dígitos; tira os espaços e passa as letras para maiúsculas. O portal de rastreamento (`clients.memphiscourier.com.br`) consulta o código sem login de conta. Pelo formato `M` + 7 dígitos + `MIA`, o Peek consulta só a Memphis Courier e mostra "Memphis Courier está com esta encomenda." quando ela responde. Para conferir padrões usados por outras transportadoras em compras internacionais, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## Significado dos códigos e status da Memphis Courier

O sistema da Memphis Courier publica códigos de três letras com descrições em português e a localidade (país ou estado). A barra de etapas do site mostra Criado (`FLT`), desembaraço (`BRP`), Liberado e Entregue; dos registros que levam às duas últimas (`CLA` e `DLV`), só se conhece o rótulo da etapa.

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
| `ANV` | Em inspeção pela ANVISA | A encomenda passa por inspeção da ANVISA durante o desembaraço. |
| `CLA` | Liberado (barra de etapas) | A alfândega e os órgãos anuentes liberaram a encomenda para seguir para a entrega nacional. |
| `DLV` | Entregue (barra de etapas) | A remessa foi entregue ao destinatário no Brasil. |

## Alfândega, validação de CPF e inspeção da ANVISA

A Receita Federal exige o CPF do importador pessoa física para liberar qualquer remessa expressa internacional no Brasil. Conforme explicado na página de [Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/), muitos sites estrangeiros não têm campo para CPF no fechamento da compra; nesses casos, a equipe de Customer Services da Memphis Courier entra em contato com o destinatário para validar o CPF e evitar travas no desembaraço.

Em remessas de cannabis medicinal e outros medicamentos que a Memphis Courier traz dos Estados Unidos, Europa, Colômbia ou Uruguai, o pacote pode passar tanto pela Receita Federal (`HOC`) quanto pela ANVISA (`ANV`) em Guarulhos antes de mudar para `CLA` (Liberado). Se o rastreamento permanecer em `BRP`, `HOC` ou `ANV` por vários dias úteis, verifique seu e-mail e o WhatsApp: a Memphis Courier informa os clientes por e-mail, WhatsApp, chat ou telefone e pode ter pedido a confirmação do CPF ou um documento. Saiba mais no [guia de alfândega](guide:customs) e em [quando o rastreamento para de atualizar](guide:tracking-not-updating).

Após a liberação (`CLA`), a distribuição cobre todo o território brasileiro. Se você também acompanha entregas de outros operadores no Brasil, consulte as páginas de [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) e [J&T Express Brazil](carrier:j-and-t-br).

## Como acompanhar a Memphis Courier no Peek

Cole o código `M...MIA` no Peek para identificar a Memphis Courier automaticamente assim que o sistema confirmar a remessa. Caso precise escolher ou alterar a transportadora manualmente, use **Trocar** ao adicionar a encomenda ou **Trocar transportadora** na tela de detalhes.

O Peek consulta o serviço público de rastreamento da Memphis Courier diretamente por meio do [adaptador Memphis Courier no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Ele lê o código, a descrição em português e a localidade de cada registro, e o peso total em quilogramas quando disponível. A Memphis Courier informa o horário de cada registro como hora local e só indica o fuso quando o horário termina com ele. O Peek mantém esse fuso; sem ele, data o registro pelo fuso do local (horário de Nova York para `Estados Unidos`, de São Paulo para `SP` ou `Brasil`), um registro sem local pelo fuso do registro vizinho, senão pelo de São Paulo, e mostra todos no relógio do seu aparelho. Campos adicionais do portal, como observações internas, contagem de volumes, CEPs, destino e documentos aduaneiros anexados, ficam só no portal da Memphis Courier.

O Peek organiza os 12 códigos da Memphis Courier em etapas claras: **Etiqueta criada** para `FLT`, **Em trânsito** de `CHK` até `AAZ` e novamente após a liberação em `CLA` (os códigos da Memphis Courier passam direto da criação do pedido para o trânsito no centro de distribuição, sem um código separado de **Recebida pela transportadora**), **Na alfândega** para `BRP`, `HOC` e `ANV`, e **Entregue** quando `DLV` é registrado.

Com a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até a cada 10 minutos, enquanto o rastreio da Memphis Courier responder. Fora disso, de hora em hora. O rastreio da Memphis Courier não tem etapa de saída para entrega, então o Peek nunca passa às consultas a cada 2 minutos do dia da entrega. Depois de 12 horas só com "Etiqueta criada" ou "Recebida pela transportadora", ou de dois dias sem novidade, consulta de hora em hora em qualquer caso, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreio da Memphis Courier não responde e o Peek acompanha a encomenda só por serviços de rastreamento, consulta de hora em hora, a cada 3 horas depois de um dia sem novidade e até a cada 15 minutos no dia em que a encomenda sai para entrega.

Quando o registro mais recente tem quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com o dia desse registro, por exemplo "Sem atualizações desde seg 12 out". A página da encomenda mostra então "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Memphis Courier.", com o link "Pedir ajuda a Memphis Courier" para a página Fale Conosco da Memphis Courier. Depois de três semanas sem registro novo, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Memphis Courier."

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

### O que significa `ANV` ("Em inspeção pela ANVISA") no rastreamento?

O código `ANV` indica que a Agência Nacional de Vigilância Sanitária (ANVISA) está inspecionando a remessa durante o desembaraço. A Memphis Courier faz o desembaraço de cannabis medicinal (RDC 660 / RDC 327) junto à ANVISA; a remessa fica na alfândega até a liberação (`CLA`).

### Por que a Memphis Courier pediu para validar meu CPF?

A Receita Federal exige o CPF do destinatário pessoa física para liberar remessas expressas internacionais. Quando a loja no exterior não coleta o CPF na compra, a equipe da Memphis Courier entra em contato para confirmar o dado e destravar o desembaraço.

### Por que o rastreamento fica sem atualizações entre `DEP` e `ARR`?

`DEP` marca a saída da origem rumo a Guarulhos e `ARR` a chegada ao aeroporto. A lista de códigos da Memphis Courier não tem registro para o voo, então o próximo é `ARR` ou o `AAZ` do terminal de cargas.

### A Memphis Courier atende solicitações feitas pelo Reclame Aqui?

Não. O aviso oficial publicado em [memphiscourier.com.br](https://memphiscourier.com.br/) estabelece que reclamações, dúvidas e pedidos de esclarecimento devem ser enviados apenas pelos canais oficiais do site (telefone, e-mail e formulário de contato) para garantir rastreabilidade e confidencialidade.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [Adaptador Memphis Courier no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
