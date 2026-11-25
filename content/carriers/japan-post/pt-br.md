---
title: Rastreamento Japan Post: códigos EMS e status explicados
description: Rastreie um pacote ou EMS da Japan Post vindo do Japão: formato do código JP, significado dos status, quem entrega no seu país, alfândega e a quem perguntar.
slug: rastreamento-japan-post
published: 2026-11-24
updated: 2026-11-24
---

A Japan Post (日本郵便, Japan Post Co., Ltd.) é a empresa postal do Japão, controlada pela Japan Post Holdings, com 24.115 agências dos correios em 31 de março de 2026. Ela envia EMS, International Air Packets e encomendas para o exterior, além de pacotes Yu-Pack dentro do Japão. Rastreie no [Track & Trace Service da Japan Post](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en), até dez números por vez, ou cole o código na caixa acima. Comprou em uma loja japonesa? Peça o código ao vendedor.

## Código de rastreamento Japan Post: como reconhecer

Um envio internacional do Japão tem 13 caracteres: duas letras, nove dígitos e `JP`. O último dos nove dígitos é um dígito verificador. Com dígitos inventados:

| Formato (inventado) | O que é |
| --- | --- |
| `EN482917367JP` | Um EMS, International Air Packet, encomenda internacional ou carta registrada enviada do Japão. |
| `482917364823` | 12 dígitos: item enviado dentro do Japão, como um pacote Yu-Pack. |
| `48291736485` | 11 dígitos: também doméstico, comprimento mostrado pelo rastreador da Japan Post para cartas e pacotes. |

O rastreador da Japan Post aceita apenas esse formato de 13 caracteres para envios internacionais, não códigos como `CNG` ou `LP` seguidos de 14 dígitos. Um código de barras iniciado por `U` em um pequeno pacote ou em sua declaração aduaneira (CN22) também não é número de rastreamento: esse envio viaja sem rastreamento. Um código terminado em outras letras, como `US`, pertence aos correios de outro país. Sem código? Veja [como encontrar um código de rastreamento](guide:find-tracking-number).

## Status do rastreamento Japan Post explicados

O rastreador em inglês da Japan Post lista primeiro o evento mais antigo, com data, hora, agência e província ou país. Os textos que o Peek reconhece, aproximadamente em ordem:

| A Japan Post mostra | O que significa para você |
| --- | --- |
| `Posting/Collection` | O remetente postou ou a Japan Post coletou o pacote. |
| `En route` | Em trânsito entre unidades da Japan Post. |
| `Arrival at outward office of exchange` | Na unidade responsável por despachar correspondências para o exterior. |
| `Dispatch from outward office of exchange` | Saiu do Japão (ou do país de origem, em envios chegando ao Japão). |
| `Arrival at inward office of exchange` | Na unidade internacional de entrada no país de destino. |
| `Item presented to import Customs`, `In Customs` | Na alfândega do país de destino. |
| `Item held at inward Office of Exchange` | Retido na unidade internacional de entrada lá. |
| `Item returned from import Customs` | A alfândega devolveu aos correios: voltou a circular. |
| `Departure from inward office of exchange` | A caminho da entrega. |
| `Item out of sorting centre` | Saiu de um centro de triagem no país de destino. |
| `Arrival`, `Processing at delivery Post Office` | Na agência postal responsável pela entrega. |
| `Item held at delivery depot/delivery office` | Está na agência de entrega; o texto não diz que você pode retirar. |
| `Item out for physical delivery` | Saiu para entrega. |
| `Final delivery` | Entregue. |
| `Final delivery - Collected at counter` | Retirado no balcão de uma agência no Japão. |
| `Retention` | Guardado em uma agência no Japão após entrega não realizada, para retirada ou até uma data escolhida por você; o texto não diferencia qual. |
| `Returned to sender` | Voltando ao remetente. |

Envios entrando no Japão têm mais três linhas. `Transported in bond` indica transporte ainda sob controle aduaneiro até a unidade internacional que desembaraça as remessas para o seu endereço; `Arrived in bond` significa que chegou lá. O desembaraço segue em andamento até surgir `Dispatched from Exchange Office`. Mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Encomendas da Japan Post no exterior: quem entrega e alfândega

Depois de `Dispatch from outward office of exchange`, os correios do seu país assumem. Para EMS, a Japan Post indica acompanhar no site desses correios, onde, segundo ela, o rastreamento é mais rápido — como [USPS](carrier:usps), Parcelforce no Reino Unido, An Post, Canada Post, [Australia Post](carrier:australia-post) e NZ Post — usando o mesmo número terminado em `JP`. Tentativas e retirada passam a seguir as regras dos correios locais: veja [entrega não realizada](guide:missed-delivery).

**Mostrado como entregue.** Um International Air Packet vai para a caixa de correio sem assinatura (salvo se houver imposto a pagar) e aparece como entregue. A Japan Post avisa que envios EMS para o Reino Unido ou a França cuja entrega falhou também podem aparecer como entregues enquanto aguardam na agência mais próxima, e EMS para a Alemanha podem aparecer como devolvidos ou entregues enquanto estão com despachante ou outra transportadora. Como a entrega é feita pelos correios locais, consulte-os primeiro: veja [entregue, mas não recebido](guide:delivered-not-received).

**Estados Unidos.** Desde 15 de outubro de 2026, a Japan Post voltou a aceitar correspondências para os EUA em praticamente todas as agências. Mercadorias de até USD 2.500 precisam primeiro ser registradas na Zonos ou na SafePackage, provedoras recomendadas pela Japan Post, com tarifas e impostos pré-pagos; a etiqueta passa a trazer DDP e um número de declaração de 13 dígitos. Até 19 de janeiro de 2027, presentes entre pessoas físicas de até USD 100 ficam dispensados disso. Acima de USD 2.500, o importador paga nos EUA.

**Alfândega na UE.** O Japão fica fora da UE, portanto o pacote passa pela alfândega do seu país. Desde 1º de julho de 2026, compras online vindas de fora da UE em pedidos de até € 150 pagam imposto aduaneiro da UE de € 3 por item. Desde 1º de novembro de 2026, aplica-se também a taxa de manuseio da União de € 2 por item, qualquer que seja o valor. Um item é o conjunto de mercadorias no pacote que compartilham classificação tarifária, descrição e, quando exigido, origem. Nos demais países valem as regras locais: veja [encomendas paradas na alfândega](guide:customs).

## Recebendo um pacote da Japan Post no Japão

**Alfândega.** Remessas do exterior são desembaraçadas em uma das seis unidades internacionais de troca no Japão. Quando há imposto, você paga na entrega ou na agência usando o aviso de cobrança entregue pela Japan Post. Itens acima de ¥ 200.000 exigem declaração de importação.

**Entrega não realizada.** Uma encomenda do exterior aguarda na agência de entrega por 15 dias — ou um mês se houver imposto a pagar — para retirada ou reagendamento. O formulário em inglês da Japan Post aceita o número de 6 ou de 11 a 13 dígitos do aviso e reentrega apenas em casa; para outro local, ligue 0570-046-111. Veja [encomendas não retiradas](guide:uncollected-parcel).

## Rastreamento Japan Post parado, extraviado ou devolvido

**Sem movimento após sair do Japão.** A partir daí os eventos vêm dos correios do seu país. A Japan Post alerta que dados do exterior podem chegar incompletos ou atrasados, e que as linhas podem aparecer fora de ordem por causa da diferença de fuso horário. Consulte o rastreamento dos seus correios com o mesmo número: veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Sem rastreamento nenhum.** Small Packets não têm rastreamento, e desde 31 de dezembro de 2025 a Japan Post não aceita mais pequenos pacotes de mercadorias como correspondência registrada. Pequenos pacotes rastreados seguem como International Air Packet. Envios por superfície só têm rastreamento como encomenda internacional.

**Extraviado ou avariado.** O remetente pede investigação em formulário na agência onde postou (não por telefone): em até 6 meses para carta registrada, envio com seguro e encomenda internacional; no EMS, o país de destino define o prazo. A indenização cobre cartas registradas e com seguro, encomendas internacionais e EMS (cobertura gratuita até ¥ 20.000, segurável até ¥ 2 milhões); o International Air Packet não tem cobertura. Em compras online, fale com a loja: veja [encomendas extraviadas](guide:lost-parcel). Se chegar avariado ou com itens faltando, guarde tudo e comunique à agência local: veja [encomendas avariadas](guide:damaged-parcel).

**Devolvido.** Um lançamento errado no exterior pode deixar `Returned to sender` visível mesmo após a entrega, alerta a Japan Post; nesse caso, a linha mostra o país de destino e não uma província japonesa. A Japan Post também pede aos remetentes que não postem pedidos de lojas IOSS abaixo de € 150 para Áustria, Bélgica, Dinamarca, Finlândia, França, Alemanha, Luxemburgo ou Portugal, pois alfândegas ou correios desses países podem devolvê-los. Veja [devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da Japan Post

Linhas da Japan Post (não aceitam ligações do exterior):

- **Central de Atendimento:** 0120-5931-55 (gratuito) ou 0570-046-666 de celular (tarifado).
- **Em inglês:** 0570-046-111 (tarifado), também para reentrega.
- **Horário:** todos os dias, das 8h às 21h (horário do Japão).

De fora do Japão, peça ao remetente ou à loja para solicitar investigação na agência de postagem. A Japan Post informa que não consegue responder dúvidas de rastreamento além do que o site mostra.

## Como rastrear um pacote da Japan Post no Peek

O [Peek](/) identifica duas letras, nove dígitos e `JP` como Japan Post quando o dígito verificador está correto e mostra "Transportadora detectada"; "Trocar" escolhe outra. Se o dígito verificador falhar, exibe "O dígito verificador deste código não confere.": confira a mensagem de envio ou escolha "Rastrear como digitado". Links do rastreador da Japan Post também funcionam. Códigos iniciados por `U` não são reconhecidos como da Japan Post e o Peek não os consulta na Japan Post, pois a Japan Post não rastreia números `U`.

Um código doméstico de 12 dígitos não identifica a Japan Post sozinho. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Ele consulta as que usam esse formato — Yamato e Japan Post primeiro se você estiver no Japão — e mostra "Japan Post está com esta encomenda." quando a Japan Post reconhece o código. Para códigos de 11 dígitos, o Peek não consulta a Japan Post: selecione-a em "Trocar". Cadastrado na Japan Post, o pacote mostra "Aguardando Japan Post" até a primeira leitura, ou "Confira o código de rastreamento" se ela ainda não reconhecer o código após dois dias.

O Peek exibe cada leitura no texto em inglês da Japan Post, com agência e local, e classifica os termos da tabela acima em etapas próprias como "Em trânsito" ou "Na alfândega". Ele omite a coluna Details da Japan Post, que pode trazer motivos como endereço desconhecido. Cada horário é lido no relógio local do lugar indicado ou dos eventos vizinhos. Em envios domésticos com dia de entrega escolhido pelo remetente, o Peek exibe esse dia e a faixa de horário como previsão até a entrega ou o fim do dia.

Em muitos países, o Peek também consulta os correios nacionais com o mesmo número `JP`: USPS nos EUA, [Royal Mail](carrier:royal-mail) no Reino Unido (ou Parcelforce, indicada pela Japan Post para EMS, em códigos iniciados por `E`), An Post na Irlanda, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) na Alemanha, [La Poste](carrier:la-poste) na França e os correios de todos os outros países da UE exceto a Holanda. Ele migra para esses correios assim que surgem novas leituras e preserva o histórico da Japan Post. Quando você rastreia da Coreia, da China, de Hong Kong ou de Singapura, o Peek não consulta outros correios. No sentido inverso, quando o rastreamento de outros correios indica o Japão como destino — ou não indica destino e você rastreia do Japão —, o Peek consulta a Japan Post com aquele código, exceto se começar por `U`.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica a Japan Post até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário da última leitura (horário do Japão no Japão; havendo faixa de horário escolhida pelo remetente, meia hora antes de abrir) no dia da saída para entrega, enquanto o rastreador da Japan Post responder; quando os correios do seu país assumem, o Peek segue o ritmo deles. Nos demais casos, e após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Por serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a Japan Post, os correios locais nem a loja por você.

## Perguntas sobre o rastreamento Japan Post

### O que é o EMS da Japan Post?

É o serviço internacional mais rápido da Japan Post, para documentos e mercadorias de até 30 kg para mais de 120 países e territórios, com rastreamento. Seus códigos terminam em `JP`.

### Por que meu rastreamento da Japan Post não atualiza?

Se a última linha for `Dispatch from outward office of exchange`, o pacote já saiu do Japão: consulte o rastreamento dos correios do seu país com o mesmo número e aguarde o voo e a alfândega.

### Posso rastrear um pequeno pacote da Japan Post?

Somente se foi enviado como International Air Packet. Um Small Packet não tem rastreamento, e um código de barras iniciado por `U` não é número de rastreamento. Confirme com o remetente qual serviço foi usado.

### Quanto tempo a Japan Post leva para entregar?

Cerca de 5 a 21 dias no International Air Packet, informa a Japan Post. O EMS é o serviço mais rápido; a ferramenta de tarifas e prazos em post.japanpost.jp calcula o tempo para cada destino.

:::sources
- [Japan Post: Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) – rastreador e exemplos de código
- [Japan Post: Item numbers on labels](https://trackings.post.japanpost.jp/slipEn.html) – código de 12 dígitos do Yu-Pack
- [Japan Post: Notices Concerning EMS Track & Trace Service](https://www.post.japanpost.jp/service/send/oversea/information/ems_search_en.html) – números, ordem das leituras, eventos no exterior, coluna Details, devoluções e linhas em trânsito aduaneiro
- [Japan Post: Link to Overseas Post Offices](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/link_en.html) – correios parceiros para EMS
- [Japan Post: Products and services comparative table](https://www.post.japanpost.jp/service/send/oversea/list/hikaku_en.html) – rastreamento por serviço
- [Japan Post: International Air Packet](https://www.post.japanpost.jp/service/send/oversea/list/delivery/airpacket_en.html) – entrega na caixa de correio e prazo
- [Japan Post: EMS](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/index_en.html) – serviço e ferramenta de prazos
- [Japan Post: EMS Insurance Program](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/damage_en.html) – cobertura e seguro
- [Japan Post: Mail destined for the United States, 23 October 2026](https://www.post.japanpost.jp/service/send/oversea/information/2026/1023_01_en.html) – regras para os EUA
- [Japan Post: EU duty-free exemption removal, 1 July 2026](https://www.post.japanpost.jp/service/send/oversea/information/2026/0701_01_en.html) – pedidos IOSS
- [Japan Post: Searches for undelivered items](https://www.post.japanpost.jp/question/oversea/18_en.html) – pedidos de investigação
- [Japan Post: Searches for EMS](https://www.post.japanpost.jp/question/oversea/138_en.html) – investigações de EMS
- [Japan Post: Compensation](https://www.post.japanpost.jp/question/oversea/21_en.html) – envios com cobertura
- [Japan Post: Compensation claims](https://www.post.japanpost.jp/question/oversea/17_en.html) – avaria
- [Japan Post: Holding period for parcels from overseas](https://www.post.japanpost.jp/question/oversea/23_en.html) – prazo de guarda
- [Japan Post: Delivery Flow](https://www.post.japanpost.jp/service/send/oversea/use/flow/receive_en.html) – alfândega no Japão
- [Japan Post: Application for redelivery](https://www.post.japanpost.jp/receive/redelivery_form/) – reentrega
- [Japan Post: Yu-Pack](https://www.post.japanpost.jp/service/you_pack/index_en.html) – dia de entrega escolhido
- [Japan Post: Inquiry by Phone](https://www.post.japanpost.jp/question/oversea/tel_en.html) – telefones e horários
- [Japan Post: Corporate Information](https://www.post.japanpost.jp/about/profile_en.html) – empresa e agências
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto de € 3 e taxa de manuseio de € 2
- [Comissão Europeia: Orientação sobre o imposto de € 3 e a taxa de manuseio da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – por item e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – textos dos eventos, o que o Peek lê e quais correios ele consulta em seguida
:::
