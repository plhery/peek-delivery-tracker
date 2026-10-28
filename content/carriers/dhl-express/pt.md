---
title: DHL Express tracking: carta de porte, estados e alfândega
description: DHL Express tracking em Portugal: onde está a carta de porte de 10 algarismos, o que querem dizer os estados, alfândega, entregas falhadas e encomendas perdidas.
slug: dhl-express-tracking
published: 2026-10-13
updated: 2026-10-27
---

Cola uma carta de porte da DHL Express, ou um link de seguimento do MyDHL+, na caixa acima para seguir a encomenda. O seguimento da própria DHL está no seu site (MyDHL+) e na aplicação da DHL Express.

## Número de seguimento DHL Express: onde encontrar a carta de porte

O número de seguimento da DHL Express é o número da carta de porte (em inglês, «waybill number»). Procura-o no e-mail de envio da loja ou na página da tua encomenda; um link de seguimento do MyDHL+ também o traz, a seguir a `id=`. Mais ideias em [onde encontrar o número de seguimento da encomenda](guide:find-tracking-number).

| Forma (inventada) | O que é |
| --- | --- |
| `1234567891` | A carta de porte: 10 algarismos, o número que segue o envio inteiro. O último é um algarismo de controlo. |
| `JD014600001234567890` | O identificador de volume: `JD01` e 16 algarismos, impresso como `(J)JD…` por baixo do código de barras inferior da etiqueta. Cada volume do envio tem o seu, que leva à carta de porte. |

A [Mondial Relay](carrier:mondial-relay), a Relais Colis, a [TIPSA](carrier:tipsa), a Estafeta, a Packeta, a [DHL eCommerce Iberia](carrier:dhl-ecommerce-es) e a [DHL Paket](carrier:dhl) também usam números de 10 algarismos, por isso confirma no e-mail da loja qual é a transportadora.

A DHL reutiliza números de carta de porte: um número pode trazer leituras de um envio anterior, que o Peek ignora.

## Estados do DHL Express tracking: o que querem dizer

Estas são as leituras que o Peek recebe da DHL Express, no inglês da DHL, mais ou menos pela ordem em que a encomenda as encontra, com o estado que o Peek mostra para cada uma. Muitas acabam com o nome do centro, por exemplo `Processed at` seguido de uma cidade e de um país.

| Leitura da DHL Express | O Peek mostra | O que quer dizer |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Etiqueta criada | O remetente enviou à DHL os dados do envio. |
| `Shipment picked up`, `Shipment accepted` | Aceite pela transportadora | A DHL Express já tem a encomenda. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | Em trânsito | Está a avançar na rede da DHL. |
| `Scheduled to depart on the next planned movement` | Em trânsito | Espera num centro pelo próximo camião ou voo. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | Na alfândega | Está a passar pela alfândega (ver abaixo). |
| `Payment is received and recorded for shipment related fees` | Na alfândega | Os direitos ou taxas estão pagos; fica na alfândega até o desalfandegamento terminar. |
| `Clearance processing complete at` | Em trânsito | A alfândega acabou e a encomenda segue viagem. |
| `Arrived at DHL Delivery Facility` | Em trânsito | Chegou ao centro da DHL que a vai entregar. |
| `Shipment is out with courier for delivery` | Em distribuição | Um estafeta está a levá-la até ti. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Tentativa de entrega | A entrega não se fez. O «consignee» és tu, o destinatário. |
| `Shipment is scheduled for delivery` | Em trânsito | Há uma entrega marcada para uma volta seguinte, por exemplo depois de uma tentativa falhada. |
| `Awaiting collection by the consignee` | Pronta para levantamento | Está à tua espera num centro da DHL para a levantares. |
| `Delivered` | Entregue | Foi entregue. |
| `Shipment is on hold` | Problema reportado | A DHL parou o envio por agora. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Problema reportado | Foi parar ao centro errado e segue para o certo, com atraso. |
| `Returned to shipper` | Devolvida ao remetente | Volta para o remetente. |

## Entrega DHL Express falhada

Depois de uma tentativa falhada, o seguimento mostra `Delivery attempted but no response at Consignee address` ou `Delivery attempt could not be completed`. Se a seguir aparecer `Shipment is scheduled for delivery`, há nova tentativa marcada; `Awaiting collection by the consignee` quer dizer que a encomenda está à tua espera num centro da DHL para a levantares. Para alterar a entrega, fala com a DHL Express no site ou na aplicação: o Peek não a pode alterar. Mais em [entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).

## Alfândega: o que pagas numa encomenda DHL Express

Uma encomenda que chega de fora da UE pode ter encargos de importação. Enquanto está na alfândega, o seguimento mostra `Customs clearance status updated…`, `Processed for clearance at` ou `Clearance event`, depois `Payment is received and recorded for shipment related fees` quando os encargos estão pagos, e `Clearance processing complete at` quando a alfândega a liberta.

Segundo a Autoridade Tributária e Aduaneira, desde 1 de julho de 2026 as compras online importadas numa remessa até 150 € pagam, além do IVA, 3 € de direitos aduaneiros por adição, ou seja, por tipo de produto: uma remessa de 100 € com uma camisa e um chapéu paga 6 €. Segundo o portal Your Europe da UE, cobrar este direito cabe ao vendedor ou à transportadora, e pode já vir incluído no que pagas online. Mais em [encomenda retida na alfândega](guide:customs).

## Encomenda DHL Express parada, retida ou perdida

Se o seguimento não mexe, ou mostra `Shipment is on hold`, lê [porque é que o tracking não atualiza](guide:tracking-not-updating).

Se compraste numa loja, é ela que responde pela entrega, mesmo que seja a DHL Express a levar a encomenda. Segundo o Your Europe, o vendedor é responsável por qualquer dano desde a expedição até à entrega. Salvo acordo para outro prazo, tem 30 dias para entregar; se não entregar, avisa-o e dá-lhe um prazo suplementar razoável. Se também não o cumprir, podes rescindir o contrato e ser reembolsado o mais rapidamente possível.

**Diz entregue mas não está cá?** A página de seguimento da DHL pode mostrar a assinatura, que o Peek não guarda. Confirma no site da DHL e depois fala com a loja. Mais passos em [encomenda dada como entregue mas não recebi](guide:delivered-not-received) e [encomenda extraviada ou perdida](guide:lost-parcel).

## Seguir uma encomenda DHL Express com o Peek

O [Peek](/) reconhece uma carta de porte de 10 algarismos que passa no algarismo de controlo da DHL Express, põe a DHL Express em primeiro lugar e pergunta ao mesmo tempo à DHL Express e a outras transportadoras com números de 10 algarismos, como a ACS Courier, a Geniki Taxydromiki e a DHL eCommerce Iberia, indicando quem tem a encomenda. A ACS e a Geniki usam o mesmo algarismo de controlo: se uma delas também conhecer o número, escolhes tu. Um número de 10 algarismos que falha o controlo não é proposto à DHL Express: escolhe-a com «Alterar transportadora». Os números de volume `JD01` e as etiquetas `JJD0146` (`JJD014612345678901234`) são logo atribuídos à DHL Express e ligados à sua carta de porte. Os links do mydhl.express.dhl com a carta de porte também funcionam. Um link do dhl.com deixa a encomenda em «DHL», que não lê as cartas de porte da DHL Express: cola só a carta de porte ou, se já colaste o link, muda para «DHL Express» com «Alterar transportadora».

O Peek lê a DHL Express pelo serviço da sua aplicação móvel ou, se não der, abre a página de seguimento do dhl.com num navegador no seu servidor, o que é mais lento e pode ser recusado; nesse caso, serviços de seguimento preenchem o histórico. Verifica de 10 em 10 minutos durante o dia, de 2 em 2 ou de 5 em 5 minutos quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite. As notificações escolhes tu: cada leitura, só os passos importantes ou só o dia da entrega.

Vês o estado, cada leitura com o seu centro num mapa e o país de destino. O Peek acerta as horas pela cidade e pelo país do centro, Açores incluídos, e, quando isso não chega, pelas leituras vizinhas. As leituras aparecem no inglês da DHL, menos `Delivered`, `Shipment picked up` e `Shipment information received`, que aparecem com as palavras do próprio Peek. Não mostra a assinatura, ignora leituras antigas de uma carta de porte reutilizada e não dá dia de entrega previsto, peso nem Service Point. Não paga direitos nem altera entregas.

## Perguntas sobre o DHL Express tracking

### A carta de porte da DHL é o número de seguimento?

Sim. Na DHL Express, o número de 10 algarismos da carta de porte segue o envio inteiro; o identificador `JD01` corresponde a um só volume e leva à carta de porte.

### A DHL e a DHL Express são a mesma coisa?

No Peek, não: a DHL Express é uma transportadora à parte, separada da [DHL Paket](carrier:dhl) e da [DHL eCommerce Iberia](carrier:dhl-ecommerce-es), cada uma com os seus números e o seu seguimento. Uma encomenda sem carta de porte de 10 algarismos nem identificador `JD01` costuma ser de uma delas.

### Porque é que o meu número da DHL não funciona?

Muitas vezes é de outra transportadora da DHL: as cartas de porte da DHL Express têm exatamente 10 algarismos e o último é um algarismo de controlo, por isso um número com um erro de escrita costuma não passar no controlo. Uma carta de porte nova pode mostrar só `Shipment information received` até a DHL ir buscar a encomenda.

### Posso seguir a DHL Express sem número?

No Peek, não: é preciso a carta de porte de 10 algarismos ou um identificador `JD01` da etiqueta. Pede a carta de porte à loja ou ao remetente.

### Um SMS da DHL a pedir uma taxa é verdadeiro?

Desconfia. O Gabinete Cibercrime da Procuradoria-Geral da República alertou, em julho de 2023, para mensagens falsas em nome dos CTT que dizem haver uma encomenda pendente e pedem uma pequena taxa antes da entrega; o link abre uma página falsa que pede os dados do cartão. O alerta lembra também que os serviços de entrega têm sido terreno fértil para este esquema. Ignora e apaga estas mensagens, sem responder, e confirma a encomenda tu mesmo no site da DHL ou no Peek. Se já deste os dados do cartão, contacta o banco e cancela-o. Mais em [SMS ou e-mail falso: como saber se é burla](guide:delivery-scam-texts).

:::sources
- [Autoridade Tributária e Aduaneira: Mercadorias eCommerce](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – 3 € por adição desde 1 de julho de 2026, remessas até 150 €, exemplo de 6 €
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – 30 dias, prazo suplementar, rescisão e reembolso, responsabilidade do vendedor, quem cobra os 3 €
- [Ministério Público, Gabinete Cibercrime: alerta de 5 de julho de 2023](https://cibercrime.ministeriopublico.pt/sites/default/files/documentos/pdf/2023.07.05_alerta_phishing_ctt_visa_4.pdf) – mensagens falsas de encomenda pendente, pequena taxa, o que fazer
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – formas da carta de porte e do identificador de volume, algarismo de controlo, outros números de 10 algarismos, cartas de porte reutilizadas, leituras da DHL Express e estado do Peek para cada uma, assinatura no site da DHL, o que o Peek lê da DHL Express
:::
