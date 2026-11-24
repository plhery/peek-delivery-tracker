---
title: Rastreio Correos de México: códigos S10 MX e reclamações
description: Rastreia uma encomenda Correos de México (SEPOMEX / Mexpost): códigos S10 MX, pesquisa por ano, estados espanhóis, alfândega e reclamações.
slug: rastreio-correos-de-mexico
published: 2026-11-24
updated: 2026-11-24
---

A Correos de México (*Servicio Postal Mexicano* / *SEPOMEX*) é o serviço postal nacional do México, responsável pelo correio registado interno (*Registrados*), pelas encomendas expresso *Mexpost* e pelos envios postais internacionais UPU tanto à saída do México como à entrada vindos de operadores postais de todo o mundo. Podes consultar um envio na [página de rastreio da Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) (introduzindo o número de rastreio e o ano de expedição, *Ejercicio*) ou colar o teu código na caixa acima.

## Números de rastreio Correos de México: formatos UPU S10

Procura o código no recibo do balcão postal mexicano, na guia Mexpost ou no e-mail de envio do vendedor estrangeiro. A Correos de México rastreia códigos UPU S10 de 13 caracteres — tanto códigos expedidos do México terminados em `MX` como envios postais estrangeiros S10 destinados ao México (como `...US`, `...CN`, `...ES` ou `...JP`):

| Formato (inventado) | O que é |
| --- | --- |
| `RR482917367MX` | Correio registado (*Correo Registrado*) enviado a partir do México (UPU S10 terminado em `MX`). |
| `MP482917367MX` | Encomenda postal interna ou expresso *Mexpost* (UPU S10 terminado em `MX`). |
| `EE482917367MX` | Correio expresso internacional EMS a partir do México (UPU S10 terminado em `MX`). |

**Seletor de ano (*Ejercicio*) na SEPOMEX.** Ao contrário da maioria dos rastreadores, o [portal Seguimiento da Correos de México](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) organiza os registos por ano civil (`2026`, `2025`, `2024`, etc.). Se uma encomenda expedida no final de dezembro continuar a viajar em janeiro, pode ser necessário consultar ambos os anos no site para ver o percurso completo. Para transportadoras privadas mexicanas como [Estafeta](carrier:estafeta), [J&T Express Mexico](carrier:j-and-t-mx) ou [Paquetexpress](carrier:paquetexpress), vê [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Correos de México

A Correos de México apresenta descrições em espanhol junto à estação postal mexicana ou ao centro de permuta estrangeiro e respetiva data e hora:

| A Correos de México indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Depósito en Oficina de Correos` / `Deposito del Cliente en ...` | Aceite ao balcão de uma estação postal mexicana ou depositada no estrangeiro pelos correios de origem. | «Aceite pela transportadora» |
| `En tránsito hacia destino`, `Recepción en Oficina de Correos`, `En zona de intercambio int. hacia el país destino, previo Aduana`, `Pieza depositada en valija y enviada a oficina del País destino` | Em circulação entre estações de distribuição mexicanas ou expedida em mala postal internacional. | «Em trânsito» |
| `Arribo a Zona Intercambio México, en espera de ingreso a la Aduana`, `Inicio de la revisión aduanal en país destino` | Chegada ao centro de permuta internacional à espera ou em curso de inspeção aduaneira. | «Na alfândega» |
| `Revisión aduanal concluida` | Inspeção aduaneira concluída; envio libertado para seguir na rede postal. | «Em trânsito» |
| `Con mensajero para entrega` | Em distribuição hoje com o carteiro local. | «Em distribuição» |
| `Puesta en ventanilla` | Retida ao balcão da estação postal indicada para levantamento pelo destinatário. | «Pronta para levantamento» |
| `Pieza devuelta ...` (ex.: `por domicilio destino incorrecto`) | Em devolução ao remetente com o motivo assinalado. | «Devolvida ao remetente» |
| `Entrega` / `Entrega Internacional` | Entregue no México ou confirmada entregue no estrangeiro pelos correios de destino. | «Entregue» |

## Levantamento ao balcão (*Puesta en ventanilla*), alfândega e reclamações

Segundo o [Servicio Postal Mexicano em gob.mx](https://www.gob.mx/correosdemexico) e a página oficial [Reclamo de un Envío (Reclamação de envio)](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **Levantamento ao balcão (*Puesta en ventanilla*):** quando aparece `Puesta en ventanilla`, a encomenda aguarda na estação postal indicada na coluna *Origen*.
- **Alfândega à chegada:** encomendas postais recebidas no México passam por inspeção aduaneira mexicana (`Inicio de la revisión aduanal...`). Os tempos variam consoante a época e a avaliação aduaneira; vê [encomendas retidas na alfândega](guide:customs).
- **Apresentar reclamação formal (*Reclamo de un Envío*):** os pedidos de averiguação e indemnização aplicam-se **apenas aos serviços Registados (*Registrados*) e *Mexpost*** (não ao correio simples sem rastreio). Podes apresentar reclamação quando um envio ultrapassa o prazo ou chega danificado — presencialmente em qualquer estação da Correos de México ou online. O prazo máximo de resposta da SEPOMEX para reclamações Registrados e Mexpost é de **30 dias úteis** (*30 días hábiles*).

## Encomenda Correos de México parada, sem novidades ou devolvida

- **Longas pausas na alfândega ou em trânsito internacional:** envios postais à entrada ou saída do México ficam frequentemente dias ou semanas sem registos em `Arribo a Zona Intercambio México` ou entre `Pieza depositada en valija` e a chegada ao destino. Vê [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Envios devolvidos (`Pieza devuelta`):** assim que a SEPOMEX regista `Pieza devuelta` (por exemplo morada incorreta ou não levantada ao balcão), os registos seguintes `Puesta en ventanilla` e `Entrega` referem-se à devolução ao remetente original.
- Em compras online, contacta o vendedor em paralelo com a reclamação. Vê [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos Correos de México

Canais oficiais publicados em [gob.mx/correosdemexico](https://www.gob.mx/correosdemexico), [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) e [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio):

- **E-mail oficial de contacto:** `contacto@correosdemexico.gob.mx`.
- **Reclamações e pedidos:** [Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) ou presencialmente na tua estação postal mexicana.
- **Litígios de consumo no México:** para litígios comerciais por resolver, os consumidores podem recorrer à [PROFECO](https://www.gob.mx/profeco).

> Desconfia de SMS ou mensagens de WhatsApp falsas que se façam passar pela Correos de México / SEPOMEX a pedir um pagamento online por cartão para «corrigir o código postal» ou «libertar uma encomenda». Verifica sempre o código S10 diretamente em [correosdemexico.gob.mx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) ou escreve para `contacto@correosdemexico.gob.mx`. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda Correos de México com o Peek

[O Peek](/) reconhece diretamente como Correos de México links colados de `correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx?guia=`. Quando colas um código UPU S10 de 13 caracteres terminado em `MX` (`RR482917367MX`, `MP482917367MX` ou `EE482917367MX`, inventados), o Peek pergunta primeiro à Correos de México e mostra «Correos de México tem esta encomenda.» assim que a confirma. Um envio expedido no estrangeiro mantém o código dos correios de origem (`US`, `CN`, etc.), que o Peek consulta; para o acompanhar na Correos de México, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda e seleciona Correos de México (pesquisar «SEPOMEX», «Mexpost» ou «Mexico Post» também a encontra).

O Peek lê diretamente o rastreio da Correos de México, percorrendo também anos anteriores disponibilizados pelo formulário para que uma viagem que atravesse a passagem de ano fique completa, e mostra a etapa («Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Devolvida ao remetente», «Entregue»), a descrição em espanhol, a estação postal ou centro de permuta, a estação onde a encomenda aguarda quando mostra `Puesta en ventanilla` e a hora de entrega quando existe. Após `Pieza devuelta`, o Peek mantém os registos posteriores de balcão e entrega em «Devolvida ao remetente», pois pertencem ao regresso ao remetente e não a uma entrega para ti. A Correos de México dá horas sem fuso: o Peek data cada registo no fuso horário da respetiva estação (Tijuana num registo em Tijuana, Cidade do México para a Cidade do México) e apresenta-o na hora do teu dispositivo. O Peek omite o nome de quem assinou, a hora da consulta e os avisos da página; o rastreio não dá data estimada de entrega, peso, nome do serviço nem foto de prova de entrega.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Correos de México mostra `Con mensajero para entrega`, de 2 em 2 minutos a partir das 6h na hora local da estação do último registo, enquanto o rastreio da Correos de México responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Correos de México não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Correos de México.», com o link «Pede ajuda a Correos de México» para a página *Reclamo de un Envío*. Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Correos de México.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode desalfandegar encomendas nem apresentar um *Reclamo de un Envío* junto da SEPOMEX em teu nome.

## Perguntas frequentes sobre Correos de México

### Que números de rastreio funcionam na Correos de México?

A Correos de México rastreia códigos UPU S10 dos correios de 13 caracteres: 2 letras + 9 dígitos + 2 letras de país (por exemplo `RR482917367MX` ou `MP482917367MX`, inventados), incluindo envios S10 estrangeiros a entrar no México.

### O que significa `Puesta en ventanilla`?

Significa que a tua encomenda está guardada ao balcão da estação postal local indicada na coluna *Origen* do registo para levantamento presencial.

### Porque é que a Correos de México não encontrou o meu número de rastreio?

Em [Seguimiento.aspx](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx), confirma que selecionaste o ano de expedição correto (*Ejercicio*) — sobretudo para envios feitos no fim do ano anterior — e tem em conta que o correio simples sem registo não tem rastreio.

### Como apresento uma reclamação à Correos de México?

Para envios Registados (*Registrados*) e *Mexpost* atrasados ou danificados, submete um *Reclamo de un Envío* numa estação postal ou em [gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) (prazo de resposta de 30 dias úteis) ou envia e-mail para `contacto@correosdemexico.gob.mx`.

:::sources
- [Correos de México (SEPOMEX): portal gob.mx](https://www.gob.mx/correosdemexico) – portal oficial Servicio Postal Mexicano e `contacto@correosdemexico.gob.mx`
- [Correos de México: Seguimiento de Envíos](https://www.correosdemexico.gob.mx/SSLServicios/SeguimientoEnvio/Seguimiento.aspx) – consulta S10 por ano (*Ejercicio*) e `contacto@correosdemexico.gob.mx`
- [Correos de México: Reclamo de un Envío](https://www.gob.mx/correosdemexico/es/acciones-y-programas/reclamo-de-un-envio) – elegibilidade Registrados & Mexpost, submissão presencial/online e resposta em 30 dias úteis (URL de ajuda ligado pelo Peek)
- [PROFECO](https://www.gob.mx/profeco) – Procuradoria Federal do Consumidor do México
- [Universal Parcel Scraper: correos-de-mexico](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correos-de-mexico) – formato S10, regra de link `Seguimiento.aspx?guia=`, pesquisa multi-ano, mapa de estados espanhóis, ponto de levantamento `Puesta en ventanilla`, gestão de devolução, fuso horário da estação e URL de ajuda
:::
