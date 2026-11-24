---
title: Seguimiento Post Haste: códigos NZ, estados y ayuda
description: Sigue un paquete de Post Haste Couriers en Nueva Zelanda: formatos de tickets y envíos, estados Freightways, nueva entrega y sucursales.
slug: seguimiento-post-haste
published: 2026-11-24
updated: 2026-11-24
---

**Post Haste** (*Post Haste Couriers*) es una red neozelandesa de mensajería que se incorporó al **grupo Freightways** en 1977 y opera en colaboración con Castle Parcels ([posthaste.co.nz](https://posthaste.co.nz/)). Con cobertura para empresas y particulares tanto en la Isla Norte como en la Isla Sur mediante más de 20 delegaciones regionales y socios Delivery Link, Post Haste gestiona envíos locales, regionales al día siguiente, nacionales entre islas al día siguiente y económicos en dos días, además de zonas rurales. Puedes consultar un envío en [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) o pegar tu enlace de seguimiento o código de envío en el cuadro de arriba.

## Números de seguimiento de Post Haste (tickets, etiquetas y enlaces)

Post Haste emplea varios formatos de etiqueta Freightways según si el remitente utilizó un ticket o bolsa prepagada, una etiqueta de cargo de 8 dígitos, un código de barras SSCC de 20 dígitos o una etiqueta impresa por el propio cliente (como Despatch IT):

| Formato (inventado) | Qué es |
| --- | --- |
| `PH48291736` | Ticket de mensajería o bolsa Express Pack: código de producto de 2 a 4 letras (por ejemplo `PE` o `PH`) + número de ticket de 5 a 8 dígitos (introducir sin espacios). |
| `73918462` | Número de etiqueta de cargo Post Haste de 8 dígitos. |
| `KLM4829173` | Número de expedición impreso por el cliente (letras + 6 a 8 dígitos); algunas etiquetas impresas por el remitente requieren también el número de cuenta del remitente incluido en el enlace de seguimiento de tu correo (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Si la tienda te envió un enlace `receiver.posthaste.co.nz` o `posthaste.co.nz` por correo electrónico o SMS, **pega ese enlace completo directamente en Peek**: aporta automáticamente tanto el número de envío como la cuenta del remitente cuando hace falta. Consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Post Haste

Post Haste funciona sobre la plataforma compartida de seguimiento de Freightways (usada por Post Haste, Castle Parcels, New Zealand Couriers y Now Couriers) e informa de tipos y subtipos de escaneo junto con el barrio o la región:

| Código o escaneo de Post Haste | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Label created` | Etiqueta electrónica generada por el sistema de expedición del remitente antes de la recogida. | «Etiqueta creada» |
| `P` (`Picked Up`) | Recogido al remitente por un mensajero de Post Haste. | «Admitido por el transportista» |
| `STAT/RZDEL` | Aviso de zona rural o no urbana; la entrega por un colaborador rural suele añadir 2 o 3 días laborables. | «En tránsito» |
| `STAT/REDEL` | Reprogramado y enviado de nuevo tras una entrega fallida anterior. | «En tránsito» |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` a un colaborador rural) | Entregado a un contratista de reparto rural; sigue en camino hacia tu domicilio y aún no está en tu puerta. | «En tránsito» |
| `ONBD` | A bordo de la furgoneta del repartidor local para entrega hoy. | «En reparto» |
| `NOND` / `NOND/*` | Incidencia de no entrega (ausencia con aviso dejado, local cerrado o problema de acceso). | «Intento de entrega» |
| `D`, `3P/D` | Entregado en la dirección del destinatario (incluido `3P/D` completado por un colaborador rural). | «Entregado» |

`STAT/HLDFR8` indica que el paquete está retenido en la oficina de carga de la delegación tras una entrega que no llegó a realizarse. No especifica si espera recogida o un nuevo intento: solicita una nueva entrega o pregunta en la delegación.

## Plazos de entrega, zonas rurales y nueva entrega en Nueva Zelanda

Según [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) y [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Plazos publicados:** servicio **local** entre empresas en la misma ciudad; **regional** al día siguiente (habitualmente antes del mediodía del siguiente día laborable); **nacional** al día siguiente dentro y entre la Isla Norte y la Isla Sur (normalmente antes del mediodía del siguiente día laborable para empresas y al final del día para particulares); **económico entre islas en dos días**; además de **entrega en sábado** en las principales áreas metropolitanas.
- **Direcciones no urbanas y rurales:** Post Haste cubre la mayoría de las direcciones rurales de Nueva Zelanda mediante socios de reparto rural (`STAT/RZDEL`), lo que suma de dos a tres días laborables sobre los plazos urbanos.
- **Domicilio ausente y nueva entrega:** la política estándar de Post Haste es **no** dejar paquetes en direcciones donde no haya nadie, salvo que se haya dado autorización previa (*authority to leave*). Si pierdes una entrega, concierta una nueva entrega en [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) con tu referencia de entrega de 16 dígitos, o contacta con tu delegación local si no dispones de ella. Lleva un documento de identidad para recoger en delegación.

## Paquete de Post Haste retrasado, retenido o perdido

- **El traspaso rural aparece como «entregado» en otros localizadores:** si un escaneo indica `Delivered by courier or branch` o `Delivery Handed Over`, normalmente el paquete se ha transferido a un contratista rural (`3P`) y no se ha dejado todavía en tu domicilio — deja 2 o 3 días laborables para la ruta rural (`3P/D`). Peek mantiene estos traspasos en «En tránsito» en lugar de marcarlos entregados antes de tiempo.
- **Sin escaneos tras la etiqueta o detenido en depósito:** si un número impreso por el cliente no aparece por sí solo, pega el enlace completo `receiver.posthaste.co.nz` de tu correo de envío para que Peek incluya el código de cuenta del remitente. Si un paquete sigue bloqueado en una sucursal, contacta con tu depósito local de Post Haste o con el vendedor.
- **Reclamaciones por pérdida o daño:** consulta las normas de pérdida y daños en [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) y contacta primero con la tienda que contrató el envío. Consulta también [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [devuelto al remitente](guide:returned-to-sender) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).

## Atención al cliente y delegaciones de Post Haste en Nueva Zelanda

Directorios y soporte oficiales publicados en [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) y [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Directorio de delegaciones locales (Isla Norte e Isla Sur):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) reúne teléfonos y direcciones de las delegaciones de la Isla Norte (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) y la Isla Sur (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), incluidas las sedes compartidas con Castle Parcels.
- **Herramienta online de nueva entrega:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Centro de ayuda para destinatarios y políticas:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Desconfía de los SMS falsos que aseguran que un envío neozelandés exige un pequeño pago con tarjeta para reprogramar la entrega. Usa siempre [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) o compruébalo directamente con tu delegación de Post Haste. Consulta [mensajes SMS falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Post Haste con Peek

La forma más sencilla de añadir un envío de Post Haste en [Peek](/) es pegar el enlace completo de tu correo de envío (`receiver.posthaste.co.nz/?consignmentid=CU..…` o `posthaste.co.nz/track?query=`), que Peek reconoce automáticamente como Post Haste y usa para extraer la cuenta del remitente cuando hace falta. Si introduces un número suelto de ticket, etiqueta de cargo o expedición (`PH48291736`, `73918462` o `KLM4829173`, inventados), Peek no reconoce Post Haste solo por el número (consulta a TNT Italia por un ticket como `PH48291736` y a Australia Post por un envío como `KLM4829173`), así que pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y selecciona **Post Haste** (buscar «Post Haste Couriers» o «Posthaste» también funciona). Peek ofrece entonces un campo opcional «Enlace de seguimiento completo» para el enlace de tu correo de envío, donde figura la cuenta del remitente que algunos paquetes necesitan.

Peek lee la consulta de Freightways bajo el nombre de Post Haste y muestra la etapa («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Intento de entrega», «Entregado»), el texto del escaneo, el barrio y la región, el día estimado de entrega cuando Post Haste lo facilita y la hora de entrega. Los tipos y subtipos de escaneo fijan la etapa y no aparecen en las aplicaciones de Peek. Las horas de escaneo llegan como instantes UTC y se muestran en la hora de tu dispositivo. Cuando otra red de Freightways, como Castle Parcels, registra el escaneo más reciente, el paquete muestra «Entrega con» y el nombre de esa red. Peek no guarda la firma ni el nombre del firmante, fotos de entrega, la marca de autorización de depósito, códigos postales ni el repartidor.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Post Haste muestra `ONBD`, cada 2 minutos desde las 6:00 hora de Nueva Zelanda mientras responda el localizador de Post Haste. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Post Haste no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Post Haste.», junto con un enlace «Pide ayuda a Post Haste» hacia la ayuda de recepción de Post Haste. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Post Haste.»

Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek supervisa únicamente las actualizaciones de seguimiento y no puede reservar nuevas entregas, conceder autorización de entrega sin firma ni contactar con tu delegación de Post Haste en tu nombre.

## Preguntas sobre el seguimiento de Post Haste

### ¿Qué números de seguimiento funcionan con Post Haste?

Post Haste rastrea tickets de mensajería y bolsas Express Pack (2 a 4 letras + 5 a 8 dígitos, como `PH48291736`, inventado), etiquetas de cargo de 8 dígitos (`73918462`, inventado), códigos de barras SSCC de 20 dígitos y números de expedición impresos por el cliente (`KLM4829173`, inventado) — o pega el enlace completo `receiver.posthaste.co.nz` de tu correo de envío.

### ¿Por qué no aparece mi número de envío impreso por el remitente en Post Haste?

Algunas etiquetas impresas por el cliente (como las expediciones de Despatch IT) están vinculadas al número de cuenta del remitente. Pega el enlace completo `receiver.posthaste.co.nz` del correo de envío para que la consulta incluya el código de cuenta.

### ¿Qué hago si perdí mi entrega de Post Haste?

Por norma general Post Haste requiere que haya alguien presente salvo autorización previa de depósito. Usa el formulario online en [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) con tu referencia de entrega de 16 dígitos o llama a tu delegación local en [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### ¿Por qué indica el seguimiento de Post Haste `Delivery Handed Over` o `STAT/RZDEL`?

`STAT/RZDEL` señala una dirección rural o no urbana, y un escaneo de traspaso significa que el paquete se ha confiado a un contratista rural — lo que suele sumar 2 o 3 días laborables antes de la entrega final en domicilio (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – red neozelandesa de mensajería (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – adquisición por Freightways (1977) y colaboración con Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – servicios locales, regionales al día siguiente, nacionales al día siguiente, económico entre islas en dos días, sábados y entregas rurales
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – etiquetas impresas por clientes (con cuenta de empresa), tickets/Express Packs, códigos de barras y sacas de contrato
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – solicitudes online de nueva entrega y requisito de documento de identidad en delegación
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – directorio de delegaciones de la Isla Norte e Isla Sur
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – política de ausencias, avisos y guías de pérdida o daño (URL de ayuda enlazada por Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – consulta Freightways de tickets/etiquetas/SSCC/expediciones, reglas de enlace `receiver.posthaste.co.nz`, mapa de códigos (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) y análisis de fecha estimada
:::
