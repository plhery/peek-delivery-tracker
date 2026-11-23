---
title: Seguimiento Raben: número de envío, estados y entrega
description: Sigue un palé o envío de grupaje de Raben: formato del número, significado de cada estado en myRaben, descarga, reclamaciones y contacto.
slug: seguimiento-raben
published: 2026-11-22
updated: 2026-11-22
---

Raben Group es un grupo logístico familiar europeo fundado en 1931 en los Países Bajos y con sede actual cerca de Poznań, Polonia. Su red por carretera transporta palés y grupaje (cargas parciales) a través de delegaciones en 17 países europeos y también entrega pedidos de tiendas online, así que una compra voluminosa puede llegar en un camión de Raben. Síguela con el número de envío en el recuadro «Shipment tracking» de raben-group.com o pégalo arriba.

## Número de envío de Raben: cómo es y dónde encontrarlo

Raben asigna un número propio a cada envío. Lo tiene la empresa que contrató el transporte, normalmente la tienda o el fabricante: búscalo en el correo de envío o en la página de tu pedido. Las filiales polacas de Raben explican que reciben los datos del destinatario de quien contrató el transporte, así que pregunta al remitente si no tienes número. Más en [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

| Número (inventado) | Qué es |
| --- | --- |
| `274829173648291` | 27 seguido de 13 dígitos más: el formato de un número de envío de Raben en el catálogo de Peek. |
| `27 4829 1736 4829 1` | El mismo con espacios. Peek ignora espacios y guiones. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Un enlace directo a un envío en el rastreador de Raben, tal como lo abre la búsqueda en raben-group.com. Pega el enlace completo en Peek. |

[BRT](carrier:brt) en Italia y Yunda en China también usan números de 15 dígitos, por lo que este formato no siempre es de Raben. El buscador de Raben también localiza un envío por referencia o etiqueta de bulto, pero Peek solo sigue el número de envío propiamente dicho.

## Qué significan los estados de seguimiento de Raben

El rastreador de Raben, myRaben, muestra el estado, cada paso con lugar y hora, la fecha prevista de entrega, el peso, el número de bultos y el volumen, una barra de progreso, observaciones y los datos de contacto de la oficina de Raben encargada. Estos son los pasos habituales, por orden:

| Raben indica | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `registered in the system` | El remitente reservó el transporte. Raben tiene la orden, aún no la mercancía. | «Etiqueta creada» |
| `arrived to the pick-up place` | El camión ha llegado al remitente para recoger el envío. | «Etiqueta creada» |
| `collected` | Raben ha recogido la mercancía del remitente. | «Admitido por el transportista» |
| `loaded` | Cargado en un camión, rumbo al siguiente depósito o en ruta de reparto. | «En tránsito» |
| `unloaded` | Descargado en el siguiente depósito. | «En tránsito» |
| `Inventory in a warehouse` | Registrado en el almacén de un depósito. | «En tránsito» |
| `shipment in depo` | En un depósito. | «En tránsito» |
| `in progress - advice required` | En camino. La entrega debe avisarse antes al destinatario. | «En tránsito» |
| `in progress - fix delivery` | En camino, para entrega en un día acordado. | «En tránsito» |
| `in delivery` | En reparto. | «En reparto» |
| `arrived to the delivery place` | El camión ha llegado a la dirección de entrega. | «En reparto» |
| `delivered` | Entregado. | «Entregado» |
| `delivered D&D` | Entregado y descargado por el conductor. | «Entregado» |
| `not delivered - self collection` | No entregado: marcado para recogida en depósito, aunque aún puede salir a reparto. Consulta con la oficina de Raben que aparece en el rastreador antes de desplazarte. | «Listo para recoger» |
| `not delivered - consignee not available` | El conductor no te encontró. | «Intento de entrega» |

**Otras entregas fallidas.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (requiere camión con plataforma elevadora) y `not delivered - wrong/missing docs` indican el motivo. Peek muestra «Intento de entrega».

**Incidencias.** `not delivered - breakdown lorry` y `not delivered - strike` señalan retrasos en ruta; `not delivered - at sender disposal` significa que Raben retiene el envío a la espera de instrucciones del remitente; `cancelled` indica que el transporte se canceló. Peek muestra «Incidencia».

**Entregado con anotación.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` y `delivered with delay` confirman la entrega indicando qué ocurrió. Peek muestra «Entregado».

**Cambios de plan.** `new delivery date agreed with consignee` y `shipment parameters updated` cambian una fecha o datos: la mercancía no se ha movido.

**Aduanas.** Un envío que pasa aduana muestra pasos como `customs: clearance in progress` y `customs clearance completed`; `customs: lack of invoices / incomplete invoices` indica que faltan facturas, así que contacta con la tienda. Consulta [paquetes retenidos en aduanas](guide:customs) y [significado de los estados](guide:tracking-statuses).

## Cómo entrega Raben: palés, descarga y plazos

Las preguntas frecuentes sobre palés de Raben Polonia detallan el servicio. Los extras los contrata el cliente de Raben, la empresa que encargó el transporte (normalmente la tienda):

- **Descarga.** Descargar corresponde al destinatario: Raben no es responsable ni tiene que llevar carretilla elevadora ni rampa. El cliente puede contratar descarga manual por el conductor (HUN) o descarga en el punto de entrega (UNLO). Para un artículo pesado, pregunta a la tienda qué contrató.
- **Plazos.** En Polonia un palé tarda un día laborable de forma estándar, o dos según el servicio contratado; al extranjero depende del país de destino. El cliente también puede reservar día fijo (FIX), entrega antes de las 8:00, 10:00, 12:00 o 16:00 (ND 08 a ND 16), o día y hora acordados (WXACT).
- **Si no estás.** Si la entrega falla por causa del destinatario, Raben puede cobrar un cargo al cliente.
- **Fotos de entrega.** Con Picture Confirming Delivery (PCD), servicio gratuito que elige el cliente, no necesitas estar presente. El conductor deja la mercancía donde se indicó y fotografía la etiqueta, el envío y ambos juntos; las fotos valen como albarán de entrega.

## Envío de Raben retrasado, dañado o no entregado

**Sin movimiento.** Si el historial se detiene durante días, pregunta a la tienda o a la oficina de Raben que muestra el rastreador. Consulta [cuando el seguimiento no se actualiza](guide:tracking-not-updating).

**Retrasado.** En la UE, el comercio debe entregar en 30 días salvo otro plazo pactado, recuerda el portal europeo Your Europe. Pasado ese plazo puedes conceder tiempo adicional y, si sigue sin llegar, cancelar y recuperar tu dinero.

**Dañado o perdido.** El vendedor responde de los daños desde el envío hasta que recibes la mercancía, y Your Europe aconseja contactar primero con el vendedor. Consulta [paquete dañado](guide:damaged-parcel) y [paquete perdido](guide:lost-parcel). Normas de Raben:

- **Polonia.** Raben Logistics Polska admite reclamaciones por escrito del remitente o del destinatario: mediante myClaim (formulario online sin cuenta myRaben) o por correo postal, fax o correo electrónico. Haz constar los daños visibles en la entrega y comunica los daños ocultos en 7 días desde la entrega (en transporte internacional no cuentan domingos ni festivos). Raben contesta en 30 días desde que recibe toda la documentación. Un envío nacional se considera perdido desde el 31.º día tras la fecha prevista de entrega.
- **Alemania.** Raben Alemania pide comprobar embalaje y bultos en la entrega y anotar detalladamente cualquier daño visible en el albarán con la firma del conductor. Notifica el daño por escrito a la delegación responsable de Raben; los daños ocultos en cuanto los detectes y como máximo 7 días después de la entrega.

## Contacto y atención al cliente de Raben

Datos publicados en las webs de Raben:

- **Contacto de tu envío.** Raben pide contactar con la persona asignada que figura en la página de estado del envío, dato que Peek no muestra.
- **Horario.** La atención al cliente de Raben Polonia atiende de lunes a viernes de 8:00 a 16:00 (hora de Polonia), aspira a responder en 3 horas y atiende en polaco por defecto.
- **Delegaciones.** La [página de contacto](https://www.raben-group.com/contact) de Raben busca depósitos por código postal y lista oficinas por país; cada web nacional, como polska.raben-group.com, incluye sus propios contactos.

## Seguir un envío de Raben con Peek

El formato de 15 dígitos es compartido con [BRT](carrier:brt) y otros transportistas, así que [Peek](/) no puede saber que es Raben solo por el número. Pégalo igualmente: Peek sigue más de 4500 transportistas. Al añadirlo, Peek consulta a Raben y BRT, y como último recurso a Ozon si ninguno de los dos lo conoce («Comprobando servicios de seguimiento…») y muestra «Raben tiene este paquete.» cuando el rastreador de Raben lo reconoce; si varios lo conocen, eliges tú. Si ninguno lo tiene todavía, verás «Todavía no hay historial de seguimiento» mientras Peek sigue comprobando, o «Esperando a Raben» una vez elegido Raben. Para fijarlo manualmente, pulsa «Cambiar» y elige «Raben» en «Encaja con este número», o búscalo («Raben Group» también aparece). En un envío ya añadido, usa «Cambiar transportista».

Un enlace de seguimiento de myraben.com o oftc.myraben.com (…/link/ShipmentInformation?ShipmentNumber=…) asigna Raben de inmediato. Si tu número de Raben tiene otro formato, elige Raben a mano: Peek consulta el rastreador de Raben con cualquier combinación de 4 a 40 letras y dígitos que incluya al menos un dígito.

Peek lee el rastreador de Raben en inglés: el estado, cada paso con el texto de Raben y su localidad y país, el día previsto de entrega (fecha sin hora) o —cuando la ruta del conductor fija una— la franja horaria con horas, el peso y la hora de entrega. Las horas aparecen en tu zona horaria («Horas en tu zona horaria:»). Los pasos que solo modifican una fecha o parámetros mantienen el estado anterior. Peek omite el número de bultos, el volumen, la barra de progreso, las observaciones y el contacto de la delegación. El rastreador público no ofrece nombre ni dirección de remitente o destinatario, punto de recogida, servicio ni prueba de entrega.

Peek consulta directamente a Raben hasta cada 10 minutos, y hasta cada 2 minutos el día en que Raben lo marca en reparto —desde las 6:00 (hora del país de su último escaneo) o, si Raben anuncia una franja horaria, desde media hora antes de que empiece—, mientras el envío esté abierto en Peek o el día de entrega con las notificaciones activadas. Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora; tras siete días sin novedades en «Etiqueta creada», cada 6 horas. Para las notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Tras cuatro días sin escaneos en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el…» y recuerda que «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Raben.». Peek no puede contactar con Raben ni con la tienda en tu nombre, reservar descargas ni presentar reclamaciones.

## Preguntas frecuentes sobre el seguimiento de Raben

### ¿Qué es Raben Group?

Un grupo logístico familiar europeo fundado en 1931 en Winterswijk (Países Bajos) y con sede en Robakowo, cerca de Poznań (Polonia). Transporta palés y cargas parciales por carretera en 17 países, generalmente contratado por el remitente.

### ¿Cómo sigo un envío de Raben?

Introduce el número de envío en el recuadro «Shipment tracking» de raben-group.com y pulsa «Search»: el rastreador de Raben se abre en otra pestaña. O pega el número o el enlace en el campo de arriba.

### ¿El conductor de Raben descargará mi pedido?

Según las normas de Raben Polonia, solo si la empresa que reservó el transporte contrató ese extra: descargar corresponde al destinatario y la descarga por el conductor es un servicio adicional (HUN o UNLO). Un paso `delivered D&D` indica que el conductor entregó y descargó.

### ¿Cuánto tarda Raben en entregar?

Dentro de Polonia, uno o dos días laborables según el servicio contratado por el remitente; al extranjero depende del país de destino. El rastreador de Raben muestra la fecha prevista de entrega.

### ¿Con quién contacto por una entrega dañada de Raben?

Primero con la tienda: en la UE el vendedor es responsable hasta que recibes los bienes. En Polonia también puedes reclamar directamente por escrito a Raben Logistics Polska; comunica los daños ocultos en un plazo de 7 días desde la entrega.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – cuadro de seguimiento, «Search», nueva pestaña, 17 países
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – un número por envío, fecha prevista, contacto en la página de estado
- [Raben Group: Contact](https://www.raben-group.com/contact) – buscador de depósitos, delegaciones por país
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – grupaje
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – entregas para tiendas online
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – servicio gratuito, fotos, albarán
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, empresa familiar, 17 países
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo cerca de Poznań
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – descarga, HUN, UNLO, plazos, FIX, ND, WXACT, cargo por ausencia, PCD, horario de atención, polaco
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – datos del destinatario facilitados por quien contrata
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – quién reclama, procedimiento, daños visibles y ocultos, pérdida, respuesta en 30 días
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – comprobación en entrega, aviso escrito, 7 días
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 días, responsabilidad del vendedor, contactar primero con el vendedor
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – formato de número, enlaces, referencias, qué muestra y lee Peek, pasos
:::
