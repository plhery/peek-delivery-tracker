---
title: Seguimiento Ekart: códigos, estados InscannedAtDH y RTO
description: Rastrea un paquete de Ekart en la India: cómo es el ID de 14 caracteres, qué significan InscannedAtDH y RTO, reintentos de entrega y Peek.
slug: seguimiento-ekart
published: 2026-11-21
updated: 2026-11-21
---

Ekart (también llamado *eKart Logistics* y *Ekart Express*), operado en la India por *Instakart Services Private Limited* desde 2009 con sede en Bengaluru (Karnataka), es uno de los mayores operadores indios de paquetería para comercio electrónico. Recoge los pedidos en manos del vendedor, los mueve por sus centros de clasificación y reparte en más de 15.000 códigos PIN de la India. Copia el identificador de seguimiento del mensaje de la tienda en el cuadro superior o introdúcelo en la [página Track Order de ekartlogistics.in](https://www.ekartlogistics.in/track-order).

## Identificador de seguimiento de Ekart: cómo es

Peek reconoce los identificadores e-commerce de Ekart como tres letras, seguidas de `C`, `P` o `R` y diez dígitos: 14 caracteres en total (ejemplos inventados):

| Identificador (inventado) | Qué es |
| --- | --- |
| `FMPP4829105736` | Uno de los 17 grupos de tres letras vinculados a Ekart (abajo), seguido de `C`, `P` o `R` y diez cifras: Peek identifica a Ekart solo por la forma. |
| `ABCP4829105736` | Otras tres letras iniciales con la misma estructura: puede ser de Ekart, pero Peek consulta antes al rastreador de Ekart para confirmarlo (ver abajo). |

Los 17 prefijos de tres letras asociados directamente a Ekart son `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` y `TEL`. Los espacios y las minúsculas no importan en Peek. Hay dos excepciones: un código que empieza por `PB` con este esquema corresponde al operador británico Parcelforce, y uno que empieza por `HRBC` (`HRBC4829105736`) encaja con un formato de [Cainiao](carrier:cainiao) / AliExpress en lugar de ofrecerse como Ekart — si procede de una tienda india para Ekart, pulsa «Cambiar» y elige Ekart.

Usa siempre el identificador de seguimiento y no el número de pedido de la tienda ([cómo encontrar el número de seguimiento](guide:find-tracking-number)); para carga B2B/FTL/PTL con código LR, `ekartlogistics.in/track-order` ofrece un enlace aparte.

## Estados de seguimiento de Ekart explicados

El buscador de Ekart acompaña cada escaneo de fecha, hora y ciudad, unas veces en inglés llano (`Shipment Created`) y otras como código seguido de guion y el nombre del centro (`InscannedAtDH` y el hub). En el orden habitual:

| Ekart indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | El vendedor contrató el envío con Ekart; Ekart aún no tiene el paquete. | «Etiqueta creada» |
| `OutForPickupEvent` | Un repartidor de Ekart va de camino a recoger el paquete al vendedor. | «Etiqueta creada» |
| `NotPickedEvent` | La recogida falló; el paquete sigue en poder del vendedor. | «Etiqueta creada» |
| `Pickup From Seller`, `PickupReceived` | Ekart recogió el paquete al vendedor. | «Admitido por el transportista» |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + centro | En tránsito entre centros de la red de Ekart. | «En tránsito» |
| `Expected` | El centro de destino ha sido avisado de la llegada del paquete. | «En tránsito» |
| `Received`, `Received at` + centro | Ha llegado a un centro logístico de Ekart. | «En tránsito» |
| `InscannedAtFacility` | Escaneado al entrar en una instalación de Ekart. | «En tránsito» |
| `InscannedAtDH`, `PrimaryDHInscan` | En la base de reparto (*Delivery Hub*, DH) de tu zona: el siguiente paso es salir a reparto. | «En tránsito» |
| `Out For Delivery` | En ruta de reparto hoy. | «En reparto» |
| `Delivered` | Entregado (o devuelto al vendedor si aparece tras una línea RTO, ver abajo). | «Entregado» |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted`, `undelivered_update` | Se intentó entregar sin éxito (`undelivered_update` puede salir en el mismo instante junto al centro). | «Intento de entrega» |
| `undelivered_unattempted` | La ruta del día concluyó sin llegar a intentar la entrega. | «Intento de entrega» |
| `Unsuccessful Unable to attempt delivery due to operational issues`, `Unsuccessful Undelivered. (Rescheduled to: …)` | Entrega fallida descrita con texto; la fecha tras `Rescheduled to:` señala el próximo intento previsto (un `Unsuccessful` de recogida no cuenta así). | «Intento de entrega» |
| `PickupCancel` | Se canceló la recogida antes de que el envío entrara en la red de Ekart. | «Incidencia» |
| `ShipmentRtoConfirmed` | Confirmada la devolución al origen (*Return to Origin*, RTO): vuelve al vendedor. | «Incidencia» |
| `Marked RTO: ORDER_REJECT` | Tras intentos fallidos, el envío queda marcado para volver al vendedor con el motivo tras los dos puntos. | «Incidencia» |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | Viajando de vuelta hacia el vendedor. | «En tránsito» |

**Qué es RTO.** Peek interpreta `ShipmentRtoConfirmed`, cualquier línea `Marked RTO` o cualquier código que empiece por `Rto` o `Return` como inicio de la devolución al vendedor: esa fila y todas las posteriores pertenecen al regreso. A partir de ahí, `InscannedAtDH` es la base del vendedor y un `Delivered` posterior indica que llegó de vuelta al comercio: Peek muestra entonces «Devuelto al remitente» en lugar de «Entregado» ([qué significan los estados de seguimiento](guide:tracking-statuses)).

## Cómo entrega Ekart en la India

Ekart opera únicamente en la India y Peek no traspasa sus paquetes a ningún otro transportista. En `ekartlogistics.in`, la página B2C Express destaca cobertura en más de 15.000 códigos PIN de entrega (las FAQ detallan 5.000 códigos PIN de recogida y 15.000 de entrega, mientras que *About Us* cita 14.000 códigos PIN, más de 80 centros de clasificación y 3.500 bases de última milla), con opciones en el mismo día o al día siguiente en distancias cortas y de 48 a 96 horas en trayectos más largos según el código PIN.

Según las preguntas frecuentes oficiales de Ekart, realiza **cinco intentos** en primera milla (recogida al vendedor) y **tres intentos** en última milla (entrega al destinatario), y permite reprogramar la entrega a petición del cliente siempre que el envío no haya sido marcado como `RTO` ([entrega fallida](guide:missed-delivery)).

## Seguimiento de Ekart parado, en RTO o no entregado

**Parado en `Shipment Created`, `Pickup Requested` o `NotPickedEvent`.** El vendedor aún no entregó el paquete a Ekart (o falló la recogida); `PickupCancel` indica cancelación antes de recogerlo. Pregunta al comercio cuándo saldrá.

**Días sin escaneos nuevos.** Cuando un envío registrado, admitido o en tránsito pasa cuatro días sin novedades, Peek avisa «Sin novedades desde hace cuatro días». Consulta a la tienda ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).

**Intento fallido o RTO.** Tras `undelivered_attempted`, `undelivered_unattempted` o `Unsuccessful`, revisa la fecha tras `Rescheduled to:` y confirma dirección y teléfono con la tienda. Si aparece `ShipmentRtoConfirmed` o `Marked RTO: ORDER_REJECT`, el paquete regresa al vendedor: pide reembolso o nuevo envío ([devuelto al remitente](guide:returned-to-sender)).

**Marcado como entregado pero no está, perdido o dañado.** Pregunta en casa, a vecinos y en portería o seguridad, y avisa después a la tienda o marketplace que contrató a Ekart ([entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel)).

## Atención al cliente y contacto de Ekart

En las FAQ de `ekartlogistics.in`, Ekart indica a los comercios abrir un ticket en su portal de vendedores (con respuesta en 24 horas) y en *Corporate Information* recoge la sede social de *Instakart Services Private Limited* en Bengaluru (`Embassy Tech Village, Outer Ring Road, Devarabeesanahalli Village, Bengaluru KA 560103`), sin publicar teléfono ni correo de atención para destinatarios. Para dudas, reenvíos o reembolsos de tu pedido, acude primero a la tienda o plataforma donde compraste.

**Cuidado con los SMS falsos.** Los dominios oficiales de Ekart son `www.ekartlogistics.in` (`ekartlogistics.in`) y `ekartlogistics.com`. Antes de pagar nada solicitado por mensaje, revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Ekart con Peek

Pega el identificador en [Peek](/): con uno de los 17 prefijos anteriores seguido de `C`, `P` o `R` y diez dígitos, Peek asigna Ekart al instante («Transportista detectado»). Con otras letras delante (`ABCP4829105736`), Peek muestra «Comprobando servicios de seguimiento…» y pregunta al rastreador de Ekart: en cuanto tenga un escaneo dirá «Ekart tiene este paquete.» (si aún no consta, pulsa «Cambiar» y elígelo bajo «Encaja con este número»). Pegar un enlace de `ekartlogistics.com` con `shipmenttrack/` también lo archiva en Ekart; en cambio, un enlace de `shiprocket.co` se sigue en Shiprocket. Elegir Ekart a mano para algo distinto de cuatro letras y diez dígitos muestra que «no emite números como este».

Bajo Ekart, un envío sin escaneos muestra «Esperando a Ekart» (o «Todavía no hay historial de seguimiento» sin operador asignado). Peek lee el rastreador propio de Ekart sin cuenta ni teléfono: muestra cada línea con sus códigos y ciudad, convierte la hora exacta a la zona de tu dispositivo («Horas en tu zona horaria: …») sin duplicados y enseña la fecha prevista de entrega de Ekart mientras el paquete viaja hacia ti (la oculta al entregarse, cancelarse o pasar a RTO).

Mientras responda el rastreador de Ekart, Peek comprueba hasta cada 10 minutos con el paquete abierto o el día de entrega con notificaciones activas, y hasta cada 2 minutos desde las 6:00 (hora india) el día en que Ekart escanea `Out For Delivery` (mediante servicios de seguimiento, hasta cada 15 minutos ese día con el paquete abierto o alertas activas); el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»). Peek no puede contactar con Ekart ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de Ekart

### ¿Cómo rastreo mi paquete de Ekart?

Pega el identificador de 14 caracteres del mensaje de envío en el cuadro superior de Peek o introdúcelo en el apartado *Ecommerce Parcel Tracking* de `ekartlogistics.in/track-order`.

### ¿Cómo es un identificador de seguimiento de Ekart?

Tres letras, seguidas de `C`, `P` o `R` y diez dígitos, como `FMPP4829105736` (inventado).

### ¿Qué significa `RTO` en el seguimiento de Ekart?

*Return to Origin*: el paquete vuelve al vendedor. `ShipmentRtoConfirmed` o `Marked RTO: ORDER_REJECT` inicia el retorno y un `Delivered` posterior confirma la entrega al vendedor («Devuelto al remitente» en Peek).

### ¿Qué significa `InscannedAtDH` en Ekart?

El envío ha sido escaneado en un centro de reparto (*Delivery Hub*): a la ida, el que reparte en tu zona; tras un RTO, el del vendedor.

### ¿Cuántos intentos de entrega hace Ekart?

Las FAQ oficiales de Ekart indican tres intentos en última milla al destinatario (y cinco intentos en la recogida al vendedor). Para gestionar tu compra, contacta primero con la tienda.

:::sources
- [Ekart Logistics: Inicio](https://www.ekartlogistics.in/) – operador logístico e-commerce en la India y más de 15.000 códigos PIN cubiertos
- [Ekart Logistics: Track Order](https://www.ekartlogistics.in/track-order) – seguimiento de paquetes e-commerce y acceso separado B2B/LR
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – tres intentos de entrega, cinco de recogida, reprogramación antes de RTO, 5.000 / 15.000 códigos PIN y soporte por ticket para vendedores
- [Ekart Logistics: About Us](https://www.ekartlogistics.in/about-us) – inicio en 2009, 3.500 bases de reparto, más de 80 centros de clasificación y 14.000 códigos PIN
- [Ekart Logistics: B2C Express](https://www.ekartlogistics.in/b2c-express) – entregas en el día / día siguiente y en 48–96 horas en la India
- [Ekart Logistics: Corporate Information](https://www.ekartlogistics.in/corporate-info) – Instakart Services Private Limited y domicilio en Bengaluru
- [Ekart Logistics: Terms & Conditions](https://www.ekartlogistics.in/terms-and-conditions) – titularidad de ekartlogistics.com por Instakart Services Private Limited
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – 17 prefijos, enlaces shipmenttrack, 31 códigos y estados, lógica RTO, marcas de tiempo y fecha estimada
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – enlaces de seguimiento de shiprocket.co
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – excepciones PB (Parcelforce) y HRBC (Cainiao / AliExpress) y seguimiento sin traspaso
:::
