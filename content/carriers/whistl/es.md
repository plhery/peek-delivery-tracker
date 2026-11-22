---
title: Seguimiento Whistl: quién entrega tu paquete y qué hacer
description: Sigue un paquete Whistl (Parcelhub): dónde hallar la referencia, significado de estados, qué transportista entrega al final y a quién acudir.
slug: seguimiento-whistl
published: 2026-11-22
updated: 2026-11-22
---

Whistl, antes TNT Post UK, es una empresa británica de envíos que gestiona paquetes de tiendas online y los entrega a transportistas como [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) o [DPD UK](carrier:dpd-uk) para el último tramo. Síguelo en el rastreador Parcelhub de Whistl con la referencia del correo de envío o enlace de la tienda, o pega el enlace arriba. Tras el traspaso, el número y el seguimiento del transportista final suelen dar más detalle.

## Número de seguimiento Whistl: dónde encontrarlo

Whistl no publica un formato único. Sus guías indican que el rastreador busca «por UPI (normalmente el código de barras del bulto) o por número de envío (*Consignment Number*)»: el UPI identifica un paquete y la remesa agrupa todo lo enviado junto. Las referencias cambian según tienda y transportista, desde una letra seguida de muchas cifras (como `L48291736482917364`) hasta el código de barras del transportista final.

Lo encuentras en el correo de envío de la tienda, a menudo como enlace al rastreador de Whistl. En envíos con seguimiento completo (*fully tracked*), el transportista final también envía correo y SMS el día de entrega bajo su propia marca y con su propio número.

El rastreador pide «Tracking Reference» y código postal opcional, y luego muestra «Tracking Number:», los escaneos y el «Carrier:» y «Service:» contratados.

Una referencia puede tener la forma de otro transportista, como el código del transportista final (caracteres inventados):

| Formato (inventado) | A quién se parece |
| --- | --- |
| `L48291736482917364` | `L` y 17 cifras: referencia Whistl que Peek consulta directamente |
| `RR482917367GB` | Royal Mail (dos letras, nueve cifras, `GB`) |
| `H00KLM0048291708` | Código Evri de 16 caracteres, ver [seguimiento Evri](carrier:evri) |
| `48291736482917` | 14 cifras: DPD UK entre otros, ver [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP |

Más ayuda para identificar códigos en [formatos de números de seguimiento](guide:tracking-number-formats).

## Significado de los estados de seguimiento Whistl

El rastreador de Whistl, solo en inglés, convierte los escaneos de cada transportista en «eventos normalizados»; los paquetes con seguimiento completo muestran además comentarios del transportista. En orden habitual:

| Whistl indica | Qué significa para ti |
| --- | --- |
| `Shipment Created` | La tienda registró el envío. Aún no se ha movido. |
| `Item receipted and processed by Whistl` | Whistl lo recibió y procesó en uno de sus depósitos. |
| `Handed over to Delivery Carrier` | El transportista indicado en la página asume la entrega. |
| `Handed over to Royal Mail` | Royal Mail lo tiene ya para el último tramo. |
| `Arrived At Depot` | Llegó a un depósito de tránsito. |
| `Shipment is Out for Delivery` | En reparto hoy. |
| `DELIVERED - Left as authorised` | Dejado donde autorizaste, sin firma. |
| `Left with Neighbour` | Entregado a un vecino. |
| `Delivered col. point` | Esperando en un punto de recogida. |
| `No-one to receive` | No había nadie: busca una tarjeta o aviso del transportista. |
| `Held by customs` | Retenido en [aduanas](guide:customs). |
| `Return - Delivered` | Devuelto y entregado de nuevo al remitente. |

Tres puntos suelen generar dudas:

- **`Shipment Created` solo** significa únicamente registrado. Si pasan días sin cambios, puede que la tienda aún no lo haya entregado.
- **`Handed over to …`** suele ser el último paso que muestra Whistl. En envíos con seguimiento parcial (*part-tracked*), Whistl advierte que no es posible seguirlos tras la entrega al socio: sigue el envío con el número del transportista final.
- **Incidencias** como `Delayed`, `Bad Address`, `Refused`, `Lost` o `Item damaged` indican un problema; `Shipment Deleted` significa que el remitente lo anuló. Líneas como `Item Weight Changed` o `Label reprinted` son simples notas sin movimiento del paquete.

## Entrega Whistl e intentos fallidos

El paquete lo entrega el transportista final, no Whistl, y rigen sus normas. Tras una entrega fallida, ese transportista deja una tarjeta o aviso digital indicando quién pasó y cuándo volverá a intentarlo.

La guía de transportistas Parcelhub de Whistl (agosto de 2025) detalla por empresa:

| Transportista | Intentos y paso siguiente |
| --- | --- |
| Royal Mail | 1 intento y tarjeta «something for you»; recogida en oficina postal o de reparto, o devolución tras 18 días. Desde mayo de 2023, si no cabe en el buzón o pide firma y no estás, se deja en lugar seguro o con un vecino. |
| Evri | Hasta tres intentos; preferencias en la app Evri; franja de 2 horas por correo o SMS. |
| DPD | Tarjeta más SMS o correo para elegir vecino, lugar seguro, nueva fecha o tienda asociada; franja de una hora. |
| Yodel | Hasta tres intentos; zonas insulares pueden tener solo uno antes de recoger en oficina postal. |
| [Amazon Shipping](carrier:amazon-shipping) | Hasta tres intentos en 3 días consecutivos. |
| DHL Parcel UK | Hasta 2 intentos. |
| Parcelforce | 1 intento, con opciones de lugar seguro y desvío. |
| [UPS](carrier:ups) | 1 intento, seguido de aviso digital con instrucciones de nueva entrega. |

Los envíos internacionales con seguimiento parcial pasan a socios locales y siguen las normas del correo del país de destino. Whistl no tiene puntos de recogida propios: `Delivered col. point` se refiere a la tienda o taquilla del transportista final. Un paquete rechazado regresa al remitente y no puede volver a repartirse.

¿Figura entregado pero no está? Las guías de Whistl aconsejan comprobar primero el aviso, lugares seguros y vecinos. Después consulta [entregado pero no recibido](guide:delivered-not-received).

## Paquete Whistl no entregado, retrasado o perdido

La página de contacto de Whistl indica al público que «contacte con el comercio donde realizó la compra». La tienda tiene el contrato, abre la consulta «Where Is My Item» con Whistl y tramita cualquier reclamación.

Según las guías de Whistl:

- Un paquete británico que no puede entregarse regresa al remitente en un plazo de 5 días laborables desde su entrada en la red de Whistl.
- En envíos con seguimiento parcial no hay compensación por retrasos, y Whistl no puede intervenir una vez entregado al socio sin entrega final.
- Los plazos de reclamación del remitente dependen del transportista: por ejemplo 28 días desde el envío en Evri, o en Royal Mail una espera mínima de 10 días naturales antes de reclamar y un límite de 80 días. Avisa pronto a la tienda.

Si el seguimiento se detiene, empieza por [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

## Teléfono y atención al cliente de Whistl

Datos publicados en la página de contacto de Whistl:

- **Sobre tu paquete:** la tienda donde compraste.
- **Seguimiento:** el botón «Whistl Tracking Portal» en la página de contacto.
- **Consultas generales:** `01628 702 965`, de 08:30 a 17:30 hora del Reino Unido. Línea destinada a clientes empresariales de Whistl.
- **Correo de Customer Services:** `packagescustomerservices@whistl.co.uk`, que «no puede atender al público en consultas de seguimiento de artículos».
- **Sede central:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

En Grecia, Chipre, Luxemburgo y Malta Whistl no dispone de oficina ni teléfono. Consulta a la tienda y, tras el traspaso, al transportista o correo local indicado en el rastreador.

## Seguir un paquete Whistl con Peek

[Peek](/) sigue a más de 4500 transportistas. Si la referencia tiene 18 caracteres empezando por `L` y 17 cifras, Peek consulta directamente a Whistl y muestra «Whistl tiene este paquete.» cuando Whistl lo conoce. Otras referencias de Whistl no tienen formato fijo: pega un enlace de Whistl (`web.parcelhub.net` o `despatch.whistl.co.uk` con `/Tracking/reference/…`, o `trackmyitem.whistl.co.uk` con `/tracking/…`), que identifica Whistl al instante («Transportista detectado»), o añade la referencia y elige «Whistl» con «Cambiar». No hace falta código postal. Una referencia con forma de otro transportista (como un código de Royal Mail acabado en `…GB`) sigue a ese otro transportista, no a Whistl.

Peek consulta el rastreador Parcelhub de Whistl. Sus horas no llevan zona horaria: Peek las lee en la hora local del lugar del escaneo (hora del Reino Unido en el Reino Unido) y las muestra en tu zona horaria («Horas en tu zona horaria:» seguido de su nombre). Muestra estado, servicio, quién firmó al entregarse, el número canónico de Whistl si difiere de la referencia y el transportista final con su número. No hay fecha prevista de entrega, peso ni punto de recogida. Los estados conservan el inglés de Whistl, salvo `Delivered`, `In transit` y `Returned to sender`, mostrados en el idioma de Peek.

Cuando Whistl indica Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK (DHL eCommerce UK), Amazon Shipping, [InPost](carrier:inpost), UPS o [FedEx](carrier:fedex), Peek puede continuar siguiéndolo allí. Si solo nombra Evri, DPD o Yodel no se enlaza automáticamente: añade el número propio de ese transportista, o usa la web de Yodel, ya que Peek no sigue a Yodel. Una referencia de varios bultos no se sigue: añade cada número individual.

Mientras tengas el paquete abierto en Peek —o el día de entrega con notificaciones activadas— Peek consulta directamente a Whistl hasta cada 10 minutos, y hasta cada 2 minutos (o cada 5 minutos si debe abrir el rastreador de Whistl en un navegador) desde las 6:00 en la hora del último escaneo (hora del Reino Unido en el Reino Unido) el día en que Whistl registra «En reparto», mientras el rastreador responda; fuera de ese caso y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Para notificaciones, elige «Cada escaneo», «Pasos importantes» o «Solo la entrega». Tras cuatro días sin escaneos en tránsito, fuera de aduanas o incidencias, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y avisa «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Whistl.», con el enlace «Pide ayuda a Whistl» a su contacto. Peek no puede contactar con Whistl ni con la tienda por ti.

## Preguntas frecuentes sobre Whistl

### ¿Whistl forma parte de Royal Mail?

No. Whistl UK Ltd es una empresa independiente, antes llamada TNT Post UK. Entrega muchos envíos a Royal Mail para el último tramo, de ahí el estado `Handed over to Royal Mail`.

### ¿Quién reparte los paquetes de Whistl?

Normalmente otro transportista: DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce o UPS en el Reino Unido, y socios locales en el extranjero.

### ¿Es legítimo el seguimiento de Whistl?

Sí. El seguimiento oficial de Whistl funciona en `web.parcelhub.net`, `despatch.whistl.co.uk` y `trackmyitem.whistl.co.uk`, y Parcelhub pertenece al Grupo Whistl.

### ¿Por qué no funciona mi número de seguimiento Whistl?

El rastreador muestra «Unable to find shipment» si desconoce la referencia. Compruébala con la tienda o sigue el número propio del transportista final recibido por SMS o correo.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – público remitido al comercio, teléfono y horario, correo Customer Services, portal de seguimiento, sede
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, servicios para empresas, Parcelhub parte de Whistl Group
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI y remesa, eventos normalizados, SMS y correos de transportistas, aviso de ausencia, rechazos, pérdidas, 5 días laborables, reclamaciones del remitente
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – traspaso a Royal Mail y socios, 18 días, sin compensación por retrasos, enlaces de seguimiento, correos de marca
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – transportistas empleados, intentos y opciones, plazos de reclamación
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – portales de seguimiento, búsqueda por UPI o remesa, sin asistencia tras traspaso
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – campos del formulario, formato de resultados, solo inglés
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – mismo rastreador bajo nombre de Whistl
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – cambio de nombre desde TNT Post UK, último tramo de Royal Mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – campos que lee Peek, estados registrados, detección y frecuencia de comprobación
:::
