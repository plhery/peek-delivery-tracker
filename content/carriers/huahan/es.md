---
title: Seguimiento de HuaHan Logistics: número HHW…YQ y entrega
description: Rastrea tu paquete de HuaHan Logistics (华翰物流) desde China: formato HHW…YQ, significado de cada estado, empresa de reparto local, aduanas y Peek.
slug: seguimiento-huahan-logistics
published: 2026-11-18
updated: 2026-11-20
---

HuaHan Logistics (华翰物流, también conocida como HuaHan Express o 华翰速递) es una empresa china de logística transfronteriza para comercio electrónico que envía paquetes desde China por vía aérea o postal y los entrega a un transportista local en el país de destino para el reparto final. Si una tienda online envió tu pedido con HuaHan, busca un número que empiece por `HHW` y termine en `YQ` y pégalo en el cuadro superior. El buscador público de HuaHan en [hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html) también puede indicar qué empresa reparte en tu zona y el número de seguimiento local asignado a tu paquete.

## Número de seguimiento de HuaHan: formato y ejemplos

Peek reconoce los albaranes de HuaHan con el formato `HHW`, dos letras, diez dígitos y `YQ`: 17 caracteres en total. Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, dos letras, diez dígitos y `YQ`: un número de seguimiento de HuaHan. |
| `HHWCN482910573YQ` | Solo nueve dígitos: no coincide con el formato de HuaHan. Comprueba si falta una cifra. |
| `WNBAA4829105736YQ` | También acaba en `YQ`, pero empieza por `WNB`: un número de WanbExpress. |
| `JXCAA4829105736YQ` | Empieza por `JXC`: un número de Juxi Express. |

Muchos transitarios chinos terminan sus códigos en `YQ`, cada uno con sus propias letras iniciales: `HHW` identifica en exclusiva a HuaHan y ningún otro transportista del catálogo de Peek utiliza ese prefijo. Los espacios, guiones y minúsculas no importan al pegar el número en Peek.

**Dónde encontrarlo.** La tienda lo envía en el correo de confirmación de envío o lo muestra en la página de tu pedido. Si no das con él, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number). En el localizador público de HuaHan ([hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html), bajo el título `运单追踪`), la página está en chino y admite hasta 20 números separados por comas (`多个单号请用英文逗号隔开（最多支持 20 个单号同时查询）`) tras resolver, si la pide, una suma sencilla; la portada [hh-exp.cn](https://www.hh-exp.cn/) corresponde al portal de acceso para vendedores (`华翰物流订单管理系统`), desde donde enlaza al buscador público.

**Un segundo número.** El registro de HuaHan puede mostrar el código propio del transportista local. A veces HuaHan añade detrás de un guion bajo un sufijo interno que no forma parte del número real del repartidor: quita el `_` y lo que le siga cuando consultes el envío en la web del transportista local.

## Estados de seguimiento de HuaHan explicados

El rastreador de HuaHan enumera cada evento con fecha y hora, texto y lugar, del más reciente al más antiguo. Sus propios estados aparecen en inglés, a veces precedidos del texto en chino o solo en chino en ciertos pasos de almacén y aeropuerto. Estos son los hitos habituales en orden de recorrido:

| Lo que muestra HuaHan | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | La tienda ha enviado los datos del paquete a HuaHan, que aún no tiene la mercancía físicamente (cuando aparece `Shipment information received` solo, Peek lo traduce como «Información del envío recibida»). | «Etiqueta creada» |
| `快件到达作业中心 Shipment arrived at facility`, `Arrived at origin facility`, `Has entered the warehouse` | El centro operativo de HuaHan en China (en Shenzhen en los historiales que maneja el código de Peek) ha recibido el paquete. | «Admitido por el transportista» |
| `快件操作完成 Shipment departed from facility`, `Departed from origin facility`, `货物离开操作中心`, `Shipment received`, `Shipments were sent` | Procesado y saliendo de la nave de origen, o pasando por el almacén de transferencia de HuaHan antes de exportar. | «En tránsito» |
| `Transfer to Origin airport`, `快件从转运仓发出至机场，出口报关中（…）` | En camino al aeropuerto de salida mientras se tramita el despacho de exportación. | «En tránsito» |
| `Export customs clearance completed`, `快件抵达机场，预计…号航班飞`, `航班延误，重新配载至…号航班飞` | Aduana de exportación en China superada (no es la aduana de tu país) y vuelo asignado o reprogramado. | «En tránsito» |
| `Departed from airport of Origin` | El vuelo ha despegado. | «En tránsito» |
| `Arrived at destination airport`, `拆板中` | El avión ha aterrizado en el aeropuerto de destino o de escala y se está despaletizando la carga (`拆板中`). | «En tránsito» |
| `Import Customs clearance started` | La aduana de importación del país de destino está revisando el envío. | «En aduanas» |
| `Import Customs clearance completed`, `目的国清关完成` | La aduana de importación ha liberado el paquete. | «En tránsito» |
| `Has been given to local carrier`, `转单：…` | HuaHan ha entregado el paquete al transportista local (o indica tras `转单：` el código de transferencia). A partir de aquí siguen los escaneos de ese operador. | «En tránsito» |

**Las líneas del transportista local.** Tras el traspaso, el seguimiento de HuaHan retransmite los eventos de la empresa que reparte en destino. Peek los interpreta por el código interno de HuaHan o por su texto:

| Después del traspaso al operador local | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | El transportista local ya ha recibido y clasificado el paquete. | «En tránsito» |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | En el almacén central o en una planta de clasificación del transportista local. | «En tránsito» |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | Paso por una plataforma logística europea (aquí en Hungría) y tránsito entre delegaciones. | «En tránsito» |
| `In Transit, Picked Up` (seguido del alemán `Einrollung` de DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | Entrada en la delegación de reparto de DPD (no es una recogida en tu domicilio) y lectura diaria mientras espera allí. | «En tránsito» |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | En reparto con el mensajero. | «En reparto» |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | En reparto con Sameday, mensajería activa en Rumanía y Bulgaria. | «En reparto» |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | Intento de entrega fallido de DPD; con el mensaje largo, el paquete pasa a un punto Pickup. | «Intento de entrega» |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Depositado por DPD en una tienda o taquilla Pickup para que pases a recogerlo. | «Listo para recoger» |
| `The parcel has been successfully delivered.`, `Delivered` | Entregado (cuando llega `Delivered` solo, Peek muestra «Entregado»). | «Entregado» |

HuaHan archiva el reparto, el punto de recogida y la entrega bajo sus propios códigos (`CB`, `PB`, `CC`), pero Peek da prioridad al texto cuando este describe otro paso: por ejemplo, HuaHan asigna el código de reparto `CB` a la salida de una planta de [USPS](carrier:usps) (`Departed USPS Facility`), que Peek mantiene en «En tránsito».

**Dos líneas que suelen confundir.** `We will pick up your parcel from the sender soon.` puede aparecer al principio, antes incluso de que HuaHan tenga el paquete en China: es el preaviso registrado por el transportista local, no una recogida en tu casa; Peek muestra «Etiqueta creada». Por su parte, `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` deja constancia de un cambio de entrega solicitado mientras el envío aún está en China: no implica movimiento físico y Peek no le asigna estado propio.

**Datos electrónicos, no escaneos.** `The instruction data for this shipment have been provided by the sender to DHL electronically.` (con o sin punto final) es el alta electrónica en [DHL](carrier:dhl); `Pendiente de recepción en CTT Express` es el preaviso de [CTT Express](carrier:ctt-express), transmitido horas antes de que HuaHan tenga el paquete en China; `Pre-Shipment Info Sent to USPS` es el aviso previo a USPS, y `货物电子信息已经收到` indica en chino la recepción informática de los datos del pedido. Ninguno mueve el paquete: Peek muestra «Etiqueta creada» o conserva el estado que ya tuviera.

**Un escaneo repetido en dos líneas.** El rastreador de HuaHan duplica a veces en el mismo minuto un evento del operador local: una línea con ubicación y las palabras del repartidor, y otra sin lugar con una frase genérica de HuaHan como `Delivered, Parcel was delivered successfully`. Peek conserva únicamente la línea con ubicación.

**Por vía postal.** HuaHan también encamina envíos a través de [China Post](carrier:china-post). En ese caso su web reproduce en chino los escaneos de China Post con la oficina entre `【】`, y después los del correo de destino tal como China Post los recibe:

| Escaneo postal retransmitido | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post se ha hecho cargo del paquete entregado por HuaHan. | «En tránsito» |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Clasificado, llegado o saliendo de una oficina de China Post indicada entre `【】`. | «En tránsito» |
| `送交出口海关` | Presentado ante la aduana de exportación en China (no la de tu país). | «En aduanas» |
| `出口海关/放行`, `已交承运商运输` | Liberado por la aduana de exportación y entregado a la aerolínea. | «En tránsito» |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Llegada al país de destino, a su centro de tratamiento postal o a una oficina de tránsito exterior. | «En tránsito» |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)`, `境外进口互换局留存`, `送交境外进口海关` | Retenido en la oficina de cambio o en revisión por la aduana de importación de tu país. | «En aduanas» |
| `寄达地海关允许进口（无需缴税）` | Importación autorizada por la aduana de destino sin pago de aranceles. | «En tránsito» |
| `离开【…】处理中心`, `已到达【…】投递局` | Salida del centro de clasificación local y llegada a la unidad de reparto. | «En tránsito» |

**A través de Landmark Global.** En otros paquetes, HuaHan retransmite en inglés las líneas de [Landmark Global](carrier:landmark-global). Las dos primeras preceden a la recepción en China y el resto sigue al traspaso a la red de [bpost](carrier:bpost):

| Línea de Landmark Global | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `Shipment Data Uploaded` | Datos del remitente cargados en Landmark Global. | «Etiqueta creada» |
| `Processed` | Procesamiento en el mismo minuto; Peek lo lee como tránsito. | «En tránsito» |
| `Incoming scan at facility` | Entrada en almacén tras el traspaso a la red de bpost. | «En tránsito» |
| `Outgoing scan at facility` | Salida de ese almacén (archivada bajo código de reparto por HuaHan, pero mostrada como «En tránsito» por Peek porque el reparto real puede tardar días). | «En tránsito» |
| `Out for delivery (On the way)` | En ruta de entrega. | «En reparto» |
| `Delivery attempt (Delivery not possible)` | Intento de entrega sin éxito. | «Intento de entrega» |

## Quién entrega los paquetes de HuaHan y cuánto tardan

En su web corporativa ([hh-exp.com](https://www.hh-exp.com/) y [hh-exp.com/gyhh](https://www.hh-exp.com/gyhh)), HuaHan (`深圳市华翰鸿运国际物流技术有限公司`, fundada en 2008 con sede en Shenzhen) señala que opera hacia más de 150 países y regiones de Europa, Norteamérica, Sudamérica y Australia, especializada en paquetería transfronteriza de mercancías especiales (`特货小包专线`: cosmética, baterías, cigarrillos electrónicos) y canales postales (`特货邮路`). En su portada menciona un plazo orientativo general de 6 a 12 días laborables (`整体时效6-12个工作日`), sin desglosar tiempos ni socios por país: guíate por la fecha estimada que te dio la tienda al comprar.

Tras `Has been given to local carrier`, entrega una empresa del país de destino. En los envíos analizados por el código de Peek aparecen [CTT Express](carrier:ctt-express) (cuyo preaviso `Pendiente de recepción en CTT Express` figura ya desde el inicio), [DHL](carrier:dhl), DPD, Sameday, [USPS](carrier:usps), [China Post](carrier:china-post) (que conecta con el operador postal del país de destino) y [Landmark Global](carrier:landmark-global) con entrega de [bpost](carrier:bpost). Peek sigue siempre al transportista que HuaHan declara para tu paquete concreto.

**Intentos de entrega y recogida.** Los intentos, avisos de paso y plazos de custodia en oficina o punto de conveniencia dependen de las normas del transportista local: acude a él con su número local para concertar una nueva entrega. Consulta [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Aduanas, aranceles y avisos de pago fraudulentos

HuaHan trabaja para vendedores online y sus páginas no detallan si los tributos de importación de tu compra se pagaron por adelantado: revisa en el resumen de tu pedido si el IVA y los gastos aduaneros estaban incluidos. Más detalles en [paquetes retenidos en aduanas](guide:customs) y [cómo detectar SMS falsos de paquetería](guide:delivery-scam-texts).

- **Unión Europea.** Desde el 1 de julio de 2026, los bienes comprados a distancia fuera de la UE en envíos con valor intrínseco de hasta 150 € están sujetos en la **Unión Europea** a un derecho de aduana de 3 € por categoría de artículos, y desde el 1 de noviembre de 2026 se suma una tasa de tramitación de la Unión de 2 € por categoría de artículos sea cual sea el valor del envío (una categoría agrupa las mercancías con la misma clasificación arancelaria, descripción y, cuando proceda, origen, sin importar cuántas unidades incluya).
- **España.** En **España**, [Correos](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) precisa que tanto el arancel de 3 € como la tasa de 2 € se aplican a envíos destinados a la **Península, Baleares y Canarias** (Canarias forma parte del territorio aduanero de la UE aunque quede fuera del territorio del IVA de la UE), mientras que **Ceuta y Melilla** quedan fuera de ambos territorios y excluidas de esas dos medidas comunitarias. Además, en **España** el Instituto Nacional de Ciberseguridad ([INCIBE](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de)) alerta sobre campañas de *smishing* que suplantan a Correos y otras empresas de paquetería reclamando supuestas tasas de aduanas mediante un enlace, y recomienda no pulsar el enlace y verificar cualquier aviso por los canales oficiales del transportista.

## Paquete de HuaHan que no llega o seguimiento parado

**Número no encontrado (`没有找到记录`).** Si HuaHan no tiene registro del código, su web responde `没有找到记录` («no se encontraron registros»); Peek muestra «Esperando a HuaHan Logistics» y sigue consultando. Si pasados dos días ni HuaHan ni otra fuente reconocen el número, aparece «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y las comprobaciones pasan a ser diarias y, tras una semana, semanales. Revisa carácter por carácter (`HHW`, dos letras, diez dígitos, `YQ`) frente al mensaje del vendedor.

**Parado en `Shipment information received`.** El comercio generó el envío, pero HuaHan aún no ha escaneado la mercancía en su almacén. Pregunta al vendedor cuándo la entregó.

**Parado en `Import Customs clearance started`.** La aduana de tu país tiene el paquete. Si permanece ahí muchos días sin aviso del operador local, avisa al vendedor.

**Sin cambios tras `Has been given to local carrier`.** El transportista local ya tiene el paquete y su propio seguimiento suele ir por delante del de HuaHan. Busca su nombre y su código en el rastreador de HuaHan o en la tarjeta del envío en Peek.

**Varios días sin novedades.** Cuando un paquete en tránsito lleva cuatro días sin escaneos nuevos, Peek lo señala («Sin novedades desde hace cuatro días») y recuerda que es habitual quedarse sin lecturas durante vuelos largos, en aduanas o en fin de semana. Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Figura como entregado, pero no lo tienes.** Revisa buzón, conserjería, vecinos y puntos de recogida cercanos, y después contacta con el repartidor local y el vendedor. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Quien contrató a HuaHan fue el vendedor, de modo que debes reclamar el reembolso o un nuevo envío a la tienda o plataforma de compra. Consulta [paquetes perdidos](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Teléfono y atención al cliente de HuaHan

En su página oficial de contacto ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)), HuaHan publica para **China**:

- **Teléfono oficial (`官方热线`):** `400-015-6080` (línea china 400 sin horario de atención publicado; el móvil `13794450408` corresponde a consultas comerciales para remitentes `业务咨询`).
- **Sede central:** Planta 10, bloque C, edificio Bairuida, 4001 Xuegang Avenue, Bantian, distrito de Longgang, Shenzhen (`深圳市龙岗区坂田雪岗大道4001号百瑞达大厦C座10楼`), con sucursales en Yiwu, Zhengzhou y Hangzhou.

HuaHan no publica correo electrónico de soporte para destinatarios particulares. Para incidencias con el pedido o reembolsos, dirígete al vendedor; para el reparto en destino, contacta con el transportista local.

## Seguir un paquete de HuaHan con Peek

[Peek](/) detecta HuaHan Logistics al instante a partir de un código `HHW…YQ` («Transportista detectado») y consulta el rastreador de HuaHan solo con ese número completo, sin cuenta, código postal ni teléfono. Si el rastreador de HuaHan no responde, Peek recurre a servicios de seguimiento.

Peek muestra el estado, cada evento con su localidad y el país de destino registrado. Como HuaHan publica las horas sin zona horaria, Peek mantiene la hora local impresa en los pasos propios de HuaHan anteriores al traspaso y sitúa los escaneos posteriores a `Has been given to local carrier` en el huso del país de destino cuando este tiene una sola zona horaria, convirtiendo las horas ubicadas a la zona de tu dispositivo («Horas en tu zona horaria: …» en peektracker.com y en la app de iPhone). HuaHan no facilita fecha prevista de entrega ni peso, por lo que Peek tampoco los muestra.

Cuando HuaHan nombra a un transportista local conocido por Peek con un número válido para ese operador, la ficha del paquete lo indica («Entrega con CTT Express», o «Anunciado a CTT Express, aún no recibido» mientras solo conste el preaviso) y consulta también a ese transportista. Si HuaHan aporta un código postal internacional (dos letras, nueve cifras, dos letras) sin operador conocido, Peek pregunta al correo nacional del país de destino. En cuanto ese transportista muestra un escaneo al menos tan reciente como el último de HuaHan, Peek pasa a seguir el reparto allí conservando el historial previo de HuaHan.

Mientras tienes el paquete abierto, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos —y el día que sale a reparto desde las 6:00 en hora local, hasta cada 2 minutos si el rastreador de HuaHan responde directamente, o cada 5 minutos cuando la consulta abre un navegador—; a través de servicios de seguimiento únicamente, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las alertas activas. El resto del tiempo, así como tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora y adopta el ritmo del transportista local en cuanto este asume la entrega. Puedes elegir avisos de «Cada escaneo», «Pasos importantes» (incluye `Import Customs clearance started`, los pasos aduaneros postales como `送交出口海关` y `送交境外进口海关`, el reparto, el punto de recogida y la entrega, pero no el trámite rutinario de exportación `快件从转运仓发出至机场，出口报关中`) o «Solo la entrega». Peek no puede contactar con HuaHan ni con el vendedor en tu nombre.

## Preguntas frecuentes sobre el seguimiento de HuaHan

### ¿Qué es HuaHan Logistics (华翰物流)?

Es un operador logístico chino de comercio electrónico transfronterizo (`深圳市华翰鸿运国际物流技术有限公司`), fundado en 2008 en Shenzhen y también llamado HuaHan Express (`华翰速递`). Envía paquetes desde China a más de 150 países y los cede a un transportista local en destino.

### ¿Dónde puedo rastrear un paquete de HuaHan?

En el buscador de la parte superior de esta página o en el localizador público de HuaHan en `hh-exp.cn/WebTrack/CargoTrack.html` (hasta 20 números separados por comas, tras una suma sencilla si la pide). Usa siempre el código `HHW…YQ` completo de 17 caracteres.

### ¿Qué significa «Has been given to local carrier» en HuaHan?

Que el despacho aduanero de importación ha finalizado y HuaHan ha entregado el paquete a la empresa encargada de repartirlo en tu país. Los eventos posteriores del historial corresponden a ese transportista local.

### ¿Qué empresa reparte los paquetes de HuaHan en mi país?

Depende de la línea contratada por el remitente: en el código de Peek figuran CTT Express, DHL, DPD, Sameday, USPS, China Post (con entrega a través del correo del país de destino) y Landmark Global junto con la red de bpost. Consulta la tarjeta del paquete en Peek o el buscador de HuaHan para ver cuál lleva tu envío.

### ¿Con quién contacto si no llega mi paquete de HuaHan?

Con la tienda o plataforma donde hiciste el pedido, ya que es quien contrató el transporte con HuaHan. Si el paquete ya pasó al transportista local de tu país, acude a ese operador con su número de seguimiento local para gestionar la entrega.

:::sources
- [HuaHan Express: 运单追踪 (rastreador público de envíos, en chino)](https://www.hh-exp.cn/WebTrack/CargoTrack.html) – consulta pública de hasta 20 códigos separados por comas, columnas de estado y detalle de movimientos
- [HuaHan Logistics: 华翰物流订单管理系统 (portal de clientes, en chino)](https://www.hh-exp.cn/) – acceso para vendedores y enlace al rastreador público
- [HuaHan Express: Página principal oficial (en chino)](https://www.hh-exp.com/) – fundación en 2008, líneas transfronterizas de mercancías especiales hacia más de 150 países y regiones, plazo orientativo global de 6 a 12 días laborables y teléfono 400-015-6080
- [HuaHan Express: 关于华翰 (presentación de la empresa, en chino)](https://www.hh-exp.com/gyhh) – trayectoria desde 2008, razón social completa, canales postales y especiales y delegaciones
- [HuaHan Express: 联系我们 (contacto, en chino)](https://www.hh-exp.com/lxwm) – sede central de Shenzhen, sucursales de Yiwu, Zhengzhou y Hangzhou, línea oficial 400-015-6080 y teléfono comercial
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por categoría de artículos hasta 150 € desde el 1 de julio de 2026 y tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de 2 € con independencia del valor y definición de categoría de artículos
- [Comisión Europea: Territorial Scope (en inglés)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias en el territorio aduanero de la UE y fuera del ámbito del IVA; Ceuta y Melilla fuera de ambos
- [Correos: Arancel y tasas para envíos de importación a la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – aplicación en España (Península, Baleares y Canarias) y exclusión de Ceuta y Melilla
- [INCIBE: En circulación SMS fraudulentos que suplantan a Correos y otras empresas de paquetería](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – aviso en España sobre SMS fraudulentos (*smishing*) con falsos cobros aduaneros
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `HHW…YQ`, verificación aritmética, códigos y textos de HuaHan, China Post, Landmark Global/bpost, DPD, Sameday, DHL, CTT Express y USPS, husos horarios y traspaso al operador local
:::
