---
title: Seguimiento de Techlink: números ZHPHD…ML y estados
description: Rastrea un paquete de Techlink (智禾) desde China: formato del número ZHPHD…ML, qué significan sus escaneos, quién reparte en destino y qué hacer si se detiene.
slug: seguimiento-techlink
published: 2026-11-21
updated: 2026-11-21
---

Techlink (智禾国际物流, Zhihe International Logistics) es una empresa logística de Shenzhen, en China continental, que envía paquetes de vendedores en línea hacia Europa y América. Sus códigos empiezan por `ZHPHD` y terminan en `ML`: introdúcelo en la página de seguimiento de Techlink (`www.techlinksz.cn/trajectory`) o pégalo en el cuadro superior. La tienda que despachó tu pedido te facilita este número junto con los datos de envío.

## Número de seguimiento de Techlink: cómo es

Techlink no publica una guía sobre el formato de sus códigos. Sus cartas de porte tienen 19 caracteres: `ZHPHD`, seis cifras que forman una fecha válida (año, mes y día), otras seis cifras y `ML`. Con dígitos inventados:

| Número | Qué identifica |
| --- | --- |
| `ZHPHD260315482910ML` | `ZHPHD`, una fecha de seis cifras, seis cifras más y `ML`: un envío de Techlink. |
| `ZHPHD261315482910ML` | El mes `13` no existe: no es un código válido de Techlink. Revisa si hay alguna errata. |
| `RR482910576CN` | Dos letras, nueve cifras y `CN`: un envío postal de [China Post](carrier:china-post), no de Techlink. |

En Peek no importan ni los espacios ni las minúsculas. Si tienes un código con otra estructura, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

**Dónde encontrarlo.** Los clientes de Techlink son los comercios. Su página de inicio indica que sus líneas de paquetería ligera hacia Europa y América atienden a vendedores en Amazon, Walmart y `TK`, además de tiendas web independientes; no menciona AliExpress, Temu ni Shein. El vendedor contrata el envío y te pasa el código en el correo de confirmación o en la ficha del pedido. Si no das con él, mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Qué significan los estados de seguimiento de Techlink

El buscador de Techlink admite hasta 40 números a la vez, uno por línea o separados por comas, y se mantiene en chino aunque cambies su menú a inglés. Para cada código muestra el último evento, cuántos escaneos acumula (`条轨迹`) y los países de origen y destino en chino. Pulsa sobre la fila o en `展开全部` (desplegar todo) para ver cada paso con su hora y país.

Delante de cada código un icono de color resume la situación: `▤` en el almacén de Techlink, `➤` en tránsito o en reparto, una marca verde `✓` entregado, un signo rojo `!` una incidencia (como un intento fallido) y `?` sin resultados.

Techlink no publica un glosario de estados, pero registra cada lectura en inglés y chino con su hora y, casi siempre, el país:

| Lo que escribe Techlink | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `Parcel electronic information received` (`收到电子信息`) | El vendedor envió a Techlink los datos electrónicos del pedido; Techlink aún no tiene el paquete. | «Etiqueta creada» |
| `Parcel has been received and processed at original facility` (`仓库入库`) | El paquete entró y se registró en el almacén de Techlink en China continental. | «Admitido por el transportista» |
| `Parcel has been packed and is ready for outbound shipping` (`包裹装箱`) | Embalado en el almacén para salir en la línea internacional. | «En tránsito» |
| `Departed from the original facility` | Salió del almacén de origen de Techlink en China continental. | «En tránsito» |
| `Export customs declaration completed` | Declaración aduanera de exportación en China continental completada tras salir del almacén; el paquete no está retenido. | «En tránsito» |
| `Parcel is in transit to origin airport` | En transporte por carretera dentro de China continental rumbo al aeropuerto de salida. | «En tránsito» |
| `Depart from the original airport` | El vuelo despegó del aeropuerto de origen. | «En tránsito» |
| `Arrive at the destination airport` | El avión aterrizó en el país de destino, antes del despacho de importación. | «En tránsito» |
| `Shipment Received at Facility` | Recibido en un hub del país de destino tras el vuelo. | «En tránsito» |
| `Your shipment has arrived at sorting center` | Llegó a un centro de clasificación en el país de destino. | «En tránsito» |
| `Your shipment has been processed at sorting center and ready for shipping` | Clasificado en ese centro y listo para el tramo siguiente. | «En tránsito» |
| `Your shipment has departed from the transit origin` | Salió del centro de clasificación hacia la siguiente instalación. | «En tránsito» |
| `In transit, it is currently in transit to the next facility` | En camino entre dos centros dentro del país de destino. | «En tránsito» |
| `The parcel has been successfully picked up and is being prepared for transportation.` | En envíos hacia Australia: recogida por J&T Express Australia tras el vuelo de Techlink, seguida de los escaneos de tránsito y distribución de J&T. | «En tránsito» |
| `Parcel is out for delivery.` | Escaneo retransmitido del repartidor local: el paquete está en reparto. | «En reparto» |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Intento fallido comunicado por el repartidor local (aquí dirección incorrecta; entre corchetes puede figurar otro motivo) con regreso a su almacén local. | «Intento de entrega» |

Cuando no reconoce un número, la página de Techlink muestra `查询不到轨迹` (no se encontró seguimiento), `未知 → 未知` (países desconocidos) y `暂无最新轨迹` (sin escaneos recientes).

## Quién entrega los paquetes de Techlink en tu país

Techlink no lo especifica. Su web describe líneas puerta a puerta: transporte aéreo desde China continental, despacho aduanero en destino mediante una empresa propia constituida en el extranjero en 2023 y reparto final a través de sus cuentas con transportistas locales, sin nombrar a ninguno en la web ni en el seguimiento. No obstante, en historiales leídos por Peek en Italia los paquetes pasan a [iMile](carrier:imile), cuyas líneas abren con el centro entre corchetes como `【Milan center】Parcel shipped to【VEN 07】.` En Australia se hace cargo [J&T Express Australia](carrier:j-and-t-au) tras el vuelo desde `The parcel has been successfully picked up and is being prepared for transportation.` En Estados Unidos, Techlink retransmite en algunos envíos los escaneos de [YWE](carrier:ywe), como `Parcel departed from Facility.` y `Parcel received by local Facility.`, hasta `Parcel is out for delivery.` o un intento fallido.

Los registros internos del rastreador de Techlink pueden guardar el código propio del transportista final sin indicar su nombre, y la página web de Techlink carece de campo para mostrarlo. Una vez identificado el repartidor local, sus condiciones rigen los intentos de entrega, avisos de paso, puntos de recogida y plazos de custodia; Techlink no publica ninguna de esas normas.

El servicio exprés puerta a puerta de Techlink se comercializa en modalidad DDP (aranceles pagados) o DDU (aranceles sin pagar). La opción contratada por el vendedor determina si pueden reclamarte cargos de importación al recibirlo: consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de Techlink parado o que no llega

**Solo aparece `Parcel electronic information received`.** El comercio generó el envío, pero el bulto todavía no ha entrado en el almacén de Techlink en China continental. Si pasan varios días sin cambios, pregunta al vendedor si ya entregó la mercancía.

**Detenido tras `Arrive at the destination airport`.** El vuelo ya aterrizó en tu país; quedan la aduana de importación y el traspaso al transportista local, que pueden sumar días sin lecturas nuevas. En muchos envíos el historial de Techlink termina en ese aterrizaje o pocos pasos después dentro del país de destino (`Shipment Received at Facility` hasta `In transit, it is currently in transit to the next facility`), aunque en otros retransmite los escaneos del repartidor local hasta `Parcel is out for delivery.` Si deja de avanzar, pide al vendedor el nombre de la empresa que reparte en tu país y su código de seguimiento. Más detalles en [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [rastrear un paquete desde China](guide:tracking-from-china).

**Sin resultados.** Confirma el código con la tienda: un número `ZHPHD…ML` mide exactamente 19 caracteres. Un pedido recién grabado también puede tardar en mostrar datos.

**Perdido, dañado o marcado como entregado sin haber llegado.** Techlink no ofrece una vía de reclamación para destinatarios. Quien tiene contrato con Techlink es el vendedor, de modo que debes reclamar el reembolso o un reenvío a la tienda o al marketplace. Lee [paquetes perdidos](guide:lost-parcel), [entregado pero no recibido](guide:delivered-not-received) y, si compraste en Amazon, [seguimiento de pedidos de Amazon](guide:amazon-orders).

## Teléfono y atención al cliente de Techlink

El sitio web de Techlink (`www.techlinksz.cn`) no incluye teléfono, correo electrónico ni dirección postal. Su enlace `联系我们` (contacto) no lleva a ninguna página y la web no dispone de chat ni formulario.

Para cualquier gestión sobre el pedido debes dirigirte al vendedor; para incidencias de reparto en destino, al transportista local una vez sepas cuál es.

## Seguir un paquete de Techlink con Peek

[Peek](/) identifica a Techlink al instante a partir de cualquier número `ZHPHD…ML` —ningún otro transportista de su catálogo emplea ese formato— y muestra «Transportista detectado» con la opción «Cambiar». No existe un enlace de Techlink que pegar, porque `www.techlinksz.cn/trajectory` no incorpora el código en la dirección web.

Peek consulta directamente el rastreador de Techlink y muestra el estado actual, cada escaneo con su país y el país de destino. Los mensajes en inglés se conservan tal como los redacta Techlink (en un enlace compartido, una entrega final muestra únicamente `Delivered`). Sus propios escaneos en China continental, la recepción electrónica inicial sin lugar y cualquier lectura en el extranjero que empiece por un centro entre corchetes (`【…】`) se interpretan en la hora de Pekín; los escaneos retransmitidos de J&T Express Australia hacia Australia se leen en la hora de Sídney sin importar el estado australiano del almacén; el resto mantiene el reloj local de su país y Peek convierte cada hora con huso asignado a la zona horaria de tu dispositivo —en `peektracker.com` y en las apps de iPhone y Android figura al pie «Horas en tu zona horaria: …»—. Techlink no facilita fecha prevista de entrega, peso ni nombre del servicio.

Mientras Techlink aún no reconozca el código, Peek muestra «Esperando a Techlink»; si la respuesta indica que el pedido no consta y pasados dos días ninguna otra fuente responde, Peek avisa «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

Cuando el registro de Techlink incluye el código del transportista de entrega, Peek examina su formato: un código postal internacional (dos letras, nueve cifras y dos letras) apunta al correo nacional del país de destino, y un formato exclusivo de un único transportista del catálogo de Peek apunta a esa empresa. Si Peek lee el seguimiento directo de ese transportista, lo consulta y cambia a él en cuanto muestra avances tan recientes como los de Techlink, manteniendo el código `ZHPHD…ML` junto al segundo número. De lo contrario, Peek permanece en el rastreador de Techlink, cuya historia suele cortarse al aterrizar el vuelo o tras pasar por un centro de clasificación en destino.

Mientras tengas el paquete abierto en pantalla o, el día de entrega, con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 de la mañana (hora local) el día en que sale a reparto mientras responda el rastreador de Techlink (mediante servicios de seguimiento externos solos: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto con el paquete abierto o alertas activas; tras un traspaso, Peek sigue el ritmo del transportista que entrega)—; fuera de esos casos, así como tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Techlink ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de Techlink

### ¿Qué es Techlink (智禾)?

Techlink es `智禾国际物流(深圳)有限公司` (Zhihe International Logistics, Shenzhen), un operador logístico de China continental que gestiona envíos de comercio electrónico transfronterizo con líneas aéreas de paquetería ligera y exprés puerta a puerta hacia Europa y América. Su web es `www.techlinksz.cn`.

### ¿Qué es un número de seguimiento ZHPHD?

Una carta de porte de Techlink de 19 caracteres formada por `ZHPHD`, seis cifras de fecha, otras seis cifras y `ML`. Puedes consultarla en `www.techlinksz.cn/trajectory` o directamente en Peek.

### ¿Quién reparte los paquetes de Techlink en mi país?

Un transportista local que Techlink no nombra ni en su web ni en su buscador (en envíos leídos por Peek han aparecido iMile en Italia, J&T Express Australia en Australia e YWE en Estados Unidos). Si los escaneos no lo delatan y Peek no pudo enlazar un segundo número, pide al vendedor el nombre del repartidor y su código local.

### ¿Por qué se ha detenido el seguimiento de Techlink tras llegar al aeropuerto?

En buena parte de los envíos el rastreador de Techlink deja de añadir pasos en `Arrive at the destination airport` o poco después en un hub o centro de clasificación del país de destino, y el reparto final solo consta en el transportista local. Solicita ese segundo código al comercio y reclámale a él si vence el plazo de entrega estimado.

:::sources
- [Techlink: 智禾官网 (inicio)](http://www.techlinksz.cn/) – denominación social en Shenzhen, líneas de paquetería ligera hacia Europa y América, vendedores en Amazon, Walmart, TK y tiendas propias, puerta a puerta DDU y DDP, filial exterior de aduanas creada en 2023, cuentas de reparto en destino y enlace de contacto sin destino
- [Techlink: 轨迹跟踪 (seguimiento)](http://www.techlinksz.cn/trajectory) – consulta de hasta 40 códigos en chino, países de origen y destino, iconos de estado y respuesta ante números desconocidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato ZHPHD…ML, mensajes en inglés y chino, escaneos retransmitidos de iMile en Italia, J&T Express Australia en Australia e YWE en EE. UU., husos de Pekín y Sídney, traspaso por segundo número y visualización en Peek
:::
