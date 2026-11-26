---
title: Rastreamento Kuayuan Express: códigos, status e ajuda
description: Rastreie encomendas da Kuayuan Express (跨源速递) da China para EUA ou Europa: código de 14 caracteres, status (AF, ND, CC), entregas locais e contatos.
slug: kuayuan-express-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, em chinês *跨源速递* / *深圳市跨源速递供应链有限公司*) é uma provedora chinesa de logística para e-commerce internacional sediada em Shenzhen, especializada em linhas ponta a ponta da China para a Europa e os Estados Unidos. Em [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/), a Kuayuan realiza coleta de primeira milha e consolidação na China, transporte aéreo e marítimo, desembaraço aduaneiro automatizado por código HS (canais DDP com impostos inclusos) e entrega de última milha por parceiros locais de destino ou frota própria. Você pode consultar até 20 códigos de uma vez em [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) ou colar seu código Kuayuan de 14 caracteres na caixa acima.

## Códigos de rastreamento da Kuayuan Express (formato de 14 caracteres)

Os códigos internacionais de encomendas da Kuayuan Express têm 14 caracteres: começam com um dígito (normalmente `3`), seguido por três letras maiúsculas e 10 dígitos (codificando o dígito final do ano, mês, dia e um sequencial de 5 dígitos):

| Formato (inventado) | O que é |
| --- | --- |
| `3KAA6101548291` | Código de 14 caracteres da Kuayuan Express (`3` + 3 letras + dígitos de data `YMMDD` + sequencial de 5 dígitos). |
| `3KBC6102873914` | Exemplo alternativo de código internacional de 14 caracteres da Kuayuan Express. |

A Kuayuan também pode fornecer o código da transportadora à qual entrega o pacote (`server_hawbcode`), como um código de barras da [DPD Alemanha](carrier:dpd-de), ao lado do seu próprio código de 14 caracteres. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Kuayuan Express

O sistema de rastreamento da Kuayuan combina códigos curtos em maiúsculas com descrições em inglês que cobrem as operações no armazém da China, a alfândega de exportação, a entrega à companhia aérea e as movimentações repassadas pela transportadora de destino:

| Código Kuayuan | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `AF` | Chegou a um centro operacional ou de triagem da Kuayuan. | "Em trânsito" |
| `DF` | Triagem e preparo de saída concluídos no centro operacional. | "Em trânsito" |
| `ECKSBG` | Declaração aduaneira de exportação enviada na China. | "Em trânsito" |
| `ECYDG` | Chegou ao porto ou aeroporto de embarque. | "Em trânsito" |
| `JH` | Entregue à companhia aérea internacional ou transportadora marítima. | "Em trânsito" |
| `ND` | Em trânsito na linha internacional ou transportadora local de última milha que recebeu o volume. | "Em trânsito" |
| `CF` | Tentativa de entrega sem sucesso no endereço do destinatário. | "Tentativa de entrega" |
| `UE` | Encomenda devolvida ao remetente após falha na entrega ou recusa. | "Devolvida ao remetente" |
| `CC` | Entregue com sucesso e assinada no destino. | "Entregue" |

As leituras `ND` mantêm o texto original da linha parceira: inglês para a DPD Alemanha, chinês para o China Post (`中国邮政已收取邮件`). Uma leitura com outro código, ou sem código, recebe a etapa de acordo com o texto exibido.

## Prazos de entrega, alfândega e repasse a parceiros locais

De acordo com [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Prazos divulgados:** a Kuayuan anuncia **7–10 dias** porta a porta na sua rota aérea de carga geral para os Estados Unidos e **7–10 dias** para os principais países europeus com voos regulares e operações locais.
- **Canais para cargas especiais:** além de mercadorias gerais, a Kuayuan transporta eletrônicos com bateria, cosméticos/líquidos e suplementos de saúde em canais dedicados em conformidade.
- **Desembaraço aduaneiro e DDP:** a Kuayuan oferece canais de duplo desembaraço com impostos inclusos (DDP) para Europa e Estados Unidos com correspondência automatizada de códigos HS. Em remessas não contratadas como DDP, IVA de importação e tarifas alfandegárias locais (incluindo cobranças da UE sobre pedidos de fora do bloco) ainda podem se aplicar. Veja [encomendas retidas na alfândega](guide:customs).
- **Entrega local:** após a liberação na importação, a equipe da Kuayuan no exterior repassa o pacote a uma parceira local, como a [DPD Alemanha](carrier:dpd-de), ou realiza a entrega com veículos próprios. A transportadora local define tentativas na porta, pontos de retirada e reagendamento.

## Encomenda da Kuayuan Express parada, sem atualização ou extraviada

Como são os vendedores e lojistas internacionais que mantêm a conta de envio com a Kuayuan Express, **contate sempre seu vendedor ou marketplace primeiro** para alterações de endereço, reembolsos ou reenvios.

- **Intervalos sem leitura entre a China e o destino:** o rastreamento costuma pausar por alguns dias entre `JH` (entregue à companhia aérea) e a primeira leitura `ND` no destino enquanto a carga aérea pousa e passa pelo desembaraço em lote. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Acompanhe o código da parceira local:** assim que a Kuayuan vincular o código da transportadora local de destino, consultar diretamente essa transportadora costuma exibir faixas de horário de entrega, códigos de retirada em loja parceira ou opções de reagendamento.
- **Falha na entrega, extravio ou dano:** se o rastreamento indicar `CF` (tentativa falha), `UE` (devolvida) ou `CC` (entregue) sem o pacote em mãos, verifique com vizinhos e com a transportadora local primeiro e abra uma disputa com o vendedor antes que a proteção ao comprador termine. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Kuayuan Express

Contatos oficiais publicados em [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Razão social:** *Shenzhen Kuayuan Express Supply Chain Co., Ltd.* (*深圳市跨源速递供应链有限公司*, registro ICP Guangdong `粤ICP备2025475526号`).
- **Telefone:** `+86 15013702902`.
- **E-mail:** `fanjinyun@kuayuanexpress.com`.
- **Rastreador público na web:** [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) (permite consultar até 20 códigos por busca).

> Desconfie de mensagens SMS falsas cobrando pequenas taxas de alfândega ou reentrega por meio de um link desconhecido. Confirme sempre qualquer taxa real de importação na página do seu pedido no marketplace ou no site oficial da transportadora local. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Kuayuan Express com o Peek

O [Peek](/) consulta a Kuayuan Express automaticamente como única candidata quando você informa um código Kuayuan de 14 caracteres iniciado por `3` (`3KAA6101548291` ou `3KBC6102873914`, inventados) e exibe "Kuayuan Express está com esta encomenda." assim que a Kuayuan confirma. Se seu código Kuayuan começar com outro dígito ou precisar ser escolhido manualmente, toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela da encomenda (buscar por "跨源速递" ou "Kuayuan" também a encontra).

O Peek lê o rastreador da Kuayuan em inglês e mostra a etapa ("Em trânsito", "Tentativa de entrega", "Devolvida ao remetente", "Entregue" ou outra etapa indicada pelo texto quando não há um dos nove códigos), a descrição da movimentação, o local e o país de destino. Os códigos de status não aparecem nos aplicativos do Peek. Na sua própria página da encomenda, o Peek mostra o nome do destinatário em "Destinatário" e, enquanto o pacote estiver a caminho, pode encerrar a rota no mapa na cidade do destinatário; links compartilhados não exibem nenhum dos dois. A Kuayuan envia horários sem fuso: o Peek interpreta cada leitura no fuso da sua localidade (Los Angeles para uma leitura em Los Angeles, Pequim para uma em Shenzhen), usa o fuso da leitura vizinha quando não reconhece o local e adota o horário de Pequim se não houver nenhum local, convertendo tudo para o relógio do seu aparelho. Quando a Kuayuan informa o código da linha parceira e o Peek reconhece essa transportadora, o app verifica se ela está com o pacote: a página então mostra "Entrega por" com o nome da transportadora e lista ambos os números. O rastreador da Kuayuan não fornece data estimada de entrega, peso nem horário próprio de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek verifica até a cada 10 minutos; no dia em que a Kuayuan mostra uma leitura de saída para entrega, consulta a cada 2 minutos a partir das 6h no relógio da localidade da leitura mais recente, enquanto o rastreador da Kuayuan responder. Caso contrário, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, passa a consultar a cada hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da Kuayuan não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia daquela leitura, como "Sem atualização desde seg 12 out". A página da encomenda passa a dizer: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Kuayuan Express." Depois de três semanas sem novo registro, exibe: "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando Kuayuan Express."

Você pode escolher alertas para "Todas as movimentações", "Etapas importantes" ou "Somente entrega". O Peek apenas acompanha atualizações de rastreamento e não pode liberar pacotes na alfândega, alterar endereços de entrega nem contatar a Kuayuan Express ou sua transportadora local por você.

## Perguntas sobre o rastreamento da Kuayuan Express

### O que é a Kuayuan Express (*跨源速递*)?

A Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*) é uma transportadora de logística para e-commerce internacional sediada em Shenzhen que opera linhas aéreas e marítimas dedicadas da China para a Europa e os Estados Unidos.

### Qual é o formato de um código de rastreamento da Kuayuan Express?

Os códigos da Kuayuan Express têm 14 caracteres: um dígito (geralmente `3`), três letras maiúsculas e 10 dígitos — por exemplo `3KAA6101548291` (inventado).

### O que significam os códigos `AF`, `JH`, `ND` e `CC` no rastreamento da Kuayuan Express?

`AF` indica que o pacote chegou a um centro operacional da Kuayuan, `JH` significa entrega à companhia aérea, `ND` cobre o trânsito na linha de destino e `CC` significa que a encomenda foi entregue.

### Como entrar em contato com a Kuayuan Express?

Fale primeiro com a loja virtual ou marketplace para questões sobre o pedido. A Kuayuan Express divulga o telefone `+86 15013702902` e o e-mail `fanjinyun@kuayuanexpress.com` em [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/).

:::sources
- [Kuayuan Express: Official Website & Tracker](https://www.kuayuanexpress.com.cn/) – *Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, rastreador para 20 códigos, rotas de 7–10 dias para EUA/Europa, alfândega DDP, telefone `+86 15013702902` e e-mail `fanjinyun@kuayuanexpress.com`
- [Universal Parcel Scraper: kuayuan-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuayuan-express) – formato de código de 14 caracteres, API `getTrackInfoOutside`, 9 códigos de leitura (`AF`–`UE`), repasse de código parceiro `server_hawbcode` e cada leitura datada no fuso horário da sua localidade
:::
