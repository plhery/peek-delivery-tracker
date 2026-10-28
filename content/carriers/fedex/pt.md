---
title: FedEx tracking: número, estados e entregas em Portugal
description: FedEx tracking em Portugal: o número de 12 dígitos, o que quer dizer «Delivery exception» e outras leituras, a alfândega e os teus direitos se a encomenda se perder.
slug: fedex-tracking
published: 2026-10-13
updated: 2026-10-28
---

Para seguir a tua encomenda, cola o número de rastreio FedEx, ou uma ligação de rastreio da FedEx, na caixa acima. O rastreio da própria FedEx está no fedex.com e na app FedEx Mobile.

## Número de rastreio FedEx: onde está e quantos dígitos tem

Procura no e-mail de envio ou na página da encomenda da loja. Uma ligação de rastreio do fedex.com também traz o número, depois de `trknbr=` ou `tracknumbers=`. Continuas sem ele? Mais ideias em [onde encontrar o número de seguimento da encomenda](guide:find-tracking-number).

| Número (inventado) | O que é |
| --- | --- |
| `123456789012` | 12 dígitos: um número de rastreio FedEx. O último dígito é um dígito de controlo calculado a partir dos 11 anteriores. |
| `123456789012343` | 15 dígitos: uma forma que o Peek também atribui à FedEx. |
| `61290000000000000014` | 20 dígitos começados por `6129` ou `7489`: FedEx Ground Economy (antes SmartPost), nos Estados Unidos. |

Doze dígitos também servem a outras transportadoras, como a [MRW](carrier:mrw), a [Mondial Relay](carrier:mondial-relay), a DPD France, a BRT e várias transportadoras asiáticas; quinze dígitos também a DPD France e a BRT. Se o fedex.com não conhecer o número, confirma no e-mail da loja qual é a transportadora.

## Estados do FedEx tracking: o que quer dizer «Delivery exception»

O Peek lê as leituras da FedEx em inglês, tal como a FedEx as escreve, e mostra por cima a etapa em português. Estas são as principais, mais ou menos pela ordem em que a encomenda as encontra:

| A FedEx escreve | O Peek mostra | O que quer dizer para ti |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Etiqueta criada | O remetente enviou à FedEx os dados do envio. |
| `Picked up`, `Tendered at FedEx location` | Aceite pela transportadora | A FedEx já a tem: um estafeta foi buscá-la ou o remetente entregou-a num local FedEx. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | Em trânsito | Avança pela rede da FedEx. |
| `Clearance in progress`, `Clearance delay` | Na alfândega | Está a ser desalfandegada (ver abaixo). |
| `International shipment release - Import` | Em trânsito | A alfândega libertou-a no país de destino. |
| `On FedEx vehicle for delivery` | Em distribuição | Vai no veículo de entrega. |
| `Delivery exception`, `Customer not available or business closed` | Tentativa de entrega | A entrega não se fez. |
| `Ready for pickup` | Pronta para levantamento | Está à tua espera num ponto de recolha. |
| `Delivered` | Entregue | Foi entregue. |
| `Shipment exception` | Problema reportado | A FedEx reporta um problema com o envio. |
| `Returning to shipper` | Devolvida ao remetente | Volta para o remetente. |

Quando a FedEx marca uma encomenda como atrasada, o Peek mostra «Problema reportado», exceto se a última leitura for `Package received after FedEx cutoff`: a FedEx recebeu a encomenda depois da hora-limite do dia, e ela só segue no dia útil seguinte.

## Entrega FedEx falhada e pontos de recolha

Se a entrega falhar, uma leitura pode dizer porquê: `Customer not available or business closed` se não estava ninguém, ou `Local delivery restriction - Delivery not attempted` se uma restrição local impediu a tentativa.

Se pedires à FedEx para guardar a encomenda num ponto de recolha, a leitura pode dizer `Delivery option requested` enquanto vai a caminho, e `Ready for pickup` quando lá chega. O Peek mostra então o nome e a morada do ponto, se a FedEx os indicar: quando o pedido é feito depois do envio, a página da FedEx só mostra a localidade a quem não tem sessão iniciada. A página da FedEx pode chamar ao último troço `Heading to pickup location`, que o Peek conta como «Em distribuição». Estas alterações tratas com a FedEx, não no Peek. Mais em [encomenda não entregue](guide:missed-delivery) e [quanto tempo tens para levantar uma encomenda](guide:uncollected-parcel).

## Alfândega: o que pagas numa encomenda FedEx

Uma encomenda que chega de fora da UE pode ter encargos de importação. Enquanto está na alfândega, o rastreio mostra `Clearance in progress` ou `Clearance delay`, e `International shipment release - Import` quando a alfândega a liberta.

Segundo a Autoridade Tributária e Aduaneira, desde 1 de julho de 2026 as compras online importadas numa remessa até 150 € pagam, além do IVA, 3 € de direitos aduaneiros por adição, ou seja, por tipo de produto: uma remessa de 100 € com uma camisa e um chapéu paga 6 €. Segundo o portal Your Europe da UE, cobrar este direito cabe ao vendedor ou à transportadora, e pode já vir incluído no que pagas online. Mais em [encomenda retida na alfândega](guide:customs).

## Encomenda FedEx atrasada, perdida ou dada como entregue

Se o rastreio não mexe, lê [porque é que o tracking não atualiza](guide:tracking-not-updating).

Se compraste numa loja, é ela que responde pela entrega, mesmo que seja a FedEx a levar a encomenda. Segundo o Your Europe, o vendedor é responsável por qualquer dano desde a expedição até à entrega. Salvo acordo para outro prazo, tem 30 dias para entregar; se não entregar, avisa-o e dá-lhe um prazo suplementar razoável. Se também não o cumprir, podes rescindir o contrato e ser reembolsado o mais rapidamente possível.

**Diz entregue mas não está cá?** A leitura de entrega da FedEx indica quem assinou, e o Peek não guarda esse nome. Confirma no fedex.com e depois fala com a loja. Mais passos em [encomenda dada como entregue mas não recebi](guide:delivered-not-received) e [encomenda extraviada ou perdida](guide:lost-parcel).

## Seguir uma encomenda FedEx com o Peek

O [Peek](/) reconhece os números FedEx de 12 e 15 dígitos quando o dígito de controlo está certo. Outras transportadoras usam as mesmas formas, por isso a encomenda é muitas vezes identificada por um serviço de seguimento ou ao fim de uma pequena espera. Um número que falha o controlo não é proposto à FedEx: escolhe a transportadora em «Alterar transportadora». Os números Ground Economy de 20 dígitos são logo atribuídos à FedEx, os códigos de barras da etiqueta (22 dígitos começados por `96` ou 34 dígitos) são seguidos pelo número FedEx que trazem, e também podes colar ligações do fedex.com com `trknbr=` ou `tracknumbers=`. Os números FedEx de 13 dígitos dos envios nacionais na Polónia são seguidos como FedEx Poland.

Um servidor do Peek abre a página de rastreio do fedex.com num navegador. Quando a FedEx recusa, ou pede a verificação do destinatário, serviços de seguimento preenchem o histórico.

Vês o estado, cada leitura num mapa com o local e a hora exata, o dia de entrega previsto, a hora da entrega e, enquanto a encomenda espera, o nome e a morada do local FedEx, se a FedEx os indicar. A verificação é de 10 em 10 minutos durante o dia, de 5 em 5 quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite, e as notificações escolhes tu: todas as atualizações, só as importantes ou só no dia da entrega. Ficam de fora o teu nome e morada, o remetente, a assinatura, o peso e as dimensões. As leituras aparecem no inglês da FedEx, menos três: a da entrega, em que a FedEx escreve quem assinou, aparece como «Entregue», e `Ready for pickup` e `Shipment exception` aparecem com as palavras do Peek.

Nos Estados Unidos, a maior parte das encomendas Ground Economy é entregue pela própria FedEx. Quando a FedEx diz que o correio americano (USPS) tem uma, o Peek segue-a lá.

## Perguntas sobre o FedEx tracking

### O que significa «delivery exception» na FedEx?

O Peek classifica esta leitura da FedEx como uma tentativa de entrega falhada e mostra «Tentativa de entrega». O motivo pode aparecer numa leitura como `Customer not available or business closed` (ninguém em casa ou empresa fechada).

### Os números de rastreio FedEx começam por 1Z?

Não. Um número começado por `1Z` é da [UPS](carrier:ups). Os da FedEx são só dígitos.

### Porque é que o rastreio FedEx só diz «Shipment information sent to FedEx»?

Esta primeira leitura quer apenas dizer que o remetente enviou à FedEx os dados do envio. O rastreio avança quando a FedEx tiver a encomenda: `Picked up` ou `Tendered at FedEx location`.

### Um SMS da FedEx a pedir uma taxa é verdadeiro?

Desconfia. O Gabinete Cibercrime da Procuradoria-Geral da República alertou, em julho de 2023, para mensagens falsas em nome dos CTT que dizem haver uma encomenda pendente e pedem uma pequena taxa antes da entrega; o link abre uma página falsa que pede os dados do cartão. Lembra também que os serviços de entrega têm sido terreno fértil para este esquema. Ignora e apaga estas mensagens, sem responder, e confirma a encomenda tu mesmo no fedex.com ou no Peek. Se já deste os dados do cartão, contacta o banco e cancela-o. Mais em [SMS ou e-mail falso: como saber se é burla](guide:delivery-scam-texts).

:::sources
- [Autoridade Tributária e Aduaneira: Mercadorias eCommerce](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – 3 € por adição desde 1 de julho de 2026, remessas até 150 €, exemplo de 6 €
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – 30 dias, prazo suplementar, rescisão e reembolso, responsabilidade do vendedor, quem cobra os 3 €
- [Ministério Público, Gabinete Cibercrime: alerta de 5 de julho de 2023](https://cibercrime.ministeriopublico.pt/sites/default/files/documentos/pdf/2023.07.05_alerta_phishing_ctt_visa_4.pdf) – mensagens falsas de encomenda pendente, pequena taxa, o que fazer
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – formatos do número, dígito de controlo, leituras da FedEx, o que o Peek lê da FedEx
:::
