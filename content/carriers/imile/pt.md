---
title: iMile rastreio: números, estados e entrega em Portugal
description: Segue uma encomenda da iMile em Portugal: como é o número, o que significam os estados em inglês, como funciona a entrega e como contactar a iMile.
slug: imile-rastreio
published: 2026-11-16
updated: 2026-11-17
---

A iMile é uma empresa de entregas para comércio eletrónico fundada no Dubai em 2017. Faz transporte internacional de encomendas, nomeadamente a partir da China, trata do desalfandegamento e assegura a última milha com estafetas próprios em alguns mercados. Consulta o teu código na [página de rastreio da iMile](https://www.imile.com/en/track) ou cola-o na caixa acima. O número vem da loja, nos dados de envio da encomenda.

## Número de rastreio iMile: como é

A iMile não publica nenhum código de exemplo. Estes são os formatos usados em encomendas da iMile, com algarismos inventados:

| Número | A quem pertence |
| --- | --- |
| `IM06010000000000` | `IM`, `0601` e dez algarismos: iMile. |
| `IM00000000000000` | `IM` e 14 algarismos: também iMile. |
| `6010125000000` | 13 algarismos começados por `6`, seguidos de seis algarismos com forma de data (mês, dia, ano): número de carta de porte próprio da iMile. |
| `49000000000000` | 14 algarismos começados por `49`: formato visto em encomendas da iMile em Itália, partilhado a 14 algarismos com transportadoras como [DPD Portugal](carrier:dpd-pt), [Chronopost](carrier:chronopost) ou [SEUR](carrier:seur). O formato, por si só, não chega, mas o Peek consulta a iMile entre as primeiras. |
| `3000000000000` | 13 algarismos começados por `3`: outra série aceite pelo seguimento da iMile, partilhada com outras transportadoras de 13 algarismos. O formato, por si só, não chega: o Peek consulta a iMile juntamente com elas. |
| `2000000000000` | Outros números de 13 algarismos: não correspondem aos formatos da iMile. São usados por empresas como Delhivery, Yunda ou Korea Post. |
| `LP00000000000000` | Não é da iMile, mas da [Cainiao](carrier:cainiao), a referência logística da AliExpress. |

Na página de rastreio da iMile, introduz o código em «TYPE YOUR ORDER NUMBER»; o histórico aparece em «ORDER STATUS». Quando a iMile não conhece o número, essa área fica vazia, sem mensagem de erro. Se não encontrares nenhum código, vê [onde encontrar o número de rastreio](guide:find-tracking-number) e pergunta depois à loja.

## Estados do rastreio iMile: o que quer dizer cada um

As leituras da iMile aparecem em inglês, tal como o Peek as lê. Os nomes entre parênteses `【】` indicam centros da iMile ou códigos de aeroporto, pelo que na tua encomenda vais ver outros locais. Eis as linhas mais habituais, pela ordem do percurso:

| A iMile escreve | O que significa para ti |
| --- | --- |
| `Order submitted, pending to receive.` | O vendedor criou o envio. A iMile ainda não tem a encomenda. |
| `Your order has been picked up.` | A iMile já recolheu a encomenda junto do remetente. |
| `【IST】The flight has taken off.` | Seguiu de avião; o código identifica o aeroporto de partida. |
| `【RUH】Flight Arrived - Waiting for customs clearance.` | O avião aterrou e a encomenda aguarda desalfandegamento. |
| `Clearance Processing` | A alfândega está a verificar a remessa. |
| `【Milan center】Your order has been received.` | Deu entrada no primeiro centro da iMile no país de destino. |
| `Your order has been shipped from 【Milan center】.` | Saiu rumo ao centro seguinte ou à estação de distribuição. |
| `Parcel is currently being processed.` | Está na estação que faz a entrega na tua zona. |
| `Our delivery associate is out for delivery.` | Saiu hoje para distribuição. |
| `User not at home` | A tentativa falhou: é o motivo registado pelo estafeta. |
| `Delivery attempt finished,return to Local logistics company warehouse.` | Depois da tentativa falhada, a encomenda regressa ao armazém local. |
| `The delivery method has been confirmed with the recipient as Self-Pickup.` | Após uma tentativa falhada, ficou combinado ires levantar a encomenda. Ainda não saiu do sítio. |
| `The parcel has been delivered to …. Please collect your parcel promptly at … using your pickup code.` | Está à tua espera no ponto de recolha indicado na linha, na morada referida: levanta-a com o teu código. Aqui, «Delivered» significa entregue nesse ponto e não a ti; o Peek mostra «Pronta para levantamento». |
| `【Contact Error】need to return.` | O estafeta não conseguiu contactar-te e inicia-se a devolução. |
| `Your order has been delivered successfully.` | Entregue. |
| `Order delivery failed, returning to sender.` | Está a voltar para o remetente. |
| `【Milan Transit Center】Shipment returned to origin.` | Devolvida ao remetente. |

Em encomendas europeias, a primeira linha surge como `Order Submited.`: a gralha é da própria iMile, tal como em `Your order has assigned delivery drvier.`, que indica o motorista atribuído antes de a carrinha sair. `Shipment is scheduled to` seguido de uma data entre parênteses retos marca um dia previsto de entrega. Já `Scheduled Delivery`, entre uma volta de distribuição e o fim da tentativa ou a volta seguinte, volta a agendar a entrega sem indicar dia: a encomenda não se mexe e o Peek mantém o estado anterior.

Outros motivos que o estafeta pode anotar numa tentativa falhada, além de `User not at home`: `wrong address` (ou `Wrong Address(Insufficient address details)`, quando faltam dados na morada), `switch off`, `Customer Wants to Change Location`, `Route not completed`, `Traffic, Strike, Bad weather`, uma linha iniciada por `failed to schedule due to` com o motivo entre parênteses retos como `noanswer` (não foi possível combinar a entrega contigo), e `The driver failed to deliver due to shop(building) closure, we will try to deliver again.` Também pode surgir `Lost order` durante uma volta de distribuição; por si só não confirma que a encomenda se perdeu, por isso acompanha as leituras seguintes. Para paragens aduaneiras, consulta [encomendas retidas na alfândega](guide:customs).

## Entrega da iMile em Portugal

**Quem entrega.** Portugal não consta na lista dos 30 países com escritório da iMile (a versão em português do site da iMile destina-se ao Brasil), nem nas rotas do serviço expresso nacional (Emirados Árabes Unidos, Arábia Saudita, México, Brasil e Malásia) ou do envio internacional direto a partir da China com equipa própria de última milha (Emirados Árabes Unidos, Arábia Saudita, México, Omã, Kuwait, Catar, Barém, Líbano, Austrália, Jordânia, África do Sul e Malásia). Para Portugal, a iMile não publica sociedade local, telefone, email, regras de distribuição nem transportadora parceira. Pergunta à loja que empresa faz a entrega final e continua a acompanhar a página de rastreio da iMile.

**Tentativas, pontos de recolha e comprovativo de entrega.** A iMile não publica regras para Portugal sobre número de tentativas, avisos de passagem ou prazo de recolha. O que aconteceu vê-se nas próprias leituras: o motivo do estafeta, o regresso à estação, uma nova saída para distribuição, `【Contact Error】need to return.` ou, se combinaste o levantamento, a linha com o nome e a morada do ponto onde a encomenda aguarda. Na página inicial, a iMile refere pontos PUDO (recolha e entrega) entre os seus serviços, mas não lista nenhum em Portugal. A FAQ geral da iMile, escrita sobretudo para os Emirados, indica:

- que a linha de apoio ao cliente recebe pedidos de reagendamento (*rescheduling requests*);
- que, para alterar a morada, deves contactar o apoio ao cliente antes de o envio chegar à fase final de entrega, ou usar o *iMile Online Bot* (vê também [alterar a morada de entrega](guide:change-delivery-address));
- que o comprovativo digital de entrega inclui uma fotografia da encomenda no local de entrega e, quando aplicável, a assinatura digital do destinatário.

A página de rastreio da iMile tem uma vista de comprovativo de entrega que pede para confirmares que és o utilizador autorizado. Nalguns países, a leitura de saída para distribuição na página da iMile acrescenta o telefone do motorista ou da linha de apoio da iMile. Mais informação em [entrega falhada](guide:missed-delivery).

## Encomenda iMile parada, perdida ou devolvida

**Ainda sem seguimento.** Enquanto só aparecer `Order submitted, pending to receive.`, o vendedor registou o envio mas pode ainda não ter entregue a encomenda à iMile. Pergunta à loja quando foi expedida. Se escolheste a iMile em «Alterar» para um código que a iMile ainda não reconhece, o Peek mostra no peektracker.com «À espera de iMile»; se ao fim de dois dias a iMile continuar sem reconhecer esse número e nenhuma outra fonte responder por ele, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificá-la uma vez por dia, e uma vez por semana após sete dias.

**Sem novidades durante vários dias.** As encomendas vindas da China ficam muitas vezes sem atualizações entre o voo, a alfândega e o primeiro centro no país de chegada. Lê mais em [rastrear uma encomenda vinda da China](guide:tracking-from-china) e [porque é que o rastreio não atualiza](guide:tracking-not-updating).

**Alfândega e taxas.** Segundo a Autoridade Tributária e Aduaneira (AT), desde 1 de julho de 2026 terminou na União Europeia a franquia aduaneira até 150 €: além do IVA, as compras online enviadas de fora da UE com valor intrínseco até 150 € pagam direitos aduaneiros fixos de 3 € por adição (categoria pautal de artigo). Desde 1 de novembro de 2026 soma-se ainda uma taxa de tratamento da União de 2 € por adição, contada do mesmo modo em vendas à distância importadas de países terceiros qualquer que seja o valor e devida no momento da aceitação da declaração aduaneira. A iMile não explica como são cobrados direitos, IVA e taxa nas suas remessas: confirma com a loja se já estavam incluídos no preço. Mais detalhes em [encomendas retidas na alfândega](guide:customs).

**O link de rastreio não abre.** A FAQ da iMile recomenda atualizar a página ou abri-la noutro navegador, limpar a cache e os cookies e, se continuar sem funcionar, pedir um novo link ao apoio ao cliente indicando o número da encomenda.

**Dada como entregue mas não está lá.** Vê a fotografia do comprovativo de entrega na página da iMile, procura à porta e pergunta aos vizinhos; depois contacta a iMile e a loja. Consulta [entregue mas não recebida](guide:delivered-not-received).

**Perdida, danificada ou devolvida.** A iMile não publica um processo de reclamação ou indemnização para destinatários em Portugal. Como o contrato de transporte é entre a iMile e a loja, pede o reembolso ou um novo envio ao vendedor. Vê [encomenda perdida](guide:lost-parcel) e, após `Order delivery failed, returning to sender.`, [devolvida ao remetente](guide:returned-to-sender).

## Contactos e apoio ao cliente da iMile

Consultados a 16 de novembro de 2026, estes são os canais disponíveis na iMile:

- **Portugal**: não há telefone nem email próprios na lista de escritórios da iMile.
- **iMile Chat**: o botão no canto inferior direito de imile.com abre a assistente virtual Alison, que responde em português, além de inglês, espanhol, italiano, alemão, húngaro e árabe.
- **Formulário de apoio ao cliente**: disponível em imile.com para dúvidas sobre envios, embora peça como campos obrigatórios o nome da empresa (*Company Name*) e o número de encomendas diárias (*Number of Daily Orders*).

Para resolver questões da própria compra, falar com a loja costuma ser mais rápido: foi ela que contratou a iMile e pode abrir um pedido junto da transportadora.

## Seguir uma encomenda da iMile com o Peek

O [Peek](/) reconhece de imediato os números `IM0601…` como sendo da iMile. Nos restantes códigos `IM` de 14 algarismos, nos de 14 algarismos começados por `49` e nos de 13 algarismos começados por `6` ou `3`, que outras transportadoras também usam, o formato por si só não chega: cola o código na mesma, porque o Peek acompanha mais de 3.500 transportadoras. Ao adicionares a encomenda, o Peek mostra «A verificar serviços de seguimento…», consulta as transportadoras compatíveis — com a iMile entre as primeiras nos códigos começados por `49` e nos começados por `6` com forma de data — e indica «iMile tem esta encomenda.» assim que a iMile responde. Se ainda nenhuma responder ou aparecer outra empresa, escolhe a iMile em «Alterar». Os links da página de seguimento em imile.com (`/track?waybillNo=`) também são lidos como iMile.

O Peek consulta o seguimento da iMile em inglês. Mostra o estado, o histórico com o centro ou a localidade de cada leitura num mapa e o país de destino, lendo as horas pelo relógio desse país e convertendo-as para o fuso horário do teu dispositivo (no peektracker.com e na app para iPhone, uma linha por baixo do histórico indica-o: «Horas no teu fuso horário: …»). Quando uma encomenda sai da China pela [Cainiao](carrier:cainiao) ao serviço da iMile, o Peek acrescenta antes do histórico da iMile as leituras anteriores da Cainiao: recolha, triagem, exportação, voo e importação.

O Peek apresenta como entrega prevista o dia marcado na linha `Shipment is scheduled to` mais recente, até haver uma tentativa falhada, um problema, chegada a um ponto de recolha, entrega, devolução ou uma leitura posterior a essa data. Deixa de fora o voo programado, o comprovativo de entrega, quem assinou, contactos e dados de pagamento, e limpa das leituras o telefone do motorista e o da linha de apoio da iMile. Etiquetas como «Em distribuição» ou «Tentativa de entrega» são do Peek; o texto de cada leitura aparece como a iMile o escreveu. Enquanto o próprio seguimento da iMile responder, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos no dia em que a encomenda sai para distribuição (a partir das 6h00, hora local); através de serviços de seguimento, de hora a hora, e até a cada 15 minutos nesse dia. Ao fim de dois dias sem novidades (ou 12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), passa a consultar de hora a hora. Podes receber alertas em «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a iMile nem a loja por ti.

## Perguntas sobre o rastreio iMile

### Onde está a minha encomenda da iMile?

A última leitura indica em que fase vai, e o nome entre parênteses `【】` mostra o centro ou aeroporto. `Our delivery associate is out for delivery.` quer dizer que saiu para entrega hoje; se a seguir aparecer um motivo do estafeta, a tentativa falhou.

### Quem entrega as encomendas da iMile em Portugal?

As páginas da iMile não o dizem: Portugal não aparece na lista de escritórios e a empresa não indica contacto nem parceiro local no país. Pergunta à loja que transportadora faz a entrega final e acompanha as leituras no rastreio da iMile.

### O que significa «Order Submited» na iMile?

Significa que o vendedor criou o pedido de envio, mas a iMile ainda não recebeu a encomenda. A gralha é da própria iMile; assim que a encomenda é recolhida, surge `Your order has been picked up.`

### A iMile é de confiança?

Sim. A iMile é uma transportadora criada no Dubai em 2017, com página de rastreio própria e escritórios em vários países europeus, como Espanha, Alemanha, Áustria, Itália e Polónia. Se a encomenda ficar parada, fala com a loja e com o apoio da iMile.

:::sources
- [iMile: Página inicial (em inglês)](https://www.imile.com/en) – fundada no Dubai em 2017, entregas transfronteiriças e desalfandegamento, pontos PUDO
- [iMile: Track Your Shipment (em inglês)](https://www.imile.com/en/track) – página de rastreio, campos TYPE YOUR ORDER NUMBER e ORDER STATUS, resultado vazio sem erro, vista do comprovativo de entrega
- [iMile: FAQs (em inglês)](https://www.imile.com/en/CustomerService/FAQ) – seguimento online, linha de apoio para reagendamento, alteração de morada e Online Bot, comprovativo digital de entrega, link de seguimento que não funciona
- [iMile: Domestic Express Delivery (em inglês)](https://www.imile.com/en/logistics-solutions/domestic-express-delivery) – países abrangidos pelo serviço expresso nacional
- [iMile: International Dropship Delivery (em inglês)](https://www.imile.com/en/logistics-solutions/international-dropship-delivery) – equipa própria de última milha e rotas a partir da China
- [iMile: Office Locations (em inglês)](https://www.imile.com/en/CustomerService/OfficeLocations) – lista dos 30 países com escritório (sem Portugal)
- [iMile: Customer Support (em inglês)](https://www.imile.com/en/CustomerService/CustomerSupport) – formulário de contacto e campos obrigatórios (empresa e encomendas diárias)
- [iMile: iMile Chat](https://help.imile.com/) – assistente virtual Alison disponível em português
- [Portal das Finanças (AT): Mercadorias eCommerce](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – fim da franquia até 150 € e direitos aduaneiros de 3 € por adição desde 1 de julho de 2026
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de tratamento da União de 2 € a partir de 1 de novembro de 2026
- [Comissão Europeia: Customs Guidance on EUR 3 customs duty, PDF (em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € por adição contada como nos 3 €, qualquer que seja o valor, devida na aceitação da declaração aduaneira
- [Universal Parcel Scraper: iMile (em inglês)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/imile) – formatos de número (incluindo 49 + 12 algarismos e 3 + 12 algarismos), estados, etapas da Cainiao e o que o Peek mostra
:::
