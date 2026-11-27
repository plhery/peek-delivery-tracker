---
title: Rastreamento Correos de México: códigos MX, SEPOMEX e ajuda
description: Rastreie encomendas Correos de México (SEPOMEX / Mexpost): códigos S10 MX, busca por ano, status em espanhol, alfândega, prazo de 30 dias e e-mail.
slug: correos-de-mexico-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Correos de México (*Servicio Postal Mexicano* / *SEPOMEX*) é o serviço postal nacional do México, operando correspondências registradas domésticas (*Registrados*), encomendas expressas *Mexpost* e envios postais internacionais UPU tanto saindo do México quanto chegando de correios do mundo todo. Você pode acompanhar uma remessa na [página de rastreamento da Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) (informando o número de rastreamento e o ano de postagem, *Ejercicio*) ou colar seu código na caixa acima.

## Códigos de rastreamento Correos de México: formatos UPU S10

Verifique o recibo da agência postal mexicana, a guia Mexpost ou o e-mail de envio do vendedor internacional. A Correos de México rastreia códigos UPU S10 de 13 caracteres — tanto códigos de saída terminados em `MX` quanto envios postais estrangeiros S10 com destino ao México (como `...US`, `...CN`, `...ES` ou `...JP`):

| Formato (fictício) | O que é |
| --- | --- |
| `RR482917367MX` | Correspondência registrada (*Correo Registrado*) enviada do México (UPU S10 terminado em `MX`). |
| `MP482917367MX` | Encomenda doméstica ou expressa *Mexpost* (UPU S10 terminado em `MX`). |
| `EE482917367MX` | Envio expresso internacional EMS do México (UPU S10 terminado em `MX`). |

**Seletor de ano (*Ejercicio*) na SEPOMEX.** Ao contrário da maioria dos rastreadores, o [portal Seguimiento da Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) arquiva leituras por ano civil (`2026`, `2025`, `2024` etc.). Se um pacote postado no final de dezembro continuar circulando em janeiro, pode ser necessário consultar ambos os anos no site para ver o trajeto completo. Para transportadoras privadas do México como [Estafeta](carrier:estafeta), [J&T Express Mexico](carrier:j-and-t-mx) ou [Paquetexpress](carrier:paquetexpress), veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Correos de México

A Correos de México exibe descrições em espanhol acompanhadas da agência mexicana ou centro de permuta internacional e data/hora:

| A Correos de México informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | Aceito em uma agência dos correios mexicanos ou postado no exterior pelo correio de origem. | "Recebida pela transportadora" |
| `En tránsito hacia destino`, `Recepción en Oficina de Correos`, `En zona de intercambio int. hacia el país destino, previo Aduana`, `Pieza depositada en valija y enviada a oficina del País destino` | Em deslocamento entre unidades mexicanas ou ensacada e despachada internacionalmente. | "Em trânsito" |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`, `Inicio de la revisión aduanal en país destino` | Chegou ao centro de intercâmbio internacional aguardando ou passando por inspeção aduaneira. | "Na alfândega" |
| `Revisión aduanal concluida` | Inspeção aduaneira encerrada; envio liberado de volta ao fluxo postal. | "Em trânsito" |
| `Con mensajero para entrega` | Saiu com o carteiro local para entrega hoje. | "Saiu para entrega" |
| `Puesta en ventanilla` | Aguardando retirada pelo destinatário no balcão da agência postal indicada. | "Pronta para retirada" |
| `Pieza devuelta ...` (ex.: `por domicilio destino incorrecto`) | Devolvida em direção ao remetente com o motivo informado. | "Devolvida ao remetente" |
| `Entrega` / `Entrega Internacional` | Entregue no México ou confirmada entregue no exterior pelo correio de destino. | "Entregue" |

## Retirada no balcão (*Puesta en ventanilla*), alfândega e reclamações

De acordo com o [Servicio Postal Mexicano em gob.mx](https://www.gob.mx/correosdemexico) e a página oficial [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Retirada no balcão (*Puesta en ventanilla*):** quando o rastreamento mostra `Puesta en ventanilla`, sua encomenda está aguardando retirada na agência postal indicada na coluna *Origen*.
- **Alfândega internacional de entrada:** encomendas postais entrando no México passam pela fiscalização aduaneira mexicana (`Inicio de la revisión aduanal...`). Os prazos variam conforme a época e avaliação aduaneira; veja [encomendas retidas na alfândega](guide:customs).
- **Abrir reclamação formal (*Reclamo de un Envío*):** reclamações e pedidos formais de indenização valem **apenas para serviços Registrados (*Registrados*) e *Mexpost*** (não abrangem correspondência comum sem rastreio). Você pode registrar reclamação quando um envio ultrapassa o prazo ou chega em más condições — presencialmente em qualquer agência da Correos de México ou online. O prazo máximo de resposta da SEPOMEX para Registrados e Mexpost é de **30 dias úteis** (*30 días hábiles*).

## Encomenda Correos de México parada, sem atualização ou devolvida

- **Pausas longas na alfândega ou trânsito internacional:** envios postais internacionais que entram ou saem do México frequentemente ficam dias ou semanas parados em `Arribo a Zona Intercambio México` ou entre `Pieza depositada en valija` e a chegada ao destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Itens devolvidos (`Pieza devuelta`):** assim que a SEPOMEX marca `Pieza devuelta` (por exemplo por endereço incorreto ou falta de retirada no balcão), até mesmo leituras posteriores de `Puesta en ventanilla` e `Entrega` referem-se à devolução ao remetente original.
- Em compras online, contate o vendedor além de abrir a reclamação. Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Correos de México

Canais oficiais publicados em [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico), [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) e [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **E-mail oficial de consultas:** `contacto@correosdemexico.gob.mx`.
- **Reclamações e consultas:** [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) ou presencialmente na agência postal mexicana mais próxima.
- **Disputas de consumo no México:** para disputas não resolvidas com lojas online ou serviços, consumidores podem acionar a [PROFECO](https://www.gob.mx/profeco).

> Atenção a mensagens falsas por SMS ou WhatsApp se passando pela Correos de México / SEPOMEX e cobrando pagamento online com cartão para "corrigir código postal" ou "liberar encomenda". Verifique sempre seu código S10 diretamente em [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) ou escreva para `contacto@correosdemexico.gob.mx`. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda Correos de México com o Peek

O [Peek](/) reconhece diretamente links colados de `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=` como Correos de México. Ao colar um código UPU S10 de 13 caracteres terminado em `MX` (`RR482917367MX`, `MP482917367MX` ou `EE482917367MX`, fictícios), o Peek consulta primeiro a Correos de México e exibe "Correos de México está com esta encomenda." quando ela confirma. Itens postados no exterior mantêm o código do correio de origem (`US`, `CN` etc.) e o Peek consulta esse correio; para acompanhar pela Correos de México, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote e escolha Correos de México (buscar "SEPOMEX", "Mexpost" ou "Mexico Post" também encontra).

O Peek lê diretamente o rastreador da Correos de México, percorrendo os anos anteriores oferecidos pelo formulário para manter inteiro um trajeto que vire o ano, e exibe a etapa ("Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Pronta para retirada", "Devolvida ao remetente", "Entregue"), o texto em espanhol, a agência ou centro de permuta, a agência onde aguarda retirada quando surge `Puesta en ventanilla` e o horário de entrega quando disponível. Após `Pieza devuelta`, o Peek mantém leituras posteriores de balcão e entrega sob "Devolvida ao remetente", pois se tratam da devolução ao remetente e não da entrega a você. Como a Correos de México informa horários sem fuso, o Peek aplica o fuso horário da agência (Tijuana em Tijuana, Cidade do México na Cidade do México) e exibe no relógio do seu dispositivo. O Peek omite nome de quem assinou, hora da consulta e avisos da página; o rastreador não informa data estimada, peso, nome do serviço nem foto POD.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a Correos de México mostra `Con mensajero para entrega`, a cada 2 minutos a partir das 6h no relógio da agência da última leitura enquanto o rastreador da própria Correos de México responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da Correos de México não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Correos de México.", com o link "Pedir ajuda a Correos de México" para a página *Reclamo de un Envío*. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Correos de México."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek monitora apenas atualizações de rastreamento e não pode liberar alfândega nem protocolar um *Reclamo de un Envío* na SEPOMEX em seu nome.

## Perguntas sobre o rastreamento da Correos de México

### Quais números de rastreamento funcionam na Correos de México?

A Correos de México rastreia códigos postais UPU S10 de 13 caracteres: 2 letras + 9 dígitos + 2 letras do país (por exemplo `RR482917367MX` ou `MP482917367MX`, fictícios), incluindo envios estrangeiros S10 entrando no México.

### O que significa `Puesta en ventanilla`?

Significa que sua encomenda está guardada no balcão da agência postal indicada na coluna *Origen* da leitura, aguardando retirada presencial.

### Por que a Correos de México não encontrou meu código de rastreamento?

Em [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx), verifique se selecionou o ano correto de postagem (*Ejercicio*) — especialmente em envios do fim do ano anterior — e lembre que correspondências comuns sem registro não têm rastreamento.

### Como abro uma reclamação na Correos de México?

Para envios Registrados (*Registrados*) e *Mexpost* atrasados ou danificados, registre um *Reclamo de un Envío* em uma agência postal ou em [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) (prazo de resposta de 30 dias úteis) ou envie e-mail para `contacto@correosdemexico.gob.mx`.

:::sources
- [Correos de México (SEPOMEX): Portal gob.mx](https://www.gob.mx/correosdemexico) – portal oficial do Servicio Postal Mexicano e `contacto@correosdemexico.gob.mx`
- [Correos de México: Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – consulta S10 por ano (*Ejercicio*) e `contacto@correosdemexico.gob.mx`
- [Correos de México: Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – elegibilidade para Registrados e Mexpost, protocolo presencial/online e prazo de resposta de 30 dias úteis (URL de ajuda vinculada pelo Peek)
- [PROFECO](https://www.gob.mx/profeco) – Procuradoria Federal do Consumidor do México
- [Universal Parcel Scraper: correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – formato S10, regra de link `Seguimiento.aspx?guia=`, consulta multi-ano, mapa de status em espanhol, ponto de retirada `Puesta en ventanilla`, tratamento de devolução, fuso por agência e URL de ajuda
:::
