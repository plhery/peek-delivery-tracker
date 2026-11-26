---
title: Rastreamento STO Express (申通快递): códigos e status
description: Rastreie uma encomenda STO Express (申通快递) na China: formato dos códigos, significado de 签收 e outros status, onde fica guardada e como falar com a STO.
slug: rastreamento-sto-express
published: 2026-11-26
updated: 2026-11-26
---

A STO Express (申通快递, Shentong) é uma transportadora expressa chinesa fundada em 1993 que se define como a primeira empresa privada de entregas expressas da China. Um código da STO normalmente pertence a uma encomenda dentro da China, muitas vezes um pedido online a caminho de você, de um conhecido ou do armazém de uma transitária. Rastreie no [site da STO](https://www.sto.cn/pc), na caixa `查快递` (rastrear encomenda), que está em chinês, ou no campo acima.

## Código de rastreamento STO Express: como ele é

Os códigos da STO (`快递单号`) têm apenas dígitos. A consulta da STO no Peek aceita 12, 13 ou 15 dígitos; os conhecimentos mais recentes têm 15. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `774829173654281` | 77 e mais 13 dígitos: um conhecimento da STO Express. A [BRT](carrier:brt) e a Yunda também usam 15 dígitos, então o Peek consulta a STO. |
| `482917365420` | 12 dígitos: formato mais antigo da STO, compartilhado por [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) e muitas outras. |
| `4829173654281` | 13 dígitos: outro formato mais antigo da STO, que o Peek não associa automaticamente à STO. |

Espaços não fazem diferença no Peek.

**Onde encontrar.** O vendedor informa nos detalhes de envio do pedido, e ele consta na etiqueta (`快递面单`) colada no pacote. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**No site da STO.** Digite o código na caixa `查快递` em sto.cn e clique em `查询` (pesquisar). O contrato de serviço da STO informa que o site, o aplicativo e a conta oficial no WeChat mantêm o rastreamento por um ano a partir do dia do envio.

## Status de rastreamento da STO Express explicados

Cada leitura no rastreamento da STO traz um tipo em chinês, um horário no fuso da China (UTC+8) e a unidade da STO que fez o registro. O Peek traduz estes tipos para o inglês na descrição da etapa e aplica os status abaixo:

| Leitura da STO | No Peek | Status no Peek | O que significa para você |
| --- | --- | --- | --- |
| `收件` | "Picked up" | Recebida pela transportadora | A STO coletou a encomenda com o remetente. |
| `发件` | "Departed", com "for" e a próxima unidade quando informada | Em trânsito | Saiu de uma unidade da STO rumo à próxima. |
| `到件` | "Arrived" | Em trânsito | Chegou a um centro de triagem ou a uma unidade. |
| `派件` | "Out for delivery" | Saiu para entrega | Está com um entregador em rota. |
| `驿站代收` | "At a parcel station for collection" | Pronta para retirada | Um posto de encomendas (`驿站`) recebeu o pacote por você. Vá retirar. |
| `柜机代收` | "In a parcel locker for collection" | Pronta para retirada | Está em um armário de encomendas. Vá retirar. |
| `客户签收` | "Delivered" | Entregue | Alguém assinou o recebimento. A STO também registra isso quando um posto ou armário assina pela encomenda. |
| `签收` | "Delivered" | Entregue | Assinada / entregue. |

Outros tipos de leitura mantêm o texto em chinês da STO no Peek. Um tipo que indique devolução (`退回` ou `退件`) aparece como "Return to the sender started" (Precisa de atenção). As movimentações posteriores mostram a viagem de volta: "Out for delivery back to the sender", "In a parcel locker or station on its way back" e "Returned to the sender".

**Enviada para uma transitária?** Então `签收` significa que o armazém da transitária assinou o recebimento, encerrando a parte da STO. A partir daí vale o código próprio da transitária.

## Entrega da STO Express na China: entregadores, postos e armários

A STO informa que sua rede alcança 99,7% dos municípios/distritos da China. Seu contrato de serviço permite entregar uma encomenda de quatro formas:

- diretamente a você;
- a alguém que more com você ou a uma pessoa indicada por você;
- em um ponto de retirada (`代收点`) ou armário de autoatendimento (`自提柜`) na região do endereço;
- de outra maneira solicitada por você ou pelo remetente.

Depois que a encomenda é deixada em uma dessas formas com a embalagem externa intacta, o contrato considera o serviço da STO concluído. Por isso "Pronta para retirada" no Peek é normal: o pacote aguarda em um posto ou armário perto do endereço. O código de retirada e o endereço do posto ficam no próprio rastreamento da STO, não no Peek.

Para alterar os dados do destinatário, o atendimento online da STO tem um tópico específico (`修改收件人信息`): peça por lá (contato abaixo) ou fale com o vendedor.

## Encomenda STO parada, extraviada ou devolvida

**Nada ainda.** O rastreamento da STO não retorna nada para códigos ainda desconhecidos nem para envios que passaram do limite de um ano. Se continuar vazio, confira o código com o vendedor. Enquanto isso, uma encomenda classificada como STO Express mostra "Aguardando STO Express"; dois dias depois de a STO responder pela primeira vez sem resultados, o Peek pede que você confira o código, escolha outra transportadora ou exclua a encomenda.

**Sem atualização.** Quando uma encomenda em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito" fica 4 dias sem nova movimentação, o Peek mostra o dia da última leitura ("Sem atualizações desde …") e "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando STO Express.", com o link "Pedir ajuda a STO Express" para o atendimento online da STO. Falta de atualização (`快递单号没有记录更新`) é um dos tópicos do atendimento online da STO. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**Extraviada ou danificada.** Fale primeiro com o vendedor: foi ele quem contratou a STO, não você. Pelo contrato de serviço da STO, o remetente ou alguém designado por ele reclama em até 30 dias após tomar ciência da perda ou do dano, apresentando comprovantes. Sem seguro (`保价`), a STO indeniza o prejuízo efetivo até 500 yuans, ou até 100 yuans para documentos. Encomendas seguradas são pagas até o valor declarado. Sem seguro nem comprovante de valor, a STO paga três vezes o valor do frete. Em caso de atraso por culpa da própria STO, ela isenta a taxa de envio. Veja [encomendas extraviadas](guide:lost-parcel).

**Devolvida.** A STO pode devolver uma encomenda quando o endereço não está claro, o destinatário recusa ou duas tentativas de entrega falham sem culpa da STO. O remetente arca com o custo. No Peek, aparece "Return to the sender started" e em seguida o trajeto de retorno. Veja [encomenda devolvida ao remetente](guide:returned-to-sender).

**Marcada como entregue, mas não chegou.** `签收` e `客户签收` significam que alguém assinou: você, alguém que mora com você, uma pessoa indicada, ou um posto ou armário que recebeu a encomenda por você. Confira postos de encomendas e armários próximos, a portaria do prédio e, se o destino era uma transitária, confirme com ela. Para reclamar, use o botão de reclamação (`投诉反馈`) no atendimento online da STO. Veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

## Contato e atendimento ao cliente da STO Express

A STO lista:

- **Atendimento online** em page.sto.cn, acessível por `在线咨询` (consulta online) em sto.cn: um assistente de chat com atendentes humanos das 8h30 às 22h no horário da China. Tem botões para enviar encomendas, consultar preços e registrar reclamações (`投诉反馈`).
- **WeChat:** conta de serviço e miniprograma para envios. A STO também está no Douyin. Os três aparecem no rodapé de sto.cn.

Esta página não informa telefone: o Peek lista apenas contatos verificados no texto das próprias páginas da STO, e nenhuma delas informa número de telefone. Sobre o pedido em si, fale com o vendedor.

## Acompanhe uma encomenda STO Express com o Peek

O formato de um código da STO sozinho não basta para o Peek ter certeza de que é da STO. Cole-o mesmo assim: o [Peek](/) acompanha mais de 4.500 transportadoras.

Para um código de 15 dígitos iniciado por 77, o Peek consulta STO Express e BRT ao mesmo tempo, mostrando "Consultando serviços de rastreamento…" enquanto verifica. Se a STO estiver com a encomenda, a linha abaixo do código indica STO Express dizendo "está com esta encomenda", e o Peek a classifica lá. Se não, aparece "ainda não encontrada · o Peek continua verificando". Adicione mesmo assim: a encomenda exibe "Provavelmente STO Express" e "Aguardando a primeira movimentação de STO Express", e o Peek continua consultando a STO. Após uma semana sem retorno, pede que você confira o código com o remetente ou escolha outra transportadora.

O Peek consulta a STO automaticamente apenas para códigos iniciados por 77. Para códigos de 12 dígitos, ele pergunta a outras transportadoras que usam esse formato (entre elas a BRT) e a serviços de rastreamento. Se encontrarem a encomenda e indicarem STO Express, o Peek a classifica lá e consulta a STO na verificação seguinte.

Para fazer o Peek ler o rastreamento próprio da STO desde o começo, toque em "Trocar" e selecione STO Express em "Compatível com este código" (12 dígitos, ou 15 começando com 77). Para 13 dígitos, ou 15 que não começam por 77, selecione em "Todas as transportadoras" ou busque por STO ou 申通. Em uma encomenda já adicionada, use "Trocar transportadora". A consulta da STO no Peek aceita somente 12, 13 ou 15 dígitos: se você escolher STO Express para qualquer outro formato, a linha abaixo do código avisa que a STO Express "não emite códigos como este".

O Peek lê o rastreamento próprio da STO, o mesmo por trás do atendimento online dela. Para cada leitura exibe a etapa (tabela acima), a cidade da unidade da STO que a registrou (ou o nome em chinês da unidade quando o Peek não consegue localizá-la) e o horário, lido no fuso da China e convertido para o do seu dispositivo. Leituras repetidas aparecem uma única vez, junto com o peso quando registrado pelas balanças da STO. Ficam de fora o texto completo da STO para cada leitura e, com ele, nomes e telefones de entregadores e postos, endereços e códigos de retirada e quem assinou. A STO não informa data prevista de entrega, então o Peek não mostra nenhuma. O Peek acompanha apenas o código da STO: o código de uma transitária deve ser adicionado como outra encomenda.

Enquanto você mantiver a encomenda aberta, ou no dia da entrega com as notificações ativadas, o Peek consulta até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário de Pequim no dia em que a STO registra a saída para entrega, enquanto o próprio rastreador da STO responder. Fora isso, e após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada", a menos que você esteja com a encomenda aberta ou ela seja esperada nesse dia. Ele pode avisar você sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a STO, com a transitária nem com o vendedor por você.

## Perguntas sobre o rastreamento STO Express

### A STO Express é confiável?

Sim. A STO Express começou em 1993, afirma ser a primeira empresa privada de entregas expressas da China e diz ter sido pioneira no modelo de franquias no setor chinês. É listada no mercado de ações A da China e declara operar 75 centros de triagem, 5.075 unidades independentes e mais de 98.000 pontos locais.

### O que significa 签收 no rastreamento da STO?

Assinada / entregue: o Peek exibe `签收` e `客户签收` como "Entregue". Significa que você, alguém que mora com você, alguém indicado ou um posto/armário assinou pela encomenda. Já `驿站代收` e `柜机代收` indicam que um posto ou armário guardou o pacote para retirada.

### Por que meu código da STO não mostra rastreamento?

A STO ainda não registrou o pacote, o código está incorreto ou a encomenda foi enviada há mais de um ano: a STO mantém rastreamento por um ano. Confira o código com o vendedor ou adicione ao Peek, que continua verificando.

### Onde fica o código de retirada da minha encomenda STO?

No próprio rastreamento da STO em sto.cn, junto do posto ou armário que guarda o pacote. O Peek omite códigos e endereços de retirada, mostrando apenas "At a parcel station for collection" ou "In a parcel locker for collection" quando a encomenda fica pronta para retirada.

### Como entrar em contato com a STO Express?

Pelo atendimento online no site da STO: há atendentes humanos das 8h30 às 22h no horário da China, além de um botão para reclamações (`投诉反馈`). Em compras online, fale primeiro com o vendedor.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – caixa de rastreamento, conta de serviço e miniprograma no WeChat, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – fundada em 1993, primeira transportadora expressa privada, modelo de franquias, listagem em ações A, centros de triagem, unidades, lojas, cobertura de municípios
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – rastreamento por um ano, formas de entrega, devoluções, indenização com e sem seguro, reclamações em 30 dias, atraso na entrega
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – chat, atendimento humano das 8h30 às 22h, reclamações, tópicos para falta de atualização e alteração de dados do destinatário
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – formatos de código, tipos de leitura, devoluções, fuso da China, peso, o que o Peek mostra e omite, sem data prevista
:::
