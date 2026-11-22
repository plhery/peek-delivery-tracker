---
title: Seguimiento Emirates Post: números AE y estados
description: Sigue un envío de Emirates Post desde o dentro de los EAU: formato AE, estados explicados, aduanas UE y paso al correo de tu país.
slug: seguimiento-emirates-post
published: 2026-11-22
updated: 2026-11-22
---

Emirates Post (بريد الإمارات) es el servicio postal de los Emiratos Árabes Unidos; el catálogo de Peek también lo conoce como Emirates Post Group. Sus números postales terminan en `AE` y su rastreador indica el lugar de la mayoría de los pasos, tanto en los EAU como, en envíos internacionales, en el país de destino. Pega el número en el recuadro superior o búscalo en el rastreador de Emirates Post en emiratespost.ae. Peek no ha podido consultar la web de Emirates Post para esta página, así que lo que sigue recoge lo que el código de Peek conoce de su rastreador.

## Número de seguimiento de Emirates Post

Un número postal de Emirates Post tiene 13 caracteres en el [formato postal internacional](guide:tracking-number-formats): dos letras, nueve dígitos y `AE`, el código de los EAU. El último de los nueve dígitos es un dígito de control. Con dígitos inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `EE482910372AE` | Empieza por C, E, L o R y acaba en `AE`, con dígito de control correcto: Peek asigna Emirates Post al instante. |
| `UA482910372AE` | Acaba en `AE`, pero empieza por otra letra. Su forma sola no basta, así que Peek lo muestra primero como «Operador postal desconocido» (ver abajo). |
| `EE482910371AE` | La primera forma con dígito de control incorrecto. Peek no reconoce ningún transportista: comprueba si hay una errata. |
| `7394028156342` | 13 dígitos. Peek puede buscarlos en Emirates Post, pero otros transportistas también usan 13 dígitos, así que elige Emirates Post tú mismo. |
| `RR482910372CN` | Termina en otras letras, aquí `CN`: lo emitió otro correo, aquí [China Post](carrier:china-post). |

Los espacios y las minúsculas no importan al pegar un número en Peek. ¿No encuentras el tuyo? Consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number).

## Estados de seguimiento de Emirates Post

El rastreador de Emirates Post incluye fecha, hora y casi siempre un lugar en cada paso. Estos son los pasos que Peek conoce, en el orden habitual:

| Emirates Post dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Yet to be received` | Emirates Post tiene los datos del envío, pero todavía nadie lo ha depositado. Puede verse días antes del envío. | «Etiqueta creada» |
| `Received at counter` | Depositado en una oficina de Emirates Post. | «Admitido por el transportista» |
| `Received - shipment under processing`, `Shipment under Processing` | En procesamiento tras la admisión. | «En tránsito» |
| `Item into sorting centre` | En un centro de clasificación. | «En tránsito» |
| `Shipped to` | En camino hacia el lugar nombrado a continuación: siguiente oficina, ciudad de destino en el extranjero o siguiente punto tras la aduana de importación. | «En tránsito» |
| `Arrived at` | Llegó al lugar indicado después: centro de reparto o país de destino en envíos internacionales. | «En tránsito» |
| `Item presented to export Customs/Security` | En controles de aduana o seguridad de exportación antes de salir. | «En aduanas» |
| `Item returned from export Customs/Security` | Liberado: vuelve a estar en camino. | «En tránsito» |
| `Item presented to import Customs` | En aduanas en el país de destino. | «En aduanas» |
| `Import terminated` | El país de destino detuvo la importación. Aún pueden aparecer pasos posteriores. | «Incidencia» |
| `Item held at delivery depot` | Está en el depósito de reparto del país de destino. No indica todavía que puedas recogerlo. | «En tránsito» |
| `Ready for collection by customer` | Listo para que lo recojas. | «Listo para recoger» |
| `Out for delivery` | En reparto. | «En reparto» |
| `Delivered` | Entregado. | «Entregado» |

Peek interpreta cualquier otro paso por su texto y lo muestra con las palabras de Emirates Post. Más en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Envíos de Emirates Post al extranjero: entrega y aduanas

Cuando el envío sale de los EAU, `Shipped to` indica adónde va. El rastreador de Emirates Post puede seguir mostrando pasos en el país de destino: llegada, aduana de importación y depósito de reparto. En muchos países, Peek también pregunta a tu correo nacional con el mismo número `AE`, como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) o [Australia Post](carrier:australia-post) (ver la última sección). Los intentos de entrega y el punto de recogida siguen entonces las normas del operador de tu país: ver [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

**Aduanas en la UE.** Los EAU están fuera de la UE, así que el envío pasa por aduanas en tu país. Desde el 1 de julio de 2026, las compras online desde fuera de la UE en pedidos de hasta 150 € pagan un derecho aduanero de la UE de 3 € por artículo. Desde el 1 de noviembre de 2026 se aplica también una tasa de gestión de la Unión de 2 € por artículo, cualquiera que sea el valor. Un artículo agrupa los bienes del paquete con igual clasificación arancelaria, descripción y, si procede, origen. Peek no ha podido comprobar cómo se cobran en un envío de Emirates Post: pregunta a la tienda si tu precio ya los incluía. Fuera de la UE rigen las normas de tu país: ver [paquetes retenidos en aduanas](guide:customs).

## Recibir un envío de Emirates Post en los EAU

Los pasos indican la oficina que tiene el envío, como un centro de clasificación o reparto. Cuando espera para recoger, figura `Ready for collection by customer`. Como Peek no ha podido consultar la web de Emirates Post ni las normas aduaneras de los EAU en una página oficial, esta página no indica plazo de custodia, normas de entrega ni tasas aduaneras.

**Desde el extranjero.** Un paquete enviado a los EAU lleva el número del correo de origen. Peek lo sigue en ese correo o mediante servicios de seguimiento y no lo transfiere automáticamente a Emirates Post: solo consulta Emirates Post con ese número si eliges Emirates Post en «Cambiar».

## Seguimiento sin novedades, perdido o devuelto

**Sin cambios tras salir de los EAU.** El vuelo y la aduana llevan tiempo. Si pasan cuatro días sin nuevo escaneo en tránsito, fuera de aduanas o de una incidencia, Peek muestra «Sin novedades desde hace cuatro días» (o «Sin novedades desde el …»). Comprueba también el correo de tu país con el mismo número; Peek lo hace por ti donde puede. Más en [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Entregado, pero no llegó nada.** Pregunta a quien entregó en tu país y avisa al remitente. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Habla primero con el remitente o la tienda: ellos contrataron el envío. Ver [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Emirates Post

La web de Emirates Post responde a las comprobaciones automáticas con una verificación de navegador, por lo que esta página no recoge teléfono, correo electrónico ni horario: Peek solo publica contactos leídos en las páginas oficiales de un transportista.

Para una compra online, consulta primero a la tienda. Una vez que el correo de tu país tenga el paquete, pregúntale directamente por la entrega.

## Seguir un paquete de Emirates Post con Peek

[Peek](/) asigna Emirates Post al instante a un número de dos letras, nueve dígitos y `AE` que empieza por C, E, L o R con dígito de control válido, y muestra «Transportista detectado»; con «Cambiar» eliges otro. Si el dígito de control falla, avisa «El dígito de control de este número no cuadra.»: revisa el número o elige «Seguir tal como está». También funciona pegar un enlace al rastreador de Emirates Post: Peek extrae el código. Clasificado bajo Emirates Post, aparece «Esperando a Emirates Post» hasta que Emirates Post lo liste.

Un número `AE` que empiece por otra letra, o un número de 13 dígitos, no basta por su forma para identificar a Emirates Post. Pégalo igualmente: Peek sigue a más de 4500 transportistas. Para ese `AE`, Peek muestra «Operador postal desconocido» y «Comprobando servicios de seguimiento…»; si ningún servicio tiene el paquete, pregunta a Emirates Post y muestra «Emirates Post tiene este paquete.» si aparece allí. Si un servicio lo tiene, Peek lo sigue ahí; durante los primeros 30 días del envío, las consultas posteriores pueden volver a preguntar a Emirates Post y moverlo allí en cuanto Emirates Post muestre movimiento. Para 13 dígitos, o para ir directo a Emirates Post, elígelo en «Cambiar» (o «Cambiar transportista»).

Como el rastreador de Emirates Post exige verificación de navegador, Peek lo lee mediante un navegador. Muestra cada paso en el inglés de Emirates Post (o en árabe si no hay inglés) junto con el lugar. Descarta datos de remitente y destinatario, contactos, referencias internas y enlaces de eventos; el rastreador no ofrece fecha estimada de entrega ni peso. Peek lee los pasos en Dubái, Abu Dabi, Sharjah, Ajmán, Fuyaira, Umm al-Qaywayn y Ras al-Jaima en hora de los EAU, y los de fuera según el reloj local del lugar nombrado o de los pasos cercanos (el del destino tras la llegada), convirtiéndolos a tu zona horaria («Horas en tu zona horaria:»). «Abrir la web de Emirates Post» abre el número directamente en su rastreador. Cuando no se puede leer el portal, Peek consulta servicios de seguimiento: ParcelsApp, Ship24 y 17TRACK por ese orden, y para números `AE` el rastreador de la Unión Postal Universal en último lugar.

Cuando los pasos de Emirates Post indican el país de destino —o no indican ninguno y haces el seguimiento desde fuera de los EAU—, Peek pregunta también al correo de ese país con el mismo número `AE`: [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), NZ Post, [DHL](carrier:dhl) en Alemania, [La Poste](carrier:la-poste) en Francia, los correos de todos los demás países de la UE y los de Suiza, Noruega, Arabia Saudí, Pakistán, Filipinas, Egipto, Jordania, Kenia y Bangladés, entre otros. Cuando ese correo muestra un escaneo al menos igual de reciente que el último de Emirates Post, Peek pasa a seguirlo allí, conserva el historial de Emirates Post y la tarjeta indica, por ejemplo, «Entrega con USPS». En algunos países como India, Sri Lanka, China, Omán, Kuwait o Baréin, Peek no conoce correo al que consultar y se queda en Emirates Post. Un envío aún mostrado como «Operador postal desconocido» solo activa este relevo si el servicio de seguimiento indica el país de destino.

Peek consulta hasta cada 10 minutos, y hasta cada 5 minutos desde las 6:00 según el reloj del último escaneo el día en que pasa a «En reparto» mientras tienes el paquete abierto en Peek o tienes las notificaciones activadas, siempre que responda el rastreador de Emirates Post (cada consulta abre un navegador). Una vez transferido al correo de tu país, sigue la frecuencia de ese correo. Consulta cada hora tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»). En notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Si pasan cuatro días sin escaneo en tránsito, fuera de aduanas o de una incidencia, Peek muestra «Sin novedades desde el …», el aviso «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Emirates Post.» y «Pide ayuda a Emirates Post». Peek no puede contactar con Emirates Post, tu correo ni la tienda por ti.

## Preguntas sobre el seguimiento de Emirates Post

### ¿Cómo es un número de seguimiento de Emirates Post?

Dos letras, nueve dígitos y `AE`, por ejemplo `EE482910372AE`. Peek asigna Emirates Post al instante cuando empieza por C, E, L o R y el dígito de control cuadra.

### ¿Por qué mi seguimiento de Emirates Post sigue en «Yet to be received»?

Emirates Post tiene los datos del envío, pero todavía no se ha depositado en ventanilla: este estado puede verse días antes del envío. Pregunta al remitente cuándo lo entregará.

### ¿Qué significa «Import terminated» en Emirates Post?

El país de destino detuvo la importación del envío. Peek lo muestra como «Incidencia». Aún pueden aparecer pasos posteriores, así que sigue revisando y pregunta al remitente o a la tienda qué ocurrió.

### ¿Puedo seguir un envío de Emirates Post en el correo de mi país?

En muchos países sí, con el mismo número `AE`. En un envío seguido en Emirates Post, Peek pregunta por ti a tu correo en EE. UU., Reino Unido, Irlanda, Canadá, Australia, todos los países de la UE y varios más, y cambia a él en cuanto muestra un nuevo escaneo.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel UE de 3 € por artículo hasta 150 € desde el 1 de julio de 2026, tasa de gestión de la Unión de 2 € desde el 1 de noviembre de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artículo cualquiera que sea el valor, qué es un artículo
- [Universal Parcel Scraper: Emirates Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/emirates-post) – nombres, formatos y dígito de control, números de 13 dígitos, lectura mediante navegador, estados y etapas, lugares, relojes, destino, campos descartados, enlaces de seguimiento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números de China Post, transportistas consultados para otros `AE`, orden de servicios de seguimiento, relevo al correo de destino, ninguno hacia Emirates Post
:::
