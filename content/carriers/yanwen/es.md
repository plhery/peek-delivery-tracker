---
title: Yanwen seguimiento: estados, quién entrega y contacto
description: Sigue tu paquete de Yanwen desde China: cómo es el número, qué significa cada estado en inglés, quién lo reparte en España y a quién preguntar si se para.
slug: yanwen-seguimiento
published: 2026-10-11
updated: 2026-11-08
---

Yanwen es una empresa de logística china, fundada en Pekín en 1998, que envía al extranjero los paquetes de vendedores online y se los entrega a un transportista local para el último tramo. Para seguir tu paquete, escribe el número en su web de seguimiento, [track.yw56.com.cn](https://track.yw56.com.cn/en/index), o pégalo en la casilla de arriba. El número te lo da la tienda o el marketplace donde compraste, en los datos de envío de tu pedido.

## Número de seguimiento de Yanwen: cómo es

Yanwen no publica ningún número de ejemplo ni páginas de ayuda para destinatarios. Estas son las formas que llevan sus paquetes, con cifras inventadas:

| Número | De quién es |
| --- | --- |
| `BYS123456789` | BYS y 9 cifras: Yanwen. |
| `UL000000005YP` | Dos letras, 9 cifras y YP al final: Yanwen. Suele empezar por UL o UK. |
| `YWABC123456789012` | YW, tres letras y 12 cifras: el número de última milla de un paquete que se entrega en Estados Unidos. |
| `RR000000005CN` | La misma forma postal, pero acabada en CN: es de [China Post](carrier:china-post), no de Yanwen. |
| `YT1234567890123456` | No es Yanwen, sino [YunExpress](carrier:yunexpress). |
| `LP12345678901234` | No es Yanwen, sino la referencia logística de AliExpress: mira [Cainiao](carrier:cainiao). |

Un número postal de Yanwen siempre termina en YP; con otra terminación, es de otro correo. En la página de resultados, Yanwen muestra dos números: «Tracking Number», el suyo, y «Last Mile Tracking», el de la empresa que reparte en tu país. Si la tienda te dio ese número local, la web de Yanwen también lo encuentra. Si no tienes ningún número, pídeselo a la tienda.

La web acepta hasta 100 números a la vez, pero el seguimiento por tu cuenta solo cubre los envíos del último año. Los productos de China Post no se siguen en la web de Yanwen.

## Estados de Yanwen: qué significa cada uno

La web de seguimiento de Yanwen solo está en inglés y en chino, y los escaneos propios de Yanwen salen en inglés en las dos versiones: no hay versión en español. Los que más se ven, en el orden en que los encuentra un paquete:

| Yanwen escribe | Qué significa para ti |
| --- | --- |
| `Order processed by shipper` | El vendedor ha registrado el envío. Yanwen aún no tiene el paquete. |
| `Yanwen Pickup Scan` | Yanwen ya tiene el paquete en China. |
| `Yanwen facility - Outbound` | Ha salido del centro de clasificación de Yanwen. |
| `Tendered for Delivery` | Entregado para el viaje al extranjero, minutos después de salir del centro de clasificación de Yanwen y antes de la aduana de exportación. Pese al nombre, todavía no es el reparto. |
| `International shipment release - Export` | Terminada la aduana de exportación en China. |
| `Port of departure - Departure` | Entregado a la aerolínea y ha salido el vuelo. |
| `Port of destination - Arrival` | Ha llegado a tu país o cerca de él. |
| `Cleared Customs` | La aduana de importación lo ha despachado. |
| `International shipment release - Import` | La aduana de importación lo ha liberado. |
| `Batch delivery to carrier` | Entregado al transportista local. Siguen sus escaneos. |
| `Delivery to last mile operator` | Lo mismo: ahora lo tiene la empresa del último tramo. |

`Order Submited.` (la errata es de Yanwen), `Waybill Generated` y `Shipping Order Created.` significan lo mismo que la primera fila: solo existe la etiqueta y puede que el paquete no haya salido del vendedor. `Pick up by local carrier at destination port` equivale a `Batch delivery to carrier`. Ojo con la palabra «delivery» en estas líneas: es la entrega al transportista local, no a ti. A partir de ahí, las líneas son las de la empresa local, a veces con un prefijo de país o el nombre de un almacén entre corchetes. La línea de DHL `The recipient has selected a Packstation as the new delivery address.` puede aparecer cuando el paquete sigue en China: solo registra que elegiste una Packstation de DHL como nueva dirección de entrega, así que Peek mantiene el estado que ya tenía el paquete y no le asigna ningún lugar.

Algunos paquetes pasan a China Post después de `Yanwen facility - Outbound`. Yanwen muestra entonces los escaneos de China Post en chino, explicados en la página de [China Post](carrier:china-post); `中国邮政已收取邮件` es ese traspaso. `【…】退回，备注:安检退回` significa que el control de seguridad de un centro de clasificación lo rechazó, y el centro lo resuelve antes de volver a enviarlo: no vuelve al vendedor. Peek lo muestra como «Incidencia».

Encima de los escaneos, Yanwen clasifica cada paquete en una categoría. `Info_Received` es la fase de etiqueta. `In_Transportation` lo abarca todo, del vuelo a la aduana y al tramo local. `Out_For_Delivery` es que va en reparto y `Delivered`, entregado; en ese caso la fila muestra los días que tardó, por ejemplo «Delivered（7d）». `End_of_Tracking` quiere decir que Yanwen dejó de seguir el paquete: no dice dónde está y no es ni una entrega ni una pérdida. `Not_Found`, con la línea `No information was found`, significa que Yanwen no tiene nada con ese número.

## Quién entrega Yanwen en España

Yanwen no reparte en Europa por sí misma: dice que trabaja «with local carriers to fulfill the last mile package delivery», es decir, con transportistas locales para el último tramo. Sus páginas no nombran ninguna empresa de reparto en España; su lista de productos incluye una línea para las islas de España y Portugal, sin decir quién la reparte. Quién lleva tu paquete lo dice su página de resultados: cuando Yanwen lo nombra, aparece un cuadro de notas con «Distributor» (la empresa), «Distributor Website» (su web) y «Contact Number» (su teléfono). En algunos paquetes ese cuadro sale vacío, y entonces mira las líneas que siguen a `Batch delivery to carrier`. En Australia, Yanwen entrega sus paquetes por lotes a J&T Express Australia en el puerto de llegada: los escaneos de J&T aparecen después en el historial de Yanwen, y Peek muestra la recogida de J&T como «En tránsito».

Los intentos de entrega, los avisos, el cambio de fecha y cuánto tiempo espera un paquete son normas del transportista local: Yanwen no publica ninguna. `Available_For_Pick_Up` indica que el paquete te espera en un punto de recogida y `Undelivered`, que la entrega ha fallado. Para pedir otra entrega, usa el número «Last Mile Tracking» y el contacto del cuadro de notas.

La página de resultados tiene también una ventana «Proof of Delivery Images» (fotos de la entrega), que pide el código postal del destinatario. Cada escaneo muestra su desfase horario GMT, y un selector cambia la zona horaria.

## Paquete de Yanwen parado, perdido o devuelto: qué hacer

**Sin movimiento al principio.** Mientras el paquete está en `Info_Received`, el vendedor ha creado la etiqueta, pero quizá aún no lo ha entregado. Pregunta a la tienda cuándo lo envió.

**Parado varios días.** Si ya lo tiene el transportista local, mira su propio seguimiento con el número «Last Mile Tracking». Más en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating).

**Retenido en aduana.** En la línea de Yanwen para la UE, según su ficha, todos los impuestos y tasas corren a cargo del remitente, es decir, del vendedor. Desde el 1 de julio de 2026, estas compras pagan un arancel de 3 € por tipo de producto si valen hasta 150 €, y desde el 1 de noviembre de 2026 la UE suma una tasa de tramitación de 2 € por tipo de producto, sea cual sea su valor. Las fichas de Yanwen no dicen quién paga esos 2 €. Si te piden pagar algo, compruébalo con la tienda y mira [paquete retenido en aduanas](guide:customs).

**Entregado pero no lo tienes.** La entrega la hizo el transportista local, así que empieza por él y después la tienda. Si la web del transportista lo da por entregado, Yanwen ayuda a la tienda a buscarlo, pero no la indemniza. Mira [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido, dañado o devuelto.** Yanwen no publica para destinatarios ningún proceso de reclamación, plazo ni indemnización. Sus webs son para vendedores, y el contrato con Yanwen lo tiene la tienda o el marketplace: pídele a ella el reembolso o un nuevo envío. Avísala pronto: puede pedir una búsqueda a partir de los 10 días del envío, y ninguna pasados 60. Si el paquete no se puede entregar en destino y la tienda no pide una nueva entrega, por defecto se destruye. `Exception` y `Returned` son las categorías de Yanwen para estos casos.

**Envíos antiguos.** Pasado un año, los envíos desaparecen del seguimiento por tu cuenta; según un aviso de Yanwen, una persona de su línea de atención aún puede buscarlos.

## Yanwen teléfono y contacto en España

Yanwen no publica ningún teléfono, correo ni oficina para España. Esto es lo que publica, leído el 11 de octubre de 2026:

- **Teléfono:** 400-108-5656, de lunes a sábado, de 9:00 a 18:00, según su página de contacto en chino. Un número 400 es un número de atención chino; Yanwen no dice si se puede llamar desde el extranjero ni en qué idiomas atiende.
- **Correo:** marketing@yw56.com.cn, el único que aparece en su web en inglés. Yanwen no lo presenta como dirección de atención al cliente.
- **Otros canales:** un chat abajo a la derecha de yw56.com.cn, un portal de clientes y una cuenta de WeChat, todo en chino y pensado para vendedores.

Para el pedido, escribe a la tienda o al marketplace. Para la entrega, usa la empresa y el teléfono del cuadro de notas de Yanwen.

## Seguir un paquete de Yanwen con Peek

[Peek](/) reconoce al momento los números BYS, los números acabados en YP y los YW de Estados Unidos. Un número YP con un dígito de control incorrecto no se reconoce como Yanwen: elígelo tú con «Cambiar». Un enlace de track.yw56.com.cn con `nums=` se lee como Yanwen, lleve el número que lleve, así que sirve también para el número local.

Muestra el estado, el historial con la ciudad o la región de cada escaneo en un mapa, la hora de entrega, el país de destino y el transportista local con su número («Entrega con» esa empresa, o «Anunciado a …, aún no recibido» mientras Peek encuentre el paquete solo anunciado a ella, sin ningún escaneo suyo). Si Yanwen nombra un transportista local que está en la lista de Peek y el número encaja, Peek le pregunta a esa empresa; si no, a la empresa a la que corresponde con seguridad el número local; y si tampoco, con un número YP, al correo nacional del país de destino. Cambia a sus datos cuando muestran un avance más reciente. No pasa con todos los paquetes. Yanwen no da fecha prevista de entrega y no verás punto de recogida; se quedan fuera las referencias de pedido, los contactos, los códigos postales, los PIN de entrega y los números de puerta.

Las horas se leen con el desfase GMT que da Yanwen y se muestran en la zona horaria de tu dispositivo; los escaneos repetidos se eliminan. Las líneas exactas `Delivered` y `Out for delivery` aparecen con las palabras de Peek; las demás, tal como aparecen en Yanwen, casi siempre en inglés. Peek consulta el seguimiento cada 10 minutos, y cada 2 minutos cuando el paquete está en reparto: solo ese día, desde las 6 de la mañana, hora local, y mientras responda el propio seguimiento de Yanwen (a través de servicios de seguimiento, cada 15 minutos). Si otra empresa toma el relevo para la entrega, Peek sigue el ritmo de la que reparte el paquete: cada hora con Royal Mail y cada 30 minutos con Poste Italiane, PostNL y USPS, también con el paquete en reparto. Puede enviarte notificaciones en cada escaneo, solo en los pasos importantes o «Solo la entrega». Tras dos días sin novedades, o tras 12 horas si el paquete sigue en «Etiqueta creada» o «Admitido por el transportista», Peek pasa a consultar una vez por hora. No puede contactar con Yanwen ni con la tienda por ti.

## Preguntas sobre el seguimiento de Yanwen

### ¿Qué es Yanwen y de dónde es?

Es una empresa de logística china para el comercio electrónico transfronterizo, fundada en Pekín en 1998, con oficinas en Pekín, Shenzhen, Shanghái y Hangzhou. Según su perfil, desde 2015 es proveedor logístico recomendado de plataformas como AliExpress y eBay.

### ¿Cuánto tarda Yanwen en llegar?

Yanwen no publica plazos de entrega para destinatarios, así que guíate por la estimación que muestra la tienda en tu pedido. Para los envíos desde China en general, mira [seguimiento de paquetes de China](guide:tracking-from-china).

### ¿Qué significa Yanwen facility - Outbound?

Que el paquete ha salido del centro de clasificación de Yanwen en China. Después vienen `Arrived at domestic terminal station`, la aduana de exportación y el vuelo.

### ¿A qué hora entrega Yanwen?

Yanwen no publica horarios de reparto, porque en España no reparte ella: lo hace el transportista local. Mira su nombre en la página de resultados de Yanwen y consulta sus horarios.

:::sources
- [Yanwen: YW Tracking](https://track.yw56.com.cn/en/index) – web de seguimiento, idiomas, columnas, categorías, cuadro de notas, prueba de entrega, zonas horarias, teléfono
- [Yanwen: 燕文追踪 (seguimiento, en chino)](https://track.yw56.com.cn/) – escaneos en inglés, aviso sobre China Post
- [Yanwen: YANWEN EXPRESS, página principal](https://www.yw56.com.cn/en/) – empresa, Pekín 1998, oficinas, último tramo con transportistas locales, teléfono, correo
- [Yanwen: Company Profile](https://www.yw56.com.cn/webfile/Profile/) – AliExpress y eBay
- [Yanwen: Contact Us](https://www.yw56.com.cn/webfile/ContactUs/) – teléfono, correo
- [Yanwen: 联系我们 (contacto)](https://www.yw56.com.cn/webfile/联系我们/) – horario del teléfono, línea a las islas de España y Portugal
- [Yanwen: 燕文TemuY2欧盟快线 (ficha de la línea para la UE, en chino)](https://www.yw56.com.cn/cn/wishDetail.html?productCode=1938&productName=燕文TemuY2欧盟快线-普货) – impuestos y tasas a cargo del remitente, búsqueda de los 10 a los 60 días, entregado según el transportista: ayuda sin indemnización, destrucción sin nueva entrega
- [Comisión Europea: EU Customs Reform, en inglés](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por tipo de producto hasta 150 € desde el 1 de julio de 2026, tasa de tramitación de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Customs Guidance on EUR 3 customs duty, en inglés](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – la tasa de 2 € por tipo de producto, como los 3 €, sea cual sea el valor
- [Yanwen: 关于官方快件查询入口及服务的通告 (canales oficiales de seguimiento)](https://www.yw56.com.cn/webfile/ANNOUNCEMENTS/2025/06-30/7345353199651393536.html) – 100 números, límite de un año, búsqueda por teléfono, chat, portal, WeChat
- [YanwenExpress: página principal](https://www.yanwenexpress.com/) – reparto de última milla en Estados Unidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lo que lee y muestra Peek, escaneos de J&T Express Australia tras la entrega por lotes, aviso de DHL sobre la Packstation
:::
