---
title: Seguimiento FAR International: números, estados y contacto
description: Sigue un paquete de FAR International (泛远国际) desde China: formato FAR…YQ, significado de cada estado, quién entrega, aduanas UE y contacto.
slug: seguimiento-far-international
published: 2026-11-22
updated: 2026-11-22
---

FAR International (泛远国际, FAR International Logistics) es una empresa de logística transfronteriza con sede en Hangzhou, China, fundada en 2004. Tiendas online y marketplaces la contratan para enviar pedidos al extranjero: FAR recoge y revisa los paquetes en China, mientras sus socios gestionan el traslado internacional, las aduanas y la entrega final con un transportista local en tu país. Puedes seguir el envío en la [página Track and Trace de FAR](https://www.far800.com/en/logistics), en inglés, o pegando el código en el cuadro de arriba. El número te lo facilita la tienda con los datos de envío.

## Número de seguimiento de FAR International: cómo es

FAR no publica un formato oficial. Esta es la estructura que Peek identifica como propia de FAR (dígitos inventados):

| Número | Qué es |
| --- | --- |
| `FARAA4829173654YQ` | `FAR`, dos letras, 10 dígitos y `YQ`: un número de FAR International. |
| `FARAA482917365YQ` | Solo 9 dígitos: no encaja con FAR. Peek no asigna FAR International; revisa si falta un dígito. |

En Peek las minúsculas, espacios y guiones no importan. El buscador de FAR requiere el código completo y admite hasta 50 números a la vez, uno por línea.

**Dónde encontrarlo.** En el correo de envío o en la página del pedido. Si no aparece, revisa [dónde encontrar tu número de seguimiento](guide:find-tracking-number), o las guías de [pedidos de Temu](guide:temu-orders) y [pedidos de SHEIN](guide:shein-orders).

**Un segundo número.** La página de resultados de FAR también puede mostrar un número de última milla (尾程单号), asignado por el transportista que reparte en tu país para seguirlo en su web.

## Estados de seguimiento de FAR International explicados

Los registros de almacén y vuelo de FAR suelen venir en chino seguido de inglés, como `仓库收到包裹 Arrived at Warehouse`: Peek conserva la parte inglesa. Los pasos posteriores pueden aparecer solo en inglés o solo en chino. Cada línea incluye hora y lugar, y el tracker de FAR tiene una columna de zona horaria. Los estados habituales, en orden:

| Qué dice FAR | Qué significa para ti | Qué muestra Peek |
| --- | --- | --- |
| `Arrived at Warehouse` | El almacén de FAR en China tiene el paquete. | «Admitido por el transportista» |
| `Inspection by Warehouse` | El almacén revisa la mercancía (FAR abre los paquetes para inspeccionarlos al recogerlos). | «Admitido por el transportista» |
| `Await Release from Sender` | El almacén retiene el paquete hasta que el vendedor lo autorice. | «Admitido por el transportista» |
| `Departed Warehouse`, `Pickup Scan` | Salió del almacén de FAR y fue recogido para el siguiente tramo. | «En tránsito» |
| `In Transit to Export Port` | De camino al aeropuerto o puerto en China. | «En tránsito» |
| `Export Custom Clearance Processing and Flight Booking` | Despacho de exportación en China y reserva de vuelo: trámite habitual antes del vuelo, no es la aduana de tu país. | «En tránsito» |
| `Scheduled to Take off`, `Export Scan` | En espera de despegue y escaneado para exportación. | «En tránsito» |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | En el centro de salida y ya en vuelo. | «En tránsito» |
| `Arrived at Facility`, `Departed from Facility` | Llegada y salida en un centro de tránsito. | «En tránsito» |
| `Last Mile Label Create` | Se creó la etiqueta del transportista local (a menudo sale tras el centro de salida: no es un retroceso). | el estado anterior |
| `At destination country` | Llegó a tu país; a continuación vienen aduanas y clasificación. | «En tránsito» |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Un agente aduanero lo recogió y empezó el despacho de importación. | «En aduanas» |
| `Customs Released`, `Import Scan`, `进口清关完成` | Liberado por la aduana de importación. | «En tránsito» |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | FAR lo entregó al transportista que reparte en tu país. | «En tránsito» |
| `包裹开始派送` | Ese transportista se ha hecho cargo; sus escaneos vienen después (todavía no implica reparto inmediato). | «En tránsito» |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | Avanzando entre centros de clasificación. | «En tránsito» |
| `Out For Delivery` | En reparto hacia tu dirección. | «En reparto» |
| `DELIVERED` | Entregado. | «Entregado» |

Tras el traspaso, el historial de FAR puede mostrar textos propios del transportista final, como los de FedEx (`Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` como «En reparto» y `Package delivered` como «Entregado»). Para otros textos, Peek interpreta términos habituales como delivered o customs; una línea sin coincidencia se mantiene con el estado previo.

Las FAQ de FAR indican que `RETURN` señala una incidencia en la entrega. Si una línea dice solo `RETURN`, Peek mantiene el estado anterior: lee el mensaje y consulta con el vendedor.

## Quién entrega los paquetes de FAR International

FAR se define como una empresa «light asset»: sus oficinas en China recogen, inspeccionan, embalan, etiquetan y clasifican los paquetes, mientras sus proveedores (operadores portuarios y aeroportuarios, aerolíneas, navieras, agentes aduaneros y empresas logísticas terceras) llevan las aduanas, el trayecto internacional y la última milla. FAR no publica qué transportista entrega en cada país: busca el número de última milla en la página de resultados de FAR.

FAR tampoco publica reglas sobre intentos de entrega, avisos ni puntos de recogida, porque dependen del transportista local. Sigue el número de última milla en su web y revisa [entrega fallida](guide:missed-delivery).

**Aduanas en la UE.** Las compras online fuera de la UE pagan desde el 1 de julio de 2026 un arancel de 3 € por artículo en paquetes de hasta 150 €, y desde el 1 de noviembre de 2026 una tasa de gestión de la UE de 2 € por artículo cualquiera que sea su valor. Se considera un artículo al conjunto de bienes con igual partida arancelaria, descripción y, cuando corresponda, origen. FAR no explica cómo se abonan estos cargos. Fuera de la UE depende de tu país: consulta [paquete retenido en aduanas](guide:customs).

**Si la aduana te necesita.** Las FAQ de FAR señalan que un problema de despacho en destino requiere ayuda del destinatario ante las autoridades locales. Si no se resuelve en plazo, el paquete se abandona o devuelve según las normas locales y el remitente asume los gastos.

## Paquete de FAR International sin novedades, perdido o devuelto

FAR trabaja para tiendas y marketplaces, y sus FAQ los remiten a su gestor de cuenta. Por eso, ante un retraso, pérdida, daño o devolución, contacta primero con el vendedor.

**Sin datos todavía.** Si FAR aún no registra el código, Peek muestra «Esperando a FAR International» y sigue comprobando. Si pasan varios días sin cambios, confirma el número con el vendedor.

**Varios días sin novedades.** Las FAQ de FAR advierten de que el seguimiento aéreo puede actualizarse con retraso. Tras cuatro días sin nuevos pasos en tránsito, Peek muestra en tu lista el día del último escaneo («Sin novedades desde el …») y en la ficha «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a FAR International.». Consulta [por qué el seguimiento no se actualiza](guide:tracking-not-updating) y [seguir paquetes desde China](guide:tracking-from-china).

**Entregado pero no está.** Las FAQ de FAR recomiendan revisar alrededor de casa (rincones, entrada, apartado postal, taquilla u otro lugar posible) y preguntar a familiares y vecinos. Si sigue sin aparecer, avisa de inmediato al vendedor: la investigación de reclamación debe iniciarse dentro de los **14 días** posteriores a la fecha de firma, y la abre el vendedor ante FAR. Ver [entregado pero no recibido](guide:delivered-not-received).

**Dañado.** Conserva la caja exterior y el producto dañado, tal como piden las FAQ de FAR, y avisa al vendedor para que gestione la reclamación. Ver [paquetes dañados](guide:damaged-parcel).

**Devuelto o perdido.** Solicita reenvío o reembolso a la tienda. Ver [devuelto al remitente](guide:returned-to-sender) y [paquetes perdidos](guide:lost-parcel).

## Atención al cliente y contacto de FAR International

Datos publicados en la [web inglesa de FAR](https://www.far800.com/en):

- **Línea global:** 400-128-5656 (número de servicio en China; la página de servicios cita una línea 24/7 para clientes, sin indicar si funciona desde el extranjero ni los idiomas disponibles).
- **Correo electrónico:** csc@far800.com.
- **Dirección:** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, China, código postal 310022.

Sobre tu pedido pregunta primero al vendedor, que es quien contrató a FAR.

## Seguir un paquete de FAR International con Peek

[Peek](/) identifica a FAR International al instante desde cualquier código `FAR…YQ` («Transportista detectado»). También funciona pegar un enlace de resultados de far800.com con `key=` y un código FAR. Si tienes otro número, como el de última milla, pégalo igual: Peek sigue más de 4500 transportistas y asigna el transportista cuando el formato es exclusivo. Si coincide con varios, muestra «Comprobando servicios de seguimiento…» mientras pregunta; si no encaja con ninguno, lo busca al añadirlo. Si hace falta, pulsa «Cambiar» y selecciónalo en «Todos los transportistas», o usa «Cambiar transportista» en un paquete ya guardado. FAR International solo responde a códigos `FAR…YQ`.

Peek lee directamente el seguimiento de FAR: el estado, cada línea con lugar y hora, la hora de entrega y el número de última milla. Cuando FAR incluye zona horaria, Peek la interpreta en esa zona y la muestra en la tuya (al final del historial aparece «Horas en tu zona horaria:» con su nombre). FAR no facilita fecha prevista de entrega ni peso.

Cuando FAR entrega un número de última milla, Peek pregunta al transportista correspondiente: al correo nacional de tu país si es un código postal (dos letras, nueve dígitos, dos letras), como [Correos](carrier:correos-spain) en España, o al transportista que corresponda por formato. En cuanto ese transportista registra un paso al menos tan reciente como el último de FAR, Peek pasa a seguirlo allí conservando el historial previo y muestra quién reparte (por ejemplo, «Entrega con Correos»). Si no hay tal transportista, sigue con FAR.

Mientras tengas el envío abierto en Peek — o el día de entrega con notificaciones activadas — Peek consulta directamente a FAR hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 hora local el día en que sale a reparto; fuera de esos casos, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puedes recibir avisos en «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con FAR ni con el vendedor en tu nombre.

## Preguntas sobre FAR International

### ¿Es fiable FAR International?

Sí. FAR International Logistics se fundó en Hangzhou en 2004. Su matriz, FAR International Holdings Group, nació en noviembre de 2022 y salió a bolsa en Hong Kong en diciembre de 2023 según sus noticias. FAR señala que empezó a operar para UPS China en 2005, lanzó un proyecto transfronterizo con Temu en 2023 y abrió cooperación estratégica con TikTok y SHEIN en 2024.

### ¿Cuánto tarda FAR International en entregar?

Para sus clientes publica estos plazos orientativos: Express de 3 a 5 días laborables, Standard de 6 a 9 y Economy de 10 a 30, sujetos al código postal de destino. Toma como referencia la estimación de tu tienda.

### ¿Qué significa «Await Release from Sender»?

El almacén de FAR en China tiene el paquete retenido a la espera de confirmación del vendedor. Si no avanza, pregunta a la tienda.

### ¿Cómo contacto con FAR International?

A través de la línea global 400-128-5656 o en csc@far800.com. Para incidencias de un pedido, contacta primero con el vendedor.

:::sources
- [FAR International: Home](https://www.far800.com/en) – empresa, 泛远国际, 2004 y 2022, salida a bolsa en Hong Kong (22 de diciembre de 2023), línea global, correo y dirección
- [FAR International: Track and Trace](https://www.far800.com/en/logistics) – código completo y hasta 50 números uno por línea
- [FAR International: About FAR](https://www.far800.com/en/about) – 2004, 2022, contratista de UPS China en 2005, Temu 2023, TikTok y SHEIN 2024
- [FAR International: Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – modelo light asset, proveedores para aduanas y última milla, inspección al recoger, plazos Express/Standard/Economy, atención 24/7
- [FAR International: FAQs](https://www.far800.com/en/service/qa) – retrasos en vuelos, RETURN, entregado pero no recibido (14 días), daños, aduanas en destino
- [FAR International: Privacy Policy](https://www.far800.com/en/support/privacy) – razón social, teléfono, correo y dirección
- [Comisión Europea: reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de gestión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: guía sobre el arancel de 3 € y la tasa de gestión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artículo sea cual sea el valor y definición de artículo
- [Universal Parcel Scraper: adaptador FAR International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/far-international) – formato FAR…YQ, enlaces, estados, número de última milla, zonas horarias y traspaso
:::
