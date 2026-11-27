---
title: Rastreamento GlobalPost: códigos GP e quem faz a entrega
description: Rastreie encomendas da GlobalPost: formato dos códigos GP e SDC, significado das etapas de rastreamento, alfândega e quem faz a entrega no seu país.
slug: globalpost-tracking
published: 2026-11-26
updated: 2026-11-26
---

A GlobalPost, parte do grupo Auctane, é um serviço de envios internacionais contratado por vendedores on-line por meio de plataformas como ShipStation e Stamps.com. A encomenda vai primeiro para um centro de expedição da GlobalPost, onde recebe a etiqueta internacional e a declaração aduaneira. Parceiros postais e transportadoras comerciais da GlobalPost assumem o trajeto a partir daí, e um correio ou transportadora no seu país realiza a entrega final. O vendedor fornece o código, normalmente iniciado por `GP`: cole-o na caixa acima. O rastreador próprio da GlobalPost fica em goglobalpost.com/tracking.

## Código de rastreamento GlobalPost: como é o formato

O Peek reconhece os códigos atuais da GlobalPost pelo formato: `GP`, quatro letras indicando o serviço, dez dígitos e `US` ou `GB` para o país de postagem. Com letras e dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `GPECON4829105736US` | `GP`, quatro letras, dez dígitos e `US`: encomenda GlobalPost enviada dos Estados Unidos. |
| `GPSTND4829105736GB` | O mesmo formato terminando em `GB`: enviada do Reino Unido. |
| `SDC482910573649182` | `SDC` e 15 dígitos: etiqueta antiga da GlobalPost. O Peek não identifica apenas pelo código: escolha GlobalPost em "Trocar". |
| `GPECON482910573US` | Apenas nove dígitos: o Peek não reconhece nenhuma transportadora. Confira se faltou um dígito. |
| `LZ482910576US` | Número postal dos EUA, fora do padrão GlobalPost: o Peek aponta [USPS](carrier:usps). Se escolher GlobalPost, o Peek avisa que ela "não emite códigos como este". |

O Peek ignora espaços, hifens e letras minúsculas.

**Onde encontrar.** No e-mail de envio do vendedor ou na página do pedido. Se não achar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

**Segundo código.** Em "Final Mile Tracking By", o rastreador da GlobalPost pode exibir o código próprio da empresa entregadora local — e também permite buscar a encomenda por esse segundo código.

## Status de rastreamento da GlobalPost explicados

O rastreador da GlobalPost mostra as movimentações da mais recente para a mais antiga, com horário, descrição e quase sempre o local, reescrevendo os registros dos parceiros com termos próprios. Na ordem em que a encomenda passa por elas:

| A GlobalPost diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Label Created` | O vendedor gerou a etiqueta. Nada se moveu ainda. | "Etiqueta criada" |
| `Data Received` | A GlobalPost recebeu os dados do vendedor, mas ainda não está com o pacote. | "Etiqueta criada" |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Um parceiro registrou o volume: o USPS a caminho da GlobalPost ou o correio do seu país. | "Em trânsito" |
| `Arrived at GlobalPost Facility` | Um centro de expedição da GlobalPost recebeu o pacote (movimentações do USPS podem vir antes). | "Recebida pela transportadora" |
| `Departed GlobalPost Facility` | Saiu desse centro. | "Em trânsito" |
| `Departed from Origin Country` | Deixou os Estados Unidos ou o Reino Unido. | "Em trânsito" |
| `In Transit`, `In-Transit` | Em transporte. | "Em trânsito" |
| `Arrived in Destination Country` | Chegou ao seu país. | "Em trânsito" |
| `In Customs` | Na alfândega do seu país. | "Na alfândega" |
| `Cleared Customs` | Liberada pela alfândega para seguir viagem. | "Em trânsito" |
| `Out for Delivery` | Na rota de entrega de hoje. | "Saiu para entrega" |
| `Attempted Delivery` | A transportadora tentou entregar, mas ninguém pôde receber. | "Tentativa de entrega" |
| `Exception, No Action Required` | Ocorrência na entrega local sem necessidade de ação da sua parte. | "Precisa de atenção" |
| `Delayed, No Action Required` | Em atraso; nada a fazer do seu lado. | "Precisa de atenção" |
| `Delivered` | Entregue. | "Entregue" |

Uma linha pode terminar com um aviso da GlobalPost de que atualizações levam tempo: o texto anterior é o status real. O Peek interpreta outras descrições pelas regras gerais usadas em todas as transportadoras; frases desconhecidas aparecem como escritas.

**Devoluções.** O rastreador da GlobalPost registra devoluções também como `Delivered`, localizadas na cidade do remetente. Quando essa cidade fica em outro país que não o seu, o Peek mostra "Devolvida ao remetente".

## Quem entrega encomendas da GlobalPost no seu país

Nos serviços Economy e Standard, a GlobalPost entrega o pacote a um operador postal como o USPS para o envio internacional. Suas páginas não listam um parceiro fixo por país, mas o rastreador pode mostrar quem entrega junto com o código dele.

**O nível de rastreamento depende do serviço escolhido pelo vendedor:**

| Serviço | Rastreamento | Impostos e taxas | Prazo informado ao vendedor |
| --- | --- | --- | --- |
| Economy | até chegar ao seu país e passar pela alfândega | pagos por você | 10 a 14 dias úteis |
| Standard | até a sua porta | pagos por você | 6 a 10 dias úteis |
| Plus / Plus – Duties & Taxes Included | até a sua porta | pagos pelo vendedor | 5 a 8 dias úteis |
| Flat Rate (antigo Go) | até a sua porta | incluídos no preço do vendedor | 6 a 10 dias úteis |

A partir dos EUA, o Plus atende mais de 30 países (incluindo Reino Unido, Irlanda, Canadá e Austrália); o Flat Rate cobre Canadá, Austrália, Nova Zelândia, Hong Kong e México. Não tem certeza do serviço? Pergunte ao vendedor.

**Entrega não realizada e retirada.** A transportadora local realiza as tentativas e guarda o volume não entregue: procure-a usando o código dela. Veja [entrega não realizada](guide:missed-delivery), [encomenda não retirada](guide:uncollected-parcel) e as páginas dos correios: [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) e [USPS](carrier:usps).

**Alfândega.** Em Economy e Standard, impostos e taxas ficam a seu cargo ("Delivered Duties Unpaid"). Na Irlanda e no restante da UE, compras on-line de fora da UE pagam imposto aduaneiro fixo de €3 por tipo de produto em encomendas até €150 (em vigor desde 1º de julho de 2026) e taxa de manuseio da União de €2 por tipo de produto, qualquer que seja o valor (desde 1º de novembro de 2026), além do IVA de importação quando não cobrado no checkout via IOSS. A Irlanda do Norte conta como território da UE para alfândega e IVA de mercadorias, segundo a Comissão Europeia. As páginas da GlobalPost não detalham como a taxa de €2 é cobrada: confirme com o vendedor se estava incluída no preço. Para outros países, veja [encomendas retidas na alfândega](guide:customs).

## Encomenda GlobalPost parada, extraviada ou devolvida

**Nada ainda.** Enquanto o rastreador da GlobalPost não exibir movimentações, o Peek mostra "Aguardando GlobalPost" e segue verificando. Se dois dias depois a GlobalPost ainda não reconhecer o código e nenhum serviço de rastreamento o localizar, o Peek exibe: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Com apenas `Label Created`, o volume pode não ter chegado à GlobalPost. Se nada mudar após alguns dias, confirme o envio com o vendedor.

**Dias sem atualização.** A GlobalPost lembra que atualizações internacionais levam tempo e que clima, feriados, greves e alfândega podem atrasar. No serviço Economy, o rastreamento termina após a alfândega do seu país. Quando uma encomenda em trânsito completa quatro dias sem movimentação, o Peek sinaliza com a data do último registro ("Sem atualizações desde seg 12 out") e lembra que pacotes costumam ficar sem novidades em voos longos, na alfândega ou no fim de semana; após três semanas sem novidades, orienta falar com o vendedor. Não há link para página de ajuda da GlobalPost; quando o Peek passa a acompanhar pela transportadora local, exibe o link dela se houver. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Não chegou após 30 dias.** A GlobalPost orienta contatar o vendedor se o pacote não chegar em até **30 dias** do envio: o vendedor pode investigar e fazer o pedido de indenização à GlobalPost em até **90 dias** da postagem, com análise de até três semanas. Veja [encomenda extraviada](guide:lost-parcel).

**Entregue, mas não chegou.** Consulte a transportadora indicada no rastreamento e depois o vendedor. Veja [entregue mas não recebida](guide:delivered-not-received).

**Impossível entregar ou devolvida.** A GlobalPost lista causas comuns: endereço errado ou incompleto, taxas não pagas, avisos alfandegários ignorados, itens restritos ou falha no desembaraço. O correio do país de destino assume o destino final — na maioria das vezes devolvendo ao vendedor ou retendo na agência local. A GlobalPost não consegue prever data de devolução nem recebe novo código do correio, e seus termos excluem volumes não entregáveis de indenização; fale com o vendedor sobre reembolso ou reenvio. Veja [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contato da GlobalPost

O suporte da GlobalPost responde em `info@goglobalpost.com` e pelo telefone `(888) 899-1255`, de segunda a sexta das 6h às 18h (horário do Pacífico). O [formulário de contato](https://www.goglobalpost.com/contact-support/) também aceita destinatários: selecione "Recipient" e informe o código de rastreamento. A GlobalPost avisa que sua equipe vê exatamente as mesmas informações da página de rastreamento.

Se o pacote não chegou, procure primeiro o vendedor, responsável pela contratação e por abrir reclamações. A GlobalPost esclarece ainda que envios da China via China Post não são da GlobalPost: consulte o remetente.

## Acompanhe uma encomenda GlobalPost com o Peek

O [Peek](/) identifica GlobalPost imediatamente em códigos `GP` ("Transportadora detectada") e consulta o próprio rastreador da GlobalPost apenas com o código, sem exigir código postal nem conta; links do rastreador da GlobalPost também funcionam. Para códigos antigos `SDC`, selecione GlobalPost em "Trocar". Se você só tiver o código da transportadora local, cole assim mesmo: o Peek acompanha mais de 4.500 transportadoras.

O Peek exibe o status, cada movimentação com o local, o país de destino e a transportadora local com seu código quando a GlobalPost os informa. Barra de progresso, cidades de remetente/destinatário e códigos postais são omitidos. Como a GlobalPost não fornece data prevista, peso nem comprovante de entrega, o Peek não os exibe. "Abrir o site de GlobalPost" abre o código no rastreador da GlobalPost.

Os horários da GlobalPost não trazem fuso horário: o Peek interpreta cada movimentação pelo relógio do local ou do trecho da viagem e converte para o fuso do seu aparelho; em peektracker.com e nos apps para iPhone e Android, o histórico termina com "Horários no seu fuso horário:" seguido do nome do fuso.

Quando a GlobalPost informa uma transportadora local reconhecida pelo Peek, o cartão exibe "Entrega por" seguido do nome dela e do código após o código `GP`, e o Peek passa a consultá-la também. Enquanto essa empresa tiver apenas o aviso prévio, sem movimentação própria, aparece "Anunciada a …, ainda não recebida". Com código mas sem nome reconhecido, o Peek consulta o correio do seu país (se postal) ou a transportadora única correspondente ao formato. Assim que a empresa local registra movimentação ao menos tão recente quanto a última da GlobalPost, o Peek prossegue lá preservando o histórico da GlobalPost.

Com a encomenda aberta, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos (a cada 5 minutos quando precisa abrir o rastreador da GlobalPost no navegador) a partir das 6h no horário local no dia em que sair para entrega, enquanto o próprio rastreador da GlobalPost responder (por serviços de rastreamento, até a cada 15 minutos); nos demais momentos, e após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora. Quando passa a seguir a transportadora local, adota a frequência dela. Você pode escolher notificações para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não contata a GlobalPost nem o vendedor por você.

## Perguntas sobre o rastreamento da GlobalPost

### Por que meu rastreamento GlobalPost parou após a alfândega?

No GlobalPost Economy, o rastreamento termina quando o pacote chega ao seu país e passa pela alfândega, e a GlobalPost explica que alguns envios têm rastreamento limitado no destino. A encomenda continua a caminho e pode ser entregue sem novas linhas. Se o rastreador informar o código da empresa local, o Peek e você podem acompanhar por lá.

### Quem entrega encomendas da GlobalPost?

Um correio ou transportadora do seu país. O rastreador da GlobalPost pode mostrar qual é e informa o código próprio dela.

### Quanto tempo a GlobalPost demora para entregar?

A GlobalPost estima 5 a 6 dias para países próximos e até 20 dias para destinos mais distantes. Para vendedores, estima 10 a 14 dias úteis no Economy, 6 a 10 no Standard e 5 a 8 no Plus, contados a partir do processamento no centro de expedição.

### A GlobalPost é confiável?

Sim. A GlobalPost pertence à Auctane e opera por meio de plataformas como ShipStation e Stamps.com, atendendo mais de 220 países e e afirma ter entregado mais de 60 milhões de envios.

### Como entrar em contato com a GlobalPost?

Envie e-mail para `info@goglobalpost.com` ou ligue para `(888) 899-1255`, de segunda a sexta das 6h às 18h (horário do Pacífico). Se o pacote não chegou 30 dias após o envio, fale primeiro com o vendedor.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, como funciona, rastreamento, atrasos, prazos de entrega, devoluções, pedidos de indenização, sem indenização para encomendas não entregáveis
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – serviços comparados: rastreamento, impostos, prazos de trânsito
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – e-mail, telefone, horários, formulário, China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – países, envios entregues
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – o rastreador
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – centros de envio, prazo de trânsito como estimativa
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto de €3 por tipo de produto até €150, taxa de manuseio da União de €2, datas
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por tipo de produto, qualquer que seja o valor
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – formatos de código, textos de status, parceiro de entrega, devoluções, fusos horários
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de código do USPS, passagem para a transportadora de entrega
:::
