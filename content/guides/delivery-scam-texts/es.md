---
title: Estafa del paquete por SMS: cómo saber si un aviso es falso
description: Cómo saber si un SMS de Correos, SEUR, GLS, DHL o InPost es falso, qué te envían de verdad y qué hacer si has pinchado el enlace o has dado tu tarjeta.
slug: estafa-paquete-sms-falso
picture: Un móvil con un mensaje con un triángulo de aviso y un anzuelo en el enlace, una moneda tachada y Pip, preocupado, con una mano de alto en su bocadillo.
published: 2026-10-14
updated: 2026-10-28
---

Un SMS o un correo que dice que tu paquete está retenido, que falta pagar una pequeña cantidad o que hay que confirmar la dirección, y que te da un enlace para arreglarlo, es casi siempre una estafa. No pulses nada: abre tú la web o la app del transportista y consulta ahí el número de seguimiento que te mandó la tienda. Si ya has metido los datos de tu tarjeta, llama ahora a tu banco al número que aparece en la tarjeta o en su app.

## ¿Cómo saber si un SMS de Correos es falso?

Estos mensajes son phishing: se hacen pasar por el transportista para sacarte datos o dinero. Un SMS falso suele tener varias de estas señales:

- **No esperas ningún paquete.** Es la primera pregunta que propone [Correos](carrier:correos-spain): si no esperas ningún envío, no eres cliente ni tienes contratado ningún servicio desde el que te puedan escribir, ignora el mensaje y bórralo.
- **Te pide dinero o datos para entregarlo.** «Correos nunca solicita datos personales, bancarios o movimientos económicos por correo electrónico o mensaje de texto (SMS)». InPost dice que nunca te pedirá el pago de ningún servicio directamente por SMS, y [GLS](carrier:gls-es) que no pide datos de pago por correo o SMS para confirmar que recibes un envío.
- **Te mete prisa.** Correos lo resume así: «las prisas nunca son buenas». InPost y GLS añaden las amenazas: que vas a perder el paquete o que habrá consecuencias si no haces algo ya.
- **El enlace no es del transportista.** Imitaciones como `inpost-es.com` o `inpost.entrega-paquete.com` (ejemplos de InPost). GLS solo enlaza a gls-group.com/ES/, e InPost te pide comprobar que el enlace lleva a inpost.es.
- **El remitente no cuadra.** GLS te pide desconfiar si te llaman, por WhatsApp o por teléfono, con un prefijo que no es el +34, y un email desde @gmail o @yahoo no es de GLS.
- **Te pide llamar, descargar algo o pagar por un canal raro.** InPost nunca te pedirá que llames a un número de tarificación adicional (807, 906…), que descargues un archivo de Dropbox o WeTransfer ni que hagas una transferencia para liberar un paquete. Correos recuerda que las entidades oficiales nunca envían adjuntos ni ejecutables.
- **Está mal escrito.** Correos y GLS señalan las faltas de ortografía y los fallos gramaticales; según GLS, muchas de estas estafas pasan por un traductor automático.

Que el SMS llegue con el nombre del transportista no demuestra nada. Desde el 15 de septiembre de 2026, los operadores de telefonía en España tienen que bloquear los SMS, MMS y RCS que llegan con un alias (el nombre que sale como remitente, como el de una marca) que no esté inscrito en el Registro de Alias de la CNMC. Ayuda, pero GLS avisa de casos en los que los estafadores suplantan también el número de teléfono, y una empresa sin alias registrado puede seguir escribiéndote desde un número normal. Ni el nombre ni el número garantizan nada: compruébalo siempre por tu cuenta.

### Cómo leer un enlace antes de pulsarlo

Los enlaces falsos suelen empezar por el nombre del transportista. El dueño de la dirección es lo que va justo antes de la primera `/` sola. El ejemplo, inventado, usa Royal Mail, el servicio postal británico, pero el truco es el mismo con correos.es o gls-group.com:

:::anatomy royalmail.co.uk. redelivery-fee.example /pay
- royalmail.co.uk. | El nombre del transportista, puesto delante como cebo. Aquí solo es una etiqueta
- redelivery-fee.example | El verdadero dueño de la dirección: no es Royal Mail
- /pay | La página que te pide la tarjeta
:::

Aunque un enlace parezca bueno, no lo uses: escribe tú la dirección del transportista.

## ¿Correos, GLS e InPost envían SMS de verdad?

Sí, casi todos, y por eso los falsos funcionan. Esto es lo que cada uno dice en su propia página:

| Transportista | Cómo te escribe de verdad | Lo que dice que nunca hace |
| --- | --- | --- |
| Correos | SMS o correo que sustituye al aviso de llegada cuando no ha podido entregar, y SMS con la oficina donde recogerlo; si elegiste entrega en oficina, un SMS con un enlace a su buscador de oficinas | Pedir datos personales, bancarios o pagos por correo electrónico o SMS |
| GLS | Emails desde @gls-spain.es, @mail.gls-spain.es, @comunicaciones.gls-spain.com o @gls-spain.com; enlaces solo a gls-group.com/ES/ | Pedirte por correo un pago con una plataforma online, tarjeta o transferencia (el reembolso se paga en efectivo al recibirlo); tener teléfonos que empiecen por 807 |
| InPost | Emails desde @inpost.es o @inpost-spain.com; web inpost.es | Pedir el pago de un servicio por SMS, o tasas y aduanas mediante un enlace en un SMS |

Los transportistas de verdad envían enlaces, así que un enlace por sí solo no demuestra nada. Los pocos pagos reales son casi todos de aduanas, como el aviso de GLS Spain por correo, SMS o carta para un paquete de fuera de la UE: la guía de [paquete retenido en aduanas](guide:customs) explica cómo te los piden de verdad.

## Qué dicen los SMS falsos de paquetes

Se repiten los mismos guiones. Estos salen de los avisos de los propios transportistas y de INCIBE:

- **Entrega suspendida por falta del número de casa.** INCIBE avisó en diciembre de 2025 de SMS que dicen que el paquete no se pudo entregar porque falta el número. La web falsa te pide una franja horaria para volver a intentarlo, luego tus datos y al final la tarjeta.
- **Confirma tu dirección.** Correos dice que pedir un pago o completar la dirección de entrega son los dos ganchos más usados con su nombre. InPost cita «Confirma tu dirección para recibir tu envío».
- **Paquete retenido.** «Tu paquete está retenido», en emails falsos con un enlace a una web que imita la suya (InPost).
- **Gastos de envío.** En 2022, INCIBE avisó de correos y SMS que suplantaban a [SEUR](carrier:seur) y pedían 2,99 € de gastos de envío. Después de la tarjeta, la web pedía un código que iba a llegar por SMS (nunca llegaba) y contestaba «código incorrecto» para parecer creíble: para entonces ya tenían los datos de la tarjeta.
- **Paquete devuelto.** En julio de 2025, INCIBE avisó de emails que suplantaban a [DHL](carrier:dhl-express): el paquete había vuelto a una oficina de DHL y, para recuperarlo en 48 horas, había que pagar 2,86 € en una web que imitaba la de DHL.
- **Tasas de aduana.** InPost cita SMS que piden «pagar una tasa de aduana» con un enlace. Si de verdad esperas algo de fuera de la UE, compruébalo en la web del transportista, como se explica más arriba.
- **Una llamada.** InPost avisa de falsos agentes que llaman por un problema con tu envío y piden datos personales o bancarios. GLS confirma que el 911 118 768 es suyo: lo usa para revisar la calidad de las entregas con una reclamación abierta, nunca con fines comerciales.
- **WhatsApp en vez de SMS.** GLS dice que, si alguna vez diste tu teléfono, los estafadores pueden llamarte por WhatsApp, muchas veces desde números extranjeros, para pedirte datos bancarios o un pago.
- **Un «transportista» para tu venta de segunda mano.** GLS no hace de intermediario ni de depositario en la compraventa de artículos de segunda mano, ni usa plataformas de compraventa para anunciar sus envíos. Si alguien en Wallapop o Milanuncios te manda un enlace de «GLS» para cobrar, es un engaño.

## Cómo comprobar un SMS de paquete sin caer en la estafa

Ninguno de estos pasos usa el mensaje:

:::steps
- Piensa si esperas algo | ¿Ningún pedido y nadie que te vaya a mandar nada? Entonces trátalo como una estafa.
- Busca el número de seguimiento real | En el correo de envío o en el pedido de la tienda, nunca el número que viene en el SMS.
- Entra tú en la web del transportista | Escribe su dirección o abre su app. No pulses, no escanees ni llames a nada del mensaje.
- Mira si de verdad te piden algo | Si hay algo que pagar, compruébalo en la web o la app del transportista, no en el mensaje.
- Repórtalo y bórralo | Manda una captura a INCIBE y bloquea al remitente (más abajo te contamos cómo).
:::

InPost lo dice claro: haz siempre el seguimiento desde su página oficial y nunca metas tu número en un enlace de un SMS sin comprobar antes que lleva a inpost.es. ¿No encuentras el número? Mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number). También puedes pegarlo en [Peek](/), que consulta al transportista y, si hace falta, otras fuentes de seguimiento, para que veas los últimos escaneos sin acercarte al enlace. Peek no puede pagar nada por ti: si hay algo pendiente, se paga en la web o la app del transportista.

Cuando Correos no puede entregarte un paquete, te lo dice con un SMS o un correo que sustituye al aviso de llegada de papel, y luego con otro SMS con la oficina donde recogerlo, sin pedirte nada a cambio. Cómo funciona un aviso de verdad y cómo pedir otro intento lo tienes en la guía sobre el [intento de entrega «ausente»](guide:missed-delivery).

## Qué hacer si pinché en el enlace o di los datos de mi tarjeta

Un SMS falso no hace daño mientras no pulses, contestes, llames o escribas algo. Lo que toca ahora depende de hasta dónde hayas llegado:

:::steps
- Cierra la página | No escribas nada más en ella y no contestes al mensaje.
- Llama a tu banco | Al número que aparece en tu tarjeta o en la app del banco, y pide que bloqueen la tarjeta y los movimientos sospechosos.
- Cambia tus contraseñas | Si escribiste una en la web falsa, cámbiala en todas partes donde la uses.
- Desconfía de las llamadas | ¿Te llama alguien, aunque diga ser del banco o del transportista? Cuelga y llama tú a un número que hayas buscado por tu cuenta.
- Denúncialo | Ante la Policía Nacional o la Guardia Civil si has perdido dinero o datos.
:::

**¿Solo has pinchado el enlace?** InPost aconseja cerrar el navegador y pasar un análisis de seguridad al móvil o al ordenador. Después, haz lo que INCIBE pide con cualquier SMS de este tipo: avísale, bórralo y bloquea al remitente.

**¿Has metido la tarjeta o has pagado?** Llama a tu banco lo antes posible para que bloquee la tarjeta y los movimientos sospechosos, como recomienda INCIBE. Guarda todas las pruebas que puedas, incluidos los enlaces, y presenta una denuncia con ellas. Y no te fíes de que un cargo pequeño sea inofensivo: lo que buscan es tu tarjeta, no los 2,99 €.

Para la denuncia, la Oficina Virtual de Denuncias de la Policía Nacional te deja empezarla por internet, pero tienes que firmarla en la comisaría que elijas lo antes posible y nunca más tarde de 72 horas; sin esa firma no tiene validez. Si tienes dudas sobre qué hacer, INCIBE atiende en el 017, gratis y de forma confidencial, todos los días de 8:00 a 23:00, y también por WhatsApp (900 116 117) y Telegram (@INCIBE017).

## Cómo denunciar un SMS falso de paquetes

Repórtalo aunque no hayas pinchado nada, y después bórralo.

| Qué ha pasado | Dónde avisar |
| --- | --- |
| Te ha llegado un SMS o un email falso | Al buzón de INCIBE-CERT, incidencias@incibe-cert.es, con una captura, el mensaje reenviado o la URL. Si suplanta a InPost, a fraude@inpost.es; a GLS, por su formulario de fraude, con capturas, el nombre y el número de quien lo envía. Si suplanta a Correos y tienes dudas, pregunta a su Atención al Cliente o en @CorreosAtiende, en X |
| Tienes dudas o no sabes qué hacer | El 017 de INCIBE, por teléfono, WhatsApp (900 116 117) o Telegram (@INCIBE017), de 8:00 a 23:00 |
| Has perdido dinero o datos | Primero tu banco. Después, denuncia en la Policía Nacional (en comisaría o con la Oficina Virtual de Denuncias y firma en 72 horas) o en la Guardia Civil |

En el iPhone, debajo de un mensaje de un remitente desconocido aparece **Mensaje no deseado**: tócalo y luego **Eliminar e informar**. Si ya has contestado al mensaje, no podrás informar de él. En Mensajes de Google, en Android, mantén pulsada la conversación, toca **Bloquear**, marca **Marcar como spam** y pulsa **Aceptar**.

:::sources
- [Correos: Phishing](https://www.correos.es/es/es/atencion-al-cliente/seguridad-de-la-informacion/phishing) – ¿esperas un envío?, nunca pide datos ni pagos por SMS o correo, ganchos más usados, adjuntos, prisas, faltas, @CorreosAtiende
- [Correos: Aviso de llegada/SMS](https://www.correos.es/es/es/atencion-al-cliente/recibir/aviso-de-llegada) – SMS o correo de aviso de llegada, SMS de oficina con enlace al buscador
- [GLS Spain: Consejos de seguridad](https://gls-group.com/ES/es/gls-informa/consejos-de-seguridad/) – dominios, web oficial, sin pagos por tarjeta o transferencia, aranceles fuera de la UE, 807, 911 118 768, +34, spoofing, segunda mano, formulario de fraude
- [InPost: Protégete contra fraudes online](https://www.inpost.es/envia-con-nosotros/como-enviar/pago-seguro/informacion-sobre-fraude) – nunca pide pagos por SMS, dominios falsos, 807 y 906, vishing, qué hacer, fraude@inpost.es
- [INCIBE: Smishing que suplanta a empresas de paquetería por falta del número de casa](https://www.incibe.es/ciudadania/avisos/campana-de-smishing-que-suplanta-empresas-de-paqueteria-con-la-excusa-de-que) – guion, banco, pruebas, denuncia, reportar y bloquear
- [INCIBE: Mensajes fraudulentos que suplantan a SEUR](https://www.incibe.es/ciudadania/avisos/mensajes-fraudulentos-que-suplantan-seur-solicitandote-dinero) – 2022, 2,99 €, código por SMS que nunca llega, «código incorrecto»
- [INCIBE: DHL no está avisando por correo de que un paquete ha sido devuelto y que se cobrará una tarifa de envío](https://www.incibe.es/ciudadania/avisos/dhl-no-esta-avisando-por-correo-de-que-un-paquete-ha-sido-devuelto-y-que-se) – julio de 2025, paquete devuelto, 48 horas, 2,86 €
- [INCIBE: Reporte de fraude](https://www.incibe.es/ciudadania/ayuda/reporte-de-fraude) – incidencias@incibe-cert.es
- [INCIBE: Tu Ayuda en Ciberseguridad](https://www.incibe.es/linea-de-ayuda-en-ciberseguridad) – 017, horario, WhatsApp, Telegram
- [Policía Nacional: Oficina Virtual de Denuncias](https://denuncias.policia.es/OVD/) – firma en comisaría en 72 horas
- [CNMC: La CNMC insta a las empresas a registrar sus alias](https://cnmc.es/prensa/registro-alias-llamamiento-20260901) – bloqueo de alias no inscritos desde el 15 de septiembre de 2026
- [BOE: Circular 3/2026 de la CNMC](https://www.boe.es/boe/dias/2026/09/12/pdfs/BOE-A-2026-19053.pdf) – bloqueo de alias no inscritos, preinscripción transitoria
- [Apple: Informar de mensajes no deseados en el iPhone](https://support.apple.com/es-es/guide/iphone/iph3f94d910d/ios) – Mensaje no deseado, Eliminar e informar
- [Google: Denunciar spam en Mensajes de Google](https://support.google.com/messages/answer/9061432?hl=es&co=GENIE.Platform%3DAndroid) – Bloquear, Marcar como spam
:::
