---
title: Seguimiento DayOne: números DOT, estados y quién entrega
description: Sigue un paquete de DayOne desde China: formato de un número DOT, significado de los estados en DayOneTrack, quién entrega y a quién acudir si se retrasa.
slug: seguimiento-dayone
published: 2026-11-23
updated: 2026-11-23
---

DayOne es el almacén logístico que despacha desde China los pedidos online de una tienda. No es una empresa de mensajería: se define a sí mismo como un almacén de preparación de pedidos («order fulfillment warehouse, not a courier delivery service provider»). Otras compañías transportan el paquete, y el rastreador de DayOne, [DayOneTrack](https://www.dayonetrack.com/), disponible en inglés y chino, muestra sus escaneos. Consulta ahí tu número DOT o pégalo en el cuadro de arriba. La tienda te entrega ese número junto con los datos de envío del pedido.

## Número de seguimiento de DayOne: cómo es

DayOne no publica un formato oficial. Esta es la forma que Peek reconoce como DayOne, con dígitos inventados:

| Número | Qué es |
| --- | --- |
| `DOT0482917364058291` | DOT y 16 dígitos: un número de DayOne. |
| `DOT1482917364058291` | DOT1 y 15 dígitos más: también de DayOne. El rastreador de DayOne consulta estos números en otro sistema. |
| `DOT048291736405829` | Solo 15 dígitos: no encaja con DayOne y Peek no lo asigna a DayOne. Revisa si falta algún dígito. |

En Peek no importan las minúsculas. Mantén DOT unido a los dígitos: si pones un espacio o un guion justo después de DOT, Peek puede leer solo los dígitos y sugerir otro transportista.

**Dónde encontrarlo.** En el correo de envío de la tienda o en la página de tu pedido. Si no lo encuentras, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**En el rastreador de DayOne.** Puedes consultar hasta 10 números a la vez, uno por línea. El rastreador solo busca números con DOT0 o DOT1: cualquier otro código, como el del correo local, muestra `Not found`. Junto al número de seguimiento, el resultado puede mostrar un `Reference Number` (la referencia interna de la tienda) y el país de destino del paquete.

## Estados de seguimiento de DayOne explicados

El rastreador de DayOne asigna un estado general a cada paquete, en inglés y chino, y enumera los escaneos bajo `Package Track`, del más reciente al más antiguo:

| Estado de DayOne | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Not found` (查询不到) | DayOne aún no tiene datos bajo este número. Añade: «The order information has not been updated yet». | «Esperando a DayOne» |
| `Info received` (收到信息) | Se han recibido los datos del envío. Es posible que el paquete aún no haya salido del almacén. | «Etiqueta creada» |
| `In transit` (运输途中) | En camino. | «En tránsito» |
| `Pick up` (到达待取) | Ha llegado y espera a que lo recojas. | «Listo para recoger» |
| `Out for Delivery` (派送途中) | En reparto. | «En reparto» |
| `Undelivered` (投递失败) | Falló un intento de entrega. | «Intento de entrega» |
| `Delivered` (成功签收) | Entregado. | «Entregado» |
| `Alert` (可能异常) | Puede haber una incidencia. | «Incidencia» |
| `Expired` (运输过久) | En chino, «en tránsito demasiado tiempo»: lleva mucho tiempo en camino. | el estado del escaneo más reciente |

Peek se guía por el escaneo más reciente que entiende. Solo recurre al estado general de DayOne cuando no reconoce ninguno de los escaneos.

Cada escaneo incluye hora, lugar y el texto de la compañía que lleva el paquete: Yanwen en China y después el servicio postal de tu país. Los escaneos habituales, en el orden en que suelen aparecer:

| DayOne indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order processed by shipper` | Yanwen tiene los datos del pedido. Todavía nadie ha escaneado el paquete. | «Etiqueta creada» |
| `Yanwen Pickup Scan`, `Processing information input` | Yanwen tiene el paquete en China y lo está procesando. | «Admitido por el transportista» |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Salió del centro de clasificación de Yanwen y llegó a una terminal en China antes de la aduana de salida. | «En tránsito» |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | En el puerto de salida, pasó la aduana de exportación china (no la tuya) y despegó. | «En tránsito» |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | Aterrizó en tu país o cerca de él. Aún falta la aduana de importación. | «En tránsito» |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | La aduana de tu país lo liberó y lo trasladaron del aeropuerto a una instalación postal. | «En tránsito» |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | En Australia: entregado a Australia Post. | «En tránsito» |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Clasificado por el correo y enviado al siguiente tramo. | «En tránsito» |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Hoy sale a reparto. | «En reparto» |
| `DELIVERED WITH SAFE DROP` | Entregado y dejado en un lugar seguro de la dirección. | «Entregado» |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` y `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` indican que el correo tiene los datos del paquete. Pueden aparecer después de escaneos posteriores: no es un retroceso, y Peek conserva el avance más reciente.

Si el único escaneo es `Shipment has begun. In transit to destination city`, DayOne entregó el paquete a [YunExpress](carrier:yunexpress), y el resto del historial está en el seguimiento de YunExpress con un número YunExpress (`YT` y 16 dígitos). `Clearence processing completed - Export` es el despacho de exportación de YunExpress, con su propia ortografía.

## Quién entrega los paquetes de DayOne

DayOne no dice quién reparte en tu país. Sus escaneos muestran quién tiene el paquete: Yanwen en China y luego el servicio postal de tu país, como [Australia Post](carrier:australia-post) para envíos a Australia.

DayOne no publica normas sobre intentos de entrega, avisos ni puntos de recogida: dependen de la empresa que entrega. Tras `Pick up` o `Undelivered`, busca un aviso o mensaje de esa empresa. Consulta [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

**Aduanas en la UE.** Los bienes comprados online fuera de la UE pagan un arancel de 3 € por artículo en paquetes de hasta 150 € desde el 1 de julio de 2026, y una tasa de gestión de la Unión de 2 € por artículo, cualquiera que sea el valor, desde el 1 de noviembre de 2026. Un artículo son las mercancías que comparten clasificación arancelaria, descripción y, cuando se exija, origen. La web de DayOne no dice nada sobre aranceles, tasas ni quién los recauda. En otros países rigen las normas locales: consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de DayOne sin actualizar, perdido o devuelto

DayOne te remite a la tienda. Su rastreador indica que para cualquier cuestión logística, pérdida, reembolso o reenvío debes contactar con la atención al cliente de la tienda donde compraste.

**`Not found`.** DayOne aún no tiene registros para ese número. Revisa el código: DOT y 16 dígitos. Si está bien, puede que el pedido aún no haya salido. Peek muestra «Esperando a DayOne» y sigue comprobando. Si dos días después DayOne sigue sin reconocer el número y nadie más ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y a partir de entonces comprueba a diario, y semanalmente cuando pasa una semana desde que lo añadiste. Si tras unos días no hay datos, pregunta a la tienda.

**Solo `Info received` u `Order processed by shipper`.** Aún nadie ha escaneado el paquete. Si permanece así varios días, pregunta a la tienda cuándo salió.

**Varios días sin un nuevo escaneo.** Los paquetes suelen quedar en silencio en vuelos largos y aduanas. Si un paquete todavía en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin un nuevo escaneo, Peek lo señala con «Sin novedades desde hace cuatro días» (o «Sin novedades desde el …»), añade «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a DayOne.» y muestra «Pide ayuda a DayOne». Pasadas tres semanas sin escaneos nuevos, Peek muestra «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a DayOne.» Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating) y [seguir paquetes desde China](guide:tracking-from-china).

**`Alert` o `Expired`.** Lee los últimos escaneos y pide ayuda a la tienda con tu número DOT.

**Perdido, dañado o devuelto.** DayOne no publica proceso de reclamación para destinatarios. Pide un reembolso o un reenvío a la tienda. Consulta [paquetes perdidos](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

**Entregado pero no está.** `DELIVERED WITH SAFE DROP` indica que el paquete se dejó en un lugar seguro de la dirección. Busca alrededor de casa, pregunta a los vecinos y avisa a la tienda. Consulta [entregado pero no recibido](guide:delivered-not-received).

## Contacto de DayOne

Según el [rastreador de DayOne](https://www.dayonetrack.com/):

- **Sobre tu paquete:** la tienda donde compraste, tal como indica DayOne.
- **Correo comercial:** info@dayone-shipping.com, indicado por DayOne para colaboraciones y asuntos comerciales ajenos a consultas de paquetes.

El rastreador de DayOne no publica teléfono, chat, formulario de contacto ni dirección. Indica que solo almacena números de envío y no guarda detalles de pedidos.

## Seguir un paquete de DayOne con Peek

[Peek](/) identifica DayOne al instante a partir de un número DOT de 16 dígitos («Transportista detectado»). También sirve un enlace de `dayonetrack.com` con `trackNo=`. Para cualquier otro número, pégalo igual: Peek sigue a más de 4.500 transportistas. Cuando el formato encaja con varios transportistas, Peek muestra «Comprobando servicios de seguimiento…» mientras consulta. Si Peek no identifica DayOne para tu número, pulsa «Cambiar» y elige DayOne en «Todos los transportistas» (buscar «Day One» también lo encuentra), o usa «Cambiar transportista» en un paquete ya añadido.

Para un número `DOT0`, Peek lee directamente el rastreador de DayOne: estado, cada escaneo con su lugar, país de destino y hora de entrega. Para un número `DOT1`, consulta servicios de seguimiento en su lugar.

Cuando DayOne incluye el desfase horario de un escaneo, Peek lo utiliza. Si falta, calcula el huso a partir del lugar o de los escaneos cercanos (el de tu país en cuanto consta la llegada). Muestra todas las horas en la zona horaria de tu dispositivo. Omite códigos postales, el nombre de quien recogió o firmó el paquete entregado y el `Reference Number` de la tienda. DayOne no da fecha prevista de entrega ni peso.

El rastreador de DayOne no muestra el número del repartidor final, pero su consulta a veces lo contiene. Peek consulta entonces también a esa empresa: al servicio postal de tu país si es un número postal (dos letras, nueve dígitos, dos letras) como Royal Mail, USPS, Canada Post, An Post o Australia Post, o al único transportista al que apunte la forma, como YunExpress para un número `YT`. En cuanto ese transportista registra un escaneo al menos tan reciente como el último de DayOne, Peek sigue el paquete ahí, muestra ese número junto al DOT y conserva el historial de DayOne.

Para un número `DOT0`, mientras tengas el paquete abierto en Peek o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos, y a partir de las 6:00 (hora local) del día en que DayOne escanea el paquete en reparto hasta cada 2 minutos mientras el rastreador propio de DayOne responda; tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora, y si permanece siete días en «Etiqueta creada», cada 6 horas. Para un número `DOT1` (vía servicios de seguimiento), Peek comprueba cada hora, cada 3 horas tras un día sin escaneos nuevos, cada 6 horas tras siete días en «Etiqueta creada», y hasta cada 15 minutos el día de entrega mientras tengas el paquete abierto en Peek o las notificaciones activadas. Puede avisarte de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con DayOne ni con la tienda por ti.

## Preguntas sobre el seguimiento de DayOne

### ¿Es DayOne una empresa de mensajería?

No. DayOne afirma ser un almacén de preparación de pedidos («order fulfillment warehouse, not a courier delivery service provider»). Otras compañías transportan el envío: según sus escaneos, Yanwen en China y luego el servicio postal de tu país.

### ¿Dónde puedo seguir un número DOT?

En el rastreador de DayOne, [dayonetrack.com](https://www.dayonetrack.com/), en inglés o chino, hasta 10 números a la vez. O pegándolo en Peek.

### ¿Por qué el rastreador de DayOne dice Not found?

DayOne aún no tiene registros para ese número. Comprueba que escribiste DOT y los 16 dígitos. Si el número es correcto, espera uno o dos días y pregunta a la tienda si el paquete ya salió.

### ¿Por qué las horas en el rastreador de DayOne aparecen desordenadas?

Cada escaneo muestra la hora local del sitio donde se hizo, sin zona horaria, y el rastreador los ordena por esas horas. En invierno boreal, Pekín va 8 horas por delante de Londres y 13 de Nueva York, así que un escaneo en tu país puede parecer anterior al último de China. Sídney va 3 horas por delante de Pekín en verano austral, por lo que allí parecen más recientes. Peek muestra cada hora en la zona horaria de tu dispositivo.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – almacén, no mensajería; posventa con la tienda; política de privacidad; correo comercial; inglés y chino; 10 números a la vez; búsquedas DOT0 y DOT1; estados; campos de resultado; horas
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026, tasa de gestión de la Unión de 2 € desde el 1 de noviembre de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artículo sin importar el valor, qué es un artículo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de número, enlaces y consultas, textos y códigos de escaneo, husos horarios, número del repartidor final, traspaso, qué lee Peek
:::
