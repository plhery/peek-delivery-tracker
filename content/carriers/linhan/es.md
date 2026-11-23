---
title: Seguimiento LinHan International: números LHGJ y estados
description: Sigue un paquete de LinHan International desde Shenzhen: formato LHGJ, significado de cada escaneo y por qué usa hora de Pekín.
slug: seguimiento-linhan
published: 2026-11-22
updated: 2026-11-22
---

LinHan International (深圳市林瀚国际物流有限公司, Shenzhen LinHan International Logistics Co., Ltd.) es un transitario con sede en Shenzhen, China, que envía por aire paquetes de tiendas online fuera de China y los entrega a un agente en el país del comprador para aduanas y reparto. Sus números empiezan por `LHGJ`: introduce el tuyo en el rastreador de LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), o en el recuadro superior.

## Número de seguimiento de LinHan

LinHan no publica un formato oficial. Sus guías se forman con `LHGJ`, ocho dígitos y `CD` (con dígitos inventados):

| Número | Qué es |
| --- | --- |
| `LHGJ48291037CD` | `LHGJ`, ocho dígitos y `CD`: un código de LinHan International. |
| `LHGJ4829103CD` | Solo siete dígitos: no corresponde al formato de LinHan. Revisa si falta una cifra. |

Las minúsculas y los espacios no importan al pegar el número en Peek.

**Dónde encontrarlo.** LinHan trabaja para vendedores: su portada ofrece tarifas en tiempo real y reservas para remitentes, y solo el rastreador para destinatarios. El número te lo da la tienda donde compraste; consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number).

**En el rastreador de LinHan.** Admite hasta 20 códigos por consulta, separados por comas o saltos de línea, y solo muestra paquetes de los últimos dos meses.

## Estados de seguimiento de LinHan explicados

LinHan no publica una lista de estados. Su rastreador está en chino, pero los escaneos salen en inglés, del más reciente al más antiguo. Encima, una línea resumen indica el país de destino y un estado en chino:

| LinHan dice | Qué significa para ti |
| --- | --- |
| `运输途中` | «En tránsito»: el paquete está en camino. |
| `成功签收` | «Firmado»: entregado. |
| `查询不到` | «No encontrado»: el rastreador no tiene ningún envío con ese número y no aparecen escaneos. |

Estos son los escaneos de LinHan en China, en el orden habitual:

| LinHan dice | Qué significa para ti |
| --- | --- |
| `Order information received` | El vendedor envió a LinHan los datos del pedido. LinHan aún no tiene el paquete. |
| `Arrive at the cross border receiving point` | LinHan recibió el paquete en su punto de recepción transfronterizo en China. `Arrive at the receiving point` significa lo mismo. |
| `Goods leave the operation center` | Salió del centro de operaciones de LinHan. |
| `Arrival at domestic internationality airport` | En el aeropuerto de salida en China. |
| `Departure from the airport of origin domestic` | El vuelo salió de China. |

Luego siguen los pasos en tu país, donde toman el relevo el agente de LinHan y la red de reparto:

| LinHan dice | Qué significa para ti |
| --- | --- |
| `Item arrives at destination airport` | Aterrizó en tu país. `Arrive at the destination international airport` significa lo mismo. |
| `local carrier Agent Import customs clearance` | El agente de LinHan está despachando la aduana de importación; aún no está liberado. `Import customs clearance`, tras la ciudad portuaria, es el mismo paso. |
| `transfer to local acting company International self-operation team` | Entregado al agente en tu país. |
| `Internationaliy Package received at processing center` | Ortografía original de LinHan. Primer escaneo de la red de reparto en un centro de procesamiento. No es un nuevo inicio: LinHan lo recibió en China. |
| `Leave the processing center and transport to … processing center` | En camino entre dos centros de procesamiento, con la siguiente ciudad en lugar de «…». `Leave the … processing center` indica el centro del que salió. |
| `Arrive at the sorting and distribution center` | En un centro de clasificación, cuya ciudad figura tras «Arrive». `Leaving the sorting center` indica su salida. |
| `Arrive at the last mile sorting and distribution center` | En el centro de clasificación de la red que entrega, tras ciudad, estado y código postal. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Escaneo de la red de reparto, con su nombre sustituido por «logistics carrier». |
| `Accepted at logistics carrier Regional Destination Facility` | Escaneo de esa red cerca de ti, tras ciudad, estado y código postal. «Accepted» no es un nuevo inicio: sigue en tránsito. |
| `package is on the way` | Sigue en ruta desde el centro local, tras la ciudad. No indica que salga a reparto hoy. |
| `Delivered` | Entregado. |

Muchas filas en tu país empiezan por la ciudad (en EE. UU., ciudad, estado y código ZIP), y la línea de entrega incluye también tu código postal: ciudad, código postal, Delivered. Una misma línea puede aparecer duplicada.

**Horas en hora de Pekín.** El rastreador de LinHan registra cada hora en horario de Pekín (UTC+8), incluso en escaneos hechos en tu país. Como Pekín cambia de día a las 16:00 UTC, un escaneo entre esa hora y tu medianoche lleva la fecha del día siguiente: en la España peninsular o Alemania desde las 17:00 en invierno y 18:00 en verano; en Nueva York desde las 11:00 en invierno y el mediodía en verano.

## Quién entrega los paquetes de LinHan

La portada de LinHan describe su cadena como recogida, almacenaje y clasificación, despacho aduanero en China, carga aérea, declaración aduanera en el extranjero, almacenes en el extranjero y reparto (揽货, 仓储分拣, 国内通关, 空运, 海外报关, 海外仓, 海外派送). No lista destinos; los paquetes vistos en su rastreador iban a Estados Unidos y Alemania.

En tu país se hace cargo un agente. El rastreador de LinHan lo llama «local carrier Agent» y «local acting company», y a la red final «logistics carrier», sin revelar la empresa ni dar un segundo número de seguimiento. Aun así el historial continúa hasta la entrega, por lo que el rastreador de LinHan sigue siendo donde consultarlo.

LinHan no publica plazos de entrega ni normas sobre intentos, puntos de recogida o custodia. Si un aviso o mensaje del transportista local indica su nombre, aplican sus normas: consulta [entrega fallida](guide:missed-delivery).

## Paquete de LinHan que no llega o seguimiento parado

**Solo `Order information received`.** El vendedor reservó el envío, pero el paquete aún no ha llegado a LinHan. Si pasan varios días sin novedades, pregunta al vendedor si ya lo envió.

**Nada después del vuelo.** Tras `Departure from the airport of origin domestic`, la siguiente línea llega al aterrizar o entrar en aduanas en tu país. Más en [seguimiento desde China](guide:tracking-from-china) y [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**En aduanas.** El rastreador de LinHan no indica si debes tasas ni nombra al transportista final, por lo que un SMS pidiendo un pago es difícil de verificar: revisa [estafas por SMS de entrega](guide:delivery-scam-texts) antes de pagar y [paquetes retenidos en aduanas](guide:customs).

**`查询不到`.** El rastreador no reconoce el código: confírmalo con el vendedor. Tampoco encuentra envíos de hace más de dos meses.

**Entregado, pero no ha llegado.** Si la última línea dice `Delivered`, consulta [entregado pero no recibido](guide:delivered-not-received) y avisa al vendedor. LinHan no muestra foto de entrega.

**Perdido, dañado o devuelto.** LinHan no publica proceso de reclamación, compensación ni devoluciones. El vendedor contrató el transporte con LinHan, así que pídele a él o a la plataforma un reembolso o reenvío. Ver [paquete perdido](guide:lost-parcel).

## Contacto de LinHan International

La web de LinHan, solo en chino, termina con un bloque 联系我们 (Contacto) que muestra un correo electrónico y una dirección de servicio en Pinghu, distrito de Longgang, Shenzhen. No incluye teléfono, chat, formulario ni canal para destinatarios: para cualquier incidencia con tu pedido, contacta con el vendedor.

## Seguir un paquete de LinHan con Peek

[Peek](/) reconoce un código de LinHan por su forma (`LHGJ`, ocho dígitos y `CD`), que ningún otro transportista de su catálogo usa, y muestra al instante «Transportista detectado». También puedes pegar un enlace al rastreador de LinHan con `nos=` y el número.

Peek lee el rastreador de LinHan y muestra el estado, cada escaneo con su localidad cuando figura y el país de destino. Separa la ciudad del texto hacia la ubicación y elimina códigos postales, de modo que una línea `Delivered` se lee «Entregado» en su localidad. Las filas duplicadas aparecen una sola vez y los demás textos conservan el inglés original de LinHan, incluido `Internationaliy`. Si la línea de entrega de LinHan nombra a quien firmó (`Delivered, signed by …`), el nombre se queda en esa línea en inglés y no aparece en ningún otro sitio; un enlace compartido muestra la línea sin él. Peek interpreta cada hora en horario de Pekín (UTC+8), como la escribe el rastreador, y la muestra en tu zona horaria («Horas en tu zona horaria:»).

Algunas líneas clasifican distinto de lo que parecen: `Order information received` pasa a «Etiqueta creada»; `Internationaliy Package received at processing center` y `Accepted at logistics carrier Regional Destination Facility` siguen en «En tránsito», no como nueva admisión; las dos líneas aduaneras pasan a «En aduanas»; `package is on the way` sigue en «En tránsito» porque no indica reparto hoy.

LinHan no facilita fecha estimada de entrega, peso, nombre del servicio, empresa final ni segundo código, así que Peek no muestra nada de eso y sigue el número `LHGJ` hasta el final sin cambiar de transportista.

Peek consulta hasta cada 10 minutos mientras el paquete esté abierto en Peek, o el día de entrega con las notificaciones activadas, siempre que responda el rastreador de LinHan. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora, y tras siete días sin novedades en «Etiqueta creada», cada 6 horas. En notificaciones puedes elegir «Cada escaneo», «Pasos importantes» o «Solo la entrega». Si pasan cuatro días sin escaneo en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el …» y recuerda «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a LinHan International.» Peek no puede contactar con LinHan ni con el vendedor por ti.

## Preguntas sobre el seguimiento de LinHan

### ¿Qué es LinHan International?

Shenzhen LinHan International Logistics (深圳市林瀚国际物流有限公司), un transitario en linhan56.com que envía por aire paquetes de vendedores fuera de China hacia un agente que los despacha en aduanas y los entrega.

### ¿Dónde puedo seguir un número LHGJ?

En el rastreador de LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), o en Peek. La portada de LinHan también enlaza con 17TRACK.

### ¿Quién entrega los paquetes de LinHan?

Un agente en tu país, llamado «local carrier» o «local acting company» en el rastreador sin revelar su nombre. Sus escaneos aparecen en el seguimiento de LinHan hasta la entrega.

### ¿Por qué las horas de LinHan parecen ir un día adelantadas?

Porque el rastreador escribe cada hora en horario de Pekín (UTC+8), también para escaneos en tu país. Un escaneo tras las 16:00 UTC, cuando ya es el día siguiente en Pekín, lleva esa fecha. Peek convierte las horas a tu zona horaria.

### ¿A quién contacto por un paquete de LinHan perdido?

Al vendedor donde hiciste el pedido, que fue quien contrató a LinHan. La web de LinHan solo muestra un correo electrónico y una dirección en Shenzhen, nada para destinatarios.

:::sources
- [LinHan International: 深圳市林瀚国际物流有限公司 (inicio)](https://www.linhan56.com/) – nombre de la empresa, carga aérea transfronteriza para remitentes, cadena de recogida a entrega exterior, enlaces al seguimiento y 17TRACK, bloque de contacto
- [LinHan International: 快件跟踪 (seguimiento)](https://www.linhan56.com/index/tracknumber/track) – rastreador en chino, paquetes de los últimos dos meses, hasta 20 códigos por búsqueda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato LHGJ, estados resumen y textos de escaneos, agente en destino y red anónima, relojes en hora de Pekín, ciudades y códigos postales, destinos observados, lo que Peek muestra y descarta
:::
