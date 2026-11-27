---
title: Rastreamento Latvijas Pasts: códigos, status e armários
description: Rastreie encomendas dos Correios da Letônia (Latvijas Pasts): formato LV, status em inglês, armários de encomendas, entregas não realizadas e contatos.
slug: rastreamento-latvijas-pasts
published: 2026-11-25
updated: 2026-11-25
---

A Latvijas Pasts (Correios da Letônia) é o serviço postal estatal da Letônia e opera a maior rede de armários de encomendas do país. Rastreie um envio em [mans.pasts.lv/track](https://mans.pasts.lv/track), que abre em letão e oferece opção em inglês, ou cole o código na caixa acima. O código vem na mensagem de envio da loja ou do remetente, ou no SMS enviado pelo entregador antes da entrega.

## Código de rastreamento da Latvijas Pasts

Um objeto postado pela Latvijas Pasts tem um [número postal internacional](guide:tracking-number-formats) de 13 caracteres: duas letras, nove dígitos e `LV`. As letras iniciais indicam o serviço:

| Letras iniciais (exemplos inventados) | O que é |
| --- | --- |
| `RR482917367LV` | Objeto registrado: exige assinatura no recebimento. |
| `LA482917367LV` | Objeto rastreável: acompanhado em cada etapa e deixado na caixa de correio sem assinatura. |
| `CA482917367LV` | Encomenda: você recebe um aviso para retirar na agência. |
| `EE482917367LV` | Express Mail Service (EMS). |

Envios Simple ou Economy não são registrados em nenhuma etapa, portanto não têm rastreamento nem indenização em caso de dano. Se você não tem código, o envio pode ter sido feito nessa modalidade; veja [como encontrar um código de rastreamento](guide:find-tracking-number).

Onde procurar: o SMS do entregador traz o número do envio, o horário previsto de chegada e um link para redirecionar o pacote. O SMS do armário informa o endereço, o prazo final de retirada e o código de abertura.

Outras letras finais indicam outros correios: `…LT` é [Lietuvos paštas](carrier:lietuvos-pastas) e `…EE` é [Omniva](carrier:omniva). Objetos vindos do exterior mantêm o código estrangeiro, e mans.pasts.lv/track também os aceita assim que a Latvijas Pasts recebe o envio; etapas no exterior mostram o país em vez de uma agência letã.

## O que significam os status da Latvijas Pasts

O rastreador mostra cada etapa com data, hora e a agência na Letônia. Acima da lista, uma barra de cinco etapas vai de `Shipment created` até `Delivered to recipient`. Estes são os textos em inglês, na ordem em que a encomenda passa por eles:

| Status da Latvijas Pasts | O que significa para você |
| --- | --- |
| `Electronic data about shipment received` | [Os dados do remetente](guide:tracking-statuses) chegaram à Latvijas Pasts. Ela ainda não recebeu o objeto. |
| `Item information received` | O remetente registrou o envio; a Latvijas Pasts também ainda não está com ele. |
| `Item received from sender` | A Latvijas Pasts recebeu o objeto do remetente. |
| `Item posted / received from customer` | Postado em uma agência; o registro de postagem nos correios estrangeiros em envios do exterior usa o mesmo texto. |
| `Item sent to the processing facility` | Saiu da agência de postagem rumo ao centro de triagem. |
| `Item arrived at office of exchange` | No centro internacional de permuta, em Riga ou no exterior. |
| `Item sent to the Customs` | Apresentado à alfândega no centro de permuta ao sair do país de origem. |
| `Item returned from Customs` | Liberado pela alfândega. Não é devolução ao remetente. |
| `Item sent abroad` | Saiu do centro de permuta rumo ao país de destino. |
| `Item sent abroad (EDI-received)` | Os correios no exterior despacharam o objeto para a Letônia; a Latvijas Pasts recebeu os dados eletrônicos. |
| `Item accepted for temporary storage` | Objeto do exterior retido em depósito temporário sob controle aduaneiro até a liberação. Não é uma coleta do remetente. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Foi enviado um SMS sobre o desembaraço aduaneiro (grafia original da Latvijas Pasts). O item segue em depósito temporário. |
| `Customs clearance completed, item to be delivered to recipient` | Liberado pela alfândega; segue para a agência entregadora. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Chegou a um centro de triagem. |
| `Item sent to next processing facility` | A caminho do próximo centro de triagem. |
| `Item sent to delivery facility` | A caminho da agência responsável pela entrega ou retirada. |
| `Item received at delivery office` | Na agência que fará a entrega ou disponibilizará para retirada. Ainda pode sair para entrega dali. |
| `Item redirected` | Encaminhado para outro local, como outro ponto de retirada (veja Redirecionamento abaixo). |
| `Item address re-labelled` | Reetiquetado com o novo endereço, para onde segue agora. |
| `Item handed over for delivery` | Saiu para entrega. |
| `Unsuccessful delivery attempt` | Entrega não realizada (veja abaixo). Porém, quando ocorre no mesmo segundo que `Item address re-labelled`, registra um redirecionamento: o envio continua a caminho, e o Peek exibe "Em trânsito", não "Tentativa de entrega". |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Pronto para retirada: a agência imprimiu o aviso ou enviou o convite por SMS. |
| `Item arrived at pick-up point` | Registro de correios estrangeiros: aguarda em ponto de retirada no exterior, informando o país e não o ponto. |
| `Item delivered` | Entregue, inclusive quando os correios de destino entregam um objeto enviado ao exterior. |

`Item returned from Customs` costuma assustar, mas significa apenas que o objeto voltou da inspeção aduaneira e retomou o trajeto, não que está sendo devolvido ao remetente. `Item awaiting next planned cycle` indica espera pelo próximo despacho no centro de permuta. Encomendas de lojas roteadas por centro de trânsito usam frases próprias: `Parcel notify received` e `Parcel data received` (apenas dados, ainda não entregue à transportadora), `Parcel has arrived at transit location` (primeiro evento), `Parcel collected at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile` (chegou à rede do país de destino) e `Parcel has gone thought last mile customs` (grafia do rastreador: passou pela alfândega local). Sobre tributos e taxas da UE para compras de fora da UE, veja [encomendas retidas na alfândega](guide:customs).

Se o rastreador disser `No information received on the tracking of the postal item. Please, try again!`, confira o código ou aguarde: o objeto pode ainda não ter sido escaneado, e envios do exterior aparecem quando os correios de origem enviam os dados. `Postal item number entered incorrectly. Please check it and enter it again.` indica erro de digitação. Se um código correto continuar desconhecido por vários dias, pergunte ao remetente se o envio tinha rastreamento: modalidades Simple e Economy nunca aparecem.

## Armários, entregas em casa e ausências na Latvijas Pasts

**Armários de encomendas.** Ao chegar a um armário, você recebe um SMS com endereço, prazo e código de seis dígitos (e e-mail, se o remetente informou). Fica guardado por até sete dias; se ainda estiver lá no terceiro dia, o código é reenviado e você recebe lembrete antes do fim do prazo. Sem retirada em sete dias, conta como não entregue e volta ao remetente; objetos com guarda maior seguem para o Centro de Atendimento ao Cliente mais próximo, onde aguardam por 30 dias. Se o código não chegou, peça ao remetente para contatar a Latvijas Pasts: o telefone só é corrigido a pedido do remetente. Os armários aceitam até 30 kg e 38 × 38 × 58 cm.

**Armários Unisend.** Pacotes enviados da Letônia para armários Unisend na Lituânia ou na Estônia também aguardam sete dias, com código enviado por SMS e e-mail (para números letões, lituanos ou estonianos) e reenviado após 48 horas. Sem retirada, retornam diretamente ao remetente.

**Entregador em domicílio.** A Latvijas Pasts informa realizar até três tentativas. Se falhar e você não tiver redirecionado, o entregador deixa aviso e retorna; falhando novamente, o pacote vai para guarda: você pode retirar na Agência Central ou ligar para o atendimento para outra opção. O remetente define o prazo de guarda. Em envios Express Mail (EMS) sem ninguém no endereço, fica um aviso "You have received a shipment" na caixa de correio: ligue para o número do aviso com o código em mãos para reagendar.

**Agência ou Centro de Atendimento.** Você recebe SMS ou aviso impresso indicando a agência. Leve documento de identidade; carteira de motorista vale para itens de até €150. É possível retirar sem o aviso se você souber o número do envio, ou ligar para o número do aviso para pedir entrega em casa pagando a taxa na entrega. Outra pessoa pode retirar com sua autorização e passaporte ou identidade própria. Em objetos registrados ou segurados, há segundo aviso após 5 dias, e devolução ao remetente se não retirados em até 30 dias do primeiro. Se a etiqueta de uma compra internacional tiver apenas parte do seu nome, leve comprovante do pedido junto com seu documento.

**Redirecionamento.** Se o remetente forneceu seu telefone ou você ativou alertas por SMS, a mensagem traz link para enviar o pacote a um armário, Centro de Atendimento, outro endereço ou ao serviço gratuito "Postman at home" onde disponível. A página de redirecionamento indica armário gratuito, Centro de Atendimento por €1,89 e entrega por courier em domicílio por €4,99, com IVA incluso. Não é possível redirecionar item já em armário ou sob controle aduaneiro, nem mandar para armário um objeto registrado ou segurado com aviso de recebimento, ou um objeto com pagamento na entrega maior que 38 × 38 × 58 cm. Ao ativar SMS, você também pode definir um armário favorito.

## Encomenda Latvijas Pasts perdida, danificada ou atrasada

Reclamações por perda ou dano em encomendas de armário devem partir do remetente, então em compras online escreva para a loja. O pedido lista o conteúdo e o valor, com recibo, fatura ou comprovante de pagamento e fotos de eventuais danos. A Latvijas Pasts analisa em até um mês.

Quando um entregador trouxer um objeto registrado ou segurado, confira o conteúdo na frente dele para lavrar termo. Se abrir sozinho e encontrar danos, tire fotos e procure o remetente para troca ou reembolso; a Latvijas Pasts afirma não responder por indenização nesse caso.

Para envios ao exterior que não chegaram, o remetente pode pedir busca internacional: é cobrada no primeiro mês após a postagem e gratuita depois de 30 dias.

Rastreamento parado ou retido na alfândega? Veja [rastreamento sem atualização](guide:tracking-not-updating) e [encomendas retidas na alfândega](guide:customs).

## Contato e atendimento da Latvijas Pasts

Canais publicados em pasts.lv:

- **Telefone:** +371 27008001 (os termos dos armários informam também +371 67008001).
- **E-mail:** info@pasts.lv.
- **Horário:** segunda a sexta das 8h às 19h, sábado das 9h às 17h; fechado aos domingos.
- **Relatar problema:** formulário em [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), atendido no horário do atendimento ao cliente.

> A Latvijas Pasts alerta para SMS e e-mails falsos pedindo confirmação de dados ou endereço, ou pagamento alfandegário em sites falsificados. E-mails verdadeiros terminam sempre em @pasts.lv, o site é www.pasts.lv e ela nunca pede dados bancários ou de cartão por SMS ou e-mail, nem confirmação pelo Smart-ID.

## Rastrear Latvijas Pasts com o Peek

O [Peek](/) identifica na hora como Latvijas Pasts qualquer número postal iniciado por C, E, L ou R e terminado em `LV` com dígito verificador válido. Para outros códigos válidos terminados em `LV`, como `VV482917367LV`, consulta Latvijas Pasts e [Chronopost](carrier:chronopost) simultaneamente e exibe "Latvijas Pasts está com esta encomenda." quando ela conhece o código, ou "Ainda não encontrada em …. O Peek continua verificando depois que você adicionar." Com dígito verificador incorreto, a Latvijas Pasts não é consultada e selecioná-la manualmente não ajuda (o Peek avisa que ela "não emite códigos como este"): confira o código com o remetente. Um link de mans.pasts.lv/track com o código sempre seleciona Latvijas Pasts; buscar "Latvian Post" no seletor também a encontra. Se a Latvijas Pasts ainda não conhecer o código, a encomenda registrada com ela mostra "Aguardando Latvijas Pasts" e o Peek continua verificando. Se dois dias depois continuar desconhecido e nada mais responder, o Peek exibe "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a consultar diariamente e, após uma semana, semanalmente.

Objetos vindos do exterior são acompanhados primeiro nos correios de origem. Quando eles indicam a Letônia como destino (ou não informam destino enquanto você está na Letônia), o Peek consulta também a Latvijas Pasts com o mesmo código e muda para ela assim que houver um evento mais recente, mantendo o histórico anterior. Para acompanhar pela Latvijas Pasts desde o início, escolha "Latvijas Pasts" em "Trocar" ao adicionar ou use "Trocar transportadora" em um envio já salvo.

Os horários da Latvijas Pasts não trazem fuso: o Peek os lê no relógio do local do evento (horário letão para agências na Letônia) e lista o histórico próprio da Latvijas Pasts. Os status mantêm o inglês da Latvijas Pasts, incluindo `Item delivered`. O Peek pode indicar a agência onde o envio aguarda e notificar mudanças de status, mas não informa previsão de entrega nem peso. Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, ele verifica até a cada 10 minutos; no dia em que a Latvijas Pasts registra saída para entrega, a cada 2 minutos a partir das 6h no relógio do local desse evento (horário letão na Letônia), enquanto o rastreador da própria Latvijas Pasts responde. Nos demais horários, verifica a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da Latvijas Pasts não responde e o Peek acompanha apenas por serviços de rastreamento, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia em que sai para entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando outra transportadora assume a entrega, o Peek segue o ritmo dela.

Quando o evento mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca essa data (como "Sem atualizações desde seg 12 out"). A página passa a exibir "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Latvijas Pasts.", com o link "Pedir ajuda a Latvijas Pasts" para a página de atendimento da Latvijas Pasts. Após três semanas sem novos eventos, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Latvijas Pasts."

O Peek pode notificar "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Perguntas frequentes sobre os Correios da Letônia

### O que é a Latvijas Pasts?

É o serviço postal nacional estatal da Letônia, que oferece serviços postais, logísticos e financeiros na Letônia e no exterior. Seu site é pasts.lv.

### A Unisend é a Latvijas Pasts?

Não. Unisend é a marca de encomendas da Lietuvos paštas, os correios da Lituânia. As duas operam uma rede conjunta de armários, permitindo enviar da Letônia para armários Unisend na Lituânia e na Estônia.

### Posso rastrear um envio internacional da Latvijas Pasts?

Sim, se foi postado com rastreamento. O site mans.pasts.lv/track também mostra eventos recebidos dos correios no exterior, com o país no lugar da agência. No exterior, os correios de destino fazem a entrega; o botão "International Tracking" do rastreador leva a um site de terceiros.

### Correio comum da Letônia tem código de rastreamento?

Nem sempre. Envios Simple e Economy não são registrados e não podem ser rastreados; registrados, rastreáveis, encomendas e EMS têm rastreamento.

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – letras por serviço, ausência de indenização para Simple, FAQ de rastreamento, busca internacional paga no primeiro mês e gratuita após 30 dias, horários, telefone e formulário de reclamação
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – telefone, e-mail e horários
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – modalidades de recebimento e autorização
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – SMS do entregador, três tentativas, guarda, Agência Central e Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – SMS do armário, sete dias, lembrete, 30 kg, 38 × 38 × 58 cm e armário favorito
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – segundo aviso após 5 dias, devolução após 30 dias, aviso EMS e comprovante para nome incompleto
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – avisos, documento, habilitação até €150, retirada sem aviso e entrega domiciliar paga
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – link de redirecionamento, opções, tarifas e restrições
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – reclamação pelo remetente, conteúdo, valor, comprovantes e análise em um mês
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – conferência na presença do entregador e pedido ao remetente
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – código de seis dígitos, e-mail, reenvio no terceiro dia, sete dias no armário, 30 dias no Centro de Atendimento, +371 67008001, correção de telefone e armários Unisend na Lituânia e Estônia
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – alerta de SMS e e-mails falsos
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – rastreador em inglês, barra de progresso, textos de status, resposta sem dados e botão International Tracking
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – correios nacionais estatais e serviços
- [Latvijas Pasts: Latvijas Pasts and UNISEND launch their joint parcel locker network](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend como marca da Lietuvos paštas e armários conjuntos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e mostra, status e significados, resposta de código incorreto, reconhecimento direto, recusa e transição do exterior para a Latvijas Pasts
:::
