---
title: Seguimiento BFE Express — códigos CK, WYB, aduana y ayuda
description: Rastrea paquetes de BFE Express en Argentina, Perú, Uruguay y EE. UU. Formatos CK y WYB, estados de aduana y última milla, y correos de soporte por país.
slug: seguimiento-bfe-express
published: 2026-11-23
updated: 2026-11-23
---

BFE Express (*BFEexpress*, operado por Hongkong Bfe Info Tech Co Limited y fundado en 2005) es un operador de logística transfronteriza y entrega de última milla que conecta tiendas internacionales — incluidos vendedores de Temu Argentina — con destinatarios en Argentina, Perú, Uruguay y Estados Unidos. Ofrece flete aéreo internacional, despacho aduanero, almacenamiento local y distribución de última milla consultable en [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Como BFE Express gestiona la llegada aérea y la aduana antes de entregar el paquete al depósito local de última milla, un mismo historial suele combinar eventos internacionales en inglés con escaneos locales en español.

## Formatos de número de seguimiento de BFE Express

El buscador oficial de BFE Express acepta referencias de 6 a 50 letras y dígitos. En pedidos transfronterizos hacia Sudamérica predominan cuatro patrones:

| Formato (inventado) | País de destino y detalle |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 dígitos (16 caracteres) — formato para Argentina, candidato único de BFE Express. |
| `CK548291736045182` | `CK54` + 13 dígitos (17 caracteres) — formato alternativo para Argentina (coincide en longitud con [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 dígitos (17 caracteres) — formato para Perú (coincide en longitud con [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 dígitos (17 caracteres) — formato para Uruguay (coincide en longitud con [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 dígitos (12 caracteres) — guía para Perú (coincide en forma con [Colis Privé](carrier:colis-prive) y [Purolator](carrier:purolator)). |

Los dígitos tras `CK` reflejan el destino: `54` corresponde a Argentina, `512` a Perú y `598` a Uruguay. Si pegas un enlace directo de `bfeexpress.com/track?track_number=...`, Peek extrae el código y selecciona BFE Express de inmediato. Para comparar otros códigos internacionales, consulta la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de BFE Express

BFE Express divide el recorrido en dos tramos: el tramo inicial internacional (`firstLegTrackingDetails`, con llegada al aeropuerto y despacho aduanero en inglés) y el tramo de distribución local (`supplierTrackingDetails`, en español).

| Texto en BFE Express | Qué significa |
| --- | --- |
| `Arrived at Airport of destination` | El vuelo con tu paquete aterrizó en el país de destino; a menudo es el primer evento visible en pedidos a Argentina. |
| `Transport to next facility` | Traslado desde el aeropuerto hacia la planta local de despacho o clasificación. |
| `Customs declaration completed` | La declaración aduanera en destino concluyó sin retención, justo antes del traspaso a última milla. |
| `Turned over to last mile facility` | Entregado al depósito local encargado de la distribución final. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Recepcionado en el centro o depósito local en Argentina o Uruguay. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Cargado y viajando entre depósitos locales rumbo a tu ciudad. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | En la sucursal distribuidora esperando salida a recorrido. |
| `Reparto en curso` | El repartidor salió a entregar tu paquete hoy. |
| `Entregado` / `Delivered` | Entregado en el domicilio del destinatario. |

En los eventos en inglés BFE Express antepone el lugar antes de una coma (por ejemplo, `<Ciudad>, Customs declaration completed`), mientras que las filas locales en español mencionan la localidad tras `en`. Si el último escaneo tiene texto genérico, el estado principal del envío (`Delivered`) confirma igualmente la entrega.

## Aduana, traspaso a última milla y demoras

La mayoría de los paquetes de BFE Express provienen de plataformas internacionales como Temu e ingresan por carga aérea consolidada a Argentina, Perú o Uruguay antes del reparto local:

- **Sin escaneos antes de llegar al país:** Es habitual que el rastreador público de BFE Express no muestre movimientos de origen hasta `Arrived at Airport of destination`. Mientras vuela la carga consolidada, revisa el seguimiento dentro de la tienda y consulta [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Traspaso rápido tras aduana:** Cuando la plataforma predeclara la importación, `Customs declaration completed` y `Turned over to last mile facility` suelen aparecer con pocas horas de diferencia, seguidos al día siguiente por el ingreso al depósito local (`Se ha recibido el paquete.` o `Tu paquete fue recibido por en: ...`). Si el envío se detiene en aduana, revisa la [guía de aduanas](guide:customs).
- **Fecha estimada de entrega:** BFE Express suele informar un día estimado (`planDeliveryTimeDescription`) mientras el envío está en camino; una vez entregado — o si el último escaneo supera ese día — la estimación deja de mostrarse.
- **Paquete marcado como entregado o dañado:** Las preguntas frecuentes de [Customer Support de BFE Express](https://www.bfeexpress.com/contactUS) indican escribir al equipo local con tu número de seguimiento a mano si figura entregado sin haberlo recibido o llega dañado. También puedes consultar la [guía de entregado y no recibido](guide:delivered-not-received).

Para envíos nacionales en Argentina gestionados directamente por correos locales, revisa también [OCA](carrier:oca) y [Andreani](carrier:andreani).

## Seguir envíos de BFE Express con Peek

Pega tu código `CK54...`, `CK512...`, `CK598...` o `WYB...` — o el enlace `bfeexpress.com/track?track_number=...` — en Peek. Como los códigos `CK` de 17 caracteres y los `WYB` de 12 caracteres coinciden en longitud con [Colis Privé](carrier:colis-prive) o [Purolator](carrier:purolator), puedes elegir BFE Express directamente con **Cambiar** al añadir el paquete o **Cambiar transportista** en la pantalla del envío.

Peek consulta la API de seguimiento de BFE Express directamente a través del [adaptador de BFE Express en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). Combina ambos tramos en orden cronológico, separa la ciudad o sucursal en el campo de ubicación, conserva la zona horaria GMT cuando el servidor la indica (o la hora local en caso contrario) y guarda la fecha estimada y el país de destino. Por privacidad, descarta direcciones exactas y fotos de comprobante de entrega.

Como el rastreador público de BFE Express suele comenzar en la llegada al aeropuerto de destino sin publicar escaneos previos de **Etiqueta creada** ni **Admitido por el transportista**, Peek muestra todo el recorrido aéreo, aduanero y entre depósitos como **En tránsito**, cambia a **En reparto** con `Reparto en curso` y marca **Entregado** cuando llega `Entregado` o el estado `Delivered`.

Si pasan días sin movimiento en tránsito internacional o el fin de semana, Peek muestra: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a BFE Express.» Si transcurren más de tres semanas sin novedades, verás: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a BFE Express.» Para acudir al soporte regional de inmediato, toca **Pide ayuda a BFE Express**.

En las alertas de Peek puedes elegir entre **Cada escaneo**, **Pasos importantes** o **Solo la entrega**.

## Cómo contactar con BFE Express

BFE Express publica correos de atención por país en [bfeexpress.com/contactUS](https://www.bfeexpress.com/contactUS), con horario de atención de **lunes a viernes de 9:00 a 18:00** (excepto feriados):

- **Argentina:** `info@ar.bfeexpress.com`
- **Perú:** `info@pe.bfeexpress.com`
- **Uruguay:** `info@uy.bfeexpress.com`
- **Estados Unidos:** `info@us.bfeexpress.com`
- **Paraguay:** `info_py@bfeexpress.com`
- **Otros destinos (Colombia, Ecuador, Serbia, Dominica):** `info@other.bfeexpress.com`
- **Sitio oficial y rastreo:** [bfeexpress.com](https://www.bfeexpress.com/) y [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Ten a mano tu código completo `CK...` o `WYB...` al escribir al equipo local; si necesitas reembolso o reposición de un pedido en un marketplace como Temu, abre además un reclamo directamente con el vendedor.

## Preguntas frecuentes

### ¿Por qué mi envío de BFE Express empieza recién en el aeropuerto de destino?

En muchas cargas consolidadas hacia Argentina, Perú y Uruguay, BFE Express comienza a mostrar escaneos públicos recién al aterrizar (`Arrived at Airport of destination`). Antes de ese momento, revisa los movimientos de despacho en la página del pedido de la tienda.

### ¿Por qué aparecen estados en inglés y luego en español?

BFE Express registra en inglés la llegada aérea, la liberación aduanera y la entrega al operador local, mientras que el depósito nacional de última milla publica sus eventos en español (hasta `Reparto en curso` y `Entregado`).

### ¿Qué indican `54`, `512` y `598` en los códigos `CK` de BFE Express?

Identifican el país de destino: `CK54...` corresponde a Argentina (16 o 17 caracteres), `CK512...` a Perú (17 caracteres) y `CK598...` a Uruguay (17 caracteres). Para Perú también existe el formato `WYB` seguido de 9 dígitos.

### ¿Por qué BFE Express dice que no encuentra mi número?

Verifica que no falte ningún carácter en el código `CK` o `WYB` y confirma con la tienda que el pedido ya fue despachado. Los envíos transfronterizos recién etiquetados pueden tardar varios días en figurar en el portal público antes de llegar al país de destino.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [Rastreo oficial de BFE Express](https://www.bfeexpress.com/tracking)
- [Soporte al cliente de BFE Express](https://www.bfeexpress.com/contactUS)
- [Adaptador de BFE Express en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
