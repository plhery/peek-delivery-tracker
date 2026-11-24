---
title: Rastreio Sengi Express: códigos China–Itália e apoio
description: Acompanha uma encomenda Sengi Express (grupo Poste Italiane) da China para Itália ou Europa: códigos SDA/MT, alfândega, entrega Crono e contactos.
slug: rastreio-sengi-express
published: 2026-11-24
updated: 2026-11-24
---

A **Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, em chinês *广州森吉物流有限公司*) é um operador logístico transfronteiriço controlado maioritariamente pela **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Especializada na injeção direta de comércio eletrónico China–Itália e China–Europa, a Sengi gere consolidação na China, transporte aéreo e marítimo, desalfandegamento europeu B2C/B2B em plataformas como Milão Malpensa (**MXP**), Amesterdão (**AMS**), Liège (**LGG**) e Paris (**CDG**), transporte rodoviário europeu através da Sennder (do grupo Poste Italiane) e entrega final ao domicílio ou cacifo em Itália pela rede Crono da [Poste Italiane](carrier:poste-italiane). Podes acompanhar uma encomenda em [portal.sengiexpress.com](https://portal.sengiexpress.com/home) ou colar o teu código na caixa acima.

## Números de rastreio Sengi Express (formatos SDA `...C...F` e `MT`)

A Sengi Express emite frequentemente já na China uma guia **Poste Italiane / SDA** de 13 carateres para que o mesmo código funcione desde o armazém chinês da Sengi até à entrega em Itália, além de códigos transfronteiriços de 16 carateres terminados em `MT`:

| Formato (inventado) | O que é |
| --- | --- |
| `482C73918462F` | Guia SDA / Poste Italiane de 13 carateres (`3 dígitos` + `C` + `8 dígitos` + `F`) seguida pela Sengi Express antes de a Poste Italiane a registar em Itália. |
| `SENGI482917360MT` | Código transfronteiriço Sengi de 16 carateres (`5 letras` + `9 dígitos` + `MT`). |

A Sengi atribui à encomenda destinada a Itália a guia SDA antes de a SDA a conhecer, pelo que o rastreio da Poste Italiane pode não encontrar o número no início; o rastreio da Sengi acompanha a encomenda desde o armazém na China até ao destinatário em Itália. Quando escolhida manualmente, o rastreio da Sengi aceita ainda outras guias alfanuméricas de parceiros com 8 a 40 carateres. Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Sengi Express

A Sengi Express publica descrições em inglês (e chinês) dos registos transfronteiriços acompanhadas de códigos de evento opcionais e datas/horas:

| A Sengi Express indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (código `10`) | O comerciante enviou os dados eletrónicos antes da entrada física no armazém da Sengi na China. | «Etiqueta criada» |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | O voo internacional aterrou no aeroporto europeu de entrada (como MXP, AMS, LGG ou CDG). | «Em trânsito» |
| `Arrived at customs clearance warehouse` | A encomenda deu entrada no armazém aduaneiro europeu para desalfandegamento. | «Na alfândega» |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Selecionada pelas alfândegas europeias para verificação do valor declarado ou do conteúdo (`VM`). | «Na alfândega» |
| `Ready for transit to last mile operator` | Desalfandegada e pronta para entrega à [Poste Italiane](carrier:poste-italiane) ou transportadora de destino. | «Em trânsito» |

Um registo com outro texto define a sua etapa a partir do texto. A Sengi indica que os destinatários também podem seguir a encomenda no site da [Poste Italiane](carrier:poste-italiane), com a mesma guia `...C...F` ou com o novo número quando a Sengi reetiqueta o volume.

## Injeção direta, plataformas aduaneiras europeias e entrega Poste Italiane

De acordo com [Sobre a Sengi Express](https://portal.sengiexpress.com/about/) e [Produtos Sengi Express](https://portal.sengiexpress.com/product/):

- **Portas aéreas europeias:** a Sengi realiza desalfandegamento direto B2C e B2B em Milão Malpensa (`MXP`), Amesterdão (`AMS`), Liège (`LGG`) e Paris Charles de Gaulle (`CDG`).
- **Serviços de última milha Poste Italiane:** em Itália, a Sengi injeta encomendas nos serviços **Crono** normal, **Crono Express**, **Extralarge** (grandes formatos / última milha FBA), **Crono ADP** (cacifos) e **Crono Reverse** (devoluções) da Poste Italiane.
- **Controlos de valor aduaneiro (`VM`) e taxas UE:** envios da China para a UE têm de cumprir as regras aduaneiras europeias, IVA de importação (IOSS ou pago na entrega) e encargos aduaneiros/de gestão da UE sobre pedidos extracomunitários. Se surgir `supposed as undervaluation, waiting for customs check(VM)`, a alfândega está a verificar o valor declarado junto do importador ou despachante — aguarda a libertação ou avisa o vendedor. Consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda Sengi Express parada, sem novidades ou desaparecida

Como os marketplaces e vendedores chineses detêm o contrato de transporte com a Sengi Express, **contacta sempre primeiro o teu vendedor ou marketplace** para reembolsos, reenvios ou documentos de valor aduaneiro.

- **Pausa antes dos registos italianos:** as encomendas param frequentemente vários dias entre `Order information received`, `Arrived at Destinated Airport` e `Ready for transit to last mile operator` enquanto os lotes aéreos passam a alfândega europeia e seguem por camião para Itália. Consulta [por que o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Seguir na Poste Italiane após entrega:** assim que surgir `Ready for transit to last mile operator` num envio italiano `PI`, seguir o mesmo número `...C...F` (ou o novo número reetiquetado mostrado pelo Peek) na [Poste Italiane](carrier:poste-italiane) revela a triagem italiana, tentativas de entrega e detalhes Punto Poste ou cacifo.
- **Perdida, danificada ou dada como entregue:** confirma primeiro com vizinhos e na estação local da Poste Italiane / Punto Poste, e abre disputa no marketplace antes de terminar a proteção ao comprador. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos Sengi Express

Contactos oficiais publicados em [Sobre a Sengi Express](https://portal.sengiexpress.com/about/) e [Contacto Sengi Express](https://portal.sengiexpress.com/contact/):

- **E-mail:** `service@sengiexpress.com`.
- **Formulário de contacto online:** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Operador:** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), controlado maioritariamente pela Poste Italiane.

> Tem cuidado com SMS falsas afirmando que é preciso pagar uma taxa da Poste Italiane ou aduaneira através de uma ligação desconhecida. Confirma sempre qualquer encargo real na página da tua encomenda no marketplace ou no site oficial da Poste Italiane. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda Sengi Express com o Peek

Quando introduzes um código `...C...F` de 13 carateres (`482C73918462F`, inventado), o [Peek](/) pergunta primeiro à [Poste Italiane](carrier:poste-italiane), depois à AliExpress e à Sengi Express (Sengi antes da AliExpress em Itália; na China primeiro AliExpress, depois Sengi), e mostra «Sengi Express tem esta encomenda.» quando a Sengi a detém. Para um código `...MT` (`SENGI482917360MT`, inventado), o Peek consulta a [MaltaPost](carrier:maltapost), cujos números também terminam em `MT`: para esse código ou outra guia parceira, toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda e seleciona **Sengi Express** (pesquisar «Sengi» também a encontra).

O Peek lê o rastreio da Sengi e mostra a etapa («Etiqueta criada», «Em trânsito», «Na alfândega» ou outra etapa quando o texto de um registo o indicar), o texto em inglês da Sengi (chinês quando não há inglês) e o local. Os códigos de evento definem a etapa e não aparecem em nenhuma app Peek. A Sengi indica um fuso UTC nos seus registos; um registo sem fuso é datado no fuso horário do respetivo local (Itália para `MXP`, Pequim para Cantão) ou no do registo vizinho, e o Peek mostra-os todos na hora do teu dispositivo. Na linha Poste Italiane da Sengi, o Peek verifica se a Poste Italiane tem a encomenda: a ficha apresenta então «Entrega:» e o nome Poste Italiane, listando o novo número quando a Sengi reetiqueta o envio.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Sengi mostrasse um registo de saída para distribuição (nenhum até agora), de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da Sengi responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Sengi não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Sengi Express.», com uma ligação «Pede ajuda a Sengi Express» para a página de contacto da Sengi. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Sengi Express.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode desalfandegar encomendas, editar moradas de entrega nem contactar a Sengi Express ou a Poste Italiane por ti.

## Perguntas sobre o rastreio Sengi Express

### O que é a Sengi Express?

A Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) é um operador logístico de comércio eletrónico China–Itália e China–Europa controlado pela **Poste Italiane**, assegurando consolidação na origem, desalfandegamento europeu (MXP, AMS, LGG, CDG) e injeção de última milha na rede Crono da Poste Italiane.

### Por que aparece primeiro na Sengi Express o meu código `...C...F` da Poste Italiane?

A Sengi atribui a uma encomenda para Itália a guia SDA / [Poste Italiane](carrier:poste-italiane) (`482C73918462F`, inventado) antes de a SDA a conhecer. O rastreio da Sengi mostra os registos de voo e alfândega, enquanto o da Poste Italiane pode ainda não encontrar o número no início.

### O que significa `supposed as undervaluation, waiting for customs check(VM)`?

A alfândega europeia assinalou a encomenda para inspeção do valor declarado (`VM`). O despachante ou o vendedor trata da documentação; contacta o vendedor se o estado persistir.

### Como contactar o apoio ao cliente da Sengi Express?

Contacta primeiro a tua loja online ou marketplace para reembolsos ou documentos aduaneiros. A Sengi Express indica `service@sengiexpress.com` e um formulário online em [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: portal de rastreio](https://portal.sengiexpress.com/home) – rastreio público de guias e dados de *Guangzhou Sengi Logistics Co., Ltd.*
- [Sengi Express: sobre nós](https://portal.sengiexpress.com/about/) – participação maioritária da Poste Italiane, serviços transfronteiriços e `service@sengiexpress.com`
- [Sengi Express: produtos e serviços](https://portal.sengiexpress.com/product/) – plataformas aduaneiras MXP/AMS/LGG/CDG, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse e transporte rodoviário europeu Sennder
- [Sengi Express: contacto](https://portal.sengiexpress.com/contact/) – página oficial de contacto para destinatários e clientes (URL de ajuda ligado pelo Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – regras de deteção `...C...F` e `...MT`, parser `api-v2/tracking/web`, mapa de estados, tratamento de fuso UTC e entrega Poste Italiane `PI` / `relabelTrackingNumber`
:::
