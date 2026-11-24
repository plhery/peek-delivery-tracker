---
title: Rastreamento Cainiao (AliExpress): códigos, status e ajuda
description: Rastreie encomendas Cainiao do AliExpress no Brasil: códigos LP, CNG e CNBR, significado dos status, prazos, alfândega e suporte.
slug: rastreamento-cainiao
published: 2026-11-24
updated: 2026-11-24
---

A Cainiao é o braço de logística do grupo Alibaba: ela envia pacotes internacionais para vendedores de plataformas como o AliExpress, e seu site de rastreamento, [global.cainiao.com](https://global.cainiao.com/), acompanha exclusivamente encomendas da Cainiao e do AliExpress. Digite o código lá ou na caixa acima. No AliExpress, o número fica em Meus Pedidos: abra o pedido e clique em Rastrear.

## Código de rastreamento Cainiao: formatos comuns

Nenhuma página da Cainiao lista formalmente os formatos `LP`, `CNG`, `DOFR` ou `CNFR`. O site aceita apenas letras e números, usando como exemplo um código postal terminado em `HK`. Veja os padrões encontrados na prática (com dígitos inventados):

| Código (inventado) | A quem pertence |
| --- | --- |
| `LP48291736405812` | `LP` seguido de 14 dígitos: provável Cainiao, embora não exclusivo. |
| `CNG48291736405812` | `CNG` seguido de 14 dígitos: Cainiao. |
| `DOFR4829173640581HD` | `DOFR` ou `CNFR`, 13 dígitos e `HD`: Cainiao. |
| `RR482917367CN` | [Código postal](guide:tracking-number-formats): o site da Cainiao rastreia esse formato, assim como o correio de origem, aqui a [China Post](carrier:china-post). |
| `YT4829173640581926` | Não é Cainiao, e sim [YunExpress](carrier:yunexpress). |

O rastreamento da Cainiao também responde a outros padrões, como `AP` + 14 dígitos e códigos iniciados por `CNBR` usados em envios ao Brasil. Fora do AliExpress, procure o código no e-mail da loja ou consulte [como encontrar o código de rastreamento](guide:find-tracking-number). Se o portal não mostrar resultados, a Cainiao orienta confirmar se o número é realmente da Cainiao ou do AliExpress.

## Significado dos status de rastreamento da Cainiao

As principais movimentações, na ordem habitual:

| A Cainiao informa | O que significa para você |
| --- | --- |
| `Shipment information received by warehouse electronically` | O armazém do vendedor recebeu os dados eletrônicos do pedido. |
| `Warehouse packaging complete` | O armazém embalou o pedido; nenhuma transportadora coletou ainda. |
| `Received by logistics company` | Primeira leitura física pela transportadora. |
| `Export customs clearance complete` | Desembaraço de exportação concluído; pacote em trânsito. |
| `Departed from departure country/region` | Saiu do país ou região de origem. |
| `Import customs clearance started` | Em fiscalização aduaneira no país de destino (veja [alfândega](guide:customs)). |
| `Received by local delivery company` | Entregue à transportadora local responsável pela última milha, ainda não a você. |
| `Out for delivery` | Saiu para entrega final. |
| `Delivery attempt unsuccessful.Unable to deliver to mailbox` | Tentativa de entrega sem sucesso; o motivo vem após a primeira frase. |
| `Arrived at pick-up point. Package available for collection.` | Disponível para retirada em ponto de coleta. |
| `Package delivered` | Entregue a você ou retirado por você. |

`Carrier update` é uma atualização geral indicando pacote em trânsito. `Accepted for transportation by postal service` significa que um correio parceiro assumiu após o repasse, não uma primeira coleta; `Delivery Carrier Accepted` confirma que a transportadora local recebeu o pacote. A chegada ao ponto de retirada registra a assinatura do próprio estabelecimento, e `Package delivered` surge quando você faz a retirada. `Processing delay at sorting center` indica atraso operacional, não extravio; `Delivery failed` encerra a entrega após as tentativas; e `Shipment cancelled` mostra que o pedido de coleta do vendedor foi cancelado antes do embarque (exibido como "Precisa de atenção" no Peek). Veja também [o que significam os status de rastreamento](guide:tracking-statuses).

## Tentativa sem sucesso, pontos de retirada e alfândega no Brasil

A Cainiao informa operar entrega local própria em nove países e regiões — citando Espanha, França e Polônia, onde possui armários próprios —, mas não nomeia um único parceiro local para todos os destinos. Após o repasse no país de destino, o botão "View Contact Information" da página de rastreamento exibe os dados da transportadora local quando disponíveis.

O aviso de rastreamento da Cainiao ressalva que fretes econômicos "não incluem rastreamento após o pacote ser entregue à transportadora do país de destino". Para saber qual empresa assumiu, consulte o vendedor ou veja [como rastrear encomendas da China](guide:tracking-from-china).

Na terminologia da Cainiao, `Undelivered` abrange destinatário ausente, endereço não localizado ou reagendamento solicitado, orientando contatar a transportadora para nova tentativa ou retirada. `Awaiting Collection` e `First Notification to recipient` indicam que o pacote aguarda coleta — retire quanto antes para evitar devolução ao remetente. Na Espanha, onde a Cainiao opera com os contatos da Ecoscooting, o FAQ da Ecoscooting informa nova tentativa em até 3 dias úteis e guarda por 14 dias em pontos de retirada ou armários com envio de PIN por e-mail ou SMS.

**Alfândega no Brasil.** Em compras do AliExpress enviadas ao Brasil, a tributação segue o [Programa Remessa Conforme da Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona): em compras certificadas, o Imposto de Importação e o ICMS são recolhidos no checkout; fora do programa, os tributos são cobrados na entrada no país.

## Rastreamento Cainiao sem atualização ou pacote perdido

**Sem histórico ainda.** O FAQ da Cainiao diz que o rastreio costuma aparecer em até 11 dias após o envio; sem avanços após 12 dias, recomenda falar com o vendedor. `Not Found` também pode indicar código digitado errado ou pacote ainda não bipado.

**Parado após chegar ao seu país.** Segundo o FAQ da Cainiao, geralmente o correio ou parceiro local ainda não enviou novas leituras; endereço ou telefone incorretos e retenção na alfândega também podem travar a remessa. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Muito atrasado ou perdido.** A Cainiao pede aguardar até a data limite prometida de entrega; depois disso, em pedidos do AliExpress, abra atendimento em Central de Ajuda ou Disputas e Relatórios. Não há formulário de indenização da Cainiao para compradores: reembolsos ocorrem via AliExpress ou vendedor.

**Consta como entregue, mas não chegou.** Fale primeiro com o remetente ou a transportadora local; em compras do AliExpress, use o link da Central de Ajuda no pedido. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Devolvido.** `Unclaimed` e `Storage term expired` indicam prazo de retirada vencido; `Unsuccessful delivery` aponta problema de endereço ou telefone. Pergunte ao vendedor sobre reenvio ou estorno.

## Atendimento ao cliente e contato da Cainiao

A página de contato em inglês da Cainiao não divulga telefone de suporte ao consumidor; ela informa:

- **E-mail:** `cainiaoglobalcs@service.cainiao.com` (listado como "Customer Service", sem especificar público atendido).
- **Pedidos AliExpress:** link para o atendimento do AliExpress, cuja Central de Ajuda oferece "Online Service" 24/7.

Os telefones publicados no site chinês atendem a China e lojistas. Na Espanha, a Ecoscooting lista `+34 912 15 93 69`, a linha gratuita `+34 800 00 03 69` e `customersupport@ecoscooting.com` (segunda a sexta das `9:00` às `18:00`, fins de semana das `9:00` às `13:00`); em Portugal, `+351 300 609 006`. Para pedidos enviados ao Brasil, o caminho mais rápido é o vendedor ou o suporte do AliExpress e, na etapa final, a transportadora local exibida no rastreamento.

## Rastrear uma encomenda Cainiao com o Peek

O [Peek](/) exibe essas remessas como "AliExpress / Cainiao". Ele reconhece imediatamente códigos `DOFR`, `CNFR`, `CNG` e `AP`; para códigos `LP` e referências como `CNUSUP`, `CNBR` ou `CNMEX`, consulta a Cainiao entre as transportadoras compatíveis. Códigos postais digitados isoladamente vão ao correio correspondente, exceto os terminados em `TY`, `MI`, `XX` ou `GO`, para os quais a Cainiao também é consultada. Para outros códigos postais, colar o link de `global.cainiao.com` com `mailNoList=` direciona direto à Cainiao (links de `track.cainiao.com` ou páginas de pedido do AliExpress não são reconhecidos).

Em alguns envios, o AliExpress informa já o número da transportadora de destino (como um código SDA para a Itália) enquanto o pacote ainda está com a Cainiao. Nesses casos, o Peek consulta ambas e mantém o pacote em AliExpress / Cainiao, incorporando as leituras locais assim que surgem.

O Peek verifica a Cainiao até a cada 30 minutos, inclusive quando sai para entrega (ou a cada hora após uma consulta sem resposta), marca cada movimentação no mapa e oferece alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Quando outra transportadora assume a entrega, o Peek passa a seguir o ritmo dela. Após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), checa de hora em hora. Quando acompanha via serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. O Peek exibe etapa, histórico, local da leitura quando informado, janela prevista de entrega, hora da entrega e código local quando divulgado pela Cainiao, mas não pode contatar a Cainiao nem abrir disputa em seu nome.

## Perguntas frequentes sobre o rastreamento Cainiao

### Onde está meu pacote da Cainiao?

A leitura mais recente indica a etapa atual e a cidade, quando informada. Depois de `Received by local delivery company`, uma transportadora do seu país já está com o pacote; se o rastreio econômico parar aí, pergunte ao vendedor qual empresa assumiu.

### A Cainiao é confiável?

Sim. A Cainiao é o grupo logístico do Alibaba, fundado em 2013 em Hangzhou, na China, e é o portal oficial indicado pelo AliExpress aos seus compradores.

### Quanto tempo a Cainiao demora para entregar?

Depende do frete escolhido. Segundo estimativas da Cainiao voltadas a vendedores (não garantias individuais): *Premium* leva de 5 a 10 dias corridos para destinos prioritários (como Espanha, Holanda, Reino Unido e Bélgica), *Standard* de 10 a 18 dias (chegando a 7 dias em rotas rápidas) e *Economy* de 20 a 45 dias.

### Por quanto tempo a Cainiao guarda encomendas em pontos de retirada?

A Cainiao não informa um prazo global e recomenda retirar imediatamente para evitar devolução. Na Espanha, o FAQ da Ecoscooting indica guarda de 14 dias em seus pontos de retirada e armários.

:::sources
- [Cainiao: Global Express Tracking](https://global.cainiao.com/) – rastreamento exclusivo Cainiao/AliExpress e exemplo de formato
- [Cainiao: Guidance](https://global.cainiao.com/helpDoc.htm?slug=ofzig4) – regra de letras e dígitos
- [Cainiao: Logistics Status Explanation](https://global.cainiao.com/helpDoc.htm?slug=phdx0i) – significados de status e orientação de retirada
- [Cainiao: FAQ](https://global.cainiao.com/helpDoc.htm?slug=bp5fbp) – atrasos, alfândega, devoluções e disputas
- [Cainiao: About Cainiao](https://www.cainiao.com/en/about-us-brief-introduction.html) – grupo Alibaba, fundação em 2013 e nove países com entrega local
- [Cainiao: Overseas Local](https://www.cainiao.com/en/global-local-capabilities.html) – operações e armários na Espanha, França e Polônia
- [Cainiao: Cross-border Express Delivery](https://www.cainiao.com/en/global-express.html) – prazos Premium, Standard e Economy
- [Cainiao: Contact Cainiao](https://www.cainiao.com/en/about-us-contact-us.html) – e-mail `cainiaoglobalcs@service.cainiao.com` e link do AliExpress
- [Cainiao: 联系我们](https://www.cainiao.com/about-us-contact-us.html) – telefones chineses para lojistas
- [Cainiao España: Sobre Cainiao](https://es.cainiao.com/about-us.html) – rede própria e contato na Espanha
- [Ecoscooting: Delivery](https://ecoscooting.com/) – linhas em Espanha e Portugal, nova tentativa em 3 dias e guarda de 14 dias
- [AliExpress: How to track my package?](https://service.aliexpress.com/page/knowledge?pageId=82&knowledge=1060063468&language=en) – localização do código em Meus Pedidos e Online Service
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – tributação de remessas internacionais no Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – comportamento no Peek e tratamento de `Shipment cancelled`
:::
