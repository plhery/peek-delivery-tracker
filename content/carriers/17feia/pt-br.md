---
title: Rastreamento 17FEIA (一起飞): códigos FEI…YQ e entrega
description: Rastreie encomendas da 17FEIA (一起飞) vindas da China: formato FEI…YQ, status de voo e alfândega explicados, parceiros de entrega e como o Peek acompanha.
slug: rastreamento-17feia
published: 2026-11-24
updated: 2026-11-24
---

A 17FEIA (一起飞, também chamada Shanghai Hongshan International Logistics, 上海宏杉国际物流) é uma empresa chinesa de logística internacional. Ela recebe encomendas em seus armazéns em Shenzhen ou Xangai, embarca em voos para fora da China e, após o desembaraço aduaneiro de importação, entrega o volume a uma transportadora local para entrega final. Seus códigos começam em `FEI` e terminam em `YQ`. Cole o seu na caixa acima ou consulte no rastreador da própria 17FEIA em www.17feia.com. O código é fornecido por quem despachou seu pedido: o vendedor ou o redirecionador que enviou suas compras da China.

## Como é o código de rastreamento da 17FEIA

O Peek reconhece os códigos da 17FEIA como `FEI`, duas letras, dez dígitos e `YQ`: 17 caracteres no total. Em embarques saindo de Shenzhen, as duas letras após `FEI` são `SZ`. Todos os exemplos abaixo são fictícios:

| Código | O que é |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, duas letras, dez dígitos e `YQ`: código da 17FEIA. O Peek identifica a 17FEIA só pelo número. |
| `FEISZ482910573YQ` | Apenas nove dígitos: fora do padrão da 17FEIA. Confira se falta um dígito. |
| `FEISZ4829105736AB` | Duas letras finais diferentes de `YQ`. O Peek não atribui direto à 17FEIA, mas consulta o rastreador dela para verificar. |
| `HHWKG4829105736YQ` | Termina em `YQ`, mas começa com `HHW`: código da [HuaHan Logistics](carrier:huahan). |

No Peek, letras minúsculas, espaços e traços não interferem.

**Onde encontrar.** No e-mail de envio, na página do pedido ou na sua conta de redirecionador, se usou um. Caso contrário, veja [como encontrar seu código de rastreamento](guide:find-tracking-number). Mais adiante, o registro da 17FEIA também pode fornecer ao Peek o código da transportadora local.

## Status de rastreamento da 17FEIA explicados

O rastreamento da 17FEIA associa à maioria das linhas um rótulo curto em chinês e uma frase de texto, do mais novo para o mais antigo. O Peek mostra o texto em inglês e identifica a etapa pelo rótulo. As etapas próprias da 17FEIA costumam aparecer nesta ordem:

| A 17FEIA mostra | O que significa | O Peek exibe |
| --- | --- | --- |
| `Item information received.` | A 17FEIA recebeu os dados do pedido, mas ainda não recebeu o pacote. | "Etiqueta criada" |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | A caminho do armazém da 17FEIA, que ainda não o recebeu. | "Etiqueta criada" |
| `Warehouse receipt.` (`仓库收货`) | O armazém da 17FEIA em Shenzhen ou Xangai recebeu a encomenda. | "Recebida pela transportadora" |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | O armazém aguarda confirmação do cliente da 17FEIA (quem contratou o envio). | "Recebida pela transportadora" |
| `Order Confirmed.` (`订单已确认`) | Pedido confirmado. | "Recebida pela transportadora" |
| `Order dispatched from warehouse.` (`订单已出库`) | O pacote saiu do armazém. | "Em trânsito" |
| `Export declaration in progress.` (`出口报关中`) | Em declaração de exportação na China — etapa de rotina antes do voo; não é retenção nem a alfândega do seu país. | "Em trânsito" |
| `Flight has departed.` (`航班已起飞`) | O voo deixou a China. | "Em trânsito" |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escala no trajeto e partida do próximo voo. | "Em trânsito" |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Pousou no porto/aeroporto onde passará pela alfândega de importação. | "Em trânsito" |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Um despachante aduaneiro levou a carga ao armazém para desembaraço; não é entrega a você. | "Na alfândega" |
| `Customs Clearance is in progress.` (`清关中`) | Alfândega de importação analisando a remessa. | "Na alfândega" |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Após o pouso no porto de desembaraço, a alfândega está inspecionando a carga e ainda não liberou. | "Na alfândega" |
| `Customs Clearance Completed.` (`清关完成`) | Liberada pela alfândega de importação. | "Em trânsito" |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Entregue no centro da transportadora local, aguardando a primeira leitura dela ("carrie" é a grafia do próprio rastreador). | "Em trânsito" |

**Duas linhas que não alteram a etapa.** `Estimated departure date:` seguido de uma data é apenas previsão antes do voo, não uma leitura física: o Peek a descarta. `Flight delayed.` (`航班延误`) indica atraso aéreo: o Peek mantém a etapa da linha anterior.

**Barra de progresso.** O site da 17FEIA também exibe uma barra de 仓库收货 (recebido no armazém), 飞机起飞 (voo decolou) e 飞机落地 (voo pousou) até 成功签收 (entregue). O Peek não copia a barra, mas exibe uma linha marcada como 成功签收 como "Entregue".

**Etapas da transportadora final.** Após a transferência, o site da 17FEIA pode repassar as movimentações da transportadora entregadora sob o rótulo 运输途中 (em trânsito), com o texto e local dela (como `The international shipment has been processed in the export parcel center` da DHL). O Peek lê essas linhas pelo texto: um recebimento segue em "Em trânsito", enquanto saída para entrega ou entrega avançam a etapa.

**Por que os horários parecem fora de ordem.** As etapas de armazém na China usam horário de Pequim (UTC+8), enquanto a transportadora final usa o horário local. As linhas de voo e alfândega não trazem local e nem sempre usam o fuso correto: guie-se pela ordem das linhas.

## Quem faz a entrega das encomendas da 17FEIA

Após o desembaraço aduaneiro de importação, a 17FEIA repassa o volume à transportadora que entrega até você. Como o Peek não conseguiu ler o site da 17FEIA por conta de verificação antibot, esta página não traz lista oficial completa de parceiros ou áreas atendidas.

**O que o código do Peek mostra.** O Peek reconhece três códigos que a 17FEIA usa para parceiros: `DPD-DE` para [DPD Germany](carrier:dpd-de), `DE-DHL` para [DHL](carrier:dhl) na Alemanha e `FEDEX-GROUND` para [FedEx](carrier:fedex). Ele identifica cinco destinos nos registros da 17FEIA: Alemanha, França, Holanda, Polônia e Estados Unidos. Em um histórico conhecido pelo Peek, a DHL na Alemanha recebe um pacote destinado à França para repassar à transportadora local.

**Tentativa sem sucesso.** Tentativas e regras de retirada seguem a transportadora final: consulte-a usando o código dela. Veja [tentativa de entrega sem sucesso](guide:missed-delivery).

**Alfândega na União Europeia.** Compras online vindas de fora da UE pagam taxa aduaneira de €3 por item em pacotes de até €150, desde 1º de julho de 2026, e taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Considera-se um item as mercadorias com mesma classificação tarifária, descrição e, quando exigido, origem. Como não foi possível confirmar no site da 17FEIA como esses valores são cobrados, pergunte ao vendedor ou redirecionador se já estavam incluídos no preço. Fora da UE, os impostos variam por país. Enquanto a alfândega inspeciona o envio sem liberá-lo, o registro mostra `Customs inspection in Progress, Release Pending.` Veja [encomendas retidas na alfândega](guide:customs) e, antes de pagar algo pedido por SMS ou e-mail, [golpes de SMS de entrega](guide:delivery-scam-texts).

## Encomenda da 17FEIA parada, extraviada ou devolvida

A 17FEIA presta serviço a quem contratou o frete: o vendedor ou seu redirecionador. Em caso de atraso, extravio, dano ou devolução, fale com eles primeiro.

**Código não encontrado.** Quando não conhece o número, o rastreador da 17FEIA não mostra histórico. O pacote fica em "Aguardando 17FEIA" e o Peek continua verificando. Se após dois dias nem a 17FEIA nem outro serviço responderem, o Peek avisa: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Confira o formato na mensagem de envio: `FEI`, duas letras, dez dígitos e `YQ`.

**Parado em `The order has information pending customer confirmation.`** Se você usou um redirecionador, confira sua conta ou mensagens por lá; caso contrário, fale com o vendedor.

**Pausa após o voo.** Depois de `Flight has departed.`, podem passar vários dias sem novidade. Após quatro dias sem nova leitura, o Peek marca a data da última linha ("Sem atualizações desde seg 12 out"). Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Entregue, mas não chegou.** Verifique ao redor do endereço e pergunte aos vizinhos, depois contate a transportadora entregadora e o vendedor. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Peça reembolso ou reenvio ao vendedor ou redirecionador. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento da 17FEIA

O site da 17FEIA respondeu ao Peek com verificação contra robôs, portanto esta página não divulga telefone nem e-mail da 17FEIA. Para assuntos do pedido, fale com o vendedor ou redirecionador que contratou o envio; para assuntos de entrega final, fale com a transportadora entregadora.

## Como rastrear uma encomenda da 17FEIA no Peek

O [Peek](/) reconhece a 17FEIA de imediato em códigos `FEI…YQ` ("Transportadora detectada"): nenhuma outra transportadora acompanhada pelo Peek usa esse padrão. Links do rastreador da 17FEIA com o código após `orderNumber=` também funcionam, sem código postal nem login.

Se tiver outro número, como o da transportadora local, cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Quando o formato cabe em várias empresas sem certeza, exibe "Consultando serviços de rastreamento…" enquanto verifica; se não casar com nenhum padrão, busca assim que você adiciona. Para escolher manualmente, toque em "Trocar" e busque por 17FEIA, Feia ou 一起飞, ou use "Trocar transportadora" num pacote já adicionado. O Peek consulta a 17FEIA somente para códigos formados por `FEI`, duas letras, dez dígitos e duas letras; em qualquer outro formato, informa que a 17FEIA "não emite códigos como este".

O Peek exibe o status, cada linha no inglês da 17FEIA com o local quando informado e o país de destino. As etapas de armazém são lidas no horário de Pequim e as da transportadora final no horário local daquela cidade, todas convertidas para o fuso do seu dispositivo. Como voo e alfândega não trazem local, o Peek adota o fuso das linhas vizinhas (Pequim até a chegada do voo; depois o da transportadora entregadora se a 17FEIA repassar suas leituras, senão o do seu país), podendo haver pequena diferença de horas. Barra de progresso, origem, códigos postais e peso ficam de fora; a 17FEIA não informa data prevista, nome do serviço, remetente/destinatário nem comprovante de entrega.

Quando a 17FEIA indica DPD Germany, DHL ou FedEx com código compatível, o cartão exibe isso ("Entrega por DPD Germany", por exemplo) e lista o novo código após o da 17FEIA (ou "Anunciada a …, ainda não recebida" enquanto o parceiro só recebeu o aviso). Assim que esse parceiro mostra leitura tão recente quanto ou mais nova que a última da 17FEIA, o Peek segue a encomenda ali mantendo o histórico da 17FEIA. Se o site da 17FEIA estiver indisponível, o Peek consulta serviços de rastreamento e mostra o histórico no texto deles.

Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia da saída para entrega, enquanto o rastreador da própria 17FEIA responde (pelos serviços de rastreamento, até a cada 15 minutos nesse dia); fora disso, de hora em hora. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega" ("Etapas importantes" inclui a alfândega no porto de desembaraço, não a declaração de exportação na China). O Peek não pode falar com a 17FEIA, o vendedor nem o redirecionador por você.

## Perguntas frequentes sobre rastreamento 17FEIA

### O que é a 17FEIA (一起飞)?

É uma empresa chinesa de logística, também conhecida como Shanghai Hongshan International Logistics (上海宏杉国际物流), que embarca encomendas de seus armazéns em Shenzhen ou Xangai e, após o desembaraço aduaneiro de importação, repassa a uma transportadora local. Seus códigos começam com `FEI` e terminam com `YQ`.

### Onde rastrear um código FEI…YQ?

Na caixa acima ou no rastreador da própria 17FEIA em www.17feia.com. Digite o código completo: `FEI`, duas letras, dez dígitos e `YQ`.

### O que significa "Awaiting carrie scan" na 17FEIA?

A frase completa é `Arrived at delivery hub，Awaiting carrie scan.`: o desembaraço aduaneiro terminou e a 17FEIA entregou o pacote na base da transportadora local, que ainda não o escaneou. Logo o rastreamento dela pode ficar mais atualizado que o da 17FEIA.

### Quem entrega encomendas da 17FEIA?

A transportadora para a qual a 17FEIA transfere o pacote após a alfândega de importação. Quando o registro da 17FEIA aponta DPD Germany, DHL na Alemanha ou FedEx com seu código, o cartão do Peek já mostra essa informação; nos demais casos, pergunte ao vendedor.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de €3 por item até €150 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por item qualquer que seja o valor e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e mostra
:::
