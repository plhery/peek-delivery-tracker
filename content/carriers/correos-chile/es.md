---
title: Seguimiento Correos de Chile: números y estados
description: Sigue un paquete de Correos de Chile: formato CL o de 13 dígitos, estados, IVA y aduanas en Chile, y seguimiento postal en el extranjero.
slug: seguimiento-correos-chile
published: 2026-11-22
updated: 2026-11-22
---

Correos de Chile es el servicio postal de Chile. Según el Servicio Nacional de Aduanas de Chile, las compras en línea enviadas por correo ingresan a través de Correos de Chile, mientras que las empresas de mensajería traen el resto. El rastreador propio está en www.correos.cl/seguimiento-en-linea. También puedes pegar el número arriba: esta página explica cada paso. ¿Compraste en línea? El código aparece en el correo de envío de la tienda o en tu página de pedido.

## Número de seguimiento de Correos de Chile

Peek reconoce dos formatos para Correos de Chile (con dígitos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `RR482910372CL` | Dos letras, nueve dígitos y `CL`, el código de Chile: un envío numerado por Correos de Chile en el [formato postal internacional](guide:tracking-number-formats). Las dos primeras letras varían y el noveno dígito es de control. |
| `7394028156342` | 13 dígitos: el otro formato que Peek conoce para Correos de Chile, pero solo una coincidencia débil, porque otros transportistas también usan 13 dígitos. |
| `RR482910372CN` | Termina en otras letras, aquí `CN`: lo numeró el correo de otro país, aquí [China Post](carrier:china-post). Peek lo sigue con ese correo. |

Los espacios y las minúsculas no importan al pegar un número en Peek. ¿No encuentras el tuyo? Consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number).

## Estados de seguimiento de Correos de Chile

El rastreador de Correos de Chile muestra los pasos del más reciente al más antiguo, en mayúsculas en español y con la oficina cuando la hay. Estas son las líneas que Peek conoce, en el orden habitual:

| Correos de Chile dice | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `ENVÍO ADMITIDO` | Correos de Chile ha recibido el envío. En un paquete del extranjero es la primera línea, antes de la internación. | «Admitido por el transportista» |
| `ENVÍO EN PROCESO DE INTERNACIÓN AL PAÍS` | Está en proceso de importación a Chile: aduanas lo está tramitando. | «En aduanas» |
| `ENVÍO EN REPARTO` | En reparto para entrega. | «En reparto» |
| `ENVÍO ENTREGADO` | Entregado. | «Entregado» |

Peek muestra cualquier línea que no reconoce como `Actualización del envío`, sin las palabras exactas de Correos de Chile, porque esas líneas pueden contener datos personales o detalles de la entrega. Consulta www.correos.cl para leerla completa. Más en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Paquetes que entran en Chile: aduanas e IVA

Un paquete llegado del extranjero, como una compra online, pasa por aduanas en Chile. El Servicio Nacional de Aduanas explica el procedimiento. ¿Pediste en una tienda de China? Consulta [seguimiento desde China](guide:tracking-from-china).

**IVA en compras online.** Desde el 25 de octubre de 2025, las compras en línea desde el extranjero hasta 500 USD pagan 19 % de IVA y no pagan arancel aduanero. Si la tienda o plataforma está inscrita en el Servicio de Impuestos Internos (SII) de Chile, suma el IVA al pagar. Si no lo está, el impuesto se cobra al importar, a través del courier o, en envíos postales, a través de Correos de Chile. Revisa el desglose antes de pagar. Ciertos bienes pagan otros impuestos o impuestos adicionales (como ropa usada, tabaco, alcohol y joyas) y algunos exigen un permiso. Por encima de 500 USD, los impuestos se pagan al importar.

**Revisiones aduaneras.** Cuando los paquetes llegan a la planta de Correos de Chile, aduanas puede elegir el tuyo para una revisión física en los almacenes de Correos de Chile. Si aduanas pide comprobante de compra, certificado o permiso (visto bueno), Correos de Chile te indica qué hacer y dónde obtenerlo; sin él, aduanas no libera el envío. Si corresponden aranceles o IVA, Correos de Chile solicita un comprobante de lo pagado (boleta o factura); sin él, aduanas valora la mercancía a precios de mercado. Una vez liberado por aduanas, Correos de Chile realiza la entrega.

**Entrega y recogida.** Peek no ha podido consultar las normas propias de Correos de Chile sobre intentos de entrega, recogida o plazo de custodia, por lo que esta página no las detalla. Consulta [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Envíos de Correos de Chile al extranjero

¿Tu paquete salió de Chile con un número acabado en `CL`? Cuando haya salido de Chile, consulta también el seguimiento del correo de tu país con el mismo número, como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), Parcelforce, An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) o NZ Post. Peek les pregunta por ti (ver más abajo). Una vez que tu correo tenga el paquete, pregúntale por la entrega y la recogida.

**Aduanas en la UE.** Chile está fuera de la UE, así que el envío pasa por aduanas en tu país. Desde el 1 de julio de 2026, las compras online desde fuera de la UE en pedidos de hasta 150 € pagan un derecho aduanero de la UE de 3 € por artículo. Desde el 1 de noviembre de 2026, se aplica además una tasa de gestión de la Unión de 2 € por artículo, sea cual sea el valor. Un artículo agrupa los bienes de un paquete con la misma clasificación arancelaria, descripción y, cuando procede, origen. Fuera de la UE rigen las normas de tu país: consulta [paquetes retenidos en aduanas](guide:customs).

## Seguimiento sin novedades, perdido o devuelto

**Aún sin pasos.** Puede que el remitente todavía no haya entregado el paquete. Clasificado bajo Correos de Chile, aparece «Esperando a Correos de Chile» y Peek sigue comprobando. Si no hay cambios en unos días, revisa el número con el remitente. Ver [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Retenido en aduanas.** La última línea es `ENVÍO EN PROCESO DE INTERNACIÓN AL PAÍS` y Peek lo marca como «Despacho de aduanas». Aduanas puede estar revisándolo o esperando un documento o pago: Correos de Chile avisa si falta algo.

**Varios días sin cambios.** Si un paquete en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» pasa cuatro días sin un nuevo escaneo, Peek muestra «Sin novedades desde el …». Es habitual en vuelos largos o fines de semana.

**Entregado, pero no llegó nada.** Pregunta a tus vecinos y a la oficina de reparto, y después al remitente. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Habla primero con el remitente o la tienda: ellos contrataron el envío. Consulta [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Correos de Chile

Peek no ha podido leer la web de Correos de Chile, así que esta página no recoge teléfono ni horarios: Peek solo publica contactos verificados en páginas oficiales. Para un paquete en trámite aduanero por vía postal, el Servicio Nacional de Aduanas indica consultar su estado en www.correos.cl o escribir a sac@correos.cl.

Si se trata de una compra en línea, consulta primero a la tienda que contrató el envío.

## Seguir un paquete de Correos de Chile con Peek

[Peek](/) reconoce dos letras, nueve dígitos y `CL` como Correos de Chile cuando el dígito de control cuadra, y muestra «Transportista detectado»; con «Cambiar» eliges otro. Si el dígito de control falla, avisa «El dígito de control de este número no cuadra.»: revisa el correo de envío o elige «Seguir tal como está».

Un número de 13 dígitos no basta por sí solo para identificar a Correos de Chile. Pégalo igualmente: Peek sigue a más de 4500 transportistas. Si lo añades sin elegir transportista, Peek pregunta primero a [FAN Courier](carrier:fan-courier) (desde México, junto con Paquetexpress), después a servicios de seguimiento («Comprobando servicios de seguimiento…») y, solo si ninguno conoce el número, a otros transportistas con números de 13 dígitos como [Delhivery](carrier:delhivery), Econt u Ozon, pero nunca a Correos de Chile. Solo lo asigna a Correos de Chile si un servicio de seguimiento lo nombra en exclusiva con pasos fechados. Así que, si la tienda dice Correos de Chile, pulsa «Cambiar» y selecciónalo bajo «Encaja con este número», o usa «Cambiar transportista» en un paquete ya añadido. Un número emitido por otro correo, como uno acabado en `CN`, no es de Correos de Chile: si lo eliges para ese código, Peek indica que «no emite números como este».

Clasificado bajo Correos de Chile, un paquete muestra «Esperando a Correos de Chile» hasta su primer paso. Luego Peek lee el seguimiento de Correos de Chile (directamente o mediante navegador) y muestra las líneas conocidas en español con su oficina, bajo sus propias etapas como «Admitido por el transportista» o «En aduanas», y las desconocidas como `Actualización del envío`. Peek no muestra el nombre de quien recibió el paquete ni una fecha estimada de entrega. Las horas de Correos de Chile no llevan zona horaria: Peek interpreta cada una con el reloj local de la oficina indicada o de los pasos cercanos y las muestra en tu zona horaria («Horas en tu zona horaria:»). Cuando el rastreador no responde, Peek consulta servicios de seguimiento, empezando por 17TRACK, y muestra su texto literal.

Cuando sigues un número `CL` desde fuera de Chile, Peek consulta también al correo de tu país con el mismo número: [USPS](carrier:usps) en EE. UU., [Royal Mail](carrier:royal-mail) en Reino Unido (Parcelforce si empieza por E), An Post en Irlanda, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), NZ Post, [DHL](carrier:dhl) en Alemania, [La Poste](carrier:la-poste) en Francia, [Correos](carrier:correos-spain) en España, [Correios](carrier:correios-br) en Brasil y los correos de los demás países de la UE salvo los Países Bajos, Argentina y Perú, entre otros. Pasa a seguirlo allí en cuanto ese correo tenga un paso al menos igual de reciente que el último de Correos de Chile, conservando el historial previo. Desde México o China no consulta a otro correo. A la inversa, Peek no busca en Correos de Chile un envío hacia Chile con código de otro correo: lo sigue en el correo que lo numeró.

Mientras tienes el paquete abierto en Peek, o el día de la entrega con las notificaciones activadas, Peek consulta a Correos de Chile hasta cada 10 minutos, y el día en que entra «En reparto», desde las 6:00 según el reloj del último escaneo (hora chilena en Chile), hasta cada 2 minutos, o cada 5 cuando la lectura del seguimiento abre un navegador, mientras el seguimiento de Correos de Chile responda. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora; tras siete días sin novedades en «Etiqueta creada» o sin ningún paso, cada 6 horas. En notificaciones puedes elegir «Cada escaneo», «Pasos importantes» o «Solo la entrega». Si pasan cuatro días sin escaneo en un paquete en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el …», el aviso «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Correos de Chile.» y «Pide ayuda a Correos de Chile». Peek no puede contactar con Correos de Chile, tu correo ni la tienda por ti.

## Preguntas sobre el seguimiento de Correos de Chile

### ¿Cómo sigo un paquete de Correos de Chile fácilmente?

Pega el número en Peek. Los pasos aparecen en español con su oficina y agrupados bajo las etapas de seguimiento de Peek, explicadas en la tabla superior.

### ¿Qué significa «ENVÍO EN PROCESO DE INTERNACIÓN AL PAÍS»?

El paquete está en trámite de importación a Chile y aduanas lo gestiona. Correos de Chile te contacta si aduanas necesita algún documento o pago.

### ¿Tengo que pagar impuestos por un paquete del extranjero a Chile?

Desde el 25 de octubre de 2025, las compras online hasta 500 USD pagan 19 % de IVA y no pagan arancel. Una plataforma registrada lo añade al pagar; en caso contrario lo cobra Correos de Chile o el courier al importar.

### ¿Por qué mi seguimiento de Correos de Chile no se actualiza?

El paquete puede estar en aduanas, en vuelo o aún no entregado al correo. ¿Va al extranjero? Comprueba el correo de tu país con el mismo número `CL`.

### ¿Cómo es un número de seguimiento de Correos de Chile?

Dos letras, nueve dígitos y `CL`, como `RR482910372CL`, o 13 dígitos. Peek reconoce el primero por su forma; para el segundo, selecciona Correos de Chile en «Cambiar».

:::sources
- [Servicio Nacional de Aduanas: Compras online](https://www.aduana.cl/aduana/site/edic/base/port/comercio_electronico.html) – compras postales por Correos de Chile, couriers para el resto
- [Servicio Nacional de Aduanas: IVA digital](https://www.aduana.cl/aduana/site/edic/base/port/especial_iva_digital.html) – 19 % de IVA hasta 500 USD, sin arancel, quién lo cobra, otros impuestos, sobre 500 USD
- [Servicio Nacional de Aduanas: ¿Cuándo debo pagar impuestos?](https://www.aduana.cl/cuando-debo-pagar-impuestos/aduana/2018-12-12/233227.html) – IVA digital desde el 25 de octubre de 2025
- [Servicio Nacional de Aduanas: Revisiones aduaneras despachos vía postal](https://www.aduana.cl/revisiones-aduaneras-despachos-via-postal/aduana/2018-12-13/161233.html) – revisiones en almacenes de Correos de Chile, documentos y permisos, comprobante de pago, liberación, www.correos.cl y sac@correos.cl
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 €, tasa de gestión de 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – por artículo, sea cual sea el valor, qué es un artículo
- [Universal Parcel Scraper: Correos de Chile](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-chile) – formatos, dígito de control, rastreador, textos en español y etapas, actualización neutra, firmante, relojes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de 13 dígitos de otros transportistas, servicios de seguimiento, correo del país de destino, ninguno hacia Correos de Chile
:::
