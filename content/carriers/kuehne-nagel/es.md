---
title: Seguimiento Kuehne+Nagel: números, estados y entregas
description: Sigue envíos terrestres, aéreos y marítimos de Kuehne+Nagel por referencia o enlace myKN: formatos de códigos, hitos, aduanas y opciones de contacto.
slug: seguimiento-kuehne-nagel
published: 2026-11-24
updated: 2026-11-24
---

Kuehne+Nagel es uno de los principales operadores logísticos mundiales, con cerca de 92.000 empleados en casi 1.300 sedes de alrededor de 100 países. Gestiona carga marítima, aérea y terrestre, y su [localizador público myKN](https://mykn.kuehne-nagel.com/public-tracking/) consulta envíos de todos los modos.

El seguimiento público en myKN cubre envíos iniciados en los últimos 90 días. Como Kuehne+Nagel mueve carga por estaciones terrestres, aeropuertos, estaciones de consolidación de contenedores (CFS), terminales ferroviarias y puertos marítimos, un mismo envío puede mostrar tanto hitos físicos de ruta como avisos separados de reserva, aduanas o documentación.

## Formatos de números de seguimiento de Kuehne+Nagel

Peek admite referencias de Kuehne+Nagel de 6 a 35 letras y dígitos con al menos un dígito, y elimina espacios y guiones. myKN busca los números de seguimiento propios de Kuehne+Nagel, cartas de porte y reservas en la misma consulta que las referencias de cliente. Peek reconoce cuatro formatos:

- **`RWM` seguido de 9 dígitos** (12 caracteres en total, como `RWM482917360`) — número de seguimiento de un envío por carretera de Kuehne+Nagel, que Peek identifica exclusivamente como de Kuehne+Nagel.
- **`RAPI` seguido de 8 dígitos** (12 caracteres en total, como `RAPI48291736`) — número de seguimiento de Kuehne+Nagel que Peek también identifica exclusivamente como propio.
- **2 dígitos, `A` y 9 dígitos** (12 caracteres en total, como `26A482917360`) — número de un envío terrestre. Este patrón coincide también con [Australia Post](carrier:australia-post) y [Colis Privé](carrier:colis-prive), de modo que Peek pregunta a Australia Post, Colis Privé y Kuehne+Nagel por orden (Kuehne+Nagel en segundo lugar en Suiza y Alemania).
- **`NTG` seguido de 9 dígitos** (12 caracteres en total, como `NTG482917360`) — número de seguimiento propio de un envío de Kuehne+Nagel. Este patrón coincide también con [Colis Privé](carrier:colis-prive), [Purolator](carrier:purolator) y TNT Australia: Peek pregunta a los cuatro y sigue el paquete con el que conoce el número.

En carga aérea y marítima, los remitentes pueden entregar además una carta de porte de Kuehne+Nagel, un conocimiento de embarque interno (*house bill of lading*) o una referencia de cliente fuera de esos cuatro patrones. Si pegas un enlace de `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, Peek extrae la referencia y la consulta directamente con Kuehne+Nagel. Para comparar otros formatos de carga y paquetería, consulta la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Cómo funcionan los hitos de Kuehne+Nagel

El localizador público de Kuehne+Nagel divide un envío en dos fuentes: paradas físicas a lo largo de la ruta (origen, terminal de salida, hub de tránsito, pasarela, terminal de llegada y destino) y avisos de información del transporte (reservas, declaraciones aduaneras y justificantes de entrega).

| Texto de Kuehne+Nagel | Qué significa |
| --- | --- |
| Booking submission / Booking confirmation | La orden queda registrada en el sistema de Kuehne+Nagel antes de que la carga se mueva; un cambio tardío de reserva nunca hace retroceder un envío ya en marcha. |
| Ready for pickup / Arrival (at origin) | La mercancía está lista en el remitente o el camión de recogida ha llegado, antes de confirmarse la carga. |
| Pickup | Kuehne+Nagel ha recogido la carga en origen. |
| Arrival / Departure / Load on vehicle / Load on vessel | Escaneos físicos en terminal, plataforma, rampa ferroviaria, aeropuerto o puerto durante el transporte principal. |
| Customs entry start / Customs duties payment / Entry documents required | El despacho aduanero de exportación o importación está en marcha, o las autoridades han solicitado documentos, aranceles o inspección de una agencia (como FDA o PGA). |
| Customs entry release / Export customs entry release / Import customs entry release | La aduana o el organismo fronterizo correspondiente ha liberado la carga para seguir avanzando. |
| In delivery | El vehículo de carretera ha salido en el tramo final de reparto o ha llegado al punto de entrega. |
| Delivery / Freight collection / Proof of delivery available | La mercancía se ha entregado al destinatario o retirado en la terminal; `Delivery with discrepancies` marca igualmente entrega dejando constancia de falta o daño. |
| Delivery attempt failed / Delivery not possible | Un intento de entrega no prosperó, por ejemplo por rechazo del destinatario. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | El envío permanece retenido en una estación o en aduana a la espera de documentos faltantes, pagos o instrucciones de entrega. |

Algunos hitos merecen una lectura atenta:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** indican que el envío llegó a una terminal de contenedores, estación CFS, aeropuerto o terminal ferroviaria y está listo para entrega a otro agente o retirada. Kuehne+Nagel o un socio puede encargarse todavía del tramo final por carretera, por lo que estos escaneos siguen en tránsito y no marcan entrega final.
- **Freight handover** significa que Kuehne+Nagel entregó la carga a un agente de aduanas o transportista siguiente, no al destinatario final.
- **Avisos documentales y de presentación** como `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` y `Customs billing invoice available` registran pasos administrativos y no cambian la fase física del envío.
- **Empty container gate-in y salida del vehículo en destino** se producen tras completarse la entrega y mantienen el estado de entregado.

## Entrega, aduanas y retrasos

`Delivery appointment submission` registra una fecha de entrega acordada para más adelante. No es un escaneo de reparto: el envío solo pasa a reparto cuando aparece `In delivery` o el evento `Arrival` del camión en la dirección de entrega.

Si el seguimiento muestra `Awaiting consignee's feedback`, `Awaiting consignee's payment` o `Entry documents required`, la aduana espera respuesta, pago o documentación del destinatario. `Unable to file customs entry - missing details or documentation` indica que la declaración aduanera no puede presentarse hasta recibirlos. Consulta la [guía de aduanas](guide:customs) para saber cómo funcionan los despachos y cargos de importación.

Las fechas planificadas mostradas en myKN son previsiones y no escaneos realizados. Para orientación general sobre seguimiento detenido, lee [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).

## Seguir Kuehne+Nagel con Peek

Cuando pegas un número `RWM` o `RAPI` o un enlace `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` en Peek, Peek reconoce a Kuehne+Nagel al instante. Con un número `26A...`, Peek consulta por orden a los transportistas anteriores y muestra «Kuehne+Nagel tiene este paquete.» cuando Kuehne+Nagel responde. Para una carta de porte o referencia de cliente con otro formato, selecciona Kuehne+Nagel con **Cambiar** al añadir el paquete o **Cambiar transportista** en la pantalla de seguimiento.

Peek consulta directamente la búsqueda pública de envíos, los hitos de ruta y los avisos de transporte de Kuehne+Nagel mediante el [adaptador abierto de Kuehne+Nagel en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). Solo registra hitos realmente alcanzados (ignorando fechas previstas) y lee el peso total en kilogramos y el país de destino cuando aparecen. Si la búsqueda localiza el envío mediante una referencia de cliente, Peek muestra el número propio de Kuehne+Nagel junto al tuyo bajo «Kuehne+Nagel lo sigue con el número»; si una referencia coincide con varios envíos, Peek no puede saber cuál es el tuyo. Cuando el sistema de escaneo conoce el desfase UTC, Peek mantiene ese instante exacto; en caso contrario fecha el escaneo en la zona horaria del lugar (hora de Berlín para Hamburgo, hora de Zúrich para Basilea), un escaneo sin lugar en la del escaneo contiguo, si no en hora de Zúrich, y muestra cada escaneo en la hora de tu dispositivo.

Peek asigna los hitos de Kuehne+Nagel a fases claras: **Etiqueta creada** para reservas y avisos de listo para recogida, **Admitido por el transportista** para recogida, **En tránsito** para movimientos por hubs, puertos, ferrocarril y entregas intermedias, **En aduanas** para despachos y retenciones aduaneras, **En reparto** para `In delivery` y llegada del camión a destino, **Intento de entrega** para intentos fallidos, **Incidencia** para retenciones, recogidas fallidas, cancelaciones y documentación faltante, y **Entregado** para entrega, retirada de carga o prueba de entrega. Los avisos administrativos permanecen en el historial sin hacer retroceder la fase actual, y un trámite aduanero posterior a la entrega nunca revierte un envío ya entregado.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Kuehne+Nagel muestra `In delivery` o `Arrival` del camión en la dirección de entrega, cada 2 minutos desde las 6:00 en la hora del último escaneo mientras el localizador de Kuehne+Nagel responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando Kuehne+Nagel no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Kuehne+Nagel.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Kuehne+Nagel.»

Puedes elegir alertas de notificación para **Cada escaneo**, **Pasos importantes** o **Solo la entrega** en los ajustes del paquete.

## Contactar con Kuehne+Nagel

El sitio web de Kuehne+Nagel ofrece un buscador de oficinas y un formulario de contacto:

- **Sitio global y portal de seguimiento:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) y [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Directorio de oficinas locales:** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations) para localizar la delegación nacional o local más cercana
- **Formulario de consulta en línea:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) para recibir respuesta por correo electrónico
- **Otras redes de carga:** si tu pedido se dividió entre varios transportistas, compara con [DHL Freight](carrier:dhl-freight) y [DSV](carrier:dsv).

## Preguntas frecuentes

### ¿Por qué mi número de seguimiento de Kuehne+Nagel dice que no hay resultados?

El seguimiento público de Kuehne+Nagel solo cubre envíos iniciados en los últimos 90 días. Comprueba que no falten caracteres y, si la reserva se acaba de crear, espera al primer hito alcanzado. Si una referencia coincide con varios envíos a la vez, pide al remitente el número `RWM`, `RAPI` u otro código propio de Kuehne+Nagel.

### ¿Qué significan «Service complete» o «Freight handover» en Kuehne+Nagel?

`Freight handover` indica que Kuehne+Nagel transfirió la carga a un agente de aduanas o transportista siguiente, por lo que el envío sigue en camino hacia ti. `Service complete` marca el final del tramo contratado a Kuehne+Nagel — que puede ser una entrega en puerto o aeropuerto en lugar de tu puerta —, así que comprueba si se emitió un número aparte para el transportista final.

### ¿Por qué algunas fechas de myKN no aparecen en el historial de escaneos de Peek?

La vista pública de ruta de Kuehne+Nagel muestra fechas previstas junto a escaneos realizados y avisos futuros sin fecha. Peek registra solo los hitos que Kuehne+Nagel marca como realmente completados, de modo que las previsiones no aparezcan como movimientos efectuados.

### ¿Cuánto tiempo permanece disponible el seguimiento público de Kuehne+Nagel?

El seguimiento público en `mykn.kuehne-nagel.com` cubre envíos iniciados durante los últimos 90 días. Para ver más historial, myKN ofrece un registro gratuito.

:::sources
- [Sitio global de Kuehne+Nagel](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Oficinas de Kuehne+Nagel en el mundo](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [adaptador de Kuehne+Nagel en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
