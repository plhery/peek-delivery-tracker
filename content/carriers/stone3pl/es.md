---
title: Seguimiento Stone3PL: números, estados y contacto
description: Rastrea un paquete de Stone3PL desde China: formato de su número S, qué significa cada línea de seguimiento, quién entrega en tu país y cómo contactar.
slug: seguimiento-stone3pl
published: 2026-11-22
updated: 2026-11-22
---

Stone3PL es una empresa logística con sede en Shenzhen (China) perteneciente al grupo Stone. Envía pedidos de tiendas online y otras empresas desde China a todo el mundo en colaboración con otros operadores logísticos. Si una tienda envió tu pedido con Stone3PL, consulta el número en [la página de seguimiento de Stone3PL](https://stone3pl.com/?route=services/track) (en inglés) o pégalo en la casilla de arriba. El vendedor te facilita ese número junto con los datos de envío de tu pedido.

## Número de seguimiento Stone3PL: qué aspecto tiene

Stone3PL no publica un formato oficial. Peek vincula un formato a Stone3PL: la letra `S` seguida de 18 dígitos. Ambos ejemplos son inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `S482917365428193642` | `S` y 18 dígitos. Peek consulta a Stone3PL por los números con este formato. La forma por sí sola no demuestra que el paquete sea de Stone3PL. |
| `S48291736542819364` | Solo 17 dígitos: no es el formato que Peek asocia a Stone3PL. Revisa si falta un dígito o pégalo igualmente. |

En Peek no importan las minúsculas, los espacios ni los guiones. El rastreador de Stone3PL admite hasta 40 números a la vez, uno por línea; conserva únicamente letras, dígitos y guiones, y convierte las minúsculas en mayúsculas.

**Dónde encontrarlo.** En el correo de envío del vendedor o en la página de tu pedido. Si no está allí, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Otros números.** La página de seguimiento de Stone3PL enumera las empresas que sigue su buscador, como DHL Express, UPS, USPS, China Post y China EMS (ePacket). Su resultado también puede indicar el transportista que entrega en tu país junto con su propio número, que puedes seguir en la web de esa empresa.

## Estados de seguimiento de Stone3PL explicados

Las líneas de seguimiento de Stone3PL están en inglés, con fecha y hora pero sin ubicación. Su página de resultados muestra primero las líneas del país de destino y luego las del inicio del trayecto. Estas son las líneas que Peek reconoce:

| Stone3PL dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order Submited.` | El vendedor envió el pedido a Stone3PL. Puede que Stone3PL aún no tenga el paquete. La grafía es la propia de Stone3PL. | «Etiqueta creada» |
| `Shipment information received` | Stone3PL recibió los datos del paquete. | «Etiqueta creada» |
| `Processing information input` | Stone3PL está registrando los datos del paquete. | «Etiqueta creada» |
| `Pickup Scan` | El paquete fue recogido y escaneado. Ya está en camino. | «Admitido por el transportista» |
| `facility - Outbound` | El paquete salió de un almacén o centro de clasificación. | «En tránsito» |
| `Arrived at domestic terminal station` | Llegó a una terminal. La línea no especifica el país. | «En tránsito» |
| `Port of departure` | Está en el puerto o aeropuerto de salida, antes de partir. | «En tránsito» |
| `International shipment release - Export` | La aduana de exportación lo liberó y sigue su curso. | «En tránsito» |
| `Port of departure - Departure` | Salió de ese puerto o aeropuerto. | «En tránsito» |
| `Port of destination - Arrival` | Llegó a un puerto o aeropuerto del país de destino. | «En tránsito» |
| `International shipment release - Import` | La aduana de importación lo liberó y continúa el viaje. | «En tránsito» |
| `Pick up by local carrier at destination port` | El transportista que entrega en tu país lo recogió en el puerto. No tienes que ir a recoger nada. | «En tránsito» |
| `Batch delivery to carrier` | Stone3PL lo entregó, junto con otros paquetes, al transportista encargado del reparto. | «En tránsito» |

Las líneas del país de destino pueden usar otras palabras. Peek detecta expresiones habituales como entrega o aduana. Una línea que Peek no reconozca permanece en el historial y el estado queda en el último paso comprendido.

**Estado general.** El rastreador de Stone3PL asigna también un estado global a cada envío. Cuando ese estado indica listo para recoger, entregado o incidencia, Peek muestra «Listo para recoger», «Entregado» o «Incidencia» incluso antes de que una línea concreta lo diga.

**Horas.** Stone3PL ofrece horas sin zona horaria. Peek las fecha en una zona deducida (por defecto la hora de China, UTC+8) y las muestra en la zona horaria de tu dispositivo («Horas en tu zona horaria:»), así que una línea de tu país puede aparecer con varias horas de desfase. Guíate por el orden de las líneas.

## Quién entrega los paquetes de Stone3PL

Stone3PL señala que trabaja en colaboración con proveedores logísticos internacionales de referencia y dispone de almacenes en NORAM, APAC y EMEA (Norteamérica, Asia-Pacífico, y Europa, Oriente Medio y África).

Sus páginas no especifican qué empresa entrega en cada país: busca en la página de resultados de Stone3PL el transportista final y su número. Los intentos de entrega, avisos y puntos de recogida siguen las reglas de esa empresa. Consulta [entregas fallidas](guide:missed-delivery).

**Aranceles y aduanas.** Stone3PL ofrece a los vendedores envíos DDP (Delivered Duty Paid) con despacho aduanero DDP. Solo el vendedor puede confirmar si tu pedido se envió bajo esa modalidad.

**En la UE (Irlanda, por ejemplo).** Los artículos comprados online fuera de la UE pagan un arancel de 3 € por artículo en envíos de hasta 150 €, desde el 1 de julio de 2026. Desde el 1 de noviembre de 2026 se aplica además una tasa de gestión de la Unión de 2 € por artículo, cualquiera que sea el valor. Un artículo comprende las mercancías que comparten clasificación arancelaria, descripción y, cuando corresponda, origen. Las páginas de Stone3PL no indican cómo se abonan estos cargos. En Reino Unido, Estados Unidos, Canadá o Australia, lo que corresponda pagar depende de la normativa local: consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de Stone3PL sin actualizar, perdido o devuelto

Stone3PL trabaja para las empresas que envían con él y no ofrece páginas de ayuda para destinatarios, así que ante un envío retrasado, perdido, dañado o devuelto, contacta primero con el vendedor.

**Nada todavía.** Peek sigue comprobando. Muestra «Esperando a Stone3PL» si elegiste Stone3PL, y «Todavía no hay historial de seguimiento» si no se asignó transportista al añadir el número. Tras dos días sin nada, Peek marca un paquete añadido sin transportista con «Esperando la primera actualización del transportista»: comprueba el número con el vendedor. Si elegiste Stone3PL y sigue sin reconocer el número dos días después sin que responda ninguna otra fuente, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo a diario, y semanalmente pasada una semana.

**Solo `Order Submited.` o `Shipment information received`.** Stone3PL tiene los datos del pedido, pero tal vez no el paquete. Si no cambia durante varios días, pregunta al vendedor si realmente lo entregó.

**Días sin actualizaciones.** Cuando un paquete en camino lleva cuatro días sin nuevas líneas, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y señala «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Stone3PL.», junto al enlace «Pide ayuda a Stone3PL» hacia su página de contacto. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating) y [seguir paquetes desde China](guide:tracking-from-china).

**«Incidencia».** Stone3PL registró un problema. Lee la línea más reciente y pregunta al vendedor cómo proceder.

**Entregado, pero no está.** Revisa los alrededores de tu domicilio y pregunta a los vecinos; luego avisa al vendedor. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido, dañado o devuelto.** La web de Stone3PL no informa a destinatarios sobre reclamaciones ni devoluciones; su portada ofrece a los vendedores un servicio de devolución y reenvío. Pide al vendedor un reembolso o un envío nuevo. Consulta [paquetes perdidos](guide:lost-parcel), [paquetes dañados](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

**Un mensaje que te pide pagar.** Antes de abonar una tasa solicitada por SMS o correo electrónico, lee [estafas por SMS de entrega](guide:delivery-scam-texts).

## Contacto de Stone3PL

Según figura en [la página de contacto de Stone3PL](https://stone3pl.com/index.php?route=services/contact_us) para su sede de Shenzhen («STONE3PL Marketing Dpt»):

- **Correo electrónico:** support@stone3pl.com. La política de privacidad de Stone3PL recoge la misma dirección.
- **Teléfono:** 86-0755-81466633, un número en Shenzhen, China. Stone3PL no publica horarios de atención ni los idiomas disponibles.
- **Dirección:** Ground Floor, Building C5, Xin'an Industry Co., No.2 Industrial Zone, Gushu 2nd Rd., Baoan, Shenzhen, Guangdong Province, China.

Sobre tu pedido, pregunta primero al vendedor: fue quien contrató a Stone3PL, no tú.

## Seguir un paquete de Stone3PL con Peek

[Peek](/) sigue los paquetes de Stone3PL, aunque no puede identificar un número de Stone3PL solo por su forma. Cuando pegas un número compuesto por `S` y 18 dígitos, Peek muestra «Comprobando servicios de seguimiento…» mientras pregunta a Stone3PL. Si Stone3PL tiene el paquete, aparece «Stone3PL tiene este paquete.» y Peek lo sigue allí. Un enlace al rastreador de Stone3PL con un número tras `nums=` nombra a Stone3PL de inmediato («Transportista detectado»).

Con cualquier otro número, pégalo igualmente: Peek sigue a más de 4500 transportistas. Cuando un formato encaja débilmente con algunos transportistas, Peek muestra «Comprobando servicios de seguimiento…» mientras les consulta; un número que no encaja con ningún formato se busca después de añadirlo. Si Peek no identifica el transportista, pulsa «Cambiar» y elige Stone3PL en «Todos los transportistas», o usa «Cambiar transportista» en un paquete ya añadido.

Peek lee el estado general, cada línea con su hora, los países de origen y destino, el servicio de envío, así como la empresa que entrega y su número cuando figuran. Elimina los números de teléfono, mientras que conserva el nombre de quien firmó si aparece en un escaneo de entrega. Stone3PL no proporciona fecha estimada de entrega, peso, ubicaciones de escaneo ni comprobante de entrega.

Cuando Stone3PL nombra al transportista final y facilita su número, Peek consulta a esa empresa. Con un número postal (dos letras, nueve dígitos, dos letras) sin empresa indicada, Peek consulta al correo del país de destino, como [USPS](carrier:usps) en EE. UU., [Royal Mail](carrier:royal-mail) en Reino Unido, An Post en Irlanda, [Canada Post](carrier:canada-post) o [Australia Post](carrier:australia-post). Con otro número, consulta al único transportista al que corresponda ese formato, si lo hay. En cuanto ese transportista muestre un escaneo al menos tan reciente como el último de Stone3PL, Peek sigue el paquete allí conservando el historial de Stone3PL. La empresa nombrada por Stone3PL ya figura antes de eso («Entrega con USPS», por ejemplo); mientras solo tenga el anuncio sin escaneo propio, aparece «Anunciado a …, aún no recibido». Si no hay transportista de relevo, Peek sigue con el rastreador de Stone3PL.

Mientras mantengas el paquete abierto en Peek — o el día de la entrega con las notificaciones activadas — Peek consulta directamente a Stone3PL hasta cada 10 minutos, y hasta cada 2 minutos a partir de las 6:00 (hora de Pekín) el día en que el paquete sale a reparto, siempre que el rastreador de Stone3PL responda; fuera de ese caso, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Cuando otro transportista toma el relevo, Peek sigue el ritmo del transportista que entrega el paquete. Puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega». No puede contactar con Stone3PL ni con el vendedor por ti.

## Preguntas sobre el seguimiento de Stone3PL

### ¿Es fiable Stone3PL?

Stone3PL declara formar parte del grupo Stone, con sede en Shenzhen, más de 10 años de experiencia y más de 8.000 clientes con contrato. Publica dirección, teléfono y correo electrónico. Trabaja para vendedores, por lo que las consultas sobre tu pedido van a la tienda.

### ¿Dónde puedo rastrear un paquete de Stone3PL?

En [la página de seguimiento de Stone3PL](https://stone3pl.com/?route=services/track) o en Peek. Si el resultado de Stone3PL indica la empresa que reparte en tu país, también puedes seguir ese número en su propia web.

### ¿Qué significa «Pick up by local carrier at destination port»?

Que la empresa que entrega en tu país recogió tu paquete en el puerto o aeropuerto de llegada. No significa que tengas que ir a recogerlo tú.

### ¿Cuánto tarda Stone3PL en entregar?

Stone3PL no publica plazos de entrega. Guíate por la estimación del vendedor y consúltale si el paquete se retrasa.

### ¿Cómo contacto con Stone3PL?

Por correo electrónico en support@stone3pl.com o por teléfono en el 86-0755-81466633 (número de Shenzhen, China). Para tu pedido, pregunta primero al vendedor.

:::sources
- [Stone3PL: Home](https://stone3pl.com/) – logística e-commerce desde China al mundo para vendedores, despacho DDP y Delivered Duty Paid, servicio de devolución y reenvío, almacenes en NORAM, APAC y EMEA
- [Stone3PL: Logistics tracking](https://stone3pl.com/?route=services/track) – rastreador en inglés, hasta 40 números uno por línea, letras, dígitos y guiones en mayúsculas, empresas que sigue
- [Stone3PL: About us](https://stone3pl.com/index.php?route=services/about_us) – Stone Group, sede en Shenzhen, más de 10 años de experiencia, más de 8.000 clientes con contrato, socios logísticos globales
- [Stone3PL: Contact us](https://stone3pl.com/index.php?route=services/contact_us) – sede en Shenzhen, correo, teléfono, dirección
- [Stone3PL: Privacy Policy](https://stone3pl.com/index.php?route=services/privacy) – correo electrónico de soporte
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por artículo hasta 150 € desde el 1 de julio de 2026, tasa de gestión de la Unión de 2 € desde el 1 de noviembre de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artículo cualquiera que sea el valor, qué constituye un artículo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de número, textos y etapas, estado general, hora de China, firmante, transportista final y traspaso
:::
