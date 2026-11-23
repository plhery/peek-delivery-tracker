---
title: Seguimento Metropolitan Warehouse (MWD): móveis
description: Acompanhe uma encomenda Metropolitan Warehouse & Delivery (MWD): número de 12 dígitos, estados, agendamento de entrega, prazo de 72 h para danos e contactos.
slug: seguimento-metropolitan-warehouse
published: 2026-11-23
updated: 2026-11-23
---

A Metropolitan Warehouse & Delivery (MWD) é uma empresa familiar norte-americana de entrega de mobiliário e logística 3PL fundada em 1989 em Perth Amboy, Nova Jérsia. Através de 47 centros e plataformas de cross-docking, entrega ao domicílio para lojas online, marcas de mobiliário e designers nos 48 estados contíguos dos EUA. Introduza o número de 12 dígitos na [página de seguimento da MWD](https://www.gomwd.com/track-a-shipment) ou cole-o na caixa acima.

## Número de seguimento MWD: 12 dígitos

O [rastreador da MWD](https://main.gomwd.com/client/wixtrackorder) tem apenas um campo, *Tracking #*, e aceita exatamente 12 dígitos e mais nada (dígitos inventados):

| Número | O que é |
| --- | --- |
| `584927163048` | O número de seguimento MWD. Na página da encomenda surge em grupos de quatro: `5849 2716 3048`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Outras referências na página da encomenda (da MWD, da loja ou de uma transportadora parceira). Não servem para seguimento. |

Não pede código postal nem telefone, mas usa reCAPTCHA e pode pedir a introdução de um captcha de texto. Qualquer valor que não tenha 12 dígitos devolve `Please enter a valid tracking number of 12 digits.`; um número desconhecido mostra `Tracking id does not exist.`. Confirme-o no e-mail da loja ou peça ao vendedor ([como encontrar o número de seguimento](guide:find-tracking-number)). A MWD disponibiliza páginas de seguimento com marca branca, pelo que a loja pode enviar-lhe uma ligação com o próprio nome.

## Significado dos estados da Metropolitan Warehouse

O histórico da MWD surge em inglês, do mais recente para o mais antigo, com data, descrição, cidade e estado. Eis os estados mapeados pelo [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse), pela ordem habitual:

| Mensagem MWD | No Peek | O que significa para si |
| --- | --- | --- |
| `Order registered` | Etiqueta criada | A encomenda foi registada na MWD. |
| `Pickup document attached` | sem alteração | Documento de recolha anexado; nada se moveu ainda. |
| `Pickup scheduled for 11/02/2026` | Etiqueta criada | A MWD agendou a recolha no fornecedor, mas ainda não tem o móvel. |
| `Pickup completed, No exceptions` | Aceite pela transportadora | A MWD recolheu o artigo sem anomalias registadas. |
| `Order enroute to Metro-Hub` | Em trânsito | A caminho de um centro MWD. |
| `Furniture Received by Metro-Hub` | Em trânsito | Registada num centro MWD (identificado pela cidade). |
| `Reached Delivery Hub` | Em trânsito | Chegou ao centro responsável pela entrega final. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | sem alteração | Entrega prevista para essa semana; o dia exato chega com o agendamento. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | sem alteração | A entrega foi movida para outra semana. |
| `Delivery Appointment Canceled` | sem alteração | O agendamento foi cancelado; deve seguir-se outro, caso contrário fale com a loja ou a MWD. |
| `Arrived at Delivery Location` | Em distribuição | A equipa chegou à sua morada. |
| `Delivery Completed, No Exceptions` | Entregue | Entregue sem ressalvas registadas (verifique tudo antes de a equipa sair). |

A página da MWD escreve por vezes `Metro-<Warehouse>-Hub` com o nome em branco; o Peek limpa para `Metro-Hub`. Não existe um registo prévio de saída matinal: a primeira linha do dia de entrega é `Arrived at Delivery Location`, já com a equipa à porta.

## Agendamento, nível de serviço e entrega falhada

**Agendamento e seguimento em direto.** Nas páginas [White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) e [Services](https://www.gomwd.com/services), a MWD indica que o cliente marca a janela de entrega em autosserviço, recebe lembretes automáticos e, no dia da entrega, uma ligação em tempo real com a localização da equipa e hora estimada de chegada. A página de seguimento apresenta também a janela marcada e o comprovativo de entrega.

**O que a equipa faz.** Depende do nível contratado pela loja (mostrado em *Service Type* na página da encomenda):

| Nível de serviço | O que inclui |
| --- | --- |
| Full White Glove | Colocação na divisão escolhida, montagem ligeira e recolha de todas as embalagens. |
| White Glove (No Assembly) | Igual para móveis pré-montados e tapetes, sem montagem. |
| Room of Choice | Transporte para o interior na divisão indicada, sem montagem. |
| Threshold Delivery | Entrega na primeira área seca da casa (entrada ou garagem), sem desembalagem nem montagem. |
| Doorstep Delivery | Entrega fora da porta sem assinatura, para envios mais leves. |
| Curbside Delivery | Entrega no passeio ou entrada da garagem; o resto fica ao seu cuidado. |

Se faltar montagem ou remoção de embalagens face ao que comprou, esclareça com a loja vendedora.

**Quando não está em casa.** As páginas públicas da MWD não explicam como remarcar um agendamento falhado, pelo que convém avisar a MWD ou a loja antes do dia. Os [termos de transporte](https://sso.gomwd.com/Account/TermsandConditions) determinam que, em caso de recusa ou de entrega impossível por causa do remetente ou do destinatário, a responsabilidade passa a ser a de depositário: após tentativa de notificação por telefone ou via eletrónica, podem iniciar-se taxas de armazenamento no dia útil seguinte; sem instruções em 48 horas envia-se um segundo aviso final, e decorridos mais 10 dias a MWD pode colocar a mercadoria em venda pública. Veja também [entregas falhadas](guide:missed-delivery).

## Encomenda MWD danificada, atrasada ou desaparecida

**Danos.** Inspecione cada peça antes de a equipa partir. Os [termos de transporte](https://sso.gomwd.com/Account/TermsandConditions) no portal de clientes fixam **72 horas** após a entrega para uma reclamação escrita por perda ou danos e permitem à MWD reparar ou substituir o bem. Na falta de contrato contrário ou opção *Enhanced Valuation*, a responsabilidade máxima predefinida limita-se a 500 USD por envio, ficando excluídos danos ocultos em embalagens preparadas pelo remetente sem sinais visíveis de impacto. Como a loja é cliente da MWD, avise-a imediatamente. Consulte [encomenda danificada](guide:damaged-parcel).

**Atraso.** Enquanto o histórico parar em `Pickup scheduled for`, a MWD ainda não recolheu o móvel. Segundo o [guia da FTC sobre a regra de vendas por correio, internet ou telefone](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule), uma encomenda só conta como expedida quando chega às mãos da transportadora: se um vendedor não conseguir expedir no prazo anunciado — ou em **30 dias** se não indicou prazo — tem de pedir o seu consentimento para o atraso e oferecer cancelamento com reembolso total rápido. Sem novidades dias após a recolha, veja [seguimento sem atualizações](guide:tracking-not-updating).

**Não entrega ou extravio.** Os mesmos termos concedem **30 dias**, após um tempo razoável de entrega, para reclamar por escrito a não entrega (e 6 meses para ação judicial após recusa escrita). Fale com a loja e com a MWD e consulte [entregue mas não recebida](guide:delivered-not-received) e [encomenda perdida](guide:lost-parcel).

## Contactos da Metropolitan Warehouse & Delivery

De acordo com a [página de contactos da MWD](https://www.gomwd.com/contact-us):

- **Linha de apoio:** (800) 300-7417 — equipa disponível de segunda a sexta-feira (sem horário específico publicado).
- **E-mail geral:** `comments@gomwd.com`.
- **Escritórios:** sede em Perth Amboy, Nova Jérsia (referida em [About Us](https://www.gomwd.com/about-metropolitan) e [Services](https://www.gomwd.com/services)), e *Corporate Office* apresentada na página de contactos em 310 Ramco Street, Suite 150, West Sacramento, CA 95691.

A opção *Info About My Order* destina-se a clientes empresariais (os retalhistas que enviam com a MWD): peça à loja para acionar a equipa *Customer Success*.

## Acompanhar uma encomenda MWD com o Peek

O [Peek](/) não consegue identificar a MWD só pelo formato, pois doze dígitos correspondem a muitas transportadoras ([porque os formatos se sobrepõem](guide:tracking-number-formats)). Cole o número na mesma: o Peek segue mais de 4500 transportadoras. **Nos Estados Unidos**, consulta a MWD entre as primeiras transportadoras de 12 dígitos — ao lado da [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) e [Purolator](carrier:purolator) (quando começa por 0–6 com dígito de controlo válido) — e mostra «Metropolitan Warehouse & Delivery tem esta encomenda.» quando a MWD a detém. **Fora dos Estados Unidos**, o Peek trata a MWD como último recurso: pergunta primeiro às restantes transportadoras possíveis e só consulta a MWD se nenhuma conhecer o número. Introduzido na caixa desta página, o número vai primeiro à MWD, esteja onde estiver. Pode também escolher Metropolitan Warehouse & Delivery com «Alterar» ao adicionar ou «Alterar transportadora» numa encomenda já guardada (pesquisar «MWD» também funciona). Aceita espaços; cole diretamente os 12 dígitos, já que o Peek não tem regra para ligações da MWD.

O Peek lê o rastreador da MWD: estado, histórico nas palavras inglesas da MWD, cidade e estado de cada linha no mapa e o peso, em quilogramas ou em libras conforme as definições. Mostra cada hora no fuso horário do seu dispositivo e indica-o por baixo do histórico. Deixa de fora a cidade e o código postal de destino, referências da encomenda e do cliente, número PRO de parceiro, contagem de volumes, volume e barra de progresso. Não apresenta data estimada, comprovativo de entrega nem localização em direto da equipa, nem segue transportadoras parceiras.

O Peek consulta diretamente a MWD até de 10 em 10 minutos enquanto tem a encomenda aberta ou no dia da entrega com alertas ativos, e de 2 em 2 minutos a partir das 6h00 hora local no dia em que surge `Arrived at Delivery Location`; caso contrário, de hora a hora. Após 12 horas em «Etiqueta criada» ou «Aceite pela transportadora», ou após dois dias sem novos registos, verifica de hora a hora mesmo com o ecrã aberto, e de 6 em 6 horas após sete dias ainda em «Etiqueta criada». Através de serviços de seguimento, consulta de hora a hora, de 3 em 3 horas após um dia sem atualizações e até de 15 em 15 minutos no dia da entrega.

Se uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem um novo registo, o Peek assinala-a com o dia desse último registo (por exemplo, «Sem atualizações desde seg 12 out»), mostra «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Metropolitan Warehouse & Delivery.» e apresenta a ligação «Pede ajuda a Metropolitan Warehouse & Delivery» para a página de contactos da MWD. Após mais de três semanas sem novidades, muda para: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Metropolitan Warehouse & Delivery.»

Pode receber alertas para «Todos os registos», «Passos importantes» ou «Só a entrega», que na MWD começa em `Arrived at Delivery Location`. O Peek não marca nem altera agendamentos, não abre reclamações nem contacta a MWD ou a loja por si.

## Perguntas sobre o seguimento Metropolitan Warehouse

### O que é a Metropolitan Warehouse & Delivery?

Uma empresa familiar norte-americana de entrega de mobiliário e armazenagem 3PL sediada em Perth Amboy (Nova Jérsia) desde 1989, com 47 centros nos 48 estados contíguos. Lojas, marcas de mobiliário e designers contratam-na; particulares não a reservam diretamente.

### O que significa «Delivery Scheduled for WEEK OF»?

A MWD planeou a entrega para a semana dessa data. O dia exato e a janela horária ficam definidos com o agendamento. `Delivery Rescheduled for WEEK OF` indica mudança para outra semana.

### A Metropolitan Warehouse monta móveis?

Apenas no nível *Full White Glove*, que inclui montagem ligeira e remoção de embalagens. Os outros níveis — incluindo *White Glove (No Assembly)* — não fazem montagem. Consulte *Service Type* na página da encomenda MWD.

### Como remarco a minha entrega da Metropolitan Warehouse?

As páginas da MWD explicam como marcar a janela inicial em autosserviço, mas não como alterá-la. Ligue para a linha de apoio (800) 300-7417 de segunda a sexta-feira ou contacte a loja.

### Quanto tempo tenho para comunicar danos à MWD?

Os termos de transporte no portal de clientes estabelecem 72 horas após a entrega para uma reclamação escrita por perda ou danos. Como a loja é cliente da MWD, avise-a de imediato também.

:::sources
- [Metropolitan Warehouse & Delivery: Início](https://www.gomwd.com/) – desde 1989, linha de apoio
- [MWD: About Us](https://www.gomwd.com/about-metropolitan) – empresa familiar, sede em Perth Amboy, 48 estados contíguos
- [MWD: Track a Shipment](https://www.gomwd.com/track-a-shipment) – seguimento, janela de entrega e comprovativo de entrega
- [MWD: Track Your Order](https://main.gomwd.com/client/wixtrackorder) – campo único de 12 dígitos, mensagem de erro, reCAPTCHA / captcha de texto
- [MWD: White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – níveis de serviço, agendamento em autosserviço, lembretes e seguimento em direto
- [MWD: Services](https://www.gomwd.com/services) – Doorstep Delivery, 47 centros e plataformas de cross-docking, 5 centros 3PL completos, páginas de seguimento de marca branca
- [MWD: Contact Us](https://www.gomwd.com/contact-us) – apoio (800) 300-7417 seg–sex, comments@gomwd.com, Corporate Office West Sacramento, área para clientes
- [MWD: Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – reclamações 72 h (danos) e 30 dias (não entrega), limite de 500 USD, armazenamento por falha na entrega
- [FTC: Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – entrega à transportadora, regra dos 30 dias, consentimento de atraso e reembolso
- [Universal Parcel Scraper: Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – regra de 12 dígitos, mensagem de número desconhecido, estados e dados lidos pelo Peek
:::
