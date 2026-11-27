---
title: Rastreamento Mayi Yinfu: códigos MY, voos e entrega DHL
description: Rastreie encomendas Mayi Yinfu (蚂蚁银服) da China: formato dos códigos MY…US, tradução das etapas de voo e como a DHL assume a entrega na Europa.
slug: mayi-yinfu-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Mayi Yinfu (蚂蚁银服) é o nome abreviado da 广东蚂蚁银服国际物流有限公司, agente de cargas em Guangdong, China continental, que despacha encomendas expressas e pequenos pacotes para o exterior. Seus códigos de rastreamento trazem `MY`, dez dígitos e `US`, e seu rastreador também reconhece códigos formados por `MYYF`, oito dígitos e `CN`: cole o seu na caixa acima ou digite no campo 快递查询 (consulta de encomendas) em mayiyinfu.com.cn, que abre o rastreador da Mayi Yinfu. A Mayi Yinfu envia os pacotes de avião para a sua região e os repassa a uma empresa que faz a entrega local: na Europa, a Deutsche Post DHL.

## Código de rastreamento Mayi Yinfu: como é o formato

`MY` vem de 蚂蚁 (*mayi*, "formiga"). Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `MY4829105736US` | `MY`, dez dígitos e `US`: código da Mayi Yinfu. |
| `MYYF48291057CN` | `MYYF` (iniciais de 蚂蚁银服), oito dígitos e `CN`: formato também aceito pelo rastreador da Mayi Yinfu. |
| `MY482910573US` | Apenas nove dígitos: fora do padrão da Mayi Yinfu. Confira se faltou um dígito. |
| `RR482910576CN` | Duas letras, nove dígitos e `CN`: código do [China Post](carrier:china-post). |

Letras minúsculas e espaços não importam no Peek.

**Onde encontrar.** A Mayi Yinfu atende empresas (o site oferece login, cadastro e tabela de preços comerciais), então o código vem da loja onde você comprou. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number). O rastreador da Mayi Yinfu também localiza pedidos pela referência do vendedor, digitada exatamente como o vendedor escreveu, incluindo hifens.

## Status de rastreamento da Mayi Yinfu explicados

A Mayi Yinfu não publica lista de status. Suas próprias movimentações vêm em chinês, a maioria acompanhada de tradução para o inglês; após a transferência, ela reproduz as movimentações da empresa entregadora em inglês. Principais registros, em ordem:

| A Mayi Yinfu diz | O que significa para você |
| --- | --- |
| `货物电子信息已经收到` | "As informações eletrônicas da mercadoria foram recebidas": o vendedor criou o pedido. Não é movimentação física — a Hualei (华磊), plataforma por trás deste rastreador e do da BYHD, gera essa linha. |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Copiado da DHL: a DHL recebeu os dados da etiqueta. Não é movimentação física. |
| `已入库,到达收货点` | Registrado no armazém do ponto de coleta: a Mayi Yinfu está com o seu pacote. |
| `已出库,货物离开操作中心` | Saiu do centro de operações. |
| `货物已交机场` | Entregue no aeroporto para embarque aéreo (não é entrega a você); depois aparece 等待安排航班中 (aguardando voo) ou a data do voo. |
| `航班延误` | O voo atrasou; a linha informa a data do próximo. |
| `头程起飞`, `头程落地` | O primeiro voo decolou e depois pousou. |
| `二程起飞` | O voo de conexão decolou. O texto em inglês ("Flight 202 is taking off") lê 二程 (segundo trecho) como um número de voo. |
| `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Pousou na sua região; os paletes estão sendo desmontados para desembaraço aduaneiro de importação. |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.` | Liberado pela alfândega, aguardando repasse à transportadora local. |
| `已转运transferred` | Entregue à empresa entregadora, cujo código passa a aparecer no rastreador. |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Copiado da DHL: processado pela DHL na Alemanha. |
| `The shipment has arrived in the destination country/destination area.` | Chegou ao seu país. Pode terminar com link para o site da empresa entregadora. |
| `The shipment has been loaded onto the delivery vehicle` | Saiu para entrega. |
| `The shipment has been successfully delivered` | Entregue. |

**Linhas adiantadas.** `国内清关中` (desembaraço de exportação), `已排航班` (voo agendado), `起运地航班起飞` (voo decolou), `已到达目的国` (chegou ao país de destino) e `清关完成` (desembaraço concluído), quando aparecem sozinhas, vêm de um script: podem mostrar o pacote no exterior antes mesmo de chegar ao aeroporto, portanto confie nas movimentações ao redor.

Os horários da Mayi Yinfu não trazem fuso horário. Suas linhas na China continental usam horário de Pequim, e em geral as linhas copiadas da DHL também: uma movimentação da DHL às 10:00 na Alemanha aparece no rastreador da Mayi Yinfu como 17:00 no inverno.

## Quem entrega encomendas da Mayi Yinfu

O site da Mayi Yinfu não informa quem entrega em cada país. O próprio rastreador mostra a passagem: após `已转运transferred`, exibe o código da empresa entregadora e depois as linhas dela.

**Na Europa**, trata-se de um código da Deutsche Post DHL, e o rastreador da DHL acompanha a encomenda até a entrega no seu país (veja [DHL](carrier:dhl)). Um link ao final da linha de chegada da DHL leva à transportadora que entrega na sua porta.

**Em outros destinos**, a Mayi Yinfu não nomeia parceiros. O formato do segundo código costuma revelar a transportadora (veja [formatos de código de rastreamento](guide:tracking-number-formats)), ou consulte o vendedor. Tentativas de entrega, pontos de retirada e prazos de guarda seguem as regras da transportadora final. Recebeu cobrança de taxas de importação? Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Mayi Yinfu não chegou ou rastreamento parado

**Apenas as linhas iniciais.** `货物电子信息已经收到`, às vezes junto à linha de etiqueta da DHL, significa que o vendedor registrou o envio mas ninguém bipou o pacote ainda. Se nada mudar em alguns dias, confirme com o vendedor se foi realmente postado.

**Parada após `已转运transferred`.** A parte da Mayi Yinfu terminou: acompanhe pelo código da empresa entregadora exibido no rastreador. Veja mais em [rastrear encomendas da China](guide:tracking-from-china) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Nada encontrado.** Para um código desconhecido, o rastreador retorna um resultado com o número em 参考号 (referência) e todos os demais campos vazios. O Peek trata isso como não encontrado: mostra "Aguardando Mayi Yinfu" e, se continuar desconhecido após dois dias, pede para você conferir o código. Compare caractere por caractere com a mensagem do vendedor.

**Extraviada, danificada ou devolvida.** A Mayi Yinfu não publica procedimento de pedido de indenização, então procure o vendedor ou marketplace para reembolso ou reenvio. Veja [encomenda extraviada](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender) e [entregue mas não recebida](guide:delivered-not-received).

## Telefone e atendimento da Mayi Yinfu

O site da Mayi Yinfu, apenas em chinês, informa um único telefone, `+86 13705996996`, um celular na China continental exibido no cabeçalho como 全国服务热线 (central nacional) e na caixa 在线咨询 (consultas on-line). As páginas de contato, empresa, aviso legal e privacidade estão vazias: não há endereço, e-mail nem formulário.

## Acompanhe uma encomenda Mayi Yinfu com o Peek

Cole um código `MY…US` no [Peek](/) e ele vai direto para a Mayi Yinfu: nenhuma outra transportadora do catálogo usa esse padrão. Para códigos `MYYF…CN`, o Peek consulta a Mayi Yinfu para confirmar e acompanha por lá quando ela reconhece. Links do rastreador da Mayi Yinfu contendo `documentCode=` com o número ou referência do vendedor também vão direto para a Mayi Yinfu. Uma referência digitada não tem formato próprio, mas o Peek acompanha mais de 4.500 transportadoras: adicione assim mesmo, toque em "Trocar" e escolha Mayi Yinfu (buscar por 蚂蚁银服 ou MYYF também encontra), ou use "Trocar transportadora" em uma encomenda já salva; o Peek exibe então o código `MY` sob "Mayi Yinfu rastreia com o código". Como o Peek remove hifens, pesquise referências com hífen no próprio campo da Mayi Yinfu.

O Peek exibe cada movimentação no texto original da Mayi Yinfu com o local e o país de destino quando informado; linhas de voo com data prevista aparecem apenas como etapa em inglês, como "The first leg has departed". As linhas iniciais aparecem como "Etiqueta criada", `已入库,到达收货点` como "Recebida pela transportadora", `航班落地，拆板清关…` como "Na alfândega", as linhas de veículo de entrega e entregue como "Saiu para entrega" e "Entregue", e as demais linhas conhecidas como "Em trânsito". As linhas adiantadas e qualquer linha registrada mais de doze horas antes do horário que exibe (ou mais de seis horas no mesmo segundo de uma dessas) são previsões, não movimentações reais, e não alteram o status da encomenda; o histórico abre então com "Outros sites podem mostrar mais etapas, mas são apenas previsões.", mantendo essas etapas esmaecidas atrás de "Ver". Linhas datadas no futuro aguardam até essa hora. Registros na China continental — e linhas copiadas da DHL alocadas em um país depois que o código da DHL surge — usam o horário de Pequim; os demais seguem o relógio do local da etapa, dentro do possível. Nomes, endereços, códigos postais, peso, taxas, assinatura e comprovante ficam de fora, e não há data prevista de entrega.

**A DHL assume o envio.** A DHL é a única entregadora que o Peek aproveita dos registros da Mayi Yinfu, e apenas quando o código entregador ali tem formato exclusivo da DHL no catálogo do Peek. A encomenda exibe então "Entrega por DHL" e o código da DHL após o seu, e o Peek passa a consultar a DHL. Enquanto a DHL tiver somente o anúncio prévio sem movimentação própria, aparece "Anunciada a DHL, ainda não recebida". Assim que a DHL exibir uma movimentação ao menos tão recente quanto a última da Mayi Yinfu, o Peek acompanha na DHL preservando as linhas da Mayi Yinfu. Qualquer outro número entregador — inclusive um da DHL compartilhado com outras transportadoras — não aparece no Peek, e o Peek não acompanha a encomenda por ele: copie-o do rastreador da Mayi Yinfu e adicione como encomenda separada.

O Peek consulta a Mayi Yinfu até a cada 10 minutos, e de hora em hora após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"). Se dois dias depois o histórico ainda tiver apenas as linhas iniciais, o Peek também recorre a serviços de rastreamento. Entre as linhas conhecidas da Mayi Yinfu, "Etapas importantes" (entrega, retirada, alfândega, tentativas sem sucesso e devoluções) notifica em `航班落地，拆板清关…`, saída para entrega e entrega; "Só a entrega", apenas nas duas últimas.

## Perguntas sobre o rastreamento da Mayi Yinfu

### Por que meu código da Mayi Yinfu termina em US?

Todo código `MY` da Mayi Yinfu termina em `US`; os códigos `MYYF` que o rastreador também aceita terminam em `CN`. A terminação não indica o país de destino e não se trata de um código postal americano.

### "Delivered to the airport" significa que minha encomenda chegou?

Não. `货物已交机场` ("The goods have been delivered to the airport") indica apenas que a Mayi Yinfu entregou o pacote ao aeroporto para embarque aéreo. A entrega a você acontece depois, pela empresa local.

### Quanto tempo a Mayi Yinfu leva para entregar?

A Mayi Yinfu não divulga prazos de entrega. Siga a estimativa informada pela loja no seu pedido.

### Quem devo contatar se uma encomenda Mayi Yinfu sumir?

O vendedor de quem você comprou: foi ele quem contratou a Mayi Yinfu, cujo site divulga apenas um celular de atendimento.

:::sources
- [Mayi Yinfu: 广东蚂蚁银服国际物流有限公司 (home)](http://www.mayiyinfu.com.cn/Default.aspx) – nome da empresa, o campo 快递查询 e o rastreador, login, cadastro, consulta de preços, a linha de atendimento (全国服务热线 no cabeçalho, caixa 在线咨询)
- [Mayi Yinfu: 联系我们 (contact)](http://www.mayiyinfu.com.cn/contact.html) – nenhum conteúdo além da linha de atendimento; as páginas de perfil da empresa, aviso legal e privacidade também estão vazias
- [Mayi Yinfu: 国际小包 (international small parcels)](http://www.mayiyinfu.com.cn/products/category-1.html) – apenas texto provisório; as páginas de expresso e de linha dedicada não trazem dados: nem prazos de entrega nem parceiros
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – plataforma Hualei, formato do código, busca por referência, textos das linhas e seu significado, linhas geradas por script, fusos horários, resposta para código desconhecido, passagem à Deutsche Post DHL na Europa, o que o Peek mostra, retém e repassa
:::
