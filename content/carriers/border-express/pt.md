---
title: Seguimento Border Express (BEX): números e estados
description: Acompanha uma encomenda Border Express (BEX) na Austrália: números de expedição, códigos de barras de etiqueta, significado dos estados e uso no Peek.
slug: seguimento-border-express
published: 2026-11-22
updated: 2026-11-22
---

A Border Express, ou BEX, é uma transportadora que o catálogo do Peek lista apenas na Austrália. Se uma loja online enviou a tua encomenda com a Border Express, o número a acompanhar é o número de expedição (*consignment number*): cola-o na caixa acima. A loja fornece-o no e-mail de envio ou na página do pedido. O rastreador próprio da Border Express é o Quick Track no BEX Online (`bexonline.borderexpress.com.au`). Como o Peek não conseguiu verificar o site da Border Express para esta página, o texto abaixo baseia-se no que o código do Peek sabe sobre esse rastreador.

## Número de seguimento Border Express: como é

A Border Express acompanha uma entrega pelo número de expedição (*consignment number*). O código do Peek conhece três formatos. Com caracteres inventados:

| Número (inventado) | O que é |
| --- | --- |
| `48291736` | 8 dígitos: um número de expedição. |
| `KLMN482917` | 4 letras (prefixo do remetente) seguidas de dígitos: também um número de expedição. O Peek aceita de 5 a 8 dígitos após as letras. |
| `KLMNBX00000482917001` | 20 caracteres: o código de barras na etiqueta de um volume. As 4 letras do remetente, `BX`, os dígitos da expedição preenchidos com zeros à esquerda até 11 dígitos e um número de volume de 3 dígitos. |

Uma expedição pode conter vários volumes, cada um com o seu próprio código de barras de etiqueta. Todas as etiquetas começam com as mesmas letras e dígitos, ligadas pelo número de expedição: é esse número que o rastreador da Border Express consulta, não aceitando códigos de barras de etiqueta. O Peek aceita-os e deduz deles o número de expedição (ver abaixo).

O Peek não consegue identificar um número Border Express apenas pelo formato: o catálogo não tem regra para os números de expedição da Border Express, e a regra para o código de barras de etiqueta é apenas uma correspondência fraca. Outras transportadoras usam os mesmos formatos: para 8 dígitos o Peek sugere [Mondial Relay](carrier:mondial-relay), Colisweb e [Heppner](carrier:heppner), na Europa, e a TForce Freight (carga norte-americana) quando o último dígito passa a verificação. Quatro letras com 8 dígitos também coincidem com os números de 12 caracteres da Colis Privé. Se a loja indicou Border Express, escolhe Border Express manualmente (abaixo). Espaços e minúsculas não importam ao colar. Sem número? Vê [como encontrar o teu número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento da Border Express explicados

O rastreador da Border Express mostra uma barra de progresso e uma lista de passos, do mais recente para o mais antigo, cada um com localidade e estado. Os passos conhecidos pelo código do Peek, por ordem habitual:

| A Border Express diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipment Data Received` | O remetente enviou os dados da encomenda à Border Express. A Border Express ainda não tem a encomenda. A barra mostra `Data Received`. | «Etiqueta criada» |
| `Picked Up` | A Border Express recolheu a encomenda ao remetente. O rastreador costuma listar este passo sem hora, e o Peek omite passos sem hora. | «Aceite pela transportadora», apenas quando o passo tem hora |
| `In Transit` | A caminho pela rede da Border Express. | «Em trânsito» |
| `On Board For Delivery` | No veículo de entrega. A barra mostra `On Board`. | «Em distribuição» |
| `Partially Delivered` | Alguns volumes da expedição foram entregues; os restantes continuam a caminho. | «Em trânsito» |
| `Partially Delivered - Final` | A entrega terminou: a expedição fecha com os volumes que chegaram. Se faltar algum, fala com o remetente. | «Entregue» |
| `Delivered` | Entregue. | «Entregue» |

O Peek mantém os passos no inglês da Border Express, com o seu próprio estado por cima. Um passo ausente desta tabela aparece como escrito e o Peek deduz o estado pelas palavras quando consegue.

## Entrega Border Express na Austrália

O rastreador da Border Express não indica outra transportadora para uma encomenda, pelo que o Peek acompanha na Border Express do primeiro passo até à entrega, sem transferência para outra transportadora.

Esta página não apresenta regras sobre tentativas de entrega, avisos, entrega sem assinatura, levantamento em depósito ou prazo de guarda, porque o Peek não pôde consultá-las no site da Border Express. Pergunta à loja remetente ou à Border Express. Vê [entrega falhada](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Encomenda Border Express sem atualizar, perdida ou entregue mas não recebida

**Número não encontrado.** Enquanto o rastreador da Border Express não listar o número, uma encomenda sob Border Express mostra «À espera de Border Express» e o Peek continua a verificar. Se dois dias depois a Border Express ainda não conhecer o número e nenhuma outra fonte responder, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar diariamente, e semanalmente após uma semana. Confirma o número no e-mail da loja: 8 dígitos, ou 4 letras e 5 a 8 dígitos. Se nada surgir após alguns dias, pergunta à loja se enviou mesmo.

**Parada em «Etiqueta criada».** A Border Express tem os dados da encomenda mas ainda nenhum registo com data e hora. O rastreador pode mostrar `Picked Up` sem hora — que o Peek omite —, por isso vê lá primeiro. Se continuar apenas em `Shipment Data Received`, pergunta à loja quando entregou a encomenda.

**Sem atualização há dias.** Quando uma encomenda em curso passa quatro dias sem novo passo, o Peek mostra a data do último registo («Sem atualizações desde …») e refere «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Border Express.», com a ligação «Pede ajuda a Border Express» para a página de apoio ao cliente. Vê [porque é que o seguimento para de atualizar](guide:tracking-not-updating).

**Parcialmente entregue.** Compara o que chegou com o teu pedido. O resto da expedição segue viagem e o rastreador da Border Express mostra os registos de cada volume, que o Peek omite.

**Entregue, mas nada chegou.** Procura junto à morada e pergunta aos vizinhos. Depois de entregue, o Peek lê também o nome de quem assinou quando consta do rastreador da Border Express. Em seguida contacta a loja. Mais em [entregue mas não recebida](guide:delivered-not-received).

**Perdida, danificada ou devolvida.** Foi a loja quem contratou a Border Express, logo é à loja que deves pedir reembolso ou novo envio. Vê [encomendas perdidas](guide:lost-parcel), [encomendas danificadas](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contacto e apoio ao cliente da Border Express

Como o Peek não conseguiu verificar o site da Border Express, esta página não indica telefone nem e-mail: o Peek lista apenas contactos lidos nas próprias páginas das transportadoras. Como a loja reservou a entrega, pergunta primeiro à loja.

Na página da encomenda no Peek, «Abrir o site de Border Express» abre o número no rastreador da Border Express. Antes de pagares qualquer taxa pedida por SMS, lê [SMS falsos de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda Border Express com o Peek

Cola o número no [Peek](/), que acompanha mais de 4500 transportadoras. Pode mostrar «A verificar serviços de seguimento…» enquanto pesquisa. Toca depois em «Alterar» e escolhe Border Express escrevendo `Border Express` na pesquisa (`BEX` também a encontra, mas mostra primeiro a BEX Express, da Sérvia). Para um código de barras de etiqueta, a Border Express aparece em «Corresponde a este número». Numa encomenda já adicionada usa «Alterar transportadora». Não precisas de conta, código postal nem telefone.

Se adicionares o número sem escolher transportadora, o Peek pergunta apenas a serviços de seguimento, nunca à Border Express, e mostra «Ainda sem histórico de seguimento» até algum conhecer a encomenda. Só quando um serviço de seguimento indica Border Express é que o Peek passa a consultá-la. Escolher Border Express manualmente é a forma direta de obter os seus passos.

Um link de seguimento da Border Express também funciona e identifica logo a Border Express («Transportadora detetada»): `borderexpress.com.au/tracking` com `cn=`, ou um link `bexonline.borderexpress.com.au/bxo/track`. Se escolheres Border Express para outro formato, o Peek indica «não emite números como este» e, após adicionares (se nenhum serviço de seguimento tiver a encomenda), mostra «Verifica o número de seguimento»: procura um carácter em falta ou a mais.

**Códigos de barras de etiqueta.** Como o rastreador da Border Express não os aceita, o Peek calcula o número de expedição a partir do código de barras: as quatro letras, depois os 11 dígitos após `BX` sem os zeros iniciais (mantendo pelo menos seis dígitos) e descartando os últimos 3 dígitos do volume. A página da encomenda mostra esse número em «Border Express segue a encomenda com o número», e «Abrir o site de Border Express» abre o Quick Track já com esse número de expedição. Se a Border Express não o encontrar assim, usa o número de expedição do e-mail da loja.

**O que vês.** O Peek mostra o estado, cada passo datado com localidade e estado, o dia previsto de entrega até à entrega, a hora de entrega, a localidade do destinatário (subúrbio, estado e código postal) e quem assinou depois de entregue. Omite a barra de progresso, a data de expedição, o número de volumes e os registos individuais de cada etiqueta, bem como a localidade do remetente; não mostra peso nem comprovativo de entrega. Cada passo traz o fuso horário do respetivo estado, garantindo a ordem certa entre estados de fusos diferentes e apresentando tudo no teu fuso horário («Horas no teu fuso horário:»).

**Frequência.** Enquanto mantiveres a encomenda aberta no Peek — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a Border Express até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6:00 (hora de Sydney) no dia em que a Border Express a regista em distribuição, enquanto o rastreador responder; fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), de hora em hora. Se o rastreador da Border Express não responder, o Peek consulta serviços de seguimento e mostra os dados deles nas suas palavras. Pode avisar-te para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não consegue contactar a Border Express nem a loja por ti.

## Perguntas sobre o seguimento Border Express

### O que é um número de expedição Border Express?

O número pelo qual a Border Express acompanha uma entrega: 8 dígitos, ou as 4 letras do remetente seguidas de dígitos. Todos os volumes da entrega ficam sob esse número, indicado pela loja no e-mail de envio ou na página do pedido.

### Posso seguir a Border Express com o código de barras da etiqueta?

No Peek, sim: cola o código de barras de 20 caracteres (4 letras, `BX` e 14 dígitos) e escolhe Border Express. O Peek calcula o número de expedição a partir dele. O rastreador próprio da Border Express aceita apenas o número de expedição.

### O que significa «On Board For Delivery» na Border Express?

A encomenda está no veículo de entrega. O Peek mostra-a como «Em distribuição».

### Porque é que o meu seguimento Border Express diz «Partially Delivered»?

A expedição tem vários volumes e só alguns foram entregues; os restantes continuam a caminho, pelo que o Peek mostra «Em trânsito». `Partially Delivered - Final` é diferente: a Border Express encerrou a expedição com os volumes chegados e o Peek mostra «Entregue».

### O que significa «Shipment Data Received» na Border Express?

O remetente enviou os dados da encomenda à Border Express, mas a Border Express ainda não a leu. O Peek mostra «Etiqueta criada».

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – nomes, Austrália, números de expedição e códigos de barras de etiqueta, links de seguimento, Quick Track, textos e etapas, passos sem hora, fusos estaduais, estimativa e hora de entrega, localidade do destinatário e assinante, campos omitidos, sem outra transportadora
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de outras transportadoras (Mondial Relay, Colisweb, Heppner, Colis Privé), nome de BEX Express, sem transferência de ou para a Border Express
:::
