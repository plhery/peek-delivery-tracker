---
title: Rastreio CJ Packet: códigos CJP...YQ, entrega e contactos
description: Rastreia uma encomenda CJ Packet (CJ Dropshipping) da China: códigos CJP...YQ, estados e entrega local, pausas na alfândega e e-mail de apoio.
slug: rastreio-cj-packet
published: 2026-11-24
updated: 2026-11-24
---

A CJ Packet (*CJPacket*) é o serviço internacional de envio e rastreio de encomendas ligado à [CJ Dropshipping](https://cjdropshipping.com/), expedindo pequenos pedidos de comércio eletrónico, volumes económicos a granel e envios expresso rápidos a partir de armazéns logísticos na China para compradores em todo o mundo através de transporte aéreo internacional e parceiros locais de última milha. Podes consultar um envio diretamente em [cjpacket.com](https://www.cjpacket.com/) ou colar o teu número de rastreio na caixa acima.

## Números de rastreio CJ Packet: formato CJP...YQ

Procura a tua guia CJ Packet no e-mail de confirmação de envio ou na página do pedido da loja online. Os códigos CJ Packet começam por `CJP`, seguidos de 2 ou 3 letras (que indicam a linha de envio), 10 dígitos e `YQ` (17 ou 18 caracteres no total):

| Formato (inventado) | O que é |
| --- | --- |
| `CJPOS4829173640YQ` | Guia CJ Packet de 17 caracteres (`CJP` + 2 letras de linha + 10 dígitos + `YQ`). |
| `CJPAEE4829173640YQ` | Guia CJ Packet de 18 caracteres (`CJP` + 3 letras de linha + 10 dígitos + `YQ`). |

Assim que a encomenda chega ao país de destino e passa pela alfândega, a CJ Packet entrega-a a uma transportadora ou correios locais — como [USPS](carrier:usps), [Canada Post](carrier:canada-post), [FedEx](carrier:fedex) ou [CTT Express](carrier:ctt-express) — e apresenta o número local e os registos de entrega desse parceiro no histórico `CJP...YQ`. Para outros prefixos chineses de comércio eletrónico, vê [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da CJ Packet

A CJ Packet combina um estado geral resumido (`0` a `7`) com registos detalhados linha a linha do armazém CJ, do transitário de exportação e da transportadora de última milha no destino:

| Resumo CJ Packet (código) / descrição do registo | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Processing` (`0`) / `Label created. Warehouse is processing this order.`, `Shipment info received` | Pedido embalado ou etiqueta gerada no armazém da CJ antes da recolha pelo transitário. | «Etiqueta criada» |
| `Dispatched` (`1`) / `Item has been collected` | Encomenda entregue pelo armazém da CJ ao transitário internacional. | «Aceite pela transportadora» |
| `En Route` (`2`), `Arrived Courier Facility` (`3`) / `Item Flight departure`, `Item Flight landing`, `Customs Clearance Completed`, `Delivery to local courier` | Transportada por avião da China até ao país de destino, desalfandegada e transferida para o centro de triagem da transportadora local. | «Em trânsito» |
| `Inbound to customs temporary warehouse`, `In clearance processing - Import` | Em desalfandegamento de importação no país de destino. | «Na alfândega» |
| `Out for Delivery` (`4`) / `Out for delivery` / `En reparto` | Em distribuição hoje com o estafeta local. | «Em distribuição» |
| `Available for Pickup` (`5`) | Pronta para levantamento num posto dos correios ou ponto de recolha local. | «Pronta para levantamento» |
| `Unsuccessful Delivery` (`6`) | A entrega não pôde ser concluída (ex.: morada errada ou destinatário ausente). | «Problema reportado» |
| `Delivered` (`7`) / `Package delivered`, `Delivered, At the door/Yard`, `Entregado` | Entregue pelo parceiro de destino (indicando por vezes quem recebeu). | «Entregue» |

Nota que avisos como `EXPECTED DELIVERY DATE UPDATED` emitidos por uma transportadora local são estimativas informativas e não alteram a etapa real de trânsito da encomenda.

## Como funciona a entrega internacional da CJ Packet

Em [cjpacket.com](https://www.cjpacket.com/), a CJ Packet apresenta três modelos principais — *Small-package Transportation*, *Low-cost Bulk Packages* e *Fast Express* — combinando linhas aéreas parceiras, apoio aduaneiro e transportadoras locais em todo o mundo.

**Quem faz a entrega à porta.** Assim que surge `Delivery to local courier`, os correios ou estafeta local que assumem a encomenda definem tentativas de entrega, locais seguros, prazo de guarda em pontos de levantamento e devoluções. O site da CJ Packet destina-se aos comerciantes que enviam com o serviço: para atrasos, reembolsos ou reclamações, contacta a loja online onde compraste. Para IVA de importação, direitos aduaneiros e retenções na alfândega (incluindo regras da UE para encomendas extra-UE), vê [encomendas retidas na alfândega](guide:customs).

## Encomenda CJ Packet retida, sem novidades ou marcada como entregue

Como o teu contrato de compra é com a loja online (e não com a CJ Dropshipping ou a CJ Packet), fala sempre primeiro com o vendedor se a encomenda estiver atrasada, danificada ou desaparecida.

- **Pausas após `Label created` ou partida do voo:** aguardar vários dias enquanto o armazém da CJ embala o artigo (`Label created. Warehouse is processing this order.`) ou enquanto a carga aérea passa na alfândega de destino é comum em pequenas encomendas internacionais. Vê [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Usa o número da transportadora local:** quando a CJ Packet apresenta o código da parceira local (por exemplo USPS, Canada Post ou uma transportadora regional), consultá-lo diretamente pode revelar moradas de levantamento ou opções de nova entrega.
- **Entrega falhada (`6`) ou entregue sem receção:** confirma primeiro junto de familiares, vizinhos, portaria e transportadora local e escreve logo ao vendedor antes de expirarem os prazos de disputa do cartão ou plataforma. Vê [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos CJ Packet

Contactos oficiais publicados em [cjpacket.com](https://www.cjpacket.com/) e [cjdropshipping.com](https://cjdropshipping.com/):

- **E-mail de apoio logístico:** `logistics@cjpacket.com` (apoio ao cliente 24/7 referido em [cjpacket.com](https://www.cjpacket.com/)).
- **Portal de rastreio:** [cjpacket.com](https://www.cjpacket.com/).
- **Plataforma de dropshipping:** [cjdropshipping.com](https://cjdropshipping.com/) (para apoio logístico a comerciantes).

> Ignora SMS ou mensagens de WhatsApp não solicitadas a pedir uma pequena taxa por cartão num link externo para «entregar» ou «desalfandegar» uma encomenda. Verifica sempre encargos legítimos na página do pedido da loja ou no site oficial dos teus correios. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda CJ Packet com o Peek

[O Peek](/) reconhece automaticamente `CJP` + 2 ou 3 letras + 10 dígitos + `YQ` (`CJPOS4829173640YQ` e `CJPAEE4829173640YQ`, dígitos inventados) como CJ Packet com elevada confiança, e reconhece também links colados de `cjpacket.com/?trackingNumber=`. Se precisares de escolher a CJ Packet manualmente, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda (pesquisar «CJPacket» ou «CJ Dropshipping» também a encontra).

O Peek lê diretamente o rastreio da CJ Packet e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Problema reportado», «Entregue»), a descrição dos registos de armazém, transitário e transportadora local sem telefones, e-mails nem linhas de apoio das transportadoras, o local do registo e o país de destino. O nome do serviço ou da linha fica de fora em todas as apps do Peek, e o nome de quem recebeu a encomenda permanece privado. Um registo com deslocamento horário mantém-no, um sem deslocamento é datado no fuso horário do respetivo local, e o Peek apresenta-os todos na hora do teu dispositivo, com a hora de entrega quando o registo de entrega traz deslocamento. Quando a CJ Packet indica a transportadora local e o respetivo número e o Peek a reconhece, verifica se ela tem a encomenda: a página mostra então «Entrega:» seguido do nome da transportadora e lista ambos os números. O Peek omite país de origem, números alternativos, referências de pedido/comerciante e contactos; o rastreio da CJ Packet não dá data estimada de entrega, peso nem foto de prova de entrega.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a CJ Packet mostra `Out for Delivery` (`4`), de 2 em 2 minutos a partir das 6h na hora do local do último registo, enquanto o rastreio da CJ Packet responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da CJ Packet não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a CJ Packet.» Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a CJ Packet.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode desalfandegar encomendas, contactar a CJ Packet nem pedir reembolso à tua loja online.

## Perguntas frequentes sobre o rastreio CJ Packet

### O que é a CJ Packet?

A CJ Packet é o serviço internacional de envio e rastreio usado por lojas que expedem encomendas através da [CJ Dropshipping](https://cjdropshipping.com/) de armazéns na China para compradores em todo o mundo.

### O que significa um número de rastreio `CJP...YQ`?

Os números que começam por `CJP`, 2 ou 3 letras de rota, 10 dígitos e `YQ` (como `CJPOS4829173640YQ` ou `CJPAEE4829173640YQ`, dígitos inventados) são guias transfronteiriças da CJ Packet.

### Quem entrega localmente uma encomenda CJ Packet?

Depois de voar da China e ser desalfandegada no destino, a CJ Packet entrega a encomenda (`Delivery to local courier`) aos correios nacionais ou a um estafeta local (como USPS, Canada Post, FedEx ou um operador regional europeu) para entrega ao domicílio.

### Como contacto o apoio ao cliente da CJ Packet?

Contacta primeiro a loja online onde fizeste a compra para reembolsos ou correções de morada. O e-mail logístico publicado pela CJ Packet em [cjpacket.com](https://www.cjpacket.com/) é `logistics@cjpacket.com`.

:::sources
- [CJ Packet: portal oficial de rastreio](https://www.cjpacket.com/) – Small-package Transportation, Low-cost Bulk Packages, Fast Express, apoio 24/7 e `logistics@cjpacket.com`
- [CJ Dropshipping](https://cjdropshipping.com/) – plataforma-mãe de logística e-commerce
- [Universal Parcel Scraper: cj-packet](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cj-packet) – deteção `CJP[A-Z]{2,3}\d{10}YQ`, regra de link `cjpacket.com/?trackingNumber=`, códigos de resumo `0`–`7`, passagem a parceiro local e extração de signatário, relógios com deslocamento/locais e campos omitidos
:::
