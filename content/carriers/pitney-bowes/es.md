---
title: Seguimiento Pitney Bowes: números UPAA, UPBE y PBX
description: Sigue un paquete de Pitney Bowes (como un pedido internacional de eBay): formatos UPAA, UPBE y PBX, significado de cada estado y quién entrega.
slug: seguimiento-pitney-bowes
published: 2026-11-23
updated: 2026-11-23
---

Pitney Bowes envía paquetes a otros países: en el código de Peek, sus números pertenecen a paquetes de eBay desde Estados Unidos y Reino Unido y a otros envíos internacionales de Pitney Bowes. En los historiales recogidos en el código de Peek, el paquete pasa por un centro de envío de Pitney Bowes y viaja después al país del comprador, donde un socio —como el correo nacional— realiza la entrega. El número empieza por UPAA, UPBE o PBX y procede del vendedor o del marketplace: pégalo en el recuadro de arriba. El rastreador propio de Pitney Bowes está en trackpb.shipment.co.

## Número de seguimiento de Pitney Bowes: cómo es

Peek reconoce tres formatos de Pitney Bowes, de entre 14 y 20 caracteres cada uno. Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `UPAAK482917364058291` | UPAA, una letra y 15 dígitos: en el código de Peek, un paquete de eBay desde Estados Unidos o Reino Unido. |
| `UPBE48291736A4F02819` | UPBE y 16 caracteres, dígitos o letras de la A a la F: un paquete de eBay desde Reino Unido. |
| `PBXHK482917365` | PBX, dos letras y 9 dígitos: otro envío internacional de Pitney Bowes. |
| `PBXHK48291736A4F0281` | PBX, dos letras y 15 caracteres, dígitos o letras de la A a la F: el mismo en su versión larga. |
| `UPAA482917364058291` | UPAA sin su letra: no tiene el formato de Pitney Bowes y Peek no reconoce ningún transportista. Si eliges Pitney Bowes igualmente, Peek muestra «no emite números como este». Comprueba si falta algún carácter. |

Los espacios, guiones y minúsculas no importan en Peek. Su catálogo también conoce a Pitney Bowes como Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program y PBI.

**Dónde encontrarlo.** En el correo de envío del vendedor o en la página de tu pedido. Para eBay, consulta [pedidos de eBay](guide:ebay-orders); si no, [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Estados de seguimiento de Pitney Bowes explicados

El rastreador de Pitney Bowes lista cada línea con fecha, hora y, casi siempre, un lugar, empezando por la más reciente. Sus líneas no llevan código: Peek interpreta el texto. En el orden habitual:

**En el centro de envío.** Líneas propias de Pitney Bowes:

| Qué dice Pitney Bowes | Qué significa para ti | Qué muestra Peek |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | El vendedor envió a Pitney Bowes los datos del paquete. Pitney Bowes todavía no lo tiene. | «Etiqueta creada» |
| `Package not received by Hub or First Mile Carrier yet` | La etiqueta existe, pero ni el centro de envío ni el transportista que lo lleva hasta allí lo ha escaneado aún. | «Etiqueta creada» |
| `Processing at Shipping Center` | El centro de envío de Pitney Bowes tiene el paquete y lo está procesando. | «En tránsito» |
| `First Dimension Event`, `Second Dimension Event` | El centro mide el paquete. | «En tránsito» |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | El centro añade documentación de exportación y etiquetas. No es la aduana en sí. | «En tránsito» |
| `Container Assignment`, `Container Closed` | Colocado en un contenedor para el viaje y contenedor cerrado. | «En tránsito» |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Ha salido del centro hacia el país de destino. | «En tránsito» |
| `International Carrier` | Entregado al transportista que lo lleva al extranjero. | «En tránsito» |
| `In Transit` | En camino. Puede aparecer sin localidad. | «En tránsito» |

**Tramo internacional, aduana y entrega al correo.** En los historiales del código de Peek, los paquetes que salen del centro británico de Pitney Bowes siguen con Interpost, un socio que presenta la declaración de importación y los entrega al correo del país de destino. Las líneas de este tramo, las dos primeras escritas sin localidad mientras el centro británico procesa el paquete:

| Qué dice Pitney Bowes | Qué significa para ti | Qué muestra Peek |
| --- | --- | --- |
| `Item processed at Interpost` | El centro británico procesa el paquete para Interpost. | «En tránsito» |
| `Interpost handed item over to postal company` | Entrega por Interpost al correo del país de destino, que entregará el paquete. Aparece al mismo tiempo que la línea anterior, cuando el paquete aún puede estar en el centro. | «En tránsito» |
| `Customs Clearance` | Declaración de importación presentada en el país de destino. Trámite documental: puede surgir horas después de salir del Reino Unido, antes de que llegue allí. | «En aduanas» |
| `B2C ClearanceStatus4` | Despachado por aduana de importación. Peek lo escribe `Customs import cleared`, tal como el rastreador de Interpost traduce ese código. | «En tránsito» |
| `B2C ClearanceStatus` seguido de otro número | Un paso de despacho cuyo significado el código de Peek desconoce. Peek lo escribe `Customs clearance update`. | «En aduanas» |
| `Cleared customs` | Superada la aduana. | «En tránsito» |
| `Package is created in the system` | El siguiente transportista abre el expediente del paquete. No implica movimiento físico. | sin cambio |

**Líneas del correo.** Tras la entrega al operador postal, el rastreador retransmite sus escaneos:

| Qué dice Pitney Bowes | Qué significa para ti | Qué muestra Peek |
| --- | --- | --- |
| `Item in transit by postal company` | El correo traslada el paquete hacia ti. | «En tránsito» |
| `Parcel is out for delivery` | En reparto. | «En reparto» |
| `Item is waiting for addressee at local post office` | Te espera en tu oficina postal local: pasa a recogerlo. | «Listo para recoger» |
| `Delivered by Consignee Pickup` | Tú, el destinatario, lo recogiste. | «Entregado» |
| `Item has been delivered` | Entregado. | «Entregado» |

`Departed carrier facility` y `Processed by Carrier` también aparecen como «En tránsito». Otras líneas siguen las reglas generales de Peek; una desconocida aparece tal cual y cuenta como «En tránsito», salvo que el paquete ya esté en reparto o en una fase posterior, en cuyo caso el estado se mantiene.

**Estado general.** El rastreador también ofrece un estado global. Cuando indica entregado, Peek muestra «Entregado» aunque la última línea no lo diga. Sus demás estados globales solo cuentan mientras ninguna línea tenga estado propio. Su estado de incidencia, que ha llegado a aparecer en un paquete simplemente recogido por el destinatario, nunca prevalece sobre una línea.

## Quién entrega los paquetes de Pitney Bowes

Peek no pudo leer la web de Pitney Bowes, así que esta página no ofrece lista de socios ni países. El código de Peek muestra a Interpost encargándose del tramo desde el centro británico hasta el correo del país de destino, y un historial muestra a [Poste Italiane](carrier:poste-italiane) entregando en Italia. El rastreador de Pitney Bowes nombra al transportista que continúa el envío y puede enlazar una línea a su web de seguimiento con su propio número.

**Intentos de entrega y recogida.** Los intentos, avisos y plazos de custodia siguen las normas del correo que entrega: pregúntale o consulta su página en Peek si tiene una. Ver también [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

**Aduanas.** Pagar o no tasas a la llegada depende de tu país y de lo que el vendedor o el marketplace cobró al pagar el pedido: pregunta primero a ellos. Peek no pudo comprobar cómo gestiona Pitney Bowes los gastos de importación. Ver [paquetes retenidos en aduanas](guide:customs).

## Paquete de Pitney Bowes sin actualizaciones, perdido o devuelto

**Todavía nada.** Ante un número desconocido o un historial caducado, el rastreador de Pitney Bowes devuelve la misma respuesta que cuando no puede leer un paquete. Peek no puede distinguirlo de una caída: consulta servicios de seguimiento y, si ninguno tiene el paquete, muestra «Esperando a Pitney Bowes» y, a partir de la segunda comprobación, «Aún no se ha podido conectar con Pitney Bowes. Peek sigue intentándolo.», mientras sigue probando. Revisa el número con la tabla superior. Si tras varios días no aparece nada, pregunta al vendedor si realmente envió el paquete.

**Atascado en `Package not received by Hub or First Mile Carrier yet`.** El vendedor generó la etiqueta, pero ni el centro de envío de Pitney Bowes ni el transportista inicial ha escaneado el paquete. Pregunta al vendedor cuándo lo envió.

**Atascado en `Customs Clearance`.** La declaración está presentada y el paquete puede no haber llegado aún al país. Espera la línea de despacho, mostrada como `Customs import cleared` en Peek. Si tarda mucho, avisa al vendedor.

**Sin novedades una vez lo tiene el correo.** Tras las primeras líneas del correo, su propio rastreador puede ir por delante de lo que transmite Pitney Bowes, y Peek no cambia solo hacia él. Abre la página del correo desde la línea del rastreador de Pitney Bowes o añade el número del correo en Peek como paquete independiente.

**Varios días sin actualizaciones.** Si pasan cuatro días sin escaneo en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra un aviso («Sin novedades desde hace cuatro días» o «Sin novedades desde el …») junto con «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Pitney Bowes.» y «Pide ayuda a Pitney Bowes». Ver [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Entregado, pero no llegó nada.** Revisa el punto de entrega y pregunta a los vecinos, luego al correo que entregó y al vendedor. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Pide al vendedor o al marketplace un reembolso o un nuevo envío: ellos contrataron el transporte. Ver [paquetes perdidos](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Pitney Bowes

Peek no pudo leer la web de Pitney Bowes, por lo que esta página no recoge teléfono ni correo electrónico: Peek solo ofrece contactos leídos directamente en las páginas del propio transportista.

Pitney Bowes transporta el paquete para el vendedor o el marketplace, así que acude primero a ellos; en un pedido de eBay, a través de eBay. Una vez lo tenga el correo local, consúltale a este por la entrega.

## Seguir un paquete de Pitney Bowes con Peek

[Peek](/) identifica a Pitney Bowes al instante por un número UPAA, UPBE o PBX («Transportista detectado») y consulta su rastreador solo con ese número: sin código postal ni cuenta. Un enlace de seguimiento de trackpb.shipment.co o parceltracking.pb.com también sirve. Si solo tienes el número del primer tramo o el del correo local, pégalo igualmente: Peek sigue a más de 4.500 transportistas. Elegir Pitney Bowes con «Cambiar» para otro formato no sirve: Peek indica «no emite números como este».

Peek muestra el estado, cada línea con su localidad y el peso en tus unidades (el rastreador da libras y onzas). Omite el enlace y el número del siguiente transportista. Como el rastreador no ofrece fecha estimada ni justificante de entrega, Peek no muestra ninguno. «Abrir la web de Pitney Bowes» en la ficha del paquete abre el número en su rastreador.

Peek no transfiere el paquete al correo local: los escaneos del correo te llegan a través del rastreador de Pitney Bowes. Cuando ese rastreador no responde o no puede leer el paquete, Peek consulta servicios de seguimiento y muestra su historial en sus propias palabras.

El rastreador imprime cada hora según el reloj de quien escaneó el paquete, sin zona horaria. Peek lee cada línea en la hora de su localidad o del tramo correspondiente. Sin localidad, `In Transit` e `Item processed at Interpost` van en UTC, `Interpost handed item over to postal company` en UTC+2 y `Customs Clearance` en la hora del país que menciona. Todas las horas se muestran en la zona horaria de tu dispositivo.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta directamente a Pitney Bowes hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 según el reloj del último escaneo el día en que sale a reparto, mientras responda el rastreador de Pitney Bowes. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora; tras siete días sin novedades en «Etiqueta creada», cada 6 horas. Puede avisarte en «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Pitney Bowes, el vendedor ni el correo por ti.

## Preguntas sobre el seguimiento de Pitney Bowes

### ¿Qué es un número de seguimiento UPAA?

Un número de Pitney Bowes: UPAA, una letra y 15 dígitos. El código de Peek lee los números UPAA como paquetes de eBay desde EE. UU. y Reino Unido, los UPBE como paquetes de eBay desde Reino Unido y los PBX como otros envíos internacionales de Pitney Bowes. Su catálogo también incluye eBay Global Shipping Program como otro nombre de Pitney Bowes.

### ¿Qué significa «Interpost handed item over to postal company»?

Es la entrega de tu paquete por Interpost —que recoge los paquetes que salen del centro británico de Pitney Bowes— al correo del país de destino, que lo entregará. Pitney Bowes lo anota mientras el centro británico procesa el paquete, así que puede aparecer antes de su salida. Los escaneos del correo aparecen después en el rastreador de Pitney Bowes.

### ¿Por qué mi seguimiento de Pitney Bowes está atascado en «Customs Clearance»?

Es la declaración de importación, que puede presentarse horas después de salir del Reino Unido, antes de que llegue el paquete. Espera la línea de despacho, `Customs import cleared` en Peek. Si pasa mucho tiempo sin cambios, pregunta al vendedor.

### ¿Cómo contacto con Pitney Bowes por mi paquete?

Pregunta primero al vendedor o al marketplace: ellos contrataron el envío. Como Peek no pudo leer la web de Pitney Bowes, esta página no da teléfono ni correo electrónico.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – nombres, los tres formatos de número y qué cubren, enlaces de seguimiento, el rastreador, sus textos y etapas, líneas de Interpost y código de despacho, líneas retransmitidas por correos, Poste Italiane en un envío a Italia, estado global, relojes, peso, qué omite Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – sin transferencia al correo de entrega, servicios de seguimiento como respaldo
:::
