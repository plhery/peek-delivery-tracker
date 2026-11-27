---
title: Rastreamento 360Lion: códigos WSH e AR, status e contato
description: Rastreie sua encomenda 360Lion Express da China para o Brasil e América Latina: códigos WSH e AR, status WSO/WSD, entrega local, alfândega e suporte.
slug: rastreamento-360lion
published: 2026-11-24
updated: 2026-11-24
---

A 360Lion (*360Lion Express*, em chinês *纬狮物流 / 纬狮物联网*) é uma operadora chinesa de logística para comércio eletrônico transfronteiriço fundada em 2015 e sediada em Shenzhen, com filiais e centros operacionais em Xangai, Guangzhou, Dongguan, Yiwu e Hong Kong. A empresa é especializada em linhas aéreas dedicadas para pequenos pacotes, frete FBA/FBM e armazéns no exterior ligando a China à América Latina e aos Estados Unidos (atendendo Brasil, México, Chile, Peru, Colômbia, Equador, Argentina, Uruguai, Bolívia, Paraguai, El Salvador, Costa Rica, Guatemala, Venezuela, Panamá e EUA) para lojistas de plataformas como Mercado Livre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart e Falabella. Você pode consultar envios no [portal de rastreamento da 360Lion](https://toms.360lion.com/track) ou em [360lion.com/en/](https://www.360lion.com/en/) (até 50 códigos por vez), ou colar seu código na caixa acima.

## Códigos de rastreamento 360Lion: formatos WSH e AR

Confira o código de rastreamento na página do seu pedido ou no e-mail de confirmação de envio. Os pacotes da 360Lion aparecem normalmente em dois formatos (dígitos inventados):

| Formato (inventado) | O que é |
| --- | --- |
| `WSHMX4829173640YQ` | Código de linha dedicada da 360Lion: `WSH`, duas letras do país de destino (como `MX` para México), 10 dígitos e `YQ`. |
| `AR4829173640` | `AR` seguido de 10 dígitos (usado em encomendas enviadas para a Argentina). Compartilha esse formato com [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz). |

Se o seu pedido segue para o Brasil ou o México com o código de barras de outra transportadora — como `AJ` da [Anjun Logistics](carrier:anjun), `8880` da [J&T Express Brasil](carrier:j-and-t-br) ou `JMX` da J&T Express Mexico — consulte [formatos de códigos de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da 360Lion

A 360Lion agrupa as remessas em estados gerais (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) e anexa códigos de leitura (`WSO*` na origem na China, `WSD*` no destino). Quando a encomenda chega à transportadora de última milha no país de destino, o rastreador pode repassar os textos da própria transportadora local.

| A 360Lion informa (código / grupo) | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | O vendedor criou o pedido de envio na 360Lion antes do início das leituras de transporte. | "Etiqueta criada" |
| Etapa `HUB` da barra de progresso | Pacote recebido no centro de triagem de origem da 360Lion na China. | "Recebida pela transportadora" |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Saiu de voo da China (com código de aeroporto como PVG) e pousou no aeroporto de destino. | "Em trânsito" |
| Etapa `CUSTOMS` da barra de progresso | Em desembaraço aduaneiro de exportação ou importação no destino. | "Na alfândega" |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Transferido para a parceira de última milha no país de destino, triado e enviado à unidade de entrega local. | "Em trânsito" |
| `Returned` (`RETURNED_BACK`) | Devolvido de volta ao remetente ou ao armazém de origem. | "Devolvida ao remetente" |
| `Delivered` (`DELIVERED`) | Entregue ao destinatário pela parceira local de última milha. | "Entregue" |

Observe que o estado `Pre-alert` (`WARNING` / *预警*) da 360Lion pode significar tanto um aviso prévio quanto um alerta; por isso, o Peek deixa as linhas individuais de leitura definirem a etapa, em vez de tratar `Pre-alert` isoladamente como falha.

## Como funciona a entrega internacional da 360Lion

Em [360lion.com/en/](https://www.360lion.com/en/) e [360lion.com](https://www.360lion.com/), a 360Lion se apresenta como transportadora de tronco internacional e agente geral de vendas e serviços aéreos (GSSA), operando voos fretados regulares da China para a América Latina com apoio de armazéns no Brasil, México, Chile, Colômbia e Estados Unidos.

**Entrega de última milha.** Após o aeroporto de destino (`WSD00`) e a alfândega de importação, a 360Lion repassa a encomenda (`WSD02`) a uma transportadora local no seu país, que realiza a entrega final no endereço.

**Regras ao destinatário e alfândega.** Como a 360Lion atende embarcadores empresariais e não consumidores de varejo, o site não publica limite de tentativas de entrega, prazo de guarda em pontos de retirada nem garantia de prazo para pequenos pacotes (a [página de Termos e Perguntas Frequentes](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) trata de regras comerciais para vendedores, como frete aéreo SEND, itens proibidos e prazo de 30 dias para reclamações de lojistas). Tributos e tarifas de importação seguem as regras aduaneiras do país de destino. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda 360Lion parada, sem atualização ou não entregue

Como a 360Lion mantém contrato com a loja ou o marketplace, e não com o comprador, fale sempre primeiro com o vendedor ou a plataforma em caso de atraso, erro de endereço ou pedido de reembolso.

**Sem atualizações entre a China e o destino.** Voos de longa distância da China para a América Latina e filas na fiscalização aduaneira podem gerar intervalos de vários dias sem leituras entre `departed from airport` (`WSO04`) e `arrived at airport` (`WSD00`). Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Repasse local sem segundo código.** O Peek acompanha as atualizações da transportadora local pelo próprio rastreador da 360Lion sob seu código `WSH` ou `AR`, sem exibir um código nacional separado. Se houver falha na entrega e você precisar do contato ou código do parceiro local, peça ao vendedor ou envie e-mail ao suporte de rastreamento da 360Lion.

**Consta como entregue, avariada ou extraviada.** Se o rastreio mostrar `Delivered` sem que o pacote tenha chegado, verifique com moradores, vizinhos e portaria; em caso de extravio ou avaria, registre as provas e abra disputa com a loja ou marketplace antes de vencer a proteção ao comprador. Veja [entregue, mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da 360Lion

Contatos divulgados na [página Fale Conosco da 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) e em [360lion.com/en/](https://www.360lion.com/en/):

- **E-mail para consulta de encomendas (*查件业务*):** `customerservice@360lion.com`.
- **E-mail geral / comercial:** `info@360lion.com`.
- **Telefone da sede em Shenzhen:** `+86 755 2360 1325` (Xangai `+86 21 6142 1360`, Guangzhou `+86 20 8653 4133`).
- **Endereço da sede:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, China.

> Desconfie de mensagens falsas por SMS ou WhatsApp exigindo pagamento imediato em um link desconhecido para "liberar" uma compra internacional na alfândega. Confirme qualquer taxa real diretamente na página do pedido ou no portal oficial dos correios ou da Receita do seu país. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Rastrear uma encomenda 360Lion com o Peek

O [Peek](/) consulta a 360Lion automaticamente quando você informa um código `WSH...YQ` (como `WSHMX4829173640YQ`, dígitos inventados) — em que a 360Lion é a única candidata — ou um código `AR` + 10 dígitos (como `AR4829173640`), em que o Peek consulta a 360Lion junto com [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz) e mostra "360Lion está com esta encomenda." quando a 360Lion confirma o envio. Colar um link `toms.360lion.com/track?trackNumber=` seleciona a 360Lion na hora; caso contrário, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela da encomenda e escolha 360Lion (buscar por "360Lion Express", "WSH" ou "纬狮物流" também encontra).

O Peek lê diretamente o rastreador da 360Lion e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Devolvida ao remetente" ou "Entregue"), o texto de cada leitura (da transportadora local quando repassado pela 360Lion, ou em inglês da própria 360Lion), o local como nome de país ou sigla de aeroporto de três letras, o país de destino e a hora da entrega. Cada leitura vem com a diferença do local em relação ao UTC, como `+8` na China e `-3` na Argentina, e aparece no horário do seu aparelho. O Peek omite a barra de progresso do site, o país de origem, o contador de dias em trânsito e o comprovante de entrega; o rastreador da 360Lion não informa data prevista de entrega, nome do serviço nem peso.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos enquanto o rastreador da 360Lion responde. Fora dessas situações, verifica a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a checar a cada hora, como quer que você a acompanhe, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da 360Lion não responde e o Peek acompanha o pacote apenas por serviços de rastreamento, ele verifica a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados.

Quando a leitura mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia dessa leitura, como "Sem atualizações desde seg 12 out". A tela da encomenda então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando 360Lion.", com o link "Pedir ajuda a 360Lion" levando à página de contato da empresa. Após três semanas sem novas leituras, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando 360Lion."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não pode liberar pacotes na alfândega nem contatar a 360Lion ou a transportadora local em seu nome.

## Perguntas frequentes sobre o rastreamento 360Lion

### O que é a 360Lion Express?

A 360Lion (fundada em 2015 em Shenzhen, China) é uma operadora de logística transfronteiriça focada em linhas aéreas de pequenos pacotes da China para a América Latina e os Estados Unidos, usada por vendedores do Mercado Livre, Amazon, AliExpress, Shopee, SHEIN e Temu.

### O que significam os códigos `WSH...YQ` e `AR...`?

`WSH` mais duas letras do país, 10 dígitos e `YQ` (por exemplo `WSHMX4829173640YQ`, dígitos inventados) é o formato de linha dedicada internacional da 360Lion, enquanto `AR` mais 10 dígitos (`AR4829173640`) é usado em encomendas da 360Lion com destino à Argentina.

### Quem faz a entrega local das encomendas da 360Lion?

A 360Lion transporta os pacotes de avião da China até o aeroporto do seu país e os repassa (`handover to lastmile`) a um serviço postal ou transportadora local para entrega no endereço. As leituras do parceiro local aparecem dentro do histórico da 360Lion.

### Como entrar em contato com a 360Lion sobre uma encomenda perdida?

Procure primeiro o vendedor ou o marketplace para garantia de reembolso e proteção ao comprador. Você também pode escrever para o suporte de rastreamento em `customerservice@360lion.com` ou ligar para a sede em Shenzhen no `+86 755 2360 1325`.

:::sources
- [360Lion Express: Página inicial em inglês](https://www.360lion.com/en/) – fundada em 2015, filiais em Shenzhen/Xangai/Guangzhou/Yiwu/Dongguan/Hong Kong, rastreador para 50 códigos, marketplaces parceiros e endereço da sede
- [360Lion: Página inicial em chinês](https://www.360lion.com/) – rotas dedicadas de pequenos pacotes para 15 países da América Latina e EUA, capacidade aérea fretada e armazéns no exterior
- [360Lion: Fale Conosco](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, telefones de Shenzhen/Guangzhou/Xangai e endereços (URL de ajuda vinculada pelo Peek)
- [360Lion: Sobre nós](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – visão geral da empresa
- [360Lion: Pequenos pacotes dedicados](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – serviço internacional B2C de encomendas
- [360Lion: Perguntas frequentes e termos](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – regras comerciais de transporte, itens proibidos e prazo de 30 dias para reclamações de lojistas
- [360Lion: Portal de rastreamento](https://toms.360lion.com/track) – página pública de consulta de rastreio
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – detecção dos formatos `WSH` e `AR`, regras de link `toms.360lion.com`, códigos `WSO`/`WSD`, fusos explícitos, campos omitidos e URL de ajuda
:::
