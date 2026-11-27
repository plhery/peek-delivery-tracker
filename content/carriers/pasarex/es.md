---
title: Rastreo PasarEX — códigos AMZPSR, aduana, PQR y teléfonos
description: Rastreo PasarEX (Pasar Express) en Colombia, Chile, Panamá y Barbados: guía AMZPSR de Amazon, estados de aduana, reclamos PQR y teléfonos de soporte.
slug: rastreo-pasarex
published: 2026-11-23
updated: 2026-11-27
---

PasarEX (marca comercial de *PASAR EXPRESS SAS*, empresa colombiana fundada en 1993) gestiona transporte expreso, carga aérea, despacho aduanero y última milla para comercio electrónico en **Colombia**, **Chile**, **Panamá** y **Barbados**, con centro logístico en Miami y rutas desde China. Para rastrear tu envío — incluida una guía `AMZPSR` de Amazon o un enlace `tracking.pasarex.com/?awb=...` — pégalo en la casilla de arriba y elige PasarEX.

Como agente aduanero, PasarEX refleja todo el trayecto desde el manifiesto electrónico hasta la llegada de la carga consolidada, la aduana, las bodegas y el reparto local.

## Formatos de guía y códigos de seguimiento de PasarEX

Peek admite números de PasarEX de 6 a 30 letras y dígitos con al menos un dígito. Una forma es solo de PasarEX:

| Formato (inventado) | Qué es |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 dígitos (15 caracteres) — número de Amazon que el rastreador de PasarEX consulta; ningún otro transportista en Peek tiene esta forma. |

Si tu correo incluye un enlace directo `tracking.pasarex.com/?awb=...`, al pegarlo en Peek la guía se extrae y se asigna a PasarEX de inmediato. Para consultar otros códigos de compras online, revisa la [guía de formatos de números de seguimiento](guide:tracking-number-formats) y la [guía de pedidos de Amazon](guide:amazon-orders).

## Qué significan los estados de PasarEX

En su portal, PasarEX agrupa los registros bajo encabezados amplios (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), mientras cada fila detalla la descripción en español o inglés junto con la ciudad y la fecha y hora.

| Texto en PasarEX | Qué significa |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | PasarEX recibió los datos del manifiesto electrónico antes de que el paquete físico llegara a su estación de importación. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | La carga aérea consolidada llegó a la estación de entrada de PasarEX (por ejemplo, Bogotá) antes de pasar a aduana. |
| `Entry to station for customs processing.` | El paquete ingresó a estación para iniciar el trámite aduanero de importación. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX liberó el envío en aduana y lo transfirió a operaciones para su distribución nacional. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | El paquete entró en una bodega o estación de destino entre tramos de la ruta nacional. |
| `Package in transit` / `Package in ground transit` | En tránsito terrestre o entre estaciones de PasarEX. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Aviso de posible demora por bloqueos o manifestaciones locales; el paquete sigue en curso después. |
| `Shipment left to local route for final delivery` | Salió a ruta local con el mensajero para entrega hoy. |
| `Shipment delivered in perfect condition` | Entregado en el domicilio del destinatario. |

## Aduana, PQR e indemnizaciones en PasarEX

Como PasarEX gestiona la nacionalización y luego la última milla, conviene tener presentes dos puntos clave:

- **Antes de que llegue el paquete:** mientras el seguimiento solo indique `Orden de pedido recibida - paquete no arribado` o `Information (Manifest) of the shipment received.`, el pedido está registrado pero el paquete aún no ha llegado a PasarEX. Las novedades se reanudan cuando se registra `Carga consolidada para importación recibida` o `Reception of consolidated cargo in import.`. Consulta la [guía de aduanas](guide:customs) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Derechos de PQR e indemnización en Colombia (Resolución CRC 5050 de 2016):** En [Soporte PasarEX Colombia](https://pasarex.com/co/soporte/), Pasar Express SAS detalla los plazos legales bajo la Ley 1369 de 2009 y la Resolución 5050 de 2016. El destinatario cuenta con **5 días hábiles** desde la entrega para reclamar por expoliación o avería, y el remitente dispone de **10 días calendario** en envíos nacionales y **6 meses** en servicios internacionales por pérdida, expoliación o avería (salvo términos pactados en cuentas corporativas). La solicitud debe adjuntar copia de la Guía Aérea, factura comercial, factura de Pasar Express SAS, lista de artículos reclamados, descripción del embalaje y registro fotográfico. PasarEX responde normalmente las PQR en **15 días hábiles** (ampliable si requiere investigación adicional), y el usuario tiene **10 días hábiles** tras la notificación para presentar recurso de reposición y en subsidio de apelación.

## Seguir envíos de PasarEX con Peek

Pega tu número o un enlace de `tracking.pasarex.com/?awb=...` en Peek, que sigue a más de 4.500 transportistas. Un enlace va a PasarEX directamente. Por un número suelto, Peek nunca pregunta a PasarEX: puede preguntar a otros transportistas con la misma forma, luego consulta servicios de seguimiento («Comprobando servicios de seguimiento…») y archiva el paquete con PasarEX solo si un servicio de seguimiento lo atribuye a PasarEX, y a ningún otro transportista, con pasos fechados. Así que pulsa «Cambiar» y elige PasarEX (en «Encaja con este número» para un número `AMZPSR`), o usa «Cambiar transportista» en un paquete ya añadido.

El rastreador de PasarEX pide marcar una casilla reCAPTCHA en cada consulta, así que Peek lo lee mediante el [adaptador de PasarEX en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) con un asistente de navegador. Lee la descripción, la ciudad y la hora de cada movimiento y ordena el historial del más reciente al más antiguo en lugar de por los encabezados de PasarEX. PasarEX da horas locales sin desfase: Peek fecha cada una en la zona de su ciudad (hora de Nueva York para Miami, de Bogotá para Bogotá, Medellín y Cali, y la de Santiago, Panamá o Barbados para esos lugares), un movimiento sin ciudad en la de su vecino, y si no en hora de Bogotá, y muestra cada movimiento en el reloj de tu dispositivo. El portal de PasarEX no muestra fecha estimada de entrega, peso ni comprobante firmado.

Peek clasifica cada movimiento en etapas claras: **Etiqueta creada** al recibir el manifiesto electrónico, **En tránsito** desde la recepción de la carga consolidada hasta la liberación aduanera, el ingreso a bodega y el tránsito terrestre (PasarEX pasa directo del manifiesto al arribo de carga consolidada sin publicar un escaneo separado de **Admitido por el transportista**), **En aduanas** durante el trámite aduanero, **En reparto** cuando sale a ruta local y **Entregado** al confirmarse la entrega.

Peek consulta el rastreador de PasarEX como mucho cada hora, el mínimo que PasarEX permite entre dos consultas, también tras una consulta fallida, tengas o no el paquete abierto; el día del reparto no hace consultas cada 2 minutos. Tras siete días en «Etiqueta creada», revisa cada 6 horas. Cuando el rastreador de PasarEX no responde y Peek sigue el paquete solo a través de servicios de seguimiento, revisa cada hora, y cada 3 horas tras un día sin novedades.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete dice entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a PasarEX.» Tras tres semanas sin un escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a PasarEX.»

En los ajustes de notificaciones de Peek puedes elegir **Cada escaneo**, **Pasos importantes** o **Solo la entrega**.

## Cómo contactar con PasarEX

PasarEX publica teléfonos y correos para cada país:

- **Colombia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (también línea de PQR en Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; correos `sac@pasar.net` y `pqr@pasar.net`
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; correo `clgestionpe@pasar.net`
- **Panamá ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; correos `pagestionpe@pasar.net` y `pqr@pasar.net`
- **Barbados:** `+1 246 4370277` y `+1 305 7042435`
- **Información institucional:** [PasarEX Colombia](https://pasarex.com/co/) y [Nosotros](https://pasarex.com/co/nosotros/)

## Preguntas frecuentes

### ¿Qué es un código de seguimiento que empieza por `AMZPSR`?

`AMZPSR` seguido de 9 dígitos (15 caracteres en total, como `AMZPSR482917360`, inventado) es un número de Amazon que el rastreador de PasarEX consulta. Ningún otro transportista en Peek tiene esta forma, pero Peek no pregunta a PasarEX por sí solo: elígelo en «Encaja con este número».

### ¿Qué significa `Orden de pedido recibida - paquete no arribado` en PasarEX?

Indica que PasarEX ya recibió el manifiesto electrónico del envío, pero el paquete físico todavía no ha arribado a la estación de importación en destino. El historial vuelve a moverse cuando aterriza la carga consolidada.

### ¿Por qué Peek actualiza PasarEX cada hora y no cada pocos minutos?

PasarEX permite consultar su rastreador como mucho una vez por hora, también tras una consulta fallida, así que Peek lo revisa cada hora. Además, su rastreador pide marcar una casilla reCAPTCHA en cada consulta.

### ¿Cuál es el plazo para presentar una PQR o reclamo por daño en PasarEX Colombia?

Según la Resolución CRC 5050 de 2016 publicada en [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), el destinatario tiene **5 días hábiles** desde la recepción para reclamar por expoliación o avería, y el remitente dispone de 10 días calendario (nacional) o 6 meses (internacional).

### ¿Cuáles son los teléfonos y correos de soporte de PasarEX en Colombia, Chile y Panamá?

En Colombia puedes llamar al `+57 601 384 7000` o `+57 601 390 6000` (también PQR en Bogotá) o escribir a `sac@pasar.net` y `pqr@pasar.net`; en Chile al `+56 2 2571 7500` (`clgestionpe@pasar.net`); en Panamá al `+507 836 0399` (`pagestionpe@pasar.net`); y en Barbados al `+1 246 4370277` o `+1 305 7042435`.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [Adaptador de PasarEX en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
