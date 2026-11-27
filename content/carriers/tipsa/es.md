---
title: TIPSA seguimiento: número, estados y teléfono
description: Seguimiento TIPSA en España y Portugal: número de 22 dígitos (por qué 10 no sirven), estado Ausente, 15 días en agencia o 7 en Pickup y teléfono 900 10 10 47.
slug: tipsa-seguimiento
published: 2026-10-11
updated: 2026-11-27
---

Para hacer el seguimiento de un envío TIPSA (Transporte Integral de Paquetería, red Geopost con más de 330 agencias en España, Portugal y Andorra), pega el número completo de 22 dígitos en la casilla de arriba o introdúcelo con tu código postal en el [localizador de tip-sa.com](https://www.tip-sa.com/es/localizacion-envios) — el albarán suelto de 10 dígitos no funciona. Tras un estado `Ausente`, TIPSA hace un segundo intento el siguiente día hábil y guarda el paquete 15 días en agencia (7 días en puntos Pickup); su teléfono gratuito es `900 10 10 47`.

## Número de envío TIPSA: cómo es y dónde está

El localizador de TIPSA pide el número de envío «compuesto por 22 dígitos» y marca también como obligatorio el código postal de destino. El número son dos códigos de agencia seguidos del albarán:

:::anatomy 028145 028145 4829105736
- 028145 | Primer código de agencia: una agencia TIPSA que lleva el envío
- 028145 | Segundo código de agencia: otra agencia del envío
- 4829105736 | Albarán: el número propio del envío
:::

| Número (inventado) | Qué es |
| --- | --- |
| `0281450281454829105736` | El número completo de 22 cifras. Es el que sirve. |
| `028145 028145 4829105736` | El mismo número con espacios. Peek lo lee; el campo del localizador de TIPSA solo admite 22 caracteres, así que allí escríbelo sin espacios. |
| `4829105736` | El albarán de 10 cifras suelto. El localizador de TIPSA pide las 22 cifras, y Peek no lo toma por un número de TIPSA. |

Las tiendas pueden hacer que TIPSA te mande correos y SMS cuando sale el envío, cuando llega a la agencia, si hay una incidencia y tras la entrega; sus enlaces de seguimiento llevan a la página del envío en TIPSA. Sin el número, el localizador de TIPSA también busca por la referencia del envío, pero pide además un identificador (agencia y cliente) que tendrías que pedir al remitente. Más sitios donde buscarlo en [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

Mucha gente busca «seguimiento TIPSA 10 dígitos» o «seguimiento TIPSA 19». Solo funciona la forma completa de 22 cifras: búscala en el correo o pídesela a la tienda. Ojo también con los parecidos: 22 cifras encajan con Austrian Post, USPS y Estafeta, y un número cuyos dos códigos de agencia empiezan por `00` tiene la forma de uno de [CTT Express](carrier:ctt-express), otro transportista español. Mira qué transportista nombra el correo de la tienda.

## Estados de TIPSA: qué significa cada uno

La página del envío de TIPSA está solo en español. Según sus preguntas frecuentes, muestra el estado del envío y la fecha prevista de entrega. Arriba enseña una línea de pasos con etiquetas como `Documentado`, `En camino`, `En reparto` y `Entregado`, cada una con una frase de explicación. Debajo, la tabla «DETALLES DEL ENVÍO» recoge cada escaneo con fecha, hora y población. Estos son sus textos:

| Estado | Qué significa para ti |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | La tienda ha registrado el envío, pero TIPSA aún no tiene el paquete: es el [estado de etiqueta creada](guide:tracking-statuses). |
| `TRANSITO` | Va de camino entre agencias y plataformas de TIPSA. |
| `LECTURA EN HUB MADRID` | Lo han escaneado en una plataforma de TIPSA; el nombre cambia (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Todavía no ha llegado a la agencia que lo va a repartir. |
| `LEIDO EN DESTINO` | Lo han escaneado en destino. |
| `LECTURA EN AGENCIA DESTINO` | Lo ha escaneado la agencia que lo reparte; detrás va su nombre. |
| `REPARTO` | Ha salido para entregártelo. |
| `Ausente` | No han podido entregarlo porque no te localizaron. |
| `Faltan Datos / Datos incorrectos` | Faltan datos de la dirección o están mal. Se anota en el reparto, antes de otro intento. |
| `Cerrado` | Al ir a entregarlo, encontraron cerrada la dirección. Se anota en el reparto, después de `REPARTO`. |
| `Solicitada en otra dirección` | Se ha pedido la entrega en otra dirección y el paquete se desvía hacia ella. |
| `DISPONIBLE` o `Pendiente de recoger en Tipsa` | Tras una entrega fallida, te espera en la agencia. |
| `Devolver a Origen` | Vuelve al remitente. |
| `ENTREGADO` | Entregado. |

`Ausente` lleva una nota de TIPSA: no pudieron contactar contigo, harán un segundo intento el siguiente día hábil y puedes ponerte en contacto con la agencia de destino para acordar la entrega. La página muestra esa agencia, con su dirección, bajo «AGENCIA DESTINO».

## TIPSA no entrega mi paquete: ausente, agencias y puntos Pickup

TIPSA reparte con su propia red en toda la península (Portugal incluido), Baleares, Canarias, Ceuta y Melilla, y fuera a través de DPDgroup. Tras un `Ausente`, el segundo intento llega el siguiente día hábil; la tarifa que paga la tienda incluye dos intentos de entrega, o tres con su servicio Premium para tiendas online.

**Recoger en la agencia.** Si no vas a estar o prefieres recogerlo, el paquete queda en la delegación más cercana a tu código postal durante 15 días desde su salida, no desde que llega. Pasado ese plazo, vuelve al remitente. Lleva tu DNI o pasaporte; si va otra persona en tu nombre, necesita una fotocopia del tuyo. El [buscador de agencias](https://www.tip-sa.com/es/agencias-de-transporte) busca por provincia, código postal o código de delegación.

**Cambiar la dirección.** Solo el remitente puede autorizar un cambio de dirección o de datos del envío, dice TIPSA, así que pídeselo a la tienda.

**Puntos Pickup y lockers.** TIPSA comparte con [SEUR](carrier:seur) una red de puntos Pickup en comercios y lockers automáticos abiertos 24/7. La mayoría de los puntos abren al menos seis días a la semana, también por la tarde y el fin de semana, y tienes 7 días para recoger el paquete. La entrega en un punto es una opción que ofrece la tienda. Encuentra uno con el [buscador de puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/).

Algunas tiendas contratan también la entrega en sábado o el preaviso, un SMS y un correo que te dicen qué día llega el paquete a destino. Con el servicio Premium puedes elegir además la franja horaria de la entrega, con una hora de margen; TIPSA no explica dónde se elige, así que pregúntalo a la agencia de destino.

**Pagar en la entrega.** Con el contrarreembolso, TIPSA te cobra en destino, al entregarte el paquete, el importe que fijó la tienda, hasta 1000 euros por envío; con portes debidos, te cobra el transporte, también en destino. TIPSA no dice si se paga en efectivo o con tarjeta: pregúntalo a la tienda o a la agencia de destino. Son los únicos cobros al destinatario que describe, así que un SMS o un correo «de TIPSA» que te pide pagar a través de un enlace no encaja con ninguno: no pagues y busca el número en el localizador de tip-sa.com. Más en [SMS falsos de paquetería](guide:delivery-scam-texts).

## Paquete TIPSA retrasado, perdido o devuelto: reclamación

Las páginas de TIPSA no hablan de un envío cuyo seguimiento se queda parado. Llama a la agencia de destino que aparece en la página del envío o pregunta a la tienda; [por qué el seguimiento no se actualiza](guide:tracking-not-updating) te ayuda a saber cuándo preocuparte.

Si la mercancía se pierde o llega dañada, las preguntas frecuentes de TIPSA son claras: ponte en contacto con el remitente para que lo sepa. La tienda tiene el contrato con TIPSA y es quien reclama; TIPSA no publica un procedimiento ni un plazo de reclamación para el destinatario. Tampoco tiene una página sobre un paquete que pone `ENTREGADO` y no te ha llegado: avisa también a la tienda en ese caso.

Un paquete que nadie recoge en 15 días desde su salida vuelve al remitente, así que pregunta a la tienda por el reembolso o un nuevo envío.

Para devolver una compra, pregunta también a la tienda: TIPSA ofrece a las tiendas gestionar sus devoluciones y cambios. Si prefieres enviarlo tú, las preguntas frecuentes de TIPSA remiten a su herramienta MANDALOXTIPSA o a la delegación más cercana, donde se paga en el momento.

## TIPSA teléfono y atención al cliente

Así lo indican la página de contacto y las preguntas frecuentes de TIPSA:

- **Teléfono:** 902 10 10 47 o 900 10 10 47, según la página de contacto. Las preguntas frecuentes dan solo el 900 10 10 47. TIPSA no indica horario ni coste de la llamada.
- **Formulario:** en la [página de contacto](https://www.tip-sa.com/es/contacto), con el motivo «Tengo un problema con mi envío» y el número del envío.
- **Correo:** info@tip-sa.com, que las preguntas frecuentes dan si no tienes el número de envío.
- **La agencia de destino**, que aparece en la página del envío, para acordar la entrega.

TIPSA mantiene una página de su app, pero sus fichas en las tiendas de aplicaciones ya no cargan.

## Seguir un envío TIPSA con Peek

[Peek](/) acepta el número de 22 cifras con o sin espacios. Otros transportistas comparten esa forma, así que Peek pregunta a la vez a los posibles, TIPSA el primero: verás «preguntando a…» y luego «TIPSA tiene este paquete.» si TIPSA lo conoce. Un número cuyos dos códigos de agencia empiezan por `00`, con el segundo entre las agencias que constan para CTT Express, se asigna a CTT Express; con los demás números cuyos dos códigos empiezan por `00`, Peek pregunta a la vez a TIPSA, la primera, a CTT Express y a Austrian Post. Si la tienda te dijo TIPSA, pulsa «Cambiar» y elígelo. No hace falta código postal.

El albarán de 10 cifras no se puede seguir a través de TIPSA, ni con «Cambiar» ni desde un enlace. Peek no propone TIPSA para 10 cifras ni le pregunta por ellas; si eliges TIPSA, Peek avisa de que TIPSA «no emite números como este» y pregunta a servicios de seguimiento. Busca el número completo de 22 cifras, o el enlace de seguimiento, en el correo o el SMS de envío, o pídeselo a la tienda. Puedes pegar el enlace del correo de la tienda: Peek lee los enlaces de tip-sa.com con `id=` en páginas que acaban en `datos_env.php`, `datos_prestashop.php` o `datos.php`, y en la dirección principal de tip-sa.com. Los enlaces al localizador de TIPSA o a la página del envío en dinapaqweb no se leen.

Peek muestra el estado, el historial con la población de cada escaneo en un mapa, el nombre del remitente si TIPSA no lo oculta y el peso. Los estados salen en el español de TIPSA, tal como los escribe. Peek lee todas las horas como hora de Madrid, también las de agencias portuguesas, y las muestra en tu zona horaria; en peektracker.com y en las apps para iPhone y Android, una línea al final del historial lo recuerda: «Horas en tu zona horaria: …». Peek resume además cada estado con sus palabras: `REPARTO` sale como «En reparto»; `Ausente`, `Cerrado` y `Faltan Datos / Datos incorrectos`, como «Intento de entrega»; `DISPONIBLE` y `Pendiente de recoger en Tipsa`, como «Listo para recoger», y `Devolver a Origen`, como «Incidencia». Descarta el destinatario, la dirección, los códigos postales, la referencia, el número de bultos, el volumen y la prueba de entrega. No muestra fecha de entrega prevista ni sigue el paquete si pasa a otro transportista. Mientras tienes el paquete abierto en pantalla, o el día de la entrega con las notificaciones activadas, y responde el propio seguimiento de TIPSA, consulta TIPSA hasta una vez cada 10 minutos, y hasta una vez cada 2 el día en que TIPSA lo escanea en reparto, desde las 6:00, hora de Madrid; el resto del tiempo, cada hora. Cuando solo responden servicios de seguimiento, consulta cada hora, cada 3 horas cuando el último escaneo tiene un día, y hasta cada 15 minutos el día del reparto mientras lo tienes abierto o con notificaciones activadas. Puede enviarte notificaciones cuando cambia el estado. Tras dos días sin novedades, o tras 12 horas si el paquete sigue en «Etiqueta creada» o «Admitido por el transportista», Peek pasa a consultar una vez por hora.

## Preguntas sobre el seguimiento de TIPSA

### ¿Qué es TIPSA y cómo es el número de seguimiento de 22 dígitos?

TIPSA (Transporte Integral de Paquetería) es una red de paquetería urgente del grupo Geopost con más de 330 agencias y 13 plataformas en España, Portugal y Andorra. Su número de envío tiene 22 dígitos: dos códigos de agencia de 6 cifras seguidos del albarán de 10 cifras.

### ¿Por qué no funciona el seguimiento TIPSA con 10 dígitos?

Las 10 cifras finales son solo el albarán: ni el localizador de tip-sa.com ni Peek pueden rastrear un paquete TIPSA sin el código completo de 22 dígitos. Búscalo en el correo o SMS de envío o pídeselo a la tienda (sin él, el localizador exige referencia más identificador de agencia y cliente).

### ¿Cuánto tarda TIPSA en entregar y reparte los sábados?

Depende del servicio contratado por la tienda: TIPSA ofrece entrega en 24 horas (el siguiente día laborable) o en 48 horas. La entrega en sábado existe solo si el remitente la contrató como servicio adicional.

### ¿Qué hago si el seguimiento pone `Ausente` o quiero recoger en agencia o Pickup?

Tras un `Ausente`, TIPSA intenta una segunda entrega el siguiente día hábil (2 intentos en Estándar, 3 en Premium) y puedes concertarla con la agencia de destino que aparece en el seguimiento. En delegación el paquete espera 15 días desde la salida (lleva DNI), y en puntos Pickup o lockers compartidos con SEUR espera 7 días.

### ¿Cuál es el teléfono gratuito de TIPSA y a quién reclamo?

Las preguntas frecuentes de TIPSA dan el teléfono gratuito `900 10 10 47` (la página de contacto añade `902 10 10 47`), el correo `info@tip-sa.com` y el [formulario de contacto](https://www.tip-sa.com/es/contacto). Para pérdida, daño o cambio de dirección, contacta siempre con la tienda remitente.

:::sources
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – número de 22 cifras, formulario, búsqueda por referencia con identificador (agencia y cliente), teléfono del pie de página
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – estado y fecha prevista en la página del envío, número que falta, agencia de reparto, 15 días en la delegación, DNI, cambio de dirección, pérdida o daños, envíos de particulares con MANDALOXTIPSA o en una delegación y pago en el momento, teléfono 900, correo, cobertura
- [TIPSA: Contacto](https://www.tip-sa.com/es/contacto) – teléfonos, motivos del formulario
- [TIPSA: Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – cobro en destino al entregar, hasta 1000 euros por envío
- [TIPSA: Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – cobro del transporte en destino
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – página del envío solo en español, textos de estado y explicaciones, agencia de destino
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, 330 agencias, plataformas, lockers
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – dos intentos, 24 y 48 horas, franja horaria con eTIPSA Premium, devoluciones y cambios, sábados, avisos de las tiendas
- [TIPSA: 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – tres intentos con Premium, dos con Estándar, devoluciones y cambios
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – red compartida con SEUR, 7 días, horarios de los puntos
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – buscador de agencias
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – buscador de puntos Pickup
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – nombre de la empresa
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – preaviso por SMS y correo
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – página de la app
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lo que lee y muestra Peek, estado `Cerrado`
:::
