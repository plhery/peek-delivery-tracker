---
title: Seguimiento OCA — números, estados, sucursales y entregas
description: Rastrea envíos de OCA Argentina y e-Pak. Formatos de 19 dígitos, significado de estados, 2 visitas a domicilio, 5 días en sucursal, SmartLockers y ayuda.
slug: seguimiento-oca
published: 2026-11-23
updated: 2026-11-23
---

OCA es un operador argentino de paquetería y correo. Su plataforma ePak sirve a ecommerce, grandes empresas y pymes que envían a todo el país, y trabaja con Mercado Envíos; también entrega en Puntos OCA y SmartLockers. Los destinatarios pueden consultar sus envíos en el [buscador de envíos de OCA](https://www.oca.com.ar/Busquedas/Envios) o ubicar su punto más cercano en el [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales).

El seguimiento de OCA registra cada ingreso en planta, viaje entre sucursales, visita a domicilio y plazo de retiro.

## Formatos de número de seguimiento de OCA

Los paquetes estándar de OCA e-Pak utilizan un número de guía u orden de admisión de 19 dígitos:

- **19 dígitos con `0000` en las posiciones 6 a 9** (por ejemplo, `3654800004829173605`) — el número de guía u orden de admisión con el que el Centro de Ayuda de OCA indica hacer el seguimiento.
- **Un `-1` apartado** — algunas etiquetas imprimen un `-1` tras el número, como `3654800004829173605-1`. El [Centro de Ayuda de OCA](https://www.oca.com.ar/CentroDeAyuda) indica no escribirlo. En Peek tampoco: Peek solo quita el guion, y `...-1` se convierte en un número de 20 dígitos que OCA no conoce.
- **Envíos con estampilla y postales** — para encomiendas con estampilla OCA usa 3 letras mayúsculas (`YAE`, `YAA`, `MDA`, `PAA`) y 8 números sin espacios ni paréntesis; los sobres con estampilla llevan 2 letras y 8 números. Las postales simples sin estampilla no tienen seguimiento y se entregan en el buzón o por debajo de la puerta.

En la web de OCA puedes sustituir los ceros centrales por `//`; Peek necesita los 19 dígitos completos o un enlace directo de `oca.com.ar/Seguimiento/Paquetes/...` o heredado de e-Pak, que Peek reconoce al instante. En Argentina, Peek consulta a OCA por un número de 19 dígitos con esta forma; fuera de Argentina solo lo consulta como último recurso. Puedes comparar otros formatos en la [guía de formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de OCA

El servicio de seguimiento e-Pak de OCA informa cada movimiento en español y suele añadir al final la sucursal (`- Suc: ...`) o el motivo entre paréntesis.

| Estado de OCA | Qué significa |
| --- | --- |
| `En proceso de Retiro` | El retiro en el domicilio del remitente está programado; OCA aún no colectó el paquete. |
| `Retirado` / `Retirado en Origen` | OCA ya retiró el paquete en origen. |
| `En Proceso en OCA` / `Procesado en OCA` | El envío fue procesado en una planta o sucursal de OCA. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | Viaja hacia el centro de distribución o la sucursal encargada de la entrega o el retiro. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Ingresó en el centro de destino o en la sucursal local de OCA. |
| `Programado para visita a domicilio` | El envío está ingresado en la sucursal «en programación», próximo a salir a reparto domiciliario. |
| `Visita a Domicilio en Curso` | El distribuidor está en recorrido para entregar el paquete hoy. |
| `No entregado (No Visitado)` / `No entregado (...)` | La entrega no se concretó; el texto entre paréntesis indica el motivo (por ejemplo, no visitado o solicitud del remitente). |
| `Reprogramado para nueva visita` | Reprogramado para un nuevo intento tras una visita sin entrega. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | El paquete aguarda para que lo retires en la sucursal indicada. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Entregado en domicilio o retirado en sucursal. |
| `Acuse digitalizado` | Digitalización del comprobante de entrega en planta días después de entregado; es solo administrativo y no mueve el envío. |
| `Devolución en Proceso` / `Devuelto al Remitente` | En viaje de regreso o ya devuelto al remitente. |

## Visitas a domicilio, retiro en sucursal y SmartLockers

El [Centro de Ayuda de OCA](https://www.oca.com.ar/CentroDeAyuda) detalla las condiciones vigentes para entregas en Argentina:

- **Dos visitas a domicilio:** OCA realiza dos visitas sin coordinación previa, de lunes a sábados de 9 a 18 h. Puede recibir cualquier persona mayor de 18 años presentando su DNI. En días de lluvia el servicio funciona de forma limitada.
- **Retiro en sucursal (5 días hábiles):** Si no encuentran a nadie tras las visitas, el paquete queda disponible durante **5 días hábiles** en el Punto OCA asignado. Retira el titular con DNI y número de envío (excluyente), o una persona autorizada mayor de 18 años con fotocopia del DNI del titular, nota manuscrita y número de envío. Vencido el plazo, se devuelve al remitente.
- **SmartLockers (48 horas + extensión opcional):** Cuando OCA deposita tu paquete en un locker, recibes un correo y/o SMS con código QR y clave alfanumérica. Tienes **48 horas corridas** para retirarlo; si se acerca el vencimiento llegan avisos y un botón de pago para sumar hasta **3 días adicionales** abonando con tarjeta antes de la devolución al remitente.
- **Dirección incorrecta o envío devuelto:** OCA no permite interrumpir el envío ni modificar la dirección una vez despachado. Debes aguardar el circuito de visitas para retirarlo personalmente en sucursal o esperar que vuelva al remitente para coordinar un reenvío. Consulta también la [guía de entregas fallidas](guide:missed-delivery).
- **Trámites especiales (DNI, pasaportes, Tarjeta OCA Geo y sobres):** El DNI se entrega solo en domicilio presentando el talón de RENAPER (sin retiro en sucursal); el pasaporte pasa a sucursal tras dos visitas al domicilio. La Tarjeta OCA Geo solo se entrega o retira por el titular. Los sobres con estampilla tienen dos visitas y, si no se entregan, regresan al remitente sin pasar a sucursal.
- **Hasta 48 horas hábiles para aparecer:** OCA aclara que un envío puede tardar hasta **48 horas hábiles** desde su ingreso en sucursal en reflejarse en la web o app. Pasado ese plazo sin registros, solo el remitente puede gestionar el reclamo. Más consejos en [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).

Si también recibes paquetes con otro correo privado argentino, puedes ver nuestra guía de [Andreani](carrier:andreani).

## Seguir envíos de OCA con Peek

Pega tu número de 19 dígitos sin el `-1`, o un enlace de `oca.com.ar/Seguimiento/Paquetes/...`, en Peek. Si necesitas seleccionar o corregir el transportista manualmente, toca **Cambiar** al añadir el paquete o **Cambiar transportista** desde la ficha del envío.

Peek consulta directamente el servicio web e-Pak `Tracking_Pieza` de OCA mediante el [adaptador de OCA en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Convierte cada fecha al huso oficial de Argentina (`America/Argentina/Buenos_Aires`, `-03:00`), extrae el nombre de la sucursal en el campo de ubicación y evita que un registro posterior de `Acuse digitalizado` altere una entrega ya completada. El servicio e-Pak de OCA no devuelve fecha estimada de entrega ni peso.

Peek traduce los estados de OCA a etapas claras: **Etiqueta creada** (`En proceso de Retiro`), **Admitido por el transportista** (`Retirado`), **En tránsito** (procesos en planta y traslados entre sucursales), **En reparto** (`Programado para visita a domicilio` y `Visita a Domicilio en Curso`), **Intento de entrega** (`No entregado` y `Reprogramado para nueva visita`), **Listo para recoger** (`En Espera de Retiro por Sucursal`), **Entregado** (`Entregado`) y **Devuelto al remitente** (`Devolución en Proceso` / `Devuelto al Remitente`).

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek revisa hasta cada 10 minutos, y cada 2 minutos desde las 6:00, hora de Argentina, el día en que OCA muestra `Programado para visita a domicilio` o `Visita a Domicilio en Curso`, mientras el rastreador de OCA responda. Si no, revisa cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, revisa cada hora sigas como sigas el paquete, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de OCA no responde y Peek sigue el paquete solo a través de servicios de seguimiento, revisa cada hora, cada 3 horas tras un día sin novedades y hasta cada 15 minutos el día en que el paquete sale a reparto.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete dice entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a OCA.», con el enlace «Pide ayuda a OCA» a el Centro de Ayuda de OCA. Tras tres semanas sin un escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a OCA.»

En las notificaciones de Peek puedes elegir entre **Cada escaneo**, **Pasos importantes** o **Solo la entrega**.

## Cómo contactar con OCA

- **Centro de Ayuda y formulario de consultas:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (preguntas frecuentes por tipo de servicio y formulario directo de soporte al final de la página)
- **Seguimiento oficial:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Sucursales y Puntos OCA:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Web principal:** [oca.com.ar](https://www.oca.com.ar/)
- **Contacta primero al remitente si no hay registro o fue devuelto:** OCA establece que el remitente es el único habilitado para reclamar un envío que no figura tras 48 horas hábiles o para gestionar un reenvío si el paquete volvió a origen.

## Preguntas frecuentes

### ¿Por qué mi número de OCA termina en `-1`?

El Centro de Ayuda de OCA indica no escribir el `-1` apartado y hacer el seguimiento con los 19 dígitos de la guía u orden de admisión. Haz lo mismo en Peek: no quita el `-1`, y con él el número no es uno que OCA conozca.

### ¿Cuántos días guarda OCA un paquete en sucursal o en un SmartLocker?

En sucursales y Puntos OCA el paquete permanece **5 días hábiles** antes de volver al remitente. En SmartLockers dispones de **48 horas corridas** y puedes abonar online hasta **3 días adicionales** desde el botón de pago antes de que sea devuelto.

### ¿Puedo cambiar la dirección de entrega si la cargué mal?

No. Según el Centro de Ayuda de OCA, no se puede interrumpir el envío ni modificar la dirección en curso. Tendrás que aguardar el circuito de visitas para retirarlo con DNI en la sucursal asignada o esperar a que vuelva al remitente.

### ¿Por qué mi código de OCA todavía no figura en el sistema?

OCA indica que un envío puede demorar hasta **48 horas hábiles** desde su ingreso en sucursal en mostrarse online. Si ya pasó ese plazo y sigue sin figurar, comunícate con tu remitente para que verifique el número con OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [Adaptador de OCA en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
