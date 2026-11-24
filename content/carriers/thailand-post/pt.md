---
title: Rastreio Thailand Post: códigos TH S10, EMS e 1545
description: Segue uma encomenda Thailand Post (ไปรษณีย์ไทย): códigos TH S10 de 13 caracteres, estados domésticos e EMS, cobrança COD, alfândega e linha 1545.
slug: rastreio-thailand-post
published: 2026-11-24
updated: 2026-11-24
---

A **Thailand Post** (*Thailand Post Co., Ltd.*, em tailandês *บริษัท ไปรษณีย์ไทย จำกัด*) é o operador postal nacional da Tailândia ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Assegura correio doméstico, encomendas registadas, EMS, Same Day, cargas pesadas LogisPost e pagamento na entrega (COD) na Tailândia, bem como serviços internacionais [EMS](carrier:ems) World, Registered, ePacket, Courier Post (até 200 kg) e Courier Post Plus para todo o mundo. O rastreio público está em [track.thailandpost.co.th](https://track.thailandpost.co.th/) e os avisos internacionais em [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Podes consultar aí um envio postal tailandês ou colar o teu código `TH` de 13 caracteres na caixa acima.

## Números de rastreio Thailand Post (formato `TH` S10 de 13 caracteres)

Os envios domésticos e internacionais seguidos da Thailand Post utilizam códigos UPU S10 de 13 caracteres: duas letras maiúsculas de serviço, 8 dígitos sequenciais, 1 dígito de controlo S10 e o código de país `TH`:

| Formato (inventado) | O que é |
| --- | --- |
| `EE482917367TH` | Objeto expresso [EMS](carrier:ems) Thailand Post de 13 caracteres (prefixo `EE` + 9 dígitos com dígito de controlo S10 válido + `TH`). |
| `RR739184627TH` | Correio registado ou pequeno pacote Thailand Post de 13 caracteres (prefixo `RR` + 9 dígitos com dígito de controlo S10 válido + `TH`). |

Os prefixos nacionais Express/EMS, Registado (`R...TH`), ePacket (`L...TH`) e de encomendas domésticas (`E`, `R`, `C`, `W`, etc. terminados em `TH`) seguem todos a mesma estrutura S10 de 13 caracteres com dígito de controlo. Cartas simples sem código de barras de 13 caracteres não podem ser seguidas online. Consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da Thailand Post

O rastreio da Thailand Post combina códigos numéricos `statusID` alinhados com a UPU e descrições em inglês (junto a nomes de estações tailandesas), abrangendo tanto registos na Tailândia como atualizações EDI internacionais retransmitidas pelos correios de destino:

| Código e texto Thailand Post | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `3` `Posting/Collection` | Aceite num balcão Thailand Post ou recolhida ao remetente. | «Aceite pela transportadora» |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Processada numa estação de permuta internacional na exportação ou importação. | «Em trânsito» |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | Em movimento entre centros de triagem postal até à chegada à estação postal local de distribuição. | «Em trânsito» |
| `31` `Item out for physical delivery` | Em distribuição hoje com o carteiro da Thailand Post. | «Em distribuição» |
| `57` `Contact recipient` | O carteiro tentou telefonar ao destinatário (ação sem movimento; mantém a etapa atual). | Mantém a etapa anterior |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Colocada num cacifo ou ponto de recolha Thailand Post pronta para levantamento. | «Pronta para levantamento» |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (grupo 4) | Tentativa de entrega falhada (por exemplo destinatário ausente ou taxas aduaneiras/COD por pagar). | «Tentativa de entrega» |
| `28` `Return to the origin post office` | Incidente assinalado; objeto a regressar à estação postal de origem. | «Problema reportado» |
| `35/L` `The sender received the returned item` | Encomenda devolvida entregue ao remetente original. | «Devolvida ao remetente» |
| `35/S` `Final delivery` | Entregue ao destinatário (pode ser seguido por `36` `Successfully transfer money to seller` em envios COD, sem alterar a hora de entrega). | «Entregue» |

## Cobrança na entrega (COD), passagens internacionais e alfândega

- **Exportações internacionais (`...TH` para o estrangeiro):** após `15` (`Departure From Outward OE`), o operador postal nacional do teu país — como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post ou [Australia Post](carrier:australia-post) — trata do desalfandegamento e da entrega local, enviando registos EDI de volta à Thailand Post.
- **Direitos aduaneiros e IVA de importação:** encomendas exportadas da Tailândia ou importadas para a Tailândia estão sujeitas às regras aduaneiras, IVA e encargos do país de destino (incluindo taxas da UE sobre encomendas de fora da UE). Se o rastreio mostrar `34` (`Item held, addressee notified due to Payment of charges`), verifica a caixa de correio ou a estação postal local para um aviso aduaneiro ou de pagamento. Consulta o guia de [encomendas retidas na alfândega](guide:customs).
- **Pagamento na entrega (COD) na Tailândia:** em envios COD domésticos, a Thailand Post regista `35/S` (`Final delivery`) quando o destinatário recebe e paga a encomenda, seguindo-se `36` (`Successfully transfer money to seller`) quando o valor é transferido para o vendedor.

## Encomenda Thailand Post atrasada, retida ou desaparecida

- **Pausa silenciosa após sair da Tailândia:** envios aéreos internacionais (especialmente registados `R...TH` e encomendas económicas) ficam muitas vezes vários dias ou semanas sem novidades entre `Departure From Outward OE` e `Arrival at inward office of exchange`. Consultar o mesmo código `TH` de 13 caracteres no site dos correios do país de destino mostra frequentemente detalhes adicionais de alfândega ou distribuição local. Vê [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Envios perdidos, danificados ou devolvidos:** contacta primeiro o remetente ou o marketplace e guarda o código de 13 caracteres e o talão. Vê também [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente Thailand Post e Contact Center 1545

Canais oficiais publicados em [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) e [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Tailândia):** **`1545`** (informações gerais, pesquisa de envios e reclamações).
- **Portal oficial de contacto e pedidos:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Rastreio online e avisos internacionais:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) e [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Desconfia de SMS ou mensagens LINE falsas que afirmem que uma encomenda Thailand Post precisa de um pequeno pagamento numa ligação desconhecida. Confirma qualquer rastreio ou encargo aduaneiro diretamente em [track.thailandpost.co.th](https://track.thailandpost.co.th/) ou através do THP Contact Center `1545`. Vê [mensagens SMS fraudulentas de entrega](guide:delivery-scam-texts).

## Acompanhar uma encomenda Thailand Post com o Peek

O [Peek](/) deteta a Thailand Post automaticamente como único candidato para qualquer código S10 `TH` válido de 13 caracteres (`EE482917367TH` ou `RR739184627TH`, inventados) e reconhece diretamente ligações coladas `track.thailandpost.co.th/?trackNumber=`. Se quiseres mudar de transportadora manualmente (por exemplo para comparar com os registos dos correios de destino quando chega ao estrangeiro), toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda.

O Peek consulta o rastreio da Thailand Post — e o segundo servidor quando o primeiro falha — apresentando a etapa («Aceite pela transportadora», «Em trânsito», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), o texto em inglês do registo, a estação postal e a hora de entrega. Os códigos de estado determinam a etapa e nenhuma aplicação Peek os mostra; o nome do destinatário surge em «Destinatário» na tua própria página da encomenda, nunca numa ligação partilhada, e nenhuma aplicação mostra o nome de quem recebeu a encomenda. Os registos próprios da Thailand Post trazem o desfasamento de Banguecoque. Um registo EDI de um correio estrangeiro contém a hora local dessa estação, que a Thailand Post guarda como se fosse hora de Banguecoque: o Peek data-o por isso no fuso dessa estação (hora de Tóquio para `TOKYO INTERNATIONAL`), um registo sem local no fuso do registo vizinho, mostrando tudo no relógio do teu dispositivo. O Peek deixa de fora nome do motorista, telefones, assinatura e nota de contacto mascarada; a Thailand Post não fornece dia estimado de entrega.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Thailand Post apresenta `31` (`Item out for physical delivery`), de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da Thailand Post responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Thailand Post não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Thailand Post.», com a ligação «Pede ajuda a Thailand Post» para a página de contactos da Thailand Post. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Thailand Post.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek monitoriza apenas as atualizações de rastreio e não pode desalfandegar, pagar cobranças COD nem contactar a Thailand Post por ti.

## Perguntas sobre o rastreio Thailand Post

### Como é um número de rastreio da Thailand Post?

Os envios seguidos da Thailand Post usam códigos UPU S10 de 13 caracteres iniciados por duas letras de serviço, seguidas de 9 dígitos (com dígito de controlo no final) e `TH` — por exemplo `EE482917367TH` para EMS ou `RR739184627TH` para correio registado (inventados).

### O que significa o código `36` (`Successfully transfer money to seller`)?

Em encomendas domésticas tailandesas com pagamento na entrega (COD), o código `36` confirma que a Thailand Post transferiu para o vendedor o montante cobrado após a entrega final (`35/S`). É um registo financeiro e não um movimento da encomenda.

### Porque parou de atualizar a minha encomenda Thailand Post após `Departure From Outward OE`?

A encomenda saiu da estação internacional de saída da Tailândia e está em trânsito aéreo internacional ou a aguardar receção de importação pelo operador postal do país de destino.

### Como contactar o apoio ao cliente da Thailand Post?

Liga para o THP Contact Center pelo **`1545`** na Tailândia ou usa a página oficial de contacto em [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, serviços domésticos/internacionais EMS, Registered, Courier Post, LogisPost e COD
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – apoio oficial ao cliente, portal de reclamações e linha `1545` (página de ajuda ligada pelo Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – consulta pública oficial de rastreio
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – estado de rastreio e avisos internacionais
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – validação do dígito S10 `TH`, redundância entre dois servidores, mapa `statusID` UPU (`3`–`36`, `57`), relógio de Banguecoque vs. EDI estrangeiro e leitura do destinatário
:::
