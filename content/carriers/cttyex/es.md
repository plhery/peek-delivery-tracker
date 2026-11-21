---
title: Seguimiento CTTYEX: códigos CTTY…CN, estados y hora Pekín
description: Rastrea un paquete de CTTYEX (诚泰通运) desde Shenzhen: formato CTTY…CN, escaneos en inglés en su web china, hora de Pekín y seguimiento con Peek.
slug: seguimiento-cttyex
published: 2026-11-21
updated: 2026-11-21
---

CTTYEX es *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`, abreviado `诚泰通运`), un transitario de comercio electrónico transfronterizo con sede en Shenzhen (China continental) que envía paquetes al extranjero para vendedores online. Su web indica que nació en 2011, cubre más de 200 países y regiones, cuenta con más de seis sucursales en China continental y trabaja para vendedores de Amazon, Wish, Tophatter, VOVA y Shopify con vuelos chárter a Estados Unidos, Reino Unido, Canadá, Alemania y Australia. Sus códigos siguen el patrón `CTTY`, diez dígitos y `CN` (`CTTY` por *Chengtai Tongyun*): búscalo en [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) o pégalo en el cuadro superior.

## Número de seguimiento de CTTYEX: cómo es

La web de CTTYEX no documenta su formato, pero los códigos que asigna tienen una forma fija (ejemplos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `CTTY4829105736CN` | `CTTY`, diez cifras y `CN`: un código de CTTYEX. |
| `CTTY482910573CN` | Solo nueve dígitos: falta un carácter. Si ningún operador responde por él, su página en `peektracker.com` muestra «Empieza como un número de seguimiento de CTTYEX, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» |
| `LX482910576CN` | Dos letras, nueve cifras y `CN`: un número de [China Post](carrier:china-post), no de CTTYEX, aunque acabe en `CN`. |

Peek admite minúsculas y espacios. La web de CTTYEX no: su casilla solo acepta letras y dígitos y su rastreador únicamente encuentra el código en mayúsculas (`CTTY…CN`, sin espacios).

**Dónde encontrarlo.** Los clientes de CTTYEX son los comercios online, así que el vendedor te facilita el código en el correo de envío o en la página del pedido ([cómo encontrar el número de seguimiento](guide:find-tracking-number)).

**En la web de CTTYEX.** Abre `物流查询` (`search.html`), escribe un código por línea en mayúsculas y pulsa `提交` (enviar): se abrirá el rastreador `快件跟踪` (`index/tracknumber/track`), que solo conserva paquetes de los últimos dos meses.

## Estados de seguimiento de CTTYEX explicados

El buscador está en chino, pero cada escaneo aparece en inglés (el más reciente arriba) bajo una fila resumen en chino con el país de destino (`目的地`) y un estado general (`状态`):

| Resumen CTTYEX | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `运输途中` (`In Transit` en inglés) | En tránsito; puede figurar cuando aún solo constan los datos del pedido: fíjate en los escaneos inferiores. | «En tránsito» |
| `查询不到` (`Not Found` en inglés) | No encontrado: el rastreador no conoce ningún envío con ese código ni lista escaneos. | No encontrado |

Primero aparecen los pasos en China continental, sin localidad:

| CTTYEX indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order information received` | Los datos electrónicos del pedido llegaron a CTTYEX desde el vendedor; el paquete sigue con el vendedor. | «Etiqueta creada» |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Recibido en el punto de admisión transfronterizo de CTTYEX en China continental: ya está en sus manos. | «Admitido por el transportista» |
| `Goods leave the operation center` | Salió del centro de operaciones de CTTYEX en China continental. | «En tránsito» |
| `Arrival at domestic internationality airport` | En el aeropuerto de salida en China continental antes del vuelo (`internationality` es la grafía de CTTYEX). | «En tránsito» |
| `Departure from the airport of origin domestic` | Ha volado fuera de China continental. | «En tránsito» |

Tras el vuelo, las líneas proceden del agente de CTTYEX en tu país y de la red encargada del reparto:

| CTTYEX indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Arrive at the destination international airport`, `Item arrives at destination airport` | El avión ha aterrizado en tu país. | «En tránsito» |
| `Import customs clearance` | Aduana de importación (escrita tras la ciudad de entrada); aún no liberado. | «En aduanas» |
| `transfer to local acting company International self-operation team` | Entregado al agente local de CTTYEX en tu país tras pasar aduana. | «En tránsito» |
| `Leave the … processing center` | Salió de un centro de procesamiento (cuya localidad ocupa el lugar de los puntos suspensivos). | «En tránsito» |
| `Internationaliy Package received at processing center` | Primer escaneo de la red local de entrega (`Internationaliy` es la grafía de CTTYEX; no es una nueva admisión). | «En tránsito» |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | En una instalación de la red de reparto (llamada solo `logistics carrier`), de camino a la siguiente. | «En tránsito» |
| `Accepted at logistics carrier Regional Destination Facility` | Primer escaneo de la red de reparto en tu región. | «En tránsito» |
| `Arrive at the last mile sorting and distribution center` | En el centro de clasificación y distribución de última milla cercano a ti. | «En tránsito» |
| `Leaving the sorting center` | Ha salido de ese centro de clasificación. | «En tránsito» |
| `Arrive at the sorting and distribution center` | En un centro de clasificación (la ciudad aparece tras `Arrive`). | «En tránsito» |
| `package is on the way` | Sigue al escaneo del centro de clasificación de esa ciudad; no confirma aún que haya salido a reparto. | «En tránsito» |
| `Delivered` | Entregado por la red local (la fila se abre con la localidad y el código postal). | «Entregado» |

En Estados Unidos, las tres filas entre `Accepted at logistics carrier Regional Destination Facility` y `Leaving the sorting center` empiezan por ciudad, estado y código ZIP. Cualquier otra frase se muestra tal como la escribe CTTYEX.

**Todas las horas son hora de Pekín.** El rastreador imprime las horas sin zona horaria, pero todas están en hora de Pekín (`UTC+8`), **incluidos los escaneos realizados en tu país**. Como en Pekín cambia el día a las `16:00 UTC`, un escaneo hecho en la España peninsular tras las `17:00` en invierno (o las `18:00` en verano) — o en América desde media mañana o mediodía — aparece en el buscador con fecha del día siguiente.

## Quién entrega los paquetes de CTTYEX

CTTYEX recoge el envío en China continental y lo despacha en avión; tras aterrizar, su agente tramita la aduana de importación y una red local reparte. Ni su web ni su rastreador revelan qué empresa es (`logistics carrier`) ni dan un segundo número: sigue siempre el código `CTTY…CN`. CTTYEX tampoco publica normas de intentos de entrega, puntos de recogida ni plazos de custodia: guíate por el plazo estimado de la tienda y, si un repartidor local te deja aviso, revisa [entrega fallida](guide:missed-delivery).

## Paquete de CTTYEX parado, en aduanas o que no llega

**Solo `Order information received`.** CTTYEX recibió los datos pero aún no el paquete físico, aunque el resumen ponga `运输途中`. Si pasan varios días igual, pide confirmación al vendedor.

**Silencio tras `Departure from the airport of origin domestic` o cuatro días parado.** Entre el despegue y el aterrizaje o la aduana en tu país no hay pasos intermedios; a los cuatro días en ruta sin novedades, Peek indica «Sin novedades desde hace cuatro días» ([por qué no se actualiza el seguimiento](guide:tracking-not-updating), [seguimiento de paquetes desde China](guide:tracking-from-china)).

**En `Import customs clearance`.** El rastreador no indica tasas ni empresa local, por lo que no respalda SMS ni correos que pidan pagar por un envío de CTTYEX. Lee antes [mensajes falsos de entrega](guide:delivery-scam-texts) y consulta [paquetes retenidos en aduanas](guide:customs).

**`查询不到`, perdido o devuelto.** Comprueba el código con el vendedor y escríbelo en mayúsculas (el buscador también borra envíos de más de dos meses). Como CTTYEX no ofrece reclamaciones a destinatarios, solicita reembolso o reemplazo al vendedor o al marketplace ([paquete perdido](guide:lost-parcel), [devuelto al remitente](guide:returned-to-sender)).

## Contacto de CTTYEX

La [página 联系我们 (contacto) de CTTYEX](https://www.cttyvip888.com/assets/html/contact.html), escrita solo en chino, muestra una dirección en Fuyong, distrito de Bao'an, Shenzhen, China continental (`深圳市宝安区福永街道怀德新村8巷12号一层`), y un correo electrónico (`cttywlvip888@163.com`), sin teléfono, chat, formulario ni atención para destinatarios.

**Cuidado con los SMS falsos.** Los dominios oficiales de CTTYEX son `www.cttyvip888.com` y `cttyvip888.com` ([mensajes falsos de entrega](guide:delivery-scam-texts)). Para tu pedido, escribe al vendedor.

## Seguir un paquete de CTTYEX con Peek

[Peek](/) identifica a CTTYEX al instante por el formato `CTTY…CN` («Transportista detectado»), exclusivo de CTTYEX en su catálogo, o al pegar un enlace de `cttyvip888.com` con el número tras `nos=` (si eliges CTTYEX a mano para algo fuera de 8 a 40 letras y dígitos con al menos un dígito, indica que «no emite números como este»).

Peek lee estado, escaneos y país de destino desde el rastreador de CTTYEX: cuando una fila en el extranjero empieza por ciudad (o ciudad y estado), la convierte en localidad del paso eliminando el código postal, omite duplicados, teléfonos y firmante, y convierte cada hora desde la hora de Pekín (`UTC+8`) a la zona de tu dispositivo («Horas en tu zona horaria: …») para cuadrar las fechas con tu día. El estado obedece al escaneo reconocido más reciente (y al resumen solo si ninguno tiene etapa); al no existir fecha estimada, peso ni segundo operador, Peek mantiene el seguimiento en CTTYEX hasta el final.

Peek comprueba hasta cada 10 minutos con el paquete abierto o el día de entrega con notificaciones activas; el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios, cada hora («Cada escaneo», «Pasos importantes» o «Solo la entrega»). Peek no puede contactar con CTTYEX ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de CTTYEX

### ¿Qué es CTTYEX?

*Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`), un transitario de Shenzhen (China continental) de paquetería e-commerce internacional cuya web es `cttyvip888.com` y cuyos números empiezan por `CTTY` y terminan en `CN`.

### ¿Dónde puedo rastrear un código CTTY…CN?

En `物流查询` dentro de [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) (escrito en mayúsculas y sin espacios) o directamente en Peek.

### ¿Las horas de CTTYEX están en mi zona horaria?

No. Todas las horas del rastreador están en hora de Pekín (`UTC+8`), también en los escaneos hechos en Europa o América, por lo que un escaneo de última hora de la tarde puede mostrar ya la fecha del día siguiente. Peek las convierte a la zona horaria de tu dispositivo.

### ¿Por qué el rastreador de CTTYEX dice `查询不到`?

Significa *no se puede encontrar* en chino: ningún paquete coincide con lo escrito. Revisa erratas, escríbelo en mayúsculas y consulta al vendedor si persiste; los envíos de hace más de dos meses tampoco aparecen ya en el buscador.

### ¿A quién reclamo si no llega un paquete de CTTYEX?

Primero al vendedor donde compraste, que es quien contrató a CTTYEX; la web china de CTTYEX solo muestra una dirección en Shenzhen y un correo sin soporte al comprador.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (inicio)](https://www.cttyvip888.com/) – nombre de la empresa, servicios y datos de contacto en el pie
- [CTTYEX: 关于我们 (quiénes somos)](https://www.cttyvip888.com/assets/html/about_us.html) – fundación en 2011, más de 200 países, más de 6 sucursales en China continental, plataformas y vuelos chárter
- [CTTYEX: 物流查询 (consulta)](https://www.cttyvip888.com/assets/html/search.html) – un código por línea (letras y dígitos) y botón 提交
- [CTTYEX: 快件跟踪 (seguimiento)](https://www.cttyvip888.com/index/tracknumber/track) – buscador limitado a envíos de los últimos 2 meses, columnas de destino y estado
- [CTTYEX: 联系我们 (contacto)](https://www.cttyvip888.com/assets/html/contact.html) – dirección en Fuyong (Bao'an, Shenzhen, China continental) y correo cttywlvip888@163.com, sin teléfono ni formulario
- [Universal Parcel Scraper: CTTYEX](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cttyex) – formato CTTY…CN, búsqueda en mayúsculas, 23 estados y resúmenes, hora de Pekín y extracción de localidades
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – diferencia frente a China Post y visualización en Peek
:::
