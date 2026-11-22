---
title: Seguimiento CDEK (СДЭК): números y estados en español
description: Sigue un paquete de CDEK (СДЭК): cómo es su número de pedido, significado en español de sus estados en ruso, entrega por mensajero o punto de recogida.
slug: seguimiento-cdek
published: 2026-11-22
updated: 2026-11-22
---

CDEK (СДЭК, escrito a veces SDEK) es una empresa de mensajería incluida en el catálogo de Peek para Rusia, Kazajistán, Bielorrusia y Serbia. Entrega paquetes a domicilio mediante mensajero, o en puntos de recogida y taquillas. El vendedor o remitente te facilita el número: pégalo en el recuadro de arriba. El rastreador propio de CDEK está en my.cdek.ru. CDEK escribe sus pasos en ruso y la tabla siguiente los traduce al español.

## Número de seguimiento de CDEK: cómo es

CDEK llama pedido (заказ) a cada envío, así que su código es un número de pedido CDEK (distinto al número de pedido de la tienda). Su buscador acepta códigos de 5 a 40 letras y dígitos: sus propios números de 10 u 11 dígitos y otras referencias vinculadas, como números postales internacionales. Peek asocia una única forma a CDEK (dígitos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `10482917365` | 11 dígitos que empiezan por `10`: la única forma que Peek vincula a CDEK, y solo como coincidencia débil. [GLS Alemania](carrier:gls-de), GLS Suiza, GLS Francia, GLS Austria, GLS Polonia, PostLogistics, Coordinadora y Mainfreight también usan 11 dígitos. |
| `48291736548` | 11 dígitos que no empiezan por `10`: Peek no sugiere CDEK; revisa el número o elige CDEK manualmente (abajo). |
| `RR482917367CN` | Un código postal extranjero, aquí de [China Post](carrier:china-post). Peek lo sigue con ese correo; si CDEK lo ha vinculado a un pedido, elige CDEK para ver sus pasos. |

¿No encuentras el tuyo? Consulta [dónde encontrar tu número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de CDEK en español

Cada paso de CDEK tiene un código (en el que se basa Peek) y texto en ruso, que Peek muestra tal como lo redacta CDEK. CDEK agrupa sus escaneos bajo hitos amplios como `В пути`: Peek muestra un hito amplio solo si no contiene escaneos propios. Por orden aproximado:

| Qué dice CDEK | Qué significa para ti | Qué muestra Peek |
| --- | --- | --- |
| `Создан` | Creado: el pedido existe en CDEK, pero puede que aún no tenga el paquete. | «Etiqueta creada» |
| `Назначен курьер` | Un mensajero va a recoger el pedido al remitente. | «Etiqueta creada» |
| `Курьер забрал заказ у отправителя` | El mensajero recogió el paquete al remitente. | «Admitido por el transportista» |
| `Принят на доставку` | Admitido para entrega: CDEK ya tiene el paquete. | «Admitido por el transportista» |
| `В пути` | En camino: hito general entre la admisión y el tramo final. | «En tránsito» |
| `Принят СДЭК на территории РФ` | Un paquete extranjero entró en la red de CDEK en Rusia. | «En tránsito» |
| `Отправлен в сортировочный центр` | Enviado a un centro de clasificación. | «En tránsito» |
| `Прибыл в сортировочный центр` | Llegó a un centro de clasificación. | «En tránsito» |
| `Отправлено в г.` + ciudad | Enviado hacia esa ciudad. | «En tránsito» |
| `Отправлен в пункт доставки` | Enviado al depósito desde donde salen los mensajeros. | «En tránsito» |
| `Прибыл в пункт доставки` | Llegó al depósito de reparto. | «En tránsito» |
| `Отправлен в пункт выдачи` | Enviado a tu punto de recogida. | «En tránsito» |
| `Доставляет курьер` | En reparto con mensajero (agrupa los intentos). | «En reparto» |
| `Выдан курьеру` | Entregado al mensajero para reparto; vuelve a aparecer en cada intento. | «En reparto» |
| `Курьер не смог доставить заказ` | El mensajero no pudo entregar el pedido. | «Intento de entrega» |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Listo: llegó a tu punto de recogida o taquilla. Ya puedes recogerlo. | «Listo para recoger» |
| `Вручён` | Entregado al destinatario. | «Entregado» |
| `Не вручён` | No entregado: el pedido terminó sin entrega (no recogido o rechazado). | «Incidencia» |

CDEK también puede cerrar un pedido como cancelado («Incidencia» en Peek) o entregado parcialmente cuando aceptaste una parte y rechazaste el resto («Entregado» en Peek).

## Cómo entrega CDEK: mensajero o punto de recogida

Los pasos indican cómo llega tu paquete.

**A domicilio.** Tras llegar al depósito (`Прибыл в пункт доставки`), sale con un mensajero (`Выдан курьеру`). Tras un intento fallido, `Выдан курьеру` vuelve a salir para el siguiente.

**En punto de recogida o taquilla.** Tras `Отправлен в пункт выдачи`, espera `Поступил. Заберите заказ` antes de ir a recogerlo.

La página del pedido en my.cdek.ru muestra el punto de recogida, dirección, contacto, horarios y último día para recogerlo. Peek omite esos datos, así que míralos allí. Como la web principal cdek.ru bloquea consultas automáticas, esta página no publica plazos de entrega, custodia ni número de intentos. Ver [paquetes no recogidos](guide:uncollected-parcel), [entregas fallidas](guide:missed-delivery) y [taquillas de paquetería](guide:parcel-lockers).

**Desde el extranjero.** `Принят СДЭК на территории РФ` indica que CDEK asumió un envío del exterior en Rusia. Sin acceso a las páginas oficiales de cdek.ru, esta página no incluye normas ni tasas aduaneras; consulta [paquete retenido en aduanas](guide:customs).

## Paquete de CDEK que no llega o seguimiento parado

**Solo `Создан` o sin pasos.** Puede que el vendedor no haya entregado el paquete todavía o haya un error en el código. Peek sigue preguntando. Revisa el código dígito a dígito y, si pasan varios días sin cambios, pregunta a la tienda.

**Días sin novedades.** Si el último paso tiene cuatro días o más antes del reparto o del punto de recogida, Peek muestra en tu lista el día del último escaneo («Sin novedades desde el …») y en la ficha «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a CDEK.», solo en ese aviso con el enlace «Pide ayuda a CDEK» a sus FAQ. Ver [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** Intento fallido. Un nuevo intento volverá a aparecer como `Выдан курьеру`; si no aparece, pregunta a la tienda.

**`Не вручён`.** El pedido finalizó sin entrega. Pregunta a la tienda por reembolso o reenvío. Ver [devuelto al remitente](guide:returned-to-sender).

**Entregado pero no llegó nada.** Comprueba si lo recogió alguien por ti y luego contacta con la tienda. Más en [entregado pero no recibido](guide:delivered-not-received).

**Perdido.** La tienda contrató a CDEK, así que pregunta primero a la tienda. Ver [paquetes perdidos](guide:lost-parcel).

## Atención al cliente y contacto de CDEK

Al estar la web principal cdek.ru bloqueada para consultas automáticas, esta página no lista teléfono ni correo electrónico: Peek solo publica contactos leídos directamente en las páginas oficiales del transportista. El seguimiento y las páginas de pedido están en my.cdek.ru.

En una compra online, contacta primero con la tienda.

## Seguir un paquete de CDEK con Peek

[Peek](/) sigue más de 4500 transportistas, pero no puede asegurar que un código sea de CDEK solo por su forma. Pégalo igualmente:

- **11 dígitos empezando por `10`.** Peek pregunta a los transportistas compatibles de cinco en cinco y muestra «Comprobando servicios de seguimiento…». Si consultas desde Rusia, Kazajistán, Bielorrusia o Serbia, CDEK va primero; desde otros países sigue entre los cinco primeros. Si CDEK conoce el envío, Peek dice «CDEK tiene este paquete.». Si lo conocen varios, eliges tú; si ninguno aún, verás «aún no encontrado · Peek seguirá comprobando». También puedes pulsar «Cambiar» y elegir CDEK en «Encaja con este número».
- **Otra forma (incluidos 10 dígitos).** Pulsa «Cambiar» y busca CDEK (escribir СДЭК o SDEK también sirve).
- **Un enlace.** Pega un enlace de pedido my.cdek.ru (`/lkfl/order/...`) para seguirlo directamente con CDEK.

En un paquete ya guardado usa «Cambiar transportista». Sin transportista asignado muestra «Todavía no hay historial de seguimiento»; bajo CDEK muestra «Esperando a CDEK» hasta que responda.

**Qué muestra Peek.** Peek lee el seguimiento público de CDEK solo con el número, sin cuenta ni confirmación telefónica: verás el estado, cada paso en ruso, la ciudad y las horas exactas (en UTC convertidas a tu zona horaria, con «Horas en tu zona horaria:» al final del historial). Las respuestas anónimas ocultan el código de recogida reservado a cuentas iniciadas, y Peek omite dirección del remitente, datos del punto de recogida (dirección, teléfono, horarios, plazo), pagos, peso, dimensiones y fecha estimada de entrega. El enlace «Abrir la web de CDEK» abre el pedido en my.cdek.ru.

**Otro número del mismo pedido.** Si sigues un código vinculado por CDEK (como un código postal extranjero), CDEK devuelve su propio número de pedido, mostrado como «CDEK lo sigue con el número».

**Si CDEK no responde.** Las webs de CDEK pueden limitar consultas automáticas. Peek consulta entonces ParcelsApp, Ship24 y 17TRACK por orden (ParcelsApp resume a veces los pasos por tramos sin hora exacta) y vuelve a CDEK cuando responde de nuevo.

**Número de otro transportista.** Si un código de 11 dígitos asignado a CDEK no aparece, Peek prueba otros transportistas compatibles durante los primeros 30 días y lo cambia automáticamente («Cambiado automáticamente desde CDEK») si otro muestra movimientos.

**Paquetes entregados a CDEK.** Qwintry traspasa algunos envíos a CDEK: cuando nombra a CDEK con su número, aparece «Entrega con CDEK» (o «Anunciado a CDEK, aún no recibido» antes del primer escaneo de CDEK) y pasa a seguirlo allí en cuanto CDEK publique un paso igual o más reciente. DobroPost muestra los pasos de CDEK en su propio seguimiento (`DBRPST`). Para un código postal extranjero bajo CDEK consultado desde Bielorrusia, Peek también puede preguntar a Belpost.

**Comprobaciones y avisos.** Mientras tengas el envío abierto en pantalla — o el día de entrega con notificaciones activadas — Peek consulta directamente a CDEK hasta cada 10 minutos, o cada 2 minutos desde las 6:00 hora local el día en que sale a reparto; fuera de esos casos, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Solo a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de entrega si tienes el paquete abierto en pantalla o las notificaciones activadas. Elige «Cada escaneo», «Pasos importantes» (recomendado para puntos de recogida, pues «Solo la entrega» no avisa cuando está listo para recoger) o «Solo la entrega». Peek no puede contactar con CDEK ni con la tienda por ti.

## Preguntas sobre CDEK

### ¿Qué es СДЭК en español o inglés?

CDEK (también escrito SDEK o CDEK Express). Su rastreador está en my.cdek.ru.

### ¿Cómo es un número de seguimiento de CDEK?

Peek vincula 11 dígitos empezando por `10`, como `10482917365`, pero otros transportistas también usan 11 dígitos (y CDEK también emite códigos de 10 dígitos).

### ¿Qué significa «Поступил. Заберите заказ» en CDEK?

Tu paquete llegó al punto de recogida o taquilla y ya puedes recogerlo: Peek muestra «Listo para recoger». Consulta la dirección y la fecha límite en my.cdek.ru.

### ¿Puedo seguir un paquete de CDEK en español?

Sí. Peek traduce el estado general al español (como «En tránsito») y conserva cada paso en el texto ruso original de CDEK, explicado en la tabla anterior.

### ¿Por qué Peek me pide elegir transportista con mi código CDEK?

GLS, PostLogistics y otros transportistas también usan 11 dígitos. Si más de uno conoce el número, selecciona CDEK en la lista.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – nombres, países, formato de 11 dígitos (`10`) y 10 dígitos, enlaces my.cdek.ru, códigos, textos rusos y etapas, horas UTC, número canónico y protección antibot
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – seguimiento Qwintry que indica CDEK y su número
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – pasos de CDEK en el seguimiento de DobroPost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros transportistas de 11 dígitos, orden de consultas y servicios de seguimiento, copias de ParcelsApp, traspasos y Belpost en Bielorrusia
:::
