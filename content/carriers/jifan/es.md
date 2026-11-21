---
title: Seguimiento Jifan: números JFGJ, estados y hora de Pekín
description: Rastrea un paquete de Jifan (吉凡国际物流) de China continental a EE. UU.: cómo es el código JFGJ…CN, qué significan sus estados y por qué usa hora de Pekín.
slug: seguimiento-jifan
published: 2026-11-21
updated: 2026-11-21
---

Jifan es Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`, o `吉凡国际物流`), un operador logístico fundado en 2017 en Shenzhen (China continental) y miembro NVOCC. Envía paquetes de comercios desde China continental a compradores en Estados Unidos por vía aérea y marítima. Sus códigos de seguimiento llevan `JFGJ`, nueve dígitos y `CN`. Consulta el tuyo en [el rastreador de Jifan](https://www.szjf56.cn/index/tracknumber/track) o pégalo en el recuadro superior; en el catálogo de Peek también aparece buscando `Jifan International Logistics`, `吉凡国际物流` o `深圳市吉凡国际物流有限公司`.

## Número de seguimiento de Jifan: cómo es

La web de Jifan no detalla el formato de sus guías, pero todas responden a un único patrón. `JFGJ` corresponde a las iniciales en pinyin de la empresa: `吉凡国际` (*Jifan Guoji*, «Jifan Internacional»). Todos los ejemplos siguientes son inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `JFGJ482910573CN` | `JFGJ`, nueve cifras y `CN`: un código de Jifan (sin dígito de control en la novena cifra). |
| `JFGJ48291057CN` | Solo ocho cifras: no encaja con el formato de Jifan y Peek no asigna Jifan. Revisa si falta un dígito. |
| `RR482910576CN` | Dos letras, nueve cifras y `CN`: un número de [China Post](carrier:china-post), no de Jifan, aunque termine en `CN`. |

En `peektracker.com`, un código `JFGJ…CN` al que le falta un carácter recibe este aviso: «Empieza como un número de seguimiento de Jifan, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» En Peek no importan las minúsculas, los espacios ni los guiones. En el buscador de Jifan, escríbelo en mayúsculas y sin espacios.

**Dónde encontrarlo.** En el correo de envío del vendedor o en la página de tu pedido. Si no aparece, consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number).

**En la web de Jifan.** La portada (`szjf56.cn`, apartado `单号查询`) admite hasta 10 números separados con Intro, y la página específica `快件跟踪` cuenta con un botón `跟踪(Track)` que acepta hasta 20 códigos a la vez, pero únicamente conserva paquetes de los dos últimos meses (`2个月内的包裹`).

## Estados de seguimiento de Jifan explicados

El rastreador de Jifan está en chino, aunque las líneas del historial vienen en inglés, de la más reciente a la más antigua, cada una con su fecha y hora. Encima figura una fila resumen con destino (`目的地`), estado (`状态`), última hora (`最新时间`) y último registro (`最新记录`). Dos estados habituales en esa cabecera:

| El rastreador dice | Qué significa para ti |
| --- | --- |
| `运输途中` | «En tránsito» en chino. Mira las líneas inferiores en inglés para saber por dónde va exactamente el envío. |
| `查询不到` | «No se encuentra» en chino. El rastreador no tiene ningún paquete con ese código y no muestra eventos. |

Primero aparecen los pasos propios de Jifan en China continental —sin localidad— desde los datos del pedido hasta la salida del vuelo, y después las lecturas en Estados Unidos del agente de destino y de la red de reparto final (cuyo nombre el rastreador sustituye por `logistics carrier`). El sistema de seguimiento de Jifan es el de CTTYEX y redacta sus líneas igual:

| Jifan indica | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `Order information received` | El vendedor envió los datos del pedido; Jifan aún no tiene el paquete. | «Etiqueta creada» |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Jifan recibió el paquete en su punto de admisión transfronteriza en China continental. | «Admitido por el transportista» |
| `Goods leave the operation center` | El envío salió del centro operativo de Jifan en China continental. | «En tránsito» |
| `Arrival at domestic internationality airport` | En el aeropuerto de salida en China continental (`internationality` es la grafía literal del rastreador). | «En tránsito» |
| `Departure from the airport of origin domestic` | El avión ha despegado de China continental. | «En tránsito» |
| `Arrive at the destination international airport` | Llegada al aeropuerto internacional de destino en Estados Unidos. | «En tránsito» |
| `Import customs clearance` | Despacho aduanero de importación en Estados Unidos (precedido de la ciudad del puerto de entrada). | «En aduanas» |
| `Leave the … processing center` | Salida del centro de procesamiento estadounidense nombrado en lugar de los puntos suspensivos. | «En tránsito» |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Paso por una instalación del transportista de reparto rumbo a la siguiente. | «En tránsito» |
| `Accepted at logistics carrier Regional Destination Facility` | Admisión en el centro regional de destino del operador estadounidense; sigue «En tránsito» porque Jifan ya admitió el envío en China continental. | «En tránsito» |

Una línea final que confirma la entrega aparece en Peek como «Entregado». En Estados Unidos, varias líneas empiezan con ciudad, estado y código ZIP.

**Todas las horas son hora de Pekín.** El rastreador de Jifan imprime las horas sin zona horaria y todas están en hora de Pekín (`UTC+8`), también en los escaneos dentro de Estados Unidos. Pekín cambia de fecha a las `16:00 UTC`: por eso una lectura hecha en Nueva York después de las 11:00 en invierno (12:00 en verano), o en Los Ángeles después de las 08:00 en invierno (09:00 en verano), aparece ya con fecha del día siguiente.

## Quién entrega los paquetes de Jifan

La web de Jifan describe cuatro líneas comerciales, todas desde China continental hacia **Estados Unidos**:

- **Paquete pequeño económico a EE. UU.** (`美国经济小包-轻小件`): artículos ligeros y planos de menos de 360 g y menos de 1,8 cm de grosor, en vuelos de carga directos, entregados por [UPS](carrier:ups) en todo Estados Unidos (incluidas islas ultramarinas y direcciones militares), con seguimiento completo. Plazo orientativo: unos 10 a 12 días naturales (`自然日`).
- **Paquete pequeño aéreo a EE. UU.** (`美国空运小包标快`): de 0 a 4,5 kg, vuelos cargueros directos, reparto con UPS (`UPS MI` según escribe Jifan) en todo EE. UU., islas y direcciones militares incluidas. Unos 8 a 10 días naturales.
- **Paquetes marítimos Matson** (`美森正班（限时达）海运大/小包`): hasta 4,5 kg se entregan igual que la línea aérea (`UPS MI`, todo EE. UU.); de 4,5 a 68 kg viajan con `UPS GROUND` solo dentro del territorio continental de EE. UU. (`仅限美国本土`). Unos 20 a 25 días naturales.
- **Carga marítima extragrande a EE. UU.** (`美国海运超大件专线`): de 100 a 2.000 kg por bulto en buques Matson y COSCO y luego en camión puerta a puerta (direcciones comerciales y particulares), exclusivamente en EE. UU. continental sin islas ni direcciones militares. Unos 25 a 40 días naturales.

En su línea económica y en los paquetes pequeños de hasta 4,5 kg por aire o mar, Jifan indica que una sola guía cubre todo el trayecto (`一单到底`). Su buscador no nombra a la empresa de reparto ni muestra un segundo código; si la tienda te facilitó además un número de UPS, puedes seguirlo en [UPS](carrier:ups). Jifan no publica normas para destinatarios sobre intentos de entrega o puntos de recogida ([entrega fallida](guide:missed-delivery)) ni sobre aranceles o impuestos en el país de destino ([paquete retenido en aduanas](guide:customs)).

## Paquete de Jifan sin cambios, perdido o devuelto

Los clientes de Jifan son las empresas remitentes en China continental, no quien compra: ante retrasos, pérdidas, daños o devoluciones, acude primero al vendedor o a la plataforma donde hiciste el pedido.

**`查询不到` (no se encuentra).** Comprueba que no haya erratas, escríbelo en mayúsculas y pregunta al vendedor si sigue fallando. Peek muestra «Esperando a Jifan» y continúa consultando. Si pasados dos días Jifan sigue sin reconocerlo y ningún otro servicio ha respondido, Peek avisa: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» A partir de ahí Peek lo comprueba a diario, y una vez por semana cuando se cumple una semana desde que lo añadiste.

**Solo `Order information received`.** Jifan recibió los datos del envío, todavía no el paquete físico. Si pasan varios días sin novedades, consulta al vendedor si llegó a entregarlo.

**Parón tras el aeropuerto o en barco.** Tras `Arrival at domestic internationality airport` o `Departure from the airport of origin domestic`, puede no haber nuevas líneas hasta el aterrizaje o la aduana en EE. UU., y los envíos marítimos tardan semanas. Cuando un paquete en ruta lleva cuatro días sin escaneos nuevos, Peek lo marca («Sin novedades desde hace cuatro días») e indica: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Jifan.» (sin enlace de soporte propio, ya que Jifan no ofrece página de ayuda al destinatario). Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Cuidado con los SMS falsos.** Los dominios oficiales de Jifan son `www.szjf56.cn` y `szjf56.cn`, y su rastreador no muestra ningún importe de aduanas ni tasas que pagar. Lee [mensajes falsos de entrega](guide:delivery-scam-texts) antes de abonar nada que te pidan por SMS o correo.

**Perdido, dañado, devuelto o marcado como entregado sin estar.** En su portada, Jifan promete a sus clientes remitentes compensación por demora y, en caso de pérdida, devolver el porte y abonar otro tanto igual (`延误即赔,丢件按运费退一赔一`): ese acuerdo es entre Jifan y la tienda, a la que debes pedir el reembolso o un nuevo envío. Mira [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [devuelto al remitente](guide:returned-to-sender) y [entregado pero no recibido](guide:delivered-not-received).

## Contacto de Jifan

En el apartado `联系我们` de la página principal de Jifan (`www.szjf56.cn`):

- **Teléfono de consultas**: `400-186-0001` (`咨询电话`), una línea de servicio de China continental. Jifan no publica horario de atención ni indica si atiende llamadas desde el extranjero o en inglés.
- **Correo electrónico**: `925930140@qq.com` (`联系邮箱`, cuenta de QQ Mail, contacto `冯生`), sin especificar quién lo atiende ni en qué idioma.
- **Oficina**: `深圳市龙岗区南湾街道2990号` (n.º 2990, subdistrito de Nanwan, distrito de Longgang, Shenzhen, China continental).

La web está íntegramente en chino y carece de formulario o chat para destinatarios: sobre tu compra, habla primero con el vendedor.

## Seguir un paquete de Jifan con Peek

[Peek](/) identifica a Jifan al instante con cualquier número `JFGJ…CN` («Transportista detectado»): ningún otro transportista del catálogo usa ese formato. También sirve un enlace a `szjf56.cn/index/tracknumber/track` con un código `JFGJ…CN` tras `nos=` (si incluye varios, Peek toma el primero).

Si el vendedor te dio otro código distinto, pégalo igualmente: Peek sigue a más de 3.500 transportistas, consulta servicios de seguimiento cuando el formato no basta y permite elegir operador con «Cambiar» o «Cambiar transportista». Peek solo consulta el buscador de Jifan para códigos `JFGJ…CN`; si eliges Jifan en otro número, indica que «no emite números como este».

Peek lee directamente del rastreador de Jifan el estado, cada línea en el inglés original y el país de destino. Cuando una línea en Estados Unidos comienza por ciudad, estado y código ZIP, Peek convierte la ciudad y el estado en el lugar del escaneo y descarta el ZIP. Cada hora se interpreta como hora de Pekín (`UTC+8`) y se convierte al huso de tu dispositivo; en `peektracker.com` y en las apps de iPhone y Android, el historial cierra con «Horas en tu zona horaria: …». El botón «Abrir la web de Jifan» abre tu código en `szjf56.cn`.

El estado de Peek sigue el escaneo más reciente que reconoce y solo recurre al resumen (`运输途中`) si ninguna línea fija etapa. Como el rastreador de Jifan no facilita fecha prevista, peso, nombre de tarifa, empresa de reparto ni segundo número, Peek mantiene el seguimiento del código `JFGJ…CN` en Jifan hasta la entrega.

Mientras responda el rastreador de Jifan, Peek comprueba cambios hasta cada 10 minutos con el paquete en pantalla o con alertas activas si la entrega se espera hoy. Desde las 6:00 del reloj del transportista (hora de Pekín) el día en que Jifan anuncia el reparto, las comprobaciones pueden bajar a cada 2 minutos con el paquete abierto o alertas activas. Fuera de pantalla y sin alertas, así como tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora; cuando solo responde un servicio de seguimiento, revisa cada hora, cada 3 horas si el último escaneo tiene más de un día y hasta cada 15 minutos el día de reparto, solo con el paquete abierto o con alertas activas. Avisos configurables: «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Jifan ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de Jifan

### ¿Qué es Jifan Logistics?

Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`), empresa logística fundada en 2017 en Shenzhen (China continental) que transporta paquetes de comercios desde China continental a compradores en Estados Unidos por aire y por mar con códigos `JFGJ…CN`.

### ¿Dónde puedo rastrear un número JFGJ…CN?

En la página [快件跟踪 de szjf56.cn](https://www.szjf56.cn/index/tracknumber/track) escribiéndolo en mayúsculas, o en Peek. El buscador oficial solo muestra envíos de los últimos dos meses.

### ¿Por qué parecen adelantadas las horas del rastreador de Jifan?

Porque todas están en hora de Pekín (`UTC+8`), incluidos los escaneos hechos en Estados Unidos. En invierno Pekín lleva 13 horas de adelanto sobre Nueva York y 16 sobre Los Ángeles, de modo que un escaneo posterior a las 11:00 en Nueva York o a las 08:00 en Los Ángeles figura con la fecha del día siguiente. Peek las muestra en la zona horaria de tu dispositivo.

### ¿Qué significa `查询不到` en el rastreador de Jifan?

Significa «no se encuentra» en chino: no consta ningún paquete con ese código. Revisa posibles erratas, escríbelo en mayúsculas sin espacios y consulta al vendedor si continúa igual; los paquetes de hace más de dos meses ya no aparecen en el buscador.

### ¿Cuánto tarda Jifan en entregar?

Su web señala unos 8 a 10 días naturales en su línea aérea pequeña a EE. UU., 10 a 12 en la económica ligera, 20 a 25 en paquetes marítimos Matson y 25 a 40 en carga marítima extragrande. Guíate ante todo por el plazo estimado que te dio la tienda.

:::sources
- [Jifan: 深圳市吉凡国际物流有限公司 (portada)](https://www.szjf56.cn/) – razón social, fundación en 2017, cuatro líneas a EE. UU., pesos y grosores, entrega con UPS y UPS Ground, buques Matson y COSCO, días naturales, guía única, compensación a remitentes, teléfono, correo y dirección en Shenzhen
- [Jifan: 快件跟踪 (seguimiento)](https://www.szjf56.cn/index/tracknumber/track) – rastreador en chino, botón 跟踪(Track), columnas de resultados, límite de 20 códigos y ventana de dos meses
- [Universal Parcel Scraper: Jifan](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jifan) – patrón JFGJ…CN e iniciales, enlace nos=, sistema CTTYEX, estados en chino e inglés, hora de Pekín y extracción de ciudad y estado sin código ZIP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de China Post y reglas compartidas de clasificación de estados
:::
