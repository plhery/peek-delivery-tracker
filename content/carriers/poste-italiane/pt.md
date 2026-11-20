---
title: Poste Italiane rastreio: códigos 1UW e 3UW e estados
description: Seguir uma encomenda Poste Italiane em Portugal: formatos do código (1UW, 3UW, 8UW, 5P), significado dos estados em italiano, prazos e contactos.
slug: poste-italiane-rastreio
published: 2026-11-17
updated: 2026-11-20
---

A Poste Italiane é o operador postal público de Itália e entrega também, através das redes Crono e SDA, compras em lojas online italianas, Vinted ou eBay. Segue o envio em «Cerca Spedizioni» em poste.it (antigo Dove Quando), na app Poste Italiane ou colando o código acima. Nos envios de Itália para Portugal, o poste.it mostra o percurso até ao centro de permuta internacional; nos códigos postais terminados em `IT`, a distribuição passa depois para o correio português.

## Código de rastreio Poste Italiane: onde está e como é

A Poste chama-lhe *codice spedizione*. No recibo ou carta de porte (*lettera di vettura*) surge por baixo do código de barras: escreve-o sem espaços nem traços. Nas compras online vem no email da loja (ou na mensagem Scegli Tu da Poste); em Itália, o aviso de chegada (*avviso di giacenza*) traz o número do registo, a estação de correios e eventual acesso ao Ritiro Digitale. O «Codice Prenotazione Ritiro» (`CP` mais algarismos) serve apenas ao remetente para recolha ao domicílio, não para rastreio. Sem código? Vê [onde encontrar o número de rastreio](guide:find-tracking-number).

Todos os exemplos abaixo são inventados:

| Formato (inventado) | O que identifica |
| --- | --- |
| `RA00000000000` | `RA` e 11 algarismos: envio e correio registado. |
| `3UW0000000000` | `1UW`, `3UW` ou `8UW` e 10 letras/algarismos: Poste Delivery Business. |
| `5PA0000000000` | `5P` e 11 letras/algarismos: encomenda Crono. |
| `JF00000000000` | `EF`, `EG`, `GF`, `JF`, `JG` ou `LF` e 11 algarismos: Poste Delivery Web e MyPoste. |
| `JJITA0000000000000` | `JJITA` e 13 letras/algarismos: código italiano em formato ISO. |
| `2IMA0000000000` | `2IMA` e 10 algarismos (ou `2IVC`, 4 algarismos, letra/algarismo, 5 algarismos): etiqueta Posta1. |
| `000000A000000` | Algarismos e letras (6 algarismos, letra, 6 algarismos, ou início `1C`, `3C`, `9C`, `F`, `D`): estilo SDA e Crono. |
| `050000201242` | 12 algarismos com controlo válido: o exemplo do poste.it para registos, valores declarados, notificações judiciais e encomendas. |
| `RR000000005IT` | Duas letras, nove algarismos e `IT` (`RR…IT`, `CW…IT`, `ZC…IT`): envio internacional expedido de Itália. |

Alguns formatos são partilhados: `5P` seguido de onze algarismos válidos na Colissimo pode pertencer à [La Poste](carrier:la-poste); 12 algarismos existem na BRT ou na Sending, e `000A00000000B`, `9C0000I000000` ou `9C0000J000000` na [AliExpress / Cainiao](carrier:cainiao). Vê [formatos de números de rastreio](guide:tracking-number-formats).

**O que tem rastreio:** registos, valores declarados, notificações judiciais e encomendas (a partir do dia útil seguinte no Poste Delivery Express, antigo Paccocelere, e Standard, antigo *pacco ordinario*). O Poste Delivery International Express tem rastreio até ao destino (exceto Tunísia, Gronelândia, Ilhas Faroé e certos códigos postais suíços); o International Standard e a Postapriority Internazionale param no centro de permuta italiano em poste.it. A Posta Ordinaria (antiga Posta4) e a Postamail Internazionale não têm rastreio; a Posta1 terminou a 1 de maio de 2026. Histórico visível por 2 meses em encomendas e 24 meses em correio.

## Estados Poste Italiane em italiano

Cada leitura mostra uma frase italiana da *Legenda degli stati di spedizione*, apresentada tal e qual pelo Peek:

| O que diz a Poste Italiane | Significado |
| --- | --- |
| `Presa in carico` | Envio aceite e encaminhado para o destino. |
| `In attesa di sdoganamento` | Em controlo aduaneiro; podem pedir-te documentos. |
| `completata la fase di verifica per lo svincolo…` | Verificação aduaneira concluída, envio desalfandegado. |
| `In transito` | Em trânsito entre centros operacionais da Poste Italiane. |
| `In transito presso il Centro di lavorazione Internazionale` | Saiu de Itália rumo ao operador estrangeiro (último registo na Postapriority Internazionale e no International Standard). |
| `presso il Centro di lavorazione Internazionale` | O mesmo passo num registo, em linha própria após `In transito`. |
| `In consegna` | Em distribuição. |
| `consegna non andata a buon fine…` | Entrega falhada, p. ex. por morada incorreta ou incompleta. |
| `disponibile per il ritiro dal giorno lavorativo successivo alla data indicata` | Disponível na estação de correios a partir do dia útil seguinte à data indicada. |
| `disponibile per il ritiro entro i termini indicati su SMS/email` | Pronta num Punto Poste ou cacifo (*locker*) até ao prazo do SMS/email. |
| `la spedizione è stata consegnata` | Entregue. |
| `sono in corso delle verifiche sulla spedizione. contatta assistenza` | Verificação em curso: contacta o apoio ao cliente. |
| `in restituzione al mittente…` | Em devolução ao remetente. |
| `Resa al mittente` | Não entregue (prazo expirado, morada errada ou recusa) e devolvida. |
| `Tracciatura non disponibile` | Muito recente ou expirada (mais de 2 meses em encomendas ou 24 em correio). |

`In consegna` pode continuar visível após a entrega ou enquanto aguarda levantamento, e após falha o estado volta a `In transito`. Qualquer data prevista é «meramente indicativa». Mais em [estados de rastreio](guide:tracking-statuses).

## Entrega em Portugal e levantamento em Itália

**Entre Itália e Portugal.** Um envio postal `…IT` passa para o operador português (como os [CTT](carrier:ctt)) ao sair do centro internacional italiano; no sentido inverso, envios para Itália só surgem em poste.it após entrada na rede italiana. Dentro da união aduaneira da UE não há direitos aduaneiros entre Itália e Portugal.

**Em Itália (*giacenza* e Punto Poste).** A Poste faz 2 tentativas à porta do prédio (*piano strada*): deixa aviso à primeira, repete no dia útil seguinte em Crono e Poste Delivery Business e, falhando de novo, guarda gratuitamente em *giacenza* na estação de correios; vê [encomenda não levantada](guide:uncollected-parcel):

- **Prazos e procuração:** de 3 dias úteis em locker a 10 na estação de correios (Poste Delivery Web: 10 estação, 7 Punto Poste, 3 locker; Crono: 10 em Punto Poste; registos: 30 dias, Raccomandata 1: 15 dias). Marcação em «Prenota» ou pelo 06 4526 4526; terceiros levantam na estação com autorização específica, identificação própria e cópia da tua, e em Punto Poste/locker com o PIN.
- **Scegli Tu e PostePlus:** com Scegli Tu alteras uma vez dia, morada em Itália, estação/Punto Poste, vizinho, local seguro ou férias; o PostePlus destina-se a residentes em Itália.
- **Alfândega extracomunitária em Itália:** paga-se na entrega, ao balcão ou mais barato em www.poste.it carregando em «Paga» (taxa comercial da Poste: 3 € até 22 €, 7 € até 150 €, 21 € acima; online: 1 € até 150 €, 18 € acima). Segundo a Comissão Europeia, acrescem um direito aduaneiro de 3 € por tipo de produto em encomendas até 150 € desde 1 de julho de 2026 e a Taxa de Gestão da União de 2 € por tipo de produto nas compras online, seja qual for o valor, desde 1 de novembro de 2026. A Poste alerta também para abrandamentos significativos (*significativi rallentamenti*) técnicos no desalfandegamento extracomunitário, sem previsão de regularização; vê [encomenda na alfândega](guide:customs).

## Encomenda parada, perdida ou danificada

Em compras online começa pela loja (na UE deve entregar em 30 dias e responde até à entrega); vê [rastreio sem atualizações](guide:tracking-not-updating), [encomenda perdida](guide:lost-parcel) e [entregue mas não recebida](guide:delivered-not-received). Na Poste podem reclamar remetente ou destinatário, mas a indemnização cabe ao remetente salvo renúncia formal (em envios vindos do estrangeiro, através do correio de origem). Assinala danos visíveis na entrega (*consegna cautelare* ou recusa); vê [encomenda danificada](guide:damaged-parcel).

**Prazos:** 20 dias seguidos após recolha em Crono/Business; do 6.º dia útil a 3 meses no Standard. Canais: [Reclami](https://www.poste.it/assistenza/reclami), PEC `reclamiretail@postecert.it`, registo para Casella Postale 160, 00144 Roma ou estação de correios (resposta em 45 dias seguidos, depois conciliação e AGCOM).

## Contactos e telefone da Poste Italiane

Verificado em poste.it a 16 de novembro de 2026:

- **De Portugal, estrangeiro ou telemóvel:** `+39 06 4526 3160`, seg.–sáb. exceto feriados italianos, 8h00–20h00 (hora italiana), ao custo do teu operador.
- **Rede fixa italiana:** `803 160` (gratuito, mesmo horário). Informações apenas ao titular registado.
- **Escrito e chat:** `assistenza.spedizioni@posteitaliane.it`, [Scrivici](https://www.poste.it/assistenza/scrivici), [Contact us](https://www.poste.it/assistenza/scrivici-en) e *Assistente Digitale* 24/7.

> SMS falsos podem surgir por *spoofing* na conversa oficial da Poste Italiane. Não abras links: escreve `www.poste.it`. A Poste nunca pede cartão, OTP nem senhas; encaminha suspeitas para `antiphishing@posteitaliane.it` e vê [SMS falsos de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda Poste Italiane com o Peek

O [Peek](/) reconhece logo a Poste Italiane em `RA`, `1UW`, `3UW`, `8UW`, `JJITA`, `2IMA`, `2IVC`, `EF`, `EG`, `GF`, `JF`, `JG`, `LF` e `ZC…IT`, nos códigos postais válidos acabados em `IT` iniciados por `C`, `E`, `L` ou `R`, e em `5P` (exceto onze algarismos após `5P` válidos na Colissimo: aí consulta a [La Poste](carrier:la-poste) e a Poste Italiane, a Poste primeiro em Itália). Outros postais válidos em `IT` mostram «Operador postal desconhecido» (Poste e depois [Chronopost](carrier:chronopost)); `X…IT` sem controlo postal (fora Chronopost) e códigos SDA/Crono vão só à Poste (`000A00000000B`, `9C0000I000000`, `9C0000J000000` também à [AliExpress / Cainiao](carrier:cainiao)). Em 12 algarismos válidos verifica operadoras como Sending, Poste e BRT («A verificar serviços de seguimento…»), ou escolhe-a em «Alterar» / «Alterar transportadora». Aceita links `poste.it` (`#/risultati-spedizioni/`) e `sda.it` (`tracing.letteraVettura=`) sem código postal.

A partir de Portugal, o Peek verifica também números postais válidos acabados em `IT` (`RR…IT`, `CW…IT`, `ZC…IT`) nos [CTT](carrier:ctt) e muda assim que haja leituras mais recentes; no sentido inverso, etiquetas de 16 caracteres da [Vinted Go](carrier:vinted-go) ou [Cainiao](carrier:cainiao) com um código `1UW`, `3UW`, `8UW` ou `5P` passam automaticamente para a Poste Italiane.

Consulta de hora a hora e alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». Mostra até 20 leituras no mapa, dia previsto, serviço, «Encargos de importação» e horas locais («Horas no teu fuso horário: …» em peektracker.com, iPhone e Android). Remetente, peso, dimensões e estação de levantamento não são mostrados. Ao fim de dois dias sem dados: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»

## Perguntas frequentes sobre o rastreio Poste Italiane

### Código 1UW, 3UW ou 8UW: que transportadora é?

Poste Italiane: `1UW`, `3UW` e `8UW` seguidos de dez caracteres são códigos de encomenda Poste Delivery Business, rastreáveis em «Cerca Spedizioni» ou no topo desta página.

### Quanto tempo demora uma encomenda da Poste Italiane?

Indicativamente em Itália: 4 dias úteis em Crono, 1–2 em Crono Express e 1–4 após aceitação no Poste Delivery Express (sem sábados, opção paga apenas no Business Express). Internacional: 2 dias para a UE e 2–4 fora da UE no International Express; 10–15 dias UE e 10–25 fora da UE no International Standard (sem sábados nem feriados).

### Quanto tempo fica uma encomenda num Punto Poste ou estação de correios?

No Poste Delivery Web: 7 dias úteis em Punto Poste, 3 em locker e 10 na estação de correios; em Crono: 10 dias úteis em Punto Poste; registos: 30 dias na estação (15 na Raccomandata 1). Conta a data do aviso ou SMS.

### Como ligar para a Poste Italiane a partir de Portugal?

Liga `+39 06 4526 3160`, de segunda a sábado das 8h00 às 20h00 (hora italiana, exceto feriados em Itália; tarifa do teu operador); o `803 160` só funciona na rede fixa italiana.

:::sources
- [Poste Italiane: FAQ Spedizioni](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – código, aviso, procuração, registos
- [Poste Italiane: FAQ Delivery Web](https://www.poste.it/assistenza/faq-poste-delivery-web) – 2 tentativas, prazos (10/7/3), PIN, CP
- [Poste Italiane: Legenda stati](https://www.poste.it/cerca/views/risultati-spedizioni.html) – estados, 12 algarismos, 2 e 24 meses
- [TG Poste: Tracking Poste](https://tgposte.poste.it/2022/03/09/tracking-poste-come-seguire-lo-stato-delle-spedizioni-di-poste-italiane/) – Dove Quando e centros
- [Poste Italiane: FAQ Scegli Tu](https://www.poste.it/assistenza/faq-scegli-tu) – alteração única
- [Poste Italiane: FAQ PostePlus](https://www.poste.it/assistenza/faq-posteplus) – residentes em Itália
- [Poste Italiane: FAQ Punto Poste](https://www.poste.it/assistenza/faq-rete-punto-poste) – lojas e cacifos
- [Poste Italiane: Chiamaci](https://www.poste.it/assistenza/chiamaci/) – números, horário e titular
- [Poste Italiane: Scrivici](https://www.poste.it/assistenza/scrivici) – email e 06 4526 4526
- [Poste Italiane: Contact us](https://www.poste.it/assistenza/scrivici-en) – contacto em inglês
- [Poste Italiane: Reclami](https://www.poste.it/assistenza/reclami) – reclamações e PEC
- [Poste Italiane: Raccomandata](https://www.poste.it/posta-raccomandata/raccomandata) – número no aviso
- [Poste Italiane: Ritiro Digitale](https://www.poste.it/ritiro-digitale) – levantamento digital
- [Poste Italiane: Difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – spoofing SMS
- [Poste Italiane: Delivery Express](https://www.poste.it/prodotti/poste-delivery-express.html) – 1 a 4 dias úteis
- [Poste Italiane: International Express](https://www.poste.it/prodotti/poste-delivery-international-express.html) – prazos e exceções
- [Poste Italiane: International Standard](https://www.poste.it/prodotti/poste-delivery-international-standard.html) – prazos
- [Poste Italiane: CGC Express](https://www.media.poste.it/d9e41964-551f-4f58-9887-6608322cdf87/web/poste-delivery-express-cgc) – antigo Paccocelere
- [Poste Italiane: CGC Standard](https://www.media.poste.it/e12354a8-ea52-44ce-a996-e83c184000ca/web/poste-delivery-standard-cgc) – antigo pacco ordinario
- [Poste Italiane: Carta non universali](https://www.media.poste.it/37381c9f-a87e-4292-8b89-00e88fa53323/web/carta-servizi-non-universali) – Crono, prazos, sábado
- [Poste Italiane: Carta universali](https://www.media.poste.it/c919f7cf-8236-428e-82fc-f8b2be8428b8/web/carta-servizi-universali) – Standard e Posta4
- [Poste Italiane: Posta Ordinaria](https://www.poste.it/lettera/posta-ordinaria) – sem rastreio
- [Poste Italiane: Servizio Universale](https://www.poste.it/variazione-servizio-postale-universale) – fim da Posta1
- [Poste Italiane: Spedizioni da estero](https://www.poste.it/ricevere-spedizioni-da-estero) – taxas e 2 €
- [Poste Italiane: Aggiornamenti operativi](https://www.poste.it/aggiornamenti-operativi) – atrasos na alfândega
- [Comissão Europeia: Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito 3 € e taxa 2 €
- [UE: União aduaneira](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_pt) – mercado interno
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – entrega em 30 dias
- [Universal Parcel Scraper: Poste Italiane](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poste-italiane) – regras do Peek
:::
