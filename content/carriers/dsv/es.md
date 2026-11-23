---
title: Seguimiento DSV: números, estados y DB Schenker
description: Sigue un envío de DSV o antiguo DB Schenker (carretera, aire o mar): qué número usar, significado de los estados, entrega, reclamos y contacto.
slug: seguimiento-dsv
published: 2026-11-23
updated: 2026-11-23
---

DSV es un grupo danés de transporte y logística que mueve carga por carretera, aire y mar, y en 2025 adquirió Schenker (DB Schenker) a Deutsche Bahn. En dsv.com, el cuadro «Track your shipment» tiene dos opciones: «Track DSV shipment» abre el rastreador de DSV, myDSV, y «Track Schenker shipment» el antiguo de DB Schenker. No hace falta cuenta. El número te lo facilita quien reservó el transporte, normalmente el remitente o la tienda: pégalo en el recuadro superior.

## Número de seguimiento de DSV: cómo es y dónde encontrarlo

El número lo tiene la empresa que contrató a DSV: búscalo en el correo de envío de la tienda, en tu página de pedido o pídelo al remitente. Más en [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

Mientras DSV integra Schenker en sus sistemas, mantiene los dos rastreadores: un envío contratado con Schenker puede aparecer solo en el de Schenker. Si uno no muestra nada, prueba el otro.

| Número (inventado) | Qué es |
| --- | --- |
| `DEKLM482917365` | 14 caracteres: un código de país europeo como DE, SE, DK o PL, tres letras y nueve dígitos. Antiguo número terrestre de DB Schenker y el formato que el catálogo de Peek asigna a DSV. |
| `KLM4829173` | Tres letras y siete u ocho dígitos: otro formato terrestre antiguo de DB Schenker. Peek no identifica DSV por esta forma. |
| `094827361549283711` | 18 dígitos (20 con `00` delante): el código de barras de un bulto de un antiguo envío terrestre de DB Schenker. |
| Letras y dígitos | Número de envío, reserva o *house bill* de myDSV. Peek consulta myDSV con cualquier código de 5 a 40 letras y dígitos que contenga al menos un dígito. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Enlace al rastreador de myDSV, tal como lo abre «Track DSV shipment» en dsv.com. |

Una referencia de cliente o pedido, o la guía aérea principal (*master air waybill*), puede localizar envíos en la búsqueda de myDSV, pero Peek no sigue esas referencias: usa el número de envío, reserva o *house bill* de DSV. Peek tampoco puede elegir uno de varios envíos si un número coincide con varios.

## Qué significan los estados de seguimiento de DSV

myDSV registra cada escaneo desde la reserva hasta la prueba de entrega. Su vista pública ofrece información general y la privada, bajo solicitud, el detalle completo. Estas son las etapas de myDSV que lee Peek:

| DSV muestra | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `Booking` | DSV tiene la reserva, con fecha pero sin hora. | «Etiqueta creada» |
| `Creation` | El envío se creó en el sistema terrestre de DSV. | «Etiqueta creada» |
| `Actual pickup` | DSV lo recogió en el remitente. | «Admitido por el transportista» |
| `Cargo received` | La mercancía llegó a DSV, en una terminal o tras la recogida. | «Admitido por el transportista» |
| `Actual Departure`, `Actual departure - Origin Port` | Salió de un puerto o aeropuerto en el tramo principal. | «En tránsito» |
| `Actual arrival`, `Actual arrival- Destination Port` | Llegó a un puerto o aeropuerto en el tramo principal. | «En tránsito» |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV entregó la carga al término de su servicio, cerrando el envío (el primero conserva la grafía de DSV). | «Entregado» |
| `Actual delivery` | Entregado. | «Entregado» |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` y `Requested delivery` son previsiones, no escaneos: Peek los omite del historial y muestra `Estimated delivery` como día previsto de entrega (o rango de días) hasta la entrega.

El antiguo rastreador de DB Schenker cubre envíos por carretera con sus propios textos:

| El rastreador de DB Schenker muestra | Qué significa para ti | Estado en Peek |
| --- | --- | --- |
| `Booked` | El remitente reservó el transporte. | «Etiqueta creada» |
| `Out for collection` | Un conductor va a recogerlo al remitente. | «Etiqueta creada» |
| `Collected` | Recogido en el remitente. | «Admitido por el transportista» |
| `Delivered to terminal by shipper` | El remitente lo llevó a una terminal. | «Admitido por el transportista» |
| `Arrived`, `Departed` | Llegó o salió de una terminal. | «En tránsito» |
| `Customs clearance initiated` | Comenzó el despacho aduanero. | «En aduanas» |
| `Customs clearance finalised` | Despachado y nuevamente en camino. | «En tránsito» |
| `Out for Delivery` | En el camión de reparto. | «En reparto» |
| `Not delivered` | Entrega fallida; puede añadir un motivo. | «Intento de entrega» |
| `To consignee's disposal`, `Ready for pick up` | Disponible para que lo recojas. | «Listo para recoger» |
| `Delivered` | Entregado. | «Entregado» |
| `Picked up by consignee`, `Picked up at agent` | Recogido por el destinatario o en un agente. | «Entregado» |
| `ePOD available` | Ya está disponible la prueba electrónica de entrega. | «Entregado» |

Durante el trayecto también aparecen `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` y `Vessel/Train Unloaded`: todos constan como «En tránsito» en Peek. `Not Collected` (recogida fallida) y `Not loaded` (perdió la carga prevista, con motivo) muestran «Incidencia». Cuando el rastreador incluye un motivo junto a un paso, Peek lo añade al texto. Más en [significado de los estados de seguimiento](guide:tracking-statuses).

## Cómo entrega DSV

**Palés y cargas parciales.** DSV define su red de grupaje terrestre como la mayor de Europa: más de 36 países, más de 400 terminales y envíos de hasta 2.500 kg, por lo que un palé o pedido voluminoso puede llegar en camión desde una terminal de DSV.

**Pedidos online.** El servicio Parcel E-commerce de DSV recoge los paquetes de una tienda, los consolida entre países y encarga la entrega final a redes locales, así que puede entregártelo otro transportista. Si el correo de la tienda nombra uno o da un segundo número, síguelo también: la consulta DSV de Peek no señala ningún transportista siguiente.

**Avisos y prueba de entrega.** En myDSV se pueden configurar notificaciones (recogida o despacho aduanero), ver horarios estimados de llegada en transporte terrestre y descargar la prueba de entrega al finalizar. Si no contrataste tú a DSV, pregunta al remitente. Las páginas de DSV no indican cuántos intentos de entrega hace ni cuánto tiempo guarda una terminal la mercancía: consulta con la oficina local de DSV.

**Aduanas.** En la mayoría de países donde tiene oficinas, DSV actúa como agente de aduanas autorizado; en otros trabaja con agentes. Para ayuda con un despacho, DSV indica contactar con DSV en ese país. En la UE, las compras online de fuera de la UE pagan un arancel de 3 € por tipo de producto en pedidos hasta 150 € desde el 1 de julio de 2026 y una tasa de gestión de la Unión de 2 € por tipo de producto, cualquiera que sea el valor, desde el 1 de noviembre de 2026. Fuera de la UE aplican las normas de tu país: ver [paquetes retenidos en aduanas](guide:customs).

## Envío de DSV retrasado, bloqueado o dañado

**Sin movimiento.** Si un rastreador se detiene, prueba el otro en dsv.com: un envío de Schenker puede no estar aún en myDSV. Después contacta con el remitente que reservó DSV. Ver [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Mercancía dañada o extraviada.** Para un pedido online empieza por la tienda que contrató a DSV. La herramienta eClaims de DSV recoge reclamaciones y documentos, muestra su estado y te conecta con los tramitadores locales; no indica un plazo concreto allí, así que no esperes. Ver [paquetes dañados](guide:damaged-parcel) y [paquetes perdidos](guide:lost-parcel).

**Uso fraudulento del nombre de DSV.** DSV advierte de que empresas o personas ajenas usan a veces su nombre para ofrecer transporte. Si dudas de una web o contacto, compruébalo con tu oficina local de DSV; para SMS sospechosos sobre paquetes, ver [mensajes falsos de entrega](guide:delivery-scam-texts).

## Contacto y atención al cliente de DSV

En dsv.com:

- **Formulario de contacto:** en la [página de ayuda y contacto de DSV](https://www.dsv.com/en/support/help-and-contact).
- **Oficinas locales:** DSV opera en más de 90 países; [encuentra tu oficina local de DSV](https://www.dsv.com/en/support/find-dsv) por país.
- **Reclamaciones:** mediante eClaims desde la [página Claims de DSV](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S:** Hedehusene, Dinamarca, +45 43 20 30 40 según su aviso legal; no es una línea de seguimiento.

Las páginas de ayuda de DSV no dan ningún teléfono para seguimiento.

## Seguir un envío de DSV con Peek

Pega un número de 14 caracteres como `DEKLM482917365` y Peek consulta a DSV al añadirlo («Comprobando servicios de seguimiento…»), mostrando «DSV tiene este paquete.» cuando DSV lo reconoce. Si DSV aún no lo lista, aparece «Esperando el primer escaneo de DSV» con la etiqueta «Probablemente DSV» mientras Peek sigue preguntando; tras una semana, Peek sugiere revisar el número o elegir otro transportista.

Peek no identifica DSV por ningún otro formato. Pégalo igualmente: Peek sigue más de 4500 transportistas. Para fijar DSV tú mismo, pulsa «Cambiar» y busca DSV («Schenker» también lo encuentra: «También llamado DB Schenker»). En un paquete ya añadido, usa «Cambiar transportista». Un enlace de mydsv.com (`?query=`) clasifica el envío bajo DSV al instante, igual que un enlace del rastreador de Schenker en dsv.com con `refNumber=`.

Peek pregunta primero a myDSV. Un antiguo número terrestre o código de barras de DB Schenker que myDSV no conozca pasa al rastreador de Schenker, que Peek lee solo para envíos por carretera: si se trata de un envío aéreo o marítimo presente solo allí, usa «Track Schenker shipment» en dsv.com.

Peek muestra el estado, cada paso con el texto de DSV junto a su ciudad y país, y el día previsto de entrega. DSV ofrece la hora local de cada escaneo sin zona horaria: Peek calcula el huso a partir del lugar (ciudad, puerto o código de aeropuerto) o de los escaneos cercanos, o bien usa la hora de Copenhague, por lo que ocasionalmente puede fallar. Peek omite el nombre del servicio, la ciudad del remitente, tu dirección, los códigos postales y la barra de progreso de DSV, y del rastreador Schenker las partes, referencias, mercancías y quien firmó.

Si la empresa sueca Early Bird entrega tu paquete a DSV o DB Schenker, Peek indica DSV como transportista repartidor y sigue a DSV cuando muestre escaneos nuevos.

Mientras el envío está abierto en Peek, o el día de la entrega con las notificaciones activadas, Peek lo comprueba hasta cada 10 minutos, y cada 2 minutos desde las 6:00, hora local, el día que DSV lo marca en reparto. Puede avisarte de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora, y cada 6 horas tras siete días sin novedades en «Etiqueta creada». Si el envío pasa cuatro días sin una línea nueva en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el …» con el día de esa línea y un enlace «Pide ayuda a DSV». No puede contactar con DSV ni con el remitente, reservar entregas ni abrir reclamaciones.

## Preguntas sobre el seguimiento de DSV

### ¿Es DSV la misma empresa que DB Schenker?

Sí. DSV, fundada en 1976, completó la compra de Schenker a Deutsche Bahn el 30 de abril de 2025, y dbschenker.com se integró en dsv.com. Los envíos de Schenker mantienen de momento su propio rastreador.

### ¿Cómo sigo ahora un envío de DB Schenker?

Escribe el número en «Track Schenker shipment» dentro del cuadro «Track your shipment» de dsv.com, o pégalo arriba; salvo que tenga 14 caracteres empezando por código de país, elige DSV tú mismo. Peek pregunta a myDSV y luego al rastreador Schenker si es por carretera.

### ¿Qué significa «handover of cargo» en el seguimiento de DSV?

DSV ha entregado la mercancía al concluir su servicio y el envío figura completo; Peek muestra «Entregado». Si no has recibido nada, pregunta al remitente dónde la entregó DSV y consulta [entregado pero no recibido](guide:delivered-not-received).

### ¿Tiene DSV teléfono de atención al cliente?

Sus páginas de ayuda no publican ninguno para seguimiento. Usa el formulario de contacto o busca tu oficina local de DSV en dsv.com. El teléfono del aviso legal de DSV A/S (+45 43 20 30 40 en Dinamarca) no es una línea de seguimiento.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – seguimiento sin cuenta, dos campos, fusión de dbschenker.com en dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – vista pública y privada, registro de escaneos, avisos, prueba de entrega
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – rastreadores DSV y Schenker, eClaims, sistemas Schenker hasta la migración
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – formulario de contacto, dos versiones de myDSV durante la transición
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – más de 90 países, buscador de oficinas
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – oficinas por país
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – qué hace eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ países, 400+ terminales, hasta 2.500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – consolidación internacional, redes locales de última milla
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – agente de aduanas autorizado, agentes, contactar con DSV en ese país
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – fundada en 1976, carretera, aire y mar, compra de Schenker en 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – completada el 30 de abril de 2025, integración
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S en Dinamarca, teléfono, uso indebido del nombre DSV
- [Comisión Europea: reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por tipo de producto hasta 150 €, tasa de gestión de 2 €
- [Comisión Europea: guía sobre el arancel de 3 € y la tasa de gestión de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por tipo de producto cualquiera que sea el valor
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – formatos y enlaces, ambos rastreadores, textos y etapas DSV, horas locales, lo que lee Peek
:::
