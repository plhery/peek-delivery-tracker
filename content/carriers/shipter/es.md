---
title: Seguimiento Shipter: números H3 desde Corea del Sur
description: Sigue paquetes aéreos internacionales de Shipter (H3 Networks) desde Corea del Sur: códigos H3...KR, entregas locales, estados y enlaces.
slug: seguimiento-shipter
published: 2026-11-24
updated: 2026-11-24
---

Shipter, operado por **H3 NETWORKS Co., Ltd.** en Corea del Sur, envía paquetes B2B y B2C desde Corea a más de **100 países** mediante más de 40 canales logísticos, sobre una red aérea que conecta más de 25 aeropuertos cada semana ([shipter.kr](https://www.shipter.kr/)). Su servicio B2B cubre transporte, aduanas y almacenamiento; su servicio B2C abarca entrega internacional, aduanas y última milla. Amazon Korea lo eligió como socio logístico oficial en su red SPN (*Service Provider Network*), está registrado como transportista en eBay, Kogan y Allegro, su seguimiento está conectado con AfterShip y 17TRACK y también opera como socio para envíos a creadores de contenido.

Puedes consultar un paquete en [shipter.kr/tracking](https://www.shipter.kr/tracking) o pegar tu código `H3...KR` en la caja de arriba.

## Formato del número de seguimiento de Shipter

Los números de Shipter empiezan por `H3` y terminan en `KR`, con dos letras y cinco o seis dígitos en medio:

| Formato (inventado) | Qué es |
| --- | --- |
| `H3AU482917KR` | 12 caracteres: `H3`, dos letras, seis dígitos y `KR`. Peek pregunta únicamente a Shipter. |
| `H3FR48291KR` | 11 caracteres: `H3`, dos letras, cinco dígitos y `KR`. Peek pregunta únicamente a Shipter. |

Un enlace de `shipter.kr/tracking/<número>` o `shiptertracking.com/trackingResult/<número>` asigna el paquete a Shipter de inmediato. Para paquetes enviados desde Corea del Sur con Korea Post, consulta [Korea Post](carrier:korea-post); para otros formatos, la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Shipter

El historial de Shipter combina sus propios hitos de exportación en Corea del Sur y carga aérea internacional con los escaneos reenviados por el transportista local de última milla en tu país, en inglés.

| Texto de Shipter / colaborador | Qué significa |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Preaviso recibido del vendedor coreano; Shipter aún no ha escaneado el paquete físico. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter recibió y escaneó el paquete en sus instalaciones de Corea. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Despachado desde Corea del Sur en un vuelo internacional de carga. |
| `Arrived at destination airport` / `Collected from airport terminal` | Aterrizó en el país de destino y fue recogido de la terminal de carga del aeropuerto. |
| `Import customs cleared` | La aduana de destino liberó el paquete para continuar hacia la entrega local. |
| `Arrived at destination` / `In transit` | Incorporado al flujo de clasificación en destino antes de los escaneos del transportista local. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Procesado y expedido dentro de la red del transportista local de destino. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Reenviado desde el socio local del país de destino, con su depósito y localidad. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | En reparto hoy con el mensajero local. |
| `Your parcel was delivered` / `Delivered.` | Entregado en la dirección del destinatario. |

Dos avisos reenviados por el socio local, `Your parcel will be entrusted to last mile .it is being prepared by your sender` y `Shipping information received`, llegan después del escaneo de admisión de Shipter. No significan que el paquete haya retrocedido.

## Tránsito aéreo internacional, aduanas y entregas locales

Shipter envía los paquetes por aire desde Corea del Sur y los entrega a un mensajero local en el país de destino:

- **Vuelo y aeropuerto:** tras `Depart origin airport`, Shipter muestra `Arrived at destination airport` al aterrizar, `Collected from airport terminal` cuando sale de la terminal de carga e `Import customs cleared` al terminar el despacho de importación. Consulta la [guía de aduanas](guide:customs) y [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Entrega local de última milla:** un transportista del país de destino realiza la entrega final y Shipter integra sus escaneos de depósito, `Out for delivery` y entrega dentro de su propio historial.
- **Sin fecha estimada de entrega:** la API pública de seguimiento de Shipter no publica una fecha estimada de entrega; sigue el avance comprobando `Import customs cleared`, la recepción en depósito local y `Out for delivery`.

## Seguir Shipter con Peek

Pega tu código `H3...KR`, o un enlace `shipter.kr/tracking/...` o `shiptertracking.com/trackingResult/...`, en Peek. Para un código de 11 o 12 caracteres, Peek pregunta solo a Shipter. Muestra «Shipter tiene este paquete.» en cuanto Shipter responde. Si Peek elige otro transportista, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en su ficha y elige Shipter.

Peek lee el seguimiento de Shipter a través del [adaptador Shipter de código abierto en universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) y reintenta tras un error de red. Muestra el texto en inglés de cada escaneo con el depósito o lugar indicado y la hora de entrega. Shipter entrega sus horas con desplazamiento GMT y Peek las muestra en la hora de tu dispositivo; una hora sin desplazamiento se muestra tal cual la entrega Shipter.

Peek clasifica los escaneos de Shipter en etapas claras: «Etiqueta creada» para el preaviso antes de la recogida, «Admitido por el transportista» cuando Shipter escanea el paquete en Corea, «En tránsito» para salida del vuelo, llegada al aeropuerto, despacho aduanero y traslados en centros locales, «En reparto» cuando el repartidor local inicia su ruta y «Entregado» al finalizar. Los avisos tardíos del socio local (`Shipping information received` y `Your parcel will be entrusted to last mile...`) permanecen en el historial sin hacer retroceder el envío.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Shipter.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Shipter.»

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Shipter muestra `Out for delivery` o `Your parcel is with your local courier and ready for delivery..`, cada 2 minutos desde las 6:00 según el reloj del escaneo más reciente mientras responda el localizador de Shipter. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Shipter no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Peek puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Contactar con Shipter

Shipter trabaja para las empresas que realizan envíos con su red:

- **Web oficial y consultas de servicio:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* para consultas de servicio)
- **Rastreador oficial online:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Contacta primero con el vendedor:** cada empresa cliente cuenta con un gestor de atención dedicado en Shipter que tramita incidencias, devoluciones y reclamaciones con sus clientes. Si tu paquete se retrasa, llega dañado o se pierde, contacta con la tienda remitente con tu código `H3...KR`.

## Preguntas frecuentes

### ¿Qué aspecto tiene un número de seguimiento de Shipter?

Empieza por `H3` y termina en `KR`, con dos letras y cinco o seis dígitos en medio: 11 o 12 caracteres, como `H3AU482917KR`. La web de Shipter no especifica qué significan las dos letras.

### ¿Por qué apareció «Shipping information received» días después de que mi paquete ya hubiera salido?

Es un aviso del socio de última milla del país de destino que Shipter reenvía después de su propio escaneo de admisión. No hace retroceder el paquete: Peek mantiene la etapa del escaneo anterior.

### ¿Por qué mi paquete de Shipter muestra actualizaciones de otro transportista?

Shipter gestiona la exportación desde Corea del Sur y el transporte aéreo internacional, luego transfiere el paquete a un mensajero local en el país de destino y muestra los escaneos de depósito y entrega de ese socio dentro de tu historial de Shipter.

### ¿Ofrece Shipter una fecha estimada de entrega?

No. El flujo público de Shipter muestra eventos de exportación, vuelo, aduanas y mensajero local con hora y desplazamiento GMT, sin fecha estimada de entrega.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
