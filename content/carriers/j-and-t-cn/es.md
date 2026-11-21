---
title: Seguimiento J&T Express China: números JT, estados y ayuda
description: Rastrea un paquete de J&T Express China (极兔速递) en China continental: cómo es el número JT, qué significan 已签收 y sus estados, 4 dígitos de teléfono y Peek.
slug: seguimiento-jt-express-china
published: 2026-11-21
updated: 2026-11-21
---

J&T Express China (`极兔速递`) es la red en China continental del grupo J&T Express, operativa desde marzo de 2020. Un número de J&T China suele corresponder a un envío dentro de China continental, por ejemplo un pedido online dirigido a un domicilio local o al almacén de un transitario (*forwarder*). Consúltalo en [el rastreador en chino de J&T](https://www.jtexpress.com.cn/service.html) o en el cuadro superior; el servicio internacional propio de J&T China (*J&T International Standard Express*) solo vuela desde China continental a siete países del Sudeste Asiático, por lo que en compras hacia Europa o América un código de J&T China puede cubrir únicamente el tramo interno en China continental hasta el transitario.

## Número de seguimiento de J&T Express China: cómo es

J&T lo denomina número de guía (`运单号`). Peek reconoce un formato para J&T Express China, con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `JT4829105736491` | `JT` y 13 dígitos: como J&T Express (la red del grupo en el Sudeste Asiático) comparte ese mismo patrón en el catálogo de Peek, Peek no adjudica J&T China solo por la forma (ver abajo). |
| `0148291057364` | Los 13 dígitos sin `JT`: el propio buscador de J&T antepone `JT`, pero en Peek 13 cifras sueltas encajan con otros operadores como DPD France o Yunda, no con J&T. Escribe también `JT`. |

Las minúsculas, los espacios y los guiones no importan en Peek.

**Dónde encontrarlo.** El vendedor lo incluye en los datos de envío del pedido y aparece impreso en la etiqueta del paquete ([cómo encontrar el número de seguimiento](guide:find-tracking-number)).

**En la web de J&T.** En la página `运单查询` (`jtexpress.com.cn/service.html`) puedes introducir hasta 10 números a la vez separados por espacios o Intro. Por privacidad, J&T suele pedir resolver un puzle o teclear los cuatro últimos dígitos del teléfono del remitente o del destinatario asociado al envío. Sus condiciones de guía electrónica ofrecen al remitente en China continental consulta de seguimiento durante un año desde la fecha de envío.

## Estados de seguimiento de J&T Express China explicados

El rastreador de J&T maneja estos estados, aproximadamente en este orden:

| J&T indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `已揽件` | Recogido: J&T retiró el paquete al remitente en China continental. | «Admitido por el transportista» |
| `运送中` | En tránsito por la red de J&T. | «En tránsito» |
| `发往代理点` | Enviado hacia un punto agente (`代理点`), mostrado en su web con el icono de reparto. | «En tránsito» |
| `派件中` | En reparto: un mensajero lo lleva en su ruta. | «En reparto» |
| `已代收` | Recibido en tu nombre por un tercero, como un punto de recogida (`代收点`): acude a retirarlo. | «Listo para recoger» |
| `已签收` | Firmado / entregado: recibido por ti o en tu nombre, con la hora (`签收时间`) y, si existe, foto (`查看签收图片`). | «Entregado» |
| `已拒收` | Rechazado por el destinatario. | «Incidencia» |
| `已退回` | Devuelto al remitente, con hora de devolución (`退回时间`). | «Devuelto al remitente» |
| `已取消` | Envío cancelado. | «Incidencia» |

Cuando surge un contratiempo, el buscador de J&T puede mostrar además un aviso de incidencia logística con la explicación de J&T. Peek muestra estos textos chinos en cuanto introduces los cuatro últimos dígitos del teléfono; hasta entonces, muestra lo que informan los servicios de seguimiento.

**¿Iba a un transitario?** Entonces `已签收` significa que su almacén en China continental firmó la recepción y la parte de J&T ha concluido: sigue a partir de ahí el código propio del transitario.

## Entrega en China continental: mensajeros, puntos de recogida y taquillas

El contrato electrónico de guía de J&T en China continental contempla cuatro formas de entrega:

- en mano al destinatario;
- a un conviviente o a la persona designada por ti;
- en un punto de recogida (`代收点`) o taquilla inteligente (`自提柜`) del entorno de la dirección;
- de cualquier otra forma solicitada o acordada con remitente o destinatario.

Si lo pides o aceptas, el repartidor también puede dejarlo en otra dirección indicada o en un punto de paquetería habilitado por la administración local, el comité vecinal o la administración del edificio; si el embalaje exterior está intacto, el servicio queda cumplido. En China continental, las Medidas de Administración del Mercado de Mensajería Exprés (`快递市场管理办法`, vigentes desde el 1 de marzo de 2024) exigen tu consentimiento previo antes de depositar un envío en taquilla o estación de paquetes, prohíben confirmar la recepción sin él y permiten revisar el paquete frente a la guía al recibirlo (abriendo o rechazándolo si el embalaje presenta daños visibles o el contenido es frágil).

## Paquete de J&T sin actualizar, perdido o devuelto

**Sin resultados aún.** Si el buscador de J&T responde `抱歉！未查到此运单` (guía no encontrada), revisa el código con el vendedor. Entretanto, Peek muestra «Todavía no hay historial de seguimiento» si aún no tiene transportista asignado, o «Esperando a J&T Express China» si ya figura bajo J&T Express China. Sin los cuatro últimos dígitos del teléfono, si ningún servicio de seguimiento lo localiza tampoco, desde la segunda comprobación Peek muestra «Actualización no disponible» con «Actualizar datos de seguimiento» para añadirlos.

**Sin avances.** Cuando un envío en camino lleva cuatro días sin pasos nuevos, Peek avisa con el día del último paso: «Sin novedades desde el lun 12 oct» (con el enlace «Pide ayuda a J&T Express China»). Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Perdido, dañado o devuelto en China continental.** Pregunta primero al vendedor que contrató a J&T. Según las condiciones de guía de J&T (aplicables únicamente a paquetes recogidos y entregados dentro de China continental), el remitente reclama en un plazo de 30 días aportando prueba de valor: sin seguro (`保价`), J&T abona la pérdida real hasta diez veces el porte; con seguro, hasta el valor declarado; por demora solo exime del coste de envío ([paquete perdido](guide:lost-parcel)). Ante dirección confusa, rechazo (`已拒收`) o imposibilidad ajena a J&T, el paquete vuelve a costa del remitente ([devuelto al remitente](guide:returned-to-sender)).

**Marcado como entregado pero no aparece.** Revisa puntos de recogida, taquillas, conserjería y, en su caso, el almacén del transitario; después llama al `956025` o escribe a la atención online con el número de guía. En China continental, si la respuesta de J&T no te convence o no llega a tiempo, la normativa permite elevar una reclamación (`申诉`) ante la autoridad postal ([entregado pero no recibido](guide:delivered-not-received)).

## Atención al cliente y contacto de J&T Express China

En `jtexpress.com.cn` (China continental):

- **Teléfono de atención**: `956025` (`服务热线`), atendido por agentes de `08:30` a `20:00` en China continental.
- **Atención en línea** (`在线客服` en `jtexpress.com.cn`): agentes de `08:30` a `21:00`; por separado, J&T indica un asistente automático (`智能客服`) operativo las 24 horas (`00:00–24:00`).
- **Correo electrónico**: `jtservice@jtexpress.com` (`服务邮箱`).
- **WeChat**: miniprograma `极兔速递+` para envíos y detalles ampliados de rastreo.

**Cuidado con los SMS falsos.** Los dominios oficiales de J&T Express China son `www.jtexpress.com.cn` y `jtexpress.com.cn`. Antes de pulsar enlaces recibidos por mensaje, revisa [mensajes falsos de entrega](guide:delivery-scam-texts). Sobre tu compra, acude primero al vendedor.

## Seguir un paquete de J&T Express China con Peek

Aunque el patrón `JT` de 13 cifras coincida con J&T Express en el Sudeste Asiático, pégalo igual en [Peek](/) (compatible con más de 4.000 transportistas):

- **Pegado solo.** Peek muestra «Comprobando servicios de seguimiento…»: si los servicios coinciden en J&T Express China con pasos fechados, lo archiva ahí automáticamente; si hay historial sin operador confirmado, verás «Historial encontrado. Peek confirmará el transportista después de guardar.»; si ninguno lo tiene aún, «aún no encontrado · Peek seguirá comprobando».
- **Elegido por ti.** Pulsa «Cambiar» y selecciona J&T Express China en «Encaja con este número» o buscando `J&T` o `极兔` en «Todos los transportistas» (o usa «Cambiar transportista» en un paquete ya guardado, incluso si quedó asignado a J&T Express).
- **Mediante enlace.** Un enlace `jtexpress.com.cn/service.html?indexIpt=…` asigna el envío al instante a J&T Express China («Transportista detectado»).

**Con o sin los 4 últimos dígitos del teléfono.** Introdúcelos en «Últimos 4 dígitos del número de teléfono» al añadirlo o tocando «Añadir los dígitos» en la ficha: Peek los guarda solo en ese paquete, los envía exclusivamente a J&T y lee entonces el rastreador oficial (estados en chino, hora de Pekín `UTC+8` convertida a tu zona con «Horas en tu zona horaria: …», sin localidad, descartando nombres de repartidor o firmante, teléfonos y fotos). Sin esos dígitos (o si no coinciden), Peek consulta por turno, en un orden que varía, a ParcelsApp, Ship24 y 17TRACK; «Abrir la web de J&T Express China» pasa entonces a abrir el servicio que respondió, y si ese servicio nombra a un operador final soportado con su código, Peek pasa a seguirlo allí en cuanto muestre un escaneo igual o más reciente.

**Frecuencia y alertas.** Cuando responde el rastreador de J&T, Peek comprueba hasta cada 10 minutos con el paquete abierto o el día de entrega con alertas activas, y hasta cada 2 minutos desde las 6:00 (hora de Pekín) el día en «En reparto» (en los demás casos y tras dos días sin cambios o 12 h en «Etiqueta creada» / «Admitido por el transportista», cada hora). Cuando responde un servicio de seguimiento, revisa cada hora, cada 3 horas tras un día sin pasos nuevos y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activas. Si puede quedar en punto o taquilla (`已代收`), elige «Pasos importantes» o «Cada escaneo» en lugar de «Solo la entrega». Peek no puede contactar con J&T, el transitario ni el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de J&T Express China

### ¿Es fiable J&T Express China?

Sí. J&T Express nació en Indonesia en agosto de 2015, empezó a operar en China continental en marzo de 2020, adquirió el negocio exprés de Best Inc en China continental en octubre de 2021, cotiza en el mercado principal de la Bolsa de Hong Kong desde octubre de 2023 y opera redes exprés en 13 países.

### ¿Cómo es un número de seguimiento de J&T Express China?

Las letras `JT` seguidas de 13 dígitos, como `JT4829105736491`. Mantén el prefijo `JT` en Peek, porque las 13 cifras solas coinciden con otros transportistas.

### ¿Qué significa `已签收` en el seguimiento de J&T?

Firmado / entregado: recibido por ti, un conviviente, una persona autorizada, un punto de recogida o taquilla, o el almacén de un transitario en China continental. `已代收` es distinto: un punto de recogida lo recepcionó por ti y todavía tienes que pasar a retirarlo.

### ¿J&T Express China entrega en España, Latinoamérica o Estados Unidos?

No con su propio servicio internacional: *J&T International Standard Express* vuela desde China continental únicamente a Malasia, Vietnam, Singapur, Tailandia, Filipinas, Indonesia y Camboya. En pedidos hacia Europa o América, un número de J&T China puede cubrir solo el recorrido interno en China continental hasta un transitario.

### ¿Cuál es el teléfono de J&T Express China?

El `956025`, su línea de atención al cliente en China continental, atendida por personas de `08:30` a `20:00` (y hasta las `21:00` en la atención online `在线客服`).

:::sources
- [J&T Express China: 极兔速递 (inicio)](https://www.jtexpress.com.cn/) – fundación en 2015, red en 13 países, línea 956025 y correo
- [J&T Express China: 运单查询 (consulta de guías)](https://www.jtexpress.com.cn/service.html) – hasta 10 códigos, verificación con 4 dígitos, estados en chino, hora y foto de firma, aviso de incidencia
- [J&T Express China: 关于极兔 (sobre J&T)](https://www.jtexpress.com.cn/aboutUs.html?type=JT) – inicio en China continental en marzo de 2020, compra de Best Inc en octubre de 2021, salida a bolsa en Hong Kong en octubre de 2023
- [J&T Express China: 联系我们 (contacto)](https://www.jtexpress.com.cn/aboutUs.html?type=LX) – teléfono 956025, buzón jtservice@jtexpress.com y atención en línea
- [J&T Express China: 服务时间 (horarios)](https://www.jtexpress.com.cn/serviceTime.html) – atención humana 08:30–20:00 / 08:30–21:00 y asistente automático 00:00–24:00
- [J&T Express China: 电子运单契约条款 (condiciones de guía electrónica)](https://www.jtexpress.com.cn/electronicContract.html) – cuatro modos de entrega, consulta de un año, devoluciones, límites de indemnización y plazo de 30 días en China continental
- [J&T Express China: J&T International Standard Express](https://www.jtexpress.com.cn/internationalv3.html) – vuelos directos desde China continental a siete países del Sudeste Asiático
- [Ministerio de Transporte de China: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – consentimiento para taquillas y estaciones, revisión al recibir y reclamación postal en China continental
- [Universal Parcel Scraper: J&T Express China](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-cn) – patrón JT, alias, enlace indexIpt=, 4 últimos dígitos telefónicos, hora de Pekín y estados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato JT compartido con J&T Express, operadores de 13 cifras, orden de servicios de seguimiento y traspasos
:::
