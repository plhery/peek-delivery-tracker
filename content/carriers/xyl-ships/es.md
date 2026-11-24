---
title: Seguimiento XYL-Ships: códigos NUDE y XLWL y estados
description: Rastrea un paquete XYL-Ships (FXYL) desde China: formatos NUDE y XLWL, qué significa cada estado, pausas sin avisos, aduanas de la UE y contacto.
slug: seguimiento-xyl-ships
published: 2026-11-24
updated: 2026-11-24
---

XYL-Ships (XYL-ships International Logistics, también visible como FXYL) es un operador logístico chino de envíos transfronterizos que cuenta con acreditaciones de agencia de carga aérea reconocidas por la Administración de Aviación Civil de China (CAAC) y la IATA. Además de carga comercial aérea, marítima, ferroviaria y por carretera, transporta pequeños paquetes individuales de comercio electrónico desde China a destinos internacionales y los entrega a correos o mensajerías locales para el reparto final. Puedes consultar un envío mediante la opción «Track Order» en [xylships.com](https://xylships.com/), o pegar el número de seguimiento en el cuadro superior.

## Números de seguimiento de XYL-Ships: formatos NUDE y XLWL

Busca el número de seguimiento en el correo de envío de la tienda online o en tu historial de pedidos. Los paquetes pequeños de XYL-Ships suelen llevar un código alfanumérico de 16 o 17 caracteres que empieza por `NUDE` o `XLWL` y termina en `EX` o `EXP`:

| Formato (inventado) | Qué es |
| --- | --- |
| `NUDE4829173640EX` | `NUDE`, 10 dígitos y `EX`. |
| `XLWL4829173640EX` | `XLWL`, 10 dígitos y `EX`. |
| `XLWL4829173640EXP` | `XLWL`, 10 dígitos y `EXP`. |

Al ser códigos propios de XYL-Ships, no se confunden con los códigos de barras de correos nacionales. Si la tienda te ha facilitado en su lugar un código postal internacional S10 (dos letras, nueve dígitos y dos letras de país como `CN`) o el código de otra mensajería, consulta [formatos de números de seguimiento](guide:tracking-number-formats) para ver a qué transportista corresponde.

## Qué significan los estados de seguimiento de XYL-Ships

XYL-Ships publica sus hitos de seguimiento en inglés, desde la clasificación de origen en China hasta el transporte internacional y la entrega al socio local de distribución en el país de destino:

| XYL-Ships muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment inbound in sorting center` | XYL-Ships recibió y escaneó el paquete en su centro de clasificación de origen. | «En tránsito» |
| `Departed from sorting center`, `Arrived at sorting warehouse`, `Leaving warehouse` | Avanza por los almacenes de clasificación en origen antes de pasar al transporte internacional. | «En tránsito» |
| `Hand over to transportation company`, `Departed from origin transportation center`, `Arrived at destination transportation center` | Entregado al transportista de línea internacional, salió de China y llegó al centro de entrada del país de destino. | «En tránsito» |
| `Released and the package is being transferred`, `Package is being transferred locally` | Liberado en el país de destino y en traslado nacional. | «En tránsito» |
| `Transfer again, send to the transportation partner department`, `Arrival at transportation partner department, waiting for processing`, `Leaving transportation partner department, sent to the transit hub` | Transferido al departamento de gestión del socio de entrega local y despachado hacia su centro de tránsito. | «En tránsito» |
| `Arriving at the transit hub, waiting for distribution`, `Leave the transit hub, transport to the destination city`, `Arriving at the destination city, about to distribute to the post office` | Clasificado en el centro regional y transportado a tu ciudad de destino para la entrega a la red local. | «En tránsito» |
| `The courier has received this parcel for delivery and will set a delivery time shortly` | El mensajero local ya tiene el paquete, pero todavía no ha salido a reparto. | «En tránsito» |
| `Parcel currently being delivered` | En ruta con el repartidor local hoy. | «En reparto» |
| `Delivered` | Registrado como entregado en tu dirección. | «Entregado» |

Fíjate en dos avisos fáciles de malinterpretar cerca del final del trayecto. `Arriving at the destination city, about to distribute to the post office` indica que el paquete se entrega a la red postal o al socio local, no que esté esperando a que acudas a una oficina de correos. Igualmente, `The courier has received this parcel for delivery and will set a delivery time shortly` sigue apareciendo en «En tránsito» porque el reparto aún no ha comenzado: espera a ver `Parcel currently being delivered` para contar con la entrega ese día.

## Cómo funciona la entrega transfronteriza de XYL-Ships

En [xylships.com](https://xylships.com/), XYL-Ships presenta carga aérea comercial (incluidos envíos aéreos nacionales exprés de 6 y 12 horas y consolidación puerta a puerta FBA), consolidación marítima en contenedores, transporte ferroviario y por carretera, además de pequeños paquetes individuales de comercio electrónico transfronterizo — citando un caso de cliente con el 99 % de entregas completadas en 20 días.

**Entrega local en la última milla.** Como muestran las propias líneas de seguimiento de XYL-Ships, una vez que el paquete llega al país de destino se transfiere a un socio de transporte local o al servicio postal para la entrega final.

**Reglas de entrega no publicadas.** `xylships.com` no publica el número de intentos de entrega, normas de taquillas, plazos de custodia, garantías de tránsito ni plazos de reclamación. Una vez que el envío entra en tu país, las condiciones concretas (entrega a un vecino, depósito en lugar seguro o días de recogida tras aviso) dependen del servicio postal o de mensajería local encargado del reparto.

**Aranceles y aduanas.** Al salir desde China, los paquetes de XYL-Ships que entran en la UE, Reino Unido, Estados Unidos u otros países están sujetos al IVA de importación y a la normativa aduanera local. Para envíos hacia la Unión Europea, ten en cuenta que se aplica un arancel fijo de 3 € por tipo de artículo a los paquetes de hasta 150 € y una tasa europea de tramitación de 2 € por tipo de artículo sea cual sea su valor. Consulta [paquetes retenidos en aduanas](guide:customs) para saber cómo afectan los controles aduaneros a la entrega.

## Paquete XYL-Ships bloqueado, sin cambios o no recibido

Si compraste en una tienda online que envió con XYL-Ships, el vendedor es tu parte contratante y el contacto principal para retrasos, reembolsos o reemplazos.

**Largos silencios en tránsito.** Son habituales las pausas de varios días entre `Departed from origin transportation center` y `Arrived at destination transportation center` mientras los envíos esperan vuelo, despacho aduanero y escaneo de entrada en destino. Consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Sin número local en Peek.** Peek sigue el envío a través del socio local con tu código `NUDE` o `XLWL` y no muestra una referencia local distinta. Si falla una entrega y necesitas el nombre o código del transportista local, pregúntaselo al vendedor.

**Marcado `Delivered` pero no aparece.** Revisa primero tu buzón, portal, conserjería y vecinos. Si sigue sin aparecer, avisa al vendedor de inmediato y pide el comprobante de entrega. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Dañado, perdido o devuelto.** Guarda fotos del embalaje dañado y escribe a la tienda enseguida, o abre una disputa de protección al comprador en la plataforma o medio de pago antes de que venza el plazo. Consulta [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Contactar con XYL-Ships (y por qué preguntar primero al vendedor)

`xylships.com` es un sitio de transitario orientado a empresas con muy pocos datos de soporte al consumidor:

- **Correo electrónico:** `fzxyl9527@163.com` (único contacto publicado al pie de [xylships.com](https://xylships.com/)).
- **Teléfono, dirección postal y portal de reclamaciones:** no hay ninguno publicado en `xylships.com`.

Como XYL-Ships trabaja con remitentes empresariales y no con destinatarios particulares, contacta primero con la tienda online o marketplace ante envíos desaparecidos, errores de dirección o reembolsos: el comercio tiene acceso directo a su cuenta de carga y a los procesos de protección al comprador.

> Desconfía de SMS o correos inesperados que te pidan pulsar un enlace de pago para «desbloquear» o «reprogramar» un paquete de XYL-Ships. Confirma cualquier pago aduanero legítimo directamente con el servicio postal o la autoridad aduanera de tu país, nunca a través de un mensaje sin verificar. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete XYL-Ships con Peek

[Peek](/) reconoce automáticamente los números `NUDE` (`NUDE` + 10 dígitos + `EX`) y `XLWL` (`XLWL` + 10 dígitos + `EX` o `EXP`) como de XYL-Ships, con alta confianza. Como XYL-Ships no usa enlaces propios de seguimiento en Peek, pega directamente el código en lugar de una URL; si necesitas elegir el transportista manualmente, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y selecciona XYL-Ships (buscar «FXYL» también lo encuentra).

Peek lee directamente el flujo JSON de XYL-Ships y muestra la fase («En tránsito», «En reparto» o «Entregado»), las descripciones en inglés de XYL-Ships, la ubicación del escaneo cuando el flujo la incluye y marcas de tiempo con huso horario explícito (mostradas en la hora de tu dispositivo). Peek descarta direcciones de remitente y destinatario y referencias internas de cliente. No muestra fecha estimada, peso, número del transportista local ni foto de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que XYL-Ships muestra `Parcel currently being delivered`, cada 2 minutos desde las 6:00 según la hora del escaneo más reciente de XYL-Ships, mientras su propio sistema responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo. Cuando el seguimiento de XYL-Ships no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a XYL-Ships.» Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a XYL-Ships.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede indicar el transportista local ni contactar con XYL-Ships por ti.

## Preguntas sobre el seguimiento de XYL-Ships

### ¿Qué es XYL-Ships (FXYL)?

XYL-Ships International Logistics es un transitario chino y operador de envíos transfronterizos de comercio electrónico acreditado ante la CAAC y la IATA. Las tiendas lo utilizan para sacar paquetes pequeños de China antes de entregarlos a transportistas locales en tu país.

### ¿Qué significan los códigos `NUDE...EX` y `XLWL...EX`?

Ambos prefijos son formatos de seguimiento de XYL-Ships: `NUDE` o `XLWL` seguidos de 10 dígitos y `EX` (o `EXP` en algunos paquetes `XLWL`). Pega el código completo en Peek o en el cuadro Track Order de [xylships.com](https://xylships.com/).

### ¿Por qué mi seguimiento de XYL-Ships dice `about to distribute to the post office`?

Significa que el paquete ha llegado a tu ciudad de destino y está siendo entregado a correos o al socio local para la última milla. Espera a ver `Parcel currently being delivered` antes de contar con recibirlo ese mismo día.

### ¿Quién reparte los paquetes de XYL-Ships en mi país?

XYL-Ships entrega los paquetes a un socio de transporte local o al servicio postal tras su entrada en el país de destino y no indica su nombre en el seguimiento; Peek tampoco muestra un segundo código. Si la entrega falla, pide al vendedor los datos del transportista local.

:::sources
- [XYL-Ships International Logistics](https://xylships.com/) – acreditaciones CAAC e IATA de agencia de carga aérea, transporte aéreo/marítimo/ferroviario/terrestre y paquetería individual transfronteriza, opción Track Order y correo `fzxyl9527@163.com`
- [Universal Parcel Scraper: xyl-ships](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xyl-ships) – detección de formatos `NUDE` y `XLWL`, alias FXYL, integración con tracker JSON, mapeo de estados en inglés, campos descartados y frecuencia de actualización
:::
