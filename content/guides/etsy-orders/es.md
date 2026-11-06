---
title: Seguimiento de Etsy: dónde está tu pedido y quién lo entrega
description: Por qué tu pedido de Etsy aún dice «No enviado», qué transportista lo entrega, cómo es su número de seguimiento y cuándo abrir un caso con Etsy.
slug: seguimiento-pedido-etsy
picture: Un paquete atado con cordel y con sellos, una furgoneta de correos y Pip corriendo a toda prisa.
published: 2026-10-17
updated: 2026-10-17
---

Etsy no envía nada por sí mismo: cada tienda fabrica o empaqueta sus pedidos, los envía con el transportista que elige y añade un número de seguimiento cuando el servicio lo tiene. Tu página de Compras te dice si el pedido ha salido de la tienda y cuándo debería llegar; el número de seguimiento te dice dónde está ahora. ¿Llega tarde o no aparece? Pregunta primero al vendedor y después a Etsy.

## ¿Por qué mi pedido de Etsy sigue diciendo «No enviado»?

Porque el vendedor aún no lo ha fabricado, empaquetado o marcado como enviado. Etsy calcula la fecha de entrega estimada así: «Tiempo de preparación + tiempo de tránsito de la compañía de transporte = Fecha de entrega estimada». El tiempo de preparación es lo que tarda el vendedor en fabricar el artículo y prepararlo para el envío. El ejemplo de Etsy: de 3 a 5 días de preparación y de 2 a 4 días de tránsito dan una entrega en 5 a 9 días. En el recibo también verás la fecha de envío con la que se ha comprometido el vendedor.

En **Compras y reseñas** (app: **Tu cuenta** → **Compras**), cada pedido muestra uno de estos estados:

| Estado | Qué significa |
| --- | --- |
| `No enviado` | El vendedor aún no ha completado el pedido o no lo ha actualizado en Etsy |
| `Enviado` | El vendedor ha completado el pedido en Etsy |
| `Notificado al transportista` | Hay número de seguimiento, pero el transportista aún no ha escaneado el paquete |
| `En tránsito` | El transportista lo tiene y va de camino |
| `Entregado` | Ha llegado y puedes dejar una reseña |

`Enviado` no siempre significa que un transportista tenga el paquete. El vendedor puede fijar una fecha de envío hasta 3 días después, y Etsy te avisa ese día. Solo con las etiquetas de Asendia, Evri, FedEx, USPS (excepto cartas y sobres) o UPS compradas en Etsy el correo espera al primer escaneo del transportista. Si la fecha estimada ya ha pasado y el pedido sigue en `No enviado`, Etsy dice que puede que el vendedor haya olvidado marcarlo como completado o que aún no lo haya enviado: pregunta en **Ayuda con el pedido**.

Dónde está el número en Etsy y en el correo de envío: [encontrar tu número de seguimiento](guide:find-tracking-number).

## ¿Qué transportista entrega un pedido de Etsy?

El que elige el vendedor. Etsy solo vende etiquetas de envío a vendedores de Estados Unidos, Canadá y Reino Unido; los demás compran el franqueo por su cuenta, en correos o en una empresa de mensajería. Para los envíos al extranjero, Etsy nombra los transportistas habituales en cada país:

| Vendedor en | Etiquetas que vende Etsy | Transportistas que Etsy nombra para el extranjero |
| --- | --- | --- |
| Estados Unidos | [USPS](carrier:usps), [FedEx](carrier:fedex), [UPS](carrier:ups) (solo dentro de EE. UU.), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| Reino Unido | Royal Mail, Evri | DHL, Evri, Royal Mail, UPS |
| Canadá | Canada Post, Chit Chats | Canada Post, Chit Chats, DHL, FedEx, UPS |
| España | Ninguna | [Correos de España](carrier:correos-spain) |
| China, Hong Kong | Ninguna | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), y SF Express desde China |

Si el vendedor ha usado una compañía de transporte compatible con Etsy, verás un botón **Seguir paquete** junto al pedido. Si no, el número solo aparece en **Ver el recibo**, y lo sigues en la web del transportista.

Desde el 1 de julio de 2026, Etsy no vende temporalmente etiquetas de USPS ni de Canada Post con destino a España, por una nueva tasa de gestión de la UE que afecta a algunos transportistas, según Etsy. Los vendedores de Estados Unidos usan entonces Global Postal Shipping o compran la etiqueta en otro sitio. Los paquetes desde China siguen su propio camino: mira [seguir un paquete desde China](guide:tracking-from-china).

## ¿Cómo es un número de seguimiento de Etsy?

Es el número del transportista, no uno de Etsy. Los correos usan el formato postal internacional: 13 caracteres que no cambian de un país a otro.

:::anatomy RR 00000000 5 US
- RR | Dos letras para el tipo de envío y el servicio
- 00000000 | Número de serie, ocho cifras
- 5 | Dígito de control, calculado a partir del número de serie
- US | País del correo que lo asignó: Estados Unidos
:::

Las formas que Peek conoce para los transportistas de arriba, con cifras inventadas:

| El número es así | Normalmente |
| --- | --- |
| Dos letras, nueve cifras y luego `ES`, `US` o `GB` | Correos, USPS o Royal Mail |
| 23 letras y cifras que empiezan por `P` | Correos |
| 22 cifras que empiezan por `91` a `95`, como `9400111111111111111114` | USPS |
| `1Z` + 16 letras y cifras | UPS |
| 12 o 15 cifras | FedEx |

¿No sabes cuál tienes? Escríbelo aquí:

:::checker
:::

Todos los demás formatos, transportista por transportista: [formatos de números de seguimiento](guide:tracking-number-formats).

## ¿Por qué se para o cambia el seguimiento de Etsy?

Casi siempre por uno de estos motivos:

- **Ha cruzado una frontera.** Etsy avisa de que «algunos pedidos internacionales no muestran el seguimiento una vez que el pedido sale del país de origen». Debería actualizarse cuando el paquete llegue a España.
- **Correos ha tomado el relevo.** Con un número postal, el correo del vendedor manda el paquete al extranjero y Correos lo entrega. La Unión Postal Universal solo admite «un único identificador S10» por envío, así que el mismo número suele funcionar también en la web de Correos.
- **Dos etiquetas, un solo viaje.** Con Global Postal Shipping de Etsy, un vendedor de Estados Unidos envía el paquete con una etiqueta de USPS a Asendia, que le pone la etiqueta internacional. Etsy junta las dos partes en un solo historial en Compras y reseñas, y en sus correos pone «Tracked on Etsy».
- **El vendedor añadió o corrigió el número tarde.** El vendedor puede editar el número durante 3 días después de completar el pedido; después, te manda el correcto por mensaje. También puede añadir un paquete más tarde, y su número aparece junto al primero.
- **Varios pedidos, una sola caja.** Si el vendedor junta varios pedidos tuyos en un paquete, todos llevan el mismo número.

:::journey
- shop | Artesano | Lo fabrica y lo empaqueta: hasta entonces, `No enviado`
- label | Etiqueta | `Notificado al transportista`: hay número, aún sin escaneo
- truck | Correo de origen | Primeros escaneos en el país del vendedor
- plane | Vuelo | Aquí el seguimiento suele callar
- customs | Aduana | Control, a veces con cargos
- handover | Correos | Lo entrega, casi siempre con el mismo número
- home | Tú | `Entregado`
:::

Cuánto puede durar un silencio: [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

### ¿Por qué no hay ningún número de seguimiento?

No todos los servicios lo tienen. Etsy lo dice claro: «No todos los métodos de envío incluyen información de seguimiento». Para cartas y sobres desde Estados Unidos, las etiquetas de Etsy añaden seguimiento a través de Pitney Bowes, pero un sobre demasiado rígido puede no mostrarlo. Etsy exige seguimiento a los vendedores de Estados Unidos, pero cualquier vendedor puede marcar un pedido como sin seguimiento indicando el motivo. ¿Sin número? Escribe al vendedor en **Ayuda con el pedido**: sabe qué servicio usó y cuándo lo envió.

## ¿Pagaré aduana por un pedido de Etsy del extranjero?

Etsy dice que, por lo general, quien compra paga los derechos, impuestos y gastos de despacho de aduana, y casi siempre al llegar el paquete. En España se aplican las normas de la UE, y Etsy cobra una parte ya al pagar:

| Pedido | Qué se aplica |
| --- | --- |
| Desde un país de la UE | El IVA va incluido en el precio, no pagas nada más |
| Desde fuera de la UE, menos de 150 EUR | Etsy cobra el IVA al pagar. Se suma un derecho de aduana de 3 EUR por artículo, al pagar o en la entrega |
| Desde fuera de la UE, más de 150 EUR | Puede que pagues el IVA y los derechos de aduana en la entrega, salvo que el vendedor los haya pagado por adelantado |

Si un paquete llega tarde o no llega porque rechazaste o no pagaste los cargos de la entrega, la protección de compras de Etsy no lo cubre. Más sobre cargos y plazos: [paquete retenido en aduana](guide:customs).

## Qué hacer si tu pedido de Etsy llega tarde o no llega

Sigue este orden:

:::steps
- Revisa las fechas | La fecha de entrega estimada está en el pedido. Etsy la recalcula si el vendedor envía antes.
- Síguelo con el transportista | Con Seguir paquete, o con el número de Ver el recibo en la web del transportista.
- Pregunta al vendedor | Compras y reseñas, luego Ayuda con el pedido. El vendedor puede revisar el estado, cambiar la dirección o reembolsarte.
- Abre un caso | 48 horas después de tu solicitud, una vez pasada la fecha estimada. Si el pedido nunca llegó: 7 días después de la fecha estimada máxima.
- Ojo al plazo | Tienes hasta 30 días después de la fecha de entrega estimada.
:::

Con la protección de compras de Etsy, un pedido que cumpla los requisitos se reembolsa entero si el artículo no llega, llega dañado, no coincide con la descripción o llega 7 días o más después de la fecha de entrega estimada máxima indicada al comprar. Tu solicitud en **Ayuda con el pedido** muestra desde cuándo puedes elegir **Abrir un caso** y hasta cuándo. Etsy solo puede tramitar reembolsos en los 180 días siguientes a la compra.

Hay casos que no cubre: retrasos por una huelga del transportista, fenómenos meteorológicos graves, guerras o disturbios, cargos rechazados en la puerta y pedidos que ya reclamaste a tu banco. ¿Compraste como invitado? Vincula primero el pedido a una cuenta de Etsy. Si pagaste con PayPal fuera de Etsy Payments, abre una disputa en PayPal en los 180 días siguientes a la compra.

### Dice «Entregado», pero no lo tienes

Etsy aconseja primero estas comprobaciones: pregunta a quien vive o trabaja contigo, espera un día si no es urgente, llama a tu oficina de correos o centro de envío, y pregunta a los vecinos. ¿Nada? Manda al vendedor una solicitud en **Ayuda con el pedido**: normalmente es él quien abre la reclamación con el transportista por ti. Después, abre un caso si no aparece. Más en [entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

## ¿Puedo seguir un pedido de Etsy fuera de Etsy?

Sí, con el número del transportista. Pégalo en [Peek](/): reconoce los números de los correos y transportistas de arriba. En un envío postal al extranjero, cuando el correo de origen indica el país de destino pero no nombra a ningún socio, Peek puede proponer una consulta con el mismo número en Correos. Conserva el historial del correo de origen y sigue a Correos en cuanto su seguimiento muestre avances nuevos. Los trámites de Etsy siguen en Etsy: Peek no ve un pedido `No enviado` ni puede abrir un caso por ti.

:::sources
- [Etsy: ¿Cuál es el estado de mi pedido?](https://help.etsy.com/hc/es/articles/115015521948--Cu%C3%A1l-es-el-estado-de-mi-pedido) – estados, Seguir paquete, Ver el recibo, seguimiento en el extranjero, sin seguimiento, consejos si no llega
- [Etsy: ¿Qué es una fecha de entrega estimada?](https://help.etsy.com/hc/es/articles/360020601674--Qu%C3%A9-es-una-fecha-de-entrega-estimada) – preparación más tránsito, ejemplo, 7 días si no llega
- [Etsy: Cómo establecer los tiempos de preparación, los perfiles de preparación y las fechas de envío](https://help.etsy.com/hc/es/articles/115015588087-C%C3%B3mo-establecer-los-tiempos-de-preparaci%C3%B3n-los-perfiles-de-preparaci%C3%B3n-y-las-fechas-de-env%C3%ADo) – tiempo de preparación, fecha de envío en el recibo
- [Etsy: Cómo añadir información de seguimiento y completar un pedido](https://help.etsy.com/hc/es/articles/115015774228-C%C3%B3mo-a%C3%B1adir-informaci%C3%B3n-de-seguimiento-y-completar-un-pedido) – fecha de envío hasta 3 días después, aviso al primer escaneo, seguimiento obligatorio en EE. UU., 3 días para editar, paquetes añadidos, pedidos combinados
- [Etsy: Cómo comprar etiquetas de envío en Etsy](https://help.etsy.com/hc/es/articles/360001967188-C%C3%B3mo-comprar-etiquetas-de-env%C3%ADo-en-Etsy) – transportistas, vendedores de Canadá, Reino Unido y EE. UU., destinos de la UE suspendidos
- [Etsy: FedEx Shipping Labels on Etsy (en inglés)](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – vendedores de EE. UU.
- [Etsy: UPS Shipping Labels on Etsy (en inglés)](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – vendedores de EE. UU., solo envíos nacionales
- [Etsy: Royal Mail Postage Labels on Etsy (en inglés)](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – vendedores de Reino Unido
- [Etsy: Evri Postage Labels on Etsy (en inglés)](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – vendedores de Reino Unido
- [Etsy: Chit Chats Shipping Labels on Etsy (en inglés)](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – vendedores de Canadá
- [Etsy: Global Postal Shipping Labels on Etsy (en inglés)](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, dos etiquetas, Tracked on Etsy
- [Etsy: How to Ship First-Class Flats and Letters (en inglés)](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – seguimiento con Pitney Bowes, sobres rígidos
- [Etsy: Información para la gestión de envíos internacionales](https://help.etsy.com/hc/es/articles/360001987487-Informaci%C3%B3n-para-la-gesti%C3%B3n-de-env%C3%ADos-internacionales) – transportistas habituales por país
- [Etsy: ¿Tendré que pagar impuestos, aranceles o tarifas de aduana por mi pedido?](https://help.etsy.com/hc/es/articles/115015691007--Tendr%C3%A9-que-pagar-impuestos-aranceles-o-tarifas-de-aduana-por-mi-pedido) – normas de la UE, 150 EUR, 3 EUR por artículo
- [Etsy: Programa de protección de compras de Etsy](https://help.etsy.com/hc/es/articles/7471925990807-Programa-de-protecci%C3%B3n-de-compras-de-Etsy) – qué cubre, cargos de aduana, huelgas y meteorología, invitados
- [Etsy: Cómo obtener ayuda con un pedido](https://help.etsy.com/hc/es/articles/4402660818583-C%C3%B3mo-obtener-ayuda-con-un-pedido) – Ayuda con el pedido, qué puede hacer el vendedor
- [Etsy: Cómo abrir un caso](https://help.etsy.com/hc/es/articles/5745586898199-C%C3%B3mo-abrir-un-caso) – 48 horas, 30 días, 180 días, PayPal, devoluciones de cargo
- [Etsy: What if My Buyer's Package Gets Lost? (en inglés)](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – reclamación del vendedor
- [UPU: norma S10 (en inglés)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – el formato de 13 caracteres, un número por envío
- [Universal Parcel Scraper: datos de transportistas (en inglés)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formas de los números, qué comprueba Peek
:::
