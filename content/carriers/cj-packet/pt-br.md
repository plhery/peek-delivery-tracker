---
title: Rastreamento CJ Packet: códigos CJP…YQ, entregas e suporte
description: Rastreie encomendas CJ Packet (CJ Dropshipping) vindas da China: códigos CJP...YQ, status locais repassados, pausas aduaneiras e e-mail de suporte.
slug: cj-packet-tracking
published: 2026-11-26
updated: 2026-11-26
---

A CJ Packet (*CJPacket*) é o serviço internacional de envio e rastreamento vinculado ao [CJ Dropshipping](https://cjdropshipping.com/), transportando pequenos pedidos de e-commerce, volumes consolidados econômicos e remessas expressas a partir de armazéns de fulfillment na China para compradores no mundo todo por frete aéreo internacional e parceiros locais de última milha. Você pode consultar um envio diretamente em [cjpacket.com](https://www.cjpacket.com/) ou colar seu código de rastreamento na caixa acima.

## Códigos de rastreamento CJ Packet: formato CJP...YQ

Procure seu código CJ Packet no e-mail de confirmação de envio da loja virtual ou na página do pedido. Os códigos da CJ Packet começam por `CJP`, seguidos de 2 ou 3 letras (indicando a linha de transporte), 10 dígitos e `YQ` (17 ou 18 caracteres ao todo):

| Formato (inventado) | O que é |
| --- | --- |
| `CJPOS4829173640YQ` | Código CJ Packet de 17 caracteres (`CJP` + 2 letras da linha + 10 dígitos + `YQ`). |
| `CJPAEE4829173640YQ` | Código CJ Packet de 18 caracteres (`CJP` + 3 letras da linha + 10 dígitos + `YQ`). |

Assim que o pedido pousa no país de destino e passa pela alfândega, a CJ Packet transfere o volume para um correio nacional ou transportadora local — como [USPS](carrier:usps), [Canada Post](carrier:canada-post), [FedEx](carrier:fedex) ou [CTT Express](carrier:ctt-express) — e repassa o código local e as movimentações de entrega dentro do histórico `CJP...YQ`. Para outros prefixos chineses de e-commerce, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da CJ Packet

A CJ Packet combina um status resumido geral (`0` a `7`) com registros linha por linha do armazém da CJ, do agente de exportação e da transportadora local no destino:

| Resumo CJ Packet (código) / movimentação | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Processing` (`0`) / `Label created. Warehouse is processing this order.`, `Shipment info received` | Pedido embalado ou etiqueta gerada no armazém da CJ antes da coleta pelo agente de cargas. | "Etiqueta criada" |
| `Dispatched` (`1`) / `Item has been collected` | Encomenda transferida do armazém da CJ para o agente internacional. | "Recebida pela transportadora" |
| `En Route` (`2`), `Arrived Courier Facility` (`3`) / `Item Flight departure`, `Item Flight landing`, `Customs Clearance Completed`, `Delivery to local courier` | Saiu de avião da China para o país de destino, concluiu o desembaraço aduaneiro e foi entregue ao centro de triagem da transportadora local. | "Em trânsito" |
| `Inbound to customs temporary warehouse`, `In clearance processing - Import` | Em processo de desembaraço aduaneiro de importação no destino. | "Na alfândega" |
| `Out for Delivery` (`4`) / `Out for delivery` / `En reparto` | Saiu hoje com o entregador local. | "Saiu para entrega" |
| `Available for Pickup` (`5`) | Disponível para retirada em agência postal ou ponto de retirada local. | "Pronta para retirada" |
| `Unsuccessful Delivery` (`6`) | Não foi possível concluir a entrega (por exemplo, endereço incorreto ou destinatário ausente). | "Precisa de atenção" |
| `Delivered` (`7`) / `Package delivered`, `Delivered, At the door/Yard`, `Entregado` | Entregue pelo parceiro de destino (às vezes indicando quem recebeu). | "Entregue" |

Avisos como `EXPECTED DELIVERY DATE UPDATED` vindos de uma transportadora de destino são apenas estimativas informativas e não alteram a etapa real de transporte.

## Como funciona o transporte internacional da CJ Packet

Em [cjpacket.com](https://www.cjpacket.com/), a CJ Packet oferece três modalidades internacionais principais — *Small-package Transportation*, *Low-cost Bulk Packages* e *Fast Express* — combinando companhias aéreas parceiras, suporte aduaneiro e transportadoras locais no mundo inteiro.

**Quem faz a entrega na porta.** A partir de `Delivery to local courier`, o correio ou transportadora local que assume o envio define tentativas de entrega, local seguro, guarda em ponto de retirada e devoluções. O site da CJ Packet destina-se aos lojistas que despacham com ela: em caso de atraso, reembolso ou pedido de indenização, fale com a loja onde você comprou. Para IVA de importação, tributos e retenções alfandegárias (incluindo regras da UE para envios de fora do bloco), veja [encomendas retidas na alfândega](guide:customs).

## Encomenda CJ Packet parada, sem atualização ou marcada como entregue

Como o seu contrato de compra é com a loja virtual (não com o CJ Dropshipping nem com a CJ Packet), procure sempre o vendedor primeiro se o pedido atrasar, chegar danificado ou extraviar.

- **Pausas após `Label created` ou decolagem:** esperar vários dias enquanto o armazém da CJ embala o item (`Label created. Warehouse is processing this order.`) ou enquanto a carga aérea aguarda desembaraço no destino é comum em pequenos pacotes internacionais. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Use o código da transportadora local:** assim que a CJ Packet informa o código do parceiro local (como USPS, Canada Post ou outra transportadora), consultar diretamente esse parceiro pode mostrar endereços de retirada ou opções de reagendamento.
- **Entrega não realizada (`6`) ou marcada como entregue sem recebimento:** verifique com moradores, vizinhos, portaria e com a transportadora local primeiro, depois escreva ao vendedor imediatamente, antes que vençam os prazos de disputa do cartão ou marketplace. Veja [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contatos da CJ Packet

Contatos oficiais publicados em [cjpacket.com](https://www.cjpacket.com/) e [cjdropshipping.com](https://cjdropshipping.com/):

- **E-mail de suporte logístico:** `logistics@cjpacket.com` (suporte ao cliente 24/7 informado em [cjpacket.com](https://www.cjpacket.com/)).
- **Portal de rastreamento:** [cjpacket.com](https://www.cjpacket.com/).
- **Plataforma de dropshipping:** [cjdropshipping.com](https://cjdropshipping.com/) (suporte de fulfillment para lojistas).

> Ignore mensagens não solicitadas por SMS ou WhatsApp pedindo pagamento por cartão em link externo para "entregar" ou "liberar" uma encomenda. Verifique qualquer taxa de importação real na página do pedido na loja ou no site oficial dos correios do seu país. Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda CJ Packet com o Peek

O [Peek](/) reconhece automaticamente códigos `CJP` + 2 ou 3 letras + 10 dígitos + `YQ` (`CJPOS4829173640YQ` e `CJPAEE4829173640YQ`, dígitos inventados) como CJ Packet com alta confiança e reconhece links colados de `cjpacket.com/?trackingNumber=`. Se precisar selecionar manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda (buscar por "CJPacket" ou "CJ Dropshipping" também encontra).

O Peek lê diretamente o rastreador da CJ Packet e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Pronta para retirada", "Precisa de atenção", "Entregue"), os textos das movimentações do armazém, agente e transportadora local (removendo telefones, e-mails e canais de suporte), o local de cada movimentação e o país de destino. O nome do serviço/linha não aparece nos apps do Peek, e o nome de quem recebeu permanece privado. Movimentações com fuso explícito preservam o fuso; sem fuso, usam o relógio do local, tudo convertido para o relógio do seu aparelho (incluindo horário de entrega quando o registro final traz fuso). Quando a CJ Packet informa a transportadora local e seu código e o Peek reconhece essa empresa, o Peek verifica se ela tem o pacote: a encomenda então mostra "Entrega por" seguido do nome dela e lista os dois códigos. País de origem, códigos alternativos, referências de pedido/lojista e dados de contato ficam de fora; o rastreador da CJ Packet não informa data prevista, peso nem foto de entrega.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a CJ Packet exibe `Out for Delivery` (`4`), a cada 2 minutos a partir das 6h no relógio do local da movimentação mais recente enquanto o próprio rastreador da CJ Packet responder. Nos demais casos, verifica a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a verificar a cada hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da CJ Packet não responde e o Peek acompanha apenas por serviços de rastreamento, verifica a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega.

Quando a movimentação mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando CJ Packet." Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando CJ Packet."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não libera mercadorias na alfândega, não contata a CJ Packet nem solicita reembolsos à loja por você.

## Perguntas sobre o rastreamento da CJ Packet

### O que é a CJ Packet?

A CJ Packet é o serviço internacional de envio e rastreamento usado por lojistas que despacham pedidos pelo [CJ Dropshipping](https://cjdropshipping.com/) a partir de armazéns na China para compradores no mundo inteiro.

### O que significa um código `CJP...YQ`?

Códigos que começam com `CJP`, 2 ou 3 letras da rota, 10 dígitos e `YQ` (como `CJPOS4829173640YQ` ou `CJPAEE4829173640YQ`, dígitos inventados) são conhecimentos internacionais da CJ Packet.

### Quem entrega uma encomenda CJ Packet localmente?

Depois de voar da China e passar pela alfândega de destino, a CJ Packet entrega o pacote (`Delivery to local courier`) a um correio nacional ou transportadora local — como USPS, Canada Post, FedEx ou transportadora europeia regional — para a entrega final.

### Como entro em contato com o suporte da CJ Packet?

Contate primeiro a loja virtual onde comprou para reembolsos ou problemas de endereço. O e-mail de suporte logístico publicado em [cjpacket.com](https://www.cjpacket.com/) é `logistics@cjpacket.com`.

:::sources
- [CJ Packet: Official Tracking Portal](https://www.cjpacket.com/) – Small-package Transportation, Low-cost Bulk Packages, Fast Express, suporte 24/7 e `logistics@cjpacket.com`
- [CJ Dropshipping](https://cjdropshipping.com/) – plataforma de fulfillment de e-commerce à qual pertence
- [Universal Parcel Scraper: cj-packet](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cj-packet) – reconhecimento de `CJP[A-Z]{2,3}\d{10}YQ`, regra do link `cjpacket.com/?trackingNumber=`, códigos de resumo `0`–`7`, repasse ao parceiro local e leitura de quem assinou, fusos explícitos ou locais e campos removidos
:::
