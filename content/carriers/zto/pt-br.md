---
title: Rastreamento ZTO Express: códigos, status e contato
description: Rastreie encomendas da ZTO Express (中通快递) na China: formatos de código, status como 已签收 explicados, entrega em lockers ou postos e atendimento.
slug: rastreamento-zto-express
published: 2026-11-24
updated: 2026-11-24
---

A ZTO Express (中通快递, Zhongtong) é uma transportadora chinesa fundada em Xangai em 8 de maio de 2002; em 2025, movimentou 38,52 bilhões de encomendas. Um código ZTO normalmente pertence a um envio dentro da China, muitas vezes um pedido online (a ZTO transporta compras do Taobao desde 2008) rumo ao galpão de um redirecionador ou a um endereço chinês. Consulte o pacote no [rastreador da ZTO](https://www.zto.com/check), em chinês, ou na caixa acima. Encomendas enviadas pelo grupo ao exterior seguem pela ZTO International, que usa códigos próprios iniciados em 771 e outro rastreador.

## Como é o código de rastreamento da ZTO Express

A ZTO chama o código de número de conhecimento (`运单号`). O Peek reconhece dois formatos (dígitos fictícios):

| Código | O que é |
| --- | --- |
| `784920573280` | 12 dígitos: conhecimento da ZTO Express, mas [FedEx](carrier:fedex), [SF Express](carrier:sf-express) e outras empresas também usam 12 dígitos. |
| `74829105736491` | 14 dígitos começando com 7: também da ZTO Express, formato compartilhado com DPD, SEUR, BRT e outras. |
| `77148291057364` | `771` e mais 11 dígitos: formato da ZTO International. O Peek consulta a ZTO International primeiro. |

Espaços não interferem no Peek. O rastreador oficial da ZTO aceita até 10 códigos de uma vez, separados por vírgulas ou espaços.

**Onde encontrar.** O remetente (em geral a loja) guarda sua via do conhecimento (`发件联`) e informa o número nos detalhes de envio do pedido. Se não achar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**No site da ZTO.** Digite o código em zto.com/check e clique em `查询` (consultar). O rastreador pode pedir que você resolva um quebra-cabeça de verificação antes. Para receber cada novidade na hora, escaneie o QR code da página pelo WeChat.

## Status de rastreamento da ZTO Express explicados

Cada movimentação traz uma etapa curta em chinês. O Peek traduz o texto da etapa para o inglês e a classifica no seu idioma:

| A ZTO mostra | O Peek exibe (em inglês) | Etapa no Peek | O que significa |
| --- | --- | --- | --- |
| `已取件` | "Collected" | Recebida pela transportadora | Um entregador da ZTO coletou o pacote com o remetente. |
| `已收件` | "Received" | Recebida pela transportadora | Uma unidade da ZTO deu entrada na encomenda. |
| `运输中` | "In transit" | Em trânsito | Em deslocamento entre hubs e filiais da ZTO. |
| `派件中` | "Out for delivery" | Saiu para entrega | O entregador está com o pacote em rota. |
| `已代收` | "Held at a collection point for the recipient" | Pronta para retirada | Um ponto de coleta, como uma estação de encomendas, guardou o pacote para você retirar. |
| `已签收` | "Signed for" | Entregue | Alguém assinou o recebimento (`签收`): você, um representante ou o galpão de destino. |
| `异常通知` | "Exception notice" | Precisa de atenção | A ZTO registrou uma ocorrência. O Peek exibe só a etapa; o site da ZTO traz o texto completo. |

Outras etapas, como devolução ou redirecionamento, mantêm as palavras originais em chinês no Peek.

**Enviado para um redirecionador?** Nesse caso, `已签收` significa que o galpão do redirecionador assinou o recebimento e encerrou a parte da ZTO: a partir daí vale o código do próprio redirecionador. A ZTO também oferece um serviço internacional de consolidação (`中通国际集运`), que reúne vários pacotes num armazém e os envia juntos; para utilizá-lo, ligue para 95311 na China continental e peça o setor internacional.

## Entrega da ZTO Express na China: entregadores, estações e lockers

Na China continental, um entregador da ZTO leva o pacote ou ele aguarda numa estação de encomendas (`驿站`), ponto de coleta ou locker inteligente (`智能快件箱`) próximo ao endereço: o contrato de serviço da ZTO com remetentes prevê todas essas modalidades. Já as Medidas Administrativas do Mercado Expresso da China, em vigor desde 1º de março de 2024, determinam que o uso de locker ou estação requer consentimento do destinatário, além do direito de conferir a encomenda com o conhecimento na entrega, abrir para inspeção ou recusar se a embalagem estiver visivelmente danificada ou o conteúdo for frágil.

O contrato da ZTO também orienta filmar a retirada e a abertura de encomendas deixadas na porta, em locker ou em estação, como prova. Uma entrega feita conforme acordado é considerada confirmada caso ninguém apresente objeção por escrito em até três dias.

Do FAQ oficial da ZTO:

- **Sem código para abrir o locker?** Encontre a unidade que atende seu endereço na [busca de agências da ZTO](https://www.zto.com/service/station) (`服务网点查询`) e ligue, ou informe o número do conhecimento ao 95311 para obter o telefone do entregador ou da filial.
- **Cobrança por pacote deixado tempo demais no locker?** A ZTO orienta tocar em pular (`跳过`) na hora da retirada e não pagar nada.
- **Cobrança de taxa de entrega?** A filial entregadora não pode cobrar taxa de entrega do destinatário.
- **Aviso de endereço fora da área de cobertura?** Retire o pacote na filial ou peça ao remetente para despachar por outra transportadora.
- **Endereço errado?** Não pode ser alterado depois que o pacote saiu para entrega ou foi entregue. Antes disso, o remetente pode solicitar à filial de origem; a viabilidade depende de onde a encomenda está.

## Encomenda da ZTO parada, extraviada ou devolvida

**Nada ainda.** Se o rastreador da ZTO não encontrar o número, confira com o vendedor. Enquanto isso, um pacote cadastrado na ZTO Express exibe "Aguardando ZTO Express"; sem transportadora definida, "Ainda sem histórico de rastreamento".

**Sem atualização.** Quando uma encomenda em trânsito fica 4 dias sem nova leitura, o Peek avisa que pacotes costumam pausar e adiciona o link "Pedir ajuda a ZTO Express" para a página de contato da ZTO. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Extravio ou avaria (China continental).** Procure o vendedor: pelo contrato da ZTO, o remetente deve reclamar por escrito em até 30 dias após tomar ciência do extravio ou dano. Sem seguro (`保价`), a indenização máxima da ZTO é de 500 yuans; com seguro, até o valor declarado. Conteúdo faltante ou danificado dentro de embalagem intacta fica excluído, portanto confira no ato. Veja [encomendas extraviadas](guide:lost-parcel).

**Devolvida.** Na China continental, a ZTO pode devolver o pacote quando o endereço é pouco claro, há recusa ou duas tentativas de entrega falham sem culpa da transportadora. Veja [devolvida ao remetente](guide:returned-to-sender).

**Entregue, mas não chegou.** `已代收` indica espera num ponto de coleta e `已签收` que alguém assinou — nem sempre você. Cheque estações de encomendas e lockers próximos, a portaria e o redirecionador, se aplicável. Para reclamar na China continental, informe o código ao 95311 ou use o formulário de reclamação (`投诉反馈`), que exige login. Se a resposta da ZTO não satisfizer ou atrasar, as normas chinesas permitem recorrer às autoridades postais. Veja [encomenda entregue mas não recebida](guide:delivered-not-received).

## Telefone e atendimento da ZTO Express

Canais divulgados pela ZTO:

- **95311:** central nacional de atendimento na China continental (`全国统一客服热线`), das 08h30 às 17h30 segundo o FAQ. O site em inglês a chama de "Customer Service Hotline".
- **95720:** número oficial de onde os entregadores da ZTO ligam (`中通业务员专属外呼号码`).
- **Atendimento online** (`在线客服`) em zto.com, além do miniprograma no WeChat e do aplicativo da ZTO.

Para os canais da ZTO International, consulte o atendimento próprio dela. Para questões sobre o pedido, procure o vendedor, que foi quem contratou a ZTO.

## Como rastrear uma encomenda da ZTO Express no Peek

Cole o código no [Peek](/): ele acompanha mais de 4.500 transportadoras. Um código da ZTO colado sozinho não basta para o Peek consultar a ZTO Express de primeira. Ele pergunta antes a outras transportadoras que usam o mesmo formato (FedEx, DHL, BRT e Colis Privé em 12 dígitos, nunca ZTO; DPD, SEUR e BRT em 14 dígitos; ZTO International primeiro num código 771) e depois aos serviços de rastreamento, exibindo "Consultando serviços de rastreamento…" nesse meio tempo. Se eles encontrarem o pacote e indicarem ZTO Express, o Peek passa a acompanhá-lo ali e consulta a ZTO na checagem seguinte. Depois dos serviços de rastreamento, em códigos de 14 dígitos iniciados em 73 ou 79, o Peek verifica o site da ZTO no navegador e associa o pacote à ZTO Express se ela o localizar. Se nada aparecer, surge "ainda não encontrada · o Peek continua verificando". Para fazer o Peek consultar a ZTO desde o início, toque em "Trocar" e escolha ZTO Express em "Compatível com este código", ou busque por ZTO ou 中通快递 (中通 sozinho traz também a ZTO International). Num pacote já adicionado, use "Trocar transportadora". A consulta direta da ZTO no Peek aceita apenas os dois formatos acima: para qualquer outro, avisa que a ZTO Express "não emite códigos como este".

O Peek lê o rastreador oficial da ZTO e abre um navegador quando o site exige verificação. Para cada movimentação, mostra a etapa (tabela acima), a cidade da unidade ZTO responsável (ou o nome chinês da unidade quando não há correspondência no mapa) e o horário exato no fuso do seu dispositivo (em peektracker.com e nos apps de iPhone e Android, uma linha ao final do histórico informa o fuso). O Peek descarta a frase completa de cada leitura da ZTO — omitindo contatos e telefones dos entregadores, bem como nome e endereço do destinatário — e não exibe previsão de entrega. O rastreador público da ZTO às vezes oculta as leituras mais antigas, o que também se reflete no Peek. O Peek acompanha apenas o código da ZTO: o número de um redirecionador deve ser adicionado como outra encomenda.

Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, a checagem ocorre até a cada 10 minutos, e até a cada 2 minutos (ou a cada 5 quando a consulta à ZTO exigiu abrir o navegador) a partir das 6h no horário local no dia em que a ZTO marca saída para entrega, enquanto o rastreador oficial responde (pelos serviços de rastreamento, até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados); fora disso, de hora em hora. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode ativar alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a ZTO, o redirecionador ou o vendedor por você.

## Perguntas frequentes sobre rastreamento ZTO Express

### A ZTO Express é confiável?

Sim. A ZTO Express foi fundada em Xangai em 8 de maio de 2002, abriu capital em Nova York em 27 de outubro de 2016 e em Hong Kong em 29 de setembro de 2020 — a primeira empresa expressa listada em ambas. Segundo a companhia, sua rede somava mais de 31.000 pontos de atendimento em 2025 e alcançava mais de 99% dos distritos e condados da China.

### ZTO Express e ZTO International são a mesma coisa?

Mesmo grupo, dois serviços e dois rastreadores. O contrato da ZTO Express cobre envios postados e entregues na China continental (fora Hong Kong, Macau e Taiwan), com consulta em zto.com/check. A ZTO International, braço internacional do grupo desde 2014, envia para o exterior e para a China sob códigos iniciados em 771, rastreados em ztoglobal.com.

### Quanto tempo a ZTO Express leva para entregar?

Em condições normais, no dia seguinte dentro da mesma província e entre Jiangsu, Zhejiang, Xangai e Anhui, de 3 a 5 dias entre outras províncias e de 5 a 7 dias para áreas remotas, segundo o FAQ da ZTO — prazos tratados apenas como referência. Se o pacote for para um redirecionador, soma-se ainda o prazo internacional dele.

### O que significa 已签收 no rastreamento da ZTO?

Significa assinado/entregue: no Peek aparece como "Signed for", na etapa "Entregue". Você, alguém em seu nome ou o galpão de um redirecionador assinou o recebimento. Já `已代收` é diferente: indica que um ponto de coleta, como uma estação de encomendas, guardou o pacote para retirada.

### Qual é o telefone da ZTO Express?

É o 95311, central nacional na China continental, das 08h30 às 17h30 (horário da China). Ligações vindas do 95720 são dos entregadores da ZTO.

:::sources
- [ZTO Express: 运单查询 (consulta de conhecimento)](https://www.zto.com/check) – rastreador em chinês, até 10 códigos e alertas no WeChat
- [ZTO Express: 关于中通 (sobre a ZTO)](https://www.zto.com/introduce/about/home) – fundação, aberturas de capital, volume e rede em 2025, Taobao desde 2008
- [ZTO Express: 首页 (página inicial)](https://www.zto.com/) – 95311, 95720, app e WeChat
- [ZTO Express: Home (English)](https://en.zto.com/) – "Customer Service Hotline: 95311"
- [ZTO Express: 联系我们 (contato)](https://www.zto.com/introduce/about/contact) – atendimento online
- [ZTO Express: 常见问题 (FAQ)](https://www.zto.com/after/answers) – horário da central, lockers, taxas, cobertura, alteração de endereço, prazos e via do conhecimento
- [ZTO Express: 服务网点查询 (busca de agências)](https://www.zto.com/service/station) – unidade responsável pelo endereço
- [ZTO Express: 投诉反馈 (reclamações)](https://www.zto.com/after/complaint/problemTypes) – formulário de reclamação com login
- [ZTO Express: 快递服务协议 (contrato de serviço expresso)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – abrangência, entrega, conferência, devolução, prazos de referência, indenização e reclamações
- [ZTO Express: 中通国际 (ZTO International)](https://www.zto.com/introduce/service/international) – braço internacional do grupo desde 2014
- [ZTO Express: 中通集运 (consolidação)](https://www.zto.com/introduce/service/concentrated) – serviço internacional de consolidação e contatos
- [Ministério dos Transportes da China: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – conferência na entrega, consentimento para lockers e estações, recursos
:::
