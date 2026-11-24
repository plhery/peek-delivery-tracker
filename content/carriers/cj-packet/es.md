---
title: Seguimiento CJ Packet: códigos CJP...YQ, traspaso y ayuda
description: Sigue un paquete de CJ Packet (CJ Dropshipping) desde China: códigos CJP...YQ, estados propios y locales, pausas en aduana y correo de soporte.
slug: seguimiento-cj-packet
published: 2026-11-24
updated: 2026-11-24
---

CJ Packet (*CJPacket*) es el servicio internacional de envío y seguimiento de paquetes vinculado a [CJ Dropshipping](https://cjdropshipping.com/), que traslada pequeños pedidos de comercio electrónico, envíos económicos a granel y envíos rápidos exprés desde almacenes logísticos en China hasta compradores de todo el mundo mediante transporte aéreo internacional y transportistas locales de última milla. Puedes consultar un envío directamente en [cjpacket.com](https://www.cjpacket.com/) o pegar tu número de seguimiento en el recuadro de arriba.

## Números de seguimiento de CJ Packet: formato CJP...YQ

Busca tu número de envío de CJ Packet en el correo de confirmación o en la página de pedido de tu tienda online. Los códigos de CJ Packet empiezan por `CJP`, seguidos de 2 o 3 letras (que identifican la línea de envío), 10 dígitos y `YQ` (17 o 18 caracteres en total):

| Formato (inventado) | Qué es |
| --- | --- |
| `CJPOS4829173640YQ` | Número de envío de CJ Packet de 17 caracteres (`CJP` + 2 letras de línea + 10 dígitos + `YQ`). |
| `CJPAEE4829173640YQ` | Número de envío de CJ Packet de 18 caracteres (`CJP` + 3 letras de línea + 10 dígitos + `YQ`). |

Cuando tu pedido aterriza en el país de destino y supera la aduana, CJ Packet lo entrega a un mensajero o correo nacional local — como [USPS](carrier:usps), [Canada Post](carrier:canada-post), [FedEx](carrier:fedex) o [CTT Express](carrier:ctt-express) — y reenvía el código local y los escaneos de entrega de ese transportista dentro del historial `CJP...YQ`. Para otros prefijos de comercio electrónico chino, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de CJ Packet

CJ Packet combina un estado general resumido (`0` a `7`) con escaneos detallados línea a línea del almacén de CJ, del transitario de exportación y de la mensajería de última milla en destino:

| Resumen CJ Packet (código) / texto del escaneo | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Processing` (`0`) / `Label created. Warehouse is processing this order.`, `Shipment info received` | Pedido empaquetado o etiqueta creada en el almacén de CJ antes de la recogida del transitario. | «Etiqueta creada» |
| `Dispatched` (`1`) / `Item has been collected` | Paquete entregado desde el almacén de CJ al transitario internacional. | «Admitido por el transportista» |
| `En Route` (`2`), `Arrived Courier Facility` (`3`) / `Item Flight departure`, `Item Flight landing`, `Customs Clearance Completed`, `Delivery to local courier` | Volado desde China al país de destino, despachado en aduana y transferido al centro de clasificación del mensajero local. | «En tránsito» |
| `Inbound to customs temporary warehouse`, `In clearance processing - Import` | En proceso de despacho aduanero de importación en destino. | «En aduanas» |
| `Out for Delivery` (`4`) / `Out for delivery` / `En reparto` | En reparto hoy con tu repartidor local. | «En reparto» |
| `Available for Pickup` (`5`) | Listo para recoger en una oficina postal o punto de recogida local. | «Listo para recoger» |
| `Unsuccessful Delivery` (`6`) | No se pudo completar la entrega (por ejemplo dirección incorrecta o destinatario ausente). | «Incidencia» |
| `Delivered` (`7`) / `Package delivered`, `Delivered, At the door/Yard`, `Entregado` | Entregado por el socio de destino (a veces indicando quién lo recibió). | «Entregado» |

Ten en cuenta que avisos como `EXPECTED DELIVERY DATE UPDATED` de un transportista local son estimaciones informativas y no cambian la fase real de tránsito del paquete.

## Cómo funciona la entrega internacional de CJ Packet

En [cjpacket.com](https://www.cjpacket.com/), CJ Packet ofrece tres modalidades principales — *Small-package Transportation*, *Low-cost Bulk Packages* y *Fast Express* — combinando líneas aéreas asociadas, despacho aduanero y mensajeríes locales en todo el mundo.

**Quién hace el reparto en domicilio.** Una vez aparece `Delivery to local courier`, el correo o mensajero local que se hace cargo fija los intentos de entrega, depósitos seguros, custodia en puntos de recogida y devoluciones. La web de CJ Packet está dirigida a los vendedores que envían con ella: ante retrasos, reembolsos o reclamaciones, contacta con la tienda online donde compraste. Para IVA de importación, aranceles y retenciones aduaneras (incluidas las reglas de la UE sobre paquetes extracomunitarios), consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de CJ Packet detenido, sin cambios o marcado entregado

Dado que tu contrato de compra es con la tienda online (no con CJ Dropshipping ni con CJ Packet), contacta siempre primero con el vendedor si tu pedido se retrasa, llega dañado o falta.

- **Pausas tras `Label created` o salida del vuelo:** esperar varios días mientras el almacén de CJ prepara el artículo (`Label created. Warehouse is processing this order.`) o mientras la carga aérea pasa la aduana de destino es habitual en pequeños paquetes internacionales. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Usa el número del mensajero local:** cuando CJ Packet muestre el número del socio local (por ejemplo USPS, Canada Post o un mensajero local), consultarlo directamente puede mostrar direcciones de recogida u opciones de nueva entrega.
- **Entrega fallida (`6`) o marcado entregado sin recibirlo:** comprueba primero con tu familia, vecinos, portería y transportista local, y escribe de inmediato al vendedor antes de que venzan los plazos de disputa de tarjeta o plataforma. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de CJ Packet

Contactos oficiales publicados en [cjpacket.com](https://www.cjpacket.com/) y [cjdropshipping.com](https://cjdropshipping.com/):

- **Correo de soporte logístico:** `logistics@cjpacket.com` (atención al cliente 24/7 indicada en [cjpacket.com](https://www.cjpacket.com/)).
- **Portal de seguimiento:** [cjpacket.com](https://www.cjpacket.com/).
- **Plataforma de dropshipping:** [cjdropshipping.com](https://cjdropshipping.com/) (soporte logístico para tiendas).

> Ignora los SMS o mensajes de WhatsApp no solicitados que te pidan pagar una pequeña tasa con tarjeta en un enlace externo para «entregar» o «despachar» un paquete. Verifica cualquier tasa real en tu pedido de la tienda o en la web oficial de tu correo nacional. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un paquete de CJ Packet con Peek

[Peek](/) reconoce automáticamente `CJP` + 2 o 3 letras + 10 dígitos + `YQ` (`CJPOS4829173640YQ` y `CJPAEE4829173640YQ`, dígitos inventados) como CJ Packet con alta confianza, y también detecta enlaces pegados de `cjpacket.com/?trackingNumber=`. Si necesitas elegir CJ Packet a mano, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «CJPacket» o «CJ Dropshipping» también lo encuentra).

Peek lee directamente el rastreador de CJ Packet y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Listo para recoger», «Incidencia», «Entregado»), el texto de los escaneos del almacén, del transitario y del mensajero local sin teléfonos, correos ni líneas de soporte de los mensajeros, el lugar del escaneo y el país de destino. El nombre del servicio o línea queda fuera de todas las apps de Peek, y el nombre de quien recibió el paquete se mantiene privado. Un escaneo con desplazamiento horario lo conserva, uno sin desplazamiento se fecha en la zona horaria de su lugar, y Peek los muestra todos en la hora de tu dispositivo, con la hora de entrega cuando el escaneo de entrega incluye desplazamiento. Cuando CJ Packet indica el mensajero local y su número y Peek lo reconoce, comprueba que tiene el paquete: entonces aparece «Entrega con» seguido del nombre del transportista y se muestran ambos números. Peek omite el país de origen, números alias, referencias de pedido/comerciante y datos de contacto; el rastreador de CJ Packet no da fecha estimada de entrega, peso ni foto de prueba de entrega.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que CJ Packet muestra `Out for Delivery` (`4`), cada 2 minutos desde las 6:00 en la hora del lugar del último escaneo, mientras responda el rastreador de CJ Packet. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora sea cual sea tu seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de CJ Packet no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a CJ Packet.» Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a CJ Packet.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, contactar con CJ Packet ni solicitar un reembolso a tu tienda online.

## Preguntas frecuentes sobre el seguimiento de CJ Packet

### ¿Qué es CJ Packet?

CJ Packet es el servicio internacional de envío y seguimiento usado por tiendas que gestionan pedidos mediante [CJ Dropshipping](https://cjdropshipping.com/) desde almacenes en China hacia compradores de todo el mundo.

### ¿Qué significa un número de seguimiento `CJP...YQ`?

Los códigos que empiezan por `CJP`, 2 o 3 letras de ruta, 10 dígitos y `YQ` (como `CJPOS4829173640YQ` o `CJPAEE4829173640YQ`, dígitos inventados) son guías internacionales de CJ Packet.

### ¿Quién entrega un paquete de CJ Packet localmente?

Tras volar desde China y superar la aduana en destino, CJ Packet entrega el paquete (`Delivery to local courier`) a un correo nacional o mensajero local (como USPS, Canada Post, FedEx o un transportista regional europeo) para la entrega en domicilio.

### ¿Cómo contacto con atención al cliente de CJ Packet?

Contacta primero con la tienda online donde hiciste la compra para reembolsos o incidencias de dirección. El correo logístico publicado por CJ Packet en [cjpacket.com](https://www.cjpacket.com/) es `logistics@cjpacket.com`.

:::sources
- [CJ Packet: portal oficial de seguimiento](https://www.cjpacket.com/) – Small-package Transportation, Low-cost Bulk Packages, Fast Express, soporte 24/7 y `logistics@cjpacket.com`
- [CJ Dropshipping](https://cjdropshipping.com/) – plataforma matriz de fulfillment e-commerce
- [Universal Parcel Scraper: cj-packet](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cj-packet) – detección `CJP[A-Z]{2,3}\d{10}YQ`, regla de enlace `cjpacket.com/?trackingNumber=`, códigos resumen `0`–`7`, traspaso a socio local y lectura de firmante, relojes con desplazamiento/locales y campos eliminados
:::
