---
title: Rastreio Swiss Post Cargo: estados, entrega e contactos
description: Rastreia um envio Swiss Post Cargo: que número funciona, o que significam Data entry POST e Loaded for Delivery, entrega de carga e contactos.
slug: rastreio-swiss-post-cargo
published: 2026-11-22
updated: 2026-11-22
---

A Swiss Post Cargo é a marca de carga da Swiss Post: paletes e outras mercadorias transportadas por camião, de volumes individuais a cargas completas, e não encomendas postais comuns. Podes seguir um envio na sua página Track & Trace (tt.swisspost-cargo.com) com o código de barras ou a referência dada pelo remetente, ou colando-o na caixa acima. Se o que tens é um número de encomenda normal da Swiss Post, usa a [página da Swiss Post](carrier:swiss-post).

## Número de seguimento da Swiss Post Cargo

O rastreio da Swiss Post Cargo aceita um código de barras para um volume ou uma referência do remetente. Uma referência apresenta todos os códigos de barras dessa expedição. Os formatos que o Peek associa à Swiss Post Cargo (dígitos inventados):

| Número | O que é |
| --- | --- |
| `00482917365428193648` | 20 dígitos começados por `00`: código de barras de palete ou volume (SSCC). O último dígito é de controlo. |
| `PL-48291736` | `PL-` e 8 dígitos: referência de cliente. |

Verifica os emails da loja e, se faltar, pergunta à loja. Em [como encontrar o número de seguimento](guide:find-tracking-number) tens outros locais onde procurar. Se o rastreio não encontrar nada, confirma os dígitos e fala com o remetente.

Dois tipos de números que não são seguidos como Swiss Post Cargo:

- **Encomendas Swiss Post:** 18 dígitos começados por `99` (impresso `99.00.482917.36542819`) ou duas letras, nove dígitos e `CH` (`RR482917367CH`).
- **Referências PostLogistics** como `48291736-542`: a PostLogistics opera agora como Swiss Post Cargo e o seu antigo rastreio, tracking.postlogistics.ch, já não abre. O Peek segue estas referências como PostLogistics.

## Estados de rastreio da Swiss Post Cargo

A Swiss Post Cargo apresenta os registos em inglês, alemão, francês ou italiano. Em inglês, um envio entregue surge assim:

| A Swiss Post Cargo indica | O que significa para ti |
| --- | --- |
| `Data entry POST` | Os dados do remetente chegaram. A mercadoria ainda não está com a Swiss Post Cargo. |
| `Received warehouse POST` | A mercadoria chegou a um centro ou depósito da Swiss Post Cargo, indicado junto ao registo. |
| `Loaded for Delivery` | Carregada no camião de entrega: em distribuição. |
| `IMAGE` | Fotografia de entrega, listada imediatamente antes do registo de entrega. |
| `DELIVERED SCANNED` | Entregue, na localidade apresentada. |
| `SIGNATURE` | Assinatura recolhida na entrega, à mesma hora do registo de entrega. |

`Data entry POST` corresponde apenas a dados: «POST» faz parte do texto e ainda nada se mexeu. `IMAGE` e `SIGNATURE` são comprovativos associados à entrega, não novos movimentos.

## Entrega da Swiss Post Cargo: quando, onde e entregas falhadas

**Quando.** A brochura de carga da Swiss Post indica que a carga é geralmente entregue no dia útil seguinte. Na Suíça e no Liechtenstein, os locais acessíveis a pesados recebem-na até às 17h (hora suíça) do dia útil seguinte. Vales alpinos e zonas de montanha de difícil acesso (Valais, Ticino e Grisões) recebem-na até às 17h do segundo dia útil.

**Onde.** Por norma, a entrega é feita na primeira porta do rés do chão, cais ou passeio, em horário laboral, e não em andares superiores. O motorista espera acesso com porta-paletes e a descarga compete ao destinatário: se o motorista descarregar, fá-lo em teu nome. O motorista pede assinatura para confirmar que a entrega está conforme.

**Extras contratados pelo remetente.** Aviso por telefone, email ou SMS, entrega agendada, entrega num piso e segunda entrega são serviços opcionais, disponíveis apenas se o remetente os tiver reservado.

**Entrega falhada e levantamento.** A brochura de carga da Swiss Post não refere pontos de recolha nem cacifos, e uma segunda entrega é um dos extras acima. Não especifica quanto tempo a carga aguarda em depósito nem como reagendar. Fala com a loja que reservou o transporte.

## Envio Swiss Post Cargo atrasado, perdido ou danificado

O cliente da Swiss Post Cargo é a loja (empresas contratam os seus serviços), pelo que atrasos, danos ou volumes em falta devem ser comunicados primeiro à loja. Participa danos à loja de imediato, com fotografias.

**Dias sem atualizações.** Apenas `Data entry POST` significa que a mercadoria pode ainda estar com o remetente: pergunta à loja quando a entrega à transportadora. [Rastreio que não atualiza](guide:tracking-not-updating) explica as razões habituais.

**Entregue mas não está lá.** Procura as linhas `SIGNATURE` e `IMAGE` no rastreio e contacta depois a loja. [Entregue mas não recebida](guide:delivered-not-received) orienta os passos seguintes.

**Mensagens suspeitas.** A página de segurança da Swiss Post alerta que tentativas de fraude em seu nome chegam por SMS, email, WhatsApp, sites ou apps falsos, chamadas telefónicas e redes sociais, com ligações, anexos ou pedidos de pagamento ou dados.

## Contacto da Swiss Post Cargo

A Swiss Post Cargo trabalha para a loja, portanto começa por aí. A brochura de carga da Swiss Post apresenta um contacto para clientes empresariais sem telefone: Customer Service Transport CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Para cartas e encomendas normais da Swiss Post (não Cargo), a linha da Swiss Post é +41 848 888 888 (0,08 CHF por minuto de rede fixa suíça), segunda a sexta das 07h30 às 18h00 e sábados das 08h00 às 12h00 (hora suíça).

## Seguir um envio Swiss Post Cargo com o Peek

Um código de barras de 20 dígitos começado por `00` pertence a quem expediu e não a uma única transportadora, pelo que o [Peek](/) mostra «A verificar serviços de seguimento…» e indica Swiss Post Cargo apenas se a Swiss Post Cargo reconhecer o número (se várias transportadoras o conhecerem, o Peek pergunta qual escolher). Uma referência `PL-48291736` é perguntada à Swiss Post Cargo e à TNT Italy, cujos códigos podem ter o mesmo aspeto. Para qualquer outra referência de 6 a 40 letras e dígitos, muda de transportadora e escolhe Swiss Post Cargo, ou cola uma ligação Track & Trace de tt.swisspost-cargo.com ou apv.swisspost-cargo.com. Um número de encomenda Swiss Post numa ligação dessas é seguido como Swiss Post.

O Peek mostra o estado e o histórico com a localidade de cada registo num mapa, bem como a hora de entrega. As horas são lidas na hora de Zurique e convertidas para o teu fuso horário (o histórico termina com «Horas no teu fuso horário:» seguido do nome). Os textos mantêm-se em inglês; `Loaded for Delivery` conta como em distribuição. As linhas de assinatura e foto integram-se no registo de entrega, sem a imagem. O Peek omite o teu nome e morada e não tem data prevista, peso nem ponto de recolha.

Uma referência partilhada por vários envios só é lida enquanto um deles tiver registos dos últimos 60 dias; caso contrário surge como não encontrado, pelo que deves usar o código de barras. Enquanto mantiveres o envio aberto no ecrã — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a Swiss Post Cargo até de 10 em 10 minutos, ou de 2 em 2 minutos a partir das 6h suíças no dia em que a Swiss Post Cargo o marca em distribuição, enquanto o seu rastreio responder. Fora disso, e após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora. Só através de serviços de seguimento verifica de hora em hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega se tiveres o envio aberto no ecrã ou as notificações ligadas. Escolhe «Todos os registos», «Passos importantes» ou «Só a entrega». Após quatro dias sem registos em trânsito, o Peek mostra na tua lista o dia do último registo («Sem atualizações desde …») e no ecrã do envio «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Swiss Post Cargo.», com a ligação «Pede ajuda a Swiss Post Cargo» para a página de ajuda apenas nesse aviso.

## Perguntas sobre o rastreio Swiss Post Cargo

### O que é a Swiss Post Cargo?

É o nome sob o qual opera a logística de mercadorias da Swiss Post desde 1 de março de 2025: soluções logísticas para empresas, na Suíça e no estrangeiro, incluindo carga. Entre as empresas do grupo na Suíça está a Swiss Post Cargo CH AG, detida a 100% pela Swiss Post. A PostLogistics foi uma das primeiras empresas do grupo a adotar o nome.

### A PostLogistics é o mesmo que a Swiss Post Cargo?

Sim, a PostLogistics opera agora como Swiss Post Cargo. O antigo rastreio, tracking.postlogistics.ch, já não abre; o da Swiss Post Cargo fica em tt.swisspost-cargo.com. O Peek continua a acompanhar referências PostLogistics como `48291736-542`.

### Existe um email da Swiss Post Cargo?

A brochura de carga da Swiss Post indica sales.ls4@post.ch para clientes empresariais. Como destinatário, escreve primeiro à loja que reservou o transporte.

### Porque é que a Swiss Post Cargo me envia um SMS?

Frequentemente porque o remetente reservou um aviso de entrega: a brochura de carga lista aviso por telefone, email ou SMS como serviço extra. Também existem SMS falsos em nome da Swiss Post, pelo que deves confirmar sempre o número diretamente no rastreio.

### Posso acompanhar a Swiss Post Cargo em tempo real?

Não num mapa ao vivo. O rastreio apresenta cada registo com a respetiva localidade, e a brochura de carga refere atualizações regulares sobre onde se encontra a expedição.

:::sources
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – de volumes avulsos a camiões completos, pedidos empresariais, dia útil seguinte, zonas de montanha, rés do chão, descarga, assinatura, extras do remetente incluindo segunda entrega, atualizações regulares, contacto de Dintikon e email
- [Swiss Post: Logistics market](https://site.post.ch/en/about-us/company/strategy-and-value/business-activities/logistics) – a Swiss Post Cargo é a logística de mercadorias da Swiss Post para empresas na Suíça e no estrangeiro
- [Swiss Post: Group companies](https://site.post.ch/en/about-us/company/governance/organisation/subsidiaries) – Swiss Post Cargo CH AG, 100% Swiss Post
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – linha de cartas e encomendas, preço, horário
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – mensagens falsas em nome da Swiss Post
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – marca Swiss Post Cargo desde 1 de março de 2025, PostLogistics entre as primeiras a adotá-la
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê: códigos de barras e referências, formatos, textos em inglês, endereços Track & Trace, PostLogistics como Swiss Post Cargo
:::
