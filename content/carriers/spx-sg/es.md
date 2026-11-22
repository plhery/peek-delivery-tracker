---
title: Seguimiento SPX Express Singapur: Shopee y estados
description: Sigue un paquete de SPX Express (Shopee Xpress) en Singapur: formatos SPXSG y SG, significado de cada estado, GST en importaciones y ayuda.
slug: seguimiento-spx-express-singapore
published: 2026-11-22
updated: 2026-11-22
---

SPX Express Singapore, también conocida como Shopee Xpress o Shopee Express, reparte paquetes en Singapur. Transporta pedidos de Shopee, incluidos envíos de vendedores en China que pasan por el centro de clasificación de Shopee en Hong Kong. El localizador oficial de SPX está en spx.sg. Para seguir un paquete aquí, pega el número en el recuadro de arriba. Si es un pedido de Shopee, ábrelo en Shopee y copia el número de seguimiento en la información de envío.

## Número de seguimiento de SPX Express Singapur: cómo es

Peek conoce dos formatos para SPX Express Singapore (dígitos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `SPXSG48291736482K` | SPXSG, 11 dígitos y un dígito o letra final (17 caracteres): el número propio de SPX. Peek asigna SPX Express Singapore al instante. |
| `SG482917364829K` | SG, 12 dígitos y un dígito o letra final (15 caracteres): el número de Shopee emparejado en el rastreador de SPX. Peek no puede asignar SPX solo por esta forma (ver abajo). |
| `SPXSG4829173648` | SPXSG y solo 10 dígitos: Peek no lo reconoce. Comprueba si falta un dígito. |
| `RA482917367SG` | Dos letras, nueve dígitos y SG al final: un número de Singapore Post, no de SPX. |

Los espacios y las minúsculas no importan al pegar un número en Peek.

**El número de pedido no es un número de seguimiento.** Si el código pegado tiene forma de pedido de Shopee, Peek muestra «Parece un número de pedido de Shopee». Copia el número de seguimiento desde el pedido. ¿No lo encuentras? Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Qué significan los estados de SPX Express Singapur

El rastreador de SPX, tal como lo lee Peek, escribe cada línea en inglés. Estas son las líneas que reconoce el código de Peek, en el orden habitual de un paquete extranjero:

| SPX indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | El vendedor en China está preparando el paquete. SPX aún no lo tiene. | «Etiqueta creada» |
| `[China] Parcel has arrived at Hong Kong sorting centre` | Llegó al centro de clasificación de Shopee en Hong Kong. | «En tránsito» |
| `[China] Parcel has departed from Hong Kong sorting centre` | Salió de ese centro de clasificación. | «En tránsito» |
| `[China] Parcel has been handed over to delivery service provider` | Se entregó a un operador aéreo que continúa el transporte. | «En tránsito» |
| `[China] Parcel has cleared export customs` | Superó la aduana de salida, no todavía la de Singapur. | «En tránsito» |
| `Parcel has departed from the origin port: Hongkong` | Ha salido de Hong Kong. | «En tránsito» |
| `Parcel has arrived at the destination port Singapore` | Ha llegado a Singapur: es una llegada, no una retención aduanera. | «En tránsito» |
| `Parcel has cleared import customs` | La aduana de importación de Singapur lo ha liberado. | «En tránsito» |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | Ya está en la red de entrega de Singapur para ser clasificado antes del reparto. | «En tránsito» |
| `[Singapore] Parcel has arrived at sorting facility` | En una planta de clasificación en Singapur. | «En tránsito» |
| `[SPX Express] Parcel is out for delivery` | Está en reparto. | «En reparto» |
| `Parcel has been delivered` | Entregado. | «Entregado» |

**Otros pasos.** El código de Peek reconoce más estados de SPX: recogido al vendedor o entregado en un punto de depósito («Admitido por el transportista»), intento fallido («Intento de entrega») y devolución al vendedor («Devuelto al remitente», o «Incidencia» si el retorno se detiene). Más en [significado de los estados de seguimiento](guide:tracking-statuses).

## Entrega de SPX Express en Singapur

Después de `[SPX Express] Parcel is out for delivery`, la siguiente línea debería ser `Parcel has been delivered`. Si SPX lleva el paquete a un punto de recogida autoservicio en vez de a tu puerta, Peek muestra «En reparto» mientras va hacia allí.

Como Peek no pudo leer el sitio web de SPX Express Singapore, esta página no incluye horarios de reparto, número de intentos ni normas de recogida. Consulta al vendedor, o a Shopee si compraste allí. Ver [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Paquetes del extranjero: GST y aduanas en Singapur

Normas de Singapore Customs para compras en el extranjero enviadas por aire o correo postal:

- **GST al pagar.** Si cada artículo cuesta 400 S$ o menos y el vendedor o plataforma extranjera está registrado en GST, el impuesto ya viene incluido en el precio. El vendedor envía los datos del pago al transportista y no vuelve a cobrarse al importar. Ese importe incluye solo el artículo, sin envío ni seguro.
- **GST al importar.** Si el vendedor no está registrado en GST y el valor CIF total del envío (mercancía, seguro y flete) supera los 400 S$, la GST se cobra al entrar en Singapur. Con 400 S$ o menos no se cobra GST al importar, salvo licores y tabaco.
- **¿Cobrado dos veces?** Si pagaste GST al comprar y te la vuelven a pedir al importar, paga la GST de importación para recibir tu paquete y pide luego el reembolso al vendedor con el justificante.

**Retenido en aduanas.** La Immigration & Checkpoints Authority (ICA) puede retener paquetes de mensajería con mercancía sujeta a derechos, controlada o prohibida, o con un valor CIF total superior a 400 S$ llegados sin factura. La ICA entrega entonces un recibo de depósito al transportista, quien debe indicarte qué hacer.

**Estafas.** Singapore Customs advierte que no pide pagos de GST ni datos personales por SMS, correo electrónico o apps de mensajería. Ver [SMS falsos de entrega](guide:delivery-scam-texts) y [paquetes retenidos en aduanas](guide:customs).

## Paquete SPX sin novedades, perdido o devuelto

**Número aún no encontrado.** Cuando SPX todavía no conoce un número SPXSG, Peek muestra «Esperando a SPX Express Singapore» y sigue comprobando. Puede que el vendedor no lo haya entregado aún; revisa también el código.

**Atascado en `[China]Sender is preparing to ship your parcel`.** El vendedor aún no lo ha enviado: pregúntale cuándo saldrá.

**Sin novedades durante días.** Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Tras cuatro días sin escaneos en tránsito (fuera de aduana o incidencia marcada), Peek lo marca con «Sin novedades desde el…» y sigue preguntando a SPX. Ver [cuando el seguimiento no se actualiza](guide:tracking-not-updating) y [seguir paquetes desde China](guide:tracking-from-china).

**Entregado pero no ha llegado.** Revisa alrededores de tu puerta y pregunta en casa y a vecinos; luego contacta con el vendedor o con Shopee. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** El envío lo contrató el vendedor: pídele reembolso o reenvío, o tramítalo a través de Shopee. Ver [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de SPX Express Singapur

Como Peek no pudo leer el sitio web de SPX Express Singapore, esta página no incluye teléfono ni correo electrónico: Peek solo recoge contactos leídos en las páginas del propio transportista.

Para un pedido de Shopee, consulta mediante Shopee. En otra tienda, pregunta primero a esa tienda, ya que contrató el envío. Singapore Customs no puede ayudar porque no tiene acceso al seguimiento de mensajerías y remite al transportista.

## Seguir un paquete de SPX Express Singapur con Peek

[Peek](/) sigue un paquete SPX solo con el número: sin cuenta, teléfono ni código postal.

- **Un número SPXSG.** Peek asigna SPX Express Singapore de inmediato («Transportista detectado») y consulta directamente el rastreador de SPX. «Cambiar» permite elegir otro transportista.
- **Un enlace.** Pega un enlace al rastreador de spx.sg con un número SPXSG (`/track?`, `/m/track?` o `/detail/`) y Peek extrae el número.
- **Un número SG.** Peek no puede asegurar el transportista solo con esa forma. Pégalo igualmente: Peek sigue más de 4500 transportistas. Mientras busca verás «Comprobando servicios de seguimiento…». Para elegir SPX tú mismo, pulsa «Cambiar»: SPX Express Singapore aparece en «Encaja con este número». Aun así, conviene usar el número SPXSG si lo tienes, porque el rastreador de SPX a menudo solo encuentra pedidos de Shopee con ese código.

Si se añade sin transportista, muestra «Todavía no hay historial de seguimiento» mientras Peek consulta servicios de seguimiento. Si alguno nombra solo a SPX Express Singapore con pasos fechados, Peek lo asigna a SPX.

**Qué muestra Peek.** El estado, cada línea en las palabras de SPX y la ubicación en almacenes, puertos y centros de clasificación. SPX indica el instante exacto de cada escaneo y Peek lo muestra en tu zona horaria («Horas en tu zona horaria:»). Peek conserva también algunos pasos que la web de SPX oculta, como el despacho de importación y una devolución en marcha. Omite la ubicación del vendedor, direcciones de oficinas y el nombre de quien recibió el paquete. Como SPX no facilita fecha prevista, peso ni punto de recogida, Peek no muestra esos datos.

**De Janio a SPX.** Cuando el seguimiento de un paquete de Janio nombra a SPX con un número SPX Singapore, la tarjeta muestra «Entrega con SPX Express Singapore» y Peek consulta también a SPX. Mientras SPX solo tenga el aviso previo sin escaneo propio, aparece «Anunciado a SPX Express Singapore, aún no recibido». En cuanto SPX tiene un escaneo tan reciente como el último de Janio, Peek sigue allí el paquete y conserva el historial de Janio.

**Comprobaciones y notificaciones.** Peek consulta directamente a SPX hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 (hora de Singapur) el día en que SPX lo marca en reparto, mientras el paquete esté abierto en Peek o el día de entrega con las notificaciones activadas. Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora. Para las notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Tras cuatro días sin escaneos en tránsito, fuera de aduana o incidencia marcada, Peek muestra «Sin novedades desde el…», recuerda que «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a SPX Express Singapore.» y ofrece «Pide ayuda a SPX Express Singapore». Peek no puede contactar por ti con SPX, Shopee ni el vendedor.

## Preguntas frecuentes sobre SPX Express Singapur

### ¿SPX Express es lo mismo que Shopee Express?

Sí. El catálogo de Peek también recoge SPX Express Singapore como Shopee Xpress Singapore y Shopee Express Singapore, y entrega pedidos de Shopee. SPX opera redes independientes en otros países, como [SPX Express Brasil](carrier:spx-br): un número SPXSG corresponde a Singapur.

### ¿Qué diferencia hay entre un número SPXSG y un número SG?

Un número SPXSG es el código propio de SPX para el paquete, con el que Peek lo identifica al instante. Un número SG es el código de Shopee emparejado con él. Como el rastreador de SPX suele encontrar un paquete de Shopee solo por el SPXSG, utiliza ese.

### ¿Qué significa «Parcel has been picked up by our local logistics partner»?

Tu paquete del extranjero ya está en Singapur y pasó a la red local de reparto para su clasificación. SPX señala que recibirás aviso cuando esté listo para entregarse. Peek muestra «En tránsito».

### ¿Por qué no se actualiza mi seguimiento de SPX?

Si la última línea es `[China]Sender is preparing to ship your parcel`, el vendedor aún no lo ha enviado. Más adelante, los envíos suelen quedarse sin cambios durante vuelos, aduanas o fines de semana. Tras cuatro días sin novedades en tránsito, Peek lo señala; si sigue parado, pregunta al vendedor o a Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – regla de 400 S$, GST en pago o importación, doble cobro, estafas
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – paquetes retenidos por ICA, consultar al transportista por el seguimiento
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – nombres, formatos de número, enlaces del rastreador, textos y etapas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – códigos compartidos de SPX, pasos ocultos conservados, formato de Singapore Post, traspaso desde Janio
:::
