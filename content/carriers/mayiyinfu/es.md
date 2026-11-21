---
title: Seguimiento de Mayi Yinfu: códigos MY, vuelos y DHL
description: Rastrea un paquete de Mayi Yinfu (蚂蚁银服) desde China continental: números MY…US y MYYF…CN, estados de vuelo, etapas adelantadas y entrega con DHL en Europa.
slug: seguimiento-mayi-yinfu
published: 2026-11-21
updated: 2026-11-21
---

Mayi Yinfu (`蚂蚁银服`) es el nombre abreviado de `广东蚂蚁银服国际物流有限公司` (*Mayi Yinfu International Logistics*), un transitario de Guangdong (China continental) que envía paquetería pequeña y urgente al extranjero. Sus números de seguimiento constan de `MY`, diez dígitos y `US` (o `MYYF`, ocho dígitos y `CN`): pega el tuyo en el cuadro superior o introdúcelo en el apartado `快递查询` de `mayiyinfu.com.cn`. Mayi Yinfu transporta la mercancía en avión hasta tu región y allí la entrega a un operador local —hacia Europa, Deutsche Post [DHL](carrier:dhl)—.

## Número de seguimiento de Mayi Yinfu: cómo es

`MY` proviene de `蚂蚁` (*mǎyǐ*, hormiga). Estos son los formatos que distingue Peek, con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `MY4829105736US` | `MY`, 10 dígitos y `US` (14 caracteres): código exclusivo de Mayi Yinfu, detectado al instante. |
| `MYYF48291057CN` | `MYYF` (iniciales de `蚂蚁银服`), 8 dígitos y `CN` (14 caracteres): segundo formato admitido por el rastreador de Mayi Yinfu. |
| `MY482910573US` | Solo 9 dígitos en vez de 10: Peek avisa: «Empieza como un número de seguimiento de Mayi Yinfu, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» |
| `RR482910576CN` | Dos letras, 9 dígitos y `CN`: un envío postal de [China Post](carrier:china-post), no de Mayi Yinfu. |

Las minúsculas y los espacios no afectan en Peek. Como Mayi Yinfu trabaja para empresas remitentes, el número te lo facilita la tienda donde compraste ([cómo encontrar tu número de seguimiento](guide:find-tracking-number)); el buscador de Mayi Yinfu también localiza pedidos por la referencia exacta del vendedor (incluidos los guiones).

## Qué significan los estados de seguimiento de Mayi Yinfu

Mayi Yinfu no publica una tabla oficial de estados. Sus propias líneas aparecen en chino (casi siempre seguidas de una traducción al inglés) y, tras el traspaso, copia en inglés los eventos de la empresa de reparto:

| Estado de Mayi Yinfu | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `货物电子信息已经收到` | Datos electrónicos recibidos en la plataforma Hualei (`华磊`, la misma que usa BYHD): el vendedor creó la orden, sin escaneo físico aún. | «Etiqueta creada» |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Copiado de DHL: DHL recibió los datos electrónicos de la etiqueta, pero aún no el paquete. | «Etiqueta creada» |
| `已入库,到达收货点` | Entrada registrada en el almacén de recepción: Mayi Yinfu ya tiene tu paquete. | «Admitido por el transportista» |
| `进仓时间：…；Warehouse entry time:…`, `已出库,货物离开操作中心`, `国内清关完成` | Hora de entrada en almacén, salida del centro operativo y despacho aduanero de exportación completado en China continental. | «En tránsito» |
| `货物已交机场` | Entregado en el aeropuerto de origen para volar (**no** en tu domicilio, pese al inglés *delivered to the airport*), seguido de `货物已交机场，等待安排航班中The goods have been delivered to the airport and are awaiting the arrangement of flights.` o del día previsto de vuelo. | «En tránsito» |
| `航班延误` | Vuelo retrasado, indicando el día previsto del siguiente vuelo. | «En tránsito» |
| `航班已起飞`, `航班落地`, `头程起飞`, `头程落地`, `二程起飞` | Despegue desde origen y aterrizaje (`航班已起飞`, `航班落地`), salida y aterrizaje del primer vuelo (`头程起飞`, `头程落地`) y salida de la conexión (`二程起飞`, mal traducido en el rastreador como *Flight 202*). | «En tránsito» |
| `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Aterrizaje en destino y desmontaje de palés para el despacho aduanero de importación. | «En aduanas» |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.`, `已转运transferred` | Aduana superada y entrega al operador final, cuyo código aparece ya en el expediente. | «En tránsito» |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Copiados de DHL: clasificación en los centros de paquetería de DHL en Alemania. | «En tránsito» |
| `The shipment has arrived in the destination country/destination area.` | Llegada a tu país (a veces termina con un enlace entre paréntesis al operador local). | «En tránsito» |
| `The shipment has been loaded onto the delivery vehicle` | Cargado en el vehículo de reparto. | «En reparto» |
| `The shipment has been successfully delivered` | Entregado correctamente. | «Entregado» |

**Líneas de guion automático (`CO_C_CR`).** Aisladas, las filas `国内清关中` (aduana de exportación), `已排航班` (vuelo reservado), `起运地航班起飞` (salida de vuelo), `已到达目的国` (en país de destino) y `清关完成` (despacho completado) proceden de una secuencia automática que puede situar el paquete en el extranjero antes de llegar al aeropuerto: Peek no las cuenta como escaneos ([qué significan los estados de seguimiento](guide:tracking-statuses)).

## Quién entrega los paquetes de Mayi Yinfu

La web de Mayi Yinfu no detalla socios locales, pero tras `已转运transferred` el registro muestra el número de transferencia. **Hacia Europa**, corresponde a Deutsche Post [DHL](carrier:dhl), que sigue el envío hasta la entrega en tu país (el evento de llegada de DHL puede incluir un enlace al operador que reparte en tu puerta). **En otros destinos**, comprueba el código en [formatos de números de seguimiento](guide:tracking-number-formats) o pregunta al vendedor. Los intentos de entrega, puntos de recogida y plazos de custodia dependen de esa empresa final; sobre tributos de importación consulta [paquetes retenidos en aduanas](guide:customs), y ante enlaces sospechosos revisa [mensajes falsos de entrega](guide:delivery-scam-texts).

## Paquete de Mayi Yinfu parado, no encontrado o retrasado

- **Solo aparecen las primeras líneas:** `货物电子信息已经收到` (con o sin el preaviso de DHL) indica que el vendedor generó el envío pero aún no entregó la mercancía. Si pasan varios días sin entrada en almacén, consulta a la tienda.
- **Parado tras `已转运transferred`:** el tramo de Mayi Yinfu ya concluyó; sigue el paquete con el número del operador de entrega ([seguir un paquete desde China](guide:tracking-from-china), [por qué no se actualiza el seguimiento](guide:tracking-not-updating)).
- **Código desconocido:** cuando no reconoce un número, el rastreador de Mayi Yinfu devuelve igualmente una fila con ese código bajo `参考号` (referencia) y el resto en blanco. Peek lo trata como un número no encontrado: muestra «Esperando a Mayi Yinfu» y, si sigue sin conocerse a los dos días, te pide revisarlo; compáralo letra por letra con el mensaje de la tienda.
- **Perdido, dañado o devuelto:** Mayi Yinfu no publica ningún proceso de reclamaciones; solicita el reembolso o un reenvío al comercio o marketplace ([paquete perdido](guide:lost-parcel), [devuelto al remitente](guide:returned-to-sender), [entregado pero no recibido](guide:delivered-not-received)).

## Teléfono y atención al cliente de Mayi Yinfu

La web de `mayiyinfu.com.cn`, disponible únicamente en chino, solo muestra un teléfono móvil de China continental, **`+86 13705996996`** (`13705996996`), como línea nacional (`全国服务热线`) en la cabecera y en el cuadro `在线咨询`. Su página de contacto (`contact.html`), perfil de empresa, aviso legal y privacidad están vacíos (sin dirección, correo ni formulario). Dirígete siempre primero al vendedor.

## Seguir un paquete de Mayi Yinfu con Peek

Al pegar un número `MY…US` o un enlace `mayiyinfu.com.cn/trackIndex.htm` con `documentCode=` en [Peek](/), pasa directamente a Mayi Yinfu («Transportista detectado»). Con un código `MYYF…CN`, Peek muestra «Comprobando servicios de seguimiento…» y «Mayi Yinfu tiene este paquete.» cuando su rastreador lo confirma. Con una referencia de pedido sin guiones, pulsa «Cambiar» y elige Mayi Yinfu (`蚂蚁银服` o `MYYF` también lo localizan; como Peek quita los guiones, busca las referencias con guiones en el propio cuadro de Mayi Yinfu).

**Etapas, relojes y líneas escritas por adelantado.** En las líneas de vuelo que incluyen un día futuro previsto, Peek muestra únicamente el paso en inglés (como `The first leg has departed`), omitiendo la fecha aún no alcanzada, y descarta nombres, direcciones, códigos postales, peso, cargos y fotos (no hay fecha estimada; la sigla aislada `SZ`, que en ISO corresponde a Esuatini, se limpia como ubicación). Los escaneos situados en China continental (`CN`) —y los eventos en inglés copiados de DHL con código de país una vez enlazado DHL— se leen en hora de Pekín (`UTC+8`) y se convierten a tu zona («Horas en tu zona horaria: …»); los demás conservan su reloj local. Cualquier fila grabada más de 12 horas antes de la hora que marca, o más de 6 horas antes en el mismo segundo que una fila así, se trata como previsión, igual que las filas `CO_C_CR`: no cuenta como escaneo ni cambia el estado. En la web, el historial empieza entonces con «Otras webs pueden mostrar más pasos, pero solo son previsiones.», con esos pasos en gris tras «Verlos». Una fila fechada después de la consulta de Peek solo aparece pasada esa hora.

**Relevo automático a DHL y frecuencia.** Cuando el número de transferencia pertenece inequívocamente a [DHL](carrier:dhl), Peek muestra «Entrega con DHL» junto a ese código (o «Anunciado a DHL, aún no recibido» mientras DHL solo tenga el preaviso electrónico) y pasa al seguimiento directo de DHL en cuanto DHL publique un escaneo al menos tan reciente como el último de Mayi Yinfu; cualquier otro número de entrega debe añadirse por separado como nuevo paquete. Con el paquete abierto o el día de entrega con notificaciones activas, Peek comprueba hasta cada 10 minutos, y cada hora el resto del tiempo o tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios (si a los dos días solo siguen las líneas iniciales, consulta también servicios de seguimiento). Avisos: «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre el seguimiento de Mayi Yinfu

### ¿Por qué mi número de Mayi Yinfu termina en `US`?

Todos los códigos `MY` de Mayi Yinfu acaban en `US` (y los `MYYF` en `CN`), vaya adonde vaya el paquete: no indica destino en Estados Unidos ni pertenece a USPS.

### ¿Significa `货物已交机场` (*delivered to the airport*) que ya se entregó mi pedido?

No: significa que Mayi Yinfu entregó la saca en el aeropuerto de salida para embarcarla en el vuelo. El reparto en tu domicilio llegará después con el transportista local.

### ¿Cuánto tarda en llegar un envío de Mayi Yinfu?

Las páginas de servicios de Mayi Yinfu contienen texto provisional sin plazos de tránsito; guíate por la fecha estimada que te dio la tienda al comprar.

### ¿A quién reclamo si no llega mi paquete de Mayi Yinfu?

Al vendedor o marketplace donde hiciste la compra: él contrató a Mayi Yinfu, cuya web solo ofrece un teléfono móvil en China continental.

:::sources
- [Mayi Yinfu: 广东蚂蚁银服国际物流有限公司 (inicio)](http://www.mayiyinfu.com.cn/Default.aspx) – razón social, buscador 快递查询, acceso para empresas y teléfono +86 13705996996 (全国服务热线 / 在线咨询)
- [Mayi Yinfu: 联系我们 (contacto)](http://www.mayiyinfu.com.cn/contact.html) – página de contacto vacía salvo el móvil de cabecera
- [Mayi Yinfu: 国际小包 (paquetería internacional)](http://www.mayiyinfu.com.cn/products/category-1.html) – texto de relleno sin tiempos de entrega ni socios publicados
- [Universal Parcel Scraper: Mayi Yinfu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mayiyinfu) – plataforma Hualei, formatos MY…US y MYYF…CN, líneas de vuelo y CO_C_CR, hora de Pekín, filtro SZ y relevo a Deutsche Post DHL
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección de China Post y DHL, etapas escritas por adelantado y servicios de seguimiento
:::
