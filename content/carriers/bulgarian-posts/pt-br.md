---
title: Rastreamento Bulgarian Posts: códigos PS/BG, taxas e ajuda
description: Rastreie encomendas dos Bulgarian Posts: códigos PS e S10 BG, status em inglês e búlgaro, 20 dias na agência, taxas da UE, reclamações e telefones.
slug: bulgarian-posts-tracking
published: 2026-11-26
updated: 2026-11-26
---

O Bulgarian Posts (*Български пощи ЕАД*) é o operador postal universal nacional da Bulgária, responsável por cartas domésticas, pacotes eletrônicos (e-packages) e encomendas, além de envios internacionais da União Postal Universal (UPU) e entregas expressas EMS / Bulpost. Você pode acompanhar um envio na [página de rastreamento do Bulgarian Posts](https://www.bgpost.bg/en/postal-services/track-package) ou colar o código de rastreamento na caixa acima.

## Códigos de rastreamento Bulgarian Posts: formatos PS e S10

Procure o código de barras de 13 caracteres no recibo de postagem, no e-mail de envio da loja virtual ou no aviso de entrega em papel (*служебно известие*). Em [bgpost.bg/en/postal-services/track-package](https://www.bgpost.bg/en/postal-services/track-package), o Bulgarian Posts lista dois formatos rastreados:

| Formato (inventado) | O que é |
| --- | --- |
| `PS48291736405` | Envio doméstico ou e-package: `PS` seguido de 11 letras e dígitos (13 caracteres no total). |
| `RR482917367BG` | Carta registrada ou pequeno pacote internacional (`R`, `C`, `E`, `L` ou `V` + segunda letra + 9 dígitos + `BG`). |
| `CP482917367BG` | Encomenda postal internacional originada na Bulgária (`CP` + 9 dígitos + `BG`). |

Encomendas vindas do exterior mantêm o código UPU S10 do correio de origem (terminando no código de duas letras desse país, como `DE`, `CN` ou `US`). O Bulgarian Posts informa que envios internacionais de saída aparecem em seu site até deixarem a Bulgária e depois no site do correio de destino, enquanto os envios de entrada surgem no Bulgarian Posts assim que chegam ao país. Para outros códigos de transportadoras na Bulgária, consulte Sameday, [BOX NOW](carrier:box-now) ou [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento do Bulgarian Posts

O rastreamento do Bulgarian Posts exibe descrições em inglês do sistema UPU IPS nos trechos internacionais — marcadas com `(Otb)` na saída ou `(Inb)` na entrada — junto com textos em búlgaro nas movimentações domésticas (mesmo acessando a página em inglês).

| O Bulgarian Posts diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Receive item from customer (Otb)`, `Получаване пратка от клиент (А10)`, primeiro `Приемане` | Postado pelo remetente em uma agência postal. | "Recebida pela transportadora" |
| `Insert item into bag (Otb) and Send item abroad`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Ensacado para exportação, despachado ao exterior ou recebido no centro de permuta internacional da Bulgária. | "Em trânsito" |
| `Receive item at sorting centre (Inb)`, `Insert item into domestic bag`, `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Приемане` posterior | Seguindo pelos centros de triagem búlgaros rumo à sua agência local. | "Em trânsito" |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Apresentado à alfândega búlgara ou aguardando documentos/avaliação aduaneira (muitas vezes com detalhe indicando espera de apresentação a um despachante aduaneiro). | "Na alfândega" |
| `Return item from customs (Inb)` | Liberado pela alfândega de volta à rede do Bulgarian Posts (não é devolução ao remetente). | "Em trânsito" |
| `Handover item to delivery agent` | Saiu com o carteiro para entrega em domicílio hoje. | "Saiu para entrega" |
| `Unsuccessful item delivery attempt (Inb)`, `Първо посещение` | A primeira tentativa falhou; um aviso por escrito foi deixado e o objeto segue para a sua agência. | "Tentativa de entrega" |
| `Receive item at collection point for pick-up (Inb)`, `За получаване в ПС`, `Второ посещение` | Aguardando retirada na agência local (`Второ посещение` registra o segundo aviso escrito enviado após 10 dias corridos). | "Pronta para retirada" |
| `Deliver item (Inb)`, `Доставена`, `Доставена пратка` | Entregue mediante assinatura. | "Entregue" |

Repare que `Създаване` (criação do registro doméstico pelo Bulgarian Posts logo após a movimentação no centro de permuta de entrada) é um lançamento administrativo e não um deslocamento físico, por isso o Peek mantém a etapa anterior. Da mesma forma, `Второ посещение` não indica nova tentativa frustrada na porta — registra o segundo aviso de retirada enquanto a encomenda continua aguardando na agência.

## Retirada na agência, prazos de guarda e taxa de armazenagem

Segundo as [Condições Gerais do Bulgarian Posts (ОУ 2023)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) (pontos 43, 45 e 46, acessíveis em [Condições Gerais](https://www.bgpost.bg/en/about_us/conditions)):

- **Objetos registrados e encomendas:** entregues pessoalmente ao destinatário ou pessoa autorizada mediante assinatura e documento oficial com foto. Encomendas postais (*пощенски колети*) normalmente são retiradas na agência de destino após o Bulgarian Posts entregar um aviso por escrito (*писмено служебно известие*), salvo se a entrega em domicílio tiver sido contratada à parte.
- **Prazo padrão de guarda — 20 dias corridos:** tanto objetos registrados após tentativa frustrada quanto encomendas retidas na agência aguardam por **20 dias corridos** a partir da chegada à agência de entrega antes de voltarem ao remetente.
- **Segundo aviso em 10 dias:** se você não retirar em **10 dias corridos**, o Bulgarian Posts envia um segundo aviso por escrito (`Второ посещение`).
- **Taxa de armazenagem após 9 dias:** para encomendas postais não retiradas em **9 dias corridos** após a notificação, o Bulgarian Posts cobra taxa de armazenagem (*магазинаж*) conforme a tabela vigente.
- **Envios desembaraçados na alfândega — 45 dias corridos:** pelas [regras aduaneiras do Bulgarian Posts](https://www.bgpost.bg/en/customs), **todos os itens que passaram por desembaraço aduaneiro são retirados somente na agência designada** (nunca entregues na porta) e ficam guardados por **45 dias corridos** antes da devolução ao remetente.

## Encomendas de fora da UE, taxas aduaneiras e imposto de €3 / taxa de €2

Envios feitos dentro da UE não passam pela alfândega. Para compras on-line e presentes que chegam à Bulgária de fora da UE, [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) e a [Comissão Europeia](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) detalham as cobranças em vigor:

- **Imposto fixo e taxa de manuseio da UE:** aplica-se um **imposto aduaneiro fixo de €3 por artigo** em remessas B2C on-line de até €150 (em vigor desde 1º de julho de 2026) e uma **Taxa de Manuseio da União (UHF) de €2 por artigo individual** em compras on-line de fora da UE independentemente do valor (em vigor desde 1º de novembro de 2026; entra na base do IVA e não incide sobre presentes particulares C2C). Tanto o imposto de €3 (em itens B2C até €150) quanto a UHF de €2 **não são reembolsáveis**, mesmo se você devolver mercadoria com defeito.
- **Taxas de representação aduaneira do Bulgarian Posts (com IVA incluído, vigentes desde 2 de novembro de 2026):**
  - B2C até €150 com IOSS (declaração H7): **€2,70** (mais imposto aduaneiro se a loja não o cobrou no checkout).
  - B2C até €150 sem IOSS (Regime Especial, H7): **€3,80** (mais IVA e imposto aduaneiro).
  - B2C de €150 a €1.000 (H6): **€12,00** (mais IVA e imposto).
  - B2C acima de €1.000 (H1): **€20,00** pelo primeiro tipo de item mais **€5,00** por cada tipo adicional.
  - Presentes particulares C2C: **€2,70** até €45 (sem IVA/imposto), **€3,80** de €45 a €1.000 ou **€20,00** (+ €5,00 por tipo adicional, exige EORI) acima de €1.000.
- **Documentos exigidos:** Declaração de Consentimento, Declaração de Valor e (para compras) pedido/fatura compatível com o código de rastreamento e comprovante de pagamento (extrato bancário/cartão/PayPal).

Veja [encomendas retidas na alfândega](guide:customs) para orientações gerais sobre a alfândega na UE.

## Encomendas extraviadas, atrasadas ou danificadas e direitos do consumidor

Em compras on-line, o lojista responde pela entrega e por reembolsos. Segundo a Comissão de Proteção ao Consumidor da Bulgária ([regras de compras on-line da KZP](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping)), pedidos on-line devem ser entregues em até 30 dias salvo acordo distinto, e consumidores têm direito de arrependimento de 14 dias após o recebimento.

**Reclamações junto ao Bulgarian Posts.** Conforme [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) e os pontos 56–59 das Condições Gerais, o **remetente** protocola reclamações antes da entrega — o destinatário só pode reclamar antes do recebimento com consentimento por escrito do remetente. O prazo para abrir reclamação é de **6 meses** após a postagem (em qualquer agência ou on-line pelo portal RQMS), com cópia do recibo de postagem. O Bulgarian Posts deve responder em **1 mês** para envios domésticos e em **3 meses** para internacionais, pagando indenizações aprovadas em até **1 mês** após a notificação.

**Escalonamento ao regulador.** Conflitos postais não resolvidos podem ser levados à Comissão de Regulação das Comunicações da Bulgária ([reclamações na CRC / КРС](https://crc.bg/bg/rubriki/271/jalbi)), que responde em 30 dias (até 2 meses se precisar de provas adicionais). Para lidar com atrasos ou falta de atualizações, consulte [por que o rastreamento para de atualizar](guide:tracking-not-updating), [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento e contatos do Bulgarian Posts

Contatos publicados em [bgpost.bg/en/contacts](https://www.bgpost.bg/en/contacts), [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) e [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs):

- **Atendimento geral:** `*7678` ou `+359 2 962 50 50` (dias úteis, 08:00–17:30), e-mail `info@bgpost.bg`.
- **Expresso EMS / Bulpost:** `*6666` ou `0700 19 666` (dias úteis, 07:00–19:00).
- **Serviço aduaneiro (1900 Sofia BESC):** `customerservice@bgpost.bg` (seg–sex, 08:00–16:00); envios EMS iniciados por `E`: `emscustomerservice@bgpost.bg` (seg–sex, 08:30–16:00); agência aduaneira de Varna `psm.varna@bgpost.bg` / `052 604 143`.
- **Linhas de reclamações (dias úteis, 09:00–17:30):** doméstico `+359 2 949 3326`, internacional de entrada `+359 2 949 3121`, internacional de saída `+359 2 949 3343`, suporte ao portal RQMS `02 949 31 90`.
- **Sede:** 1, Akademik Stefan Mladenov Str., Bl. 31, 1700 Sofia, Bulgária.

> Desconfie de SMS ou e-mails não solicitados pedindo pagamento por cartão on-line para "agendar a entrega" de uma encomenda do Bulgarian Posts: itens desembaraçados na alfândega são pagos e retirados na agência designada. Verifique o código diretamente em [bgpost.bg](https://www.bgpost.bg/en/postal-services/track-package). Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda do Bulgarian Posts com o Peek

O [Peek](/) identifica diretamente com alta confiança códigos `PS` de 13 caracteres (`PS48291736405`, dígitos inventados) como Bulgarian Posts e reconhece links colados de `bgpost.bg/postal-services/track-package?itemid=` (inclusive `/en/`) e links antigos `IPSWeb_item_events.asp?itemid=`. Para códigos internacionais UPU S10 terminados em `BG` (como `RR482917367BG` ou `CP482917367BG`), o Peek acompanha o Bulgarian Posts automaticamente como correio de origem preferencial; você também pode escolher Bulgarian Posts em códigos S10 vindos do exterior tocando em "Trocar" ao adicionar ou "Trocar transportadora" na tela da encomenda (buscar por "Български пощи", "Bulgaria Post" ou "BG Post" também funciona). Se o código pertencer ao Bulpost (`EMS/Bulpost`), o Bulgarian Posts redireciona para a página própria do Bulpost.

O Peek consulta diretamente a API pública do Bulgarian Posts e exibe a etapa ("Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Tentativa de entrega", "Pronta para retirada", "Entregue"), os textos IPS em inglês (além das descrições domésticas em búlgaro e observações aduaneiras), a agência/país (sem códigos postais) e o horário de entrega nas agências búlgaras. Movimentações em agências da Bulgária usam o fuso de Sófia e movimentações de correios estrangeiros usam o fuso local (por exemplo, o da Alemanha em Frankfurt); o Peek converte tudo para o relógio do seu aparelho. O rastreador do Bulgarian Posts não informa data prevista de entrega, peso, nome do serviço, nome de remetente/destinatário nem foto de entrega.

Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos; no dia em que o Bulgarian Posts mostra `Handover item to delivery agent`, a cada 2 minutos a partir das 6h no horário da Bulgária enquanto o próprio rastreador do Bulgarian Posts responder. Nos demais momentos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a consultar a cada hora de qualquer modo, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador do Bulgarian Posts não responde e o Peek acompanha a encomenda somente por serviços de rastreamento, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega.

Quando a movimentação mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Bulgarian Posts.", com um link "Pedir ajuda a Bulgarian Posts" para a página de contatos. Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Bulgarian Posts."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não envia declarações aduaneiras nem abre reclamações junto ao Bulgarian Posts por você.

## Perguntas sobre o rastreamento do Bulgarian Posts

### Por quanto tempo o Bulgarian Posts guarda uma encomenda na agência?

Encomendas comuns e itens registrados aguardam **20 dias corridos** a partir da chegada à agência de entrega (com segundo aviso após 10 dias e taxa de armazenagem para encomendas após 9 dias). Itens que passaram pela alfândega ficam guardados por **45 dias corridos** na agência designada.

### O que significa `Второ посещение` no rastreamento do Bulgarian Posts?

Significa que o Bulgarian Posts emitiu o segundo aviso escrito de retirada após 10 dias corridos sem coleta. Sua encomenda continua aguardando na agência — o Peek mantém o status em "Pronta para retirada".

### O que significa `Return item from customs (Inb)`?

Significa que a alfândega búlgara liberou o envio e o devolveu à rede do Bulgarian Posts para seguir até a sua agência. Não significa devolução ao remetente.

### Quanto o Bulgarian Posts cobra pelo desembaraço aduaneiro?

Desde 2 de novembro de 2026, a taxa de representação do Bulgarian Posts (com IVA incluído) é de €2,70 para compras IOSS até €150 (ou presentes C2C até €45), €3,80 para compras sem IOSS até €150 (ou presentes de €45 a €1.000), €12,00 para compras de €150 a €1.000 e €20,00 (+ €5,00 por tipo extra de item) acima de €1.000, além do imposto aduaneiro da UE aplicável (€3 até €150), da taxa de manuseio da UE de €2 e do IVA.

:::sources
- [Bulgarian Posts: Home](https://www.bgpost.bg/en) – national postal operator overview
- [Bulgarian Posts: Item Tracking](https://www.bgpost.bg/en/postal-services/track-package) – 13-character S10 and `PS` number rules, inbound/outbound tracking scope
- [Bulgarian Posts: Customs](https://www.bgpost.bg/en/customs) – €3 duty, €2 Union Handling Fee, 2 Nov 2026 customs representation fee schedule (€2.70 / €3.80 / €12 / €20), post-office-only delivery and 45-calendar-day customs storage
- [Bulgarian Posts: General Terms (ОУ 2023 PDF)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) – points 43, 45, 46 (ID/signature, 20-calendar-day hold, 10-day second notice, 9-day storage fee) and points 56–59 (claims)
- [Bulgarian Posts: General Terms Overview](https://www.bgpost.bg/en/about_us/conditions) – universal postal service and EMS/Bulpost terms
- [Bulgarian Posts: Claims and Inquiries](https://www.bgpost.bg/en/complaints) – sender claim rule, 6-month deadline, 1-month/3-month resolution, RQMS portal and claims phones
- [Bulgarian Posts: Contacts](https://www.bgpost.bg/en/contacts) – `*7678`, `+359 2 962 50 50`, `info@bgpost.bg`, EMS `*6666` / `0700 19 666`, Sofia headquarters
- [Bulgarian Posts: FAQ](https://www.bgpost.bg/en/faq) – general recipient questions
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty and €2 EU handling fee
- [KZP: Online Shopping](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping) – 30-day delivery rule and 14-day withdrawal right
- [CRC (КРС): Complaints](https://crc.bg/bg/rubriki/271/jalbi) – postal regulator complaints and 30-day response timeframe
- [Universal Parcel Scraper: bulgarian-posts](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bulgarian-posts) – `PS` and S10 `BG` detection, `bgpost.bg` URL rules, English/Bulgarian IPS status mapping, Bulpost redirect, Sofia clock and help URL
:::
