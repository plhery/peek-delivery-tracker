---
title: Rastreamento bpost: correio da Bélgica, status e alfândega
description: Rastreie encomendas da bpost na Bélgica: onde achar o código de barras, status do Track & Trace, pontos de retirada, lockers Bbox, alfândega e contato.
slug: rastreamento-bpost
published: 2026-11-24
updated: 2026-11-24
---

A bpost é a operadora postal da Bélgica, marca do grupo bnode responsável pela entrega final na Bélgica e na Holanda. Você pode rastrear um pacote no [Track & Trace da bpost](https://track.bpost.cloud/) ou no app My bpost com o código de barras, ou colar o código na caixa acima. O código de barras vem no e-mail de envio da loja, em SMS ou e-mail da bpost, na etiqueta da caixa ou no aviso deixado na caixa de correio.

## Código de rastreamento bpost: onde achar o código de barras

A bpost chama o código de rastreamento de *barcode* (código de barras). Ele aparece na etiqueta de envio, no recibo da agência postal, no aviso de ausência e no e-mail de confirmação. As lojas costumam enviá-lo por e-mail, às vezes já como link do Track & Trace. Sem código? Peça à loja. Veja mais dicas em [como encontrar seu código de rastreamento](guide:find-tracking-number).

Os formatos mais comuns divulgados pela bpost (exemplos fictícios):

| Formato | Exemplo | O que é |
| --- | --- | --- |
| 24 dígitos começando em 3232 ou 3299 | `323248291057364918273649` | Código de barras de encomenda |
| 18 dígitos começando em 3232 | `323248291057364918` | Código de barras de encomenda |
| 27 caracteres começando em JJBEA | `JJBEA4829105736491827364918` | Código de barras de encomenda |
| Duas letras, nove dígitos, `BE` | `CE482910576BE` | Código postal internacional da Bélgica |
| `R`, uma letra, nove dígitos, duas letras | `RR482910576BE` | Correspondência registrada internacional |
| 30 dígitos começando em 010 | `010482910573649182736491827364` | Correspondência registrada dentro da Bélgica |

Um código começando por `U` existe apenas para a alfândega: o remetente ou comprador escolheu envio sem Track & Trace, e o item chega em até 5 dias úteis após o desembaraço aduaneiro, segundo a bpost.

Um código postal terminado nas letras de outro país pertence ao correio daquele país. Um dígito trocado num código terminado em `BE` invalida o dígito verificador e faz o Peek não sugerir nenhuma transportadora. Alguns códigos de 24 dígitos pertencem à [InPost](carrier:inpost) ou à Ciblex.

## Status do Track & Trace da bpost explicados

O Track & Trace da bpost funciona em inglês, francês, holandês e alemão. Estas são as mensagens em inglês na ordem em que costumam aparecer:

| A bpost informa | O que significa |
| --- | --- |
| `Confirmation of preparation of the shipment received` | O remetente anunciou a encomenda; a bpost ainda não a recebeu. |
| `Item is announced / Landmark Global (bpost) received the information` | Envio internacional anunciado à Landmark Global, marca internacional do grupo. |
| `Landmark Global (bpost) has received the item` | A Landmark Global recebeu o pacote. |
| `Arrival at facilities` | Pacote postado no exterior chegou a um centro de triagem na origem, antes da leitura pela bpost. |
| `Your item has been sorted` | Triado e a caminho. |
| `Departure to country of destination` | Seguindo para o país de destino. |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | O correio do país de destino recebeu o item e fará a entrega nos próximos dias. |
| `The postman's delivery round for your item has started` | Saiu para entrega hoje com o carteiro. |
| `Delivery interrupted due to unforeseen circumstances` | A rota do carteiro parou antes do seu pacote por motivo da bpost; um novo dia de entrega será definido. |
| `New delivery scheduled` | A bpost agendou esse novo dia e seu pacote aguarda saída. |
| `Item available at Pick-up point` | Pronto para retirada num ponto de coleta. |
| `Item available at the bbox locker` | Pronto para retirada num locker Bbox. |
| `Item delivered` | Entregue, ou retirado no ponto de coleta. |

## Entrega, pontos de retirada e lockers Bbox

Se você não estiver em casa e não tiver definido preferências, o carteiro costuma tentar um vizinho ou local seguro. Se isso falhar, deixa um aviso de ausência e você pode retirar a encomenda no ponto de retirada indicado a partir do próximo dia útil. A bpost avisa por e-mail quando fica disponível; o aviso em papel só chega quando não há canal digital. O carteiro não retorna no mesmo dia, mas após tentativa falha no sábado passa novamente no dia útil seguinte.

**Prazo de guarda.** Um ponto de retirada guarda o pacote por 14 dias corridos; um locker Bbox, por 5 dias corridos sem prorrogação. Depois disso, volta ao remetente. Qualquer pessoa com o código de barras pode retirar encomendas comuns; correspondência registrada exige documento do destinatário ou procuração assinada no aviso.

**Lockers Bbox** ficam abertos 24 horas e dispensam conta. Basta ler o QR code do app My bpost ou do e-mail; lockers sem tela abrem somente pelo app via Bluetooth.

**Alterar a entrega.** Em MyPreferences ou no app My bpost dá para definir local seguro, vizinho, ponto de retirada ou locker. Com a encomenda já em trânsito, você pode redirecionar para outro endereço próximo ou adiar uma vez por um dia útil. Vizinho e local seguro podem ser alterados até 5 minutos antes da entrega; ponto de retirada ou locker, até a meia-noite da véspera.

## Encomenda atrasada, extraviada ou na alfândega da bpost

**Parada.** Em envios internacionais, a bpost pede aguardar ao menos 5 dias úteis sem atualização (países vizinhos) ou 10 dias úteis (Reino Unido e demais destinos) e então pedir ao remetente para contatá-la. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Rota interrompida.** Após `Delivery interrupted due to unforeseen circumstances`, a rota parou do lado da bpost e ganha nova data em `New delivery scheduled`. O Peek mostra "Precisa de atenção" na primeira linha e volta a "Em trânsito" na segunda.

**Quem reclama.** Como destinatário você não pode abrir reclamação própria. A maioria das lojas tem acordo com a bpost para extravio ou avaria, então peça reenvio ou reembolso à loja; apenas o remetente pode iniciar uma investigação.

**Avaria.** Contate o remetente em até 7 dias com fotos; em pacotes vindos do exterior, avise a bpost também.

**Entregue, mas não chegou.** Pergunte aos vizinhos, confira a caixa de correio (volumes pequenos entram sem tocar a campainha), suas preferências e a foto do local seguro no app ou Track & Trace. A bpost oferece ainda um formulário para envios não entregues — tenha em mãos o código de barras e o código postal.

**Alfândega.** Pacotes de fora da UE precisam ser liberados primeiro. Pague os custos de importação com antecedência, de preferência no app My bpost ou Track & Trace, em até 14 dias. Em compras abaixo de €150, a bpost lista €3 por item de direito aduaneiro e, desde 1º de novembro de 2026, taxa europeia de manuseio de €2 por item (cada item é um tipo de produto: duas camisetas idênticas pagam €5 de imposto e taxa; uma camiseta e uma bermuda de banho, €10). Somam-se o IVA (normalmente 21%) e €21,50 de custos administrativos se a loja não recolheu IVA e taxas na compra. Acima de €150, pagam-se direitos conforme o produto, IVA, €40 de administração e, desde 1º de novembro de 2026, a taxa de €2. Confira a [página da bpost para compras fora da UE](https://www.bpost.be/en/shopping-outside-eu) e o [guia de alfândega](guide:customs).

> Em envios dentro da UE, a bpost nunca cobra pagamento durante o transporte nem pede dados pessoais por e-mail ou SMS. Quando uma encomenda de fora da UE tem taxas de importação, porém, a bpost envia pedido de pagamento por e-mail, SMS, notificação no app My bpost ou carta: pague apenas no app My bpost, no Track & Trace ou numa agência postal. Encaminhe mensagens suspeitas para abuse@bpost.be.

## Telefone e atendimento da bpost

Canais oficiais da bpost:

- **Telefone:** 02 278 51 26 nas páginas em inglês e holandês, 02 278 51 27 nas páginas em francês e alemão; segunda a sexta 08h–12h30 e 13h–18h, sábado 09h–13h, tarifa zonal.
- **Chat:** disponível para alguns serviços, incluindo Track & Trace.
- **Presencialmente:** numa Post Office, Post Point ou Parcel Point.
- **Carta (sem selo):** bpost, Box 5000, 1000 Brussels.

Tenha o código de barras em mãos. A bpost não divulga e-mail de atendimento.

## Como rastrear encomendas da bpost no Peek

O [Peek](/) reconhece a bpost na hora em códigos válidos de duas letras, nove dígitos e `BE`, e em `JJBE` seguido de uma letra e 18 ou 22 dígitos. Códigos de 24 dígitos iniciados em 3232 ou 3299 são consultados na bpost, Austrian Post, InPost e Ciblex; códigos de 18 dígitos iniciados em 3232, na bpost — e na Ecoscooting quando os dígitos 6 a 9 são zeros, padrão da Ecoscooting. Quando a bpost encontra o número, o Peek a seleciona; só quando a Ecoscooting também reconhece esse código de 18 dígitos você escolhe em "Escolher". Os códigos registrados de 30 dígitos só funcionam após escolher bpost em "Trocar". Links de bpost.be ou do Track & Trace com `itemCodes` abrem como bpost. Um código iniciado em `U` não vai para a bpost (pois ela não o rastreia); o Peek sugere apenas Chronopost.

O Peek consulta a bpost diretamente, sem código postal. Você vê as leituras em inglês da bpost com local, peso e dimensões; leituras na Bélgica usam o horário belga e aparecem no fuso do seu dispositivo. Enquanto o pacote aguarda num ponto de retirada ou locker, o Peek mostra o nome e o endereço do ponto, mantendo-os mesmo após a retirada. Entregas na Bélgica exibem o horário de entrega, e devoluções ao remetente aparecem como devolvidas. O Peek verifica até a cada 10 minutos, ou a cada 2 minutos a partir das 6h locais no dia em que a bpost marca saída para entrega, enquanto o rastreador oficial responde (pelos serviços de rastreamento, até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados), e avisa quando o status muda. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora.

O Peek não exibe dia previsto de entrega e omite remetente, destinatário, endereço, contatos, preferências de entrega e comprovante de entrega.

## Perguntas frequentes sobre rastreamento bpost

### O que é a bpost?

É a marca que entrega encomendas e cartas na Bélgica e na Holanda pelo grupo bnode (antigo bpostgroup). A marca do grupo para envios internacionais é a Landmark Global.

### A bpost entrega aos sábados?

Encomendas, às vezes, quando o remetente (certas lojas online) tem acordo de fim de semana com a bpost: em ponto de retirada ou locker aos sábados das 8h às 17h, ou em casa aos domingos das 9h às 19h. Cartas e registrados saem apenas em dias úteis.

### A bpost tem chat ao vivo?

Sim, para alguns serviços incluindo o Track & Trace. A bpost não publica horários de funcionamento do chat.

### Por que não consigo rastrear minha encomenda da bpost?

Normalmente o remetente ainda não entregou o pacote à bpost. Códigos iniciados com `U` não têm rastreamento Track & Trace.

:::sources
- [bpost: What does a barcode look like and where can I find it?](https://www.bpost.be/en/faq/what-does-barcode-look-and-where-can-i-find-it) – formatos de código de barras, onde encontrar e códigos U
- [bpost: Track & Trace, follow a parcel](https://www.bpost.be/en/faq/can-i-follow-my-parcel-online) – canais de rastreamento, código necessário e envios ao exterior
- [bpost: When can you follow a parcel with Track & Trace?](https://www.bpost.be/en/faq/when-can-i-follow-my-parcel-track-trace) – início do rastreamento na postagem
- [bpost: Parcel in transit but didn't receive a barcode](https://www.bpost.be/en/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – e-mail da loja e contato com o remetente
- [bpost: Track your shipment](https://track.bpost.cloud/) – idiomas e status do Track & Trace
- [bpost: I was not home, where is my parcel now?](https://www.bpost.be/en/faq/i-was-not-home-when-postman-passed-where-my-parcel-now) – vizinho, local seguro e aviso
- [bpost: Not home, can I pick up my parcel somewhere today?](https://www.bpost.be/en/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – próximo dia útil e aviso por e-mail
- [bpost: Missed parcel, can the postman pass by again?](https://www.bpost.be/en/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – sem segunda visita no mesmo dia
- [bpost: Notice in letterbox, postman will pass by a second time](https://www.bpost.be/en/faq/why-will-postman-be-passing-second-time-after-leaving-failed-delivery-notice) – segunda tentativa após sábado
- [bpost: Receiving a parcel in a Pick-up point](https://www.bpost.be/en/receive-parcel/pick-up-point) – 14 e 5 dias, retirada por terceiros e registrados
- [bpost: How to get a parcel out of a bbox locker?](https://www.bpost.be/en/faq/how-do-i-remove-parcel-parcel-locker) – QR code, código de 6 dígitos, Bluetooth e sem prorrogação
- [bpost: Bbox](https://www.bpost.be/en/bbox) – 24 horas e sem conta
- [bpost: What happens to my parcel if I am not at home?](https://www.bpost.be/en/faq/what-if-i-am-not-home-when-postman-delivers-my-parcel) – preferências, redirecionamento e adiamento
- [bpost: Can I change the time of delivery of my parcel?](https://www.bpost.be/en/faq/can-i-change-time-delivery) – adiar uma vez por um dia útil
- [bpost: Your delivery preferences, your rules](https://www.bpost.be/en/mybpostapp-pref-parcel) – prazos para alterar preferências
- [bpost: Receive a parcel](https://www.bpost.be/en/receive-parcel) – 14 dias para pagar custos de importação
- [bpost: My parcel stopped moving in Track & Trace](https://www.bpost.be/en/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 e 10 dias úteis e reclamação pelo remetente
- [bpost: How to file a complaint about a parcel](https://www.bpost.be/en/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – destinatário não reclama, avaria em 7 dias e horários
- [bpost: Parcel delivered, but didn't receive anything](https://www.bpost.be/en/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – verificações, foto do local seguro e formulário
- [bpost: Parcel must pass through customs](https://www.bpost.be/en/receive-parcel/customs) – pagamento antecipado na importação
- [bpost: A parcel from outside the EU?](https://www.bpost.be/en/shopping-outside-eu) – €3 e €2 por item, €21,50 e €40 de administração
- [bpost: Does bpost deliver on Saturday and Sunday?](https://www.bpost.be/en/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – horários de fim de semana
- [bpost: How do I contact bpost's customer services?](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – telefone, chat, presencial e Box 5000
- [bpost: Comment contacter le service clientèle de bpost ?](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 02 278 51 27 (página em francês)
- [bpost: Phishing, received suspicious text or email?](https://www.bpost.be/en/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – alerta de golpes e abuse@bpost.be
- [bnode: bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – nome do grupo, marca bpost e Landmark Global
:::
