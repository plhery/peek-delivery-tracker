---
title: Seguimento bpost: código de barras, estados e levantamento
description: Segue uma encomenda bpost da Bélgica ou para a Bélgica: formatos do código de barras, estados em inglês no Track & Trace, prazos nos cacifos bbox e contactos.
slug: bpost-seguimento
published: 2026-11-17
updated: 2026-11-20
---

A bpost é o operador postal da Bélgica e a marca do grupo bnode (antigo bpostgroup desde dezembro de 2025) para a entrega final na Bélgica e nos Países Baixos. Segue uma encomenda no [Track & Trace da bpost](https://track.bpost.cloud/), na app My bpost ou colando o número na caixa acima. O código de barras vem no e-mail de expedição da loja, na mensagem da bpost, na etiqueta ou no aviso deixado na caixa do correio.

## Número de seguimento bpost: onde encontrar o código de barras

A bpost chama código de barras (*barcode*) ao número de seguimento. Está na etiqueta de envio, no talão dos correios, no aviso de ausência e no e-mail de confirmação. As lojas enviam-no normalmente por e-mail, muitas vezes como ligação para o Track & Trace. Sem código de barras, a bpost não consegue localizar o envio: pede-o à loja. Vê mais em [como encontrar o número de seguimento](guide:find-tracking-number).

Formatos mais comuns indicados pela bpost (exemplos inventados):

| Formato | Exemplo | O que identifica |
| --- | --- | --- |
| 24 dígitos começados por `3232` ou `3299` | `323200000000000000000001` | Código de barras de encomenda bpost |
| 18 dígitos começados por `3232` | `323212345000000001` | Código de barras de encomenda bpost |
| 23 ou 27 caracteres com `JJBE` + letra | `JJBEA0000000000000000000001` | Código doméstico bpost (encomenda ou registado) |
| 2 letras (exceto `U`), 9 dígitos e `BE` | `CE482910576BE` | Encomenda postal internacional expedida da Bélgica |
| `R` + letra, 9 dígitos e `BE` | `RR482910576BE` | Correio registado internacional da Bélgica |
| 30 dígitos começados por `010` | `010000000000000000000000000001` | Correio registado nacional na Bélgica |

Um código começado por `U` (`UA…BE`) serve apenas para a alfândega: o envio não tem Track & Trace, segue como correio normal e chega até 5 dias úteis após o desalfandegamento.

**Números parecidos.** Um código postal terminado nas letras de outro país pertence ao operador desse país — por exemplo, `…PT` é dos [CTT](carrier:ctt). Se escreveres `O` em vez de zero nos nove algarismos de um código `…BE`, o Peek lê-o como `0`; mas um algarismo trocado que invalide o dígito de controlo S10 impede o reconhecimento até o corrigires. Alguns códigos de 24 dígitos pertencem à [InPost](carrier:inpost), Ciblex ou Austrian Post, e os de 18 dígitos começados por `323200000` coincidem com os da [Ecoscooting](carrier:ecoscooting).

## Estados do Track & Trace da bpost explicados

O Track & Trace da bpost só existe em inglês, francês, neerlandês e alemão, e pede o código postal do destinatário nas encomendas normais. No Peek vês diretamente os estados em inglês da bpost, sem código postal:

| O que a bpost escreve | O que significa para ti |
| --- | --- |
| `Confirmation of preparation of the shipment received` | O remetente anunciou o envio, mas a bpost ainda não recebeu a encomenda. |
| `Item accepted after deposit by sender` | Entregue pelo remetente num ponto da bpost e aceite na rede. |
| `Item is announced / Landmark Global (bpost) received the information` | Envio internacional anunciado à Landmark Global, a marca transfronteiriça do grupo bnode. |
| `Landmark Global (bpost) has received the item` | A Landmark Global já tem a encomenda. |
| `Arrival at facilities` / `Arrival at international depot` | Chegou a uma triagem de origem ou ao centro internacional da bpost. |
| `Your item has been sorted` | Triada e a caminho da etapa seguinte. |
| `Awaiting presentation to Customs` / `No extra costs` | Em espera de controlo aduaneiro, ou já libertada sem taxas. |
| `Departure to country of destination` | Saiu da Bélgica para o país de destino. |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | O correio local (em Portugal, os [CTT](carrier:ctt)) recebeu a encomenda e fará a entrega. |
| `The postman's delivery round for your item has started` | Saiu hoje na volta do carteiro. |
| `Delivery interrupted due to unforeseen circumstances` | A volta parou do lado da bpost antes da tua morada; segue-se novo dia de entrega (`New delivery scheduled`). |
| `Item on it's way to bbox locker` | A caminho de um cacifo bbox, ainda indisponível para recolha. |
| `Item available at Pick-up point` / `Item available at the bbox locker` | Pronta a levantar num Ponto de Recolha ou num cacifo bbox. |
| `Item delivered` / `Item delivered in mailbox` / `Item delivered at safeplace` | Entregue em mão ou no ponto, na caixa do correio ou no local seguro. |
| `Your parcel was delivered at one of your neighbours. You can find all delivery details in your mailbox.` | Deixada num vizinho; o aviso na caixa do correio indica qual. |

Se surgir `Item arrived at the distribution center - Item can't be assigned to a delivery round - missing information`, faltam dados na morada; e `Returned to Landmark Global (bpost), Incorrect / illegible / incomplete address` indica devolução pelo correio estrangeiro por morada incorreta ou incompleta. Na Bélgica, o objetivo habitual após receção pela bpost é o dia útil seguinte (95% dentro da janela estimada); com acordo da loja, há também entregas ao sábado (8h00–17h00 em ponto ou cacifo) e ao domingo (9h00–19h00 em casa).

## Entrega na Bélgica, Pontos de Recolha e cacifos bbox

**Ausência em casa.** Sem preferências definidas, o carteiro tenta um vizinho ou um local seguro. Caso contrário, levantas o volume no Ponto de Recolha indicado no dia útil seguinte (a partir das 11h00 nos envios vindos do estrangeiro). A bpost avisa sempre por e-mail e só deixa aviso em papel quando não te consegue contactar digitalmente; confirma o ponto no Track & Trace antes de ires. Não há segunda passagem no mesmo dia, exceto após tentativa falhada num sábado (nova passagem no dia útil seguinte).

**Prazos e documentos.** São 14 dias seguidos num Ponto de Recolha e 5 dias seguidos num cacifo bbox, sem prolongamento (ao 6.º dia regressa ao remetente), e não é possível transferir um volume já chegado a outro ponto. Qualquer pessoa com o código de barras levanta uma encomenda normal; um registado (`010…` ou `R…`) exige o destinatário com identificação e código de barras, ou procuração assinada no aviso.

**Cacifos bbox e preferências.** Os mais de 2.500 cacifos bbox abrem 24/7 sem conta: nos modelos com ecrã usas o código QR do e-mail ou da app My bpost (com código de 6 dígitos nos envios diretos); os modelos sem ecrã abrem só pela app via Bluetooth e localização. Encomendas demasiado grandes vão para outro Ponto de Recolha, e pacotes que caibam na caixa do correio ficam lá primeiro. Em MyPreferences ou na app podes mudar vizinho ou local seguro até 5 minutos antes da entrega (exceto cobrança, código seguro, registados `R` e, no local seguro, envios com garantia ou seguro), ponto ou cacifo até à meia-noite da véspera, ou adiar uma vez por 1 dia útil antes da volta matinal.

## Encomenda atrasada, na alfândega ou não recebida

**Seguimento parado e reclamações.** Aguarda vários dias úteis na Bélgica, 5 dias úteis de ou para países vizinhos, ou 10 dias úteis de ou para Portugal, Reino Unido e outros países; depois pede ao remetente que contacte a bpost, pois só o remetente pode abrir investigação formal (vê [seguimento que não atualiza](guide:tracking-not-updating)). Em compras a profissionais na UE, o vendedor tem por defeito 30 dias para entregar ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm)).

**Danificada, não recebida ou devolvida.** Podes recusar logo uma encomenda danificada num Ponto de Recolha; após a receção, envia fotos ao vendedor em 7 dias (e também à bpost se veio do estrangeiro para a Bélgica; vê [encomenda danificada](guide:damaged-parcel)). Se constar como entregue mas não estiver contigo, verifica vizinhos, caixa do correio, foto do local seguro na app e o [formulário de envio não recebido da bpost](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1), além de [encomenda entregue mas não recebida](guide:delivered-not-received). Se voltar ao remetente por morada incorreta, recusa ou prazo expirado, pede reembolso ou reenvio à loja ([encomenda devolvida ao remetente](guide:returned-to-sender)).

**Alfândega na Bélgica.** Pagas os custos de importação na app My bpost ou no Track & Trace num prazo de 14 dias (um controlo físico pode somar 3 a 7 dias). Em compras fora da UE abaixo de 150 € (sem portes), a bpost cobra **3 € de direitos aduaneiros por tipo de produto** (desde 1 de julho de 2026) mais **2 € de taxa europeia de tratamento por tipo de produto** (desde 1 de novembro de 2026) — duas t-shirts iguais pagam 3 € + 2 € = 5 €, uma t-shirt e uns calções de banho pagam 6 € + 4 € = 10 € — acrescidos de IVA (normalmente 21%) e **21,50 €** de taxa administrativa da bpost se a loja não cobrou IVA e direitos na compra. Acima de 150 €, aplicam-se direitos pautais, IVA, **40 €** de taxa da bpost e os **2 €** por tipo de produto. Presentes entre particulares até 45 € estão isentos (salvo álcool, perfumes e tabaco). Se houver dupla cobrança de IVA por falha IOSS da loja, podes recusar na app ou pagar e reclamar o reembolso ao vendedor. Consulta as [compras fora da UE na bpost](https://www.bpost.be/en/shopping-outside-eu) e o guia de [alfândega](guide:customs).

> Para envios dentro da UE, a bpost nunca pede pagamentos em trânsito, nunca solicita dados pessoais por e-mail ou SMS e nunca ameaça com oficial de justiça. Quando uma encomenda de fora da UE tem custos de importação a pagar, porém, a bpost envia mesmo um pedido de pagamento por e-mail, SMS, notificação na app My bpost ou carta: paga-o só na app My bpost, no Track & Trace ou numa estação dos correios. Reencaminha mensagens suspeitas para `abuse@bpost.be` e vê [mensagens falsas de entregas](guide:delivery-scam-texts).

## Apoio ao cliente bpost e mediação postal

- **Telefone:** `02 278 51 26` nas páginas em inglês/neerlandês (`+32 2 278 51 26` do estrangeiro) e `02 278 51 27` em francês/alemão; seg.–sex. 08h00–12h30 e 13h00–18h00, sáb. 09h00–13h00 (tarifa zonal).
- **Chat e balcões:** chat em páginas como o Track & Trace (sem horário publicado) e atendimento em Post Office, Post Point ou Parcel Point.
- **Correio (sem selo na Bélgica):** `bpost, Box 5000, 1000 Brussels`.

A bpost não publica e-mail de apoio ao cliente. Após reclamação prévia sem solução junto da bpost, remetentes e destinatários podem recorrer gratuitamente ao [Provedor do Setor Postal belga](https://www.ombudsmanposte.be/en/) (`Boulevard Roi Albert II 8 box 4, 1000 Brussels`, `+32 2 221 02 21`).

## Seguir uma encomenda bpost com o Peek

O [Peek](/) deteta logo a bpost («Transportadora detetada») num código válido de duas letras (exceto `U`), nove dígitos e `BE`, e em `JJBE` + letra + 18 ou 22 dígitos. Num código de 24 dígitos começado por `3232` ou `3299`, consulta a bpost, a [InPost](carrier:inpost), a Austrian Post e a Ciblex («A verificar serviços de seguimento…») e escolhe automaticamente a bpost quando esta confirma («bpost tem esta encomenda.»). Num código de 18 dígitos começado por `3232`, pergunta à bpost e também à [Ecoscooting](carrier:ecoscooting) quando do 6.º ao 9.º dígito são zeros (`323200000…`), pedindo «Escolher» apenas se ambas responderem. Para registados belgas de 30 dígitos (`010…`), escolhe a bpost com «Alterar»; códigos `U` só são testados na [Chronopost](carrier:chronopost). Aceita também ligações `bpost.be` ou Track & Trace com `itemCodes=`. Nos códigos S10 entre a Bélgica e Portugal, o Peek faz a passagem automática entre a bpost e os [CTT](carrier:ctt) mantendo todo o histórico.

O Peek consulta a bpost sem código postal, mostra os eventos em inglês sob «O que diz bpost» (com etapas como «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Problema reportado» ou «Devolvida ao remetente»), localidade, peso, dimensões, nome e morada do ponto ou cacifo (mesmo após levantamento) e horas convertidas («Horas no teu fuso horário: …»). Atualiza até a cada 10 minutos, até a cada 2 minutos desde as 06h00 locais «Em distribuição» diretamente na bpost (15 minutos via serviços), e a cada hora após dois dias parado ou 12 horas em «Etiqueta criada» / «Aceite pela transportadora». Se com «À espera de bpost» continuar desconhecido dois dias, surge «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»; após quatro dias sem avanços antes da entrega, mostra «Pede ajuda a bpost». Não mostra data prevista nem guarda remetente, destinatário, moradas pessoais, contactos, preferências ou prova de entrega, com alertas em «Todos os registos», «Passos importantes» ou «Só a entrega».

## Perguntas frequentes sobre o seguimento bpost

### Quanto tempo tenho para levantar uma encomenda na bpost?

14 dias seguidos num Ponto de Recolha e 5 dias seguidos num cacifo automático bbox, sem prolongamento. Ao 6.º dia num cacifo, ou após 14 dias no ponto, regressa ao remetente.

### A bpost entrega ao sábado e ao domingo?

Sim, quando a loja online tem acordo de fim de semana com a bpost: ao sábado das 8h00 às 17h00 em Ponto de Recolha ou cacifo, e ao domingo das 9h00 às 19h00 ao domicílio. Cartas e registados só circulam em dias úteis.

### Quem pode levantar um correio registado na bpost?

Apenas o destinatário com documento de identificação e código de barras, ou um representante com procuração assinada no aviso de ausência. Uma encomenda comum pode ser levantada por qualquer pessoa com o código de barras.

### Porque é que o meu código de barras bpost não funciona?

Normalmente o vendedor ainda não entregou a encomenda à bpost («À espera do primeiro registo de bpost» na maioria dos códigos partilhados). Códigos começados por `U` não têm Track & Trace, e códigos `…BE` com algarismo mal copiado falham a validação S10.

:::sources
- [bpost: What does a barcode look like and where can I find it?](https://www.bpost.be/en/faq/what-does-barcode-look-and-where-can-i-find-it) – formatos e códigos U
- [bpost: Track & Trace, follow a parcel](https://www.bpost.be/en/faq/can-i-follow-my-parcel-online) – seguimento e correio estrangeiro
- [bpost: When can you follow a parcel with Track & Trace?](https://www.bpost.be/en/faq/when-can-i-follow-my-parcel-track-trace) – início do seguimento
- [bpost: Parcel in transit but didn't receive a barcode](https://www.bpost.be/en/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – pedir código ao vendedor
- [bpost: When will my parcel be delivered?](https://www.bpost.be/en/faq/when-will-my-parcel-be-delivered) – dia útil seguinte e janela de 95%
- [bpost: Track & Trace](https://track.bpost.cloud/) – portal oficial
- [bpost: Track & Trace (en.json)](https://track.bpost.cloud/assets/i18n/en.json) – idiomas e estados em inglês
- [bpost: Not home, where and when can I pick it up?](https://www.bpost.be/en/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – dia útil seguinte e aviso por e-mail
- [bpost: Missed parcel, can the postman pass again today?](https://www.bpost.be/en/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – sem segunda passagem no mesmo dia
- [bpost: Second pass after Saturday notice](https://www.bpost.be/en/faq/why-will-postman-be-passing-second-time-after-leaving-failed-delivery-notice) – segunda tentativa após sábado
- [bpost: Pick-up point](https://www.bpost.be/en/receive-parcel/pick-up-point) – 14 e 5 dias, registados e sem transferência
- [bpost: Remove parcel from locker](https://www.bpost.be/en/faq/how-do-i-remove-parcel-parcel-locker) – QR, 6 dígitos, Bluetooth e 6.º dia
- [bpost: Bbox](https://www.bpost.be/en/bbox) – +2.500 cacifos 24/7
- [bpost: Can I change the time of delivery?](https://www.bpost.be/en/faq/can-i-change-time-delivery) – adiar 1 dia útil
- [bpost: Delivery preferences](https://www.bpost.be/en/mybpostapp-pref-parcel) – prazos e exceções em MyPreferences
- [bpost: Receive a parcel from abroad](https://www.bpost.be/en/receive-parcel/abroad) – 3–7 dias na alfândega, IOSS e recolha às 11h00
- [bpost: Parcel stopped moving](https://www.bpost.be/en/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 e 10 dias úteis
- [bpost: File a complaint](https://www.bpost.be/en/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – reclamação pelo remetente e 7 dias para danos
- [bpost: Delivered, haven't received anything](https://www.bpost.be/en/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – verificações e foto do local seguro
- [bpost: Formulaire colis pas reçu](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1) – formulário online
- [bpost: Colis renvoyé à l'expéditeur](https://www.bpost.be/fr/faq/mon-colis-aurait-du-etre-livre-domicile-mais-ete-renvoye-lexpediteur-pourquoi-et-comment-puis-je) – motivos de devolução
- [bpost: Customs](https://www.bpost.be/en/receive-parcel/customs) – 14 dias para pagar e presentes até 45 €
- [bpost: Shopping outside the EU](https://www.bpost.be/en/shopping-outside-eu) – 3 € e 2 € por tipo de produto, IVA, 21,50 € e 40 €
- [bpost: Saturday and Sunday delivery](https://www.bpost.be/en/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – horários de fim de semana
- [bpost: Contact customer services](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – 02 278 51 26, horários, chat e Box 5000
- [bpost: Contacter le service clientèle](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 02 278 51 27
- [Ombudsman for the Postal Sector](https://www.ombudsmanposte.be/en/) – mediação gratuita
- [Ombudsman: Qui peut introduire une plainte ?](https://www.ombudsmanposte.be/fr/a-propos-de-nous/notre-fonctionnement/qui-peut-introduire-une-plainte/) – remetentes e destinatários
- [bpost: Phishing](https://www.bpost.be/en/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – alertas na UE e abuse@bpost.be
- [Your Europe: Envios e entregas](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – regra de 30 dias na UE
- [bnode: bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – grupo bnode e marcas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – regras de seguimento no Peek
:::
