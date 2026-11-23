---
title: Seguimiento CVC (Си Ви Си): seguir un paquete en Bulgaria
description: Sigue un paquete de CVC (Си Ви Си, e-CVC) en Bulgaria con Peek: cómo añadir el número, elegir CVC, qué significan los estados y qué hacer si se detiene.
slug: seguimiento-cvc
published: 2026-11-23
updated: 2026-11-23
---

CVC es un transportista de Bulgaria. El catálogo de Peek también lo conoce como Си Ви Си, Си Ви Си ЕООД y e-CVC. Para seguir un paquete de CVC, pega un enlace al rastreador de CVC en la casilla de arriba, o pega su número y elige CVC como transportista: Peek no puede reconocer un número de CVC solo por su forma. El número lo facilita la tienda o la persona que envió el paquete. El rastreador propio de CVC está en my.e-cvc.bg/track, en búlgaro: pide el número de carta de porte y tiene un único botón, «Провери» (Comprobar).

Las propias páginas de CVC apenas dan más información: my.e-cvc.bg solo muestra ese rastreador y una pantalla de acceso, sin páginas públicas sobre reglas de entrega, tarifas o contactos. Por eso esta página explica qué pide el rastreador de CVC y cómo sigue Peek un paquete de CVC, sin incluir normas de entrega, precios ni teléfonos que CVC no publica.

## Número de seguimiento de CVC: cómo es

El rastreador de CVC pide un número de carta de porte (*товарителница* en búlgaro). Su campo solo admite dígitos: descarta letras y espacios al escribir y admite como máximo 20 caracteres. Por tanto, un número de CVC consta de hasta 20 dígitos, pero el catálogo de Peek no tiene ningún patrón asignado a CVC: Peek no lo reconoce como CVC y CVC nunca aparece en «Encaja con este número» al añadirlo. Esta página no ofrece ningún ejemplo inventado porque el rastreador de CVC no muestra cómo es un número completo.

| Lo que pegas | Lo que hace Peek |
| --- | --- |
| Solo el número de CVC | No nombra a CVC. Puede mostrar otros transportistas cuyo formato encaje, o ninguno. Elige CVC tú mismo, como se indica en «Seguir un paquete de CVC con Peek» más abajo. |
| Un enlace al rastreador de CVC: `my.e-cvc.bg/track?wb=` y el número | Peek extrae el número del enlace e identifica CVC al instante («Transportista detectado»). |
| Un número que la tienda indica que es de otro transportista | Síguelo con ese transportista: el correo de la tienda es la mejor pista sobre quién reparte. |

**Dónde encontrarlo.** En el correo de envío de la tienda o en la página de tu pedido, a veces como número de albarán o carta de porte (*waybill*). Si te lo envió un particular, pídeselo. ¿Sin número? Consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats) para ver otros transportistas.

## Qué significan los estados de seguimiento de CVC en Peek

Peek consulta directamente la página de seguimiento de CVC en my.e-cvc.bg/track y muestra cada línea con el texto búlgaro de CVC, clasificada bajo una etapa de Peek en español. Los principales textos búlgaros que Peek reconoce:

| CVC dice | Peek muestra | Qué significa para ti |
| --- | --- | --- |
| `Приета за събиране` | Etiqueta creada | El envío está registrado para recogida; CVC aún no ha recogido el paquete. |
| `Отложено събиране за …` | Etiqueta creada | La recogida se aplazó hasta la fecha indicada. |
| `Взета от куриер …` | Admitido por el transportista | Un repartidor de CVC ya recogió el paquete. |
| `Приета в хъб …`, `Транспорт към хъб …` | En tránsito | Llegó a un centro logístico de CVC o viaja hacia uno. |
| `Приета за разнос` | En reparto | El paquete salió hoy a reparto. |
| `Доставена`, `Доставена на …` | Entregado | El paquete fue entregado. |

Cuando el rastreador de CVC no encuentra un número, responde `Пратката не е открита!` (envío no encontrado). Si el rastreador de CVC no responde, Peek también puede consultar servicios de seguimiento como ParcelsApp, Ship24 y 17TRACK y mostrar el historial del primero que tenga el paquete, ordenado en las etapas habituales de Peek («Listo para recoger», «Intento de entrega», «Devuelto al remitente», «Incidencia»).

Antes de que haya algún movimiento, Peek muestra «Buscando novedades» mientras consulta y luego «Esperando a CVC». Si dos comprobaciones seguidas fallan, sigue mostrando «Esperando a CVC» y su página indica: «Aún no se ha podido conectar con CVC. Peek sigue intentándolo.» Más en [estados de seguimiento explicados](guide:tracking-statuses).

## Entregas de CVC en Bulgaria

Las páginas de CVC no publican plazos de entrega, intentos de reparto, oficinas, reglas de contra reembolso ni días de recogida: my.e-cvc.bg solo tiene su rastreador y el inicio de sesión. Pregunta a la tienda o al remitente cómo y cuándo entrega CVC, ya que contrataron el envío. Si falla una entrega o el paquete espera en algún lugar, consulta [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

## Paquete de CVC sin actualizar, perdido o devuelto

**Nada todavía.** Puede que CVC aún no registre el número. Peek muestra entonces «Esperando a CVC» y sigue comprobando. Si dos días después de añadir el paquete sigue sin haber movimientos, Peek avisa: «Esperando la primera actualización del transportista». Comprueba el número con el correo de la tienda. Si tras varios días no aparece nada, abre el rastreador de CVC con «Abrir la web de CVC» en la página del paquete y pregunta a la tienda si realmente se envió.

**Sin novedades durante días.** Tras cuatro días sin una línea nueva, un paquete que siga en «Etiqueta creada», «Admitido por el transportista» o «En tránsito» muestra el aviso «Sin novedades desde hace cuatro días». Ver [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Entregado, pero no llegó nada.** Revisa alrededor de la puerta y pregunta a vecinos, y luego consulta a la tienda. Ver [entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** Avisa primero a la tienda: contrató el envío y puede reclamar a CVC o enviarte un nuevo paquete. Ver [paquetes perdidos](guide:lost-parcel) y [paquetes dañados](guide:damaged-parcel).

**Devuelto.** «Devuelto al remitente» significa que el paquete volvió a la tienda o al remitente. Pídeles un reembolso o un nuevo envío. Ver [devuelto al remitente](guide:returned-to-sender).

## Contacto y atención al cliente de CVC

Las páginas de CVC no muestran teléfono, correo electrónico ni oficina: my.e-cvc.bg solo ofrece su rastreador y el acceso de usuarios. Peek solo incluye contactos leídos en páginas oficiales del transportista, así que esta página no lista ninguno. Acude primero a la tienda o al remitente con tu número de pedido y de seguimiento: contrataron la entrega y pueden gestionarlo con CVC.

## Seguir un paquete de CVC con Peek

[Peek](/) sigue a más de 4.500 transportistas, entre ellos CVC, pero no puede identificar un paquete de CVC solo por su número. Un enlace al rastreador de CVC identifica CVC al instante; con el número solo, elige el transportista manualmente:

1. Pega el número en la casilla de arriba.
2. Pulsa «Cambiar» junto a la línea del transportista y busca CVC. También sirve buscar «e-CVC» o «Си Ви Си» (con «También llamado e-CVC» o «También llamado Си Ви Си» bajo el nombre), así como «Bulgaria».
3. Elige CVC: la línea pasará a mostrar «Elegido por ti». Empieza el seguimiento.

En un paquete ya añadido, usa «Cambiar transportista». Para CVC, Peek solo admite números de 1 a 20 dígitos; para cualquier otro valor dice que CVC «no emite números como este». Si Peek lista otros transportistas en «Encaja con este número», es solo porque coinciden en longitud. Antes de elegir, la línea puede decir «aún no encontrado · Peek seguirá comprobando», lo habitual en números recientes.

Si dejas que Peek elija el transportista, consulta servicios de seguimiento: si muestran el paquete en movimiento y nombran a CVC, Peek lo archiva automáticamente en CVC.

**Qué verás.** El estado y cada línea del historial con fecha y hora en hora local de Bulgaria. CVC no incluye localidad de escaneo, día estimado de entrega, punto de recogida ni peso. «Abrir la web de CVC» abre tu número directamente en el rastreador búlgaro de CVC.

**Otro transportista.** Durante los primeros 30 días del paquete, Peek también pregunta a los transportistas cuyo formato encaje con el número. Si alguno muestra el paquete en camino con ese número, Peek lo cambia allí y muestra «Cambiado automáticamente desde CVC».

**Frecuencia.** Mientras responda el rastreador de CVC, Peek revisa hasta cada 10 minutos, y cada 2 minutos desde las 6:00 hora local el día en que CVC lo marca en reparto (`Приета за разнос`). Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Las notificaciones cubren «Todas las novedades», «Solo importantes» o «Solo el día de entrega».

Peek no puede contactar a CVC, a la tienda ni al remitente por ti, ni cambiar la entrega o abrir una reclamación.

## Preguntas sobre el seguimiento de CVC

### ¿Cómo sigo un paquete de CVC?

Pega un enlace al rastreador de CVC en la casilla de arriba y Peek elige CVC por sí solo. Con el número solo, pulsa «Cambiar» y elige CVC, ya que Peek no reconoce su forma automáticamente. En un paquete ya añadido, usa «Cambiar transportista». El rastreador oficial está en my.e-cvc.bg/track: escribe el número de carta de porte y pulsa «Провери».

### ¿Cómo es un número de seguimiento de CVC?

Es un número de carta de porte (*товарителница*) compuesto solo por dígitos: el rastreador de CVC elimina letras y espacios y admite hasta 20 dígitos. El catálogo de Peek no tiene un patrón exclusivo para él, así que elige CVC manualmente.

### ¿Es e-CVC lo mismo que CVC?

Sí. El rastreador de CVC en my.e-cvc.bg se llama e-CVC.bg y lleva el logotipo de CVC, y el catálogo de Peek incluye e-CVC, Си Ви Си y Си Ви Си ЕООД como nombres alternativos de CVC. Buscar cualquiera de ellos en la lista de transportistas de Peek encuentra CVC.

### ¿Por qué mi paquete de CVC dice «Esperando a CVC»?

En peektracker.com, un paquete guardado en CVC muestra ese texto mientras el rastreador de CVC (o un servicio de seguimiento) todavía no tiene movimientos para el número o no ha podido responder: su página indica entonces «Aún no se ha podido conectar con CVC. Peek sigue intentándolo.» Comprueba el número con la tienda y consúltalo con «Abrir la web de CVC».

### ¿Cuál es el número de teléfono de CVC?

Esta página no indica ninguno porque las páginas de CVC no publican teléfono. Pregunta a la tienda que envió el paquete: es quien contrató la entrega con CVC.

:::sources
- [CVC: seguimiento e-CVC.bg](https://my.e-cvc.bg/track) – rastreador en búlgaro, «Потърсете товарителница», solo dígitos, máximo 20 caracteres, «Провери», número tras `wb=`, nombre e-CVC.bg, logotipo CVC
- [CVC: acceso e-CVC.bg](https://my.e-cvc.bg/) – solo página de acceso, sin páginas de entrega, precios ni contacto
- [Universal Parcel Scraper: CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – nombres y alias, Bulgaria, dirección del rastreador, regla de enlaces, sin patrón de número, consulta directa a my.e-cvc.bg/track y estados en búlgaro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – servicios de seguimiento de respaldo, correspondencia del nombre de transportista con CVC, enlace a la web del transportista
:::
