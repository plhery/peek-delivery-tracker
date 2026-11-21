---
title: Seguimento CNE Express: números 3A5V, estados e entrega
description: Segue uma encomenda CNE Express (递一物流) da China continental: códigos 3A5V, significado dos estados, entrega local, alfândega na UE e uso do Peek.
slug: seguimento-cne-express
published: 2026-11-21
updated: 2026-11-21
---

A CNE Express (`递一物流`, `CNE EXPRESS CO LTD` / `递一国际物流有限公司`) é uma empresa de logística transfronteiriça fundada em 2003 em Xangai, na China continental, com rotas para mais de 100 países e regiões. Expede por avião, a partir da China continental, encomendas de vendedores na Amazon, eBay, Walmart, Wish, DHgate, TikTok, Temu, Shopify e SHEIN, entregando-as a um parceiro local no país de chegada. Consulta o teu número no [localizador em inglês da CNE](https://www.cne.com/en/track) ou cola-o na caixa acima; no catálogo do Peek também encontras a transportadora pesquisando `CNE` ou `递一物流`.

## Número de seguimento da CNE Express: como é

A CNE não publica um formato oficial de numeração. Este é o padrão que o Peek reconhece de imediato como pertencendo à CNE Express, com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `3A5V482910573` | `3A5V` e 9 algarismos: um número da CNE Express. |
| `3A5V48291057` | Apenas 8 algarismos: não tem o formato `3A5V` da CNE. O Peek não atribui a CNE e verifica-o junto da Colis Privé (cujo formato de 12 caracteres coincide) e dos serviços de seguimento. Confirma se falta um dígito. |
| `LX482910576CN` | Duas letras, 9 algarismos e `CN`: um envio postal da [China Post](carrier:china-post), cujos serviços (*International E-packet*, *China Post Packet*) a CNE também comercializa. |

Minúsculas, espaços e traços não contam no Peek. O localizador da CNE aceita vários números de uma vez, separados por espaços ou vírgulas.

**Onde encontrar o número.** No e-mail de envio da loja ou na página da encomenda: consulta [como encontrar o número de seguimento](guide:find-tracking-number), [encomendas da Temu](guide:temu-orders) e [encomendas da SHEIN](guide:shein-orders). Em `Shipment Information`, o localizador da CNE pode mostrar ainda o `Order number` do vendedor e o `Last mile number` da transportadora final com site e telefone.

## Estados de seguimento da CNE Express explicados

Acima das linhas de histórico, o localizador da CNE apresenta um estado geral em inglês:

| Estado da CNE | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipping` | Em caminho. | o estado da linha mais recente |
| `Customs inspection` | Retida na alfândega (`扣关` na página chinesa da CNE). | «Na alfândega» |
| `Delivered` | Entregue. | «Entregue» |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Morada incorreta, encomenda perdida ou destruída ou outro imprevisto. | «Problema reportado» |
| `Returned` | Devolvida ao vendedor (`退件` em chinês). | «Devolvida ao remetente» |
| `Not delivered` | A página chinesa indica `未发送` («não enviada»): a encomenda ainda não partiu. | o estado da linha mais recente |

Também podem surgir `Time out` e `Unknown`, casos em que o Peek se guia pelas linhas detalhadas. Cada linha indica hora, texto e local:

| A CNE diz | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `Parcel Infomation Received` | O vendedor enviou os dados da encomenda (grafia da própria CNE); a CNE ainda não tem o pacote. | «Etiqueta criada» |
| `Picked Up by Courier` | A CNE recolheu a encomenda junto do vendedor na China continental. | «Aceite pela transportadora» |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Entrada, triagem e carregamento numa instalação da CNE. | «Em trânsito» |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Saída de um centro logístico. | «Em trânsito» |
| `Arrived Export Airport`, `Released From Export Customs` | No aeroporto de partida na China continental e saída da alfândega de exportação. | «Em trânsito» |
| `Shipment Departed From Original Country` | O voo partiu da China continental. | «Em trânsito» |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Escala num aeroporto de trânsito. | «Em trânsito» |
| `Arrived at Import Airport`, `Arrived at destination` | Chegou ao país de destino: a triagem e a alfândega de importação ainda se seguem (não está na tua estação local). | «Em trânsito» |
| `The shipment is picked up by customs broker` | Recolhida por um despachante para desalfandegamento de importação. | «Na alfândega» |
| `Released From Import Customs`, `Pickup from airport` | Desalfandegada na importação e levantada no aeroporto. | «Em trânsito» |
| `Handled by local carrier` | Entregue pela CNE à rede de distribuição local; a aceitação própria dessa empresa vem a seguir. | «Em trânsito» |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Na transportadora final ou num centro de triagem local. | «Em trânsito» |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Triada no país de chegada e encaminhada para o centro local antes da volta de entrega. | «Em trânsito» |
| `The shipment has been processed in the parcel center of origin.` | Triagem da DHL na Alemanha retransmitida pela CNE após a entrega ao parceiro. | «Em trânsito» |
| `Pendiente de recepción en CTT Express`, `Enviado`, `En reparto`, `Entregado` | Estados ibéricos da [CTT Express](carrier:ctt-express) retransmitidos com explicação após vírgula: pré-registo («Etiqueta criada», sem recuar depois de a remessa avançar), expedição («Em trânsito»), distribuição («Em distribuição») e entrega («Entregue»). | conforme a etapa |
| `On Board with Courier`, `Your shipment is out for delivery` | Na carrinha do estafeta para distribuição hoje. | «Em distribuição» |
| `Your shipment delivery attempt failed [Recipient not at home]` | Ninguém em casa na tentativa de entrega; haverá nova tentativa. | «Tentativa de entrega», ou «Problema reportado» enquanto o resumo indicar `Exception` |
| `Sorting failed []` | Falha na triagem (os parênteses retos vazios são da CNE). | «Problema reportado» |
| `It was returned to customer's address by CNE` | Devolvida pela CNE à morada do vendedor expedidor. | «Devolvida ao remetente» |

Após a entrega ao parceiro local, as linhas deste surgem muitas vezes como «etiqueta；explicação» (ou com vírgula na CTT Express): o Peek lê apenas a etiqueta inicial, mantém em «Em trânsito» uma aceitação do parceiro codificada como `Shipping` e usa o código de estado da CNE quando desconhece a frase. Um estado de resumo final ou bloqueador (`Delivered`, `Returned`, `Customs inspection`, `Invalid address`, `Lost`, `Exception`, `Destroyed`) sobrepõe-se à última linha.

## Quem entrega as encomendas da CNE Express em Portugal e noutros países

A CNE opera linhas próprias para a Europa, América do Norte, Austrália e Brasil, escolhendo parceiros locais para a última milha: a sua página de parceiros lista entre outros [Correos](carrier:correos-spain), [DHL](carrier:dhl), [GLS](carrier:gls-de), [DPD](carrier:dpd-de), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane), [Royal Mail](carrier:royal-mail), [Evri](carrier:evri), Austrian Post, POST Luxembourg e [USPS](carrier:usps), além das rotas ibéricas retransmitidas via [CTT Express](carrier:ctt-express) e [CTT](carrier:ctt).

O Peek identifica diretamente três códigos de fornecedores finais da CNE quando acompanhados do número de transferência: [DHL](carrier:dhl) (`DE DHL`), [Royal Mail](carrier:royal-mail) e [USPS](carrier:usps), apresentando «Entrega: …» (ou «Anunciada a …, ainda não recebida» enquanto essa empresa não tiver leituras próprias). Com outro código de fornecedor e respetivo número de transferência (como `US SPX`, sem transportadora única associada), perante um código postal internacional válido o Peek consulta o operador postal do país de destino (os [CTT](carrier:ctt) em Portugal) ou a única transportadora indicada pelo formato desse número; sem fornecedor nomeado, o segundo número pode ser uma referência comercial e não é seguido. As tentativas de entrega e o levantamento seguem as regras da empresa final ([entrega falhada](guide:missed-delivery)).

**Alfândega em Portugal e na União Europeia.** Na **União Europeia**, os bens comprados online fora da UE em remessas com valor intrínseco até 150 € pagam desde 1 de julho de 2026 um direito aduaneiro da UE de 3 € por artigo (*per item*), somando-se desde 1 de novembro de 2026 uma taxa de manuseamento da União (*Union handling fee*) de 2 € por artigo seja qual for o valor (igual classificação pautal, descrição e, quando exigida, origem; acima de 150 € direitos normais). Em comunicado de 22 de outubro de 2026, a CNE informou os vendedores de que desde as `00:00` (hora de Pequim) de 26 de outubro de 2026, nas rotas para os 27 Estados da UE, cobra antecipadamente aos vendedores esses 2 € por encomenda e código HS além dos 3 € já cobrados da mesma forma desde julho de 2026, desalfandegando com base nos dados declarados pelo vendedor. O aviso nada refere quanto ao IVA de importação em **Portugal** (incluindo os **Açores** e a **Madeira**, que segundo a Comissão Europeia integram o território aduaneiro e a área de IVA da UE; devido salvo se já tiver sido cobrado na compra via IOSS) nem quanto a eventuais encargos do operador local; fora da UE aplicam-se as regras aduaneiras de cada país ([encomenda retida na alfândega](guide:customs)). Os avisos de atraso por rota saem em chinês em `新闻公告`.

## Encomenda CNE Express parada, perdida ou devolvida

Os clientes da CNE são os vendedores: em caso de atraso, perda, dano ou devolução, fala primeiro com a loja ou plataforma.

**«À espera de CNE Express».** Enquanto a CNE responder que o pedido não existe (`订单不存在`), o Peek mostra «À espera de CNE Express»; se ao fim de dois dias a CNE continuar sem reconhecer o código e nenhum serviço de seguimento responder, o Peek avisa: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» Com `Not delivered` (`未发送`) ou `Parcel Infomation Received`, confirma com o vendedor se não avançar após alguns dias.

**Quatro dias sem atualizações.** Ao fim de quatro dias sem novas linhas em viagem, o Peek mostra «Sem atualizações há quatro dias» com a nota sobre voos longos, alfândega e fim de semana, acrescentando o botão «Pede ajuda a CNE Express» para a página de contactos da CNE. Lê [porque é que o seguimento não atualiza](guide:tracking-not-updating) e [seguir encomendas vindas da China](guide:tracking-from-china).

**Na alfândega, com problema, devolvida ou dada como entregue sem chegar.** Em `Customs inspection` (`扣关`), `The shipment is picked up by customs broker`, `Lost`, `Destroyed`, `Invalid address` ou `Returned`, fala com o vendedor ([encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender)). No localizador da CNE, `View POD` mostra o comprovativo de entrega introduzindo o nome ou código postal do destinatário ([entregue mas não recebido](guide:delivered-not-received)).

## Apoio ao cliente e contactos da CNE Express

Na página [Contact us da CNE](https://www.cne.com/en/about/contact):

- **Linha nacional**: `400-021-5600` (*National Service Hotline*, linha de serviço na China continental).
- **Sede de Xangai**: `021-5109 6677`, `15/F, Block G, Lane 1777, Hualong Road, QingPu District, Shanghai` (China continental).
- **Horário**: `08:30-18:00 Mon-Sun` sem fuso indicado (os centros de triagem de Jiaxing e Dongguan na China continental funcionam `7 x 24 Hours`, indicação repetida no rodapé).
- **Formulário online**: *Consulting form* na mesma página escolhendo `Complaint`, com telefone, e-mail, nome, número de seguimento e mensagem (a CNE não publica e-mail de apoio ao cliente).

**Cuidado com SMS fraudulentos.** O domínio oficial da CNE Express é `www.cne.com` (`cne.com`). Antes de seguires ligações recebidas por SMS ou e-mail, consulta [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da CNE Express com o Peek

O [Peek](/) identifica de imediato a CNE Express num número `3A5V` («Transportadora detetada»), e qualquer ligação `cne.com` com `?no=` regista a encomenda na CNE Express (usando o primeiro número se houver vários). Para outro código alfanumérico CNE de 6 a 40 caracteres, se o Peek mostrar «A verificar serviços de seguimento…» ou não indicar a CNE, toca em «Alterar» e escolhe a CNE Express em «Todas as transportadoras» (ou usa «Alterar transportadora» depois); fora de 6 a 40 letras e algarismos, o Peek avisa que «não emite números como este».

O Peek lê o estado, cada leitura com o seu local, o país de destino e a transportadora final com o número de transferência. Lê `Arrived at destination` e `Handled by local carrier` na hora de Pequim (`UTC+8`), os estados retransmitidos da [CTT Express](carrier:ctt-express) em `UTC+02:00` e, em encomendas destinadas a Portugal, as etapas finais dos [CTT](carrier:ctt) (distribuição, tentativa falhada, ponto de recolha e entrega) em `UTC+01:00`, convertendo-os para o fuso do teu dispositivo («Horas no teu fuso horário: …»), enquanto as restantes horas sem fuso se mantêm tal como a CNE as escreve; deixa de fora o número de encomenda da loja, a data de entrega de resumo sem fuso e o `View POD` (a CNE não indica data prevista nem peso). «Abrir o site de CNE Express» abre o teu número em `cne.com`. Assim que a transportadora final consultada apresente uma leitura pelo menos tão recente como a última da CNE, o Peek passa a segui-la lá mantendo o histórico da CNE.

Enquanto mantiveres a encomenda aberta ou, no dia da entrega, tiveres notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 locais no dia em que passa a «Em distribuição» enquanto o localizador da CNE responder (após transição para a empresa final, acompanha o ritmo desta; apenas por serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ativas) —; nos restantes casos e após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a CNE nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da CNE Express

### A CNE Express é fidedigna?

Sim. A `CNE EXPRESS CO LTD` (`递一国际物流有限公司`) foi fundada em 2003 em Xangai (China continental), tem centro operacional em Shenzhen e centros de triagem em Jiaxing e Dongguan capazes de processar mais de 2 milhões de remessas por dia desde 2020, expedindo para vendedores na Amazon, eBay, Walmart, TikTok, Temu e SHEIN.

### Quem entrega as encomendas da CNE Express?

Os correios ou uma transportadora local escolhida pela CNE (como CTT, CTT Express, Correos, DHL, GLS, DPD, La Poste ou Royal Mail), cujo `Last mile number` pode constar no localizador da CNE.

### O que significa `Arrived at destination` na CNE Express?

A encomenda chegou ao país de destino, mas a triagem e a alfândega de importação ainda se podem seguir: não está ainda na tua estação local de distribuição. A CNE regista esta linha na hora de Pequim (`UTC+8`, 8 horas à frente de Lisboa no inverno), enquanto leituras locais vizinhas mantêm a hora local sem fuso e podem parecer fora de ordem.

### Quanto tempo demora a CNE Express a entregar?

A CNE indica aos vendedores prazos de referência variáveis consoante a rota: `3-8 business days` no *CNE Priority Express* (para EUA, Reino Unido, França e Alemanha) e `5-20 business days` no *CNE Standard Express*. Guia-te pela estimativa indicada pela loja.

### Como posso contactar a CNE Express?

Pelo número `400-021-5600` (linha da China continental), pelo `021-5109 6677` em Xangai ou através do *Consulting form* em `cne.com/en/about/contact` selecionando `Complaint`. Sobre a tua encomenda, fala primeiro com o vendedor.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – localizador em inglês, estados, Shipment Information e View POD
- [CNE: 运单追踪 (seguimento em chinês)](https://www.cne.com/track) – estados em chinês 未发送 e 扣关
- [CNE: Home](https://www.cne.com/en) – fundação em 2003 em Xangai, destinos, plataformas e linha 400
- [CNE: 首页 (página inicial em chinês)](https://www.cne.com/) – 递一物流 e 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – centros de triagem de Dongguan e Jiaxing, mais de 2 milhões de envios por dia desde 2020
- [CNE: Partner](https://www.cne.com/en/about/partner) – parceiros logísticos globais
- [CNE: Contact us](https://www.cne.com/en/about/contact) – linha 400, escritório de Xangai, horário 08:30-18:00 e Consulting form (Complaint)
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – serviços da China Post comercializados pela CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – prazo de referência 3-8 business days e rotas US/GB/FR/DE
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – prazo de referência 5-20 business days
- [CNE: 新闻公告 (notícias e avisos)](https://www.cne.com/news/notice) – avisos de atraso por destino
- [CNE: 关于欧盟国家2026年11月税改通知 (aviso fiscal UE)](https://www.cne.com/news/article/rDe4ANa6KYOW) – cobrança antecipada aos vendedores dos 2 € desde 26 de outubro de 2026 além dos 3 € por código HS e desalfandegamento pelos dados do vendedor
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Orientações sobre o direito de 3 € e a taxa da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € independentemente do valor, definição de artigo e isenção IOSS
- [Comissão Europeia: Âmbito territorial do IVA e das alfândegas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira
- [Universal Parcel Scraper: CNE Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cne) – formato 3A5V, ligações ?no=, estados e códigos, fornecedores finais, relógios Pequim / CTT Express / CTT Portugal / locais e transição
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção Colis Privé e China Post e transição para a empresa final
:::
