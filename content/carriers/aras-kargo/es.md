---
title: Seguimiento Aras Kargo: números y estados en español
description: Sigue un paquete de Aras Kargo: cómo es su número de 13 dígitos, qué significan estados turcos como TESLİMAT ŞUBESİNDE y cómo lo sigue Peek.
slug: seguimiento-aras-kargo
published: 2026-11-22
updated: 2026-11-22
---

Aras Kargo (también escrito Aras Cargo) es una empresa de mensajería de Turquía. Su rastreador, tal como lo lee Peek, muestra cada paso en turco. La tienda o el remitente te facilita el número de seguimiento: pégalo en el campo de arriba. El rastreador propio de Aras Kargo está en kargotakip.araskargo.com.tr. Las tablas de abajo explican sus estados en turco en español.

## Número de seguimiento de Aras Kargo: cómo es

El código de Peek reconoce los números de Aras Kargo como códigos de 13 dígitos. Los que vincula automáticamente a Aras empiezan por 60 o 65. Ejemplos inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `6048291736542` | 13 dígitos que empiezan por 60: el formato que Peek asocia a Aras Kargo. Otros transportistas como [Delhivery](carrier:delhivery), Econt, Ozon Logistics, Paquetexpress, Correos Chile, Yunda y Korea Post también usan 13 dígitos. |
| `6548291736542` | 13 dígitos que empiezan por 65: lo mismo. |
| `1482917365482` | 13 dígitos con otro inicio: Peek no los vincula automáticamente a Aras, aunque el rastreador de Aras acepta 13 dígitos. Si la tienda indica Aras Kargo, elige Aras Kargo manualmente (ver abajo). |
| `604829173654` | Solo 12 dígitos. Si eliges Aras Kargo para ese código, Peek indica que «no emite números como este». Comprueba si falta algún dígito. |

Los espacios no importan al pegar un número en Peek. También funciona un enlace al rastreador de Aras con el número incluido (kargotakip.araskargo.com.tr con `code=` y el código).

**Dónde encontrarlo.** En el correo de envío de la tienda o en la página del pedido. Si no lo encuentras, consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de Aras Kargo en español

El rastreador de Aras muestra el estado actual (`Son Durum`), un día previsto de entrega y los movimientos de vehículos (`Araç Hareketleri`). Cada movimiento incluye fecha y hora (`İşlem Tarihi`), provincia (`İl`), unidad (`Birim`, sucursal o centro de transferencia) y descripción (`İşlem Açıklaması`). Cuando ocurre, también enumera los días sin entrega y las redirecciones a otra sucursal.

**Estado actual.** Aparece en la parte superior con uno de estos textos:

| Aras indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | En la sucursal de origen que admitió el paquete. | «Admitido por el transportista» |
| `YOLDA` | En camino entre centros de transferencia de Aras. | «En tránsito» |
| `TESLİMAT ŞUBESİNDE` | En la sucursal de reparto que lleva el paquete a tu dirección. Se mantiene igual tras un intento fallido. | «En tránsito» |
| `TESLİM EDİLDİ` | Entregado. Aras da fecha y hora (si falta la hora, el estado sigue en «Entregado» sin evento de entrega). | «Entregado» |

**Movimientos de vehículos.** Cada escaneo al cargar, descargar o embolsar:

| Aras indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Cargado en un vehículo en una sucursal. | «En tránsito» |
| `ŞUBEDE ARAÇ İNDİRME` | Descargado de un vehículo en una sucursal. | «En tránsito» |
| `AKTARMADA HAT ARACI YÜKLEME` | Cargado en un camión de larga distancia en un centro de transferencia. | «En tránsito» |
| `AKTARMADA HAT ARACI İNDİRME` | Descargado de un camión de larga distancia en un centro de transferencia. | «En tránsito» |
| `AKTARMADA SUBE ARACI YUKLEME` | Cargado en un vehículo de sucursal en un centro de transferencia. | «En tránsito» |
| `AKTARMADA SUBE ARACI INDIRME` | Descargado de un vehículo de sucursal en un centro de transferencia. | «En tránsito» |
| `AKTARMADA TORBA DOLDURMA` | Introducido en una saca con otros paquetes en un centro de transferencia. | «En tránsito» |

El rastreador escribe las dos líneas con `SUBE` sin caracteres turcos, tal como aparecen arriba.

**Entrega no realizada** (`Teslim Edilememe Nedeni`, motivo). Una línea por cada día afectado:

| Aras indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | El paquete no pudo entregarse ese día. | «Intento de entrega» |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Sin entrega por día festivo. | «Intento de entrega» |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | El repartidor pasó y dejó un aviso. | «Intento de entrega» |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Tu dirección está fuera del área de reparto de la sucursal. | «Intento de entrega» |

**Redirección.** `YÖNLENDİRİLDİ` significa que el paquete se envió a otra sucursal. Aras indica el motivo y Peek lo añade entre paréntesis tras la palabra. Peek muestra «En tránsito».

Estos dos últimos tipos de línea solo llevan fecha, sin hora. El rastreador de Aras puede mostrar además una nota libre del repartidor junto a una entrega fallida, que Peek omite. Más sobre las fases en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Entrega de Aras Kargo en Turquía

El paquete viaja desde la sucursal de Aras que lo recibe (`ÇIKIŞ ŞUBESİNDE`), pasando por centros de transferencia (líneas con `AKTARMADA`), hasta la sucursal de entrega (`TESLİMAT ŞUBESİNDE`), encargada de llevarlo a tu dirección.

**Día previsto de entrega.** El rastreador de Aras ofrece una fecha. Peek la muestra como fecha estimada mientras el paquete está en movimiento y ese día no queda por detrás del último escaneo. La retira tras una entrega fallida o cuando ya pasó el día.

**Fuera del área de reparto.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` indica que la sucursal no reparte en tu dirección: pregunta a la tienda cuál es el siguiente paso.

**Lo que esta página no puede indicar.** Peek no pudo leer las páginas de ayuda del sitio web de Aras Kargo, por lo que esta página no incluye plazos de entrega, número de intentos, días de custodia ni normas de recogida. El rastreador de Aras, tal como lo lee Peek, no indica punto de recogida, peso ni nombre de servicio. Ante una ausencia, consulta [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).

## Seguimiento de Aras Kargo parado, perdido o devuelto

**Sin resultados.** Cuando Aras no conoce el número, su rastreador responde `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (no se encontraron resultados). Puede que la tienda aún no haya entregado el paquete. Peek sigue consultando: un envío guardado con Aras Kargo muestra «Esperando a Aras Kargo». Revisa los 13 dígitos con la tienda y, si no aparece nada tras unos días, pregúntale si entregó realmente el paquete a Aras.

**Atascado en `TESLİMAT ŞUBESİNDE`.** La sucursal de entrega tiene el paquete y ese estado no cambia tras un intento fallido. Si aparece una línea de motivo para un día (tabla anterior), la entrega no se completó y el motivo explica por qué. Si no, sigue esperando reparto. Como el rastreador de Aras no tiene un paso de en reparto, Peek muestra «En tránsito» hasta la entrega o un intento fallido.

**Días sin actualizaciones.** Tras cuatro días sin nuevos escaneos en un envío en tránsito, Peek muestra en tu lista el día del último escaneo («Sin novedades desde el …») y en la ficha «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Aras Kargo.», con el enlace «Pide ayuda a Aras Kargo» a su página de contacto solo en ese aviso. Es frecuente que no haya movimientos durante el fin de semana. Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Marcado como entregado pero no llegó.** Pregunta a vecinos y convivientes. Peek muestra el nombre enmascarado de quien recibió el paquete (`Teslim Alan`) tal como aparece en el resumen de Aras; también puedes revisar el rastreador de Aras directamente. Después contacta con la tienda y con Aras. Más en [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Peek aún no tiene mapeado un texto específico de devolución de Aras. Como la tienda contrató el envío con Aras, pídele primero un reembolso o un nuevo envío. Ver [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Aras Kargo

Peek no pudo leer el sitio web de Aras Kargo, así que esta página no lista teléfono ni correo electrónico: Peek solo publica datos de contacto leídos directamente en las páginas oficiales del transportista.

Para un pedido de tienda, escribe primero a la tienda que contrató el envío. En Peek, la ubicación de cada movimiento indica la sucursal o el centro de transferencia, y la ubicación de entrega señala la sucursal que entregó: facilita ese nombre al consultar.

## Seguir un paquete de Aras Kargo con Peek

[Peek](/) no puede confirmar Aras Kargo solo por el formato del código porque otros transportistas también usan 13 dígitos. Pégalo igualmente: Peek sigue más de 4500+ transportistas.

- **13 dígitos que empiezan por 60 o 65.** Peek pregunta a la vez a Aras Kargo y a los demás transportistas compatibles con ese patrón de 13 dígitos (como [Delhivery](carrier:delhivery), Econt, Ozon Logistics y Paquetexpress); si buscas desde Turquía, consulta primero a Aras Kargo. Mientras comprueba, ves «Comprobando servicios de seguimiento…». Si solo Aras Kargo confirma el paquete, Peek muestra «Aras Kargo tiene este paquete.» y lo guarda allí; si lo reconocen varios, Peek te pregunta cuál elegir.
- **Otros 13 dígitos o aún sin resultados.** Cambia de transportista y elige Aras Kargo (escribe Aras) para que Peek consulte directamente el rastreador de Aras. En un paquete ya añadido, puedes cambiar de transportista desde su ficha.
- **Enlace al rastreador de Aras.** Peek extrae el código y señala Aras Kargo al instante («Transportista detectado»).

Añadido sin transportista, un paquete muestra «Todavía no hay historial de seguimiento» mientras Peek sigue consultando transportistas y servicios de seguimiento. Si un servicio de seguimiento identifica a Aras Kargo como único transportista con pasos fechados, Peek lo archiva bajo Aras.

**Qué muestra Peek.** El estado actual, cada movimiento con fecha, hora y lugar (sucursal o centro de transferencia y provincia), los días sin entrega, las redirecciones, el día previsto de entrega, la hora y sucursal de entrega, y los nombres enmascarados del destinatario y de quien recibió el paquete (`Teslim Alan`) según el resumen. Omite el nombre completo oculto en la página, el remitente, el código de entrega, la verificación de identidad, datos de pago y factura, volumen y número de bultos, barra de progreso, notas libres y pestaña de SMS. Peek interpreta las horas de Aras en hora de Turquía y las convierte a tu zona horaria (el historial termina con «Horas en tu zona horaria:» seguido de su nombre). El enlace «Abrir la web de Aras Kargo» abre el código directamente en el rastreador de Aras.

Cuando el rastreador de Aras no responde, Peek consulta servicios de seguimiento y muestra el historial que devuelven, con sus propias palabras.

Mientras tienes el paquete abierto en pantalla — o el día de la entrega con notificaciones activadas — Peek consulta directamente a Aras Kargo hasta cada 10 minutos (sin tramo de 2 minutos, porque Aras no registra un estado de en reparto). Fuera de esos momentos, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Solo a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de entrega si tienes el paquete abierto en pantalla o las notificaciones activadas. Elige «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con Aras Kargo ni con la tienda por ti.

## Preguntas sobre el seguimiento de Aras Kargo

### ¿Qué significa TESLİMAT ŞUBESİNDE en Aras Kargo?

Significa «en la sucursal de entrega»: la sucursal de Aras encargada de llevar el paquete a tu dirección lo tiene allí. El estado no cambia tras un intento fallido: revisa si aparece una línea de motivo, que Peek muestra como «Intento de entrega».

### ¿Qué significa YOLDA en Aras Kargo?

Significa «en camino»: el paquete viaja entre centros de transferencia de Aras rumbo a la sucursal de entrega. Peek muestra «En tránsito».

### ¿Cómo es un número de seguimiento de Aras Kargo?

Tiene 13 dígitos en el código de Peek. Los que empiezan por 60 o 65, como `6048291736542`, se asocian a Aras Kargo. Para otros números de 13 dígitos, selecciona Aras Kargo manualmente.

### ¿Por qué Aras Kargo no encuentra mi número de seguimiento?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` indica que Aras aún no tiene resultados para ese número. Quizá la tienda no ha entregado el paquete todavía. Revisa los 13 dígitos, espera un día y consulta a la tienda.

### ¿Cómo contacto con Aras Kargo?

Pregunta primero a la tienda que contrató el envío. Como Peek no pudo leer el sitio web de Aras Kargo, esta página no incluye teléfono ni correo electrónico.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – nombres, código de 13 dígitos y prefijos 60 y 65, rastreador y enlace, secciones y textos turcos con sus etapas, aviso sin resultados, día previsto de entrega, hora de Turquía, campos leídos y omitidos por Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros transportistas que comparten el formato, reconocimiento automático y respaldo con servicios de seguimiento
:::
