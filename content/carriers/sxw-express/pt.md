---
title: Seguimento SXW Express: números SXW, estados e entrega
description: Segue uma encomenda SXW Express (Shengxingwang) enviada de Shenzhen: formato do código SXW, seguimento oficial, estados, quem entrega e alfândega.
slug: seguimento-sxw-express
published: 2026-11-21
updated: 2026-11-21
---

A SXW Express é o nome usado pelo Peek para a `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`), uma empresa de logística transfronteiriça sediada em **Shenzhen, na China**, que expede mercadorias para o estrangeiro para exportadores e vendedores online. Se uma loja chinesa enviou a tua encomenda pela SXW, procura um número composto por `SXW` e doze algarismos e cola-o na caixa acima. Também podes consultá-lo no localizador oficial aberto a partir da secção *Express Tracking* em `sxwexpress.cn`, exatamente a mesma fonte que o Peek lê por ti.

## Número de seguimento da SXW Express: como é

O site da SXW Express não descreve o formato dos seus números. A estrutura que o Peek reconhece como exclusiva da SXW Express tem 15 caracteres: `SXW` seguido de doze algarismos. Exemplos com algarismos inventados:

| Número (inventado) | O que identifica |
| --- | --- |
| `SXW482910573649` | `SXW` e doze algarismos: o formato da SXW Express. O Peek identifica logo a SXW Express. |
| `SXW48291057364` | Apenas onze algarismos: não corresponde ao formato da SXW. Confirma na mensagem do vendedor se falta um algarismo. |
| `GFUS48291057364918` | `GFUS` e catorze algarismos: número da [GOFO Express](carrier:gofo), que o registo da SXW pode indicar para entregas nos **Estados Unidos**. |

Espaços, traços e minúsculas não afetam a pesquisa quando colas o número no Peek. É o vendedor quem reserva o envio junto da SXW e te transmite o código; se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number).

**Consultar no site da SXW.** Na página inicial de `sxwexpress.cn`, o separador *Express Tracking* tem uma caixa com *Enter your tracking number here!* e um botão *Track* que abre num novo separador o localizador da SXW em chinês, o qual dispõe também de uma página em inglês (`sxwexpress.cn:8082/en/trackIndex.htm`). Ao lado da caixa, um logótipo 17TRACK liga a `17track.net`, um serviço externo independente do localizador da SXW.

## Estados de seguimento da SXW Express explicados

No localizador da SXW, três linhas vêm em chinês do próprio sistema da SXW — e o Peek apresenta-as traduzidas em inglês — enquanto a maior parte das restantes vem em inglês da linha de expedição. Eis as mensagens principais pela ordem habitual:

| Localizador SXW / mostrado no Peek | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `Parcel Data Received`, `Electronic notification of order creation received.` | O vendedor enviou os dados eletrónicos da remessa; ninguém recebeu ainda a encomenda física. | «Etiqueta criada» |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | O mesmo registo eletrónico no sistema da SXW (traduzido para inglês no Peek). | «Etiqueta criada» |
| `到达收货点` → `Arrived at the receiving point` | Primeira leitura física nas mãos da SXW no ponto de receção (em inglês no Peek). | «Aceite pela transportadora» |
| `Parcel picked up`, `Item has been collected` | Encomenda recolhida pela linha logística que faz o transporte. | «Aceite pela transportadora» |
| `货物离开操作中心` → `Goods leave the operation center` | A mercadoria saiu do centro operacional da SXW (em inglês no Peek). | «Em trânsito» |
| `Parcel received at Shenzhen Sorting Centre.`, `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Entrada, triagem e saída de um centro de triagem em Shenzhen. | «Em trânsito» |
| `Parcel departure in Transit Facility, Parcel Departure` | Saída de um entreposto de trânsito ao longo do percurso. | «Em trânsito» |
| `Customs declaration completed`, `Clearence processing completed - Export`, `Released From Export Customs` | Declaração aduaneira de exportação concluída na **China** antes do voo (por vezes escrito `Clearence`). A encomenda não está retida na alfândega do teu país. | «Em trânsito» |
| `Parcel is leaving Airport, Parcel Leaving Port`, `International flight has departed` | Partida do voo no aeroporto chinês. | «Em trânsito» |
| `Parcel arrives at Airport, Parcel Arrival`, `International flight has arrived` | Chegada do avião ao aeroporto do país de destino. | «Em trânsito» |
| `Start customs clearence` | Início do desalfandegamento de importação no destino (escrito `clearence`). | «Na alfândega» |
| `Clearance processing completed - Import`, `Released From Import Customs` | Desalfandegamento de importação concluído; a remessa segue viagem. | «Em trânsito» |
| `Returned parcel has been recieved by shipper` | Encomenda devolvida e recebida de volta pelo expedidor na origem (escrito `recieved`). | «Devolvida ao remetente» |

Como o sistema da SXW e o da linha de transporte coexistem no mesmo registo, podem aparecer duas linhas equivalentes (`货物电子信息已经收到` e `Parcel Data Received`, ou `到达收货点` e `Parcel picked up`). Nalgumas rotas a SXW copia também eventos da transportadora final (como `Arrived at GOFO Regional Hub` nos EUA, `Outward MC - Handheld Acceptance` da Royal Mail no Reino Unido ou `Onboard for delivery` e `Delivered - Left in a safe place` da Australia Post). Mais explicações em [o que significam os estados de seguimento](guide:tracking-statuses).

**Horas e fusos horários.** O Peek lê os registos efetuados na China na hora de Pequim (`Asia/Shanghai`, UTC+8) e converte-os para o fuso horário do teu dispositivo; a partir da primeira linha cujo local termina no código de outro país (como `…, US`, `…, GB` ou `AU`), o Peek mantém a hora local do destino. Em peektracker.com e nas aplicações para iPhone e Android, aparece por baixo do histórico «Horas no teu fuso horário: …».

## Quem entrega as encomendas da SXW Express e que alfândega se aplica

O site da SXW Express refere entregas porta a porta `DDU/DDP` e distribuição local de última milha (*local last-mile delivery*) na Europa, América, Sudeste Asiático, Médio Oriente e África. A lista de produtos inclui linhas [UPS](carrier:ups) a partir de Hong Kong (*Hong Kong UPS Direct Line Double Clearing With Tax Included - F*, *Hong Kong UPS-5000 (H)*), uma *ARAMEX Special Line*, um *Middle East and Europe small package* e um *US Express Mail Small Packet - F*, sem especificar qual a transportadora que entrega em cada país.

**Transições que o Peek mostra.** Para uma encomenda enviada para os **Estados Unidos**, o registo da SXW pode trazer um número da [GOFO Express](carrier:gofo) (`GFUS` + 14 algarismos): nesse caso, o Peek mostra «Entrega: GOFO Express» (salvo se o canal de envio indicar outra transportadora como a UPS), apresenta «Anunciada a GOFO Express, ainda não recebida» enquanto a GOFO a listar sem leituras e passa a acompanhá-la na GOFO mal exista uma leitura tão ou mais recente do que a última da SXW. Do mesmo modo, quando o canal indica a [Swiss Post](carrier:swiss-post) com um número de envio suíço válido para a **Suíça** ou o **Liechtenstein**, o Peek passa o seguimento para a Swiss Post nas mesmas condições. Tentativas de entrega e recolha seguem as regras da transportadora final: pede o nome e o número local ao vendedor e consulta [entrega falhada](guide:missed-delivery).

**Alfândega em Portugal e na União Europeia.** Na **União Europeia**, desde 1 de julho de 2026 os bens comprados online fora da UE em remessas com valor intrínseco até 150 € pagam um direito aduaneiro da UE de 3 € por artigo (*per item*), e desde 1 de novembro de 2026 soma-se uma taxa de manuseamento da União (*Union handling fee*) de 2 € por artigo seja qual for o valor (um artigo reúne mercadorias da mesma encomenda com igual classificação pautal, descrição e, quando exigida, origem; acima de 150 € aplicam-se os direitos normais), além do IVA de importação em **Portugal** (incluindo os **Açores** e a **Madeira**, que segundo o âmbito territorial publicado pela Comissão Europeia fazem parte tanto do território aduaneiro como da área de IVA da UE), salvo se o IVA já tiver sido cobrado na compra através do IOSS. Confirma com o vendedor se a encomenda seguiu em regime `DDP` (encargos pagos) ou `DDU` e lê [encomenda retida na alfândega](guide:customs).

## Encomenda SXW Express parada, atrasada ou não entregue

**Apenas `Parcel Data Received` ou `The electronic cargo information has been received`.** O vendedor criou a expedição, mas a encomenda ainda não foi lida fisicamente. Se nada mudar após alguns dias, pergunta à loja se já entregou o pacote.

**«À espera de SXW Express».** Enquanto a SXW listar o envio sem leituras reais (ou apenas com etapas escritas com antecedência) e os serviços de seguimento também nada tiverem, o Peek mantém «À espera de SXW Express»: na aplicação iPhone ou Android ou numa conta, o Peek consulta a SXW a cada 6 horas após as primeiras horas; sem conta, durante as primeiras 6 horas, depois quando abres a ligação (no máximo a cada 6 horas, e uma vez por dia passados dois dias) e diariamente até 30 dias enquanto as notificações estiverem ativas. Pelo contrário, se a página da SXW responder que desconhece totalmente esse número e nenhum serviço o encontrar, ao fim de dois dias o Peek mostra: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»

**`Customs declaration completed`.** É a declaração de exportação na China antes do voo («Em trânsito»), e não uma retenção aduaneira no teu país.

**Sem atualizações, dada como entregue sem chegar, perdida ou devolvida.** Se o Peek mostrar um número da GOFO Express ou da Swiss Post, verifica esse seguimento; caso contrário, orienta-te pelo prazo estimado da loja e fala com o vendedor se for ultrapassado. Vê [porque é que o seguimento não atualiza](guide:tracking-not-updating), [seguir uma encomenda vinda da China](guide:tracking-from-china), [entregue mas não recebido](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da SXW Express

O site `sxwexpress.cn` não publica número de telefone, endereço de e-mail nem morada postal: a página *Contact US* mostra apenas o título e um painel *Online Support* vazio, e o acesso de membros em `sxwexpress.cn:8082` destina-se a contas empresariais com a SXW (não precisas de conta para seguir uma encomenda).

O cliente da SXW Express é o vendedor, pelo que deves contactar primeiro a loja onde compraste e abrir disputa no marketplace se não obtiveres resposta. Para entregas nos EUA asseguradas pela GOFO Express, contacta a GOFO sobre a entrega propriamente dita.

## Seguir uma encomenda da SXW Express com o Peek

O [Peek](/) identifica de imediato um código composto por `SXW` e doze algarismos com «Transportadora detetada», visto nenhuma outra transportadora do catálogo usar esse formato (se o vendedor te deu um número diferente, o Peek segue mais de 4 000 transportadoras; para selecionar a SXW manualmente carrega em «Alterar» ou «Alterar transportadora» e pesquisa `SXW Express`, `Shengxingwang` ou `胜兴旺`).

O Peek consulta diretamente o localizador oficial da SXW sem conta, código postal nem telefone. Mostra o estado, cada leitura real (com as três linhas chinesas traduzidas para inglês), o local quando indicado, o serviço e o país de destino, descartando nomes, morada, códigos postais, número de volumes, peso, referência de cliente, dados de pagamento, assinante, assinatura e comprovativo de entrega. Como o localizador da SXW não dá data prevista, o Peek também não apresenta nenhuma.

**Etapas escritas com antecedência.** O Peek retém qualquer etapa datada no futuro segundo a hora de Pequim, linhas de rasto simulado (`CO_C_CR`) e etapas gravadas mais de 12 horas antes da hora que exibem (ou mais de 6 horas antes no mesmo segundo de uma dessas etapas): são previsões e não leituras reais. O histórico abre então com «Outros sites podem mostrar mais passos, mas são apenas previsões.» até a encomenda ser entregue ou devolvida, e «Ver» lista essas etapas a cinzento, cada uma com «Previsto para» e o respetivo dia. Estas etapas nunca mudam o estado nem enviam alertas. Se o localizador da SXW não responder ou desconhecer o número, o Peek recorre a serviços de seguimento.

Enquanto mantiveres a encomenda aberta ou, no dia da entrega, tiveres notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 locais no dia em que passa a «Em distribuição» enquanto o localizador direto da SXW responder (após transição para a GOFO Express ou a Swiss Post, o Peek acompanha o ritmo da transportadora final; apenas por serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ativas). Nos outros casos, e após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Podes escolher alertas para «Todos os registos», «Passos importantes» (alfândega de importação como `Start customs clearence`, problemas, distribuição, falhas, recolha, entrega e devoluções — `Customs declaration completed` não gera alerta) ou «Só a entrega» / «Só no dia da entrega». O Peek não pode contactar a SXW Express nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da SXW Express

### O que é a SXW Express?

É o nome usado pelo Peek para a `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`, `sxwexpress.cn`), um operador logístico transfronteiriço em Shenzhen dedicado a carga marítima e aérea, comboio China-Europa, expresso internacional e entregas porta a porta DDU/DDP.

### Onde posso seguir eu próprio uma encomenda da SXW Express?

Na caixa *Express Tracking* na página inicial de `sxwexpress.cn`: escreve o número e carrega em *Track*. O localizador oficial abre em chinês e tem também uma página em inglês; o logótipo 17TRACK ao lado leva a um serviço externo distinto.

### O que significa «Customs declaration completed» no seguimento da SXW?

Que a declaração aduaneira de exportação na China ficou concluída antes do voo. O Peek mostra-a como «Em trânsito»: trata-se da alfândega chinesa de saída, não da alfândega do teu país, e a encomenda não está retida.

### Que empresa entrega as encomendas da SXW Express no meu país?

O site da SXW não indica uma lista por país. Para os Estados Unidos, o registo pode incluir um número da GOFO Express que o Peek passa a seguir assim que a GOFO lê a encomenda (e para a Suíça, a Swiss Post quando o canal a identifica); nos restantes casos, pergunta ao vendedor qual é a transportadora final e pede o respetivo número.

### Quem devo contactar se uma encomenda da SXW Express desaparecer?

Fala primeiro com o vendedor onde fizeste a compra, pois foi ele quem contratou o transporte e `sxwexpress.cn` não divulga telefone nem e-mail. Se o vendedor não responder, abre uma reclamação no marketplace.

:::sources
- [SXW Express: página inicial](http://www.sxwexpress.cn/) – denominação social, caixa Express Tracking e botão Track, ligação 17TRACK e serviço DDU/DDP
- [SXW Express: About US](http://www.sxwexpress.cn/about.html) – sede em Shenzhen, carga marítima, aérea, ferroviária e expresso, DDU/DDP e entrega local de última milha
- [SXW Express: Products](http://www.sxwexpress.cn/products/default.html) – linhas UPS de Hong Kong, ARAMEX Special Line e pequenos pacotes Europa/Médio Oriente e EUA
- [SXW Express: Contact US](http://www.sxwexpress.cn/contact.html) – página de contacto sem telefone, e-mail nem morada e painel Online Support vazio
- [SXW Express: 会员登录 (acesso de membros)](http://www.sxwexpress.cn:8082/) – nome em chinês da empresa no sistema de seguimento
- [SXW Express: trace (localizador em inglês)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – página em inglês do localizador oficial
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Orientações sobre o direito de 3 € e a taxa (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por artigo
- [Comissão Europeia: Âmbito territorial do IVA e das alfândegas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato SXW, tradução inglesa das linhas chinesas, fusos horários, etapas escritas com antecedência e transição para GOFO e Swiss Post
:::
