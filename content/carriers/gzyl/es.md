---
title: Rastreo y seguimiento GZYL: número YL…YQ y estados
description: Rastrea tu paquete de GZYL (广州云链) desde China: cómo es un número YL…YQ, qué significa cada estado y por qué puede salir entregado sin haber llegado.
slug: gzyl-seguimiento
published: 2026-11-12
updated: 2026-11-12
---

GZYL (广州云链, Guangzhou Yunlian) es 广州云链货运代理有限公司, un transitario (agente de carga) de Guangzhou, en China, que envía paquetes al extranjero. Si una tienda china te ha enviado el pedido con GZYL, tu número de seguimiento empieza por YL, lleva diez cifras y acaba en YQ: pégalo en la casilla de arriba para rastrear el paquete. Peek no conoce ningún sitio web de GZYL, y su página de seguimiento está en una simple dirección IP, sin nombre de dominio. Lee ese seguimiento con cautela: GZYL escribe de antemano buena parte del recorrido de un paquete, así que un paso, incluso `Delivered`, no siempre es un escaneo.

## Número de seguimiento de GZYL: cómo es

Los números de envío de GZYL llevan YL, diez cifras y YQ: 14 caracteres en total. Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `YL0000000000YQ` | YL, diez cifras y YQ: el formato de los números de GZYL. Peek lo comprueba en la página de seguimiento de GZYL. |
| `YL000000000YQ` | Solo nueve cifras: no es el formato de GZYL. Mira si falta una cifra. |
| `GZXH0000000000YQ` | GZXH, diez cifras y YQ: Xinghe Freight (星和货运), otro transitario. Sus paquetes se siguen en la página de seguimiento de GZYL, con los mismos textos. |
| `JXCAA0000000000YQ` | También acaba en YQ, como los números de muchos transitarios chinos, pero empieza por JXC: es un número de Juxi Express, no de GZYL. |

En Peek da igual que lo pegues con espacios o en minúsculas.

**Dónde encontrarlo.** Es la tienda quien contrata el envío con GZYL y te pasa el número. ¿No lo encuentras? Mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

## Estados del seguimiento de GZYL: qué significa cada uno

GZYL escribe sus pasos en inglés, con erratas incluidas. En el orden en que aparecen:

| GZYL escribe | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order information received` | La tienda le ha enviado a GZYL los datos del pedido. GZYL todavía no tiene el paquete. | «Etiqueta creada» |
| `Arrive at the cross border receiving point` | GZYL ya tiene el paquete: es el primer escaneo con el paquete en sus manos. | «Admitido por el transportista» |
| `Goods leave the operation center` | El paquete ha salido del centro de operaciones de GZYL. | «En tránsito» |
| `Arrive at the international airport` | Está en el aeropuerto, esperando su vuelo. | «En tránsito» |
| `Departing from the airport` | El vuelo ha despegado. | «En tránsito» |
| `Item arrives at destination airport` | Ha aterrizado en tu país. | «En tránsito» |
| `local carrier Agent Import customs clearance` | Un agente en tu país está haciendo el despacho de importación en la aduana. | «En aduanas» |
| `The import customs keep it for inspection` | La aduana de tu país está inspeccionando el paquete. | «En aduanas» |
| `Customs clearance completed` | Ya ha pasado la aduana y sigue su camino. | «En tránsito» |
| `Internationaliy Package received at processing center` | Un centro de procesamiento de tu país ha recibido el paquete, ya despachado. No es una nueva admisión: el paquete se admitió mucho antes. | «En tránsito» |
| `Leave the email processing center` | Ha salido de ese centro de procesamiento. | «En tránsito» |
| `Arrival sorting, distribution center` | Ha llegado a un centro de distribución de tu país; GZYL escribe la ciudad después del texto. | «En tránsito» |
| `Leaving the sorting center` | Sale de un centro de clasificación; GZYL da su ciudad. | «En tránsito» |
| `Package is on the way` | Sigue su camino en tu país; GZYL añade el nombre de una ciudad. No quiere decir que ya esté en reparto. | «En tránsito» |
| `Delivered` | La entrega, con la ciudad. Puede ser una previsión y no un escaneo: mira más abajo. | «Entregado», con una nota sobre el calendario fijo, salvo que Peek deje fuera el paso (más abajo) |

Cuando GZYL escribe una ciudad después de estos textos, Peek la muestra como lugar del paso. Los pasos aparecen tal como los escribe GZYL, salvo `Delivered`, que en tu historial sale en español: «Entregado».

**De paso por Estados Unidos.** Un paquete también puede mostrar `Los Angeles Processing Center has received it`, con una ciudad entre corchetes detrás: un centro de procesamiento de Estados Unidos lo ha recibido de camino a su destino. Peek muestra «En tránsito».

**Una línea en chino.** En un pedido que todavía no tiene escaneos, la plataforma en la que funciona la página de seguimiento de GZYL escribe por su cuenta `货物电子信息已经收到` (datos electrónicos de la mercancía recibidos). La tienda ha creado el pedido, pero nadie ha escaneado aún el paquete. Peek muestra la línea tal cual, en chino, con el estado «Etiqueta creada».

**Horas.** Todas las horas de la página de seguimiento de GZYL van en hora de Pekín (UTC+8), también las de los pasos en tu país, sin indicar la zona horaria. Un paso de las 20:00 en la página de GZYL es de las 6:00 de esa misma mañana en El Salvador o en Ciudad de México, y de las 13:00 en la España peninsular en invierno (las 14:00 en verano; en Canarias, una hora menos).

**Pasos escritos de antemano.** GZYL puede escribir de antemano todo el recorrido de un paquete, desde que lo recoge hasta su último paso en tu país, mucho antes de las horas que marcan esos pasos, y su página de seguimiento muestra cada uno cuando llega su hora. Un paso escrito así es una previsión, no un escaneo de tu paquete. [Meiteshun](carrier:mtscn), que usa la misma plataforma de seguimiento, también lo hace. En GZYL, esa previsión termina además con un `Delivered` que la plataforma añade en bloque, por la noche del mismo día que indica: con fecha del día siguiente al último paso previsto y en horario de oficina de Pekín, sea cual sea tu país. Los servicios de seguimiento que copian los datos de GZYL también pueden mostrar esos pasos, así que un `Delivered`, en la página de GZYL o en un servicio de seguimiento, no demuestra que tu paquete haya llegado.

## Quién entrega los paquetes de GZYL en tu país

Peek no sabe quién entrega los paquetes de GZYL en España ni en ningún país de América Latina: los pasos de GZYL en el país de destino no nombran ninguna empresa, y Peek no pasa el seguimiento de un paquete de GZYL a ningún correo ni a ninguna otra empresa de esos países. La única empresa de entrega a la que Peek puede pasar un paquete de GZYL es Swiss Post, el correo suizo, en Suiza y Liechtenstein.

Peek no ha encontrado plazos de entrega de GZYL, ni reglas sobre intentos de entrega, puntos de recogida o cuánto tiempo se guarda un paquete: todo eso depende de la empresa que lo entrega. Pregúntale a la tienda qué empresa entrega tu pedido y con qué número de seguimiento.

## Paquete de GZYL que no llega o seguimiento parado

**Solo `Order information received` o `货物电子信息已经收到`.** La tienda ha creado el pedido, pero GZYL no ha escaneado el paquete. Si en unos días no aparece nada más, pregúntale a la tienda si de verdad lo ha enviado.

**Parado en la aduana.** Si te piden pagar algo para recibir el paquete, mira [paquete retenido en aduanas](guide:customs). Si se queda ahí varios días, avisa a la tienda.

**La página de GZYL o un servicio de seguimiento muestra más pasos que Peek.** Peek deja fuera los pasos escritos de antemano, el `Delivered` con el que termina la previsión de GZYL y los pasos con fecha posterior a su última consulta (más abajo), así que los pasos que ves de más pueden ser la previsión de GZYL y no escaneos. Guíate por el plazo de entrega que te dio la tienda, y escríbele cuando se haya cumplido. Más en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating) y [seguimiento de paquetes de China](guide:tracking-from-china).

**`Delivered`, pero no ha llegado nada.** Ese paso puede ser parte de la previsión de GZYL, no un escaneo. Salvo que el paquete lo entregue Swiss Post, Peek añade debajo de «Entregado»: «El seguimiento de GZYL sigue un calendario fijo y no muestra ninguna firma. Si no ha llegado nada, contacta con el vendedor.» Dile a la tienda que el paquete no ha llegado, y pregúntale qué empresa lo entrega y con qué número. Más en [paquete entregado pero no recibido](guide:delivered-not-received).

**No sale nada.** Con un número que no conoce, la página de seguimiento de GZYL muestra un solo resultado: tu número en 参考号 (referencia) y todos los demás campos vacíos. Si añadiste un número YL…YQ sin elegir transportista, Peek pone entonces «Probablemente GZYL» en el lugar del transportista, con el estado «Esperando el primer escaneo de GZYL», y sigue consultando a GZYL. Si una semana después GZYL sigue sin tenerlo, Peek te muestra el botón «Elegir otro transportista» y este aviso: «GZYL sigue sin tenerlo después de una semana. Comprueba el número con el remitente o elige otro transportista.» Compara el número con el mensaje de la tienda, carácter por carácter.

**Perdido o devuelto.** Fue la tienda quien contrató el envío con GZYL, así que pídele a ella, o a la plataforma donde compraste, el reembolso o un nuevo envío. Mira [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## GZYL: teléfono y atención al cliente

Cuando Peek lo comprobó, el 30 de octubre de 2026, no encontró ningún dato de contacto de GZYL: ni un sitio web aparte de su página de seguimiento, ni un teléfono o correo electrónico para destinatarios. Quien contrató el envío es la tienda: pregúntale a ella por tu pedido, y a la plataforma donde compraste si no te responde.

## Seguir un paquete de GZYL con Peek

[Peek](/) no puede saber con seguridad que un número es de GZYL solo por su forma: YL, diez cifras y YQ es solo un indicio. Pégalo igualmente: Peek sigue más de 3500 transportistas. Mientras lo añades, Peek consulta la página de seguimiento de GZYL («Comprobando servicios de seguimiento…») y muestra «GZYL tiene este paquete.» si la página conoce el número. Si no, pulsa «Cambiar» y elige GZYL en «Encaja con este número» (también lo encuentras escribiendo 广州云链), o usa «Cambiar transportista» en un paquete que ya hayas añadido. La referencia que te dio la tienda también sirve: pulsa «Cambiar» y busca GZYL. Peek muestra entonces el número de seguimiento del pedido junto a «GZYL lo sigue con el número». Eso sí, Peek quita los guiones, así que una referencia con guiones no se encuentra: pídele a la tienda el número YL…YQ.

Peek lee la página de seguimiento de GZYL y muestra el estado, cada paso con su lugar y, cuando los datos de GZYL lo indican, el país de destino. Lee las horas como hora de Pekín y te las muestra en la zona horaria de tu dispositivo. Deja fuera los nombres, la dirección, los códigos postales, el peso, los datos de pago, la firma y la prueba de entrega que pueden traer los datos de GZYL, y no muestra fecha prevista de entrega: GZYL no da ninguna.

Peek deja fuera todo paso escrito más de doce horas antes de la hora que marca, y todo paso con fecha posterior al momento en que consulta, en hora de Pekín. En un pedido con pasos escritos de antemano, deja fuera también `Delivered`, aunque se haya escrito el mismo día. Se guía por los pasos que conserva, no por el estado general que dan los datos de GZYL. Si deja fuera todos los pasos de un pedido, ese pedido todavía no tiene historial de seguimiento en Peek. Y si un servicio de seguimiento copia los pasos que Peek deja fuera como previsión de GZYL, `Delivered` incluido, Peek descarta también esas copias, aunque conserve otros pasos del pedido. Pero cuando no consigue acceder a la página de seguimiento de GZYL, Peek consulta a los servicios de seguimiento en su lugar, y sus copias pueden incluir pasos que GZYL escribió de antemano.

Salvo que pase el paquete a Swiss Post, Peek se queda con la página de seguimiento de GZYL: si la tienda te da el número de la empresa que lo entrega, añádelo como un paquete aparte. Peek consulta cada 10 minutos, y cada 2 minutos mientras el paquete está «En reparto», aunque ningún paso de GZYL que Peek conozca da ese estado. Cuando GZYL deja de dar noticias, consulta cada hora: tras 12 horas sin un paso nuevo si el paquete sigue en «Etiqueta creada» o «Admitido por el transportista», o tras 48 horas en las demás fases. Puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega»: «Pasos importantes» te avisa de los dos pasos de aduana de GZYL y de la entrega, pero no de sus vuelos ni de su clasificación. Peek no puede comunicarse con GZYL ni con la tienda por ti.

## Preguntas sobre el seguimiento de GZYL

### ¿Qué es GZYL (广州云链)?

Es 广州云链货运代理有限公司 (Guangzhou Yunlian), un transitario de Guangzhou, en China, que envía paquetes al extranjero. Sus números de seguimiento llevan YL, diez cifras y YQ.

### ¿Todos los números YL…YQ son de GZYL?

No con total seguridad. YL, diez cifras y YQ es el formato de GZYL, y ningún otro transportista del catálogo de Peek lo usa, pero Peek consulta igualmente la página de seguimiento de GZYL antes de dar el número por suyo. Con nueve u once cifras, no es el formato de GZYL.

### ¿Por qué GZYL dice que mi paquete está entregado si no ha llegado?

Puede ser parte de la previsión de GZYL. GZYL escribe de antemano buena parte del recorrido de un paquete y lo termina con un `Delivered`, y esos pasos son una previsión, no escaneos. Cuando lee la página de seguimiento de GZYL, Peek deja fuera esos pasos, `Delivered` incluido, y si aun así muestra «Entregado», añade debajo que el seguimiento de GZYL sigue un calendario fijo. Dile a la tienda que el paquete no ha llegado y pregúntale qué empresa lo entrega.

### ¿Quién entrega los paquetes de GZYL en mi país?

Peek no lo sabe: los pasos de GZYL en el país de destino no nombran ninguna empresa. Pregúntale a la tienda qué empresa entrega tu pedido y con qué número de seguimiento, y añade ese número en Peek como un paquete aparte.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nombre de la empresa y otros nombres, página de seguimiento en una dirección IP, formato YL…YQ y su coincidencia débil, Xinghe Freight en la página de seguimiento de GZYL, búsqueda por número o por referencia con sus guiones, textos de los pasos y sus estados, la línea en chino de la plataforma, ciudades después de los textos, hora de Pekín, recorrido escrito de antemano y la entrega que lo cierra, respuesta a un número desconocido, Swiss Post como único traspaso, lo que Peek muestra, deja fuera y descarta
:::
