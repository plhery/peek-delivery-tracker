---
title: Seguimento Chronodiali: números CHPU, estados e contacto
description: Acompanha uma encomenda Chronodiali em Marrocos: número CHPU, estados em francês explicados, pagamento na entrega, três tentativas e contactos.
slug: seguimento-chronodiali
published: 2026-11-23
updated: 2026-11-23
---

A Chronodiali (também escrita Chrono Diali) é uma empresa marroquina de entregas para lojas online, uma parceria entre a Barid Al-Maghrib (Poste Maroc) e a Geopost. Entrega encomendas de comércio eletrónico em todo o território de Marrocos, ao domicílio ou numa das suas agências. Se a loja escolheu pagamento na entrega, pagas ao estafeta quando a encomenda chega, em dinheiro ou por cartão bancário. Segue-a em «Suivre mon colis» em [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) ou na caixa acima. O número vem da loja que enviou a encomenda; o exemplo da Chronodiali começa por `CHPU`.

## O teu número de seguimento Chronodiali

Os formatos que o Peek conhece, com dígitos inventados:

| Número | O que é |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` e 19 dígitos: um número Chronodiali. O Peek reconhece-o de imediato. |
| `CD4829103756` | `CD` e 10 dígitos. O Peek também o acompanha na Chronodiali, mas o mesmo formato cabe na Colis Privé, Aramex Austrália e [Aramex Nova Zelândia](carrier:aramex-nz), por isso o Peek consulta as quatro (ver abaixo). |
| `chronodiali.ma/suivi_colis.html?id=…` | Uma ligação para a página de seguimento da Chronodiali. O Peek identifica-a como Chronodiali qualquer que seja o número incluído. |

A Chronodiali não é a [Chronopost](carrier:chronopost), a transportadora expresso francesa: os números e sistemas de seguimento são diferentes. Sem número? Pergunta à loja ou consulta [onde encontrar um número de seguimento](guide:find-tracking-number).

## Estados de seguimento Chronodiali explicados

A Chronodiali escreve os estados em francês. Pela ordem habitual em que surgem:

| A Chronodiali diz | O Peek mostra | O que significa para ti |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Etiqueta criada | A loja enviou os dados da encomenda à Chronodiali, que ainda não a recebeu. |
| `Le ramassage a été programmé.` | Etiqueta criada | Foi marcada uma recolha na loja. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Etiqueta criada | À espera de o estafeta recolher a encomenda no remetente. |
| `Le colis a été collecté par le livreur.` | Aceite pela transportadora | O estafeta já recolheu a encomenda. |
| `Le colis est arrivé au hub d'origine.` | Em trânsito | Chegou ao primeiro hub da Chronodiali. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | Em trânsito | Em circulação entre hubs da Chronodiali. |
| `Delivery Scheduled` | Em trânsito | Colocada numa rota de entrega no hub de destino. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | Em distribuição | Saiu com o estafeta hoje. Se pagas na entrega, tem o valor pronto. |
| `Le colis a été livré.` | Entregue | Entregue. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Tentativa de entrega | O estafeta não conseguiu contactar-te por telefone nem à porta. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Tentativa de entrega | A morada está errada ou não foi encontrada. |
| `La livraison a échoué : le client est indisponible.` | Tentativa de entrega | Não estavas disponível. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Tentativa de entrega | Não tinhas o valor exigido para pagamento na entrega (COD). |
| `La livraison a échoué : le client a refusé le colis.` | Tentativa de entrega | A encomenda foi recusada. |
| `La livraison a échoué.` | Tentativa de entrega | Falhou sem motivo especificado. |
| `Le colis est retenu au hub.` | Problema reportado | A encomenda está retida num hub. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Problema reportado | A encomenda está danificada e não pode ser entregue. |
| `Le colis a été annulé.` | Problema reportado | O envio foi cancelado. |
| `Le colis va être renvoyé à l'expéditeur.` | Devolvida ao remetente | Vai regressar à loja. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Devolvida ao remetente | Voltou à morada da loja. |

**Ausente em chronodiali.ma.** A própria página de seguimento da Chronodiali omite algumas destas linhas: `Delivery Scheduled`, `Out For Delivery`, entregas falhadas, entregas canceladas ou reagendadas e `Le colis a été confié à un partenaire tiers.` O Peek mostra-as, pelo que o histórico pode ser mais completo do que o do site oficial.

**Outras linhas.** `La livraison a été reprogrammée : une nouvelle date a été définie.` significa que a entrega passou para nova data; `La livraison a été reprogrammée.` não dá pormenores. `La livraison a été annulée à la demande du client.` indica cancelamento pedido pelo cliente. `Le colis est en attente au hub.` significa que aguarda num hub. Sobre as duas frases com `partenaire tiers`, vê as perguntas abaixo; o Peek mostra ambas como «Em trânsito».

**Encomendas do estrangeiro.** Algumas chegam a Marrocos vindas de outro país. Podes ver `arrivé dans le pays.` (chegou a Marrocos; o Peek mostra «Em trânsito»), `dédouanement en cours` (desalfandegamento em curso; «Na alfândega») e `dédouanement terminé` (libertada pela alfândega; «Em trânsito»). O site da Chronodiali não indica direitos aduaneiros nem quem os paga: pergunta à loja e consulta [encomendas retidas na alfândega](guide:customs).

## Entrega Chronodiali: pagamento na entrega, três tentativas e agências

A Chronodiali refere entregar em todo o Marrocos, em mais de 300 destinos, ao domicílio ou numa agência.

- **Pagamento na entrega:** a página de seguimento indica o valor a pagar (`Montant à payer`) em dirhams (DH). Segundo a Chronodiali, podes pagar na entrega em dinheiro ou por cartão bancário.
- **Três tentativas:** a Chronodiali lista «3 Tentatives de livraison» entre os seus serviços. No Peek, cada tentativa falhada surge numa linha que começa por `La livraison a échoué`, com ou sem motivo.
- **Mantém o telemóvel ligado:** um dos motivos de entrega falhada é não atender ou estar incontactável.
- **Notificações:** a página de serviços lista avisos por SMS e e-mail e uma notificação ao cliente em cada etapa.
- **Agências:** Agadir, Casablanca, Fez, Marraquexe, Rabat e Tânger, abertas de segunda a sexta-feira das 9:00 às 18:00 e sábado das 9:00 às 13:00 (hora de Marrocos). As moradas constam da página «Points de livraison».

O site não diz durante quantos dias uma agência guarda a encomenda nem o que acontece após a terceira tentativa falhada: pergunta à Chronodiali ou à loja. Falhaste o estafeta? Vê [entregas falhadas](guide:missed-delivery).

## Encomenda Chronodiali atrasada, recusada, devolvida ou não recebida

Para reembolso ou novo envio, fala com a loja: é ela a cliente da Chronodiali e marca as recolhas através da plataforma do seu contrato.

- **Sem movimento após `Les données du colis ont été intégrées dans nos systèmes.`:** a loja registou o envio, mas a Chronodiali ainda não recolheu a encomenda. Pergunta à loja quando a entrega. Se chronodiali.ma mostrar `En cours de traitement.`, a Chronodiali conhece o número mas ainda não tem registos.
- **Atrasada:** a Chronodiali não publica prazos de entrega. As condições indicam que são dados na encomenda e variam com o destino. Vê [seguimento que não atualiza](guide:tracking-not-updating).
- **Recusada ou não paga:** se recusares ou não conseguires pagar, o estafeta regista entrega falhada e a encomenda pode voltar à loja: `Le colis va être renvoyé à l'expéditeur.` e depois `Le colis a été retourné à l'adresse de l'expéditeur.` Vê [devolvida ao remetente](guide:returned-to-sender).
- **Entregue mas não recebida:** a Chronodiali disponibiliza às lojas prova digital de entrega na área de cliente. Pede à loja para a verificar. Mais em [entregue mas não recebida](guide:delivered-not-received).
- **Danificada ou perdida:** avisa primeiro a loja. Podes também assinalar «Réclamation ?» no formulário de contacto da Chronodiali, ou ligar ou escrever com o teu número. Vê [encomendas danificadas](guide:damaged-parcel) e [encomendas perdidas](guide:lost-parcel).
- **Uma mensagem a pedir dinheiro:** no site, a Chronodiali descreve o pagamento como feito na entrega, em dinheiro ou cartão. Se um SMS ou e-mail pedir pagamento através de uma ligação, verifica primeiro o seguimento e consulta [SMS fraudulentos de entrega](guide:delivery-scam-texts).

## Número de contacto e apoio ao cliente da Chronodiali

Contactos publicados em chronodiali.ma:

- **Telefone:** +212 5 20 61 62 63 (05 20 61 62 63 a partir de Marrocos).
- **E-mail:** service-client@chronodiali.ma.
- **Formulário de contacto:** na página [Contact](https://chronodiali.ma/contact_us.html), escolhe «Réclamation ?» para reclamação ou «Demande d'information ?» para uma dúvida. O formulário pede apenas nome, e-mail, telefone e nome da loja, sem campo para mensagem ou número de seguimento; a Chronodiali indica que entrará em contacto contigo.
- **Agências:** as seis cidades acima, com o mesmo telefone e e-mail.

O site não indica horários específicos para a linha telefónica nem lista número de WhatsApp.

## Seguir uma encomenda Chronodiali com o Peek

Cola um número `CHPU` e o [Peek](/) identifica logo a Chronodiali em «Transportadora detetada». Minúsculas e espaços não importam. Uma ligação para a página de seguimento em chronodiali.ma também funciona.

Um número `CD` de 10 dígitos não basta para identificar a transportadora. Cola-o na mesma: o Peek acompanha mais de 4 500 transportadoras. Mostra «A verificar serviços de seguimento…» enquanto pergunta à Chronodiali e às outras transportadoras com o mesmo formato; se apenas a Chronodiali tiver a encomenda, surge o seu nome com «tem esta encomenda». Ou toca em «Alterar» e escolhe «Chronodiali» em «Corresponde a este número». Se escolheres Chronodiali para um número com outro formato, o Peek indica que a Chronodiali «não emite números como este» e a encomenda mostra «Verifica o número de seguimento».

O Peek lê diretamente o seguimento da Chronodiali: o estado, cada linha tal como escrita pela Chronodiali (em francês, ou em inglês nos dois casos acima), o nome do hub como local quando existe, o país de destino, a hora da entrega e, quando a Chronodiali os fornece, o nome do destinatário, o destino e quem assinou. O Peek apresenta as horas no fuso horário do teu dispositivo. Ficam de fora o nome e a cidade do remetente, o valor a pagar, a prova de entrega e as chamadas do estafeta. A Chronodiali não indica data prevista de entrega nem peso. As linhas sobre um parceiro terceiro não nomeiam o parceiro, e o Peek não acompanha o seguimento desse parceiro.

Se a Chronodiali ainda não conhecer o número, o Peek mostra «À espera de Chronodiali» e continua a verificar: até de 10 em 10 minutos, e de 2 em 2 a partir das 6h locais no dia em que a Chronodiali regista a encomenda em distribuição. Após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Pode notificar-te em «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega». Não pode pagar, reagendar uma entrega nem contactar a Chronodiali ou a loja por ti.

## Perguntas sobre o seguimento Chronodiali

### A Chronodiali faz parte dos Poste Maroc?

Em parte. A página de notícias da Chronodiali descreve-a como uma parceria: a Barid Al-Maghrib (Poste Maroc) detém 51 % e a Geopost 49 %. Tem seguimento próprio em chronodiali.ma e apoio ao cliente próprio.

### Quantas vezes tenta a Chronodiali entregar?

Três vezes, segundo a Chronodiali. No Peek, cada tentativa falhada surge numa linha que começa por `La livraison a échoué`, com ou sem motivo.

### Posso pagar uma encomenda Chronodiali com cartão?

Sim. A Chronodiali refere que podes pagar na entrega em dinheiro ou por cartão bancário.

### O que significa «partenaire tiers» no seguimento Chronodiali?

`Le colis a été assigné à un partenaire tiers.` e `Le colis a été confié à un partenaire tiers.` significam que a Chronodiali atribuiu ou entregou a encomenda a uma empresa parceira. O seguimento não indica o nome do parceiro: aguarda a próxima atualização ou pergunta à Chronodiali com o teu número.

### Como contacto a Chronodiali sobre a minha encomenda?

Liga para +212 5 20 61 62 63 ou escreve para service-client@chronodiali.ma com o número de seguimento. Para reembolso ou novo envio, fala com a loja.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib e Geopost, pagamento na entrega em dinheiro ou cartão, entrega ao domicílio ou em agência, mais de 300 destinos, agências e horários, telefone, e-mail
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – seguimento, exemplo `CHPU`, valor a pagar, linhas de estado em francês, o que a página mostra e «En cours de traitement.» (no script da página)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – três tentativas de entrega, notificações SMS e e-mail, dinheiro ou cartão, prova digital de entrega
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – entrega em todo o Marrocos, agências e horários
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – contas e recolhas das lojas, provas de entrega para lojas
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – formulário de contacto com «Réclamation ?» e respetivos campos, telefone, e-mail
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – parceria, 51 % Barid Al-Maghrib e 49 % Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; termos: contrato e recolhas da loja, prazos dados na encomenda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de números, linhas de estado e etapas, o que a página Chronodiali e o Peek mostram
:::
