---
title: Seguimiento YDH: códigos YDH...YQ y AZ y contactos
description: Rastrea un paquete transfronterizo YDH Express desde China: formatos YDH...YQ y AZ, códigos DSD y CARR, traspaso local, aduanas y soporte.
slug: seguimiento-ydh
published: 2026-11-24
updated: 2026-11-24
---

YDH (*YDH Express* / *YDH Cross Border*, en chino *义达跨境*) es un operador chino de logística transfronteriza de comercio electrónico fundado en 2005 con sede en Shanghái. Con presencia en más de 20 centros chinos de comercio electrónico — incluidos Shenzhen, Cantón, Hangzhou, Ningbo y Hong Kong — y oficinas en Japón, Estados Unidos, Reino Unido y Bélgica, YDH mueve pequeños paquetes y carga comercial a más de 200 países y regiones antes de entregarlos a transportistas locales. Puedes consultar un envío en el [portal de seguimiento de YDH](https://track.ydhex.com/track_query.aspx), o pegar tu número arriba.

## Números de seguimiento de YDH: formatos YQ y AZ

Busca el número de guía en los detalles de tu pedido o en el correo de expedición. Los números de YDH tienen 15 caracteres, empiezan por `YDH`, siguen con 10 dígitos y terminan en `YQ` o `AZ`:

| Formato (inventado) | Qué es |
| --- | --- |
| `YDH4829173640YQ` | Código transfronterizo de YDH: `YDH` + 10 dígitos + `YQ`. |
| `YDH4829173640AZ` | Código transfronterizo de YDH: `YDH` + 10 dígitos + `AZ`. |

Una vez que tu paquete supera las aduanas de destino, YDH lo entrega a un transportista local: [USPS](carrier:usps) o [GOFO](carrier:gofo) en Estados Unidos y [Poste Italiane](carrier:poste-italiane) en Italia figuran entre los operadores cuyos escaneos YDH reenvía bajo tu código `YDH`. YDH también puede indicar el número propio del transportista local. Para otros formatos transfronterizos, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados y códigos de YDH

YDH asigna códigos cortos de hito a sus escaneos de almacén, exportación y vuelo (en chino o inglés) y retransmite además los escaneos del repartidor de destino con el texto original de ese socio.

| Código / texto YDH | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Orden electrónica recibida del vendedor antes de que YDH escanee el paquete físico. | «Etiqueta creada» |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Paquete recibido en el almacén o centro de clasificación de YDH en China. | «En tránsito» |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | En cola para vuelo, despegó de China (o sufrió retraso aéreo) y aterrizó en el aeropuerto de destino. | «En tránsito» |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Despachado en aduanas de destino, entregado al transportista final, esperando su primer escaneo (`D-SCAN DELAY`) o recogido por el socio local. | «En tránsito» |
| `OD` (`Out for Delivery`) | Cargado por el transportista de destino para el reparto final. | «En reparto» |
| `DD` | Incidencia de entrega o intento fallido comunicado por el socio local. | «Intento de entrega» / «Incidencia» |
| `CC` | Entregado por el socio de destino (con nombre del firmante si consta). | «Entregado» |

**Qué significa `D-SCAN DELAY` (`DSD`).** Cuando YDH entrega un lote consolidado a un correo o mensajería local (`CARR`), pueden pasar varios días hasta que ese socio escanea cada paquete individual en su red. `D-SCAN DELAY` señala ese desfase en el traspaso: el envío sigue en camino y no está perdido.

## Cómo funciona la entrega transfronteriza de YDH

En [ydhex.com](https://www.ydhex.com/), YDH menciona más de 50 aerolíneas y navieras contratadas y despacho aduanero en 12 países. Su [página de contacto](https://www.ydhex.com/product/59/) enumera oficinas en Japón (Tokio, Narita, Osaka, Kansai y Nagoya), Estados Unidos (La Puente, California), Reino Unido (Hounslow) y Bélgica (Grâce-Hollogne, cerca de Lieja).

**Entrega local de última milla.** YDH gestiona la recogida en origen, el tramo aéreo y la aduana, y transfiere después el paquete (`CARR`) a un servicio postal o mensajería de tu país para la entrega en puerta, puntos de recogida o devoluciones. Ese transportista local fija intentos, lugares seguros y plazos de custodia.

**Aduanas y tasas de importación.** Los pedidos transfronterizos enviados por YDH quedan sujetos al IVA de importación, aranceles y tasas locales de tu país salvo pago previo al comercio o marketplace en el checkout. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete YDH bloqueado, sin novedades o no entregado

Como el contrato de transporte de YDH es con la tienda online o marketplace y no con el comprador, contacta siempre primero con el vendedor o abre una consulta en la plataforma para retrasos, cambios de dirección o reembolsos.

- **Pausas durante el vuelo o el traspaso:** son normales varios días sin novedades esperando espacio aéreo (`HBYP`), en aduanas de destino (`RC`) o esperando el primer escaneo local (`DSD` / `D-SCAN DELAY`). Consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).
- **Usa el código local tras el traspaso:** en cuanto YDH muestra el número propio del transportista local, la web de ese operador puede ofrecer opciones de entrega que el rastreador de YDH no incluye.
- **Entregado, dañado o desaparecido:** si aparece `CC` / entregado sin haber llegado, comprueba con familiares, vecinos, conserjería y el transportista local, y avisa pronto al vendedor. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos de YDH

Datos oficiales de oficinas publicados en la [página de contacto de YDH](https://www.ydhex.com/product/59/):

- **Líneas de atención en China:** `800-988-1378` / `400-888-1378`.
- **Atención al cliente Shanghái:** `(+86) 21-52277600` / `(+86) 21-52277688` (sede: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Shanghái 201100, China).
- **Oficina de Hong Kong:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Oficina de Japón (Tokio):** `+81 3-5644-5733`.
- **Oficina de Bélgica (Lieja):** `+32 470 588 200`.

> Desconfía de SMS o correos inesperados que pidan pagar con tarjeta para «liberar aduanas» o «actualizar tu dirección» de un paquete procedente de China. Comprueba cualquier cargo legítimo en la página de pedido del marketplace o en el sitio oficial de correos de tu país. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de YDH con Peek

[Peek](/) reconoce automáticamente `YDH` + 10 dígitos + `YQ` o `AZ` (`YDH4829173640YQ` y `YDH4829173640AZ`, dígitos inventados) como YDH con alta confianza, y también enlaces pegados `track.ydhex.com/track_query.aspx?track_number=`. Si necesitas elegir YDH manualmente, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete (buscar «YDH Express», «YDH Cross Border» o «义达跨境» también lo encuentra).

Peek lee directamente el seguimiento de YDH y muestra la fase («Etiqueta creada», «En tránsito», «En reparto», «Intento de entrega», «Incidencia», «Entregado»), el texto del escaneo — de YDH o del transportista local — junto con su lugar y el país de destino. Los códigos de YDH como `CARR` y `DSD` fijan la fase; Peek no los muestra. YDH fecha sus escaneos en China con hora de Shanghái y los del transportista local con la zona horaria de ese lugar; Peek los muestra en la hora de tu dispositivo. Cuando YDH facilita el número propio del transportista local y Peek lo reconoce, Peek comprueba que tiene el paquete: la ficha muestra entonces «Entrega con» y el nombre del transportista, y lista ambos números. El nombre de quien firma nunca aparece en Peek, y Peek omite la referencia de cliente y la etiqueta de socio de YDH. El rastreador de YDH no ofrece fecha estimada, peso, nombre del servicio, punto de recogida ni foto de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que YDH muestra `OD`, cada 2 minutos desde las 6:00 según la hora del último escaneo de YDH, mientras su propio sistema responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el seguimiento de YDH no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a YDH.», con un enlace «Pide ayuda a YDH» a su página de contacto. Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a YDH.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede contactar con YDH, despachar aduanas ni redirigir tu paquete con el socio local.

## Preguntas sobre el seguimiento de YDH

### ¿Qué es YDH Express?

YDH (*义达跨境*) es un operador logístico transfronterizo de comercio electrónico con sede en Shanghái fundado en 2005 que envía pequeños paquetes desde China a más de 200 países y los entrega a correos o mensajerías locales para el reparto final.

### ¿Qué significan los códigos `YDH...YQ` y `YDH...AZ`?

Ambos son códigos de guía YDH Express de 15 caracteres compuestos por `YDH`, 10 dígitos y `YQ` o `AZ` (por ejemplo `YDH4829173640YQ` o `YDH4829173640AZ`, dígitos inventados).

### ¿Qué significa `D-SCAN DELAY` (`DSD`) en el seguimiento de YDH?

`D-SCAN DELAY` indica que YDH ha transferido el paquete al transportista local del país de destino, pero ese socio aún no ha publicado su primer escaneo de admisión. El envío sigue en tránsito mientras espera la entrada local.

### ¿Cómo contacto con el servicio al cliente de YDH?

Contacta primero con tu vendedor o marketplace para reembolsos o incidencias. Para consultas al transportista, YDH publica sus líneas en China (`800-988-1378` / `400-888-1378`), Shanghái (`+86 21-52277600` / `7688`), Hong Kong (`+852 2690-1686`, `hk@ydhexpress.com`), Japón (`+81 3-5644-5733`) y Bélgica (`+32 470 588 200`) en [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH: Sitio oficial](https://www.ydhex.com/) – fundada en 2005, 20+ centros en China, 200+ países/regiones, 50+ aerolíneas y navieras, aduanas en 12 países
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – sede en Shanghái, teléfonos China (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), oficinas en Hong Kong, Japón, EE. UU., Reino Unido y Bélgica (URL de ayuda enlazada por Peek)
- [YDH: Portal de seguimiento](https://track.ydhex.com/track_query.aspx) – consulta pública de envíos YDH
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – detección `YDH` + 10 dígitos + `YQ`/`AZ`, regla de enlace `track.ydhex.com`, códigos (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), número del transportista local, relojes de escaneo y URL de ayuda
:::
