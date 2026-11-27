---
title: Meiteshun seguimiento: número AMTS, estados y a quién acudir
description: Seguimiento de Meiteshun Logistics (美特顺 / MTSCN): número AMTS…FS, qué significa cada estado, por qué «Delivered» suele ser una previsión y a quién reclamar.
slug: meiteshun-seguimiento
published: 2026-11-01
updated: 2026-11-27
---

Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司, MTSCN) es una empresa de logística de Shenzhen, en China, cuyos envíos llevan un número `AMTS`, nueve cifras y `FS`. Pega tu número AMTS (o un enlace `trackIndex.htm`) en la casilla de arriba para seguirlo; lee el seguimiento con cuidado, porque el sistema de Meiteshun escribe todo el guion del viaje —entrega incluida— al crear el pedido, así que un `Delivered` en su web no demuestra por sí solo que el paquete haya llegado.

## Número de seguimiento de Meiteshun: cómo es

Los números de envío de Meiteshun llevan AMTS, nueve cifras y FS. Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `AMTS000000017FS` | AMTS, nueve cifras y FS: un número de envío de Meiteshun. |
| `AMTS00000001FS` | Solo ocho cifras: no es el formato de Meiteshun. Mira si falta una cifra. |
| `RR482910576CN` | Dos letras, nueve cifras y CN: un número de [China Post](carrier:china-post), no de Meiteshun. |

En Peek da igual que lo pegues en minúsculas o con espacios.

**Dónde encontrarlo.** La tienda contrata el envío con Meiteshun y te pasa el número. Si no lo encuentras, mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

**Una referencia de la tienda.** La web de seguimiento de Meiteshun también encuentra un pedido por la referencia que le pone la tienda, si la escribes tal cual, con sus guiones, y entonces muestra el número AMTS.

**Un enlace con cifras en vez de un nombre.** Esa web no tiene dominio: está en una dirección IP, por HTTP sin cifrar. Un enlace a ella lleva esas cifras donde iría el nombre de la web, además de `trackIndex.htm`, `documentCode=` y tu número o referencia, y puedes pegarlo en Peek tal cual.

## Estados del seguimiento de Meiteshun: qué significa cada uno

Todos los pedidos de Meiteshun siguen el mismo guion, y la mayoría de sus pasos se escriben de antemano (más abajo). Los textos, en el orden del guion:

| Meiteshun escribe | Qué significa para ti |
| --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | El primer paso: la recogida de Meiteshun en Shenzhen. |
| `item inbound in sorting center` | En un centro de clasificación, con el lugar `ShenZhen`. |
| `item outbound in sorting center` | Sale de ese centro de clasificación. |
| `Arriving at the terminal` | En una terminal de aeropuerto, con el lugar `Hong Kong`. |
| `Leaving the terminal, the airline will take off` | El vuelo sale de Hong Kong. |
| `Arriving at the destination country` | Ha llegado a tu país, indicado con su código de dos letras (`ES` en España). |
| `Customs clearanceisin progress` | En la aduana de importación de tu país (la errata es de Meiteshun). |
| `Customs clearance completed` | Despachado en aduana y otra vez en camino. |
| `Arriving at the mail processing center` | En un centro de tratamiento postal de tu país. |
| `Arrive at the sorting and distribution center` | En un centro de clasificación cerca de ti, con tu localidad o tu región. |
| `package is on the way` | El paso anterior a la entrega. No dice que el paquete esté en reparto. |
| `Delivered` | El último paso del guion (más abajo). |

Para un pedido aún sin pasos, la plataforma escribe por su cuenta `货物电子信息已经收到`: «se ha recibido la información electrónica de la mercancía». La tienda ha creado el pedido, pero nadie ha escaneado el paquete; Peek muestra la línea en chino, con el estado «Etiqueta creada».

El lugar de un paso va al principio, antes de una coma, y los pasos en tu país añaden tu localidad o región y el código postal después del texto. Todas las horas van en hora de Pekín, también las de los pasos en España: las 21:00 en la web de Meiteshun son las 14:00, hora peninsular, con el horario de invierno y las 15:00 con el de verano.

**Pasos escritos de antemano.** Meiteshun tiene su propia web de seguimiento en Hualei (华磊), una plataforma que también usan otras empresas de logística chinas, como Pengyou. En la de Meiteshun, todos los pasos de un pedido se escriben al crearlo, cada uno con su fecha prevista, desde la recogida hasta `Delivered`. Son una previsión, no escaneos de tu paquete: dicen dónde debía estar el paquete en cada fecha. Los servicios de seguimiento que copian el registro de Meiteshun muestran los mismos pasos.

## Quién reparte los paquetes de Meiteshun en España

Los pasos de Meiteshun en tu país no dicen qué empresa reparte el paquete. En sus registros, el servicio se llama MTS全球小包 (en chino, pequeño paquete global de MTS). En la web de seguimiento de Meiteshun, un número en la columna 渠道转单号 (número de transferencia), o en 跟踪号码 (número de seguimiento) si no es tu número AMTS, puede ser el de la empresa que reparte: añádelo a Peek como otro paquete.

Peek no ha encontrado plazos de entrega de Meiteshun, ni normas sobre intentos de entrega, puntos de recogida o cuánto tiempo se guarda un paquete. Pregunta a la tienda qué empresa te entrega el pedido y pídele su número de seguimiento: el seguimiento y las normas que cuentan son los de esa empresa. Si te piden pagar impuestos de importación, que en la UE incluyen desde el 1 de noviembre de 2026 una tasa de tramitación de 2 € por tipo de producto, sea cual sea el valor del paquete, mira [paquete retenido en aduanas](guide:customs).

## Paquete de Meiteshun que no llega o seguimiento parado

**`Delivered`, pero no ha llegado nada.** Ese paso puede ser la previsión del guion, no un escaneo. Dile a la tienda que no te ha llegado y pídele el número de seguimiento de la empresa que lo reparte. Más en [paquete entregado pero no recibido](guide:delivered-not-received).

**Todos los pasos a su hora, pero ni rastro del paquete.** Los pasos se escribieron al crear el pedido: muestran el plan, no dónde está el paquete. Guíate por el plazo de entrega que te dio la tienda y pregúntale cuando haya pasado.

**Parado en Shenzhen según Peek.** Si Peek solo muestra los primeros pasos en Shenzhen y un enlace de seguimiento de la tienda muestra más, los pasos que faltan entre los escaneos de Peek se escribieron de antemano o tienen fecha posterior a su consulta (más abajo). Un paso escrito de antemano nunca cuenta como escaneo: no cambia el estado ni la fecha prevista ni genera avisos. Hasta que el paquete se entrega o vuelve al remitente, Peek abre su historial de seguimiento con «Otras webs pueden mostrar más pasos, pero solo son previsiones.» y, bajo «Verlos», lista en gris los pasos que ha apartado, cada uno con «Previsto para» y su día. Busca en la web de Meiteshun la empresa que reparte en tu país y su número de seguimiento; si no aparece, pregunta a la tienda. Más en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating) y [seguimiento de paquetes de China](guide:tracking-from-china).

**No sale nada.** Con un número que no conoce, la web de Meiteshun muestra el número como referencia y todos los demás campos vacíos. Peek toma esa respuesta como un número no encontrado: muestra «Esperando a Meiteshun Logistics» y, dos días después, «Revisa el número de seguimiento». Comprueba el número con la tienda.

**Perdido, dañado o devuelto.** Fue la tienda quien contrató el envío con Meiteshun, así que pídele a ella o al marketplace el reembolso o un nuevo envío. Mira [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## Meiteshun: teléfono y atención al cliente

Para cualquier duda sobre tu pedido, pregunta primero a la tienda, que contrató el envío con Meiteshun, o al marketplace si no responde. Si ninguno te ayuda, la página de 17TRACK sobre Meiteshun da una dirección de correo electrónico, szmeiteshun@163.com, su web, mtswl.com.cn, y una oficina en el distrito de Longgang, en Shenzhen, pero ningún teléfono (consultada el 10 de noviembre de 2026): escribe con tu número AMTS y el nombre de la tienda.

## Seguir un paquete de Meiteshun con Peek

[Peek](/) reconoce un número de Meiteshun por su forma: AMTS, nueve cifras y FS, que no usa ningún otro transportista de su catálogo. Una referencia de la tienda, en cambio, no tiene una forma que Peek pueda distinguir entre los más de 4500 transportistas que sigue: pégala igualmente, pulsa «Cambiar» y elige Meiteshun Logistics (también la encuentras escribiendo 美特顺 o MTSCN), o usa «Cambiar transportista» en un paquete que ya hayas añadido. Después verás también el número AMTS del pedido. Eso sí, Peek quita los guiones, los escribas tú o vengan en un enlace, así que una referencia con guiones tendrás que buscarla en la propia web de Meiteshun.

Peek lee la propia web de seguimiento de Meiteshun y muestra el estado, cada escaneo con su lugar y el país de destino. Los escaneos salen como los escribe Meiteshun, salvo `Delivered`, que aparece como «Entregado». Si el último paso es `Customs clearanceisin progress`, el estado es «En aduanas»; si es `package is on the way`, «En tránsito», no en reparto. De los pasos en tu país, Peek se queda con tu localidad y quita el código postal. Las horas se leen en hora de Pekín y se muestran en la zona horaria de tu dispositivo. Peek descarta los nombres, la dirección, el peso, los datos de pago, la firma y la prueba de entrega que puede llevar el registro de Meiteshun, y no muestra fecha prevista de entrega: Meiteshun no da ninguna.

Peek aparta de los escaneos los pasos escritos más de doce horas antes de la hora que llevan y los que tienen fecha posterior a su consulta, en hora de Pekín. Se guía por los pasos que conserva, no por el estado resumido del registro. Si todo el guion de un pedido se escribió al crearlo, Peek solo conserva los pasos con fecha anterior a ese momento o de hasta seis horas después, así que el `Delivered` escrito entonces se queda fuera. Si Peek tiene que leer la página `trackIndex.htm`, porque la consulta de la web de seguimiento no da ningún paso para el número, deja fuera todos sus `Delivered`: esa página no muestra cuándo se escribió cada paso. Si se quedan todos fuera, el pedido aún no tiene historial en Peek y aparece como «Esperando a Meiteshun Logistics», sin que Peek te pida que revises el número: para Peek, los pasos escritos de antemano significan que Meiteshun ya tiene el pedido, igual que un envío que Meiteshun tiene registrado aunque aún sin escanear. Peek sigue consultando la web de seguimiento de Meiteshun. Tanto si deja fuera algunos pasos como si los deja todos, Peek aparta también las copias de esos pasos que hacen los servicios de seguimiento.

Peek sigue el paquete en la web de Meiteshun. Los registros de Meiteshun nunca nombran la empresa que reparte en destino, así que Peek no pasa el paquete a otra: si la tienda te da el número de la empresa que reparte en España, añádelo como otro paquete.

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek consulta hasta una vez cada 10 minutos, y hasta una vez cada 2 minutos mientras el paquete está «En reparto» (ese día, desde las 6:00, hora local, o, si la empresa da una franja, desde media hora antes de que empiece, y siempre que responda el propio seguimiento de Meiteshun), aunque ningún paso de Meiteshun que Peek conozca da ese estado. Si no, consulta cada hora. A través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día sin novedades y hasta cada 15 minutos el día del reparto, con la misma condición. Puede enviarte notificaciones con «Cada escaneo», «Pasos importantes» o «Solo la entrega». Tras dos días sin novedades, o tras 12 horas si el paquete sigue en «Etiqueta creada» o «Admitido por el transportista», Peek pasa a consultar una vez por hora. No puede contactar con Meiteshun ni con la tienda por ti.

## Preguntas sobre el seguimiento de Meiteshun

### ¿Qué es Meiteshun Logistics (美特顺 / MTSCN)?

Es 深圳市美特顺物流有限公司 (Shenzhen Meiteshun Logistics), una empresa de logística de Shenzhen, en China. Sus números de envío son `AMTS`, nueve cifras y `FS`, y en sus registros el servicio figura como MTS全球小包.

### ¿Un número AMTS…FS pertenece a China Post?

No. `AMTS`, nueve cifras y `FS` es el formato de Meiteshun. Los números internacionales de China Post tienen dos letras, nueve cifras y `CN`.

### ¿Por qué el seguimiento de Meiteshun pone «Delivered» si no me ha llegado?

Seguramente porque el sistema de Meiteshun (sobre la plataforma Hualei) escribe todos los pasos de un pedido, entrega incluida, al crearlo. Un `Delivered` escrito así es una previsión, no un escaneo real: avisa a la tienda de que el paquete no ha llegado.

### ¿Qué empresa reparte los paquetes de Meiteshun en España?

Los pasos de Meiteshun no nombran ninguna. Su web de seguimiento puede mostrar el número de la empresa local en la columna 渠道转单号 (número de transferencia); si no aparece, pregunta a la tienda qué transportista entrega tu pedido y pídele ese número.

### ¿Cómo contactar o reclamar si no llega un paquete de Meiteshun?

Reclama primero a la tienda o al marketplace, que contrató el envío con Meiteshun. Si no responden, la ficha de 17TRACK sobre MTSCN indica el correo szmeiteshun@163.com y una oficina en el distrito de Longgang (Shenzhen), sin teléfono.

:::sources
- [17TRACK: MTSCN tracking (en inglés)](https://www.17track.net/en/carriers/mtscn) – correo electrónico, web y oficina en Shenzhen de Meiteshun, sin teléfono (consultada el 10 de noviembre de 2026)
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tasa de tramitación de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por línea de la declaración (un tipo de producto), como el arancel de 3 €, sea cual sea el valor
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nombre de la empresa, plataforma Hualei, formato AMTS, la web de seguimiento y sus enlaces, búsqueda por referencia, el guion y sus textos, la línea en chino de la plataforma, lugares y hora de Pekín, pasos escritos de antemano, la respuesta a un número desconocido, nombre del servicio, ninguna empresa de reparto en los pasos, las columnas de número de transferencia y de seguimiento, lo que Peek muestra, aparta y deja fuera
:::
