---
title: Rastreamento Swiss Post Cargo: status, entrega e contato
description: Rastreie uma carga Swiss Post Cargo (antiga PostLogistics): códigos SSCC e PL, significado de Data entry POST e Loaded for Delivery, entrega e contato.
slug: swiss-post-cargo-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Swiss Post Cargo é a marca de cargas dos Correios Suíços (Swiss Post): paletes e outras mercadorias transportadas por caminhão, de volumes avulsos a cargas fechadas, e não encomendas comuns. Rastreie uma remessa na página Track & Trace (tt.swisspost-cargo.com) com o código de barras ou a referência enviada pelo remetente, ou cole-o na caixa acima. Se você tiver um código de encomenda comum da Swiss Post, consulte a [página da Swiss Post](carrier:swiss-post).

## Código de rastreamento Swiss Post Cargo

O rastreador da Swiss Post Cargo aceita o código de barras de um volume ou uma referência do remetente; a referência traz todos os códigos de barras daquela remessa. Formatos que o Peek vincula à Swiss Post Cargo (dígitos fictícios):

| Código | O que é |
| --- | --- |
| `00482917365428193648` | 20 dígitos começando com `00`: código de barras de palete ou volume (SSCC), com dígito verificador no final. |
| `PL-48291736` | `PL-` e 8 dígitos: referência de cliente. |

Procure nos e-mails da loja e depois pergunte à loja. Veja mais locais em [como encontrar seu código de rastreamento](guide:find-tracking-number), em inglês. Se o rastreador não retornar nada, confira os dígitos e fale com o remetente.

Dois tipos de código que não são rastreados como Swiss Post Cargo:

- **Encomendas comuns da Swiss Post:** 18 dígitos começando com `99` (impresso `99.00.482917.36542819`) ou duas letras, nove dígitos e `CH` (`RR482917367CH`).
- **Referências da PostLogistics** como `48291736-542`: a PostLogistics agora opera como Swiss Post Cargo, e seu antigo rastreador (tracking.postlogistics.ch) não abre mais. O Peek continua rastreando essas referências como PostLogistics.

## Status do Track & Trace da Swiss Post Cargo

A Swiss Post Cargo exibe leituras em inglês, alemão, francês ou italiano. Em inglês, uma remessa entregue costuma seguir esta sequência:

| A Swiss Post Cargo mostra | O que significa para você |
| --- | --- |
| `Data entry POST` | Os dados do remetente chegaram; a carga ainda não está com a Swiss Post Cargo. |
| `Received warehouse POST` | A carga chegou a um hub ou depósito da Swiss Post Cargo, identificado ao lado. |
| `Loaded for Delivery` | Carregada no caminhão de entrega: saiu para entrega. |
| `IMAGE` | Foto da entrega, listada logo antes da leitura de entrega. |
| `DELIVERED SCANNED` | Entregue, na cidade exibida. |
| `SIGNATURE` | Assinatura colhida na entrega, no mesmo horário da leitura de entrega. |

`Data entry POST` representa apenas envio de dados ("POST" faz parte do texto e nada se movimentou ainda). `IMAGE` e `SIGNATURE` são comprovantes ligados à entrega, não novas movimentações.

## Entrega da Swiss Post Cargo: prazos, local e ausências

**Quando chega.** O folheto de cargas da Swiss Post informa que, em regra, a entrega ocorre no dia útil seguinte. Na Suíça e em Liechtenstein, locais acessíveis a caminhões pesados recebem até 17h (horário suíço) do dia útil seguinte; vales alpinos e áreas montanhosas de difícil acesso (Valais, Ticino e Grisões) recebem até 17h do segundo dia útil.

**Onde é entregue.** No serviço padrão, a carga vai até a primeira porta no térreo, doca ou calçada, em horário comercial — não sobe andares. O motorista conta com acesso para paleteira, e a descarga é responsabilidade do destinatário (se o motorista descarregar, age em seu nome). Ele pede sua assinatura confirmando o recebimento correto.

**Serviços extras contratados pelo remetente.** Aviso prévio por telefone, e-mail ou SMS, entrega agendada, entrega em andar e segunda tentativa são serviços adicionais, disponíveis somente se o remetente os contratou.

**Entrega não realizada e retirada.** O folheto de cargas não cita pontos de retirada nem armários, e a segunda tentativa é um serviço extra. Tampouco informa quanto tempo a carga fica no depósito ou como reagendar: fale com a loja que contratou o transporte.

## Carga Swiss Post Cargo atrasada, extraviada ou danificada

Quem contrata a Swiss Post Cargo é a loja (serviço empresarial); por isso atrasos, danos ou itens faltantes devem ser tratados primeiro com a loja. Comunique qualquer dano imediatamente à loja com fotos.

**Dias sem atualização.** Se ficar apenas em `Data entry POST`, a carga pode continuar com o remetente: pergunte à loja quando será entregue à transportadora. Veja [rastreamento que não atualiza](guide:tracking-not-updating), em inglês.

**Entregue mas não recebida.** Procure as linhas `SIGNATURE` e `IMAGE` no rastreador e contate a loja. Veja [entregue mas não recebida](guide:delivered-not-received), em inglês.

**Mensagens suspeitas.** A página antifraude da Swiss Post alerta sobre golpes em seu nome por SMS, e-mail, WhatsApp, sites, ligações e redes sociais, geralmente mencionando encomendas, taxas, rastreamento ou dados pessoais.

## Contato da Swiss Post Cargo

Como a Swiss Post Cargo presta serviço para a loja, comece falando com ela. O folheto de cargas da Swiss Post (de abril de 2022) indica um único contato, voltado a empresas e sem telefone: Customer Service Transport CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Para cartas e encomendas comuns da Swiss Post (não Cargo), o telefone é +41 848 888 888 (CHF 0,08 por minuto de telefone fixo suíço), de segunda a sexta das 7h30 às 18h e sábado das 8h às 12h (horário suíço).

## Como rastrear Swiss Post Cargo no Peek

Um código de barras de 20 dígitos iniciado em `00` pertence a quem enviou e não a uma transportadora única; por isso o [Peek](/) mostra "Consultando serviços de rastreamento…" e seleciona Swiss Post Cargo apenas se ela reconhecer o código. Referências no formato `PL-48291736` são consultadas na Swiss Post Cargo e na TNT Italy, que usa formato parecido. Para qualquer outra referência de 6 a 40 letras e dígitos, selecione Swiss Post Cargo em "Trocar", ou cole um link Track & Trace de tt.swisspost-cargo.com ou apv.swisspost-cargo.com (`/public/trackandtrace/` seguido do número). Se um link desses trouxer número de encomenda comum da Swiss Post, o Peek o rastreia como Swiss Post.

O Peek mostra a etapa, o histórico com a cidade de cada leitura no mapa e o horário da entrega, interpretado em Zurique e convertido para o fuso do seu dispositivo. Os textos permanecem em inglês, e `Loaded for Delivery` conta como "Saiu para entrega". As linhas de assinatura e imagem se fundem à leitura de entrega (sem exibir a foto). O Peek omite seu nome e endereço e não tem previsão de entrega, peso nem ponto de retirada.

Se várias remessas compartilharem a mesma referência, ela só funciona quando apenas uma tiver leituras nos últimos 60 dias; caso contrário a Swiss Post Cargo não a encontra (o Peek mostra "Aguardando Swiss Post Cargo" e após dois dias sugere conferir o número) — nesses casos, use o código de barras. Com a remessa aberta ou no dia da entrega com notificações ligadas, o Peek consulta a cada 10 minutos no máximo, ou a cada 2 minutos a partir das 6h (horário suíço) no dia em que sai para entrega, enquanto o rastreador da própria Swiss Post Cargo responder, notificando cada leitura, etapas importantes ou apenas o dia da entrega. Fora disso, e após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora.

## Perguntas frequentes sobre o rastreamento Swiss Post Cargo

### O que é a Swiss Post Cargo?

É o nome adotado pela logística de mercadorias da Swiss Post desde 1º de março de 2025, atendendo empresas na Suíça e no exterior, incluindo cargas. O grupo conta na Suíça com a Swiss Post Cargo CH AG, subsidiária integral da Swiss Post, tendo a PostLogistics sido uma das primeiras a adotar o nome.

### A PostLogistics é a mesma coisa que a Swiss Post Cargo?

Sim, a PostLogistics passou a operar como Swiss Post Cargo. Seu antigo rastreador (tracking.postlogistics.ch) saiu do ar e o atual é tt.swisspost-cargo.com; o Peek ainda acompanha referências antigas da PostLogistics como `48291736-542`.

### Existe um e-mail da Swiss Post Cargo?

O folheto de cargas da Swiss Post (abril de 2022) traz sales.ls4@post.ch para clientes corporativos. Como destinatário, escreva primeiro à loja que contratou o transporte.

### Por que recebi um SMS da Swiss Post Cargo?

Geralmente porque o remetente contratou o aviso de entrega (o folheto lista aviso por telefone, e-mail ou SMS como serviço extra). Como também existem SMS falsos em nome da Swiss Post, confira o código você mesmo no rastreador.

### Posso acompanhar a Swiss Post Cargo ao vivo no mapa?

Não em tempo real. O rastreador mostra a cidade de cada leitura registrada, e o folheto de cargas menciona atualizações regulares sobre o andamento da remessa.

:::sources
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – single items to whole truckloads, orders through Swiss Post Cargo Web by business customers, next working day, mountain areas, ground floor, unloading, signature, sender extras including a second delivery, regular location updates, Dintikon contact and email
- [Swiss Post: Logistics market](https://site.post.ch/en/about-us/company/strategy-and-value/business-activities/logistics) – Swiss Post Cargo is Swiss Post's goods logistics, solutions for companies in Switzerland and abroad
- [Swiss Post: Group companies](https://site.post.ch/en/about-us/company/governance/organisation/subsidiaries) – Swiss Post Cargo CH AG, 100% Swiss Post
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – letters and parcels line, price, hours
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – fake messages in Swiss Post's name
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – Swiss Post Cargo name from 1 March 2025, PostLogistics among the first group companies to take it
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads: barcodes and references, number shapes, the English scan wordings, Track & Trace addresses, PostLogistics now trading as Swiss Post Cargo
:::
