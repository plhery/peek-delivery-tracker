---
title: Rastreamento Onlog Express: códigos AA, status e contato
description: Rastreie sua encomenda na Onlog Express no Brasil: códigos AA de 12 caracteres, status de entrega, alerta contra golpes de taxa e telefones em São Paulo.
slug: rastreamento-onlog-express
published: 2026-11-23
updated: 2026-11-23
---

A **Onlog Express** (*Onlog Franqueadora*) é uma rede brasileira de transporte, fulfillment e distribuição de encomendas com matriz em São Paulo, mais de 20 anos desenvolvendo parcerias em logística e mais de 18 milhões de pacotes entregues em todo o Brasil ([onlogexpress.com.br](https://onlogexpress.com.br/)). Conectando lojas virtuais e empresas a diferentes modalidades de entrega — de envios urgentes no mesmo dia (*Entrega em Minutos* e *Entrega no Dia*) até *Expresso D1*, *Econômico D2* e frete internacional —, a Onlog disponibiliza rastreamento público por código do objeto ou CPF/CNPJ em [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Você pode consultar por lá ou colar seu código `AA` de 12 caracteres na caixa acima.

## Códigos de rastreamento da Onlog Express (formato `AA`)

O rastreamento público direto da Onlog Express usa um código do objeto de 12 caracteres iniciado por `AA` seguido de 10 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `AA4829173605` | Código do objeto Onlog Express de 12 caracteres (`AA` + 10 dígitos). |
| `AA7391846208` | Outro exemplo de referência `AA` de 12 caracteres da Onlog Express. |

Procure o código `AA` no e-mail da loja ou no link `onlog.app.br/rastreio/...`. Se você não tiver o código em mãos, o portal oficial [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) também permite pesquisar pelo CPF ou CNPJ do destinatário (além de contar com consulta separada para objetos anteriores a 01/01/2025). Veja os [formatos de códigos de rastreamento](guide:tracking-number-formats).

## O que significam os status da Onlog Express

A Onlog Express publica movimentações em português desde a emissão da etiqueta e coleta até os bipes nos centros de distribuição e a assinatura na entrega:

| A Onlog Express diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Envio registrado e etiqueta gerada eletronicamente antes da coleta física. | "Etiqueta criada" |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Encomenda postada em uma unidade Onlog ou coletada no remetente. | "Recebida pela transportadora" |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Em trânsito entre unidades, no centro de distribuição ou transferida ao operador logístico regional. | "Em trânsito" |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | Em rota com o entregador local para entrega hoje. | "Saiu para entrega" |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Entregue e assinada no endereço de destino. | "Entregue" |

Como a Onlog conecta diferentes operadores no Brasil (assim como redes integradas que atuam ao lado de transportadoras como [Jadlog](carrier:jadlog), [J&T Express Brazil](carrier:j-and-t-br) e [Anjun Logistics](carrier:anjun)), a mensagem `ENCAMINHADO PARA O OPERADOR LOGÍSTICO` indica apenas que o pacote seguiu para o parceiro responsável pelo próximo trecho.

## Modalidades de entrega e alerta oficial contra golpes de taxa

Segundo [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Modalidades disponíveis:** **Entrega em Minutos** (demandas urgentes), **Entrega no Dia**, **Expresso D1** (prioridade no prazo) e **Econômico D2** (custo otimizado), com disponibilidade e prazos variando conforme origem e destino.
- **Aviso importante contra golpes:** a Onlog destaca na página inicial que **não solicita pagamentos de taxas por e-mail, WhatsApp ou telefone**. Se você receber uma cobrança por mensagem pedindo PIX ou cartão para liberar uma encomenda em nome da Onlog, não pague. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Encomenda da Onlog Express atrasada, parada ou extraviada

- **Pausa após `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** é comum o rastreio ficar alguns dias sem novos bipes durante a transferência interestadual até o centro de distribuição da cidade de destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Consta entregue ou encomenda sumida:** confirme primeiro na portaria, com familiares ou vizinhos; se não localizar o pacote, acione a loja onde comprou e entre em contato com o atendimento da Onlog em horário comercial informando o código `AA`. Veja [encomenda entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Onlog Express

Canais oficiais publicados em [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Telefones (São Paulo):** `(11) 5662-8108` e `(11) 5663-3829`.
- **E-mail:** `atendimento@onlogexpress.com.br`.
- **Horário de atendimento:** segunda a sexta-feira, das `8h` às `18h` (fechado aos sábados e domingos).
- **Matriz São Paulo:** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, CEP `01427-002`.
- **Rastreador oficial:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Reforçando o aviso oficial da Onlog: a transportadora nunca cobra taxas de liberação por e-mail, WhatsApp ou telefone. Ignore e denuncie qualquer mensagem desse tipo. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Rastreie sua encomenda da Onlog Express com o Peek

Quando você digita um código `AA` de 12 caracteres (`AA4829173605` ou `AA7391846208`, inventados), o [Peek](/) verifica a Onlog Express automaticamente como opção preferencial e mostra `"Onlog Express está com esta encomenda."` assim que o sistema confirma; o Peek também reconhece diretamente links colados no formato `onlog.app.br/rastreio/AA...`. Se precisar escolher manualmente, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela do pacote (buscar por "ONLOG" ou "Onlog Franqueadora" também encontra).

O Peek consulta a Onlog Express por meio do serviço de navegador (com intervalo mínimo de 30 minutos entre consultas, ou 60 minutos após falha) e exibe a etapa mapeada ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Entregue"), a descrição original em português, a localidade da movimentação e o horário local com precisão de minutos informado pela Onlog (sem fuso horário). O rastreador público da Onlog não informa previsão de entrega, peso, códigos numéricos de evento nem horário de entrega separado.

O Peek verifica até a cada 30 minutos enquanto você mantém o pacote aberto ou no dia da entrega com alertas ativados (60 minutos após falha, respeitando o limite da Onlog). No restante do tempo consulta a cada hora, mantém consultas de hora em hora com a tela aberta após 12 horas em "Etiqueta criada" ou "Recebida pela transportadora" ou 2 dias sem novidade, e passa para cada 6 horas após 7 dias ainda em "Etiqueta criada". Quando serviços de rastreamento entram como alternativa, consultam a cada hora, a cada 3 horas após um dia sem mudança e até a cada 15 minutos no dia da entrega. Após 4 dias em trânsito sem nova movimentação, o Peek destaca a pausa (por exemplo "Sem atualizações desde seg., 12 de out.") e mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Onlog Express." Após mais de três semanas sem novidades, o Peek mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Onlog Express."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha as atualizações de rastreamento e não consegue buscar por CPF/CNPJ, alterar o endereço de entrega nem falar com a Onlog Express por você.

## Perguntas frequentes sobre o rastreamento da Onlog Express

### Como é o código de rastreamento da Onlog Express?

O código do objeto da Onlog Express tem 12 caracteres: `AA` seguido de 10 números (por exemplo `AA4829173605`, inventado).

### Posso rastrear um pedido na Onlog Express usando meu CPF ou CNPJ?

Sim, diretamente no site [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php), selecionando a aba **POR CPF/CNPJ**. No Peek o rastreamento usa apenas o código `AA`.

### Recebi uma cobrança de taxa no WhatsApp ou e-mail em nome da Onlog — devo pagar?

Não. A própria Onlog alerta em [onlogexpress.com.br](https://onlogexpress.com.br/) que **não cobra taxas por e-mail, WhatsApp ou telefone**.

### Qual é o telefone e o e-mail de atendimento da Onlog Express?

Os contatos oficiais são `(11) 5662-8108`, `(11) 5663-3829` e `atendimento@onlogexpress.com.br`, de segunda a sexta-feira das 8h às 18h.

:::sources
- [Onlog Express: Site Oficial](https://onlogexpress.com.br/) – modalidades (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18 milhões de pacotes, aviso contra golpes de taxa e contatos da matriz em São Paulo
- [Onlog Express: Rastreador Oficial (Conecta)](https://conecta.log.br/rastreio.php) – rastreamento por código do objeto ou CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – formato `AA\d{10}`, regra de link `onlog.app.br/rastreio/`, mapa de 13 frases em português, consulta via navegador (30/60 min) e horários locais
:::
