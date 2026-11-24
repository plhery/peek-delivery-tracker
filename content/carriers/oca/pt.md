---
title: Rastreio OCA: números de 19 dígitos, estados e agências
description: Acompanha encomendas OCA e e-Pak na Argentina: números de 19 dígitos, estados em espanhol, 2 visitas ao domicílio, levantamento em 5 dias e apoio.
slug: rastreio-oca
published: 2026-11-24
updated: 2026-11-24
---

A OCA é uma transportadora argentina de encomendas e correio. A sua plataforma ePak serve comércio eletrónico, grandes empresas e pequenos negócios em todo o país e trabalha com o Mercado Envíos. Os destinatários acompanham envios na [pesquisa de encomendas da OCA](https://www.oca.com.ar/Busquedas/Envios), levantam encomendas retidas nas agências Puntos OCA nas províncias argentinas ou recolhem-nas nos cacifos OCA SmartLockers.

O rastreio da OCA regista em espanhol cada leitura em centro de triagem, transferência entre agências, visita ao domicílio e prazo de levantamento.

## Formatos dos números de rastreio OCA

Os números padrão das encomendas OCA e-Pak seguem uma estrutura de 19 dígitos:

- **19 dígitos com `0000` nas posições 6–9** (como `3654800004829173605`) — o número de guia de envio ou ordem de admissão (`número de guía` / `orden de admisión`) que o Centro de Ajuda da OCA indica para o rastreio.
- **Um `-1` separado** — as etiquetas podem imprimir `-1` no fim do número, como `3654800004829173605-1`. O [Centro de Ajuda da OCA](https://www.oca.com.ar/CentroDeAyuda) recomenda não o escrever. Deixa-o de fora também no Peek: o Peek remove apenas o hífen, pelo que `...-1` vira um número de 20 dígitos que a OCA não reconhece.
- **Encomendas e envelopes franquiados** — a OCA emite ainda envios franquiados com 3 letras maiúsculas (`YAE`, `YAA`, `MDA`, `PAA`) seguidas de 8 dígitos para encomendas, ou 2 letras mais 8 dígitos para envelopes franquiados, introduzidos sem espaços nem parênteses na página da OCA. O correio simples sem selo registado (`postales simples`) não tem rastreio e é entregue diretamente na caixa de correio ou por baixo da porta.

No site da OCA podes substituir os zeros do meio por `//`; o Peek precisa dos 19 dígitos completos, ou de uma ligação direta `oca.com.ar/Seguimiento/Paquetes/...` ou ligação antiga e-Pak, que o Peek lê automaticamente. Na Argentina o Peek consulta a OCA para um número de 19 dígitos com este formato; noutros países consulta a OCA apenas em último recurso. Para outros formatos regionais, consulta o [guia de formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio OCA

O serviço de rastreio e-Pak da OCA apresenta estados em espanhol e junta frequentemente a agência responsável após um travessão (`- Suc: ...`) ou um motivo entre parênteses.

| Texto de estado OCA | O que significa |
| --- | --- |
| `En proceso de Retiro` | Recolha no remetente agendada; a OCA ainda não recolheu a encomenda. |
| `Retirado` / `Retirado en Origen` | A OCA recolheu a encomenda junto do remetente. |
| `En Proceso en OCA` / `Procesado en OCA` | Processada num centro de triagem ou agência OCA. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | Em trânsito para o centro de distribuição ou agência de destino. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Chegou ao centro de destino ou à agência OCA local. |
| `Programado para visita a domicilio` | Inserida na programação de entregas (`en programación`) da agência local antes de uma visita ao domicílio. |
| `Visita a Domicilio en Curso` | Em distribuição hoje com o estafeta para entrega ao domicílio. |
| `No entregado (No Visitado)` / `No entregado (...)` | A entrega não foi concluída; o texto entre parênteses indica a razão (como não visitado ou retido a pedido do remetente). |
| `Reprogramado para nueva visita` | Reagendada para uma nova tentativa de entrega. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | Em espera na agência OCA atribuída, pronta para levantamento. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Entregue ao destinatário ou levantada na agência. |
| `Acuse digitalizado` | Comprovativo de entrega digitalizado nas instalações da OCA dias após a entrega — meramente administrativo, não movimenta a encomenda. |
| `Devolución en Proceso` / `Devuelto al Remitente` | A caminho de volta ou devolvida ao remetente. |

## Visitas ao domicílio, agências e SmartLockers

O [Centro de Ajuda da OCA](https://www.oca.com.ar/CentroDeAyuda) define regras claras para entregas na Argentina:

- **Duas tentativas de entrega ao domicílio:** a OCA faz duas visitas sem marcação de segunda a sábado, entre as 09h00 e as 18h00. Qualquer pessoa com 18 anos ou mais na morada pode receber a encomenda mostrando o DNI (documento nacional de identidade). Dias de chuva podem limitar as rotas de entrega.
- **Levantamento em agência (5 dias úteis):** se ambas as visitas falharem, a encomenda aguarda **5 dias úteis** na agência Punto OCA designada (encontra agências em [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). O destinatário deve levar o DNI e o número de rastreio; uma pessoa autorizada com mais de 18 anos precisa de cópia do DNI do destinatário, autorização manuscrita e número de rastreio. Encomendas não levantadas regressam ao remetente.
- **SmartLockers (48 horas + extensão opcional):** quando a OCA deposita uma encomenda num SmartLocker, recebes um e-mail e/ou SMS com código QR e PIN alfanumérico. Tens **48 horas consecutivas** para a levantar; após avisos, a OCA envia uma ligação de pagamento com cartão que permite comprar até **3 dias extra de guarda** antes da devolução ao remetente.
- **Morada errada ou encomendas devolvidas:** a OCA não permite interromper um envio nem alterar a morada de entrega em trânsito. Aguarda o fim das visitas para a levantares na agência (ou deixa-a voltar ao remetente para novo envio). Para mais opções após uma entrega falhada, consulta o [guia de entrega falhada](guide:missed-delivery).
- **Envios especiais (DNI, passaportes, cartões OCA Geo, envelopes):** os DNI vão apenas para a morada residencial (com o talão RENAPER) e não podem ser levantados em agência; os passaportes seguem para levantamento em agência após duas visitas ao domicílio. Os cartões OCA Geo exigem entrega direta ao titular. Os envelopes franquiados têm duas visitas ao domicílio e regressam se não entregues, sem guarda em agência.
- **48 horas úteis para o primeiro registo:** a OCA informa que envios acabados de admitir podem demorar até **48 horas úteis** após entrega em agência para surgir online. Se nada aparecer passadas 48 horas úteis, só o remetente pode abrir pedido de verificação. Consulta [quando o rastreio deixa de atualizar](guide:tracking-not-updating) para verificações gerais.

Para outras transportadoras nacionais argentinas, compara com a [Andreani](carrier:andreani).

## Seguir a OCA com o Peek

Cola um número OCA de 19 dígitos sem o `-1`, ou uma ligação `oca.com.ar/Seguimiento/Paquetes/...`, no Peek. Se o Peek não selecionar a OCA automaticamente, escolhe OCA em **Alterar** ao adicionar a encomenda ou **Alterar transportadora** na página de rastreio.

O Peek lê diretamente o serviço web e-Pak `Tracking_Pieza` da OCA através do [adaptador OCA de código aberto do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Cada data e hora argentina fica guardada como instante exato em `America/Argentina/Buenos_Aires` (`-03:00`), o nome das agências é extraído de forma limpa para o campo de localização e registos administrativos `Acuse digitalizado` nunca substituem nem perturbam uma entrega já concluída. O feed e-Pak da OCA não inclui data estimada de entrega nem peso da encomenda.

O Peek traduz os estados da OCA em etapas claras: **Etiqueta criada** (`En proceso de Retiro`), **Aceite pela transportadora** (`Retirado`), **Em trânsito** (centros de triagem e transferências entre agências), **Em distribuição** (`Programado para visita a domicilio` e `Visita a Domicilio en Curso`), **Tentativa de entrega** (`No entregado` e `Reprogramado para nueva visita`), **Pronta para levantamento** (`En Espera de Retiro por Sucursal`), **Entregue** (`Entregado`) e **Devolvida ao remetente** (`Devolución en Proceso` / `Devuelto al Remitente`).

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a OCA mostra `Programado para visita a domicilio` ou `Visita a Domicilio en Curso`, de 2 em 2 minutos a partir das 6h00 hora argentina, enquanto o rastreio da OCA responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a OCA não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a OCA.», com uma ligação «Pede ajuda a OCA» para o Centro de Ajuda da OCA. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a OCA.»

Escolhe **Todos os registos**, **Passos importantes** ou **Só a entrega** nas definições de alertas do Peek para controlar que atualizações geram notificações.

## Contactar a OCA

- **Centro de Ajuda e formulário de apoio online:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (inclui categorias de perguntas frequentes e o formulário direto no fundo da página)
- **Pesquisa oficial de rastreio:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Localizador de agências:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Site principal:** [oca.com.ar](https://www.oca.com.ar/)
- **Primeiro o remetente para códigos sem registo ou reenvios:** a OCA declara que só o remetente tem autorização para investigar um envio sem registo 48 horas úteis após a entrega em agência ou para tratar de um reenvio depois de uma devolução.

## Perguntas frequentes

### Por que termina o meu número de rastreio OCA em `-1`?

O Centro de Ajuda da OCA indica para omitir o `-1` separado e rastrear pelo número de guia ou admissão de 19 dígitos. Faz o mesmo no Peek: ele não remove o `-1`, e o número com esse sufixo não é reconhecido pela OCA.

### Durante quanto tempo guarda a OCA uma encomenda numa agência ou SmartLocker?

A OCA guarda as encomendas nas agências Puntos OCA durante **5 dias úteis** após tentativas de entrega falhadas ou chegada à agência. Nos SmartLockers dispões de **48 horas consecutivas** gratuitas, com opção de pagar online até **3 dias adicionais** antes da devolução ao remetente.

### Posso alterar a morada de entrega depois de a OCA recolher a encomenda?

Não. O Centro de Ajuda da OCA refere que nem o percurso do envio nem a morada de entrega podem ser alterados em trânsito. Após as visitas ao domicílio, podes levantar a encomenda pessoalmente na agência OCA atribuída com o teu DNI, ou deixá-la regressar ao remetente.

### Por que ainda não mostra atualizações o meu número de rastreio OCA?

A OCA avisa que uma encomenda pode demorar até **48 horas úteis** desde a admissão na agência até aparecer no rastreio online. Se passarem 48 horas úteis sem registo, contacta o remetente, única parte autorizada a abrir uma verificação junto da OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [adaptador OCA do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
