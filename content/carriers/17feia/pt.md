---
title: Rastreio 17FEIA: números FEI…YQ, estados e entrega
description: Segue uma encomenda 17FEIA (一起飞) da China: formato do número FEI…YQ, significado de cada estado, transportadora final, alfândega e Peek.
slug: rastreio-17feia
published: 2026-11-21
updated: 2026-11-21
---

A 17FEIA (一起飞, também Shanghai Hongshan International Logistics, 上海宏杉国际物流) é uma empresa de logística chinesa. Recebe encomendas no seu armazém de Shenzhen ou Xangai, expede-as da China e, após o desalfandegamento de importação, entrega-as a uma transportadora para distribuição. Os seus números começam por `FEI` e terminam em `YQ`. Cola o teu na caixa acima ou usa o rastreio da 17FEIA em www.17feia.com. O número vem de quem expediu o pedido: o vendedor ou o transitário que enviou a mercadoria da China.

## Número de seguimento 17FEIA: como é

O Peek reconhece os números da 17FEIA como `FEI`, duas letras, dez algarismos e `YQ`: 17 carateres no total. As duas letras depois de `FEI` indicam a delegação da 17FEIA, como `SZ` para Shenzhen ou `SH` para Xangai. Todos os exemplos são inventados:

| Número (inventado) | O que é |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, duas letras, dez algarismos e `YQ`: um número 17FEIA. O Peek identifica a 17FEIA só pelo número. |
| `FEISZ482910573YQ` | Apenas nove algarismos: não segue a forma da 17FEIA. Verifica se falta um algarismo. |
| `FEISZ4829105736AB` | Duas letras diferentes de `YQ` no fim. O Peek não escolhe logo a 17FEIA, mas pergunta ao rastreio dela se conhece o número. |
| `HHWKG4829105736YQ` | Acaba em `YQ`, mas começa por `HHW`: é um número da [HuaHan Logistics](carrier:huahan). |

No Peek, minúsculas, espaços e hífenes não importam.

**Onde encontrar.** No e-mail de envio, na página da encomenda ou na conta do transitário se usaste um. Caso contrário, vê [onde encontrar o número de seguimento](guide:find-tracking-number). Mais tarde, o registo da 17FEIA pode também fornecer ao Peek o número próprio da transportadora de entrega.

## Estados de seguimento da 17FEIA explicados

O registo da 17FEIA dá à maioria das linhas uma etiqueta curta em chinês e uma frase de texto, da mais recente para a mais antiga. O Peek mostra o texto em inglês e lê a etapa a partir da etiqueta chinesa. As linhas da própria 17FEIA, pela ordem habitual:

| O que diz a 17FEIA | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Item information received.` | A 17FEIA tem os dados da encomenda, mas ainda não a encomenda. | «Etiqueta criada» |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | A caminho do armazém da 17FEIA, que ainda não a recebeu. | «Etiqueta criada» |
| `Warehouse receipt.` (`仓库收货`) | O armazém da 17FEIA em Shenzhen ou Xangai recebeu a encomenda. | «Aceite pela transportadora» |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | O armazém aguarda que o cliente da 17FEIA que contratou o envio confirme o pedido. | «Aceite pela transportadora» |
| `Order Confirmed.` (`订单已确认`) | Pedido confirmado. | «Aceite pela transportadora» |
| `Order dispatched from warehouse.` (`订单已出库`) | A encomenda saiu do armazém. | «Em trânsito» |
| `Export declaration in progress.` (`出口报关中`) | Declaração de exportação em curso na China, um passo normal antes do voo. Não está retida e não é a alfândega do teu país. | «Em trânsito» |
| `Flight has departed.` (`航班已起飞`) | O voo partiu da China. | «Em trânsito» |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escala no percurso e partida do voo seguinte. | «Em trânsito» |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Aterrou onde a encomenda passa pelo desalfandegamento de importação. | «Em trânsito» |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Um despachante aduaneiro levou a remessa para o seu armazém para desalfandegamento (ou após a libertação). Não é uma entrega na tua morada. | «Na alfândega» (ou «Em trânsito» se aparecer depois de `清关完成`) |
| `Customs Clearance is in progress.` (`清关中`) | A alfândega de importação está a verificar o envio. | «Na alfândega» |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Após a chegada ao porto de desalfandegamento, a alfândega está a inspecionar a encomenda e ainda não a libertou. | «Na alfândega» |
| `Customs Clearance Completed.` (`清关完成`) | A alfândega de importação libertou a encomenda. | «Em trânsito» |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Entregue no depósito da transportadora final. Segue-se o primeiro registo dessa transportadora («carrie» é a grafia do rastreio). | «Em trânsito» |
| `包裹已做国内退回处理` (`国内退回`) | Devolução interna na China: depois da confirmação no armazém, a encomenda regressa ao remetente na China em vez de sair do país. | «Devolvida ao remetente» |

**Duas linhas que não alteram o estado.** `Estimated departure date:` seguido de uma data (`预计起飞日期`) é uma previsão escrita antes do voo, não um registo: o Peek descarta-a. `Flight delayed.` (`航班延误`) indica atraso no voo: o Peek mantém a etapa da linha anterior.

**A barra de progresso.** A página de rastreio da 17FEIA também mostra uma barra desde `仓库收货` (receção em armazém), passando por `飞机起飞` (descolagem) e `飞机落地` (aterragem), até `成功签收` (assinado/entregue). O Peek não copia a barra, mas mostra uma linha com a etiqueta `成功签收` como «Entregue».

**As linhas da transportadora final.** Após a passagem, o rastreio da 17FEIA pode retransmitir registos da transportadora de entrega com a etiqueta `运输途中` (em trânsito), nas palavras dela e com o seu local: por exemplo DHL (`The international shipment has been processed in the export parcel center`), UPS nos Estados Unidos (`Arrived at Facility`, `Departed from Facility`, `On the Way, Delivery will be delayed by one business day.`) ou um parceiro no Reino Unido (`Shipment received in depot`). O Peek lê-os pelas palavras: uma receção fica em «Em trânsito», enquanto a distribuição ou entrega fazem avançar o estado.

**Porque as horas parecem fora de ordem.** Os passos em armazém na China (incluindo devolução interna) seguem a hora de Pequim (UTC+8). Os registos retransmitidos da transportadora final usam a hora local quando o local define um único fuso horário. As linhas de voo e alfândega não indicam local e o fuso que a 17FEIA lhes atribui nem sempre corresponde ao real: guia-te pela ordem das linhas.

## Quem entrega as encomendas da 17FEIA

Após o desalfandegamento de importação, a 17FEIA entrega a encomenda a uma transportadora que a leva até ti. Como o Peek não conseguiu consultar o site da 17FEIA, esta página não tem lista completa de parceiros nem áreas de entrega.

**O que mostra o código do Peek.** O Peek conhece três nomes que a 17FEIA dá aos parceiros: `DPD-DE` para [DPD Germany](carrier:dpd-de), `DE-DHL` para [DHL](carrier:dhl) na Alemanha e `FEDEX-GROUND` para [FedEx](carrier:fedex). Reconhece cinco destinos no registo da 17FEIA: Alemanha, França, Países Baixos, Polónia e Estados Unidos. Um histórico no código mostra a DHL na Alemanha a receber uma encomenda para França para a passar à empresa que entrega lá.

**Entrega falhada.** Tentativas e levantamento seguem as regras da transportadora final: contacta-a com o respetivo número. Vê [entrega falhada](guide:missed-delivery).

**Alfândega na UE.** As compras online feitas fora da UE têm um direito aduaneiro temporário de 3 € por adição em encomendas até 150 €, desde 1 de julho de 2026, e uma taxa de tratamento da União de 2 € por adição, qualquer que seja o valor, desde 1 de novembro de 2026. Uma adição corresponde às mercadorias com a mesma classificação pautal, descrição e, quando exigida, origem. O Peek não conseguiu confirmar como são cobradas numa encomenda da 17FEIA: pergunta ao vendedor ou ao teu transitário se estavam incluídas no preço. Fora da UE, o valor depende do teu país. Enquanto a alfândega de importação inspeciona o envio sem o libertar, o registo indica `Customs inspection in Progress, Release Pending.` Vê [encomendas na alfândega](guide:customs) e, antes de pagares após um SMS ou e-mail, [SMS falsos de entregas](guide:delivery-scam-texts).

## Encomenda 17FEIA parada, perdida ou devolvida

A 17FEIA trabalha para quem contratou o envio: o vendedor ou o transitário que usaste. Em caso de atraso, perda, danos ou devolução, fala primeiro com eles.

**Número não encontrado.** Se não conhece o número, o rastreio da 17FEIA não mostra progresso. O Peek mostra «À espera de 17FEIA» e continua a verificar. Se dois dias depois a 17FEIA continuar sem reconhecer o número e nenhuma outra fonte tiver respondido, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e verifica depois uma vez por dia, e uma vez por semana passada uma semana desde que a adicionaste. Confere o número com a mensagem de envio: `FEI`, duas letras, dez algarismos, `YQ`.

**Parada em `The order has information pending customer confirmation.`** Se usaste um transitário, verifica a tua conta ou mensagens lá. Caso contrário, pergunta ao vendedor.

**Sem novidades depois do voo.** Após `Flight has departed.`, podem passar dias sem nova linha. Ao fim de quatro dias sem atualização, o Peek assinala-o («Sem atualizações há quatro dias»). Vê [porque o seguimento não atualiza](guide:tracking-not-updating) e [seguir uma encomenda da China](guide:tracking-from-china).

**Entregue, mas nada chegou.** Verifica à volta de casa e pergunta aos vizinhos, depois à transportadora final e ao vendedor. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Pede reembolso ou novo envio ao vendedor ou ao transitário. Vê [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto da 17FEIA

O site da 17FEIA respondeu ao Peek com uma verificação antibot, pelo que esta página não indica contactos da 17FEIA. Sobre o teu pedido, fala com o vendedor ou transitário que contratou o envio: são eles os clientes da 17FEIA. Para a entrega em si, contacta a transportadora local.

## Seguir uma encomenda 17FEIA com o Peek

O [Peek](/) identifica logo a 17FEIA a partir de um número `FEI…YQ` («Transportadora detetada»): nenhuma outra transportadora seguida pelo Peek usa este formato. Uma ligação para a página de rastreio da 17FEIA com o número depois de `orderNumber=` também serve. Não precisas de código postal nem de conta.

Para qualquer outro número, como o da transportadora final, cola-o na mesma: o Peek segue mais de 4500 transportadoras. Quando a forma encaixa em várias transportadoras sem certeza, o Peek mostra «A verificar serviços de seguimento…» enquanto consulta; se não encaixa em nenhuma, pesquisa assim que a adicionares. Para escolheres a transportadora, toca em «Alterar» e procura `17FEIA`, `Feia` ou `一起飞`, ou usa «Alterar transportadora» numa encomenda já adicionada. O Peek só pergunta à 17FEIA por números compostos por `FEI`, duas letras, dez algarismos e duas letras: para outro formato indica que a 17FEIA «não emite números como este».

O Peek mostra o estado, até às 100 linhas mais recentes no inglês da 17FEIA com o local quando indicado, e o país de destino. Lê os passos de armazém na hora de Pequim e os registos retransmitidos de um país com fuso único (ou estado dos EUA com fuso único) na hora local, convertidos para o fuso do teu dispositivo; as linhas sem local ou sem fuso determinado (voos, alfândega ou um depósito sem país) mantêm a hora tal como a 17FEIA a escreve. O Peek omite a barra de progresso, a origem, os códigos postais e o peso. A 17FEIA não fornece data prevista de entrega, nome de serviço, dados de remetente ou destinatário nem comprovativo de entrega.

Quando a 17FEIA indica DPD Germany, DHL ou FedEx com um número da forma dessa transportadora, o cartão da encomenda mostra-o (por exemplo, «Entrega: DPD Germany») e coloca esse número abaixo do da 17FEIA. Enquanto essa transportadora tiver apenas o anúncio sem registo próprio, o cartão indica «Anunciada a …, ainda não recebida». Assim que mostrar um registo pelo menos tão recente como o último da 17FEIA, o Peek passa a segui-la lá e mantém o histórico da 17FEIA. Quando o rastreio da 17FEIA está inacessível, o Peek consulta serviços de seguimento e mostra o histórico deles nas palavras deles.

Enquanto tens a encomenda aberta no ecrã ou no dia da entrega com as notificações ativas, e enquanto o rastreio da 17FEIA responde, o Peek verifica até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6h na hora da transportadora no dia em que sai para distribuição; caso contrário, de hora a hora. Através de serviços de seguimento verifica de hora a hora, e até de 15 em 15 minutos no dia da distribuição enquanto a tens aberta ou com notificações ativas. Depois de dois dias sem novidades (12 horas enquanto ainda mostra «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega»; «Passos importantes» inclui a alfândega no porto de desalfandegamento, mas não a declaração de exportação na China. O Peek não pode contactar a 17FEIA, o vendedor nem o teu transitário por ti.

## Perguntas sobre o rastreio 17FEIA

### O que é a 17FEIA (一起飞)?

Uma empresa de logística chinesa, também conhecida como Shanghai Hongshan International Logistics (上海宏杉国际物流). Envia encomendas para fora da China a partir do seu armazém de Shenzhen ou Xangai e, após o desalfandegamento de importação, entrega-as a uma transportadora para distribuição. Os seus números começam por `FEI` e acabam em `YQ`.

### Onde posso rastrear um número FEI…YQ?

Na caixa acima ou no rastreio da 17FEIA em www.17feia.com. Usa o número completo: `FEI`, duas letras, dez algarismos e `YQ`.

### O que significa «Awaiting carrie scan» na 17FEIA?

A linha completa é `Arrived at delivery hub，Awaiting carrie scan.`: o desalfandegamento de importação acabou e a 17FEIA entregou a encomenda no depósito da transportadora final, que ainda não a leu. O rastreio dela pode em breve ficar adiantado em relação ao da 17FEIA.

### Quem entrega as encomendas da 17FEIA?

A transportadora à qual a 17FEIA entrega o envio após o desalfandegamento de importação. O registo da 17FEIA pode indicar o nome e o número dela: quando é a DPD Germany, a DHL na Alemanha ou a FedEx, o cartão do Peek mostra-o. Caso contrário, pergunta ao vendedor.

:::sources
- [Universal Parcel Scraper: 17FEIA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/17feia) – nomes, formato `FEI…YQ` e delegações (`SZ`/`SH`), rastreio e ligação `orderNumber=`, etiquetas chinesas, textos ingleses e etapas (incluindo inspeção aduaneira, `国内退回`, mudança de etapa de `清关行提货到仓` após `清关完成` e linhas retransmitidas DHL/UPS/depósito Reino Unido), barra de progresso, gestão de fusos horários, parceiros (`DPD-DE`, `DE-DHL`, `FEDEX-GROUND`), destinos e 100 registos mais recentes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outros formatos acabados em `YQ` (`HuaHan Logistics`), serviços de seguimento de recurso e passagem para a transportadora local
- [Comissão Europeia: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro temporário de 3 € por adição até 150 € desde 1 de julho de 2026, taxa de tratamento da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € por adição seja qual for o valor, definição de adição
:::
