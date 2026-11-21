---
title: Rastreio PostNL: códigos 3S e NL, estados e entrega
description: Seguir encomenda PostNL fora dos Países Baixos: formato dos códigos NL e 3S, estados em postnl.post, quem entrega em Portugal ou no Brasil e como pedir ajuda.
slug: postnl-rastreio
published: 2026-11-17
updated: 2026-11-21
---

A PostNL é o operador postal dos Países Baixos. Envia para o estrangeiro encomendas e correio de lojas neerlandesas e, através da Spring GDS, o seu braço internacional, transporta pedidos de lojas online de todo o mundo. Podes seguir uns e outros no site internacional da PostNL, [postnl.post](https://postnl.post/) (em inglês), ou na caixa de pesquisa acima. Quando entra no teu país, a encomenda passa muitas vezes para o serviço postal local ou para um parceiro de distribuição. O código vem no e-mail de expedição ou na página da encomenda; se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number).

## Código de seguimento da PostNL: como é formado

De acordo com a documentação técnica da PostNL para comerciantes, existem dois tipos de códigos de barras (com algarismos inventados):

| Código (inventado) | O que identifica |
| --- | --- |
| `CW482910576NL` | Duas letras, 9 algarismos e `NL`: número postal internacional S10 da PostNL. Um código iniciado por `C` (como `CW`, `CC`, `CD` ou `CP`) identifica uma encomenda *Parcels Non-EU* para fora da UE, como o Brasil. |
| `LA482910576NL` | `L`: pequeno pacote ou correspondência com seguimento (*Boxable* ou *Packet Track & Trace*). `LW` corresponde ao mesmo serviço. |
| `RI482910576NL` | `R`: correio registado (*Registered Letter*). `RF` corresponde ao mesmo serviço. |
| `UL482910576NL` | `U`: pequeno pacote sem seguimento (*Untracked*, também `UE` ou `UM`). Não contes com leituras após chegar ao país de destino. |
| `3SABCD482910576` | `3S`, um código de cliente em letras (1 a 4 letras) e algarismos, num total de 15 caracteres: código de barras de encomenda da PostNL. |
| `3SABCD4829105` | O mesmo código `3S` na versão de 13 caracteres. A PostNL usa-o no serviço *Parcel EU*, dos Países Baixos para outros países da UE, incluindo Portugal continental, Açores e Madeira. |

O último dos nove algarismos de um código terminado em `NL` é um dígito de controlo S10: basta trocar um algarismo para o número ficar quase sempre inválido.

O portal postnl.post aceita até 150 códigos de uma só vez. Responde aos números `NL` e aos códigos `3S` de 15 caracteres, mas recusa os `3S` de 13 caracteres com a mensagem `The shipment barcode was incorrect.`. Nesse caso, pede à loja o código do parceiro (*DownPartnerBarcode*): quando o vendedor emite uma etiqueta *Parcel EU*, a PostNL devolve-lhe também o código de barras do parceiro estrangeiro que vai entregar a encomenda no teu país. Tem em conta que, enquanto a encomenda ainda está nos Países Baixos, esse código do parceiro pode mudar.

## Estados de seguimento da PostNL explicados

Em postnl.post surge uma barra de progresso com `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing` e `Delivered`, e por baixo as leituras em inglês. Quase todas indicam o país onde ocorreram. Eis as linhas mais habituais, pela ordem em que costumam aparecer:

| postnl.post mostra | O que significa para ti |
| --- | --- |
| `Pre-advised`, `Preparing` | O remetente registou o envio e enviou os dados à PostNL; a encomenda pode ainda não ter sido entregue à transportadora. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | A PostNL recebeu a remessa conjunta do vendedor; a leitura individual do teu volume pode surgir mais tarde. |
| `The item is received by PostNL` | A PostNL leu o teu objeto individualmente, por vezes dias depois da entrada da remessa. |
| `Departed`, `Transit` | Em viagem entre países, frequentemente de avião. |
| `Arrived` | Chegou a um país do percurso, normalmente o país de destino. |
| `Customs` | Em controlo aduaneiro no país de chegada. |
| `The item has arrived at the domestic sorting centre`, `The item is at the local sorting centre` | Em triagem num centro do país indicado ao lado da leitura. |
| `The item is at the local delivery office` | No centro de distribuição local que vai fazer a entrega. |
| `The item is out for delivery` | Saiu para entrega na tua morada. |
| `Driver is en route to the pickup location` | O motorista leva a encomenda para um ponto de recolha, não para a tua porta. |
| `Pick-up point` | Aguarda levantamento num ponto de recolha. |
| `Unsuccesfull`, `Undelivered` | Tentativa de entrega não conseguida (a grafia é da própria PostNL). |
| `Undeliverable item, has been returned to shipper` | Não foi possível entregar; segue de volta para o remetente. |
| `The item has been returned and arrived at PostNL` | A devolução chegou de volta à PostNL. |
| `Delivered` | Entregue. |

`The shipment barcode was not found.` significa que postnl.post ainda não tem registos para esse código ou que há um carácter enganado. «Destination: unknown» na página de resultado indica que nenhuma leitura nomeou ainda o destino. Se indicares no Peek o código postal de entrega de uma encomenda `3S`, o Peek consulta também o seguimento próprio da PostNL, cujas mensagens em inglês para envios internacionais incluem `Your parcel has crossed the border`, `Your parcel's at a sorting centre in the destination country`, `Your shipment is on its way to the recipient` (em distribuição) e `We missed you; we’ll try again` (tentativa falhada).

## Quem entrega as encomendas da PostNL em Portugal e no Brasil?

A PostNL ou a Spring GDS leva a encomenda até ao país de destino. A Spring GDS explica que gere o troço internacional enquanto um parceiro local trata da entrega final, acrescentando que, ao entrar no teu país, o envio é muitas vezes entregue ao serviço postal local (dando como exemplos USPS, Royal Mail e Correos).

- **Código `NL` (norma S10):** segue o formato de 13 caracteres da União Postal Universal (UPU) partilhado entre operadores postais. Assim que a encomenda chega ao país de destino, podes testar o mesmo código nos [CTT](carrier:ctt) em Portugal ou nos Correios no Brasil. Um pacote sem seguimento (`UL`) pode não apresentar leituras locais.
- **Código `3S` (*Parcel EU* para Portugal):** abrange Portugal continental, Açores e Madeira e é entregue pelo parceiro da PostNL no estrangeiro, que a documentação da PostNL não identifica pelo nome. O código desse parceiro está com a loja.
- **Envios fora da UE (*Parcels Non-EU* para o Brasil):** usam um código S10 iniciado por `C` (`CW…NL`).

Novas tentativas, avisos na caixa de correio e prazos de levantamento seguem as regras de quem faz a entrega local; vê [tentativa de entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel). Sobre taxas e desalfandegamento em encomendas vindas de fora da UE ou para o Brasil, consulta [encomenda retida na alfândega](guide:customs): a Spring GDS lembra que os direitos e impostos são estatais e que o despachante pode pedir fatura ou identificação por e-mail ou SMS.

## Encomenda PostNL parada, perdida ou devolvida

**Ainda sem leituras.** Com `Pre-advised` ou `Preparing`, o vendedor reservou o envio mas talvez ainda não tenha entregue o volume. A Spring GDS pede que aguardes 24 a 48 horas após receberes o código para aparecer a primeira leitura.

**Parada em trânsito.** Depois de `Departed` ou `Transit`, uma encomenda que viaja de avião ou navio não recebe leituras até aterrar ou atracar, e a passagem pela alfândega (`Customs`) costuma demorar vários dias. Quando chegar ao destino, experimenta o código `NL` no operador postal nacional. Ao fim de quatro dias sem novas leituras em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra na lista o dia da última leitura («Sem atualizações desde seg 12 out») e, na página da encomenda: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a PostNL.», com a ligação «Pede ajuda a PostNL». Ao fim de três semanas sem leituras, passa a dizer: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a PostNL.» Mais dicas em [seguimento sem atualizações](guide:tracking-not-updating).

**Dada como entregue, mas não chegou.** A Spring GDS sugere perguntar aos vizinhos, procurar em locais abrigados (alpendre, garagem) e na portaria ou receção, notando que algumas transportadoras marcam o envio como `Delivered` até 24 horas antes da entrega real. Se continuar desaparecida, fala com o vendedor e lê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Comunica de imediato ao vendedor e fotografa qualquer embalagem danificada: segundo a Spring GDS, o vendedor precisa dessas fotografias para abrir uma reclamação junto deles. Consulta [encomenda perdida](guide:lost-parcel).

**Devolvida ao remetente.** Pede à loja novo envio ou reembolso. A Spring GDS aponta como motivos habituais morada incorreta (*Incorrect Address*) e encomenda não levantada dentro do prazo (*Unclaimed*), avisando que uma devolução já iniciada não pode ser revertida. Vê [devolvida ao remetente](guide:returned-to-sender).

## Contactos e apoio ao cliente da PostNL

Segundo as páginas oficiais da transportadora:

- **postnl.post:** para dúvidas sobre entregas fora dos Países Baixos, remete para a loja onde compraste (*«Questions about your delivery outside The Netherlands? Please contact your webshop.»*). Para entregas nos Países Baixos, aponta para o apoio ao cliente da PostNL.
- **Spring GDS:** por motivos de privacidade de dados e obrigações contratuais com os comerciantes, não discute detalhes de envios diretamente com consumidores. É o vendedor, titular da conta, quem abre averiguações junto da Spring GDS.
- **Transportadora local:** para reagendar uma entrega falhada, contacta diretamente quem faz a distribuição no teu país; a Spring GDS não consegue marcar novas entregas locais. Em Portugal, vê os contactos na página dos [CTT](carrier:ctt).

Para qualquer outro problema, falar com a loja ou o marketplace onde fizeste a compra é o caminho mais rápido.

## Seguir uma encomenda PostNL com o Peek

O [Peek](/) reconhece de imediato os números `NL` com dígito de controlo válido e os códigos `3S` com código de cliente de quatro letras. Um `3S` com três letras é atribuído à DHL eCommerce Netherlands, embora as regras da PostNL também incluam códigos de três letras. Com uma ou duas letras, o formato não basta para distinguir a transportadora (com duas letras, o Peek pergunta também à DHL eCommerce Netherlands): cola o código na mesma, pois o Peek acompanha mais de 3 500 transportadoras. Enquanto verifica, vês «A verificar serviços de seguimento…»; se aparecer outra transportadora, toca em «Alterar» e escolhe PostNL. As ligações de postnl.post (`/track?barcodes=` ou `/details/…`) e de mailingtechnology.com (`/tracking?tn=`), o rastreador da Spring GDS, também são lidas como PostNL. Nos códigos `3S`, surge o campo opcional «Código postal de entrega»: «PostNL só mostra uma encomenda enviada dentro dos Países Baixos com o código postal de entrega.» Com ele (e «País de entrega» fora dos Países Baixos), o Peek pergunta primeiro ao seguimento próprio da PostNL; sem ele, recorre a outras fontes.

O Peek lê postnl.post e, quando indicas o código postal, o seguimento próprio da PostNL. Mostra o estado, o histórico com o país de cada leitura, a hora de entrega e o país de destino. As horas são apresentadas no fuso horário do teu dispositivo (no fundo do histórico surge «Horas no teu fuso horário: …»); quando a leitura indica apenas o país num território com vários fusos horários — como Portugal (onde assume a hora de Lisboa face aos Açores) ou o Brasil (onde assume São Paulo) — a hora pode ter diferença de uma ou mais horas. A PostNL não indica data prevista de entrega nem nome do remetente. Num código `NL`, assim que a PostNL indica o país de destino (ou, na falta dele, usando o teu país — a região do telemóvel na app ou o país de ligação no navegador), o Peek consulta o operador postal nacional com o mesmo número — [CTT](carrier:ctt) em Portugal ou os Correios no Brasil — e muda para ele mal mostre progresso mais recente («Entrega: CTT Portugal» ou «Entrega: Correios Brazil»). Como postnl.post rejeita os códigos `3S` de 13 caracteres, sem código postal o Peek procura-os através de [serviços de seguimento universais](guide:universal-tracker).

O Peek consulta a PostNL até a cada 30 minutos, mesmo em distribuição, embora uma atualização manual possa verificar antes. Quando o operador postal nacional assume a entrega, o Peek passa a seguir o ritmo dessa transportadora. Podes receber alertas em «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a PostNL, os CTT, os Correios nem a loja por ti.

## Perguntas frequentes sobre o rastreio PostNL

### Onde está a minha encomenda da PostNL?

A última leitura indica a etapa atual e o país onde se encontra. Quando um código `NL` já aparece chegado a Portugal ou ao Brasil, experimenta esse mesmo número no site dos CTT ou dos Correios para veres as leituras locais mais detalhadas.

### O que significa «The shipment barcode was incorrect» na PostNL?

Significa que postnl.post não aceita esse código de barras. Quase sempre trata-se de um código `3S` de 13 caracteres do serviço *Parcel EU* da PostNL. Pede à loja o código de barras do parceiro que faz a entrega em Portugal e segue a encomenda no site desse parceiro.

### A PostNL entrega através dos CTT em Portugal ou dos Correios no Brasil?

Com frequência, sim. A Spring GDS, divisão internacional da PostNL, refere que ao entrar no país de destino a encomenda passa muitas vezes para o serviço postal local, e os códigos `NL` usam o formato postal internacional S10 aceite pelos CTT e pelos Correios. Já as encomendas *Parcel EU* com código `3S` de 13 caracteres para Portugal (incluindo Açores e Madeira) são entregues por um parceiro da PostNL que a transportadora não nomeia.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – limite de 150 códigos, barra de progresso, respostas «not found» e «incorrect», destino e indicação para contactar a loja
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – códigos 3S de 13 ou 15 caracteres, código de cliente em letras e códigos S10 terminados em NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU com 3S de 13 caracteres (Portugal incluindo Açores e Madeira), código do parceiro estrangeiro, Parcels Non-EU com C/CW, LA/LW, RI/RF e UL/UE/UM
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – empresa internacional de e-commerce integrada no grupo PostNL
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – entrega pelo serviço postal local ou parceiro local, 24–48 h até à primeira leitura, paragens em trânsito e na alfândega, entregue mas não recebido, fotografias de danos, devoluções e ausência de contacto com consumidores
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – pedidos de informação sobre seguimento remetidos para a loja online
- [UPU: Norma S10, Identification of postal items (PDF)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – identificador postal de 13 caracteres e cálculo do dígito de controlo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção de códigos, código postal 3S, fusos horários, consulta aos CTT e aos Correios e intervalo de 30 minutos no Peek
:::
