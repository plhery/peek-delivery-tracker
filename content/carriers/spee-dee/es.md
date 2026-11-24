---
title: Seguimiento Spee-Dee Delivery: códigos SP, estados y ayuda
description: Sigue paquetes de Spee-Dee Delivery Service en el Medio Oeste de EE. UU.: códigos SP de 18 y 20 caracteres, reciclaje de códigos, cobertura y 800-862-5578.
slug: seguimiento-spee-dee
published: 2026-11-24
updated: 2026-11-24
---

Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) es una empresa familiar de entregas de segunda generación fundada en 1978 por Don Weeres como servicio bajo demanda. Se define como líder indiscutible de la última milla en el Medio Oeste estadounidense con red al día siguiente y ofrece además carga fraccionada LTL, almacenaje, puntos de envío presencial y EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Puedes consultar un envío en la página [Track a Shipment de Spee-Dee](https://speedeedelivery.com/track-a-shipment/) o pegar tu código de barras `SP` en la caja de arriba.

## Formatos de número de seguimiento de Spee-Dee

Peek reconoce los códigos de barras de Spee-Dee como `SP` seguido de 16 o 18 dígitos:

| Formato (inventado) | Qué es |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 dígitos (18 caracteres en total) — código estándar de Spee-Dee; ten en cuenta que Spee-Dee recicla con el tiempo los códigos de 16 dígitos. |
| `SP482917360514829173` | `SP` + 18 dígitos (20 caracteres en total) — formato largo de código de barras Spee-Dee. |

Ambos formatos `SP` identifican a Spee-Dee con confianza alta y no chocan con grandes transportistas nacionales estadounidenses como [UPS](carrier:ups), [FedEx](carrier:fedex) o [USPS](carrier:usps). Si tienes un enlace de `speedeedelivery.com/...?tracking=...`, Peek extrae directamente el código `SP`. Para otros formatos estadounidenses, consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Área de servicio de Spee-Dee en el Medio Oeste

Según los [mapas de cobertura de Spee-Dee](https://speedeedelivery.com/services/service-area-maps/), Spee-Dee ofrece reparto terrestre regular en **todo** el territorio de seis estados del Medio Oeste y en ciudades y códigos postales (*ZIP Codes*) seleccionados de cuatro estados vecinos:

- **Cobertura estatal completa:** Illinois, Iowa, Minnesota, Dakota del Norte, Dakota del Sur y Wisconsin.
- **Ciudades y ZIP Codes seleccionados:** Kansas (incluidos los ZIP ampliados `660` y `666`), Míchigan, Misuri (incluidos los ZIP ampliados `634–635`, `644–646` y `650–653`) y Nebraska.

Para consultar plazos de tránsito, la página *Service Area Maps* ofrece un mapa *Days in Transit* por código postal de origen, y su archivo *Zip Transit File* detalla los días para cada código postal atendido.

## Qué significan los estados de seguimiento de Spee-Dee

La tabla de progreso de Spee-Dee muestra tres columnas — `Date / Time`, `Location` y `Activity` — del más reciente al más antiguo, junto con un bloque resumen superior con `Status:`, `Delivered on:`, `Signed by:` y `Delivered to:` una vez entregada la carga.

| Actividad de Spee-Dee | Qué significa |
| --- | --- |
| `ARRIVAL SCAN` | Escaneado en una terminal o centro de clasificación de Spee-Dee dentro de su red terrestre del Medio Oeste. |
| `OUT FOR DELIVERY` *(a menudo seguido de un número de ruta entre paréntesis)* | Cargado en el camión local de Spee-Dee para entrega hoy en domicilio o empresa. |
| `Delivered` | Entrega completada; el resumen de la propia web de Spee-Dee muestra también hora de entrega, dirección y firma del destinatario cuando se recogió. |
| `No packages were found matching the barcode supplied.` | El código aún no se ha escaneado en el sistema de Spee-Dee o contiene un error tipográfico. |

### Códigos de 16 dígitos reutilizados

Spee-Dee reutiliza códigos `SP` antiguos de 16 dígitos en envíos nuevos. En la página original de Spee-Dee pueden seguir apareciendo filas de un paquete entregado anteriormente debajo de los escaneos de tu paquete actual. Si consultas un código reutilizado en su web, fíjate en las fechas de la parte superior y detente en cuanto llegues a un `Delivered` antiguo de un envío anterior.

## Retrasos, escaneos ausentes y reclamaciones

Algunos detalles ayudan cuando el seguimiento parece confuso:

- **Aún sin escaneos:** si Spee-Dee devuelve `No packages were found matching the barcode supplied.`, todavía no ha escaneado el paquete o el código tiene un fallo. Revisa el código, pregunta al vendedor si ya entregó el paquete a Spee-Dee o lee [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Límites por recargas frecuentes:** el servidor de Spee-Dee limita las consultas por minuto y devuelve temporalmente HTTP 503 si se recarga muy seguido. Esperar un minuto o dejar que Peek consulte automáticamente evita ese límite.
- **Reclamaciones por no entrega o daños:** el [formulario de atención al cliente de Spee-Dee](https://speedeedelivery.com/customer-service/) incluye tipos específicos **Claims – Non-Delivery** y **Claims – Damage** y pide incluir la **dirección completa de entrega** en las consultas de seguimiento. Consulta también [entregado pero no recibido](guide:delivered-not-received) si aparece entregado sin haber llegado.

## Seguir Spee-Dee con Peek

Pega cualquier código Spee-Dee de 18 caracteres (`SP` + 16 dígitos) o 20 caracteres (`SP` + 18 dígitos), o un enlace de `speedeedelivery.com`, en Peek y reconocerá Spee-Dee al instante. Si alguna vez necesitas cambiar de transportista, usa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla de seguimiento.

Peek lee directamente la página de progreso de Spee-Dee mediante el [adaptador Spee-Dee de código abierto en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), y a través de una red remota si el servidor de Spee-Dee rechaza la conexión directa. Para un **código reutilizado**, conserva solo las filas posteriores a la entrega previa con al menos un día de diferencia. Descarta comentarios HTML de depuración y el número de ruta tras `OUT FOR DELIVERY`, y guarda ciudad o terminal y hora de cada escaneo. Spee-Dee ofrece horas locales sin desfase respecto a UTC: Peek fecha cada escaneo según la zona horaria del lugar (hora de Chicago para St. Cloud, Fargo, Milwaukee o Sioux Falls; hora de Detroit para Detroit), un escaneo sin lugar según el escaneo vecino y, en su defecto, con hora de Nueva York, mostrando todo en la hora de tu dispositivo. El localizador de Spee-Dee no muestra fecha estimada de entrega ni peso.

Peek traduce `ARRIVAL SCAN` a «En tránsito», `OUT FOR DELIVERY` a «En reparto» y `Delivered` a «Entregado», e interpreta cualquier otra actividad mediante sus reglas comunes de estado.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Spee-Dee muestra `OUT FOR DELIVERY`, cada 2 minutos desde las 6:00 según el reloj del escaneo más reciente, o cada 5 minutos si la última comprobación tuvo que abrir un navegador, mientras responda el localizador de Spee-Dee. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando Spee-Dee no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Spee-Dee.», junto con un enlace «Pide ayuda a Spee-Dee» hacia su página de atención al cliente. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Spee-Dee.»

En las preferencias de notificación de Peek, elige «Cada escaneo», «Pasos importantes» o «Solo la entrega» para controlar tus avisos.

## Contactar con Spee-Dee Delivery

Spee-Dee señala en su [página de atención al cliente](https://speedeedelivery.com/customer-service/) que quienes llaman hablan directamente con una persona y no con un menú telefónico automatizado:

- **Atención al cliente gratuita:** `800-862-5578`
- **Teléfono local:** `320-251-6697`
- **Fax:** `320-251-1846` (fax LTL: `320-240-7995`)
- **Formulario online de contacto y reclamaciones:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (incluye tu código `SP` y la dirección completa de entrega para consultas de seguimiento)
- **Dirección física:** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Dirección postal:** P.O. Box 1417, St. Cloud, MN 56302
- **Empresa y seguimiento:** [Acerca de Spee-Dee](https://speedeedelivery.com/about-us/) y [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Preguntas frecuentes

### ¿Por qué mi página de seguimiento de Spee-Dee muestra una entrega de hace meses?

Spee-Dee reutiliza códigos `SP` de 16 dígitos con el tiempo, por lo que su web original puede listar un envío antiguo ya entregado debajo de los escaneos de tu paquete actual. Peek detecta automáticamente ese límite y oculta las filas de entregas anteriores.

### ¿En qué estados de EE. UU. opera Spee-Dee Delivery?

Spee-Dee cubre por completo Illinois, Iowa, Minnesota, Dakota del Norte, Dakota del Sur y Wisconsin, además de ciudades y códigos postales seleccionados de Kansas, Míchigan, Misuri y Nebraska.

### ¿Muestra Spee-Dee Delivery una fecha estimada de entrega en su página de seguimiento?

No. La página de seguimiento de Spee-Dee muestra escaneos de llegada a terminal, `OUT FOR DELIVERY` y detalles de entrega final, sin fecha estimada. Puedes consultar los días habituales de tránsito terrestre por código postal en [Service Area Maps de Spee-Dee](https://speedeedelivery.com/services/service-area-maps/).

### ¿Qué datos debo incluir al contactar con Spee-Dee sobre un paquete?

Ten listo tu número completo `SP` de 18 o 20 caracteres y la dirección completa de entrega: el formulario de atención al cliente de Spee-Dee solicita expresamente la dirección completa de entrega en las consultas de seguimiento.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
