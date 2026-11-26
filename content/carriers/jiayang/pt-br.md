---
title: Rastreamento Jiayang International: códigos JYGJ e status
description: Rastreie encomendas internacionais da Jiayang International (东莞嘉扬国际): códigos JYGJ, horários de Pequim, linhas para Oriente Médio e EUA, atrasos e contatos.
slug: jiayang-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Jiayang International (*Dongguan Jiayang International Logistics Co., Ltd.*, em chinês *东莞市嘉扬国际物流有限公司* / *东莞嘉扬国际*) é uma agente chinesa de frete internacional e transporte de encomendas de e-commerce sediada em Shenzhen, com escritórios em Guangzhou, Shenzhen, Dubai, Omã e Kuwait, armazéns em Guangzhou, Shenzhen, Zhongshan, Foshan e Yiwu e depósitos no exterior em Dubai e Omã. Em [jygjgyl.com](https://www.jygjgyl.com/), a Jiayang concentra-se em linhas dedicadas aéreas, marítimas e de pequenos pacotes porta a porta com impostos inclusos (DDP) ou DDU saindo da China para destinos no Oriente Médio (Emirados Árabes Unidos/Dubai, Arábia Saudita, Omã, Catar, Bahrein e Kuwait), Estados Unidos e África do Sul. Você pode consultar uma remessa em [jygjgyl.com](https://www.jygjgyl.com/) ou colar seu código `JYGJ` na caixa acima.

## Códigos de rastreamento da Jiayang International (formato `JYGJ`)

Pequenos pacotes internacionais enviados pela Jiayang costumam receber um código de 16 caracteres que começa com `JYGJ`, seguido por 10 dígitos e terminado em `YQ`:

| Formato (inventado) | O que é |
| --- | --- |
| `JYGJ4829173605YQ` | Código padrão de 16 caracteres da Jiayang International (`JYGJ` + 10 dígitos + `YQ`). |
| `JYGJ7391846208YQ` | Exemplo alternativo de conhecimento internacional `JYGJ...YQ` de 16 caracteres. |

Quando você escolhe a Jiayang International manualmente, o rastreador também aceita outras referências alfanuméricas de 8 a 40 caracteres que contenham pelo menos um dígito. Os serviços da Jiayang abrangem desde a exportação na China e o trecho internacional aéreo ou marítimo até o desembaraço aduaneiro e a entrega local (当地派送) no país de destino. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Jiayang International

O portal de rastreamento da Jiayang usa o mesmo mecanismo bilíngue (inglês e chinês) adotado por diversas linhas chinesas de exportação, registrando movimentações desde a criação do pedido na China até o desembaraço no destino e a entrega pela transportadora local:

| O que a Jiayang exibe | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Order information received` | Dados eletrônicos do envio recebidos do vendedor antes da entrada física no armazém da Jiayang. | "Etiqueta criada" |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Pacote recebido fisicamente na unidade de consolidação de exportação da Jiayang na China. | "Recebida pela transportadora" |
| `Goods leave the operation center`, `Arrival at domestic internationality airport`, `Departure from the airport of origin domestic` | Processado no centro da Jiayang na China, transferido ao aeroporto de origem e embarcado para fora da China. | "Em trânsito" |
| `Arrive at the destination international airport` | Chegou ao aeroporto internacional de entrada no país de destino. | "Em trânsito" |
| `Import customs clearance` (com nome do porto ou cidade) | Submetido à alfândega de importação do destino para desembaraço e inspeção. | "Na alfândega" |
| `Leave the ... processing center`, `Arrived at logistics carrier facility in transit to next facility`, `Accepted at logistics carrier regional destination facility`, `Arrive at the last mile sorting and distribution center`, `Leaving the sorting center` | Entregue à transportadora local de última milha (muitas vezes identificada apenas como "logistics carrier") e em trânsito pelos centros regionais. | "Em trânsito" |
| `Delivered` | Entregue no endereço do destinatário pelo parceiro de destino. | "Entregue" |

Note que o rastreador da Jiayang grava **todos os horários no horário de Pequim (`UTC+8`)**, inclusive eventos ocorridos nos Estados Unidos, no Oriente Médio ou na Europa; por isso, uma entrega matinal no exterior pode exibir horário noturno de Pequim nos dados brutos da transportadora. Até hoje o rastreador da Jiayang não exibiu uma leitura de saída para entrega: o pacote passa do centro de triagem de última milha direto para `Delivered`.

## Linhas internacionais dedicadas, entrega local e alfândega

De acordo com [Perfil da Empresa Jiayang](https://www.jygjgyl.com/assets/12779/index.html), [Pequenos Pacotes de E-Commerce](https://www.jygjgyl.com/assets/12777/index.html) e [Linha Dedicada EUA](https://www.jygjgyl.com/assets/12783/index.html):

- **Centros regionais:** a Jiayang consolida pacotes em armazéns em Guangzhou, Shenzhen, Zhongshan, Foshan e Yiwu e mantém armazéns de consolidação no exterior em Dubai e Omã para remessas ao Golfo, além de canais aéreos/marítimos DDP e FBA para os Estados Unidos.
- **Entrega local:** a Jiayang lista a entrega local (当地派送) entre seus serviços. Seu rastreador chama a rede de última milha apenas de "logistics carrier" e não informa o código de rastreamento local.
- **Desembaraço aduaneiro:** muitos envios comerciais usam canais de duplo desembaraço com impostos pagos (DDP), enquanto remessas DDU exigem que o destinatário quite IVA de importação ou taxas alfandegárias junto à alfândega local ou à transportadora final. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda da Jiayang parada, sem atualização ou desaparecida

Como a Jiayang International é uma agente de cargas B2B contratada por lojistas e agentes de compras, **fale sempre primeiro com o vendedor ou marketplace** para alterações de endereço, reembolsos ou reenvios.

- **Intervalo sem leitura após sair da China:** o rastreamento costuma pausar por vários dias entre `Departure from the airport of origin domestic`, `Arrive at the destination international airport` e `Import customs clearance` enquanto a carga consolidada aérea ou marítima aguarda liberação aduaneira em lote. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Marcada como entregue, mas nada chegou:** verifique varanda, caixa de correspondências, vizinhos e portaria; depois peça ao vendedor o código da transportadora local de destino e o comprovante de entrega antes que a proteção ao comprador do marketplace expire. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Jiayang International

Dados oficiais publicados em [jygjgyl.com](https://www.jygjgyl.com/) e [Sobre a Jiayang](https://www.jygjgyl.com/assets/12779/index.html):

- **Razão social:** *Dongguan Jiayang International Logistics Co., Ltd.* (*东莞市嘉扬国际物流有限公司*).
- **Linha global de atendimento:** `186 8244 9187` (celular na China `+86 186 8244 9187`).
- **Endereço do escritório:** Room 606, No. 1 Langwei 1st Road, Chang'an Town, Dongguan, Guangdong, China (*广东省东莞市长安镇朗尾一路1号606室*).

> Desconfie de mensagens SMS falsas cobrando pequenas taxas de alfândega ou reentrega por um link desconhecido. Confirme qualquer cobrança real diretamente na página do seu pedido no marketplace ou no site oficial dos correios locais. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Jiayang International com o Peek

O [Peek](/) consulta a Jiayang International automaticamente como única candidata quando você informa um código `JYGJ` (`JYGJ4829173605YQ` ou `JYGJ7391846208YQ`, inventados) e mostra "Jiayang International está com esta encomenda." assim que o rastreador confirma; o Peek também reconhece links colados de `www.jygjgyl.com/index/tracknumber/track?nos=`. Se precisar selecionar a Jiayang International manualmente (por exemplo, com uma referência sem `JYGJ`), toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela da encomenda (buscar por "嘉扬国际", "JYGJ" ou "Dongguan Jiayang" também a encontra).

O Peek lê diretamente o rastreador da Jiayang e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Entregue" ou outra etapa indicada pela redação do evento), o texto da movimentação em inglês, o local sem código postal e o país de destino. A Jiayang envia cada registro com o fuso de Pequim, inclusive movimentações no exterior, e o Peek converte os horários para o relógio do seu aparelho. O rastreador da Jiayang não informa data estimada de entrega, peso, nome de quem assinou, nome do serviço nem código da transportadora local.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Jiayang mostrar saída para entrega (nenhuma registrada até agora), a cada 2 minutos a partir das 6h no horário de Pequim, enquanto o rastreador próprio da Jiayang responder. Fora isso, o Peek verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a consultar a cada hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da Jiayang não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia daquela movimentação, como "Sem atualização desde seg 12 out". A página da encomenda passa a dizer: "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Jiayang International." Depois de três semanas sem novo registro, exibe: "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando Jiayang International."

Você pode escolher alertas para "Todas as movimentações", "Etapas importantes" ou "Somente entrega". O Peek acompanha apenas atualizações de rastreamento e não pode liberar pacotes na alfândega, localizar códigos de transportadoras locais não vinculados nem contatar a Jiayang International por você.

## Perguntas sobre o rastreamento da Jiayang International

### O que é a Jiayang International (`JYGJ`)?

A Jiayang International (*东莞市嘉扬国际物流有限公司*) é uma agente internacional de fretes sediada em Shenzhen e Dongguan que opera linhas dedicadas aéreas, marítimas e de pequenos pacotes da China para o Oriente Médio (Dubai, Arábia Saudita, Omã, Catar, Bahrein e Kuwait), Estados Unidos e África do Sul.

### Qual é o formato de um código de rastreamento da Jiayang International?

Os códigos internacionais padrão da Jiayang têm 16 caracteres: `JYGJ` seguido de 10 dígitos e terminado em `YQ` (por exemplo `JYGJ4829173605YQ`, inventado).

### Por que os horários das movimentações da Jiayang International parecem adiantados ou atrasados várias horas?

O sistema da Jiayang registra todos os eventos no horário padrão da China (`UTC+8`), incluindo chegadas a aeroportos no exterior e entregas locais. O Peek interpreta esses horários no fuso de Pequim para convertê-los corretamente ao relógio local do seu dispositivo.

### Quem devo contatar se minha encomenda da Jiayang International atrasar ou sumir?

Fale primeiro com a loja virtual ou marketplace onde fez o pedido, pois são eles que detêm o contrato de frete com a Jiayang. A linha de atendimento da Jiayang na China divulgada em [jygjgyl.com](https://www.jygjgyl.com/) é `186 8244 9187`.

:::sources
- [Jiayang International: Official Website](https://www.jygjgyl.com/) – rastreamento de cargas, linhas especiais para Oriente Médio/EUA/África do Sul, endereço em Dongguan e telefone `186 8244 9187`
- [Jiayang International: Company Profile](https://www.jygjgyl.com/assets/12779/index.html) – sede em Shenzhen, escritórios em Guangzhou, Shenzhen, Dubai, Omã e Kuwait, armazéns em Guangzhou/Shenzhen/Zhongshan/Foshan/Yiwu e depósitos no exterior em Dubai e Omã
- [Jiayang International: E-Commerce Small Parcels](https://www.jygjgyl.com/assets/12777/index.html) – consolidação e rastreamento de pequenos pacotes internacionais
- [Jiayang International: US Dedicated Line](https://www.jygjgyl.com/assets/12783/index.html) – canais aéreos, marítimos DDP/DDU e FBA para os EUA
- [Universal Parcel Scraper: jiayang](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jiayang) – formato `JYGJ...YQ`, regra de link `jygjgyl.com`, mapeamento de status CTTYEX/LinHan, conversão do horário de Pequim e identificação do país de destino
:::
