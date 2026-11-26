---
title: Rastreamento YDH Express: códigos YQ e AZ, repasses e ajuda
description: Rastreie encomendas internacionais YDH Express da China: códigos YDH...YQ e YDH...AZ, eventos DSD e CARR, entrega local, alfândega e suporte.
slug: ydh-tracking
published: 2026-11-26
updated: 2026-11-26
---

A YDH (*YDH Express* / *YDH Cross Border*, em chinês *义达跨境*) é uma operadora chinesa de logística de e-commerce internacional fundada em 2005 e sediada em Xangai. Atendendo mais de 20 polos chineses de e-commerce transfronteiriço — entre eles Shenzhen, Guangzhou, Hangzhou, Ningbo e Hong Kong — e com escritórios no Japão, Estados Unidos, Reino Unido e Bélgica, a YDH transporta pequenos pacotes e carga comercial para mais de 200 países e regiões antes de repassá-los a parceiros locais de entrega. Você pode consultar um envio no [portal de rastreamento da YDH](https://track.ydhex.com/track_query.aspx) ou colar seu código na caixa acima.

## Códigos de rastreamento YDH: formatos YQ e AZ

Verifique os detalhes do pedido no marketplace ou o e-mail de envio. Os códigos de rastreamento da YDH têm 15 caracteres, começando com `YDH`, seguidos por 10 dígitos e terminando em `YQ` ou `AZ`:

| Formato (fictício) | O que é |
| --- | --- |
| `YDH4829173640YQ` | Código internacional YDH: `YDH` + 10 dígitos + `YQ`. |
| `YDH4829173640AZ` | Código internacional YDH: `YDH` + 10 dígitos + `AZ`. |

Após a liberação na alfândega de destino, a YDH entrega o pacote a uma transportadora local: [USPS](carrier:usps) ou [GOFO](carrier:gofo) nos Estados Unidos e [Poste Italiane](carrier:poste-italiane) na Itália estão entre aquelas cujas leituras a YDH repassa sob seu código `YDH`. A YDH também pode informar o número de rastreamento próprio da transportadora local. Para outros padrões internacionais, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status e códigos da YDH

A YDH associa códigos curtos às suas próprias leituras de armazém, exportação e embarque aéreo (em chinês ou inglês) e também retransmite as leituras do parceiro de destino no texto original dele.

| Código / texto YDH | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Dados eletrônicos do pedido recebidos do vendedor antes de a YDH ler o pacote físico. | "Etiqueta criada" |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Encomenda chegou ao armazém ou centro de triagem da YDH na China. | "Em trânsito" |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | Aguardando embarque aéreo, partiu da China (ou atraso de voo) e pousou no aeroporto de destino. | "Em trânsito" |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Liberada pela alfândega de destino, entregue à transportadora de última milha, aguardando a primeira leitura desse parceiro (`D-SCAN DELAY`) ou coletada por ele. | "Em trânsito" |
| `OD` (`Out for Delivery`) | Saiu com a transportadora de destino para entrega final. | "Saiu para entrega" |
| `DD` | Exceção ou tentativa falha de entrega informada pelo parceiro local. | "Tentativa de entrega" / "Precisa de atenção" |
| `CC` | Entregue pelo parceiro de destino (com nome de quem assinou quando disponível). | "Entregue" |

**O que significa `D-SCAN DELAY` (`DSD`).** Quando a YDH entrega um lote consolidado de encomendas a um correio ou transportadora local (`CARR`), vários dias podem passar até esse parceiro bipar individualmente o pacote na própria rede. `D-SCAN DELAY` sinaliza esse intervalo de transferência — a encomenda continua em caminho e não está perdida.

## Como funciona a entrega internacional da YDH

Em [ydhex.com](https://www.ydhex.com/), a YDH informa trabalhar com mais de 50 companhias aéreas e marítimas e operar desembaraço aduaneiro em 12 países. Sua [página de contato](https://www.ydhex.com/product/59/) lista escritórios no Japão (Tóquio, Narita, Osaka, Kansai e Nagoya), Estados Unidos (La Puente, Califórnia), Reino Unido (Hounslow) e Bélgica (Grâce-Hollogne, perto de Liège).

**Entrega na última milha.** A YDH cuida da coleta na exportação, transporte aéreo e desembaraço aduaneiro, transferindo depois o pacote (`CARR`) a um correio ou transportadora local no seu país para entrega em domicílio, ponto de retirada ou devolução. As tentativas de entrega, locais seguros e prazo de guarda seguem as regras do parceiro local.

**Alfândega e taxas de importação.** Pedidos internacionais enviados pela YDH estão sujeitos ao IVA de importação, imposto aduaneiro e eventuais taxas locais do seu país, salvo pagamento prévio no checkout da loja ou marketplace. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda YDH parada, sem atualização ou não entregue

Como o contrato de transporte da YDH é com a loja online ou marketplace e não com o comprador, contate sempre primeiro o vendedor ou abra chamado na plataforma para atrasos, correção de endereço ou reembolso.

- **Intervalos sem leitura durante o voo ou transferência:** pausas de vários dias são normais enquanto pacotes aguardam espaço em voo (`HBYP`), passam pela alfândega (`RC`) ou esperam a primeira leitura do parceiro local (`DSD` / `D-SCAN DELAY`). Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Use o código local após o repasse:** assim que a YDH mostrar o número próprio da transportadora local, o site dela pode oferecer opções de entrega indisponíveis no rastreador da YDH.
- **Entregue, danificada ou sumida:** se aparecer `CC` / entregue sem o pacote ter chegado, verifique com moradores, vizinhos, portaria e a transportadora local, avisando rapidamente o vendedor. Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da YDH

Dados oficiais publicados na [página Contact Us da YDH](https://www.ydhex.com/product/59/):

- **Centrais de atendimento na China:** `800-988-1378` / `400-888-1378`.
- **Atendimento em Xangai:** `(+86) 21-52277600` / `(+86) 21-52277688` (Sede: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Xangai 201100, China).
- **Escritório em Hong Kong:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Escritório no Japão (Tóquio):** `+81 3-5644-5733`.
- **Escritório na Bélgica (Liège):** `+32 470 588 200`.

> Desconfie de links não solicitados por SMS ou e-mail pedindo pequeno pagamento no cartão para "liberar alfândega" ou "atualizar endereço" de um pacote vindo da China. Confirme qualquer cobrança tributária legítima na página do seu pedido no marketplace ou no site oficial do correio do seu país. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda YDH com o Peek

O [Peek](/) reconhece automaticamente com alta confiança `YDH` + 10 dígitos + `YQ` ou `AZ` (`YDH4829173640YQ` e `YDH4829173640AZ`, dígitos fictícios) como YDH, além de links colados de `track.ydhex.com/track_query.aspx?track_number=`. Se precisar escolher a YDH manualmente, toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela da encomenda (buscar "YDH Express", "YDH Cross Border" ou "义达跨境" também encontra).

O Peek lê diretamente o rastreador da YDH e mostra a etapa ("Etiqueta criada", "Em trânsito", "Saiu para entrega", "Tentativa de entrega", "Precisa de atenção", "Entregue"), o texto de cada leitura — da YDH ou da transportadora local — com o local e o país de destino. Códigos como `CARR` e `DSD` definem a etapa e não são exibidos pelo Peek. A YDH data leituras na China no horário de Xangai e leituras do parceiro local no fuso de cada local; o Peek converte tudo para o relógio do seu dispositivo. Quando a YDH informa o código próprio da transportadora local e o Peek a reconhece, ele confirma se ela já tem o pacote: a página então mostra "Entrega por" com o nome da transportadora e lista ambos os códigos. O nome de quem assinou fica fora de todos os apps do Peek, que também omite referência do cliente e rótulo de parceiro da YDH. O rastreador da YDH não informa data estimada de entrega, peso, nome do serviço, ponto de retirada nem foto de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a YDH mostra `OD`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da YDH não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página então informa "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando YDH.", com o link "Obter ajuda da YDH" para a página de contato oficial. Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando YDH."

Você pode escolher alertas para "Cada leitura", "Etapas importantes" ou "Apenas entrega". O Peek monitora apenas atualizações de rastreamento e não pode contatar a YDH, liberar alfândega nem redirecionar sua encomenda com o parceiro local.

## Perguntas sobre o rastreamento da YDH

### O que é a YDH Express?

A YDH (*义达跨境*) é uma provedora chinesa de logística de e-commerce internacional sediada em Xangai e fundada em 2005, que envia pequenos pacotes da China para mais de 200 países e os repassa a correios ou transportadoras locais para a entrega final.

### O que significam os códigos de rastreamento `YDH...YQ` e `YDH...AZ`?

Ambos são formatos de 15 caracteres da YDH Express compostos por `YDH`, 10 dígitos e final `YQ` ou `AZ` (por exemplo `YDH4829173640YQ` ou `YDH4829173640AZ`, dígitos fictícios).

### O que significa `D-SCAN DELAY` (`DSD`) no rastreamento da YDH?

`D-SCAN DELAY` indica que a YDH já transferiu o pacote rumo à transportadora de última milha no país de destino, mas esse parceiro ainda não registrou a primeira leitura de entrada. A remessa continua em trânsito aguardando a recepção local.

### Como entro em contato com o suporte da YDH?

Procure primeiro o vendedor online ou marketplace para reembolsos ou problemas de entrega. Para consultas diretas, a YDH divulga centrais na China (`800-988-1378` / `400-888-1378`), Xangai (`+86 21-52277600` / `7688`), Hong Kong (`+852 2690-1686`, `hk@ydhexpress.com`), Japão (`+81 3-5644-5733`) e Bélgica (`+32 470 588 200`) em [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH: Site oficial](https://www.ydhex.com/) – fundada em 2005, 20+ polos chineses, 200+ países/regiões, 50+ companhias aéreas e marítimas e desembaraço em 12 países
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – sede em Xangai, centrais na China (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), escritórios em Hong Kong, Japão, EUA, Reino Unido e Bélgica (URL de ajuda vinculada pelo Peek)
- [YDH: Portal de Rastreamento](https://track.ydhex.com/track_query.aspx) – consulta pública de encomendas YDH
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – detecção `YDH` + 10 dígitos + `YQ`/`AZ`, regra de link `track.ydhex.com`, códigos de eventos (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), código da transportadora local, fusos e URL de ajuda
:::
