---
title: Seguimiento TSJY (通世集运): números de pedido y estados
description: Sigue un paquete consolidado de TSJY (通世集运) desde China: formato de un número TSJY…CN, qué significa cada estado en chino y cuándo pasa a 17FEIA.
slug: seguimiento-tsjy
published: 2026-11-23
updated: 2026-11-23
---

TSJY (通世集运) es un servicio de consolidación de paquetes en China operado por 苏州通世供应链有限公司, una empresa logística de Suzhou. Compras en webs chinas como Taobao, Tmall, Pinduoduo, JD o Douyin y las tiendas envían tus productos al almacén de TSJY en Suzhou. TSJY los guarda, los agrupa en un solo paquete y los envía al extranjero. Sigue tu número TSJY en la [web de TSJY](https://www.tsjy56.com/), en 订单查询 (búsqueda de pedidos), o pégalo en el cuadro de arriba.

## Número de seguimiento de TSJY: cómo es

La web de TSJY no describe sus números, pero siguen un formato fijo. TSJY son las iniciales de 通世集运, Tongshi Jiyun. Todos los ejemplos siguientes son inventados:

| Número | Qué es |
| --- | --- |
| `TSJY482917365CN` | TSJY, nueve dígitos y CN: un número de pedido de TSJY. |
| `TSJY48291736CN` | Solo ocho dígitos: no es el formato de TSJY y Peek no identifica TSJY. Comprueba si falta un dígito. |
| `FEIKM4829173650YQ` | FEI, dos letras, diez dígitos y YQ: un número de [17FEIA](carrier:17feia), la línea que transporta algunos envíos de TSJY al extranjero. |

En Peek no importan las minúsculas, los espacios ni los guiones.

**Dónde encontrarlo.** Es el número de tu orden de envío (订单号), que envías en la página web de pedidos de TSJY (网页下单) o por WeChat (微信下单) una vez que tus compras llegan al almacén. Si otra persona contrató el envío, pídeselo.

**Dos números.** El buscador de TSJY también admite el número de la línea (运单号). En el móvil, la tarjeta de cada pedido muestra el número de TSJY y, tras una barra, el de la línea. Toca la tarjeta para ver las líneas de seguimiento (轨迹详情).

## Estados de seguimiento de TSJY explicados

El rastreador de TSJY está en chino. En el móvil, cada tarjeta muestra un estado breve para todo el pedido:

| La tarjeta dice | Qué significa para ti |
| --- | --- |
| `等待处理` | Pendiente de procesar: TSJY ha recibido tu orden de envío. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | El almacén recoge tus artículos, embala y pesa el paquete y registra la orden. |
| `等待出库`, `已出库`, `运输途中` | Esperando salida del almacén; ya salió; en tránsito. |
| `已送达`, `已完成` | Entregado; completado. |
| `运输异常` | Incidencia durante el transporte. Pregunta a TSJY. |

Peek no lee este estado de la tarjeta, sino las líneas de seguimiento (轨迹) que hay detrás. Primero aparecen los pasos de TSJY en su almacén, en chino:

| TSJY indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Pedido enviado, esperando confirmación del sistema. Aún no se ha recogido nada. | «Etiqueta creada» |
| `仓库开始拣货` | El almacén empieza a recoger tus productos. | «Admitido por el transportista» |
| `仓库拣货完成` | Recogida terminada. | «Admitido por el transportista» |
| `订单打包完成，等待出库` | Embalado, esperando salir del almacén. Puede repetirse dos veces en un minuto. | «Admitido por el transportista» |
| `订单已出库，开始国际运输` | Salió del almacén de TSJY y comienza el transporte internacional. | «En tránsito» |

En la línea 17FEIA, TSJY copia después las líneas de 17FEIA, en inglés:

| La línea dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Item information received.` | 17FEIA tiene los datos del pedido. Puede que TSJY aún conserve el paquete. | «Admitido por el transportista» |
| `The package is on the way to the facility.`, `Warehouse receipt.` | En camino al almacén de 17FEIA en China y luego recibido allí. | «En tránsito» |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | El almacén de 17FEIA retiene el paquete hasta confirmar el pedido. | «En tránsito» |
| `Order dispatched from warehouse.` | Salió del almacén de 17FEIA. | «En tránsito» |
| `Export declaration in progress.` | Declaración de exportación desde China en curso, paso habitual antes del vuelo. Aduanas no lo retiene, y no es la aduana de tu país. | «En tránsito» |
| `Flight has departed.` | El vuelo ha partido. | «En tránsito» |
| `Flight delayed.` | El vuelo lleva retraso. El paquete no se ha movido. | el estado de la línea anterior |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escala en ruta. | «En tránsito» |
| `Flight Arrived at Clearance Port.` | Aterrizó donde lo despachará la aduana de importación. | «En tránsito» |
| `Broker picked up Shipment and delivered to warehouse.` | Un agente aduanal lo llevó a su almacén para el despacho. No es una entrega a tu domicilio. | «En aduanas» |
| `Customs Clearance is in progress.` | La aduana de importación lo está revisando. | «En aduanas» |
| `Customs Clearance Completed.` | Aduanas lo ha liberado. | «En tránsito» |

En otra línea también puede aparecer su registro: `已下单` (pedido cursado), en chino y sin lugar, entre el embalaje de TSJY y su salida. Igual que `Item information received.` en 17FEIA, llega mientras TSJY aún tiene el paquete: «Admitido por el transportista». Tras la salida de TSJY, `已出库` (despachado del almacén), también en chino y sin lugar, indica que el almacén de esa línea reexpidió el paquete al día siguiente de recibirlo: «En tránsito».

Peek descarta la línea de 17FEIA con fecha estimada de salida, que no es un escaneo, así como cualquier línea fechada en el futuro. Otras líneas, como las de la empresa repartidora, Peek las interpreta por sus palabras, como entregado o aduanas.

**Horas.** La página de detalles de TSJY muestra cada hora según el reloj de tu dispositivo, y Peek muestra igual los pasos propios de TSJY. Peek lee las líneas de almacén de 17FEIA en hora de Pekín (UTC+8), tal como las registra 17FEIA. Si el lugar de una línea posterior termina con un código de país como `(DE)`, Peek usa la hora de ese país cuando tiene una sola zona horaria.

## Quién entrega los paquetes de TSJY

La web de TSJY indica que envía a más de 220 países y regiones, por vía aérea exprés (加急空运) o marítima económica (经济海运). No especifica quién reparte en cada país ni da plazos de entrega. La página de detalles indica la línea (渠道, canal) por la que viaja cada envío.

Peek conoce una línea por su nombre: [17FEIA](carrier:17feia) (一起飞), una empresa logística china con rastreador propio en www.17feia.com. El seguimiento de 17FEIA puede nombrar la empresa que reparte en tu país junto con su número.

TSJY no publica normas de intentos de entrega ni puntos de recogida: corresponden a la empresa que entrega. Consulta [entregas fallidas](guide:missed-delivery).

**Aduanas en la UE.** Los bienes comprados online introducidos en la UE desde fuera llevan un arancel temporal de 3 € por artículo en paquetes de hasta 150 € desde el 1 de julio de 2026, y una tasa de gestión de la Unión de 2 € por artículo, cualquiera que sea el valor, desde el 1 de noviembre de 2026. Un artículo son las mercancías con la misma clasificación arancelaria, descripción y, cuando se exija, origen. Ambos se aplican a ventas a distancia: bienes enviados por el vendedor o por cuenta suya. La guía de la Comisión aclara que los bienes no los envía el vendedor cuando el cliente organiza el transporte sin intervención del vendedor, lo cual puede darse al contratar tú mismo a TSJY: decide la aduana de tu país. La web de TSJY no explica cómo se abonan aranceles o tasas. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de TSJY sin actualizar, perdido o devuelto

Con TSJY normalmente el cliente eres tú porque contrataste el envío. Así que si un paquete se retrasa, se pierde o llega dañado, contacta primero con TSJY.

**Productos aún no llegados al almacén.** Antes de llegar a TSJY, tus compras viajan con mensajerías chinas de cada tienda: síguelas donde las compraste. La web de TSJY indica que su seguimiento empieza cuando el paquete entra en su almacén y dura hasta que se firma la entrega.

**Sin resultados para el número.** Si no reconoce un número, la página móvil de TSJY muestra `未找到相关的轨迹信息。` (no se encontró información de seguimiento). Revisa posibles errores. El paquete muestra «Esperando a TSJY» y Peek sigue comprobando. Si dos días después TSJY sigue sin conocer el número y nadie más ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobar a diario, y semanalmente cuando pasa una semana desde que lo añadiste.

**Parado en el almacén.** Si los pasos propios de TSJY no avanzan durante varios días, pregunta a su atención al cliente.

**Sin novedades tras el vuelo.** Después de `Flight has departed.`, puede no haber noticias hasta el aterrizaje o la aduana. Si un paquete todavía en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin una nueva línea, Peek lo marca con «Sin novedades desde hace cuatro días» (o «Sin novedades desde el …»), explica «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a TSJY.» y ofrece «Pide ayuda a TSJY». Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating) y [seguir paquetes desde China](guide:tracking-from-china).

**Perdido o dañado.** La web de TSJY promete compensar íntegramente por pérdida o daño (丢破必赔), pero sus páginas públicas no dan reglas ni plazos de reclamación: escribe enseguida a su atención al cliente y conserva la caja y la mercancía. Consulta [paquetes perdidos](guide:lost-parcel) y [paquetes dañados](guide:damaged-parcel).

**Entregado pero no está, o devuelto.** Revisa alrededor de casa y pregunta a los vecinos, luego avisa a TSJY. Su web no menciona devoluciones. Consulta [entregado pero no recibido](guide:delivered-not-received) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de TSJY

En la [página principal de TSJY](https://www.tsjy56.com/), en el apartado 客服中心 (centro de atención al cliente):

- **Teléfono y correo:** un teléfono de atención al cliente (客户服务电话) y un correo electrónico (客服邮箱). No se indican horarios.
- **WeChat:** un código QR para añadir la atención al cliente por WeChat (添加微信客服), además de su cuenta de servicio y miniprograma de WeChat.
- **Almacén:** en Huaqiao (花桥镇), Suzhou, con la dirección completa en la página.

El sitio está solo en chino y no menciona atención en inglés.

## Seguir un paquete de TSJY con Peek

[Peek](/) identifica TSJY al instante por un número `TSJY…CN` («Transportista detectado»): ningún otro transportista que sigue Peek usa este formato. También funciona un enlace a los resultados de TSJY con un número TSJY tras `nos=`; si incluye varios, Peek toma el primero. Un enlace de TSJY con el número de la línea, o con cualquier número que no cumpla la forma de TSJY, no se guarda en TSJY: Peek lee ese código por sí solo, como si lo hubieras pegado directamente.

Para cualquier otro número, pégalo igualmente: Peek sigue a más de 4.500 transportistas. Cuando el formato encaja con varios, Peek muestra «Comprobando servicios de seguimiento…» mientras pregunta; si no encaja con ninguno, lo busca en cuanto lo añades. Si Peek no identifica al transportista, pulsa «Cambiar» y elígelo en «Todos los transportistas», o usa «Cambiar transportista» más adelante. Si eliges TSJY para otro número, Peek avisa de que TSJY «no emite números como este».

Peek lee el rastreador de TSJY: cada línea con lugar y hora. Omite el origen, el país de destino y el nombre de la línea. TSJY no ofrece fecha de entrega, peso, código de recogida ni firmante. El estado de Peek sigue la línea más reciente que comprende.

Cuando TSJY facilita un número de 17FEIA o un número de línea cuyo formato apunta a un transportista que Peek conoce, Peek consulta a ese transportista. Hasta que ese transportista registre un escaneo al menos tan reciente como el último de TSJY, Peek sigue en TSJY mostrando ya quién entrega («Entrega con 17FEIA», o «Anunciado a 17FEIA, aún no recibido» mientras 17FEIA solo tiene el aviso sin escaneo propio) junto con ese número. Después Peek pasa a seguir el paquete allí y conserva el historial de TSJY.

Mientras tengas el paquete abierto en Peek, o el día de entrega si las notificaciones están activadas, Peek comprueba hasta cada 10 minutos mientras el rastreador propio de TSJY responda (cuando 17FEIA o el repartidor final toma el relevo, Peek sigue el ritmo de ese transportista, incluido hasta cada 2 minutos desde las 6:00 hora local el día de entrega si su rastreador propio responde). Después de dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora; y si permanece siete días en «Etiqueta creada», cada 6 horas. Puede notificarte sobre «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con TSJY, 17FEIA ni las tiendas por ti.

## Preguntas sobre el seguimiento de TSJY

### ¿Qué es TSJY (通世集运)?

Un servicio de consolidación (集运) para personas en el extranjero que compran en webs chinas. Su web indica que fue fundado en 2005, almacena paquetes gratis durante 180 días y envía a más de 220 países y regiones.

### ¿Dónde puedo seguir un número de TSJY?

En la [web de TSJY](https://www.tsjy56.com/), bajo 订单查询: introduce el número TSJY o el número de la línea y pulsa 查询. O pégalo en Peek.

### ¿Qué significa 订单打包完成，等待出库?

En chino significa «pedido embalado, esperando salir del almacén»: tu paquete ya está embalado en el almacén de TSJY a la espera de la línea. Peek muestra «Admitido por el transportista». Después viene `订单已出库，开始国际运输`: ya salió y comenzó el viaje internacional.

### ¿Por qué mi paquete de TSJY muestra un número de 17FEIA?

[17FEIA](carrier:17feia) es la línea que lleva tu paquete al extranjero: la tarjeta de resultados de TSJY muestra su número `FEI…YQ` tras una barra. En cuanto 17FEIA se pone al día, Peek sigue el paquete ahí.

### ¿Cómo contacto con TSJY?

Desde la sección 客服中心 de su página principal: teléfono, correo electrónico y WeChat, todo en chino.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – empresa, servicios, compromiso de compensación, alcance del seguimiento, búsqueda de pedido y resultados en móvil, pedidos, contactos, almacén
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – resultados en móvil: tarjetas con ambos números, estados de tarjeta, mensaje sin resultados
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – nombre y número del canal (渠道), líneas de seguimiento, horas según el reloj del dispositivo
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026, tasa de gestión de la Unión de 2 € desde el 1 de noviembre de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artículo sin importar el valor, qué es un artículo, ventas a distancia y transporte organizado por el cliente
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de número, enlace, textos y fases, husos horarios, traspaso a la línea, qué lee Peek
:::
