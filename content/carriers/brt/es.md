---
title: Seguimiento BRT: paquete desde Italia, estados y contacto
description: Sigue tu paquete de BRT desde Italia: dónde está el BRTcode, qué significan sus estados en inglés, relación con SEUR y DPD en España y cómo reclamar.
slug: brt-seguimiento
published: 2026-11-17
updated: 2026-11-17
---

BRT es una empresa italiana de transporte urgente integrada en el grupo Geopost, igual que DPD, [SEUR](carrier:seur) y [Chronopost](carrier:chronopost). Puedes seguir un paquete desde el enlace del SMS o correo de BRT, en [brt.it](https://www.brt.it/en/) o pegando el número arriba. BRT envía ese código —el *BRTcode*— en cuanto recibe el envío y el remitente ha facilitado tus datos de contacto.

## Número de seguimiento de BRT: dónde está, formatos y avisos reales

El *BRTcode* aparece en los SMS y correos de BRT y en el aviso que deja el repartidor si no estabas. ¿Sin mensaje? Mira el correo de envío de la tienda o consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number).

Si el comercio comparte tu teléfono o correo, BRT envía avisos legítimos con el *BRTcode* al recoger el bulto y notificaciones por ausencia, almacenaje (*giacenza*) o llegada a un *BRT-fermopoint* o *BRT Locker*. El 16 de abril de 2026, no obstante, BRT publicó una [alerta de phishing por SMS y correo](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) por falsos avisos de no entrega y facturas falsas con software dañino: revisa posibles erratas, remitentes genéricos o alterados y llamadas urgentes a pulsar enlaces y, ante la duda, no pulses enlaces ni descargues adjuntos e introduce tu *BRTcode* directamente en `brt.it` o `vas.brt.it` (lee también [cómo detectar SMS falsos de reparto](guide:delivery-scam-texts)).

Formatos que Peek admite para BRT (ejemplos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `182094736105` | 12 dígitos: número de expedición (*numero spedizione*) asignado por BRT. |
| `06482917350428` | 14 dígitos: un *BRTcode*. |
| `918273645019283` | 15 dígitos: identificador de bulto (*ID collo cliente*) creado por el remitente. |
| `EOB19283746501928374` | `EOB` y 17 dígitos: otro identificador de bulto del remitente. |

El buscador avanzado de BRT pide 19 dígitos para el *BRTcode*; Peek no admite códigos de 19 cifras para BRT. El código de seguridad de 3 cifras (*codice di sicurezza*), usado para cambiar una entrega, no es un número de seguimiento.

Si BRT no muestra nada, comprueba posibles erratas o espera a que reciba el paquete. El [buscador avanzado de BRT](https://vas.brt.it/vas/sped_numspe_par.htm?lang=en) (*Rintraccia spedizione*) abarca 12 meses con *BRTcode* o número de 12 cifras, y solo 2 meses con un *ID collo cliente*. Los códigos de 14 cifras también los usan [SEUR](carrier:seur) y DPD, y los de 12 cifras redes como [Mondial Relay](carrier:mondial-relay), [Sending](carrier:sending) o Colis Privé: verifica el transportista citado por la tienda o [los formatos de número de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de BRT: qué significa cada mensaje

El seguimiento en `vas.brt.it` abre en italiano y cambia a inglés, francés o alemán (sin español). Peek lee la versión inglesa, donde las columnas siguen en italiano: *Data* (fecha), *Ora* (hora), *Filiale* (delegación BRT) y *Evento* (evento). En el historial de Peek aparecen estos textos en inglés, salvo cualquier entrega —incluida la recogida en *BRT-fermopoint* o *BRT Locker*—, cuya línea consta como `Delivered` (con Peek en italiano verás las etiquetas italianas de BRT y `Consegnata`, quedando en inglés lecturas como las de taquilla que solo existen en ese idioma):

| Texto en BRT | Qué significa para ti | Etapa en Peek |
| --- | --- | --- |
| `SHIPPING INFO SENT TO BRT` | BRT tiene los datos del remitente, aún no el paquete. Ver [estados de seguimiento](guide:tracking-statuses). | «Etiqueta creada» |
| `COLLECTED` | BRT recogió el paquete en origen. | «Admitido por el transportista» |
| `DEPARTED` | Salió de una delegación o plataforma de BRT. | «En tránsito» |
| `ARRIVED AT DEPOT` | Llegó a la delegación indicada en *Filiale*. | «En tránsito» |
| `FOR DELIVERY` | El repartidor va en ruta con tu paquete. | «En reparto» |
| `NOTICE OF ATTEMPTED DELIVERY` | No había nadie y el mensajero dejó aviso. | «Intento de entrega» |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | Sin entregar: destinatario desconocido o dirección incompleta. | «Intento de entrega» |
| `TURMOIL` | Una manifestación pública retiene el envío. | «Incidencia» |
| `PATRONAL HOLIDAY` | Fiesta patronal en destino: el bulto espera el día antes de la delegación de reparto, sin intento. | «En tránsito» |
| `ARRIVED AT BRT-fermopoint` | Listo para recoger en un punto *BRT-fermopoint*. | «Listo para recoger» |
| `COLLECTED AT BRT-fermopoint` | Recogido por ti en el *BRT-fermopoint*. | «Entregado» |
| `ARRIVED AT BRT LOCKER` | Listo para recoger en una taquilla *BRT Locker*. | «Listo para recoger» |
| `COLLECTED AT BRT LOCKER` | Retirado por ti del *BRT Locker*. | «Entregado» |
| `TO BE DELIVERED` | El socio extranjero lo tiene para reparto (anotado en el mismo minuto que `FOR DELIVERY`). | «En tránsito» (con `FOR DELIVERY`: «En reparto») |
| `ABSENT AFTER ATTEMPT.DELIVERY` | El socio extranjero no encontró a nadie; seguirá otro intento o devolución. | «Intento de entrega» |
| `To collect at the parcel shop` | Depositado en la tienda de recogida del socio extranjero. | «Listo para recoger» |
| `DELIVERED` | Entregado. | «Entregado» |
| `RETURNED TO SENDER` | Regresa al remitente. | «Devuelto al remitente» |

Tres matices: `COLLECTED` es la recogida al vendedor, y si el paquete llegó a Italia vía [Chronopost](carrier:chronopost) o DPD marca la entrega a BRT («En tránsito»). Un `FOR DELIVERY` en un almacén DPD extranjero es la cesión a esa red («En tránsito»). Y si `DELIVERED` sigue a `RETURNED TO SENDER`, el bulto volvió al remitente: la línea dice `Delivered`, pero la etapa en Peek sigue siendo «Devuelto al remitente».

## Paquete de BRT de Italia a España

**Quién reparte.** Por carretera hacia Europa, BRT usa la red DPD (u otro socio para envíos pesados o voluminosos); en España, la marca de Geopost es [SEUR](carrier:seur). En envíos DPD, la [ayuda del detalle de BRT](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) describe un recuadro con enlace al seguimiento DPD. En Peek, el cambio automático desde un código de BRT solo salta a transportistas con marca DPD: si sigues un envío a España con su número BRT, Peek mantiene la lectura de BRT (con estados copiados del socio como `TO BE DELIVERED`, `ABSENT AFTER ATTEMPT.DELIVERY` o `To collect at the parcel shop`). Si tienes el código de 14 dígitos del socio y lo pegas aparte desde España, Peek consultará primero a [SEUR](carrier:seur) y las redes DPD.

**Aduanas.** De Italia a la Península o Baleares el paquete circula dentro de la UE, sin aduanas. Canarias, Ceuta y Melilla quedan fuera del territorio de IVA de la UE (y Ceuta y Melilla, también del aduanero), así que el envío sale con trámites de exportación; para estos destinos y los de fuera de la UE, consulta [paquetes en aduanas](guide:customs).

## Entrega fallida en Italia: nuevo intento, BRT-fermopoint y Locker

Si estás ausente en una entrega en Italia, BRT avisa por correo, SMS o nota. Suele reintentar al siguiente día laborable; según la *Carta dei servizi*, si falla otra vez, la delegación te llama.

Desde el enlace del aviso o en myBRT (con *BRTcode* y código postal o PIN) puedes elegir otro día o franja, desviar a *BRT-fermopoint* o delegación, liberar un almacenaje o cambiar dirección con permiso del remitente. Más en [entrega fallida](guide:missed-delivery).

**Almacenaje (*in giacenza*).** Motivos: destinatario ausente o desconocido, dirección errónea o incompleta o rechazo; figura en el detalle del envío. Puedes dar instrucciones en el aviso o el seguimiento (a veces con el código de seguridad de 3 cifras). BRT también consulta al remitente: según la *Carta dei servizi*, sin orden escrita en 10 días desde el aviso, el paquete se devuelve (las condiciones generales remiten al plazo pactado con el remitente). Respondido el aviso, el detalle indica si hay gastos de almacenaje y quién los paga.

**En delegación (*fermo deposito*).** Acude con tu documento de identidad o envía a un autorizado con autorización escrita, su documento y copia del tuyo. Con tus datos, BRT envía dirección, horario e importe de reembolso si aplica.

**BRT-fermopoint.** Comercios asociados (quioscos, estancos, papelerías…), algunos abiertos en fin de semana. Aviso por correo o SMS y 10 días de custodia.

**BRT Locker.** Taquillas en comercios, centros comerciales y gasolineras —muchas exteriores 24/7— abiertas con QR o PIN. Los bultos de más de 20 kg no entran en *Locker* ni en *BRT-fermopoint*. Si falla la pantalla o el hueco está vacío, usa el formulario al pie del correo de recogida. Ver [taquillas de paquetes](guide:parcel-lockers).

## Paquete de BRT retrasado, dañado o extraviado

Los plazos de BRT son orientativos. Si no avanza, contacta con la delegación de *Filiale* o con la tienda titular del contrato y revisa [seguimiento que no se actualiza](guide:tracking-not-updating).

**Daños o pérdida.** Si pagaste el porte, usa el formulario de soporte de BRT con fotos si hay daños; si no, avisa al vendedor. Ante daños visibles al recibirlo, firma con reserva específica («*con riserva di controllo*» describiendo el daño), pues las reservas genéricas no valen según BRT. Los daños ocultos se reclaman por escrito en 8 días en envíos dentro de Italia (art. 1698 del Código Civil italiano) o en 7 días sin domingos ni festivos en carretera internacional (convenio CMR). Más en [paquete dañado](guide:damaged-parcel).

**Reclamación y conciliación.** Destinatario y remitente pueden reclamar descargando el PDF *Modulo di Reclamo* en [Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/) y adjuntándolo en `services.brt.it/it/form-agcom`. BRT contesta en 45 días. Después cabe conciliación en `conciliazioni@brt.it`, por ejemplo con *Casa del Consumatore* (cuota anual de 50 €, o 25 € hasta 50 € de litigio, reembolsada por BRT si hay acuerdo) u otra entidad, antes de AGCOM. ¿Consta entregado y no está? Mira [entregado pero no recibido](guide:delivered-not-received).

## Atención al cliente y contacto de BRT

- **Formularios** en la página inglesa [Support](https://services.brt.it/en/support) según tu incidencia (retraso, daños, entregado no recibido). No figura teléfono.
- **Teléfono gratuito italiano** `800 278 708`, lun.–vie. 8:30–18:30 según la *Carta dei servizi* (BRT no indica si funciona desde el extranjero).
- **Correo** `assistenzaclienti@brt.it` según la *Carta*, indicando obligatoriamente nombre y apellidos, códigos postales de origen y destino y *BRTcode*.
- **Chat** en `brt.it` o delegación en el [buscador de delegaciones](https://services.brt.it/en/customer-service/search-for-a-branch). El número de Bolonia de la *Carta* es de la sede central.

## Seguir envíos de BRT con Peek

Pega el número en [Peek](/). Con `EOB` y la mayoría de los 15 dígitos, Peek consulta solo a BRT (antes que a [FedEx](carrier:fedex) si también pasa su control); con 12 dígitos pregunta a BRT en la primera tanda junto a redes como [Sending](carrier:sending). Con 14 dígitos consulta a BRT al instante en Italia, y en España tras los cinco primeros candidatos ([SEUR](carrier:seur) y redes DPD). Si eligió otro, pulsa «Cambiar» y escoge BRT en «Encaja con este número» o «Todos los transportistas» (también por «Bartolini»). Funcionan enlaces `vas.brt.it` con `brtCode=`; no los de myBRT ni 19 dígitos.

Peek lee BRT sin navegador, ubica cada lectura en el mapa y muestra el peso, omitiendo nombres, direcciones, referencia, bultos, volumen y punto de recogida. Con 14 cifras puede mostrar la fecha prevista de DPD o [Chronopost](carrier:chronopost) mientras siga vigente. Lee las horas en horario italiano y suma los escaneos previos cuando [Chronopost](carrier:chronopost) o DPD llevan el paquete a Italia.

Mientras tienes el paquete abierto, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día de reparto, hasta cada 2 minutos desde las 6:00 local (o 30 minutos antes de una franja de DPD o Chronopost) mientras BRT o DPD respondan directamente (cada 5 minutos si la consulta abre un navegador). Si no, cada hora, igual que tras dos días sin escaneos o 12 horas en «Etiqueta creada» o «Admitido por el transportista». Cuando solo responden servicios de seguimiento, consulta cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de entrega mientras tienes el paquete abierto o las notificaciones activadas. Avisos: «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre el seguimiento de BRT

### ¿Dónde está mi paquete de BRT ahora mismo?

Lo indica la última línea: `ARRIVED AT DEPOT` señala la delegación en *Filiale* y `FOR DELIVERY` que va en reparto. Sin lecturas aún, Peek muestra «Esperando a BRT» (o «Esperando el primer escaneo de BRT» en un código `EOB` sin transportista fijado).

### ¿Son lo mismo BRT, DPD y SEUR?

Pertenecen al mismo grupo Geopost: BRT gestiona la red italiana, DPD gran parte de Europa y [SEUR](carrier:seur) es la marca del grupo en España.

### ¿Cuánto tarda un envío con BRT?

Como orientación, BRT indica 24–48 horas en Italia y 48–72 horas por carretera a la mayoría de países europeos. Su *Carta dei servizi* (julio de 2026, bultos hasta 31,5 kg) fija como meta entregar el 96 % en 1–2 días laborables en Italia y en 2–10 días laborables en el extranjero.

### ¿Por qué Peek no pasa solo de BRT a SEUR en España?

Porque el traspaso automático desde BRT solo enlaza con filiales de marca DPD. Con destino a España, Peek sigue leyendo BRT; si recibes el código de 14 cifras del socio, puedes añadirlo aparte en [SEUR](carrier:seur).

:::sources
- [BRT: Inicio (EN)](https://www.brt.it/en/) – buscador por BRTcode
- [BRT: FAQ (EN)](https://www.brt.it/en/customer-service/faq/) – BRTcode, ausencias, almacenaje, plazos, daños y 20 kg
- [BRT: Codes and Support (EN)](https://www.brt.it/en/customer-service/codes-and-support/) – dónde aparece el BRTcode
- [BRT: Alerta phishing (EN)](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) – aviso del 16 de abril de 2026
- [BRT: Código de seguridad (IT)](https://www.brt.it/it/faq/cose-il-codice-di-sicurezza/) – código de 3 cifras
- [BRT: Rintraccia spedizione (EN)](https://vas.brt.it/vas/sped_numspe_par.htm?lang=en) – formatos, 12 y 2 meses e idiomas
- [BRT: Ayuda del detalle (EN)](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) – delegaciones, almacenaje, gastos y DPD
- [BRT: BRT-fermopoint (EN)](https://www.brt.it/en/brt-fermopoint/) – tiendas, fines de semana y 10 días
- [BRT: Locker (EN)](https://www.brt.it/en/network/locker/) – taquillas 24/7, QR, PIN y 20 kg
- [BRT: Recogida en delegación (EN)](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – autorizado y documentos
- [BRT: Support (EN)](https://services.brt.it/en/support) – formularios sin teléfono
- [BRT: Buscador de delegaciones (EN)](https://services.brt.it/en/customer-service/search-for-a-branch) – delegaciones BRT
- [BRT: Carta dei servizi, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/11/261105-Carta-dei-Servizi.pdf) – julio 2026: 31,5 kg, intentos, 10 días, 8/7 días, 96 %, 800 278 708, assistenzaclienti@brt.it y 45 días
- [BRT: Carta dei servizi (IT)](https://www.brt.it/it/carta-dei-servizi/) – form-agcom, conciliación y AGCOM
- [BRT: Modulo di Reclamo, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – reclamación de destinatario o remitente
- [BRT: Modulo di Conciliazione, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – conciliazioni@brt.it y cuota
- [BRT: Condizioni generali, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – días acordados con el remitente
- [DPD: Inicio (EN)](https://www.dpd.com/en/) – marcas de Geopost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos, estados y frecuencias de Peek
:::
