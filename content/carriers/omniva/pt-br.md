---
title: Rastreamento Omniva: códigos, status e parcel machines
description: Rastreie encomendas da Omniva na Estônia, Letônia ou Lituânia: formato dos códigos JJEE e EE, significado dos status, guarda nos lockers e contatos.
slug: omniva-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Omniva é o correio da Estônia (a razão social continua Eesti Post) e a rede de encomendas que opera os armários inteligentes (parcel machines) na Estônia, Letônia e Lituânia. Rastreie sua encomenda com o código de envio em minu.omniva.ee, no aplicativo Omniva ou colando o código na caixa acima. O código vem no e-mail de envio da loja ou por SMS/e-mail da própria Omniva.

## Código de rastreamento Omniva: como é o formato

A Omniva chama o código de "shipment tracking code" ou "shipment code (tracking number)". Sua ajuda de devoluções em inglês cita o exemplo `CEXXXXXXXXEE`. Estes são os formatos mais comuns (todos os dígitos inventados):

| Código | O que é |
| --- | --- |
| `JJEE48291736405829173` | Encomenda Omniva: `JJEE` e 17 dígitos, 21 caracteres ao todo. |
| `JJEEMKP48291736405` | Outro código de encomenda Omniva: `JJEE`, três letras e 11 dígitos. |
| `CE482917367EE` | Duas letras, nove dígitos e `EE`: envio entregue à Omniva no formato postal internacional. Pacotes para parcel machines na Letônia e na Lituânia também costumam terminar em `EE`. |
| `RR739402812CN` | Encomenda vinda do exterior mantém o código do correio remetente; este termina em `CN`, postado na China. |

O código de porta de seis dígitos no SMS de chegada abre o armário inteligente e não é código de rastreamento; o código de devolução de seis dígitos também não é.

Códigos terminados em `LV` pertencem ao [Latvijas Pasts](carrier:latvijas-pasts), terminados em `LT` ao [Lietuvos paštas](carrier:lietuvos-pastas), e códigos `JJFI` ao [Posti](carrier:posti) na Finlândia. Veja outros padrões em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Omniva em inglês

As páginas de rastreamento da Omniva funcionam em inglês, estoniano, letão, lituano e russo, e o app exibe as etapas em inglês. "Track & trace details" abre o histórico completo. Principais etapas, em ordem aproximada:

| A Omniva diz | O que significa para você |
| --- | --- |
| `Shipment registered. Shipment has not been handed over to Omniva` | O remetente criou o envio. A Omniva ainda não está com o pacote. |
| `Sender has placed the shipment in the parcel machine` | O remetente depositou o pacote em um parcel machine. |
| `The courier has picked up the shipment` | Um entregador da Omniva coletou o volume do remetente. |
| `Shipment has been posted by the sender or collected by the postal service` | Um pacote do exterior foi postado no país de origem. |
| `Shipment held in customs` | Aguardando desembaraço aduaneiro. |
| `Shipment has arrived at a postal facility for further processing` | Na rede da Omniva, no terminal ou centro de triagem indicado ao lado. |
| `The shipment has been handed over to the courier` | Saiu para entrega. |
| `Shipment is ready for collection from the parcel machine` | Disponível no parcel machine indicado. Use o código de porta de seis dígitos. |
| `A notification of shipment arrival has been sent` | A Omniva enviou SMS, e-mail ou notificação no app. A encomenda não se moveu. |
| `Delivery attempt made` | O entregador tentou entregar e não conseguiu. |
| `Shipment delivered` | Entregue ou retirada; o parcel machine ou agência é informado. |
| `Shipment is being returned to the sender` | Em devolução ao remetente. |

Dois status costumam enganar: `Delivery attempt made` significa que a tentativa falhou, e `Shipment delivered` após uma devolução indica que o volume voltou ao remetente.

## Parcel machines da Omniva, entregadores e tentativas perdidas

**Avisos.** A Omniva avisa por SMS, e-mail ou notificação do app. No app da Omniva, notificações por SMS vêm desligadas por padrão; ative em Profile → Notification preferences.

**No parcel machine.** Digite o código de porta de seis dígitos na máquina indicada na mensagem. Qualquer pessoa com o código pode retirar para você. Não é preciso documento, salvo se o remetente incluiu verificação de identidade.

**Prazo de guarda.** Parcel machines guardam encomendas por **4 dias** nos três países, com lembrete enviado antes do vencimento. No e-service você pode prorrogar por até 4 dias de cada vez mediante taxa, somando no máximo 12 dias extras. A guarda em agências postais na Estônia pode ser estendida uma vez, por até um mês. Se o pacote não for retirado no prazo, retorna ao remetente, que paga o frete de volta.

**Entregador em domicílio.** Você recebe SMS com a data de entrega e o entregador liga antes de chegar (na Letônia e Lituânia, para combinar dia e horário). Limites na porta: até 30 kg na Estônia e Letônia, e 25 kg na Lituânia. Se o horário não servir, peça ao entregador outro dia ou um parcel machine próximo.

**Tentativa perdida com entregador.** Volumes até tamanho L vão para um parcel machine próximo por 4 dias. Na Estônia, pacotes até XL podem ficar 7 dias em uma agência postal e volumes XXL 7 dias no centro de entrega. Na Letônia, volumes maiores ficam 4 dias no centro de entrega; na Lituânia, retornam ao remetente.

**Redirecionamento.** Gratuito até o pacote chegar ao centro de triagem, pelo link no e-mail da Omniva ou no e-service; depois disso, ou para mudar para entrega em domicílio, há taxa. O volume também pode ser redirecionado se a máquina estiver cheia.

## Encomenda Omniva atrasada, danificada ou retida na alfândega

**Não entregue ou sem atualização.** Confirme primeiro se o prazo da Omniva (abaixo) já passou. Se passou, a Omniva orienta o destinatário a procurar o remetente, que preenche o formulário "Request parcel delivery status". Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Compartimento vazio ou local errado.** Contate o atendimento a clientes particulares da Omniva no seu país.

**Danificada.** Na Estônia, o destinatário pode enviar o formulário de encomenda danificada ou extraviada em até **6 meses** da postagem, embora normalmente só o remetente receba reembolso. Na Letônia e Lituânia, as páginas de ajuda orientam falar com o remetente (na Lituânia com fotos do pacote e da embalagem) para abrir reclamação, mas os termos letões da Omniva também permitem que o destinatário reclame por escrito em até **6 meses**.

**Alfândega (Estônia).** Desde 1º de julho de 2021, toda encomenda vinda de fora da UE deve ser declarada, qualquer que seja o valor, em até **30 dias**. Você pode autorizar a Omniva ou declarar por conta própria à Receita e Alfândega. Veja [encomendas retidas na alfândega](guide:customs).

> Mensagens fraudulentas em nome da Omniva cobram pequena taxa de entrega ou alfândega por um link falso. A Omniva nunca manda SMS de números estrangeiros e envia e-mails somente de `omniva.ee`, `omniva.lv`, `omniva.lt` ou `omniva.eu`. Confira sua encomenda diretamente em `minu.omniva.ee`.

## Telefone e atendimento da Omniva

Canais listados nas páginas para clientes particulares da Omniva (horário local de cada país):

| País | Telefone | E-mail | Horário |
| --- | --- | --- | --- |
| Estônia | `+372 661 6616` | `info@omniva.ee` | Seg a sex 8:00–19:00, sáb 9:00–15:00 |
| Letônia | `+371 27042424` | `info@omniva.lv` | Seg a sex 8:00–18:00, sáb 9:00–15:00 |
| Lituânia | `+370 700 55077` | `info@omniva.lt` | Seg a sex 8:00–18:00, sáb 9:00–15:00 |

Cada site traz formulários para consulta de status, encomenda danificada/extraviada e redirecionamento. Na Lituânia, a tarifa telefônica depende da tabela da Telia. A Omniva não oferece chat ao vivo.

## Acompanhe a Omniva com o Peek

O [Peek](/) identifica imediatamente códigos `JJEE` de qualquer um dos dois formatos acima como Omniva. Links de `minu.omniva.ee`, `mana.omniva.lv` e `mano.omniva.lt` (`/track/` + código), bem como links de `omniva.ee` ou `omniva.lt` com `barcode=`, funcionam para qualquer código e não exigem código postal.

Um código postal terminado em `EE` que comece por `C`, `R`, `E` ou `L` (exceto `CR`, `CV` ou `RO`) e tenha dígito verificador válido pertence à Omniva pelo formato e é identificado na hora. Para outras letras iniciais, o Peek consulta Omniva e Chronopost juntas e registra naquela que reconhece o código; se nenhuma ainda o conhecer, exibe "Ainda não encontrada em …. O Peek continua verificando depois que você adicionar." e segue verificando com intervalos maiores ao longo dos dias. Um código identificado como Omniva que ela ainda não listou mostra "Aguardando Omniva"; se dois dias depois continuar desconhecido sem outra resposta, o Peek pede para conferir o código, trocar de transportadora ou excluir a encomenda, passando a consultar diariamente e, após uma semana, semanalmente. Códigos com dígito verificador inválido não são atribuídos a nenhuma transportadora e a Omniva os recusa mesmo escolhidos manualmente: confira a digitação. Códigos postais estrangeiros podem ser acompanhados pela Omniva usando "Trocar" ou colando um link da Omniva. Quando o correio remetente indica destino Estônia (ou não informa destino e você está na Estônia), o Peek também consulta a Omniva e prossegue nela assim que surge movimentação tão recente quanto a última do correio de origem (para Letônia ou Lituânia, consulta Latvijas Pasts ou Lietuvos paštas). Da mesma forma, códigos `EE` enviados ao exterior podem continuar sendo seguidos no correio de destino.

O Peek mostra os status em inglês da Omniva, o local da Omniva de cada movimentação no mapa, o país de destino, o horário de entrega e o parcel machine ou agência onde aguarda. Endereços, códigos postais e motivos de falha são descartados, e não há data prevista nem peso. Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta a Omniva até a cada 10 minutos — e a cada 2 minutos a partir das 6h (horário da Estônia) no dia em que sair para entrega, enquanto o próprio rastreador da Omniva responder; nos demais momentos, de hora em hora. Após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas depois de sete dias em "Etiqueta criada". Após quatro dias sem nova movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", sinaliza "Sem atualizações desde …" com "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Omniva." e um link "Pedir ajuda a Omniva"; após três semanas, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Omniva." Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Encomendas antigas podem manter apenas o registro final de entrega.

## Perguntas sobre o rastreamento da Omniva

### Quanto tempo a Omniva demora para entregar?

A Omniva informa 1 a 2 dias úteis dentro de um mesmo país báltico e 2 a 3 dias úteis entre eles, contados da entrega à Omniva. As páginas da Estônia e Lituânia dizem que entregas em domicílio ocorrem no dia seguinte e em até 3 dias; a página da Letônia cita apenas o prazo de 3 dias.

### Posso rastrear uma encomenda da Omniva pelo número de telefone?

As páginas da Omniva descrevem rastreamento apenas pelo código de envio. Uma conta no e-service em `minu.omniva.ee` reúne automaticamente suas futuras encomendas em um só lugar.

### O que significa "Delivery attempt made" na Omniva?

O entregador tentou entregar e não conseguiu; o texto apenas soa como algo concluído.

### Posso rastrear uma encomenda internacional para a Estônia na Omniva?

Sim, desde que o país remetente tenha registrado o objeto no banco internacional; antes de chegar à Estônia, consulte o site do correio de origem.

:::sources
- [Omniva Estonia: Track and receive parcels](https://www.omniva.ee/en/track-and-receive-parcels/) – shipment code, door code, receiving options, courier SMS and call
- [Omniva Latvia: Track and receive](https://www.omniva.lv/en/track-and-receive-parcels/) – receiving in Latvia, courier, 30 kg
- [Omniva Lithuania: Track and receive](https://www.omniva.lt/en/track-and-receive-parcels/) – receiving in Lithuania, courier, 25 kg
- [Omniva: How to track your parcel?](https://www.omniva.ee/help/how-to-track-your-parcel-ee) – tracking code by SMS or email, summary and details, parcels from abroad
- [Omniva: Parcel delivery timeframes](https://www.omniva.ee/help/omniva-parcel-delivery-timeframes-ee) – delivery times, courier next day
- [Omniva Latvia: Omniva sūtījumu piegādes termiņi](https://www.omniva.lv/palidziba/omniva-sutijumu-piegades-termini) – courier within 3 days (Latvian)
- [Omniva Lithuania: OMNIVA siuntų pristatymo terminai](https://www.omniva.lt/pagalba/omniva-siuntu-pristatymo-terminai-lt) – courier next working day (Lithuanian)
- [Omniva: How long items are stored by Omniva?](https://www.omniva.ee/help/how-long-items-are-stored-by-omniva-ee) – storage in each country, returns
- [Omniva Latvia: Cik ilgi Omniva uzglabā sūtījumus?](https://www.omniva.lv/palidziba/cik-ilgi-omniva-uzglaba-sutijumus) – reminder in Latvia (Latvian)
- [Omniva: Extend parcel storage time](https://www.omniva.ee/help/extend-parcel-storage-time-ee) – up to 4 days at a time, 12 extra days at most
- [Omniva: Will I get notified about a delivery?](https://www.omniva.ee/help/will-i-get-notified-about-a-delivery-ee) – notices, courier calls
- [Omniva: Issue with the delivery SMS or email notification](https://www.omniva.ee/help/i-have-an-issue-with-the-delivery-smsemail-notification) – app notification settings
- [Omniva: When courier parcels are delivered?](https://www.omniva.ee/help/when-courier-parcels-are-delivered-ee) – courier hours
- [Omniva: Will the courier deliver parcels to my door?](https://www.omniva.ee/help/will-the-courier-deliver-parcels-to-my-door-ee) – weight limits
- [Omniva: Not at the address when the courier delivers](https://www.omniva.ee/help/what-happens-if-im-not-at-the-delivery-address-when-the-courier-delivers-the-parcel-ee) – missed courier options
- [Omniva: Can someone else pick up my parcel?](https://www.omniva.ee/help/can-someone-else-pick-up-my-parcel-ee) – collection by another person
- [Omniva: Do I need to show ID?](https://www.omniva.ee/help/do-i-need-to-show-id-when-receiving-items-via-omniva-ee) – ID rules
- [Omniva: Redirect a parcel or letter](https://www.omniva.ee/help/redirect-a-parcel-or-letter-ee) – redirection rules and fees
- [Omniva: My parcel hasn't been delivered](https://www.omniva.ee/help/my-parcel-hasn%E2%80%99t-been-delivered-ee) – who acts when a parcel is late
- [Omniva: My parcel is damaged](https://www.omniva.ee/help/my-parcel-is-damaged-ee) – damage form, 6 months
- [Omniva Latvia: Sūtījumu pakalpojumu standarta noteikumi privātajiem klientiem](https://www.omniva.lv/wp-content/uploads/sites/6/2026/06/LV_Omniva-Standard-Terms-and-Conditions-of-Parcel-Services-for-Private-Customers_2026_07_22.pdf) – sender or recipient complains within 6 months (Latvian)
- [Omniva: The parcel machine locker is empty](https://www.omniva.ee/help/the-parcel-machine-locker-is-empty-or-a-shipment-is-missing) – contact support
- [Omniva: How to return a parcel?](https://www.omniva.ee/help/manage-shipments-general/return-a-parcel-containing-items-purchased-in-estonia-latvia-or-lithuania-ee) – example code CEXXXXXXXXEE, return code
- [Omniva Estonia: Customs: receiving](https://www.omniva.ee/en/customs-receiving/) – declaring within 30 days
- [Omniva: Which shipments require a customs declaration](https://www.omniva.ee/help/which-shipments-require-a-customs-declaration) – all non-EU parcels since 1 July 2021
- [Omniva Estonia: Contacts for private customers](https://www.omniva.ee/en/contacts-for-private-customers/) – phone, email, hours, forms, Eesti Post
- [Omniva Latvia: Contacts for private customers](https://www.omniva.lv/en/contacts-for-private-customers/) – phone, email, hours
- [Omniva Lithuania: Contacts for private customers](https://www.omniva.lt/en/contacts-for-private-customers/) – phone, email, hours, Telia tariff
- [Omniva: How to distinguish scams from genuine parcel notifications](https://www.omniva.ee/en/frauds/) – scam signs, phone numbers, email domains
- [Omniva Estonia: Home](https://www.omniva.ee/en/) – scam warning, e-service
- [Omniva: Tracker interface strings](https://minu.omniva.ee/locales/common/common_en.json) – tracker languages
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
