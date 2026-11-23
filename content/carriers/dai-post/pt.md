---
title: Seguimento DAI Post: números, estados e ajuda de entrega
description: Acompanha uma encomenda DAI Post (Parcel Right) na Austrália, Nova Zelândia ou Singapura: números, estados, entrega sem assinatura, recolha e contactos.
slug: seguimento-dai-post
published: 2026-11-23
updated: 2026-11-23
---

A DAI Post é uma empresa australiana de entregas para lojas online, fundada em Melbourne em 2000. Traz encomendas dos EUA, Reino Unido, Europa e Ásia até compradores na Austrália, Nova Zelândia e Singapura. A sua entrega própria de última milha chama-se Parcel Right. Segue uma encomenda na [página de seguimento da DAI Post](https://daipost.com/track) ou cola o número na caixa acima. O número consta da confirmação de pedido da loja; se não o encontrares, pergunta à loja.

## Número de seguimento DAI Post: que aspeto tem

A DAI Post segue os números que as lojas imprimem nas encomendas, pelo que surgem em vários formatos. O seu seguimento aceita até 10 de cada vez. Os formatos que o código do Peek conhece, com dígitos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `NZC482910375` | `NZC` e nove dígitos: no código do Peek, o número DAI Post para encomendas enviadas para a Nova Zelândia. O Peek identifica logo a DAI Post. |
| `00393580944829103754` | 20 dígitos começados por `0039358094`, sendo o último um dígito de controlo: um código de barras SSCC de etiqueta, por vezes impresso como `(00) 3 9358094 482910375 4`. Outras transportadoras também usam 20 dígitos, pelo que o Peek não consegue ter certeza apenas pela forma. |
| `KPR48291037561` | Referência própria da loja, letras seguidas de dígitos. O Peek não a reconhece pela forma. |

Espaços, parênteses e minúsculas não importam. Se um dígito num número de 20 dígitos estiver errado, o dígito de controlo falha e o Peek não o associa à DAI Post: confirma o número. Mais em [como encontrar um número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento DAI Post explicados

O seguimento da DAI Post mostra uma frase informal para cada etapa. Algumas encomendas mostram uma forma mais curta do mesmo passo, como `Checked in at origin hub`. O Peek lê o código da etapa, por isso ambas significam o mesmo. As linhas que o Peek conhece, por ordem:

| A DAI Post diz | O que significa | O Peek mostra |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` ou `Customer label information received` | A loja enviou os teus dados; a DAI Post ainda não tem a encomenda. | «Etiqueta criada» |
| `Your parcel’s checked in and being prepared for its journey.` ou `Checked in at origin hub` | O hub de origem da DAI Post já recebeu a encomenda. | «Aceite pela transportadora» |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` ou `Parcel processed at origin facility` | Triada e pronta a partir. | «Em trânsito» |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` ou `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | O voo está atrasado ou há atraso em trânsito. | «Em trânsito» |
| `Your parcel is in the air and is heading your way.` ou `Parcel departed origin country` | Em voo a sair do país de origem. | «Em trânsito» |
| `Parcel arrived in trans-shipment country.`, depois `Your parcel’s left the transfer country and is back on the move.` | Escala num país de trânsito. | «Em trânsito» |
| `Touchdown! Your parcel has landed.` ou `Parcel arrived in destination country` | Chegou ao teu país. | «Em trânsito» |
| `We’ve collected your parcel from the airline and it’s being processed.` ou `Collected from the airline for processing` | A DAI Post recolheu-a na companhia aérea. | «Em trânsito» |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` ou `Parcel processed by clearance facility` | A alfândega está a verificá-la. | «Na alfândega» |
| `Customs clearance delay` | A alfândega ainda a retém. | «Na alfândega» |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` ou `Customs cleared` | Libertada pela alfândega. | «Em trânsito» |
| `We’ve got your parcel and it’s been sorted for delivery.` ou `Parcel received and sorted` | Triada para distribuição. | «Em trânsito» |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | Em viagem entre estados. | «Em trânsito» |
| `Your parcel’s arrived at our local facility, it's on the way.` | Num depósito perto de ti. | «Em trânsito» |
| `Your parcel’s on its way to you, almost there!` | A caminho do depósito de entrega, ainda sem sair em distribuição. | «Em trânsito» |
| `It’s happening, your parcel will be delivered today. Get ready!` ou `Parcel out for final mile carrier delivery` | Em distribuição hoje. | «Em distribuição» |
| `Parcel unsafe to leave at address` | O motorista não a deixou no local (vê abaixo). | «Tentativa de entrega» |
| `Parcel available for collection` | À espera num ponto de recolha. | «Pronta para levantamento» |
| `Delivered! Your parcel has arrived. Enjoy!` | Entregue. | «Entregue» |
| `Delivered, left where you can find it.` ou `Parcel delivered - authority to leave` | Deixada na tua morada sem assinatura. | «Entregue» |

Para um número que não conhece, o seguimento da DAI Post responde `Sorry, we couldn't find your parcel.` Mais em [o que significam os estados de seguimento](guide:tracking-statuses).

**Horas.** A DAI Post indica cada hora no relógio local do local onde foi registada, sem fuso horário. O Peek deduz o fuso a partir da localidade e apresenta as horas no fuso horário do teu dispositivo; a hora da primeira linha pode desviar-se.

## Entrega DAI Post: entrega sem assinatura, recolha e devoluções

A DAI Post refere que a sua rede cobre 100 % da população australiana. A hora depende da rota do motorista, que muda conforme os envios do dia e o trânsito.

- **Autorização para deixar (*Authority to leave*).** Não exige assinatura. Se ninguém atender e o motorista considerar o local seguro, deixa a encomenda.
- **Local pouco seguro.** Se ficasse demasiado exposta, segue para o ponto de recolha mais próximo da tua zona e a DAI Post envia instruções. No site da DAI Post, `Parcel available for collection` abre um mapa do local; o Peek mostra apenas a localidade.
- **7 dias para levantar.** Uma encomenda não levantada em 7 dias volta ao depósito da DAI Post e depois à loja. O mesmo acontece com morada incompleta.
- **Mensagens.** Só recebes notificações dos passos principais se a loja entregou à DAI Post o teu e-mail e número de telemóvel.

Os termos da DAI Post acrescentam que, se não estiveres presente, a encomenda pode ficar num local seguro ou ir para um *Collection Point*, contando então como entregue. As suas páginas não indicam quem faz a entrega final na Nova Zelândia ou em Singapura. Vê [entrega falhada](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Alfândega numa encomenda DAI Post

**Retida na alfândega (*Customs held*).** A página de ajuda da DAI Post explica que *Customs held* significa que a ABF ou o DAFF — agências australianas de fronteiras e biossegurança — precisam provavelmente de mais dados antes da libertação. Pergunta ao apoio ao cliente da DAI Post o que falta: o formulário tem o motivo «My parcel is Customs held.»

**Taxas.** Os termos da DAI Post responsabilizam o remetente pelas despesas do envio, incluindo impostos e direitos aduaneiros. Se recusares pagar uma taxa na entrega, o remetente continua devedor e a DAI Post pode reter a encomenda até ao pagamento ou devolvê-la ao remetente. O que eventualmente tens a pagar depende das regras do teu país: vê [encomendas retidas na alfândega](guide:customs). Uma mensagem sobre taxas parece suspeita? Vê [SMS fraudulentos de entrega](guide:delivery-scam-texts).

## Seguimento DAI Post parado, encomenda perdida ou devolvida

**Sem novidades há dias.** A DAI Post regista encomendas apenas em pontos-chave e lembra que continuam em movimento entre eles. Em envios interestaduais ou regionais pode parecer parada vários dias. Se a encomenda ficar quatro dias sem uma linha nova em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde …» com o dia dessa linha e uma ligação «Pede ajuda a DAI Post». Vê [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Ainda «Etiqueta criada».** A DAI Post ainda não recebeu a encomenda. Se permanecer assim durante dias, pergunta à loja se já a enviou.

**Entregue, mas não está lá.** Com entrega sem assinatura, procura primeiro à volta de casa. A DAI Post pode enviar por e-mail prova de entrega *Authority to leave*: pede pelo formulário e contacta depois a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Para uma encomenda que nunca chegou, usa o formulário da DAI Post com o motivo «I did not receive my parcel.» Em qualquer caso, foi a loja que contratou a entrega: pede reembolso ou novo envio. Vê [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto da DAI Post

Canais de contacto da DAI Post:

- **Telefone:** Austrália +61 3 8586 1200, Nova Zelândia +64 800 451 718, Singapura +65 800 321 1587. Não são indicados horários de atendimento.
- **E-mail:** consumercare@daipost.com.
- **Online:** o [formulário de contacto](https://daipost.com/contact). Pede nome, telefone, e-mail, número de seguimento e motivo do pedido.

## Seguir uma encomenda DAI Post com o Peek

O [Peek](/) segue uma encomenda DAI Post apenas com o número: sem conta nem código postal.

- **Um número `NZC`.** O Peek identifica logo a DAI Post («Transportadora detetada»). Se, dois dias depois da primeira resposta, a DAI Post continuar sem conhecer o número, o Peek pede-te que o verifiques, escolhas outra transportadora ou apagues a encomenda.
- **Um número de 20 dígitos começado por `0039358094`.** Cola-o na mesma: o Peek acompanha mais de 4 500 transportadoras. Enquanto verifica vês «A verificar serviços de seguimento…», e a DAI Post está entre as primeiras consultadas. Se a DAI Post a tiver, o Peek mostra-a. Se ainda não, a encomenda indica «À espera do primeiro registo de DAI Post» e o cartão diz «Provavelmente DAI Post»; após uma semana, o Peek sugere confirmar o número com o remetente. Para escolher DAI Post manualmente, toca em «Alterar»: surge em «Corresponde a este número».
- **Qualquer outro número.** Cola-o na mesma, toca em «Alterar» e escolhe DAI Post (pesquisa DAI Post ou Parcel Right). Numa encomenda já adicionada, usa «Alterar transportadora».
- **Uma ligação.** Cola uma ligação de seguimento de daipost.com, parcelright.com.au ou parcelrig.ht: o Peek extrai o número e identifica logo a DAI Post.

O Peek mostra o estado, cada linha nas palavras da DAI Post, a cidade e país de cada registo, a data prevista de entrega quando a DAI Post a fornece e a hora da entrega. Não mostra ponto de recolha, nome de serviço, peso nem prova de entrega. «Abrir o site de DAI Post» abre o número no seguimento oficial. No código do Peek, algumas encomendas da [Asendia](carrier:asendia), e algumas da [ePost Global](carrier:epost-global) para a Nova Zelândia, chegam à DAI Post: o seu seguimento inclui os registos dela.

Enquanto a encomenda está aberta no Peek, ou no dia da entrega com as notificações ativas, o Peek verifica-a até de 10 em 10 minutos, e de 2 em 2 a partir das 6h locais no dia em que a DAI Post regista a encomenda em distribuição. Após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora, e de 6 em 6 horas após sete dias sem novidades em «Etiqueta criada». As notificações podem seguir «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a DAI Post nem a loja por ti.

## Perguntas sobre o seguimento DAI Post

### A Parcel Right é o mesmo que a DAI Post?

A Parcel Right é a entrega própria de última milha da DAI Post. No Peek podes pesquisar qualquer um dos dois nomes.

### O que significa «Parcel unsafe to leave at address»?

O motorista não encontrou ninguém e o local não era seguro. A encomenda vai para o ponto de recolha mais próximo: levanta-a em 7 dias, caso contrário volta à loja.

### Quanto tempo demora a DAI Post a entregar?

Os termos da DAI Post indicam que os prazos normais são estimativas, não garantias. O seguimento pode mostrar uma data prevista de entrega, também visível no Peek.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, encomendas dos EUA, Reino Unido, Europa e Ásia para Austrália, Nova Zelândia e Singapura, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – telefones, cobertura, prazos, sem atualizações recentes, notificações, entrega sem assinatura, recolha, devoluções, alfândega, prova de entrega
- [DAI Post: Delivery tracking](https://daipost.com/track) – o seguimento, até 10 números
- [DAI Post: Contact](https://daipost.com/contact) – formulário de pedido e motivos, telefones, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – local seguro ou Collection Point, prazos estimados, taxas
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – nomes, formatos de números, ligações, textos e etapas, relógios, entrega prevista
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção, registos da DAI Post na Asendia e na ePost Global
:::
