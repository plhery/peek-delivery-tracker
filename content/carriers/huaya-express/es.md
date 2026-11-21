---
title: Seguimiento Huaya Express: códigos HYYT…GZ y estados
description: Huaya Express (华亚运通) envía compras online desde Cantón. Cómo es el código HYYT…GZ, qué dicen sus escaneos y a quién pedir el número local tras el traspaso.
slug: seguimiento-huaya-express
published: 2026-11-21
updated: 2026-11-21
---

Huaya Express (`华亚运通`) es la marca del logotipo de `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), un transitario de Cantón (Guangzhou), en China continental, que envía paquetes al extranjero para tiendas online. Una guía de Huaya empieza por `HYYT` y termina en `GZ`. Pégala en el cuadro superior o sigue el enlace `物流跟踪` (seguimiento logístico) del menú superior de `hyytgz.com` hacia el rastreador propio de Huaya. El historial de Huaya se corta cuando entrega el envío a un operador de tu país, o tras algunos escaneos de ese operador (en EE. UU., a veces hasta la entrega), sin decir nunca qué empresa es — salvo en una ruta hacia Alemania con [DHL](carrier:dhl), donde copia los escaneos de DHL, en un aviso sueco sobre Early Bird y en una ruta hacia México, donde copia los escaneos de J&T Express.

## Número de seguimiento de Huaya Express: cómo es

Huaya no publica el formato de sus códigos. El esquema que reconoce Peek lleva `HYYT`, doce cifras y `GZ` (ejemplos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `HYYT482910573617GZ` | `HYYT`, doce dígitos y `GZ`: la estructura de una guía de Huaya Express. |
| `HYYT48291057317GZ` | Solo once dígitos: se perdió una cifra al copiarlo. Vuelve a copiarlo del mensaje del vendedor. |
| `RR482910576CN` | Código postal terminado en `CN`: lo rastrea [China Post](carrier:china-post), no Huaya. |

Peek lee el código con o sin espacios y tanto en mayúsculas como en minúsculas.

**De dónde sale el número.** Huaya trabaja para comercios: su portada menciona tiendas Shopify, Vova, DHgate (`敦煌`) y vendedores de Facebook. El código te lo facilita la tienda donde compraste; si aún no lo ves, revisa [cómo encontrar el número de seguimiento](guide:find-tracking-number). El buscador de Huaya también admite la referencia de pedido del comercio (`参考号`), escrita tal cual con sus guiones.

**Un enlace de rastreo con dirección numérica.** El rastreador propio de Huaya no cuelga de `hyytgz.com`, sino de una dirección numérica por HTTP sin cifrar (`trackIndex.htm` con `documentCode=` y tu código): puedes pegar ese enlace directamente en Peek. En cambio, el enlace `物流跟踪` del pie de página abre el servicio externo 17TRACK.

## Estados de seguimiento de Huaya Express explicados

El buscador de Huaya tiene versión en chino y en inglés. Las líneas que conoce Peek están sobre todo en inglés, salvo la entrada en almacén y algunos pasos de aeropuerto, vuelo, aduana y entrega al operador en chino, más una frase en sueco en Suecia. En el orden habitual del envío:

| Huaya indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipping Label Created` | Ya existe la etiqueta del operador que repartirá en tu país: solo datos del pedido; Huaya aún no tiene el paquete. | «Etiqueta creada» |
| `Shipment not yet received or processed` | Línea del operador de destino transmitida antes de que el paquete llegue al punto de recogida de Huaya: tampoco está aún en manos de Huaya. | «Etiqueta creada» |
| `Package received & scanned at warehouse`, `到达收货点` | Huaya recibió el paquete en su almacén de Cantón (China continental): primer escaneo en su poder (`到达收货点` significa llegó al punto de recepción). | «Admitido por el transportista» |
| `Package departed from the warehouse` | Ha salido de ese almacén en Cantón. | «En tránsito» |
| `货物发往机场途中`, `货物到达机场` | De camino al aeropuerto y llegada al aeropuerto en China continental, antes del despacho de exportación. | «En tránsito» |
| `Customs export clearance completed`, `国内清关完成` | Despacho de exportación completado en China continental; la aduana de importación de tu país viene tras aterrizar. | «En tránsito» |
| `安排航班中`, `Package handed over to airline` | Reservando vuelo y entrega del envío a la aerolínea. | «En tránsito» |
| `Shipment Announced` | Huaya notificó el envío al transportista de tu país, que todavía no lo ha recibido. | «En tránsito» |
| `Package in transit to destination country`, `航班起飞，预计航班落地时间(…)` | Viajando hacia el país de destino (la línea en chino incluye entre paréntesis la fecha prevista de aterrizaje). | «En tránsito» |
| `Package arrived at destination country` | Ha llegado al país de destino. | «En tránsito» |
| `航班落地，清关中` | Vuelo aterrizado, despacho de aduana de importación en curso. | «En aduanas» |
| `Customs import clearance completed, awaiting pickup by local carrier`, `清关完成` | La aduana de tu país ya lo liberó; falta que el transportista local pase a recogerlo. | «En tránsito» |
| `It has been handed over to the service provider.`, `已交仓`, `Preparing for delivery` | Entregado al operador local (o a su almacén) o preparándose para salir a reparto. Huaya no indica ni qué empresa es ni su código; detrás solo aparecen en rutas de EE. UU. escaneos de un operador estadounidense (`Arrived at Origin Hub` junto a Chicago O'Hare, `Departed Origin Hub`, `Arrived at Destination Hub`, `Departed Destination Hub`, `Arrived At Warehouse`, luego `Out for Delivery` como «En reparto» y `Delivered` como «Entregado») o, hacia Alemania, escaneos copiados de [DHL](carrier:dhl), o, hacia México, escaneos copiados de J&T Express. Peek conoce además otras líneas de reparto en inglés cuyo país no indica ninguna fuente (`Arrived at the regional hub`, `Departed from the regional hub`, `Arrived at the sorting center`, `Departed from the sorting center`, `Arrived in your region`, `Out for delivery with the courier` como «En reparto»). | «En tránsito» |
| `Din leverans ?r bokad med Early Bird.` | Sueco para «Tu entrega está reservada con Early Bird» (escribe la `ä` de `är` como `?`): en Suecia reparte Early Bird. | «En tránsito» |

Las horas de Huaya vienen sin huso horario: van en hora de Pekín (`UTC+8`), salvo los escaneos copiados de DHL y los del operador estadounidense en una localidad de EE. UU., que mantienen la hora local donde se escaneó el paquete, y los de J&T Express copiados hacia México, en hora de Ciudad de México. Si el buscador no conoce un código, la columna `参考号` (referencia) repite el número y deja en blanco el resto de la fila.

## Quién entrega los paquetes de Huaya Express

Fuera de los escaneos copiados de [DHL](carrier:dhl) en Alemania, de Early Bird en la línea sueca y de los escaneos copiados de J&T Express en una ruta hacia México, el registro de Huaya no nombra a la empresa de reparto ni muestra un segundo número. Su propia web tampoco lo aclara, porque sus páginas para vendedores no concuerdan entre sí: la de la línea de Reino Unido (`ywfw.php?id=21`) termina hablando de EE. UU. y USPS, y la de Australia (`ywfw.php?id=27`) envía el paquete a EE. UU. para USPS. Reparte el operador contratado según la línea elegida por el vendedor: pídele el nombre de esa empresa y su número de seguimiento. Sobre aranceles e IVA de importación, consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de Huaya Express parado o que no llega

**Última línea `It has been handed over to the service provider.`.** Es habitual que el rastreador de Huaya no añada nada más: el paquete viaja ya con el código propio del transportista local. Pide al vendedor el nombre del operador y ese segundo código para seguirlo allí ([seguimiento de paquetes desde China](guide:tracking-from-china)).

**Sin cambios tras completarse la aduana de importación.** La aduana ya terminó y el paquete espera a que el operador local lo retire. Si pasan varios días sin traspaso al operador, avisa al vendedor ([por qué no se actualiza el seguimiento](guide:tracking-not-updating)).

**Sin resultados.** Como Huaya devuelve una fila vacía con la referencia cuando no reconoce un código, Peek lo interpreta como envío registrado aún sin primer escaneo y muestra «Esperando a Huaya Express» sin pedirte revisar el número (en consultas sin cuenta sigue comprobando las primeras 6 horas, luego cuando abras su enlace y, hasta 30 días después, mientras tengas activadas las notificaciones). Comprueba tú mismo el código frente al mensaje de la tienda (`HYYT`, doce cifras, `GZ`) y confirma con el vendedor que realmente envió con Huaya.

**Retrasado, perdido o devuelto.** Las condiciones de reclamación de Huaya están redactadas para las tiendas remitentes, no para compradores. Pide al vendedor un reenvío o reembolso y, si deja de responder, abre una disputa en la plataforma donde compraste ([paquete perdido](guide:lost-parcel), [devuelto al remitente](guide:returned-to-sender), [entregado pero no recibido](guide:delivered-not-received)).

## Teléfono y atención al cliente de Huaya Express

Datos de empresa publicados únicamente en chino en `hyytgz.com`:

- **Teléfono**: `18039073131`, rotulado como línea de atención de la empresa (`公司服务热线`). Es un móvil de China continental (añade `+86` si llamas desde fuera); no indica horario ni idiomas.
- **Correo electrónico**: `2065332407@qq.com`, cuenta de QQ Mail indicada como correo corporativo (`公司邮箱`).
- **Formulario**: la página `投诉建议` (`message.php`, quejas y sugerencias) solicita nombre, móvil, correo electrónico y mensaje.
- **Ubicación**: distrito de Baiyun, Cantón (`广州市白云区`), China continental.

Las secciones `关于我们` (`about.php`) y `服务网点` (`outlets.php`) solo muestran el título sin contenido, y no hay páginas pensadas para destinatarios.

**Cuidado con los mensajes falsos.** La web corporativa de Huaya Express es `hyytgz.com`. Antes de abrir enlaces recibidos por SMS o correo, lee [mensajes falsos de entrega](guide:delivery-scam-texts). Para cualquier gestión de tu compra, habla primero con la tienda.

## Seguir un paquete de Huaya Express con Peek

Al pegar un código `HYYT…GZ` o un enlace del rastreador con `documentCode=`, [Peek](/) lo archiva al instante en Huaya Express («Transportista detectado»), ya que ningún otro operador del catálogo usa esa forma. Para buscar por la referencia de pedido de la tienda entre más de 3.500 transportistas, introdúcela y elige Huaya Express en «Cambiar» (`华亚运通` o `GZHY` también lo encuentran; o «Cambiar transportista» si ya lo añadiste): Peek mostrará además el código con el que Huaya rastrea el pedido, aunque elimina los guiones, así que las referencias con guiones deben consultarse en el buscador de Huaya.

**Escaneos, huso horario y etapas escritas por adelantado.** Peek consulta el rastreador propio de Huaya, muestra cada paso con su localidad y país de destino, descarta datos personales (nombres, direcciones, códigos postales, firmas, fotos, peso) y no muestra fecha estimada de entrega (Huaya no la da). Convierte las horas desde Pekín (`UTC+8`) — o desde la hora local del escaneo en las líneas copiadas de DHL y las de localidades de EE. UU., y desde la hora de Ciudad de México en las de J&T Express — a la zona de tu dispositivo («Horas en tu zona horaria: …»). Si una línea trae una hora que aún no ha llegado en Pekín, la deja fuera de los escaneos hasta entonces. También retiene las filas grabadas más de 12 horas antes de la hora que marcan, o más de 6 horas antes en el mismo segundo que una de ellas (previsiones, no escaneos). Entonces, hasta que el paquete se entrega o se devuelve, el historial empieza con «Otras webs pueden mostrar más pasos, pero solo son previsiones.», y «Verlos» las lista en gris, cada una con «Previsto para» y su día. Estas filas nunca cuentan como un escaneo, ni alteran el estado ni envían alertas.

**Sin traspaso automático y alertas.** Como Huaya no facilita operador de entrega ni segundo código que Peek pueda seguir, Peek permanece en el rastreador de Huaya; añade el código local que te dé el vendedor como segundo paquete. Peek comprueba hasta cada 10 minutos con el paquete abierto o el día de entrega con notificaciones activas, y hasta cada 2 minutos desde las 6:00 locales el día en «En reparto» (el resto del tiempo, y tras 12 h en «Etiqueta creada» / «Admitido por el transportista» o dos días sin cambios, cada hora). De las líneas propias de Huaya, solo `航班落地，清关中` cuenta como «En aduanas» en «Pasos importantes», mientras que «Solo la entrega» únicamente avisa con los repartos copiados de DHL, de J&T Express o del socio en EE. UU.: usa mejor «Cada escaneo» o «Pasos importantes». Peek no puede contactar con Huaya ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de Huaya Express

### ¿Qué es Huaya Express (`华亚运通`)?

Es la marca de `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), un transitario de Cantón (China continental) para comercios online que emplea sobre todo [China Post](carrier:china-post), líneas propias con despacho e impuestos incluidos hacia EE. UU. y Europa, líneas para mercancía sensible y las cuatro grandes empresas exprés. Sus guías siguen el patrón `HYYT`, doce cifras y `GZ`.

### ¿Por qué mi seguimiento se detuvo en `It has been handed over to the service provider.`?

Porque ahí acaba el tramo de Huaya: una empresa de reparto de tu país lleva ahora el paquete con un código propio que la línea de Huaya no menciona. El vendedor puede darte el nombre del transportista y ese número.

### ¿Qué significan las líneas en gris en Peek?

Son etapas cargadas en el sistema más de 12 horas antes de la hora que muestran (o más de 6 horas antes en el mismo segundo): previsiones escritas de antemano y no escaneos confirmados, incluso si mencionan la entrega.

### ¿Con quién contacto si no llega un paquete de Huaya Express?

Con la tienda donde hiciste el pedido: es el cliente de Huaya y sus condiciones de indemnización se dirigen a remitentes comerciales. Huaya solo ofrece atención en chino.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (inicio)](http://hyytgz.com/) – razón social y logotipo, plataformas para las que envía, servicios principales, enlaces 物流跟踪 (rastreador propio y 17TRACK), teléfono 18039073131, correo 2065332407@qq.com y dirección en Baiyun (Cantón)
- [Huaya Express: 欧洲双清包税小包 (líneas Europa y Reino Unido)](http://hyytgz.com/ywfw.php?id=21) – líneas de Reino Unido con texto estadounidense que acaba en USPS y condiciones para remitentes
- [Huaya Express: 澳大利亚专线小包 (línea Australia)](http://hyytgz.com/ywfw.php?id=27) – línea de Australia con vuelo a EE. UU. para USPS y condiciones para remitentes
- [Huaya Express: 投诉建议 (quejas y sugerencias)](http://hyytgz.com/message.php) – campos del formulario (nombre, móvil, correo, mensaje)
- [Huaya Express: 关于我们 (quiénes somos)](http://hyytgz.com/about.php) – página sin contenido
- [Huaya Express: 服务网点 (oficinas)](http://hyytgz.com/outlets.php) – página sin contenido
- [Universal Parcel Scraper: Huaya Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/huaya-express) – patrón HYYT…GZ, alias, enlace trackIndex.htm, estados en inglés/chino/sueco, escaneos de DHL y EE. UU., relojes y filas escritas por adelantado
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección en catálogo, búsqueda por referencia y qué muestra Peek
:::
