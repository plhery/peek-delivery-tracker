---
title: Seguimiento Xinghe Freight: número GZXH…YQ y estados
description: Rastrea tu paquete de Xinghe Freight (星和货运) desde Guangzhou: cómo es el número GZXH…YQ, en qué web se sigue, qué significan sus pasos y a quién reclamar.
slug: xinghe-freight-seguimiento
published: 2026-11-18
updated: 2026-11-20
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) es un transitario de Guangzhou (Cantón), en China. Si una tienda china ha enviado tu pedido con Xinghe, busca un número formado por GZXH, diez cifras y YQ —el formato que Peek reconoce como propio de Xinghe— y pégalo en la casilla de arriba. El seguimiento de Xinghe funciona sobre la plataforma de [GZYL](carrier:gzyl), otro transitario de Guangzhou: el buscador de la web de Xinghe envía allí tu número. Ese sistema está alojado en una dirección numérica de servidor, por HTTP sin cifrar. Peek lo consulta por ti y recurre a servicios de seguimiento cuando ese servidor no conoce tu número.

## Número de seguimiento de Xinghe Freight: cómo es

Los números que Peek asocia a Xinghe Freight tienen GZXH, diez cifras y YQ: 16 caracteres en total. Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `GZXH4829105736YQ` | GZXH, diez cifras y YQ: el formato de Xinghe Freight. Peek identifica a Xinghe Freight al instante. |
| `GZXH482910573YQ` | Solo nueve cifras: no es el formato de Xinghe. Comprueba si falta algún dígito. |
| `YL4829105736YQ` | YL, diez cifras y YQ: el formato de las cartas de porte de [GZYL](carrier:gzyl). Usa el mismo sistema de seguimiento, pero es otro transitario. |
| `JXCAA4829105736YQ` | También acaba en YQ, como los números de muchos transitarios chinos, pero empieza por JXC: es un número de Juxi Express, no de Xinghe. |

Los espacios, los guiones y las minúsculas no importan al pegar el número en Peek.

**Dónde encontrarlo.** La tienda contrata el envío con Xinghe y te facilita el número. Si no das con él, mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

## Estados del seguimiento de Xinghe Freight: qué significan

Como el seguimiento de Xinghe corre en el sistema de GZYL, sus pasos aparecen con los textos en inglés de GZYL, erratas incluidas. Peek los interpreta con la lista de pasos de GZYL. En el orden en que los recibe un envío:

| Lo que dice la web de seguimiento | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order information received` | La tienda ha enviado los datos del pedido. Nadie tiene todavía el paquete. | «Etiqueta creada» |
| `Arrive at the cross border receiving point` | El transitario ya tiene el paquete: es el primer escaneo en sus manos. | «Admitido por el transportista» |
| `Goods leave the operation center` | El paquete ha salido del centro de operaciones. | «En tránsito» |
| `Arrive at the international airport` | En el aeropuerto, esperando vuelo. | «En tránsito» |
| `Departing from the airport` | El vuelo ha despegado. | «En tránsito» |
| `Item arrives at destination airport` | Ha aterrizado en el país de destino. | «En tránsito» |
| `local carrier Agent Import customs clearance` | Un agente en tu país está tramitando el despacho aduanero de importación. | «En aduanas» |
| `The import customs keep it for inspection` | La aduana de tu país retiene el paquete para revisarlo. | «En aduanas» |
| `Customs clearance completed` | Despacho aduanero terminado; el paquete vuelve a estar en camino. | «En tránsito» |
| `Internationaliy Package received at processing center` | Un centro de tratamiento en tu país ha recibido el paquete ya despachado. No es una nueva recogida en origen. | «En tránsito» |
| `Leave the email processing center` | El paquete ha salido de ese centro de tratamiento. | «En tránsito» |
| `Arrival sorting, distribution center` | En un centro de clasificación y distribución de tu país, seguido de la localidad. | «En tránsito» |
| `Leaving the sorting center` | Sale de un centro de clasificación, con su localidad. | «En tránsito» |
| `Package is on the way` | Sigue avanzando en tu país, con una localidad. No significa que esté ya en reparto. | «En tránsito» |
| `Delivered` | La entrega, con la localidad. Puede ser un plan escrito de antemano y no un escaneo real (más abajo). | «Entregado», con un aviso sobre el calendario fijo, salvo que Peek aparte el paso (abajo) |

**Paso por Hong Kong.** Un paquete también puede mostrar `香港处理中心已收寄【Hong Kong】`: una admisión postal en el centro de tratamiento de Hong Kong, que el sistema transmite en chino con la ciudad entre corchetes. Peek muestra la línea tal cual bajo «Admitido por el transportista».

**Una línea en chino.** En un pedido que aún no tiene escaneos, la página de seguimiento puede mostrar `货物电子信息已经收到`, que significa que se ha recibido la información electrónica de la mercancía. La plataforma escribe esta línea por su cuenta: el vendedor ha creado el pedido, pero nadie ha escaneado el paquete. Peek la muestra en chino bajo «Etiqueta creada».

**Horas.** Peek lee todas las horas del sistema como hora de Pekín (UTC+8), incluidos los pasos en tu país, y las convierte a la zona horaria de tu dispositivo. Una etapa a las 20:00 en Pekín equivale a las 13:00 en la España peninsular y Baleares en invierno (12:00 en Canarias) y a las 14:00 con el horario de verano, del último domingo de marzo al último domingo de octubre (13:00 en Canarias).

**Pasos escritos por adelantado.** En esta plataforma de seguimiento, los transitarios pueden dejar escrita de antemano toda la cronología de un envío, desde la admisión hasta el último paso en tu país, y la web va mostrando cada etapa cuando llega su hora. Un paso así es una previsión, no un escaneo físico. En el sistema de GZYL, esa previsión termina además en un `Delivered` que la plataforma añade en un lote nocturno el día indicado: lleva fecha del día siguiente al último paso previsto, en horario de oficina de Pekín, sea cual sea tu país. Peek aparta estos pasos programados (ver abajo), pero la copia de un servicio de seguimiento puede seguir mostrándolos, de modo que ver `Delivered` ahí no demuestra que el paquete haya llegado.

## Quién entrega los paquetes de Xinghe Freight

Peek no sabe qué empresa reparte los paquetes de Xinghe en España ni en Latinoamérica: ni los pasos del sistema en destino ni la web de Xinghe mencionan al transportista final. La única empresa de reparto a la que Peek puede traspasar un envío de Xinghe es [Swiss Post](carrier:swiss-post), en Suiza y Liechtenstein, cuando la ficha del envío la nombra.

La web de Xinghe tampoco ofrece información sobre paquetería para compradores particulares: no detalla plazos de entrega, intentos de reparto ni puntos de recogida. Esas normas dependen de la empresa que entrega en destino. Pregunta al vendedor qué transportista reparte tu pedido y cuál es su número de seguimiento.

**Aduanas y cargos en la UE.** Los envíos procedentes de China llegan desde fuera del territorio aduanero de la Unión Europea. Según la Comisión Europea, desde el 1 de julio de 2026 las compras en línea procedentes de fuera del territorio aduanero de la UE en paquetes de hasta 150 € pagan un arancel de 3 € por categoría de producto (en España, Canarias forma parte del territorio aduanero de la UE, mientras que Ceuta y Melilla quedan fuera tanto del territorio aduanero como del ámbito del IVA comunitario). Desde el 1 de noviembre de 2026 se suma además una tasa de tramitación de la Unión de 2 € por categoría de producto, sea cual sea el valor del envío; una categoría agrupa las mercancías de un paquete que comparten clasificación arancelaria, descripción y, cuando deba declararse, origen, sin importar el número de unidades. Para ver quién cobra el IVA y los gastos en destino, consulta [paquete retenido en aduanas](guide:customs).

## Paquete de Xinghe Freight que no llega o seguimiento parado

**Solo `Order information received` o `货物电子信息已经收到`.** El vendedor ha generado el pedido, pero nadie ha escaneado todavía el paquete. Si tras varios días no aparece nada más, pregunta a la tienda si realmente llegó a enviarlo.

**«Esperando a Xinghe Freight».** Peek aún no tiene ningún paso confirmado para tu número: o bien el sistema de GZYL no lo conoce, o solo guarda pasos escritos por adelantado (que pueden terminar en `Delivered`), y los servicios de seguimiento consultados tampoco tienen datos. Peek sigue comprobando: en la app de iPhone o Android, o en tu cuenta, interroga al sistema de GZYL cada 6 horas tras las primeras horas. En una consulta sin cuenta, pregunta durante las primeras 6 horas, luego cuando abres su enlace (como máximo cada 6 horas, y como máximo una vez al día pasados los dos primeros días) y, mientras tengas activadas las notificaciones, una vez al día hasta 30 días después de la consulta. Cuando el sistema de GZYL no conoce un número, devuelve un único resultado con ese número como referencia y los demás campos vacíos; Peek interpreta tanto esa respuesta como un plan escrito por adelantado como un envío registrado sin escaneo y mantiene «Esperando a Xinghe Freight», sin pedirte que revises el número. Compáralo tú mismo con el mensaje del vendedor.

**Retenido en aduanas.** Si te piden abonar cargos de importación, revisa [paquete retenido en aduanas](guide:customs). Si lleva muchos días parado en el despacho aduanero, avisa al vendedor.

**La web de seguimiento o un servicio externo muestra más pasos que Peek.** Esos pasos adicionales, `Delivered` incluido, pueden formar parte del plan escrito de antemano que Peek omite. Guíate por la fecha estimada de entrega que te dio la tienda y reclama al vendedor cuando haya vencido. Más información en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating) y [seguimiento de paquetes de China](guide:tracking-from-china).

**`Delivered`, pero no ha llegado nada.** Puede tratarse de una etapa programada de antemano. Salvo cuando entrega Swiss Post, Peek advierte bajo «Entregado»: «Xinghe Freight actualiza según un calendario fijo, sin firma. Si no ha llegado nada, contacta con el vendedor.» Comunica a la tienda que no has recibido el paquete y pídele el nombre de la empresa de reparto y su número de seguimiento. Consulta también [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Como fue el vendedor quien contrató el envío con Xinghe, reclama a la tienda o al marketplace el reembolso o un nuevo envío. Mira [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## Xinghe Freight: teléfono y atención al cliente

La página web de Xinghe (`xinghe-express.cn`), disponible únicamente en chino, publica estos datos:

- **Teléfono de atención**: `19924483103`, anunciado como operativo las 24 horas todos los días (`+86 199 2448 3103` si llamas desde el extranjero).
- **Correo electrónico**: `3975605292@qq.com`, destinado a consultas comerciales y solicitudes de presupuesto.
- **Dirección**: Guangzhou, provincia de Guangdong (`广东省广州市`), sin calle ni número; la web pide contactar antes de acudir.

La web no ofrece atención para destinatarios particulares en otros países, y su formulario de contacto simplemente abre tu gestor de correo hacia esa dirección. El cliente de Xinghe es la tienda: ante cualquier incidencia con tu pedido, acude primero al vendedor y, si no responde, al marketplace.

## Seguir un paquete de Xinghe Freight con Peek

[Peek](/) reconoce al instante a Xinghe Freight ante un número GZXH…YQ, mostrando «Transportista detectado». Si el vendedor te dio un número con otro formato, pégalo igualmente: Peek sigue más de 3500 transportistas. Para asignar manualmente un envío a Xinghe, pulsa «Cambiar» y busca Xinghe Freight (también aparece escribiendo 星和货运), o usa «Cambiar transportista» en un paquete ya guardado.

Peek consulta el sistema de GZYL, igual que hace el cuadro de búsqueda de la web de Xinghe. Muestra el estado, cada paso con su localidad y, cuando consta en el registro, el país de destino. Lee las horas en horario de Pekín y las presenta en la zona horaria de tu dispositivo; en peektracker.com y en las apps de iPhone y Android, el historial termina con «Horas en tu zona horaria:» seguido del nombre de tu zona. Peek descarta nombres, dirección, códigos postales, peso, datos de pago, firma y prueba de entrega que pueda traer la ficha, y no muestra fecha estimada de entrega porque el sistema no da ninguna.

Cuando el sistema de GZYL no conoce tu número, Peek pregunta a servicios de seguimiento y muestra su copia de los pasos, que a veces es todo lo que existe para un número GZXH. Mientras ninguno tenga pasos confirmados, el envío muestra «Esperando a Xinghe Freight».

Peek aparta cualquier paso escrito más de 12 horas antes de la hora que figura en él, o más de 6 horas antes en el mismo segundo que otro paso así, además de cualquier paso con fecha posterior al momento de la consulta, en hora de Pekín. En un registro con etapas escritas de antemano, aparta también `Delivered`, incluso si se escribió el mismo día. Calcula el estado a partir de los pasos que conserva, no del resumen del registro, y descarta igualmente en las copias de los servicios de seguimiento las etapas que ya ha identificado como escritas por adelantado, `Delivered` incluido. Ahora bien, si el sistema no tiene rastro del número o no responde, Peek no puede distinguir un paso previsto de un escaneo real dentro de la copia de un servicio de seguimiento. Cuando aparta etapas escritas por adelantado, Peek lo avisa bajo el estado hasta que el paquete se entrega o se devuelve: «La web de Xinghe Freight muestra etapas escritas por adelantado, incluso la entrega. Peek no puede confirmarlas, así que las omite. Busca en esa web la empresa que reparte en tu país y su número de seguimiento; si no aparece, pregunta al vendedor.» Un paso así nunca cuenta como escaneo: no cambia el estado ni envía alertas.

Dejando aparte a Swiss Post, Peek no traspasa un paquete de Xinghe al transportista que hace la entrega final: si la tienda te facilita ese número, añádelo como un paquete independiente. Peek comprueba hasta cada 10 minutos mientras tienes el paquete abierto o el día del reparto con las notificaciones activadas, y cada hora el resto del tiempo o cuando el envío deja de dar novedades (tras 12 horas en «Etiqueta creada» o «Admitido por el transportista», o dos días sin cambios); a través de servicios de seguimiento comprueba cada hora, cada 3 horas tras un día sin escaneos, y hasta cada 15 minutos el día de la entrega mientras tengas el paquete abierto o las notificaciones activadas. Puedes activar avisos con «Cada escaneo», «Pasos importantes» o «Solo la entrega»: «Pasos importantes» avisa de los dos pasos de aduanas y de `Delivered`, pero no de los vuelos ni de la clasificación. Peek no puede contactar con Xinghe Freight, con GZYL ni con el vendedor por ti.

## Preguntas sobre el seguimiento de Xinghe Freight

### ¿Qué es Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), un transitario de Guangzhou, en China. Su web indica que se fundó en 2014 y que gestiona transporte marítimo, aéreo y terrestre, consignación de buques y trámites aduaneros. Los números que Peek archiva bajo esta empresa tienen GZXH, diez cifras y YQ.

### ¿Por qué mi paquete de Xinghe Freight muestra el seguimiento de GZYL?

Porque el seguimiento de Xinghe funciona sobre el sistema de GZYL: el buscador de la propia web de Xinghe envía allí tu número y los pasos aparecen con las frases de GZYL. Peek mantiene el envío bajo Xinghe Freight y lee ese sistema por ti.

### ¿Quién reparte los paquetes de Xinghe Freight en mi país?

Peek no lo sabe: ni los pasos del seguimiento ni la web de Xinghe indican una empresa de reparto. Solo en Suiza y Liechtenstein puede Peek pasar un paquete de Xinghe a Swiss Post. Pregunta al vendedor qué transportista entrega tu pedido y pídele su número de seguimiento.

### ¿Por qué Peek aún no muestra seguimiento para mi número GZXH?

Puede que el sistema todavía no tenga registrado el pedido o que solo contenga etapas escritas por adelantado (incluido `Delivered`), que Peek deja fuera. Peek consulta también servicios de seguimiento y sigue comprobando al ritmo explicado más arriba bajo «Esperando a Xinghe Freight». Como en ese caso no pide revisar el número, compáralo tú con el mensaje de la tienda; si pasan varios días sin novedades, pregunta al vendedor si el paquete salió de verdad.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (inicio, en chino)](http://xinghe-express.cn/) – nombre de la empresa, servicios de transporte internacional, buscador de envíos, teléfono y correo
- [Xinghe Freight: 关于我们 (quiénes somos, en chino)](http://xinghe-express.cn/about.html) – fundación en 2014, transporte marítimo, aéreo y terrestre, agencia marítima y despacho aduanero
- [Xinghe Freight: 产品服务 (servicios, en chino)](http://xinghe-express.cn/services.html) – catálogo de servicios sin mención a paquetería de comercio electrónico para particulares
- [Xinghe Freight: 联系我们 (contacto, en chino)](http://xinghe-express.cn/contact.html) – línea 24 horas los 7 días, correo para consultas comerciales y presupuestos, ubicación en Guangzhou con aviso previo y formulario que abre el cliente de correo
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por categoría de producto en envíos de hasta 150 € desde el 1 de julio de 2026 y tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de tramitación de 2 € por categoría de producto cualquiera que sea el valor y definición de categoría de producto
- [Comisión Europea: Territorial Scope (en inglés)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias dentro del territorio aduanero de la UE; Ceuta y Melilla fuera del territorio aduanero y del ámbito del IVA
- [Comisión Europea: Summertime (en inglés)](https://transport.ec.europa.eu/transport-themes/summertime_en) – horario de verano europeo del último domingo de marzo al último domingo de octubre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nombres y alias, formato GZXH…YQ, consulta en el sistema de GZYL, textos y fases de los pasos, admisión en Hong Kong, línea en chino de la plataforma, hora de Pekín, cronologías escritas de antemano, respaldo en servicios de seguimiento, respuesta ante un número desconocido, traspaso a Swiss Post y qué muestra, aparta y descarta Peek
:::
