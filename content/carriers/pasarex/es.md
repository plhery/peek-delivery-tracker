---
title: Rastreo PasarEX — códigos AMZPSR, aduana, PQR y teléfonos
description: Rastrea envíos de PasarEX (Pasar Express) en Colombia, Chile, Panamá y Barbados. Guías AMZPSR, estados de aduana, plazos de PQR y líneas de atención.
slug: rastreo-pasarex
published: 2026-11-23
updated: 2026-11-23
---

PasarEX (marca comercial de *PASAR EXPRESS SAS*, empresa colombiana fundada en 1993) es un operador de mensajería expresa internacional, carga aérea y última milla para comercio electrónico con presencia en **Colombia**, **Chile**, **Panamá** y **Barbados**, respaldado por un centro logístico en Miami y rutas multimodales desde China. Los compradores suelen encontrar a PasarEX en pedidos internacionales de Amazon y compras transfronterizas que ingresan por Bogotá, Santiago, Ciudad de Panamá o Bridgetown.

Como PasarEX actúa a la vez como agente aduanero de importación y distribuidor local en muchas rutas, el seguimiento acompaña el paquete desde el manifiesto electrónico hasta la llegada de la carga consolidada, el despacho aduanero, la bodega y el reparto final.

## Formatos de guía y códigos de seguimiento de PasarEX

El rastreador de PasarEX (`tracking.pasarex.com`) admite guías alfanuméricas de 6 a 30 caracteres que incluyan al menos un dígito:

| Formato (inventado) | Qué es |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 dígitos (15 caracteres) — código de importación internacional de Amazon gestionado por PasarEX; reconocido como candidato exclusivo de PasarEX. |
| `PSR482917360` | Guía aérea propia de PasarEX — selecciona PasarEX manualmente o pega tu enlace de seguimiento. |

Si tu correo incluye un enlace directo `tracking.pasarex.com/?awb=...`, al pegarlo en Peek la guía se extrae y se asigna a PasarEX de inmediato. Para consultar otros códigos de compras online, revisa la [guía de formatos de números de seguimiento](guide:tracking-number-formats) y la [guía de pedidos de Amazon](guide:amazon-orders).

## Qué significan los estados de PasarEX

En su portal, PasarEX agrupa los registros bajo encabezados amplios (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`), mientras cada fila detalla la descripción en español o inglés junto con la ciudad y la fecha y hora.

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

- **Espera antes del arribo consolidado:** Mientras el seguimiento solo indique `Orden de pedido recibida - paquete no arribado` o `Information (Manifest) of the shipment received.`, la mercancía aún viaja desde el origen (habitualmente Miami o China) hacia el país de destino. Las novedades se reanudan cuando se registra `Carga consolidada para importación recibida`. Consulta la [guía de aduanas](guide:customs) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Derechos de PQR e indemnización en Colombia (Resolución CRC 5050 de 2016):** En [Soporte PasarEX Colombia](https://pasarex.com/co/soporte/), Pasar Express SAS detalla los plazos legales bajo la Ley 1369 de 2009 y la Resolución 5050 de 2016. El destinatario cuenta con **5 días hábiles** desde la entrega para reclamar por expoliación o avería, y el remitente dispone de **10 días calendario** en envíos nacionales y **6 meses** en servicios internacionales por pérdida, expoliación o avería (salvo términos pactados en cuentas corporativas). La solicitud debe adjuntar copia de la Guía Aérea, factura comercial, factura de Pasar Express SAS, lista de artículos reclamados, descripción del embalaje y registro fotográfico. PasarEX responde normalmente las PQR en **15 días hábiles** (ampliable si requiere investigación adicional), y el usuario tiene **10 días hábiles** tras la notificación para presentar recurso de reposición y en subsidio de apelación.

## Seguir envíos de PasarEX con Peek

Pega tu código `AMZPSR...` o un enlace de `tracking.pasarex.com/?awb=...` en Peek. Para guías sin prefijo `AMZPSR`, elige PasarEX mediante **Cambiar** al añadir el paquete o **Cambiar transportista** en la pantalla del envío.

Como el portal de PasarEX exige superar una casilla reCAPTCHA en cada consulta, Peek lee los movimientos mediante el [adaptador de PasarEX en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) con un asistente de navegador y una cadencia mínima de actualización de 60 minutos. Extrae cada descripción, ciudad y hora local (`dd/MM/yyyy HH:mm:ss`, sin inventar huso horario) y ordena todo el historial del más reciente al más antiguo en lugar de mantenerlo separado por pestañas. El portal de PasarEX no muestra fecha estimada de entrega, peso ni comprobante firmado.

Peek clasifica cada movimiento en etapas claras: **Etiqueta creada** al recibir el manifiesto electrónico, **En tránsito** desde la recepción de la carga consolidada hasta la liberación aduanera, el ingreso a bodega y el tránsito terrestre (PasarEX pasa directo del manifiesto al arribo de carga consolidada sin publicar un escaneo separado de **Admitido por el transportista**), **En aduanas** durante el trámite aduanero, **En reparto** cuando sale a ruta local y **Entregado** al confirmarse la entrega.

Durante vuelos consolidados, trámites de aduana o fines de semana, Peek recuerda: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a PasarEX.» Si pasan más de tres semanas sin novedades, aparece: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a PasarEX.»

En los ajustes de notificaciones de Peek puedes elegir **Cada escaneo**, **Pasos importantes** o **Solo la entrega**.

## Cómo contactar con PasarEX

PasarEX dispone de líneas telefónicas, atención bilingüe y correos específicos en cada país donde opera:

- **Colombia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (también línea de PQR en Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; correos `sac@pasar.net` y `pqr@pasar.net`
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; correo `clgestionpe@pasar.net`
- **Panamá ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; correos `pagestionpe@pasar.net` y `pqr@pasar.net`
- **Barbados:** `+1 246 4370277` y `+1 305 7042435`
- **Información institucional:** [PasarEX Colombia](https://pasarex.com/co/) y [Nosotros](https://pasarex.com/co/nosotros/)

Si tu compra vino de Amazon con código `AMZPSR...`, también puedes verificar el estado del pedido o solicitar reembolso o reemplazo directamente desde el soporte de Amazon.

## Preguntas frecuentes

### ¿Qué es un código que empieza por `AMZPSR`?

`AMZPSR` seguido de 9 dígitos (15 caracteres en total, como `AMZPSR482917360`) es un número de envío internacional de Amazon gestionado por PasarEX para el despacho aduanero y la entrega local en Colombia, Chile, Panamá o Barbados.

### ¿Qué significa «Orden de pedido recibida - paquete no arribado»?

Indica que PasarEX ya recibió el manifiesto electrónico del envío, pero el paquete físico todavía no ha arribado a la estación de importación en destino. El historial vuelve a moverse cuando aterriza la carga consolidada.

### ¿Por qué Peek actualiza PasarEX cada hora y no cada pocos minutos?

Porque el rastreador web de PasarEX exige completar una verificación reCAPTCHA en cada consulta; Peek espacia las comprobaciones al menos 60 minutos para mantener el seguimiento estable.

### ¿Cuál es el plazo para reclamar por avería o daño en PasarEX Colombia?

Según la Resolución CRC 5050 de 2016 publicada en [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), el destinatario tiene **5 días hábiles** desde la recepción para reclamar por expoliación o avería, y el remitente dispone de 10 días calendario (nacional) o 6 meses (internacional).

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [Adaptador de PasarEX en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
