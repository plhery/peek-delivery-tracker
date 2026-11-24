---
title: Rastreamento YunExpress: código YT, status e entrega
description: Rastreie sua encomenda YunExpress (Yun Express) da China: formato YT com 16 dígitos, significado dos status em inglês, entrega local e suporte.
slug: rastreamento-yunexpress
published: 2026-11-24
updated: 2026-11-24
---

A YunExpress (também grafada *Yun Express*) é uma empresa de logística sediada em Shenzhen, na China, que transporta pequenos pacotes internacionais para vendedores online, incluindo lojas da Amazon, Temu, SHEIN e AliExpress. Um código formado por `YT` seguido de 16 dígitos pertence à YunExpress. Você pode rastreá-lo em [yuntrack.com](https://www.yuntrack.com/), o portal oficial da empresa, ou colando o código na caixa acima. Como o site da YunExpress não menciona envio de SMS ou e-mails diretos ao destinatário, procure o código no e-mail de envio da loja ou na página do seu pedido.

## Código de rastreamento YunExpress: `YT` e 16 dígitos

O Yuntrack pede "seu número de rastreamento YunExpress (iniciado por YT)" sem especificar o comprimento. Veja exemplos com dígitos inventados e [formatos parecidos de outras transportadoras](guide:tracking-number-formats):

| Código (inventado) | Como o Peek identifica |
| --- | --- |
| `YT4829173640581926` | YunExpress (`YT` seguido de 16 dígitos). |
| `YT4829173640581` | [YTO Express](carrier:yto), outra transportadora (`YT` e 13 dígitos). |
| `YT482917364058192` | Não é YunExpress (`YT` e 15 dígitos): confira se falta um dígito. |
| `LP48291736405812` | Código logístico do AliExpress (`LP` e 14 dígitos), da Cainiao. |

Começa com `YT2` ou `YT22`? Continua sendo YunExpress: o que define o formato é `YT` mais 16 dígitos, independentemente dos primeiros algarismos. A YunExpress não explica o significado de cada bloco numérico, portanto conte o total de dígitos.

Não encontrou o número nas mensagens da loja? Veja [como encontrar o código de rastreamento](guide:find-tracking-number).

O Yuntrack aceita até 100 códigos por busca, separados por vírgulas, espaços ou quebras de linha. Depois que uma transportadora local assume o pacote, a página de detalhes pode exibir o código dessa parceira em "Additional Notes". A YunExpress não informa prazo fixo para um código novo aparecer; se o Yuntrack retornar `Not Found`, confira `YT` e os 16 dígitos e confirme com a loja.

## O que significam os status de rastreamento da YunExpress

O Yuntrack funciona somente em inglês. Ele classifica cada encomenda em uma aba (`Processing`, `Transit`, `Delivered`, `Not Found`, `Alert` ou `Returned`) e exibe as movimentações. As mais comuns, na ordem aproximada:

| A YunExpress informa | O que significa para você |
| --- | --- |
| `Shipment information received` | O vendedor transmitiu os dados; a YunExpress pode ainda não ter o pacote fisicamente. |
| `Shipment picked up` | A YunExpress coletou a encomenda com o vendedor. |
| `The country of origin commences customs declaration.` | Despacho aduaneiro de exportação no país de origem, não a alfândega do seu país. |
| `International flight has departed`, `Departed from AIRPORT` | Embarcou no voo internacional. |
| `Start Customs Clearence` | A alfândega de importação no destino começou (grafia própria da YunExpress). |
| `Customs inspection - Import` | Selecionada para inspeção aduaneira. |
| `Clearance processing completed - Import` | Liberada pela alfândega de importação. |
| `Delivered to local carrier` | Entregue à transportadora local responsável pela entrega final, ainda não a você. |
| `Item accepted` | Leitura repassada pela parceira local confirmando que recebeu o pacote da YunExpress. |
| `Arrived at GOFO Regional Destination Facility` | Chegou à GOFO, transportadora de última milha citada na leitura. |
| `The driver is out for delivery` | Saiu para entrega com o entregador. |
| `Your parcel is out for delivery.` | Repassada pela J&T Express Australia em envios à Austrália: saiu para entrega (pode terminar com o telefone de atendimento da J&T). |
| `Item is out for delivery` | Repassada pela transportadora local de `Item accepted`: saiu para entrega. |
| `Delivered by Mailbox` | Entregue na caixa de correio. |

`Transit` significa em trânsito; a leitura mais recente indica a etapa atual. A YunExpress não detalha a aba `Alert` (encomendas com alerta): leia a linha logo abaixo e fale com a loja se não houver avanço.

Após `Delivered to local carrier`, surgem as leituras da parceira local em inglês (veja [o que significam os status](guide:tracking-statuses)). Na DPD, `Sender goods issue` é apenas um registro de dados gerado ainda na China, não um problema no produto. Quando a DHL assume na Alemanha, a YunExpress repassa linhas como `The instruction data for this shipment have been provided by the sender to DHL electronically` ("Etiqueta criada" no Peek), `Pick-up was successful.` (coleta da DHL após o repasse), `The shipment has been processed in the parcel center of origin`, `Loaded to movement / tour vehicle` (entre centros de triagem, não rota final), `The shipment has been processed in the destination parcel center` e `Movement / tour vehicle arrived` (todas "Em trânsito" no Peek); já `Not delivered-Consignee could not be reached` indica destinatário ausente ("Tentativa de entrega"). O código da página do Yuntrack também mostra uma barra de progresso (`Pickup`, `Departed from origin`, `Arrived at destination`, `Local carrier on the way`, `Delivered successfully`), onde `Pickup` significa coleta com o vendedor e não ponto de retirada. Em alguns pacotes, o Yuntrack exibe ainda um `Estimated delivery time`.

## Quem entrega encomendas da YunExpress na etapa final

A YunExpress despacha os pacotes da China por via aérea, coordena o desembaraço aduaneiro e os transfere a uma transportadora local no destino — [o fluxo habitual de compras da China](guide:tracking-from-china). As linhas após `Delivered to local carrier` às vezes indicam qual parceira local assumiu o pacote.

**Reino Unido e Irlanda.** A YunExpress opera linha dedicada para o Reino Unido desde 2016, sem nomear parceiro de entrega no Reino Unido ou na Irlanda nem divulgar telefone ou escritório de atendimento ao destinatário; para questões de entrega, acione a transportadora indicada no rastreamento. Uma de suas linhas cobre o território britânico e ilhas integradas, excluindo Jersey e Guernsey. Para regras de impostos, veja [encomendas retidas na alfândega](guide:customs).

**Estados Unidos.** O site chinês da YunExpress oferece aos lojistas a operação de última milha CIRRO-E-COMMERCE, sem dizer quais pacotes ela leva. A empresa também lançou em 2020 a linha "US Standard Postal Service", mas não diz qual transportadora local entrega cada remessa nos EUA. Em alguns envios aos EUA, as movimentações mostram a entrega pela GOFO, e o rastreamento da própria GOFO encontra esses pacotes pelo mesmo código `YT`.

**Brasil e demais países.** A YunExpress não publica regras para destinatários sobre tentativas de entrega, avisos, prazos de guarda ou reagendamento: tudo segue as políticas da transportadora ou correio local que recebe o pacote após a alfândega. Para compras internacionais enviadas ao Brasil, a Receita Federal explica como os tributos são cobrados no [Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona).

Um artigo da YunExpress em chinês descreve ainda linhas europeias para pontos de retirada e armários inteligentes (a até 3 km ou escolhidos pelo destinatário), com média de 6 a 10 dias corridos da entrada até o ponto, exibindo o endereço no rastreamento.

## Rastreamento YunExpress sem atualização, atrasado ou perdido

A YunExpress não oferece ao destinatário formulário de reclamação nem prazo próprio de reembolso para atraso, extravio ou avaria. O cliente da YunExpress é o titular da conta que assina o contrato, ou seja, o vendedor; portanto, acione a loja ou o marketplace informando o número do pedido e o código `YT`. Para intervalos sem leituras, veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

Parado em `Customs inspection - Import`? O blog da YunExpress para lojistas explica que a seleção para fiscalização não garante longa retenção por si só: conferência documental costuma acrescentar de alguns dias úteis a cerca de uma semana; escaneamento ou inspeção física, de uma a duas semanas (às vezes com taxas de armazenagem ou inspeção); e revisão mais profunda pelas autoridades pode levar semanas ou mais de um mês. Veja [encomendas retidas na alfândega](guide:customs).

Consta como entregue mas não chegou? Verifique se a página de rastreamento exibe comprovante de entrega, consulte a transportadora local e a loja, e leia [entregue, mas não recebido](guide:delivered-not-received). `Returned` significa devolução em andamento; como a YunExpress não divulga regras de retorno a compradores, fale com a loja.

## Contato e atendimento ao cliente da YunExpress

A YunExpress não publica telefone local, formulário ao consumidor nem escritório de atendimento ao destinatário no Brasil, nos EUA, no Reino Unido ou na Irlanda. Os canais divulgados em seus sites são:

- **E-mail:** `info@yunexpress.com`, listado como "Customer Service" no rodapé de `yunexpress.com` (horário informado: `8:00` às `23:00`, sem fuso especificado; a página inicial cita atendimento humano em inglês e chinês).
- **Telefone na China:** `400-8575-500` em `yunexpress.cn`, linha da China continental sem confirmação de acesso do exterior.
- **Contatos regionais:** divulgados apenas para Ásia e Austrália em `sg.yunexpress.com/contact/`.

A caixa de feedback do Yuntrack trata do próprio site. Para resolver problemas com a entrega, procure primeiro a loja (detentora do contrato) e depois a transportadora local exibida no rastreamento.

## Rastrear uma encomenda YunExpress com o Peek

O [Peek](/) reconhece automaticamente `YT` seguido de 16 dígitos como YunExpress e também aceita links colados de `yuntrack.com`. Enquanto você mantém o pacote aberto, ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos — e a cada 5 minutos a partir das 6h no horário local no dia em que o pacote sai para entrega, enquanto o rastreador próprio da YunExpress responde; fora disso, verifica a cada hora. Quando acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), passa a checar de hora em hora.

O Peek marca cada leitura no mapa e exibe etapa, histórico, local e horário da entrega. Leituras na China são interpretadas no horário chinês; pouso do voo e etapas terminadas em `- Import` sem cidade usam o fuso do aeroporto de chegada informado pela YunExpress; leituras da J&T Express Australia usam o horário de Sydney; e as demais seguem o fuso do local ou das leituras adjacentes. O Peek mostra cada leitura no horário do seu aparelho.

Quando a YunExpress informa a transportadora local e seu código, o Peek pode acompanhar o pacote nessa parceira (embora não em todos os casos). O Peek não exibe data prevista nem ponto de retirada para a YunExpress, omite referências de pedido e não pode contatar a YunExpress nem abrir reclamação em seu nome.

## Perguntas frequentes sobre o rastreamento YunExpress

### Qual transportadora usa códigos `YT`?

A YunExpress usa `YT` seguido de 16 dígitos (18 caracteres ao todo), incluindo códigos iniciados por `YT2` ou `YT22`. Já `YT` com 13 dígitos pertence à [YTO Express](carrier:yto), outra transportadora chinesa. Você pode rastrear em `yuntrack.com` ou na caixa acima; após o repasse local, o rastreio pode mostrar também o código local.

### O que é `yuntrack.com`?

É o site oficial de rastreamento da YunExpress ("Track & Trace Platform"), acessado diretamente pelo botão "Tracking" em `yunexpress.com`. Funciona em inglês, aceita até 100 códigos `YT` por busca e não exige conta.

### A YunExpress é confiável?

Sim. Trata-se de uma empresa real de logística: *Shenzhen Qianhai YunExpress Logistics Co., Ltd.*, fundada em Shenzhen em 2014. Segundo a própria empresa, tornou-se parceira logística recomendada da Amazon em 2017 e parceira oficial do AliExpress em abril de 2025.

### Onde fica a sede da YunExpress?

No distrito de Longgang, em Shenzhen, China. A empresa não lista escritórios para destinatários no Reino Unido, na Irlanda, nos EUA ou no Brasil; sua política de privacidade aponta a *Hongkong YunExpress Logistics Limited* como controladora de dados fora da China continental.

### Quanto tempo a YunExpress demora para entregar?

Segundo as estimativas da YunExpress para lojistas, o *Standard Express* leva de 3 a 5 dias úteis ao Reino Unido, 5 a 8 aos EUA, 8 a 10 ao Canadá e 4 a 7 à Austrália; o *Priority Express* (lançado em 2024) indica 3 a 5 dias ao Reino Unido e 5 a 8 aos EUA, enquanto outra linha em chinês estima 5 a 8 dias úteis ao Reino Unido, 6 a 10 à Irlanda e 8 a 15 aos EUA. Normalmente não é possível saber qual linha a loja escolheu, e a alfândega pode acrescentar tempo.

:::sources
- [YunExpress: Track & Trace Platform (Yuntrack)](https://www.yuntrack.com/) – portal oficial de rastreamento, códigos `YT`, interface em inglês e feedback
- [YunExpress: código da página do Yuntrack](https://www.yuntrack.com/static/js/0.38a2ccebfb56bd50819e.js) – "starting with YT", separadores, abas, `Alert`, barra de progresso, "Additional Notes", comprovante de entrega e `Estimated delivery time`
- [YunExpress: código da página de resultados do Yuntrack](https://www.yuntrack.com/static/js/6.9b7c1a51737b2bcaa67c.js) – abas, `Not Found`, "Last Mile Tracking", 100 códigos por busca, sem conta
- [YunExpress: Site oficial](https://www.yunexpress.com/) – empresa, endereço em Shenzhen, e-mail `info@yunexpress.com`, horários e link para Yuntrack
- [YunExpress: About Us](https://www.yunexpress.com/about-us) – fundação em 2014, linha do Reino Unido (2016), linha postal dos EUA (2020), Priority Express (2024) e Amazon
- [YunExpress: Privacy Policy](https://www.yunexpress.com/privacy-policy) – titular do contrato como cliente e Hongkong YunExpress Logistics Limited
- [YunExpress: Partners](https://www.yunexpress.com/yun-ecosystem/partners) – marketplaces atendidos
- [YunExpress: Standard Express](https://www.yunexpress.com/our-solutions/b2c/33) – prazos estimados do serviço padrão
- [YunExpress: Priority Express](https://www.yunexpress.com/our-solutions/b2c/priority-express) – linhas para Reino Unido e EUA, prazos e exceção para áreas remotas
- [YunExpress: 全球专线挂号（特惠带电）](https://www.yunexpress.com/our-solutions/b2c/7) – prazos de linha dedicada e cobertura no Reino Unido
- [YunExpress: 最高省21%！云途自提服务上线](https://www.yunexpress.com/newsroom/detail/3) – linhas para pontos de retirada na Europa
- [YunExpress: 云途物流成为速卖通官方物流合作伙伴](https://www.yunexpress.com/newsroom/detail/1) – parceria oficial com o AliExpress
- [YunExpress: 為什麼你的包裹會被海關抽查？](https://hk.yunexpress.com/blog/2179/) – níveis e prazos de inspeção aduaneira
- [YunExpress: 云途物流 YunExpress (China)](https://www.yunexpress.cn/) – pequenos pacotes, telefone `400-8575-500` e CIRRO-E-COMMERCE
- [YunExpress: Contact Us (Regional)](https://sg.yunexpress.com/contact/) – contatos regionais para Ásia e Austrália
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – regras tributárias para importações ao Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – leituras processadas pelo Peek, integração GOFO e fusos das movimentações
:::
