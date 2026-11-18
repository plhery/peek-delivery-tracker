---
title: Seguimento Straightship: números STRTD e entregas
description: Segue uma encomenda da Straightship no Canadá: formatos STRTD, STRCA e STSH, significado de cada estado, alfândega CBSA, pedidos SHEIN e apoio ao cliente.
slug: seguimento-straightship
published: 2026-11-18
updated: 2026-11-18
---

A Straightship (Straightship Solutions Limited) é uma empresa de logística sediada em Burnaby, na Colúmbia Britânica (**Canadá**), que transporta mercadorias da Ásia para a América do Norte e entrega encomendas em todo o Canadá com a sua própria frota de motoristas. No seu site inclui a SHEIN entre os seus parceiros. Se uma compra online vier com um número começado por `STRTD` ou `STRCA`, é tratada pela Straightship; um número começado por `STSH` também pode ser seu. Podes segui-lo em [straightship.com](https://www.straightship.com/new/), na caixa «Track Your Shipment», ou colá-lo diretamente no campo de pesquisa acima. A loja envia-te o código no e-mail de expedição ou na página da encomenda.

## Número de seguimento da Straightship: como é

A Straightship não publica um guia de formatos. Os códigos que o Peek reconhece seguem estas estruturas, aqui com algarismos inventados:

| Número | O que é |
| --- | --- |
| `STRTD482910573` | `STRTD` e 9 algarismos. |
| `STRTD4829105736491827` | `STRTD` e 16 algarismos (também existem com 17). |
| `STRCA4829105736491` | `STRCA` e 13 algarismos. |
| `STSHYW48291057364` | `STSH`, duas letras e 11 algarismos. |

Minúsculas e espaços não importam no Peek. Quando o rastreador da Straightship não conhece um número, responde `No results found!`: confirma-o no e-mail de envio da loja. Não encontras o código em lado nenhum? Consulta [como encontrar o teu número de seguimento](guide:find-tracking-number) e, se for uma compra da SHEIN, [seguimento de encomendas SHEIN](guide:shein-orders).

## Estados de seguimento da Straightship explicados

O rastreador da Straightship está em inglês. Mostra em cima um resumo com o campo «Package Status» e por baixo o histórico com a hora, o estado e a localidade de cada leitura. Estas são as linhas habituais pela ordem em que uma encomenda costuma passar por elas no **Canadá**:

| O que diz a Straightship | O que significa para ti |
| --- | --- |
| `Package information is created in the system` | A loja criou a etiqueta. A Straightship ainda não tem a encomenda: continua com o vendedor ou em viagem desde a Ásia. |
| `Package Received with Origin Scan warehouse` | Primeira leitura da Straightship no Canadá, com cidade e província. |
| `Package is received at a Straightship station` | Chegou a uma das estações da Straightship (em páginas mais antigas surge `Arrived at Facility`). |
| `Package arrived at transit station` | Numa estação de trânsito a caminho do centro local que serve a tua morada. |
| `Package is in transit to a Straightship station` | Em rota entre estações, antes de chegar à estação de distribuição. |
| `Loading` | Em carregamento na estação antes de sair para entrega; pode aparecer duas vezes com segundos de diferença. |
| `Package will be delivered before 9:00pm` | Em distribuição hoje: a entrega ou uma tentativa terá lugar nesse mesmo dia. |
| `Attempted Delivery` | O motorista passou, mas não conseguiu concluir a entrega. |
| `Shipment delayed` | Atraso sem motivo detalhado no rastreador. |
| `Delivered` | Entregue, com cidade e província (mostrado como «Entregue» no Peek). |
| `Package is being returned to shipper` | Em devolução ao remetente. |

Duas mensagens de tentativa falhada dizem o que fazer: `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` significa que o local de entrega estava encerrado; a mesma frase com `unable to leave package at safe location` em vez de `consignee closed` indica que o motorista não encontrou um sítio seguro onde deixar a encomenda. Ambas pedem que escrevas para `SS@straightship.com` para combinar a entrega seguinte.

Três pormenores costumam gerar dúvidas:

- **O resumo anda atrasado.** O campo «Package Status» pode continuar a indicar `ReadyForShipping` (ou `Ready For Shipping`) enquanto a encomenda já circula entre armazéns. Guia-te sempre pela linha mais recente do histórico em baixo; os valores seguintes do resumo são `Shipped`, `Delivered` e `ReturnedToSender`.
- **«In progress» não significa em distribuição.** `Delivery is in progress` e `Your order is in Progress` surgem depois de uma entrega que não chegou a concretizar-se, por vezes dias mais tarde e antes de uma devolução. Escreve à Straightship se aparecerem.
- **As horas estão em UTC.** Por baixo do histórico, o rastreador avisa que todas as horas estão em Tempo Universal Coordenado (UTC): uma leitura ao final da noite no Canadá aparece já com a data do dia seguinte.

Uma encomenda em devolução ainda pode voltar a ser lida numa estação e sair novamente para distribuição: `Package is being returned to shipper` nem sempre é o último registo.

## Entrega da Straightship no Canadá: quem entrega

O rastreador da Straightship destina-se ao **Canadá** (o cabeçalho diz «Canada B2C Shipment Tracking»). A Straightship afirma que a sua rede própria cobre mais de 95 % da população do Canadá e 90 % dos códigos postais canadianos através de mais de 85 centros de distribuição, e a sua mensagem de saída promete a entrega antes das 21:00 (`before 9:00pm`). Também entrega no **Canadá** para a empresa logística chinesa [JS Express](carrier:js-express): o seguimento da JS Express reproduz as leituras canadianas da Straightship e, quando o registo da JS Express inclui um código `STRTD` ou `STRCA`, o Peek consulta também a Straightship.

Para as encomendas trazidas dos Estados Unidos para o **Canadá**, a Straightship apresenta na sua página em inglês várias opções de última milha — a sua própria rede, Canada Post, Purolator ou [UPS](carrier:ups) —, enquanto a página em chinês enumera a sua própria rede, Canada Post, [FedEx](carrier:fedex) ou UPS. A Straightship indica que este serviço entrega em 6 a 10 dias e trata o desalfandegamento em 24 horas. O seu rastreador mostra apenas as estações da própria Straightship e não indica outra transportadora nem um segundo código.

No **Canadá**, a Straightship não publica quantas tentativas de entrega faz, se deixa aviso em papel nem durante quanto tempo guarda uma encomenda por entregar:

- **Após uma tentativa falhada**, envia um e-mail para `SS@straightship.com` com o teu número de seguimento, tal como pede a mensagem no rastreador.
- **Para alterar a morada ou o telefone**, usa o formulário de contacto e escolhe «Address and/or contact details update».
- **Prova de entrega:** a página inicial refere que o rastreador apresenta o estado mais recente e a fotografia de entrega quando disponível («POD image (Where available)»).

## Alfândega no Canadá numa encomenda da Straightship vinda da Ásia

No **Canadá**, a Straightship trata do desalfandegamento para os expedidores empresariais e as suas páginas não esclarecem se alguma vez cobra diretamente ao destinatário. Se te pedirem um pagamento por uma encomenda da Straightship, pergunta primeiro à loja: a ajuda da SHEIN Canadá confirma que todas as encomendas enviadas para o **Canadá** seguem com direitos pagos (`DDP`, *delivered duty paid*), sem taxas aduaneiras adicionais para o comprador. Outros serviços de seguimento podem mostrar `Customs Released` antes da primeira leitura no Canadá (linha que o site da Straightship não apresenta), bem como `Customs Held For Examination` durante uma inspeção ou `Customs Rejected`, que nem sempre é definitivo: numa encomenda seguida pelo Peek, a alfândega examinou o envio e libertou-o dois dias depois. O Peek mostra ambas as linhas como «Na alfândega»; se alguma se mantiver durante vários dias, avisa o vendedor.

O que é devido no **Canadá** depende do valor e do país de expedição segundo os limiares para transportadoras expresso da Agência de Serviços Fronteiriços do Canadá (CBSA): por transportadora expresso, até 20 CAD a partir da China ou de qualquer país fora dos EUA e do México não há direitos nem impostos, e acima desse valor aplicam-se ambos. A partir dos Estados Unidos ou do México não se paga nada até 40 CAD, apenas impostos entre 40,01 CAD e 150 CAD, e direitos e impostos acima de 150 CAD (salvo bens sujeitos a regras específicas, como álcool ou tabaco).

Nos envios por transportadora expresso para o **Canadá**, a CBSA autoriza as transportadoras qualificadas a liquidar direitos e impostos em teu nome: segundo explica a CBSA, a transportadora entra em contacto contigo antes da entrega para combinar o pagamento e retém a encomenda até pagares o montante devido, sendo as taxas de desalfandegamento da transportadora independentes dos direitos e impostos e alheias ao controlo da CBSA. Além disso, a CBSA nunca telefona nem envia SMS ou e-mails a pedir o teu número de segurança social (`SIN`) nem o número do cartão de crédito: qualquer mensagem desse tipo é uma burla. Sabe mais em [encomendas retidas na alfândega](guide:customs).

## Encomenda da Straightship atrasada, perdida ou devolvida

**Quem apresenta reclamação.** Os clientes da Straightship são as lojas e plataformas expedidoras, e a empresa não publica nenhum processo de indemnização para destinatários. Para pedir reembolso ou novo envio, fala com a loja onde compraste (para a SHEIN, consulta [seguimento de encomendas SHEIN](guide:shein-orders)).

**Parada antes do Canadá.** `Package information is created in the system` sem linhas posteriores significa que a encomenda ainda não chegou às mãos da Straightship no Canadá; pode continuar em trânsito desde a Ásia. Pergunta à loja quando foi expedida e consulta [seguir encomendas vindas da China](guide:tracking-from-china).

**Sem atualizações no Canadá.** Se passarem vários dias sem novidades após uma leitura numa estação ou surgir `Shipment delayed`, envia o formulário de contacto da Straightship com o motivo «Delivery Status Enquiry». Mais detalhes em [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Dada como entregue, mas não aparece.** Procura primeiro se há fotografia de entrega no rastreador da Straightship (nem sempre há) e verifica vizinhos, sala do correio do prédio e locais abrigados junto à porta. No **Canadá**, a SHEIN acrescenta que em raras ocasiões uma encomenda pode surgir como entregue até 48 horas antes de chegar efetivamente; depois disso, usa «Delivered Order Not Received» no formulário da Straightship e, se for uma compra da SHEIN, carrega no botão «Parcel Delivered, Not Received» em «My orders» para iniciar o pedido de reembolso. Vê [entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** `Package is being returned to shipper` indica que a encomenda regressa ao remetente: pede à loja o reembolso ou um novo envio e consulta [devolvida ao remetente](guide:returned-to-sender).

## Telefone e apoio ao cliente da Straightship

No seu site oficial, a Straightship divulga:

- **Telefone no Canadá:** `+1 (800) 452-3304`, de segunda a sexta-feira das 08:00 às 21:00 e ao sábado das 08:00 às 16:00, hora do Leste (`EST`); na página inicial consta também `+31 800 0200244` para os Países Baixos (`NL`).
- **E-mail:** `SS@straightship.com`, o endereço indicado nos avisos de tentativa de entrega falhada.
- **Formulário de contacto:** na [sua página de contacto](https://www.straightship.com/new/contact.html), onde o número de seguimento é obrigatório. A Straightship pede que contes com 2 dias úteis para obter resposta.

Para questões sobre a encomenda em si, devoluções ou reembolsos, contacta o vendedor.

## Seguir uma encomenda da Straightship com o Peek

O [Peek](/) identifica a Straightship de imediato a partir de um código `STRTD` ou `STRCA` nos formatos acima, que mais nenhuma transportadora do catálogo partilha. Com um código `STSH` não é possível decidir só pelo formato, pois coincide com alguns números da Colis Privé: o Peek pergunta à Straightship se conhece a encomenda e seleciona-a quando responde que sim. Se não surgir escolhida, toca em «Alterar» e seleciona-a em «Corresponde a este número». Também podes colar uma ligação de seguimento de `straightship.com` (`/track-your-shipment/tracking-id/` seguido do código). O Peek só consulta na Straightship estes formatos conhecidos: se escolheres a Straightship em «Alterar» para outro formato diferente, o Peek avisa «Esta transportadora não emite números de seguimento como este. Verifica o número ou escolhe outra transportadora.»; usa nesse caso a pesquisa própria da Straightship.

O Peek lê o rastreador oficial da Straightship e mostra o estado, o histórico com cidade e província de cada leitura e a hora de entrega. Interpreta as horas como UTC, tal como indica o site, e converte-as para o fuso horário do teu dispositivo («Horas no teu fuso horário: …» em peektracker.com e na app para iPhone). `Package will be delivered before 9:00pm` conta como «Em distribuição», os avisos de tentativa como «Tentativa de entrega» e `Delivery is in progress` como «Em trânsito». O Peek não apresenta data prevista de entrega e omite a referência do cliente e a fotografia de entrega disponíveis no site da Straightship. Como a Straightship não nomeia outro operador, o Peek acompanha o código da Straightship até à entrega.

Enquanto tens a encomenda aberta, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos — e até de 2 em 2 minutos a partir das 6:00 na hora do Pacífico no dia em que a Straightship regista a saída para distribuição, incluindo entregas ao final da noite — sempre que o rastreador da Straightship responda; apenas através de serviços de seguimento, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da entrega enquanto a encomenda estiver aberta ou com alertas ativos. No resto do tempo, bem como após dois dias sem atualizações (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes ativar alertas de «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Straightship nem a loja em teu nome.

## Perguntas frequentes sobre o seguimento da Straightship

### A Straightship é fidedigna?

Sim. A Straightship Solutions Limited é uma empresa de logística sediada em Burnaby, Colúmbia Britânica (Canadá). O seu site menciona a SHEIN, Canada Post, FedEx, COSCO e [YTO](carrier:yto) entre os seus parceiros, refere a Walmart e a Costco entre os retalhistas com que trabalha e declara ter mais de 85 centros de distribuição no Canadá e processar mais de 500 000 encomendas da Amazon por dia.

### O que significa «Package will be delivered before 9:00pm»?

Que a tua encomenda está hoje em distribuição no Canadá. O motorista irá entregá-la ou registar uma tentativa nesse mesmo dia; se ninguém puder recebê-la, surgirá uma linha a pedir que escrevas para `SS@straightship.com`.

### Porque é que a Straightship continua a mostrar «ReadyForShipping»?

Porque o resumo «Package Status» pode ficar parado em `ReadyForShipping` enquanto a encomenda já avança pelas estações da Straightship. Orienta-te pela linha de topo do histórico detalhado que aparece por baixo.

### Quanto tempo demora a Straightship a entregar?

A Straightship não publica prazos para envios vindos da Ásia; para o seu serviço dos Estados Unidos para o Canadá indica 6 a 10 dias. Guia-te pela estimativa de entrega dada pela loja na compra; quando surge `Package will be delivered before 9:00pm`, o motorista passa nesse próprio dia.

### Quem entrega as encomendas da Straightship no Canadá?

Os próprios motoristas da Straightship a partir das suas estações no Canadá, segundo mostra o seu rastreador, que não menciona qualquer outra transportadora. Para envios dos Estados Unidos para o Canadá, as suas páginas comerciais listam também Canada Post e UPS, juntamente com Purolator na página inglesa e FedEx na chinesa.

:::sources
- [Straightship: Home (em inglês)](https://www.straightship.com/new/) – envios da Ásia para a América do Norte, frota própria, caixa de seguimento, imagem POD, parceiros, telefone, e-mail e horário EST
- [Straightship: About (em inglês)](https://www.straightship.com/new/about.html) – logística porta a porta da Ásia para a América do Norte
- [Straightship: Services (em inglês)](https://www.straightship.com/new/services.html) – rede própria com cobertura de mais de 95 % da população e 90 % dos códigos postais do Canadá, serviço EUA–Canadá em 6 a 10 dias, alfândega em 24 horas e opções Canada Post, Purolator e UPS
- [Straightship: 服务 (serviços, em chinês)](https://www.straightship.com/new/cn/services.html) – o mesmo serviço EUA–Canadá com rede própria, Canada Post, FedEx ou UPS na última milha
- [Straightship: Resources (em inglês)](https://www.straightship.com/new/resources.html) – mais de 85 centros de distribuição no Canadá, mais de 500 000 encomendas da Amazon por dia, Walmart e Costco
- [Straightship: Contact (em inglês)](https://www.straightship.com/new/contact.html) – motivos de contacto, número de seguimento obrigatório e resposta em 2 dias úteis
- [Straightship: Privacy Policy (em inglês)](https://www.straightship.com/new/privacy.html) – denominação Straightship Solutions Limited e instalações em Burnaby (Colúmbia Britânica)
- [CBSA: Importing casual goods by courier (em inglês)](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – liquidação de direitos e impostos por transportadoras expresso no Canadá e taxas de desalfandegamento alheias ao controlo da CBSA
- [CBSA: Protect yourself against fraud (em inglês)](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – cobrança combinada antes da entrega pela transportadora e ausência de pedidos de SIN ou cartão de crédito por parte da CBSA
- [CBSA: Low-value shipment thresholds (em inglês)](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-eng.html) – franquias por transportadora expresso no Canadá (20 CAD de outros países, 40 CAD e 150 CAD dos EUA e México)
- [SHEIN Canada: Are there any additional fee for customs? (em inglês)](https://ca.shein.com/faq/detail?id=2307) – encomendas da SHEIN para o Canadá enviadas com direitos pagos (DDP)
- [SHEIN Canada: My order is marked as delivered, but I haven't received it (em inglês)](https://ca.shein.com/faq/detail?id=2362) – estado entregue até 48 horas antes da entrega e botão Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos `STRTD`, `STRCA` e `STSH`, textos de leitura, aviso UTC, valores do resumo, `Customs Released` e retenções aduaneiras retransmitidas, ligação a partir da JS Express e campos mostrados no Peek
:::
