---
title: Rastreamento OCA Argentina: códigos, status e retirada
description: Rastreie encomendas OCA e e-Pak na Argentina: códigos de 19 dígitos, status em espanhol, 2 tentativas em casa, 5 dias úteis na agência e suporte.
slug: oca-tracking
published: 2026-11-26
updated: 2026-11-26
---

A OCA é uma transportadora argentina de encomendas e correspondências. Sua plataforma ePak atende e-commerce, grandes empresas e pequenos negócios em todo o país, além de operar com o Mercado Envíos. Os destinatários acompanham pacotes pela [consulta de envios da OCA](https://www.oca.com.ar/Busquedas/Envios), retiram volumes aguardando em agências Puntos OCA nas províncias argentinas ou utilizam os OCA SmartLockers.

O rastreamento da OCA registra em espanhol cada leitura de depósito, transferência entre agências, visita domiciliar e janela de retirada.

## Formatos de código de rastreamento da OCA

Os números padrão OCA e-Pak seguem uma estrutura de 19 dígitos:

- **19 dígitos com `0000` nas posições 6 a 9** (como `3654800004829173605`) — o número da guia ou ordem de admissão (`número de guía` / `orden de admisión`) indicado pela Central de Ajuda da OCA para rastreamento.
- **Sufixo `-1` separado** — as etiquetas podem imprimir `-1` após o código, como `3654800004829173605-1`. A [Central de Ajuda da OCA](https://www.oca.com.ar/CentroDeAyuda) orienta a não digitá-lo. Deixe-o de fora no Peek também: o Peek remove apenas o hífen, transformando `...-1` em um número de 20 dígitos que a OCA não reconhece.
- **Encomendas e envelopes com selo (*estampillados*)** — a OCA também emite itens selados com 3 letras maiúsculas (`YAE`, `YAA`, `MDA`, `PAA`) mais 8 dígitos para pacotes, ou 2 letras mais 8 dígitos para envelopes selados, digitados sem espaços nem parênteses na busca da OCA. Correspondências simples sem selo (`postales simples`) não possuem rastreamento e vão direto para a caixa de correio ou sob a porta.

No site da OCA é possível substituir a sequência de zeros centrais por `//`; o Peek precisa dos 19 dígitos completos ou de um link direto `oca.com.ar/Seguimiento/Paquetes/...` (ou link e-Pak legado), reconhecido automaticamente. Na Argentina, o Peek consulta a OCA para números de 19 dígitos nesse formato; fora do país, consulta a OCA apenas como último recurso. Para outros formatos regionais, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da OCA

O serviço de rastreamento e-Pak da OCA informa os eventos em espanhol e costuma incluir a agência após um hífen (`- Suc: ...`) ou o motivo entre parênteses.

| Texto de status da OCA | O que significa |
| --- | --- |
| `En proceso de Retiro` | Coleta agendada no remetente; a OCA ainda não recolheu a encomenda. |
| `Retirado` / `Retirado en Origen` | A OCA coletou a encomenda no remetente. |
| `En Proceso en OCA` / `Procesado en OCA` | Em processamento em centro de triagem ou agência da OCA. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | Em trânsito rumo ao centro de distribuição ou agência de destino. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Chegou ao centro de distribuição ou agência local da OCA. |
| `Programado para visita a domicilio` | Incluído na programação de entregas (`en programación`) da agência local antes da visita em domicílio. |
| `Visita a Domicilio en Curso` | Saiu com o entregador para entrega em domicílio hoje. |
| `No entregado (No Visitado)` / `No entregado (...)` | Entrega não concluída; o texto entre parênteses indica o motivo (como não visitado ou suspenso a pedido do remetente). |
| `Reprogramado para nueva visita` | Reagendado para uma nova tentativa de entrega. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | Aguardando retirada na agência OCA designada. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Entregue ao destinatário ou retirado na agência. |
| `Acuse digitalizado` | Comprovante de entrega digitalizado na central da OCA dias após a entrega — registro apenas administrativo, não movimenta o pacote. |
| `Devolución en Proceso` / `Devuelto al Remitente` | A caminho de retorno ou já devolvido ao remetente. |

## Visitas domiciliares, agências e SmartLockers

A [Central de Ajuda da OCA](https://www.oca.com.ar/CentroDeAyuda) define regras claras para entregas em toda a Argentina:

- **Duas tentativas em domicílio:** a OCA realiza duas visitas sem agendamento de segunda a sábado, das 09:00 às 18:00. Qualquer pessoa com 18 anos ou mais no endereço pode receber apresentando o DNI (documento de identidade nacional). Dias chuvosos podem limitar as rotas.
- **Retirada em agência (5 dias úteis):** se ambas as visitas falharem, a encomenda aguarda por **5 dias úteis** na agência Punto OCA designada (encontre unidades no [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). O destinatário deve levar o DNI e o código de rastreamento; um terceiro autorizado maior de 18 anos precisa levar cópia do DNI do destinatário, autorização escrita à mão e o número de rastreamento. Encomendas não retiradas retornam ao remetente.
- **SmartLockers (48 horas + prorrogação opcional):** quando a OCA deposita o pacote em um SmartLocker, você recebe e-mail e/ou SMS com código QR e PIN alfanumérico. Você tem **48 horas corridas** para retirar; após lembretes de aviso, a OCA envia um link de pagamento com cartão que permite comprar até **3 dias extras** de guarda antes da devolução ao remetente.
- **Endereço incorreto ou devolução:** a OCA não permite interromper o envio nem alterar o endereço em trânsito. Aguarde a conclusão das visitas para retirar na agência (ou aguarde o retorno ao remetente para solicitar novo envio). Para mais orientações após ausência, veja o [guia de entregas perdidas](guide:missed-delivery).
- **Itens especiais (DNI, passaportes, cartões OCA Geo, envelopes):** entregas de DNI vão apenas ao endereço residencial (exigindo o comprovante do RENAPER) e não podem ser retiradas em agência; passaportes seguem para agência após duas visitas; cartões OCA Geo exigem entrega direta ao titular; envelopes selados têm duas visitas e retornam ao remetente se não entregues, sem guarda em agência.
- **48 horas úteis para a primeira leitura:** a OCA informa que envios recém-postados podem levar até **48 horas úteis** após a postagem para aparecer online. Se nada aparecer após 48 horas úteis, apenas o remetente pode abrir verificação. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) para checagens gerais.

Para outras transportadoras domésticas da Argentina, compare com a [Andreani](carrier:andreani).

## Rastreando a OCA com o Peek

Cole no [Peek](/) o número OCA de 19 dígitos sem o `-1` ou um link `oca.com.ar/Seguimiento/Paquetes/...`. Se o Peek não selecionar a OCA automaticamente, escolha OCA em **Alterar** ao adicionar ou **Alterar transportadora** na página do pacote.

O Peek lê diretamente o serviço web e-Pak `Tracking_Pieza` da OCA por meio do [adaptador open-source OCA do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Cada data e hora argentina é gravada como instante exato em `America/Argentina/Buenos_Aires` (`-03:00`), o nome da agência é extraído de forma limpa para o campo de local e registros administrativos `Acuse digitalizado` nunca sobrescrevem nem alteram uma entrega concluída. O feed e-Pak da OCA não informa data prevista de entrega nem peso do pacote.

O Peek mapeia os eventos da OCA em etapas claras: **Etiqueta criada** (`En proceso de Retiro`), **Recebida pela transportadora** (`Retirado`), **Em trânsito** (transferências entre plantas e agências), **Saiu para entrega** (`Programado para visita a domicilio` e `Visita a Domicilio en Curso`), **Tentativa de entrega** (`No entregado` e `Reprogramado para nueva visita`), **Pronta para retirada** (`En Espera de Retiro por Sucursal`), **Entregue** (`Entregado`) e **Devolvida ao remetente** (`Devolución en Proceso` / `Devuelto al Remitente`).

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek verifica até a cada 10 minutos; no dia em que a OCA mostra `Programado para visita a domicilio` ou `Visita a Domicilio en Curso`, a cada 2 minutos a partir das 6h (horário argentino), enquanto o rastreador da OCA responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a OCA não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando OCA.", com o link "Obter ajuda da OCA" para a Central de Ajuda oficial. Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando OCA."

Escolha **Cada leitura**, **Etapas importantes** ou **Apenas entrega** nas configurações de alertas do Peek.

## Como contatar a OCA

- **Central de Ajuda e formulário online:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (inclui categorias de FAQ e o formulário de mensagem direta no rodapé da página)
- **Consulta oficial de rastreamento:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Localizador de agências:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Site oficial:** [oca.com.ar](https://www.oca.com.ar/)
- **Remetente primeiro para códigos ausentes ou reenvios:** a OCA afirma que somente o remetente tem autorização para investigar um envio sem registros após 48 horas úteis da postagem ou para solicitar novo envio após devolução.

## Perguntas frequentes

### Por que meu código de rastreamento da OCA termina com `-1`?

A Central de Ajuda da OCA orienta omitir o `-1` separado e consultar usando apenas os 19 dígitos da guia ou ordem de admissão. Faça o mesmo no Peek: ele não remove o `-1`, e o código com esse sufixo não é reconhecido pela OCA.

### Por quanto tempo a OCA guarda uma encomenda na agência ou no SmartLocker?

A OCA guarda encomendas nas agências Puntos OCA por **5 dias úteis** após tentativas sem sucesso ou chegada na unidade. Nos SmartLockers, você tem **48 horas corridas** gratuitas, com opção de pagar online por até **3 dias adicionais** antes da devolução ao remetente.

### Posso mudar o endereço de entrega depois que a OCA coleta minha encomenda?

Não. A Central de Ajuda da OCA informa que nem o processo de envio nem o endereço de entrega podem ser alterados em trânsito. Após as visitas domiciliares, você pode retirar o pacote pessoalmente na agência OCA designada com seu DNI ou deixá-lo retornar ao remetente.

### Por que meu código da OCA ainda não mostra atualizações?

A OCA informa que um pacote pode levar até **48 horas úteis** a partir da postagem na agência para aparecer no rastreador online. Passadas 48 horas úteis sem registro, contate o remetente, único autorizado a abrir busca por registro ausente junto à OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [Adaptador OCA do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
