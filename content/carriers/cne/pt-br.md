---
title: Rastreamento CNE Express: códigos 3A5V, status e contato
description: Rastreie encomendas da CNE Express (递一物流) da China: formato 3A5V, status explicados, quem entrega no destino, alfândega na UE e contato.
slug: rastreamento-cne-express
published: 2026-11-24
updated: 2026-11-24
---

A CNE Express (递一物流, CNE EXPRESS CO LTD) é uma empresa de logística internacional sediada em Xangai, China, fundada em 2003. Ela envia encomendas ao exterior para vendedores online — incluindo lojas no eBay, Walmart, TikTok, Temu e SHEIN —, despacha em voos saindo da China e entrega a um parceiro local no país de destino para o trecho final. Consulte o código no [rastreador da CNE](https://www.cne.com/en/track), em inglês, ou cole-o na caixa acima. Quem contrata a CNE é o vendedor, que fornece o código nos detalhes de envio do pedido.

## Como é o código de rastreamento da CNE Express

A CNE não publica um padrão oficial de numeração. O formato reconhecido pelo Peek como da CNE (com dígitos fictícios):

| Código | O que é |
| --- | --- |
| `3A5V482910573` | `3A5V` seguido de 9 dígitos: código da CNE Express. |
| `3A5V48291057` | Apenas 8 dígitos: fora do padrão da CNE. O Peek não identifica a CNE e consulta serviços de rastreamento. Confira se falta um dígito. |

Letras minúsculas, espaços e traços não interferem no Peek. O rastreador oficial da CNE aceita vários códigos de uma vez, separados por espaços ou vírgulas.

**Onde encontrar.** No e-mail de envio do vendedor ou na página do pedido. Se não achar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number), ou os guias de [pedidos da Temu](guide:temu-orders) e [pedidos da SHEIN](guide:shein-orders).

**Outros números.** Em `Shipment Information`, o rastreador da CNE também pode exibir o `Order number` do vendedor e o `Last mile number`: o código da transportadora local que fará a entrega, com site e telefone dela.

**Envios postais via CNE.** A CNE também comercializa serviços do próprio correio chinês, como International E-packet e China Post Packet. Para um código no padrão do correio chinês, consulte [China Post](carrier:china-post).

## Status de rastreamento da CNE Express explicados

O rastreador da CNE exibe um status geral acima das movimentações. Em inglês, como a CNE rotula:

| Status da CNE | O que significa | O Peek mostra |
| --- | --- | --- |
| `Shipping` | Em transporte. | status da linha mais recente |
| `Customs inspection` | Retida pela alfândega (扣关 na página chinesa). | "Na alfândega" |
| `Delivered` | Entregue. | "Entregue" |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Endereço inválido, pacote perdido ou destruído, ou outra ocorrência. | "Precisa de atenção" |
| `Returned` | Devolvida ao vendedor. | "Devolvida ao remetente" |
| `Not delivered` | A página chinesa mostra 未发送 ("não enviada"): a encomenda ainda não saiu. | status da linha mais recente |

`Time out` e `Unknown` também podem aparecer: nesses casos o Peek segue as movimentações. Cada linha traz horário, texto da CNE e local. As linhas mais comuns, em ordem:

| A CNE mostra | O que significa | O Peek mostra |
| --- | --- | --- |
| `Parcel Infomation Received` | O vendedor enviou os dados à CNE (a grafia é da própria CNE); a CNE ainda não recebeu o pacote. | "Etiqueta criada" |
| `Picked Up by Courier` | A CNE coletou o volume com o vendedor na China. | "Recebida pela transportadora" |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Numa unidade da CNE: entrada, triagem e carregamento. | "Em trânsito" |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Saiu da unidade. | "Em trânsito" |
| `Arrived Export Airport`, `Released From Export Customs` | No aeroporto na China e liberada pela alfândega de exportação chinesa (não a do seu país). | "Em trânsito" |
| `Shipment Departed From Original Country` | O voo partiu da China. | "Em trânsito" |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Escala num aeroporto intermediário. | "Em trânsito" |
| `Arrived at Import Airport`, `Arrived at destination` | Pousou no país de destino; ainda faltam triagem e alfândega — não está na sua unidade local. | "Em trânsito" |
| `The shipment is picked up by customs broker` | Despachante aduaneiro retirou a carga para desembaraço de importação. | "Na alfândega" |
| `Released From Import Customs`, `Pickup from airport` | Liberada pela alfândega de importação e retirada do aeroporto. | "Em trânsito" |
| `Handled by local carrier` | A CNE transferiu para a transportadora local; a entrada dela virá a seguir. | "Em trânsito" |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Com a transportadora entregadora ou num centro de triagem antes dela. | "Em trânsito" |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Triada no país de destino e enviada à base local entregadora antes da rota. Esse "transit" é o trecho interno final, não escala no exterior. | "Em trânsito" |
| `On Board with Courier` | No veículo do entregador: saiu para entrega. | "Saiu para entrega" |
| `Your shipment delivery attempt failed [Recipient not at home]` | Ninguém atendeu; nova tentativa será realizada. | "Tentativa de entrega", ou "Precisa de atenção" enquanto a CNE mostrar `Exception` |
| `Sorting failed []` | Erro na triagem (os colchetes vazios são da própria CNE). | "Precisa de atenção" |
| `It was returned to customer's address by CNE` | A CNE devolveu ao vendedor, cliente dela. | "Devolvida ao remetente" |

Após a transferência, as leituras da transportadora local aparecem no histórico da CNE, muitas vezes como rótulo curto seguido de explicação. O Peek interpreta o rótulo e, quando encontra texto desconhecido, usa o status dado pela CNE. O rastreador da CNE exibe horários sem fuso, e nem todos seguem o mesmo relógio (veja as perguntas abaixo).

## Quem faz a entrega das encomendas da CNE Express

A CNE opera linhas próprias para Europa, EUA, Canadá, Austrália, Brasil e outros países, selecionando parceiros locais para a entrega final. Sua página de parceiros lista USPS, Royal Mail, Austrian Post, Correos, Poste Italiane, GLS, La Poste, DHL, Evri, DPD e POST Luxembourg entre seus parceiros globais.

O Peek identifica três códigos que a CNE usa para entregadores finais: DHL na Alemanha ([DHL Paket](carrier:dhl)), [Royal Mail](carrier:royal-mail) no Reino Unido e [USPS](carrier:usps) nos EUA. Quando a CNE indica um deles junto com o novo código, o cartão do Peek avisa ("Entrega por USPS", por exemplo) e exibe o número (ou "Anunciada a …, ainda não recebida" enquanto o parceiro só tem o aviso prévio). Sem esse código, o Peek não nomeia entregador, pois o parceiro só rastreia pelo código próprio. Outros códigos, como `US SPX`, não identificam transportadora no Peek.

A CNE não define regras de tentativas de entrega, avisos ou pontos de retirada — tudo segue a transportadora local, como Evri, [La Poste / Colissimo](carrier:la-poste), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), Austrian Post ou POST Luxembourg. Veja [tentativa de entrega sem sucesso](guide:missed-delivery).

**Alfândega na União Europeia.** Compras online vindas de fora da UE pagam direito aduaneiro de €3 por item em pacotes de até €150, desde 1º de julho de 2026, e taxa de manuseio da União de €2 por item, qualquer que seja o valor, desde 1º de novembro de 2026. Um item é o conjunto de mercadorias com mesma classificação tarifária, descrição e, quando exigido, origem. Na área de IVA da UE também incide IVA de importação, salvo se pago na compra via IOSS. A CNE comunicou a vendedores em 22 de outubro de 2026 que, em suas rotas à UE, passou a cobrar os €2 deles antecipadamente desde 26 de outubro por código tarifário em cada pacote, somados aos €3 cobrados da mesma forma desde julho, fazendo o desembaraço pelos dados declarados pelo vendedor. O aviso não menciona IVA nem cobranças adicionais da transportadora final. Fora da UE, os tributos variam por país: veja [encomendas retidas na alfândega](guide:customs).

**Atrasos.** A CNE publica avisos de atrasos em rotas (como greves ou mau tempo no exterior) na página chinesa de notícias (新闻公告).

## Encomenda da CNE Express parada, extraviada ou devolvida

Os clientes da CNE são os vendedores que contratam o frete: em caso de atraso, extravio, avaria ou devolução, procure primeiro o vendedor ou marketplace.

**Nada ainda.** Se o rastreador da CNE não encontrar nada, ainda não há registro: o Peek mostra "Aguardando CNE Express" e continua verificando. Se após dois dias a CNE continuar sem reconhecer o código e nenhum serviço encontrá-lo, o Peek informa: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." `Not delivered` indica que o pacote ainda não foi postado (未发送 na página chinesa); o Peek exibe então a etapa da última linha, como "Etiqueta criada" para `Parcel Infomation Received`. Se nada mudar após alguns dias, confirme o código com o vendedor.

**Dias sem atualização.** Quando um envio em trânsito fica quatro dias sem nova leitura, o Peek destaca a data da última linha ("Sem atualizações desde seg., 12 de out."), avisa que pacotes costumam pausar em voos longos, na alfândega ou no fim de semana (após três semanas sem novidade, orienta procurar o vendedor) e exibe o link "Pedir ajuda a CNE Express" para a página de contato da CNE. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Na alfândega.** `Customs inspection` (扣关, retido na alfândega) ou `The shipment is picked up by customs broker` indica que a encomenda está na alfândega. Como a CNE desembaraça com base na declaração do vendedor, consulte-o primeiro se o status não avançar.

**Extravio, destruição, endereço errado ou devolução.** Para `Lost`, `Destroyed` ou `Invalid address`, avise o vendedor imediatamente pedindo reembolso ou reenvio: apenas o vendedor tem contrato com a CNE. `Returned` ou `It was returned to customer's address by CNE` indica retorno ao vendedor: acerte reenvio ou reembolso com a loja. Veja [encomendas extraviadas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

**Entregue, mas não chegou.** No site da CNE, uma linha com `View POD` guarda o comprovante de entrega (como foto), exigindo nome ou código postal do destinatário para abrir. Confira onde foi deixada, pergunte aos vizinhos e avise o vendedor. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

## Atendimento e contato da CNE Express

Na [página Contact us da CNE](https://www.cne.com/en/about/contact):

- **Central:** 400-021-5600, linha de atendimento na China chamada de National Service Hotline.
- **Matriz em Xangai:** 021-5109 6677, 15/F, Block G, Lane 1777, Hualong Road, Qingpu District, Xangai.
- **Horário:** das 08h30 às 18h, de segunda a domingo (a CNE não informa fuso; seus centros de triagem operam 24 horas).
- **Formulário online:** formulário na mesma página. Escolha `Complaint` como tipo e informe telefone, e-mail, nome, código de rastreamento e mensagem.

A CNE não divulga e-mail de suporte; as demais filiais na China atendem vendedores. Sobre seu pedido, fale primeiro com o vendedor.

## Como rastrear uma encomenda da CNE Express no Peek

O [Peek](/) identifica a CNE Express na hora por um código `3A5V` ("Transportadora detectada"). Links de cne.com com `?no=` associam o pacote à CNE Express qualquer que seja o formato do número (se houver vários, o Peek pega o primeiro). Para qualquer outro código CNE, cole-o assim mesmo: o Peek acompanha mais de 4.500 transportadoras. Quando o padrão serve para várias empresas, exibe "Consultando serviços de rastreamento…"; se não casar com nenhuma, pesquisa assim que você adiciona. Se a CNE não surgir, toque em "Trocar" e escolha CNE Express em "Todas as transportadoras" (buscar por CNE ou 递一物流 também encontra), ou use "Trocar transportadora" num pacote já adicionado.

O Peek lê o rastreamento oficial da CNE: status, cada linha com local, país de destino e entregador final quando a CNE indica um dos três acima com código. Ele lê `Arrived at destination` e a linha de transferência ao entregador local no horário de Pequim, calcula o fuso das demais linhas pelo local ou trecho percorrido e exibe tudo no fuso do seu dispositivo. Ele omite número do pedido do vendedor, site/telefone do entregador e comprovante de entrega; a CNE não informa previsão de entrega nem peso.

Quando a CNE informa o entregador local com o código dele, o Peek consulta também esse parceiro (um dos três acima, o correio do seu país em códigos postais de duas letras, nove dígitos e duas letras, ou a única transportadora daquele formato). Assim que ele registra leitura tão recente quanto a última da CNE, o Peek passa a segui-lo preservando o histórico da CNE.

Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, verifica até a cada 10 minutos — e até a cada 2 minutos a partir das 6h locais no dia em que a CNE registra saída para entrega — enquanto o rastreador oficial responde (pelos serviços de rastreamento, até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados); fora disso, de hora em hora. Quando outra transportadora assume, o Peek segue o ritmo dela. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a CNE nem com o vendedor por você.

## Perguntas frequentes sobre rastreamento CNE Express

### A CNE Express é confiável?

Sim. A CNE EXPRESS CO LTD (递一国际物流有限公司) foi fundada em 2003 com sede em Xangai e centros de triagem em Jiaxing e Dongguan capazes de processar mais de 2 milhões de envios por dia desde 2020, segundo a empresa. Ela atende vendedores na Amazon, eBay, Walmart, TikTok, Temu e SHEIN, entre outros.

### Quem entrega encomendas da CNE Express?

Um correio ou transportadora local escolhida pela CNE, como os listados em sua página de parceiros: Royal Mail, La Poste, DHL, GLS, DPD ou Evri. O rastreador da CNE pode exibir o `Last mile number` do seu pacote.

### O que significa "Arrived at destination" na CNE Express?

Significa que o pacote chegou ao seu país. Triagem e alfândega de importação ainda podem ocorrer antes da entrega ao transportador local, portanto ainda não chegou à sua agência entregadora. O site da CNE registra essa linha no horário de Pequim, enquanto leituras no exterior podem usar horário local, fazendo a ordem dos horários parecer trocada.

### Quanto tempo a CNE Express leva para entregar?

A CNE divulga prazos de referência para vendedores que variam conforme a rota: 3 a 8 dias úteis no CNE Priority Express (para EUA, Reino Unido, França e Alemanha) e 5 a 20 dias úteis no CNE Standard Express. Considere sempre a estimativa da loja para o seu pedido.

### Como entrar em contato com a CNE Express?

Pela central 400-021-5600 ou pelo formulário online da página Contact us, selecionando `Complaint`. Para dúvidas sobre o pedido, procure primeiro o vendedor.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – rastreador, rótulos de status, linhas, Shipment Information e View POD
- [CNE: 运单追踪 (rastreamento em chinês)](https://www.cne.com/track) – 未发送 e 扣关 por trás dos rótulos em inglês
- [CNE: Home](https://www.cne.com/en) – fundação em 2003, Xangai, parceiros locais, destinos, plataformas e central
- [CNE: 首页 (página inicial em chinês)](https://www.cne.com/) – 递一物流 e razão social
- [CNE: About us](https://www.cne.com/en/about/intro) – centros de triagem, capacidade acima de 2 milhões/dia desde 2020 e plataformas
- [CNE: Partner](https://www.cne.com/en/about/partner) – parceiros globais de logística
- [CNE: Contact us](https://www.cne.com/en/about/contact) – central, matriz em Xangai, horários e formulário
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – serviços do China Post oferecidos pela CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – 3 a 8 dias úteis e rotas
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – 5 a 20 dias úteis
- [CNE: 新闻公告 (notícias e avisos)](https://www.cne.com/news/notice) – avisos de atraso por destino
- [CNE: 关于欧盟国家2026年11月税改通知 (aviso fiscal UE)](https://www.cne.com/news/article/rDe4ANa6KYOW) – cobrança antecipada de €2 dos vendedores desde 26 de outubro, além dos €3, por código tarifário e declaração do vendedor
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de €3 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por item qualquer que seja o valor e definição de item
:::
