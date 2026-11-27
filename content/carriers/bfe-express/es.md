---
title: Seguimiento BFE Express — códigos CK, WYB, aduana y ayuda
description: Seguimiento BFE Express en Argentina, Perú, Uruguay y EE. UU.: códigos CK54, CK512, CK598 y WYB, estados de aduana y última milla, y correos de soporte.
slug: seguimiento-bfe-express
published: 2026-11-23
updated: 2026-11-27
---

Para rastrear un paquete de BFE Express (*BFEexpress*, operado por Hongkong Bfe Info Tech Co Limited desde 2005 — habitual en envíos internacionales como Temu Argentina hacia Argentina, Perú, Uruguay y Estados Unidos), pega tu código `CK54…`, `CK512…`, `CK598…` o `WYB…` en el cuadro superior o consúltalo en [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking). El historial combina el tramo aéreo y aduanero en inglés con los escaneos locales de última milla en español, y cada país cuenta con correo de soporte propio (lunes a viernes, 9:00 a 18:00).

Como BFE Express gestiona la llegada aérea y la aduana antes de entregar el paquete al depósito local de última milla, un mismo historial suele combinar eventos internacionales en inglés con escaneos locales en español.

## Formatos de número de seguimiento de BFE Express

Peek admite números de BFE Express de 6 a 50 letras y dígitos. El formulario de contacto de BFE Express acepta estos formatos, según el destino:

| Formato (inventado) | País de destino y detalle |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 dígitos (16 caracteres) — formato para Argentina; ningún otro transportista en Peek tiene esta forma. |
| `CK548291736045182` | `CK54` + 13 dígitos (17 caracteres) — formato alternativo para Argentina (coincide en longitud con [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 dígitos (17 caracteres) — formato para Perú (coincide en longitud con [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 dígitos (17 caracteres) — formato para Uruguay (coincide en longitud con [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 dígitos (12 caracteres) — guía para Perú (coincide en forma con [Purolator](carrier:purolator) y [TNT Australia](carrier:tnt-au)). |

Los dígitos tras `CK` reflejan el destino: `54` corresponde a Argentina, `512` a Perú y `598` a Uruguay. Si pegas un enlace directo de `bfeexpress.com/track?track_number=...`, Peek extrae el código y selecciona BFE Express de inmediato. Para comparar otros códigos internacionales, consulta la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de BFE Express

El rastreador de BFE Express da dos grupos de filas, el tramo internacional y la última milla, y Peek lee ambos.

| Texto en BFE Express | Qué significa |
| --- | --- |
| `Arrived at Airport of destination` | El vuelo con tu paquete aterrizó en el país de destino. |
| `Transport to next facility` | Traslado desde el aeropuerto hacia la planta local de despacho o clasificación. |
| `Customs declaration completed` | La declaración aduanera en destino concluyó, antes del traspaso a la última milla. |
| `Turned over to last mile facility` | Entregado al depósito local encargado de la distribución final. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Recepcionado en el centro o depósito local en Argentina o Uruguay. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Cargado y viajando entre depósitos locales rumbo a tu ciudad. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | En la sucursal distribuidora esperando salida a recorrido. |
| `Reparto en curso` | El repartidor salió a entregar tu paquete hoy. |
| `Entregado` | Entregado al destinatario. El estado propio de BFE para el envío dice `Delivered` al lado. |

Las filas de BFE Express en inglés ponen el lugar primero, antes de una coma (por ejemplo, `<Ciudad>, Customs declaration completed`); sus filas en español en Argentina ponen primero el país, y la última milla uruguaya nombra el lugar tras `en` o `a`. Cuando el estado propio de BFE para el envío dice `Delivered`, Peek lo marca como entregado aunque la fila más reciente diga otra cosa.

## Aduana, traspaso a última milla y demoras

La web de BFE Express ofrece envío de paquetes, flete internacional, despacho aduanero, almacenamiento local y última milla, y se dirige a los vendedores de Temu Argentina. Algunos puntos ayudan a entender los tiempos:

- **Antes del aeropuerto de destino:** si el rastreador de BFE Express aún no muestra nada, revisa la página del pedido en la tienda y consulta [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Aduana y última milla:** `Customs declaration completed` y `Turned over to last mile facility` pueden aparecer con una hora de diferencia en el mismo lugar, y el ingreso al depósito local (`Se ha recibido el paquete.` o `Tu paquete fue recibido por en: ...`) un día después. Si el envío se detiene en aduana, revisa la [guía de aduanas](guide:customs).
- **Fecha prevista de entrega:** BFE Express puede indicar un día previsto mientras el envío está en camino, y Peek lo muestra. Peek lo quita cuando el paquete se entrega, se devuelve o está listo para recoger, o cuando un escaneo es posterior a ese día.
- **Paquete marcado como entregado o dañado:** la página [Customer Support de BFE Express](https://www.bfeexpress.com/contactUS) incluye ambos casos entre sus preguntas frecuentes y pide tener el número de seguimiento a mano al contactar con el equipo local. Consulta también la [guía de entregado y no recibido](guide:delivered-not-received).

Para envíos nacionales en Argentina gestionados directamente por correos locales, revisa también [OCA](carrier:oca) y [Andreani](carrier:andreani).

## Seguir envíos de BFE Express con Peek

Pega tu código `CK...` o `WYB...` en Peek, que sigue a más de 4.500 transportistas. Por su forma, Peek no puede saber si el número es de BFE Express, así que al añadirlo pregunta a BFE Express («Comprobando servicios de seguimiento…») y muestra «BFE Express tiene este paquete.» cuando BFE Express conoce el número. Para un código `CK54` de 16 caracteres pregunta solo a BFE Express. Para un código `CK` de 17 caracteres pregunta también a [Colis Privé](carrier:colis-prive): primero en Francia y, en otros países, solo cuando ni BFE Express ni los servicios de seguimiento conocen el número. Para un código `WYB` pregunta a BFE Express, [TNT Australia](carrier:tnt-au) y [Purolator](carrier:purolator): primero a BFE Express en Argentina, Perú, Uruguay y Estados Unidos, primero a Purolator en Canadá y primero a TNT Australia en otros países. Para seguir el paquete con BFE Express desde el principio, pulsa «Cambiar» y elige BFE Express en «Encaja con este número», o usa «Cambiar transportista» en un paquete ya añadido. Un enlace `bfeexpress.com/track?track_number=...` va a BFE Express directamente.

Peek consulta la API de seguimiento de BFE Express directamente a través del [adaptador de BFE Express en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). Lee ambos grupos de filas en orden cronológico, toma la ciudad, la sucursal o el país como lugar, y guarda el día previsto y el país de destino. Una hora con su desfase GMT conserva ese instante. Una hora sin él se fecha en la zona de su lugar (hora de Buenos Aires para Ezeiza, de Lima para Lima, de Montevideo para Montevideo), un escaneo sin lugar en la de su vecino, y si no en hora de Buenos Aires, y cada escaneo se muestra en el reloj de tu dispositivo. Descarta las direcciones y las fotos de comprobante de entrega del portal de BFE.

Peek muestra las filas de BFE Express como **En tránsito**, salvo `Reparto en curso`, que es **En reparto**, y `Entregado`, que es **Entregado**. También marca el paquete como **Entregado** cuando el estado propio de BFE para el envío dice `Delivered`.

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek revisa hasta cada 10 minutos, y cada 2 minutos desde las 6:00, en el reloj del escaneo más reciente, el día en que BFE Express muestra `Reparto en curso`, mientras el rastreador de BFE Express responda. Si no, revisa cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, revisa cada hora sigas como sigas el paquete, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de BFE Express no responde y Peek sigue el paquete solo a través de servicios de seguimiento, revisa cada hora, cada 3 horas tras un día sin novedades y hasta cada 15 minutos el día en que el paquete sale a reparto.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete dice entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a BFE Express.», con el enlace «Pide ayuda a BFE Express» a la página Contact Us de BFE Express. Tras tres semanas sin un escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a BFE Express.»

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

Ten a mano tu código completo `CK...` o `WYB...` al escribir al equipo local.

## Preguntas frecuentes

### ¿Qué indican `CK54`, `CK512`, `CK598` y `WYB` en BFE Express?

Los prefijos señalan el país de destino: `CK54…` corresponde a Argentina (16 o 17 caracteres), `CK512…` a Perú (17 caracteres), `CK598…` a Uruguay (17 caracteres) y `WYB` + 9 dígitos es otro formato de guía para Perú. En Peek, si pegas el número suelto, pulsa «Cambiar» y selecciona BFE Express (o pega un enlace directo `bfeexpress.com/track?track_number=…`).

### ¿Por qué mi envío de BFE Express empieza recién en el aeropuerto de destino?

El rastreador de BFE Express suele mostrar su primera línea con `Arrived at Airport of destination`, al aterrizar el vuelo en el país de destino. Antes de ese hito, consulta el avance del despacho en la página del pedido de la tienda.

### ¿Por qué aparecen estados en inglés y luego en español?

BFE Express publica en inglés la llegada aérea, la declaración aduanera (`Customs declaration completed`) y la entrega a última milla (`Turned over to last mile facility`), mientras que el depósito local registra en español el traslado hasta `Reparto en curso` y `Entregado`.

### ¿Por qué BFE Express dice que no encuentra mi número?

Revisa que no falte ningún carácter en el código `CK` o `WYB` y confirma con la tienda si el paquete ya fue entregado a BFE Express o llegó al tramo internacional.

### ¿Cómo contactar con atención al cliente de BFE Express?

Escribe de lunes a viernes de 9:00 a 18:00 (excepto feriados) con tu código completo al correo de tu país en [bfeexpress.com/contactUS](https://www.bfeexpress.com/contactUS): Argentina `info@ar.bfeexpress.com`, Perú `info@pe.bfeexpress.com`, Uruguay `info@uy.bfeexpress.com`, EE. UU. `info@us.bfeexpress.com` y Paraguay `info_py@bfeexpress.com`.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [Rastreo oficial de BFE Express](https://www.bfeexpress.com/tracking)
- [Soporte al cliente de BFE Express](https://www.bfeexpress.com/contactUS)
- [Adaptador de BFE Express en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
