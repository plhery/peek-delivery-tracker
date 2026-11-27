---
title: Rastreio Chronodiali: códigos CHPU, status e contato
description: Rastreie encomendas Chronodiali no Marrocos: código CHPU, significado dos status em francês, pagamento na entrega, três tentativas e canais de contato.
slug: chronodiali-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Chronodiali (também grafada Chrono Diali) é uma empresa marroquina de entregas para lojas virtuais, fruto de uma joint venture entre o Barid Al-Maghrib (Poste Maroc) e a Geopost. Ela entrega encomendas de e-commerce em todo o Marrocos, na sua porta ou em uma de suas agências. Se a loja escolheu pagamento na entrega, você paga ao entregador na chegada do pacote, em dinheiro ou no cartão bancário. Rastreie em "Suivre mon colis" no site [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) ou na caixa acima. O código é enviado pela loja; o exemplo oficial da Chronodiali começa com `CHPU`.

## Seu código de rastreio Chronodiali

Os formatos que o Peek conhece, com dígitos inventados:

| Código | O que é |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` seguido de 19 dígitos: código da Chronodiali. O Peek reconhece na hora. |
| `CD4829103756` | `CD` seguido de 10 dígitos. O Peek também pode rastrear na Chronodiali, mas o mesmo formato serve para [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) e [Aramex New Zealand](carrier:aramex-nz), então o Peek consulta as quatro para saber qual tem o pacote (veja abaixo). |
| `chronodiali.ma/suivi_colis.html?id=…` | Link da página de rastreio da Chronodiali. O Peek identifica como Chronodiali seja qual for o código contido nele. |

A Chronodiali não é a [Chronopost](carrier:chronopost), transportadora expressa francesa: seus códigos e rastreadores são independentes. Não recebeu nenhum código? Fale com a loja ou veja [onde encontrar o código de rastreio](guide:find-tracking-number).

## Significado dos status Chronodiali em português

A Chronodiali escreve os registros de rastreio em francês. Na ordem habitual de uma entrega:

| A Chronodiali exibe | O Peek mostra | O que significa para você |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Etiqueta criada | A loja enviou os dados do pacote à Chronodiali, que ainda não está com ele. |
| `Le ramassage a été programmé.` | Etiqueta criada | Uma coleta na loja foi agendada. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Etiqueta criada | Aguardando o entregador coletar a encomenda no remetente. |
| `Le colis a été collecté par le livreur.` | Recebida pela transportadora | O entregador coletou o pacote. |
| `Le colis est arrivé au hub d'origine.` | Em trânsito | Chegou ao centro de origem da Chronodiali. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | Em trânsito | Movimentando-se entre os centros da Chronodiali. |
| `Delivery Scheduled` | Em trânsito | Incluído em uma rota de entrega no centro de destino. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | Saiu para entrega | Está com o entregador hoje. Se houver pagamento na entrega, deixe o valor separado. |
| `Le colis a été livré.` | Entregue | Entregue. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Tentativa de entrega | O entregador não conseguiu contato com você por telefone ou na porta. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Tentativa de entrega | O endereço está incorreto ou o entregador não o encontrou. |
| `La livraison a échoué : le client est indisponible.` | Tentativa de entrega | Você não estava disponível. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Tentativa de entrega | Você não tinha o valor exigido do pagamento na entrega (COD). |
| `La livraison a échoué : le client a refusé le colis.` | Tentativa de entrega | A encomenda foi recusada. |
| `La livraison a échoué.` | Tentativa de entrega | Falha na entrega, sem motivo informado. |
| `Le colis est retenu au hub.` | Precisa de atenção | O pacote está retido em um centro de distribuição. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Precisa de atenção | A encomenda está danificada e não pode ser entregue. |
| `Le colis a été annulé.` | Precisa de atenção | O envio foi cancelado. |
| `Le colis va être renvoyé à l'expéditeur.` | Devolvida ao remetente | O pacote vai voltar para a loja. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Devolvida ao remetente | Voltou ao endereço da loja. |

**O que não aparece em chronodiali.ma.** A página oficial da Chronodiali omite algumas dessas linhas: `Delivery Scheduled`, `Out For Delivery`, falhas de entrega, cancelamentos ou reagendamentos e `Le colis a été confié à un partenaire tiers.` O Peek exibe esses eventos, portanto seu histórico pode ser mais completo que o do site.

**Outras mensagens.** `La livraison a été reprogrammée : une nouvelle date a été définie.` significa que a entrega passou para uma nova data; `La livraison a été reprogrammée.` não traz detalhes. `La livraison a été annulée à la demande du client.` indica cancelamento a pedido do cliente. `Le colis est en attente au hub.` indica pacote aguardando em um centro. Sobre as duas linhas de `partenaire tiers`, veja as perguntas abaixo; o Peek mostra ambas como "Em trânsito".

**Encomendas do exterior.** Alguns pacotes entram no Marrocos vindos de fora. Nesse caso podem aparecer `arrivé dans le pays.` (chegou ao Marrocos; o Peek exibe "Em trânsito"), `dédouanement en cours` (desembaraço alfandegário em andamento; "Na alfândega") e `dédouanement terminé` (liberado pela alfândega; "Em trânsito"). O site da Chronodiali não trata de taxas nem de quem paga: pergunte à loja e consulte [encomendas retidas na alfândega](guide:customs).

## Entregas Chronodiali: pagamento na entrega, três tentativas e agências

A Chronodiali informa que entrega em todo o Marrocos, em mais de 300 destinos, em domicílio ou em agência.

- **Pagamento na entrega:** a página de rastreio mostra o valor a pagar, `Montant à payer`, em dirhams (DH). A Chronodiali informa que você pode pagar na entrega em dinheiro ou com cartão bancário.
- **Três tentativas:** a Chronodiali lista "3 Tentatives de livraison" (três tentativas de entrega) entre seus serviços. No Peek, cada tentativa sem sucesso aparece em uma linha iniciada por `La livraison a échoué`, com ou sem motivo.
- **Mantenha o celular ligado:** um dos motivos de falha de entrega é não atender ou ficar incontactável.
- **Notificações:** a página de serviços lista avisos por SMS e e-mail, com notificação ao cliente em cada etapa do trajeto.
- **Agências:** Agadir, Casablanca, Fès, Marrakech, Rabat e Tânger, abertas de segunda a sexta das 9h às 18h e aos sábados das 9h às 13h, no horário do Marrocos. Os endereços estão na página "Points de livraison".

O site da Chronodiali não diz por quantos dias uma agência guarda o pacote nem o que ocorre após a terceira tentativa frustrada. Pergunte à Chronodiali ou à loja. Perdeu o entregador? Veja [entregas perdidas](guide:missed-delivery).

## Encomenda Chronodiali atrasada, recusada, devolvida ou não recebida

Para reembolso ou novo envio, fale com a loja. É a loja quem tem contrato com a Chronodiali e agenda as coletas na plataforma corporativa.

- **Sem avanço após `Les données du colis ont été intégrées dans nos systèmes.`:** a loja gerou o envio, mas a Chronodiali ainda não coletou o pacote. Pergunte à loja quando fará o despacho. Se chronodiali.ma exibir `En cours de traitement.` (em processamento), a Chronodiali já conhece o código, mas ainda não há leitura registrada.
- **Atraso:** a Chronodiali não divulga prazos fixos. Seus termos dizem que os prazos são informados no pedido e variam conforme o destino. Quando o último registro tiver quatro dias ou mais e o pacote ainda estiver em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia daquele evento (por exemplo, "Sem atualização de rastreamento desde seg 12 out") e mostra um link para pedir ajuda à Chronodiali. Veja [rastreio sem atualização](guide:tracking-not-updating).
- **Recusada ou não paga:** se você recusar o pacote ou não puder pagar, o entregador registra falha na entrega e a encomenda pode voltar à loja (`Le colis va être renvoyé à l'expéditeur.`, depois `Le colis a été retourné à l'adresse de l'expéditeur.`). Veja [devolvida ao remetente](guide:returned-to-sender).
- **Entregue mas não recebida:** a Chronodiali disponibiliza comprovante digital de entrega no portal da loja. Peça à loja para conferir. Saiba mais em [entregue mas não recebida](guide:delivered-not-received).
- **Danificada ou perdida:** avise primeiro a loja. Você também pode marcar "Réclamation ?" no formulário de contato da Chronodiali, ou ligar/enviar e-mail informando seu código. Veja [encomendas danificadas](guide:damaged-parcel) e [encomendas perdidas](guide:lost-parcel).
- **Mensagem cobrando dinheiro:** em seu site, a Chronodiali descreve o pagamento como feito na entrega, em dinheiro ou cartão. Se receber SMS ou e-mail pedindo pagamento por link, confira o rastreio antes e leia [golpes por SMS de entrega](guide:delivery-scam-texts).

## Telefone e atendimento ao cliente Chronodiali

Em chronodiali.ma:

- **Telefone:** +212 5 20 61 62 63 (05 20 61 62 63 ligando do Marrocos).
- **E-mail:** service-client@chronodiali.ma.
- **Formulário de contato:** na página [Contact](https://chronodiali.ma/contact_us.html), selecione "Réclamation ?" para reclamação ou "Demande d'information ?" para dúvida. O formulário pede apenas nome, e-mail, telefone e nome da loja, sem campo para mensagem ou código de rastreio; a Chronodiali informa que entrará em contato.
- **Agências:** as seis listadas acima, com o mesmo telefone e e-mail.

O site não informa horário separado para o atendimento telefônico e não divulga número de WhatsApp.

## Acompanhe sua encomenda Chronodiali no Peek

Cole um código `CHPU` e o [Peek](/) identifica a Chronodiali na hora em "Transportadora detectada". Letras minúsculas e espaços não atrapalham. Um link para a página de rastreio em chronodiali.ma também funciona.

Um código `CD` com 10 dígitos não indica sozinho a transportadora. Mesmo assim, cole-o: o Peek acompanha mais de 4.500 transportadoras e mostra "Consultando serviços de rastreamento…" enquanto verifica a Chronodiali e as outras empresas que compartilham esse formato; se somente a Chronodiali tiver o pacote, o nome dela aparece confirmado. Ou toque em "Alterar" e escolha "Chronodiali" em "Compatível com este código". Se selecionar Chronodiali para um código de outro formato, o Peek avisa que ela não emite códigos desse tipo e pede para conferir o número.

O Peek lê o rastreamento próprio da Chronodiali: o status, cada linha exatamente como ela escreve (em francês ou, em dois casos acima, em inglês), o nome do hub quando informado, o país de destino e o horário de entrega, convertendo os horários para o fuso do seu dispositivo. Ele omite nome e cidade do remetente, valor a pagar, comprovante de entrega e ligações do entregador. Quando a Chronodiali envia esses dados, você vê também o nome do destinatário em "Destinatário" e, durante o trajeto, a cidade de destino no mapa se o Peek conseguir localizá-la no Marrocos; quem receber o link compartilhado não vê nenhum desses dois dados, e o nome de quem assinou permanece privado em qualquer aplicativo. A Chronodiali não informa data prevista de entrega nem peso. As linhas sobre empresa parceira não identificam a parceira, e o Peek não acompanha o rastreamento externo dela.

Se a Chronodiali ainda não conhecer o código, o Peek mostra "Aguardando Chronodiali" e segue consultando; se após dois dias o código continuar desconhecido, pede que você confira o número, escolha outra transportadora ou exclua a encomenda. Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário do Marrocos no dia em que a Chronodiali registrar a saída para entrega, enquanto o rastreador oficial responder. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". Você pode receber notificações em "Cada atualização", "Etapas importantes" ou "Somente entrega". O Peek não efetua pagamentos, não reagenda entregas nem contata a Chronodiali ou a loja por você.

## Perguntas sobre o rastreio Chronodiali

### A Chronodiali faz parte do Poste Maroc?

Em parte. A página de notícias da Chronodiali a descreve como uma joint venture: o Barid Al-Maghrib (Poste Maroc) detém 51% e a Geopost, 49%. Ela tem rastreamento próprio em chronodiali.ma e atendimento separado.

### Quantas vezes a Chronodiali tenta entregar?

Três vezes, segundo a Chronodiali. No Peek, cada tentativa sem sucesso aparece numa linha iniciada por `La livraison a échoué`, com ou sem motivo em seguida.

### Posso pagar uma encomenda Chronodiali com cartão?

Sim. A Chronodiali informa que o pagamento na entrega pode ser feito em dinheiro ou com cartão bancário.

### O que significa "partenaire tiers" no rastreio da Chronodiali?

`Le colis a été assigné à un partenaire tiers.` e `Le colis a été confié à un partenaire tiers.` indicam que a Chronodiali atribuiu ou repassou o pacote a uma empresa parceira. O rastreio não informa qual é essa empresa. Aguarde a próxima atualização ou consulte a Chronodiali com seu código.

### Como entrar em contato com a Chronodiali sobre minha encomenda?

Ligue para +212 5 20 61 62 63 ou escreva para service-client@chronodiali.ma informando o código de rastreio. Para reembolso ou novo envio, fale com a loja.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib e Geopost, pagamento na entrega em dinheiro ou cartão, entrega em casa ou agência, mais de 300 destinos, agências e horários, telefone, e-mail
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – rastreador, exemplo `CHPU`, valor a pagar, linhas de status em francês, o que a página mostra e "En cours de traitement." (no script da página)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – três tentativas de entrega, notificações por SMS e e-mail, dinheiro ou cartão, comprovante digital de entrega
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – entrega em todo o Marrocos, agências e horários
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – contas e coletas de lojas, comprovantes de entrega para lojistas
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – formulário de contato com "Réclamation ?" e seus campos, telefone, e-mail
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – joint venture, 51% Barid Al-Maghrib e 49% Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; termos sobre contrato da loja, coletas e prazos informados no pedido
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de código, linhas de status e suas etapas, o que a página da Chronodiali e o Peek exibem
:::
