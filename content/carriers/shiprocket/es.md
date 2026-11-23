---
title: Seguimiento Shiprocket: números AWB, estados y RTO
description: Sigue un paquete Shiprocket o ShiprocketX: formato del AWB, significado de estados, entregas fallidas (NDR), RTO y contacto.
slug: seguimiento-shiprocket
published: 2026-11-22
updated: 2026-11-22
---

Shiprocket, con sede en Gurugram, es una plataforma india de envíos, no una mensajería: las tiendas online contratan a través de ella empresas como Delhivery, Blue Dart o Xpressbees, y Shiprocket ofrece al comprador una página de seguimiento. ShiprocketX es su servicio para envíos desde India al extranjero. Sigue tu paquete en la [página de seguimiento de Shiprocket](https://www.shiprocket.in/shipment-tracking/) con su AWB, ID de pedido o móvil, en el enlace `shiprocket.co` que envió el vendedor, o en el recuadro de arriba. Shiprocket indica que envía el AWB por correo y SMS al confirmarse el pedido.

## Número de seguimiento de Shiprocket

Shiprocket llama AWB (*air waybill number*) al número de seguimiento. Dentro de India es el número de la mensajería contratada. Un envío ShiprocketX viaja bajo el propio AWB de Shiprocket: 13 dígitos que empiezan por `832`. Shiprocket no publica un formato oficial. Con dígitos inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `8324829103756` | `832` y 10 dígitos más: un AWB de ShiprocketX. |
| `7394028156342` | 13 o 14 dígitos: el AWB de una mensajería nacional india, como Delhivery o Xpressbees. |
| `74829103753` | 11 dígitos: la forma de un AWB de Blue Dart. |
| `FMPC4829103756` | Tres letras, C, P o R y 10 dígitos: un AWB de Ekart. |

**Dónde encontrarlo.** Revisa el correo y el SMS enviados al confirmar el pedido. El rastreador de Shiprocket también admite el ID de pedido o tu número de móvil, valida un código de un solo uso (OTP) y abre myShiprocket para compradores. Más opciones en [cómo encontrar un número de seguimiento](guide:find-tracking-number).

**Números parecidos.** Como las guías de Delhivery también pueden tener 13 dígitos, un número que empieza por `832` es probablemente, aunque no con certeza, de Shiprocket. Como el seguimiento de Shiprocket responde para cualquier mensajería reservada mediante la plataforma, el enlace `shiprocket.co/tracking/` funciona tenga la forma que tenga el código.

## Estados de seguimiento de Shiprocket explicados

La página muestra el estado, la mensajería o servicio junto al AWB y los escaneos del más reciente al más antiguo, con actividad, a menudo localidad, día, mes y hora, pero sin año. Casi todas las líneas usan el texto de la mensajería: la nacional en India o la local en destino. Líneas habituales por orden:

| La página de Shiprocket dice | Qué significa para ti |
| --- | --- |
| `Order Received` | El vendedor creó el pedido. Ninguna mensajería tiene aún el paquete. |
| `Manifested - Out for Pickup` | El mensajero va de camino a recoger el paquete al vendedor. |
| `In Transit - Shipment picked up` | La mensajería ya lo ha recogido. |
| `In Transit - Shipment Recieved at Origin Center` | En el primer centro de la mensajería (escrito así en la web). |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX: la mensajería lleva el paquete al centro de exportación de Shiprocket, no a tu casa. |
| `Received At DC` | Ya está en el centro de exportación de Shiprocket. |
| `AWB Handed over to Flight` | Entregado a la aerolínea. |
| `CUSTOM CLEARED OVERSEAS` | Despachado por aduanas. |
| `Received by carrier` | Ya lo tiene el transportista de tu país. |
| `RTO DELIVERED` | Devuelto al vendedor. |

`Out For Pickup` y `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` también indican que aún no se ha recogido. `Reached At Warehouse` y `Connection Aligned` corresponden al centro de exportación en Delhi. `Customer has made payment or Provided ID/Tax information` es un paso aduanero en el extranjero (tasas pagadas o datos fiscales aportados; ver [paquetes retenidos en aduanas](guide:customs)). Cualquier línea con `RTO` (*return to origin*) significa que vuelve al vendedor.

**Paquetes más antiguos.** Pasado un tiempo, la página muestra solo una vista archivada: último estado (`DELIVERED`, `FULFILLED` si se entregó y cerró, o `RTO DELIVERED`), día, servicio y AWB con los últimos caracteres ocultos. El historial completo solo se muestra al comprador tras un código al teléfono. Tras alrededor de un año, la página responde `AWB Not Found`.

## Quién entrega los paquetes de Shiprocket

**En India**, la mensajería que contrató el vendedor. Shiprocket indica colaborar con 42 mensajerías asociadas, entre ellas Delhivery, Blue Dart, Xpressbees, Ekart, DTDC, Shadowfax, India Post y Amazon Shipping, y aclara que el plazo depende de la mensajería, el código PIN y el servicio. La página indica el servicio, como `Delhivery Surface`.

**En el extranjero**, una mensajería local lleva el paquete ShiprocketX al centro de exportación en Delhi; después sale por avión, pasa aduanas y se entrega a un transportista del país de destino, siempre con el AWB de Shiprocket. En historiales leídos en Shiprocket, USPS asumió el reparto en EE. UU. La página muestra el servicio, como `SRX Economy` o `SRX Premium`. Shiprocket indica a vendedores 12 a 15 días laborables en económico a EE. UU., Reino Unido, Canadá y otros destinos, y 10 a 12 días con aranceles pagados a EE. UU.: cifras comerciales, no una fecha garantizada para tu compra. En sus menús también llama «ShipX» a ShiprocketX.

## Entrega fallida (NDR) y devolución (RTO)

Un NDR (*non-delivery report*) indica que no se pudo entregar. El vendedor lo ve minutos después en Shiprocket y decide si reintentar o devolver el paquete.

- **Mensajes.** Si el vendedor usa el flujo NDR para compradores, un pedido marcado como «customer requested future delivery» o «customer refused the delivery» envía un SMS —y según el plan una llamada automatizada— pidiendo confirmar la entrega. El vendedor también puede enviar un enlace por SMS para pedir otro intento y corregir dirección o teléfono.
- **Página de seguimiento.** Shiprocket afirma que los compradores pueden solicitar allí un reintento y ver el motivo de cada intento.
- **Intento que no ocurrió.** Avisa al vendedor, que puede reportar un «fake attempt» a Shiprocket con tu confirmación.
- **Tres intentos.** Las mensajerías hacen hasta tres intentos. Después el pedido pasa a RTO y vuelve al almacén o dirección de recogida del vendedor.

Una vez iniciado el retorno, pide al vendedor un reenvío o reembolso. Ver [entrega fallida](guide:missed-delivery) y [devuelto al remitente](guide:returned-to-sender).

## Paquete de Shiprocket que no llega o sin actualizaciones

**Sin cambios.** Shiprocket explica que las mensajerías actualizan al escanear, lo cual puede tardar unas horas; tras más de un día sin cambios, contacta con el vendedor. Mientras figure solo `Order Received` o una línea de recogida, la mensajería aún no lo ha retirado. Más en [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Quién actúa.** El cliente de Shiprocket es el vendedor. Ante «Whom shall I contact if I have concerns?», Shiprocket responde que te pongas en contacto con el vendedor donde compraste. También para pérdida, daño o no recepción acude al vendedor: la cobertura ShiprocketX hasta 5000 INR es para vendedores. Ver [paquete perdido](guide:lost-parcel) y [entregado pero no recibido](guide:delivered-not-received).

## Teléfono y atención al cliente de Shiprocket

Datos publicados por Shiprocket:

- **Teléfono:** 9643887070, 9711451060 y 8510042020 (números indios, +91 desde fuera). Su web de seguimiento los lista para incidencias de seguimiento junto con chat en vivo 24/7; su página de contacto indica los mismos números de 9:00 a 21:00 (hora india).
- **Correo electrónico:** support@shiprocket.in.
- **Escalado:** soporte responde en 5 días laborables. Si no se resuelve, escribe a escalation@shiprocket.com y luego a grievance@shiprocket.com; ambos responden en 3 días laborables.

La mayor parte de esta ayuda va dirigida a vendedores. Además, la web de seguimiento advierte: si un mensaje de origen no verificado te pide un pago, no respondas ni compartas datos de pago; bloquea y denuncia al remitente. Ver [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir paquetes de Shiprocket con Peek

Para un número de 13 dígitos que empiece por `832`, [Peek](/) pregunta desde el principio a Shiprocket (junto con Delhivery si tu país es India) y, solo si Shiprocket no conoce el número, a servicios de seguimiento y otros transportistas: al añadirlo muestra «Comprobando servicios de seguimiento…» y luego «Shiprocket tiene este paquete.» Como las guías de Delhivery comparten esa longitud, Peek no asigna Shiprocket solo por la forma; si no responde, muestra «Esperando el primer escaneo de Shiprocket» y sigue preguntándole, y puedes elegir Shiprocket en «Cambiar» (o «Cambiar transportista»). Un enlace `shiprocket.co/tracking/` identifica a Shiprocket sea cual sea la forma del código.

Peek lee la página de seguimiento de Shiprocket y muestra el estado, cada escaneo con su localidad si aparece, la fecha estimada de Shiprocket mientras viaja y la hora de entrega en envíos dentro de India. Los escaneos en el extranjero no llevan zona horaria: Peek lee cada uno según la hora local del lugar (una línea sin lugar, según la de una línea vecina, a veces la hora india) y lo muestra en tu zona horaria («Horas en tu zona horaria:»). Las líneas conservan su texto salvo coincidencias exactas como `Delivered`. Peek descarta vendedor, referencia de pedido y dirección del destinatario, no recibe peso ni justificante de entrega y no cambia al transportista en destino.

En envíos antiguos muestra el último estado archivado con su fecha y busca un historial más completo en servicios de seguimiento. Peek consulta hasta cada 10 minutos, y cada 2 minutos desde las 6:00 según el reloj del último escaneo (hora india en India) el día en que entra «En reparto», mientras el envío esté abierto en Peek o el día de entrega con las notificaciones activadas, siempre que responda la página de Shiprocket. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora, y tras siete días sin novedades en «Etiqueta creada», cada 6 horas. En notificaciones puedes elegir «Cada escaneo», «Pasos importantes» o «Solo la entrega». Si pasan cuatro días sin escaneo en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el …» y recuerda «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Shiprocket.» Peek sigue a más de 4500 transportistas —entre ellos [Delhivery](carrier:delhivery), [Blue Dart](carrier:blue-dart), Xpressbees, [Ekart](carrier:ekart), DTDC, Shadowfax e [India Post](carrier:india-post) por sus propios números—, pero no puede verificarte como comprador, pedir otro intento ni contactar con Shiprocket o el vendedor por ti.

## Preguntas sobre el seguimiento de Shiprocket

### ¿Es Shiprocket una empresa de mensajería?

No. Shiprocket es una plataforma de envíos: los vendedores reservan a través de ella mensajerías como Delhivery, Blue Dart o Xpressbees, que llevan el paquete. Los envíos ShiprocketX usan el AWB de Shiprocket y los entrega un transportista en el país de destino.

### ¿Qué significa RTO en el seguimiento de Shiprocket?

RTO significa *return to origin* (devolución a origen): el paquete no pudo entregarse tras hasta tres intentos —o antes si lo pide el vendedor— y regresa al vendedor. `RTO DELIVERED` indica que ya lo recibió de vuelta.

### ¿Cuál es el teléfono de atención de Shiprocket?

La web de seguimiento de Shiprocket indica 9643887070, 9711451060 y 8510042020 para incidencias de seguimiento. Para cualquier problema con tu pedido, Shiprocket indica a los compradores contactar con el vendedor.

### ¿Por qué el seguimiento de Shiprocket dice AWB Not Found?

Shiprocket no reconoce el número introducido: comprueba erratas o dígitos faltantes y espera unos minutos si acabas de hacer el pedido, tal como recomienda su página. Tampoco encuentra envíos con más de un año aproximadamente.

:::sources
- [Shiprocket: Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – seguimiento por AWB, ID de pedido o móvil, OTP y myShiprocket, AWB por correo y SMS, contactar al vendedor, actualizaciones tras escaneos, ShipX
- [Shiprocket: AWB Not Found](https://shiprocket.co/tracking) – comprobaciones si no se encuentra, aviso sobre pagos, teléfonos, chat en vivo, soporte 24/7
- [Shiprocket: Contact us](https://www.shiprocket.in/contact/) – oficina en Gurugram, horarios telefónicos, correo, escalado y plazos de respuesta
- [Shiprocket: FAQs](https://www.shiprocket.in/faq/) – plataforma para vendedores, plazos estimados, RTO, actualizaciones tras escaneos
- [Shiprocket: Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 mensajerías asociadas y sus nombres
- [Shiprocket: ShiprocketX](https://www.shiprocket.in/x/) – servicios internacionales, plazos, servicios con aranceles pagados, cobertura hasta 5000 INR
- [Shiprocket: NDR management](https://www.shiprocket.in/ndr-management/) – NDR y RTO, flujo de comprador NDR, SMS y llamadas automatizadas
- [Shiprocket Support: How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – reintentos, enlace SMS, intentos falsos, tres intentos antes de RTO
- [Shiprocket: Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – solicitud de reintento y motivos en la página de seguimiento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formas de AWB, textos de estado, hub de exportación en Delhi, USPS en EE. UU., vista archivada, qué muestra Peek
:::
