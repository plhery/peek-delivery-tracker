---
title: Seguimiento XDP: números de envío, estados y entrega
description: Rastrea un paquete de XDP en el Reino Unido: de dónde sale el número de envío, por qué XDP pide el código postal, qué significan los estados y contacto.
slug: seguimiento-xdp
published: 2026-11-22
updated: 2026-11-22
---

XDP (XDP Express) es una red británica de paquetería y carga que entrega artículos grandes y pesados para tiendas: muebles, colchones, equipamiento de jardín o gimnasio, a menudo con uno o dos repartidores. Sigue un paquete en [la página de seguimiento de XDP](https://xdp.co.uk/track/) o en la app de XDP con el número de envío (*consignment number*) y el código postal de entrega, o pega el número en la casilla de arriba. El número lo envía la tienda remitente, no XDP.

## Número de seguimiento XDP: el número de envío

XDP lo denomina *consignment number*. Su página de seguimiento lo deja claro: lo recibes de la empresa que te envía el artículo a través de XDP, así que búscalo en el correo de envío de la tienda o en tu página de pedido. Los propios SMS y correos de XDP llegan más tarde, cuando el paquete ya va de camino.

XDP no publica un formato; sus números tienen este aspecto:

| Formato (inventado) | Qué es |
| --- | --- |
| `ZDBKLM482917` | `ZDB`, tres letras y seis dígitos: un número de envío de XDP. |
| `0ZDBKLM482917` | El mismo número con un `0` delante, que puede suprimirse. |

El rastreador de XDP pide también el código postal de entrega o recogida. Un código postal incorrecto y un número mal escrito dan la misma respuesta (`Consignment not found for consignment number`), así que revisa ambos.

Ningún otro transportista británico usa el formato `ZDB`. Un número de [DPD UK](carrier:dpd-uk) tiene 14 dígitos y una guía de APC Overnight 22 dígitos: si tu correo menciona uno de ellos, síguelo allí. Más en [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de XDP

El rastreador, la app y las preguntas frecuentes de XDP están solo en inglés. Los números de ruta y horarios cambian según el envío.

| Estado de XDP | Qué significa para ti |
| --- | --- |
| `Order received` | La tienda envió a XDP los datos del pedido, pero XDP aún no tiene el artículo. |
| `At Collection Depot` | XDP tiene tu artículo y viaja hacia tu centro de reparto. |
| `At Delivery Depot` | En el centro que efectuará la entrega. |
| `Allocated to Delivery Vehicle - Route 03` | Asignado a una ruta de reparto. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Prevista su llegada hoy en esta franja de dos horas. |
| `Next stop: On time` | Eres la siguiente parada del conductor. XDP muestra `On time`, `Late by …` o `Early by …` respecto a la franja; Peek antepone `Next stop:`. |
| `Delivered` | Entregado. |
| `Carded` | El conductor lo intentó, pero no había nadie para recogerlo. |
| `Consignment Split` | El depósito solo tiene parte de tu pedido de la tienda. |
| `Check Address` | El conductor no encuentra la dirección y necesita más datos. |
| `Service Fail` | Algo ajeno al control de XDP (avería, tráfico o mal tiempo) lo retrasó. |
| `Return` | Devuelto al remitente: contacta con la tienda. |

La app de XDP redacta algunos estados de forma distinta (`Delivery scheduled`). Tras `Consignment Split`, XDP indica que los artículos restantes llegan en los días siguientes. `RETURN LABEL CREATED` inicia el regreso al remitente, y `Damaged` significa que el artículo parece dañado (ver abajo).

## Entrega de XDP: franjas horarias, entregas fallidas y recogida

XDP envía una franja de entrega de dos horas el día de la entrega cuando el conductor carga la furgoneta, por lo general antes del mediodía (hora del Reino Unido). En épocas de mucha carga un centro puede planificarlo con 24 horas o más de antelación. Luego llega un aviso «You Are Next» por SMS, correo y la app. En entregas de dos personas (2Man), XDP contacta primero con una fecha y envía la franja de dos horas ese día. En los servicios 1+ y 2Man, el repartidor puede subir el artículo a la habitación que elijas tras firmar una exención que XDP envía antes.

En la app de XDP puedes:

- elegir **Change Delivery Date** para reprogramar la entrega o, en algunos casos, escoger fecha;
- añadir instrucciones de entrega, como dejar el artículo a un vecino o en porche, cobertizo, garaje o detrás de una verja;
- dejar notas para el conductor y ver el comprobante de entrega.

XDP advierte que al pedir «Left Safe» asumes toda la responsabilidad sobre el artículo desde el momento de la entrega.

**Entregas fallidas.** `Carded` significa que el conductor no encontró a nadie. XDP indica que intenta entregar tres veces antes de devolverlo al remitente. Si no se dejó tarjeta, el conductor no puede volver ese mismo día: reprograma con Change Delivery Date.

**Sin puntos de recogida ni recogida en almacén.** XDP no dispone de tiendas de paquetería ni taquillas, y su app indica que no ofrece recogida en sus depósitos o centros. Las preguntas frecuentes te remiten a la tienda si deseas recogerlo.

## Paquete XDP no entregado, retrasado o dañado

XDP trabaja para el remitente, no para ti. Ante cualquier incidencia, aconseja comprobar primero el seguimiento en la app o web y después contactar con tu proveedor (la tienda). La tienda tiene el contrato y presenta las reclamaciones.

**Retrasado o sin actualizar.** XDP no garantiza entregas al día siguiente, menos aún en picos como festivos, Black Friday o Cyber Monday. Los conductores retenidos por el tráfico no siempre actualizan si cambia la franja. Ver [seguimiento que no se actualiza](guide:tracking-not-updating).

**Dañado.** XDP pide aceptar la entrega en lugar de rechazarla y avisar enseguida al remitente. Anota el daño en el comprobante de entrega al firmar: la guía de procedimientos de XDP para remitentes señala que una reclamación se deniega si la firma no refleja incidencia, y notas como «unchecked» o «unseen» no cuentan.

**Perdido.** Los plazos del remitente con XDP son ajustados: 33 días desde el envío para reclamar pérdida total o parcial y 7 días desde la entrega por daños, fines de semana incluidos. Avisa rápido a la tienda. Si marca `Delivered` y no tienes nada, ver [entregado pero no recibido](guide:delivered-not-received).

**Devuelto.** Tras `Return`, XDP indica contactar con tu proveedor.

## Contacto y atención al cliente de XDP

Canales de XDP para destinatarios:

- **Formulario Delivery Query** en [la página de contacto de XDP](https://xdp.co.uk/contact/). El formulario «New Business Enquiry» es solo comercial.
- **Correo electrónico:** trackingupdates@xdp.co.uk.
- **Chat en vivo** en `xdp.co.uk` y en la app de XDP; XDP no publica horarios.
- **Formulario de contacto en la app**, que pide nombre, código postal, correo y, opcionalmente, número de envío.

La web de XDP no publica teléfono. Su app menciona llamadas de tarificación especial a 7 peniques por minuto sin decir qué número; los teléfonos listados en otras webs no están publicados por XDP.

## Seguir paquetes de XDP con Peek

[Peek](/) reconoce al instante un número `ZDB` (con o sin `0` inicial) como de XDP. Los enlaces de `xdp.co.uk` (`/track/` seguido del número o `track.php?c=`) también funcionan, aunque no se extrae el código postal del enlace.

Peek muestra un campo opcional «Delivery or collection postcode» para XDP. Sin código postal no consulta a XDP en absoluto y el paquete se sigue solo mediante [servicios universales de seguimiento](guide:universal-tracker). Con él, Peek lee directamente la página de seguimiento de XDP y envía el código postal a XDP y, si hace falta, a servicios de seguimiento. Un código postal incorrecto aparece como no encontrado, igual que en la web de XDP: corrígelo con «Cambiar».

Las horas de XDP no llevan zona horaria: Peek las lee en hora del Reino Unido y las muestra en tu zona horaria («Horas en tu zona horaria:»). Lista el historial de XDP (eliminando avisos de correo/SMS y escaneos repetidos de varios bultos), el peso, el servicio, el nombre del destinatario (en «Destinatario») y el código postal de entrega, y descarta códigos de almacén o conductor, empresa remitente, referencias y número de bultos. No hay día previsto, ubicaciones por escaneo ni campo de franja aparte (salvo dentro de textos como `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`).

Con código postal, mientras tengas el paquete abierto en Peek — o el día de la entrega con las notificaciones activadas — Peek consulta directamente a XDP hasta cada 10 minutos, y hasta cada 2 minutos a partir de las 6:00 (hora del Reino Unido) el día en que pasa a reparto, mientras el rastreador de XDP responda; fuera de ese caso, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), cada hora. Sin código postal, solo mediante servicios universales de seguimiento, comprueba cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de la entrega si el paquete está abierto en Peek o las notificaciones están activadas. Tras cuatro días sin nuevos escaneos en un envío en camino, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a XDP.», junto al enlace «Pide ayuda a XDP» hacia su página de contacto. Te avisa según elijas: «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas sobre el seguimiento de XDP

### ¿Por qué no funciona mi seguimiento de XDP?

Normalmente por el código postal: el rastreador de XDP necesita el número de envío y el código postal de entrega o recogida, y un código erróneo muestra «Consignment not found». Compruébalos en el correo de la tienda.

### ¿Cuánto tarda XDP en entregar?

XDP indica a los remitentes de 1 a 3 días para su servicio 1Man y de 3 a 5 días para 1+ y 2Man, sujeto a transporte marítimo para islas. El día de entrega incluye una franja de dos horas.

### ¿Puedo recoger mi paquete en un almacén de XDP?

No. La app de XDP indica que no ofrece recogida en sus depósitos ni centros, y las preguntas frecuentes remiten a la tienda. Puedes reprogramar la entrega o pedir que se entregue a un vecino.

### ¿Es XDP una empresa de transporte real?

Sí. X D P Limited figura registrada en Companies House desde 1995, con sede en Curdworth, cerca de Sutton Coldfield, y reparte en Gran Bretaña continental, Irlanda del Norte, las islas del Canal y la isla de Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – número de envío del remitente, código postal, avisos del rastreador
- [XDP: FAQs](https://xdp.co.uk/faqs/) – significado de estados, contactar al proveedor, recogida en almacén, reprogramar en la app
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – formulario Delivery Query, correos
- [XDP: About Us](https://xdp.co.uk/about-us/) – fundada en 1995, cobertura, franja de dos horas, prueba de entrega
- [XDP: Home](https://xdp.co.uk/) – carga grande y no estándar, servicios, app
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 a 3 días, notificaciones, teléfono puente
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 a 5 días, habitación elegida, You Are Next, chat y correo
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – fecha concertada, franja de dos horas
- [XDP: App (web version)](https://app.xdp.co.uk/) – franja de entrega, tres intentos, Change Delivery Date, left safe, sin recogida en almacén, daños, devoluciones, llamadas, formulario de contacto
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – sin garantía al día siguiente, picos de demanda, plazos de reclamación
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 y 7 días, anotar daños en el comprobante de entrega
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – código postal y avisos de envío no encontrado
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – registro, oficina
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – la app de XDP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lo que Peek lee
:::
