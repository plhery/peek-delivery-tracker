---
title: Rastreamento Poșta Română: códigos RO, status e alfândega
description: Rastreie envios da Poșta Română: formato de códigos RO, status em romeno explicados, encomendas do exterior, taxas aduaneiras, reclamações e telefone.
slug: posta-romana-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Poșta Română (Compania Națională Poșta Română, CNPR) opera o serviço postal universal da Romênia e realiza entregas em todas as cidades e vilas do país. Acompanhe um envio na [página Track & Trace](https://www.posta-romana.ro/en/track-trace.html) ou cole o número na caixa acima. Cada correio informa apenas o trecho dentro do próprio país, portanto o rastreador da Poșta Română cobre somente a Romênia: uma encomenda vinda de uma loja no exterior aparece primeiro no rastreador do correio de origem e passa a aparecer na Poșta Română quando chega à Romênia. Não encontra seu código? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Código de rastreamento da Poșta Română: como ele se parece

O código da Poșta Română tem 13 caracteres sem espaços: duas letras, nove dígitos e duas letras. Seus códigos próprios terminam em `RO`. É o [formato postal internacional](guide:tracking-number-formats), portanto códigos de outros correios seguem a mesma estrutura trocando as duas letras finais pelo código do país; o nono dígito é verificador. Com dígitos fictícios:

| Formato (fictício) | O que é |
| --- | --- |
| `CP482917367RO` | Envio numerado pela Poșta Română, dentro da Romênia ou rumo ao exterior. As duas letras iniciais variam conforme o serviço; o que identifica é o `RO` no final. |
| `RR739402812CN` | Termina com outras letras, aqui `CN`: numerado pelo correio de outro país, aqui a [China Post](carrier:china-post). Encomendas que entram na Romênia vindas do exterior geralmente trazem esse formato. |

A Poșta Română rastreia itens registrados e com seguro, encomendas, pacotes com cobrança na entrega, EMS e impressos registrados. Cartas comuns não têm rastreamento.

## Status de rastreamento da Poșta Română explicados

O Peek lê cada etapa em romeno (normalmente sem acentos), junto com a agência ou centro que registrou a leitura. As linhas reconhecidas pelo Peek, aproximadamente em ordem:

| A Poșta Română informa | O que significa para você |
| --- | --- |
| `Primit de la expeditor` | Recebido do remetente: a Poșta Română já está com o envio. |
| `In prelucrare` | Em processamento no local informado: agência postal, centro regional ou centro de intercâmbio internacional de Bucareste. |
| `In transport` | Saiu do local informado e está a caminho. |
| `Sosit in Romania` | Chegou à Romênia vindo do exterior, no centro de intercâmbio internacional. |
| `Expediat din Romania` | Enviado ao exterior. O rastreamento da Poșta Română termina aqui. |
| `Predat la factor` | Entregue ao carteiro para a rota: saiu para entrega. |
| `Programat pentru predare incepand cu aceasta data` | Pronto para retirada a partir desta data na agência postal indicada. Retire lá. |
| `Predat la destinatar` | Entregue ao destinatário: concluído. |
| `Returnat catre expeditor` | Está retornando ao remetente. |

O Peek classifica essas etapas em seus próprios status, como "Em trânsito" ou "Pronta para retirada", e também reconhece as mensagens com acentos. Uma entrega após `Returnat catre expeditor` corresponde à devolução ao remetente, então o Peek exibe "Devolvida ao remetente". Outras linhas são interpretadas pelas regras gerais do Peek, e textos desconhecidos aparecem como escritos, sem etapa. Saiba mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Recebendo uma encomenda Poșta Română na Romênia

**Prazos de entrega.** Os termos gerais da Poșta Română definem o padrão Z+5 para entregar na Romênia uma encomenda de até 20 kg vinda do exterior: cinco dias a partir da entrada na rede da Poșta Română. Em pacotes de fora da UE, soma-se o tempo da alfândega. Veja [prazos de entrega](guide:delivery-times).

**Retirada na agência.** Quando o pacote aguarda na agência, o rastreador exibe `Programat pentru predare incepand cu aceasta data` com o nome da agência. A Poșta Română guarda o pacote por pelo menos **5 dias corridos** a partir da notificação. Se estiver em uma agência com assistência aduaneira, contate-a antes de ir. Veja [entrega não realizada](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

**Alfândega em encomendas de fora da UE.** A Romênia integra a UE, portanto pacotes da China, Reino Unido ou EUA passam pela alfândega e todo volume com mercadorias exige declaração aduaneira eletrônica, inclusive presentes. A Poșta Română declara compras online de até €150 como sua representante e cobra o IVA padrão de 21%, salvo quando o vendedor já recolheu via IOSS e os dados eletrônicos enviados com o pacote comprovam isso. Desde 1º de julho de 2026, compras online fora da UE de até €150 pagam €3 de imposto aduaneiro da UE por item, conforme confirma a página aduaneira da Poșta Română. Desde 1º de novembro de 2026, aplica-se também a taxa de manuseio da União de €2 por item, independentemente do valor. Um item corresponde às mercadorias do pacote que compartilham classificação tarifária, descrição e, quando exigido, origem.

A Poșta Română cobra taxa de apresentação aduaneira para esses pacotes de baixo valor, incluindo presentes e remessas IOSS. Se a alfândega selecionar o volume para análise de risco, você recebe aviso solicitando documentos e aplica-se uma taxa maior de despacho aduaneiro por pacote. É necessário ter número EORI para formalidades aduaneiras, mesmo sendo pessoa física recebendo um presente sujeito a trâmite. Os tributos podem ser pagos em dinheiro ou por cartão nas agências com assistência aduaneira que possuem maquininha. Veja mais em [encomendas retidas na alfândega](guide:customs).

**Atrasos e devoluções na alfândega.** A Poșta Română processa esses volumes em Bucareste usando os dados eletrônicos transmitidos pelo correio de origem. Dados ausentes ou incompletos atrasam o desembaraço; sem eles ou sem formulários CN22/CN23 adequados, o pacote retorna ao remetente. Comprou no [AliExpress](guide:aliexpress-orders), [Temu](guide:temu-orders) ou [Shein](guide:shein-orders)? Veja [rastrear encomendas da China](guide:tracking-from-china).

## Envios da Poșta Română para o exterior

O rastreador da Poșta Română acompanha o envio até sair da Romênia e não mostra nada enquanto cruza outros países. Após `Expediat din Romania`, use o mesmo código `RO` no rastreador do correio nacional do país de destino, como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), [Poste Italiane](carrier:poste-italiane) ou [Correos](carrier:correos-spain).

## Rastreamento Poșta Română parado, extravio ou devolução

**Nenhuma nova etapa.** O rastreador cobre somente a Romênia: itens para o exterior param em `Expediat din Romania`, e itens vindos de fora só aparecem ao chegar na Romênia. Pacotes de fora da UE também podem aguardar na alfândega. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Perdida, atrasada ou danificada.** Tanto o remetente quanto o destinatário podem registrar reclamação pelos canais abaixo em até **6 meses** da postagem (em envios internacionais, os 6 meses contam do dia seguinte à postagem; para expressos internacionais como EMS, o prazo é de 1 a 4 meses). Tenha em mãos o código de rastreamento e a data de postagem. A Poșta Română responde em até **3 meses**, incluindo indenização. Você também pode pedir indenização por item registrado perdido ou danificado em qualquer agência postal levando o recibo de postagem; envios não registrados não têm cobertura. Em remessas internacionais, a indenização vai até 30 DES para documentos registrados e até 40 DES por encomenda mais 4,5 DES por kg para pacotes padrão; itens com seguro cobrem até o valor declarado. Comprou online? Avise também a loja: veja [encomendas perdidas](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Entregue, mas não recebida.** Abra reclamação conforme acima: o formulário internacional inclui item não entregue e conteúdo faltante entre seus motivos. Veja [entregue mas não recebido](guide:delivered-not-received).

**Devolvida.** `Returnat catre expeditor` significa que o item está voltando ao remetente. O Peek mostra como "Devolvida ao remetente": veja [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da Poșta Română

Canais da Poșta Română:

- **Telefone:** `021 9393`, de segunda a sexta, das 8h às 20h (horário da Romênia). Chamadas cobradas na tarifa normal.
- **E-mail:** `suportclienti@ro.post`.
- **Reclamações:** online pelo formulário interno ou [formulário de reclamação internacional](https://www.posta-romana.ro/en/reclamatii-externe.html), no balcão de uma agência postal ou por carta com aviso de recebimento. A Poșta Română não aceita reclamações por telefone nem pelo formulário comum de Suporte ao Cliente.

A Poșta Română não pede dados bancários, números de cartão ou senhas, nem pagamento online de taxas de entrega. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda Poșta Română com o Peek

Todos os correios usam o mesmo formato de 13 caracteres, portanto o [Peek](/) não identifica a Poșta Română apenas por um código `RO`. Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Ele mostra "Consultando serviços de rastreamento…" enquanto consulta as transportadoras compatíveis, começando pela Poșta Română, e exibe "Poșta Română está com esta encomenda." se ela confirmar. Caso ainda não conste, o Peek classifica como correio desconhecido e consulta serviços de rastreamento (e o correio de destino quando informado); nos primeiros 30 dias volta a consultar a Poșta Română e vincula o envio assim que ela registrar movimentação. Você também pode escolher Poșta Română em "Trocar". Se o dígito verificador estiver errado, o Peek avisa "O dígito verificador deste código não confere.": confira o número ou escolha "Rastrear como digitado". Um link para o rastreador da Poșta Română também funciona: o Peek tira o código dele. Vinculada à Poșta Română, a encomenda exibe "Aguardando Poșta Română" até a primeira leitura.

O Peek lê o rastreamento próprio da Poșta Română. Mostra cada etapa no texto em romeno com o local informado e indica a agência postal onde o pacote aguarda como "Ponto de retirada" (ou "Retirada em" após a coleta). Todas as leituras são interpretadas no horário da Romênia, já que o rastreador cobre apenas eventos no país. Ele omite a origem, a data de partida e o horário de funcionamento da agência. Uma linha `Programat pentru predare incepand cu aceasta data` com data futura (como o dia seguinte) passa a aparecer no Peek quando chega esse horário. O rastreador não informa previsão de entrega, peso nem nomes, por isso o Peek não exibe esses campos. "Abrir o site de Poșta Română" na página do pacote abre o código diretamente no rastreador da Poșta Română. Se você escolher Poșta Română para um número incompatível, o Peek mostra "não emite códigos como este".

Quando você rastreia um código `RO` de fora da Romênia, o Peek consulta também o correio do seu país com o mesmo código: USPS nos EUA, Royal Mail no Reino Unido ([Parcelforce](carrier:parcelforce) para códigos iniciados em `E`), An Post na Irlanda, Canada Post, Australia Post, [NZ Post](carrier:nz-post), [DHL](carrier:dhl) na Alemanha, [La Poste](carrier:la-poste) na França, Israel Post em Israel, os correios de todos os demais países da UE (Spring GDS nos Países Baixos), Suíça, Noruega, Moldávia e muitos outros, do Japão ao Brasil. Ele muda para esse correio assim que surgir uma leitura tão recente quanto a última da Poșta Română, mantendo o histórico anterior. Na Romênia, como a Poșta Română não informa o destino, o Peek não consulta outro correio — acompanhe diretamente no correio de destino; o mesmo vale em países cujo correio o Peek não integra, como Turquia ou Emirados Árabes. No sentido inverso, quando outro correio indica a Romênia como destino (ou não indica destino e você rastreia a partir da Romênia), o Peek consulta a Poșta Română com o mesmo código.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta a Poșta Română até a cada 10 minutos, e a cada 2 minutos a partir das 6h (horário romeno) no dia em que a Poșta Română entrega o pacote ao carteiro (`Predat la factor`), enquanto o rastreador dela responder; nos demais casos, de hora em hora. Após dois dias sem novidades (12 horas enquanto o pacote ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". Após quatro dias sem nova linha em um pacote em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data dessa linha, "Sem atualizações desde …", a mensagem "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Poșta Română." e o link "Pedir ajuda a Poșta Română"; após três semanas, a mensagem passa a ser "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Poșta Română." Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a Poșta Română, seu correio nem a loja por você.

## Perguntas sobre o rastreamento da Poșta Română

### Como rastrear uma encomenda da Poșta Română?

Insira o código de 13 caracteres na página Track & Trace da Poșta Română ou cole no Peek. Para encomendas vindas do exterior, use o código fornecido pelo remetente ou loja: a Poșta Română passa a exibi-lo quando chegar na Romênia.

### Por que meu rastreamento da Poșta Română não está atualizando?

Se a última etapa for `Expediat din Romania`, o item já saiu da Romênia e o rastreador da Poșta Română acompanha apenas o trecho interno: acompanhe no correio do país de destino com o mesmo número. Encomendas de fora da UE também podem aguardar na alfândega.

### Por quanto tempo a Poșta Română guarda uma encomenda na agência?

Por pelo menos 5 dias corridos a partir da notificação. Retire na agência indicada no rastreamento.

### Pago taxas alfandegárias em encomendas de fora da UE?

Em compras online, sim. Pedidos de até €150 pagam €3 de imposto aduaneiro por item, e todo pedido online paga a taxa de manuseio da União de €2 por item, qualquer que seja o valor. A Poșta Română também recolhe 21% de IVA, salvo quando o vendedor já cobrou via IOSS, além de cobrar taxa de apresentação aduaneira.

### Qual é o telefone de atendimento da Poșta Română?

`021 9393`, de segunda a sexta, das 8h às 20h (horário da Romênia), ou pelo e-mail `suportclienti@ro.post`. A Poșta Română não registra reclamações por telefone.

:::sources
- [Poșta Română: Track & Trace](https://www.posta-romana.ro/en/track-trace.html) – código de 13 caracteres, rastreio apenas na Romênia, rastreador do correio de destino, formulários de reclamação, contato e alerta de golpes
- [Poșta Română](https://www.posta-romana.ro/) – alerta contra golpes
- [Poșta Română: Ajutor (cópia arquivada)](https://web.archive.org/web/20260713182410/https://www.posta-romana.ro/ajutor.html) – serviços rastreáveis, cobertura de cada correio, indenização em qualquer agência, declarações aduaneiras, IVA de 21%, taxas de apresentação e despacho, centro de intercâmbio, devolução sem dados, pagamento e autodeclaração
- [Poșta Română: Modificări vamale începând cu 01.07.2026](https://www.posta-romana.ro/a2050/servicii-destinatari/informatii-utile/intrebari-frecvente/modificari-vamale-incepand-cu-01072026.html) – imposto de €3 por item desde 1º de julho de 2026 e EORI para presentes
- [Poșta Română: Termeni și condiții](https://www.posta-romana.ro/a119/termeni-de-utilizare/termeni-si-conditii.html) – serviço universal, entrega em toda a Romênia, Z+5, guarda de 5 dias, responsabilidade e regras de reclamação
- [Poșta Română: International complaint](https://www.posta-romana.ro/en/reclamatii-externe.html) – quem pode reclamar e motivos aceitos
- [Poșta Română: Contact](https://www.posta-romana.ro/contact.html) – telefone, e-mail, horários e restrição de reclamações por telefone ou formulário comum
- [Comissão Europeia: Reforma Aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto de €3 e taxa de manuseio de €2
- [Comissão Europeia: Orientação sobre o imposto aduaneiro de €3 e taxa de manuseio da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por item e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – textos de leitura, o que o Peek exibe e qual correio consulta na sequência
:::
