---
title: JRT seguimiento: número JRT…MM, vuelos, DHL y UPS
description: Sigue tu paquete de JRT (今日通) desde Xiamen: cómo es un número JRT…MM, qué significan sus pasos en chino y en inglés y quién lo entrega en Europa y en EE. UU.
slug: jrt-seguimiento
published: 2026-11-03
updated: 2026-11-08
---

JRT (今日通, Jinritong) es 厦门今日通商贸有限公司, una empresa de logística de Xiamen, en China. Si una tienda china te ha enviado el pedido con JRT, te habrá dado un número de seguimiento que empieza por JRT y acaba en MM: pégalo en la casilla de arriba para rastrear el paquete. La web de seguimiento de JRT lo sigue desde China: el vuelo, la aduana y el paso a DHL, en los envíos a Europa, o a UPS, en los envíos a Estados Unidos. JRT copia en su web los escaneos de DHL y de UPS. En su línea a Irlanda con FedEx en el nombre del producto, el paquete pasa en cambio a FedEx; en su línea a España, a CTT Express, y en la de Polonia, a InPost.

## Número de seguimiento de JRT: cómo es

Los números de envío de JRT llevan JRT, diez cifras y MM: 15 caracteres en total (JRT son las iniciales de 今日通). Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `JRT0000000000MM` | JRT, diez cifras y MM: un número de envío de JRT. |
| `JRT000000000MM` | Solo nueve cifras: no es el formato de JRT. Mira si falta una cifra. |
| `JRTO0000000000MM` | Una letra O detrás de JRT: Peek lo lee como `JRT0000000000MM`, la única forma que conoce la web de seguimiento de JRT. |
| `UP0000000000000000` | UP y dieciséis cifras: una referencia del remitente con la que se puede encontrar un pedido de JRT. Nada indica que ese formato solo lo usen los clientes de JRT. |
| `RR000000005CN` | Dos letras, nueve cifras y CN: un número de [China Post](carrier:china-post), no de JRT. |

En Peek da igual que lo pegues con espacios o en minúsculas. ¿No encuentras el número? Mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number). La web de seguimiento de JRT también encuentra un pedido por la referencia que le puso la tienda, si la escribes tal cual, con sus guiones.

**Un enlace con cifras en vez de un nombre.** La web de seguimiento de JRT no tiene dominio: está en la dirección numérica de un servidor, por HTTP sin cifrar, y Peek no conoce ninguna otra web de JRT. Un enlace a ella lleva esas cifras donde iría el nombre de la web, además de `trackIndex.htm`, `documentCode=` y un número o una referencia: puedes pegarlo en Peek tal cual. Peek, en cambio, no pone enlaces a esa web: sin HTTPS, tu número viajaría sin cifrar.

## Estados del seguimiento de JRT: qué significa cada uno

Muchos pasos de JRT van en chino, seguidos de la traducción al inglés de la propia JRT, y Peek los muestra enteros. En el orden en que aparecen:

| JRT escribe | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `货物电子信息已经收到` | Se han recibido los datos electrónicos del envío: la tienda ha creado el pedido. Lo escribe Hualei (华磊), la plataforma en la que funciona la web de JRT; no es un escaneo. | «Etiqueta creada» |
| `Shipment information received` | Los datos del envío han llegado a JRT; nadie ha escaneado todavía el paquete. | «Etiqueta creada» |
| `到达收货点` | Ha llegado al punto de recepción de JRT: el primer escaneo con el paquete en sus manos. | «Admitido por el transportista» |
| `货物离开操作中心` | El paquete ha salido del centro de operaciones de JRT. | «En tránsito» |
| `深圳集散中心 已收货` | El centro de distribución de JRT en Shenzhen ha recibido el paquete, después de que saliera del centro de operaciones y antes de su vuelo. Peek lo muestra en inglés: «Received at the Shenzhen distribution center». | «En tránsito» |
| `货物打包完成，已发往航司` | Embalado y camino de la aerolínea, antes del vuelo. Peek lo muestra en inglés: «Goods packed and sent to the airline». | «En tránsito» |
| `抵达航司已起飞` | En manos de la aerolínea, y el vuelo ha salido del aeropuerto que indica el paso. Peek muestra «Arrived at the airline, flight departed». | «En tránsito» |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Entregado en el aeropuerto, a la espera de un vuelo. Ese `delivered` es al aeropuerto, no a ti. | «En tránsito» |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Despachado en la aduana de exportación: espera el vuelo del día que aparece en lugar de los puntos. | «En tránsito» |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | El vuelo ha despegado. | «En tránsito» |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Ha aterrizado: se desmontan los palés antes de la aduana. | «En tránsito» |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | En el aeropuerto de destino, a la espera de la recogida y de la aduana de importación. | «En aduanas» |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | El despacho de importación está en curso. | «En aduanas» |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Ha pasado la aduana y va en camión. | «En tránsito» |
| `已交服务商 Delivered to service provider` | Entregado a la empresa que hace el último tramo. No es la entrega a ti. | «En tránsito» |

Algunos pasos que JRT escribe solo en chino aparecen en Peek con el inglés que les dan otras empresas de logística: `货物电子信息已经收到` como «The electronic cargo information has been received», `到达收货点` como «Arrived at the receiving point», `货物离开操作中心` como «Goods leave the operation center» y `快件操作完成` como «Shipment departed from facility». El paso del aeropuerto con el vuelo ya reservado, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, da el día del vuelo en chino y en inglés; Peek lo muestra como «Goods delivered to airport», porque el vuelo todavía no ha salido.

Ya al final del trayecto a Europa, JRT escribe solo en chino otros tres pasos, que Peek muestra en inglés, todos «En tránsito»:

| JRT escribe | Peek muestra | Qué significa para ti |
| --- | --- | --- |
| `航班已抵达` | «Flight has arrived» | El vuelo ha aterrizado en el aeropuerto europeo que indica el paso. |
| `清关完成，中转德国中` | «Customs cleared, in transit to Germany» | Ha pasado la aduana en el aeropuerto de llegada y va camino de Alemania, donde se entrega a la empresa que reparte. |
| `抵达德国，已交服务商` | «Arrived in Germany, handed to the delivering carrier» | Ha llegado a Alemania y se ha entregado a la empresa que reparte, como DHL. No es la entrega a ti. |

**Por carretera, a través de Kazajistán.** En la línea de JRT a Europa por carretera, el paquete sale de China por tierra y no en avión, con cuatro pasos solo en chino que Peek muestra en inglés, todos «En tránsito»: `已装柜发车，待抵达新疆` como «Loaded into a container and on its way to Xinjiang» (después del centro de Shenzhen), `已抵达新疆，换装/报关，待出境` como «Arrived in Xinjiang for transloading and export declaration, waiting to leave China», `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` como «Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one» (una espera en la frontera, no un paquete perdido) y `已出境，哈萨克斯坦阿拉木图` como «Left China, Almaty, Kazakhstan» (ha cruzado la frontera y está en Almaty).

**Pasos solo en inglés.** Otros pasos de JRT van solo en inglés. En Peek todos son «En tránsito», salvo que se indique otra cosa:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`: camino del vuelo.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: se está reservando un vuelo.
- `Flight is expected to take off on …` o `The flight is expected to depart on … and land on …`, con fechas en lugar de los puntos: el vuelo está reservado.
- `Flight has flown`, `The flight has departed and is expected to … land`: en el aire.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `The NOA has been received` (el aviso de llegada de la aerolínea), `Dismantling and picking up goods in progress`: ha aterrizado y aún no ha pasado la aduana.
- `Customs clearance of the cargo is in progress` («En aduanas») y `Customs clearance completed`: en la aduana de importación y ya despachado.
- `The goods have been handed over to the carrier`: entregado a la empresa que reparte.
- `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment` (en el almacén de JRT en el país de destino) y luego `It has been handed over to the service provider.` (entregado a la empresa que reparte, como FedEx): después de la aduana, no es un primer escaneo.

En `The flight is expected to depart on … and land on …` y `The flight has departed and is expected to … land`, las fechas llevan primero el mes: 11.12 es el 12 de noviembre, no el 11 de diciembre.

**Pasos con barra.** En algunos envíos, JRT une el chino y el inglés con una barra, y en Peek todos son «En tránsito»: `预计…航班/Flight scheduled for …` (el vuelo está reservado, con su día en las dos partes en lugar de los puntos), `航班已启运/Package in transit to destination country` (en el aire) y `航班已落地，拆板中/Package arrived at destination country` (ha aterrizado y aún no ha pasado la aduana). Antes llega `国内清关完成`: es el despacho de exportación en China, no la aduana de tu país («En tránsito»).

**Pasos de la línea a México.** En su línea a México, JRT anuncia el vuelo en español: `Se espera que el vuelo salga hacia … el …`, con el destino y el día en lugar de los puntos (el vuelo está reservado), y después `The flight is en route to the airport in …`, que vuelve a nombrar el destino (en el aire). Los dos son «En tránsito».

**Pasos de DHL y de FedEx.** En los envíos a Europa, la web de JRT sigue con los escaneos de DHL, casi siempre en minúsculas:

| DHL escribe | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `the instruction data for this shipment have been provided by the sender to dhl electronically` | DHL tiene los datos de la etiqueta. | «Etiqueta creada» |
| `shipment in transit to dhl` | El paquete va camino de DHL, antes del vuelo. | «En tránsito» |
| `The shipment has been posted by the sender at the retail outlet` | JRT ha entregado el paquete en una oficina de Deutsche Post. | «En tránsito» |
| `pick-up was successful.` | DHL ha recogido el paquete que dejó JRT. No es una recogida tuya. | «En tránsito» |
| `the shipment has been processed in the parcel center of origin`, `loaded to movement / tour vehicle` | Clasificado en el centro de paquetes de origen de DHL y cargado en un vehículo. | «En tránsito» |
| `the shipment has been processed in the destination parcel center` | Clasificado en el centro de paquetes de DHL de destino, cerca de ti. | «En tránsito» |
| `not delivered-consignee could not be reached` | No se pudo entregar: no había nadie. | «Intento de entrega» |
| `not delivered-bad address` | No se pudo entregar: el repartidor no encontró la dirección. | «Intento de entrega» |
| `held for consignee collection` | Te espera en un punto de recogida. | «Listo para recoger» |
| `shipment will be returned to origin-not ordered` | El destinatario lo rechazó porque no lo había pedido, y vuelve al origen. | «Devuelto al remitente» |
| `shipment will be returned to origin-bad address` | No se encontró la dirección, y vuelve al origen. | «Devuelto al remitente» |
| `the shipment has been successfully delivered` | Entregado. Si antes hubo un paso de devolución, es la entrega al remitente. | «Entregado», o «Devuelto al remitente» tras una devolución |

Algunos paquetes empiezan, en cambio, con `Shipment information sent to FedEx`: los datos de la etiqueta han pasado a FedEx, que hace el último tramo («Etiqueta creada»). Después, la web de JRT copia los escaneos de FedEx en inglés, como `Arrived at FedEx hub`, `Departed FedEx hub` y `On the way`, todos «En tránsito».

**Pasos de JRT y de UPS en los envíos a Estados Unidos.** En los envíos a Estados Unidos, JRT escribe solo en chino, y Peek muestra sus pasos en inglés, todos «En tránsito»:

| JRT escribe | Peek muestra | Qué significa para ti |
| --- | --- | --- |
| `…号航班` | «Booked on a flight» | Tiene plaza en un vuelo. En lugar de los puntos van el mes y el día, primero el mes: `10/22号航班` es el 22 de octubre. |
| `航班已飞` | «Flight has flown» | El vuelo ha despegado. |
| `航班抵达` | «Flight has arrived» | El vuelo ha llegado. |
| `提货中` | «Picking up the goods» | Lo están recogiendo en el aeropuerto, tras el aterrizaje y antes de la aduana. |
| `清关完成，已提货，待交仓` | «Customs clearance completed, goods picked up, awaiting hand-over to the warehouse» | Ha pasado la aduana y está recogido, a la espera de entrar en el almacén. |
| `已交仓` | «Handed over to the warehouse» | Entregado en el almacén de la empresa que reparte, antes del primer escaneo de UPS en uno de sus centros. |

Después, la web de JRT copia los escaneos de UPS en chino, y Peek los muestra con el inglés de la propia UPS:

| JRT copia de UPS | Peek muestra | Estado en Peek |
| --- | --- | --- |
| `发件人已创建标签，但是 UPS 尚未收到包裹。` | «Shipper created a label, UPS has not received the package yet.» | «Etiqueta creada» |
| `抵达设施`, `离开设施`, `UPS 设施处理` | «Arrived at Facility», «Departed from Facility», «Processing at UPS Facility»: llegada, salida y clasificación en centros de UPS. | «En tránsito» |
| `已提取` | «Picked Up», entre escaneos de centros de UPS. No es una recogida tuya. | «En tránsito» |
| `已装上递送车辆`, `准备今天递送` | «Loaded on Delivery Vehicle», «Out For Delivery Today»: en el vehículo de reparto. | «En reparto» |
| `已递送` | «Delivered» | «Entregado» |

En los envíos a Europa, las horas de los pasos de JRT van en hora de Pekín (UTC+8): las 17:00 allí son las 10:00 en la España peninsular con el horario de invierno (desde el 25 de octubre de 2026) y las 11:00 con el de verano; en Canarias, una hora menos. Los pasos copiados de DHL van, en cambio, en la hora local de DHL: los que empiezan por `The shipment …`, `The international shipment …` o `the instruction data …`, `loaded to movement / tour vehicle` y los demás pasos del vehículo, `pick-up was successful.`, `not delivered-consignee …`, `not delivered-bad address`, `held for consignee collection` y `shipment will be returned to origin …`. Si uno de ellos nombra una ciudad de Alemania o del país de destino, Peek lo lee en la hora de esa ciudad. Si no nombra ninguna (un código de país a su lado no basta), lee los datos de la etiqueta y los pasos del centro de paquetería de origen, del centro de exportación y del transporte al país de destino en hora de Alemania, y los demás en la hora del país de destino (en la de Alemania si los datos de JRT no lo traen). Los escaneos copiados de UPS, y cualquier paso con lugar en Estados Unidos, Peek los lee en la hora local de ese lugar.

## Quién reparte los paquetes de JRT en Europa y en Estados Unidos

**En los envíos a Europa**, el historial de JRT acaba con DHL, salvo en su línea a Irlanda con FedEx (más abajo), en su línea a España, que acaba con CTT Express, y en la de Polonia, que acaba con InPost. En las líneas de DHL, JRT entrega el paquete en una oficina de Deutsche Post, y su web copia los escaneos de DHL. Cuando JRT ya ha entregado el paquete, sus datos traen el número de DHL en lugar del de JRT, aunque siguen sin el nombre de la línea. Si Peek reconoce ese número como de DHL, muestra «Entrega con DHL» con el número (o «Anunciado a DHL, aún no recibido» mientras DHL solo lo tenga anunciado, sin ningún escaneo suyo) y pasa al seguimiento de DHL en cuanto DHL escanea el paquete (más abajo). Si no, pide a la tienda el número de DHL: con él, el seguimiento de DHL te lleva hasta la entrega (en [DHL Paket](carrier:dhl) tienes cómo son sus números, qué significan sus estados y quién entrega sus paquetes en España).

**En la línea a España**, `En tránsito internacional. Pendiente de recepción en CTT Express` quiere decir que CTT Express ya tiene el aviso del paquete, pero aún no lo ha recibido («En tránsito»). Peek no pasa al seguimiento de CTT Express: si la tienda te da su número, síguelo en [CTT Express](carrier:ctt-express).

**En los envíos a Estados Unidos**, el historial de JRT acaba con UPS: JRT deja el paquete en el almacén de la empresa que reparte (`已交仓`), y su web copia los escaneos de UPS hasta la entrega.

**En la línea de JRT a Irlanda con FedEx en el nombre del producto**, el paquete pasa a FedEx, y desde entonces los datos de JRT traen el número de FedEx en lugar del suyo. Si ese número tiene uno de los formatos de FedEx, Peek muestra «Entrega con FedEx» con el número (o «Anunciado a FedEx, aún no recibido» mientras FedEx solo lo tenga anunciado) y pasa al seguimiento de FedEx en cuanto FedEx escanea el paquete, como con DHL (más abajo). Si FedEx reparte tu paquete y Peek no muestra su número, pídeselo a la tienda y síguelo en [FedEx](carrier:fedex).

**En otros casos**, Peek no sabe quién reparte los paquetes de JRT. Pregunta a la tienda qué empresa te entrega el pedido y con qué número: los intentos de entrega, los puntos de recogida y los plazos para recoger son cosa de esa empresa.

**Impuestos al importar en la UE.** Según la Comisión Europea, desde el 1 de julio de 2026 los envíos de hasta 150 € que llegan de fuera de la UE pagan un arancel de 3 € por tipo de producto, y desde el 1 de noviembre de 2026 las compras a distancia pagan además una tasa de tramitación de 2 € por tipo de producto, también por encima de 150 €. ¿Te piden pagar algo para recibir el paquete? Mira [paquete retenido en aduanas](guide:customs).

## Paquete de JRT que no llega o seguimiento parado

**Solo los primeros pasos.** Si solo ves `货物电子信息已经收到`, `Shipment information received` o un paso de etiqueta de DHL o de FedEx, la tienda ha registrado el envío, pero nadie ha escaneado el paquete. Si en unos días no aparece nada más, pregunta a la tienda si lo envió de verdad.

**Esperando un vuelo.** Un paso de vuelo da el día para el que está reservado, y `航班延误，预计…号飞出 Flight delayed, estimated departure on …`, el nuevo día si se retrasa. Si pasa ese día sin un paso de salida, avisa a la tienda.

**Nada después de `已交服务商` o `已交仓`.** JRT ya ha hecho su parte. Si su web no copia los escaneos de DHL, de UPS o de FedEx y Peek no muestra el número de DHL o de FedEx, los siguientes pasos están en el seguimiento de la empresa que reparte: pide ese número a la tienda. Más en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating) y [seguimiento de paquetes de China](guide:tracking-from-china).

**No sale nada.** Con un número que no conoce, la web de JRT muestra un solo resultado, con el número como referencia y todas las demás columnas vacías. Peek muestra «Esperando al transportista» y sigue comprobando (si pegaste una referencia UP sin elegir transportista, «Esperando el primer escaneo de JRT»). Compara el número con el mensaje de la tienda, carácter por carácter.

**Dirección no encontrada.** Después de `not delivered-bad address`, revisa la dirección de tu pedido y avisa enseguida a la tienda. Si luego aparece `shipment will be returned to origin-bad address`, el paquete vuelve al origen: pide a la tienda el reembolso o un nuevo envío.

**Entregado, pero no ha llegado nada.** El escaneo de entrega es de la empresa que reparte, no de JRT: pregunta a esa empresa y después avisa a la tienda. Más en [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Fue la tienda quien contrató el envío con JRT, así que pídele a ella, o al marketplace, el reembolso o un nuevo envío. Mira [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## JRT: teléfono y atención al cliente

A 31 de octubre de 2026, Peek no ha encontrado datos de contacto de JRT: no conoce ninguna web de la empresa aparte de su web de seguimiento, ni teléfono, ni correo electrónico, ni dirección para destinatarios. Fue la tienda quien contrató el envío con JRT: pregúntale a ella por tu pedido, y al marketplace si no responde.

## Seguir un paquete de JRT con Peek

[Peek](/) reconoce un número de JRT por su forma: JRT, diez cifras y MM, que no usa ningún otro transportista de su catálogo. También reconoce un enlace a la web de seguimiento de JRT (más arriba). Con una referencia UP de dieciséis cifras, Peek pregunta a la web de JRT si la conoce y, si es así, elige JRT. Las demás referencias no tienen una forma que Peek pueda reconocer: pégalas igualmente, porque Peek sigue más de 3500 transportistas, y luego pulsa «Cambiar» y elige JRT (también lo encuentras escribiendo 今日通 o Jinritong), o usa «Cambiar transportista» en un paquete que ya hayas añadido. Si usaste una referencia, Peek muestra después el número JRT bajo «JRT lo sigue con el número», hasta que JRT entrega el paquete y sus datos pasan a traer el número de DHL o de FedEx. Eso sí, Peek quita los guiones, así que una referencia con guiones no se encuentra a través de Peek: pide a la tienda el número JRT…MM.

Peek muestra el estado, cada paso con su lugar y, cuando los datos de JRT lo traen, el país de destino; el estado lo saca de los pasos, no del resumen que da JRT. Lee las horas en hora de Pekín, salvo las de los pasos de DHL descritos arriba, que lee en la hora local de DHL, y las de los escaneos copiados de UPS y los pasos con lugar en Estados Unidos, que lee en la hora local de ese lugar, y te las muestra en la zona horaria de tu dispositivo. Aparta los pasos escritos más de doce horas antes de la hora que llevan y los que tienen una fecha posterior a la consulta. Deja fuera los nombres, la dirección, los códigos postales, el peso, los datos de pago, la firma y la prueba de entrega, y no muestra fecha prevista de entrega: JRT no da ninguna.

Peek sigue los paquetes a Estados Unidos solo en la web de JRT, con los escaneos de UPS que copia. En los envíos a Europa, cuando los datos de JRT traen un número que Peek reconoce como de DHL, Peek consulta también a DHL y pasa a su seguimiento en cuanto DHL muestra un escaneo igual de reciente o más que el último de JRT, sin perder los de JRT; hasta entonces sigue en la web de JRT, con los escaneos de DHL que copia. En la línea a Irlanda con FedEx en el nombre del producto, hace lo mismo con FedEx cuando los datos de JRT traen un número con uno de los formatos de FedEx. Peek solo toma de los datos de JRT un traspaso más: a Swiss Post, el correo suizo, cuando esos datos nombran a Swiss Post como línea de envío y dan su número, en un paquete que va a Suiza o Liechtenstein. Si la tienda te da otro número de la empresa que reparte, añádelo como un paquete aparte.

Peek consulta cada 10 minutos, y cada hora cuando JRT deja de dar noticias (12 horas en los primeros pasos, o dos días sin nada nuevo). Puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega». Con «Pasos importantes» te avisa de los pasos de JRT con el estado «En aduanas», de los escaneos copiados de DHL de intento de entrega, punto de recogida, entrega y devolución, y de los de UPS de reparto y entrega, pero no de los vuelos de JRT, de `已交服务商` ni de `已交仓`. Peek no puede contactar con JRT ni con la tienda por ti.

## Preguntas sobre el seguimiento de JRT

### ¿Qué es JRT (今日通)?

Es 厦门今日通商贸有限公司, Xiamen Jinritong, una empresa de logística de Xiamen, en China. Sus números de envío son JRT, diez cifras y MM, y su web de seguimiento funciona sobre Hualei (华磊), una plataforma que también usan otras empresas de logística chinas.

### ¿Por qué JRT pone «delivered» si mi paquete no ha llegado?

Por cómo traduce sus pasos. `Cargo delivered to airport` quiere decir que JRT entregó el paquete en el aeropuerto para su vuelo, y `Delivered to service provider`, que se lo pasó a la empresa que reparte. Ninguna de las dos es una entrega a ti.

### ¿Quién reparte los paquetes de JRT en España y en Estados Unidos?

En los envíos a Europa, el historial de JRT acaba con DHL, y en los envíos a Estados Unidos, con UPS: la web de JRT copia sus escaneos. Quién entrega en España los paquetes de DHL Paket te lo cuenta la página de [DHL Paket](carrier:dhl). Algunos paquetes pasan a FedEx, como los de la línea de JRT a Irlanda con FedEx en el nombre del producto, a CTT Express, como los de su línea a España, o a InPost, como los de su línea a Polonia. En los demás casos, Peek no lo sabe: pregunta a la tienda qué empresa reparte el tuyo y con qué número.

### ¿A quién reclamo si no me llega un paquete de JRT?

A la tienda donde compraste: fue ella quien contrató el envío con JRT. Aparte de su web de seguimiento, Peek no conoce ninguna web ni datos de contacto de JRT.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nombre de la empresa y sus iniciales, plataforma Hualei, la web de seguimiento en una dirección numérica por HTTP sin cifrar, sus enlaces y ningún enlace publicado, formatos JRT…MM y UP, una O detrás de JRT leída como JRT, búsqueda por número o por referencia con sus guiones, textos de los pasos y sus estados, el inglés de los pasos solo en chino, los pasos solo en chino del final del trayecto a Europa, fechas mes.día, pasos copiados de DHL y la oficina de Deutsche Post, la ruta a Estados Unidos y los pasos copiados de UPS en hora local, el paso de FedEx y sus escaneos copiados, el número de DHL o de FedEx en los datos tras la entrega, el traspaso a DHL y, en la línea a Irlanda, a FedEx, traspaso a Swiss Post, hora de Pekín y hora local de DHL en sus pasos copiados, según la ciudad que nombran, la respuesta a un número desconocido, lo que Peek muestra, aparta y deja fuera, los pasos de DHL con dirección no encontrada, la línea por carretera de Xinjiang a Kazajistán, el vuelo retrasado, los pasos en español de la línea a México, el aviso de CTT Express
- [BOE: Orden PCM/186/2022, calendario de la hora de verano de 2022 a 2026](https://www.boe.es/buscar/doc.php?id=BOE-A-2022-4026) – fin del horario de verano el 25 de octubre de 2026; en Canarias, una hora menos
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 en envíos de hasta 150 €, tasa de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the 3 EUR customs duty and Union handling fee (en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa fija de 2 € por tipo de producto en ventas a distancia, también por encima de 150 €; qué es un tipo de producto
:::
