---
title: Rastreamento Chit Chats: códigos de 10 dígitos e USPS
description: Rastreie encomendas Chit Chats do Canadá para EUA, Canadá ou exterior: códigos de 10 caracteres, repasse USPS/Canada Post, atrasos e 1-844-842-8777.
slug: chit-chats-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Chit Chats** (*Chit Chats Express*) é uma empresa canadense de envios para e-commerce fundada em 2001 por Derek Nolan. Ela conta com 66 unidades no Canadá, aceita pacotes também via postagem (SmartDrop) e coleta, e já enviou mais de 50 milhões de encomendas para mais de 100.000 remetentes ([chitchats.com](https://chitchats.com/)). Transportadoras parceiras realizam a entrega final: seu rastreador informa qual parceira assumiu e fornece o número dela, como [USPS](carrier:usps) nos Estados Unidos ou [Canada Post](carrier:canada-post) no Canadá. Você pode acompanhar um envio em [chitchats.com/tracking](https://chitchats.com/tracking) ou colar seu ID de 10 caracteres na caixa acima.

## Códigos de rastreamento Chit Chats (ID de envio de 10 caracteres)

Cada pedido da Chit Chats recebe um **ID de envio (*shipment ID*)** alfanumérico de 10 caracteres em maiúsculas (combinando letras e dígitos, geralmente começando com uma letra e um dígito diferente de zero):

| Formato (fictício) | O que é |
| --- | --- |
| `K4N8V3T6R2` | ID de envio padrão de 10 caracteres da Chit Chats. |
| `P7M3X9D5Q8` | ID alternativo de 10 caracteres da Chit Chats para envios aos EUA, Canadá ou exterior. |

Assim que a Chit Chats repassa o pacote à parceira de última milha, a página de rastreamento exibe também o código próprio dela — como um código de barras Intelligent Mail do [USPS](carrier:usps) (que pode incluir o prefixo de roteamento `420` + ZIP, que o Peek remove) ou um código do [Canada Post](carrier:canada-post). Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Chit Chats

O rastreador da Chit Chats mostra primeiro suas próprias leituras e depois as da transportadora parceira ([USPS](carrier:usps), [Canada Post](carrier:canada-post) ou outra):

| A Chit Chats ou parceira informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | O lojista comprou a etiqueta online, mas ainda não entregou o pacote em uma unidade Chit Chats. | "Etiqueta criada" |
| `Received by Chit Chats` | Pacote físico recebido e bipado em uma unidade ou ponto de entrega canadense da Chit Chats. | "Recebida pela transportadora" |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | Triado em uma unidade da Chit Chats; `In transit` marca a transferência para a transportadora parceira. | "Em trânsito" |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | Integrado à rede de triagem da transportadora parceira (USPS, Canada Post ou parceira internacional). | "Em trânsito" / "Recebida pela transportadora" |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | Em movimento pelas unidades domésticas ou internacionais da transportadora parceira. | "Em trânsito" |
| `Out for Delivery`, `Item out for delivery` | Saiu com a transportadora parceira local para entrega em domicílio hoje. | "Saiu para entrega" |
| `Notice card left indicating where and when to pick up item` | A transportadora parceira tentou entregar sem ninguém em casa e deixou aviso de retirada. | "Tentativa de entrega" |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | Aguardando retirada em uma agência postal (mesmo o aviso final indica que o pacote ainda espera retirada, sem ter sido devolvido). | "Pronta para retirada" |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Entregue no endereço do destinatário pela transportadora parceira. | "Entregue" |

## Entrada nos EUA, alfândega e repasse a parceiras

- **Envios para os Estados Unidos:** a Chit Chats oferece envio aos EUA com desembaraço aduaneiro, modalidade DDP (impostos pagos) e suporte à documentação CUSMA, informa prazo estimado de 2–6 dias úteis e publica atualizações de tarifas americanas de 2026 em [chitchats.com](https://chitchats.com/). Veja [encomendas retidas na alfândega](guide:customs).
- **Rastreamento na parceira:** após o repasse pela Chit Chats, sua página de contato recomenda usar o código da transportadora parceira diretamente no site dela.

## Encomenda Chit Chats atrasada, parada ou extraviada

- **Contate o vendedor primeiro:** a [página de contato](https://chitchats.com/contact) da Chit Chats informa que sua equipe vê exatamente as mesmas informações de rastreamento que você e só pode orientar o remetente em questões de conta, portanto destinatários devem falar com o vendedor.
- **Pacote atrasado:** depois que o prazo estimado passa, a página de rastreamento da Chit Chats orienta contatar o vendedor. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Entregue ou desaparecido:** confira caixa de correio, entrada ou agência postal local (especialmente se o Canada Post deixou aviso) e fale rapidamente com o vendedor. Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos no Canadá da Chit Chats

Canais oficiais divulgados em [Chit Chats Contact Us](https://chitchats.com/contact) e [Chit Chats Support](https://support.chitchats.com/en/support/home):

- **Telefone gratuito (inglês e francês — faturamento e unidades):** `1-844-842-8777` (além dos telefones individuais das 66 unidades canadenses em horário local).
- **Portal de ajuda e suporte:** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) e [chitchats.com/contact](https://chitchats.com/contact).
- **Rastreador público:** [chitchats.com/tracking](https://chitchats.com/tracking).

> Cuidado com mensagens SMS falsas cobrando pequeno pagamento online para liberar um envio Canadá/EUA. Confirme qualquer cobrança aduaneira ou postal diretamente com o vendedor ou no rastreamento oficial do USPS/Canada Post. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda Chit Chats com o Peek

O [Peek](/) consulta a Chit Chats automaticamente como candidata única quando você informa um ID de envio Chit Chats de 10 caracteres (`K4N8V3T6R2` ou `P7M3X9D5Q8`, fictícios) e exibe "Chit Chats está com esta encomenda." quando ela confirma; também reconhece links colados de `chitchats.com/tracking/<10-caracteres>`. Se precisar escolher manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda (buscar "ChitChats" ou "Chit Chats Express" também encontra).

O Peek lê diretamente o rastreador da Chit Chats e mostra a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Entregue"), o texto das leituras da Chit Chats e da parceira e a cidade sem código postal (locais com número de rua são omitidos). Mostra ainda a data prevista de entrega, o peso em quilogramas e as dimensões. O nome do destinatário aparece em "Destinatário" na sua página do pacote, e a cidade de destino pode encerrar a rota no seu mapa enquanto em trânsito; links compartilhados não exibem nenhum dos dois. Quando a Chit Chats indica uma parceira conhecida pelo Peek, como USPS ou Canada Post, com o número dela (no USPS sem o prefixo ZIP de roteamento), o Peek verifica se ela já tem o pacote: a página então mostra "Entrega por" seguido do nome e lista ambos os números. As leituras próprias da Chit Chats trazem horário exato; já as da parceira vêm marcadas pela Chit Chats como horário do Pacífico mesmo podendo divergir várias horas, então o Peek aplica o fuso do local (Nova York para `NEW YORK, NY`, Toronto para `TORONTO, ON`), usa o da leitura vizinha sem local ou Vancouver por padrão, convertendo tudo para o relógio do seu dispositivo. O Peek omite nome do serviço, códigos de status e cidade do remetente.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek verifica até a cada 10 minutos; no dia em que aparece `Out for Delivery` ou `Item out for delivery`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da Chit Chats não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Chit Chats.", com o link "Pedir ajuda a Chit Chats" para a página de contato da Chit Chats. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Chit Chats."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek monitora apenas atualizações de rastreamento e não pode abrir pedidos de indenização, editar endereços nem contatar a Chit Chats ou suas parceiras por você.

## Perguntas sobre o rastreamento da Chit Chats

### Como é um código de rastreamento da Chit Chats?

Os IDs de envio da Chit Chats têm 10 letras maiúsculas e dígitos (como `K4N8V3T6R2` ou `P7M3X9D5Q8`, fictícios). Após o repasse, o pacote recebe também um código da transportadora parceira (como USPS ou Canada Post).

### Quem entrega uma encomenda Chit Chats na minha casa?

Uma transportadora parceira, como o [USPS](carrier:usps) nos Estados Unidos ou o [Canada Post](carrier:canada-post) no Canadá. O rastreador da Chit Chats informa a parceira e seu código de rastreamento.

### Por que minha encomenda Chit Chats pausou após `Departed from Chit Chats facility`?

O rastreador da Chit Chats não detalha o motivo. O evento `In transit` marca a entrega à transportadora parceira, e as novas leituras aparecem assim que ela bipa o volume. Se o prazo estimado já passou, fale com o vendedor.

### Quem devo contatar se meu pacote Chit Chats sumir?

Fale com o vendedor que despachou a encomenda. Conforme [chitchats.com/contact](https://chitchats.com/contact), a equipe da Chit Chats só pode orientar o remetente sobre questões da conta; o telefone `1-844-842-8777` atende faturamento, unidades e suporte em francês.

:::sources
- [Chit Chats: Site oficial](https://chitchats.com/) – consolidadora canadense de envios de e-commerce para EUA, Canadá e exterior
- [Chit Chats: Sobre nós](https://chitchats.com/about) – fundada em 2001 por Derek Nolan, mais de 50 milhões de pacotes, 66 unidades no Canadá e mais de 100 mil remetentes
- [Chit Chats: Portal de Rastreamento](https://chitchats.com/tracking) – página de consulta por ID de envio de 10 caracteres
- [Chit Chats: Fale conosco](https://chitchats.com/contact) – telefone gratuito `1-844-842-8777`, política de destinatário contatar o vendedor e diretório de unidades (URL de ajuda vinculada pelo Peek)
- [Chit Chats: Portal de Ajuda e Suporte](https://support.chitchats.com/en/support/home) – central de ajuda oficial
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – detecção de ID de 10 caracteres, regra de link `chitchats.com/tracking/`, mapa de status próprios e retransmitidos, extração de ETA/peso/dimensões e remoção do prefixo `420` do USPS
:::
