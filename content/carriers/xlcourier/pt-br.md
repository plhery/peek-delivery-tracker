---
title: Rastreamento XLCourier: códigos XLC, cargas pesadas e ajuda
description: Rastreie encomendas pesadas XLCourier no Reino Unido (30–70 kg): códigos XLC e ZX, status 100–1600, prazo de 1–3 dias úteis, POD e contato.
slug: xlcourier-tracking
published: 2026-11-26
updated: 2026-11-26
---

A XLCourier (*XL Courier Limited*, sediada em Birmingham) é uma transportadora britânica especializada em entregas pesadas, volumosas e fora de padrão para B2B e e-commerce — especialmente itens de 30 a 70 kg como móveis, eletrodomésticos, bicicletas elétricas, baterias de alta capacidade, equipamentos de ginástica e itens frágeis ou industriais. Com frota própria no Nordeste, Leste e Sul da Inglaterra e distribuição nacional pelo Reino Unido, a XLCourier informa prazo médio de 1 a 3 dias úteis, com GPS ao vivo e Comprovante Eletrônico de Entrega (POD). Você pode consultar um envio na [página Track Your Order da XLCourier](https://www.xlcourier.com/trackOrder) ou colar seu código na caixa acima.

## Códigos de rastreamento XLCourier: formatos XLC e ZX

Procure o número no e-mail de envio da loja, no SMS de aviso ou na página do pedido. Os códigos da XLCourier seguem dois prefixos:

| Formato (fictício) | O que é |
| --- | --- |
| `XLC48291736405` | Conhecimento padrão XLCourier UK: `XLC` seguido por 10 a 12 dígitos (13 a 15 caracteres ao todo). |
| `ZX48291736` | Código alternativo de 10 caracteres da XLCourier (`ZX` + 8 dígitos), aceito no portal de rastreamento. |

Se a sua entrega pesada no Reino Unido vier por outra rede de grandes volumes — como [XDP](carrier:xdp), [Parcelforce](carrier:parcelforce) ou [APC Overnight](carrier:apc-overnight) — confira [formatos de código de rastreamento](guide:tracking-number-formats) para identificar o prefixo.

## O que significam os status e códigos da XLCourier

O rastreador da XLCourier atribui a cada leitura um código numérico (`100` a `1600`), um título curto e uma linha descritiva (incluindo o motivo quando uma visita falha):

| Código e título XLCourier | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `100` (`Created`), `200` (`Scheduled`), `300` (`Out for Pickup`), `400` (`Pickup Failed`) | Dados do pacote enviados e coleta agendada ou tentada; a XLCourier ainda não recolheu o volume físico. | "Etiqueta criada" |
| `500` (`Picked Up`) | Coletada com sucesso no vendedor ou armazém. | "Recebida pela transportadora" |
| `600` (`Received`), `700` (`Pending Transit`), `800` (`In Transit`), `900` (`Scheduled`) | Chegou ao depósito local ou hub nacional da XLCourier, aguarda transferência, partiu do hub ou foi escalada para rota de entrega. | "Em trânsito" |
| `1000` (`Out for Delivery`) | Carregada em um veículo da XLCourier para entrega hoje. | "Saiu para entrega" |
| `1100` (`Delivery Failed`) | Tentativa de entrega não concluída (com motivo anexado quando informado). | "Tentativa de entrega" |
| `1200` (`Return Scheduled`), `1300` (`Returning`), `1400` (`Return Failed`), `1500` (`Returned`) | Devolução agendada ou em retorno ao remetente. | "Devolvida ao remetente" |
| `1570` (`Destroyed`) | Encomenda marcada como destruída no sistema; fale imediatamente com o vendedor. | "Precisa de atenção" |
| `1600` (`Delivered`) | Entregue ao destinatário com assinatura eletrônica de POD. | "Entregue" |

A XLCourier usa o mesmo título `Scheduled` para o código `200` (coleta agendada no remetente) e o código `900` (entrega agendada para você): a linha abaixo do título diferencia os dois.

## Como funciona a entrega de volumes pesados da XLCourier no Reino Unido

Segundo [xlcourier.com](https://www.xlcourier.com/) e a [página About Us da XLCourier](https://www.xlcourier.com/aboutUs):

- **Itens pesados e especiais:** estrutura dedicada a cargas de 30–70 kg e fora de padrão, incluindo sofás, armários, eletrodomésticos, bicicletas elétricas, patinetes, estações portáteis de energia / baterias grandes, aparelhos de academia, vidros/TVs e equipamentos industriais, manuseados com empilhadeiras e cintas acolchoadas.
- **Prazo e suporte:** a XLCourier informa prazo médio de **1 a 3 dias úteis**, suporte no Reino Unido por chat ao vivo, WhatsApp, SMS e e-mail, e visibilidade GPS ao vivo do motorista no dia da entrega.
- **Comprovante Eletrônico de Entrega (POD):** motoristas coletam assinatura digital do destinatário e fotos POD em múltiplos ângulos no ato da entrega.

## Encomenda XLCourier atrasada, entrega falha ou danificada

Como o varejista ou vendedor de e-commerce britânico é o cliente contratante da XLCourier, avise sempre a loja além de contatar a XLCourier caso uma entrega pesada falhe ou chegue com dano.

- **Entrega falha (`1100`):** verifique o motivo informado (por exemplo restrição de acesso, ausência ou endereço incompleto) e contate imediatamente o suporte da XLCourier e a loja antes de a devolução (`1200`) ser agendada.
- **Bens pesados ou frágeis danificados:** inspecione a embalagem externa antes de assinar o POD digital; fotografe amassados ou caixas rasgadas e reporte imediatamente o dano visível à loja e à XLCourier.
- **Pacote parado ou extraviado:** veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da XLCourier

Contatos oficiais divulgados em [XLCourier Contact Us](https://www.xlcourier.com/contactUs):

- **E-mail de atendimento no Reino Unido:** `customerservice@xlcourier.com`.
- **Telefone no Reino Unido:** `07476 947927`.
- **Atendimento na Alemanha:** `decustomerservice@xlcourier.com`, `+49 177 5831818`.
- **Endereço:** XL Courier Limited, Unit 40, Holly Park Industrial Estate, Spitfire Road, Birmingham, B24 9PB, Reino Unido.
- **Outros canais:** chat ao vivo, WhatsApp, SMS e e-mail citados em [xlcourier.com](https://www.xlcourier.com/).

> Desconfie de mensagens de texto inesperadas cobrando taxa no cartão para "reagendar" uma entrega pesada no Reino Unido. Confira seu código `XLC` diretamente em [xlcourier.com/trackOrder](https://www.xlcourier.com/trackOrder) ou confirme por `customerservice@xlcourier.com`. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda XLCourier com o Peek

O [Peek](/) reconhece automaticamente com alta confiança `XLC` + 10 a 12 dígitos (`XLC48291736405`, dígitos fictícios) como XLCourier, além de links colados de `xlcourier.com/trackOrder?number=`. Para códigos `ZX` + 8 dígitos (`ZX48291736`), toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela do pacote e escolha XLCourier (buscar "XL Courier" ou "XLC" também encontra).

O Peek lê o rastreador da XLCourier diretamente e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), a linha descritiva com o motivo de visita falha (com telefones e e-mails removidos) e o depósito ou hub indicado em linhas de chegada. Os códigos (`100` a `1600`) definem a etapa e não são mostrados pelo Peek. Como a XLCourier informa horários sem fuso, o Peek aplica o fuso horário do local (horário britânico para depósitos no Reino Unido) e converte para o relógio do seu dispositivo. O endereço do destinatário não aparece; enquanto o volume estiver em trânsito, seu próprio mapa pode terminar na cidade do endereço, dado que links compartilhados não exibem. O rastreador da XLCourier não fornece data estimada de entrega, peso, nome do serviço, horário separado de entrega nem parceiro local.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a XLCourier mostra `1000` (`Out for Delivery`), a cada 2 minutos a partir das 6h no relógio do local da última leitura, enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da XLCourier não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando XLCourier.", com o link "Obter ajuda da XLCourier" para a página de contato oficial. Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando XLCourier."

Você pode escolher alertas para "Cada leitura", "Etapas importantes" ou "Apenas entrega". O Peek monitora apenas atualizações de rastreamento e não pode agendar horário de entrega, abrir o GPS ao vivo do motorista nem solicitar reentrega à XLCourier por você.

## Perguntas sobre o rastreamento da XLCourier

### O que é a XLCourier no Reino Unido?

A XLCourier (*XL Courier Limited*, sediada em Birmingham) é uma transportadora britânica especializada em encomendas pesadas e volumosas (normalmente de 30 a 70 kg), como móveis, eletrodomésticos, bicicletas elétricas, baterias grandes e aparelhos de ginástica.

### Como é um código de rastreamento da XLCourier?

A maioria dos códigos XLCourier começa com `XLC` seguido por 10 a 12 dígitos (por exemplo `XLC48291736405`, dígitos fictícios). O rastreador também aceita códigos `ZX` + 8 dígitos (`ZX48291736`).

### Quanto tempo a XLCourier leva para entregar?

Em [xlcourier.com](https://www.xlcourier.com/), a XLCourier informa prazo médio de **1 a 3 dias úteis** no Reino Unido, com Comprovante Eletrônico de Entrega (POD) e assinatura na entrega.

### Como entro em contato com o suporte da XLCourier no Reino Unido?

Escreva para `customerservice@xlcourier.com` ou ligue para `07476 947927` informando seu código `XLC`. A XLCourier também menciona suporte por chat ao vivo, WhatsApp e SMS em [xlcourier.com](https://www.xlcourier.com/).

:::sources
- [XLCourier: Home](https://www.xlcourier.com/) – transportadora britânica de cargas grandes/pesadas (30–70 kg), entrega média em 1–3 dias úteis, GPS ao vivo, assinatura POD e cobertura no Reino Unido
- [XLCourier: About Us](https://www.xlcourier.com/aboutUs) – fluxo TMS/WMS, manuseio especializado, POD eletrônico e histórico da empresa
- [XLCourier: Track Your Order](https://www.xlcourier.com/trackOrder) – portal público de rastreamento
- [XLCourier: Contact Us](https://www.xlcourier.com/contactUs) – `customerservice@xlcourier.com`, `07476 947927`, contatos na Alemanha e sede em Birmingham (`Unit 40, Holly Park Industrial Estate, B24 9PB`) (URL de ajuda vinculada pelo Peek)
- [Universal Parcel Scraper: xlcourier](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xlcourier) – formatos `XLC`/`ZX`, regra de link `trackOrder?number=`, códigos `100`–`1600`, fuso por local da leitura, extração de depósito e URL de ajuda
:::
