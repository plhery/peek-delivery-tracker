---
title: Rastreio Border Express: códigos, status e entregas
description: Rastreie sua encomenda Border Express (BEX) na Austrália: formatos de código e etiqueta, significado de cada status em inglês e como acompanhar no Peek.
slug: border-express-tracking
published: 2026-11-27
updated: 2026-11-27
---

Border Express, ou BEX, é uma transportadora listada no catálogo do Peek exclusivamente na Austrália. Se uma loja virtual enviou seu pedido pela Border Express, o código para rastreamento é o número de remessa (*consignment number*): cole-o na caixa acima. A loja envia esse código no e-mail de confirmação de envio ou na página do seu pedido. O rastreador próprio da Border Express é o Quick Track no BEX Online, em bexonline.borderexpress.com.au. O Peek não conseguiu consultar o site da Border Express para montar esta página, então as informações abaixo refletem o que o código do Peek sabe sobre esse rastreador.

## Formato do código de rastreio Border Express

A Border Express rastreia entregas pelo número de remessa (*consignment number*). O código do Peek conhece três formatos. Veja exemplos com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `48291736` | 8 dígitos: um número de remessa. |
| `KLMN482917` | 4 letras (prefixo do remetente) seguidas de dígitos: também é um número de remessa. O Peek aceita de 5 a 8 dígitos após as letras. |
| `KLMNBX00000482917001` | 20 caracteres: o código de barras na etiqueta de um volume. Traz as 4 letras do remetente, BX, os dígitos da remessa preenchidos com zeros à esquerda até 11 posições e 3 dígitos do número do volume. |

Uma remessa pode reunir vários volumes, cada um com seu próprio código de barras de etiqueta. Todas as etiquetas começam com as mesmas letras e dígitos, e o número de remessa une tudo: é por ele que o rastreador da Border Express pesquisa, já que o site não aceita códigos de barras de etiqueta. O Peek aceita o código de etiqueta e extrai dele o número de remessa (veja abaixo).

O Peek não consegue identificar um código da Border Express apenas pelo formato. Seu catálogo não tem regra de detecção para os números de remessa da Border Express, e a regra para o código de barras da etiqueta gera apenas uma correspondência fraca. Outras transportadoras usam formatos iguais: para 8 dígitos, o Peek sugere [Mondial Relay](carrier:mondial-relay), [Colisweb](carrier:colisweb) e Heppner, todas na Europa, além da TForce Freight, transportadora de cargas da América do Norte, quando o último dígito passa na verificação dela. Quatro letras com 8 dígitos também batem com os códigos de 12 caracteres da [Colis Privé](carrier:colis-prive). Se a loja informou Border Express, escolha Border Express (veja abaixo). Espaços e letras minúsculas não fazem diferença ao colar. Não tem nenhum código? Veja [como encontrar seu código de rastreio](guide:find-tracking-number) e [formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio Border Express

O rastreador da Border Express mostra uma barra de progresso e uma lista de etapas, da mais recente para a mais antiga, cada uma com subúrbio e estado. Estas são as etapas conhecidas pelo código do Peek, na ordem em que a encomenda passa por elas:

| O que a Border Express exibe | O que significa para você | O que o Peek mostra |
| --- | --- | --- |
| `Shipment Data Received` | O remetente enviou os dados da encomenda à Border Express, que ainda não recebeu o pacote. Na barra de progresso aparece como `Data Received`. | "Etiqueta criada" |
| `Picked Up` | A Border Express coletou a encomenda com o remetente. O rastreador costuma listar essa etapa sem horário, e o Peek omite etapas sem horário. | "Recebida pela transportadora", apenas quando a etapa tem horário |
| `In Transit` | A caminho pela rede da Border Express. | "Em trânsito" |
| `On Board For Delivery` | No veículo de entrega. A barra de progresso exibe `On Board`. | "Saiu para entrega" |
| `Partially Delivered` | Alguns volumes da remessa foram entregues; os demais continuam a caminho. | "Em trânsito" |
| `Partially Delivered - Final` | A entrega foi encerrada: a remessa foi fechada com os volumes que chegaram. Se faltar algo, fale com o remetente. | "Entregue" |
| `Delivered` | Entregue. | "Entregue" |

O Peek mantém as etapas no texto original em inglês da Border Express, com o status resumido acima delas. Qualquer etapa fora desta tabela aparece como escrita, e o Peek interpreta o status pelo texto sempre que possível.

## Entrega Border Express na Austrália

O rastreador da Border Express não cita outra transportadora para a encomenda, então o Peek acompanha o envio pela própria Border Express da primeira etapa até a entrega, sem repasse para outra empresa.

Esta página não traz regras sobre tentativas de entrega, avisos deixados na porta, entrega sem assinatura (*safe drop*), retirada em depósito ou prazo de guarda: o Peek não pôde conferir esses detalhes no site da Border Express. Consulte a loja que fez o envio ou a própria Border Express. Veja também [entrega não realizada](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Encomenda Border Express sem atualização, perdida ou entregue mas não recebida

**Código não encontrado.** Enquanto o rastreador da Border Express não listar o código, uma encomenda cadastrada como Border Express mostra "Aguardando Border Express", e o Peek continua verificando. Se após dois dias a Border Express ainda não reconhecer o código e nenhum outro serviço responder por ele, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a consultar diariamente, e semanalmente após uma semana desde que você a adicionou. Confira o número no e-mail da loja: 8 dígitos, ou 4 letras e de 5 a 8 dígitos. Se nada aparecer depois de alguns dias, pergunte à loja se o pedido realmente foi despachado.

**Parado em "Etiqueta criada".** A Border Express tem os dados da encomenda, mas ainda não há registro com data e hora. O rastreador dela pode mostrar `Picked Up` sem horário, que o Peek omite; vale consultar lá primeiro. Se ainda constar apenas `Shipment Data Received`, pergunte à loja quando o pacote foi entregue à transportadora.

**Sem atualização há dias.** Quando uma encomenda em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito" fica quatro dias sem nova etapa, o Peek exibe um aviso com o dia da última movimentação: "Sem atualizações desde seg 12 out". Ele continua consultando a Border Express e mostra a próxima etapa assim que ela surgir. Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).

**Entrega parcial (`Partially Delivered`).** Confira o que chegou em relação ao seu pedido. O restante da remessa ainda está a caminho, e o rastreador da Border Express mostra os registros de cada volume separadamente, detalhe que o Peek não exibe.

**Entregue, mas nada chegou.** Procure nos arredores do endereço e pergunte aos vizinhos. O rastreador da Border Express mostra o nome de quem assinou o recebimento, informação que o Peek omite. Depois, fale com a loja. Saiba mais em [entregue mas não recebida](guide:delivered-not-received).

**Perdida, danificada ou devolvida.** Como foi a loja que contratou a Border Express, cabe a ela providenciar reembolso ou novo envio. Veja [encomendas perdidas](guide:lost-parcel), [encomendas danificadas](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento ao cliente Border Express

O Peek não conseguiu acessar o site da Border Express, portanto esta página não lista telefone nem e-mail: o Peek divulga apenas contatos lidos nas páginas da própria transportadora. Como a loja contratou o frete, fale com ela primeiro.

Na página da encomenda no Peek, "Abrir o site de Border Express" abre o seu número diretamente no rastreador da Border Express. Antes de pagar qualquer cobrança recebida por SMS sobre uma encomenda, leia [golpes por SMS de entrega](guide:delivery-scam-texts).

## Acompanhe sua encomenda Border Express no Peek

Cole o número no [Peek](/), que acompanha mais de 4.500 transportadoras. O app pode mostrar "Consultando serviços de rastreamento…" enquanto pesquisa. Em seguida, toque em "Trocar" e escolha Border Express: digite Border Express na busca. Digitar BEX também encontra a transportadora, mas lista primeiro a BEX Express, da Sérvia. Para um código de barras de etiqueta, Border Express aparece em "Compatível com este código". Em uma encomenda já adicionada, use "Trocar transportadora". Não é necessário criar conta nem informar código postal ou telefone.

Se você adicionar o código sem escolher uma transportadora, o Peek nunca consulta o rastreador da Border Express diretamente: ele consulta serviços de rastreamento (e a Colis Privé no caso de 4 letras com 8 dígitos) e mostra "Ainda sem histórico de rastreamento" até que algum deles encontre o pacote. Só quando um serviço de rastreamento identifica Border Express é que o Peek passa a consultar a própria Border Express. Escolher você mesmo a Border Express é o caminho para receber as etapas do rastreador dela.

Um link de rastreio da Border Express também funciona e identifica Border Express na hora ("Transportadora detectada"): borderexpress.com.au/tracking com `cn=`, ou um link bexonline.borderexpress.com.au/bxo/track. Se você escolher Border Express para um código com outro formato, o Peek diz que ela "não emite códigos como este" e, depois de adicionada a encomenda, a menos que um serviço de rastreamento a encontre, mostra "Confira o código de rastreamento": verifique se há algum caractere faltando ou sobrando.

**Códigos de barras de etiqueta.** O rastreador da Border Express não aceita esses códigos, então o Peek calcula o número de remessa a partir da etiqueta: as quatro letras seguidas dos 11 dígitos após BX sem os zeros à esquerda, mantendo pelo menos seis dígitos. Os últimos 3 dígitos (número do volume) são descartados. A página da encomenda mostra esse número em "Border Express rastreia com o código", e o link "Abrir o site de Border Express" também abre o rastreador com esse número de remessa. Se a Border Express não localizar o pacote por ele, use o número de remessa do e-mail da loja.

**O que você vê.** O Peek mostra o status, cada etapa com subúrbio e estado, a data prevista de entrega até a conclusão e o horário de entrega. Ele omite a barra de progresso, a data de despacho, a quantidade de volumes e os registros individuais de cada volume, a cidade e o código postal do remetente, o seu código postal e o nome de quem assinou; enquanto o pacote viaja, o mapa exibe a cidade de destino. O Peek não mostra peso nem comprovante de entrega da Border Express. Cada etapa usa o fuso horário do respectivo estado, mantendo as etapas de estados em fusos diferentes na ordem correta.

**Frequência de verificação.** Enquanto a encomenda estiver aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos, ou a cada 2 minutos a partir das 6h no horário do estado da última etapa no dia em que a Border Express registrar a saída para entrega, enquanto o rastreador da Border Express responder; nos demais casos, e após dois dias sem novidades (12 horas enquanto a encomenda ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada" sem novidades. Se o rastreador da Border Express ficar fora do ar, o Peek consulta serviços de rastreamento e exibe o que eles retornarem, nos termos deles. Você pode receber alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode entrar em contato com a Border Express nem com a loja em seu nome.

## Perguntas sobre o rastreio Border Express

### O que é um número de remessa (consignment number) da Border Express?

É o número pelo qual a Border Express rastreia uma entrega: 8 dígitos, ou as 4 letras do remetente seguidas de dígitos. Todos os volumes da remessa ficam vinculados a ele. A loja informa esse código no e-mail de envio ou na página do pedido.

### Posso rastrear a Border Express usando o código de barras da etiqueta?

No Peek, sim: cole o código de barras de 20 caracteres (4 letras, BX e 14 dígitos) e selecione Border Express. O Peek calcula o número de remessa a partir dele. O rastreador da própria Border Express aceita apenas o número de remessa.

### O que significa "On Board For Delivery" no rastreio da Border Express?

Significa que a encomenda está no veículo de entrega. O Peek exibe essa etapa como "Saiu para entrega".

### Por que meu rastreio Border Express diz "Partially Delivered"?

Sua remessa tem vários volumes e apenas parte deles foi entregue. Os demais continuam a caminho, por isso o Peek mostra "Em trânsito". Já `Partially Delivered - Final` é diferente: a Border Express encerrou a remessa com os volumes que chegaram, e o Peek exibe "Entregue".

### O que significa "Shipment Data Received" no rastreio da Border Express?

O remetente enviou os dados da encomenda à Border Express, mas a transportadora ainda não registrou o pacote fisicamente. O Peek mostra "Etiqueta criada".

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – nomes, Austrália, números de remessa e códigos de barras de etiqueta, links de rastreio, rastreador, textos e etapas, etapas sem horário, fusos estaduais, previsão e horário de entrega, o que o Peek omite, ausência de outra transportadora citada
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de código de outras transportadoras (Mondial Relay, Colisweb, Heppner, Colis Privé), nome da BEX Express, ausência de repasse para ou da Border Express
:::
