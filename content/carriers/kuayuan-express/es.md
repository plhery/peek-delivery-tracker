---
title: Seguimiento Kuayuan Express: códigos, relevos y ayuda
description: Sigue un paquete Kuayuan Express (跨源速递) de China a EE. UU. o Europa: formato de 14 caracteres, códigos de escaneo (AF, ND, CC), relevos y contactos.
slug: seguimiento-kuayuan-express
published: 2026-11-24
updated: 2026-11-24
---

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, en chino *跨源速递* / *深圳市跨源速递供应链有限公司*) es un operador logístico de comercio electrónico transfronterizo con sede en Shenzhen centrado en líneas de paquetería de extremo a extremo desde China hacia Europa y Estados Unidos. En [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/), Kuayuan gestiona recogida inicial y consolidación en China, transporte aéreo y marítimo, despacho aduanero automatizado por código SA (canales DDP con impuestos incluidos) y entrega de última milla mediante socios locales o su propia flota. Puedes consultar hasta 20 albaranes a la vez en [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) o pegar tu código Kuayuan de 14 caracteres en el cuadro de arriba.

## Números de seguimiento de Kuayuan Express (formato de 14 caracteres)

Los códigos transfronterizos de Kuayuan Express tienen 14 caracteres: un dígito (normalmente `3`), seguido de tres letras mayúsculas y 10 dígitos (que codifican el año en un dígito, mes, día y número de serie de 5 dígitos):

| Formato (inventado) | Qué es |
| --- | --- |
| `3KAA6101548291` | Albarán de Kuayuan Express de 14 caracteres (`3` + 3 letras + fecha `YMMDD` + serie de 5 dígitos). |
| `3KBC6102873914` | Otro código transfronterizo de Kuayuan Express de 14 caracteres. |

Kuayuan también puede facilitar el número de la línea a la que entrega el paquete (`server_hawbcode`), como un código de barras de [DPD Alemania](carrier:dpd-de), junto a su propio número de 14 caracteres. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Kuayuan Express

El sistema de seguimiento de Kuayuan combina códigos cortos en mayúsculas con descripciones en inglés que cubren operaciones en almacenes de China, aduana de exportación, entrega a aerolínea y movimientos retransmitidos del transportista de destino:

| Código Kuayuan | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `AF` | Llegó a un centro operativo o de clasificación de Kuayuan. | «En tránsito» |
| `DF` | Clasificación y preparación de salida completadas en el centro operativo. | «En tránsito» |
| `ECKSBG` | Declaración aduanera de exportación presentada en China. | «En tránsito» |
| `ECYDG` | Llegó al puerto o aeropuerto hub de salida. | «En tránsito» |
| `JH` | Entregado a la aerolínea o naviera internacional. | «En tránsito» |
| `ND` | En tránsito en la línea internacional o en el transportista local de última milla. | «En tránsito» |
| `CF` | Falló un intento de entrega en la dirección del destinatario. | «Intento de entrega» |
| `UE` | Paquete devuelto al remitente tras entrega fallida o rechazo. | «Devuelto al remitente» |
| `CC` | Entregado y firmado con éxito en destino. | «Entregado» |

Los escaneos `ND` aparecen con las palabras propias de la línea receptora: inglés para DPD Alemania, chino para China Post (`中国邮政已收取邮件`). Un escaneo con otro código, o sin código, toma su fase de la redacción.

## Plazos de entrega, aduanas y entregas a socios locales

Según [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Plazos publicados:** Kuayuan anuncia **7–10 días** puerta a puerta en su línea aérea general hacia Estados Unidos y **7–10 días** hacia los principales países europeos con vuelos fijos y operaciones locales.
- **Canales para mercancía especial:** además de carga general, Kuayuan transporta electrónica con baterías, cosméticos/líquidos y suplementos mediante canales específicos autorizados.
- **Despacho aduanero y DDP:** Kuayuan promueve canales de doble despacho con impuestos incluidos (DDP) para Europa y Estados Unidos mediante asignación automática de códigos SA. En envíos no contratados en DDP pueden aplicarse IVA de importación y aranceles en destino (incluidos los cargos de la UE sobre pedidos de fuera de la UE). Consulta [paquetes retenidos en aduanas](guide:customs).
- **Entrega local:** tras el despacho de importación, el equipo exterior de Kuayuan entrega el paquete a un socio local, como [DPD Alemania](carrier:dpd-de), o lo reparte con sus propios vehículos. El transportista local define los intentos a domicilio, puntos de recogida y nuevas entregas.

## Paquete de Kuayuan Express retenido, sin novedades o perdido

Como son las tiendas y vendedores transfronterizos quienes mantienen la cuenta de envío con Kuayuan Express, **contacta siempre primero con tu vendedor o plataforma** para cambios de dirección, reembolsos o reenvíos.

- **Silencio entre China y el destino:** el seguimiento suele detenerse varios días entre `JH` (entregado a la aerolínea) y el primer escaneo `ND` en destino mientras aterriza la carga aérea y se despachan los lotes aduaneros. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Sigue el código del transportista local:** en cuanto Kuayuan enlaza el número del socio de destino, consultar directamente a ese transportista suele mostrar franjas horarias, códigos de recogida en tienda o cambios de entrega.
- **Entrega fallida, extravío o daño:** si el seguimiento indica `CF` (intento fallido), `UE` (devuelto) o `CC` (entregado) sin haber recibido el paquete, pregunta primero a vecinos y al transportista local y abre una reclamación con tu vendedor antes de que caduque la protección al comprador. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos de Kuayuan Express

Datos oficiales publicados en [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Razón social:** *Shenzhen Kuayuan Express Supply Chain Co., Ltd.* (*深圳市跨源速递供应链有限公司*, registro ICP Guangdong `粤ICP备2025475526号`).
- **Teléfono:** `+86 15013702902`.
- **Correo electrónico:** `fanjinyun@kuayuanexpress.com`.
- **Localizador web público:** [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) (admite hasta 20 números por consulta).

> Desconfía de SMS falsos que pidan pagar una pequeña tasa de aduana o nueva entrega mediante un enlace desconocido. Confirma siempre cualquier cargo real de importación en tu página de pedido o en el sitio oficial del transportista local. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Kuayuan Express con Peek

[Peek](/) consulta automáticamente a Kuayuan Express como único candidato cuando introduces un albarán de Kuayuan de 14 caracteres que empieza por `3` (`3KAA6101548291` o `3KBC6102873914`, inventados) y muestra «Kuayuan Express tiene este paquete.» en cuanto Kuayuan lo confirma. Si tu código Kuayuan empieza por otro dígito o necesitas elegirlo manualmente, toca «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «跨源速递» o «Kuayuan» también lo encuentra).

Peek lee el localizador de Kuayuan en inglés y muestra la fase («En tránsito», «Intento de entrega», «Devuelto al remitente», «Entregado» u otra fase si un escaneo sin ninguno de los nueve códigos lo indica), el texto del escaneo, el lugar y el país de destino. Los códigos de escaneo no aparecen en ninguna aplicación de Peek. En tu propia página del paquete, Peek muestra el nombre del destinatario bajo «Destinatario» y, mientras el paquete está en camino, puede terminar la ruta del mapa en la localidad del destinatario; los enlaces compartidos no muestran ni una cosa ni la otra. Kuayuan entrega horas de escaneo sin zona horaria: Peek fecha cada escaneo en la zona horaria de su lugar (Los Ángeles para un escaneo en Los Ángeles, Pekín para Shenzhen), un escaneo sin lugar conocido en la zona del escaneo contiguo y un paquete sin lugar alguno en hora de Pekín, mostrándolos en el reloj de tu dispositivo. Cuando Kuayuan facilita el número del transportista socio y Peek reconoce a ese transportista, Peek comprueba que tiene el paquete: la ficha muestra entonces «Entrega con» seguido del nombre del transportista e incluye ambos números. El localizador de Kuayuan no proporciona fecha estimada de entrega, peso ni hora propia de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Kuayuan muestra un escaneo de salida a reparto, cada 2 minutos desde las 6:00 en la hora del lugar del último escaneo mientras el localizador propio de Kuayuan responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora sea cual sea tu modo de seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Kuayuan no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tienes el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun, 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Kuayuan Express.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Kuayuan Express.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, modificar direcciones de entrega ni contactar con Kuayuan Express o tu transportista local por ti.

## Preguntas sobre el seguimiento de Kuayuan Express

### ¿Qué es Kuayuan Express (*跨源速递*)?

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*) es un transportista logístico de comercio electrónico transfronterizo con sede en Shenzhen que opera líneas aéreas y marítimas dedicadas de China a Europa y Estados Unidos.

### ¿Qué aspecto tiene un número de seguimiento de Kuayuan Express?

Los albaranes de Kuayuan Express tienen 14 caracteres: un dígito (normalmente `3`), tres letras mayúsculas y 10 dígitos — por ejemplo `3KAA6101548291` (inventado).

### ¿Qué significan los códigos `AF`, `JH`, `ND` y `CC` en Kuayuan Express?

`AF` significa que el paquete llegó a un centro operativo de Kuayuan, `JH` que se entregó a la aerolínea, `ND` cubre el tránsito en la línea de destino y `CC` indica que el paquete ha sido entregado.

### ¿Cómo contacto con Kuayuan Express?

Contacta primero con tu vendedor en línea o mercado para incidencias con el pedido. Kuayuan Express indica `+86 15013702902` y `fanjinyun@kuayuanexpress.com` en [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/).

:::sources
- [Kuayuan Express: sitio oficial y localizador](https://www.kuayuanexpress.com.cn/) – *Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, localizador de 20 números, rutas EE. UU./Europa en 7–10 días, aduana DDP, teléfono `+86 15013702902` y correo `fanjinyun@kuayuanexpress.com`
- [Universal Parcel Scraper: kuayuan-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuayuan-express) – formato de albarán de 14 caracteres, API `getTrackInfoOutside`, 9 códigos de escaneo (`AF`–`UE`), relevo socio `server_hawbcode` y fechado de cada escaneo en la zona horaria de su lugar
:::
