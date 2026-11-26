---
title: Rastreamento FAR International: códigos FAR…YQ e status
description: Rastreie encomendas FAR International (泛远国际) da China: formato FAR…YQ, significado dos status, quem entrega no destino, alfândega e contato.
slug: rastreamento-far-international
published: 2026-11-25
updated: 2026-11-25
---

A FAR International (泛远国际, FAR International Logistics) é uma empresa de logística internacional sediada em Hangzhou, na China, fundada em 2004. Lojas online e marketplaces contratam a empresa para despachar pacotes ao exterior. A FAR coleta e inspeciona as encomendas na China, enquanto seus parceiros cuidam do transporte internacional, do desembaraço aduaneiro e da entrega final com uma transportadora no seu país. Rastreie o código na página em inglês [Track and Trace da FAR](https://www.far800.com/en/logistics) ou cole-o na caixa acima. O código vem da loja, nos detalhes de envio do pedido.

## Código de rastreamento FAR International: como ele é

A FAR não publica regras de formato. Este é o padrão que o Peek reconhece como da FAR, com dígitos inventados:

| Código | O que é |
| --- | --- |
| `FARAA4829173654YQ` | FAR, duas letras, 10 dígitos e YQ: código da FAR International. |
| `FARAA482917365YQ` | Apenas 9 dígitos: não segue o formato da FAR, e o Peek não identifica FAR International. Confira se falta um dígito. |

Letras minúsculas, espaços e hifens não fazem diferença no Peek. O rastreador da FAR exige o código completo e aceita até 50 por vez, um por linha.

**Onde encontrar.** No e-mail de envio ou na página do pedido. Caso contrário, veja [como encontrar seu código de rastreamento](guide:find-tracking-number), ou os guias para [pedidos da Temu](guide:temu-orders) e [pedidos da SHEIN](guide:shein-orders).

**Segundo código.** A página de resultado da FAR também pode exibir um código de última milha (尾程单号). Esse é o código atribuído pela transportadora que faz a entrega no seu país, e você pode rastreá-lo no site dela.

## Status de rastreamento da FAR International explicados

As linhas de armazém e voo da FAR aparecem em chinês seguido de inglês, como `仓库收到包裹 Arrived at Warehouse`: o Peek mantém a parte em inglês. Etapas posteriores podem vir apenas em inglês ou apenas em chinês. Cada linha traz horário e local, e o rastreador da FAR inclui uma coluna de fuso horário. Estas são as linhas mais vistas, na ordem em que a encomenda costuma passar por elas:

| A FAR mostra | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Arrived at Warehouse` | O armazém da FAR na China recebeu o pacote. | "Recebida pela transportadora" |
| `Inspection by Warehouse` | O armazém confere a mercadoria. A FAR abre os pacotes para inspeção ao coletá-los. | "Recebida pela transportadora" |
| `Await Release from Sender` | O armazém retém o pacote até o vendedor confirmar a liberação. | "Recebida pela transportadora" |
| `Departed Warehouse`, `Pickup Scan` | Saiu do armazém da FAR e foi coletado para o trecho seguinte. | "Em trânsito" |
| `In Transit to Export Port` | A caminho do aeroporto ou porto na China. | "Em trânsito" |
| `Export Custom Clearance Processing and Flight Booking` | Desembaraço de exportação na China (etapa de rotina antes do voo) e reserva de voo. A alfândega não reteve o pacote, e ainda não é a alfândega do seu país. | "Em trânsito" |
| `Scheduled to Take off`, `Export Scan` | Aguardando o voo e escaneado para exportação. | "Em trânsito" |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | No hub de saída e embarcado no voo. | "Em trânsito" |
| `Arrived at Facility`, `Departed from Facility` | Em um centro de transferência no caminho e saindo dele. | "Em trânsito" |
| `Last Mile Label Create` | A etiqueta da transportadora final foi gerada. Costuma surgir após a chegada ao hub de saída e não é um retrocesso. | o status da linha anterior |
| `At destination country` | Chegou ao seu país. Alfândega e triagem vêm a seguir. | "Em trânsito" |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Um despachante aduaneiro recebeu o pacote e iniciou a liberação de importação. | "Na alfândega" |
| `Customs Released`, `Import Scan`, `进口清关完成` | Liberado pela alfândega de importação. | "Em trânsito" |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | A FAR entregou o pacote à transportadora local no seu país. | "Em trânsito" |
| `包裹开始派送` | A transportadora local assumiu o pacote. Os eventos próprios dela virão em seguida: ainda não saiu em rota de entrega. | "Em trânsito" |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | Seguindo entre centros de triagem. | "Em trânsito" |
| `Out For Delivery` | Em rota de entrega. | "Saiu para entrega" |
| `DELIVERED` | Entregue. | "Entregue" |

Após o repasse, o histórico da FAR pode incluir linhas da própria transportadora local, como os textos da FedEx `Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` ("Saiu para entrega") e `Package delivered` ("Entregue"). Para outras frases, o Peek identifica palavras comuns como entregue ou alfândega; linhas não reconhecidas continuam no histórico sem alterar o status anterior.

O FAQ da FAR informa que um rastreamento mostrando RETURN indica problema na entrega. O Peek não atribui status próprio a uma linha que diz apenas RETURN (mantém o anterior), então leia o texto da linha e pergunte ao vendedor.

## Quem entrega encomendas da FAR International

A FAR se define como uma empresa "light asset". Suas unidades na China coletam, conferem, embalam, etiquetam e triam os pacotes. Já seus fornecedores — operadores portuários e aeroportuários, companhias aéreas, armadores, despachantes aduaneiros e outras empresas de logística — cuidam da alfândega, do trajeto internacional e da entrega de última milha. As páginas da FAR não dizem qual empresa entrega em cada país: procure o código de última milha na página de resultado da FAR.

A FAR não publica regras de tentativas de entrega, avisos ou pontos de retirada: valem as regras da transportadora entregadora. Rastreie o código de última milha no site dela e veja [entrega não realizada](guide:missed-delivery).

**Alfândega na União Europeia.** Compras online vindas de fora da UE pagam direito aduaneiro de €3 por item em pacotes de até €150 desde 1º de julho de 2026, e taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Um item reúne as mercadorias com a mesma classificação tarifária, descrição e, quando exigido, origem. As páginas da FAR não dizem como esses valores são pagos. Fora da UE, o valor devido depende do seu país: veja [encomendas retidas na alfândega](guide:customs).

**Quando a alfândega precisa de você.** O FAQ da FAR afirma que problemas de liberação no país de destino exigem ação do destinatário: contate as autoridades locais rapidamente. Se o prazo expirar sem liberação, o pacote é abandonado ou devolvido conforme as regras locais, com custos a cargo do remetente.

## Encomenda FAR International parada, extraviada ou devolvida

A FAR trabalha para as lojas e marketplaces que a contratam, e seu FAQ encaminha esses clientes ao gerente de conta da FAR. Portanto, em caso de atraso, extravio, dano ou devolução, procure primeiro o vendedor.

**Nada ainda.** Se o rastreador da FAR não encontrar o código, o Peek mostra "Aguardando FAR International" e continua verificando. Se nada mudar em alguns dias, confirme o código com o vendedor.

**Dias sem atualização.** O FAQ da FAR diz que o rastreamento de voos pode atrasar. Quando um pacote em trajeto passa quatro dias sem nova linha, o Peek marca o dia dessa linha ("Sem atualizações desde seg 12 out") e avisa que encomendas costumam ficar quietas em voos longos, na alfândega ou no fim de semana. Após três semanas sem novidade, a nota muda para "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor." Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Entregue, mas não chegou.** O FAQ recomenda procurar ao redor da residência — cantos, entrada, caixa postal, armário ou qualquer lugar onde possa ter sido deixada — e perguntar a parentes e vizinhos. Se continuar desaparecida, avise o vendedor imediatamente: a investigação precisa ser aberta em até 14 dias após a data da assinatura, pelo vendedor junto à FAR. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Danificada.** Guarde a caixa externa e o produto danificado, como orienta o FAQ da FAR, e comunique o vendedor para que ele acione a FAR. Veja [encomendas danificadas](guide:damaged-parcel).

**Devolvida ou extraviada.** Peça à loja o reenvio ou o reembolso. Veja [devolvida ao remetente](guide:returned-to-sender) e [encomendas extraviadas](guide:lost-parcel).

## Atendimento ao cliente e contato da FAR International

No [site em inglês da FAR](https://www.far800.com/en):

- **Telefone global:** 400-128-5656, número de atendimento chinês. A página de serviços diz que os clientes podem contatá-la por uma linha 24/7, sem informar se funciona a partir do exterior nem quais idiomas são atendidos.
- **E-mail:** csc@far800.com.
- **Endereço:** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, China, código postal 310022.

Sobre seu pedido, procure primeiro o vendedor, que contratou a FAR.

## Rastrear uma encomenda FAR International com o Peek

O [Peek](/) identifica FAR International na hora a partir de um código FAR…YQ ("Transportadora detectada"). Colar um link de resultado de far800.com contendo `key=` e um código FAR também funciona. Para qualquer outro código, como o de última milha, cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras e identifica a empresa quando o formato é exclusivo dela. Quando o formato serve para várias transportadoras, o Peek exibe "Consultando serviços de rastreamento…" enquanto verifica; quando não se encaixa em nenhum padrão, pesquisa após você adicionar. Se o Peek não identificar a empresa, toque em "Trocar" e escolha em "Todas as transportadoras", ou use "Trocar transportadora" em uma encomenda já adicionada. A FAR International responde apenas a códigos FAR…YQ.

O Peek lê o rastreamento da própria FAR: o status, cada linha com local e horário, a hora da entrega e o código de última milha. Quando a FAR informa o fuso horário de uma linha, o Peek lê o horário nesse fuso e o converte para o fuso do seu aparelho. Se a linha de entrega informar quem assinou, o Peek mostra o nome. A FAR não fornece previsão de entrega nem peso.

Quando a FAR divulga um código de última milha, o Peek consulta a transportadora correspondente: os correios do país de onde você acompanha para códigos postais (duas letras, nove dígitos e duas letras), como os [Correos](carrier:correos-spain) na Espanha, ou a transportadora exclusiva daquele formato. Assim que essa empresa apresentar um evento ao menos tão recente quanto o último da FAR, o Peek passa a acompanhá-la mantendo o histórico da FAR e exibe quem entrega ("Entrega por Correos", por exemplo). Sem essa correspondência, o Peek segue no rastreamento da FAR.

Enquanto o pacote está aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos a partir das 6h locais no dia em que sai para entrega — enquanto o rastreador da FAR responde; nos demais horários, a cada hora. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta a cada hora. Ele pode notificar "Cada movimentação", "Etapas importantes" ou "Só a entrega". Não consegue contatar a FAR nem o vendedor por você.

## Perguntas frequentes sobre o rastreamento FAR International

### A FAR International é confiável?

Sim. A FAR International Logistics foi fundada em Hangzhou em 2004. Sua controladora, FAR International Holdings Group, foi constituída em novembro de 2022 e, segundo notícias no site da FAR, abriu capital na Bolsa de Hong Kong em dezembro de 2023. A FAR afirma ter se tornado prestadora da UPS China em 2005, iniciado projeto transfronteiriço com a Temu em 2023 e firmado cooperação estratégica com TikTok e SHEIN em 2024.

### Quanto tempo a FAR International leva para entregar?

A FAR informa aos clientes estes prazos: Express em 3 a 5 dias úteis, Standard em 6 a 9 e Economy em 10 a 30, podendo variar conforme o código postal de destino. Use como referência a estimativa da loja para o seu pedido.

### O que significa "Await Release from Sender"?

O armazém da FAR na China está com a encomenda e a mantém retida até o vendedor confirmar a liberação. Se permanecer nesse status, fale com a loja.

### Como entrar em contato com a FAR International?

Pelo telefone global 400-128-5656 ou pelo e-mail csc@far800.com. Para resolver questões do seu pedido, fale primeiro com o vendedor.

:::sources
- [FAR International: Home](https://www.far800.com/en) – empresa, 泛远国际, 2004 e 2022, notícia da listagem em Hong Kong em 22 de dezembro de 2023, formulário de rastreamento, telefone global, e-mail e endereço
- [FAR International: Track and Trace](https://www.far800.com/en/logistics) – código completo e até 50 códigos, um por linha
- [FAR International: About FAR](https://www.far800.com/en/about) – 2004, 2022, prestadora UPS China em 2005, projeto Temu em 2023 e cooperação com TikTok e SHEIN em 2024
- [FAR International: Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – modelo light asset, fornecedores para alfândega, transporte internacional e última milha, plataformas e vendedores como clientes, inspeção na coleta, prazos e atendimento 24/7 para clientes
- [FAR International: FAQs](https://www.far800.com/en/service/qa) – atraso no rastreamento de voos, RETURN, entrega não recebida e prazo de 14 dias, danos e alfândega no exterior
- [FAR International: Privacy Policy](https://www.far800.com/en/support/privacy) – razão social, telefone, e-mail e endereço
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de €3 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por item qualquer que seja o valor e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato e link, textos e etapas, código de última milha, fusos horários, transição de transportadora e o que o Peek lê
:::
