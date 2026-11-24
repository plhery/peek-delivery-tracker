---
title: Rastreio AnserX: códigos BTYCN...YQ, entrega local e ajuda
description: Rastreia uma encomenda internacional AnserX (斑头雁) da China: códigos BTYCN...YQ, eventos BTY, entrega local, alfândega e apoio em Shenzhen.
slug: rastreio-anserx
published: 2026-11-24
updated: 2026-11-24
---

A AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, em chinês *斑头雁国际物流*), fundada em 2018 e sediada em Shenzhen, é um operador chinês de logística de comércio eletrónico transfronteiriço que explora linhas dedicadas de pequenas encomendas da China para os Estados Unidos, Europa (incluindo Reino Unido, França e Alemanha), Japão, Canadá, Austrália, Sudeste Asiático, México, Nigéria e Arábia Saudita, além de carga aérea/marítima FBA (0–30 kg, lado máximo 1,2 m) e armazéns no estrangeiro. Podes rastrear um envio no [portal OMS da AnserX](https://oms.anserx.com/tracking) ou colar o teu número de guia na caixa acima.

## Números de rastreio AnserX: formato BTYCN...YQ

Procura o número de rastreio nos detalhes da tua encomenda no marketplace ou no e-mail de confirmação de envio. Os códigos da AnserX têm 17 caracteres: `BTYCN` seguido de 10 dígitos e terminado em `YQ`:

| Formato (inventado) | O que é |
| --- | --- |
| `BTYCN4829173640YQ` | Guia padrão de encomenda internacional AnserX: `BTYCN` + 10 dígitos + `YQ`. |

Assim que a encomenda chega ao país de destino e passa pela alfândega, a AnserX transfere-a para um operador postal ou estafeta local (como a [USPS](carrier:usps) nos Estados Unidos ou um parceiro local de última milha na Europa ou no México) e associa o número de rastreio local desse parceiro enquanto continua a apresentar os registos locais sob o teu código `BTYCN...YQ`. Para outros prefixos transfronteiriços chineses, consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados e códigos de rastreio da AnserX

A AnserX combina códigos de etapa `BTY_` com descrições em inglês e chinês ao longo da triagem de exportação, transporte aéreo, desalfandegamento e entrega no destino:

| Código e descrição AnserX | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | O vendedor transmitiu os dados eletrónicos do pedido à AnserX antes de a encomenda física chegar ao centro. | «Etiqueta criada» |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Recebida e registada no centro de triagem da AnserX na China. | «Aceite pela transportadora» |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Saiu do centro de triagem na China, foi entregue à companhia aérea e aterrou no aeroporto de destino. | «Em trânsito» |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Desalfandegamento concluído (nota: o texto em inglês da AnserX refere «export», enquanto o texto chinês confirma que o desalfandegamento de importação no destino terminou); a encomenda segue em trânsito local. | «Em trânsito» |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Entregue à transportadora local do país de destino e a caminho da estação local de distribuição. | «Em trânsito» |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | Em distribuição hoje com o estafeta local. | «Em distribuição» |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Entregue ao destinatário pelo parceiro de destino. | «Entregue» |

## Como funciona a entrega internacional da AnserX

Em [anserx.com](https://www.anserx.com/) e [Sobre a AnserX](https://www.anserx.com/about.html), a AnserX descreve a sua plataforma inteligente de capacidade e encaminhamento que liga recolha na China, hubs de exportação, transporte aéreo, desalfandegamento e transportadoras locais de última milha (incluindo canais dedicados de pequenas encomendas compatíveis com artigos eletrónicos com bateria).

**Entrega final e alfândega.** A AnserX é um consolidador aéreo internacional e não uma rede de entregas porta a porta fora da China. Depois de concluído o desalfandegamento no destino (`BTY_EXCC_COMPLETED`), o operador postal ou expresso local do teu país define tentativas de entrega, depósitos em local seguro, guarda em pontos de levantamento e devoluções. IVA de importação, direitos aduaneiros ou taxas de apresentação seguem as regras aduaneiras do destino, salvo se pré-pagos pelo vendedor na compra; vê [encomendas retidas na alfândega](guide:customs).

## Encomenda AnserX retida, sem atualizações ou não entregue

Os clientes da AnserX são os vendedores que expedem com ela, pelo que deves contactar primeiro o vendedor em caso de atraso, morada incorreta, reembolso ou reclamação por extravio.

- **Pausas durante o voo ou na alfândega:** o rastreio fica frequentemente vários dias sem novos registos entre `BTY_LINE_HANDOVER` (partida do porto de origem), `BTY_LINE_ARRIVED` e `BTY_EXCC_COMPLETED` enquanto os voos chegam e a alfândega processa lotes consolidados. Vê [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Consulta o número local após a passagem:** assim que a AnserX associa o número da transportadora de destino, consultá-lo diretamente no site dessa transportadora costuma dar detalhes mais completos sobre janela de entrega ou levantamento.
- **Entregue, danificada ou desaparecida:** confirma primeiro junto de familiares, vizinhos, portaria e transportadora local, e depois informa rapidamente o vendedor online. Vê [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos AnserX

Contactos oficiais publicados em [Contactos AnserX](https://www.anserx.com/contact-us.html) e [anserx.com](https://www.anserx.com/):

- **Linhas de apoio 24 horas (China):** `0755-2891 8474` e `0755-2868-5256` (atendimento online `09:00–24:00` hora da China).
- **E-mail de apoio ao cliente:** `bantouyankf@anserx.com`.
- **Sede:** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, China.

> Desconfia de SMS ou mensagens de WhatsApp não solicitadas a pedir um pagamento online com cartão para «desalfandegar» ou «corrigir a morada» de uma encomenda da China. Confirma sempre qualquer taxa legítima na página da encomenda ou no site oficial dos teus correios. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda AnserX com o Peek

[O Peek](/) reconhece automaticamente `BTYCN` + 10 dígitos + `YQ` (`BTYCN4829173640YQ`, dígitos inventados) como AnserX com elevada confiança, e reconhece também links colados de `oms.anserx.com/tracking?nos=`. Se precisares de escolher a AnserX manualmente, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda (pesquisar «斑头雁» ou «AnserX International Logistics» também a encontra).

O Peek lê diretamente o rastreio OMS da AnserX e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Entregue»), a descrição em inglês do registo (em chinês quando não existe versão inglesa), a cidade ou hub sem código postal e o país de destino. Os códigos `BTY_` definem a etapa; nenhuma app do Peek os mostra. A AnserX data os seus próprios registos na hora de Pequim; os registos reencaminhados da transportadora local usam o fuso horário do respetivo local (hora de Los Angeles num registo em Los Angeles, Cidade do México para Puebla), e o Peek apresenta-os todos na hora do teu dispositivo. Quando a AnserX fornece o número da transportadora de entrega e o Peek reconhece essa transportadora, o Peek verifica se ela tem a encomenda: o ecrã mostra então «Entrega:» seguido do nome da transportadora e lista ambos os números. Num código de barras USPS o prefixo postal de encaminhamento é retirado. O Peek omite o código de conta de cliente e a referência do pedido; o rastreio da AnserX não indica data estimada de entrega, peso, nome do serviço, hora de entrega própria nem foto de prova de entrega.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a AnserX mostra `BTY_LASTMILE_DELIVERING`, de 2 em 2 minutos a partir das 6:00 na hora local do último registo, enquanto o rastreio da AnserX responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da AnserX não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a AnserX.», com o link «Pede ajuda a AnserX» para a página de contactos da AnserX. Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a AnserX.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode desalfandegar encomendas nem contactar a AnserX ou o teu estafeta local por ti.

## Perguntas frequentes sobre o rastreio AnserX

### O que é a AnserX (*斑头雁*)?

A AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, fundada em 2018 em Shenzhen) é um operador chinês de logística de comércio eletrónico transfronteiriço com linhas aéreas dedicadas para pequenas encomendas, envios FBA e armazéns no estrangeiro para os EUA, Europa, Canadá, Austrália, Japão, México e outros destinos.

### O que significa um número de rastreio `BTYCN...YQ`?

`BTYCN` + 10 dígitos + `YQ` (por exemplo `BTYCN4829173640YQ`, dígitos inventados) é a guia transfronteiriça de 17 caracteres da AnserX.

### Porque é que a AnserX mostra `Relased from export customs` depois de chegar ao meu país?

Trata-se de uma imprecisão de tradução no feed em inglês da AnserX para o código `BTY_EXCC_COMPLETED` — o texto chinês correspondente (`目的地进口清关完成`) confirma que o desalfandegamento de importação no destino foi concluído e que a encomenda segue para a transportadora local.

### Como contacto o apoio ao cliente da AnserX?

Fala primeiro com a loja online ou marketplace para problemas com o pedido e reembolsos. Para contactos diretos, a AnserX indica `bantouyankf@anserx.com` e as linhas 24 horas de Shenzhen `0755-2891 8474` e `0755-2868-5256` em [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX: site oficial](https://www.anserx.com/) – linhas dedicadas de pequenas encomendas, limites FBA 0–30 kg / 1,2 m, armazéns no estrangeiro e sede em Shenzhen
- [AnserX: sobre nós](https://www.anserx.com/about.html) – fundada em 2018, rotas globais (EUA, Europa, Japão, Sudeste Asiático, Rússia, Austrália) e plataforma inteligente de encaminhamento
- [AnserX: contactos](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, atendimento online `09:00–24:00` e morada em Shenzhen (URL de ajuda ligado pelo Peek)
- [AnserX: portal de rastreio OMS](https://oms.anserx.com/tracking) – consulta pública de envios
- [Universal Parcel Scraper: anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – deteção `BTYCN...YQ`, regra de link `oms.anserx.com/tracking?nos=`, códigos `BTY_*`, hora de Pequim nos seus registos e fuso local nos reencaminhados, extração `deliveryNo` local e URL de ajuda
:::
