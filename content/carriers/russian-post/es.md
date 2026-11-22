---
title: Seguimiento Russian Post (Почта России): números y estados
description: Sigue un paquete de Russian Post (Почта России): formatos RU y de 14 cifras, significado de los estados en ruso y cómo lo sigue Peek.
slug: seguimiento-russian-post
published: 2026-11-22
updated: 2026-11-22
---

Russian Post (Почта России, Pochta Rossii) es el servicio postal de Rusia. Transporta paquetes enviados desde Rusia y paquetes enviados hacia Rusia. El remitente o la tienda te da el número de seguimiento: pégalo en la casilla de arriba. La web propia de Russian Post es pochta.ru, y Peek consulta su seguimiento por ti como se explica más abajo.

## Número de seguimiento de Russian Post: cómo es

Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `RR482910576RU` | Dos letras, nueve cifras y `RU`: el formato postal internacional, terminado en el código de Rusia y con un dígito de control en la novena cifra. Cuando empieza por C, E, L o R, Peek reconoce Russian Post al instante. |
| `VV482910576RU` | Otro número internacional acabado en `RU` cuya primera letra no es C, E, L ni R. Peek lo muestra como «Unknown postal carrier» (servicio postal desconocido); puedes elegir Russian Post manualmente. |
| `80482910573625` | 14 cifras que empiezan por `80`, con la última como dígito de control. Peek lo asocia a Russian Post solo como coincidencia débil, porque DPD, SEUR, BRT, Delhivery y otros transportistas también usan números así. |
| `80482910573620` | La misma forma con el dígito de control incorrecto. Peek no sugiere Russian Post para él, solo los otros transportistas: si el tuyo es de Russian Post, comprueba si hay una errata. |
| `RR482910576CN` | Un número emitido por otro correo, aquí [China Post](carrier:china-post). Peek lo sigue con ese correo, también cuando viaja hacia Rusia. |

Los espacios y las minúsculas no importan al pegar un número en Peek. Si no encuentras el tuyo, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados del seguimiento de Russian Post explicados

Russian Post escribe sus pasos en ruso. Peek los lee tal como aparecen en la tabla de abajo en el seguimiento de Qwintry, un reexpedidor que entrega parte de sus paquetes a Russian Post: para un paquete añadido con su número de Qwintry, o añadido con su número RU y asignado a Qwintry (mira «Paquetes de Qwintry» más abajo). Allí, cada paso de Russian Post tiene una operación, como `обработка` (procesamiento), una línea de detalle y la oficina postal al final. Si no hay línea de detalle, la propia operación figura como paso. Más o menos en el orden en que los recibe un paquete:

| Russian Post indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Партионный` | Russian Post ha recibido el paquete dentro de un lote con otros (operación `приём`, admisión). | «En tránsito» |
| `экспорт международной почты` | Exportación de correo internacional: el paquete sale del país en el que está. | «En tránsito» |
| `Прибыло на территорию РФ` | El paquete ha llegado a Rusia. | «En tránsito» |
| `импорт международной почты` | Importación de correo internacional: el paquete entra en la red de Rusia. | «En tránsito» |
| `приём на таможню` | Entregado a aduanas. | «En aduanas» |
| `Выпущено таможней` | Liberado por aduanas: vuelve a estar en camino. | «En tránsito» |
| `Покинуло место международного обмена` | Ha salido de la oficina que gestiona el correo del extranjero. | «En tránsito» |
| `Сортировка` | Clasificación. | «En tránsito» |
| `Прибыло в сортировочный центр` | Ha llegado a un centro de clasificación. | «En tránsito» |
| `Покинуло сортировочный центр` | Ha salido de un centro de clasificación. | «En tránsito» |
| `Прибыло в место вручения` | Ha llegado a la oficina postal que lo entregará o donde podrás recogerlo. | «En tránsito» |
| `Передано почтальону` | Entregado al cartero para su reparto. | «En reparto» |
| `Временное отсутствие адресата` | No entregado: no estabas en casa. | «Intento de entrega» |
| `Адресат заберет отправление сам` | No entregado porque pasarás a recoger el paquete tú mismo. | «Intento de entrega» |
| `Вручение адресату` | Entregado a ti, el destinatario. | «Entregado» |

Para cualquier otro paquete de Russian Post, Peek consulta el seguimiento propio de Russian Post, que responde en inglés: mira «Seguir un paquete de Russian Post con Peek» más abajo.

## Cómo entrega Russian Post

Los pasos anteriores muestran el recorrido dentro de Rusia: centros de clasificación y después la oficina postal que entrega el paquete. Desde allí puede llevarlo un cartero (`Передано почтальону`). Si no estabas o vas a recogerlo tú, el paso lo indica. En el seguimiento de Qwintry, Peek muestra la localidad de la oficina postal como lugar del paso. Si usas Peek en ruso o si no logra ubicar la oficina, muestra el nombre de la oficina tal como lo escribe Russian Post para que sepas cuál tiene tu paquete.

Esta página no indica plazos de entrega, días de espera ni reglas de intentos de entrega porque Peek no pudo comprobarlos en la web de Russian Post. Si un paquete espera en una oficina, mira [paquetes no recogidos](guide:uncollected-parcel) y [entregas fallidas](guide:missed-delivery).

**Enviado desde Rusia a otro país.** Peek no sabe de antemano qué empresa entrega un paquete de Russian Post en tu país. En un número RU, consulta también al correo del país que el seguimiento de Russian Post señala como destino y puede seguir el paquete allí (mira más abajo).

**Enviado a Rusia desde otro país.** Peek sigue el paquete con el transportista que emitió el número, usando su propio seguimiento o servicios de seguimiento. Si ese seguimiento nombra a Russian Post como empresa encargada de la entrega, Peek consulta también el seguimiento de Russian Post y pasa el paquete allí cuando muestra un escaneo al menos tan reciente como el último conocido. Que vaya rumbo a Rusia no basta por sí solo para moverlo.

## Paquete de Russian Post que no llega o seguimiento detenido

**Aún sin pasos.** Puede que el seguimiento de Russian Post o los servicios de seguimiento todavía no tengan el número, o que el remitente aún no haya entregado el paquete. Peek sigue consultando. Si tras varios días no aparece nada, confirma el número con el remitente. Más en [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Retenido en aduanas.** Si Peek muestra «En aduanas», el paquete está en la aduana. Esta página no incluye normas ni tasas aduaneras porque no se pudieron comprobar en una página oficial. Mira [paquetes retenidos en aduanas](guide:customs).

**Peek va con retraso.** Cuando el seguimiento de Russian Post no responde a Peek, un servicio de seguimiento cubre el hueco y puede ir por detrás de la web oficial. Compáralo con pochta.ru antes de preocuparte.

**Entregado, pero no ha llegado nada.** Si Peek indica «Entregado» y no tienes el paquete, pregunta en tu oficina postal y avisa al remitente. Más en [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Contacta primero con el remitente o la tienda: ellos contrataron el envío. Mira [paquetes perdidos](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Russian Post

Como Peek no ha podido leer la web de Russian Post, esta página no recoge teléfono ni correo electrónico: solo publicamos contactos comprobados en las páginas del propio transportista. Las páginas de ayuda de Russian Post están en info.pochta.ru. Si un paquete deja de moverse, Peek enlaza la página de ayuda sobre seguimiento de Russian Post como «Pide ayuda a Russian Post».

En un pedido de tienda, pregunta primero a la tienda: es quien contrató la entrega.

## Seguir un paquete de Russian Post con Peek

Pega el número en [Peek](/): Peek sigue más de 4500 transportistas. Según la forma del número:

- **Número internacional en `RU` que empieza por C, E, L o R (con dígito de control válido).** Peek detecta Russian Post directamente («Transportista detectado»).
- **Otro número internacional en `RU` (otra letra inicial).** Peek lo muestra como «Unknown postal carrier». Pulsa «Cambiar» (o más tarde «Cambiar transportista») y elige Russian Post: escribiendo Почта России también aparece. Peek mostrará entonces Russian Post.
- **14 cifras que empiezan por `80` (con dígito de control válido).** Mientras lo añades, Peek muestra «Comprobando servicios de seguimiento…» y pregunta al seguimiento de Russian Post, junto con DPD y otros transportistas compatibles, si tienen el paquete. Si solo Russian Post lo tiene, Peek indica «Russian Post tiene este paquete.» y lo guarda ahí. Si no, pulsa «Cambiar»: Russian Post sale en «Encaja con este número», junto a los demás transportistas compatibles, empezando por los de tu país. En un paquete ya añadido, usa «Cambiar transportista».

Peek lee el seguimiento propio de Russian Post, que le responde en inglés. Muestra cada paso con los textos en inglés de Russian Post, como `Sorting`, `Arrived at the local distribution center` o `Forwarded to the postman` («En reparto»), junto con su hora, ciudad, país y país de destino. `Available for pickup at the Post Office` aparece como «Listo para recoger» y `Handed over for temporary storage` como «Incidencia». Tras `Returned to the sender` (también «Incidencia»), el paquete vuelve al remitente y su entrega figura luego como «Devuelto al remitente». Russian Post no ofrece fecha prevista de entrega. Hasta que su seguimiento conozca el número, Peek muestra «Esperando a Russian Post», o «Esperando el primer escaneo de Russian Post» si añadiste un número `80` sin transportista. Cuando el seguimiento de Russian Post no responde a Peek, Peek consulta servicios de seguimiento —ParcelsApp, Ship24 y 17TRACK por ese orden, y para un número RU el seguimiento de la Unión Postal Universal al final— y muestra el historial del primero que tenga el paquete, con sus propias palabras, que pueden ir por detrás de pochta.ru. Un servicio que no conocía el número vuelve a consultarse al día siguiente.

Para un número `80` añadido sin transportista, o un número RU mostrado como «Unknown postal carrier», Peek consulta también durante los primeros 30 días, antes que a los servicios de seguimiento, a los transportistas que puedan tenerlo (como Russian Post, DPD y otros para un número `80`). Si uno muestra movimiento, Peek mueve el paquete allí. Si añadiste un número `80` sin transportista y un servicio de seguimiento nombra solo a Russian Post con pasos fechados, Peek lo archiva automáticamente bajo Russian Post. En un número RU mostrado como «Unknown postal carrier», que un servicio nombre a Russian Post no basta: hace falta que el seguimiento de Russian Post lo muestre o que elijas Russian Post tú mismo.

Peek no enlaza directamente al seguimiento de Russian Post. Si respondió un servicio de seguimiento, el enlace de la página del paquete abre ese servicio, por ejemplo «Abrir la web de ParcelsApp». Para pochta.ru, ábrelo tú mismo.

**Paquetes de Qwintry.** Si Qwintry envió tu paquete a Rusia, añade mejor su número de Qwintry: `QR` y ocho cifras. Peek reconoce Qwintry al instante y lee su seguimiento, que sigue mostrando los pasos de Russian Post cuando el correo recibe el paquete. Cuando Qwintry nombra a Russian Post, el paquete muestra «Entrega con Russian Post» y el número RU del correo, y Peek consulta también a Russian Post: en cuanto este muestre un escaneo al menos tan reciente como el último de Qwintry, Peek sigue el paquete ahí conservando los pasos previos de Qwintry. Mientras Russian Post solo tenga el aviso previo sin escaneo propio, aparece «Anunciado a Russian Post, aún no recibido». El seguimiento de Qwintry también acepta el número RU de un paquete entregado por ellos al correo: si solo tienes ese número y Russian Post no lo muestra, pulsa «Cambiar» y elige Qwintry.

**El correo de tu país.** En un número RU asignado a Russian Post, el seguimiento de Russian Post indica el país de destino y Peek consulta también al correo de ese país con el número RU si lee directamente su seguimiento, como ocurre con [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, Canada Post y Australia Post. En un paquete mostrado como «Unknown postal carrier», basta con que un servicio de seguimiento indique el correo que lo entrega con su número, o el país de destino si es un número RU: Peek consulta a ese correo del mismo modo y continúa allí el seguimiento en cuanto muestre un escaneo al menos tan reciente como el último registrado.

Mientras tienes el paquete abierto en pantalla, o el día de la entrega con las notificaciones activadas, Peek consulta el seguimiento de Russian Post hasta cada 10 minutos —y hasta cada 2 minutos a partir de las 6:00 (hora local) el día en que sale a reparto— mientras responda su propio seguimiento; a través de servicios de seguimiento, lo hace cada hora, cada 3 horas tras un día sin escaneos nuevos y hasta cada 15 minutos el día en que sale a reparto. El resto del tiempo, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta una vez por hora. Puedes recibir alertas de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Russian Post ni con el remitente por ti.

## Preguntas sobre el seguimiento de Russian Post

### ¿Qué significa Почта России en español?

Correos de Rusia, conocido internacionalmente como Russian Post o Pochta Rossii. Su web oficial es pochta.ru.

### ¿Cómo es un número de seguimiento de Russian Post?

Uno internacional tiene dos letras, nueve cifras y `RU`, como `RR482910576RU`. Peek también asocia a Russian Post los números de 14 cifras que empiezan por `80` cuando su dígito de control es correcto.

### ¿Por qué los pasos en Peek no coinciden con pochta.ru?

Peek consulta el seguimiento de Russian Post en inglés, por lo que en la mayoría de los pasos muestra los textos en inglés de Russian Post en lugar de los rusos. Cuando ese seguimiento no responde, entra un servicio de seguimiento con sus propios textos, que puede ir retrasado. Peek muestra los pasos en ruso de la tabla superior en paquetes seguidos mediante Qwintry.

### ¿Puede Peek seguir un paquete enviado a Rusia desde otro país?

Sí. Pega el número que te dio el remitente: Peek lo sigue bajo el transportista que emitió el número y solo lo pasa a Russian Post si ese seguimiento señala a Russian Post como empresa de entrega.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – nombres, formatos `80` y `RU` con dígitos de control y coincidencias, seguimiento leído por Peek y textos en inglés, lugares, destino, etapas y página de ayuda
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – pasos de Russian Post en el seguimiento de Qwintry y sus etapas, oficina como lugar, número `QR` y números `RU` aceptados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números postales `RU`, transportistas consultados primero, orden de servicios de seguimiento, traspasos al correo de tu país y a Russian Post
:::
