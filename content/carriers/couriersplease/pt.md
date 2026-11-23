---
title: Seguimento CouriersPlease: números, estados e ajuda
description: Acompanhe uma encomenda CouriersPlease na Austrália: formato do número de envio, estados, pontos HUBBED, nova entrega, Delivery Choices e reclamações.
slug: seguimento-couriersplease
published: 2026-11-23
updated: 2026-11-23
---

A CouriersPlease (Couriers Please Pty Limited, parte do FMH Group) é uma transportadora australiana que entrega encomendas nas capitais e nos centros regionais da Austrália através de parceiros franchisados locais (*Franchise Partners*). Para acompanhar uma encomenda, introduza o número na [página de seguimento da CouriersPlease](https://www.couriersplease.com.au/tools-track) — que aceita até 10 números separados por vírgulas — ou cole-o na caixa acima. O número é fornecido pela loja ou pelo remetente que reservou o envio.

## Número de seguimento CouriersPlease: como é

A CouriersPlease chama-lhe *consignment number* (número de envio ou de seguimento). A página de nova entrega indica que pode começar por `CP` e ter até 20 caracteres. Para a CouriersPlease, o Peek aceita entre 8 e 30 letras e dígitos (com pelo menos um dígito), abrangendo envios, etiquetas de volumes, cupões pré-pagos e códigos SSCC emitidos por alguns remetentes. Nem todos os números da CouriersPlease começam por `CP`.

| Número (inventado) | O que é |
| --- | --- |
| `CPABC4829173640` | Número de envio típico da CouriersPlease: `CP`, letras e depois algarismos. |
| `CPA48291736405` | Outro formato da CouriersPlease com prefixo `CP`, um pouco mais curto. |
| `CP482917367AU` | Não é CouriersPlease: duas letras, nove algarismos e `AU` correspondem a um envio postal internacional, como os da [Australia Post](carrier:australia-post). |

**Onde encontrá-lo.** Consulte primeiro o e-mail de confirmação da encomenda enviado pela loja. Os e-mails oficiais da CouriersPlease vêm de `noreply@couriersplease.com.au` (apenas se o remetente tiver partilhado o seu e-mail) e os SMS aparecem com o remetente `Courierspls`. Se foi deixado um aviso em papel, este inclui um *redelivery card number*, também aceite na página de nova entrega. Veja mais dicas em [como encontrar o número de seguimento](guide:find-tracking-number).

**Número inválido ou sem dados?** Verifique possíveis gralhas e confirme com a loja se a encomenda seguiu mesmo pela CouriersPlease. Enquanto não existirem registos de leitura para o código, a página oficial apresenta o número como inválido ou inexistente.

## Estados de seguimento CouriersPlease explicados

No [guia de receção](https://www.couriersplease.com.au/faqs), a CouriersPlease refere cinco momentos principais: notificação de recolha, recolha no remetente, chegada ao depósito, saída para entrega e entrega ou encaminhamento para um ponto de recolha. No seguimento, os textos aparecem em inglês; eis os principais estados apresentados pela CouriersPlease e mapeados pelo [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), pela ordem habitual:

| Estado CouriersPlease | O que significa para si |
| --- | --- |
| `Shipment information received` | O remetente criou o envio. A CouriersPlease pode ainda não ter a encomenda em sua posse. |
| `Your parcel has been picked up` | A CouriersPlease recolheu a encomenda no remetente. |
| `Pickup attempt was unsuccessful. Awaiting rebook confirmation by sender` | A tentativa de recolha falhou; o vendedor tem de reagendar. |
| `Arrived at facility for processing` | Chegou a um centro de processamento da CouriersPlease. |
| `Parcel requires further processing` | A encomenda precisa de processamento adicional no centro; continua normalmente na rede. |
| `Processed at delivery centre` | Processada no centro de distribuição. |
| `In transit to delivery centre` | Em trânsito para um centro de distribuição (muitas vezes com as duas cidades entre parênteses). |
| `Arrived at delivery centre` | Chegou ao centro de distribuição; não precisa de fazer nada. |
| `In transit to regional delivery centre` | Entregue a um parceiro regional ou em trânsito para um centro regional. |
| `Onboard for delivery` | A bordo da viatura do motorista ou do Franchise Partner para entrega. |
| `Delivery rescheduled for next business day` | Regressou ao depósito sem entrega e foi reagendada para o dia útil seguinte. |
| `Delivery attempted – Unsafe to leave – Sent to a collection point` | Não estava ninguém nem havia local seguro; seguiu para um ponto de recolha. |
| `Delivery attempted – Signature required – Redirected to a collection point` | Era necessária assinatura e não estava ninguém; redirecionada para um ponto de recolha. |
| `Unable to deliver` | Entrega impossível; o caso requer análise do apoio ao cliente. |
| `Processing for collection at a nearby collection point` | Já está no ponto de recolha, mas ainda não pronta para levantamento. |
| `Pickup at Collection Point – Ready for collection` | Pronta para levantamento no ponto de recolha com o seu código PIN. |
| `Delivered with Authority to Leave (ATL)` | Deixada num local seguro na sua morada sem assinatura. |
| `Delivered` | Entregue na morada de destino. |
| `Parcel has been collected from` | Levantada no ponto de recolha indicado a seguir ao texto. |
| `Collection period expired – Return to sender in progress` | Prazo de levantamento expirado; devolução ao remetente em curso. |

`Shipment information received` é o estado que gera mais dúvidas: os dados do envio já foram criados, mas o volume pode continuar na loja. Se o estado não mudar, peça ao vendedor para confirmar a recolha.

Avisos como `Delivery rescheduled` ou as entradas `Delivery instructions –` (nova data, ponto de recolha, vizinho, autorização de depósito ou nova morada) registam apenas uma instrução ou uma nova data prevista, sem mover ainda a encomenda.

## Entrega, pontos HUBBED, nova entrega e Delivery Choices

**Prazos estimados.** O [guia de receção](https://www.couriersplease.com.au/faqs) indica 1 a 3 dias úteis entre áreas metropolitanas, alguns dias extra de costa a costa (por exemplo, de este para oeste), mais tempo para zonas regionais ou remotas e uma semana adicional em períodos de pico como o Natal. Todos os prazos são estimativas que incluem preparação do pedido, passagem pelo depósito e eventual nova entrega ou levantamento, podendo sofrer desvios devido ao tempo, ao trânsito ou a imprevistos.

**Quando não está em casa (Authority to Leave).** Durante a entrega, o motorista bate à porta ou toca à campainha, salvo se o remetente tiver pedido entrega sem contacto. De acordo com a cláusula 5.3 dos [Termos e Condições de Transporte](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) e a página [Delivery Choices](https://www.couriersplease.com.au/delivery-choices), as encomendas seguem por defeito com *Authority to Leave* (ATL): se alguém estiver presente, o motorista regista `SIG` e o nome de quem recebe; não estando ninguém, procura um local seguro. Se não houver local seguro (como apartamentos ou entradas movimentadas) ou se for exigida assinatura, a encomenda vai para um ponto de recolha próximo — exceto se ultrapassar 10 kg e/ou 100 cm cúbicos (aproximadamente uma mala de cabine), caso em que lhe é pedido que agende uma nova entrega. Em zonas regionais, a CouriersPlease indica que as encomendas são sempre deixadas num local seguro (ATL).

**Pontos de recolha HUBBED.** Em parceria com a HUBBED, a CouriersPlease disponibiliza mais de 3.000 pontos de recolha na Austrália com horários alargados. Encontre o mais próximo por subúrbio ou código postal no [localizador de pontos de recolha](https://www.couriersplease.com.au/resources/find-a-collection-point), com filtros para *Drop Off*, *Redirections* e *Failed deliveries*. Se o remetente tiver dado o seu e-mail, a CouriersPlease envia aviso em 24 horas; caso contrário, deixa um talão em papel. Segundo a cláusula 5.4 dos Termos de Transporte, a entrega considera-se concluída quando a CouriersPlease envia o código PIN: dispõe de **7 dias** após esse aviso para levantar a encomenda, findos os quais permanece no depósito mais próximo durante mais **5 dias** antes de ser devolvida a expensas do cliente. Se não recebeu o PIN, peça-o na [página Get PIN da HUBBED](https://hubbed.com/getpin/) (para si, para a sua empresa ou para outra pessoa).

**Nova entrega.** Agende-a na [página de nova entrega](https://www.couriersplease.com.au/book/redelivery) com o número de envio ou do cartão de aviso. O guia de receção refere ainda que, se a encomenda tiver sido levada para um ponto de recolha, pode pedir nova entrega no prazo de 6 dias. O centro de ajuda da CouriersPlease diz, pelo contrário, que uma encomenda já desviada para um ponto de recolha não pode ser entregue de novo na sua morada e tem de ser levantada lá; em caso de dúvida, pergunte ao apoio antes de a agendar.

**Delivery Choices.** Quando o remetente fornece o seu e-mail e telemóvel na reserva, recebe uma ligação segura com cinco opções em [Delivery Choices](https://www.couriersplease.com.au/delivery-choices):

- pedir assinatura obrigatória (*Signature required*: na ausência, segue para ponto de recolha ou depósito);
- levantar num ponto próximo (*Collect nearby*);
- deixar com um vizinho até três casas para cada lado (*Leave with a neighbour*);
- alterar a morada de entrega (*Change the address*);
- escolher outro dia dentro de três dias após a data original (*Choose another day*).

## Encomenda atrasada, desaparecida ou danificada

**Atraso ou sem atualizações.** A CouriersPlease refere que a maioria das encomendas atrasadas chega nas 24 a 48 horas úteis seguintes à estimativa inicial. Consulte os avisos de serviço na página inicial de couriersplease.com.au e, se algo parecer errado, utilize o *Virtual Assistant* (ícone de chat no canto inferior direito), selecione *Assistance with a Delivery* e envie o pedido apenas uma vez, pois pedidos repetidos atrasam o atendimento. Veja também [seguimento sem atualizações](guide:tracking-not-updating).

**Marcada como entregue mas não recebida.** Verifique locais abrigados junto à porta, pergunte a familiares, vizinhos ou portaria e contacte depois a loja e o *Virtual Assistant*. Consulte [entregue mas não recebida](guide:delivered-not-received) e [encomenda perdida](guide:lost-parcel).

**Danos, extravio e cobertura Parcel Protect.** Nos envios domésticos na Austrália, a [cobertura doméstica Parcel Protect](https://www.couriersplease.com.au/parcel-protect-domestic-cover) oferece proteção gratuita automática até 1.000 AUD, mas exclui os primeiros 100 AUD de prejuízo, as encomendas entregues com *Authority to Leave* (ATL), embalagem deficiente e diversas categorias (bens de elevado valor, dinheiro, joias, cartões-presente, quadros com vidro, espelhos, flores, plantas, perecíveis, objetos pessoais e eletrónica, mercadorias perigosas). A notificação escrita de perda ou dano deve ser feita em **14 dias úteis** após a entrega ou a data prevista de entrega (a que ocorrer primeiro). Se o transporte foi reservado por um intermediário terceiro, a reclamação tem de ser apresentada através desse intermediário. Como destinatário, contacte primeiro a loja, que é a titular da conta. Fora do Parcel Protect, a cláusula 7.3 dos Termos de Transporte limita a responsabilidade padrão à repetição do serviço, ao seu custo ou a 100 AUD (sem prejuízo dos direitos ao abrigo da Australian Consumer Law). Mais detalhes em [encomenda danificada](guide:damaged-parcel).

## Contactos da CouriersPlease e proteção contra burlas

- **Virtual Assistant:** clique no ícone de chat no canto inferior direito em [couriersplease.com.au](https://www.couriersplease.com.au/faqs) e escolha *Assistance with a Delivery* (ou escreva `feedback`). Submeta apenas um pedido por encomenda.
- **Help Centre:** acessível através da ligação *Help centre* no site couriersplease.com.au.
- **Sede:** Couriers Please Pty Limited (ABN 76 006 144 734), Rosehill Industrial Estate Building C South, Level 1, 5–7 Shirley Street, Rosehill, NSW 2142, Australia.

> De acordo com a página de [proteção contra burlas](https://www.couriersplease.com.au/scam-protection), os e-mails oficiais da CouriersPlease são enviados de `noreply@couriersplease.com.au` e os SMS surgem com o remetente `Courierspls`. A CouriersPlease nunca pede para alterar dados bancários, pagar taxas de libertação, introduzir dados de cartão ou palavras-passe nem responder `Y` para ativar uma ligação (desconfie de domínios falsos como `couriersplease.addliik.com` ou `no-reply@wufoo.com`). Na dúvida, apague a mensagem, consulte diretamente couriersplease.com.au e denuncie à CouriersPlease e à Scamwatch.

## Acompanhar encomendas CouriersPlease com o Peek

O [Peek](/) reconhece imediatamente a CouriersPlease nos números com o padrão `CP` (como `CPABC4829173640` ou `CPA48291736405`) e nas ligações de couriersplease.com.au/tools-track com `no=` ou `trackingCode=`. Não exige conta, código postal nem telefone. Se o número não começar por `CP`, cole a ligação de seguimento ou selecione CouriersPlease com «Alterar» ao adicionar ou «Alterar transportadora» numa encomenda já guardada.

O Peek lê diretamente o sistema oficial da CouriersPlease e apresenta cada registo no texto inglês da transportadora, com a hora que a CouriersPlease indica (hora de Sydney, mesmo fora da Nova Gales do Sul), o estado australiano quando indicado e a data prevista de entrega (*Delivery ETA*) assim que a encomenda ultrapassa o registo inicial (até à entrega ou devolução). Remove código HTML e ligações de suporte inseridas nos textos e não mostra morada do ponto de recolha, PIN HUBBED nem fotografia de entrega.

O Peek consulta diretamente a CouriersPlease até de 10 em 10 minutos enquanto tem a encomenda aberta ou no dia da entrega com alertas ativos, e de 2 em 2 minutos a partir das 6h00 hora de Sydney no dia em que a CouriersPlease regista a saída para entrega (`Onboard for delivery`), enquanto o seu sistema responde; caso contrário, de hora a hora. O Peek conta esse dia na hora de Sydney, a dos registos da CouriersPlease: em Perth, essas consultas começam às 3h ou 4h e terminam às 21h ou 22h. Após 12 horas em «Etiqueta criada» ou «Aceite pela transportadora», ou após dois dias sem novos registos, passa a verificar de hora a hora mesmo com o ecrã aberto, e de 6 em 6 horas após sete dias ainda em «Etiqueta criada». Quando recorre a serviços de seguimento, consulta de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da entrega.

Se uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem um novo registo, o Peek assinala-a com o dia desse último registo (por exemplo, «Sem atualizações desde seg 12 out»), apresenta «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a CouriersPlease.» e mostra a ligação «Pede ajuda a CouriersPlease» para o centro de ajuda da CouriersPlease. Após mais de três semanas sem novidades, passa para: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a CouriersPlease.»

Pode escolher notificações para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não agenda novas entregas, não pede códigos PIN HUBBED nem submete reclamações Parcel Protect — para isso, contacte a CouriersPlease ou a loja.

## Perguntas sobre o seguimento CouriersPlease

### Quanto tempo demora a CouriersPlease a entregar?

Segundo o guia de receção, estima-se 1 a 3 dias úteis entre áreas metropolitanas, alguns dias adicionais de costa a costa, prazos mais longos para zonas regionais ou remotas e uma semana extra em épocas de pico como o Natal.

### Porque é que o meu seguimento CouriersPlease não atualiza?

Em trajetos interestaduais, entregas por parceiros regionais ou processamento em depósito podem decorrer vários dias entre leituras. A CouriersPlease refere que a maioria das encomendas atrasadas chega em 24 a 48 horas úteis após a estimativa inicial; depois disso, utilize o *Virtual Assistant* em couriersplease.com.au.

### Como funcionam os pontos de recolha HUBBED e o código PIN?

Quando uma encomenda até 10 kg e 100 cm cúbicos não pode ser deixada em segurança ou exige assinatura, vai para um dos mais de 3.000 pontos HUBBED. Nos termos dos Termos de Transporte, tem 7 dias após o envio do PIN para a levantar, seguidos de 5 dias no depósito mais próximo antes da devolução. Se o PIN não chegou, peça-o em hubbed.com/getpin/.

### O que fazer se a encomenda chegar danificada ou for entregue com Authority to Leave?

Avise de imediato a loja remetente. As reclamações escritas ao abrigo do Parcel Protect devem ser apresentadas em 14 dias úteis (até 1.000 AUD, excluindo os primeiros 100 AUD), mas o Parcel Protect não abrange entregas com *Authority to Leave* (ATL).

:::sources
- [CouriersPlease: Track A Parcel Delivery](https://www.couriersplease.com.au/tools-track) – seguimento até 10 números separados por vírgulas
- [CouriersPlease: Guide to receiving](https://www.couriersplease.com.au/faqs) – cinco etapas de seguimento, prazos estimados, limites 10 kg / 100 cm, 6 dias para nova entrega, 24–48 horas úteis em atrasos, Virtual Assistant
- [CouriersPlease: Book A Parcel Redelivery](https://www.couriersplease.com.au/book/redelivery) – número de envio ou cartão de aviso, prefixo CP, até 20 caracteres
- [CouriersPlease: How do I arrange a redelivery?](https://support.couriersplease.com.au/hc/en-au/articles/31312747333785-How-do-I-arrange-a-redelivery) – sem nova entrega para uma encomenda já desviada para um ponto de recolha
- [CouriersPlease: Find a Collection Point](https://www.couriersplease.com.au/resources/find-a-collection-point) – pesquisa por subúrbio ou código postal e filtros de pontos
- [CouriersPlease: Delivery Choices](https://www.couriersplease.com.au/delivery-choices) – ATL por defeito, cinco opções de entrega, regra regional ATL
- [CouriersPlease: Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) – limite de 1.000 AUD, exclusão dos primeiros 100 AUD e de ATL, prazo de 14 dias úteis, reclamações via intermediários
- [CouriersPlease: Terms and Conditions of Carriage](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) – cláusulas 5.3 (ATL / SIG), 5.4 (7 dias após PIN + 5 dias em depósito), 7.3 (limite de responsabilidade) e sede social
- [CouriersPlease: Scam protection](https://www.couriersplease.com.au/scam-protection) – remetentes oficiais noreply@couriersplease.com.au e Courierspls, sinais de phishing, Scamwatch
- [CouriersPlease: Our Story](https://www.couriersplease.com.au/our-story) – FMH Group, Franchise Partners, mais de 3.000 pontos HUBBED
- [HUBBED: Get PIN](https://hubbed.com/getpin/) – pedido do código PIN de levantamento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção de números e ligações CouriersPlease, mapa de estados e campos lidos pelo Peek
:::
