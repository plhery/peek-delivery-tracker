---
title: Rastreamento Posti: código JJFI, status e armários
description: Rastreie encomendas dos Correios da Finlândia (Posti): formato JJFI, status em inglês, prazo nos armários, alfândega e atendimento ao cliente.
slug: rastreamento-posti
published: 2026-11-25
updated: 2026-11-25
---

A Posti é a empresa postal e de encomendas da Finlândia. Rastreie um pacote na [página de rastreamento da Posti](https://www.posti.fi/en/tracking) ou no aplicativo OmaPosti usando o código do item (*item ID*), ou cole o código na caixa acima. O número vem do remetente e também aparece no aviso de chegada da Posti no OmaPosti, por SMS ou por e-mail.

## Código de rastreamento Posti: o item ID JJFI

A Posti chama o código de "item ID" (às vezes "shipment ID"). Nas encomendas próprias da Posti ele começa com `JJFI`. O site oferece versão em inglês ("EN" no topo).

| Código (inventado) | O que é |
| --- | --- |
| `JJFI63482917465283914` | Encomenda Posti: `JJFI` seguido de 17 dígitos, 21 caracteres ao todo. |
| `RR482917367FI` | Item registrado ou rastreável postado na Finlândia no formato internacional: duas letras, nove dígitos e `FI`. |
| `RR482917367CN` | Encomenda vinda do exterior mantém o número de origem (este termina em `CN`, da China Post); a Posti acompanha com o mesmo código. |
| `00064382917465283912` | Código de barras SSCC de 20 dígitos; `64` após os três primeiros dígitos é o prefixo da Finlândia. |

Se o remetente informou seu telefone à Posti, o pacote aparece sozinho no OmaPosti; caso contrário, adicione manualmente. O rastreador da Posti só responde ao código exato, nunca por nome ou endereço.

O código de retirada e o código de abertura do armário (6 ou 9 caracteres) não são códigos de rastreamento. Um número iniciado em `JJEE` pertence à [Omniva](carrier:omniva), correios da Estônia, e um terminado em `SE` à PostNord na Suécia. Em dúvida sobre a transportadora? Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Posti em inglês

O rastreamento da Posti exibe os textos em inglês, finlandês ou sueco conforme o idioma da página, geralmente acompanhados de uma linha explicativa. Estes são os que uma encomenda costuma encontrar, aproximadamente na ordem em que passa por eles:

| A Posti mostra | O que significa para você |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | O remetente anunciou a encomenda. A Posti ainda não está com ela. |
| `Item received for transport` | A Posti recebeu o pacote. |
| `Item is in transport in country of origin.` | Postado no exterior. Ainda sem previsão de entrega. |
| `Item arrived in the destination country.` | Chegou à Finlândia e segue como entrega da Posti. |
| `The item is in sorting` | Em centro de triagem. Pacotes passam geralmente por uma ou duas triagens, mais em rotas longas. |
| `Item accepted from transport` | Ainda em trânsito. Não foi entregue, apesar do texto. |
| `Item is routed to an outlet because the locker was full.` | O armário escolhido estava cheio; o pacote foi encaminhado a um posto da Posti. |
| `Item is in delivery transportation.` | No veículo de entrega a caminho de você. O Peek mostra "Saiu para entrega". |
| `Item is ready for a pick up` | Aguardando retirada. O aviso de chegada traz local e código. |
| `We sent the recipient a text message about the item` | A Posti enviou um SMS para você. O pacote não se moveu. |
| `The item has been delivered` | Entregue, inclusive após retirada por você. |
| `Item delivered to the recipient.` | Entregue a você. |

`Item accepted from transport` confunde muita gente. A Posti esclarece que é apenas uma marcação de transporte: a entrega segue em andamento e o pacote ainda não foi entregue a ninguém. Eventos no exterior mostram o local `ULKOMAILLA` ("no exterior", em finlandês) em todos os idiomas.

## Armários Posti, retirada e entregas não realizadas

**Aviso de chegada.** Ao chegar a um Parcel Locker ou ponto de atendimento, a Posti envia os dados e o código de abertura no OmaPosti, por SMS e por e-mail. Retire em até 5 dias. Alguns envios ficam mais tempo, como encomendas vindas do exterior (geralmente 14 dias) ou avisadas por carta (9 dias); confie na data limite informada no aviso.

**Mais prazo.** No OmaPosti, "Extend pickup time" acrescenta até 14 dias a partir da data limite original, por €1,90 segundo a página em inglês da Posti — ou €1,90 por dia segundo a página em finlandês.

**No armário.** Digite o código de abertura (nos armários com teclado: C, o código e OK). Qualquer pessoa com o código pode retirar sem documento. Itens registrados e segurados exigem procuração. Envios sem código de abertura ficam em um posto Posti: leve o item ID e um documento de identidade.

**Por que foi para outro local.** Encomendas grandes demais para o armário, que exigem entrega em mãos ou enviadas com número de telefone errado seguem para um posto Posti.

**Entrega em casa.** Se a Posti não conseguir entregar na porta, leva o pacote ao ponto de retirada mais próximo e avisa no OmaPosti, por SMS ou e-mail. Um Small parcel fica na porta ou na caixa de correio se não houver ninguém. No OmaPosti, só dá para mudar para entrega domiciliar enquanto o pacote ainda está com o remetente.

**Alterações.** Somente o remetente pode mudar o endereço. O atendimento pode transferir o pacote para outro ponto mediante taxa. Pacotes não retirados retornam ao remetente.

## Encomenda Posti atrasada, perdida, danificada ou na alfândega

**Sem atualização.** Antes de um pacote vindo do exterior chegar à Finlândia, a Posti não consegue estimar a entrega. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Extraviada ou atrasada.** Se o rastreamento não mostrar sinal do pacote, contate o atendimento da Posti para abrir um pedido de esclarecimento (remetente ou destinatário podem pedir). Caso não seja encontrado, o remetente solicita a indenização, pois a Posti paga a quem pagou o frete — quase sempre o remetente. Pela lei finlandesa, a loja online responde pelo transporte e costuma enviar reposição. A autoridade de consumo finlandesa (KKV) considera perdido um pacote nacional 14 dias após o prazo combinado, ou 28 dias após a coleta sem prazo combinado.

**Entregue, mas não chegou.** Confira a caixa de correio em caso de Small parcel e depois fale com o atendimento. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

**Danificada.** Avise o motorista no recebimento, tire fotos, preencha o formulário de item danificado da Posti e informe o remetente.

**Alfândega.** Pacotes vindos de fora da UE (ou de territórios fora da área fiscal da UE, como Ilhas Åland ou Ilhas Canárias) podem precisar de desembaraço no site da Alfândega Finlandesa ou no OmaPosti, aguardando até 20 dias no armazém da Posti antes de retornar. Quando há tributos no desembaraço, a Posti cobra taxa de manuseio de €3,10 (€3,30 no posto), paga no próprio site da Posti. Desde 1º de novembro de 2026, a Alfândega Finlandesa também cobra €2 por item de mercadoria em compras online. Para compras abaixo de €150 cujo IVA a loja cobrou no checkout (IOSS), o remetente realiza o desembaraço e você nada paga na chegada; se faltarem os dados de pagamento do imposto e da taxa, a Posti devolve o pacote ao remetente. As tarifas atuais constam na [página aduaneira da Posti](https://www.posti.fi/en/receiving/customs-clearance).

> A Posti nunca envia link para pagar alfândega ou taxa de manuseio. Mensagens falsas podem até incluir um código real: apague sem abrir o link.

## Atendimento e telefone da Posti

Canais listados em posti.fi:

- **Telefone:** 0100 5577, ou +358 100 5577 do exterior, com tarifa local ou móvel. A [página de atendimento da Posti](https://www.posti.fi/en/customer-service) informa os horários.
- **Chat:** pelo aplicativo OmaPosti.
- **Formulários:** pedido de indenização, relato de item danificado e encomenda não entregável (quando a Posti não conseguiu entregar a você nem devolver ao remetente).

A Posti não divulga endereço de e-mail para atendimento.

## Rastreamento Posti com o Peek

O [Peek](/) identifica na hora códigos `JJFI` como Posti. Um link de rastreamento de posti.fi (`/tracking/`, `/seuranta/` ou `/uppfoljning/` seguido do código, mesmo após idioma como `/en/`) seleciona Posti para qualquer código, sem exigir código postal.

Para números postais terminados em `FI`, o Peek consulta primeiro Posti e Chronopost e seleciona Posti assim que ela reconhece o código. Se ainda não constar, exibe "Ainda não encontrada em …. O Peek continua verificando depois que você adicionar." Adicionada a uma conta Peek, é verificada a cada hora por 6 horas e depois em intervalos maiores; sem conta, para após 6 horas salvo se você ativar "Me avise quando ela for encontrada", que mantém uma checagem diária até 30 dias após a adição. Para o código SSCC acima, consulta Posti, Matkahuolto, Swiss Post Cargo, PostNord e NZ Post. Com dígito verificador incorreto, escolha Posti em "Trocar". Um código estrangeiro pode ser seguido na Posti via "Trocar" ou link de posti.fi; caso contrário, o Peek acompanha primeiro nos correios de origem e, quando estes indicam Finlândia como destino (ou sem destino enquanto você acompanha da Finlândia), consulta também a Posti e migra para ela assim que surgir evento ao menos tão recente quanto o último da origem, preservando o histórico anterior.

Na Estônia, Letônia e Lituânia, a Posti opera como SmartPosti: a busca do Peek encontra Posti por esse nome ("Também chamada de SmartPosti"), e links de smartposti.ee ou itella.ee (ou versões .lv e .lt) identificam Posti para qualquer código. O Peek só aceita a resposta da Posti quando exatamente um envio corresponde ao código exato digitado, portanto certos códigos SmartPosti podem não retornar nada; nesses casos, consulte em smartposti.ee.

O Peek lê a Posti em inglês e mostra cada status com a linha explicativa da Posti, cidade de cada evento, peso, dimensões e nome e endereço do ponto de retirada. Enquanto o pacote está aberto, ou no dia da entrega com alertas ligados, verifica até a cada 10 minutos (a cada 2 minutos a partir das 6h locais no dia em que a Posti registra saída para entrega, enquanto o rastreador da própria Posti responde); nos demais horários, a cada hora. Marca cada etapa no mapa e pode avisar você das mudanças. Quando outra transportadora assume a entrega, segue o ritmo dela. Os horários aparecem no fuso do seu aparelho; em peektracker.com e nos apps iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. O código de retirada e seu endereço são omitidos, e não há previsão de entrega nem remetente. Códigos que agrupam vários pacotes não são rastreados, e pacotes antigos aparecem como não encontrados.

## Perguntas frequentes sobre o rastreamento Posti

### Como rastrear uma encomenda Posti em inglês?

Acesse posti.fi/en/tracking e digite o item ID; todos os status aparecerão em inglês.

### Por quanto tempo a Posti guarda um pacote no armário?

A Posti pede retirada em até 5 dias; o aviso informa a data exata. No OmaPosti é possível prorrogar por até 14 dias por €1,90 (€1,90 por dia, segundo a página em finlandês).

### Quanto tempo a Posti leva para entregar?

Um Small parcel leva de 2 a 5 dias úteis após o recebimento pela Posti. Nos demais casos, o rastreamento informa uma estimativa de entrega.

### O atendimento da Posti tem chat?

Sim, dentro do aplicativo OmaPosti.

:::sources
- [Posti: Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – código JJFI, avisos, prazos de guarda (14 dias do exterior, 9 dias por carta), prorrogação, armários, códigos, procuração, devoluções, telefone e chat
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – em finlandês: prorrogação por €1,90 ao dia
- [Posti: Track item](https://www.posti.fi/en/tracking) – textos de status, linhas explicativas, ULKOMAILLA e idiomas
- [Posti: Home delivery](https://www.posti.fi/en/receiving/home-delivery) – ausência, Small parcel, prazos e entrega não recebida
- [Posti: Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – taxa de manuseio, Alfândega Finlandesa, taxa de €2 desde 1º de novembro de 2026, desembaraço IOSS pelo remetente e devolução sem dados de pagamento
- [Posti: Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 20 dias no armazém e OmaPosti
- [Posti: What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – pedido de esclarecimento, indenização e passos em caso de dano
- [Posti: Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – formulário de indenização
- [Posti: Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – formulário de dano e aviso ao remetente
- [Posti: Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – formulário de encomenda não entregável
- [Posti: Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – alertas de golpes e ausência de links de pagamento
- [Posti: Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – rastreamento por código exato e tarifas telefônicas
- [Posti: Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – prazo de guarda de cinco dias
- [Posti: Customer Service](https://www.posti.fi/en/customer-service) – página de atendimento ao cliente
- [SmartPosti: Meist (About us)](https://www.smartposti.ee/ariklient/meist) – integrante do Grupo Posti e marca SmartPosti na Letônia e Lituânia
- [SmartPosti: Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – página de rastreamento da SmartPosti
- [KKV: Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – quando um pacote conta como perdido
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e mostra
:::
