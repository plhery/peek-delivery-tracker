---
title: Rastreio Onlog Express: códigos AA, estados e apoio
description: Acompanha uma encomenda Onlog Express no Brasil: códigos AA de 12 carateres, estados nos centros de distribuição, aviso contra fraudes e apoio.
slug: rastreio-onlog-express
published: 2026-11-24
updated: 2026-11-24
---

A **Onlog Express** (*Onlog Franqueadora*) é uma rede brasileira de entrega de encomendas e logística sediada em São Paulo, com mais de 20 anos de experiência em parcerias logísticas e mais de 18 milhões de encomendas entregues em todo o Brasil ([onlogexpress.com.br](https://onlogexpress.com.br/)). Ligando lojas online e empresas a operadores nacionais de entrega em modalidades que vão da entrega urgente em minutos ou no próprio dia (*Entrega em Minutos*, *Entrega no Dia*) ao prioritário (*Expresso D1*) e económico (*Econômico D2*), além de fulfillment e carga internacional, a Onlog disponibiliza rastreio público por código de objeto ou CPF/CNPJ em [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Podes verificar um envio nesse portal ou colar o teu código `AA` de 12 carateres na caixa acima.

## Números de rastreio Onlog Express (formato `AA`)

O rastreio público de visitante da Onlog Express utiliza um código de objeto de 12 carateres iniciado por `AA` seguido de 10 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `AA4829173605` | Código de objeto Onlog Express de 12 carateres (`AA` + 10 dígitos). |
| `AA7391846208` | Outra referência de encomenda `AA` Onlog Express de 12 carateres. |

Procura a referência `AA` no e-mail de expedição da loja ou na ligação de rastreio `onlog.app.br/rastreio/...`. Se não tiveres o código, o portal web da Onlog em [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) permite aos destinatários pesquisar usando o seu CPF ou CNPJ brasileiro (incluindo uma pesquisa separada para objetos anteriores a 01/01/2025). Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Onlog Express

A Onlog Express publica descrições de registos em português desde a criação da etiqueta e recolha até às transferências nos centros de distribuição e à assinatura do destinatário:

| A Onlog Express indica (PT-BR) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Envio criado e etiqueta emitida eletronicamente antes da recolha física. | «Etiqueta criada» |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Encomenda entregue numa unidade Onlog ou recolhida junto do remetente. | «Aceite pela transportadora» |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Recebida num centro de distribuição, expedida entre instalações ou encaminhada para o operador logístico regional. | «Em trânsito» |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | Em distribuição hoje com o estafeta local para entrega ao domicílio. | «Em distribuição» |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Entregue e assinada na morada de destino. | «Entregue» |

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` significa que a encomenda foi encaminhada para o operador logístico responsável pelo troço seguinte.

## Serviços de entrega e aviso oficial contra fraudes de taxas

Segundo [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Níveis de serviço:** **Entrega em Minutos** (urgente), **Entrega no Dia** (no próprio dia), **Expresso D1** (trânsito prioritário) e **Econômico D2** (económico), com prazos exatos e disponibilidade dependentes dos códigos postais (CEP) de origem e destino.
- **Aviso antifraude importante:** a Onlog publica um aviso explícito na sua página inicial afirmando que **a Onlog não pede pagamentos de taxas por e-mail, WhatsApp ou telefone**. Nunca pagues impostos ou taxas de desbloqueio exigidas por mensagens não solicitadas em nome da Onlog. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Encomenda Onlog Express atrasada, sem novidades ou perdida

- **Pausa após `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** os registos param frequentemente entre hubs regionais ou durante a passagem para o parceiro local da última milha entre estados brasileiros. Consulta [por que o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Entregue sem recibo ou encomenda desaparecida:** confirma primeiro na portaria (*portaria*), com familiares e vizinhos; depois contacta a loja online onde compraste e fala com o apoio da Onlog em São Paulo durante o horário laboral com o teu código `AA`. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente Onlog Express e contactos em São Paulo

Canais oficiais publicados em [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Telefone (São Paulo):** `(11) 5662-8108` e `(11) 5663-3829`.
- **E-mail:** `atendimento@onlogexpress.com.br`.
- **Horário de atendimento:** segunda a sexta-feira, das `08:00` às `18:00` BRT (encerrado sábados e domingos).
- **Sede (*Matriz São Paulo*):** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, Brasil.
- **Rastreio online:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Lembra-te do aviso oficial da Onlog: a Onlog nunca cobra taxas de libertação por e-mail, WhatsApp ou telefone. Ignora e denuncia qualquer mensagem que peça um pagamento PIX ou com cartão para desbloquear uma encomenda. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda Onlog Express com o Peek

Quando introduzes um código `AA` de 12 carateres (`AA4829173605` ou `AA7391846208`, inventados), o [Peek](/) consulta primeiro a [Colis Privé](carrier:colis-prive), a [Aramex Austrália](carrier:aramex-au) e a [Aramex Nova Zelândia](carrier:aramex-nz), depois os serviços de rastreio e por fim a Onlog Express — cujo rastreio abre num navegador —, mostrando «Onlog Express tem esta encomenda.» assim que a Onlog confirma. O Peek reconhece ainda como Onlog ligações coladas `onlog.app.br/rastreio/AA...`. Se precisares de escolher a Onlog manualmente, toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda (pesquisar «ONLOG» ou «Onlog Franqueadora» também a encontra).

O Peek lê o rastreio da Onlog num navegador e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Entregue»), o texto português do registo e o local. A Onlog apresenta cada registo ao minuto, sem o desfasamento em relação a UTC: o Peek data-o no fuso horário do respetivo local (São Paulo para um registo em São Paulo, Manaus para Manaus), um registo sem local no fuso do registo vizinho ou, na falta dele, no de São Paulo, e mostra tudo na hora do teu dispositivo. O rastreio da Onlog não indica dia estimado de entrega, peso, códigos de evento nem hora de entrega.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 30 em 30 minutos — o intervalo mínimo permitido pela Onlog entre duas consultas — e aguarda uma hora após uma verificação falhada; não faz verificações de 2 em 2 minutos no dia em que a encomenda está em distribuição. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Onlog não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 30 em 30 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Onlog Express.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Onlog Express.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode procurar encomendas por CPF/CNPJ, editar moradas de entrega nem contactar a Onlog Express por ti.

## Perguntas sobre o rastreio Onlog Express

### Como é um número de rastreio Onlog Express?

Os códigos de rastreio Onlog Express começam por `AA` seguidos de 10 dígitos (12 carateres no total, por exemplo `AA4829173605`, inventado).

### Posso seguir uma encomenda Onlog Express usando o meu CPF ou CNPJ?

Sim: no site da Onlog em [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) podes mudar para o separador **POR CPF/CNPJ** se não tiveres o código `AA`. O Peek utiliza apenas o código de objeto `AA`.

### Recebi uma mensagem WhatsApp ou e-mail a pedir uma taxa da Onlog — é verdadeiro?

Não. A Onlog declara explicitamente em [onlogexpress.com.br](https://onlogexpress.com.br/) que **nunca** solicita pagamentos de taxas por e-mail, WhatsApp ou telefone. Não pagues.

### Como contactar o apoio ao cliente da Onlog Express?

Liga para `(11) 5662-8108` ou `(11) 5663-3829`, ou escreve para `atendimento@onlogexpress.com.br` de segunda a sexta-feira das 08h00 às 18h00 BRT.

:::sources
- [Onlog Express: site oficial](https://onlogexpress.com.br/) – níveis de serviço (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18M+ encomendas, 20+ anos, aviso antifraude e contactos da sede em São Paulo
- [Onlog Express: rastreio oficial (Conecta)](https://conecta.log.br/rastreio.php) – rastreio público por código de objeto ou CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – formato `AA\d{10}`, regra de ligação `onlog.app.br/rastreio/`, mapa de 13 frases em português, consulta em navegador no máximo de 30 em 30 minutos (60 após erro) e relógios de registo
:::
